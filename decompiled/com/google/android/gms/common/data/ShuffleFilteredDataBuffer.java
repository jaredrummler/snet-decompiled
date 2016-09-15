package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShuffleFilteredDataBuffer<T> extends FilteredDataBuffer<T> {
    private final List<Integer> mEntityOffsets;
    private final int mSizeLimit;

    public ShuffleFilteredDataBuffer(DataBuffer<T> dataBuffer, int sizeLimit) {
        super(dataBuffer);
        this.mSizeLimit = sizeLimit;
        this.mEntityOffsets = getRandomUniqueIndexes(this.mSizeLimit, this.mDataBuffer.getCount());
    }

    public int getCount() {
        return Math.min(this.mSizeLimit, this.mDataBuffer.getCount());
    }

    public int computeRealPosition(int position) {
        if (position >= 0 && position < getCount()) {
            return ((Integer) this.mEntityOffsets.get(position)).intValue();
        }
        throw new IllegalArgumentException("Position " + position + " is out of bounds " + "for this buffer");
    }

    private static List<Integer> getRandomUniqueIndexes(int numIndexes, int max) {
        if (numIndexes > max) {
            throw new IllegalArgumentException("numIndexes must be smaller or equal to max");
        }
        ArrayList<Integer> indexList = new ArrayList(max);
        for (int i = 0; i < max; i++) {
            indexList.add(Integer.valueOf(i));
        }
        Collections.shuffle(indexList);
        return indexList.subList(0, numIndexes);
    }
}
