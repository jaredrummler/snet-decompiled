package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;

public class ClientLibraryUtils {
    public static final int GMS_CLIENT_VERSION_UNKNOWN = -1;

    private ClientLibraryUtils() {
    }

    public static int getClientVersion(Context context, String packageName) {
        return getClientVersion(getPackageInfo(context, packageName));
    }

    public static int getClientVersion(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return GMS_CLIENT_VERSION_UNKNOWN;
        }
        Bundle metaData = packageInfo.applicationInfo.metaData;
        if (metaData != null) {
            return metaData.getInt(GooglePlayServicesUtilLight.KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION, GMS_CLIENT_VERSION_UNKNOWN);
        }
        return GMS_CLIENT_VERSION_UNKNOWN;
    }

    public static PackageInfo getPackageInfo(Context context, String packageName) {
        try {
            return context.getPackageManager().getPackageInfo(packageName, LogSource.KEEP);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static boolean isPackageStopped(Context context, String packageName) {
        try {
            if ((context.getPackageManager().getApplicationInfo(packageName, 0).flags & AccessibilityNodeInfoCompat.ACTION_SET_TEXT) != 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean isPackageSide() {
        return BuildConstants.IS_PACKAGE_SIDE && GservicesValue.isInitialized() && GservicesValue.getSharedUserId() == Process.myUid();
    }
}
