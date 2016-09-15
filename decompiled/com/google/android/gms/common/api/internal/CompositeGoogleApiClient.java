package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.InternalGoogleApiClient.InternalCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public class CompositeGoogleApiClient implements InternalGoogleApiClient {
    private static final int CONNECTION_MODE_ALL = 2;
    private static final int CONNECTION_MODE_NONE = 0;
    private static final int CONNECTION_MODE_SIGN_IN = 1;
    private static final String TAG = "CompositeGAC";
    private ConnectionResult mAnonResult;
    private final GoogleApiClientStateHolder mAnonymousClient;
    private final GoogleApiClientImpl mApiClient;
    private ConnectionResult mAuthResult;
    private final GoogleApiClientStateHolder mAuthenticatedClient;
    private boolean mCallOnConnectionSuspended;
    @GuardedBy("mLock")
    private int mConnectionMode;
    private final Context mContext;
    private final Map<ClientKey<?>, GoogleApiClientStateHolder> mGoogleApiClientMap;
    private final Lock mLock;
    private final Looper mLooper;
    private Bundle mOnConnectedHint;
    private final Client mSignInClient;
    private final Set<SignInConnectionListener> mSignInConnListeners;

    public CompositeGoogleApiClient(Context context, GoogleApiClientImpl apiClient, Lock lock, Looper looper, GoogleApiAvailabilityLight apiAvailability, Map<ClientKey<?>, Client> clients, ClientSettings commonSettings, Map<Api<?>, Integer> apiTypeMap, AbstractClientBuilder<? extends SignInClient, SignInOptions> signInApiBuilder, ArrayList<ClientCallbacks> clientCallbacks) {
        Iterator i$;
        ClientKey<?> clientKey;
        this.mGoogleApiClientMap = new ArrayMap();
        this.mSignInConnListeners = Collections.newSetFromMap(new WeakHashMap());
        this.mAnonResult = null;
        this.mAuthResult = null;
        this.mCallOnConnectionSuspended = false;
        this.mConnectionMode = CONNECTION_MODE_NONE;
        this.mContext = context;
        this.mApiClient = apiClient;
        this.mLock = lock;
        this.mLooper = looper;
        Client signInClient = null;
        Map<ClientKey<?>, Client> authenticatedClients = new ArrayMap();
        Map<ClientKey<?>, Client> anonymousClients = new ArrayMap();
        for (ClientKey<?> clientKey2 : clients.keySet()) {
            Client client = (Client) clients.get(clientKey2);
            if (client.providesSignIn()) {
                signInClient = client;
            }
            if (client.requiresSignIn()) {
                authenticatedClients.put(clientKey2, client);
            } else {
                anonymousClients.put(clientKey2, client);
            }
        }
        this.mSignInClient = signInClient;
        if (authenticatedClients.isEmpty()) {
            throw new IllegalStateException("CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        }
        Map<Api<?>, Integer> authenticatedTypes = new ArrayMap();
        Map<Api<?>, Integer> anonymousTypes = new ArrayMap();
        for (Api<?> api : apiTypeMap.keySet()) {
            clientKey2 = api.getClientKey();
            if (authenticatedClients.containsKey(clientKey2)) {
                authenticatedTypes.put(api, apiTypeMap.get(api));
            } else if (anonymousClients.containsKey(clientKey2)) {
                anonymousTypes.put(api, apiTypeMap.get(api));
            } else {
                throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList<ClientCallbacks> authClientCallbacks = new ArrayList();
        ArrayList<ClientCallbacks> anonClientCallbacks = new ArrayList();
        i$ = clientCallbacks.iterator();
        while (i$.hasNext()) {
            ClientCallbacks callbacks = (ClientCallbacks) i$.next();
            if (authenticatedTypes.containsKey(callbacks.mApi)) {
                authClientCallbacks.add(callbacks);
            } else if (anonymousTypes.containsKey(callbacks.mApi)) {
                anonClientCallbacks.add(callbacks);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
            }
        }
        Context context2 = context;
        this.mAnonymousClient = new GoogleApiClientStateHolder(context2, this.mApiClient, lock, looper, apiAvailability, anonymousClients, null, anonymousTypes, null, anonClientCallbacks, new InternalCallbacks() {
            public void handleOnConnectionSuccess(@Nullable Bundle connectionHint) {
                CompositeGoogleApiClient.this.mLock.lock();
                try {
                    CompositeGoogleApiClient.this.mergeOnConnectedHint(connectionHint);
                    CompositeGoogleApiClient.this.mAnonResult = ConnectionResult.RESULT_SUCCESS;
                    CompositeGoogleApiClient.this.tryCallCallbacksLocked();
                } finally {
                    CompositeGoogleApiClient.this.mLock.unlock();
                }
            }

            public void handleOnConnectionFailed(@NonNull ConnectionResult result) {
                CompositeGoogleApiClient.this.mLock.lock();
                try {
                    CompositeGoogleApiClient.this.mAnonResult = result;
                    CompositeGoogleApiClient.this.tryCallCallbacksLocked();
                } finally {
                    CompositeGoogleApiClient.this.mLock.unlock();
                }
            }

            public void handleOnConnectionSuspended(int cause, boolean disconnectPending) {
                CompositeGoogleApiClient.this.mLock.lock();
                try {
                    if (CompositeGoogleApiClient.this.mCallOnConnectionSuspended || CompositeGoogleApiClient.this.mAuthResult == null || !CompositeGoogleApiClient.this.mAuthResult.isSuccess()) {
                        CompositeGoogleApiClient.this.mCallOnConnectionSuspended = false;
                        CompositeGoogleApiClient.this.handleOnConnectionSuspendedLocked(cause, disconnectPending);
                        return;
                    }
                    CompositeGoogleApiClient.this.mCallOnConnectionSuspended = true;
                    CompositeGoogleApiClient.this.mAuthenticatedClient.onConnectionSuspended(cause);
                    CompositeGoogleApiClient.this.mLock.unlock();
                } finally {
                    CompositeGoogleApiClient.this.mLock.unlock();
                }
            }
        });
        InternalCallbacks anonymousClass2 = new InternalCallbacks() {
            public void handleOnConnectionSuccess(@Nullable Bundle connectionHint) {
                CompositeGoogleApiClient.this.mLock.lock();
                try {
                    CompositeGoogleApiClient.this.mAuthResult = ConnectionResult.RESULT_SUCCESS;
                    CompositeGoogleApiClient.this.tryCallCallbacksLocked();
                } finally {
                    CompositeGoogleApiClient.this.mLock.unlock();
                }
            }

            public void handleOnConnectionFailed(@NonNull ConnectionResult result) {
                CompositeGoogleApiClient.this.mLock.lock();
                try {
                    CompositeGoogleApiClient.this.mAuthResult = result;
                    CompositeGoogleApiClient.this.tryCallCallbacksLocked();
                } finally {
                    CompositeGoogleApiClient.this.mLock.unlock();
                }
            }

            public void handleOnConnectionSuspended(int cause, boolean disconnectPending) {
                CompositeGoogleApiClient.this.mLock.lock();
                try {
                    if (CompositeGoogleApiClient.this.mCallOnConnectionSuspended) {
                        CompositeGoogleApiClient.this.mCallOnConnectionSuspended = false;
                        CompositeGoogleApiClient.this.handleOnConnectionSuspendedLocked(cause, disconnectPending);
                        return;
                    }
                    CompositeGoogleApiClient.this.mCallOnConnectionSuspended = true;
                    CompositeGoogleApiClient.this.mAnonymousClient.onConnectionSuspended(cause);
                    CompositeGoogleApiClient.this.mLock.unlock();
                } finally {
                    CompositeGoogleApiClient.this.mLock.unlock();
                }
            }
        };
        Context context3 = context;
        this.mAuthenticatedClient = new GoogleApiClientStateHolder(context3, this.mApiClient, lock, looper, apiAvailability, authenticatedClients, commonSettings, authenticatedTypes, signInApiBuilder, authClientCallbacks, anonymousClass2);
        for (ClientKey<?> clientKey22 : anonymousClients.keySet()) {
            this.mGoogleApiClientMap.put(clientKey22, this.mAnonymousClient);
        }
        for (ClientKey<?> clientKey222 : authenticatedClients.keySet()) {
            this.mGoogleApiClientMap.put(clientKey222, this.mAuthenticatedClient);
        }
    }

    @GuardedBy("mLock")
    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T methodImpl) {
        if (!isAuthenticatedCall(methodImpl)) {
            return this.mAnonymousClient.enqueue(methodImpl);
        }
        if (!hasSignInFailedWithSignInRequiredLocked()) {
            return this.mAuthenticatedClient.enqueue(methodImpl);
        }
        methodImpl.setFailedResult(new Status(4, null, getSignInResolution()));
        return methodImpl;
    }

    @GuardedBy("mLock")
    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T methodImpl) {
        if (!isAuthenticatedCall(methodImpl)) {
            return this.mAnonymousClient.execute(methodImpl);
        }
        if (!hasSignInFailedWithSignInRequiredLocked()) {
            return this.mAuthenticatedClient.execute(methodImpl);
        }
        methodImpl.setFailedResult(new Status(4, null, getSignInResolution()));
        return methodImpl;
    }

    @Nullable
    @GuardedBy("mLock")
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        if (!((GoogleApiClientStateHolder) this.mGoogleApiClientMap.get(api.getClientKey())).equals(this.mAuthenticatedClient)) {
            return this.mAnonymousClient.getConnectionResult(api);
        }
        if (hasSignInFailedWithSignInRequiredLocked()) {
            return new ConnectionResult(4, getSignInResolution());
        }
        return this.mAuthenticatedClient.getConnectionResult(api);
    }

    @GuardedBy("mLock")
    public void connect() {
        this.mConnectionMode = CONNECTION_MODE_ALL;
        this.mCallOnConnectionSuspended = false;
        connectInternalLocked();
    }

    @GuardedBy("mLock")
    public ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    @GuardedBy("mLock")
    public ConnectionResult blockingConnect(long timeout, @NonNull TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @GuardedBy("mLock")
    public boolean disconnect() {
        this.mAuthResult = null;
        this.mAnonResult = null;
        this.mConnectionMode = CONNECTION_MODE_NONE;
        boolean anonDisconnectSuccessful = this.mAnonymousClient.disconnect();
        boolean authDisconnectSuccessful = this.mAuthenticatedClient.disconnect();
        callSignInCallbacksLocked();
        if (anonDisconnectSuccessful && authDisconnectSuccessful) {
            return true;
        }
        return false;
    }

    public boolean isConnected() {
        boolean z = true;
        this.mLock.lock();
        try {
            if (!(this.mAnonymousClient.isConnected() && (isSignedIn() || hasSignInFailedWithSignInRequiredLocked() || this.mConnectionMode == CONNECTION_MODE_SIGN_IN))) {
                z = false;
            }
            this.mLock.unlock();
            return z;
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    public boolean isConnecting() {
        this.mLock.lock();
        try {
            boolean z = this.mConnectionMode == CONNECTION_MODE_ALL;
            this.mLock.unlock();
            return z;
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    public boolean maybeSignIn(SignInConnectionListener listener) {
        this.mLock.lock();
        try {
            if ((isConnecting() || isConnected()) && !isSignedIn()) {
                this.mSignInConnListeners.add(listener);
                if (this.mConnectionMode == 0) {
                    this.mConnectionMode = CONNECTION_MODE_SIGN_IN;
                }
                this.mAuthResult = null;
                this.mAuthenticatedClient.connect();
                return true;
            }
            this.mLock.unlock();
            return false;
        } finally {
            this.mLock.unlock();
        }
    }

    @GuardedBy("mLock")
    public void maybeFinishDisconnectingLocked() {
        this.mAnonymousClient.maybeFinishDisconnectingLocked();
        this.mAuthenticatedClient.maybeFinishDisconnectingLocked();
    }

    public void maybeSignOut() {
        this.mLock.lock();
        try {
            boolean wasConnectingOpportunistically = isConnecting();
            this.mAuthenticatedClient.disconnect();
            this.mAuthResult = new ConnectionResult(4);
            if (wasConnectingOpportunistically) {
                new Handler(this.mLooper).post(new Runnable() {
                    public void run() {
                        CompositeGoogleApiClient.this.mLock.lock();
                        try {
                            CompositeGoogleApiClient.this.tryCallCallbacksLocked();
                        } finally {
                            CompositeGoogleApiClient.this.mLock.unlock();
                        }
                    }
                });
            } else {
                callSignInCallbacksLocked();
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    @VisibleForTesting
    public boolean isSignedIn() {
        return this.mAuthenticatedClient.isConnected();
    }

    @GuardedBy("mLock")
    private void connectInternalLocked() {
        this.mAuthResult = null;
        this.mAnonResult = null;
        this.mAnonymousClient.connect();
        this.mAuthenticatedClient.connect();
    }

    @GuardedBy("mLock")
    private void tryCallCallbacksLocked() {
        if (isConnectionSuccess(this.mAnonResult)) {
            if (isConnectionSuccess(this.mAuthResult) || hasSignInFailedWithSignInRequiredLocked()) {
                callSuccessCallbacksLocked();
            } else if (this.mAuthResult == null) {
            } else {
                if (this.mConnectionMode == CONNECTION_MODE_SIGN_IN) {
                    callSignInCallbacksLocked();
                    return;
                }
                callFailureCallbacksLocked(this.mAuthResult);
                this.mAnonymousClient.disconnect();
            }
        } else if (this.mAnonResult != null && isConnectionSuccess(this.mAuthResult)) {
            this.mAuthenticatedClient.disconnect();
            callFailureCallbacksLocked(this.mAnonResult);
        } else if (this.mAnonResult != null && this.mAuthResult != null) {
            ConnectionResult worstFailure = this.mAnonResult;
            if (this.mAuthenticatedClient.mLastConnectionFailurePriority < this.mAnonymousClient.mLastConnectionFailurePriority) {
                worstFailure = this.mAuthResult;
            }
            callFailureCallbacksLocked(worstFailure);
        }
    }

    @GuardedBy("mLock")
    private void callSuccessCallbacksLocked() {
        switch (this.mConnectionMode) {
            case CONNECTION_MODE_SIGN_IN /*1*/:
                break;
            case CONNECTION_MODE_ALL /*2*/:
                this.mApiClient.handleOnConnectionSuccess(this.mOnConnectedHint);
                break;
            default:
                Log.wtf(TAG, "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        callSignInCallbacksLocked();
        this.mConnectionMode = CONNECTION_MODE_NONE;
    }

    @GuardedBy("mLock")
    private void callFailureCallbacksLocked(ConnectionResult result) {
        switch (this.mConnectionMode) {
            case CONNECTION_MODE_SIGN_IN /*1*/:
                break;
            case CONNECTION_MODE_ALL /*2*/:
                this.mApiClient.handleOnConnectionFailed(result);
                break;
            default:
                Log.wtf(TAG, "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        callSignInCallbacksLocked();
        this.mConnectionMode = CONNECTION_MODE_NONE;
    }

    @GuardedBy("mLock")
    private void callSignInCallbacksLocked() {
        for (SignInConnectionListener listener : this.mSignInConnListeners) {
            listener.onComplete();
        }
        this.mSignInConnListeners.clear();
    }

    @GuardedBy("mLock")
    private void handleOnConnectionSuspendedLocked(int cause, boolean disconnectPending) {
        this.mApiClient.handleOnConnectionSuspended(cause, disconnectPending);
        this.mAuthResult = null;
        this.mAnonResult = null;
    }

    @GuardedBy("mLock")
    private boolean hasSignInFailedWithSignInRequiredLocked() {
        return this.mAuthResult != null && this.mAuthResult.getErrorCode() == 4;
    }

    private boolean isAuthenticatedCall(ApiMethodImpl<? extends Result, ? extends Client> methodImpl) {
        ClientKey key = methodImpl.getClientKey();
        Preconditions.checkArgument(this.mGoogleApiClientMap.containsKey(key), "GoogleApiClient is not configured to use the API required for this call.");
        return ((GoogleApiClientStateHolder) this.mGoogleApiClientMap.get(key)).equals(this.mAuthenticatedClient);
    }

    @Nullable
    private PendingIntent getSignInResolution() {
        if (this.mSignInClient == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, this.mApiClient.getSessionId(), this.mSignInClient.getSignInIntent(), 134217728);
    }

    private void mergeOnConnectedHint(Bundle onConnectedHint) {
        if (this.mOnConnectedHint == null) {
            this.mOnConnectedHint = onConnectedHint;
        } else if (onConnectedHint != null) {
            this.mOnConnectedHint.putAll(onConnectedHint);
        }
    }

    private static boolean isConnectionSuccess(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.append(prefix).append("authClient").println(":");
        this.mAuthenticatedClient.dump(prefix + "  ", fd, writer, args);
        writer.append(prefix).append("anonClient").println(":");
        this.mAnonymousClient.dump(prefix + "  ", fd, writer, args);
    }
}
