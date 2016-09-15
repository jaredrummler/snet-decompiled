package com.google.android.snet;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

class PreferredPackageFinder {
    private static final String PACKAGE_MIME_TYPE = "application/vnd.android.package-archive";
    private static final String RANDOM_NAME;
    private static final int RANDOM_NAME_BITS = 130;
    private static final int RANDOM_NAME_RADIX = 16;
    private static final String[] WEB_BROWSER_WHITELIST;
    private PackageManager mPackageManager;

    static {
        RANDOM_NAME = getRandomName();
        WEB_BROWSER_WHITELIST = new String[]{"com.android.browser", "com.android.chrome"};
    }

    private static String getRandomName() {
        return new BigInteger(RANDOM_NAME_BITS, new SecureRandom()).toString(RANDOM_NAME_RADIX);
    }

    PreferredPackageFinder(Context ctx) {
        this.mPackageManager = ctx.getPackageManager();
    }

    PackageInfo findWhitelistedWebBrowser() {
        PackageInfo packageInfo = getPreferredPackage(new Intent("android.intent.action.VIEW", Uri.parse("http://" + RANDOM_NAME + ".com")));
        return (packageInfo == null || !Arrays.asList(WEB_BROWSER_WHITELIST).contains(packageInfo.packageName)) ? null : packageInfo;
    }

    PackageInfo findPackageInstaller() {
        Uri uri = Uri.parse("file://" + RANDOM_NAME + ".apk");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, PACKAGE_MIME_TYPE);
        return getPreferredPackage(intent);
    }

    private PackageInfo getPreferredPackage(Intent intent) {
        PackageInfo packageInfo = null;
        ResolveInfo resolveInfo = this.mPackageManager.resolveActivity(intent, PeopleColumnBitmask.AFFINITY_2);
        if (resolveInfo != null) {
            try {
                packageInfo = this.mPackageManager.getPackageInfo(resolveInfo.activityInfo.packageName, 0);
            } catch (NameNotFoundException e) {
            }
        }
        return packageInfo;
    }
}
