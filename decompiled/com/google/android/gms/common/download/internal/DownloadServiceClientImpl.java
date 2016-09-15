package com.google.android.gms.common.download.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.download.internal.IDownloadService.Stub;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

public class DownloadServiceClientImpl extends GmsClient<IDownloadService> {
    public static final String ACTION_START_SERVICE = "com.google.android.gms.common.download.START";
    public static final String SERVICE_DESCRIPTOR = "com.google.android.gms.common.download.internal.IDownloadService";

    public DownloadServiceClientImpl(Context context, Looper looper, ClientSettings commonSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener connectionFailedListener) {
        super(context, looper, 43, commonSettings, connectionCallbacks, connectionFailedListener);
    }

    protected String getStartServiceAction() {
        return ACTION_START_SERVICE;
    }

    protected String getServiceDescriptor() {
        return SERVICE_DESCRIPTOR;
    }

    protected IDownloadService createServiceInterface(IBinder binder) {
        return Stub.asInterface(binder);
    }
}
