package com.google.android.gms.auth.proto;

public interface OauthPolicy {

    public interface OAuthPolicy {
        public static final int GAMES_AUTO_SIGN_IN = 1;
        public static final int UNKNOWN_POLICY = 0;
    }

    public interface PolicyAction {
        public static final int APPLY = 2;
        public static final int APPROVE = 1;
        public static final int NONE = 0;
    }
}
