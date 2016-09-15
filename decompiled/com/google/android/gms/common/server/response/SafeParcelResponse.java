package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.snet.Csv;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Class(creator = "SafeParcelResponseCreator")
@VisibleForTesting
public class SafeParcelResponse extends FastJsonResponse implements SafeParcelable {
    public static final SafeParcelResponseCreator CREATOR;
    private static final String TAG = "SafeParcelResponse";
    private static final int VERSION_CODE = 1;
    private final String mClassName;
    private final int mCreationType;
    @Field(getter = "getFieldMappingDictionary", id = 3)
    private final FieldMappingDictionary mDictionary;
    @Field(getter = "getParcel", id = 2)
    private final Parcel mParcel;
    private int mParseStartPosition;
    private int mParseState;
    @VersionField(getter = "getVersionCode", id = 1)
    private final int mVersionCode;

    private interface CreationType {
        public static final int FROM_IPC = 2;
        public static final int FROM_PARSING_JSON = 0;
        public static final int FROM_SAFE_PARCELABLE = 1;
    }

    private interface ParseState {
        public static final int FINISHED = 2;
        public static final int IN_PROGRESS = 1;
        public static final int NOT_STARTED = 0;
    }

    public SafeParcelResponse(FieldMappingDictionary dictionary) {
        this(dictionary, dictionary.getRootClassName());
    }

    public SafeParcelResponse(FieldMappingDictionary dictionary, String className) {
        this.mVersionCode = VERSION_CODE;
        this.mParcel = Parcel.obtain();
        this.mCreationType = 0;
        this.mDictionary = (FieldMappingDictionary) Preconditions.checkNotNull(dictionary);
        this.mClassName = (String) Preconditions.checkNotNull(className);
        this.mParseState = 0;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, FieldMappingDictionary dictionary, String className) {
        this.mVersionCode = VERSION_CODE;
        this.mParcel = Parcel.obtain();
        safeParcelable.writeToParcel(this.mParcel, 0);
        this.mCreationType = VERSION_CODE;
        this.mDictionary = (FieldMappingDictionary) Preconditions.checkNotNull(dictionary);
        this.mClassName = (String) Preconditions.checkNotNull(className);
        this.mParseState = 2;
    }

    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse from(T safeParcelable) {
        String className = safeParcelable.getClass().getCanonicalName();
        return new SafeParcelResponse((SafeParcelable) safeParcelable, generateDictionary((FastJsonResponse) safeParcelable), className);
    }

    private static FieldMappingDictionary generateDictionary(FastJsonResponse response) {
        FieldMappingDictionary dictionary = new FieldMappingDictionary(response.getClass());
        addToDictionary(dictionary, response);
        dictionary.copyInternalFieldMappings();
        dictionary.linkFields();
        return dictionary;
    }

    private static void addToDictionary(FieldMappingDictionary dictionary, FastJsonResponse response) {
        Class<? extends FastJsonResponse> clazz = response.getClass();
        if (!dictionary.hasFieldMappingForClass(clazz)) {
            Map<String, FastJsonResponse.Field<?, ?>> fieldMap = response.getFieldMappings();
            dictionary.put(clazz, fieldMap);
            for (String key : fieldMap.keySet()) {
                FastJsonResponse.Field<?, ?> field = (FastJsonResponse.Field) fieldMap.get(key);
                Class<? extends FastJsonResponse> concreteType = field.getConcreteType();
                if (concreteType != null) {
                    try {
                        addToDictionary(dictionary, (FastJsonResponse) concreteType.newInstance());
                    } catch (InstantiationException e) {
                        throw new IllegalStateException("Could not instantiate an object of type " + field.getConcreteType().getCanonicalName(), e);
                    } catch (IllegalAccessException e2) {
                        throw new IllegalStateException("Could not access object of type " + field.getConcreteType().getCanonicalName(), e2);
                    }
                }
            }
        }
    }

    public <T extends SafeParcelable> T inflate(Creator<T> creator) {
        Parcel data = getParcel();
        data.setDataPosition(0);
        return (SafeParcelable) creator.createFromParcel(data);
    }

