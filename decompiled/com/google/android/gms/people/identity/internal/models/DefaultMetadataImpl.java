package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Class(creator = "DefaultMetadataImplCreator")
public final class DefaultMetadataImpl extends FastJsonResponse implements SafeParcelable {
    public static final String AFFINITIES = "affinities";
    public static final String CONTAINER = "container";
    public static final String CONTAINER_CONTACT_ID = "containerContactId";
    public static final String CONTAINER_ID = "containerId";
    public static final DefaultMetadataImplCreator CREATOR;
    public static final String EDGE_KEY = "edgeKey";
    public static final String PRIMARY = "primary";
    public static final String VERIFIED = "verified";
    private static final int VERSION_CODE = 1;
    public static final String VISIBILITY = "visibility";
    public static final String WRITEABLE = "writeable";
    private static final HashMap<String, Field<?, ?>> sFields;
    @SafeParcelable.Field(id = 2)
    List<Affinities> mAffinities;
    @SafeParcelable.Field(id = 3)
    String mContainer;
    @SafeParcelable.Field(id = 4)
    String mContainerContactId;
    @SafeParcelable.Field(id = 5)
    String mContainerId;
    @SafeParcelable.Field(id = 6)
    boolean mEdgeKey;
    @Indicator
    final Set<Integer> mIndicatorSet;
    @SafeParcelable.Field(id = 7)
    boolean mPrimary;
    @SafeParcelable.Field(id = 8)
    boolean mVerified;
    @VersionField(id = 1)
    final int mVersionCode;
    @SafeParcelable.Field(id = 9)
    String mVisibility;
    @SafeParcelable.Field(id = 10)
    boolean mWriteable;

    @Class(creator = "DefaultMetadataImpl_AffinitiesCreator")
    public static final class Affinities extends FastJsonResponse implements SafeParcelable {
        public static final DefaultMetadataImpl_AffinitiesCreator CREATOR;
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        String mType;
        @SafeParcelable.Field(id = 3)
        double mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultMetadataImpl_AffinitiesCreator();
            sFields = new HashMap();
            sFields.put(TYPE, Field.forString(TYPE, 2));
            sFields.put(VALUE, Field.forDouble(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Affinities() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Affinities(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String type, @Param(id = 3) double value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mType = type;
            this.mValue = value;
        }

        public Affinities(Set<Integer> indicatorSet, String type, double value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mType = type;
            this.mValue = value;
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public double getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public Affinities setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Affinities setValue(double newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultMetadataImpl_AffinitiesCreator defaultMetadataImpl_AffinitiesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultMetadataImpl_AffinitiesCreator defaultMetadataImpl_AffinitiesCreator = CREATOR;
            DefaultMetadataImpl_AffinitiesCreator.writeToParcel(this, out, flags);
        }

        protected Object getValueObject(String key) {
            return null;
        }

        protected boolean isPrimitiveFieldSet(String outputField) {
            return false;
        }

        protected boolean isFieldSet(Field field) {
            return this.mIndicatorSet.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
        }

        protected Object getFieldValue(Field field) {
            switch (field.getSafeParcelableFieldId()) {
                case Type.INDEFINITELY /*2*/:
                    return this.mType;
                case Type.CUSTOM /*3*/:
                    return Double.valueOf(this.mValue);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
            }
        }

        public byte[] toByteArray() {
            Parcel p = Parcel.obtain();
            writeToParcel(p, 0);
            byte[] out = p.marshall();
            p.recycle();
            return out;
        }

        public static Affinities fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Affinities object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setDoubleInternal(Field<?, ?> field, String outputField, double value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a double.");
            }
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mType = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public int hashCode() {
            int hash = 0;
            for (Field field : sFields.values()) {
                if (isFieldSet(field)) {
                    hash = (hash + field.getSafeParcelableFieldId()) + getFieldValue(field).hashCode();
                }
            }
            return hash;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Affinities)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Affinities entity = (Affinities) obj;
            for (Field<?, ?> field : sFields.values()) {
                if (isFieldSet(field)) {
                    if (!entity.isFieldSet(field)) {
                        return false;
                    }
                    if (!getFieldValue(field).equals(entity.getFieldValue(field))) {
                        return false;
                    }
                } else if (entity.isFieldSet(field)) {
                    return false;
                }
            }
            return true;
        }
    }

    static {
        CREATOR = new DefaultMetadataImplCreator();
        sFields = new HashMap();
        sFields.put(AFFINITIES, Field.forConcreteTypeArray(AFFINITIES, 2, Affinities.class));
        sFields.put(CONTAINER, Field.forString(CONTAINER, 3));
        sFields.put(CONTAINER_CONTACT_ID, Field.forString(CONTAINER_CONTACT_ID, 4));
        sFields.put(CONTAINER_ID, Field.forString(CONTAINER_ID, 5));
        sFields.put(EDGE_KEY, Field.forBoolean(EDGE_KEY, 6));
        sFields.put(PRIMARY, Field.forBoolean(PRIMARY, 7));
        sFields.put(VERIFIED, Field.forBoolean(VERIFIED, 8));
        sFields.put(VISIBILITY, Field.forString(VISIBILITY, 9));
        sFields.put(WRITEABLE, Field.forBoolean(WRITEABLE, 10));
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return sFields;
    }

    public DefaultMetadataImpl() {
        this.mVersionCode = VERSION_CODE;
        this.mIndicatorSet = new HashSet();
    }

    @Constructor
    DefaultMetadataImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) List<Affinities> affinities, @Param(id = 3) String container, @Param(id = 4) String containerContactId, @Param(id = 5) String containerId, @Param(id = 6) boolean edgeKey, @Param(id = 7) boolean primary, @Param(id = 8) boolean verified, @Param(id = 9) String visibility, @Param(id = 10) boolean writeable) {
        this.mIndicatorSet = indicatorSet;
        this.mVersionCode = versionCode;
        this.mAffinities = affinities;
        this.mContainer = container;
        this.mContainerContactId = containerContactId;
        this.mContainerId = containerId;
        this.mEdgeKey = edgeKey;
        this.mPrimary = primary;
        this.mVerified = verified;
        this.mVisibility = visibility;
        this.mWriteable = writeable;
    }

