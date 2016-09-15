package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;

public class ClientCallbacks implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api<?> mApi;
    private final int mApiType;
    private GoogleApiClientStateHolder mGoogleApiClient;

    public ClientCallbacks(Api<?> api, int apiType) {
        this.mApi = api;
        this.mApiType = apiType;
    }

    public void onConnected(@Nullable Bundle connectionHint) {
        assertGoogleApiClientSet();
        this.mGoogleApiClient.onConnected(connectionHint);
    }

    public void onConnectionSuspended(int cause) {
        assertGoogleApiClientSet();
        this.mGoogleApiClient.onConnectionSuspended(cause);
    }

    public void onConnectionFailed(@NonNull ConnectionResult result) {
        assertGoogleApiClientSet();
        this.mGoogleApiClient.onConnectionFailed(result, this.mApi, this.mApiType);
    }

    public void setGoogleApiClient(GoogleApiClientStateHolder googleApiClient) {
        this.mGoogleApiClient = googleApiClient;
    }

    private void assertGoogleApiClientSet() {
        Preconditions.checkNotNull(this.mGoogleApiClient, "Callbacks must be attached to a GoogleApiClient instance before connecting the client.");
    }
}
