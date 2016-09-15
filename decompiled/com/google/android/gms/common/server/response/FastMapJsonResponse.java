package com.google.android.gms.common.server.response;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastMapJsonResponse extends FastJsonResponse {
    private final HashMap<String, Object> mValues;

    public FastMapJsonResponse() {
        this.mValues = new HashMap();
    }

    public HashMap<String, Object> getValues() {
        return this.mValues;
    }

    public Object getValueObject(String key) {
        return this.mValues.get(key);
    }

    protected boolean isPrimitiveFieldSet(String key) {
        return this.mValues.containsKey(key);
    }

    public void setInteger(String outputField, int value) {
        this.mValues.put(outputField, Integer.valueOf(value));
    }

    public void setIntegers(String outputField, ArrayList<Integer> values) {
        this.mValues.put(outputField, values);
    }

    public void setBigInteger(String outputField, BigInteger value) {
        this.mValues.put(outputField, value);
    }

    public void setBigIntegers(String outputField, ArrayList<BigInteger> values) {
        this.mValues.put(outputField, values);
    }

    public void setLong(String outputField, long value) {
        this.mValues.put(outputField, Long.valueOf(value));
    }

    public void setLongs(String outputField, ArrayList<Long> values) {
        this.mValues.put(outputField, values);
    }

    protected void setFloat(String outputField, float value) {
        this.mValues.put(outputField, Float.valueOf(value));
    }

    protected void setFloats(String outputField, ArrayList<Float> values) {
        this.mValues.put(outputField, values);
    }

    public void setDouble(String outputField, double value) {
        this.mValues.put(outputField, Double.valueOf(value));
    }

    public void setDoubles(String outputField, ArrayList<Double> values) {
        this.mValues.put(outputField, values);
    }

    public void setBigDecimal(String outputField, BigDecimal value) {
        this.mValues.put(outputField, value);
    }

    public void setBigDecimals(String outputField, ArrayList<BigDecimal> values) {
        this.mValues.put(outputField, values);
    }

    public void setBoolean(String outputField, boolean value) {
        this.mValues.put(outputField, Boolean.valueOf(value));
    }

    public void setBooleans(String outputField, ArrayList<Boolean> values) {
        this.mValues.put(outputField, values);
    }

    public void setString(String outputField, String value) {
        this.mValues.put(outputField, value);
    }

    public void setStrings(String outputField, ArrayList<String> values) {
        this.mValues.put(outputField, values);
    }

    public void setDecodedBytes(String outputField, byte[] value) {
        this.mValues.put(outputField, value);
    }

    public void setStringMap(String outputField, Map<String, String> value) {
        this.mValues.put(outputField, value);
    }
}
