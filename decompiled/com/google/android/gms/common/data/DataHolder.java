package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.AbstractWindowedCursor;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

@KeepName
@Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder implements SafeParcelable {
    public static final DataHolderCreator CREATOR;
    private static final boolean DEBUG = false;
    private static final Builder EMPTY;
    private static final String TAG = "DataHolder";
    private static final int VERSION_CODE = 1;
    boolean mClosed;
    Bundle mColumnBundle;
    @Field(getter = "getColumns", id = 1)
    private final String[] mColumns;
    private boolean mIsLeakDetectionEnabled;
    private Object mLeakIdentifier;
    @Field(getter = "getMetadata", id = 4)
    private final Bundle mMetadata;
    int mRowCount;
    @Field(getter = "getStatusCode", id = 3)
    private final int mStatusCode;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;
    int[] mWindowRowOffsets;
    @Field(getter = "getWindows", id = 2)
    private final CursorWindow[] mWindows;

    public static class Builder {
        private final String[] mColumns;
        private boolean mIsSorted;
        private final ArrayList<HashMap<String, Object>> mRows;
        private String mSortedColumn;
        private final String mUniqueColumn;
        private final HashMap<Object, Integer> mUniqueColumnIndices;

        private Builder(String[] columns, String uniqueColumn) {
            this.mColumns = (String[]) Preconditions.checkNotNull(columns);
            this.mRows = new ArrayList();
            this.mUniqueColumn = uniqueColumn;
            this.mUniqueColumnIndices = new HashMap();
            this.mIsSorted = DataHolder.DEBUG;
            this.mSortedColumn = null;
        }

        public Builder withRow(HashMap<String, Object> row) {
            Asserts.checkNotNull(row);
            int previousIndex = markUniqueRow(row);
            if (previousIndex == -1) {
                this.mRows.add(row);
            } else {
                this.mRows.remove(previousIndex);
                this.mRows.add(previousIndex, row);
            }
            this.mIsSorted = DataHolder.DEBUG;
            return this;
        }

        private int markUniqueRow(HashMap<String, Object> row) {
            if (this.mUniqueColumn == null) {
                return -1;
            }
            Object value = row.get(this.mUniqueColumn);
            if (value == null) {
                return -1;
            }
            Integer previousIndex = (Integer) this.mUniqueColumnIndices.get(value);
            if (previousIndex != null) {
                return previousIndex.intValue();
            }
            this.mUniqueColumnIndices.put(value, Integer.valueOf(this.mRows.size()));
            return -1;
        }

        public void modifyUniqueRowValue(Object uniqueValue, String columnName, Object newValue) {
            if (this.mUniqueColumn != null) {
                Integer rowIndex = (Integer) this.mUniqueColumnIndices.get(uniqueValue);
                if (rowIndex != null) {
                    ((HashMap) this.mRows.get(rowIndex.intValue())).put(columnName, newValue);
                }
            }
        }

        public Builder withRow(ContentValues values) {
            Asserts.checkNotNull(values);
            HashMap rowData = new HashMap(values.size());
            for (Entry<String, Object> entry : values.valueSet()) {
                rowData.put(entry.getKey(), entry.getValue());
            }
            return withRow(rowData);
        }

        public boolean containsRowWithValue(String column, Object value) {
            int numRows = this.mRows.size();
            for (int i = 0; i < numRows; i += DataHolder.VERSION_CODE) {
                if (Objects.equal(((HashMap) this.mRows.get(i)).get(column), value)) {
                    return true;
                }
            }
            return DataHolder.DEBUG;
        }

        public Builder removeRowsWithValue(String column, Object value) {
            for (int i = this.mRows.size() - 1; i >= 0; i--) {
                if (Objects.equal(((HashMap) this.mRows.get(i)).get(column), value)) {
                    this.mRows.remove(i);
                }
            }
            return this;
        }

        public Builder sort(String sortColumn) {
            if (!isSortedOnColumn(sortColumn)) {
                Collections.sort(this.mRows, new ColumnValueComparator(sortColumn));
                retrackUniqueColumn();
                this.mIsSorted = true;
                this.mSortedColumn = sortColumn;
            }
            return this;
        }

        public Builder descendingSort(String sortColumn) {
            if (!isSortedOnColumn(sortColumn)) {
                sort(sortColumn);
                Collections.reverse(this.mRows);
            }
            return this;
        }

        private boolean isSortedOnColumn(String sortColumn) {
            Asserts.checkNotNull(sortColumn);
            if (this.mIsSorted && sortColumn.equals(this.mSortedColumn)) {
                return true;
            }
            return DataHolder.DEBUG;
        }

        private void retrackUniqueColumn() {
            if (this.mUniqueColumn != null) {
                this.mUniqueColumnIndices.clear();
                int numRows = this.mRows.size();
                for (int i = 0; i < numRows; i += DataHolder.VERSION_CODE) {
                    Object value = ((HashMap) this.mRows.get(i)).get(this.mUniqueColumn);
                    if (value != null) {
                        this.mUniqueColumnIndices.put(value, Integer.valueOf(i));
                    }
                }
            }
        }

        public int getCount() {
            return this.mRows.size();
        }

        public DataHolder build(int statusCode) {
            return new DataHolder(statusCode, null, null);
        }

        public DataHolder build(int statusCode, Bundle metadata) {
            return new DataHolder(statusCode, metadata, -1, null);
        }

        public DataHolder build(int statusCode, Bundle metadata, int maxResults) {
            return new DataHolder(statusCode, metadata, maxResults, null);
        }
    }

    /* renamed from: com.google.android.gms.common.data.DataHolder.1 */
    static class AnonymousClass1 extends Builder {
        AnonymousClass1(String[] x0, String x1) {
            super(x1, null);
        }

        public Builder withRow(HashMap<String, Object> hashMap) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }

        public Builder withRow(ContentValues values) {
            throw new UnsupportedOperationException("Cannot add data to empty builder");
        }
    }

    private static final class ColumnValueComparator implements Comparator<HashMap<String, Object>> {
        private final String mSortColumn;

        ColumnValueComparator(String sortColumn) {
            this.mSortColumn = (String) Preconditions.checkNotNull(sortColumn);
        }

        public int compare(HashMap<String, Object> left, HashMap<String, Object> right) {
            Object lValue = Preconditions.checkNotNull(left.get(this.mSortColumn));
            Object rValue = Preconditions.checkNotNull(right.get(this.mSortColumn));
            if (lValue.equals(rValue)) {
                return 0;
            }
            if (lValue instanceof Boolean) {
                return ((Boolean) lValue).compareTo((Boolean) rValue);
            }
            if (lValue instanceof Long) {
                return ((Long) lValue).compareTo((Long) rValue);
            }
            if (lValue instanceof Integer) {
                return ((Integer) lValue).compareTo((Integer) rValue);
            }
            if (lValue instanceof String) {
                return ((String) lValue).compareTo((String) rValue);
            }
            if (lValue instanceof Double) {
                return ((Double) lValue).compareTo((Double) rValue);
            }
            if (lValue instanceof Float) {
                return ((Float) lValue).compareTo((Float) rValue);
            }
            throw new IllegalArgumentException("Unknown type for lValue " + lValue);
        }
    }

    public static class DataHolderException extends RuntimeException {
        public DataHolderException(String msg) {
            super(msg);
        }
    }

    static {
        CREATOR = new DataHolderCreator();
        EMPTY = new AnonymousClass1(new String[0], null);
    }

    @Constructor
    DataHolder(@Param(id = 1000) int versionCode, @Param(id = 1) String[] columns, @Param(id = 2) CursorWindow[] windows, @Param(id = 3) int statusCode, @Param(id = 4) Bundle metadata) {
        this.mClosed = DEBUG;
        this.mIsLeakDetectionEnabled = true;
        this.mVersionCode = versionCode;
        this.mColumns = columns;
        this.mWindows = windows;
        this.mStatusCode = statusCode;
        this.mMetadata = metadata;
    }

    public DataHolder(String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = DEBUG;
        this.mIsLeakDetectionEnabled = true;
        this.mVersionCode = VERSION_CODE;
        this.mColumns = (String[]) Preconditions.checkNotNull(columns);
        this.mWindows = (CursorWindow[]) Preconditions.checkNotNull(windows);
        this.mStatusCode = statusCode;
        this.mMetadata = metadata;
        validateContents();
    }

    public void setLeakIdentifier(Object leakIdentifier) {
        this.mLeakIdentifier = leakIdentifier;
    }

    public void disableLeakDetection() {
        this.mIsLeakDetectionEnabled = DEBUG;
    }

    public DataHolder(AbstractWindowedCursor cursor, int statusCode, Bundle metadata) {
        this(cursor.getColumnNames(), extractWindows(cursor), statusCode, metadata);
    }

    private DataHolder(Builder builder, int statusCode, Bundle metadata) {
        this(builder.mColumns, extractWindows(builder, -1), statusCode, metadata);
    }

    private DataHolder(Builder builder, int statusCode, Bundle metadata, int maxResults) {
        this(builder.mColumns, extractWindows(builder, maxResults), statusCode, metadata);
    }

    public void validateContents() {
        int i;
        this.mColumnBundle = new Bundle();
        for (i = 0; i < this.mColumns.length; i += VERSION_CODE) {
            this.mColumnBundle.putInt(this.mColumns[i], i);
        }
        this.mWindowRowOffsets = new int[this.mWindows.length];
        int totalRows = 0;
        for (i = 0; i < this.mWindows.length; i += VERSION_CODE) {
            this.mWindowRowOffsets[i] = totalRows;
            totalRows += this.mWindows[i].getNumRows() - (totalRows - this.mWindows[i].getStartPosition());
        }
        this.mRowCount = totalRows;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        DataHolderCreator.writeToParcel(this, dest, flags);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    String[] getColumns() {
        return this.mColumns;
    }

    CursorWindow[] getWindows() {
        return this.mWindows;
    }

    public void logCursorMetadataForDebugging() {
        Log.d(TAG, "*******************************************");
        Log.d(TAG, "num cursor windows : " + this.mWindows.length);
        Log.d(TAG, "total number of objects in holder: " + this.mRowCount);
        Log.d(TAG, "total mumber of windowOffsets: " + this.mWindowRowOffsets.length);
        for (int i = 0; i < this.mWindowRowOffsets.length; i += VERSION_CODE) {
            Log.d(TAG, "offset for window " + i + " : " + this.mWindowRowOffsets[i]);
            Log.d(TAG, "num rows for window " + i + " : " + this.mWindows[i].getNumRows());
            Log.d(TAG, "start pos for window " + i + " : " + this.mWindows[i].getStartPosition());
        }
        Log.d(TAG, "*******************************************");
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public Bundle getMetadata() {
        return this.mMetadata;
    }

    private static CursorWindow[] readCursorWindowsFromParcel(Parcel parcel) {
        int windowCount = parcel.readInt();
        CursorWindow[] windows = new CursorWindow[windowCount];
        for (int i = 0; i < windowCount; i += VERSION_CODE) {
            windows[i] = (CursorWindow) parcel.readParcelable(DataHolder.class.getClassLoader());
        }
        return windows;
    }

    private static CursorWindow[] extractWindows(AbstractWindowedCursor cursor) {
        ArrayList<CursorWindow> windows = new ArrayList();
        int totalCount = cursor.getCount();
        CursorWindow firstWindow = cursor.getWindow();
        int index = 0;
        if (firstWindow != null && firstWindow.getStartPosition() == 0) {
            firstWindow.acquireReference();
            cursor.setWindow(null);
            windows.add(firstWindow);
            index = firstWindow.getNumRows();
        }
        while (index < totalCount && cursor.moveToPosition(index)) {
            CursorWindow window = cursor.getWindow();
            if (window != null) {
                window.acquireReference();
                cursor.setWindow(null);
            } else {
                window = new CursorWindow(DEBUG);
                window.setStartPosition(index);
                cursor.fillWindow(index, window);
            }
            if (window.getNumRows() == 0) {
                break;
            }
            try {
                windows.add(window);
                index = window.getStartPosition() + window.getNumRows();
            } catch (Throwable th) {
                cursor.close();
            }
        }
        cursor.close();
        return (CursorWindow[]) windows.toArray(new CursorWindow[windows.size()]);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.database.CursorWindow[] extractWindows(com.google.android.gms.common.data.DataHolder.Builder r24, int r25) {
        /*
        r21 = r24.mColumns;
        r0 = r21;
        r0 = r0.length;
        r21 = r0;
        if (r21 != 0) goto L_0x0014;
    L_0x000b:
        r21 = 0;
        r0 = r21;
        r0 = new android.database.CursorWindow[r0];
        r21 = r0;
    L_0x0013:
        return r21;
    L_0x0014:
        if (r25 < 0) goto L_0x0024;
    L_0x0016:
        r21 = r24.mRows;
        r21 = r21.size();
        r0 = r25;
        r1 = r21;
        if (r0 < r1) goto L_0x00cc;
    L_0x0024:
        r13 = r24.mRows;
    L_0x0028:
        r15 = r13.size();
        r18 = new android.database.CursorWindow;
        r21 = 0;
        r0 = r18;
        r1 = r21;
        r0.<init>(r1);
        r20 = new java.util.ArrayList;
        r20.<init>();
        r0 = r20;
        r1 = r18;
        r0.add(r1);
        r21 = r24.mColumns;
        r0 = r21;
        r0 = r0.length;
        r21 = r0;
        r0 = r18;
        r1 = r21;
        r0.setNumColumns(r1);
        r11 = 0;
        r12 = 0;
        r19 = r18;
    L_0x0057:
        if (r12 >= r15) goto L_0x0262;
    L_0x0059:
        r21 = r19.allocRow();	 Catch:{ RuntimeException -> 0x01e4 }
        if (r21 != 0) goto L_0x00e0;
    L_0x005f:
        r21 = "DataHolder";
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x01e4 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x01e4 }
        r23 = "Allocating additional cursor window for large data set (row ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r22;
        r22 = r0.append(r12);	 Catch:{ RuntimeException -> 0x01e4 }
        r23 = ")";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x01e4 }
        android.util.Log.d(r21, r22);	 Catch:{ RuntimeException -> 0x01e4 }
        r18 = new android.database.CursorWindow;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = 0;
        r0 = r18;
        r1 = r21;
        r0.<init>(r1);	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r18;
        r0.setStartPosition(r12);	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r24.mColumns;	 Catch:{ RuntimeException -> 0x0274 }
        r0 = r21;
        r0 = r0.length;	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r0;
        r0 = r18;
        r1 = r21;
        r0.setNumColumns(r1);	 Catch:{ RuntimeException -> 0x0274 }
        r0 = r20;
        r1 = r18;
        r0.add(r1);	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r18.allocRow();	 Catch:{ RuntimeException -> 0x0274 }
        if (r21 != 0) goto L_0x00de;
    L_0x00ac:
        r21 = "DataHolder";
        r22 = "Unable to allocate row to hold data.";
        android.util.Log.e(r21, r22);	 Catch:{ RuntimeException -> 0x0274 }
        r0 = r20;
        r1 = r18;
        r0.remove(r1);	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r20.size();	 Catch:{ RuntimeException -> 0x0274 }
        r0 = r21;
        r0 = new android.database.CursorWindow[r0];	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r0;
        r21 = r20.toArray(r21);	 Catch:{ RuntimeException -> 0x0274 }
        r21 = (android.database.CursorWindow[]) r21;	 Catch:{ RuntimeException -> 0x0274 }
        goto L_0x0013;
    L_0x00cc:
        r21 = r24.mRows;
        r22 = 0;
        r0 = r21;
        r1 = r22;
        r2 = r25;
        r13 = r0.subList(r1, r2);
        goto L_0x0028;
    L_0x00de:
        r19 = r18;
    L_0x00e0:
        r14 = r13.get(r12);	 Catch:{ RuntimeException -> 0x01e4 }
        r14 = (java.util.Map) r14;	 Catch:{ RuntimeException -> 0x01e4 }
        r17 = 1;
        r5 = 0;
    L_0x00e9:
        r21 = r24.mColumns;	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r21;
        r0 = r0.length;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        r0 = r21;
        if (r5 >= r0) goto L_0x01fc;
    L_0x00f6:
        if (r17 == 0) goto L_0x01fc;
    L_0x00f8:
        r21 = r24.mColumns;	 Catch:{ RuntimeException -> 0x01e4 }
        r6 = r21[r5];	 Catch:{ RuntimeException -> 0x01e4 }
        r16 = r14.get(r6);	 Catch:{ RuntimeException -> 0x01e4 }
        if (r16 != 0) goto L_0x010d;
    L_0x0104:
        r0 = r19;
        r17 = r0.putNull(r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
    L_0x010a:
        r5 = r5 + 1;
        goto L_0x00e9;
    L_0x010d:
        r0 = r16;
        r0 = r0 instanceof java.lang.String;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x0120;
    L_0x0115:
        r16 = (java.lang.String) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r19;
        r1 = r16;
        r17 = r0.putString(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x0120:
        r0 = r16;
        r0 = r0 instanceof java.lang.Long;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x0137;
    L_0x0128:
        r16 = (java.lang.Long) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r16.longValue();	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r19;
        r1 = r22;
        r17 = r0.putLong(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x0137:
        r0 = r16;
        r0 = r0 instanceof java.lang.Integer;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x0153;
    L_0x013f:
        r16 = (java.lang.Integer) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r16.intValue();	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r21;
        r0 = (long) r0;	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r0;
        r0 = r19;
        r1 = r22;
        r17 = r0.putLong(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x0153:
        r0 = r16;
        r0 = r0 instanceof java.lang.Boolean;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x0171;
    L_0x015b:
        r16 = (java.lang.Boolean) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r4 = r16.booleanValue();	 Catch:{ RuntimeException -> 0x01e4 }
        if (r4 == 0) goto L_0x016e;
    L_0x0163:
        r22 = 1;
    L_0x0165:
        r0 = r19;
        r1 = r22;
        r17 = r0.putLong(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x016e:
        r22 = 0;
        goto L_0x0165;
    L_0x0171:
        r0 = r16;
        r0 = r0 instanceof byte[];	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x0186;
    L_0x0179:
        r16 = (byte[]) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r16 = (byte[]) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r19;
        r1 = r16;
        r17 = r0.putBlob(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x0186:
        r0 = r16;
        r0 = r0 instanceof java.lang.Double;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x019e;
    L_0x018e:
        r16 = (java.lang.Double) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r16.doubleValue();	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r19;
        r1 = r22;
        r17 = r0.putDouble(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x019e:
        r0 = r16;
        r0 = r0 instanceof java.lang.Float;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r0;
        if (r21 == 0) goto L_0x01bb;
    L_0x01a6:
        r16 = (java.lang.Float) r16;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = r16.floatValue();	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r21;
        r0 = (double) r0;	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r0;
        r0 = r19;
        r1 = r22;
        r17 = r0.putDouble(r1, r12, r5);	 Catch:{ RuntimeException -> 0x01e4 }
        goto L_0x010a;
    L_0x01bb:
        r21 = new java.lang.IllegalArgumentException;	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x01e4 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x01e4 }
        r23 = "Unsupported object for column ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r22;
        r22 = r0.append(r6);	 Catch:{ RuntimeException -> 0x01e4 }
        r23 = ": ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r22;
        r1 = r16;
        r22 = r0.append(r1);	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x01e4 }
        r21.<init>(r22);	 Catch:{ RuntimeException -> 0x01e4 }
        throw r21;	 Catch:{ RuntimeException -> 0x01e4 }
    L_0x01e4:
        r10 = move-exception;
        r18 = r19;
    L_0x01e7:
        r8 = 0;
        r7 = r20.size();
    L_0x01ec:
        if (r8 >= r7) goto L_0x0261;
    L_0x01ee:
        r0 = r20;
        r21 = r0.get(r8);
        r21 = (android.database.CursorWindow) r21;
        r21.close();
        r8 = r8 + 1;
        goto L_0x01ec;
    L_0x01fc:
        if (r17 != 0) goto L_0x025d;
    L_0x01fe:
        if (r11 == 0) goto L_0x020a;
    L_0x0200:
        r9 = "Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.";
        r21 = new com.google.android.gms.common.data.DataHolder$DataHolderException;	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r21;
        r0.<init>(r9);	 Catch:{ RuntimeException -> 0x01e4 }
        throw r21;	 Catch:{ RuntimeException -> 0x01e4 }
    L_0x020a:
        r21 = "DataHolder";
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x01e4 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x01e4 }
        r23 = "Couldn't populate window data for row ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r22;
        r22 = r0.append(r12);	 Catch:{ RuntimeException -> 0x01e4 }
        r23 = " - allocating new window.";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x01e4 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x01e4 }
        android.util.Log.d(r21, r22);	 Catch:{ RuntimeException -> 0x01e4 }
        r19.freeLastRow();	 Catch:{ RuntimeException -> 0x01e4 }
        r18 = new android.database.CursorWindow;	 Catch:{ RuntimeException -> 0x01e4 }
        r21 = 0;
        r0 = r18;
        r1 = r21;
        r0.<init>(r1);	 Catch:{ RuntimeException -> 0x01e4 }
        r0 = r18;
        r0.setStartPosition(r12);	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r24.mColumns;	 Catch:{ RuntimeException -> 0x0274 }
        r0 = r21;
        r0 = r0.length;	 Catch:{ RuntimeException -> 0x0274 }
        r21 = r0;
        r0 = r18;
        r1 = r21;
        r0.setNumColumns(r1);	 Catch:{ RuntimeException -> 0x0274 }
        r0 = r20;
        r1 = r18;
        r0.add(r1);	 Catch:{ RuntimeException -> 0x0274 }
        r12 = r12 + -1;
        r11 = 1;
    L_0x0257:
        r12 = r12 + 1;
        r19 = r18;
        goto L_0x0057;
    L_0x025d:
        r11 = 0;
        r18 = r19;
        goto L_0x0257;
    L_0x0261:
        throw r10;
    L_0x0262:
        r21 = r20.size();
        r0 = r21;
        r0 = new android.database.CursorWindow[r0];
        r21 = r0;
        r21 = r20.toArray(r21);
        r21 = (android.database.CursorWindow[]) r21;
        goto L_0x0013;
    L_0x0274:
        r10 = move-exception;
        goto L_0x01e7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.extractWindows(com.google.android.gms.common.data.DataHolder$Builder, int):android.database.CursorWindow[]");
    }

    private void checkColumnAndRow(String column, int row) {
        if (this.mColumnBundle == null || !this.mColumnBundle.containsKey(column)) {
            throw new IllegalArgumentException("No such column: " + column);
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (row < 0 || row >= this.mRowCount) {
            throw new CursorIndexOutOfBoundsException(row, this.mRowCount);
        }
    }

    public boolean hasColumn(String column) {
        return this.mColumnBundle.containsKey(column);
    }

    public long getLong(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].getLong(row, this.mColumnBundle.getInt(column));
    }

    public int getInteger(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].getInt(row, this.mColumnBundle.getInt(column));
    }

    public String getString(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].getString(row, this.mColumnBundle.getInt(column));
    }

    public boolean getBoolean(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return Long.valueOf(this.mWindows[windowIndex].getLong(row, this.mColumnBundle.getInt(column))).longValue() == 1 ? true : DEBUG;
    }

    public float getFloat(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].getFloat(row, this.mColumnBundle.getInt(column));
    }

    public double getDouble(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].getDouble(row, this.mColumnBundle.getInt(column));
    }

    public byte[] getByteArray(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].getBlob(row, this.mColumnBundle.getInt(column));
    }

    public Uri parseUri(String column, int row, int windowIndex) {
        String uriString = getString(column, row, windowIndex);
        return uriString == null ? null : Uri.parse(uriString);
    }

    public void copyToBuffer(String column, int row, int windowIndex, CharArrayBuffer dataOut) {
        checkColumnAndRow(column, row);
        this.mWindows[windowIndex].copyStringToBuffer(row, this.mColumnBundle.getInt(column), dataOut);
    }

    public boolean hasNull(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        return this.mWindows[windowIndex].isNull(row, this.mColumnBundle.getInt(column));
    }

    public void clearColumn(String column, int row, int windowIndex) {
        checkColumnAndRow(column, row);
        this.mWindows[windowIndex].putNull(row, this.mColumnBundle.getInt(column));
    }

    public void replaceValue(String column, int row, int windowIndex, String value) {
        checkColumnAndRow(column, row);
        this.mWindows[windowIndex].putString(value, row, this.mColumnBundle.getInt(column));
    }

    public void replaceValue(String column, int row, int windowIndex, double value) {
        checkColumnAndRow(column, row);
        this.mWindows[windowIndex].putDouble(value, row, this.mColumnBundle.getInt(column));
    }

    public void replaceValue(String column, int row, int windowIndex, long value) {
        checkColumnAndRow(column, row);
        this.mWindows[windowIndex].putLong(value, row, this.mColumnBundle.getInt(column));
    }

    public void replaceValue(String column, int row, int windowIndex, byte[] value) {
        checkColumnAndRow(column, row);
        this.mWindows[windowIndex].putBlob(value, row, this.mColumnBundle.getInt(column));
    }

    public int getCount() {
        return this.mRowCount;
    }

    public int getWindowIndex(int row) {
        boolean z = (row < 0 || row >= this.mRowCount) ? DEBUG : true;
        Preconditions.checkState(z);
        int winIndex = 0;
        while (winIndex < this.mWindowRowOffsets.length) {
            if (row < this.mWindowRowOffsets[winIndex]) {
                winIndex--;
                break;
            }
            winIndex += VERSION_CODE;
        }
        if (winIndex == this.mWindowRowOffsets.length) {
            return winIndex - 1;
        }
        return winIndex;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.mWindows.length; i += VERSION_CODE) {
                    this.mWindows[i].close();
                }
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mIsLeakDetectionEnabled && this.mWindows.length > 0 && !isClosed()) {
                String identifier;
                if (this.mLeakIdentifier == null) {
                    identifier = "internal object: " + toString();
                } else {
                    identifier = this.mLeakIdentifier.toString();
                }
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + identifier + ")");
                close();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public static Builder builder(String[] columns) {
        return new Builder(null, null);
    }

    public static Builder builder(String[] columns, String uniqueColumn) {
        Preconditions.checkNotNull(uniqueColumn);
        return new Builder(uniqueColumn, null);
    }

    public static DataHolder empty(int statusCode) {
        return empty(statusCode, null);
    }

    public static DataHolder empty(int statusCode, Bundle metadata) {
        return new DataHolder(EMPTY, statusCode, metadata);
    }
}
