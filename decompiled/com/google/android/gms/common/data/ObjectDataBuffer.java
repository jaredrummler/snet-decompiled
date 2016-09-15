package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBufferObserver.Observable;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public final class ObjectDataBuffer<T> extends AbstractDataBuffer<T> implements ObjectExclusionFilterable<T>, Observable {
    private final ArrayList<Integer> mEntityOffsets;
    private final HashSet<Integer> mExcludedPositionSet;
    private final ArrayList<T> mObjectList;
    private DataBufferObserverSet mObserverSet;

    public ObjectDataBuffer() {
        super(null);
        this.mExcludedPositionSet = new HashSet();
        this.mEntityOffsets = new ArrayList();
        this.mObjectList = new ArrayList();
        this.mObserverSet = new DataBufferObserverSet();
        filterEntities();
    }

    public ObjectDataBuffer(T... objects) {
        super(null);
        this.mExcludedPositionSet = new HashSet();
        this.mEntityOffsets = new ArrayList();
        this.mObjectList = new ArrayList(Arrays.asList(objects));
        this.mObserverSet = new DataBufferObserverSet();
        filterEntities();
    }

    public ObjectDataBuffer(ArrayList<T> objectList) {
        super(null);
        this.mExcludedPositionSet = new HashSet();
        this.mEntityOffsets = new ArrayList();
        this.mObjectList = objectList;
        this.mObserverSet = new DataBufferObserverSet();
        filterEntities();
    }

    public void addObserver(DataBufferObserver observer) {
        this.mObserverSet.addObserver(observer);
    }

    public void removeObserver(DataBufferObserver observer) {
        this.mObserverSet.removeObserver(observer);
    }

    public void add(T object) {
        boolean z = false;
        int index = this.mObjectList.size();
        this.mObjectList.add(object);
        filterEntities();
        if (this.mObserverSet.hasObservers()) {
            boolean z2;
            Asserts.checkState(!this.mExcludedPositionSet.contains(Integer.valueOf(index)));
            int entitySize = this.mEntityOffsets.size();
            if (entitySize > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            Asserts.checkState(z2);
            if (((Integer) this.mEntityOffsets.get(entitySize - 1)).intValue() == index) {
                z = true;
            }
            Asserts.checkState(z);
            this.mObserverSet.onDataRangeInserted(entitySize - 1, 1);
        }
    }

    public void insertRaw(int index, T object) {
        this.mObjectList.add(index, object);
        int externalIndex = index;
        HashSet<Integer> toAdd = new HashSet(this.mExcludedPositionSet.size());
        Iterator<Integer> iter = this.mExcludedPositionSet.iterator();
        while (iter.hasNext()) {
            Integer i = (Integer) iter.next();
            if (i.intValue() < index) {
                externalIndex--;
            } else {
                toAdd.add(Integer.valueOf(i.intValue() + 1));
                iter.remove();
            }
        }
        Iterator i$ = toAdd.iterator();
        while (i$.hasNext()) {
            this.mExcludedPositionSet.add((Integer) i$.next());
        }
        filterEntities();
        if (this.mObserverSet.hasObservers()) {
            this.mObserverSet.onDataRangeInserted(externalIndex, 1);
        }
    }

    public boolean setRaw(int index, T object) {
        this.mObjectList.set(index, object);
        boolean unfiltered = !this.mExcludedPositionSet.contains(Integer.valueOf(index));
        if (unfiltered && this.mObserverSet.hasObservers()) {
            int size = this.mEntityOffsets.size();
            for (int i = 0; i < size; i++) {
                if (((Integer) this.mEntityOffsets.get(i)).intValue() == index) {
                    this.mObserverSet.onDataRangeChanged(i, 1);
                    break;
                }
            }
        }
        return unfiltered;
    }

    public int getCount() {
        return this.mObjectList.size() - this.mExcludedPositionSet.size();
    }

    public T get(int position) {
        return this.mObjectList.get(getRawPosition(position));
    }

    public int getRawCount() {
        return this.mObjectList.size();
    }

    public T getRaw(int index) {
        return this.mObjectList.get(index);
    }

    public int getRawPosition(int position) {
        if (position >= 0 && position < getCount()) {
            return ((Integer) this.mEntityOffsets.get(position)).intValue();
        }
        throw new IllegalArgumentException("Position " + position + " is out of bounds " + "for this buffer");
    }

    public int getPositionFromRawPosition(int rawPosition) {
        int result = -1;
        for (int i = 0; i <= rawPosition; i++) {
            if (!this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                result++;
            }
        }
        return result;
    }

    public boolean isRawPositionFiltered(int rawPosition) {
        return this.mExcludedPositionSet.contains(Integer.valueOf(rawPosition));
    }

    public void release() {
        this.mObserverSet.clear();
    }

    public Bundle getMetadata() {
        return null;
    }

    public void removeRaw(int index) {
        this.mObjectList.remove(index);
        boolean wasFiltered = this.mExcludedPositionSet.remove(Integer.valueOf(index));
        int externalIndex = index;
        HashSet<Integer> toAdd = new HashSet(this.mExcludedPositionSet.size());
        Iterator<Integer> iter = this.mExcludedPositionSet.iterator();
        while (iter.hasNext()) {
            Integer i = (Integer) iter.next();
            if (i.intValue() < index) {
                externalIndex--;
            } else {
                iter.remove();
                toAdd.add(Integer.valueOf(i.intValue() - 1));
            }
        }
        Iterator i$ = toAdd.iterator();
        while (i$.hasNext()) {
            this.mExcludedPositionSet.add((Integer) i$.next());
        }
        filterEntities();
        if (!wasFiltered && this.mObserverSet.hasObservers()) {
            this.mObserverSet.onDataRangeRemoved(externalIndex, 1);
        }
    }

    public void filterOut(T item) {
        int externalIndex = -1;
        boolean refilter = false;
        int currentBase = -1;
        int currentCount = -1;
        int size = this.mObjectList.size();
        for (int i = 0; i < size; i++) {
            if (!this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                externalIndex++;
                if (item.equals(this.mObjectList.get(i))) {
                    this.mExcludedPositionSet.add(Integer.valueOf(i));
                    refilter = true;
                    if (this.mObserverSet.hasObservers()) {
                        if (currentBase < 0) {
                            currentBase = externalIndex;
                            currentCount = 1;
                        } else {
                            currentCount++;
                        }
                    }
                } else if (currentBase >= 0) {
                    filterEntities();
                    refilter = false;
                    this.mObserverSet.onDataRangeRemoved(currentBase, currentCount);
                    externalIndex -= currentCount;
                    currentBase = -1;
                    currentCount = -1;
                }
            }
        }
        if (refilter) {
            filterEntities();
        }
        if (currentBase >= 0) {
            this.mObserverSet.onDataRangeRemoved(currentBase, currentCount);
        }
    }

    public void filterOutRaw(int index) {
        boolean itemRemoved = this.mExcludedPositionSet.add(Integer.valueOf(index));
        int externalIndex = -1;
        if (this.mObserverSet.hasObservers() && itemRemoved) {
            int size = this.mEntityOffsets.size();
            for (int i = 0; i < size; i++) {
                if (((Integer) this.mEntityOffsets.get(i)).intValue() == index) {
                    externalIndex = i;
                    break;
                }
            }
        }
        filterEntities();
        if (externalIndex >= 0) {
            this.mObserverSet.onDataRangeRemoved(externalIndex, 1);
        }
    }

    public void unfilter(T item) {
        int externalInsertIndex = 0;
        boolean refilter = false;
        int currentBase = -1;
        int currentCount = -1;
        int size = this.mObjectList.size();
        for (int i = 0; i < size; i++) {
            if (!this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                externalInsertIndex++;
                if (currentBase >= 0) {
                    filterEntities();
                    refilter = false;
                    this.mObserverSet.onDataRangeInserted(currentBase, currentCount);
                    externalInsertIndex += currentCount;
                    currentBase = -1;
                    currentCount = -1;
                }
            } else if (item.equals(this.mObjectList.get(i))) {
                this.mExcludedPositionSet.remove(Integer.valueOf(i));
                refilter = true;
                if (this.mObserverSet.hasObservers()) {
                    if (currentBase < 0) {
                        currentBase = externalInsertIndex;
                        currentCount = 1;
                    } else {
                        currentCount++;
                    }
                }
            } else if (this.mObserverSet.hasObservers() && currentBase >= 0) {
                filterEntities();
                refilter = false;
                this.mObserverSet.onDataRangeInserted(currentBase, currentCount);
                externalInsertIndex += currentCount;
                currentBase = -1;
                currentCount = -1;
            }
        }
        if (refilter) {
            filterEntities();
        }
        if (currentBase >= 0) {
            this.mObserverSet.onDataRangeInserted(currentBase, currentCount);
        }
    }

    public void unfilterRaw(int index) {
        boolean itemAdded = this.mExcludedPositionSet.remove(Integer.valueOf(index));
        filterEntities();
        if (this.mObserverSet.hasObservers() && itemAdded) {
            int externalIndex = -1;
            int size = this.mEntityOffsets.size();
            for (int i = 0; i < size; i++) {
                if (((Integer) this.mEntityOffsets.get(i)).intValue() == index) {
                    externalIndex = i;
                    break;
                }
            }
            if (externalIndex >= 0) {
                this.mObserverSet.onDataRangeInserted(externalIndex, 1);
            }
        }
    }

    public void notifyChanged(T item) {
        if (this.mObserverSet.hasObservers()) {
            int size = this.mEntityOffsets.size();
            for (int i = 0; i < size; i++) {
                if (item.equals(this.mObjectList.get(((Integer) this.mEntityOffsets.get(i)).intValue()))) {
                    this.mObserverSet.onDataRangeChanged(i, 1);
                }
            }
        }
    }

    private void filterEntities() {
        this.mEntityOffsets.clear();
        int size = this.mObjectList.size();
        for (int i = 0; i < size; i++) {
            if (!this.mExcludedPositionSet.contains(Integer.valueOf(i))) {
                this.mEntityOffsets.add(Integer.valueOf(i));
            }
        }
    }
}
