package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api.SimpleClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
    private final SimpleClient<T> mClientImpl;

    public SimpleClientAdapter(Context context, Looper looper, int gCoreServiceId, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener, ClientSettings clientSettings, SimpleClient client) {
        super(context, looper, gCoreServiceId, clientSettings, connectedListener, connectionFailedListener);
        this.mClientImpl = client;
    }

    protected String getStartServiceAction() {
        return this.mClientImpl.getStartServiceAction();
    }

    protected String getServiceDescriptor() {
        return this.mClientImpl.getServiceDescriptor();
    }

    protected T createServiceInterface(IBinder binder) {
        return this.mClientImpl.createServiceInterface(binder);
    }

    protected void onSetConnectState(int state, T service) {
        this.mClientImpl.setState(state, service);
    }
}
