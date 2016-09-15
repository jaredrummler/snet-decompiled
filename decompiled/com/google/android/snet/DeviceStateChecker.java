package com.google.android.snet;

import android.text.TextUtils;
import com.google.android.gms.people.PeopleConstants.Endpoints;
import java.lang.reflect.InvocationTargetException;

class DeviceStateChecker {
    private static final boolean DEBUG = false;
    private static final String SYSTEM_PROPERTY_UNKNOWN = "Unknown";
    private static final String TAG;

    static class DeviceState {
        int oemLocked;
        int oemUnlockSupported;
        String securityPatchLevel;
        String verifiedBootState;
        String verityMode;

        DeviceState() {
        }
    }

    DeviceStateChecker() {
    }

    static {
        TAG = DeviceStateChecker.class.getCanonicalName();
    }

    private static String systemPropertyStringValue(String propertyName) {
        try {
            String result = (String) Class.forName("android.os.SystemProperties").getMethod(Endpoints.ENDPOINT_GET, new Class[]{String.class}).invoke(null, new Object[]{propertyName});
            if (TextUtils.isEmpty(result)) {
                return SYSTEM_PROPERTY_UNKNOWN;
            }
            return result;
        } catch (ClassNotFoundException e) {
            return SYSTEM_PROPERTY_UNKNOWN;
        } catch (NoSuchMethodException e2) {
            return SYSTEM_PROPERTY_UNKNOWN;
        } catch (IllegalArgumentException e3) {
            return SYSTEM_PROPERTY_UNKNOWN;
        } catch (IllegalAccessException e4) {
            return SYSTEM_PROPERTY_UNKNOWN;
        } catch (InvocationTargetException e5) {
            return SYSTEM_PROPERTY_UNKNOWN;
        }
    }

    private static int systemPropertyIntValue(String propertyName) {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{propertyName, Integer.valueOf(-1)})).intValue();
        } catch (ClassNotFoundException e) {
            return -1;
        } catch (NoSuchMethodException e2) {
            return -1;
        } catch (IllegalArgumentException e3) {
            return -1;
        } catch (IllegalAccessException e4) {
            return -1;
        } catch (InvocationTargetException e5) {
            return -1;
        }
    }

    static DeviceState getDeviceState() {
        DeviceState deviceState = new DeviceState();
        deviceState.verifiedBootState = systemPropertyStringValue("ro.boot.verifiedbootstate");
        deviceState.verityMode = systemPropertyStringValue("ro.boot.veritymode");
        deviceState.securityPatchLevel = systemPropertyStringValue("ro.build.version.security_patch");
        deviceState.oemUnlockSupported = systemPropertyIntValue("ro.oem_unlock_supported");
        deviceState.oemLocked = systemPropertyIntValue("ro.boot.flash.locked");
        return deviceState;
    }
}
