package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public interface InternalGoogleApiClient {

    public interface InternalCallbacks {
        void handleOnConnectionFailed(ConnectionResult connectionResult);

        void handleOnConnectionSuccess(Bundle bundle);

        void handleOnConnectionSuspended(int i, boolean z);
    }

    ConnectionResult blockingConnect();

    ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    void connect();

    boolean disconnect();

    void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T t);

    <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t);

    @Nullable
    ConnectionResult getConnectionResult(@NonNull Api<?> api);

    boolean isConnected();

    boolean isConnecting();

    void maybeFinishDisconnectingLocked();

    boolean maybeSignIn(SignInConnectionListener signInConnectionListener);

    void maybeSignOut();
}
