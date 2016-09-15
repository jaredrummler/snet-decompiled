package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.InternalGoogleApiClient.InternalCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public class GoogleApiClientStateHolder implements InternalGoogleApiClient {
    private static final String TAG = "GACStateManager";
    private final GoogleApiAvailabilityLight mApiAvailability;
    final GoogleApiClientImpl mApiClient;
    final Map<Api<?>, Integer> mApiTypeMap;
    final ClientSettings mClientSettings;
    final Map<ClientKey<?>, Client> mClients;
    private final Context mContext;
    private ConnectionResult mDisconnectionCause;
    final Map<ClientKey<?>, ConnectionResult> mFailedConnections;
    private final TaskHandler mHandlerForTasks;
    final InternalCallbacks mInternalCallbacks;
    int mLastConnectionFailurePriority;
    private final Lock mLock;
    private boolean mShowCrossClientAuthToast;
    final AbstractClientBuilder<? extends SignInClient, SignInOptions> mSignInApiBuilder;
    private volatile GoogleApiClientState mState;
    private final Condition mStateChanged;

    static abstract class HandlerTask {
        private final GoogleApiClientState mOwner;

        protected abstract void runLocked();

        protected HandlerTask(GoogleApiClientState owner) {
            this.mOwner = owner;
        }

        public final void run(GoogleApiClientStateHolder apiClient) {
            apiClient.mLock.lock();
            try {
                if (apiClient.mState == this.mOwner) {
                    runLocked();
                    apiClient.mLock.unlock();
                }
            } finally {
                apiClient.mLock.unlock();
            }
        }
    }

    final class TaskHandler extends Handler {
        static final int RUN_TASK = 1;
        static final int THROW = 2;

        TaskHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RUN_TASK /*1*/:
                    msg.obj.run(GoogleApiClientStateHolder.this);
                case THROW /*2*/:
                    throw ((RuntimeException) msg.obj);
                default:
                    Log.w(GoogleApiClientStateHolder.TAG, "Unknown message id: " + msg.what);
            }
        }
    }

    public GoogleApiClientStateHolder(Context context, GoogleApiClientImpl apiClient, Lock lock, Looper looper, GoogleApiAvailabilityLight apiAvailability, Map<ClientKey<?>, Client> clients, ClientSettings commonSettings, Map<Api<?>, Integer> apiTypeMap, AbstractClientBuilder<? extends SignInClient, SignInOptions> signInApiBuilder, ArrayList<ClientCallbacks> clientCallbacks, InternalCallbacks internalCallbacks) {
        this.mFailedConnections = new HashMap();
        this.mDisconnectionCause = null;
        this.mContext = context;
        this.mLock = lock;
        this.mApiAvailability = apiAvailability;
        this.mClients = clients;
        this.mClientSettings = commonSettings;
        this.mApiTypeMap = apiTypeMap;
        this.mSignInApiBuilder = signInApiBuilder;
        this.mApiClient = apiClient;
        this.mInternalCallbacks = internalCallbacks;
        Iterator i$ = clientCallbacks.iterator();
        while (i$.hasNext()) {
            ((ClientCallbacks) i$.next()).setGoogleApiClient(this);
        }
        this.mHandlerForTasks = new TaskHandler(looper);
        this.mStateChanged = lock.newCondition();
        this.mState = new GoogleApiClientDisconnected(this);
    }

    @GuardedBy("mLock")
    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T methodImpl) {
        return this.mState.enqueue(methodImpl);
    }

    @GuardedBy("mLock")
    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T methodImpl) {
        return this.mState.execute(methodImpl);
    }

    @GuardedBy("mLock")
    public void connect() {
        this.mState.connect();
    }

    @GuardedBy("mLock")
    public ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.mStateChanged.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.mDisconnectionCause != null) {
            return this.mDisconnectionCause;
        }
        return new ConnectionResult(13, null);
    }

    @GuardedBy("mLock")
    public ConnectionResult blockingConnect(long timeout, TimeUnit unit) {
        connect();
        long nanosTimeout = unit.toNanos(timeout);
        while (isConnecting()) {
            if (nanosTimeout <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            }
            nanosTimeout = this.mStateChanged.awaitNanos(nanosTimeout);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.mDisconnectionCause != null) {
            return this.mDisconnectionCause;
        }
        return new ConnectionResult(13, null);
    }

    @GuardedBy("mLock")
    public boolean disconnect() {
        boolean disconnectSuccessful = this.mState.disconnect();
        if (disconnectSuccessful) {
            this.mFailedConnections.clear();
        }
        return disconnectSuccessful;
    }

    @Nullable
    @GuardedBy("mLock")
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        ClientKey<?> clientKey = api.getClientKey();
        if (this.mClients.containsKey(clientKey)) {
            if (((Client) this.mClients.get(clientKey)).isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            if (this.mFailedConnections.containsKey(clientKey)) {
                return (ConnectionResult) this.mFailedConnections.get(clientKey);
            }
        }
        return null;
    }

    void changeToConnecting() {
        this.mLock.lock();
        try {
            this.mState = new GoogleApiClientConnecting(this, this.mClientSettings, this.mApiTypeMap, this.mApiAvailability, this.mSignInApiBuilder, this.mLock, this.mContext);
            this.mState.begin();
            this.mStateChanged.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    void changeToConnected() {
        this.mLock.lock();
        try {
            this.mApiClient.stopResumingLocked();
            this.mState = new GoogleApiClientConnected(this);
            this.mState.begin();
            this.mStateChanged.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    void changeToDisconnected(ConnectionResult failure) {
        this.mLock.lock();
        try {
            this.mDisconnectionCause = failure;
            this.mState = new GoogleApiClientDisconnected(this);
            this.mState.begin();
            this.mStateChanged.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    @GuardedBy("mLock")
    void disconnectClientsLocked() {
        for (Client client : this.mClients.values()) {
            client.disconnect();
        }
    }

    public boolean isConnected() {
        return this.mState instanceof GoogleApiClientConnected;
    }

    public boolean isConnecting() {
        return this.mState instanceof GoogleApiClientConnecting;
    }

    public boolean maybeSignIn(SignInConnectionListener listener) {
        return false;
    }

    public void maybeSignOut() {
    }

    @GuardedBy("mLock")
    public void maybeFinishDisconnectingLocked() {
        if (isConnected()) {
            ((GoogleApiClientConnected) this.mState).maybeFinishDisconnecting();
        }
    }

    public void onConnectionFailed(@NonNull ConnectionResult result, @NonNull Api<?> api, int apiType) {
        this.mLock.lock();
        try {
            this.mState.onConnectionFailed(result, api, apiType);
        } finally {
            this.mLock.unlock();
        }
    }

    public void onConnected(@Nullable Bundle connectionHint) {
        this.mLock.lock();
        try {
            this.mState.onConnected(connectionHint);
        } finally {
            this.mLock.unlock();
        }
    }

    public void onConnectionSuspended(int cause) {
        this.mLock.lock();
        try {
            this.mState.onConnectionSuspended(cause);
        } finally {
            this.mLock.unlock();
        }
    }

    void postToHandler(HandlerTask task) {
        this.mHandlerForTasks.sendMessage(this.mHandlerForTasks.obtainMessage(1, task));
    }

    void postToHandler(RuntimeException e) {
        this.mHandlerForTasks.sendMessage(this.mHandlerForTasks.obtainMessage(2, e));
    }

    @VisibleForTesting
    GoogleApiClientState getStateForTesting() {
        return this.mState;
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        String innerPrefix = prefix + "  ";
        for (Api<?> api : this.mApiTypeMap.keySet()) {
            writer.append(prefix).append(api.getName()).println(":");
            ((Client) this.mClients.get(api.getClientKey())).dump(innerPrefix, fd, writer, args);
        }
    }
}