    public static FieldMappingDictionary generateDictionary(Class<? extends FastJsonResponse> clazz) {
        try {
            return generateDictionary((FastJsonResponse) clazz.newInstance());
        } catch (InstantiationException e) {
            throw new IllegalStateException("Could not instantiate an object of type " + clazz.getCanonicalName(), e);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Could not access object of type " + clazz.getCanonicalName(), e2);
        }
    }

    @Constructor
    SafeParcelResponse(@Param(id = 1) int versionCode, @Param(id = 2) Parcel parcel, @Param(id = 3) FieldMappingDictionary fieldMappingDictionary) {
        this.mVersionCode = versionCode;
        this.mParcel = (Parcel) Preconditions.checkNotNull(parcel);
        this.mCreationType = 2;
        this.mDictionary = fieldMappingDictionary;
        if (this.mDictionary == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.mDictionary.getRootClassName();
        }
        this.mParseState = 2;
    }

    static {
        CREATOR = new SafeParcelResponseCreator();
    }

    public int describeContents() {
        SafeParcelResponseCreator safeParcelResponseCreator = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        SafeParcelResponseCreator safeParcelResponseCreator = CREATOR;
        SafeParcelResponseCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public Parcel getParcel() {
        switch (this.mParseState) {
            case Action.UNKNOWN /*0*/:
                this.mParseStartPosition = SafeParcelWriter.beginObjectHeader(this.mParcel);
                SafeParcelWriter.finishObjectHeader(this.mParcel, this.mParseStartPosition);
                this.mParseState = 2;
                break;
            case VERSION_CODE /*1*/:
                SafeParcelWriter.finishObjectHeader(this.mParcel, this.mParseStartPosition);
                this.mParseState = 2;
                break;
        }
        return this.mParcel;
    }

    FieldMappingDictionary getFieldMappingDictionary() {
        switch (this.mCreationType) {
            case Action.UNKNOWN /*0*/:
                return null;
            case VERSION_CODE /*1*/:
                return this.mDictionary;
            case Type.INDEFINITELY /*2*/:
                return this.mDictionary;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.mCreationType);
        }
    }

    String getClassName() {
        return this.mClassName;
    }

    public Map<String, FastJsonResponse.Field<?, ?>> getFieldMappings() {
        if (this.mDictionary == null) {
            return null;
        }
        return this.mDictionary.getFieldMapping(this.mClassName);
    }

    protected Object getValueObject(String key) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    protected boolean isPrimitiveFieldSet(String outputField) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    private void ensureValidForParsing(FastJsonResponse.Field<?, ?> field) {
        if (!field.isValidSafeParcelableFieldId()) {
            throw new IllegalStateException("Field does not have a valid safe parcelable field id.");
        } else if (this.mParcel == null) {
            throw new IllegalStateException("Internal Parcel object is null.");
        } else {
            switch (this.mParseState) {
                case Action.UNKNOWN /*0*/:
                    this.mParseStartPosition = SafeParcelWriter.beginObjectHeader(this.mParcel);
                    this.mParseState = VERSION_CODE;
                case VERSION_CODE /*1*/:
                case Type.INDEFINITELY /*2*/:
                    throw new IllegalStateException("Attempted to parse JSON with a SafeParcelResponse object that is already filled with data.");
                default:
                    throw new IllegalStateException("Unknown parse state in SafeParcelResponse.");
            }
        }
    }

