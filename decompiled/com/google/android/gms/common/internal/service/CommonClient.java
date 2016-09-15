package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.service.ICommonService.Stub;

public class CommonClient extends GmsClient<ICommonService> {
    private static final String SERVICE_DESCRIPTOR = "com.google.android.gms.common.internal.service.ICommonService";
    public static final String START_SERVICE_ACTION = "com.google.android.gms.common.service.START";

    public CommonClient(Context context, Looper looper, ClientSettings commonSettings, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        super(context, looper, 39, commonSettings, connectedListener, connectionFailedListener);
    }

    protected ICommonService createServiceInterface(IBinder binder) {
        return Stub.asInterface(binder);
    }

    public String getStartServiceAction() {
        return START_SERVICE_ACTION;
    }

    protected String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }
}
