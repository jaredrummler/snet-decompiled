package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import com.google.android.gms.R;
import com.google.android.gms.lint.BuildConfig;

public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final String GMS_UPDATING_DIALOG = "GooglePlayServicesUpdatingDialog";
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability INSTANCE;

    static {
        INSTANCE = new GoogleApiAvailability();
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public static GoogleApiAvailability getInstance() {
        return INSTANCE;
    }

    GoogleApiAvailability() {
    }

    public Dialog getErrorDialog(Activity activity, int errorCode, int requestCode) {
        return GooglePlayServicesUtil.getErrorDialog(errorCode, activity, requestCode);
    }

    public Dialog getErrorDialog(Activity activity, int errorCode, int requestCode, OnCancelListener cancelListener) {
        return GooglePlayServicesUtil.getErrorDialog(errorCode, activity, requestCode, cancelListener);
    }

    public boolean showErrorDialogFragment(Activity activity, int errorCode, int requestCode) {
        return GooglePlayServicesUtil.showErrorDialogFragment(errorCode, activity, requestCode);
    }

    public boolean showErrorDialogFragment(Activity activity, int errorCode, int requestCode, OnCancelListener cancelListener) {
        return GooglePlayServicesUtil.showErrorDialogFragment(errorCode, activity, requestCode, cancelListener);
    }

    public void showErrorNotification(Context context, int errorCode) {
        GooglePlayServicesUtil.showErrorNotification(errorCode, context);
    }

    public void showErrorNotification(Context context, int errorCode, String tag) {
        GooglePlayServicesUtil.showErrorNotification(errorCode, context, tag);
    }

    public Dialog showUpdatingDialog(Activity activity, OnCancelListener cancelListener) {
        ProgressBar progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        Builder builder = new Builder(activity);
        builder.setView(progressBar);
        String appName = GooglePlayServicesUtilLight.getAppName(activity);
        builder.setMessage(activity.getResources().getString(R.string.common_google_play_services_updating_text, new Object[]{appName}));
        builder.setTitle(R.string.common_google_play_services_updating_title);
        builder.setPositiveButton(BuildConfig.VERSION_NAME, null);
        AlertDialog updatingDialog = builder.create();
        GooglePlayServicesUtil.showDialogFragment(activity, cancelListener, GMS_UPDATING_DIALOG, updatingDialog);
        return updatingDialog;
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    public final boolean isUserResolvableError(int errorCode) {
        return super.isUserResolvableError(errorCode);
    }

    @Nullable
    @Deprecated
    public Intent getErrorResolutionIntent(int errorCode) {
        return super.getErrorResolutionIntent(errorCode);
    }

    @Nullable
    public Intent getErrorResolutionIntent(Context context, int errorCode, @Nullable String trackingSource) {
        return super.getErrorResolutionIntent(context, errorCode, trackingSource);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int errorCode, int requestCode) {
        return super.getErrorResolutionPendingIntent(context, errorCode, requestCode);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int errorCode, int requestCode, @Nullable String trackingSource) {
        return super.getErrorResolutionPendingIntent(context, errorCode, requestCode, trackingSource);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return super.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int getClientVersion(Context context) {
        return super.getClientVersion(context);
    }

    public int getApkVersion(Context context) {
        return super.getApkVersion(context);
    }

    public boolean isPlayServicesPossiblyUpdating(Context context, int errorCode) {
        return super.isPlayServicesPossiblyUpdating(context, errorCode);
    }

    public boolean isPlayStorePossiblyUpdating(Context context, int errorCode) {
        return super.isPlayStorePossiblyUpdating(context, errorCode);
    }

    public final String getErrorString(int errorCode) {
        return super.getErrorString(errorCode);
    }
}