    protected void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String outputField, int value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeInt(this.mParcel, field.getSafeParcelableFieldId(), value);
    }

    protected void setIntegersInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<Integer> values) {
        ensureValidForParsing(field);
        int size = values.size();
        int[] contents = new int[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = ((Integer) values.get(i)).intValue();
        }
        SafeParcelWriter.writeIntArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setBigIntegerInternal(FastJsonResponse.Field<?, ?> field, String outputField, BigInteger value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeBigInteger(this.mParcel, field.getSafeParcelableFieldId(), value, true);
    }

    protected void setBigIntegersInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<BigInteger> values) {
        ensureValidForParsing(field);
        int size = values.size();
        BigInteger[] contents = new BigInteger[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = (BigInteger) values.get(i);
        }
        SafeParcelWriter.writeBigIntegerArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setLongInternal(FastJsonResponse.Field<?, ?> field, String outputField, long value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeLong(this.mParcel, field.getSafeParcelableFieldId(), value);
    }

    protected void setLongsInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<Long> values) {
        ensureValidForParsing(field);
        int size = values.size();
        long[] contents = new long[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = ((Long) values.get(i)).longValue();
        }
        SafeParcelWriter.writeLongArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setFloatInternal(FastJsonResponse.Field<?, ?> field, String outputField, float value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeFloat(this.mParcel, field.getSafeParcelableFieldId(), value);
    }

    protected void setFloatsInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<Float> values) {
        ensureValidForParsing(field);
        int size = values.size();
        float[] contents = new float[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = ((Float) values.get(i)).floatValue();
        }
        SafeParcelWriter.writeFloatArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setDoubleInternal(FastJsonResponse.Field<?, ?> field, String outputField, double value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeDouble(this.mParcel, field.getSafeParcelableFieldId(), value);
    }

    protected void setDoublesInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<Double> values) {
        ensureValidForParsing(field);
        int size = values.size();
        double[] contents = new double[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = ((Double) values.get(i)).doubleValue();
        }
        SafeParcelWriter.writeDoubleArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setBigDecimalInternal(FastJsonResponse.Field<?, ?> field, String outputField, BigDecimal value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeBigDecimal(this.mParcel, field.getSafeParcelableFieldId(), value, true);
    }

    protected void setBigDecimalsInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<BigDecimal> values) {
        ensureValidForParsing(field);
        int size = values.size();
        BigDecimal[] contents = new BigDecimal[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = (BigDecimal) values.get(i);
        }
        SafeParcelWriter.writeBigDecimalArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setBooleanInternal(FastJsonResponse.Field<?, ?> field, String outputField, boolean value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeBoolean(this.mParcel, field.getSafeParcelableFieldId(), value);
    }

    protected void setBooleansInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<Boolean> values) {
        ensureValidForParsing(field);
        int size = values.size();
        boolean[] contents = new boolean[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = ((Boolean) values.get(i)).booleanValue();
        }
        SafeParcelWriter.writeBooleanArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setStringInternal(FastJsonResponse.Field<?, ?> field, String outputField, String value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeString(this.mParcel, field.getSafeParcelableFieldId(), value, true);
    }

    protected void setStringsInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<String> values) {
        ensureValidForParsing(field);
        int size = values.size();
        String[] contents = new String[size];
        for (int i = 0; i < size; i += VERSION_CODE) {
            contents[i] = (String) values.get(i);
        }
        SafeParcelWriter.writeStringArray(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    protected void setDecodedBytesInternal(FastJsonResponse.Field<?, ?> field, String outputField, byte[] value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeByteArray(this.mParcel, field.getSafeParcelableFieldId(), value, true);
    }

    protected void setStringMapInternal(FastJsonResponse.Field<?, ?> field, String outputField, Map<String, String> value) {
        ensureValidForParsing(field);
        Bundle contents = new Bundle();
        for (String key : value.keySet()) {
            contents.putString(key, (String) value.get(key));
        }
        SafeParcelWriter.writeBundle(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(FastJsonResponse.Field<?, ?> field, String outputField, T value) {
        ensureValidForParsing(field);
        SafeParcelWriter.writeParcel(this.mParcel, field.getSafeParcelableFieldId(), ((SafeParcelResponse) value).getParcel(), true);
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(FastJsonResponse.Field<?, ?> field, String outputField, ArrayList<T> values) {
        ensureValidForParsing(field);
        ArrayList<Parcel> contents = new ArrayList();
        int size = values.size();
        Iterator i$ = values.iterator();
        while (i$.hasNext()) {
            contents.add(((SafeParcelResponse) ((FastJsonResponse) i$.next())).getParcel());
        }
        SafeParcelWriter.writeParcelList(this.mParcel, field.getSafeParcelableFieldId(), contents, true);
    }

    public String toString() {
        Preconditions.checkNotNull(this.mDictionary, "Cannot convert to JSON on client side.");
        Parcel parcel = getParcel();
        parcel.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        writeObjectToJson(sb, this.mDictionary.getFieldMapping(this.mClassName), parcel);
        return sb.toString();
    }

    private void writeObjectToJson(StringBuilder sb, Map<String, FastJsonResponse.Field<?, ?>> fieldMap, Parcel parcel) {
        HashMap<Integer, Entry<String, FastJsonResponse.Field<?, ?>>> fieldIdMap = remapWithSafeParcelableFieldId(fieldMap);
        sb.append('{');
        int end = SafeParcelReader.validateObjectHeader(parcel);
        boolean addedEntry = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            Entry<String, FastJsonResponse.Field<?, ?>> entry = (Entry) fieldIdMap.get(Integer.valueOf(SafeParcelReader.getFieldId(header)));
            if (entry != null) {
                if (addedEntry) {
                    sb.append(Csv.COMMA);
                }
                writeEntryToJson(sb, (String) entry.getKey(), (FastJsonResponse.Field) entry.getValue(), parcel, header);
                addedEntry = true;
            }
        }
        if (parcel.dataPosition() != end) {
            throw new ParseException("Overread allowed size end=" + end, parcel);
        }
        sb.append('}');
    }

    private static HashMap<Integer, Entry<String, FastJsonResponse.Field<?, ?>>> remapWithSafeParcelableFieldId(Map<String, FastJsonResponse.Field<?, ?>> fieldMap) {
        HashMap<Integer, Entry<String, FastJsonResponse.Field<?, ?>>> fieldIdMap = new HashMap();
        for (Entry<String, FastJsonResponse.Field<?, ?>> entry : fieldMap.entrySet()) {
            fieldIdMap.put(Integer.valueOf(((FastJsonResponse.Field) entry.getValue()).getSafeParcelableFieldId()), entry);
        }
        return fieldIdMap;
    }

    private void writeEntryToJson(StringBuilder sb, String inputFieldName, FastJsonResponse.Field<?, ?> field, Parcel parcel, int header) {
        sb.append("\"").append(inputFieldName).append("\":");
        if (field.hasConverter()) {
            writeConvertedValueToJson(sb, field, parcel, header);
        } else {
            writeUnconvertedValueToJson(sb, field, parcel, header);
        }
    }

    private void writeConvertedValueToJson(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Parcel parcel, int header) {
        switch (field.getTypeOut()) {
            case Action.UNKNOWN /*0*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, Integer.valueOf(SafeParcelReader.readInt(parcel, header))));
            case VERSION_CODE /*1*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, SafeParcelReader.createBigInteger(parcel, header)));
            case Type.INDEFINITELY /*2*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, Long.valueOf(SafeParcelReader.readLong(parcel, header))));
            case Type.CUSTOM /*3*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, Float.valueOf(SafeParcelReader.readFloat(parcel, header))));
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, Double.valueOf(SafeParcelReader.readDouble(parcel, header))));
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, SafeParcelReader.createBigDecimal(parcel, header)));
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, Boolean.valueOf(SafeParcelReader.readBoolean(parcel, header))));
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, SafeParcelReader.createString(parcel, header)));
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, SafeParcelReader.createByteArray(parcel, header)));
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                writeOriginalValueToJson(sb, field, getOriginalValue(field, convertBundleToStringMap(SafeParcelReader.createBundle(parcel, header))));
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.getTypeOut());
        }
    }

    private void writeUnconvertedValueToJson(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Parcel parcel, int header) {
        if (field.isTypeOutArray()) {
            sb.append("[");
            switch (field.getTypeOut()) {
                case Action.UNKNOWN /*0*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createIntArray(parcel, header));
                    break;
                case VERSION_CODE /*1*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createBigIntegerArray(parcel, header));
                    break;
                case Type.INDEFINITELY /*2*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createLongArray(parcel, header));
                    break;
                case Type.CUSTOM /*3*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createFloatArray(parcel, header));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createDoubleArray(parcel, header));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createBigDecimalArray(parcel, header));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    ArrayUtils.writeArray(sb, SafeParcelReader.createBooleanArray(parcel, header));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    ArrayUtils.writeStringArray(sb, SafeParcelReader.createStringArray(parcel, header));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    Parcel[] value = SafeParcelReader.createParcelArray(parcel, header);
                    int length = value.length;
                    for (int i = 0; i < length; i += VERSION_CODE) {
                        if (i > 0) {
                            sb.append(Csv.COMMA);
                        }
                        value[i].setDataPosition(0);
                        writeObjectToJson(sb, field.getConcreteTypeFieldMappingFromDictionary(), value[i]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (field.getTypeOut()) {
            case Action.UNKNOWN /*0*/:
                sb.append(SafeParcelReader.readInt(parcel, header));
            case VERSION_CODE /*1*/:
                sb.append(SafeParcelReader.createBigInteger(parcel, header));
            case Type.INDEFINITELY /*2*/:
                sb.append(SafeParcelReader.readLong(parcel, header));
            case Type.CUSTOM /*3*/:
                sb.append(SafeParcelReader.readFloat(parcel, header));
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                sb.append(SafeParcelReader.readDouble(parcel, header));
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                sb.append(SafeParcelReader.createBigDecimal(parcel, header));
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                sb.append(SafeParcelReader.readBoolean(parcel, header));
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                sb.append("\"").append(JsonUtils.escapeString(SafeParcelReader.createString(parcel, header))).append("\"");
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                sb.append("\"").append(Base64Utils.encode(SafeParcelReader.createByteArray(parcel, header))).append("\"");
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                sb.append("\"").append(Base64Utils.encodeUrlSafe(SafeParcelReader.createByteArray(parcel, header)));
                sb.append("\"");
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                Bundle bundle = SafeParcelReader.createBundle(parcel, header);
                Set<String> keys = bundle.keySet();
                int size = keys.size();
                sb.append("{");
                boolean isFirst = true;
                for (String key : keys) {
                    if (!isFirst) {
                        sb.append(Csv.COMMA);
                    }
                    isFirst = false;
                    sb.append("\"").append(key).append("\"");
                    sb.append(":");
                    sb.append("\"").append(JsonUtils.escapeString(bundle.getString(key))).append("\"");
                }
                sb.append("}");
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                Parcel value2 = SafeParcelReader.createParcel(parcel, header);
                value2.setDataPosition(0);
                writeObjectToJson(sb, field.getConcreteTypeFieldMappingFromDictionary(), value2);
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void writeOriginalValueToJson(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Object originalValue) {
        if (field.isTypeInArray()) {
            writeBackConvertedArrayList(sb, field, (ArrayList) originalValue);
        } else {
            writeBackConvertedValueToJson(sb, field.getTypeIn(), originalValue);
        }
    }

    private void writeBackConvertedArrayList(StringBuilder sb, FastJsonResponse.Field<?, ?> field, ArrayList<?> list) {
        sb.append("[");
        int size = list.size();
        for (int i = 0; i < size; i += VERSION_CODE) {
            if (i != 0) {
                sb.append(Csv.COMMA);
            }
            writeBackConvertedValueToJson(sb, field.getTypeIn(), list.get(i));
        }
        sb.append("]");
    }

    private void writeBackConvertedValueToJson(StringBuilder sb, int type, Object value) {
        switch (type) {
            case Action.UNKNOWN /*0*/:
            case VERSION_CODE /*1*/:
            case Type.INDEFINITELY /*2*/:
            case Type.CUSTOM /*3*/:
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                sb.append(value);
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                sb.append("\"").append(JsonUtils.escapeString(value.toString())).append("\"");
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                sb.append("\"").append(Base64Utils.encode((byte[]) value)).append("\"");
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                sb.append("\"").append(Base64Utils.encodeUrlSafe((byte[]) value));
                sb.append("\"");
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                MapUtils.writeStringMapToJson(sb, (HashMap) value);
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + type);
        }
    }

    private <O> String getOriginalValueAsString(FastJsonResponse.Field<?, O> field, O value) {
        return String.valueOf(getOriginalValue(field, value));
    }

    public static HashMap<String, String> convertBundleToStringMap(Bundle bundle) {
        HashMap<String, String> stringMap = new HashMap();
        for (String key : bundle.keySet()) {
            stringMap.put(key, bundle.getString(key));
        }
        return stringMap;
    }

    public static Bundle convertStringMapToBundle(HashMap<String, String> map) {
        Bundle bundle = new Bundle();
        for (String key : map.keySet()) {
            bundle.putString(key, (String) map.get(key));
        }
        return bundle;
    }
}