    public DefaultMetadataImpl(Set<Integer> indicatorSet, List<Affinities> affinities, String container, String containerContactId, String containerId, boolean edgeKey, boolean primary, boolean verified, String visibility, boolean writeable) {
        this.mIndicatorSet = indicatorSet;
        this.mVersionCode = VERSION_CODE;
        this.mAffinities = affinities;
        this.mContainer = container;
        this.mContainerContactId = containerContactId;
        this.mContainerId = containerId;
        this.mEdgeKey = edgeKey;
        this.mPrimary = primary;
        this.mVerified = verified;
        this.mVisibility = visibility;
        this.mWriteable = writeable;
    }

    public List<Affinities> getAffinities() {
        return this.mAffinities;
    }

    public boolean hasAffinities() {
        return this.mIndicatorSet.contains(Integer.valueOf(2));
    }

    public String getContainer() {
        return this.mContainer;
    }

    public boolean hasContainer() {
        return this.mIndicatorSet.contains(Integer.valueOf(3));
    }

    public String getContainerContactId() {
        return this.mContainerContactId;
    }

    public boolean hasContainerContactId() {
        return this.mIndicatorSet.contains(Integer.valueOf(4));
    }

    public String getContainerId() {
        return this.mContainerId;
    }

    public boolean hasContainerId() {
        return this.mIndicatorSet.contains(Integer.valueOf(5));
    }

    public boolean isEdgeKey() {
        return this.mEdgeKey;
    }

    public boolean hasEdgeKey() {
        return this.mIndicatorSet.contains(Integer.valueOf(6));
    }

    public boolean isPrimary() {
        return this.mPrimary;
    }

    public boolean hasPrimary() {
        return this.mIndicatorSet.contains(Integer.valueOf(7));
    }

    public boolean isVerified() {
        return this.mVerified;
    }

    public boolean hasVerified() {
        return this.mIndicatorSet.contains(Integer.valueOf(8));
    }

    public String getVisibility() {
        return this.mVisibility;
    }

    public boolean hasVisibility() {
        return this.mIndicatorSet.contains(Integer.valueOf(9));
    }

    public boolean isWriteable() {
        return this.mWriteable;
    }

    public boolean hasWriteable() {
        return this.mIndicatorSet.contains(Integer.valueOf(10));
    }

    public DefaultMetadataImpl setAffinities(List<Affinities> newValue) {
        this.mAffinities = newValue;
        this.mIndicatorSet.add(Integer.valueOf(2));
        return this;
    }

