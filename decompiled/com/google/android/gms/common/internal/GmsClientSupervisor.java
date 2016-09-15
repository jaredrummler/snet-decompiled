package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import com.google.android.gms.common.util.VisibleForTesting;

public abstract class GmsClientSupervisor {
    private static GmsClientSupervisor sInstance;
    private static final Object sSingletonLock;

    public abstract boolean bindService(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract boolean bindService(String str, ServiceConnection serviceConnection, String str2);

    @VisibleForTesting
    public abstract int getClientCountForTesting(String str);

    @VisibleForTesting
    public abstract void resetForTesting();

    public abstract void unbindService(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract void unbindService(String str, ServiceConnection serviceConnection, String str2);

    static {
        sSingletonLock = new Object();
    }

    public static GmsClientSupervisor getInstance(Context context) {
        synchronized (sSingletonLock) {
            if (sInstance == null) {
                sInstance = new GmsClientSupervisorImpl(context.getApplicationContext());
            }
        }
        return sInstance;
    }
}
