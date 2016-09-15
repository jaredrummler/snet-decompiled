package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    private final ThreadFactory defaultFactory;
    private final String mName;
    private final int mPriority;

    public NamedThreadFactory(String name) {
        this(name, 0);
    }

    public NamedThreadFactory(String name, int priority) {
        this.defaultFactory = Executors.defaultThreadFactory();
        this.mName = (String) Preconditions.checkNotNull(name, "Name must not be null");
        this.mPriority = priority;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = this.defaultFactory.newThread(new PriorityRunnable(runnable, this.mPriority));
        thread.setName(this.mName);
        return thread;
    }
}
