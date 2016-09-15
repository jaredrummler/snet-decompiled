package com.google.android.gms.common.util;

import java.util.ArrayList;

public abstract class ObjectPoolBase<T> {
    private final int mMaxSize;
    private final ArrayList<T> mPool;

    protected abstract T newObject();

    public ObjectPoolBase(int maxSize) {
        this.mPool = new ArrayList(maxSize);
        this.mMaxSize = maxSize;
    }

    @VisibleForTesting
    int getMaxSize() {
        return this.mMaxSize;
    }

    protected boolean cleanUpObject(T t) {
        return true;
    }

    public final T aquire() {
        T remove;
        synchronized (this.mPool) {
            int size = this.mPool.size();
            if (size > 0) {
                remove = this.mPool.remove(size - 1);
            } else {
                remove = newObject();
            }
        }
        return remove;
    }

    public final boolean release(T object) {
        boolean z;
        synchronized (this.mPool) {
            int size = this.mPool.size();
            for (int i = 0; i < size; i++) {
                if (this.mPool.get(i) == object) {
                    throw new IllegalArgumentException("Object released already: " + object);
                }
            }
            if (size >= this.mMaxSize || !cleanUpObject(object)) {
                z = false;
            } else {
                this.mPool.add(object);
                z = true;
            }
        }
        return z;
    }

    @VisibleForTesting
    ArrayList<T> getPoolForTest() {
        return this.mPool;
    }
}
