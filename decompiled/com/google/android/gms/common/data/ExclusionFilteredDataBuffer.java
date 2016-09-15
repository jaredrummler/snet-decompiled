package com.google.android.gms.common.data;

import android.text.TextUtils;
import com.google.android.gms.common.data.DataBufferObserver.Observable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@VisibleForTesting
public final class ExclusionFilteredDataBuffer<T> extends FilteredDataBuffer<T> implements ExclusionFilterable, Observable {
    private AbstractDataBuffer<T> mAbstractDataBuffer;
    private final HashSet<Integer> mExcludedPositionSet;
    private DataBufferObserverSet mObserverSet;
    private final String mStringColumnName;

    public ExclusionFilteredDataBuffer(AbstractDataBuffer<T> dataBuffer, String stringColumnName) {
        super(dataBuffer);
        this.mExcludedPositionSet = new HashSet();
        this.mAbstractDataBuffer = dataBuffer;
        this.mStringColumnName = stringColumnName;
        this.mObserverSet = new DataBufferObserverSet();
    }

    public void addObserver(DataBufferObserver observer) {
        this.mObserverSet.addObserver(observer);
    }

    public void removeObserver(DataBufferObserver observer) {
        this.mObserverSet.removeObserver(observer);
    }

    public int getCount() {
        return this.mDataBuffer.getCount() - this.mExcludedPositionSet.size();
    }

    public int computeRealPosition(int position) {
        if (this.mExcludedPositionSet.isEmpty()) {
            return position;
        }
        if (position < 0 || position >= getCount()) {
            throw new IllegalArgumentException("Position " + position + " is out of bounds " + "for this buffer");
        }
        int nextExternalPosition = 0;
        int size = this.mDataBuffer.getCount();
        for (int i = 0; i < size; i++) {
            if (!this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                if (nextExternalPosition == position) {
                    return i;
                }
                nextExternalPosition++;
            }
        }
        return -1;
    }

    public void release() {
        super.release();
        this.mObserverSet.clear();
    }

    public void filterOut(String term) {
        if (!TextUtils.isEmpty(term)) {
            ArrayList<Integer> externalPositions = null;
            if (this.mObserverSet.hasObservers()) {
                externalPositions = new ArrayList();
            }
            ArrayList<Integer> positions = getPositions(term, externalPositions);
            if (this.mObserverSet.hasObservers()) {
                int currentRangeBase = 0;
                int currentRangeSize = 0;
                for (int i = positions.size() - 1; i >= 0; i--) {
                    int externalPosition = ((Integer) externalPositions.get(i)).intValue();
                    if (!isInactiveInfo(externalPosition)) {
                        this.mExcludedPositionSet.add(Integer.valueOf(((Integer) positions.get(i)).intValue()));
                        if (currentRangeSize == 0) {
                            currentRangeBase = externalPosition;
                            currentRangeSize = 1;
                        } else if (externalPosition == currentRangeBase - 1) {
                            currentRangeBase--;
                            currentRangeSize++;
                        } else {
                            this.mObserverSet.onDataRangeRemoved(currentRangeBase, currentRangeSize);
                            currentRangeBase = externalPosition;
                            currentRangeSize = 1;
                        }
                    }
                }
                if (currentRangeSize > 0) {
                    this.mObserverSet.onDataRangeRemoved(currentRangeBase, currentRangeSize);
                    return;
                }
                return;
            }
            this.mExcludedPositionSet.addAll(positions);
        }
    }

