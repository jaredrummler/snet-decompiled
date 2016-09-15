package com.google.android.gms.common.data;

import com.google.android.gms.common.data.DataBufferObserver.Observable;
import java.util.HashSet;
import java.util.Iterator;

public final class DataBufferObserverSet implements DataBufferObserver, Observable {
    private HashSet<DataBufferObserver> mObserverSet;

    public DataBufferObserverSet() {
        this.mObserverSet = new HashSet();
    }

    public boolean hasObservers() {
        return !this.mObserverSet.isEmpty();
    }

    public void clear() {
        this.mObserverSet.clear();
    }

    public void addObserver(DataBufferObserver observer) {
        this.mObserverSet.add(observer);
    }

    public void removeObserver(DataBufferObserver observer) {
        this.mObserverSet.remove(observer);
    }

    public void onDataChanged() {
        Iterator i$ = this.mObserverSet.iterator();
        while (i$.hasNext()) {
            ((DataBufferObserver) i$.next()).onDataChanged();
        }
    }

    public void onDataRangeChanged(int position, int count) {
        Iterator i$ = this.mObserverSet.iterator();
        while (i$.hasNext()) {
            ((DataBufferObserver) i$.next()).onDataRangeChanged(position, count);
        }
    }

    public void onDataRangeInserted(int position, int count) {
        Iterator i$ = this.mObserverSet.iterator();
        while (i$.hasNext()) {
            ((DataBufferObserver) i$.next()).onDataRangeInserted(position, count);
        }
    }

    public void onDataRangeRemoved(int position, int count) {
        Iterator i$ = this.mObserverSet.iterator();
        while (i$.hasNext()) {
            ((DataBufferObserver) i$.next()).onDataRangeRemoved(position, count);
        }
    }

    public void onDataRangeMoved(int fromPosition, int toPosition, int count) {
        Iterator i$ = this.mObserverSet.iterator();
        while (i$.hasNext()) {
            ((DataBufferObserver) i$.next()).onDataRangeMoved(fromPosition, toPosition, count);
        }
    }
}
