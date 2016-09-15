package com.google.android.gms.common.api;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public final class Api<O extends ApiOptions> {
    private final AbstractClientBuilder<?, O> mClientBuilder;
    private final ClientKey<?> mClientKey;
    private final String mName;
    private final SimpleClientBuilder<?, O> mSimpleClientBuilder;
    private final SimpleClientKey<?> mSimpleClientKey;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }
    }

    @VisibleForTesting
    public static abstract class AbstractClientBuilder<T extends Client, O> {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;

        public abstract T buildClient(Context context, Looper looper, ClientSettings clientSettings, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener);

        public int getPriority() {
            return API_PRIORITY_OTHER;
        }

        public List<Scope> getImpliedScopes(O o) {
            return Collections.emptyList();
        }
    }

    public interface Client {
        void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks);

        void disconnect();

        void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

        void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set);

        @Nullable
        IBinder getServiceBrokerBinder();

        Intent getSignInIntent();

        boolean isConnected();

        boolean isConnecting();

        boolean providesSignIn();

        boolean requiresAccount();

        boolean requiresSignIn();
    }

    public interface ApiType {
        public static final int OPTIONAL_NONRECOVERABLE = 2;
        public static final int OPTIONAL_RECOVERABLE = 1;
        public static final int STANDARD = 0;
    }

    @VisibleForTesting
    public static final class ClientKey<C extends Client> {
    }

    public interface SimpleClient<T extends IInterface> {
        T createServiceInterface(IBinder iBinder);

        String getServiceDescriptor();

        String getStartServiceAction();

        void setState(int i, T t);
    }

    @VisibleForTesting
    public interface SimpleClientBuilder<T extends SimpleClient, O> {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;

        T buildClient(O o);

        int getGCoreServiceId();

        int getPriority();
    }

    @VisibleForTesting
    public static final class SimpleClientKey<C extends SimpleClient> {
    }

    public <C extends Client> Api(String name, AbstractClientBuilder<C, O> clientBuilder, ClientKey<C> clientKey) {
        Preconditions.checkNotNull(clientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        this.mName = name;
        this.mClientBuilder = clientBuilder;
        this.mSimpleClientBuilder = null;
        this.mClientKey = clientKey;
        this.mSimpleClientKey = null;
    }

    public <C extends SimpleClient> Api(String name, SimpleClientBuilder<C, O> clientBuilder, SimpleClientKey<?> clientKey) {
        Preconditions.checkNotNull(clientBuilder, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(clientKey, "Cannot construct an Api with a null ClientKey");
        this.mName = name;
        this.mClientBuilder = null;
        this.mSimpleClientBuilder = clientBuilder;
        this.mClientKey = null;
        this.mSimpleClientKey = clientKey;
    }

    public AbstractClientBuilder<?, O> getClientBuilder() {
        Preconditions.checkState(this.mClientBuilder != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.mClientBuilder;
    }

    public SimpleClientBuilder<?, O> getSimpleClientBuilder() {
        Preconditions.checkState(this.mSimpleClientBuilder != null, "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.mSimpleClientBuilder;
    }

    public ClientKey<?> getClientKey() {
        Preconditions.checkState(this.mClientKey != null, "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.mClientKey;
    }

    public SimpleClientKey<?> getSimpleClientKey() {
        Preconditions.checkState(this.mSimpleClientKey != null, "This API was constructed with a ClientKey. Use getClientKey");
        return this.mSimpleClientKey;
    }

    public boolean usesSimpleClient() {
        return this.mSimpleClientKey != null;
    }

    public String getName() {
        return this.mName;
    }
}
