package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingServiceConnection implements ServiceConnection {
    private final BlockingQueue<IBinder> mBlockingQueue;
    boolean mUsed;

    public BlockingServiceConnection() {
        this.mUsed = false;
        this.mBlockingQueue = new LinkedBlockingQueue();
    }

    public void onServiceConnected(ComponentName name, IBinder service) {
        this.mBlockingQueue.add(service);
    }

    public void onServiceDisconnected(ComponentName name) {
    }

    public IBinder getService() throws InterruptedException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        } else if (this.mUsed) {
            throw new IllegalStateException();
        } else {
            this.mUsed = true;
            return (IBinder) this.mBlockingQueue.take();
        }
    }
}
