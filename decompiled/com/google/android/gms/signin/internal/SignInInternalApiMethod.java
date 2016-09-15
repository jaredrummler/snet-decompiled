package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.signin.SignIn;

public abstract class SignInInternalApiMethod<R extends Result> extends ApiMethodImpl<R, SignInClientImpl> {
    public SignInInternalApiMethod(GoogleApiClient client) {
        super(SignIn.INTERNAL_CLIENT_KEY, client);
    }
}
