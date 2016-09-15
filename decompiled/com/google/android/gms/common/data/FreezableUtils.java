package com.google.android.gms.common.data;

import java.util.ArrayList;

public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> list) {
        ArrayList<T> frozen = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            frozen.add(((Freezable) list.get(i)).freeze());
        }
        return frozen;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] array) {
        ArrayList<T> frozen = new ArrayList(array.length);
        for (Freezable freeze : array) {
            frozen.add(freeze.freeze());
        }
        return frozen;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        ArrayList<T> events = new ArrayList();
        for (E item : iterable) {
            events.add(item.freeze());
        }
        return events;
    }
}
