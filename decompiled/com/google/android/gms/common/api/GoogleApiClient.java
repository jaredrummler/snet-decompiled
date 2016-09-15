package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api.SimpleClient;
import com.google.android.gms.common.api.Api.SimpleClientBuilder;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.ClientCallbacks;
import com.google.android.gms.common.api.internal.GoogleApiClientImpl;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.SupportLifecycleFragment;
import com.google.android.gms.common.api.internal.TransformedResultImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gsf.GoogleLoginServiceConstants;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;

public abstract class GoogleApiClient {
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int RESOLUTION_MODE_FAIL_WITH_INTENT = 1;
    public static final int RESOLUTION_MODE_IGNORE_INTENT = 2;
    public static final int SIGN_IN_MODE_NONE = 3;
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    @GuardedBy("sAllClients")
    private static final Set<GoogleApiClient> sAllClients;

    public static final class Builder {
        private static final int DEFAULT_CLIENT_ID = 0;
        private Account mAccount;
        private GoogleApiAvailabilityLight mApiAvailability;
        private final Map<Api<?>, ApiOptions> mApis;
        private int mAutoManageId;
        private final ArrayList<ConnectionCallbacks> mConnectedCallbacks;
        private OnConnectionFailedListener mConnectionFailedWithoutFixListener;
        private final Context mContext;
        private int mGravityForPopups;
        private final Set<Scope> mImpliedScopes;
        private FragmentActivity mLifecycleActivity;
        private Looper mLooper;
        private final ArrayList<OnConnectionFailedListener> mOnConnectionFailedListeners;
        private final Map<Api<?>, OptionalApiSettings> mOptionalApis;
        private String mRealClientClassName;
        private String mRealClientPackageName;
        private final Set<Scope> mRequiredScopes;
        private AbstractClientBuilder<? extends SignInClient, SignInOptions> mSignInApiBuilder;
        private View mViewForPopups;

