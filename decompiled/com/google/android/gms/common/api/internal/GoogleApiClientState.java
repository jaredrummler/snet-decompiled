package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

public interface GoogleApiClientState {
    void begin();

    void connect();

    boolean disconnect();

    <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T t);

    <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(T t);

    String getName();

    void onConnected(Bundle bundle);

    void onConnectionFailed(ConnectionResult connectionResult, Api<?> api, int i);

    void onConnectionSuspended(int i);
}
