package com.google.android.snet;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.google.android.gms.auth.firstparty.recovery.RecoveryParamConstants;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ApplicationInfoUtils {
    private final Context mContext;
    private final GBundle mGBundle;
    private PackageManager mPackageManager;
    private Map<Integer, List<AppInfo>> mPidToAppInfoListCache;
    private Map<Integer, Integer> mPidToUidMap;

    static class AppInfo {
        public String apkSha256;
        public byte[] apkSha256Bytes;
        public String packageName;
        public List<String> signatureSha256;
        public byte[][] signatureSha256Bytes;
        public boolean systemApp;

        AppInfo() {
        }
    }

    ApplicationInfoUtils(Context context, GBundle gBundle) {
        this.mContext = context;
        this.mGBundle = gBundle;
        this.mPackageManager = this.mContext.getPackageManager();
        this.mPidToAppInfoListCache = new HashMap();
        this.mPidToUidMap = pidToUidMap();
    }

    Map<Integer, Integer> pidToUidMap() {
        List<RunningAppProcessInfo> runningAppInfos = ((ActivityManager) this.mContext.getSystemService(RecoveryParamConstants.VALUE_ACTIVITY)).getRunningAppProcesses();
        Map<Integer, Integer> pidToUidMap = new HashMap();
        for (RunningAppProcessInfo runningAppInfo : runningAppInfos) {
            if (runningAppInfo.pid != 0) {
                pidToUidMap.put(Integer.valueOf(runningAppInfo.pid), Integer.valueOf(runningAppInfo.uid));
            }
        }
        return pidToUidMap;
    }

    AppInfo appInfo(String packageName) {
        return appInfo(this.mPackageManager, packageName);
    }

    static AppInfo appInfo(PackageManager packageManager, String packageName) {
        AppInfo info = new AppInfo();
        info.packageName = packageName;
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 64);
            info.apkSha256Bytes = Utils.getSha256(new File(applicationInfo.sourceDir));
            info.apkSha256 = Utils.toHexString(info.apkSha256Bytes);
            Signature[] signatures = packageInfo.signatures;
            info.signatureSha256 = new ArrayList();
            info.signatureSha256Bytes = new byte[signatures.length][];
            for (int i = 0; i < signatures.length; i++) {
                info.signatureSha256Bytes[i] = digester.digest(signatures[i].toByteArray());
                String signatureSha256 = Utils.toHexString(info.signatureSha256Bytes[i]);
                if (signatureSha256 != null) {
                    info.signatureSha256.add(signatureSha256);
                }
            }
            return info;
        } catch (NameNotFoundException e) {
            return null;
        } catch (NoSuchAlgorithmException e2) {
            return null;
        }
    }

    List<AppInfo> appsForPid(int pid) {
        if (this.mPidToAppInfoListCache.containsKey(Integer.valueOf(pid))) {
            return (List) this.mPidToAppInfoListCache.get(Integer.valueOf(pid));
        }
        if (!this.mPidToUidMap.containsKey(Integer.valueOf(pid))) {
            return null;
        }
        int uid = ((Integer) this.mPidToUidMap.get(Integer.valueOf(pid))).intValue();
        if (uid == -1) {
            return null;
        }
        List<AppInfo> appInfoList = appsForUid(uid);
        this.mPidToAppInfoListCache.put(Integer.valueOf(pid), appInfoList);
        return appInfoList;
    }

    List<AppInfo> appsForUid(int uid) {
        String[] packages = this.mPackageManager.getPackagesForUid(uid);
        if (packages == null) {
            return null;
        }
        List<AppInfo> appInfoList = new ArrayList();
        for (String packageName : packages) {
            AppInfo appInfo = appInfo(packageName);
            if (appInfo != null) {
                appInfoList.add(appInfo);
            }
        }
        return appInfoList;
    }

    List<AppInfo> apps() {
        List<AppInfo> appsInfoList = new ArrayList();
        boolean reportNonSystemApps = this.mGBundle.getReportNonSystemApps();
        boolean reportSystemApps = this.mGBundle.getReportSystemApps();
        if (reportNonSystemApps || reportSystemApps) {
            for (ApplicationInfo applicationInfo : this.mPackageManager.getInstalledApplications(LogSource.KEEP)) {
                boolean isSystemApp = (applicationInfo.flags & 1) != 0;
                if ((isSystemApp && reportSystemApps) || (!isSystemApp && reportNonSystemApps)) {
                    AppInfo currentAppInfo = appInfo(applicationInfo.packageName);
                    if (currentAppInfo != null) {
                        currentAppInfo.systemApp = isSystemApp;
                        appsInfoList.add(currentAppInfo);
                    }
                }
            }
        }
        return appsInfoList;
    }
}
