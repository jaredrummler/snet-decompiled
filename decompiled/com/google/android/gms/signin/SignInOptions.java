package com.google.android.gms.signin;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.Preconditions;

public final class SignInOptions implements Optional {
    public static final SignInOptions DEFAULT;
    private final boolean mForceCodeForRefreshToken;
    private final String mHostedDomain;
    private final boolean mIdTokenRequested;
    private final boolean mOfflineAccessRequested;
    private final String mServerClientId;
    private final boolean mWaitForAccessTokenRefresh;

    public static final class Builder {
        private boolean forceCodeForRefreshToken;
        private String hostedDomain;
        private boolean idTokenRequested;
        private boolean offlineAccessRequested;
        private String serverClientId;
        private boolean waitForAccessTokenRefresh;

        public SignInOptions build() {
            return new SignInOptions(this.idTokenRequested, this.serverClientId, this.forceCodeForRefreshToken, this.hostedDomain, this.waitForAccessTokenRefresh, null);
        }

        public Builder requestServerAuthCode(String serverClientId, boolean forceCodeForRefreshToken) {
            this.forceCodeForRefreshToken = forceCodeForRefreshToken;
            this.offlineAccessRequested = true;
            this.serverClientId = checkServerClientId(serverClientId);
            return this;
        }

        public Builder requestIdToken(String serverClientId) {
            this.idTokenRequested = true;
            this.serverClientId = checkServerClientId(serverClientId);
            return this;
        }

        public Builder setHostedDomain(@Nullable String hostedDomain) {
            this.hostedDomain = hostedDomain;
            return this;
        }

        public Builder setWaitForAccessTokenRefresh(boolean wait) {
            this.waitForAccessTokenRefresh = wait;
            return this;
        }

        private String checkServerClientId(String id) {
            Preconditions.checkNotNull(id);
            boolean z = this.serverClientId == null || this.serverClientId.equals(id);
            Preconditions.checkArgument(z, "two different server client ids provided");
            return id;
        }
    }

    static {
        DEFAULT = new Builder().build();
    }

    private SignInOptions(boolean offlineAccessRequested, boolean idTokenRequested, String serverClientId, boolean forceCodeForRefreshToken, String hostedDomain, boolean waitForAccessTokenRefresh) {
        this.mOfflineAccessRequested = offlineAccessRequested;
        this.mIdTokenRequested = idTokenRequested;
        this.mServerClientId = serverClientId;
        this.mForceCodeForRefreshToken = forceCodeForRefreshToken;
        this.mWaitForAccessTokenRefresh = waitForAccessTokenRefresh;
        this.mHostedDomain = hostedDomain;
    }

    public boolean isOfflineAccessRequested() {
        return this.mOfflineAccessRequested;
    }

    public boolean isIdTokenRequested() {
        return this.mIdTokenRequested;
    }

    public String getServerClientId() {
        return this.mServerClientId;
    }

    public boolean isForceCodeForRefreshToken() {
        return this.mForceCodeForRefreshToken;
    }

    @Nullable
    public String getHostedDomain() {
        return this.mHostedDomain;
    }

    public boolean waitForAccessTokenRefresh() {
        return this.mWaitForAccessTokenRefresh;
    }
}
