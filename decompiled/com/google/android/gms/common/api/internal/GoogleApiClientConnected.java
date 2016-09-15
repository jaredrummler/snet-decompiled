package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

public class GoogleApiClientConnected implements GoogleApiClientState {
    private static final String TAG = "GACConnected";
    private boolean mDisconnectPending;
    private final GoogleApiClientStateHolder mHolder;

    /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientConnected.1 */
    class AnonymousClass1 extends HandlerTask {
        AnonymousClass1(GoogleApiClientState x0) {
            super(x0);
        }

        public void runLocked() {
            GoogleApiClientConnected.this.onConnectionSuspended(1);
        }
    }

    /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientConnected.2 */
    class AnonymousClass2 extends HandlerTask {
        AnonymousClass2(GoogleApiClientState x0) {
            super(x0);
        }

        public void runLocked() {
            GoogleApiClientConnected.this.mHolder.mInternalCallbacks.handleOnConnectionSuccess(null);
        }
    }

    public GoogleApiClientConnected(GoogleApiClientStateHolder holder) {
        this.mDisconnectPending = false;
        this.mHolder = holder;
    }

    public void begin() {
    }

    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T methodImpl) {
        return execute(methodImpl);
    }

    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(T methodImpl) {
        try {
            executeImpl(methodImpl);
        } catch (DeadObjectException e) {
            this.mHolder.postToHandler(new AnonymousClass1(this));
        }
        return methodImpl;
    }

    public boolean disconnect() {
        if (this.mDisconnectPending) {
            return false;
        }
        if (this.mHolder.mApiClient.hasPendingTransforms()) {
            this.mDisconnectPending = true;
            for (TransformedResultImpl<?> transformedResult : this.mHolder.mApiClient.mPendingTransforms) {
                transformedResult.clearCallbacks();
            }
            return false;
        }
        this.mHolder.changeToDisconnected(null);
        return true;
    }

    public void connect() {
        if (this.mDisconnectPending) {
            this.mDisconnectPending = false;
            this.mHolder.postToHandler(new AnonymousClass2(this));
        }
    }

    public void onConnected(Bundle connectionHint) {
    }

    public void onConnectionFailed(ConnectionResult result, Api<?> api, int apiType) {
    }

    public void onConnectionSuspended(int cause) {
        this.mHolder.changeToDisconnected(null);
        this.mHolder.mInternalCallbacks.handleOnConnectionSuspended(cause, this.mDisconnectPending);
    }

    public String getName() {
        return "CONNECTED";
    }

    void maybeFinishDisconnecting() {
        if (this.mDisconnectPending) {
            this.mDisconnectPending = false;
            this.mHolder.mApiClient.processUnconsumedRunnersLocked(false);
            disconnect();
        }
    }

    private <A extends Client> void executeImpl(Runner<A> runner) throws DeadObjectException {
        this.mHolder.mApiClient.addUnconsumedRunner(runner);
        A client = this.mHolder.mApiClient.getClient(runner.getClientKey());
        if (client.isConnected() || !this.mHolder.mFailedConnections.containsKey(runner.getClientKey())) {
            runner.run(client);
        } else {
            runner.setFailedResult(new Status(17));
        }
    }
}
