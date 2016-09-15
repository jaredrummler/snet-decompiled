package com.google.android.gms.common.server.response;

import android.content.ContentValues;
import com.google.android.gms.common.util.VisibleForTesting;

public abstract class FastContentValuesJsonResponse extends FastJsonResponse {
    private final ContentValues mValues;

    public FastContentValuesJsonResponse() {
        this.mValues = new ContentValues();
    }

    @VisibleForTesting
    public FastContentValuesJsonResponse(ContentValues values) {
        this.mValues = values;
    }

    protected Object getValueObject(String key) {
        return this.mValues.get(key);
    }

    protected boolean isPrimitiveFieldSet(String field) {
        return this.mValues.containsKey(field);
    }

    public ContentValues getValues() {
        return this.mValues;
    }

    protected void setInteger(String outputField, int value) {
        this.mValues.put(outputField, Integer.valueOf(value));
    }

    protected void setLong(String outputField, long value) {
        this.mValues.put(outputField, Long.valueOf(value));
    }

    protected void setFloat(String outputField, float value) {
        this.mValues.put(outputField, Float.valueOf(value));
    }

    protected void setDouble(String outputField, double value) {
        this.mValues.put(outputField, Double.valueOf(value));
    }

    protected void setBoolean(String outputField, boolean value) {
        this.mValues.put(outputField, Boolean.valueOf(value));
    }

    protected void setString(String outputField, String value) {
        this.mValues.put(outputField, value);
    }

    protected void setDecodedBytes(String outputField, byte[] value) {
        this.mValues.put(outputField, value);
    }
}
