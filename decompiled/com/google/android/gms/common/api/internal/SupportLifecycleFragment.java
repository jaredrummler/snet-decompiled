package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class SupportLifecycleFragment extends Fragment implements OnCancelListener {
    protected static final int DIALOG_REQUEST_CODE = 2;
    @VisibleForTesting
    static final int RESOLVE_ERROR_REQUEST_CODE = 1;
    private static final String STATE_FAILED_CLIENT_ID = "failed_client_id";
    private static final String STATE_FAILED_RESOLUTION = "failed_resolution";
    private static final String STATE_FAILED_STATUS = "failed_status";
    private static final String STATE_RESOLVING_ERROR = "resolving_error";
    private static final String SUPPORT_LIFECYCLE_FRAGMENT_IMPL_CLASS = "com.google.android.gms.common.api.internal.SupportLifecycleFragmentImpl";
    private static final String TAG = "GmsSupportLifecycleFrag";
    private final SparseArray<ClientInfo> mClientInfoMap;
    private final Handler mConnectionFailedHandler;
    private int mFailingClientId;
    private ConnectionResult mFailingResult;
    protected GooglePlayServicesUpdatedReceiver mGmsUpdatedReceiver;
    private boolean mResolvingError;
    private boolean mStarted;

    private class ClientInfo implements OnConnectionFailedListener {
        public final GoogleApiClient apiClient;
        public final int clientId;
        public final OnConnectionFailedListener connectionFailedWithoutFixListener;

        public ClientInfo(int clientId, GoogleApiClient googleApiClient, OnConnectionFailedListener connectionFailedWithoutFixListener) {
            this.clientId = clientId;
            this.apiClient = googleApiClient;
            this.connectionFailedWithoutFixListener = connectionFailedWithoutFixListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void onConnectionFailed(@NonNull ConnectionResult result) {
            SupportLifecycleFragment.this.mConnectionFailedHandler.post(new ConnectionFailedResolver(this.clientId, result));
        }

        public void stopAutoManage() {
            this.apiClient.unregisterConnectionFailedListener(this);
            this.apiClient.disconnect();
        }

        public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            writer.append(prefix).append("GoogleApiClient #").print(this.clientId);
            writer.println(":");
            this.apiClient.dump(prefix + "  ", fd, writer, args);
        }
    }

    private class ConnectionFailedResolver implements Runnable {
        private final int mClientId;
        private final ConnectionResult mResult;

        public ConnectionFailedResolver(int clientId, ConnectionResult result) {
            this.mClientId = clientId;
            this.mResult = result;
        }

        @MainThread
        public void run() {
            if (SupportLifecycleFragment.this.mStarted && !SupportLifecycleFragment.this.mResolvingError) {
                SupportLifecycleFragment.this.mResolvingError = true;
                SupportLifecycleFragment.this.mFailingClientId = this.mClientId;
                SupportLifecycleFragment.this.mFailingResult = this.mResult;
                if (this.mResult.hasResolution()) {
                    try {
                        this.mResult.startResolutionForResult(SupportLifecycleFragment.this.getActivity(), ((SupportLifecycleFragment.this.getActivity().getSupportFragmentManager().getFragments().indexOf(SupportLifecycleFragment.this) + SupportLifecycleFragment.RESOLVE_ERROR_REQUEST_CODE) << 16) + SupportLifecycleFragment.RESOLVE_ERROR_REQUEST_CODE);
                    } catch (SendIntentException e) {
                        SupportLifecycleFragment.this.markErrorsResolved();
                    }
                } else if (SupportLifecycleFragment.this.getGoogleApiAvailibility().isUserResolvableError(this.mResult.getErrorCode())) {
                    SupportLifecycleFragment.this.onUserResolvableError(this.mClientId, this.mResult);
                } else if (this.mResult.getErrorCode() == 18) {
                    SupportLifecycleFragment.this.onServiceUpdating(this.mClientId, this.mResult);
                } else {
                    SupportLifecycleFragment.this.dispatchConnectionFailed(this.mClientId, this.mResult);
                }
            }
        }
    }

    public SupportLifecycleFragment() {
        this.mFailingClientId = -1;
        this.mConnectionFailedHandler = new Handler(Looper.getMainLooper());
        this.mClientInfoMap = new SparseArray();
    }

    @Nullable
    public static SupportLifecycleFragment getInstanceOrNull(FragmentActivity activity) {
        Preconditions.checkMainThread("Must be called from main thread of process");
        try {
            SupportLifecycleFragment fragment = (SupportLifecycleFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
            if (fragment == null || fragment.isRemoving()) {
                return null;
            }
            return fragment;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFrag is not a SupportLifecycleFragment", e);
        }
    }

    public static SupportLifecycleFragment getInstance(FragmentActivity activity) {
        SupportLifecycleFragment fragment = getInstanceOrNull(activity);
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        if (fragment == null) {
            fragment = getImplInstance();
            if (fragment == null) {
                Log.w(TAG, "Unable to find connection error message resources (Did you include play-services-base and the proper proguard rules?); error dialogs may be unavailable.");
                fragment = new SupportLifecycleFragment();
            }
            fragmentManager.beginTransaction().add((Fragment) fragment, TAG).commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return fragment;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    private static com.google.android.gms.common.api.internal.SupportLifecycleFragment getImplInstance() {
        /*
        r6 = 3;
        r3 = 0;
        r2 = 0;
        r4 = "com.google.android.gms.common.api.internal.SupportLifecycleFragmentImpl";
        r3 = java.lang.Class.forName(r4);	 Catch:{ ClassNotFoundException -> 0x003e, LinkageError -> 0x0014, SecurityException -> 0x003c }
    L_0x0009:
        if (r3 == 0) goto L_0x0013;
    L_0x000b:
        r4 = r3.newInstance();	 Catch:{ IllegalAccessException -> 0x003a, InstantiationException -> 0x0036, ExceptionInInitializerError -> 0x0038, RuntimeException -> 0x0025 }
        r0 = r4;
        r0 = (com.google.android.gms.common.api.internal.SupportLifecycleFragment) r0;	 Catch:{ IllegalAccessException -> 0x003a, InstantiationException -> 0x0036, ExceptionInInitializerError -> 0x0038, RuntimeException -> 0x0025 }
        r2 = r0;
    L_0x0013:
        return r2;
    L_0x0014:
        r1 = move-exception;
    L_0x0015:
        r4 = "GmsSupportLifecycleFrag";
        r4 = android.util.Log.isLoggable(r4, r6);
        if (r4 == 0) goto L_0x0009;
    L_0x001d:
        r4 = "GmsSupportLifecycleFrag";
        r5 = "Unable to find SupportLifecycleFragmentImpl class";
        android.util.Log.d(r4, r5, r1);
        goto L_0x0009;
    L_0x0025:
        r1 = move-exception;
    L_0x0026:
        r4 = "GmsSupportLifecycleFrag";
        r4 = android.util.Log.isLoggable(r4, r6);
        if (r4 == 0) goto L_0x0013;
    L_0x002e:
        r4 = "GmsSupportLifecycleFrag";
        r5 = "Unable to instantiate SupportLifecycleFragmentImpl class";
        android.util.Log.d(r4, r5, r1);
        goto L_0x0013;
    L_0x0036:
        r1 = move-exception;
        goto L_0x0026;
    L_0x0038:
        r1 = move-exception;
        goto L_0x0026;
    L_0x003a:
        r1 = move-exception;
        goto L_0x0026;
    L_0x003c:
        r1 = move-exception;
        goto L_0x0015;
    L_0x003e:
        r1 = move-exception;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.SupportLifecycleFragment.getImplInstance():com.google.android.gms.common.api.internal.SupportLifecycleFragment");
    }

    public void startAutoManage(int clientId, GoogleApiClient googleApiClient, OnConnectionFailedListener connectionFailedWithoutFixListener) {
        Preconditions.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        Preconditions.checkState(this.mClientInfoMap.indexOfKey(clientId) < 0, "Already managing a GoogleApiClient with id " + clientId);
        this.mClientInfoMap.put(clientId, new ClientInfo(clientId, googleApiClient, connectionFailedWithoutFixListener));
        if (this.mStarted && !this.mResolvingError) {
            googleApiClient.connect();
        }
    }

    public void stopAutoManage(int clientId) {
        ClientInfo clientInfo = (ClientInfo) this.mClientInfoMap.get(clientId);
        this.mClientInfoMap.remove(clientId);
        if (clientInfo != null) {
            clientInfo.stopAutoManage();
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        dispatchConnectionFailed(this.mFailingClientId, new ConnectionResult(13, null));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mResolvingError = savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
            this.mFailingClientId = savedInstanceState.getInt(STATE_FAILED_CLIENT_ID, -1);
            if (this.mFailingClientId >= 0) {
                this.mFailingResult = new ConnectionResult(savedInstanceState.getInt(STATE_FAILED_STATUS), (PendingIntent) savedInstanceState.getParcelable(STATE_FAILED_RESOLUTION));
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, this.mResolvingError);
        if (this.mFailingClientId >= 0) {
            outState.putInt(STATE_FAILED_CLIENT_ID, this.mFailingClientId);
            outState.putInt(STATE_FAILED_STATUS, this.mFailingResult.getErrorCode());
            outState.putParcelable(STATE_FAILED_RESOLUTION, this.mFailingResult.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
        if (!this.mResolvingError) {
            for (int i = 0; i < this.mClientInfoMap.size(); i += RESOLVE_ERROR_REQUEST_CODE) {
                ((ClientInfo) this.mClientInfoMap.valueAt(i)).apiClient.connect();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean errorResolved = false;
        switch (requestCode) {
            case RESOLVE_ERROR_REQUEST_CODE /*1*/:
                if (resultCode != -1) {
                    if (resultCode == 0) {
                        this.mFailingResult = new ConnectionResult(13, null);
                        break;
                    }
                }
                errorResolved = true;
                break;
                break;
            case DIALOG_REQUEST_CODE /*2*/:
                if (getGoogleApiAvailibility().isGooglePlayServicesAvailable(getActivity()) == 0) {
                    errorResolved = true;
                    break;
                }
                break;
        }
        if (errorResolved) {
            markErrorsResolved();
        } else {
            dispatchConnectionFailed(this.mFailingClientId, this.mFailingResult);
        }
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
        for (int i = 0; i < this.mClientInfoMap.size(); i += RESOLVE_ERROR_REQUEST_CODE) {
            ((ClientInfo) this.mClientInfoMap.valueAt(i)).apiClient.disconnect();
        }
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(prefix, fd, writer, args);
        for (int i = 0; i < this.mClientInfoMap.size(); i += RESOLVE_ERROR_REQUEST_CODE) {
            ((ClientInfo) this.mClientInfoMap.valueAt(i)).dump(prefix, fd, writer, args);
        }
    }

    private void dispatchConnectionFailed(int clientId, ConnectionResult result) {
        Log.w(TAG, "Unresolved error while connecting client. Stopping auto-manage.");
        ClientInfo clientInfo = (ClientInfo) this.mClientInfoMap.get(clientId);
        if (clientInfo != null) {
            stopAutoManage(clientId);
            OnConnectionFailedListener listener = clientInfo.connectionFailedWithoutFixListener;
            if (listener != null) {
                listener.onConnectionFailed(result);
            }
        }
        markErrorsResolved();
    }

    protected void markErrorsResolved() {
        this.mResolvingError = false;
        this.mFailingClientId = -1;
        this.mFailingResult = null;
        if (this.mGmsUpdatedReceiver != null) {
            this.mGmsUpdatedReceiver.unregister();
            this.mGmsUpdatedReceiver = null;
        }
        for (int i = 0; i < this.mClientInfoMap.size(); i += RESOLVE_ERROR_REQUEST_CODE) {
            ((ClientInfo) this.mClientInfoMap.valueAt(i)).apiClient.connect();
        }
    }

    private static String getErrorMessageString(ConnectionResult result) {
        return result.getErrorMessage() + " (" + result.getErrorCode() + ": " + GooglePlayServicesUtilLight.getErrorString(result.getErrorCode()) + ')';
    }

    protected GoogleApiAvailabilityLight getGoogleApiAvailibility() {
        return GoogleApiAvailabilityLight.getInstance();
    }

    protected void onUserResolvableError(int clientId, ConnectionResult result) {
        Log.w(TAG, "Failed to connect due to user resolvable error " + getErrorMessageString(result));
        dispatchConnectionFailed(clientId, result);
    }

    protected void onServiceUpdating(int clientId, ConnectionResult result) {
        Log.w(TAG, "Unable to connect, GooglePlayServices is updating.");
        dispatchConnectionFailed(clientId, result);
    }
}
