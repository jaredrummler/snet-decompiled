package com.google.android.gms.people.internal;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;

@VisibleForTesting
public class StringToIntsMap {
    private final StringToObjectsMap<Integer> mInner;

    public StringToIntsMap() {
        this.mInner = new StringToObjectsMap();
    }

    @VisibleForTesting
    public void put(String key, int value) {
        this.mInner.put(key, Integer.valueOf(value));
    }

    @VisibleForTesting
    public int getValueCount(String key) {
        return this.mInner.getValueCount(key);
    }

    @VisibleForTesting
    public int getValue(String key, int index) {
        return ((Integer) this.mInner.getValue(key, index)).intValue();
    }

    @VisibleForTesting
    public void clear() {
        this.mInner.clear();
    }

    @VisibleForTesting
    public int size() {
        return this.mInner.size();
    }

    @VisibleForTesting
    public Set<String> getKeys() {
        return this.mInner.getKeys();
    }

    @VisibleForTesting
    public void dumpForDebug(String logtag) {
        this.mInner.dumpForDebug(logtag);
    }

    public String toString() {
        return this.mInner.toString();
    }
}
