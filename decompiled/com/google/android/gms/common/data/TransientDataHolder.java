package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder.Builder;
import com.google.android.gms.common.util.VisibleForTesting;

public final class TransientDataHolder {
    private Builder mData;
    private String mNextToken;
    private String mPrevToken;

    public TransientDataHolder(String[] dataColumns) {
        this(dataColumns, null, null, null);
    }

    public TransientDataHolder(String[] dataColumns, String uniqueColumn, String prevToken, String nextToken) {
        this.mPrevToken = prevToken;
        this.mNextToken = nextToken;
        if (uniqueColumn != null) {
            this.mData = DataHolder.builder(dataColumns, uniqueColumn);
        } else {
            this.mData = DataHolder.builder(dataColumns);
        }
    }

    public String getPrevToken() {
        return this.mPrevToken;
    }

    public void setPrevToken(String token) {
        this.mPrevToken = token;
    }

    public String getNextToken() {
        return this.mNextToken;
    }

    public void setNextToken(String token) {
        this.mNextToken = token;
    }

    public void addRow(ContentValues rowValues) {
        this.mData.withRow(rowValues);
    }

    public boolean containsRowWithValue(String column, Object value) {
        return this.mData.containsRowWithValue(column, value);
    }

    public void modifyUniqueRowValue(Object uniqueValue, String columnName, Object newValue) {
        this.mData.modifyUniqueRowValue(uniqueValue, columnName, newValue);
    }

    @VisibleForTesting
    public void removeRows(String column, Object value) {
        this.mData.removeRowsWithValue(column, value);
    }

    public int getCount() {
        return this.mData.getCount();
    }

    public void sortData(String sortColumn) {
        this.mData.sort(sortColumn);
    }

    public void sortDataDescending(String sortColumn) {
        this.mData.descendingSort(sortColumn);
    }

    @VisibleForTesting
    public DataHolder build(int statusCode) {
        return build(statusCode, new Bundle(), -1);
    }

    public DataHolder build(int statusCode, Bundle metadata, int maxResults) {
        metadata.putString(DataBufferUtils.KEY_NEXT_PAGE_TOKEN, this.mNextToken);
        metadata.putString(DataBufferUtils.KEY_PREV_PAGE_TOKEN, this.mPrevToken);
        return this.mData.build(statusCode, metadata, maxResults);
    }
}
