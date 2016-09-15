package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean mEntitiesIndexed;
    private ArrayList<Integer> mEntityOffsets;

    protected abstract T getEntry(int i, int i2);

    protected abstract String getPrimaryDataMarkerColumn();

    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.mEntitiesIndexed = false;
    }

    public final T get(int position) {
        indexEntities();
        return getEntry(getRowIndex(position), getChildCount(position));
    }

    public int getCount() {
        indexEntities();
        return this.mEntityOffsets.size();
    }

    private void indexEntities() {
        synchronized (this) {
            if (!this.mEntitiesIndexed) {
                int count = this.mDataHolder.getCount();
                this.mEntityOffsets = new ArrayList();
                if (count > 0) {
                    this.mEntityOffsets.add(Integer.valueOf(0));
                    String markerColumn = getPrimaryDataMarkerColumn();
                    String currMarker = this.mDataHolder.getString(markerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    for (int i = 1; i < count; i++) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String newMarker = this.mDataHolder.getString(markerColumn, i, windowIndex);
                        if (newMarker == null) {
                            throw new NullPointerException("Missing value for markerColumn: " + markerColumn + ", at row: " + i + ", for window: " + windowIndex);
                        }
                        if (!newMarker.equals(currMarker)) {
                            currMarker = newMarker;
                            this.mEntityOffsets.add(Integer.valueOf(i));
                        }
                    }
                }
                this.mEntitiesIndexed = true;
            }
        }
    }

    int getRowIndex(int pos) {
        if (pos >= 0 && pos < this.mEntityOffsets.size()) {
            return ((Integer) this.mEntityOffsets.get(pos)).intValue();
        }
        throw new IllegalArgumentException("Position " + pos + " is out of bounds for this buffer");
    }

    protected int getChildCount(int pos) {
        if (pos < 0 || pos == this.mEntityOffsets.size()) {
            return 0;
        }
        int childCount;
        if (pos == this.mEntityOffsets.size() - 1) {
            childCount = this.mDataHolder.getCount() - ((Integer) this.mEntityOffsets.get(pos)).intValue();
        } else {
            childCount = ((Integer) this.mEntityOffsets.get(pos + 1)).intValue() - ((Integer) this.mEntityOffsets.get(pos)).intValue();
        }
        if (childCount != 1) {
            return childCount;
        }
        int index = getRowIndex(pos);
        int windowIndex = this.mDataHolder.getWindowIndex(index);
        String childMarkerColumn = getChildDataMarkerColumn();
        if (childMarkerColumn == null || this.mDataHolder.getString(childMarkerColumn, index, windowIndex) != null) {
            return childCount;
        }
        return 0;
    }

    protected String getChildDataMarkerColumn() {
        return null;
    }
}
