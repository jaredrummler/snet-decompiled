package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Class(creator = "FieldMappingDictionaryCreator")
public class FieldMappingDictionary implements SafeParcelable {
    public static final FieldMappingDictionaryCreator CREATOR;
    private static final int VERSION_CODE = 1;
    private final HashMap<String, Map<String, Field<?, ?>>> mDictionary;
    @SafeParcelable.Field(getter = "getRootClassName", id = 3)
    private final String mRootClassName;
    @SafeParcelable.Field(getter = "getSerializedDictionary", id = 2)
    private final ArrayList<Entry> mSerializedDictionary;
    @VersionField(getter = "getVersionCode", id = 1)
    private final int mVersionCode;

    @Class(creator = "FieldMappingDictionaryEntryCreator")
    public static class Entry implements SafeParcelable {
        public static final FieldMappingDictionaryEntryCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @SafeParcelable.Field(id = 2)
        final String className;
        @SafeParcelable.Field(id = 3)
        final ArrayList<FieldMapPair> fieldMapping;
        @VersionField(id = 1)
        final int versionCode;

        static {
            CREATOR = new FieldMappingDictionaryEntryCreator();
        }

        @Constructor
        Entry(@Param(id = 1) int versionCode, @Param(id = 2) String className, @Param(id = 3) ArrayList<FieldMapPair> fieldMapping) {
            this.versionCode = versionCode;
            this.className = className;
            this.fieldMapping = fieldMapping;
        }

        Entry(String className, Map<String, Field<?, ?>> fieldMap) {
            this.versionCode = VERSION_CODE;
            this.className = className;
            this.fieldMapping = convertToArrayList(fieldMap);
        }

        private static ArrayList<FieldMapPair> convertToArrayList(Map<String, Field<?, ?>> fieldMap) {
            if (fieldMap == null) {
                return null;
            }
            ArrayList<FieldMapPair> list = new ArrayList();
            for (String key : fieldMap.keySet()) {
                list.add(new FieldMapPair(key, (Field) fieldMap.get(key)));
            }
            return list;
        }

        HashMap<String, Field<?, ?>> deserializeFieldMapping() {
            HashMap<String, Field<?, ?>> result = new HashMap();
            int size = this.fieldMapping.size();
            for (int i = 0; i < size; i += VERSION_CODE) {
                FieldMapPair pair = (FieldMapPair) this.fieldMapping.get(i);
                result.put(pair.key, pair.value);
            }
            return result;
        }

        public int describeContents() {
            FieldMappingDictionaryEntryCreator fieldMappingDictionaryEntryCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            FieldMappingDictionaryEntryCreator fieldMappingDictionaryEntryCreator = CREATOR;
            FieldMappingDictionaryEntryCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "FieldMapPairCreator")
    public static class FieldMapPair implements SafeParcelable {
        public static final FieldMapPairCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @SafeParcelable.Field(id = 2)
        final String key;
        @SafeParcelable.Field(id = 3)
        final Field<?, ?> value;
        @VersionField(id = 1)
        final int versionCode;

        static {
            CREATOR = new FieldMapPairCreator();
        }

        @Constructor
        FieldMapPair(@Param(id = 1) int versionCode, @Param(id = 2) String key, @Param(id = 3) Field<?, ?> value) {
            this.versionCode = versionCode;
            this.key = key;
            this.value = value;
        }

        FieldMapPair(String key, Field<?, ?> value) {
            this.versionCode = VERSION_CODE;
            this.key = key;
            this.value = value;
        }

        public int describeContents() {
            FieldMapPairCreator fieldMapPairCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            FieldMapPairCreator fieldMapPairCreator = CREATOR;
            FieldMapPairCreator.writeToParcel(this, out, flags);
        }
    }

    static {
        CREATOR = new FieldMappingDictionaryCreator();
    }

    @Constructor
    FieldMappingDictionary(@Param(id = 1) int versionCode, @Param(id = 2) ArrayList<Entry> serializedDictionary, @Param(id = 3) String rootClassName) {
        this.mVersionCode = versionCode;
        this.mSerializedDictionary = null;
        this.mDictionary = deserialize(serializedDictionary);
        this.mRootClassName = (String) Preconditions.checkNotNull(rootClassName);
        linkFields();
    }

    public void linkFields() {
        for (String className : this.mDictionary.keySet()) {
            Map<String, Field<?, ?>> fieldMap = (Map) this.mDictionary.get(className);
            for (String key : fieldMap.keySet()) {
                ((Field) fieldMap.get(key)).setFieldMappingDictionary(this);
            }
        }
    }

    private static HashMap<String, Map<String, Field<?, ?>>> deserialize(ArrayList<Entry> serializedDictionary) {
        HashMap<String, Map<String, Field<?, ?>>> result = new HashMap();
        int size = serializedDictionary.size();
        for (int i = 0; i < size; i += VERSION_CODE) {
            Entry entry = (Entry) serializedDictionary.get(i);
            result.put(entry.className, entry.deserializeFieldMapping());
        }
        return result;
    }

    public void copyInternalFieldMappings() {
        for (String className : this.mDictionary.keySet()) {
            Map<String, Field<?, ?>> fieldMap = (Map) this.mDictionary.get(className);
            HashMap<String, Field<?, ?>> fieldMapCopy = new HashMap();
            for (String key : fieldMap.keySet()) {
                fieldMapCopy.put(key, ((Field) fieldMap.get(key)).copyForDictionary());
            }
            this.mDictionary.put(className, fieldMapCopy);
        }
    }

    public FieldMappingDictionary(Class<? extends FastJsonResponse> rootClazz) {
        this.mVersionCode = VERSION_CODE;
        this.mSerializedDictionary = null;
        this.mDictionary = new HashMap();
        this.mRootClassName = rootClazz.getCanonicalName();
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    ArrayList<Entry> getSerializedDictionary() {
        ArrayList<Entry> serializedDictionary = new ArrayList();
        for (String className : this.mDictionary.keySet()) {
            serializedDictionary.add(new Entry(className, (Map) this.mDictionary.get(className)));
        }
        return serializedDictionary;
    }

    public void put(Class<? extends FastJsonResponse> clazz, Map<String, Field<?, ?>> fieldMap) {
        this.mDictionary.put(clazz.getCanonicalName(), fieldMap);
    }

    @VisibleForTesting
    public Map<String, Field<?, ?>> getFieldMapping(Class<? extends FastJsonResponse> clazz) {
        return (Map) this.mDictionary.get(clazz.getCanonicalName());
    }

    public Map<String, Field<?, ?>> getFieldMapping(String className) {
        return (Map) this.mDictionary.get(className);
    }

    public boolean hasFieldMappingForClass(Class<? extends FastJsonResponse> clazz) {
        return this.mDictionary.containsKey(clazz.getCanonicalName());
    }

    public String getRootClassName() {
        return this.mRootClassName;
    }

    public int describeContents() {
        FieldMappingDictionaryCreator fieldMappingDictionaryCreator = CREATOR;
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String className : this.mDictionary.keySet()) {
            sb.append(className).append(":\n");
            Map<String, Field<?, ?>> fieldMap = (Map) this.mDictionary.get(className);
            for (String key : fieldMap.keySet()) {
                sb.append("  ").append(key).append(": ");
                sb.append(fieldMap.get(key));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        FieldMappingDictionaryCreator fieldMappingDictionaryCreator = CREATOR;
        FieldMappingDictionaryCreator.writeToParcel(this, out, flags);
    }
}
