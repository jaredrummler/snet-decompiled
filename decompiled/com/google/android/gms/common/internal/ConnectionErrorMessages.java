package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public final class ConnectionErrorMessages {
    private static final String TAG = "GoogleApiAvailability";

    @Nullable
    public static final String getErrorTitle(Context context, int errorCode) {
        Resources resources = context.getResources();
        switch (errorCode) {
            case Type.TEMPORARY /*1*/:
                return resources.getString(R.string.common_google_play_services_install_title);
            case Type.INDEFINITELY /*2*/:
            case LogSource.PHOTOS /*42*/:
                return resources.getString(R.string.common_google_play_services_update_title);
            case Type.CUSTOM /*3*/:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                return null;
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                Log.e(TAG, "An invalid account was specified when connecting. Please provide a valid account.");
                return resources.getString(R.string.common_google_play_services_invalid_account_title);
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                Log.e(TAG, "Network error occurred. Please retry request later.");
                return resources.getString(R.string.common_google_play_services_network_error_title);
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                Log.e(TAG, "Internal error occurred. Please see logs for detailed information");
                return null;
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                Log.e(TAG, "Google Play services is invalid. Cannot recover.");
                return resources.getString(R.string.common_google_play_services_unsupported_title);
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                Log.e(TAG, "Developer error occurred. Please see logs for detailed information");
                return null;
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                Log.e(TAG, "The application is not licensed to the user.");
                return null;
            case LogSource.GMS_CORE_PEOPLE /*16*/:
                Log.e(TAG, "One of the API components you attempted to connect to is not available.");
                return null;
            case LogSource.LE /*17*/:
                Log.e(TAG, "The specified account could not be signed in.");
                return resources.getString(R.string.common_google_play_services_sign_in_failed_title);
            case LogSource.GOOGLE_ANALYTICS /*18*/:
                return resources.getString(R.string.common_google_play_services_updating_title);
            case LogSource.ANDROID_GSA /*20*/:
                Log.e(TAG, "The current user profile is restricted and could not use authenticated features.");
                return resources.getString(R.string.common_google_play_services_restricted_profile_title);
            default:
                Log.e(TAG, "Unexpected error code " + errorCode);
                return null;
        }
    }

    public static String getErrorMessage(Context context, int errorCode, String appName) {
        Resources resources = context.getResources();
        switch (errorCode) {
            case Type.TEMPORARY /*1*/:
                if (DeviceProperties.isTablet(resources)) {
                    return resources.getString(R.string.common_google_play_services_install_text_tablet, new Object[]{appName});
                }
                return resources.getString(R.string.common_google_play_services_install_text_phone, new Object[]{appName});
            case Type.INDEFINITELY /*2*/:
                return resources.getString(R.string.common_google_play_services_update_text, new Object[]{appName});
            case Type.CUSTOM /*3*/:
                return resources.getString(R.string.common_google_play_services_enable_text, new Object[]{appName});
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                return resources.getString(R.string.common_google_play_services_network_error_text);
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                return resources.getString(R.string.common_google_play_services_unsupported_text, new Object[]{appName});
            case LogSource.GMS_CORE_PEOPLE /*16*/:
                return resources.getString(R.string.common_google_play_services_api_unavailable_text, new Object[]{appName});
            case LogSource.LE /*17*/:
                return resources.getString(R.string.common_google_play_services_sign_in_failed_text);
            case LogSource.GOOGLE_ANALYTICS /*18*/:
                return resources.getString(R.string.common_google_play_services_updating_text, new Object[]{appName});
            case LogSource.ANDROID_GSA /*20*/:
                return resources.getString(R.string.common_google_play_services_restricted_profile_text);
            case LogSource.PHOTOS /*42*/:
                return resources.getString(R.string.common_google_play_services_wear_update_text);
            default:
                return resources.getString(R.string.common_google_play_services_unknown_issue, new Object[]{appName});
        }
    }

    public static String getErrorDialogButtonMessage(Context context, int errorCode) {
        Resources resources = context.getResources();
        switch (errorCode) {
            case Type.TEMPORARY /*1*/:
                return resources.getString(R.string.common_google_play_services_install_button);
            case Type.INDEFINITELY /*2*/:
                return resources.getString(R.string.common_google_play_services_update_button);
            case Type.CUSTOM /*3*/:
                return resources.getString(R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }

    private ConnectionErrorMessages() {
    }
}
