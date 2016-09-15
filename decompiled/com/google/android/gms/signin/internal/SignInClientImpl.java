package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.ISignInService.Stub;

public class SignInClientImpl extends GmsClient<ISignInService> implements SignInClient {
    public static final String ACTION_START_SERVICE = "com.google.android.gms.signin.service.START";
    public static final String INTERNAL_ACTION_START_SERVICE = "com.google.android.gms.signin.service.INTERNAL_START";
    public static final String KEY_CLIENT_REQUESTED_ACCOUNT = "com.google.android.gms.signin.internal.clientRequestedAccount";
    public static final String KEY_FORCE_CODE_FOR_REFRESH_TOKEN = "com.google.android.gms.signin.internal.forceCodeForRefreshToken";
    public static final String KEY_HOSTED_DOMAIN = "com.google.android.gms.signin.internal.hostedDomain";
    public static final String KEY_ID_TOKEN_REQUESTED = "com.google.android.gms.signin.internal.idTokenRequested";
    @Deprecated
    public static final String KEY_OFFLINE_ACCESS_CALLBACKS = "com.google.android.gms.signin.internal.signInCallbacks";
    public static final String KEY_OFFLINE_ACCESS_REQUESTED = "com.google.android.gms.signin.internal.offlineAccessRequested";
    public static final String KEY_REAL_CLIENT_PACKAGE_NAME = "com.google.android.gms.signin.internal.realClientPackageName";
    public static final String KEY_SERVER_CLIENT_ID = "com.google.android.gms.signin.internal.serverClientId";
    public static final String KEY_USE_PROMPT_MODE_FOR_AUTH_CODE = "com.google.android.gms.signin.internal.usePromptModeForAuthCode";
    public static final String KEY_WAIT_FOR_ACCESS_TOKEN_REFRESH = "com.google.android.gms.signin.internal.waitForAccessTokenRefresh";
    private static final String SERVICE_DESCRIPTOR = "com.google.android.gms.signin.internal.ISignInService";
    private static final String TAG = "SignInClientImpl";
    private final ClientSettings mClientSettings;
    private final boolean mIsExposedSignInApi;
    private Integer mSessionId;
    private final Bundle mSignInOptionsBundle;

    public SignInClientImpl(Context context, Looper looper, boolean isExposedSignInApi, ClientSettings clientSettings, Bundle signInOptionsBundle, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        super(context, looper, 44, clientSettings, connectedListener, connectionFailedListener);
        this.mIsExposedSignInApi = isExposedSignInApi;
        this.mClientSettings = clientSettings;
        this.mSignInOptionsBundle = signInOptionsBundle;
        this.mSessionId = clientSettings.getClientSessionId();
    }

    public SignInClientImpl(Context context, Looper looper, boolean isExposedSignInApi, ClientSettings clientSettings, SignInOptions signInOptions, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        this(context, looper, isExposedSignInApi, clientSettings, createBundleFromClientSettings(clientSettings), connectedListener, connectionFailedListener);
    }

    public boolean requiresSignIn() {
        return this.mIsExposedSignInApi;
    }

    public void saveDefaultAccount(IAccountAccessor accountAccessor, boolean showCrossClientAuthToast) {
        try {
            ((ISignInService) getService()).saveDefaultAccountToSharedPref(accountAccessor, this.mSessionId.intValue(), showCrossClientAuthToast);
        } catch (RemoteException e) {
            Log.w(TAG, "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void clearAccountFromSessionStore() {
        try {
            ((ISignInService) getService()).clearAccountFromSessionStore(this.mSessionId.intValue());
        } catch (RemoteException e) {
            Log.w(TAG, "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public void signIn(ISignInCallbacks callbacks) {
        Preconditions.checkNotNull(callbacks, "Expecting a valid ISignInCallbacks");
        try {
            ((ISignInService) getService()).signIn(new SignInRequest(createResolveAccountRequest()), callbacks);
        } catch (RemoteException e) {
            Log.w(TAG, "Remote service probably died when signIn is called");
            try {
                callbacks.onSignInComplete(new SignInResponse(8));
            } catch (RemoteException e2) {
                Log.wtf(TAG, "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    private ResolveAccountRequest createResolveAccountRequest() {
        Account account = this.mClientSettings.getAccountOrDefault();
        GoogleSignInAccount signInAccountHint = null;
        if (GoogleApiClient.DEFAULT_ACCOUNT.equals(account.name)) {
            signInAccountHint = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount();
        }
        return new ResolveAccountRequest(account, this.mSessionId.intValue(), signInAccountHint);
    }

    protected String getStartServiceAction() {
        return ACTION_START_SERVICE;
    }

    protected String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }

    protected ISignInService createServiceInterface(IBinder binder) {
        return Stub.asInterface(binder);
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.mClientSettings.getRealClientPackageName())) {
            this.mSignInOptionsBundle.putString(KEY_REAL_CLIENT_PACKAGE_NAME, this.mClientSettings.getRealClientPackageName());
        }
        return this.mSignInOptionsBundle;
    }

    public void connect() {
        connect(new LegacyClientCallbackAdapter());
    }

    public static Bundle createBundleFromClientSettings(ClientSettings clientSettings) {
        SignInOptions signInOptions = clientSettings.getSignInOptions();
        Integer sessionId = clientSettings.getClientSessionId();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_CLIENT_REQUESTED_ACCOUNT, clientSettings.getAccount());
        if (sessionId != null) {
            bundle.putInt(ClientSettings.KEY_CLIENT_SESSION_ID, sessionId.intValue());
        }
        if (signInOptions != null) {
            bundle.putBoolean(KEY_OFFLINE_ACCESS_REQUESTED, signInOptions.isOfflineAccessRequested());
            bundle.putBoolean(KEY_ID_TOKEN_REQUESTED, signInOptions.isIdTokenRequested());
            bundle.putString(KEY_SERVER_CLIENT_ID, signInOptions.getServerClientId());
            bundle.putBoolean(KEY_USE_PROMPT_MODE_FOR_AUTH_CODE, true);
            bundle.putBoolean(KEY_FORCE_CODE_FOR_REFRESH_TOKEN, signInOptions.isForceCodeForRefreshToken());
            bundle.putString(KEY_HOSTED_DOMAIN, signInOptions.getHostedDomain());
            bundle.putBoolean(KEY_WAIT_FOR_ACCESS_TOKEN_REFRESH, signInOptions.waitForAccessTokenRefresh());
        }
        return bundle;
    }
}
