package com.google.android.gms.common.internal;

import android.support.v4.util.LruCache;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExpirableLruCache<K, V> {
    public static int TIME_UNSET;
    private final long mAccessExpireNano;
    private HashMap<K, Long> mAccessTimeMap;
    private final LruCache<K, V> mCache;
    private final Object mLock;
    private final long mWriteExpireNano;
    private HashMap<K, Long> mWriteTimeMap;

    /* renamed from: com.google.android.gms.common.internal.ExpirableLruCache.1 */
    class AnonymousClass1 extends LruCache<K, V> {
        AnonymousClass1(int x0) {
            super(x0);
        }

        protected int sizeOf(K key, V value) {
            return ExpirableLruCache.this.sizeOf(key, value);
        }

        protected V create(K key) {
            return ExpirableLruCache.this.create(key);
        }

        protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
            ExpirableLruCache.this.entryRemoved(evicted, key, oldValue, newValue);
            synchronized (ExpirableLruCache.this.mLock) {
                if (newValue == null) {
                    if (ExpirableLruCache.this.hasAccessExpiration()) {
                        ExpirableLruCache.this.mAccessTimeMap.remove(key);
                    }
                }
                if (newValue == null && ExpirableLruCache.this.hasWriteExpiration()) {
                    ExpirableLruCache.this.mWriteTimeMap.remove(key);
                }
            }
        }
    }

    static {
        TIME_UNSET = -1;
    }

    public ExpirableLruCache(int maxSize, long expireAfterAccess, long expireAfterWrite, TimeUnit unit) {
        this.mLock = new Object();
        this.mAccessExpireNano = TimeUnit.NANOSECONDS.convert(expireAfterAccess, unit);
        this.mWriteExpireNano = TimeUnit.NANOSECONDS.convert(expireAfterWrite, unit);
        boolean z = hasAccessExpiration() || hasWriteExpiration();
        Preconditions.checkArgument(z, "ExpirableLruCache has both access and write expiration negative");
        this.mCache = new AnonymousClass1(maxSize);
        if (hasAccessExpiration()) {
            this.mAccessTimeMap = new HashMap();
        }
        if (hasWriteExpiration()) {
            this.mWriteTimeMap = new HashMap();
        }
    }

    protected int sizeOf(K k, V v) {
        return 1;
    }

    protected V create(K k) {
        return null;
    }

    protected void entryRemoved(boolean evicted, K k, V v, V v2) {
    }

    public V put(K key, V value) {
        if (hasWriteExpiration()) {
            long currentNano = System.nanoTime();
            synchronized (this.mLock) {
                this.mWriteTimeMap.put(key, Long.valueOf(currentNano));
            }
        }
        return this.mCache.put(key, value);
    }

    public V get(K key) {
        V value;
        synchronized (this.mLock) {
            if (isExpired(key)) {
                this.mCache.remove(key);
            }
            value = this.mCache.get(key);
            if (value != null && this.mAccessExpireNano > 0) {
                this.mAccessTimeMap.put(key, Long.valueOf(System.nanoTime()));
            }
        }
        return value;
    }

    public V remove(K key) {
        return this.mCache.remove(key);
    }

    public void removeExpired() {
        for (K key : this.mCache.snapshot().keySet()) {
            synchronized (this.mLock) {
                if (isExpired(key)) {
                    this.mCache.remove(key);
                }
            }
        }
    }

    public Map<K, V> snapshot() {
        removeExpired();
        return this.mCache.snapshot();
    }

    public void evictAll() {
        this.mCache.evictAll();
    }

    private boolean isExpired(K key) {
        long currentNano = System.nanoTime();
        if (hasAccessExpiration() && this.mAccessTimeMap.containsKey(key) && currentNano - ((Long) this.mAccessTimeMap.get(key)).longValue() > this.mAccessExpireNano) {
            return true;
        }
        if (hasWriteExpiration() && this.mWriteTimeMap.containsKey(key) && currentNano - ((Long) this.mWriteTimeMap.get(key)).longValue() > this.mWriteExpireNano) {
            return true;
        }
        return false;
    }

    private boolean hasAccessExpiration() {
        return this.mAccessExpireNano >= 0;
    }

    private boolean hasWriteExpiration() {
        return this.mWriteExpireNano >= 0;
    }
}