    public DefaultMetadataImpl addAffinities(Affinities newValue) {
        if (this.mAffinities == null) {
            this.mAffinities = new ArrayList();
        }
        this.mAffinities.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(2));
        return this;
    }

    public DefaultMetadataImpl setContainer(String newValue) {
        this.mContainer = newValue;
        this.mIndicatorSet.add(Integer.valueOf(3));
        return this;
    }

    public DefaultMetadataImpl setContainerContactId(String newValue) {
        this.mContainerContactId = newValue;
        this.mIndicatorSet.add(Integer.valueOf(4));
        return this;
    }

    public DefaultMetadataImpl setContainerId(String newValue) {
        this.mContainerId = newValue;
        this.mIndicatorSet.add(Integer.valueOf(5));
        return this;
    }

    public DefaultMetadataImpl setEdgeKey(boolean newValue) {
        this.mEdgeKey = newValue;
        this.mIndicatorSet.add(Integer.valueOf(6));
        return this;
    }

    public DefaultMetadataImpl setPrimary(boolean newValue) {
        this.mPrimary = newValue;
        this.mIndicatorSet.add(Integer.valueOf(7));
        return this;
    }

    public DefaultMetadataImpl setVerified(boolean newValue) {
        this.mVerified = newValue;
        this.mIndicatorSet.add(Integer.valueOf(8));
        return this;
    }

    public DefaultMetadataImpl setVisibility(String newValue) {
        this.mVisibility = newValue;
        this.mIndicatorSet.add(Integer.valueOf(9));
        return this;
    }

    public DefaultMetadataImpl setWriteable(boolean newValue) {
        this.mWriteable = newValue;
        this.mIndicatorSet.add(Integer.valueOf(10));
        return this;
    }

    public int describeContents() {
        DefaultMetadataImplCreator defaultMetadataImplCreator = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        DefaultMetadataImplCreator defaultMetadataImplCreator = CREATOR;
        DefaultMetadataImplCreator.writeToParcel(this, out, flags);
    }

    protected Object getValueObject(String key) {
        return null;
    }

    protected boolean isPrimitiveFieldSet(String outputField) {
        return false;
    }

    protected boolean isFieldSet(Field field) {
        return this.mIndicatorSet.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    protected Object getFieldValue(Field field) {
        switch (field.getSafeParcelableFieldId()) {
            case Type.INDEFINITELY /*2*/:
                return this.mAffinities;
            case Type.CUSTOM /*3*/:
                return this.mContainer;
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                return this.mContainerContactId;
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                return this.mContainerId;
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                return Boolean.valueOf(this.mEdgeKey);
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                return Boolean.valueOf(this.mPrimary);
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                return Boolean.valueOf(this.mVerified);
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                return this.mVisibility;
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                return Boolean.valueOf(this.mWriteable);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
        }
    }

    public byte[] toByteArray() {
        Parcel p = Parcel.obtain();
        writeToParcel(p, 0);
        byte[] out = p.marshall();
        p.recycle();
        return out;
    }

    public static DefaultMetadataImpl fromByteArray(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        DefaultMetadataImpl object = CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return object;
    }

    protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                this.mEdgeKey = value;
                break;
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                this.mPrimary = value;
                break;
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                this.mVerified = value;
                break;
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                this.mWriteable = value;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
        }
        this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
    }

    protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case Type.CUSTOM /*3*/:
                this.mContainer = value;
                break;
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                this.mContainerContactId = value;
                break;
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                this.mContainerId = value;
                break;
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                this.mVisibility = value;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
        }
        this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String outputField, ArrayList<T> value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case Type.INDEFINITELY /*2*/:
                this.mAffinities = value;
                this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known array of" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
        }
    }

    public int hashCode() {
        int hash = 0;
        for (Field field : sFields.values()) {
            if (isFieldSet(field)) {
                hash = (hash + field.getSafeParcelableFieldId()) + getFieldValue(field).hashCode();
            }
        }
        return hash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultMetadataImpl)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DefaultMetadataImpl entity = (DefaultMetadataImpl) obj;
        for (Field<?, ?> field : sFields.values()) {
            if (isFieldSet(field)) {
                if (!entity.isFieldSet(field)) {
                    return false;
                }
                if (!getFieldValue(field).equals(entity.getFieldValue(field))) {
                    return false;
                }
            } else if (entity.isFieldSet(field)) {
                return false;
            }
        }
        return true;
    }
}
