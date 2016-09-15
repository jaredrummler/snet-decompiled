package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Callback {
    private static final int SEND_ON_CONNECTED = 1;
    private static final String TAG = "GmsClientEvents";
    private volatile boolean mCallbacksEnabled;
    private final ArrayList<OnConnectionFailedListener> mConnectionFailedListeners;
    private final ArrayList<ConnectionCallbacks> mConnectionListeners;
    @VisibleForTesting
    final ArrayList<ConnectionCallbacks> mConnectionListenersRemoved;
    private final AtomicInteger mDisableCallbacksCount;
    private final GmsClientEventState mEventState;
    private final Handler mHandler;
    private boolean mIsProcessingConnectionCallback;
    private final Object mLock;

    @VisibleForTesting
    public interface GmsClientEventState {
        Bundle getConnectionHint();

        boolean isConnected();
    }

    public GmsClientEventManager(Looper looper, GmsClientEventState eventState) {
        this.mConnectionListeners = new ArrayList();
        this.mConnectionListenersRemoved = new ArrayList();
        this.mConnectionFailedListeners = new ArrayList();
        this.mCallbacksEnabled = false;
        this.mDisableCallbacksCount = new AtomicInteger(0);
        this.mIsProcessingConnectionCallback = false;
        this.mLock = new Object();
        this.mEventState = eventState;
        this.mHandler = new Handler(looper, this);
    }

    @VisibleForTesting
    GmsClientEventManager(Handler handler, GmsClientEventState eventState) {
        this.mConnectionListeners = new ArrayList();
        this.mConnectionListenersRemoved = new ArrayList();
        this.mConnectionFailedListeners = new ArrayList();
        this.mCallbacksEnabled = false;
        this.mDisableCallbacksCount = new AtomicInteger(0);
        this.mIsProcessingConnectionCallback = false;
        this.mLock = new Object();
        this.mEventState = eventState;
        this.mHandler = handler;
    }

    public void disableCallbacks() {
        this.mCallbacksEnabled = false;
        this.mDisableCallbacksCount.incrementAndGet();
    }

    public void enableCallbacks() {
        this.mCallbacksEnabled = true;
    }

    @VisibleForTesting
    protected void onConnectionSuccess() {
        synchronized (this.mLock) {
            onConnectionSuccess(this.mEventState.getConnectionHint());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.util.VisibleForTesting
    public void onConnectionSuccess(android.os.Bundle r10) {
        /*
        r9 = this;
        r6 = 0;
        r5 = 1;
        r4 = android.os.Looper.myLooper();
        r7 = r9.mHandler;
        r7 = r7.getLooper();
        if (r4 != r7) goto L_0x006e;
    L_0x000e:
        r4 = r5;
    L_0x000f:
        r7 = "onConnectionSuccess must only be called on the Handler thread";
        com.google.android.gms.common.internal.Preconditions.checkState(r4, r7);
        r7 = r9.mLock;
        monitor-enter(r7);
        r4 = r9.mIsProcessingConnectionCallback;	 Catch:{ all -> 0x0080 }
        if (r4 != 0) goto L_0x0070;
    L_0x001b:
        r4 = r5;
    L_0x001c:
        com.google.android.gms.common.internal.Preconditions.checkState(r4);	 Catch:{ all -> 0x0080 }
        r4 = r9.mHandler;	 Catch:{ all -> 0x0080 }
        r8 = 1;
        r4.removeMessages(r8);	 Catch:{ all -> 0x0080 }
        r4 = 1;
        r9.mIsProcessingConnectionCallback = r4;	 Catch:{ all -> 0x0080 }
        r4 = r9.mConnectionListenersRemoved;	 Catch:{ all -> 0x0080 }
        r4 = r4.size();	 Catch:{ all -> 0x0080 }
        if (r4 != 0) goto L_0x0072;
    L_0x0030:
        com.google.android.gms.common.internal.Preconditions.checkState(r5);	 Catch:{ all -> 0x0080 }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0080 }
        r4 = r9.mConnectionListeners;	 Catch:{ all -> 0x0080 }
        r0.<init>(r4);	 Catch:{ all -> 0x0080 }
        r4 = r9.mDisableCallbacksCount;	 Catch:{ all -> 0x0080 }
        r1 = r4.get();	 Catch:{ all -> 0x0080 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0080 }
    L_0x0044:
        r4 = r2.hasNext();	 Catch:{ all -> 0x0080 }
        if (r4 == 0) goto L_0x0064;
    L_0x004a:
        r3 = r2.next();	 Catch:{ all -> 0x0080 }
        r3 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r3;	 Catch:{ all -> 0x0080 }
        r4 = r9.mCallbacksEnabled;	 Catch:{ all -> 0x0080 }
        if (r4 == 0) goto L_0x0064;
    L_0x0054:
        r4 = r9.mEventState;	 Catch:{ all -> 0x0080 }
        r4 = r4.isConnected();	 Catch:{ all -> 0x0080 }
        if (r4 == 0) goto L_0x0064;
    L_0x005c:
        r4 = r9.mDisableCallbacksCount;	 Catch:{ all -> 0x0080 }
        r4 = r4.get();	 Catch:{ all -> 0x0080 }
        if (r4 == r1) goto L_0x0074;
    L_0x0064:
        r4 = r9.mConnectionListenersRemoved;	 Catch:{ all -> 0x0080 }
        r4.clear();	 Catch:{ all -> 0x0080 }
        r4 = 0;
        r9.mIsProcessingConnectionCallback = r4;	 Catch:{ all -> 0x0080 }
        monitor-exit(r7);	 Catch:{ all -> 0x0080 }
        return;
    L_0x006e:
        r4 = r6;
        goto L_0x000f;
    L_0x0070:
        r4 = r6;
        goto L_0x001c;
    L_0x0072:
        r5 = r6;
        goto L_0x0030;
    L_0x0074:
        r4 = r9.mConnectionListenersRemoved;	 Catch:{ all -> 0x0080 }
        r4 = r4.contains(r3);	 Catch:{ all -> 0x0080 }
        if (r4 != 0) goto L_0x0044;
    L_0x007c:
        r3.onConnected(r10);	 Catch:{ all -> 0x0080 }
        goto L_0x0044;
    L_0x0080:
        r4 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0080 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientEventManager.onConnectionSuccess(android.os.Bundle):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.util.VisibleForTesting
    public void onUnintentionalDisconnection(int r9) {
        /*
        r8 = this;
        r4 = 0;
        r5 = 1;
        r6 = android.os.Looper.myLooper();
        r7 = r8.mHandler;
        r7 = r7.getLooper();
        if (r6 != r7) goto L_0x000f;
    L_0x000e:
        r4 = r5;
    L_0x000f:
        r6 = "onUnintentionalDisconnection must only be called on the Handler thread";
        com.google.android.gms.common.internal.Preconditions.checkState(r4, r6);
        r4 = r8.mHandler;
        r4.removeMessages(r5);
        r5 = r8.mLock;
        monitor-enter(r5);
        r4 = 1;
        r8.mIsProcessingConnectionCallback = r4;	 Catch:{ all -> 0x005e }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x005e }
        r4 = r8.mConnectionListeners;	 Catch:{ all -> 0x005e }
        r0.<init>(r4);	 Catch:{ all -> 0x005e }
        r4 = r8.mDisableCallbacksCount;	 Catch:{ all -> 0x005e }
        r1 = r4.get();	 Catch:{ all -> 0x005e }
        r2 = r0.iterator();	 Catch:{ all -> 0x005e }
    L_0x0030:
        r4 = r2.hasNext();	 Catch:{ all -> 0x005e }
        if (r4 == 0) goto L_0x0048;
    L_0x0036:
        r3 = r2.next();	 Catch:{ all -> 0x005e }
        r3 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r3;	 Catch:{ all -> 0x005e }
        r4 = r8.mCallbacksEnabled;	 Catch:{ all -> 0x005e }
        if (r4 == 0) goto L_0x0048;
    L_0x0040:
        r4 = r8.mDisableCallbacksCount;	 Catch:{ all -> 0x005e }
        r4 = r4.get();	 Catch:{ all -> 0x005e }
        if (r4 == r1) goto L_0x0052;
    L_0x0048:
        r4 = r8.mConnectionListenersRemoved;	 Catch:{ all -> 0x005e }
        r4.clear();	 Catch:{ all -> 0x005e }
        r4 = 0;
        r8.mIsProcessingConnectionCallback = r4;	 Catch:{ all -> 0x005e }
        monitor-exit(r5);	 Catch:{ all -> 0x005e }
        return;
    L_0x0052:
        r4 = r8.mConnectionListeners;	 Catch:{ all -> 0x005e }
        r4 = r4.contains(r3);	 Catch:{ all -> 0x005e }
        if (r4 == 0) goto L_0x0030;
    L_0x005a:
        r3.onConnectionSuspended(r9);	 Catch:{ all -> 0x005e }
        goto L_0x0030;
    L_0x005e:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x005e }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientEventManager.onUnintentionalDisconnection(int):void");
    }

    @VisibleForTesting
    public void onConnectionFailure(ConnectionResult status) {
        Preconditions.checkState(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(SEND_ON_CONNECTED);
        synchronized (this.mLock) {
            ArrayList<OnConnectionFailedListener> connectionFailedListeners = new ArrayList(this.mConnectionFailedListeners);
            int expectedDisableCount = this.mDisableCallbacksCount.get();
            Iterator i$ = connectionFailedListeners.iterator();
            while (i$.hasNext()) {
                OnConnectionFailedListener listener = (OnConnectionFailedListener) i$.next();
                if (!this.mCallbacksEnabled || this.mDisableCallbacksCount.get() != expectedDisableCount) {
                    return;
                } else if (this.mConnectionFailedListeners.contains(listener)) {
                    listener.onConnectionFailed(status);
                }
            }
        }
    }

    public void registerConnectionCallbacks(ConnectionCallbacks listener) {
        Preconditions.checkNotNull(listener);
        synchronized (this.mLock) {
            if (this.mConnectionListeners.contains(listener)) {
                Log.w(TAG, "registerConnectionCallbacks(): listener " + listener + " is already registered");
            } else {
                this.mConnectionListeners.add(listener);
            }
        }
        if (this.mEventState.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(SEND_ON_CONNECTED, listener));
        }
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks listener) {
        boolean contains;
        Preconditions.checkNotNull(listener);
        synchronized (this.mLock) {
            contains = this.mConnectionListeners.contains(listener);
        }
        return contains;
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks listener) {
        Preconditions.checkNotNull(listener);
        synchronized (this.mLock) {
            if (!this.mConnectionListeners.remove(listener)) {
                Log.w(TAG, "unregisterConnectionCallbacks(): listener " + listener + " not found");
            } else if (this.mIsProcessingConnectionCallback) {
                this.mConnectionListenersRemoved.add(listener);
            }
        }
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener listener) {
        Preconditions.checkNotNull(listener);
        synchronized (this.mLock) {
            if (this.mConnectionFailedListeners.contains(listener)) {
                Log.w(TAG, "registerConnectionFailedListener(): listener " + listener + " is already registered");
            } else {
                this.mConnectionFailedListeners.add(listener);
            }
        }
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener listener) {
        boolean contains;
        Preconditions.checkNotNull(listener);
        synchronized (this.mLock) {
            contains = this.mConnectionFailedListeners.contains(listener);
        }
        return contains;
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener listener) {
        Preconditions.checkNotNull(listener);
        synchronized (this.mLock) {
            if (!this.mConnectionFailedListeners.remove(listener)) {
                Log.w(TAG, "unregisterConnectionFailedListener(): listener " + listener + " not found");
            }
        }
    }

    public boolean handleMessage(Message msg) {
        if (msg.what == SEND_ON_CONNECTED) {
            ConnectionCallbacks listener = msg.obj;
            synchronized (this.mLock) {
                if (this.mCallbacksEnabled && this.mEventState.isConnected() && this.mConnectionListeners.contains(listener)) {
                    listener.onConnected(this.mEventState.getConnectionHint());
                }
            }
            return true;
        }
        Log.wtf(TAG, "Don't know how to handle message: " + msg.what, new Exception());
        return false;
    }

    public boolean areCallbacksEnabled() {
        return this.mCallbacksEnabled;
    }
}
