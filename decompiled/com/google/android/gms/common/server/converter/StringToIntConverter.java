package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Class(creator = "StringToIntConverterCreator")
public final class StringToIntConverter implements SafeParcelable, FieldConverter<String, Integer> {
    public static final StringToIntConverterCreator CREATOR;
    private static final String DEFAULT_GMS_UNKNOWN = "gms_unknown";
    private static final int VERSION_CODE = 1;
    private final HashMap<Integer, String> mIntToStringMap;
    @Field(getter = "getSerializedMap", id = 2)
    private final ArrayList<Entry> mSerializedMap;
    private final HashMap<String, Integer> mStringToIntMap;
    @VersionField(getter = "getVersionCode", id = 1)
    private final int mVersionCode;

    @Class(creator = "StringToIntConverterEntryCreator")
    public static final class Entry implements SafeParcelable {
        public static final StringToIntConverterEntryCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        final int intValue;
        @Field(id = 2)
        final String stringValue;
        @VersionField(id = 1)
        final int versionCode;

        static {
            CREATOR = new StringToIntConverterEntryCreator();
        }

        @Constructor
        Entry(@Param(id = 1) int versionCode, @Param(id = 2) String stringValue, @Param(id = 3) int intValue) {
            this.versionCode = versionCode;
            this.stringValue = stringValue;
            this.intValue = intValue;
        }

        Entry(String stringValue, int intValue) {
            this.versionCode = VERSION_CODE;
            this.stringValue = stringValue;
            this.intValue = intValue;
        }

        public int describeContents() {
            StringToIntConverterEntryCreator stringToIntConverterEntryCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            StringToIntConverterEntryCreator stringToIntConverterEntryCreator = CREATOR;
            StringToIntConverterEntryCreator.writeToParcel(this, out, flags);
        }
    }

    static {
        CREATOR = new StringToIntConverterCreator();
    }

    @Constructor
    StringToIntConverter(@Param(id = 1) int versionCode, @Param(id = 2) ArrayList<Entry> serializedMap) {
        this.mVersionCode = versionCode;
        this.mStringToIntMap = new HashMap();
        this.mIntToStringMap = new HashMap();
        this.mSerializedMap = null;
        deserialize(serializedMap);
    }

    public StringToIntConverter() {
        this.mVersionCode = VERSION_CODE;
        this.mStringToIntMap = new HashMap();
        this.mIntToStringMap = new HashMap();
        this.mSerializedMap = null;
    }

    private void deserialize(ArrayList<Entry> serializedMap) {
        Iterator i$ = serializedMap.iterator();
        while (i$.hasNext()) {
            Entry entry = (Entry) i$.next();
            add(entry.stringValue, entry.intValue);
        }
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    ArrayList<Entry> getSerializedMap() {
        ArrayList<Entry> list = new ArrayList();
        for (String key : this.mStringToIntMap.keySet()) {
            list.add(new Entry(key, ((Integer) this.mStringToIntMap.get(key)).intValue()));
        }
        return list;
    }

    public StringToIntConverter add(String stringValue, int intValue) {
        this.mStringToIntMap.put(stringValue, Integer.valueOf(intValue));
        this.mIntToStringMap.put(Integer.valueOf(intValue), stringValue);
        return this;
    }

    public int getTypeIn() {
        return 7;
    }

    public int getTypeOut() {
        return 0;
    }

    public Integer convert(String input) {
        Integer intValue = (Integer) this.mStringToIntMap.get(input);
        if (intValue == null) {
            return (Integer) this.mStringToIntMap.get(DEFAULT_GMS_UNKNOWN);
        }
        return intValue;
    }

    public String convertBack(Integer output) {
        String stringValue = (String) this.mIntToStringMap.get(output);
        if (stringValue == null && this.mStringToIntMap.containsKey(DEFAULT_GMS_UNKNOWN)) {
            return DEFAULT_GMS_UNKNOWN;
        }
        return stringValue;
    }

    public int describeContents() {
        StringToIntConverterCreator stringToIntConverterCreator = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        StringToIntConverterCreator stringToIntConverterCreator = CREATOR;
        StringToIntConverterCreator.writeToParcel(this, out, flags);
    }
}
