package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public class GoogleApiClientConnecting implements GoogleApiClientState {
    public static final int STEP_GETTING_REMOTE_SERVICE = 1;
    public static final int STEP_SERVICE_BINDINGS_AND_SIGN_IN = 0;
    private static final String TAG = "GoogleApiClientConnecting";
    private boolean mAccountResolved;
    private final GoogleApiAvailabilityLight mApiAvailability;
    private final Map<Api<?>, Integer> mApiTypeMap;
    private ArrayList<Future<?>> mAsyncTasks;
    private final ClientSettings mClientSettings;
    private final Bundle mConnectionHints;
    private int mConnectionStep;
    private final Context mContext;
    private final GoogleApiClientStateHolder mHolder;
    private final Lock mLock;
    private final Set<ClientKey> mOptionalApisWithSignIn;
    private boolean mPerformSignIn;
    private int mRemainingConnections;
    private IAccountAccessor mResolvedAccountAccessor;
    private boolean mSaveDefaultAccount;
    private boolean mShowCrossClientAuthToast;
    private final AbstractClientBuilder<? extends SignInClient, SignInOptions> mSignInApiBuilder;
    private int mSignInApiType;
    private SignInClient mSignInClient;
    private ConnectionResult mWorstFailure;
    private int mWorstFailurePriority;

    private static class ConnectionProgressReportCallbacksImpl implements ConnectionProgressReportCallbacks {
        private final Api<?> mApi;
        private final int mApiType;
        private final WeakReference<GoogleApiClientConnecting> mStateRef;

        public ConnectionProgressReportCallbacksImpl(GoogleApiClientConnecting state, Api<?> api, int apiType) {
            this.mStateRef = new WeakReference(state);
            this.mApi = api;
            this.mApiType = apiType;
        }

        public void onReportServiceBinding(@NonNull ConnectionResult result) {
            boolean z = false;
            GoogleApiClientConnecting state = (GoogleApiClientConnecting) this.mStateRef.get();
            if (state != null) {
                if (Looper.myLooper() == state.mHolder.mApiClient.getLooper()) {
                    z = true;
                }
                Preconditions.checkState(z, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
                state.mLock.lock();
                try {
                    if (state.checkStepLocked(GoogleApiClientConnecting.STEP_SERVICE_BINDINGS_AND_SIGN_IN)) {
                        if (!result.isSuccess()) {
                            state.recordFailedConnectionLocked(result, this.mApi, this.mApiType);
                        }
                        if (state.onClientStepCallback()) {
                            state.tryEndServiceBindingAndSignInLocked();
                        }
                        state.mLock.unlock();
                    }
                } finally {
                    state.mLock.unlock();
                }
            }
        }
    }

    private abstract class WorkerThreadTask implements Runnable {
        @WorkerThread
        protected abstract void runLocked();

        private WorkerThreadTask() {
        }

        @WorkerThread
        public void run() {
            GoogleApiClientConnecting.this.mLock.lock();
            try {
                if (!Thread.interrupted()) {
                    runLocked();
                    GoogleApiClientConnecting.this.mLock.unlock();
                }
            } catch (RuntimeException e) {
                GoogleApiClientConnecting.this.mHolder.postToHandler(e);
            } finally {
                GoogleApiClientConnecting.this.mLock.unlock();
            }
        }
    }

    private class ConnectionTask extends WorkerThreadTask {
        private final Map<Client, ConnectionProgressReportCallbacks> mProgressCallbacksMap;

        /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientConnecting.ConnectionTask.1 */
        class AnonymousClass1 extends HandlerTask {
            final /* synthetic */ ConnectionResult val$failure;

            AnonymousClass1(GoogleApiClientState x0, ConnectionResult connectionResult) {
                this.val$failure = connectionResult;
                super(x0);
            }

            @GuardedBy("mLock")
            public void runLocked() {
                GoogleApiClientConnecting.this.handleConnectionFailureLocked(this.val$failure);
            }
        }

        public ConnectionTask(Map<Client, ConnectionProgressReportCallbacks> progressCallbacksMap) {
            super(null);
            this.mProgressCallbacksMap = progressCallbacksMap;
        }

        @WorkerThread
        public void runLocked() {
            int statusCode = GoogleApiClientConnecting.this.mApiAvailability.isGooglePlayServicesAvailable(GoogleApiClientConnecting.this.mContext);
            if (statusCode != 0) {
                GoogleApiClientConnecting.this.mHolder.postToHandler(new AnonymousClass1(GoogleApiClientConnecting.this, new ConnectionResult(statusCode, null)));
                return;
            }
            if (GoogleApiClientConnecting.this.mPerformSignIn) {
                GoogleApiClientConnecting.this.mSignInClient.connect();
            }
            for (Client client : this.mProgressCallbacksMap.keySet()) {
                client.connect((ConnectionProgressReportCallbacks) this.mProgressCallbacksMap.get(client));
            }
        }
    }

    private class GetRemoteServiceTask extends WorkerThreadTask {
        private final ArrayList<Client> mClients;

        public GetRemoteServiceTask(ArrayList<Client> clients) {
            super(null);
            this.mClients = clients;
        }

        @WorkerThread
        public void runLocked() {
            GoogleApiClientConnecting.this.mHolder.mApiClient.mValidatedScopes = GoogleApiClientConnecting.this.determineValidatedScopes();
            Iterator i$ = this.mClients.iterator();
            while (i$.hasNext()) {
                ((Client) i$.next()).getRemoteService(GoogleApiClientConnecting.this.mResolvedAccountAccessor, GoogleApiClientConnecting.this.mHolder.mApiClient.mValidatedScopes);
            }
        }
    }

    private static class SignInCallbackHandler extends BaseSignInCallbacks {
        private final WeakReference<GoogleApiClientConnecting> mStateRef;

        /* renamed from: com.google.android.gms.common.api.internal.GoogleApiClientConnecting.SignInCallbackHandler.1 */
        class AnonymousClass1 extends HandlerTask {
            final /* synthetic */ SignInResponse val$response;
            final /* synthetic */ GoogleApiClientConnecting val$state;

            AnonymousClass1(GoogleApiClientState x0, GoogleApiClientConnecting googleApiClientConnecting, SignInResponse signInResponse) {
                this.val$state = googleApiClientConnecting;
                this.val$response = signInResponse;
                super(x0);
            }

            public void runLocked() {
                this.val$state.endSignIn(this.val$response);
            }
        }

        SignInCallbackHandler(GoogleApiClientConnecting state) {
            this.mStateRef = new WeakReference(state);
        }

        @BinderThread
        public void onSignInComplete(SignInResponse response) {
            GoogleApiClientConnecting state = (GoogleApiClientConnecting) this.mStateRef.get();
            if (state != null) {
                state.mHolder.postToHandler(new AnonymousClass1(state, state, response));
            }
        }
    }

    private class SignInConnectionCallbacks implements ConnectionCallbacks, OnConnectionFailedListener {
        private SignInConnectionCallbacks() {
        }

        public void onConnected(Bundle connectionHint) {
            GoogleApiClientConnecting.this.mSignInClient.signIn(new SignInCallbackHandler(GoogleApiClientConnecting.this));
        }

        public void onConnectionSuspended(int cause) {
        }

        public void onConnectionFailed(@NonNull ConnectionResult result) {
            GoogleApiClientConnecting.this.mLock.lock();
            try {
                if (GoogleApiClientConnecting.this.shouldSkipSignInLocked(result)) {
                    GoogleApiClientConnecting.this.cancelSignInLocked();
                    GoogleApiClientConnecting.this.tryEndServiceBindingAndSignInLocked();
                } else {
                    GoogleApiClientConnecting.this.handleConnectionFailureLocked(result);
                }
                GoogleApiClientConnecting.this.mLock.unlock();
            } catch (Throwable th) {
                GoogleApiClientConnecting.this.mLock.unlock();
            }
        }
    }

    public GoogleApiClientConnecting(GoogleApiClientStateHolder holder, ClientSettings clientSettings, Map<Api<?>, Integer> apiTypeMap, GoogleApiAvailabilityLight apiAvailability, AbstractClientBuilder<? extends SignInClient, SignInOptions> signInApiBuilder, Lock lock, Context context) {
        this.mConnectionStep = STEP_SERVICE_BINDINGS_AND_SIGN_IN;
        this.mConnectionHints = new Bundle();
        this.mOptionalApisWithSignIn = new HashSet();
        this.mAsyncTasks = new ArrayList();
        this.mHolder = holder;
        this.mClientSettings = clientSettings;
        this.mApiTypeMap = apiTypeMap;
        this.mApiAvailability = apiAvailability;
        this.mSignInApiBuilder = signInApiBuilder;
        this.mLock = lock;
        this.mContext = context;
    }

    public String getName() {
        return "CONNECTING";
    }

    public void begin() {
        this.mHolder.mFailedConnections.clear();
        this.mPerformSignIn = false;
        this.mWorstFailure = null;
        this.mConnectionStep = STEP_SERVICE_BINDINGS_AND_SIGN_IN;
        this.mSignInApiType = 2;
        this.mAccountResolved = false;
        this.mSaveDefaultAccount = false;
        boolean hasGamesApi = false;
        Map<Client, ConnectionProgressReportCallbacks> progressCallbacksMap = new HashMap();
        for (Api<?> api : this.mApiTypeMap.keySet()) {
            Client client = (Client) this.mHolder.mClients.get(api.getClientKey());
            int apiType = ((Integer) this.mApiTypeMap.get(api)).intValue();
            hasGamesApi |= api.getClientBuilder().getPriority() == STEP_GETTING_REMOTE_SERVICE ? STEP_GETTING_REMOTE_SERVICE : STEP_SERVICE_BINDINGS_AND_SIGN_IN;
            if (client.requiresSignIn()) {
                this.mPerformSignIn = true;
                if (apiType < this.mSignInApiType) {
                    this.mSignInApiType = apiType;
                }
                if (apiType != 0) {
                    this.mOptionalApisWithSignIn.add(api.getClientKey());
                }
            }
            progressCallbacksMap.put(client, new ConnectionProgressReportCallbacksImpl(this, api, apiType));
        }
        if (hasGamesApi) {
            this.mPerformSignIn = false;
        }
        if (this.mPerformSignIn) {
            this.mClientSettings.setClientSessionId(Integer.valueOf(this.mHolder.mApiClient.getSessionId()));
            SignInConnectionCallbacks callbacks = new SignInConnectionCallbacks();
            this.mSignInClient = (SignInClient) this.mSignInApiBuilder.buildClient(this.mContext, this.mHolder.mApiClient.getLooper(), this.mClientSettings, this.mClientSettings.getSignInOptions(), callbacks, callbacks);
        }
        this.mRemainingConnections = this.mHolder.mClients.size();
        this.mAsyncTasks.add(GoogleApiExecutor.getInstance().submit(new ConnectionTask(progressCallbacksMap)));
    }

    @GuardedBy("mLock")
    private boolean onClientStepCallback() {
        this.mRemainingConnections--;
        if (this.mRemainingConnections > 0) {
            return false;
        }
        if (this.mRemainingConnections < 0) {
            Log.i(TAG, this.mHolder.mApiClient.dumpToString());
            Log.wtf(TAG, "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            handleConnectionFailureLocked(new ConnectionResult(8, null));
            return false;
        } else if (this.mWorstFailure == null) {
            return true;
        } else {
            this.mHolder.mLastConnectionFailurePriority = this.mWorstFailurePriority;
            handleConnectionFailureLocked(this.mWorstFailure);
            return false;
        }
    }

    @GuardedBy("mLock")
    private void endSignIn(SignInResponse signInResponse) {
        if (checkStepLocked(STEP_SERVICE_BINDINGS_AND_SIGN_IN)) {
            ConnectionResult signInResult = signInResponse.getConnectionResult();
            if (signInResult.isSuccess()) {
                ResolveAccountResponse resolveAccountResponse = signInResponse.getResolveAccountResponse();
                ConnectionResult resolveAccountResult = resolveAccountResponse.getConnectionResult();
                if (resolveAccountResult.isSuccess()) {
                    this.mAccountResolved = true;
                    this.mResolvedAccountAccessor = resolveAccountResponse.getAccountAccessor();
                    this.mSaveDefaultAccount = resolveAccountResponse.getSaveDefaultAccount();
                    this.mShowCrossClientAuthToast = resolveAccountResponse.isFromCrossClientAuth();
                    tryEndServiceBindingAndSignInLocked();
                    return;
                }
                Log.wtf(TAG, "Sign-in succeeded with resolve account failure: " + resolveAccountResult, new Exception());
                handleConnectionFailureLocked(resolveAccountResult);
            } else if (shouldSkipSignInLocked(signInResult)) {
                cancelSignInLocked();
                tryEndServiceBindingAndSignInLocked();
            } else {
                handleConnectionFailureLocked(signInResult);
            }
        }
    }

    @GuardedBy("mLock")
    private void tryEndServiceBindingAndSignInLocked() {
        if (this.mRemainingConnections == 0) {
            if (!this.mPerformSignIn || this.mAccountResolved) {
                startGetRemoteServiceLocked();
            }
        }
    }

    @GuardedBy("mLock")
    private void startGetRemoteServiceLocked() {
        ArrayList<Client> clients = new ArrayList();
        this.mConnectionStep = STEP_GETTING_REMOTE_SERVICE;
        this.mRemainingConnections = this.mHolder.mClients.size();
        for (ClientKey clientKey : this.mHolder.mClients.keySet()) {
            if (!this.mHolder.mFailedConnections.containsKey(clientKey)) {
                clients.add(this.mHolder.mClients.get(clientKey));
            } else if (onClientStepCallback()) {
                endGetRemoteServiceLocked();
            }
        }
        if (!clients.isEmpty()) {
            this.mAsyncTasks.add(GoogleApiExecutor.getInstance().submit(new GetRemoteServiceTask(clients)));
        }
    }

    @GuardedBy("mLock")
    public void onConnected(Bundle connectionHint) {
        if (checkStepLocked(STEP_GETTING_REMOTE_SERVICE)) {
            if (connectionHint != null) {
                this.mConnectionHints.putAll(connectionHint);
            }
            if (onClientStepCallback()) {
                endGetRemoteServiceLocked();
            }
        }
    }

    @GuardedBy("mLock")
    public void onConnectionFailed(ConnectionResult result, Api<?> api, int apiType) {
        if (checkStepLocked(STEP_GETTING_REMOTE_SERVICE)) {
            recordFailedConnectionLocked(result, api, apiType);
            if (onClientStepCallback()) {
                endGetRemoteServiceLocked();
            }
        }
    }

    @GuardedBy("mLock")
    private void endGetRemoteServiceLocked() {
        this.mHolder.changeToConnected();
        GoogleApiExecutor.getInstance().execute(new Runnable() {
            public void run() {
                GoogleApiClientConnecting.this.mApiAvailability.cancelAvailabilityErrorNotifications(GoogleApiClientConnecting.this.mContext);
            }
        });
        if (this.mSignInClient != null) {
            if (this.mSaveDefaultAccount) {
                this.mSignInClient.saveDefaultAccount(this.mResolvedAccountAccessor, this.mShowCrossClientAuthToast);
            }
            disconnectSignInClient(false);
        }
        for (ClientKey<?> clientKey : this.mHolder.mFailedConnections.keySet()) {
            ((Client) this.mHolder.mClients.get(clientKey)).disconnect();
        }
        this.mHolder.mInternalCallbacks.handleOnConnectionSuccess(this.mConnectionHints.isEmpty() ? null : this.mConnectionHints);
    }

    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(T methodImpl) {
        this.mHolder.mApiClient.mWorkQueue.add(methodImpl);
        return methodImpl;
    }

    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public void connect() {
    }

    public boolean disconnect() {
        cancelAsyncTasks();
        disconnectSignInClient(true);
        this.mHolder.changeToDisconnected(null);
        return true;
    }

    @GuardedBy("mLock")
    public void onConnectionSuspended(int cause) {
        handleConnectionFailureLocked(new ConnectionResult(8, null));
    }

    @GuardedBy("mLock")
    private void recordFailedConnectionLocked(ConnectionResult result, Api<?> api, int apiType) {
        if (apiType != 2) {
            int apiPriority = api.getClientBuilder().getPriority();
            if (shouldUpdateWorstFailureLocked(apiPriority, apiType, result)) {
                this.mWorstFailure = result;
                this.mWorstFailurePriority = apiPriority;
            }
        }
        this.mHolder.mFailedConnections.put(api.getClientKey(), result);
    }

    @GuardedBy("mLock")
    private boolean shouldUpdateWorstFailureLocked(int failurePriority, int apiType, ConnectionResult failure) {
        if (apiType == STEP_GETTING_REMOTE_SERVICE && !isRecoverable(failure)) {
            return false;
        }
        if (this.mWorstFailure == null || failurePriority < this.mWorstFailurePriority) {
            return true;
        }
        return false;
    }

    private boolean isRecoverable(ConnectionResult failure) {
        if (!failure.hasResolution() && this.mApiAvailability.getErrorResolutionIntent(failure.getErrorCode()) == null) {
            return false;
        }
        return true;
    }

    @GuardedBy("mLock")
    private void cancelSignInLocked() {
        this.mPerformSignIn = false;
        this.mHolder.mApiClient.mValidatedScopes = Collections.emptySet();
        for (ClientKey<?> clientKey : this.mOptionalApisWithSignIn) {
            if (!this.mHolder.mFailedConnections.containsKey(clientKey)) {
                this.mHolder.mFailedConnections.put(clientKey, new ConnectionResult(17, null));
            }
        }
    }

    @GuardedBy("mLock")
    private boolean shouldSkipSignInLocked(ConnectionResult failureResult) {
        if (this.mSignInApiType != 2) {
            return this.mSignInApiType == STEP_GETTING_REMOTE_SERVICE && !failureResult.hasResolution();
        } else {
            return true;
        }
    }

    @GuardedBy("mLock")
    private void handleConnectionFailureLocked(ConnectionResult failureResult) {
        cancelAsyncTasks();
        disconnectSignInClient(!failureResult.hasResolution());
        this.mHolder.changeToDisconnected(failureResult);
        this.mHolder.mInternalCallbacks.handleOnConnectionFailed(failureResult);
    }

    private void disconnectSignInClient(boolean clearAccountFromSessionStore) {
        if (this.mSignInClient != null) {
            if (this.mSignInClient.isConnected() && clearAccountFromSessionStore) {
                this.mSignInClient.clearAccountFromSessionStore();
            }
            this.mSignInClient.disconnect();
            this.mResolvedAccountAccessor = null;
        }
    }

    private void cancelAsyncTasks() {
        Iterator i$ = this.mAsyncTasks.iterator();
        while (i$.hasNext()) {
            ((Future) i$.next()).cancel(true);
        }
        this.mAsyncTasks.clear();
    }

    private Set<Scope> determineValidatedScopes() {
        if (this.mClientSettings == null) {
            return Collections.emptySet();
        }
        Set<Scope> validatedScopes = new HashSet(this.mClientSettings.getRequiredScopes());
        Map<Api<?>, OptionalApiSettings> optionalApiSettingsMap = this.mClientSettings.getOptionalApiSettings();
        for (Api<?> api : optionalApiSettingsMap.keySet()) {
            if (!this.mHolder.mFailedConnections.containsKey(api.getClientKey())) {
                validatedScopes.addAll(((OptionalApiSettings) optionalApiSettingsMap.get(api)).mScopes);
            }
        }
        return validatedScopes;
    }

    @GuardedBy("mLock")
    private boolean checkStepLocked(int step) {
        if (this.mConnectionStep == step) {
            return true;
        }
        Log.i(TAG, this.mHolder.mApiClient.dumpToString());
        Log.wtf(TAG, "GoogleApiClient connecting is in step " + printStep(this.mConnectionStep) + " but received callback for step " + printStep(step), new Exception());
        handleConnectionFailureLocked(new ConnectionResult(8, null));
        return false;
    }

    private String printStep(int step) {
        switch (step) {
            case STEP_SERVICE_BINDINGS_AND_SIGN_IN /*0*/:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case STEP_GETTING_REMOTE_SERVICE /*1*/:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    @VisibleForTesting
    SignInClient getSignInClientForTesting() {
        return this.mSignInClient;
    }
}
