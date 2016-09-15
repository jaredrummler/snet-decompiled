package com.google.android.gms.people.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.snet.Csv;
import java.util.ArrayList;

@VisibleForTesting
public class MultiIntArray {
    private final ArrayList<Object> mArray;

    public MultiIntArray() {
        this.mArray = new ArrayList();
    }

    public int size() {
        return this.mArray.size();
    }

    @VisibleForTesting
    public void addEmptyRow() {
        this.mArray.add(null);
    }

    @VisibleForTesting
    public void add(int value) {
        this.mArray.add(Integer.valueOf(value));
    }

    @VisibleForTesting
    public void appendToLastEntry(int value) {
        Preconditions.checkArgument(this.mArray.size() > 0);
        int lastIndex = this.mArray.size() - 1;
        Object last = this.mArray.get(lastIndex);
        if (last == null) {
            throw new IllegalStateException("Tried to append to an empty row");
        } else if (last instanceof Integer) {
            ArrayList<Integer> inArray = new ArrayList();
            inArray.add((Integer) last);
            inArray.add(Integer.valueOf(value));
            this.mArray.set(lastIndex, inArray);
        } else {
            ((ArrayList) last).add(Integer.valueOf(value));
        }
    }

    public void addAllValues(StringToIntsMap stim, String key) {
        int count = stim.getValueCount(key);
        if (count != 0) {
            if (count == 1) {
                this.mArray.add(Integer.valueOf(stim.getValue(key, 0)));
                return;
            }
            ArrayList<Integer> ar = new ArrayList(count);
            for (int i = 0; i < count; i++) {
                ar.add(Integer.valueOf(stim.getValue(key, i)));
            }
            this.mArray.add(ar);
        }
    }

    @VisibleForTesting
    public int getValueCount(int index) {
        Object stored = this.mArray.get(index);
        if (stored == null) {
            return 0;
        }
        if (stored instanceof Integer) {
            return 1;
        }
        return ((ArrayList) stored).size();
    }

    private static IndexOutOfBoundsException newIOOBException(int size, int requested) {
        return new IndexOutOfBoundsException(String.format("Size=%d, requested=%d", new Object[]{Integer.valueOf(size), Integer.valueOf(requested)}));
    }

    @VisibleForTesting
    public int get(int outerIndex, int innerIndex) {
        ArrayList<Integer> stored = this.mArray.get(outerIndex);
        if (stored == null) {
            throw newIOOBException(0, innerIndex);
        } else if (!(stored instanceof Integer)) {
            ArrayList<Integer> list = stored;
            if (innerIndex <= list.size()) {
                return ((Integer) list.get(innerIndex)).intValue();
            }
            throw newIOOBException(list.size(), innerIndex);
        } else if (innerIndex <= 0) {
            return ((Integer) stored).intValue();
        } else {
            throw newIOOBException(1, innerIndex);
        }
    }

    public String toString() {
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        for (int i = 0; i < size(); i++) {
            if (i > 0) {
                sb.append(Csv.COMMA);
            }
            sb.append("[");
            int count = getValueCount(i);
            for (int j = 0; j < count; j++) {
                if (j > 0) {
                    sb.append(Csv.COMMA);
                }
                sb.append(get(i, j));
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
