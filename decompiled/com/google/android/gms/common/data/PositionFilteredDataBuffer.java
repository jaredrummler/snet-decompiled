package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.HashSet;

public final class PositionFilteredDataBuffer<T> extends FilteredDataBuffer<T> {
    private final ArrayList<Integer> mEntityOffsets;
    private final HashSet<Integer> mExcludedPositionSet;

    public PositionFilteredDataBuffer(AbstractDataBuffer<T> dataBuffer) {
        super(dataBuffer);
        this.mExcludedPositionSet = new HashSet();
        this.mEntityOffsets = new ArrayList();
        filterEntities();
    }

    public int getCount() {
        return this.mDataBuffer.getCount() - this.mExcludedPositionSet.size();
    }

    public int computeRealPosition(int position) {
        if (position >= 0 && position < getCount()) {
            return ((Integer) this.mEntityOffsets.get(position)).intValue();
        }
        throw new IllegalArgumentException("Position " + position + " is out of bounds " + "for this buffer");
    }

    public void filterOut(int position) {
        if (position >= 0 && position <= this.mDataBuffer.getCount()) {
            this.mExcludedPositionSet.add(Integer.valueOf(position));
            filterEntities();
        }
    }

    public void unfilter(int position) {
        this.mExcludedPositionSet.remove(Integer.valueOf(position));
        filterEntities();
    }

    public void clearFilters() {
        this.mExcludedPositionSet.clear();
        filterEntities();
    }

    private void filterEntities() {
        this.mEntityOffsets.clear();
        int size = this.mDataBuffer.getCount();
        for (int i = 0; i < size; i++) {
            if (!this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                this.mEntityOffsets.add(Integer.valueOf(i));
            }
        }
    }
}
