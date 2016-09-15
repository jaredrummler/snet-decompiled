package com.google.android.gms.people.exp;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;

public abstract class RawBuffer {
    private final DataHolder mDataHolder;
    private int mPos;

    protected RawBuffer(DataHolder dataHolder) {
        Preconditions.checkNotNull(dataHolder);
        this.mDataHolder = dataHolder;
        this.mPos = -1;
    }

    public int getCount() {
        return this.mDataHolder.getCount();
    }

    public void close() {
        this.mDataHolder.close();
    }

    public boolean isClosed() {
        return this.mDataHolder.isClosed();
    }

    public Bundle getMetadata() {
        return this.mDataHolder.getMetadata();
    }

    public int getPosition() {
        return this.mPos;
    }

    protected int getWindowIndex() {
        return this.mDataHolder.getWindowIndex(this.mPos);
    }

    public boolean moveToPosition(int position) {
        int count = getCount();
        if (position >= count) {
            this.mPos = count;
            return false;
        } else if (position < 0) {
            this.mPos = -1;
            return false;
        } else {
            this.mPos = position;
            return true;
        }
    }

    public final boolean move(int offset) {
        return moveToPosition(this.mPos + offset);
    }

    public boolean moveToFirst() {
        return moveToPosition(0);
    }

    public final boolean moveToLast() {
        return moveToPosition(getCount() - 1);
    }

    public final boolean moveToNext() {
        return moveToPosition(this.mPos + 1);
    }

    public final boolean moveToPrevious() {
        return moveToPosition(this.mPos - 1);
    }

    public final boolean isFirst() {
        return this.mPos == 0 && getCount() != 0;
    }

    public final boolean isLast() {
        int cnt = getCount();
        return this.mPos == cnt + -1 && cnt != 0;
    }

    public final boolean isBeforeFirst() {
        if (getCount() == 0 || this.mPos == -1) {
            return true;
        }
        return false;
    }

    public final boolean isAfterLast() {
        if (getCount() == 0 || this.mPos == getCount()) {
            return true;
        }
        return false;
    }

    public int getInteger(String column) {
        return this.mDataHolder.getInteger(column, getPosition(), getWindowIndex());
    }

    public long getLong(String column) {
        return this.mDataHolder.getLong(column, getPosition(), getWindowIndex());
    }

    public String getString(String column) {
        return this.mDataHolder.getString(column, getPosition(), getWindowIndex());
    }

    public double getDouble(String column) {
        return this.mDataHolder.getDouble(column, getPosition(), getWindowIndex());
    }
}
