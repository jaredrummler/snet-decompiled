package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class SingleRefDataBufferIterator<T> extends DataBufferIterator<T> {
    private T mDataBufferRef;

    public SingleRefDataBufferIterator(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (hasNext()) {
            this.mPosition++;
            if (this.mPosition == 0) {
                this.mDataBufferRef = this.mDataBuffer.get(0);
                if (!(this.mDataBufferRef instanceof DataBufferRef)) {
                    throw new IllegalStateException("DataBuffer reference of type " + this.mDataBufferRef.getClass() + " is not movable");
                }
            }
            ((DataBufferRef) this.mDataBufferRef).setDataRow(this.mPosition);
            return this.mDataBufferRef;
        }
        throw new NoSuchElementException("Cannot advance the iterator beyond " + this.mPosition);
    }
}