        /* renamed from: com.google.android.gms.common.api.GoogleApiClient.Builder.1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ GoogleApiClient val$apiClient;

            AnonymousClass1(GoogleApiClient googleApiClient) {
                this.val$apiClient = googleApiClient;
            }

            public void run() {
                if (!Builder.this.mLifecycleActivity.isFinishing() && !Builder.this.mLifecycleActivity.getSupportFragmentManager().isDestroyed()) {
                    Builder.this.startAutoManage(SupportLifecycleFragment.getInstance(Builder.this.mLifecycleActivity), this.val$apiClient);
                }
            }
        }

        public Builder(@NonNull Context context) {
            this.mRequiredScopes = new HashSet();
            this.mImpliedScopes = new HashSet();
            this.mOptionalApis = new ArrayMap();
            this.mApis = new ArrayMap();
            this.mAutoManageId = -1;
            this.mApiAvailability = GoogleApiAvailabilityLight.getInstance();
            this.mSignInApiBuilder = SignIn.CLIENT_BUILDER;
            this.mConnectedCallbacks = new ArrayList();
            this.mOnConnectionFailedListeners = new ArrayList();
            this.mContext = context;
            this.mLooper = context.getMainLooper();
            this.mRealClientPackageName = context.getPackageName();
            this.mRealClientClassName = context.getClass().getName();
        }

        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectedListener, @NonNull OnConnectionFailedListener connectionFailedListener) {
            this(context);
            Preconditions.checkNotNull(connectedListener, "Must provide a connected listener");
            this.mConnectedCallbacks.add(connectedListener);
            Preconditions.checkNotNull(connectionFailedListener, "Must provide a connection failed listener");
            this.mOnConnectionFailedListeners.add(connectionFailedListener);
        }

        public Builder setHandler(@NonNull Handler handler) {
            Preconditions.checkNotNull(handler, "Handler must not be null");
            this.mLooper = handler.getLooper();
            return this;
        }

        public Builder addConnectionCallbacks(@NonNull ConnectionCallbacks listener) {
            Preconditions.checkNotNull(listener, "Listener must not be null");
            this.mConnectedCallbacks.add(listener);
            return this;
        }

        public Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener listener) {
            Preconditions.checkNotNull(listener, "Listener must not be null");
            this.mOnConnectionFailedListeners.add(listener);
            return this;
        }

        public Builder setViewForPopups(@NonNull View viewForPopups) {
            Preconditions.checkNotNull(viewForPopups, "View must not be null");
            this.mViewForPopups = viewForPopups;
            return this;
        }

        public Builder addScope(@NonNull Scope scope) {
            Preconditions.checkNotNull(scope, "Scope must not be null");
            this.mRequiredScopes.add(scope);
            return this;
        }

        public Builder addScopeNames(String[] scopeNames) {
            for (int i = 0; i < scopeNames.length; i += GoogleApiClient.SIGN_IN_MODE_REQUIRED) {
                this.mRequiredScopes.add(new Scope(scopeNames[i]));
            }
            return this;
        }

        public Builder addApi(@NonNull Api<? extends NotRequiredOptions> api) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.mApis.put(api, null);
            List<Scope> impliedScopes = api.getClientBuilder().getImpliedScopes(null);
            this.mImpliedScopes.addAll(impliedScopes);
            this.mRequiredScopes.addAll(impliedScopes);
            return this;
        }

        public Builder addApiIfAvailable(@NonNull Api<? extends NotRequiredOptions> api, int resolutionMode, Scope... scopes) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.mApis.put(api, null);
            addApiIfAvailableInternal(api, null, resolutionMode, scopes);
            return this;
        }

        public Builder addApiIfAvailable(@NonNull Api<? extends NotRequiredOptions> api, Scope... scopes) {
            Preconditions.checkNotNull(api, "Api must not be null");
            this.mApis.put(api, null);
            addApiIfAvailableInternal(api, null, GoogleApiClient.SIGN_IN_MODE_REQUIRED, scopes);
            return this;
        }

        public <O extends HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O options) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(options, "Null options are not permitted for this Api");
            this.mApis.put(api, options);
            List<Scope> impliedScopes = api.getClientBuilder().getImpliedScopes(options);
            this.mImpliedScopes.addAll(impliedScopes);
            this.mRequiredScopes.addAll(impliedScopes);
            return this;
        }

        public <O extends HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O options, int resolutionMode, Scope... scopes) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(options, "Null options are not permitted for this Api");
            this.mApis.put(api, options);
            addApiIfAvailableInternal(api, options, resolutionMode, scopes);
            return this;
        }

        public <O extends HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O options, Scope... scopes) {
            Preconditions.checkNotNull(api, "Api must not be null");
            Preconditions.checkNotNull(options, "Null options are not permitted for this Api");
            this.mApis.put(api, options);
            addApiIfAvailableInternal(api, options, GoogleApiClient.SIGN_IN_MODE_REQUIRED, scopes);
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.mAccount = accountName == null ? null : new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
            return this;
        }

        public Builder setAccount(Account account) {
            this.mAccount = account;
            return this;
        }

        public Builder setRealClientPackageName(String realClientPackageName) {
            this.mRealClientPackageName = realClientPackageName;
            return this;
        }

        public Builder setRealClientName(String realClientName) {
            this.mRealClientClassName = realClientName;
            return this;
        }

        @VisibleForTesting
        public Builder setSignInApiBuilderForTest(AbstractClientBuilder<? extends SignInClient, SignInOptions> signInApiBuilder) {
            this.mSignInApiBuilder = signInApiBuilder;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName(GoogleApiClient.DEFAULT_ACCOUNT);
        }

        public Builder setGravityForPopups(int gravityForPopups) {
            this.mGravityForPopups = gravityForPopups;
            return this;
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int clientId, @Nullable OnConnectionFailedListener unresolvedConnectionFailedListener) {
            Preconditions.checkArgument(clientId >= 0, "clientId must be non-negative");
            this.mAutoManageId = clientId;
            this.mLifecycleActivity = (FragmentActivity) Preconditions.checkNotNull(fragmentActivity, "Null activity is not permitted.");
            this.mConnectionFailedWithoutFixListener = unresolvedConnectionFailedListener;
            return this;
        }

        public Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener unresolvedConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, unresolvedConnectionFailedListener);
        }

        @VisibleForTesting
        public Builder setApiAvailabilityForTesting(GoogleApiAvailabilityLight apiAvailability) {
            this.mApiAvailability = apiAvailability;
            return this;
        }

        @VisibleForTesting
        public ClientSettings buildClientSettings() {
            SignInOptions signInOptions = SignInOptions.DEFAULT;
            if (this.mApis.containsKey(SignIn.API)) {
                signInOptions = (SignInOptions) this.mApis.get(SignIn.API);
            }
            return new ClientSettings(this.mAccount, this.mRequiredScopes, this.mOptionalApis, this.mGravityForPopups, this.mViewForPopups, this.mRealClientPackageName, this.mRealClientClassName, signInOptions);
        }

        @VisibleForTesting
        public Map<Api<?>, ApiOptions> getApiMapForTest() {
            return this.mApis;
        }

        @VisibleForTesting
        public Map<Api<?>, OptionalApiSettings> getOptionalApiMapForTest() {
            return this.mOptionalApis;
        }

        public GoogleApiClient build() {
            Preconditions.checkArgument(!this.mApis.isEmpty(), "must call addApi() to add at least one API");
            GoogleApiClient apiClient = buildInternal();
            synchronized (GoogleApiClient.sAllClients) {
                GoogleApiClient.sAllClients.add(apiClient);
            }
            if (this.mAutoManageId >= 0) {
                startAutoManage(apiClient);
            }
            return apiClient;
        }

        private GoogleApiClient buildInternal() {
            ClientSettings commonSettings = buildClientSettings();
            Api<?> signInApi = null;
            Api<?> gamesApi = null;
            Map<Api<?>, OptionalApiSettings> optionalApiSettingsMap = commonSettings.getOptionalApiSettings();
            Map<Api<?>, Integer> apiTypemap = new ArrayMap();
            Map<ClientKey<?>, Client> clients = new ArrayMap();
            ArrayList<ClientCallbacks> clientCallbacksList = new ArrayList();
            for (Api<?> api : this.mApis.keySet()) {
                Client client;
                Object apiOptions = this.mApis.get(api);
                int apiType = 0;
                if (optionalApiSettingsMap.get(api) != null) {
                    apiType = ((OptionalApiSettings) optionalApiSettingsMap.get(api)).mIsRecoverable ? GoogleApiClient.SIGN_IN_MODE_REQUIRED : GoogleApiClient.SIGN_IN_MODE_OPTIONAL;
                }
                apiTypemap.put(api, Integer.valueOf(apiType));
                ConnectionCallbacks callbacks = new ClientCallbacks(api, apiType);
                clientCallbacksList.add(callbacks);
                if (api.usesSimpleClient()) {
                    SimpleClientBuilder builder = api.getSimpleClientBuilder();
                    if (builder.getPriority() == GoogleApiClient.SIGN_IN_MODE_REQUIRED) {
                        gamesApi = api;
                    }
                    client = buildClient(builder, apiOptions, this.mContext, this.mLooper, commonSettings, callbacks, (OnConnectionFailedListener) callbacks);
                } else {
                    AbstractClientBuilder builder2 = api.getClientBuilder();
                    if (builder2.getPriority() == GoogleApiClient.SIGN_IN_MODE_REQUIRED) {
                        gamesApi = api;
                    }
                    client = buildClient(builder2, apiOptions, this.mContext, this.mLooper, commonSettings, callbacks, (OnConnectionFailedListener) callbacks);
                }
                clients.put(api.getClientKey(), client);
                if (client.providesSignIn()) {
                    if (signInApi != null) {
                        throw new IllegalStateException(api.getName() + " cannot be used with " + signInApi.getName());
                    }
                    signInApi = api;
                }
            }
            if (signInApi != null) {
                if (gamesApi != null) {
                    throw new IllegalStateException(signInApi.getName() + " cannot be used with " + gamesApi.getName());
                }
                boolean z = this.mAccount == null;
                Object[] objArr = new Object[GoogleApiClient.SIGN_IN_MODE_REQUIRED];
                objArr[0] = signInApi.getName();
                Preconditions.checkState(z, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", objArr);
                objArr = new Object[GoogleApiClient.SIGN_IN_MODE_REQUIRED];
                objArr[0] = signInApi.getName();
                Preconditions.checkState(this.mRequiredScopes.equals(this.mImpliedScopes), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", objArr);
            }
            return new GoogleApiClientImpl(this.mContext, new ReentrantLock(), this.mLooper, commonSettings, this.mApiAvailability, this.mSignInApiBuilder, apiTypemap, this.mConnectedCallbacks, this.mOnConnectionFailedListeners, clients, this.mAutoManageId, GoogleApiClientImpl.selectSignInModeAutomatically(clients.values(), true), clientCallbacksList);
        }

        private void startAutoManage(GoogleApiClient apiClient) {
            SupportLifecycleFragment lifecycleFragment = SupportLifecycleFragment.getInstanceOrNull(this.mLifecycleActivity);
            if (lifecycleFragment == null) {
                new Handler(this.mContext.getMainLooper()).post(new AnonymousClass1(apiClient));
            } else {
                startAutoManage(lifecycleFragment, apiClient);
            }
        }

        private void startAutoManage(SupportLifecycleFragment lifecycleFragment, GoogleApiClient apiClient) {
            lifecycleFragment.startAutoManage(this.mAutoManageId, apiClient, this.mConnectionFailedWithoutFixListener);
        }

        private <O extends ApiOptions> void addApiIfAvailableInternal(Api<O> api, O options, int resolutionMode, Scope... scopes) {
            boolean isRecoverable;
            if (resolutionMode == GoogleApiClient.SIGN_IN_MODE_REQUIRED) {
                isRecoverable = true;
            } else if (resolutionMode == GoogleApiClient.SIGN_IN_MODE_OPTIONAL) {
                isRecoverable = false;
            } else {
                throw new IllegalArgumentException("Invalid resolution mode: '" + resolutionMode + "', use a constant from GoogleApiClient.ResolutionMode");
            }
            Set<Scope> apiScopes = new HashSet(api.getClientBuilder().getImpliedScopes(options));
            Scope[] arr$ = scopes;
            int len$ = arr$.length;
            for (int i$ = 0; i$ < len$; i$ += GoogleApiClient.SIGN_IN_MODE_REQUIRED) {
                apiScopes.add(arr$[i$]);
            }
            this.mOptionalApis.put(api, new OptionalApiSettings(apiScopes, isRecoverable));
        }

        private static <C extends Client, O> C buildClient(AbstractClientBuilder<C, O> apiBuilder, Object apiOptions, Context context, Looper looper, ClientSettings commonSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return apiBuilder.buildClient(context, looper, commonSettings, apiOptions, connectionCallbacks, onConnectionFailedListener);
        }

        private static <C extends SimpleClient, O> SimpleClientAdapter buildClient(SimpleClientBuilder<C, O> apiBuilder, Object apiOptions, Context context, Looper looper, ClientSettings commonSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new SimpleClientAdapter(context, looper, apiBuilder.getGCoreServiceId(), connectionCallbacks, onConnectionFailedListener, commonSettings, apiBuilder.buildClient(apiOptions));
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(@NonNull ConnectionResult connectionResult);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResolutionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SignInMode {
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    static {
        sAllClients = Collections.newSetFromMap(new WeakHashMap());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void dumpAll(java.lang.String r8, java.io.FileDescriptor r9, java.io.PrintWriter r10, java.lang.String[] r11) {
        /*
        r6 = sAllClients;
        monitor-enter(r6);
        r1 = 0;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0040 }
        r5.<init>();	 Catch:{ all -> 0x0040 }
        r5 = r5.append(r8);	 Catch:{ all -> 0x0040 }
        r7 = "  ";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0040 }
        r4 = r5.toString();	 Catch:{ all -> 0x0040 }
        r5 = sAllClients;	 Catch:{ all -> 0x0040 }
        r3 = r5.iterator();	 Catch:{ all -> 0x0040 }
        r2 = r1;
    L_0x001e:
        r5 = r3.hasNext();	 Catch:{ all -> 0x0043 }
        if (r5 == 0) goto L_0x003e;
    L_0x0024:
        r0 = r3.next();	 Catch:{ all -> 0x0043 }
        r0 = (com.google.android.gms.common.api.GoogleApiClient) r0;	 Catch:{ all -> 0x0043 }
        r5 = r10.append(r8);	 Catch:{ all -> 0x0043 }
        r7 = "GoogleApiClient#";
        r5 = r5.append(r7);	 Catch:{ all -> 0x0043 }
        r1 = r2 + 1;
        r5.println(r2);	 Catch:{ all -> 0x0040 }
        r0.dump(r4, r9, r10, r11);	 Catch:{ all -> 0x0040 }
        r2 = r1;
        goto L_0x001e;
    L_0x003e:
        monitor-exit(r6);	 Catch:{ all -> 0x0043 }
        return;
    L_0x0040:
        r5 = move-exception;
    L_0x0041:
        monitor-exit(r6);	 Catch:{ all -> 0x0040 }
        throw r5;
    L_0x0043:
        r5 = move-exception;
        r1 = r2;
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApiClient.dumpAll(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    public static Set<GoogleApiClient> getAllClients() {
        return sAllClients;
    }

    public <A extends Client, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <A extends Client, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        throw new UnsupportedOperationException();
    }

    public <L> ListenerHolder<L> registerListener(@NonNull L l) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public <C extends Client> C getClient(@NonNull ClientKey<C> clientKey) {
        throw new UnsupportedOperationException();
    }

    public boolean hasApi(@NonNull Api<?> api) {
        throw new UnsupportedOperationException();
    }

    public boolean hasAuthenticatedScope(Scope scope) {
        throw new UnsupportedOperationException();
    }

    public Context getContext() {
        throw new UnsupportedOperationException();
    }

    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }

    public boolean maybeSignIn(SignInConnectionListener listener) {
        throw new UnsupportedOperationException();
    }

    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public void connect(int signInMode) {
        throw new UnsupportedOperationException();
    }

    public void setResultStore(ResultStore resultStore) {
        throw new UnsupportedOperationException();
    }

    public void registerPendingTransform(TransformedResultImpl transformedResult) {
        throw new UnsupportedOperationException();
    }

    public void unregisterPendingTransform(TransformedResultImpl transformedResult) {
        throw new UnsupportedOperationException();
    }
}
