package com.google.android.gms.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GmsVersionParser {
    public static final int UNKNOWN = -1;
    private static Pattern sBuildNumberPattern;

    static {
        sBuildNumberPattern = null;
    }

    public static long parseBuildNumber(String versionName) {
        if (sBuildNumberPattern == null) {
            sBuildNumberPattern = Pattern.compile("\\(\\d+-");
        }
        Matcher matcher = sBuildNumberPattern.matcher(versionName);
        if (!matcher.find()) {
            return -1;
        }
        String buildNumber = matcher.group();
        return Long.parseLong(buildNumber.substring(1, buildNumber.length() + UNKNOWN));
    }

    public static int parseBuildVersion(int versionCode) {
        return versionCode / 1000;
    }

    public static int parseBuildMajorVersion(int versionCode) {
        return versionCode / 100000;
    }

    public static int parseBuildType(int versionCode) {
        return (versionCode % 1000) / 100;
    }

    public static int parseTargetArchitecture(int versionCode) {
        return (versionCode % 100) / 10;
    }

    public static int parseScreenDensity(int versionCode) {
        return versionCode % 10;
    }

    private GmsVersionParser() {
    }
}
