package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

public final class Common {
    public static final Api<NoOptions> API;
    private static final AbstractClientBuilder<CommonClient, NoOptions> CLIENT_BUILDER;
    public static final ClientKey<CommonClient> CLIENT_KEY;
    public static final CommonApi CommonApi;

    static {
        CLIENT_KEY = new ClientKey();
        CLIENT_BUILDER = new AbstractClientBuilder<CommonClient, NoOptions>() {
            public CommonClient buildClient(Context context, Looper looper, ClientSettings commonSettings, NoOptions apiOptions, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
                return new CommonClient(context, looper, commonSettings, connectedListener, connectionFailedListener);
            }
        };
        API = new Api("Common.API", CLIENT_BUILDER, CLIENT_KEY);
        CommonApi = new CommonApiImpl();
    }
}
