package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public final class SortedDataBuffer<T> implements DataBuffer<T> {
    private final DataBuffer<T> mBuffer;
    private final Integer[] mIndexMap;

    /* renamed from: com.google.android.gms.common.data.SortedDataBuffer.1 */
    class AnonymousClass1 implements Comparator<Integer> {
        final /* synthetic */ Comparator val$comparator;

        AnonymousClass1(Comparator comparator) {
            this.val$comparator = comparator;
        }

        public int compare(Integer o1, Integer o2) {
            return this.val$comparator.compare(SortedDataBuffer.this.mBuffer.get(o1.intValue()), SortedDataBuffer.this.mBuffer.get(o2.intValue()));
        }
    }

    public SortedDataBuffer(DataBuffer<T> buffer, Comparator<T> comparator) {
        this.mBuffer = buffer;
        this.mIndexMap = new Integer[buffer.getCount()];
        for (int i = 0; i < this.mIndexMap.length; i++) {
            this.mIndexMap[i] = Integer.valueOf(i);
        }
        Arrays.sort(this.mIndexMap, new AnonymousClass1(comparator));
    }

    public int getCount() {
        return this.mIndexMap.length;
    }

    public T get(int position) {
        return this.mBuffer.get(this.mIndexMap[position].intValue());
    }

    public void release() {
        this.mBuffer.release();
    }

    public Bundle getMetadata() {
        return this.mBuffer.getMetadata();
    }

    public void close() {
        this.mBuffer.release();
    }

    public boolean isClosed() {
        return this.mBuffer.isClosed();
    }

    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public Iterator<T> singleRefIterator() {
        return iterator();
    }
}
