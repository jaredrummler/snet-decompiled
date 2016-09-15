package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultStore;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.InternalGoogleApiClient.InternalCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class GoogleApiClientImpl extends GoogleApiClient implements InternalCallbacks {
    private static final long RESUME_DELAY_MS = 5000;
    private static final long RESUME_TIMEOUT_MS = 120000;
    private static final String TAG = "GoogleApiClientImpl";
    private final GoogleApiAvailabilityLight mApiAvailability;
    private InternalGoogleApiClient mApiClient;
    final Map<Api<?>, Integer> mApiTypeMap;
    private final int mAutoManageId;
    private final ArrayList<ClientCallbacks> mClientCallbacks;
    final ClientSettings mClientSettings;
    final Map<ClientKey<?>, Client> mClients;
    private final Context mContext;
    private final GmsClientEventState mEventStateCallbacks;
    private final GmsClientEventManager mEvents;
    private final CallbackHandler mHandlerForCallbacks;
    private final Set<ListenerHolder<?>> mListeners;
    private final Lock mLock;
    private final Looper mLooper;
    @VisibleForTesting
    PackageUpdatedReceiver mPackageUpdatedReceiver;
    Set<TransformedResultImpl> mPendingTransforms;
    private final ResultConsumedCallback mResultListener;
    private ResultStore mResultStore;
    private long mResumeDelayMs;
    private long mResumeTimeoutMs;
    private volatile boolean mResuming;
    final AbstractClientBuilder<? extends SignInClient, SignInOptions> mSignInApiBuilder;
    private Integer mSignInMode;
    @VisibleForTesting
    final Set<Runner<?>> mUnconsumedRunners;
    Set<Scope> mValidatedScopes;
    @VisibleForTesting
    final Queue<ApiMethodImpl<?, ?>> mWorkQueue;

    interface Runner<A extends Client> {
        void cancel();

        void cancelUnlessPossibleTransform();

        void clearResultCallback();

        void forceFailureUnlessReady(Status status);

        ClientKey<A> getClientKey();

        Integer getResultId();

        boolean isReady();

        void run(A a) throws DeadObjectException;

        void setFailedResult(Status status);

        void setResultConsumedCallback(ResultConsumedCallback resultConsumedCallback);
    }

    interface ResultConsumedCallback {
        void onConsumed(Runner<?> runner);
    }

    /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientImpl.3 */
    class AnonymousClass3 implements ConnectionCallbacks {
        final /* synthetic */ AtomicReference val$apiClientRef;
        final /* synthetic */ StatusPendingResult val$pendingResult;

        AnonymousClass3(AtomicReference atomicReference, StatusPendingResult statusPendingResult) {
            this.val$apiClientRef = atomicReference;
            this.val$pendingResult = statusPendingResult;
        }

        public void onConnected(Bundle connectionHint) {
            GoogleApiClientImpl.this.clearDefaultAccountInternal((GoogleApiClient) this.val$apiClientRef.get(), this.val$pendingResult, true);
        }

        public void onConnectionSuspended(int cause) {
        }
    }

    /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientImpl.4 */
    class AnonymousClass4 implements OnConnectionFailedListener {
        final /* synthetic */ StatusPendingResult val$pendingResult;

        AnonymousClass4(StatusPendingResult statusPendingResult) {
            this.val$pendingResult = statusPendingResult;
        }

        public void onConnectionFailed(@NonNull ConnectionResult result) {
            this.val$pendingResult.setResult(new Status(8));
        }
    }

    /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientImpl.5 */
    class AnonymousClass5 implements ResultCallback<Status> {
        final /* synthetic */ GoogleApiClient val$apiClient;
        final /* synthetic */ boolean val$disconnectWhenComplete;
        final /* synthetic */ StatusPendingResult val$pendingResult;

        AnonymousClass5(StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
            this.val$pendingResult = statusPendingResult;
            this.val$disconnectWhenComplete = z;
            this.val$apiClient = googleApiClient;
        }

        public void onResult(@NonNull Status result) {
            Storage.getInstance(GoogleApiClientImpl.this.mContext).removeSavedDefaultGoogleSignInAccount();
            if (result.isSuccess() && GoogleApiClientImpl.this.isConnected()) {
                GoogleApiClientImpl.this.reconnect();
            }
            this.val$pendingResult.setResult(result);
            if (this.val$disconnectWhenComplete) {
                this.val$apiClient.disconnect();
            }
        }
    }

    /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientImpl.6 */
    class AnonymousClass6 implements Runnable {
        final /* synthetic */ FragmentActivity val$lifecycleActivity;

        AnonymousClass6(FragmentActivity fragmentActivity) {
            this.val$lifecycleActivity = fragmentActivity;
        }

        public void run() {
            if (!this.val$lifecycleActivity.isFinishing() && !this.val$lifecycleActivity.getSupportFragmentManager().isDestroyed()) {
                SupportLifecycleFragment.getInstance(this.val$lifecycleActivity).stopAutoManage(GoogleApiClientImpl.this.mAutoManageId);
            }
        }
    }

    final class CallbackHandler extends Handler {
        static final int RESUME = 2;
        static final int TIMEOUT_RESUMING = 1;

        CallbackHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIMEOUT_RESUMING /*1*/:
                    GoogleApiClientImpl.this.timeoutResuming();
                case RESUME /*2*/:
                    GoogleApiClientImpl.this.resume();
                default:
                    Log.w(GoogleApiClientImpl.TAG, "Unknown message id: " + msg.what);
            }
        }
    }

    private static class DetachedRunnerCallback implements DeathRecipient, ResultConsumedCallback {
        private final WeakReference<IBinder> mBinder;
        private final WeakReference<ResultStore> mResultStore;
        private final WeakReference<Runner<?>> mRunner;

        private DetachedRunnerCallback(Runner runner, ResultStore resultStore, IBinder binder) {
            this.mResultStore = new WeakReference(resultStore);
            this.mRunner = new WeakReference(runner);
            this.mBinder = new WeakReference(binder);
        }

        public void onConsumed(Runner<?> runner) {
            cleanUp();
        }

        public void binderDied() {
            cleanUp();
        }

        private void cleanUp() {
            Runner<?> runner = (Runner) this.mRunner.get();
            ResultStore resultStore = (ResultStore) this.mResultStore.get();
            if (!(resultStore == null || runner == null)) {
                resultStore.remove(runner.getResultId().intValue());
            }
            IBinder binder = (IBinder) this.mBinder.get();
            if (this.mBinder != null) {
                binder.unlinkToDeath(this, 0);
            }
        }
    }

    static class PackageUpdatedReceiver extends GooglePlayServicesUpdatedReceiver {
        private WeakReference<GoogleApiClientImpl> mClientRef;

        PackageUpdatedReceiver(GoogleApiClientImpl client) {
            this.mClientRef = new WeakReference(client);
        }

        public void onUpdated() {
            GoogleApiClientImpl client = (GoogleApiClientImpl) this.mClientRef.get();
            if (client != null) {
                client.resume();
            }
        }
    }

    private static void registerDetachedRunnerCleanup(Runner<?> runner, ResultStore resultStore, IBinder binder) {
        if (runner.isReady()) {
            runner.setResultConsumedCallback(new DetachedRunnerCallback(resultStore, binder, null));
        } else if (binder == null || !binder.isBinderAlive()) {
            runner.setResultConsumedCallback(null);
            runner.cancel();
            resultStore.remove(runner.getResultId().intValue());
        } else {
            DetachedRunnerCallback callback = new DetachedRunnerCallback(resultStore, binder, null);
            runner.setResultConsumedCallback(callback);
            try {
                binder.linkToDeath(callback, 0);
            } catch (RemoteException e) {
                runner.cancel();
                resultStore.remove(runner.getResultId().intValue());
            }
        }
    }

    public GoogleApiClientImpl(Context context, Lock lock, Looper looper, ClientSettings commonSettings, GoogleApiAvailabilityLight apiAvailability, AbstractClientBuilder<? extends SignInClient, SignInOptions> signInApiBuilder, Map<Api<?>, Integer> apiTypeMap, List<ConnectionCallbacks> connectionCallbacks, List<OnConnectionFailedListener> onConnectionFailedListeners, Map<ClientKey<?>, Client> clients, int autoManageId, int autoManageSignInMode, ArrayList<ClientCallbacks> clientCallbacksList) {
        this.mApiClient = null;
        this.mWorkQueue = new LinkedList();
        this.mResumeTimeoutMs = RESUME_TIMEOUT_MS;
        this.mResumeDelayMs = RESUME_DELAY_MS;
        this.mValidatedScopes = new HashSet();
        this.mListeners = Collections.newSetFromMap(new WeakHashMap());
        this.mUnconsumedRunners = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.75f, 2));
        this.mSignInMode = null;
        this.mPendingTransforms = null;
        this.mResultListener = new ResultConsumedCallback() {
            public void onConsumed(Runner<?> runner) {
                GoogleApiClientImpl.this.mUnconsumedRunners.remove(runner);
                if (runner.getResultId() != null && GoogleApiClientImpl.this.mResultStore != null) {
                    GoogleApiClientImpl.this.mResultStore.remove(runner.getResultId().intValue());
                }
            }
        };
        this.mEventStateCallbacks = new GmsClientEventState() {
            public boolean isConnected() {
                return GoogleApiClientImpl.this.isConnected();
            }

            public Bundle getConnectionHint() {
                return null;
            }
        };
        this.mContext = context;
        this.mLock = lock;
        this.mEvents = new GmsClientEventManager(looper, this.mEventStateCallbacks);
        this.mLooper = looper;
        this.mHandlerForCallbacks = new CallbackHandler(looper);
        this.mApiAvailability = apiAvailability;
        this.mAutoManageId = autoManageId;
        if (this.mAutoManageId >= 0) {
            this.mSignInMode = Integer.valueOf(autoManageSignInMode);
        }
        this.mApiTypeMap = apiTypeMap;
        this.mClients = clients;
        this.mClientCallbacks = clientCallbacksList;
        for (ConnectionCallbacks callback : connectionCallbacks) {
            this.mEvents.registerConnectionCallbacks(callback);
        }
        for (OnConnectionFailedListener callback2 : onConnectionFailedListeners) {
            this.mEvents.registerConnectionFailedListener(callback2);
        }
        this.mClientSettings = commonSettings;
        this.mSignInApiBuilder = signInApiBuilder;
    }

    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T methodImpl) {
        Preconditions.checkArgument(methodImpl.getClientKey() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        Preconditions.checkArgument(this.mClients.containsKey(methodImpl.getClientKey()), "GoogleApiClient is not configured to use the API required for this call.");
        this.mLock.lock();
        try {
            if (this.mApiClient == null) {
                this.mWorkQueue.add(methodImpl);
            } else {
                methodImpl = this.mApiClient.enqueue(methodImpl);
                this.mLock.unlock();
            }
            return methodImpl;
        } finally {
            this.mLock.unlock();
        }
    }

    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T methodImpl) {
        Preconditions.checkArgument(methodImpl.getClientKey() != null, "This task can not be executed (it's probably a Batch or malformed)");
        this.mLock.lock();
        try {
            if (this.mApiClient == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (isResuming()) {
                this.mWorkQueue.add(methodImpl);
                while (!this.mWorkQueue.isEmpty()) {
                    Runner runner = (Runner) this.mWorkQueue.remove();
                    addUnconsumedRunner(runner);
                    runner.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                }
            } else {
                methodImpl = this.mApiClient.execute(methodImpl);
                this.mLock.unlock();
            }
            return methodImpl;
        } finally {
            this.mLock.unlock();
        }
    }

    public <L> ListenerHolder<L> registerListener(@NonNull L listener) {
        Preconditions.checkNotNull(listener, "Listener must not be null");
        this.mLock.lock();
        try {
            ListenerHolder<L> holder = new ListenerHolder(this.mLooper, listener);
            this.mListeners.add(holder);
            return holder;
        } finally {
            this.mLock.unlock();
        }
    }

    @NonNull
    public <C extends Client> C getClient(@NonNull ClientKey<C> clientKey) {
        Client result = (Client) this.mClients.get(clientKey);
        Preconditions.checkNotNull(result, "Appropriate Api was not requested.");
        return result;
    }

    public boolean hasApi(@NonNull Api<?> api) {
        return this.mClients.containsKey(api.getClientKey());
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        Client client = (Client) this.mClients.get(api.getClientKey());
        return client != null && client.isConnected();
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.mLock.lock();
        try {
            if (!isConnected() && !isResuming()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.mClients.containsKey(api.getClientKey())) {
                ConnectionResult connectionResult = this.mApiClient.getConnectionResult(api);
                if (connectionResult != null) {
                    this.mLock.unlock();
                } else if (isResuming()) {
                    connectionResult = ConnectionResult.RESULT_SUCCESS;
                } else {
                    Log.i(TAG, dumpToString());
                    Log.wtf(TAG, api.getName() + " requested in getConnectionResult" + " is not connected but is not present in the failed " + " connections map", new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.mLock.unlock();
                }
                return connectionResult;
            } else {
                throw new IllegalArgumentException(api.getName() + " was never registered with GoogleApiClient");
            }
        } finally {
            this.mLock.unlock();
        }
    }

    public boolean hasAuthenticatedScope(Scope scope) {
        this.mLock.lock();
        try {
            boolean z = isConnected() && this.mValidatedScopes.contains(scope);
            this.mLock.unlock();
            return z;
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    public void connect() {
        boolean z = false;
        this.mLock.lock();
        try {
            if (this.mAutoManageId >= 0) {
                if (this.mSignInMode != null) {
                    z = true;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.mSignInMode == null) {
                this.mSignInMode = Integer.valueOf(selectSignInModeAutomatically(this.mClients.values(), false));
            } else if (this.mSignInMode.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.mSignInMode.intValue());
        } finally {
            this.mLock.unlock();
        }
    }

    public void connect(int signInMode) {
        boolean z = true;
        this.mLock.lock();
        if (!(signInMode == 3 || signInMode == 1 || signInMode == 2)) {
            z = false;
        }
        try {
            Preconditions.checkArgument(z, "Illegal sign-in mode: " + signInMode);
            checkModeAndBuildApiClient(signInMode);
            connectLocked();
        } finally {
            this.mLock.unlock();
        }
    }

    public ConnectionResult blockingConnect() {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.mLock.lock();
        try {
            if (this.mAutoManageId >= 0) {
                if (this.mSignInMode == null) {
                    z = false;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.mSignInMode == null) {
                this.mSignInMode = Integer.valueOf(selectSignInModeAutomatically(this.mClients.values(), false));
            } else if (this.mSignInMode.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            checkModeAndBuildApiClient(this.mSignInMode.intValue());
            this.mEvents.enableCallbacks();
            ConnectionResult blockingConnect = this.mApiClient.blockingConnect();
            return blockingConnect;
        } finally {
            this.mLock.unlock();
        }
    }

    public ConnectionResult blockingConnect(long timeout, @NonNull TimeUnit unit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        Preconditions.checkState(z, "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(unit, "TimeUnit must not be null");
        this.mLock.lock();
        try {
            if (this.mSignInMode == null) {
                this.mSignInMode = Integer.valueOf(selectSignInModeAutomatically(this.mClients.values(), false));
            } else if (this.mSignInMode.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            checkModeAndBuildApiClient(this.mSignInMode.intValue());
            this.mEvents.enableCallbacks();
            ConnectionResult blockingConnect = this.mApiClient.blockingConnect(timeout, unit);
            return blockingConnect;
        } finally {
            this.mLock.unlock();
        }
    }

    public void disconnect() {
        this.mLock.lock();
        try {
            boolean disconnectPending = (this.mApiClient == null || this.mApiClient.disconnect()) ? false : true;
            processUnconsumedRunnersLocked(disconnectPending);
            for (ListenerHolder<?> holder : this.mListeners) {
                holder.clear();
            }
            this.mListeners.clear();
            for (Runner<?> runner : this.mWorkQueue) {
                runner.setResultConsumedCallback(null);
                runner.cancel();
            }
            this.mWorkQueue.clear();
            if (this.mApiClient != null) {
                stopResumingLocked();
                this.mEvents.disableCallbacks();
                this.mLock.unlock();
            }
        } finally {
            this.mLock.unlock();
        }
    }

    @GuardedBy("mLock")
    void processUnconsumedRunnersLocked(boolean disconnectPending) {
        for (Runner<?> runner : this.mUnconsumedRunners) {
            if (runner.getResultId() != null) {
                runner.clearResultCallback();
                registerDetachedRunnerCleanup(runner, this.mResultStore, getClient(runner.getClientKey()).getServiceBrokerBinder());
                this.mUnconsumedRunners.remove(runner);
            } else if (disconnectPending) {
                runner.cancelUnlessPossibleTransform();
            } else {
                runner.cancel();
                this.mUnconsumedRunners.remove(runner);
            }
        }
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void setResultStore(ResultStore resultStore) {
        this.mResultStore = resultStore;
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        boolean z;
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        if (this.mSignInMode.intValue() != 2) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkState(z, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        StatusPendingResult pendingResult = new StatusPendingResult((GoogleApiClient) this);
        if (this.mClients.containsKey(Common.CLIENT_KEY)) {
            clearDefaultAccountInternal(this, pendingResult, false);
        } else {
            AtomicReference<GoogleApiClient> apiClientRef = new AtomicReference();
            ConnectionCallbacks connectionCallbacks = new AnonymousClass3(apiClientRef, pendingResult);
            GoogleApiClient apiClient = new Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(new AnonymousClass4(pendingResult)).setHandler(this.mHandlerForCallbacks).build();
            apiClientRef.set(apiClient);
            apiClient.connect();
        }
        return pendingResult;
    }

    private void clearDefaultAccountInternal(GoogleApiClient apiClient, StatusPendingResult pendingResult, boolean disconnectWhenComplete) {
        Common.CommonApi.clearDefaultAccount(apiClient).setResultCallback(new AnonymousClass5(pendingResult, disconnectWhenComplete, apiClient));
    }

    public void stopAutoManage(@NonNull FragmentActivity lifecycleActivity) {
        if (this.mAutoManageId >= 0) {
            SupportLifecycleFragment lifecycleFragment = SupportLifecycleFragment.getInstanceOrNull(lifecycleActivity);
            if (lifecycleFragment == null) {
                new Handler(this.mContext.getMainLooper()).post(new AnonymousClass6(lifecycleActivity));
                return;
            } else {
                lifecycleFragment.stopAutoManage(this.mAutoManageId);
                return;
            }
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public boolean isConnected() {
        return this.mApiClient != null && this.mApiClient.isConnected();
    }

    public boolean isConnecting() {
        return this.mApiClient != null && this.mApiClient.isConnecting();
    }

    @VisibleForTesting
    boolean isResuming() {
        return this.mResuming;
    }

    @VisibleForTesting
    void setResumeTimeoutMsForTest(long resumeTimeoutMs) {
        this.mResumeTimeoutMs = resumeTimeoutMs;
    }

    @VisibleForTesting
    void setResumeDelayMsForTest(long resumeDelayMs) {
        this.mResumeDelayMs = resumeDelayMs;
    }

    @VisibleForTesting
    InternalGoogleApiClient getInternalClientForTest() {
        return this.mApiClient;
    }

    private void checkModeAndBuildApiClient(int signInMode) {
        if (this.mSignInMode == null) {
            this.mSignInMode = Integer.valueOf(signInMode);
        } else if (this.mSignInMode.intValue() != signInMode) {
            throw new IllegalStateException("Cannot use sign-in mode: " + getSignInModeName(signInMode) + ". Mode was already set to " + getSignInModeName(this.mSignInMode.intValue()));
        }
        if (this.mApiClient == null) {
            boolean containsAuthenticatedApi = false;
            boolean containsSignInApi = false;
            for (Client client : this.mClients.values()) {
                if (client.requiresSignIn()) {
                    containsAuthenticatedApi = true;
                }
                if (client.providesSignIn()) {
                    containsSignInApi = true;
                }
            }
            switch (this.mSignInMode.intValue()) {
                case Type.TEMPORARY /*1*/:
                    if (!containsAuthenticatedApi) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (containsSignInApi) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case Type.INDEFINITELY /*2*/:
                    if (containsAuthenticatedApi) {
                        this.mApiClient = new CompositeGoogleApiClient(this.mContext, this, this.mLock, this.mLooper, this.mApiAvailability, this.mClients, this.mClientSettings, this.mApiTypeMap, this.mSignInApiBuilder, this.mClientCallbacks);
                        return;
                    }
                    break;
            }
            this.mApiClient = new GoogleApiClientStateHolder(this.mContext, this, this.mLock, this.mLooper, this.mApiAvailability, this.mClients, this.mClientSettings, this.mApiTypeMap, this.mSignInApiBuilder, this.mClientCallbacks, this);
        }
    }

    @GuardedBy("mLock")
    private void connectLocked() {
        this.mEvents.enableCallbacks();
        this.mApiClient.connect();
    }

    private void resume() {
        this.mLock.lock();
        try {
            if (isResuming()) {
                connectLocked();
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    private void timeoutResuming() {
        this.mLock.lock();
        try {
            if (stopResumingLocked()) {
                connectLocked();
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    void startResuming() {
        if (!isResuming()) {
            this.mResuming = true;
            if (this.mPackageUpdatedReceiver == null) {
                this.mPackageUpdatedReceiver = (PackageUpdatedReceiver) GooglePlayServicesUpdatedReceiver.register(this.mContext.getApplicationContext(), new PackageUpdatedReceiver(this), this.mApiAvailability);
            }
            this.mHandlerForCallbacks.sendMessageDelayed(this.mHandlerForCallbacks.obtainMessage(1), this.mResumeTimeoutMs);
            this.mHandlerForCallbacks.sendMessageDelayed(this.mHandlerForCallbacks.obtainMessage(2), this.mResumeDelayMs);
        }
    }

    @GuardedBy("mLock")
    boolean stopResumingLocked() {
        if (!isResuming()) {
            return false;
        }
        this.mResuming = false;
        this.mHandlerForCallbacks.removeMessages(2);
        this.mHandlerForCallbacks.removeMessages(1);
        if (this.mPackageUpdatedReceiver != null) {
            this.mPackageUpdatedReceiver.unregister();
            this.mPackageUpdatedReceiver = null;
        }
        return true;
    }

    <A extends Client> void addUnconsumedRunner(Runner<A> runner) {
        this.mUnconsumedRunners.add(runner);
        runner.setResultConsumedCallback(this.mResultListener);
    }

    public void registerConnectionCallbacks(@NonNull ConnectionCallbacks listener) {
        this.mEvents.registerConnectionCallbacks(listener);
    }

    public boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks listener) {
        return this.mEvents.isConnectionCallbacksRegistered(listener);
    }

    public void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks listener) {
        this.mEvents.unregisterConnectionCallbacks(listener);
    }

    public void registerConnectionFailedListener(@NonNull OnConnectionFailedListener listener) {
        this.mEvents.registerConnectionFailedListener(listener);
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener listener) {
        return this.mEvents.isConnectionFailedListenerRegistered(listener);
    }

    public void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener listener) {
        this.mEvents.unregisterConnectionFailedListener(listener);
    }

    @GuardedBy("mLock")
    public void handleOnConnectionSuccess(Bundle connectionHint) {
        while (!this.mWorkQueue.isEmpty()) {
            execute((ApiMethodImpl) this.mWorkQueue.remove());
        }
        this.mEvents.onConnectionSuccess(connectionHint);
    }

    @GuardedBy("mLock")
    public void handleOnConnectionFailed(ConnectionResult result) {
        if (!this.mApiAvailability.isPlayServicesPossiblyUpdating(this.mContext, result.getErrorCode())) {
            stopResumingLocked();
        }
        if (!isResuming()) {
            this.mEvents.onConnectionFailure(result);
            this.mEvents.disableCallbacks();
        }
    }

    @GuardedBy("mLock")
    public void handleOnConnectionSuspended(int cause, boolean disconnectPending) {
        if (cause == 1 && !disconnectPending) {
            startResuming();
        }
        for (Runner<?> runner : this.mUnconsumedRunners) {
            if (disconnectPending) {
                runner.clearResultCallback();
            }
            runner.forceFailureUnlessReady(new Status(8, "The connection to Google Play services was lost"));
        }
        this.mUnconsumedRunners.clear();
        this.mEvents.onUnintentionalDisconnection(cause);
        this.mEvents.disableCallbacks();
        if (cause == 2) {
            connectLocked();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public boolean maybeSignIn(SignInConnectionListener listener) {
        return this.mApiClient != null && this.mApiClient.maybeSignIn(listener);
    }

    public void maybeSignOut() {
        if (this.mApiClient != null) {
            this.mApiClient.maybeSignOut();
        }
    }

    public void registerPendingTransform(TransformedResultImpl transformedResult) {
        this.mLock.lock();
        try {
            if (this.mPendingTransforms == null) {
                this.mPendingTransforms = new HashSet();
            }
            this.mPendingTransforms.add(transformedResult);
        } finally {
            this.mLock.unlock();
        }
    }

    public void unregisterPendingTransform(TransformedResultImpl transformedResult) {
        this.mLock.lock();
        try {
            if (this.mPendingTransforms == null) {
                Log.wtf(TAG, "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.mPendingTransforms.remove(transformedResult)) {
                Log.wtf(TAG, "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!hasPendingTransforms()) {
                this.mApiClient.maybeFinishDisconnectingLocked();
            }
            this.mLock.unlock();
        } catch (Throwable th) {
            this.mLock.unlock();
        }
    }

    boolean hasPendingTransforms() {
        boolean z = false;
        this.mLock.lock();
        try {
            if (this.mPendingTransforms != null) {
                if (!this.mPendingTransforms.isEmpty()) {
                    z = true;
                }
                this.mLock.unlock();
            }
            return z;
        } finally {
            this.mLock.unlock();
        }
    }

    String dumpToString() {
        StringWriter writer = new StringWriter();
        dump(BuildConfig.VERSION_NAME, null, new PrintWriter(writer), null);
        return writer.toString();
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.append(prefix).append("mContext=").println(this.mContext);
        writer.append(prefix).append("mResuming=").print(this.mResuming);
        writer.append(" mWorkQueue.size()=").print(this.mWorkQueue.size());
        writer.append(" mUnconsumedRunners.size()=").println(this.mUnconsumedRunners.size());
        if (this.mApiClient != null) {
            this.mApiClient.dump(prefix, fd, writer, args);
        }
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public static int selectSignInModeAutomatically(Iterable<Client> clients, boolean canSelectSignInApi) {
        boolean containsAuthenticatedApi = false;
        boolean containsSignInApi = false;
        for (Client client : clients) {
            if (client.requiresSignIn()) {
                containsAuthenticatedApi = true;
            }
            if (client.providesSignIn()) {
                containsSignInApi = true;
            }
        }
        if (!containsAuthenticatedApi) {
            return 3;
        }
        if (containsSignInApi && canSelectSignInApi) {
            return 2;
        }
        return 1;
    }

    static String getSignInModeName(int mode) {
        switch (mode) {
            case Type.TEMPORARY /*1*/:
                return "SIGN_IN_MODE_REQUIRED";
            case Type.INDEFINITELY /*2*/:
                return "SIGN_IN_MODE_OPTIONAL";
            case Type.CUSTOM /*3*/:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }
}
