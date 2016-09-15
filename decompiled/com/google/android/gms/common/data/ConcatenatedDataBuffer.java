package com.google.android.gms.common.data;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.data.TextFilterable.StringFilter;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;

@VisibleForTesting
public final class ConcatenatedDataBuffer<T> implements DataBuffer<T>, ExclusionFilterable, TextFilterable {
    private Bundle mBundle;
    private final ArrayList<Integer> mCumulativeCountList;
    private final ArrayList<DataBuffer<T>> mDataBufferList;
    private int mTotalCount;

    public ConcatenatedDataBuffer() {
        this.mDataBufferList = new ArrayList();
        this.mCumulativeCountList = new ArrayList();
    }

    public ConcatenatedDataBuffer(DataBuffer<T> dataBuffer) {
        this.mDataBufferList = new ArrayList();
        this.mCumulativeCountList = new ArrayList();
        append(dataBuffer);
    }

    public int getCount() {
        int i;
        synchronized (this) {
            i = this.mTotalCount;
        }
        return i;
    }

    public T get(int position) {
        T t;
        synchronized (this) {
            int n = this.mDataBufferList.size();
            for (int i = 0; i < n; i++) {
                int cumulativeCount = ((Integer) this.mCumulativeCountList.get(i)).intValue();
                if (position < cumulativeCount) {
                    DataBuffer<T> dataBuffer = (DataBuffer) this.mDataBufferList.get(i);
                    if (dataBuffer != null) {
                        t = dataBuffer.get((position - cumulativeCount) + dataBuffer.getCount());
                        break;
                    }
                }
            }
            t = null;
        }
        return t;
    }

    public void release() {
        synchronized (this) {
            int n = this.mDataBufferList.size();
            for (int i = 0; i < n; i++) {
                DataBuffer<T> buffer = (DataBuffer) this.mDataBufferList.get(i);
                if (buffer != null) {
                    buffer.release();
                }
            }
            this.mDataBufferList.clear();
            this.mCumulativeCountList.clear();
            this.mBundle = null;
        }
    }

    public void append(DataBuffer<T> dataBuffer) {
        if (dataBuffer != null) {
            synchronized (this) {
                Bundle metadata;
                if (this.mDataBufferList.isEmpty()) {
                    this.mBundle = new Bundle();
                    metadata = dataBuffer.getMetadata();
                    if (metadata != null) {
                        this.mBundle.putString(DataBufferUtils.KEY_PREV_PAGE_TOKEN, metadata.getString(DataBufferUtils.KEY_PREV_PAGE_TOKEN));
                    }
                }
                this.mDataBufferList.add(dataBuffer);
                computeCounts();
                metadata = dataBuffer.getMetadata();
                if (metadata != null) {
                    this.mBundle.putString(DataBufferUtils.KEY_NEXT_PAGE_TOKEN, metadata.getString(DataBufferUtils.KEY_NEXT_PAGE_TOKEN));
                } else {
                    this.mBundle.remove(DataBufferUtils.KEY_NEXT_PAGE_TOKEN);
                }
            }
        }
    }

    public Bundle getMetadata() {
        Bundle bundle;
        synchronized (this) {
            bundle = this.mBundle;
        }
        return bundle;
    }

    @Deprecated
    public void close() {
        release();
    }

    @Deprecated
    public boolean isClosed() {
        return false;
    }

    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public Iterator<T> singleRefIterator() {
        return iterator();
    }

    public void computeCounts() {
        int totalCount = 0;
        this.mCumulativeCountList.clear();
        int n = this.mDataBufferList.size();
        for (int i = 0; i < n; i++) {
            DataBuffer<T> buffer = (DataBuffer) this.mDataBufferList.get(i);
            if (buffer != null) {
                totalCount += buffer.getCount();
            }
            this.mCumulativeCountList.add(Integer.valueOf(totalCount));
        }
        this.mTotalCount = totalCount;
    }

    public static <T> ConcatenatedDataBuffer<T> extend(ConcatenatedDataBuffer<T> original, DataBuffer<T> dataBuffer) {
        ConcatenatedDataBuffer<T> result = new ConcatenatedDataBuffer();
        Iterator i$ = original.mDataBufferList.iterator();
        while (i$.hasNext()) {
            result.append((DataBuffer) i$.next());
        }
        result.append(dataBuffer);
        return result;
    }

    public void filterOut(String term) {
        int size = this.mDataBufferList.size();
        for (int i = 0; i < size; i++) {
            DataBuffer<T> dataBuffer = (DataBuffer) this.mDataBufferList.get(i);
            if (dataBuffer instanceof ExclusionFilterable) {
                ((ExclusionFilterable) dataBuffer).filterOut(term);
            }
        }
        computeCounts();
    }

    public void unfilter(String term) {
        int size = this.mDataBufferList.size();
        for (int i = 0; i < size; i++) {
            DataBuffer<T> dataBuffer = (DataBuffer) this.mDataBufferList.get(i);
            if (dataBuffer instanceof ExclusionFilterable) {
                ((ExclusionFilterable) dataBuffer).unfilter(term);
            }
        }
        computeCounts();
    }

    public void clearFilters() {
        int size = this.mDataBufferList.size();
        for (int i = 0; i < size; i++) {
            DataBuffer<T> dataBuffer = (DataBuffer) this.mDataBufferList.get(i);
            if (dataBuffer instanceof ExclusionFilterable) {
                ((ExclusionFilterable) dataBuffer).clearFilters();
            }
        }
        computeCounts();
    }

    public void setFilterTerm(Context context, String term) {
        int size = this.mDataBufferList.size();
        for (int i = 0; i < size; i++) {
            DataBuffer<T> dataBuffer = (DataBuffer) this.mDataBufferList.get(i);
            if (dataBuffer instanceof TextFilterable) {
                ((TextFilterable) dataBuffer).setFilterTerm(context, term);
            }
        }
        computeCounts();
    }

    public void setFilterTerm(Context context, StringFilter stringFilter, String term) {
        int size = this.mDataBufferList.size();
        for (int i = 0; i < size; i++) {
            DataBuffer<T> dataBuffer = (DataBuffer) this.mDataBufferList.get(i);
            if (dataBuffer instanceof TextFilterable) {
                ((TextFilterable) dataBuffer).setFilterTerm(context, stringFilter, term);
            }
        }
        computeCounts();
    }
}
