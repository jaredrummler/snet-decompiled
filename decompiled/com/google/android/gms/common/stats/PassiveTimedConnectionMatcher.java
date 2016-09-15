package com.google.android.gms.common.stats;

import android.support.v4.util.SimpleArrayMap;

public class PassiveTimedConnectionMatcher {
    private static final String TAG = "ConnectionTracker";
    private final SimpleArrayMap<String, Long> mMap;
    private final int mMaxCapacity;
    private final long mTimeOutDurationMillis;

    public PassiveTimedConnectionMatcher() {
        this.mTimeOutDurationMillis = 60000;
        this.mMaxCapacity = 10;
        this.mMap = new SimpleArrayMap(10);
    }

    public PassiveTimedConnectionMatcher(int maxCapacity, long timeOutDurationMillis) {
        this.mTimeOutDurationMillis = timeOutDurationMillis;
        this.mMaxCapacity = maxCapacity;
        this.mMap = new SimpleArrayMap();
    }

    public Long get(String key) {
        Long l;
        synchronized (this) {
            l = (Long) this.mMap.get(key);
        }
        return l;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Long put(java.lang.String r8) {
        /*
        r7 = this;
        r0 = android.os.SystemClock.elapsedRealtime();
        r2 = r7.mTimeOutDurationMillis;
        monitor-enter(r7);
    L_0x0007:
        r4 = r7.mMap;	 Catch:{ all -> 0x003c }
        r4 = r4.size();	 Catch:{ all -> 0x003c }
        r5 = r7.mMaxCapacity;	 Catch:{ all -> 0x003c }
        if (r4 < r5) goto L_0x003f;
    L_0x0011:
        r7.cleanUp(r2, r0);	 Catch:{ all -> 0x003c }
        r4 = 2;
        r2 = r2 / r4;
        r4 = "ConnectionTracker";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003c }
        r5.<init>();	 Catch:{ all -> 0x003c }
        r6 = "The max capacity ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r6 = r7.mMaxCapacity;	 Catch:{ all -> 0x003c }
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r6 = " is not enough. Current durationThreshold is: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x003c }
        r5 = r5.append(r2);	 Catch:{ all -> 0x003c }
        r5 = r5.toString();	 Catch:{ all -> 0x003c }
        android.util.Log.w(r4, r5);	 Catch:{ all -> 0x003c }
        goto L_0x0007;
    L_0x003c:
        r4 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        throw r4;
    L_0x003f:
        r4 = r7.mMap;	 Catch:{ all -> 0x003c }
        r5 = java.lang.Long.valueOf(r0);	 Catch:{ all -> 0x003c }
        r4 = r4.put(r8, r5);	 Catch:{ all -> 0x003c }
        r4 = (java.lang.Long) r4;	 Catch:{ all -> 0x003c }
        monitor-exit(r7);	 Catch:{ all -> 0x003c }
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.stats.PassiveTimedConnectionMatcher.put(java.lang.String):java.lang.Long");
    }

    private void cleanUp(long durationThreshold, long currentTimeMillis) {
        for (int i = this.mMap.size() - 1; i >= 0; i--) {
            if (currentTimeMillis - ((Long) this.mMap.valueAt(i)).longValue() > durationThreshold) {
                this.mMap.removeAt(i);
            }
        }
    }

    public boolean remove(String connKey) {
        boolean z;
        synchronized (this) {
            z = this.mMap.remove(connKey) != null;
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean removeByPrefix(java.lang.String r5) {
        /*
        r4 = this;
        r2 = 0;
        monitor-enter(r4);
        r0 = 0;
    L_0x0003:
        r3 = r4.size();	 Catch:{ all -> 0x0024 }
        if (r0 >= r3) goto L_0x0022;
    L_0x0009:
        r3 = r4.mMap;	 Catch:{ all -> 0x0024 }
        r1 = r3.keyAt(r0);	 Catch:{ all -> 0x0024 }
        r1 = (java.lang.String) r1;	 Catch:{ all -> 0x0024 }
        if (r1 == 0) goto L_0x001f;
    L_0x0013:
        r3 = r1.startsWith(r5);	 Catch:{ all -> 0x0024 }
        if (r3 == 0) goto L_0x001f;
    L_0x0019:
        r3 = r4.mMap;	 Catch:{ all -> 0x0024 }
        r3.remove(r1);	 Catch:{ all -> 0x0024 }
        r2 = 1;
    L_0x001f:
        r0 = r0 + 1;
        goto L_0x0003;
    L_0x0022:
        monitor-exit(r4);	 Catch:{ all -> 0x0024 }
        return r2;
    L_0x0024:
        r3 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0024 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.stats.PassiveTimedConnectionMatcher.removeByPrefix(java.lang.String):boolean");
    }

    public int size() {
        int size;
        synchronized (this) {
            size = this.mMap.size();
        }
        return size;
    }
}
