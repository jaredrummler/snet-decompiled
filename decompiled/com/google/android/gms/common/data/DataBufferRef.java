package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public abstract class DataBufferRef {
    protected final DataHolder mDataHolder;
    protected int mDataRow;
    private int mWindowIndex;

    public DataBufferRef(DataHolder holder, int dataRow) {
        this.mDataHolder = (DataHolder) Preconditions.checkNotNull(holder);
        setDataRow(dataRow);
    }

    protected int getDataRow() {
        return this.mDataRow;
    }

    protected void setDataRow(int dataRow) {
        boolean z = dataRow >= 0 && dataRow < this.mDataHolder.getCount();
        Preconditions.checkState(z);
        this.mDataRow = dataRow;
        this.mWindowIndex = this.mDataHolder.getWindowIndex(this.mDataRow);
    }

    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    public boolean hasColumn(String column) {
        return this.mDataHolder.hasColumn(column);
    }

    protected long getLong(String column) {
        return this.mDataHolder.getLong(column, this.mDataRow, this.mWindowIndex);
    }

    protected int getInteger(String column) {
        return this.mDataHolder.getInteger(column, this.mDataRow, this.mWindowIndex);
    }

    protected boolean getBoolean(String column) {
        return this.mDataHolder.getBoolean(column, this.mDataRow, this.mWindowIndex);
    }

    protected String getString(String column) {
        return this.mDataHolder.getString(column, this.mDataRow, this.mWindowIndex);
    }

    protected float getFloat(String column) {
        return this.mDataHolder.getFloat(column, this.mDataRow, this.mWindowIndex);
    }

    protected double getDouble(String column) {
        return this.mDataHolder.getDouble(column, this.mDataRow, this.mWindowIndex);
    }

    protected byte[] getByteArray(String column) {
        return this.mDataHolder.getByteArray(column, this.mDataRow, this.mWindowIndex);
    }

    protected Uri parseUri(String column) {
        return this.mDataHolder.parseUri(column, this.mDataRow, this.mWindowIndex);
    }

    protected void copyToBuffer(String column, CharArrayBuffer dataOut) {
        this.mDataHolder.copyToBuffer(column, this.mDataRow, this.mWindowIndex, dataOut);
    }

    protected boolean hasNull(String column) {
        return this.mDataHolder.hasNull(column, this.mDataRow, this.mWindowIndex);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mDataRow), Integer.valueOf(this.mWindowIndex), this.mDataHolder);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DataBufferRef)) {
            return false;
        }
        DataBufferRef other = (DataBufferRef) obj;
        if (Objects.equal(Integer.valueOf(other.mDataRow), Integer.valueOf(this.mDataRow)) && Objects.equal(Integer.valueOf(other.mWindowIndex), Integer.valueOf(this.mWindowIndex)) && other.mDataHolder == this.mDataHolder) {
            return true;
        }
        return false;
    }
}
