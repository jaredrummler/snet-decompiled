package com.google.android.gsf;

public class MobileConnectionSettings {
    public static String getDeviceId(long androidId) {
        return "android-" + Long.toHexString(androidId);
    }
}
