package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.GmsVersionParser;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;

public class GooglePlayServicesUtilLight {
    public static final String FEATURE_SIDEWINDER = "cn.google";
    static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
    static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 39789;
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_GAMES_URI_STRING = "http://play.google.com/store/apps/category/GAME";
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static final String GOOGLE_PLAY_STORE_URI_STRING = "http://play.google.com/store/apps/";
    public static final boolean HONOR_DEBUG_CERTIFICATES = false;
    public static final String KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION = "com.google.android.gms.version";
    private static final String KEY_RESTRICTED_PROFILE = "restricted_profile";
    public static final int MAX_ATTEMPTS_NO_SUCH_ALGORITHM = 2;
    static final String TAG = "GooglePlayServicesUtil";
    static final String TRACKING_SOURCE_DIALOG = "d";
    static final String TRACKING_SOURCE_EXCEPTION = "e";
    static final String TRACKING_SOURCE_NOTIFICATION = "n";
    private static final String VALUE_TRUE = "true";
    @GuardedBy("sLock")
    private static String sAppPackageName;
    @VisibleForTesting
    static final AtomicBoolean sCanceledAvailabilityNotification;
    @GuardedBy("sLock")
    private static Integer sDeclaredVersionCache;
    @GuardedBy("sLock")
    @VisibleForTesting
    static int sIsTestKeys;
    @VisibleForTesting
    public static boolean sIsTestMode;
    private static final Object sLock;
    @VisibleForTesting
    public static boolean sTestIsUserBuild;
    private static final AtomicBoolean sUsingApkIndependentContext;

    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = getGooglePlayServicesVersionCode();
        sIsTestMode = HONOR_DEBUG_CERTIFICATES;
        sTestIsUserBuild = HONOR_DEBUG_CERTIFICATES;
        sIsTestKeys = -1;
        sLock = new Object();
        sAppPackageName = null;
        sDeclaredVersionCache = null;
        sCanceledAvailabilityNotification = new AtomicBoolean();
        sUsingApkIndependentContext = new AtomicBoolean();
    }

    public static void enableUsingApkIndependentContext() {
        sUsingApkIndependentContext.set(true);
    }

    GooglePlayServicesUtilLight() {
    }

    private static int getGooglePlayServicesVersionCode() {
        return BuildConstants.JAR_BUILD_VERSION_CODE;
    }

    @Deprecated
    @VisibleForTesting
    public static String getErrorString(int errorCode) {
        return ConnectionResult.getStatusString(errorCode);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        if (BuildConstants.IS_PACKAGE_SIDE) {
            return GOOGLE_PLAY_SERVICES_VERSION_CODE;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e(TAG, "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!GOOGLE_PLAY_SERVICES_PACKAGE.equals(context.getPackageName())) {
            verifyVersionDeclaredInAppMetadata(context);
        }
        try {
            PackageInfo servicePackageInfo = packageManager.getPackageInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 64);
            GoogleSignatureVerifier signatureVerifier = GoogleSignatureVerifier.getInstance();
            if (!DeviceProperties.isWearable(context)) {
                try {
                    if (signatureVerifier.verifySignature(packageManager.getPackageInfo(GOOGLE_PLAY_STORE_PACKAGE, 8256), VALID_PUBLIC_SIGNATURES.KEYS) == null) {
                        Log.w(TAG, "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (signatureVerifier.verifySignature(servicePackageInfo, signatureVerifier.verifySignature(packageManager.getPackageInfo(GOOGLE_PLAY_STORE_PACKAGE, 8256), VALID_PUBLIC_SIGNATURES.KEYS)) == null) {
                        Log.w(TAG, "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (NameNotFoundException e) {
                    Log.w(TAG, "Google Play Store is neither installed nor updating.");
                    return 9;
                }
            } else if (signatureVerifier.verifySignature(servicePackageInfo, VALID_PUBLIC_SIGNATURES.KEYS) == null) {
                Log.w(TAG, "Google Play services signature invalid.");
                return 9;
            }
            if (GmsVersionParser.parseBuildVersion(servicePackageInfo.versionCode) < GmsVersionParser.parseBuildVersion(GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                Log.w(TAG, "Google Play services out of date.  Requires " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + servicePackageInfo.versionCode);
                return MAX_ATTEMPTS_NO_SUCH_ALGORITHM;
            }
            ApplicationInfo appInfo = servicePackageInfo.applicationInfo;
            if (appInfo == null) {
                try {
                    appInfo = packageManager.getApplicationInfo(GOOGLE_PLAY_SERVICES_PACKAGE, GOOGLE_PLAY_SERVICES_VERSION_CODE);
                } catch (NameNotFoundException e2) {
                    Log.wtf(TAG, "Google Play services missing when getting application info.", e2);
                    return 1;
                }
            }
            if (appInfo.enabled) {
                return GOOGLE_PLAY_SERVICES_VERSION_CODE;
            }
            return 3;
        } catch (NameNotFoundException e3) {
            Log.w(TAG, "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static void ensurePlayServicesAvailable(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int code = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context);
        if (code != 0) {
            Intent recoveryIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context, code, TRACKING_SOURCE_EXCEPTION);
            Log.e(TAG, "GooglePlayServices not available due to error " + code);
            if (recoveryIntent == null) {
                throw new GooglePlayServicesNotAvailableException(code);
            }
            throw new GooglePlayServicesRepairableException(code, "Google Play Services not available", recoveryIntent);
        }
    }

    private static void verifyVersionDeclaredInAppMetadata(Context context) {
        if (!sUsingApkIndependentContext.get()) {
            Integer declaredVersion;
            synchronized (sLock) {
                if (sAppPackageName == null) {
                    sAppPackageName = context.getPackageName();
                    try {
                        Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), LogSource.KEEP).metaData;
                        if (metaData != null) {
                            sDeclaredVersionCache = Integer.valueOf(metaData.getInt(KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION));
                        } else {
                            sDeclaredVersionCache = null;
                        }
                    } catch (NameNotFoundException ex) {
                        Log.wtf(TAG, "This should never happen.", ex);
                    }
                } else if (!sAppPackageName.equals(context.getPackageName())) {
                    throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + sAppPackageName + "' and this call used package '" + context.getPackageName() + "'.");
                }
                declaredVersion = sDeclaredVersionCache;
            }
            if (declaredVersion == null) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (declaredVersion.intValue() != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but" + " found " + declaredVersion + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + KEY_METADATA_GOOGLE_PLAY_SERVICES_VERSION + "\" android:value=\"@integer/google_play_services_version\" />");
            }
        }
    }

    public static boolean isGooglePlayServicesUid(Context context, int uid) {
        if (!uidHasPackageName(context, uid, GOOGLE_PLAY_SERVICES_PACKAGE)) {
            return HONOR_DEBUG_CERTIFICATES;
        }
        try {
            return GoogleSignatureVerifier.getInstance().isGooglePublicSignedPackage(context.getPackageManager(), context.getPackageManager().getPackageInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 64));
        } catch (NameNotFoundException e) {
            if (!Log.isLoggable(TAG, 3)) {
                return HONOR_DEBUG_CERTIFICATES;
            }
            Log.d(TAG, "Package manager can't find google play services package, defaulting to false");
            return HONOR_DEBUG_CERTIFICATES;
        }
    }

    @TargetApi(19)
    public static boolean uidHasPackageName(Context context, int uid, String packageName) {
        if (PlatformVersion.isAtLeastKitKat()) {
            try {
                ((AppOpsManager) context.getSystemService("appops")).checkPackage(uid, packageName);
                return true;
            } catch (SecurityException e) {
                return HONOR_DEBUG_CERTIFICATES;
            }
        }
        String[] packageNamesForUid = context.getPackageManager().getPackagesForUid(uid);
        if (!(packageName == null || packageNamesForUid == null)) {
            for (int i = GOOGLE_PLAY_SERVICES_VERSION_CODE; i < packageNamesForUid.length; i++) {
                if (packageName.equals(packageNamesForUid[i])) {
                    return true;
                }
            }
        }
        return HONOR_DEBUG_CERTIFICATES;
    }

    @Deprecated
    public static boolean isGoogleSignedUid(PackageManager packageManager, int uid) {
        GoogleSignatureVerifier.getInstance().verifyUidIsGoogleSigned(packageManager, uid);
        return true;
    }

    @Deprecated
    public static void verifyPackageIsGoogleSigned(PackageManager packageManager, String callingPackage) throws SecurityException {
        GoogleSignatureVerifier.getInstance().verifyPackageIsGoogleSigned(packageManager, callingPackage);
    }

    @Deprecated
    public static boolean isPackageGoogleSigned(PackageManager packageManager, String callingPackage) {
        return GoogleSignatureVerifier.getInstance().isPackageGoogleSigned(packageManager, callingPackage);
    }

    @Deprecated
    public static boolean isGoogleSignedPackage(PackageManager packageManager, PackageInfo packageInfo) {
        return GoogleSignatureVerifier.getInstance().isPackageGoogleSigned(packageManager, packageInfo);
    }

    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int errorCode) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, errorCode, null);
    }

    public static boolean isUserBuildDevice() {
        if (sIsTestMode) {
            return sTestIsUserBuild;
        }
        return "user".equals(Build.TYPE);
    }

    public static boolean isTestKeysBuild(PackageManager packageManager) {
        boolean z = true;
        synchronized (sLock) {
            if (sIsTestKeys == -1) {
                try {
                    PackageInfo gmsPackageInfo = packageManager.getPackageInfo(GOOGLE_PLAY_SERVICES_PACKAGE, 64);
                    if (GoogleSignatureVerifier.getInstance().verifySignature(gmsPackageInfo, VALID_PUBLIC_SIGNATURES.KEYS[1]) != null) {
                        sIsTestKeys = 1;
                    } else {
                        sIsTestKeys = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                    }
                } catch (NameNotFoundException e) {
                    sIsTestKeys = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                }
            }
            if (sIsTestKeys == 0) {
                z = HONOR_DEBUG_CERTIFICATES;
            }
        }
        return z;
    }

    public static boolean honorsDebugCertificates(PackageManager packageManager) {
        return (isTestKeysBuild(packageManager) || !isUserBuildDevice()) ? true : HONOR_DEBUG_CERTIFICATES;
    }

    public static boolean getApiaryPrettyPrintOption() {
        return isUserBuildDevice() ? HONOR_DEBUG_CERTIFICATES : true;
    }

    static boolean isAvailabilityError(int errorCode) {
        switch (errorCode) {
            case Type.TEMPORARY /*1*/:
            case MAX_ATTEMPTS_NO_SUCH_ALGORITHM /*2*/:
            case Type.CUSTOM /*3*/:
            case LogSource.GOOGLE_ANALYTICS /*18*/:
            case LogSource.PHOTOS /*42*/:
                return true;
            default:
                return HONOR_DEBUG_CERTIFICATES;
        }
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int errorCode, Context context, int requestCode) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, errorCode, requestCode);
    }

    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context context) {
        if (!sCanceledAvailabilityNotification.getAndSet(true)) {
            try {
                ((NotificationManager) context.getSystemService("notification")).cancel(GMS_AVAILABILITY_NOTIFICATION_ID);
            } catch (SecurityException e) {
            }
        }
    }

    @Deprecated
    public static boolean isUserRecoverableError(int errorCode) {
        switch (errorCode) {
            case Type.TEMPORARY /*1*/:
            case MAX_ATTEMPTS_NO_SUCH_ALGORITHM /*2*/:
            case Type.CUSTOM /*3*/:
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                return true;
            default:
                return HONOR_DEBUG_CERTIFICATES;
        }
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        InputStream is;
        try {
            is = context.getContentResolver().openInputStream(new Builder().scheme("android.resource").authority(GOOGLE_PLAY_SERVICES_PACKAGE).appendPath("raw").appendPath("oss_notice").build());
            String next = new Scanner(is).useDelimiter("\\A").next();
            if (is == null) {
                return next;
            }
            is.close();
            return next;
        } catch (NoSuchElementException e) {
            if (is != null) {
                is.close();
            }
            return null;
        } catch (Exception e2) {
            return null;
        } catch (Throwable th) {
            if (is != null) {
                is.close();
            }
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication(GOOGLE_PLAY_SERVICES_PACKAGE);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext(GOOGLE_PLAY_SERVICES_PACKAGE, 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static String getAppName(Context context) {
        String appName = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(appName)) {
            return appName;
        }
        ApplicationInfo appicationInfo;
        appName = context.getPackageName();
        PackageManager pm = context.getApplicationContext().getPackageManager();
        try {
            appicationInfo = pm.getApplicationInfo(context.getPackageName(), GOOGLE_PLAY_SERVICES_VERSION_CODE);
        } catch (NameNotFoundException e) {
            appicationInfo = null;
        }
        if (appicationInfo != null) {
            return pm.getApplicationLabel(appicationInfo).toString();
        }
        return appName;
    }

    @Deprecated
    public static int getClientVersion(Context context) {
        Preconditions.checkState(!BuildConstants.IS_PACKAGE_SIDE ? true : HONOR_DEBUG_CERTIFICATES);
        return ClientLibraryUtils.getClientVersion(context, context.getPackageName());
    }

    @Deprecated
    public static int getApkVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(GOOGLE_PLAY_SERVICES_PACKAGE, GOOGLE_PLAY_SERVICES_VERSION_CODE).versionCode;
        } catch (NameNotFoundException e) {
            Log.w(TAG, "Google Play services is missing.");
            return GOOGLE_PLAY_SERVICES_VERSION_CODE;
        }
    }

    @VisibleForTesting
    public static boolean isSidewinderDevice(Context context) {
        return (PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature(FEATURE_SIDEWINDER)) ? true : HONOR_DEBUG_CERTIFICATES;
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int connectionStatusCode) {
        if (connectionStatusCode == 18) {
            return true;
        }
        if (connectionStatusCode == 1) {
            return isUninstalledAppPossiblyUpdating(context, GOOGLE_PLAY_SERVICES_PACKAGE);
        }
        return HONOR_DEBUG_CERTIFICATES;
    }

    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context, int connectionStatusCode) {
        if (connectionStatusCode == 9) {
            return isUninstalledAppPossiblyUpdating(context, GOOGLE_PLAY_STORE_PACKAGE);
        }
        return HONOR_DEBUG_CERTIFICATES;
    }

    @TargetApi(21)
    static boolean isUninstalledAppPossiblyUpdating(Context context, String packageName) {
        boolean z = HONOR_DEBUG_CERTIFICATES;
        if (PlatformVersion.isAtLeastLollipop()) {
            for (SessionInfo session : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (packageName.equals(session.getAppPackageName())) {
                    return true;
                }
            }
        }
        if (isRestrictedUserProfile(context)) {
            return z;
        }
        try {
            return context.getPackageManager().getApplicationInfo(packageName, PeopleColumnBitmask.FAMILY_NAME).enabled;
        } catch (NameNotFoundException e) {
            return z;
        }
    }

    @TargetApi(18)
    public static boolean isRestrictedUserProfile(Context context) {
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle restrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (restrictions != null && VALUE_TRUE.equals(restrictions.getString(KEY_RESTRICTED_PROFILE))) {
                return true;
            }
        }
        return HONOR_DEBUG_CERTIFICATES;
    }
}
