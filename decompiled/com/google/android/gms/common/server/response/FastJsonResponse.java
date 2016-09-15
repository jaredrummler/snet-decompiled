package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.server.response.FastParser.ParseException;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.Endpoints;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.android.snet.Csv;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public abstract class FastJsonResponse {
    protected static final String QUOTE = "\"";
    private static final String TAG = "FastJsonResponse";
    private boolean mHasResponse;
    private byte[] mResponseBody;
    private int mResponseCode;

    public interface FieldConverter<I, O> {
        O convert(I i);

        I convertBack(O o);

        int getTypeIn();

        int getTypeOut();
    }

    @Class(creator = "FieldCreator")
    @VisibleForTesting
    public static class Field<I, O> implements SafeParcelable {
        public static final FieldCreator CREATOR;
        private static final int UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID = -1;
        private static final int VERSION_CODE = 1;
        protected final Class<? extends FastJsonResponse> mConcreteType;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)
        protected final String mConcreteTypeName;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        private FieldConverter<I, O> mConverter;
        private FieldMappingDictionary mDictionary;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getOutputFieldName", id = 6)
        protected final String mOutputFieldName;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)
        protected final int mSafeParcelableFieldId;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeIn", id = 2)
        protected final int mTypeIn;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeInArray", id = 3)
        protected final boolean mTypeInArray;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "getTypeOut", id = 4)
        protected final int mTypeOut;
        @com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field(getter = "isTypeOutArray", id = 5)
        protected final boolean mTypeOutArray;
        @VersionField(getter = "getVersionCode", id = 1)
        private final int mVersionCode;

        @Constructor
        Field(@Param(id = 1) int versionCode, @Param(id = 2) int typeIn, @Param(id = 3) boolean typeInArray, @Param(id = 4) int typeOut, @Param(id = 5) boolean typeOutArray, @Param(id = 6) String outputFieldName, @Param(id = 7) int safeParcelableFieldId, @Param(id = 8) String concreteTypeName, @Param(id = 9) ConverterWrapper wrappedConverter) {
            this.mVersionCode = versionCode;
            this.mTypeIn = typeIn;
            this.mTypeInArray = typeInArray;
            this.mTypeOut = typeOut;
            this.mTypeOutArray = typeOutArray;
            this.mOutputFieldName = outputFieldName;
            this.mSafeParcelableFieldId = safeParcelableFieldId;
            if (concreteTypeName == null) {
                this.mConcreteType = null;
                this.mConcreteTypeName = null;
            } else {
                this.mConcreteType = SafeParcelResponse.class;
                this.mConcreteTypeName = concreteTypeName;
            }
            if (wrappedConverter == null) {
                this.mConverter = null;
            } else {
                this.mConverter = wrappedConverter.unwrap();
            }
        }

        protected Field(int typeIn, boolean typeInArray, int typeOut, boolean typeOutArray, String outputFieldName, int safeParcelableFieldId, Class<? extends FastJsonResponse> concreteType, FieldConverter<I, O> converter) {
            this.mVersionCode = VERSION_CODE;
            this.mTypeIn = typeIn;
            this.mTypeInArray = typeInArray;
            this.mTypeOut = typeOut;
            this.mTypeOutArray = typeOutArray;
            this.mOutputFieldName = outputFieldName;
            this.mSafeParcelableFieldId = safeParcelableFieldId;
            this.mConcreteType = concreteType;
            if (concreteType == null) {
                this.mConcreteTypeName = null;
            } else {
                this.mConcreteTypeName = concreteType.getCanonicalName();
            }
            this.mConverter = converter;
        }

        public Field<I, O> copyForDictionary() {
            return new Field(this.mVersionCode, this.mTypeIn, this.mTypeInArray, this.mTypeOut, this.mTypeOutArray, this.mOutputFieldName, this.mSafeParcelableFieldId, this.mConcreteTypeName, getWrappedConverter());
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public int getTypeIn() {
            return this.mTypeIn;
        }

        public boolean isTypeInArray() {
            return this.mTypeInArray;
        }

        public int getTypeOut() {
            return this.mTypeOut;
        }

        public boolean isTypeOutArray() {
            return this.mTypeOutArray;
        }

        public String getOutputFieldName() {
            return this.mOutputFieldName;
        }

        public int getSafeParcelableFieldId() {
            return this.mSafeParcelableFieldId;
        }

        public boolean isValidSafeParcelableFieldId() {
            return this.mSafeParcelableFieldId != UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID;
        }

        public Class<? extends FastJsonResponse> getConcreteType() {
            return this.mConcreteType;
        }

        String getConcreteTypeName() {
            if (this.mConcreteTypeName == null) {
                return null;
            }
            return this.mConcreteTypeName;
        }

        public boolean hasConverter() {
            return this.mConverter != null;
        }

        public void setFieldMappingDictionary(FieldMappingDictionary dictionary) {
            this.mDictionary = dictionary;
        }

        ConverterWrapper getWrappedConverter() {
            if (this.mConverter == null) {
                return null;
            }
            return ConverterWrapper.wrap(this.mConverter);
        }

        public FastJsonResponse newConcreteTypeInstance() throws InstantiationException, IllegalAccessException {
            if (this.mConcreteType != SafeParcelResponse.class) {
                return (FastJsonResponse) this.mConcreteType.newInstance();
            }
            Preconditions.checkNotNull(this.mDictionary, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
            return new SafeParcelResponse(this.mDictionary, this.mConcreteTypeName);
        }

        public Map<String, Field<?, ?>> getConcreteTypeFieldMappingFromDictionary() {
            Preconditions.checkNotNull(this.mConcreteTypeName);
            Preconditions.checkNotNull(this.mDictionary);
            return this.mDictionary.getFieldMapping(this.mConcreteTypeName);
        }

        public O convert(I input) {
            return this.mConverter.convert(input);
        }

        public I convertBack(O output) {
            return this.mConverter.convertBack(output);
        }

        @VisibleForTesting
        public static Field<Integer, Integer> forInteger(String outputFieldName, int safeParcelableId) {
            return new Field(0, false, 0, false, outputFieldName, safeParcelableId, null, null);
        }

        @VisibleForTesting
        public static Field<ArrayList<Integer>, ArrayList<Integer>> forIntegers(String outputFieldName, int safeParcelableId) {
            return new Field(0, true, 0, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<BigInteger, BigInteger> forBigInteger(String outputFieldName, int safeParcelableId) {
            return new Field(VERSION_CODE, false, VERSION_CODE, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<BigInteger>, ArrayList<BigInteger>> forBigIntegers(String outputFieldName, int safeParcelableId) {
            return new Field(0, true, VERSION_CODE, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<Long, Long> forLong(String outputFieldName, int safeParcelableId) {
            return new Field(2, false, 2, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<Long>, ArrayList<Long>> forLongs(String outputFieldName, int safeParcelableId) {
            return new Field(2, true, 2, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<Float, Float> forFloat(String outputFieldName, int safeParcelableId) {
            return new Field(3, false, 3, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<Float>, ArrayList<Float>> forFloats(String outputFieldName, int safeParcelableId) {
            return new Field(3, true, 3, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<Double, Double> forDouble(String outputFieldName, int safeParcelableId) {
            return new Field(4, false, 4, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<Double>, ArrayList<Double>> forDoubles(String outputFieldName, int safeParcelableId) {
            return new Field(4, true, 4, true, outputFieldName, safeParcelableId, null, null);
        }

        @VisibleForTesting
        public static Field<BigDecimal, BigDecimal> forBigDecimal(String outputFieldName, int safeParcelableId) {
            return new Field(5, false, 5, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<BigDecimal>, ArrayList<BigDecimal>> forBigDecimals(String outputFieldName, int safeParcelableId) {
            return new Field(5, true, 5, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<Boolean, Boolean> forBoolean(String outputFieldName, int safeParcelableId) {
            return new Field(6, false, 6, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<Boolean>, ArrayList<Boolean>> forBooleans(String outputFieldName, int safeParcelableId) {
            return new Field(6, true, 6, true, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<String, String> forString(String outputFieldName, int safeParcelableId) {
            return new Field(7, false, 7, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String outputFieldName, int safeParcelableId) {
            return new Field(7, true, 7, true, outputFieldName, safeParcelableId, null, null);
        }

        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64(String outputFieldName, int safeParcelableId) {
            return new Field(8, false, 8, false, outputFieldName, safeParcelableId, null, null);
        }

        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64UrlSafe(String outputFieldName, int safeParcelableId) {
            return new Field(9, false, 9, false, outputFieldName, safeParcelableId, null, null);
        }

        public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(String outputFieldName, int safeParcelableId) {
            return new Field(10, false, 10, false, outputFieldName, safeParcelableId, null, null);
        }

        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String fieldName, int safeParcelableId, Class<T> type) {
            return new Field(11, false, 11, false, fieldName, safeParcelableId, type, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String fieldName, int safeParcelableId, Class<T> type) {
            return new Field(11, true, 11, true, fieldName, safeParcelableId, type, null);
        }

        public static <T extends FieldConverter> Field withConverter(String outputFieldName, int safeParcelableId, Class<T> converterClass, boolean inputArrayType) {
            try {
                return withConverter(outputFieldName, safeParcelableId, (FieldConverter) converterClass.newInstance(), inputArrayType);
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        }

        public static Field withConverter(String outputFieldName, FieldConverter<?, ?> converter, boolean inputArrayType) {
            return withConverter(outputFieldName, (int) UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, (FieldConverter) converter, inputArrayType);
        }

        public static Field withConverter(String outputFieldName, int safeParcelableId, FieldConverter<?, ?> converter, boolean inputArrayType) {
            return new Field(converter.getTypeIn(), inputArrayType, converter.getTypeOut(), false, outputFieldName, safeParcelableId, null, converter);
        }

        public static Field<Integer, Integer> forInteger(String outputFieldName) {
            return new Field(0, false, 0, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<Integer>, ArrayList<Integer>> forIntegers(String outputFieldName) {
            return new Field(0, true, 0, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<BigInteger, BigInteger> forBigInteger(String outputFieldName) {
            return new Field(VERSION_CODE, false, VERSION_CODE, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<BigInteger>, ArrayList<BigInteger>> forBigIntegers(String outputFieldName) {
            return new Field(0, true, VERSION_CODE, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        @VisibleForTesting
        public static Field<Long, Long> forLong(String outputFieldName) {
            return new Field(2, false, 2, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        @VisibleForTesting
        public static Field<ArrayList<Long>, ArrayList<Long>> forLongs(String outputFieldName) {
            return new Field(2, true, 2, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<Float, Float> forFloat(String outputFieldName) {
            return new Field(3, false, 3, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<Float>, ArrayList<Float>> forFloats(String outputFieldName) {
            return new Field(3, true, 3, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<Double, Double> forDouble(String outputFieldName) {
            return new Field(4, false, 4, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<Double>, ArrayList<Double>> forDoubles(String outputFieldName) {
            return new Field(4, true, 4, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<BigDecimal, BigDecimal> forBigDecimal(String outputFieldName) {
            return new Field(5, false, 5, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<BigDecimal>, ArrayList<BigDecimal>> forBigDecimals(String outputFieldName) {
            return new Field(5, true, 5, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<Boolean, Boolean> forBoolean(String outputFieldName) {
            return new Field(6, false, 6, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<Boolean>, ArrayList<Boolean>> forBooleans(String outputFieldName) {
            return new Field(6, true, 6, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<String, String> forString(String outputFieldName) {
            return new Field(7, false, 7, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<ArrayList<String>, ArrayList<String>> forStrings(String outputFieldName) {
            return new Field(7, true, 7, true, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<byte[], byte[]> forBase64(String outputFieldName) {
            return new Field(8, false, 8, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        @VisibleForTesting
        public static Field<byte[], byte[]> forBase64UrlSafe(String outputFieldName) {
            return new Field(9, false, 9, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(String outputFieldName) {
            return new Field(10, false, 10, false, outputFieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, null, null);
        }

        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(String fieldName, Class<T> type) {
            return new Field(11, false, 11, false, fieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, type, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(String fieldName, Class<T> type) {
            return new Field(11, true, 11, true, fieldName, UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, type, null);
        }

        public static <T extends FieldConverter> Field withConverter(String outputFieldName, Class<T> converterClass, boolean inputArrayType) {
            return withConverter(outputFieldName, (int) UNSPECIFIED_SAFE_PARCELABLE_FIELD_ID, (Class) converterClass, inputArrayType);
        }

        static {
            CREATOR = new FieldCreator();
        }

        public int describeContents() {
            FieldCreator fieldCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            FieldCreator fieldCreator = CREATOR;
            FieldCreator.writeToParcel(this, out, flags);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.mVersionCode).append('\n');
            sb.append("                 typeIn=").append(this.mTypeIn).append('\n');
            sb.append("            typeInArray=").append(this.mTypeInArray).append('\n');
            sb.append("                typeOut=").append(this.mTypeOut).append('\n');
            sb.append("           typeOutArray=").append(this.mTypeOutArray).append('\n');
            sb.append("        outputFieldName=").append(this.mOutputFieldName).append('\n');
            sb.append("      safeParcelFieldId=").append(this.mSafeParcelableFieldId).append('\n');
            sb.append("       concreteTypeName=").append(getConcreteTypeName()).append('\n');
            if (getConcreteType() != null) {
                sb.append("     concreteType.class=").append(getConcreteType().getCanonicalName()).append('\n');
            }
            sb.append("          converterName=").append(this.mConverter == null ? "null" : this.mConverter.getClass().getCanonicalName()).append('\n');
            return sb.toString();
        }
    }

    public interface FieldType {
        public static final int BASE64 = 8;
        public static final int BASE64_URL_SAFE = 9;
        public static final int BIG_DECIMAL = 5;
        public static final int BIG_INTEGER = 1;
        public static final int BOOLEAN = 6;
        public static final int CONCRETE_TYPE = 11;
        public static final int DOUBLE = 4;
        public static final int FLOAT = 3;
        public static final int INT = 0;
        public static final int LONG = 2;
        public static final int STRING = 7;
        public static final int STRING_MAP = 10;
    }

    public abstract Map<String, Field<?, ?>> getFieldMappings();

    protected abstract Object getValueObject(String str);

    protected abstract boolean isPrimitiveFieldSet(String str);

    public <T extends FastJsonResponse> void parseNetworkResponse(int code, byte[] body) throws ParseException {
        this.mResponseCode = code;
        this.mResponseBody = body;
        this.mHasResponse = true;
        InputStream is = getUnzippedStream(body);
        try {
            new FastParser().parse(is, this);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static InputStream getUnzippedStream(byte[] data) {
        InputStream is = new ByteArrayInputStream(data);
        if (!IOUtils.isGzipByteBuffer(data)) {
            return is;
        }
        try {
            return new GZIPInputStream(is);
        } catch (IOException e) {
            return is;
        }
    }

    public int getResponseCode() {
        Preconditions.checkState(this.mHasResponse);
        return this.mResponseCode;
    }

    public byte[] getResponseBody() {
        byte[] toByteArray;
        Throwable th;
        Preconditions.checkState(this.mHasResponse);
        InputStream inputStream = null;
        try {
            InputStream is = new GZIPInputStream(new ByteArrayInputStream(this.mResponseBody));
            try {
                byte[] data = new byte[PeopleColumnBitmask.GIVEN_NAME];
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                while (true) {
                    int size = is.read(data, 0, data.length);
                    if (size == -1) {
                        break;
                    }
                    buffer.write(data, 0, size);
                }
                buffer.flush();
                toByteArray = buffer.toByteArray();
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
                inputStream = is;
            } catch (IOException e2) {
                inputStream = is;
                try {
                    toByteArray = this.mResponseBody;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    return toByteArray;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = is;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (IOException e5) {
            toByteArray = this.mResponseBody;
            if (inputStream != null) {
                inputStream.close();
            }
            return toByteArray;
        }
        return toByteArray;
    }

    protected boolean isFieldSet(Field field) {
        if (field.getTypeOut() != 11) {
            return isPrimitiveFieldSet(field.getOutputFieldName());
        }
        if (field.isTypeOutArray()) {
            return isConcreteTypeArrayFieldSet(field.getOutputFieldName());
        }
        return isConcreteTypeFieldSet(field.getOutputFieldName());
    }

    protected boolean isConcreteTypeFieldSet(String outputField) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean isConcreteTypeArrayFieldSet(String outputField) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    private <I, O> void setConvertedValue(Field<I, O> field, I input) {
        String name = field.getOutputFieldName();
        O output = field.convert(input);
        switch (field.getTypeOut()) {
            case Action.UNKNOWN /*0*/:
                if (isOutputNonNull(name, output)) {
                    setIntegerInternal(field, name, ((Integer) output).intValue());
                }
            case Type.TEMPORARY /*1*/:
                setBigIntegerInternal(field, name, (BigInteger) output);
            case Type.INDEFINITELY /*2*/:
                if (isOutputNonNull(name, output)) {
                    setLongInternal(field, name, ((Long) output).longValue());
                }
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                if (isOutputNonNull(name, output)) {
                    setDoubleInternal(field, name, ((Double) output).doubleValue());
                }
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                setBigDecimalInternal(field, name, (BigDecimal) output);
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                if (isOutputNonNull(name, output)) {
                    setBooleanInternal(field, name, ((Boolean) output).booleanValue());
                }
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                setStringInternal(field, name, (String) output);
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                if (isOutputNonNull(name, output)) {
                    setDecodedBytesInternal(field, name, (byte[]) output);
                }
            default:
                throw new IllegalStateException("Unsupported type for conversion: " + field.getTypeOut());
        }
    }

    protected <O, I> I getOriginalValue(Field<I, O> field, Object convertedValue) {
        if (field.mConverter != null) {
            return field.convertBack(convertedValue);
        }
        return convertedValue;
    }

    public final <O> void setInteger(Field<Integer, O> field, int value) {
        if (field.mConverter != null) {
            setConvertedValue(field, Integer.valueOf(value));
        } else {
            setIntegerInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setIntegers(Field<ArrayList<Integer>, O> field, ArrayList<Integer> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setIntegersInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setBigInteger(Field<BigInteger, O> field, BigInteger value) {
        if (field.mConverter != null) {
            setConvertedValue(field, value);
        } else {
            setBigIntegerInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setBigIntegers(Field<ArrayList<BigInteger>, O> field, ArrayList<BigInteger> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setBigIntegersInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setLong(Field<Long, O> field, long value) {
        if (field.mConverter != null) {
            setConvertedValue(field, Long.valueOf(value));
        } else {
            setLongInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setLongs(Field<ArrayList<Long>, O> field, ArrayList<Long> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setLongsInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setFloat(Field<Float, O> field, float value) {
        if (field.mConverter != null) {
            setConvertedValue(field, Float.valueOf(value));
        } else {
            setFloatInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setFloats(Field<ArrayList<Float>, O> field, ArrayList<Float> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setFloatsInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setDouble(Field<Double, O> field, double value) {
        if (field.mConverter != null) {
            setConvertedValue(field, Double.valueOf(value));
        } else {
            setDoubleInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setDoubles(Field<ArrayList<Double>, O> field, ArrayList<Double> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setDoublesInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setBigDecimal(Field<BigDecimal, O> field, BigDecimal value) {
        if (field.mConverter != null) {
            setConvertedValue(field, value);
        } else {
            setBigDecimalInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setBigDecimals(Field<ArrayList<BigDecimal>, O> field, ArrayList<BigDecimal> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setBigDecimalsInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setBoolean(Field<Boolean, O> field, boolean value) {
        if (field.mConverter != null) {
            setConvertedValue(field, Boolean.valueOf(value));
        } else {
            setBooleanInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setBooleans(Field<ArrayList<Boolean>, O> field, ArrayList<Boolean> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setBooleansInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setString(Field<String, O> field, String value) {
        if (field.mConverter != null) {
            setConvertedValue(field, value);
        } else {
            setStringInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setStrings(Field<ArrayList<String>, O> field, ArrayList<String> values) {
        if (field.mConverter != null) {
            setConvertedValue(field, values);
        } else {
            setStringsInternal(field, field.getOutputFieldName(), values);
        }
    }

    public final <O> void setDecodedBytes(Field<byte[], O> field, byte[] value) {
        if (field.mConverter != null) {
            setConvertedValue(field, value);
        } else {
            setDecodedBytesInternal(field, field.getOutputFieldName(), value);
        }
    }

    public final <O> void setStringMap(Field<Map<String, String>, O> field, Map<String, String> value) {
        if (field.mConverter != null) {
            setConvertedValue(field, value);
        } else {
            setStringMapInternal(field, field.getOutputFieldName(), value);
        }
    }

    protected void setIntegerInternal(Field<?, ?> field, String outputField, int value) {
        setInteger(outputField, value);
    }

    protected void setIntegersInternal(Field<?, ?> field, String outputField, ArrayList<Integer> values) {
        setIntegers(outputField, (ArrayList) values);
    }

    protected void setBigIntegerInternal(Field<?, ?> field, String outputField, BigInteger value) {
        setBigInteger(outputField, value);
    }

    protected void setBigIntegersInternal(Field<?, ?> field, String outputField, ArrayList<BigInteger> values) {
        setBigIntegers(outputField, (ArrayList) values);
    }

    protected void setLongInternal(Field<?, ?> field, String outputField, long value) {
        setLong(outputField, value);
    }

    protected void setLongsInternal(Field<?, ?> field, String outputField, ArrayList<Long> values) {
        setLongs(outputField, (ArrayList) values);
    }

    protected void setFloatInternal(Field<?, ?> field, String outputField, float value) {
        setFloat(outputField, value);
    }

    protected void setFloatsInternal(Field<?, ?> field, String outputField, ArrayList<Float> values) {
        setFloats(outputField, (ArrayList) values);
    }

    protected void setDoubleInternal(Field<?, ?> field, String outputField, double value) {
        setDouble(outputField, value);
    }

    protected void setDoublesInternal(Field<?, ?> field, String outputField, ArrayList<Double> values) {
        setDoubles(outputField, (ArrayList) values);
    }

    protected void setBigDecimalInternal(Field<?, ?> field, String outputField, BigDecimal value) {
        setBigDecimal(outputField, value);
    }

    protected void setBigDecimalsInternal(Field<?, ?> field, String outputField, ArrayList<BigDecimal> values) {
        setBigDecimals(outputField, (ArrayList) values);
    }

    protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
        setBoolean(outputField, value);
    }

    protected void setBooleansInternal(Field<?, ?> field, String outputField, ArrayList<Boolean> values) {
        setBooleans(outputField, (ArrayList) values);
    }

    protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
        setString(outputField, value);
    }

    protected void setStringsInternal(Field<?, ?> field, String outputField, ArrayList<String> values) {
        setStrings(outputField, (ArrayList) values);
    }

    protected void setDecodedBytesInternal(Field<?, ?> field, String outputField, byte[] value) {
        setDecodedBytes(outputField, value);
    }

    protected void setStringMapInternal(Field<?, ?> field, String outputField, Map<String, String> value) {
        setStringMap(outputField, (Map) value);
    }

    private <O> boolean isOutputNonNull(String outputField, O output) {
        if (output != null) {
            return true;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "Output field (" + outputField + ") has a null value," + " but expected a primitive");
        }
        return false;
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
        addConcreteType(outputField, value);
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String outputField, ArrayList<T> values) {
        addConcreteTypeArray(outputField, values);
    }

    protected void setInteger(String outputField, int value) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    protected void setIntegers(String outputField, ArrayList<Integer> arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    protected void setBigInteger(String outputField, BigInteger value) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    protected void setBigIntegers(String outputField, ArrayList<BigInteger> arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    protected void setLong(String outputField, long value) {
        throw new UnsupportedOperationException("Long not supported");
    }

    protected void setLongs(String outputField, ArrayList<Long> arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    protected void setFloat(String outputField, float value) {
        throw new UnsupportedOperationException("Float not supported");
    }

    protected void setFloats(String outputField, ArrayList<Float> arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    protected void setDouble(String outputField, double value) {
        throw new UnsupportedOperationException("Double not supported");
    }

    protected void setDoubles(String outputField, ArrayList<Double> arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    protected void setBigDecimal(String outputField, BigDecimal value) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    protected void setBigDecimals(String outputField, ArrayList<BigDecimal> arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    protected void setBoolean(String outputField, boolean value) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    protected void setBooleans(String outputField, ArrayList<Boolean> arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    protected void setString(String outputField, String value) {
        throw new UnsupportedOperationException("String not supported");
    }

    protected void setStrings(String outputField, ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    protected void setDecodedBytes(String outputField, byte[] value) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    protected void setStringMap(String outputField, Map<String, String> map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    public <T extends FastJsonResponse> void addConcreteType(String field, T t) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    public <T extends FastJsonResponse> void addConcreteTypeArray(String field, ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    public HashMap<String, Object> getConcreteTypes() {
        return null;
    }

    public HashMap<String, Object> getConcreteTypeArrays() {
        return null;
    }

    public String toString() {
        Map<String, Field<?, ?>> mappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String fieldName : mappings.keySet()) {
            Field field = (Field) mappings.get(fieldName);
            if (isFieldSet(field)) {
                HashMap<String, String> value = getOriginalValue(field, getFieldValue(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(Csv.COMMA);
                }
                sb.append(QUOTE).append(fieldName).append("\":");
                if (value != null) {
                    switch (field.getTypeOut()) {
                        case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                            sb.append(QUOTE).append(Base64Utils.encode((byte[]) value)).append(QUOTE);
                            break;
                        case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                            sb.append(QUOTE).append(Base64Utils.encodeUrlSafe((byte[]) value)).append(QUOTE);
                            break;
                        case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                            MapUtils.writeStringMapToJson(sb, value);
                            break;
                        default:
                            if (!field.isTypeInArray()) {
                                appendValue(sb, field, value);
                                break;
                            }
                            appendValueArray(sb, field, (ArrayList) value);
                            break;
                    }
                }
                sb.append("null");
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    protected Object getFieldValue(Field field) {
        String outputName = field.getOutputFieldName();
        if (field.getConcreteType() == null) {
            return getValueObject(field.getOutputFieldName());
        }
        Preconditions.checkState(getValueObject(field.getOutputFieldName()) == null, "Concrete field shouldn't be value object: %s", field.getOutputFieldName());
        Map<String, Object> types = field.isTypeOutArray() ? getConcreteTypeArrays() : getConcreteTypes();
        if (types != null) {
            return types.get(outputName);
        }
        try {
            return getClass().getMethod(Endpoints.ENDPOINT_GET + Character.toUpperCase(outputName.charAt(0)) + outputName.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void appendValue(StringBuilder sb, Field field, Object value) {
        if (field.getTypeIn() == 11) {
            sb.append(((FastJsonResponse) field.getConcreteType().cast(value)).toString());
        } else if (field.getTypeIn() == 7) {
            sb.append(QUOTE);
            sb.append(JsonUtils.escapeString((String) value));
            sb.append(QUOTE);
        } else {
            sb.append(value);
        }
    }

    private void appendValueArray(StringBuilder sb, Field field, ArrayList<Object> values) {
        sb.append("[");
        int len = values.size();
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                sb.append(Csv.COMMA);
            }
            Object elementValue = values.get(i);
            if (elementValue != null) {
                appendValue(sb, field, elementValue);
            }
        }
        sb.append("]");
    }

    public PostProcessor<? extends FastJsonResponse> getPostProcessor() {
        return null;
    }
}
