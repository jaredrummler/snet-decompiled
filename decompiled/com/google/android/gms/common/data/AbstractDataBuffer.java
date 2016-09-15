package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    private static final boolean DBG_STACK_TRACE = false;
    protected final DataHolder mDataHolder;

    public abstract T get(int i);

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
        if (this.mDataHolder != null) {
            this.mDataHolder.setLeakIdentifier(this);
        }
    }

    public int getCount() {
        return this.mDataHolder == null ? 0 : this.mDataHolder.getCount();
    }

    @Deprecated
    public final void close() {
        release();
    }

    @Deprecated
    public boolean isClosed() {
        return this.mDataHolder == null || this.mDataHolder.isClosed();
    }

    public Bundle getMetadata() {
        return this.mDataHolder.getMetadata();
    }

    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public Iterator<T> singleRefIterator() {
        return new SingleRefDataBufferIterator(this);
    }

    public void release() {
        if (this.mDataHolder != null) {
            this.mDataHolder.close();
        }
    }
}
