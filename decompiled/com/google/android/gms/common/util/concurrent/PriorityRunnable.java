package com.google.android.gms.common.util.concurrent;

import android.os.Process;

class PriorityRunnable implements Runnable {
    private final int mPriority;
    private final Runnable mRunnable;

    public PriorityRunnable(Runnable runnable, int priority) {
        this.mRunnable = runnable;
        this.mPriority = priority;
    }

    public void run() {
        Process.setThreadPriority(this.mPriority);
        this.mRunnable.run();
    }
}
