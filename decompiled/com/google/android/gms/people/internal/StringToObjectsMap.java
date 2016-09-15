package com.google.android.gms.people.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.snet.Csv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class StringToObjectsMap<T> {
    private final HashMap<String, Object> mMap;

    public StringToObjectsMap() {
        this.mMap = new HashMap();
    }

    public void put(String key, T value) {
        Preconditions.checkNotNull(key);
        Object stored = this.mMap.get(key);
        if (stored == null) {
            this.mMap.put(key, value);
        } else if (stored instanceof ArrayList) {
            ((ArrayList) stored).add(value);
        } else {
            ArrayList<T> array = new ArrayList(4);
            array.add(stored);
            array.add(value);
            this.mMap.put(key, array);
        }
    }

    public int getValueCount(String key) {
        Preconditions.checkNotNull(key);
        Object stored = this.mMap.get(key);
        if (stored == null) {
            return 0;
        }
        if (stored instanceof ArrayList) {
            return ((ArrayList) stored).size();
        }
        return 1;
    }

    public T getValue(String key, int index) {
        Preconditions.checkNotNull(key);
        Preconditions.checkArgument(index >= 0);
        ArrayList<T> stored = this.mMap.get(key);
        if (stored == null) {
            throw new IndexOutOfBoundsException("Size=0, requested=" + index);
        } else if (stored instanceof ArrayList) {
            ArrayList<T> list = stored;
            if (index <= list.size()) {
                return list.get(index);
            }
            throw new IndexOutOfBoundsException("Size=" + list.size() + ", requested=" + index);
        } else if (index <= 0) {
            return stored;
        } else {
            throw new IndexOutOfBoundsException("Size=1, requested=" + index);
        }
    }

    public void clear() {
        this.mMap.clear();
    }

    public int size() {
        return this.mMap.size();
    }

    public Set<String> getKeys() {
        return this.mMap.keySet();
    }

    private String[] getSortedKeys() {
        String[] keys = (String[]) new ArrayList(this.mMap.keySet()).toArray(PeopleUtils.EMPTY_STRINGS);
        Arrays.sort(keys);
        return keys;
    }

    public void dumpForDebug(String logtag) {
        PeopleServiceLog.v(logtag, "Dumping " + this);
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        for (String key : getSortedKeys()) {
            sb.setLength(0);
            sb.append("key=" + key + "  ");
            int count = getValueCount(key);
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    sb.append(Csv.COMMA);
                }
                sb.append(getValue(key, i));
            }
            PeopleServiceLog.v(logtag, sb.toString());
        }
    }

    public String toString() {
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        for (String key : getSortedKeys()) {
            if (sb.length() > 0) {
                sb.append(Csv.COMMA);
            }
            sb.append(key);
            sb.append("=");
            int count = getValueCount(key);
            for (int i = 0; i < count; i++) {
                if (i > 0) {
                    sb.append(".");
                }
                sb.append(getValue(key, i));
            }
            sb.append(BuildConfig.VERSION_NAME);
        }
        return sb.toString();
    }
}
