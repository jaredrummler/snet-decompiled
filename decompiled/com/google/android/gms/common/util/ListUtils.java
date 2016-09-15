package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ListUtils {
    public static <T> ArrayList<T> copyAndRemoveElementFromListIfPresent(List<T> elements, T elementToRemove) {
        int size = elements.size();
        ArrayList<T> elementsCopy = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            T next = elements.get(i);
            if (elementToRemove == null || !elementToRemove.equals(next)) {
                elementsCopy.add(next);
            }
        }
        return elementsCopy;
    }

    public static <T> ArrayList<T> copyAndRemoveElementsFromListIfPresent(List<T> elements, Collection<T> elementsToRemove) {
        Preconditions.checkNotNull(elementsToRemove);
        ArrayList<T> elementsCopy = new ArrayList(elements.size());
        for (T next : elements) {
            if (!elementsToRemove.contains(next)) {
                elementsCopy.add(next);
            }
        }
        return elementsCopy;
    }

    private ListUtils() {
    }
}
