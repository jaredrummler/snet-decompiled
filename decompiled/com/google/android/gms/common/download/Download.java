package com.google.android.gms.common.download;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.download.internal.DownloadApiImpl;
import com.google.android.gms.common.download.internal.DownloadServiceClientImpl;
import com.google.android.gms.common.internal.ClientSettings;

public class Download {
    public static final Api<NoOptions> API;
    static final AbstractClientBuilder<DownloadServiceClientImpl, NoOptions> CLIENT_BUILDER;
    public static final ClientKey<DownloadServiceClientImpl> CLIENT_KEY;
    public static final DownloadApi DownloadApi;

    private Download() {
    }

    static {
        CLIENT_KEY = new ClientKey();
        CLIENT_BUILDER = new AbstractClientBuilder<DownloadServiceClientImpl, NoOptions>() {
            public DownloadServiceClientImpl buildClient(Context context, Looper looper, ClientSettings commonSettings, NoOptions apiOptions, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
                return new DownloadServiceClientImpl(context, looper, commonSettings, connectedListener, connectionFailedListener);
            }
        };
        API = new Api("Download.API", CLIENT_BUILDER, CLIENT_KEY);
        DownloadApi = new DownloadApiImpl();
    }
}
