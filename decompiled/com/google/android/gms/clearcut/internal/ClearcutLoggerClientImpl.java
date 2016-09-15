package com.google.android.gms.clearcut.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.clearcut.internal.IClearcutLoggerService.Stub;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public class ClearcutLoggerClientImpl extends GmsClient<IClearcutLoggerService> {
    public static final String ACTION_START_SERVICE = "com.google.android.gms.clearcut.service.START";
    private static final String SERVICE_DESCRIPTOR = "com.google.android.gms.clearcut.internal.IClearcutLoggerService";

    public ClearcutLoggerClientImpl(Context context, Looper looper, ClientSettings commonSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener connectionFailedListener) {
        super(context, looper, 40, commonSettings, connectionCallbacks, connectionFailedListener);
    }

    protected String getStartServiceAction() {
        return ACTION_START_SERVICE;
    }

    protected String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }

    protected IClearcutLoggerService createServiceInterface(IBinder binder) {
        return Stub.asInterface(binder);
    }

    public void logEvent(IClearcutLoggerCallbacks callback, LogEventParcelable logEvent) throws RemoteException {
        ((IClearcutLoggerService) getService()).logEvent(callback, logEvent);
    }
}
