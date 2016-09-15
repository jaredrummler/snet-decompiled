package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBufferIterator<T> implements Iterator<T> {
    protected final DataBuffer<T> mDataBuffer;
    protected int mPosition;

    public DataBufferIterator(DataBuffer<T> dataBuffer) {
        this.mDataBuffer = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
        this.mPosition = -1;
    }

    public boolean hasNext() {
        return this.mPosition < this.mDataBuffer.getCount() + -1;
    }

    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.mDataBuffer;
            int i = this.mPosition + 1;
            this.mPosition = i;
            return dataBuffer.get(i);
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.mPosition);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
