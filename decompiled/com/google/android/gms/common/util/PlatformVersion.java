package com.google.android.gms.common.util;

import android.os.Build.VERSION;

@VisibleForTesting
public final class PlatformVersion {
    private PlatformVersion() {
    }

    public static boolean isAtLeastFroyo() {
        return checkVersion(8);
    }

    public static boolean isAtLeastGingerbread() {
        return checkVersion(9);
    }

    public static boolean isAtLeastGingerbreadMR1() {
        return checkVersion(10);
    }

    public static boolean isAtLeastHoneycomb() {
        return checkVersion(11);
    }

    public static boolean isAtLeastHoneycombMR1() {
        return checkVersion(12);
    }

    public static boolean isAtLeastHoneycombMR2() {
        return checkVersion(13);
    }

    public static boolean isAtLeastIceCreamSandwich() {
        return checkVersion(14);
    }

    public static boolean isAtLeastIceCreamSandwichMR1() {
        return checkVersion(15);
    }

    public static boolean isAtLeastJellyBean() {
        return checkVersion(16);
    }

    public static boolean isAtLeastJellyBeanMR1() {
        return checkVersion(17);
    }

    public static boolean isAtLeastJellyBeanMR2() {
        return checkVersion(18);
    }

    @Deprecated
    public static boolean isAtLeastKeyLimePie() {
        return isAtLeastKitKat();
    }

    public static boolean isAtLeastKitKat() {
        return checkVersion(19);
    }

    public static boolean isAtLeastKitKatWatch() {
        return checkVersion(20);
    }

    @Deprecated
    public static boolean isAtLeastL() {
        return isAtLeastLollipop();
    }

    public static boolean isAtLeastLollipop() {
        return checkVersion(21);
    }

    public static boolean isAtLeastLollipopMR1() {
        return checkVersion(22);
    }

    public static boolean isAtLeastM() {
        return checkVersion(23);
    }

    private static boolean checkVersion(int minSdkVersion) {
        return VERSION.SDK_INT >= minSdkVersion;
    }
}
