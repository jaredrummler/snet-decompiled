package com.google.android.gms.common.internal;

import android.os.DeadObjectException;
import android.os.IInterface;
import com.google.android.gms.common.api.Api.SimpleClient;

public abstract class BaseSimpleClient<T extends IInterface> implements SimpleClient<T> {
    private final Object mLock;
    private T mService;
    private int mState;

    public BaseSimpleClient() {
        this.mLock = new Object();
        this.mService = null;
        this.mState = 1;
    }

    public T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.mState == 4) {
                throw new DeadObjectException();
            } else if (this.mState != 3) {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            } else {
                Preconditions.checkState(this.mService != null, "Client is connected but service is null");
                t = this.mService;
            }
        }
        return t;
    }

    public void setState(int state, T service) {
        synchronized (this.mLock) {
            this.mState = state;
            this.mService = service;
        }
    }
}
