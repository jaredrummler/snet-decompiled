package com.google.android.gms.people.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.snet.Csv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@VisibleForTesting
public class IntToStringsMap {
    private static final Integer[] EMPTY_INTEGERS;
    private final HashMap<Integer, Object> mMap;

    public IntToStringsMap() {
        this.mMap = new HashMap();
    }

    static {
        EMPTY_INTEGERS = new Integer[0];
    }

    @VisibleForTesting
    public void put(Integer key, String value) {
        Preconditions.checkNotNull(key);
        Object stored = this.mMap.get(key);
        if (stored == null) {
            this.mMap.put(key, value);
        } else if (stored instanceof String) {
            ArrayList<String> array = new ArrayList(4);
            array.add((String) stored);
            array.add(value);
            this.mMap.put(key, array);
        } else {
            ((ArrayList) stored).add(value);
        }
    }

    @VisibleForTesting
    public int getValueCount(int key) {
        Preconditions.checkNotNull(Integer.valueOf(key));
        Object stored = this.mMap.get(Integer.valueOf(key));
        if (stored == null) {
            return 0;
        }
        if (stored instanceof String) {
            return 1;
        }
        return ((ArrayList) stored).size();
    }

    @VisibleForTesting
    public String getValue(int key, int index) {
        Preconditions.checkNotNull(Integer.valueOf(key));
        Preconditions.checkArgument(index >= 0);
        ArrayList<String> stored = this.mMap.get(Integer.valueOf(key));
        if (stored == null) {
            throw new IndexOutOfBoundsException("Size=0, requested=" + index);
        } else if (!(stored instanceof String)) {
            ArrayList<String> list = stored;
            if (index <= list.size()) {
                return (String) list.get(index);
            }
            throw new IndexOutOfBoundsException("Size=" + list.size() + ", requested=" + index);
        } else if (index <= 0) {
            return (String) stored;
        } else {
            throw new IndexOutOfBoundsException("Size=1, requested=" + index);
        }
    }

    @VisibleForTesting
    public void clear() {
        this.mMap.clear();
    }

    @VisibleForTesting
    public int size() {
        return this.mMap.size();
    }

    @VisibleForTesting
    private Integer[] getSortedKeys() {
        Integer[] keys = (Integer[]) new ArrayList(this.mMap.keySet()).toArray(EMPTY_INTEGERS);
        Arrays.sort(keys);
        return keys;
    }

    @VisibleForTesting
    public void dumpForDebug(String logtag) {
        PeopleServiceLog.v(logtag, "Dumping IntToStringsMap...");
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        for (Integer key : getSortedKeys()) {
            sb.setLength(0);
            sb.append("key=" + key + "  ");
            int count = getValueCount(key.intValue());
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    sb.append(Csv.COMMA);
                }
                sb.append(getValue(key.intValue(), i));
            }
            PeopleServiceLog.v(logtag, sb.toString());
        }
    }

    public String toString() {
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        for (Integer key : getSortedKeys()) {
            if (sb.length() > 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(key);
            sb.append("=");
            int count = getValueCount(key.intValue());
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    sb.append(".");
                }
                sb.append(getValue(key.intValue(), i));
            }
            sb.append(BuildConfig.VERSION_NAME);
        }
        return sb.toString();
    }
}
