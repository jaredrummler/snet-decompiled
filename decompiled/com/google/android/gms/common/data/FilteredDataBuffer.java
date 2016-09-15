package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.internal.Asserts;
import java.util.Iterator;

public abstract class FilteredDataBuffer<T> implements DataBuffer<T> {
    protected final DataBuffer<T> mDataBuffer;

    protected abstract int computeRealPosition(int i);

    public FilteredDataBuffer(DataBuffer<T> dataBuffer) {
        Asserts.checkNotNull(dataBuffer);
        Asserts.checkState(!(dataBuffer instanceof FilteredDataBuffer), "Not possible to have nested FilteredDataBuffers.");
        this.mDataBuffer = dataBuffer;
    }

    public T get(int position) {
        return this.mDataBuffer.get(computeRealPosition(position));
    }

    public void release() {
        this.mDataBuffer.release();
    }

    @Deprecated
    public boolean isClosed() {
        return this.mDataBuffer.isClosed();
    }

    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public Iterator<T> singleRefIterator() {
        return iterator();
    }

    public Bundle getMetadata() {
        return this.mDataBuffer.getMetadata();
    }

    public void close() {
        release();
    }
}
