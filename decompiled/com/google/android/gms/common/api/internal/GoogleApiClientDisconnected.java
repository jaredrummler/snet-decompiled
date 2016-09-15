package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import java.util.Collections;

public class GoogleApiClientDisconnected implements GoogleApiClientState {
    private final GoogleApiClientStateHolder mHolder;

    public GoogleApiClientDisconnected(GoogleApiClientStateHolder holder) {
        this.mHolder = holder;
    }

    public void begin() {
        this.mHolder.disconnectClientsLocked();
        this.mHolder.mApiClient.mValidatedScopes = Collections.emptySet();
    }

    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T methodImpl) {
        this.mHolder.mApiClient.mWorkQueue.add(methodImpl);
        return methodImpl;
    }

    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public boolean disconnect() {
        return true;
    }

    public void connect() {
        this.mHolder.changeToConnecting();
    }

    public void onConnected(Bundle connectionHint) {
    }

    public void onConnectionFailed(ConnectionResult result, Api<?> api, int apiType) {
    }

    public void onConnectionSuspended(int cause) {
    }

    public String getName() {
        return "DISCONNECTED";
    }
}
