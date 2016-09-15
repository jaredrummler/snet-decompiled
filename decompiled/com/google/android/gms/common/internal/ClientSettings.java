package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gsf.GoogleLoginServiceConstants;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@VisibleForTesting
public final class ClientSettings {
    public static final String KEY_CLIENT_SESSION_ID = "com.google.android.gms.common.internal.ClientSettings.sessionId";
    private final Account mAccount;
    private final Set<Scope> mAllRequestedScopes;
    private final int mGravityForPopups;
    private final Map<Api<?>, OptionalApiSettings> mOptionalApiSettingsMap;
    private final String mRealClientClassName;
    private final String mRealClientPackageName;
    private final Set<Scope> mRequiredScopes;
    private Integer mSessionId;
    private final SignInOptions mSignInOptions;
    private final View mViewForPopups;

    public static final class OptionalApiSettings {
        public final boolean mIsRecoverable;
        public final Set<Scope> mScopes;

        public OptionalApiSettings(Set<Scope> scopes, boolean isRecoverable) {
            Preconditions.checkNotNull(scopes);
            this.mScopes = Collections.unmodifiableSet(scopes);
            this.mIsRecoverable = isRecoverable;
        }
    }

    public static ClientSettings createDefault(Context context) {
        return new Builder(context).buildClientSettings();
    }

    public ClientSettings(Account account, Set<Scope> scopes, Map<Api<?>, OptionalApiSettings> optionalApiSettingsMap, int gravityForPopups, View viewForPopups, String realClientPackageName, String realClientClassName, SignInOptions signInOptions) {
        this.mAccount = account;
        this.mRequiredScopes = scopes == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(scopes);
        if (optionalApiSettingsMap == null) {
            optionalApiSettingsMap = Collections.EMPTY_MAP;
        }
        this.mOptionalApiSettingsMap = optionalApiSettingsMap;
        this.mViewForPopups = viewForPopups;
        this.mGravityForPopups = gravityForPopups;
        this.mRealClientPackageName = realClientPackageName;
        this.mRealClientClassName = realClientClassName;
        this.mSignInOptions = signInOptions;
        Set<Scope> allRequestedScopes = new HashSet(this.mRequiredScopes);
        for (OptionalApiSettings optionalApiSettings : this.mOptionalApiSettingsMap.values()) {
            allRequestedScopes.addAll(optionalApiSettings.mScopes);
        }
        this.mAllRequestedScopes = Collections.unmodifiableSet(allRequestedScopes);
    }

    @Deprecated
    @Nullable
    public String getAccountName() {
        return this.mAccount != null ? this.mAccount.name : null;
    }

    @Nullable
    public Account getAccount() {
        return this.mAccount;
    }

    public Account getAccountOrDefault() {
        return this.mAccount != null ? this.mAccount : new Account(GoogleApiClient.DEFAULT_ACCOUNT, GoogleLoginServiceConstants.ACCOUNT_TYPE);
    }

    public int getGravityForPopups() {
        return this.mGravityForPopups;
    }

    public Set<Scope> getRequiredScopes() {
        return this.mRequiredScopes;
    }

    public Set<Scope> getAllRequestedScopes() {
        return this.mAllRequestedScopes;
    }

    public Map<Api<?>, OptionalApiSettings> getOptionalApiSettings() {
        return this.mOptionalApiSettingsMap;
    }

    @Nullable
    public String getRealClientPackageName() {
        return this.mRealClientPackageName;
    }

    @Nullable
    public String getRealClientClassName() {
        return this.mRealClientClassName;
    }

    @Nullable
    public View getViewForPopups() {
        return this.mViewForPopups;
    }

    @Nullable
    public SignInOptions getSignInOptions() {
        return this.mSignInOptions;
    }

    @Nullable
    public Integer getClientSessionId() {
        return this.mSessionId;
    }

    public void setClientSessionId(Integer sessionId) {
        this.mSessionId = sessionId;
    }

    public Set<Scope> getApplicableScopes(Api<?> api) {
        OptionalApiSettings settings = (OptionalApiSettings) this.mOptionalApiSettingsMap.get(api);
        if (settings == null || settings.mScopes.isEmpty()) {
            return this.mRequiredScopes;
        }
        Set<Scope> combined = new HashSet(this.mRequiredScopes);
        combined.addAll(settings.mScopes);
        return combined;
    }
}
