package com.google.android.gms.common.internal;

public class BuildConstants {
    public static final long APK_BUILD_TIMESTAMP = 1450394707942L;
    public static final int APK_BUILD_VERSION_CODE = 8473000;
    public static final String APK_BUILD_VERSION_NAME = "8.4.73 (2495818-000)";
    public static final boolean APK_IS_GLASS_APK = false;
    public static final boolean APK_IS_TRUSTED_TESTER = false;
    public static final boolean APK_IS_WEARABLE_APK = false;
    public static final boolean IS_PACKAGE_SIDE;
    public static final int JAR_BUILD_VERSION_CODE = 8473000;
    public static final String JAR_BUILD_VERSION_NAME = "8.4.73-000";
    public static final int MAJOR_RELEASE_NUMBER = 19;

    public interface VersionCodeArchitecture {
        public static final int ARM64 = 4;
        public static final int ARMV5 = 1;
        public static final int ARMV7 = 3;
        public static final int MIPS = 5;
        public static final int NON_NATIVE = 0;
        public static final int X86 = 7;
        public static final int X86_64 = 8;
    }

    public interface VersionCodeBuildType {
        public static final int GLASS = 3;
        public static final int INTERNAL = 1;
        public static final int PANO = 8;
        public static final int PROD = 0;
        public static final int PROD_LMP = 2;
        public static final int PROD_MNC = 4;
        public static final int SIDEWINDER = 7;
        public static final int TRUSTED_TESTER = 6;
        public static final int WEARABLE = 5;
    }

    public interface VersionCodeDensity {
        public static final int HDPI = 4;
        public static final int MDPI = 2;
        public static final int MIXED = 0;
        public static final int XHDPI = 6;
        public static final int XXHDPI = 8;
    }

    static {
        IS_PACKAGE_SIDE = isPackageSide();
    }

    private static final boolean isPackageSide() {
        return IS_PACKAGE_SIDE;
    }
}
