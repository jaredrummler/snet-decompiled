package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class DefaultClock implements Clock {
    private static DefaultClock sInstance;

    public static synchronized Clock getInstance() {
        Clock clock;
        synchronized (DefaultClock.class) {
            if (sInstance == null) {
                sInstance = new DefaultClock();
            }
            clock = sInstance;
        }
        return clock;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public long nanoTime() {
        return System.nanoTime();
    }
}
