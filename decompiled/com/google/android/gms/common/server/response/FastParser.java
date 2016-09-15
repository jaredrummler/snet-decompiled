package com.google.android.gms.common.server.response;

import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.Base64Utils;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class FastParser<T extends FastJsonResponse> {
    private static final char[] BASE64_ALLOWED_CONTROL_CHARS;
    private static final ParseHelper<BigDecimal> BIG_DECIMAL_PARSE_HELPER;
    private static final ParseHelper<BigInteger> BIG_INTEGER_PARSE_HELPER;
    private static final ParseHelper<Boolean> BOOLEAN_PARSE_HELPER;
    private static final boolean DBG = false;
    private static final ParseHelper<Double> DOUBLE_PARSE_HELPER;
    private static final char END = '\u0000';
    private static final char END_ARRAY = ']';
    private static final char END_OBJECT = '}';
    private static final char ESCAPE = '\\';
    private static final char FALSE_START = 'f';
    private static final char[] FALSE_TRAIL;
    private static final char[] FALSE_TRAIL_IN_STRING;
    private static final char FIELD_SEPARATOR = ',';
    private static final ParseHelper<Float> FLOAT_PARSE_HELPER;
    private static final ParseHelper<Integer> INTEGER_PARSE_HELPER;
    private static final int IN_ARRAY = 5;
    private static final int IN_FIELD = 2;
    private static final int IN_KEY = 3;
    private static final int IN_OBJECT = 1;
    private static final int IN_VALUE = 4;
    private static final char KEY_VALUE_SEPARATOR = ':';
    private static final int LARGE_STRING_READ_BUFFER_SIZE = 1024;
    private static final ParseHelper<Long> LONG_PARSE_HELPER;
    private static final char NULL_START = 'n';
    private static final char[] NULL_TRAIL;
    private static final int PARSING = 0;
    private static final int SMALL_STRING_READ_BUFFER_SIZE = 32;
    private static final char START_ARRAY = '[';
    private static final char START_OBJECT = '{';
    private static final char STRING_BOUNDARY = '\"';
    private static final ParseHelper<String> STRING_PARSE_HELPER;
    private static final String TAG = "FastParser";
    private static final char TRUE_START = 't';
    private static final char[] TRUE_TRAIL;
    private static final char[] TRUE_TRAIL_IN_STRING;
    private final char[] mChar;
    private final char[] mLargeBuf;
    private final StringBuilder mLargeSb;
    private final char[] mSmallBuf;
    private final StringBuilder mSmallSb;
    private final Stack<Integer> mStateStack;

    private interface ParseHelper<O> {
        O parse(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    public static class ParseException extends Exception {
        public ParseException(String message) {
            super(message);
        }

        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }

        public ParseException(Throwable cause) {
            super(cause);
        }
    }

    static {
        NULL_TRAIL = new char[]{'u', 'l', 'l'};
        TRUE_TRAIL = new char[]{'r', 'u', 'e'};
        TRUE_TRAIL_IN_STRING = new char[]{'r', 'u', 'e', STRING_BOUNDARY};
        FALSE_TRAIL = new char[]{'a', 'l', 's', 'e'};
        FALSE_TRAIL_IN_STRING = new char[]{'a', 'l', 's', 'e', STRING_BOUNDARY};
        char[] cArr = new char[IN_OBJECT];
        cArr[PARSING] = '\n';
        BASE64_ALLOWED_CONTROL_CHARS = cArr;
        INTEGER_PARSE_HELPER = new ParseHelper<Integer>() {
            public Integer parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return Integer.valueOf(parser.parseInt(in));
            }
        };
        LONG_PARSE_HELPER = new ParseHelper<Long>() {
            public Long parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return Long.valueOf(parser.parseLong(in));
            }
        };
        FLOAT_PARSE_HELPER = new ParseHelper<Float>() {
            public Float parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return Float.valueOf(parser.parseFloat(in));
            }
        };
        DOUBLE_PARSE_HELPER = new ParseHelper<Double>() {
            public Double parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return Double.valueOf(parser.parseDouble(in));
            }
        };
        BOOLEAN_PARSE_HELPER = new ParseHelper<Boolean>() {
            public Boolean parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return Boolean.valueOf(parser.parseBoolean(in, FastParser.DBG));
            }
        };
        STRING_PARSE_HELPER = new ParseHelper<String>() {
            public String parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return parser.parseString(in);
            }
        };
        BIG_INTEGER_PARSE_HELPER = new ParseHelper<BigInteger>() {
            public BigInteger parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return parser.parseBigInteger(in);
            }
        };
        BIG_DECIMAL_PARSE_HELPER = new ParseHelper<BigDecimal>() {
            public BigDecimal parse(FastParser parser, BufferedReader in) throws ParseException, IOException {
                return parser.parseBigDecimal(in);
            }
        };
    }

    public FastParser() {
        this.mChar = new char[IN_OBJECT];
        this.mSmallBuf = new char[SMALL_STRING_READ_BUFFER_SIZE];
        this.mLargeBuf = new char[LARGE_STRING_READ_BUFFER_SIZE];
        this.mSmallSb = new StringBuilder(SMALL_STRING_READ_BUFFER_SIZE);
        this.mLargeSb = new StringBuilder(LARGE_STRING_READ_BUFFER_SIZE);
        this.mStateStack = new Stack();
    }

    public void parse(InputStream is, T response) throws ParseException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is), LARGE_STRING_READ_BUFFER_SIZE);
        try {
            this.mStateStack.push(Integer.valueOf(PARSING));
            parseInternal(in, response);
            assumePop(PARSING);
            try {
                in.close();
            } catch (IOException e) {
                Log.w(TAG, "Failed to close reader while parsing.");
            }
        } catch (Throwable ioe) {
            throw new ParseException(ioe);
        } catch (Throwable th) {
            try {
                in.close();
            } catch (IOException e2) {
                Log.w(TAG, "Failed to close reader while parsing.");
            }
        }
    }

    public void parse(String json, T response) throws ParseException {
        InputStream bis = new ByteArrayInputStream(json.getBytes());
        try {
            parse(bis, (FastJsonResponse) response);
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                Log.w(TAG, "Failed to close the input stream while parsing.");
            }
        }
    }

    private void parseInternal(BufferedReader in, T response) throws ParseException, IOException {
        char next = readNextNonWhitespaceChar(in);
        switch (next) {
            case PARSING /*0*/:
                throw new ParseException("No data to parse");
            case LogSource.BATTERY_STATS /*91*/:
                this.mStateStack.push(Integer.valueOf(IN_ARRAY));
                parseArray(in, (FastJsonResponse) response);
            case LogSource.ANDROID_SNET_IDLE /*123*/:
                this.mStateStack.push(Integer.valueOf(IN_OBJECT));
                parseObject(in, response);
            default:
                throw new ParseException("Unexpected token: " + next);
        }
    }

    private void parseArray(BufferedReader in, FastJsonResponse response) throws ParseException, IOException {
        Map<String, Field<?, ?>> fields = response.getFieldMappings();
        if (fields.size() != IN_OBJECT) {
            throw new ParseException("Object array response class must have a single Field");
        }
        Field field = (Field) ((Entry) fields.entrySet().iterator().next()).getValue();
        response.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), parseObjectArray(in, field));
    }

    private boolean parseObject(BufferedReader in, FastJsonResponse response) throws ParseException, IOException {
        Map<String, Field<?, ?>> fields = response.getFieldMappings();
        String fieldKey = readFieldKey(in);
        if (fieldKey == null) {
            assumePop(IN_OBJECT);
            return DBG;
        }
        while (fieldKey != null) {
            Field field = (Field) fields.get(fieldKey);
            if (field == null) {
                fieldKey = skipToNextField(in);
            } else {
                char next;
                this.mStateStack.push(Integer.valueOf(IN_VALUE));
                switch (field.getTypeIn()) {
                    case PARSING /*0*/:
                        if (!field.isTypeInArray()) {
                            response.setInteger(field, parseInt(in));
                            break;
                        }
                        response.setIntegers(field, parseArray(in, INTEGER_PARSE_HELPER));
                        break;
                    case IN_OBJECT /*1*/:
                        if (!field.isTypeInArray()) {
                            response.setBigInteger(field, parseBigInteger(in));
                            break;
                        }
                        response.setBigIntegers(field, parseArray(in, BIG_INTEGER_PARSE_HELPER));
                        break;
                    case IN_FIELD /*2*/:
                        if (!field.isTypeInArray()) {
                            response.setLong(field, parseLong(in));
                            break;
                        }
                        response.setLongs(field, parseArray(in, LONG_PARSE_HELPER));
                        break;
                    case IN_KEY /*3*/:
                        if (!field.isTypeInArray()) {
                            response.setFloat(field, parseFloat(in));
                            break;
                        }
                        response.setFloats(field, parseArray(in, FLOAT_PARSE_HELPER));
                        break;
                    case IN_VALUE /*4*/:
                        if (!field.isTypeInArray()) {
                            response.setDouble(field, parseDouble(in));
                            break;
                        }
                        response.setDoubles(field, parseArray(in, DOUBLE_PARSE_HELPER));
                        break;
                    case IN_ARRAY /*5*/:
                        if (!field.isTypeInArray()) {
                            response.setBigDecimal(field, parseBigDecimal(in));
                            break;
                        }
                        response.setBigDecimals(field, parseArray(in, BIG_DECIMAL_PARSE_HELPER));
                        break;
                    case Type.REMOVE_SHARE /*6*/:
                        if (!field.isTypeInArray()) {
                            response.setBoolean(field, parseBoolean(in, DBG));
                            break;
                        }
                        response.setBooleans(field, parseArray(in, BOOLEAN_PARSE_HELPER));
                        break;
                    case Type.RESET_TIME /*7*/:
                        if (!field.isTypeInArray()) {
                            response.setString(field, parseString(in));
                            break;
                        }
                        response.setStrings(field, parseArray(in, STRING_PARSE_HELPER));
                        break;
                    case Type.TAP_GET_LINK /*8*/:
                        response.setDecodedBytes(field, Base64Utils.decode(parseString(in, this.mLargeBuf, this.mLargeSb, BASE64_ALLOWED_CONTROL_CHARS)));
                        break;
                    case Type.CREATE_LINK /*9*/:
                        response.setDecodedBytes(field, Base64Utils.decodeUrlSafe(parseString(in, this.mLargeBuf, this.mLargeSb, BASE64_ALLOWED_CONTROL_CHARS)));
                        break;
                    case Type.TAP_ABOUT /*10*/:
                        response.setStringMap(field, parseStringMap(in));
                        break;
                    case Type.TAP_LEARN_MORE /*11*/:
                        if (field.isTypeInArray()) {
                            next = readNextNonWhitespaceChar(in);
                            if (next != NULL_START) {
                                this.mStateStack.push(Integer.valueOf(IN_ARRAY));
                                if (next == START_ARRAY) {
                                    response.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), parseObjectArray(in, field));
                                    break;
                                }
                                throw new ParseException("Expected array start");
                            }
                            expectChars(in, NULL_TRAIL);
                            response.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), null);
                            break;
                        }
                        char nextChar = readNextNonWhitespaceChar(in);
                        if (nextChar == NULL_START) {
                            expectChars(in, NULL_TRAIL);
                            response.addConcreteTypeInternal(field, field.getOutputFieldName(), null);
                            break;
                        }
                        this.mStateStack.push(Integer.valueOf(IN_OBJECT));
                        if (nextChar != START_OBJECT) {
                            throw new ParseException("Expected start of object");
                        }
                        try {
                            FastJsonResponse instance = field.newConcreteTypeInstance();
                            parseObject(in, instance);
                            response.addConcreteTypeInternal(field, field.getOutputFieldName(), instance);
                            break;
                        } catch (InstantiationException ie) {
                            throw new ParseException("Error instantiating inner object", ie);
                        } catch (IllegalAccessException iae) {
                            throw new ParseException("Error instantiating inner object", iae);
                        }
                    default:
                        throw new ParseException("Invalid field type " + field.getTypeIn());
                }
                assumePop(IN_VALUE);
                assumePop(IN_FIELD);
                next = readNextNonWhitespaceChar(in);
                switch (next) {
                    case LogSource.GOKART /*44*/:
                        fieldKey = readFieldKey(in);
                        break;
                    case LogSource.CONTEXT_MANAGER /*125*/:
                        fieldKey = null;
                        break;
                    default:
                        throw new ParseException("Expected end of object or field separator, but found: " + next);
                }
            }
        }
        PostProcessor processor = response.getPostProcessor();
        if (processor != null) {
            response = processor.postProcess(response);
        }
        assumePop(IN_OBJECT);
        return true;
    }

    private String readFieldKey(BufferedReader in) throws ParseException, IOException {
        String key = null;
        this.mStateStack.push(Integer.valueOf(IN_FIELD));
        char next = readNextNonWhitespaceChar(in);
        switch (next) {
            case LogSource.NOVA /*34*/:
                this.mStateStack.push(Integer.valueOf(IN_KEY));
                key = readString(in, this.mSmallBuf, this.mSmallSb, null);
                assumePop(IN_KEY);
                if (readNextNonWhitespaceChar(in) != KEY_VALUE_SEPARATOR) {
                    throw new ParseException("Expected key/value separator");
                }
                break;
            case LogSource.PROC_STATS /*93*/:
                assumePop(IN_FIELD);
                assumePop(IN_OBJECT);
                assumePop(IN_ARRAY);
                break;
            case LogSource.CONTEXT_MANAGER /*125*/:
                assumePop(IN_FIELD);
                break;
            default:
                throw new ParseException("Unexpected token: " + next);
        }
        return key;
    }

    private String skipToNextField(BufferedReader in) throws ParseException, IOException {
        char next;
        in.mark(LARGE_STRING_READ_BUFFER_SIZE);
        char currChar;
        switch (readNextNonWhitespaceChar(in)) {
            case LogSource.NOVA /*34*/:
                boolean escaping = DBG;
                if (in.read(this.mChar) == -1) {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                currChar = this.mChar[PARSING];
                do {
                    if (currChar == STRING_BOUNDARY && !escaping) {
                        break;
                    }
                    escaping = currChar == ESCAPE ? !escaping ? true : DBG : DBG;
                    if (in.read(this.mChar) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    currChar = this.mChar[PARSING];
                } while (!Character.isISOControl(currChar));
                throw new ParseException("Unexpected control character while reading string");
            case LogSource.GOKART /*44*/:
                throw new ParseException("Missing value");
            case LogSource.BATTERY_STATS /*91*/:
                this.mStateStack.push(Integer.valueOf(IN_ARRAY));
                in.mark(SMALL_STRING_READ_BUFFER_SIZE);
                if (readNextNonWhitespaceChar(in) != END_ARRAY) {
                    in.reset();
                    boolean escapingInString = DBG;
                    boolean inString = DBG;
                    int arrayDepth = IN_OBJECT;
                    while (arrayDepth > 0) {
                        currChar = readNextNonWhitespaceChar(in);
                        if (currChar == '\u0000') {
                            throw new ParseException("Unexpected EOF while parsing array");
                        } else if (Character.isISOControl(currChar)) {
                            throw new ParseException("Unexpected control character while reading array");
                        } else {
                            if (currChar == STRING_BOUNDARY && !escapingInString) {
                                inString = !inString ? true : DBG;
                            }
                            if (currChar == START_ARRAY && !inString) {
                                arrayDepth += IN_OBJECT;
                            }
                            if (currChar == END_ARRAY && !inString) {
                                arrayDepth--;
                            }
                            if (currChar == ESCAPE && inString) {
                                escapingInString = !escapingInString ? true : DBG;
                            } else {
                                escapingInString = DBG;
                            }
                        }
                    }
                    assumePop(IN_ARRAY);
                    break;
                }
                assumePop(IN_ARRAY);
                break;
                break;
            case LogSource.ANDROID_SNET_IDLE /*123*/:
                this.mStateStack.push(Integer.valueOf(IN_OBJECT));
                in.mark(SMALL_STRING_READ_BUFFER_SIZE);
                next = readNextNonWhitespaceChar(in);
                if (next == END_OBJECT) {
                    assumePop(IN_OBJECT);
                    break;
                } else if (next == STRING_BOUNDARY) {
                    in.reset();
                    readFieldKey(in);
                    do {
                    } while (skipToNextField(in) != null);
                    assumePop(IN_OBJECT);
                    break;
                } else {
                    throw new ParseException("Unexpected token " + next);
                }
            default:
                in.reset();
                readValueChars(in, this.mLargeBuf);
                break;
        }
        next = readNextNonWhitespaceChar(in);
        switch (next) {
            case LogSource.GOKART /*44*/:
                assumePop(IN_FIELD);
                return readFieldKey(in);
            case LogSource.CONTEXT_MANAGER /*125*/:
                assumePop(IN_FIELD);
                return null;
            default:
                throw new ParseException("Unexpected token " + next);
        }
    }

    private String parseString(BufferedReader in) throws ParseException, IOException {
        return parseString(in, this.mSmallBuf, this.mSmallSb, null);
    }

    private <O> ArrayList<O> parseArray(BufferedReader in, ParseHelper<O> parser) throws ParseException, IOException {
        char next = readNextNonWhitespaceChar(in);
        if (next != NULL_START) {
            if (next == START_ARRAY) {
                this.mStateStack.push(Integer.valueOf(IN_ARRAY));
                ArrayList<O> output = new ArrayList();
                while (true) {
                    in.mark(LARGE_STRING_READ_BUFFER_SIZE);
                    switch (readNextNonWhitespaceChar(in)) {
                        case PARSING /*0*/:
                            throw new ParseException("Unexpected EOF");
                        case LogSource.GOKART /*44*/:
                            break;
                        case LogSource.PROC_STATS /*93*/:
                            assumePop(IN_ARRAY);
                            return output;
                        default:
                            in.reset();
                            output.add(parser.parse(this, in));
                            break;
                    }
                }
            }
            throw new ParseException("Expected start of array");
        }
        expectChars(in, NULL_TRAIL);
        return null;
    }

    private HashMap<String, String> parseStringMap(BufferedReader in) throws ParseException, IOException {
        char next = readNextNonWhitespaceChar(in);
        if (next != NULL_START) {
            if (next == START_OBJECT) {
                this.mStateStack.push(Integer.valueOf(IN_OBJECT));
                HashMap<String, String> stringMap = new HashMap();
                while (true) {
                    switch (readNextNonWhitespaceChar(in)) {
                        case PARSING /*0*/:
                            throw new ParseException("Unexpected EOF");
                        case LogSource.NOVA /*34*/:
                            String key = readString(in, this.mSmallBuf, this.mSmallSb, null);
                            if (readNextNonWhitespaceChar(in) == KEY_VALUE_SEPARATOR) {
                                if (readNextNonWhitespaceChar(in) == STRING_BOUNDARY) {
                                    stringMap.put(key, readString(in, this.mSmallBuf, this.mSmallSb, null));
                                    next = readNextNonWhitespaceChar(in);
                                    if (next == FIELD_SEPARATOR) {
                                        break;
                                    } else if (next == END_OBJECT) {
                                        assumePop(IN_OBJECT);
                                        return stringMap;
                                    } else {
                                        throw new ParseException("Unexpected character while parsing string map: " + next);
                                    }
                                }
                                throw new ParseException("Expected String value for key " + key);
                            }
                            throw new ParseException("No map value found for key " + key);
                        case LogSource.CONTEXT_MANAGER /*125*/:
                            assumePop(IN_OBJECT);
                            return stringMap;
                        default:
                            break;
                    }
                }
            }
            throw new ParseException("Expected start of a map object");
        }
        expectChars(in, NULL_TRAIL);
        return null;
    }

    private String parseString(BufferedReader in, char[] buf, StringBuilder sb, char[] allowedControlChars) throws ParseException, IOException {
        switch (readNextNonWhitespaceChar(in)) {
            case LogSource.NOVA /*34*/:
                return readString(in, buf, sb, allowedControlChars);
            case LogSource.GMM_UE3 /*110*/:
                expectChars(in, NULL_TRAIL);
                return null;
            default:
                throw new ParseException("Expected string");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readString(java.io.BufferedReader r9, char[] r10, java.lang.StringBuilder r11, char[] r12) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
        r8 = this;
        r5 = 0;
        r11.setLength(r5);
        r2 = 0;
        r4 = 0;
        r6 = r10.length;
        r9.mark(r6);
    L_0x000a:
        r0 = r9.read(r10);
        r6 = -1;
        if (r0 == r6) goto L_0x0063;
    L_0x0011:
        r3 = 0;
    L_0x0012:
        if (r3 >= r0) goto L_0x005b;
    L_0x0014:
        r1 = r10[r3];
        r6 = java.lang.Character.isISOControl(r1);
        if (r6 == 0) goto L_0x002a;
    L_0x001c:
        r6 = r8.charArrayContains(r12, r1);
        if (r6 != 0) goto L_0x002a;
    L_0x0022:
        r5 = new com.google.android.gms.common.server.response.FastParser$ParseException;
        r6 = "Unexpected control character while reading string";
        r5.<init>(r6);
        throw r5;
    L_0x002a:
        r6 = 34;
        if (r1 != r6) goto L_0x004c;
    L_0x002e:
        if (r2 != 0) goto L_0x004c;
    L_0x0030:
        r11.append(r10, r5, r3);
        r9.reset();
        r5 = r3 + 1;
        r6 = (long) r5;
        r9.skip(r6);
        if (r4 == 0) goto L_0x0047;
    L_0x003e:
        r5 = r11.toString();
        r5 = com.google.android.gms.common.util.JsonUtils.unescapeString(r5);
    L_0x0046:
        return r5;
    L_0x0047:
        r5 = r11.toString();
        goto L_0x0046;
    L_0x004c:
        r6 = 92;
        if (r1 != r6) goto L_0x0059;
    L_0x0050:
        if (r2 != 0) goto L_0x0057;
    L_0x0052:
        r2 = 1;
    L_0x0053:
        r4 = 1;
    L_0x0054:
        r3 = r3 + 1;
        goto L_0x0012;
    L_0x0057:
        r2 = r5;
        goto L_0x0053;
    L_0x0059:
        r2 = 0;
        goto L_0x0054;
    L_0x005b:
        r11.append(r10, r5, r0);
        r6 = r10.length;
        r9.mark(r6);
        goto L_0x000a;
    L_0x0063:
        r5 = new com.google.android.gms.common.server.response.FastParser$ParseException;
        r6 = "Unexpected EOF while parsing string";
        r5.<init>(r6);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.readString(java.io.BufferedReader, char[], java.lang.StringBuilder, char[]):java.lang.String");
    }

    private boolean charArrayContains(char[] array, char desired) {
        if (array == null) {
            return DBG;
        }
        for (int i = PARSING; i < array.length; i += IN_OBJECT) {
            if (array[i] == desired) {
                return true;
            }
        }
        return DBG;
    }

    private int parseInt(BufferedReader in) throws ParseException, IOException {
        int valLen = readValueChars(in, this.mLargeBuf);
        if (valLen == 0) {
            return PARSING;
        }
        return parseInt(this.mLargeBuf, valLen);
    }

    private long parseLong(BufferedReader in) throws ParseException, IOException {
        int valLen = readValueChars(in, this.mLargeBuf);
        if (valLen == 0) {
            return 0;
        }
        return parseLong(this.mLargeBuf, valLen);
    }

    private BigInteger parseBigInteger(BufferedReader in) throws ParseException, IOException {
        int valLen = readValueChars(in, this.mLargeBuf);
        if (valLen == 0) {
            return null;
        }
        return new BigInteger(new String(this.mLargeBuf, PARSING, valLen));
    }

    private boolean parseBoolean(BufferedReader in, boolean inString) throws ParseException, IOException {
        char next = readNextNonWhitespaceChar(in);
        switch (next) {
            case LogSource.NOVA /*34*/:
                if (!inString) {
                    return parseBoolean(in, true);
                }
                throw new ParseException("No boolean value found in string");
            case LogSource.LAUNCHPAD_TOYS /*102*/:
                expectChars(in, inString ? FALSE_TRAIL_IN_STRING : FALSE_TRAIL);
                return DBG;
            case LogSource.GMM_UE3 /*110*/:
                expectChars(in, NULL_TRAIL);
                return DBG;
            case LogSource.LB_AS /*116*/:
                expectChars(in, inString ? TRUE_TRAIL_IN_STRING : TRUE_TRAIL);
                return true;
            default:
                throw new ParseException("Unexpected token: " + next);
        }
    }

    private float parseFloat(BufferedReader in) throws ParseException, IOException {
        int valLen = readValueChars(in, this.mLargeBuf);
        if (valLen == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.mLargeBuf, PARSING, valLen));
    }

    private double parseDouble(BufferedReader in) throws ParseException, IOException {
        int valLen = readValueChars(in, this.mLargeBuf);
        if (valLen == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.mLargeBuf, PARSING, valLen));
    }

    private BigDecimal parseBigDecimal(BufferedReader in) throws ParseException, IOException {
        int valLen = readValueChars(in, this.mLargeBuf);
        if (valLen == 0) {
            return null;
        }
        return new BigDecimal(new String(this.mLargeBuf, PARSING, valLen));
    }

    private <T extends FastJsonResponse> ArrayList<T> parseObjectArray(BufferedReader in, Field<?, ?> field) throws ParseException, IOException {
        ArrayList<T> objects = new ArrayList();
        char next = readNextNonWhitespaceChar(in);
        switch (next) {
            case LogSource.PROC_STATS /*93*/:
                assumePop(IN_ARRAY);
                return objects;
            case LogSource.GMM_UE3 /*110*/:
                expectChars(in, NULL_TRAIL);
                assumePop(IN_ARRAY);
                return null;
            case LogSource.ANDROID_SNET_IDLE /*123*/:
                this.mStateStack.push(Integer.valueOf(IN_OBJECT));
                while (true) {
                    try {
                        T instance = field.newConcreteTypeInstance();
                        if (!parseObject(in, instance)) {
                            return objects;
                        }
                        objects.add(instance);
                        next = readNextNonWhitespaceChar(in);
                        switch (next) {
                            case LogSource.GOKART /*44*/:
                                if (readNextNonWhitespaceChar(in) != START_OBJECT) {
                                    throw new ParseException("Expected start of next object in array");
                                }
                                this.mStateStack.push(Integer.valueOf(IN_OBJECT));
                            case LogSource.PROC_STATS /*93*/:
                                assumePop(IN_ARRAY);
                                return objects;
                            default:
                                throw new ParseException("Unexpected token: " + next);
                        }
                    } catch (InstantiationException ie) {
                        throw new ParseException("Error instantiating inner object", ie);
                    } catch (IllegalAccessException iae) {
                        throw new ParseException("Error instantiating inner object", iae);
                    }
                }
            default:
                throw new ParseException("Unexpected token: " + next);
        }
    }

    private char readNextNonWhitespaceChar(BufferedReader in) throws ParseException, IOException {
        if (in.read(this.mChar) == -1) {
            return END;
        }
        while (Character.isWhitespace(this.mChar[PARSING])) {
            if (in.read(this.mChar) == -1) {
                return END;
            }
        }
        return this.mChar[PARSING];
    }

    private int readValueChars(BufferedReader in, char[] buf) throws ParseException, IOException {
        char firstChar = readNextNonWhitespaceChar(in);
        if (firstChar == '\u0000') {
            throw new ParseException("Unexpected EOF");
        } else if (firstChar == FIELD_SEPARATOR) {
            throw new ParseException("Missing value");
        } else if (firstChar == NULL_START) {
            expectChars(in, NULL_TRAIL);
            return PARSING;
        } else {
            in.mark(LARGE_STRING_READ_BUFFER_SIZE);
            int i = IN_OBJECT;
            if (firstChar == STRING_BOUNDARY) {
                i = PARSING;
                boolean escaping = DBG;
                while (i < buf.length && in.read(buf, i, IN_OBJECT) != -1) {
                    char currChar = buf[i];
                    if (Character.isISOControl(currChar)) {
                        throw new ParseException("Unexpected control character while reading string");
                    } else if (currChar != STRING_BOUNDARY || escaping) {
                        if (currChar != ESCAPE) {
                            escaping = DBG;
                        } else if (escaping) {
                            escaping = DBG;
                        } else {
                            escaping = true;
                        }
                        i += IN_OBJECT;
                    } else {
                        in.reset();
                        in.skip((long) (i + IN_OBJECT));
                        return i;
                    }
                }
            }
            buf[PARSING] = firstChar;
            while (i < buf.length && in.read(buf, i, IN_OBJECT) != -1) {
                if (buf[i] == END_OBJECT || buf[i] == FIELD_SEPARATOR || Character.isWhitespace(buf[i]) || buf[i] == END_ARRAY) {
                    in.reset();
                    in.skip((long) (i - 1));
                    buf[i] = END;
                    return i;
                }
                i += IN_OBJECT;
            }
            if (i == buf.length) {
                throw new ParseException("Absurdly long value");
            }
            throw new ParseException("Unexpected EOF");
        }
    }

    private void expectChars(BufferedReader in, char[] chars) throws ParseException, IOException {
        int offset = PARSING;
        while (offset < chars.length) {
            int charsRead = in.read(this.mSmallBuf, PARSING, chars.length - offset);
            if (charsRead == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i = PARSING; i < charsRead; i += IN_OBJECT) {
                if (chars[i + offset] != this.mSmallBuf[i]) {
                    throw new ParseException("Unexpected character");
                }
            }
            offset += charsRead;
        }
    }

    private void assumePop(int state) throws ParseException {
        if (this.mStateStack.isEmpty()) {
            throw new ParseException("Expected state " + state + " but had empty stack");
        }
        int popState = ((Integer) this.mStateStack.pop()).intValue();
        if (popState != state) {
            throw new ParseException("Expected state " + state + " but had " + popState);
        }
    }

    private static int parseInt(char[] buf, int len) throws ParseException {
        int result = PARSING;
        boolean negative = DBG;
        int i = PARSING;
        if (len > 0) {
            int limit;
            int i2;
            int digit;
            if (buf[PARSING] == '-') {
                negative = true;
                limit = ExploreByTouchHelper.INVALID_ID;
                i = PARSING + IN_OBJECT;
            } else {
                limit = -2147483647;
            }
            int multmin = limit / 10;
            if (i < len) {
                i2 = i + IN_OBJECT;
                digit = Character.digit(buf[i], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                result = -digit;
            } else {
                i2 = i;
            }
            while (i2 < len) {
                i = i2 + IN_OBJECT;
                digit = Character.digit(buf[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (result < multmin) {
                    throw new ParseException("Number too large");
                } else {
                    result *= 10;
                    if (result < limit + digit) {
                        throw new ParseException("Number too large");
                    }
                    result -= digit;
                    i2 = i;
                }
            }
            if (!negative) {
                return -result;
            }
            if (i2 > IN_OBJECT) {
                return result;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    private static long parseLong(char[] buf, int len) throws ParseException {
        long result = 0;
        boolean negative = DBG;
        int i = PARSING;
        if (len > 0) {
            long limit;
            int i2;
            int digit;
            if (buf[PARSING] == '-') {
                negative = true;
                limit = Long.MIN_VALUE;
                i = PARSING + IN_OBJECT;
            } else {
                limit = -9223372036854775807L;
            }
            long multmin = limit / 10;
            if (i < len) {
                i2 = i + IN_OBJECT;
                digit = Character.digit(buf[i], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                result = (long) (-digit);
            } else {
                i2 = i;
            }
            while (i2 < len) {
                i = i2 + IN_OBJECT;
                digit = Character.digit(buf[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (result < multmin) {
                    throw new ParseException("Number too large");
                } else {
                    result *= 10;
                    if (result < ((long) digit) + limit) {
                        throw new ParseException("Number too large");
                    }
                    result -= (long) digit;
                    i2 = i;
                }
            }
            if (!negative) {
                return -result;
            }
            if (i2 > IN_OBJECT) {
                return result;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    private void dbgLog(String message) {
        StringBuilder sb = new StringBuilder();
        int n = this.mStateStack.size();
        for (int i = PARSING; i < n; i += IN_OBJECT) {
            sb.append(" ");
        }
        sb.append(message);
        Log.d(TAG, sb.toString());
    }
}
