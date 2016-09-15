package com.google.android.gms.auth;

import com.google.android.gms.common.logging.Logger;

public class AuthLoggers {
    private static final String TAG_AUTH_API_CREDENTIALS = "Auth.Api.Credentials";
    private static final String TAG_AUTH_API_SIGN_IN = "Auth.Api.SignIn";
    private static final String TAG_AUTH_CLIENT_SERVICE = "Auth.ClientService";
    private static final String TAG_AUTH_CORE = "Auth";
    private static final String TAG_AUTH_PROXIMITY_AUTH = "Auth.ProximityAuth";

    public static Logger newLogger(String... categories) {
        return new Logger(TAG_AUTH_CORE, categories);
    }

    public static Logger newClientServiceLogger(String... categories) {
        return new Logger(TAG_AUTH_CLIENT_SERVICE, categories);
    }

    public static Logger newProximityAuthLogger(String... categories) {
        return new Logger(TAG_AUTH_PROXIMITY_AUTH, categories);
    }

    public static Logger newCredentialsApiLogger(String... categories) {
        return new Logger(TAG_AUTH_API_CREDENTIALS, categories);
    }

    public static Logger newSignInApiLogger(String... categories) {
        return new Logger(TAG_AUTH_API_SIGN_IN, categories);
    }

    private AuthLoggers() {
    }
}
