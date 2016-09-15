package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import com.google.android.snet.Csv;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@VisibleForTesting
public final class ArrayUtils {

    private static class CountingMap {
        HashMap<Object, Counter> mMap;

        static class Counter {
            int count;

            Counter() {
            }
        }

        CountingMap(int capacity) {
            this.mMap = new HashMap(capacity);
        }

        void increment(Object object) {
            Counter counter = getCounter(object);
            counter.count++;
        }

        void decrement(Object object) {
            Counter counter = getCounter(object);
            counter.count--;
        }

        private Counter getCounter(Object object) {
            Counter counter = (Counter) this.mMap.get(object);
            if (counter != null) {
                return counter;
            }
            counter = new Counter();
            this.mMap.put(object, counter);
            return counter;
        }

        boolean checkAllZero() {
            for (Counter counter : this.mMap.values()) {
                if (counter.count != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static <T> int indexOf(T[] array, T searchValue) {
        int arrayLength = array != null ? array.length : 0;
        for (int i = 0; i < arrayLength; i++) {
            if (Objects.equal(array[i], searchValue)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> boolean contains(T[] array, T searchValue) {
        return indexOf(array, searchValue) >= 0;
    }

    public static boolean contains(byte[] array, byte value) {
        if (array == null) {
            return false;
        }
        for (byte b : array) {
            if (b == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(boolean[] array, boolean value) {
        if (array == null) {
            return false;
        }
        for (boolean b : array) {
            if (b == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(char[] array, char value) {
        if (array == null) {
            return false;
        }
        for (char c : array) {
            if (c == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(short[] array, short value) {
        if (array == null) {
            return false;
        }
        for (short s : array) {
            if (s == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int[] array, int value) {
        if (array == null) {
            return false;
        }
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(double[] array, double value) {
        if (array == null) {
            return false;
        }
        for (double d : array) {
            if (d == value) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(float[] array, float value, float delta) {
        if (array == null) {
            return false;
        }
        float min = value - delta;
        float max = value + delta;
        for (float f : array) {
            if (min <= f && f <= max) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsIgnoreCase(String[] array, String value) {
        if (array == null) {
            return false;
        }
        for (String s : array) {
            if (s == value) {
                return true;
            }
            if (s != null && s.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equalsAnyOrder(Object[] first, Object[] second) {
        if (first == second) {
            return true;
        }
        int firstLength = first == null ? 0 : first.length;
        int secondLength = second == null ? 0 : second.length;
        if (firstLength == 0 && secondLength == 0) {
            return true;
        }
        if (firstLength != secondLength) {
            return false;
        }
        CountingMap countingMap = new CountingMap(firstLength);
        for (Object o : first) {
            countingMap.increment(o);
        }
        for (Object o2 : second) {
            countingMap.decrement(o2);
        }
        return countingMap.checkAllZero();
    }

    public static Integer[] toWrapperArray(int[] array) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        Integer[] newArray = new Integer[length];
        for (int i = 0; i < length; i++) {
            newArray[i] = Integer.valueOf(array[i]);
        }
        return newArray;
    }

    private ArrayUtils() {
    }

    public static <T> void writeArray(StringBuilder sb, T[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(value[i].toString());
        }
    }

    public static void writeArray(StringBuilder sb, int[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(Integer.toString(value[i]));
        }
    }

    public static void writeArray(StringBuilder sb, long[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(Long.toString(value[i]));
        }
    }

    public static void writeArray(StringBuilder sb, float[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(Float.toString(value[i]));
        }
    }

    public static void writeArray(StringBuilder sb, double[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(Double.toString(value[i]));
        }
    }

    public static void writeArray(StringBuilder sb, boolean[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(Boolean.toString(value[i]));
        }
    }

    public static void writeStringArray(StringBuilder sb, String[] value) {
        int length = value.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            sb.append("\"").append(value[i]).append("\"");
        }
    }

    public static <T> T[] concat(T[]... arrays) {
        if (arrays.length == 0) {
            return (Object[]) Array.newInstance(arrays.getClass(), 0);
        }
        int i;
        int totalSize = 0;
        for (T[] length : arrays) {
            totalSize += length.length;
        }
        T[] result = Arrays.copyOf(arrays[0], totalSize);
        int pos = arrays[0].length;
        for (i = 1; i < arrays.length; i++) {
            T[] current = arrays[i];
            System.arraycopy(current, 0, result, pos, current.length);
            pos += current.length;
        }
        return result;
    }

    public static byte[] concatByteArrays(byte[]... arrays) {
        if (arrays.length == 0) {
            return new byte[0];
        }
        int i;
        int totalSize = 0;
        for (byte[] length : arrays) {
            totalSize += length.length;
        }
        byte[] result = Arrays.copyOf(arrays[0], totalSize);
        int pos = arrays[0].length;
        for (i = 1; i < arrays.length; i++) {
            byte[] current = arrays[i];
            System.arraycopy(current, 0, result, pos, current.length);
            pos += current.length;
        }
        return result;
    }

    public static <T> T[] appendToArray(T[] from, T itemToAppend) {
        if (from == null && itemToAppend == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        }
        T[] result;
        if (from == null) {
            result = (Object[]) Array.newInstance(itemToAppend.getClass(), 1);
        } else {
            result = Arrays.copyOf(from, from.length + 1);
        }
        result[result.length - 1] = itemToAppend;
        return result;
    }

    public static int[] appendToArray(int[] from, int itemToAppend) {
        int[] result;
        if (from == null || from.length == 0) {
            result = new int[1];
        } else {
            result = Arrays.copyOf(from, from.length + 1);
        }
        result[result.length - 1] = itemToAppend;
        return result;
    }

    public static <T> T[] removeAll(T[] array, T... toBeRemoved) {
        if (array == null) {
            return null;
        }
        if (toBeRemoved == null || toBeRemoved.length == 0) {
            return Arrays.copyOf(array, array.length);
        }
        int resultArrayLength;
        Object[] copy = (Object[]) ((Object[]) Array.newInstance(toBeRemoved.getClass().getComponentType(), array.length));
        T[] arr$;
        int len$;
        int i$;
        int resultArrayLength2;
        if (toBeRemoved.length == 1) {
            arr$ = array;
            len$ = arr$.length;
            i$ = 0;
            resultArrayLength2 = 0;
            while (i$ < len$) {
                T originalVal = arr$[i$];
                if (Objects.equal(toBeRemoved[0], originalVal)) {
                    resultArrayLength = resultArrayLength2;
                } else {
                    resultArrayLength = resultArrayLength2 + 1;
                    copy[resultArrayLength2] = originalVal;
                }
                i$++;
                resultArrayLength2 = resultArrayLength;
            }
            resultArrayLength = resultArrayLength2;
        } else {
            arr$ = array;
            len$ = arr$.length;
            i$ = 0;
            resultArrayLength2 = 0;
            while (i$ < len$) {
                Object originalVal2 = arr$[i$];
                if (contains((Object[]) toBeRemoved, originalVal2)) {
                    resultArrayLength = resultArrayLength2;
                } else {
                    resultArrayLength = resultArrayLength2 + 1;
                    copy[resultArrayLength2] = originalVal2;
                }
                i$++;
                resultArrayLength2 = resultArrayLength;
            }
            resultArrayLength = resultArrayLength2;
        }
        return resize(copy, resultArrayLength);
    }

    public static int[] removeAll(int[] array, int... toBeRemoved) {
        if (array == null) {
            return null;
        }
        if (toBeRemoved == null || toBeRemoved.length == 0) {
            return Arrays.copyOf(array, array.length);
        }
        int resultArrayLength;
        int[] copy = new int[array.length];
        int[] arr$;
        int len$;
        int i$;
        int resultArrayLength2;
        int originalVal;
        if (toBeRemoved.length == 1) {
            arr$ = array;
            len$ = arr$.length;
            i$ = 0;
            resultArrayLength2 = 0;
            while (i$ < len$) {
                originalVal = arr$[i$];
                if (toBeRemoved[0] != originalVal) {
                    resultArrayLength = resultArrayLength2 + 1;
                    copy[resultArrayLength2] = originalVal;
                } else {
                    resultArrayLength = resultArrayLength2;
                }
                i$++;
                resultArrayLength2 = resultArrayLength;
            }
            resultArrayLength = resultArrayLength2;
        } else {
            arr$ = array;
            len$ = arr$.length;
            i$ = 0;
            resultArrayLength2 = 0;
            while (i$ < len$) {
                originalVal = arr$[i$];
                if (contains(toBeRemoved, originalVal)) {
                    resultArrayLength = resultArrayLength2;
                } else {
                    resultArrayLength = resultArrayLength2 + 1;
                    copy[resultArrayLength2] = originalVal;
                }
                i$++;
                resultArrayLength2 = resultArrayLength;
            }
            resultArrayLength = resultArrayLength2;
        }
        return resize(copy, resultArrayLength);
    }

    public static <T> T[] resize(T[] elements, int size) {
        if (elements == null) {
            return null;
        }
        T[] result = elements;
        if (size != elements.length) {
            return Arrays.copyOf(elements, size);
        }
        return result;
    }

    public static int[] resize(int[] elements, int size) {
        if (elements == null) {
            return null;
        }
        int[] result = elements;
        if (size != elements.length) {
            return Arrays.copyOf(elements, size);
        }
        return result;
    }

    public static <T> int rearrange(T[] elements, Predicate<T> rearrangePredicate) {
        if (elements == null || elements.length == 0) {
            return 0;
        }
        int currentNonMatchingIndex = 0;
        int length = elements.length;
        for (int i = 0; i < length; i++) {
            if (rearrangePredicate.apply(elements[i])) {
                if (currentNonMatchingIndex != i) {
                    T holder = elements[currentNonMatchingIndex];
                    elements[currentNonMatchingIndex] = elements[i];
                    elements[i] = holder;
                }
                currentNonMatchingIndex++;
            }
        }
        return currentNonMatchingIndex;
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList();
    }

    public static <T> ArrayList<T> toArrayList(T[] src) {
        ArrayList<T> ret = new ArrayList(length);
        for (Object add : src) {
            ret.add(add);
        }
        return ret;
    }

    public static <T> ArrayList<T> toArrayList(Collection<T> collection) {
        return collection == null ? null : new ArrayList(collection);
    }

    public static int[] toPrimitiveArray(Collection<Integer> integerCollection) {
        if (integerCollection == null || integerCollection.size() == 0) {
            return new int[0];
        }
        int i = 0;
        int[] intArray = new int[integerCollection.size()];
        for (Integer intVal : integerCollection) {
            int i2 = i + 1;
            intArray[i] = intVal.intValue();
            i = i2;
        }
        return intArray;
    }

    public static int[] toPrimitiveArray(Integer[] array) {
        if (array == null) {
            return new int[0];
        }
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = array[i].intValue();
        }
        return intArray;
    }

    public static long[] toLongArray(Collection<Long> collection) {
        if (collection == null || collection.size() == 0) {
            return new long[0];
        }
        int i = 0;
        long[] longArray = new long[collection.size()];
        for (Long longVal : collection) {
            int i2 = i + 1;
            longArray[i] = longVal.longValue();
            i = i2;
        }
        return longArray;
    }

    public static long[] toLongArray(Long[] array) {
        if (array == null) {
            return new long[0];
        }
        long[] longArray = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            longArray[i] = array[i].longValue();
        }
        return longArray;
    }

    public static String[] toStringArray(Collection<String> stringCollection) {
        if (stringCollection == null || stringCollection.size() == 0) {
            return new String[0];
        }
        return (String[]) stringCollection.toArray(new String[stringCollection.size()]);
    }
}
