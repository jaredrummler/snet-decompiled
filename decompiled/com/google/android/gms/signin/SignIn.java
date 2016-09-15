package com.google.android.gms.signin;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gms.signin.internal.SignInApiImpl;
import com.google.android.gms.signin.internal.SignInClientImpl;

public final class SignIn {
    public static final Api<SignInOptions> API;
    public static final AbstractClientBuilder<SignInClientImpl, SignInOptions> CLIENT_BUILDER;
    public static final ClientKey<SignInClientImpl> CLIENT_KEY;
    public static final Api<SignInOptionsInternal> INTERNAL_API;
    static final AbstractClientBuilder<SignInClientImpl, SignInOptionsInternal> INTERNAL_CLIENT_BUILDER;
    public static final ClientKey<SignInClientImpl> INTERNAL_CLIENT_KEY;
    public static final Scope SCOPE_EMAIL;
    public static final Scope SCOPE_PROFILE;
    public static final SignInApi SignInApi;

    public static class SignInOptionsInternal implements HasOptions {
        private final Bundle mSignInOptionsBundle;

        public static SignInOptionsInternal create(Bundle signInOptionsBundle) {
            return new SignInOptionsInternal(signInOptionsBundle);
        }

        private SignInOptionsInternal(Bundle signInOptionsBundle) {
            if (signInOptionsBundle == null) {
                signInOptionsBundle = new Bundle();
            }
            this.mSignInOptionsBundle = signInOptionsBundle;
        }

        public Bundle getSignInOptionsBundle() {
            return this.mSignInOptionsBundle;
        }
    }

    static {
        CLIENT_KEY = new ClientKey();
        INTERNAL_CLIENT_KEY = new ClientKey();
        CLIENT_BUILDER = new AbstractClientBuilder<SignInClientImpl, SignInOptions>() {
            public SignInClientImpl buildClient(Context context, Looper looper, ClientSettings commonSettings, SignInOptions signInOptions, ConnectionCallbacks connectionListener, OnConnectionFailedListener connectionFailedListener) {
                if (signInOptions == null) {
                    signInOptions = SignInOptions.DEFAULT;
                }
                return new SignInClientImpl(context, looper, true, commonSettings, signInOptions, connectionListener, connectionFailedListener);
            }
        };
        INTERNAL_CLIENT_BUILDER = new AbstractClientBuilder<SignInClientImpl, SignInOptionsInternal>() {
            public SignInClientImpl buildClient(Context context, Looper looper, ClientSettings commonSettings, SignInOptionsInternal apiOptions, ConnectionCallbacks connectionListener, OnConnectionFailedListener connectionFailedListener) {
                return new SignInClientImpl(context, looper, false, commonSettings, apiOptions.getSignInOptionsBundle(), connectionListener, connectionFailedListener);
            }
        };
        SCOPE_PROFILE = new Scope(Scopes.PROFILE);
        SCOPE_EMAIL = new Scope(PeopleEmail.EMAIL_ADDRESS);
        API = new Api("SignIn.API", CLIENT_BUILDER, CLIENT_KEY);
        INTERNAL_API = new Api("SignIn.INTERNAL_API", INTERNAL_CLIENT_BUILDER, INTERNAL_CLIENT_KEY);
        SignInApi = new SignInApiImpl();
    }

    private SignIn() {
    }
}