    public void unfilter(String term) {
        if (!TextUtils.isEmpty(term)) {
            ArrayList<Integer> externalPositions = null;
            if (this.mObserverSet.hasObservers()) {
                externalPositions = new ArrayList();
            }
            ArrayList<Integer> positions = getPositions(term, externalPositions);
            if (this.mObserverSet.hasObservers()) {
                int currentRangeBase = 0;
                int currentRangeSize = 0;
                for (int i = positions.size() - 1; i >= 0; i--) {
                    int externalPosition = ((Integer) externalPositions.get(i)).intValue();
                    if (isInactiveInfo(externalPosition)) {
                        this.mExcludedPositionSet.remove(Integer.valueOf(((Integer) positions.get(i)).intValue()));
                        int insertPos = getInsertPos(externalPosition);
                        if (currentRangeSize == 0) {
                            currentRangeBase = insertPos;
                            currentRangeSize = 1;
                        } else if (insertPos == currentRangeBase) {
                            currentRangeSize++;
                        } else {
                            this.mObserverSet.onDataRangeInserted(currentRangeBase, currentRangeSize);
                            currentRangeBase = insertPos;
                            currentRangeSize = 1;
                        }
                    }
                }
                if (currentRangeSize > 0) {
                    this.mObserverSet.onDataRangeInserted(currentRangeBase, currentRangeSize);
                    return;
                }
                return;
            }
            this.mExcludedPositionSet.removeAll(positions);
        }
    }

    public void clearFilters() {
        if (this.mObserverSet.hasObservers()) {
            int size = this.mExcludedPositionSet.size();
            Integer[] exclusionArray = (Integer[]) this.mExcludedPositionSet.toArray(new Integer[size]);
            Arrays.sort(exclusionArray);
            int currentRangeBase = 0;
            int currentRangeSize = 0;
            for (int i = 0; i < size; i++) {
                int externalPosition = exclusionArray[i].intValue();
                this.mExcludedPositionSet.remove(Integer.valueOf(externalPosition));
                if (currentRangeSize == 0) {
                    currentRangeBase = externalPosition;
                    currentRangeSize = 1;
                } else if (externalPosition == currentRangeBase + currentRangeSize) {
                    currentRangeSize++;
                } else {
                    this.mObserverSet.onDataRangeRemoved(currentRangeBase, currentRangeSize);
                    currentRangeBase = externalPosition;
                    currentRangeSize = 1;
                }
            }
            if (currentRangeSize > 0) {
                this.mObserverSet.onDataRangeRemoved(currentRangeBase, currentRangeSize);
                return;
            }
            return;
        }
        this.mExcludedPositionSet.clear();
    }

    private ArrayList<Integer> getPositions(String term, ArrayList<Integer> externalPositions) {
        ArrayList<Integer> positionList = new ArrayList();
        if (externalPositions != null) {
            externalPositions.clear();
        }
        DataHolder dataHolder = this.mAbstractDataBuffer.mDataHolder;
        String idColumn = this.mStringColumnName;
        boolean isEntityBuffer = this.mAbstractDataBuffer instanceof EntityBuffer;
        int nextExternalPosition = 0;
        int size = this.mAbstractDataBuffer.getCount();
        for (int i = 0; i < size; i++) {
            int internalPosition;
            if (isEntityBuffer) {
                internalPosition = ((EntityBuffer) this.mAbstractDataBuffer).getRowIndex(i);
            } else {
                internalPosition = i;
            }
            String value = dataHolder.getString(idColumn, internalPosition, dataHolder.getWindowIndex(internalPosition));
            int externalPosition = nextExternalPosition;
            if (externalPositions != null) {
                if (this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                    externalPosition = makeInactiveInfo(nextExternalPosition);
                } else {
                    nextExternalPosition++;
                }
            }
            if (!TextUtils.isEmpty(value) && value.equals(term)) {
                positionList.add(Integer.valueOf(i));
                if (externalPositions != null) {
                    externalPositions.add(Integer.valueOf(externalPosition));
                }
            }
        }
        return positionList;
    }

    private static int makeInactiveInfo(int insertPos) {
        return (-insertPos) - 1;
    }

    private static boolean isInactiveInfo(int value) {
        return value < 0;
    }

    private static int getInsertPos(int inactiveInfo) {
        return (-inactiveInfo) - 1;
    }
}
