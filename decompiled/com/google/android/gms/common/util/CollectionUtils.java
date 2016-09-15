package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CollectionUtils {
    private static final int ARRAY_MAP_THRESHOLD = 32;

    private CollectionUtils() {
    }

    public static <T> List<T> listOf() {
        return Collections.emptyList();
    }

    public static <T> List<T> listOf(T item) {
        return Collections.singletonList(item);
    }

    public static <T> List<T> listOf(T item1, T item2) {
        List<T> innerList = new ArrayList(2);
        innerList.add(item1);
        innerList.add(item2);
        return Collections.unmodifiableList(innerList);
    }

    public static <T> List<T> listOf(T... items) {
        switch (items.length) {
            case Action.UNKNOWN /*0*/:
                return listOf();
            case Type.TEMPORARY /*1*/:
                return listOf(items[0]);
            default:
                return Collections.unmodifiableList(Arrays.asList(items));
        }
    }

    public static <T> List<T> mutableListOf() {
        return new ArrayList();
    }

    public static <T> List<T> mutableListOf(T item) {
        ArrayList result = new ArrayList(1);
        result.add(item);
        return result;
    }

    public static <T> List<T> mutableListOf(T item1, T item2) {
        ArrayList result = new ArrayList(2);
        result.add(item1);
        result.add(item2);
        return result;
    }

    public static <T> List<T> mutableListOf(T... items) {
        return items.length == 0 ? mutableListOf() : new ArrayList(Arrays.asList(items));
    }

    public static <T> Set<T> setOf() {
        return Collections.emptySet();
    }

    public static <T> Set<T> setOf(T item) {
        return Collections.singleton(item);
    }

    public static <T> Set<T> setOf(T item1, T item2) {
        Set<T> innerSet = new ArraySet(2);
        innerSet.add(item1);
        innerSet.add(item2);
        return Collections.unmodifiableSet(innerSet);
    }

    public static <T> Set<T> setOf(T item1, T item2, T item3) {
        Set<T> innerSet = new ArraySet(3);
        innerSet.add(item1);
        innerSet.add(item2);
        innerSet.add(item3);
        return Collections.unmodifiableSet(innerSet);
    }

    public static <T> Set<T> setOf(T item1, T item2, T item3, T item4) {
        Set<T> innerSet = new ArraySet(4);
        innerSet.add(item1);
        innerSet.add(item2);
        innerSet.add(item3);
        innerSet.add(item4);
        return Collections.unmodifiableSet(innerSet);
    }

    public static <T> Set<T> setOf(T... items) {
        switch (items.length) {
            case Action.UNKNOWN /*0*/:
                return setOf();
            case Type.TEMPORARY /*1*/:
                return setOf(items[0]);
            case Type.INDEFINITELY /*2*/:
                return setOf(items[0], items[1]);
            case Type.CUSTOM /*3*/:
                return setOf(items[0], items[1], items[2]);
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                return setOf(items[0], items[1], items[2], items[3]);
            default:
                Set<T> innerSet;
                if (items.length <= ARRAY_MAP_THRESHOLD) {
                    innerSet = new ArraySet(Arrays.asList(items));
                } else {
                    innerSet = new HashSet(Arrays.asList(items));
                }
                return Collections.unmodifiableSet(innerSet);
        }
    }

    public static <T> Set<T> inOrderSetOf() {
        return setOf();
    }

    public static <T> Set<T> inOrderSetOf(T item) {
        return setOf((Object) item);
    }

    public static <T> Set<T> inOrderSetOf(T item1, T item2) {
        Set<T> innerSet = new LinkedHashSet(2, 1.0f);
        innerSet.add(item1);
        innerSet.add(item2);
        return Collections.unmodifiableSet(innerSet);
    }

    public static <T> Set<T> inOrderSetOf(T item1, T item2, T item3) {
        Set<T> innerSet = new LinkedHashSet(3, 1.0f);
        innerSet.add(item1);
        innerSet.add(item2);
        innerSet.add(item3);
        return Collections.unmodifiableSet(innerSet);
    }

    public static <T> Set<T> inOrderSetOf(T item1, T item2, T item3, T item4) {
        Set<T> innerSet = new LinkedHashSet(4, 1.0f);
        innerSet.add(item1);
        innerSet.add(item2);
        innerSet.add(item3);
        innerSet.add(item4);
        return Collections.unmodifiableSet(innerSet);
    }

    public static <T> Set<T> inOrderSetOf(T... items) {
        switch (items.length) {
            case Action.UNKNOWN /*0*/:
                return inOrderSetOf();
            case Type.TEMPORARY /*1*/:
                return inOrderSetOf(items[0]);
            case Type.INDEFINITELY /*2*/:
                return inOrderSetOf(items[0], items[1]);
            case Type.CUSTOM /*3*/:
                return inOrderSetOf(items[0], items[1], items[2]);
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                return inOrderSetOf(items[0], items[1], items[2], items[3]);
            default:
                return Collections.unmodifiableSet(new LinkedHashSet(Arrays.asList(items)));
        }
    }

    public static <T> Set<T> mutableSetOf() {
        return new ArraySet();
    }

    public static <T> Set<T> mutableSetOf(T item) {
        ArraySet result = new ArraySet(1);
        result.add(item);
        return result;
    }

    public static <T> Set<T> mutableSetOf(T item1, T item2) {
        ArraySet result = new ArraySet(2);
        result.add(item1);
        result.add(item2);
        return result;
    }

    public static <T> Set<T> mutableSetOf(T... items) {
        if (items.length == 0) {
            return mutableSetOf();
        }
        return items.length <= ARRAY_MAP_THRESHOLD ? new ArraySet(Arrays.asList(items)) : new HashSet(Arrays.asList(items));
    }

    public static <T> Set<T> mutableInOrderSetOf() {
        return new LinkedHashSet();
    }

    public static <T> Set<T> mutableInOrderSetOf(T item) {
        Set<T> result = new LinkedHashSet(1);
        result.add(item);
        return result;
    }

    public static <T> Set<T> mutableInOrderSetOf(T item1, T item2) {
        Set<T> result = new LinkedHashSet(2);
        result.add(item1);
        result.add(item2);
        return result;
    }

    public static <T> Set<T> mutableInOrderSetOf(T... items) {
        if (items.length == 0) {
            return mutableInOrderSetOf();
        }
        return new LinkedHashSet(Arrays.asList(items));
    }

    private static <K, V> void checkMapArrays(K[] keys, V[] values) {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("Key and values array lengths not equal: " + keys.length + " != " + values.length);
        }
    }

    public static <K, V> Map<K, V> mapOf() {
        return Collections.emptyMap();
    }

    public static <K, V> Map<K, V> mapOf(K key, V value) {
        return Collections.singletonMap(key, value);
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2) {
        Map<K, V> innerMap = new ArrayMap(2);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2, K key3, V value3) {
        Map<K, V> innerMap = new ArrayMap(3);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4) {
        Map<K, V> innerMap = new ArrayMap(4);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        innerMap.put(key4, value4);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5) {
        Map<K, V> innerMap = new ArrayMap(5);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        innerMap.put(key4, value4);
        innerMap.put(key5, value5);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6) {
        Map<K, V> innerMap = new ArrayMap(6);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        innerMap.put(key4, value4);
        innerMap.put(key5, value5);
        innerMap.put(key6, value6);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> mapOfKeyValueArrays(K[] keys, V[] values) {
        checkMapArrays(keys, values);
        int size = keys.length;
        switch (size) {
            case Action.UNKNOWN /*0*/:
                return mapOf();
            case Type.TEMPORARY /*1*/:
                return mapOf(keys[0], values[0]);
            default:
                Map<K, V> innerMap = size <= ARRAY_MAP_THRESHOLD ? new ArrayMap(size) : new HashMap(size, 1.0f);
                for (int i = 0; i < size; i++) {
                    innerMap.put(keys[i], values[i]);
                }
                return Collections.unmodifiableMap(innerMap);
        }
    }

    public static <K, V> Map<K, V> inOrderMapOf() {
        return mapOf();
    }

    public static <K, V> Map<K, V> inOrderMapOf(K key, V value) {
        return mapOf(key, value);
    }

    public static <K, V> Map<K, V> inOrderMapOf(K key1, V value1, K key2, V value2) {
        Map<K, V> innerMap = new LinkedHashMap(2, 1.0f);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> inOrderMapOf(K key1, V value1, K key2, V value2, K key3, V value3) {
        Map<K, V> innerMap = new LinkedHashMap(3, 1.0f);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> inOrderMapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4) {
        Map<K, V> innerMap = new LinkedHashMap(4, 1.0f);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        innerMap.put(key4, value4);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> inOrderMapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5) {
        Map<K, V> innerMap = new LinkedHashMap(5, 1.0f);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        innerMap.put(key4, value4);
        innerMap.put(key5, value5);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> inOrderMapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4, K key5, V value5, K key6, V value6) {
        Map<K, V> innerMap = new LinkedHashMap(6, 1.0f);
        innerMap.put(key1, value1);
        innerMap.put(key2, value2);
        innerMap.put(key3, value3);
        innerMap.put(key4, value4);
        innerMap.put(key5, value5);
        innerMap.put(key6, value6);
        return Collections.unmodifiableMap(innerMap);
    }

    public static <K, V> Map<K, V> inOrderMapOfKeyValueArrays(K[] keys, V[] values) {
        checkMapArrays(keys, values);
        int size = keys.length;
        switch (size) {
            case Action.UNKNOWN /*0*/:
                return inOrderMapOf();
            case Type.TEMPORARY /*1*/:
                return inOrderMapOf(keys[0], values[0]);
            default:
                Map<K, V> innerMap = new LinkedHashMap(size, 1.0f);
                for (int i = 0; i < size; i++) {
                    innerMap.put(keys[i], values[i]);
                }
                return Collections.unmodifiableMap(innerMap);
        }
    }

    public static boolean isEmpty(@Nullable Collection collection) {
        return collection == null ? true : collection.isEmpty();
    }
}
