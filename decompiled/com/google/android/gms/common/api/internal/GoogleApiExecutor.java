package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class GoogleApiExecutor {
    private static final int NUM_THREADS = 2;
    private static final ExecutorService sInstance;

    static {
        sInstance = Executors.newFixedThreadPool(NUM_THREADS, new NumberedThreadFactory("GAC_Executor"));
    }

    public static ExecutorService getInstance() {
        return sInstance;
    }
}
