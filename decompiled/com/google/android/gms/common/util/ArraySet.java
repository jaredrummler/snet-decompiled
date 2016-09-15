package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

public class ArraySet<E> extends AbstractSet<E> {
    private final ArrayMap<E, E> mBackingMap;

    public ArraySet() {
        this.mBackingMap = new ArrayMap();
    }

    public ArraySet(int capacity) {
        this.mBackingMap = new ArrayMap(capacity);
    }

    public ArraySet(ArraySet<E> coll) {
        this.mBackingMap = new ArrayMap(coll.mBackingMap);
    }

    public ArraySet(Collection<E> coll) {
        this(coll.size());
        addAll((Collection) coll);
    }

    public boolean add(E object) {
        if (this.mBackingMap.containsKey(object)) {
            return false;
        }
        this.mBackingMap.put(object, object);
        return true;
    }

    public boolean addAll(ArraySet<? extends E> collection) {
        int oldSize = size();
        this.mBackingMap.putAll(collection.mBackingMap);
        return size() > oldSize;
    }

    public boolean addAll(Collection<? extends E> collection) {
        if (collection instanceof ArraySet) {
            return addAll((ArraySet) collection);
        }
        return super.addAll(collection);
    }

    public boolean remove(Object object) {
        if (!this.mBackingMap.containsKey(object)) {
            return false;
        }
        this.mBackingMap.remove(object);
        return true;
    }

    public void clear() {
        this.mBackingMap.clear();
    }

    public boolean contains(Object object) {
        return this.mBackingMap.containsKey(object);
    }

    public Iterator<E> iterator() {
        return this.mBackingMap.keySet().iterator();
    }

    public int size() {
        return this.mBackingMap.size();
    }

    public void ensureCapacity(int minimumCapacity) {
        this.mBackingMap.ensureCapacity(minimumCapacity);
    }
}
