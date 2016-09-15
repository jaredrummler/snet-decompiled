package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NumberedThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultFactory;
    private final String mBaseName;
    private final AtomicInteger mCount;
    private final int mPriority;

    public NumberedThreadFactory(String baseName) {
        this(baseName, 0);
    }

    public NumberedThreadFactory(String baseName, int priority) {
        this.mCount = new AtomicInteger();
        this.defaultFactory = Executors.defaultThreadFactory();
        this.mBaseName = (String) Preconditions.checkNotNull(baseName, "Name must not be null");
        this.mPriority = priority;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = this.defaultFactory.newThread(new PriorityRunnable(runnable, this.mPriority));
        thread.setName(this.mBaseName + "[" + this.mCount.getAndIncrement() + "]");
        return thread;
    }
}
