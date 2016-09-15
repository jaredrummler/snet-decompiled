package com.google.android.snet;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.auth.firstparty.recovery.RecoveryParamConstants;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DeviceAdminDeactivator {
    private static final boolean DEBUG = false;
    private static final String TAG;

    static class BadBlacklistException extends RuntimeException {
        final DeviceAdminBlacklist blacklist;

        public BadBlacklistException(DeviceAdminBlacklist blacklist) {
            this.blacklist = blacklist;
        }
    }

    static class DeviceAdminBlacklist {
        private final HashMap<String, Set<String>> mBlacklistedDeviceAdmins;

        DeviceAdminBlacklist(HashMap<String, Set<String>> blacklistedDeviceAdmins) {
            this.mBlacklistedDeviceAdmins = blacklistedDeviceAdmins;
        }

        Set<String> getAllPackageNames() {
            return this.mBlacklistedDeviceAdmins.keySet();
        }

        boolean packageNameInBlacklist(String packageName) {
            return this.mBlacklistedDeviceAdmins.containsKey(packageName);
        }

        boolean digestInBlacklist(String packageName, String sha256Prefix) {
            Set<String> digestPrefixes = (Set) this.mBlacklistedDeviceAdmins.get(packageName);
            if (digestPrefixes == null) {
                return false;
            }
            if (digestPrefixes.isEmpty()) {
                return true;
            }
            return digestPrefixes.contains(sha256Prefix);
        }

        private static DeviceAdminBlacklist loadBlacklistFromResource() {
            DeviceAdminBlacklist deviceAdminBlacklist = null;
            InputStream blacklistStream = DeviceAdminBlacklist.class.getClassLoader().getResourceAsStream("device_admin_blacklist.txt");
            if (blacklistStream != null) {
                byte[] buf = new byte[PeopleColumnBitmask._ID];
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int bytesRead = blacklistStream.read(buf);
                        if (bytesRead != -1) {
                            output.write(buf, 0, bytesRead);
                        } else {
                            try {
                                break;
                            } catch (UnsupportedEncodingException e) {
                            }
                        }
                    } catch (IOException e2) {
                    }
                }
                deviceAdminBlacklist = loadBlacklistFromString(output.toString("UTF-8"));
            }
            return deviceAdminBlacklist;
        }

        static DeviceAdminBlacklist loadBlacklistFromString(String packageList) {
            HashMap<String, Set<String>> packageBlacklist = parsePackageList(packageList);
            if (packageBlacklist.isEmpty()) {
                return null;
            }
            return new DeviceAdminBlacklist(packageBlacklist);
        }

        private static HashMap<String, Set<String>> parsePackageList(String packages) {
            HashMap<String, Set<String>> blacklistedPackages = new HashMap();
            for (String pkg : packages.split(Csv.NEWLINE)) {
                String pkg2 = pkg2.trim();
                if (pkg2.length() != 0) {
                    String[] parts = pkg2.trim().split(Csv.COMMA, 2);
                    if (parts.length == 1) {
                        blacklistedPackages.put(parts[0], new HashSet(0));
                    } else {
                        blacklistedPackages.put(parts[0], new HashSet(Arrays.asList(parts[1].toLowerCase().split(Csv.COMMA))));
                    }
                }
            }
            return blacklistedPackages;
        }
    }

    DeviceAdminDeactivator() {
    }

    static {
        TAG = DeviceAdminDeactivator.class.getSimpleName();
    }

    static List<AppInfo> deactivateDeviceAdmins(Context ctx) {
        List<AppInfo> deactivatedDeviceAdmins = new ArrayList();
        PackageManager packageManager = ctx.getPackageManager();
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService(RecoveryParamConstants.VALUE_ACTIVITY);
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) ctx.getSystemService("device_policy");
        List<ComponentName> activeAdmins = devicePolicyManager.getActiveAdmins();
        if (!(activeAdmins == null || activeAdmins.isEmpty())) {
            DeviceAdminBlacklist blacklist = DeviceAdminBlacklist.loadBlacklistFromResource();
            if (blacklist != null) {
                for (ComponentName activeAdmin : activeAdmins) {
                    if (blacklist.packageNameInBlacklist(activeAdmin.getPackageName())) {
                        AppInfo appInfo = ApplicationInfoUtils.appInfo(packageManager, activeAdmin.getPackageName());
                        if (appInfo != null) {
                            if (blacklist.digestInBlacklist(activeAdmin.getPackageName(), appInfo.apkSha256.toLowerCase().substring(0, 8))) {
                                boolean success;
                                try {
                                    devicePolicyManager.removeActiveAdmin(activeAdmin);
                                    success = true;
                                } catch (SecurityException e) {
                                    success = false;
                                }
                                if (success) {
                                    deactivatedDeviceAdmins.add(appInfo);
                                    forceStopPackage(activityManager, activeAdmin.getPackageName());
                                }
                            }
                        }
                    }
                }
            }
        }
        return deactivatedDeviceAdmins;
    }

    private static void forceStopPackage(ActivityManager am, String packageName) {
        try {
            am.getClass().getMethod("forceStopPackage", new Class[]{String.class}).invoke(am, new Object[]{packageName});
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e2) {
        } catch (InvocationTargetException e3) {
        } catch (SecurityException e4) {
        }
    }
}
