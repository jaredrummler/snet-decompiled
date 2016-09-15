package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState;
import com.google.android.gms.common.internal.IGmsCallbacks.Stub;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gsf.GoogleLoginServiceConstants;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public abstract class GmsClient<T extends IInterface> implements Client, GmsClientEventState {
    public static final int CONNECT_STATE_CONNECTED = 3;
    public static final int CONNECT_STATE_CONNECTING = 2;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 4;
    private static final boolean DBG = false;
    public static final String FEATURE_GOOGLE_ME = "service_googleme";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private static final String TAG = "GmsClient";
    private long lastConnectedTime;
    private int lastFailedStatusCode;
    private long lastFailedTime;
    private int lastSuspendedCause;
    private long lastSuspendedTime;
    private final Account mAccount;
    private final GoogleApiAvailabilityLight mApiAvailability;
    private final ArrayList<CallbackProxy<?>> mCallbackProxyList;
    private final ClientSettings mClientSettings;
    @GuardedBy("mLock")
    private int mConnectState;
    @GuardedBy("mLock")
    private GmsServiceConnection mConnection;
    private final ConnectionCallbacks mConnectionCallbacks;
    private final OnConnectionFailedListener mConnectionFailedListener;
    private ConnectionProgressReportCallbacks mConnectionProgressReportCallbacks;
    private final Context mContext;
    @VisibleForTesting
    protected AtomicInteger mDisconnectCount;
    private final int mGCoreServiceId;
    final Handler mHandler;
    private final Object mLock;
    private final Looper mLooper;
    private final Set<Scope> mScopes;
    @GuardedBy("mLock")
    private T mService;
    @GuardedBy("mServiceBrokerLock")
    private IGmsServiceBroker mServiceBroker;
    private final Object mServiceBrokerLock;
    private final GmsClientSupervisor mSupervisor;

    protected abstract class CallbackProxy<TListener> {
        private boolean mCallbackDelivered;
        private TListener mListener;

        protected abstract void deliverCallback(TListener tListener);

        protected abstract void onDeliverCallbackFailed();

        public CallbackProxy(TListener listener) {
            this.mListener = listener;
            this.mCallbackDelivered = GmsClient.DBG;
        }

        public void deliverCallback() {
            synchronized (this) {
                TListener listener = this.mListener;
                if (this.mCallbackDelivered) {
                    Log.w(GmsClient.TAG, "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (listener != null) {
                try {
                    deliverCallback(listener);
                } catch (RuntimeException e) {
                    onDeliverCallbackFailed();
                    throw e;
                }
            }
            onDeliverCallbackFailed();
            synchronized (this) {
                this.mCallbackDelivered = true;
            }
            unregister();
        }

        public void unregister() {
            removeListener();
            synchronized (GmsClient.this.mCallbackProxyList) {
                GmsClient.this.mCallbackProxyList.remove(this);
            }
        }

        public void removeListener() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        @VisibleForTesting
        boolean hasListenerForTest() {
            return this.mListener != null ? true : GmsClient.DBG;
        }

        @VisibleForTesting
        boolean isRegisteredForTest() {
            return GmsClient.this.mCallbackProxyList.contains(this);
        }
    }

    private abstract class ApiServiceCallback extends CallbackProxy<Boolean> {
        public final Bundle resolution;
        public final int statusCode;

        protected abstract void handleServiceFailure(ConnectionResult connectionResult);

        protected abstract boolean handleServiceSuccess();

        @BinderThread
        protected ApiServiceCallback(int statusCode, Bundle resolution) {
            super(Boolean.valueOf(true));
            this.statusCode = statusCode;
            this.resolution = resolution;
        }

        protected void deliverCallback(Boolean nullIfRemoved) {
            if (nullIfRemoved == null) {
                GmsClient.this.setConnectState(GmsClient.CONNECT_STATE_DISCONNECTED, null);
                return;
            }
            switch (this.statusCode) {
                case Action.UNKNOWN /*0*/:
                    if (!handleServiceSuccess()) {
                        GmsClient.this.setConnectState(GmsClient.CONNECT_STATE_DISCONNECTED, null);
                        handleServiceFailure(new ConnectionResult(8, null));
                    }
                case Type.TAP_ABOUT /*10*/:
                    GmsClient.this.setConnectState(GmsClient.CONNECT_STATE_DISCONNECTED, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    GmsClient.this.setConnectState(GmsClient.CONNECT_STATE_DISCONNECTED, null);
                    PendingIntent pendingIntent = null;
                    if (this.resolution != null) {
                        pendingIntent = (PendingIntent) this.resolution.getParcelable(GmsClient.KEY_PENDING_INTENT);
                    }
                    handleServiceFailure(new ConnectionResult(this.statusCode, pendingIntent));
            }
        }

        protected void onDeliverCallbackFailed() {
        }
    }

    final class CallbackHandler extends Handler {
        public static final int NORMAL_CALLBACK = 2;
        public static final int ON_CONNECTION_SUSPENDED = 4;
        public static final int ON_NOT_AVAILABLE = 3;
        public static final int ON_POST_BIND = 5;
        public static final int ON_POST_INIT = 1;

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            if (GmsClient.this.mDisconnectCount.get() != msg.arg1) {
                if (hasCallback(msg)) {
                    deliverCallbackFailed(msg);
                }
            } else if ((msg.what == ON_POST_INIT || msg.what == ON_POST_BIND) && !GmsClient.this.isConnecting()) {
                deliverCallbackFailed(msg);
            } else if (msg.what == ON_NOT_AVAILABLE) {
                ConnectionResult failure = new ConnectionResult(msg.arg2, null);
                GmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(failure);
                GmsClient.this.onConnectionFailed(failure);
            } else if (msg.what == ON_CONNECTION_SUSPENDED) {
                GmsClient.this.setConnectState(ON_CONNECTION_SUSPENDED, null);
                if (GmsClient.this.mConnectionCallbacks != null) {
                    GmsClient.this.mConnectionCallbacks.onConnectionSuspended(msg.arg2);
                }
                GmsClient.this.onConnectionSuspended(msg.arg2);
                GmsClient.this.compareAndSetConnectState(ON_CONNECTION_SUSPENDED, ON_POST_INIT, null);
            } else if (msg.what == NORMAL_CALLBACK && !GmsClient.this.isConnected()) {
                deliverCallbackFailed(msg);
            } else if (hasCallback(msg)) {
                msg.obj.deliverCallback();
            } else {
                Log.wtf(GmsClient.TAG, "Don't know how to handle message: " + msg.what, new Exception());
            }
        }

        private void deliverCallbackFailed(Message msg) {
            CallbackProxy<?> callback = msg.obj;
            callback.onDeliverCallbackFailed();
            callback.unregister();
        }

        private boolean hasCallback(Message msg) {
            return (msg.what == NORMAL_CALLBACK || msg.what == ON_POST_INIT || msg.what == ON_POST_BIND) ? true : GmsClient.DBG;
        }
    }

    @VisibleForTesting
    public static final class GmsCallbacks extends Stub {
        private final int mDisconnectCount;
        private GmsClient mGmsClient;

        public GmsCallbacks(@NonNull GmsClient gmsClient, int disconnectCount) {
            this.mGmsClient = gmsClient;
            this.mDisconnectCount = disconnectCount;
        }

        @BinderThread
        public void onAccountValidationComplete(int statusCode, @Nullable Bundle resolution) {
            Log.wtf(GmsClient.TAG, "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @BinderThread
        public void onPostInitComplete(int statusCode, @NonNull IBinder service, @Nullable Bundle resolution) {
            Preconditions.checkNotNull(this.mGmsClient, "onPostInitComplete can be called only once per call to getRemoteService");
            this.mGmsClient.onPostInitHandler(statusCode, service, resolution, this.mDisconnectCount);
            onCallbackComplete();
        }

        private void onCallbackComplete() {
            this.mGmsClient = null;
        }
    }

    @VisibleForTesting
    public final class GmsServiceConnection implements ServiceConnection {
        private final int mDisconnectCount;

        public GmsServiceConnection(int disconnectCount) {
            this.mDisconnectCount = disconnectCount;
        }

        public void onServiceConnected(ComponentName component, IBinder binder) {
            Preconditions.checkNotNull(binder, "Expecting a valid IBinder");
            synchronized (GmsClient.this.mServiceBrokerLock) {
                GmsClient.this.mServiceBroker = IGmsServiceBroker.Stub.asInterface(binder);
            }
            GmsClient.this.onPostServiceBindingHandler(0, this.mDisconnectCount);
        }

        public void onServiceDisconnected(ComponentName component) {
            synchronized (GmsClient.this.mServiceBrokerLock) {
                GmsClient.this.mServiceBroker = null;
            }
            GmsClient.this.mHandler.sendMessage(GmsClient.this.mHandler.obtainMessage(GmsClient.CONNECT_STATE_DISCONNECTING, this.mDisconnectCount, GmsClient.CONNECT_STATE_DISCONNECTED));
        }
    }

    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public void onReportServiceBinding(@NonNull ConnectionResult result) {
            if (result.isSuccess()) {
                GmsClient.this.getRemoteService(null, GmsClient.this.mScopes);
            } else if (GmsClient.this.mConnectionFailedListener != null) {
                GmsClient.this.mConnectionFailedListener.onConnectionFailed(result);
            }
        }
    }

    protected final class PostInitCallback extends ApiServiceCallback {
        public final IBinder service;

        @BinderThread
        public PostInitCallback(int statusCode, IBinder service, Bundle resolution) {
            super(statusCode, resolution);
            this.service = service;
        }

        protected void handleServiceFailure(ConnectionResult result) {
            if (GmsClient.this.mConnectionFailedListener != null) {
                GmsClient.this.mConnectionFailedListener.onConnectionFailed(result);
            }
            GmsClient.this.onConnectionFailed(result);
        }

        protected boolean handleServiceSuccess() {
            try {
                String descriptor = this.service.getInterfaceDescriptor();
                if (GmsClient.this.getServiceDescriptor().equals(descriptor)) {
                    T serviceInterface = GmsClient.this.createServiceInterface(this.service);
                    if (serviceInterface == null || !GmsClient.this.compareAndSetConnectState(GmsClient.CONNECT_STATE_CONNECTING, GmsClient.CONNECT_STATE_CONNECTED, serviceInterface)) {
                        return GmsClient.DBG;
                    }
                    Bundle connectionHint = GmsClient.this.getConnectionHint();
                    if (GmsClient.this.mConnectionCallbacks != null) {
                        GmsClient.this.mConnectionCallbacks.onConnected(connectionHint);
                    }
                    return true;
                }
                Log.e(GmsClient.TAG, "service descriptor mismatch: " + GmsClient.this.getServiceDescriptor() + " vs. " + descriptor);
                return GmsClient.DBG;
            } catch (RemoteException e) {
                Log.w(GmsClient.TAG, "service probably died");
                return GmsClient.DBG;
            }
        }
    }

    protected final class PostServiceBindingCallback extends ApiServiceCallback {
        @BinderThread
        public PostServiceBindingCallback(int statusCode) {
            super(statusCode, null);
        }

        protected void handleServiceFailure(ConnectionResult result) {
            GmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(result);
            GmsClient.this.onConnectionFailed(result);
        }

        protected boolean handleServiceSuccess() {
            GmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    @Nullable
    protected abstract T createServiceInterface(IBinder iBinder);

    @NonNull
    protected abstract String getServiceDescriptor();

    @NonNull
    protected abstract String getStartServiceAction();

    static {
        String[] strArr = new String[CONNECT_STATE_CONNECTING];
        strArr[0] = "service_esmobile";
        strArr[CONNECT_STATE_DISCONNECTED] = FEATURE_GOOGLE_ME;
        GOOGLE_PLUS_REQUIRED_FEATURES = strArr;
    }

    @VisibleForTesting
    protected GmsClient(Context context, Handler handler, int gCoreServiceId, ClientSettings clientSettings) {
        this(context, handler, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), gCoreServiceId, clientSettings, null, null);
    }

    protected GmsClient(Context context, Looper looper, int gCoreServiceId, ClientSettings clientSettings, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), gCoreServiceId, clientSettings, (ConnectionCallbacks) Preconditions.checkNotNull(connectedListener), (OnConnectionFailedListener) Preconditions.checkNotNull(connectionFailedListener));
    }

    protected GmsClient(Context context, Looper looper, int gCoreServiceId, ClientSettings clientSettings) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), gCoreServiceId, clientSettings, null, null);
    }

    @VisibleForTesting
    protected GmsClient(Context context, Looper looper, GmsClientSupervisor supervisor, GoogleApiAvailabilityLight apiAvailability, int gCoreServiceId, ClientSettings clientSettings, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        this.mLock = new Object();
        this.mServiceBrokerLock = new Object();
        this.mConnectionProgressReportCallbacks = new LegacyClientCallbackAdapter();
        this.mCallbackProxyList = new ArrayList();
        this.mConnectState = CONNECT_STATE_DISCONNECTED;
        this.mDisconnectCount = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mLooper = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.mSupervisor = (GmsClientSupervisor) Preconditions.checkNotNull(supervisor, "Supervisor must not be null");
        this.mApiAvailability = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(apiAvailability, "API availability must not be null");
        this.mHandler = new CallbackHandler(looper);
        this.mGCoreServiceId = gCoreServiceId;
        this.mClientSettings = (ClientSettings) Preconditions.checkNotNull(clientSettings);
        this.mAccount = clientSettings.getAccount();
        this.mScopes = validateScopesDisallowingExpansion(clientSettings.getAllRequestedScopes());
        this.mConnectionCallbacks = connectedListener;
        this.mConnectionFailedListener = connectionFailedListener;
    }

    @VisibleForTesting
    protected GmsClient(Context context, Handler handler, GmsClientSupervisor supervisor, GoogleApiAvailabilityLight apiAvailability, int gCoreServiceId, ClientSettings clientSettings, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
        this.mLock = new Object();
        this.mServiceBrokerLock = new Object();
        this.mConnectionProgressReportCallbacks = new LegacyClientCallbackAdapter();
        this.mCallbackProxyList = new ArrayList();
        this.mConnectState = CONNECT_STATE_DISCONNECTED;
        this.mDisconnectCount = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.mLooper = handler.getLooper();
        this.mSupervisor = (GmsClientSupervisor) Preconditions.checkNotNull(supervisor, "Supervisor must not be null");
        this.mApiAvailability = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(apiAvailability, "API availability must not be null");
        this.mGCoreServiceId = gCoreServiceId;
        this.mClientSettings = (ClientSettings) Preconditions.checkNotNull(clientSettings);
        this.mAccount = clientSettings.getAccount();
        this.mScopes = validateScopesDisallowingExpansion(clientSettings.getAllRequestedScopes());
        this.mConnectionCallbacks = connectedListener;
        this.mConnectionFailedListener = connectionFailedListener;
    }

    private Set<Scope> validateScopesDisallowingExpansion(Set<Scope> scopes) {
        Set<Scope> validated = validateScopes(scopes);
        if (validated != null) {
            for (Scope scope : validated) {
                if (!scopes.contains(scope)) {
                    throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
                }
            }
        }
        return validated;
    }

    @NonNull
    protected Set<Scope> validateScopes(@NonNull Set<Scope> scopes) {
        return scopes;
    }

    @Nullable
    protected final String getRealClientName() {
        return this.mClientSettings.getRealClientClassName();
    }

    @CallSuper
    protected void onConnectedLocked(@NonNull T t) {
        this.lastConnectedTime = System.currentTimeMillis();
    }

    @CallSuper
    protected void onConnectionSuspended(int cause) {
        this.lastSuspendedCause = cause;
        this.lastSuspendedTime = System.currentTimeMillis();
    }

    @CallSuper
    protected void onConnectionFailed(ConnectionResult result) {
        this.lastFailedStatusCode = result.getErrorCode();
        this.lastFailedTime = System.currentTimeMillis();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setConnectState(int r5, T r6) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        r2 = 3;
        if (r5 != r2) goto L_0x001d;
    L_0x0005:
        r3 = r0;
    L_0x0006:
        if (r6 == 0) goto L_0x001f;
    L_0x0008:
        r2 = r0;
    L_0x0009:
        if (r3 != r2) goto L_0x0021;
    L_0x000b:
        com.google.android.gms.common.internal.Preconditions.checkArgument(r0);
        r1 = r4.mLock;
        monitor-enter(r1);
        r4.mConnectState = r5;	 Catch:{ all -> 0x0027 }
        r4.mService = r6;	 Catch:{ all -> 0x0027 }
        r4.onSetConnectState(r5, r6);	 Catch:{ all -> 0x0027 }
        switch(r5) {
            case 1: goto L_0x002e;
            case 2: goto L_0x0023;
            case 3: goto L_0x002a;
            default: goto L_0x001b;
        };	 Catch:{ all -> 0x0027 }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        return;
    L_0x001d:
        r3 = r1;
        goto L_0x0006;
    L_0x001f:
        r2 = r1;
        goto L_0x0009;
    L_0x0021:
        r0 = r1;
        goto L_0x000b;
    L_0x0023:
        r4.bindServiceLocked();	 Catch:{ all -> 0x0027 }
        goto L_0x001b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r4.onConnectedLocked(r6);	 Catch:{ all -> 0x0027 }
        goto L_0x001b;
    L_0x002e:
        r4.unbindServiceLocked();	 Catch:{ all -> 0x0027 }
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClient.setConnectState(int, android.os.IInterface):void");
    }

    void onSetConnectState(int state, T t) {
    }

    @GuardedBy("mLock")
    private void bindServiceLocked() {
        if (this.mConnection != null) {
            Log.e(TAG, "Calling connect() while still connected, missing disconnect() for " + getStartServiceAction());
            this.mSupervisor.unbindService(getStartServiceAction(), this.mConnection, getRealClientName());
            this.mDisconnectCount.incrementAndGet();
        }
        this.mConnection = new GmsServiceConnection(this.mDisconnectCount.get());
        if (!this.mSupervisor.bindService(getStartServiceAction(), this.mConnection, getRealClientName())) {
            Log.e(TAG, "unable to connect to service: " + getStartServiceAction());
            onPostServiceBindingHandler(8, this.mDisconnectCount.get());
        }
    }

    @GuardedBy("mLock")
    private void unbindServiceLocked() {
        if (this.mConnection != null) {
            this.mSupervisor.unbindService(getStartServiceAction(), this.mConnection, getRealClientName());
            this.mConnection = null;
        }
    }

    private boolean compareAndSetConnectState(int expected, int state, T service) {
        boolean z;
        synchronized (this.mLock) {
            if (this.mConnectState != expected) {
                z = DBG;
            } else {
                setConnectState(state, service);
                z = true;
            }
        }
        return z;
    }

    public void checkAvailabilityAndConnect() {
        int errorCode = this.mApiAvailability.isGooglePlayServicesAvailable(this.mContext);
        if (errorCode != 0) {
            setConnectState(CONNECT_STATE_DISCONNECTED, null);
            this.mConnectionProgressReportCallbacks = new LegacyClientCallbackAdapter();
            this.mHandler.sendMessage(this.mHandler.obtainMessage(CONNECT_STATE_CONNECTED, this.mDisconnectCount.get(), errorCode));
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    public void connect(@NonNull ConnectionProgressReportCallbacks callbacks) {
        this.mConnectionProgressReportCallbacks = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(callbacks, "Connection progress callbacks cannot be null.");
        setConnectState(CONNECT_STATE_CONNECTING, null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnectState == CONNECT_STATE_CONNECTED ? true : DBG;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnectState == CONNECT_STATE_CONNECTING ? true : DBG;
        }
        return z;
    }

    public void disconnect() {
        this.mDisconnectCount.incrementAndGet();
        synchronized (this.mCallbackProxyList) {
            int numCallbacks = this.mCallbackProxyList.size();
            for (int i = 0; i < numCallbacks; i += CONNECT_STATE_DISCONNECTED) {
                ((CallbackProxy) this.mCallbackProxyList.get(i)).removeListener();
            }
            this.mCallbackProxyList.clear();
        }
        synchronized (this.mServiceBrokerLock) {
            this.mServiceBroker = null;
        }
        setConnectState(CONNECT_STATE_DISCONNECTED, null);
    }

    public void triggerConnectionSuspended(int cause) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(CONNECT_STATE_DISCONNECTING, this.mDisconnectCount.get(), cause));
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.mLooper;
    }

    public final Account getAccountOrDefault() {
        return this.mAccount != null ? this.mAccount : new Account(GoogleApiClient.DEFAULT_ACCOUNT, GoogleLoginServiceConstants.ACCOUNT_TYPE);
    }

    protected final ClientSettings getClientSettings() {
        return this.mClientSettings;
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @BinderThread
    protected void onPostInitHandler(int statusCode, IBinder service, Bundle resolution, int disconnectCount) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(CONNECT_STATE_DISCONNECTED, disconnectCount, -1, new PostInitCallback(statusCode, service, resolution)));
    }

    protected void onPostServiceBindingHandler(int statusCode, int disconnectCount) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, disconnectCount, -1, new PostServiceBindingCallback(statusCode)));
    }

    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @VisibleForTesting
    public final Handler getHandlerForTesting() {
        return this.mHandler;
    }

    public Bundle getConnectionHint() {
        return null;
    }

    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.mConnectState == CONNECT_STATE_DISCONNECTING) {
                throw new DeadObjectException();
            }
            checkConnected();
            Preconditions.checkState(this.mService != null ? true : DBG, "Client is connected but service is null");
            t = this.mService;
        }
        return t;
    }

    @VisibleForTesting
    public final void setServiceForTesting(T service) {
        setConnectState(service != null ? CONNECT_STATE_CONNECTED : CONNECT_STATE_DISCONNECTED, service);
    }

    @VisibleForTesting
    public final void setServiceBrokerForTesting(IGmsServiceBroker gmsServiceBroker) {
        synchronized (this.mServiceBrokerLock) {
            this.mServiceBroker = gmsServiceBroker;
        }
    }

    @VisibleForTesting
    public final IGmsServiceBroker getServiceBrokerForTesting() {
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.mServiceBrokerLock) {
            iGmsServiceBroker = this.mServiceBroker;
        }
        return iGmsServiceBroker;
    }

    @Deprecated
    public final void doCallbackDEPRECATED(CallbackProxy<?> callback) {
        synchronized (this.mCallbackProxyList) {
            this.mCallbackProxyList.add(callback);
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(CONNECT_STATE_CONNECTING, this.mDisconnectCount.get(), -1, callback));
    }

    @VisibleForTesting
    int getCallbackProxyListSizeForTest() {
        int size;
        synchronized (this.mCallbackProxyList) {
            size = this.mCallbackProxyList.size();
        }
        return size;
    }

    @WorkerThread
    public void getRemoteService(IAccountAccessor authedAccountAccessor, Set<Scope> scopes) {
        try {
            GetServiceRequest request = new GetServiceRequest(this.mGCoreServiceId).setCallingPackage(this.mContext.getPackageName()).setExtraArgs(getGetServiceRequestExtraArgs());
            if (scopes != null) {
                request.setScopes(scopes);
            }
            if (requiresSignIn()) {
                request.setClientRequestedAccount(getAccountOrDefault()).setAuthenticatedAccount(authedAccountAccessor);
            } else if (requiresAccount()) {
                request.setClientRequestedAccount(this.mAccount);
            }
            synchronized (this.mServiceBrokerLock) {
                if (this.mServiceBroker != null) {
                    this.mServiceBroker.getService(new GmsCallbacks(this, this.mDisconnectCount.get()), request);
                } else {
                    Log.w(TAG, "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w(TAG, "service died");
            triggerConnectionSuspended(CONNECT_STATE_DISCONNECTED);
        } catch (RemoteException e2) {
            Log.w(TAG, "Remote exception occurred", e2);
        }
    }

    public boolean requiresSignIn() {
        return DBG;
    }

    public boolean requiresAccount() {
        return DBG;
    }

    public boolean providesSignIn() {
        return DBG;
    }

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        synchronized (this.mLock) {
            int connectState = this.mConnectState;
            T service = this.mService;
        }
        writer.append(prefix).append("mConnectState=");
        switch (connectState) {
            case CONNECT_STATE_DISCONNECTED /*1*/:
                writer.print("DISCONNECTED");
                break;
            case CONNECT_STATE_CONNECTING /*2*/:
                writer.print("CONNECTING");
                break;
            case CONNECT_STATE_CONNECTED /*3*/:
                writer.print("CONNECTED");
                break;
            case CONNECT_STATE_DISCONNECTING /*4*/:
                writer.print("DISCONNECTING");
                break;
            default:
                writer.print("UNKNOWN");
                break;
        }
        writer.append(" mService=");
        if (service == null) {
            writer.println("null");
        } else {
            writer.append(getServiceDescriptor()).append("@").println(Integer.toHexString(System.identityHashCode(service.asBinder())));
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.lastConnectedTime > 0) {
            writer.append(prefix).append("lastConnectedTime=").println(this.lastConnectedTime + " " + dateFormat.format(new Date(this.lastConnectedTime)));
        }
        if (this.lastSuspendedTime > 0) {
            writer.append(prefix).append("lastSuspendedCause=");
            switch (this.lastSuspendedCause) {
                case CONNECT_STATE_DISCONNECTED /*1*/:
                    writer.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                case CONNECT_STATE_CONNECTING /*2*/:
                    writer.append("CAUSE_NETWORK_LOST");
                    break;
                default:
                    writer.append(String.valueOf(this.lastSuspendedCause));
                    break;
            }
            writer.append(" lastSuspendedTime=").println(this.lastSuspendedTime + " " + dateFormat.format(new Date(this.lastSuspendedTime)));
        }
        if (this.lastFailedTime > 0) {
            writer.append(prefix).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.lastFailedStatusCode));
            writer.append(" lastFailedTime=").println(this.lastFailedTime + " " + dateFormat.format(new Date(this.lastFailedTime)));
        }
    }

    @Nullable
    public IBinder getServiceBrokerBinder() {
        IBinder iBinder;
        synchronized (this.mServiceBrokerLock) {
            if (this.mServiceBroker == null) {
                iBinder = null;
            } else {
                iBinder = this.mServiceBroker.asBinder();
            }
        }
        return iBinder;
    }
}
