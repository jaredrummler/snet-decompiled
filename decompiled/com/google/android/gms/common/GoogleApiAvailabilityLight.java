package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.GmsIntents;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class GoogleApiAvailabilityLight {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailabilityLight INSTANCE;
    private static final String TRACKING_ID_PREFIX = "gcore_";
    private static final String TRACKING_ID_SEPARATOR = "-";

    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        INSTANCE = new GoogleApiAvailabilityLight();
    }

    public static GoogleApiAvailabilityLight getInstance() {
        return INSTANCE;
    }

    GoogleApiAvailabilityLight() {
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int errorCode = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, errorCode)) {
            return 18;
        }
        return errorCode;
    }

    public void verifyGooglePlayServicesIsAvailable(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context);
    }

    public boolean isUserResolvableError(int errorCode) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(errorCode);
    }

    @Nullable
    @Deprecated
    public Intent getErrorResolutionIntent(int errorCode) {
        return getErrorResolutionIntent(null, errorCode, null);
    }

    @Nullable
    public Intent getErrorResolutionIntent(Context context, int errorCode, @Nullable String trackingSource) {
        switch (errorCode) {
            case Type.TEMPORARY /*1*/:
            case Type.INDEFINITELY /*2*/:
                return GmsIntents.createPlayStoreIntent(GOOGLE_PLAY_SERVICES_PACKAGE, getTrackingId(context, trackingSource));
            case Type.CUSTOM /*3*/:
                return GmsIntents.createSettingsIntent(GOOGLE_PLAY_SERVICES_PACKAGE);
            case LogSource.PHOTOS /*42*/:
                return GmsIntents.createAndroidWearUpdateIntent();
            default:
                return null;
        }
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int errorCode, int requestCode) {
        return getErrorResolutionPendingIntent(context, errorCode, requestCode, null);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int errorCode, int requestCode, @Nullable String trackingSource) {
        Intent intent = getErrorResolutionIntent(context, errorCode, trackingSource);
        if (intent == null) {
            return null;
        }
        return PendingIntent.getActivity(context, requestCode, intent, 268435456);
    }

    public void cancelAvailabilityErrorNotifications(Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return GooglePlayServicesUtilLight.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int getClientVersion(Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }

    public int getApkVersion(Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    public boolean isPlayServicesPossiblyUpdating(Context context, int errorCode) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, errorCode);
    }

    public boolean isPlayStorePossiblyUpdating(Context context, int errorCode) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, errorCode);
    }

    public boolean isUninstalledAppPossiblyUpdating(Context context, String packageName) {
        return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context, packageName);
    }

    public String getErrorString(int errorCode) {
        return GooglePlayServicesUtilLight.getErrorString(errorCode);
    }

    private String getTrackingId(@Nullable Context context, @Nullable String source) {
        StringBuilder builder = new StringBuilder();
        builder.append(TRACKING_ID_PREFIX);
        builder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        builder.append(TRACKING_ID_SEPARATOR);
        if (!TextUtils.isEmpty(source)) {
            builder.append(source);
        }
        builder.append(TRACKING_ID_SEPARATOR);
        if (context != null) {
            builder.append(context.getPackageName());
        }
        builder.append(TRACKING_ID_SEPARATOR);
        if (context != null) {
            try {
                builder.append(context.getPackageManager().getPackageInfo(context.getPackageName(), GOOGLE_PLAY_SERVICES_VERSION_CODE).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return builder.toString();
    }
}
