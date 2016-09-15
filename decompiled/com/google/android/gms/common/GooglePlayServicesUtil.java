package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatExtras;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {
    public static final String GINGERBREAD_ALERT_DIALOG_THEME_NAME = "Theme.Dialog.Alert";
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static final long UPDATE_NOTIFICATION_DELAY = 120000;

    private static class NotificationHandler extends Handler {
        static final int SHOW_NOTIFICATION = 1;
        private final Context mApplicationContext;

        NotificationHandler(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.mApplicationContext = context.getApplicationContext();
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_NOTIFICATION /*1*/:
                    int connectionResult = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mApplicationContext);
                    if (GooglePlayServicesUtil.isUserRecoverableError(connectionResult)) {
                        GooglePlayServicesUtil.showErrorNotificationInternal(connectionResult, this.mApplicationContext);
                    }
                default:
                    Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + msg.what);
            }
        }
    }

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode) {
        return getErrorDialog(errorCode, activity, requestCode, null);
    }

    @Deprecated
    public static Dialog getErrorDialog(int errorCode, Activity activity, int requestCode, OnCancelListener cancelListener) {
        return getErrorDialog(errorCode, activity, null, requestCode, cancelListener);
    }

    @TargetApi(14)
    private static Dialog getErrorDialog(int errorCode, Activity activity, Fragment fragment, int requestCode, OnCancelListener cancelListener) {
        if (errorCode == 0) {
            return null;
        }
        DialogRedirect redirect;
        if (DeviceProperties.isWearable(activity) && errorCode == 2) {
            errorCode = 42;
        }
        if (isPlayServicesPossiblyUpdating(activity, errorCode)) {
            errorCode = 18;
        }
        Builder builder = null;
        if (PlatformVersion.isAtLeastIceCreamSandwich()) {
            TypedValue outValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, outValue, true);
            if (GINGERBREAD_ALERT_DIALOG_THEME_NAME.equals(activity.getResources().getResourceEntryName(outValue.resourceId))) {
                builder = new Builder(activity, 5);
            }
        }
        if (builder == null) {
            builder = new Builder(activity);
        }
        builder.setMessage(ConnectionErrorMessages.getErrorMessage(activity, errorCode, GooglePlayServicesUtilLight.getAppName(activity)));
        if (cancelListener != null) {
            builder.setOnCancelListener(cancelListener);
        }
        Intent recoveryIntent = GoogleApiAvailability.getInstance().getErrorResolutionIntent(activity, errorCode, "d");
        if (fragment == null) {
            redirect = new DialogRedirect(activity, recoveryIntent, requestCode);
        } else {
            redirect = new DialogRedirect(fragment, recoveryIntent, requestCode);
        }
        String buttonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(activity, errorCode);
        if (buttonMessage != null) {
            builder.setPositiveButton(buttonMessage, redirect);
        }
        String title = ConnectionErrorMessages.getErrorTitle(activity, errorCode);
        if (title != null) {
            builder.setTitle(title);
        }
        return builder.create();
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode, OnCancelListener cancelListener) {
        return showErrorDialogFragment(errorCode, activity, null, requestCode, cancelListener);
    }

    public static boolean showErrorDialogFragment(int errorCode, Activity activity, Fragment fragment, int requestCode, OnCancelListener cancelListener) {
        Dialog dialog = getErrorDialog(errorCode, activity, fragment, requestCode, cancelListener);
        if (dialog == null) {
            return false;
        }
        showDialogFragment(activity, cancelListener, GMS_ERROR_DIALOG, dialog);
        return true;
    }

    @TargetApi(11)
    public static void showDialogFragment(Activity activity, OnCancelListener cancelListener, String tag, @NonNull Dialog dialog) {
        boolean isSupport;
        try {
            isSupport = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
            isSupport = false;
        }
        if (isSupport) {
            SupportErrorDialogFragment.newInstance(dialog, cancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), tag);
        } else if (PlatformVersion.isAtLeastHoneycomb()) {
            ErrorDialogFragment.newInstance(dialog, cancelListener).show(activity.getFragmentManager(), tag);
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
    }

    @Deprecated
    public static void showErrorNotification(int errorCode, Context context) {
        if (DeviceProperties.isWearable(context) && errorCode == 2) {
            errorCode = 42;
        }
        if (isPlayServicesPossiblyUpdating(context, errorCode) || isPlayStorePossiblyUpdating(context, errorCode)) {
            showErrorNotificationWithDelay(context);
        } else {
            showErrorNotificationInternal(errorCode, context);
        }
    }

    @Deprecated
    public static void showErrorNotification(int errorCode, Context context, String tag) {
        if (DeviceProperties.isWearable(context) && errorCode == 2) {
            errorCode = 42;
        }
        showErrorNotificationInternal(errorCode, context, tag);
    }

    private static void showErrorNotificationWithDelay(Context context) {
        Handler handler = new NotificationHandler(context);
        handler.sendMessageDelayed(handler.obtainMessage(1), UPDATE_NOTIFICATION_DELAY);
    }

    @TargetApi(21)
    private static void showErrorNotificationInternal(int errorCode, Context context) {
        showErrorNotificationInternal(errorCode, context, null);
    }

    @TargetApi(21)
    private static void showErrorNotificationInternal(int errorCode, Context context, String tag) {
        Notification notification;
        int notificationId;
        Resources resources = context.getResources();
        String appName = GooglePlayServicesUtilLight.getAppName(context);
        String contentTitle = ConnectionErrorMessages.getErrorTitle(context, errorCode);
        if (contentTitle == null) {
            contentTitle = resources.getString(R.string.common_google_play_services_notification_ticker);
        }
        String contentText = ConnectionErrorMessages.getErrorMessage(context, errorCode, appName);
        PendingIntent pendingIntent = GoogleApiAvailability.getInstance().getErrorResolutionPendingIntent(context, errorCode, GOOGLE_PLAY_SERVICES_VERSION_CODE, "n");
        if (DeviceProperties.isWearable(context)) {
            Preconditions.checkState(PlatformVersion.isAtLeastJellyBean());
            notification = new Notification.Builder(context).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new BigTextStyle().bigText(contentTitle + " " + contentText)).addAction(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), pendingIntent).build();
        } else {
            String tickerMessage = resources.getString(R.string.common_google_play_services_notification_ticker);
            if (PlatformVersion.isAtLeastHoneycomb()) {
                Notification.Builder builder = new Notification.Builder(context).setSmallIcon(17301642).setContentTitle(contentTitle).setContentText(contentText).setContentIntent(pendingIntent).setTicker(tickerMessage).setAutoCancel(true);
                if (PlatformVersion.isAtLeastKitKatWatch()) {
                    builder.setLocalOnly(true);
                }
                if (PlatformVersion.isAtLeastJellyBean()) {
                    builder.setStyle(new BigTextStyle().bigText(contentText));
                    notification = builder.build();
                } else {
                    notification = builder.getNotification();
                }
                if (VERSION.SDK_INT == 19) {
                    notification.extras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
                }
            } else {
                notification = new NotificationCompat.Builder(context).setSmallIcon(17301642).setTicker(tickerMessage).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(contentTitle).setContentText(contentText).build();
            }
        }
        if (GooglePlayServicesUtilLight.isAvailabilityError(errorCode)) {
            notificationId = 10436;
            sCanceledAvailabilityNotification.set(false);
        } else {
            notificationId = 39789;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (tag != null) {
            notificationManager.notify(tag, notificationId, notification);
        } else {
            notificationManager.notify(notificationId, notification);
        }
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int errorCode, Activity activity, int requestCode) {
        return showErrorDialogFragment(errorCode, activity, requestCode, null);
    }

    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public static void enableUsingApkIndependentContext() {
        GooglePlayServicesUtilLight.enableUsingApkIndependentContext();
    }

    @Deprecated
    @VisibleForTesting
    public static String getErrorString(int errorCode) {
        return GooglePlayServicesUtilLight.getErrorString(errorCode);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }

    @Deprecated
    public static boolean isGoogleSignedUid(PackageManager packageManager, int uid) {
        return GooglePlayServicesUtilLight.isGoogleSignedUid(packageManager, uid);
    }

    @Deprecated
    public static void verifyPackageIsGoogleSigned(PackageManager packageManager, String callingPackage) throws SecurityException {
        GooglePlayServicesUtilLight.verifyPackageIsGoogleSigned(packageManager, callingPackage);
    }

    @Deprecated
    public static boolean isPackageGoogleSigned(PackageManager packageManager, String callingPackage) {
        return GooglePlayServicesUtilLight.isPackageGoogleSigned(packageManager, callingPackage);
    }

    @Deprecated
    public static boolean isGoogleSignedPackage(PackageManager packageManager, PackageInfo packageInfo) {
        return GooglePlayServicesUtilLight.isGoogleSignedPackage(packageManager, packageInfo);
    }

    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int errorCode) {
        return GooglePlayServicesUtilLight.getGooglePlayServicesAvailabilityRecoveryIntent(errorCode);
    }

    public static boolean honorsDebugCertificates(PackageManager packageManager) {
        return GooglePlayServicesUtilLight.honorsDebugCertificates(packageManager);
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int errorCode, Context context, int requestCode) {
        return GooglePlayServicesUtilLight.getErrorPendingIntent(errorCode, context, requestCode);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int errorCode) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(errorCode);
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        return GooglePlayServicesUtilLight.getOpenSourceSoftwareLicenseInfo(context);
    }

    public static Resources getRemoteResource(Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    public static Context getRemoteContext(Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }

    @Deprecated
    public static int getClientVersion(Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }

    @Deprecated
    public static int getApkVersion(Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    @VisibleForTesting
    public static boolean isSidewinderDevice(Context context) {
        return GooglePlayServicesUtilLight.isSidewinderDevice(context);
    }

    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int connectionStatusCode) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, connectionStatusCode);
    }

    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context, int connectionStatusCode) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, connectionStatusCode);
    }

    @TargetApi(18)
    public static boolean isRestrictedUserProfile(Context context) {
        return GooglePlayServicesUtilLight.isRestrictedUserProfile(context);
    }
}
