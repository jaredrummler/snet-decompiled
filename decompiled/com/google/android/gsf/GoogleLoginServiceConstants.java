package com.google.android.gsf;

import android.content.Intent;

public class GoogleLoginServiceConstants {
    @Deprecated
    public static final String ACCOUNTS_KEY = "accounts";
    public static final String ACCOUNT_TYPE = "com.google";
    public static final String ACTION_GET_GLS = "com.google.android.gsf.action.GET_GLS";
    @Deprecated
    public static final String AUTHTOKEN_KEY = "authtoken";
    @Deprecated
    public static final String AUTH_ACCOUNT_KEY = "authAccount";
    public static final int ERROR_CODE_GLS_NOT_FOUND = 0;
    public static final int ERROR_CODE_GLS_VERIFICATION_FAILED = 1;
    @Deprecated
    public static final String ERROR_CODE_KEY = "errorCode";
    public static final String FEATURE_GOOGLE = "google";
    public static final String FEATURE_HOSTED_OR_GOOGLE = "hosted_or_google";
    @Deprecated
    public static final String FEATURE_LEGACY_GOOGLE = "google";
    @Deprecated
    public static final String FEATURE_LEGACY_HOSTED_OR_GOOGLE = "hosted_or_google";
    public static final String FEATURE_SAML_ACCOUNT = "saml";
    public static final String FEATURE_SERVICE_PREFIX = "service_";
    public static final String FEATURE_YOUTUBE = "youtubelinked";
    public static final int FLAG_GOOGLE_ACCOUNT = 1;
    public static final int FLAG_HOSTED_ACCOUNT = 2;
    public static final int FLAG_SAML_ACCOUNT = 8;
    public static final int FLAG_YOUTUBE_ACCOUNT = 4;
    public static final String LOGIN_ACCOUNTS_MISSING_ACTION = "com.google.android.gsf.LOGIN_ACCOUNTS_MISSING";
    public static final String PACKAGE_GLS = "com.google.android.gsf.login";
    public static final boolean PREFER_HOSTED = false;
    public static final String PRE_FROYO_AID_FILENAME = "pre_froyo_aid";
    public static final String REQUEST_EXTRAS = "callerExtras";
    public static final boolean REQUIRE_GOOGLE = true;
    public static final Intent SERVICE_INTENT;
    public static final String YOUTUBE_USER_KEY = "YouTubeUser";

    private GoogleLoginServiceConstants() {
    }

    public static String featureForService(String service) {
        return FEATURE_SERVICE_PREFIX + service;
    }

    static String getErrorCodeMessage(int errorCode) {
        switch (errorCode) {
            case ERROR_CODE_GLS_NOT_FOUND /*0*/:
                return "The Google login service cannot be found.";
            case FLAG_GOOGLE_ACCOUNT /*1*/:
                return "The Google login service cannot be verified.";
            default:
                return "Unknown error";
        }
    }

    static {
        SERVICE_INTENT = new Intent().setPackage(PACKAGE_GLS).setAction(ACTION_GET_GLS).addCategory("android.intent.category.DEFAULT");
    }
}
