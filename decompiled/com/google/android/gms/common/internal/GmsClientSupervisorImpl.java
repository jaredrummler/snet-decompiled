package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class GmsClientSupervisorImpl extends GmsClientSupervisor implements Callback {
    private static final boolean DEBUG = false;
    private static final long DEFAULT_UNBIND_DELAY_MILLIS = 5000;
    private static final int MSG_UNBIND_SERVICE = 0;
    private static final String TAG = "GmsClientSupervisor";
    private final Context mApplicationContext;
    private final HashMap<ConnectionStatusConfig, GmsClientConnectionStatus> mConnectionStatus;
    private final ConnectionTracker mConnectionTracker;
    private final Handler mHandler;
    private final long mUnbindDelayMillis;

    private static final class ConnectionStatusConfig {
        private final String mAction;
        private final ComponentName mComponentName;

        public ConnectionStatusConfig(String action) {
            this.mAction = Preconditions.checkNotEmpty(action);
            this.mComponentName = null;
        }

        public ConnectionStatusConfig(ComponentName componentName) {
            this.mAction = null;
            this.mComponentName = (ComponentName) Preconditions.checkNotNull(componentName);
        }

        public String toString() {
            return this.mAction == null ? this.mComponentName.flattenToString() : this.mAction;
        }

        public String getAction() {
            return this.mAction;
        }

        public ComponentName getComponentName() {
            return this.mComponentName;
        }

        public Intent getStartServiceIntent() {
            if (this.mAction != null) {
                return new Intent(this.mAction).setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
            }
            return new Intent().setComponent(this.mComponentName);
        }

        public int hashCode() {
            return Objects.hashCode(this.mAction, this.mComponentName);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof ConnectionStatusConfig)) {
                return GmsClientSupervisorImpl.DEBUG;
            }
            ConnectionStatusConfig that = (ConnectionStatusConfig) o;
            if (Objects.equal(this.mAction, that.mAction) && Objects.equal(this.mComponentName, that.mComponentName)) {
                return true;
            }
            return GmsClientSupervisorImpl.DEBUG;
        }
    }

    private final class GmsClientConnectionStatus {
        public static final int STATE_SERVICE_CONNECTED = 1;
        public static final int STATE_SERVICE_CONNECTING = 3;
        public static final int STATE_SERVICE_DISCONNECTED = 2;
        private IBinder mBinder;
        private final Set<ServiceConnection> mClientConnections;
        private ComponentName mComponentName;
        private final ConnectionStatusConfig mConfig;
        private boolean mIsBound;
        private int mState;
        private final SupervisorServiceConnection mSupervisorConnection;

        public class SupervisorServiceConnection implements ServiceConnection {
            public void onServiceConnected(ComponentName component, IBinder binder) {
                synchronized (GmsClientSupervisorImpl.this.mConnectionStatus) {
                    GmsClientConnectionStatus.this.mBinder = binder;
                    GmsClientConnectionStatus.this.mComponentName = component;
                    for (ServiceConnection c : GmsClientConnectionStatus.this.mClientConnections) {
                        c.onServiceConnected(component, binder);
                    }
                    GmsClientConnectionStatus.this.mState = GmsClientConnectionStatus.STATE_SERVICE_CONNECTED;
                }
            }

            public void onServiceDisconnected(ComponentName component) {
                synchronized (GmsClientSupervisorImpl.this.mConnectionStatus) {
                    GmsClientConnectionStatus.this.mBinder = null;
                    GmsClientConnectionStatus.this.mComponentName = component;
                    for (ServiceConnection c : GmsClientConnectionStatus.this.mClientConnections) {
                        c.onServiceDisconnected(component);
                    }
                    GmsClientConnectionStatus.this.mState = GmsClientConnectionStatus.STATE_SERVICE_DISCONNECTED;
                }
            }
        }

        public GmsClientConnectionStatus(ConnectionStatusConfig config) {
            this.mConfig = config;
            this.mSupervisorConnection = new SupervisorServiceConnection();
            this.mClientConnections = new HashSet();
            this.mState = STATE_SERVICE_DISCONNECTED;
        }

        @TargetApi(14)
        public void bindService(String realClientName) {
            this.mState = STATE_SERVICE_CONNECTING;
            this.mIsBound = GmsClientSupervisorImpl.this.mConnectionTracker.bindService(GmsClientSupervisorImpl.this.mApplicationContext, realClientName, this.mConfig.getStartServiceIntent(), this.mSupervisorConnection, LogSource.GMM_BRIIM_COUNTERS);
            if (!this.mIsBound) {
                this.mState = STATE_SERVICE_DISCONNECTED;
                try {
                    GmsClientSupervisorImpl.this.mConnectionTracker.unbindService(GmsClientSupervisorImpl.this.mApplicationContext, this.mSupervisorConnection);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void unbindService(String realClientName) {
            GmsClientSupervisorImpl.this.mConnectionTracker.unbindService(GmsClientSupervisorImpl.this.mApplicationContext, this.mSupervisorConnection);
            this.mIsBound = GmsClientSupervisorImpl.DEBUG;
            this.mState = STATE_SERVICE_DISCONNECTED;
        }

        public void addServiceConnection(ServiceConnection serviceConnection, String realClientName) {
            GmsClientSupervisorImpl.this.mConnectionTracker.logConnectService(GmsClientSupervisorImpl.this.mApplicationContext, serviceConnection, realClientName, this.mConfig.getStartServiceIntent());
            this.mClientConnections.add(serviceConnection);
        }

        public void removeServiceConnection(ServiceConnection serviceConnection, String realClientName) {
            GmsClientSupervisorImpl.this.mConnectionTracker.logDisconnectService(GmsClientSupervisorImpl.this.mApplicationContext, serviceConnection);
            this.mClientConnections.remove(serviceConnection);
        }

        public boolean isBound() {
            return this.mIsBound;
        }

        public int getState() {
            return this.mState;
        }

        public boolean containsGmsServiceConnection(ServiceConnection serviceConnection) {
            return this.mClientConnections.contains(serviceConnection);
        }

        public boolean hasNoGmsServiceConnections() {
            return this.mClientConnections.isEmpty();
        }

        public int getNumGmsServiceConnections() {
            return this.mClientConnections.size();
        }

        public IBinder getBinder() {
            return this.mBinder;
        }

        public ComponentName getComponentName() {
            return this.mComponentName;
        }
    }

    GmsClientSupervisorImpl(Context context) {
        this.mConnectionStatus = new HashMap();
        this.mApplicationContext = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.mConnectionTracker = ConnectionTracker.getInstance();
        this.mUnbindDelayMillis = DEFAULT_UNBIND_DELAY_MILLIS;
    }

    @VisibleForTesting
    GmsClientSupervisorImpl(Context context, ConnectionTracker connectionTracker, long unbindDelayMillis) {
        this.mConnectionStatus = new HashMap();
        this.mApplicationContext = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.mConnectionTracker = connectionTracker;
        this.mUnbindDelayMillis = unbindDelayMillis;
    }

    public boolean bindService(String startServiceAction, ServiceConnection connection, String realClientName) {
        return bindService(new ConnectionStatusConfig(startServiceAction), connection, realClientName);
    }

    public boolean bindService(ComponentName componentName, ServiceConnection connection, String realClientName) {
        return bindService(new ConnectionStatusConfig(componentName), connection, realClientName);
    }

    private boolean bindService(ConnectionStatusConfig config, ServiceConnection connection, String realClientName) {
        boolean isBound;
        Preconditions.checkNotNull(connection, "ServiceConnection must not be null");
        synchronized (this.mConnectionStatus) {
            GmsClientConnectionStatus status = (GmsClientConnectionStatus) this.mConnectionStatus.get(config);
            if (status != null) {
                this.mHandler.removeMessages(MSG_UNBIND_SERVICE, status);
                if (!status.containsGmsServiceConnection(connection)) {
                    status.addServiceConnection(connection, realClientName);
                    switch (status.getState()) {
                        case Type.TEMPORARY /*1*/:
                            connection.onServiceConnected(status.getComponentName(), status.getBinder());
                            break;
                        case Type.INDEFINITELY /*2*/:
                            status.bindService(realClientName);
                            break;
                        default:
                            break;
                    }
                }
                throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + config);
            }
            status = new GmsClientConnectionStatus(config);
            status.addServiceConnection(connection, realClientName);
            status.bindService(realClientName);
            this.mConnectionStatus.put(config, status);
            isBound = status.isBound();
        }
        return isBound;
    }

    public void unbindService(String startServiceAction, ServiceConnection connection, String realClientName) {
        unbindService(new ConnectionStatusConfig(startServiceAction), connection, realClientName);
    }

    public void unbindService(ComponentName componentName, ServiceConnection connection, String realClientName) {
        unbindService(new ConnectionStatusConfig(componentName), connection, realClientName);
    }

    private void unbindService(ConnectionStatusConfig config, ServiceConnection connection, String realClientName) {
        Preconditions.checkNotNull(connection, "ServiceConnection must not be null");
        synchronized (this.mConnectionStatus) {
            GmsClientConnectionStatus status = (GmsClientConnectionStatus) this.mConnectionStatus.get(config);
            if (status == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + config);
            } else if (status.containsGmsServiceConnection(connection)) {
                status.removeServiceConnection(connection, realClientName);
                if (status.hasNoGmsServiceConnections()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(MSG_UNBIND_SERVICE, status), this.mUnbindDelayMillis);
                }
            } else {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + config);
            }
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_UNBIND_SERVICE /*0*/:
                GmsClientConnectionStatus status = msg.obj;
                synchronized (this.mConnectionStatus) {
                    if (status.hasNoGmsServiceConnections()) {
                        if (status.isBound()) {
                            status.unbindService(TAG);
                        }
                        this.mConnectionStatus.remove(status.mConfig);
                    }
                    break;
                }
                return true;
            default:
                return DEBUG;
        }
    }

    @VisibleForTesting
    public int getClientCountForTesting(String startServiceAction) {
        int size;
        synchronized (this.mConnectionStatus) {
            GmsClientConnectionStatus status = (GmsClientConnectionStatus) this.mConnectionStatus.get(new ConnectionStatusConfig(startServiceAction));
            size = status == null ? MSG_UNBIND_SERVICE : status.mClientConnections.size();
        }
        return size;
    }

    @VisibleForTesting
    public void resetForTesting() {
        synchronized (this.mConnectionStatus) {
            for (GmsClientConnectionStatus status : this.mConnectionStatus.values()) {
                this.mHandler.removeMessages(MSG_UNBIND_SERVICE, status);
                if (status.isBound()) {
                    status.unbindService(TAG);
                }
            }
            this.mConnectionStatus.clear();
        }
    }

    @VisibleForTesting
    Handler getHandlerForTesting() {
        return this.mHandler;
    }
}
