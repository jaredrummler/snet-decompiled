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
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Class(creator = "DefaultPersonImplCreator")
public class DefaultPersonImpl extends FastJsonResponse implements SafeParcelable {
    public static final String ABOUTS = "abouts";
    public static final String ADDRESSES = "addresses";
    public static final String AGE_RANGE = "ageRange";
    public static final String BIRTHDAYS = "birthdays";
    public static final String BRAGGING_RIGHTS = "braggingRights";
    public static final String COVER_PHOTOS = "coverPhotos";
    public static final DefaultPersonImplCreator CREATOR;
    public static final String CUSTOM_FIELDS = "customFields";
    public static final String EMAILS = "emails";
    public static final String ETAG = "etag";
    public static final String EVENTS = "events";
    public static final String GENDERS = "genders";
    public static final String ID = "id";
    public static final String IMAGES = "images";
    public static final String INSTANT_MESSAGING = "instantMessaging";
    public static final String KIND = "kind";
    public static final String LANGUAGE = "language";
    public static final String LEGACY_FIELDS = "legacyFields";
    public static final String LINKED_PEOPLE = "linkedPeople";
    public static final String MEMBERSHIPS = "memberships";
    public static final String METADATA = "metadata";
    public static final String NAMES = "names";
    public static final String NICKNAMES = "nicknames";
    public static final String OCCUPATIONS = "occupations";
    public static final String ORGANIZATIONS = "organizations";
    public static final String PHONE_NUMBERS = "phoneNumbers";
    public static final String PLACES_LIVED = "placesLived";
    public static final String PROFILE_URL = "profileUrl";
    public static final String RELATIONS = "relations";
    public static final String RELATIONSHIP_INTERESTS = "relationshipInterests";
    public static final String RELATIONSHIP_STATUSES = "relationshipStatuses";
    public static final String SKILLS = "skills";
    public static final String SORT_KEYS = "sortKeys";
    public static final String TAGLINES = "taglines";
    public static final String URLS = "urls";
    private static final int VERSION_CODE = 1;
    private static final HashMap<String, Field<?, ?>> sFields;
    @SafeParcelable.Field(id = 2)
    List<Abouts> mAbouts;
    @SafeParcelable.Field(id = 3)
    List<Addresses> mAddresses;
    @SafeParcelable.Field(id = 4)
    String mAgeRange;
    @SafeParcelable.Field(id = 5)
    List<Birthdays> mBirthdays;
    @SafeParcelable.Field(id = 6)
    List<BraggingRights> mBraggingRights;
    @SafeParcelable.Field(id = 7)
    List<CoverPhotos> mCoverPhotos;
    @SafeParcelable.Field(id = 8)
    List<CustomFields> mCustomFields;
    @SafeParcelable.Field(id = 9)
    List<Emails> mEmails;
    @SafeParcelable.Field(id = 10)
    String mEtag;
    @SafeParcelable.Field(id = 11)
    List<Events> mEvents;
    @SafeParcelable.Field(id = 12)
    List<Genders> mGenders;
    @SafeParcelable.Field(id = 13)
    String mId;
    @SafeParcelable.Field(id = 14)
    List<Images> mImages;
    @Indicator
    final Set<Integer> mIndicatorSet;
    @SafeParcelable.Field(id = 15)
    List<InstantMessaging> mInstantMessaging;
    @SafeParcelable.Field(id = 17)
    String mLanguage;
    @SafeParcelable.Field(id = 18)
    LegacyFields mLegacyFields;
    @SafeParcelable.Field(id = 19)
    List<DefaultPersonImpl> mLinkedPeople;
    @SafeParcelable.Field(id = 20)
    List<Memberships> mMemberships;
    @SafeParcelable.Field(id = 21)
    Metadata mMetadata;
    @SafeParcelable.Field(id = 22)
    List<Names> mNames;
    @SafeParcelable.Field(id = 23)
    List<Nicknames> mNicknames;
    @SafeParcelable.Field(id = 24)
    List<Occupations> mOccupations;
    @SafeParcelable.Field(id = 25)
    List<Organizations> mOrganizations;
    @SafeParcelable.Field(id = 26)
    List<PhoneNumbers> mPhoneNumbers;
    @SafeParcelable.Field(id = 27)
    List<PlacesLived> mPlacesLived;
    @SafeParcelable.Field(id = 28)
    String mProfileUrl;
    @SafeParcelable.Field(id = 29)
    List<Relations> mRelations;
    @SafeParcelable.Field(id = 30)
    List<RelationshipInterests> mRelationshipInterests;
    @SafeParcelable.Field(id = 31)
    List<RelationshipStatuses> mRelationshipStatuses;
    @SafeParcelable.Field(id = 32)
    List<Skills> mSkills;
    @SafeParcelable.Field(id = 33)
    SortKeys mSortKeys;
    @SafeParcelable.Field(id = 34)
    List<Taglines> mTaglines;
    @SafeParcelable.Field(id = 35)
    List<Urls> mUrls;
    @VersionField(id = 1)
    final int mVersionCode;

    @Class(creator = "DefaultPersonImpl_AboutsCreator")
    public static final class Abouts extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_AboutsCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mType;
        @SafeParcelable.Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_AboutsCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 3));
            sFields.put(VALUE, Field.forString(VALUE, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Abouts() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Abouts(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String type, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public Abouts(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public Abouts setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Abouts setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Abouts setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_AboutsCreator defaultPersonImpl_AboutsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_AboutsCreator defaultPersonImpl_AboutsCreator = CREATOR;
            DefaultPersonImpl_AboutsCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mType;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mValue;
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

        public static Abouts fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Abouts object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mType = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Abouts)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Abouts entity = (Abouts) obj;
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

    @Class(creator = "DefaultPersonImpl_AddressesCreator")
    public static final class Addresses extends FastJsonResponse implements SafeParcelable {
        public static final String CITY = "city";
        public static final String COUNTRY = "country";
        public static final String COUNTRY_CODE = "countryCode";
        public static final DefaultPersonImpl_AddressesCreator CREATOR;
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String POSTAL_CODE = "postalCode";
        public static final String PO_BOX = "poBox";
        public static final String REGION = "region";
        public static final String STREET_ADDRESS = "streetAddress";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mCity;
        @SafeParcelable.Field(id = 3)
        String mCountry;
        @SafeParcelable.Field(id = 4)
        String mCountryCode;
        @SafeParcelable.Field(id = 5)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 6)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 7)
        String mPoBox;
        @SafeParcelable.Field(id = 8)
        String mPostalCode;
        @SafeParcelable.Field(id = 9)
        String mRegion;
        @SafeParcelable.Field(id = 10)
        String mStreetAddress;
        @SafeParcelable.Field(id = 11)
        String mType;
        @SafeParcelable.Field(id = 12)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_AddressesCreator();
            sFields = new HashMap();
            sFields.put(CITY, Field.forString(CITY, 2));
            sFields.put(COUNTRY, Field.forString(COUNTRY, 3));
            sFields.put(COUNTRY_CODE, Field.forString(COUNTRY_CODE, 4));
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 5));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 6, DefaultMetadataImpl.class));
            sFields.put(PO_BOX, Field.forString(PO_BOX, 7));
            sFields.put(POSTAL_CODE, Field.forString(POSTAL_CODE, 8));
            sFields.put(REGION, Field.forString(REGION, 9));
            sFields.put(STREET_ADDRESS, Field.forString(STREET_ADDRESS, 10));
            sFields.put(TYPE, Field.forString(TYPE, 11));
            sFields.put(VALUE, Field.forString(VALUE, 12));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Addresses() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Addresses(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String city, @Param(id = 3) String country, @Param(id = 4) String countryCode, @Param(id = 5) String formattedType, @Param(id = 6) DefaultMetadataImpl metadata, @Param(id = 7) String poBox, @Param(id = 8) String postalCode, @Param(id = 9) String region, @Param(id = 10) String streetAddress, @Param(id = 11) String type, @Param(id = 12) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mCity = city;
            this.mCountry = country;
            this.mCountryCode = countryCode;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mPoBox = poBox;
            this.mPostalCode = postalCode;
            this.mRegion = region;
            this.mStreetAddress = streetAddress;
            this.mType = type;
            this.mValue = value;
        }

        public Addresses(Set<Integer> indicatorSet, String city, String country, String countryCode, String formattedType, DefaultMetadataImpl metadata, String poBox, String postalCode, String region, String streetAddress, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mCity = city;
            this.mCountry = country;
            this.mCountryCode = countryCode;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mPoBox = poBox;
            this.mPostalCode = postalCode;
            this.mRegion = region;
            this.mStreetAddress = streetAddress;
            this.mType = type;
            this.mValue = value;
        }

        public String getCity() {
            return this.mCity;
        }

        public boolean hasCity() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getCountry() {
            return this.mCountry;
        }

        public boolean hasCountry() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getCountryCode() {
            return this.mCountryCode;
        }

        public boolean hasCountryCode() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public String getPoBox() {
            return this.mPoBox;
        }

        public boolean hasPoBox() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public String getPostalCode() {
            return this.mPostalCode;
        }

        public boolean hasPostalCode() {
            return this.mIndicatorSet.contains(Integer.valueOf(8));
        }

        public String getRegion() {
            return this.mRegion;
        }

        public boolean hasRegion() {
            return this.mIndicatorSet.contains(Integer.valueOf(9));
        }

        public String getStreetAddress() {
            return this.mStreetAddress;
        }

        public boolean hasStreetAddress() {
            return this.mIndicatorSet.contains(Integer.valueOf(10));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(11));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(12));
        }

        public Addresses setCity(String newValue) {
            this.mCity = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Addresses setCountry(String newValue) {
            this.mCountry = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Addresses setCountryCode(String newValue) {
            this.mCountryCode = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Addresses setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public Addresses setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public Addresses setPoBox(String newValue) {
            this.mPoBox = newValue;
            this.mIndicatorSet.add(Integer.valueOf(7));
            return this;
        }

        public Addresses setPostalCode(String newValue) {
            this.mPostalCode = newValue;
            this.mIndicatorSet.add(Integer.valueOf(8));
            return this;
        }

        public Addresses setRegion(String newValue) {
            this.mRegion = newValue;
            this.mIndicatorSet.add(Integer.valueOf(9));
            return this;
        }

        public Addresses setStreetAddress(String newValue) {
            this.mStreetAddress = newValue;
            this.mIndicatorSet.add(Integer.valueOf(10));
            return this;
        }

        public Addresses setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(11));
            return this;
        }

        public Addresses setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(12));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_AddressesCreator defaultPersonImpl_AddressesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_AddressesCreator defaultPersonImpl_AddressesCreator = CREATOR;
            DefaultPersonImpl_AddressesCreator.writeToParcel(this, out, flags);
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
                    return this.mCity;
                case Type.CUSTOM /*3*/:
                    return this.mCountry;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mCountryCode;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mFormattedType;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    return this.mPoBox;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    return this.mPostalCode;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    return this.mRegion;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    return this.mStreetAddress;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    return this.mType;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    return this.mValue;
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

        public static Addresses fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Addresses object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mCity = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mCountry = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mCountryCode = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mFormattedType = value;
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    this.mPoBox = value;
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    this.mPostalCode = value;
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    this.mRegion = value;
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    this.mStreetAddress = value;
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    this.mType = value;
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Addresses)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Addresses entity = (Addresses) obj;
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

    @Class(creator = "DefaultPersonImpl_BirthdaysCreator")
    public static final class Birthdays extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_BirthdaysCreator CREATOR;
        public static final String DATE = "date";
        public static final String METADATA = "metadata";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mDate;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_BirthdaysCreator();
            sFields = new HashMap();
            sFields.put(DATE, Field.forString(DATE, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Birthdays() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Birthdays(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String date, @Param(id = 3) DefaultMetadataImpl metadata) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mDate = date;
            this.mMetadata = metadata;
        }

        public Birthdays(Set<Integer> indicatorSet, String date, DefaultMetadataImpl metadata) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mDate = date;
            this.mMetadata = metadata;
        }

        public String getDate() {
            return this.mDate;
        }

        public boolean hasDate() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public Birthdays setDate(String newValue) {
            this.mDate = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Birthdays setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_BirthdaysCreator defaultPersonImpl_BirthdaysCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_BirthdaysCreator defaultPersonImpl_BirthdaysCreator = CREATOR;
            DefaultPersonImpl_BirthdaysCreator.writeToParcel(this, out, flags);
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
                    return this.mDate;
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
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

        public static Birthdays fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Birthdays object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mDate = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Birthdays)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Birthdays entity = (Birthdays) obj;
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

    @Class(creator = "DefaultPersonImpl_BraggingRightsCreator")
    public static final class BraggingRights extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_BraggingRightsCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_BraggingRightsCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public BraggingRights() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        BraggingRights(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public BraggingRights(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public BraggingRights setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public BraggingRights setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_BraggingRightsCreator defaultPersonImpl_BraggingRightsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_BraggingRightsCreator defaultPersonImpl_BraggingRightsCreator = CREATOR;
            DefaultPersonImpl_BraggingRightsCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mValue;
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

        public static BraggingRights fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            BraggingRights object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof BraggingRights)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            BraggingRights entity = (BraggingRights) obj;
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

    @Class(creator = "DefaultPersonImpl_CoverPhotosCreator")
    public static final class CoverPhotos extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_CoverPhotosCreator CREATOR;
        public static final String HEIGHT = "height";
        public static final String ID = "id";
        public static final String IS_DEFAULT = "isDefault";
        public static final String URL = "url";
        private static final int VERSION_CODE = 1;
        public static final String WIDTH = "width";
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        int mHeight;
        @SafeParcelable.Field(id = 3)
        String mId;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 4)
        boolean mIsDefault;
        @SafeParcelable.Field(id = 5)
        String mUrl;
        @VersionField(id = 1)
        final int mVersionCode;
        @SafeParcelable.Field(id = 6)
        int mWidth;

        static {
            CREATOR = new DefaultPersonImpl_CoverPhotosCreator();
            sFields = new HashMap();
            sFields.put(HEIGHT, Field.forInteger(HEIGHT, 2));
            sFields.put(ID, Field.forString(ID, 3));
            sFields.put(IS_DEFAULT, Field.forBoolean(IS_DEFAULT, 4));
            sFields.put(URL, Field.forString(URL, 5));
            sFields.put(WIDTH, Field.forInteger(WIDTH, 6));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public CoverPhotos() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        CoverPhotos(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) int height, @Param(id = 3) String id, @Param(id = 4) boolean isDefault, @Param(id = 5) String url, @Param(id = 6) int width) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mHeight = height;
            this.mId = id;
            this.mIsDefault = isDefault;
            this.mUrl = url;
            this.mWidth = width;
        }

        public CoverPhotos(Set<Integer> indicatorSet, int height, String id, boolean isDefault, String url, int width) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mHeight = height;
            this.mId = id;
            this.mIsDefault = isDefault;
            this.mUrl = url;
            this.mWidth = width;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public boolean hasHeight() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getId() {
            return this.mId;
        }

        public boolean hasId() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public boolean isDefault() {
            return this.mIsDefault;
        }

        public boolean hasIsDefault() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getUrl() {
            return this.mUrl;
        }

        public boolean hasUrl() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public int getWidth() {
            return this.mWidth;
        }

        public boolean hasWidth() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public CoverPhotos setHeight(int newValue) {
            this.mHeight = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public CoverPhotos setId(String newValue) {
            this.mId = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public CoverPhotos setIsDefault(boolean newValue) {
            this.mIsDefault = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public CoverPhotos setUrl(String newValue) {
            this.mUrl = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public CoverPhotos setWidth(int newValue) {
            this.mWidth = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_CoverPhotosCreator defaultPersonImpl_CoverPhotosCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_CoverPhotosCreator defaultPersonImpl_CoverPhotosCreator = CREATOR;
            DefaultPersonImpl_CoverPhotosCreator.writeToParcel(this, out, flags);
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
                    return Integer.valueOf(this.mHeight);
                case Type.CUSTOM /*3*/:
                    return this.mId;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return Boolean.valueOf(this.mIsDefault);
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mUrl;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return Integer.valueOf(this.mWidth);
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

        public static CoverPhotos fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            CoverPhotos object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setIntegerInternal(Field<?, ?> field, String outputField, int value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mHeight = value;
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mWidth = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an int.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mIsDefault = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mId = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mUrl = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
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
            if (!(obj instanceof CoverPhotos)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            CoverPhotos entity = (CoverPhotos) obj;
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

    @Class(creator = "DefaultPersonImpl_CustomFieldsCreator")
    public static final class CustomFields extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_CustomFieldsCreator CREATOR;
        public static final String KEY = "key";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        String mKey;
        @SafeParcelable.Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_CustomFieldsCreator();
            sFields = new HashMap();
            sFields.put(KEY, Field.forString(KEY, 2));
            sFields.put(VALUE, Field.forString(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public CustomFields() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        CustomFields(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String key, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mKey = key;
            this.mValue = value;
        }

        public CustomFields(Set<Integer> indicatorSet, String key, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mKey = key;
            this.mValue = value;
        }

        public String getKey() {
            return this.mKey;
        }

        public boolean hasKey() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public CustomFields setKey(String newValue) {
            this.mKey = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public CustomFields setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_CustomFieldsCreator defaultPersonImpl_CustomFieldsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_CustomFieldsCreator defaultPersonImpl_CustomFieldsCreator = CREATOR;
            DefaultPersonImpl_CustomFieldsCreator.writeToParcel(this, out, flags);
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
                    return this.mKey;
                case Type.CUSTOM /*3*/:
                    return this.mValue;
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

        public static CustomFields fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            CustomFields object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mKey = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
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
            if (!(obj instanceof CustomFields)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            CustomFields entity = (CustomFields) obj;
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

    @Class(creator = "DefaultPersonImpl_EmailsCreator")
    public static final class Emails extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_EmailsCreator CREATOR;
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mType;
        @SafeParcelable.Field(id = 5)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_EmailsCreator();
            sFields = new HashMap();
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 4));
            sFields.put(VALUE, Field.forString(VALUE, 5));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Emails() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Emails(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String formattedType, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String type, @Param(id = 5) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public Emails(Set<Integer> indicatorSet, String formattedType, DefaultMetadataImpl metadata, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public Emails setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Emails setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Emails setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Emails setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_EmailsCreator defaultPersonImpl_EmailsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_EmailsCreator defaultPersonImpl_EmailsCreator = CREATOR;
            DefaultPersonImpl_EmailsCreator.writeToParcel(this, out, flags);
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
                    return this.mFormattedType;
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mType;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mValue;
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

        public static Emails fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Emails object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mFormattedType = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mType = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Emails)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Emails entity = (Emails) obj;
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

    @Class(creator = "DefaultPersonImpl_EventsCreator")
    public static final class Events extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_EventsCreator CREATOR;
        public static final String DATE = "date";
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mDate;
        @SafeParcelable.Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 4)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 5)
        String mType;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_EventsCreator();
            sFields = new HashMap();
            sFields.put(DATE, Field.forString(DATE, 2));
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 3));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 4, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 5));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Events() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Events(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String date, @Param(id = 3) String formattedType, @Param(id = 4) DefaultMetadataImpl metadata, @Param(id = 5) String type) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mDate = date;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
        }

        public Events(Set<Integer> indicatorSet, String date, String formattedType, DefaultMetadataImpl metadata, String type) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mDate = date;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
        }

        public String getDate() {
            return this.mDate;
        }

        public boolean hasDate() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public Events setDate(String newValue) {
            this.mDate = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Events setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Events setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Events setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_EventsCreator defaultPersonImpl_EventsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_EventsCreator defaultPersonImpl_EventsCreator = CREATOR;
            DefaultPersonImpl_EventsCreator.writeToParcel(this, out, flags);
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
                    return this.mDate;
                case Type.CUSTOM /*3*/:
                    return this.mFormattedType;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mType;
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

        public static Events fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Events object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mDate = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mFormattedType = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mType = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Events)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Events entity = (Events) obj;
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

    @Class(creator = "DefaultPersonImpl_GendersCreator")
    public static final class Genders extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_GendersCreator CREATOR;
        public static final String FORMATTED_VALUE = "formattedValue";
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mFormattedValue;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_GendersCreator();
            sFields = new HashMap();
            sFields.put(FORMATTED_VALUE, Field.forString(FORMATTED_VALUE, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Genders() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Genders(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String formattedValue, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mFormattedValue = formattedValue;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public Genders(Set<Integer> indicatorSet, String formattedValue, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mFormattedValue = formattedValue;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public String getFormattedValue() {
            return this.mFormattedValue;
        }

        public boolean hasFormattedValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public Genders setFormattedValue(String newValue) {
            this.mFormattedValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Genders setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Genders setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_GendersCreator defaultPersonImpl_GendersCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_GendersCreator defaultPersonImpl_GendersCreator = CREATOR;
            DefaultPersonImpl_GendersCreator.writeToParcel(this, out, flags);
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
                    return this.mFormattedValue;
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mValue;
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

        public static Genders fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Genders object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mFormattedValue = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Genders)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Genders entity = (Genders) obj;
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

    @Class(creator = "DefaultPersonImpl_ImagesCreator")
    public static final class Images extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_ImagesCreator CREATOR;
        public static final String IS_DEFAULT = "isDefault";
        public static final String METADATA = "metadata";
        public static final String URL = "url";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        boolean mIsDefault;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mUrl;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_ImagesCreator();
            sFields = new HashMap();
            sFields.put(IS_DEFAULT, Field.forBoolean(IS_DEFAULT, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(URL, Field.forString(URL, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Images() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Images(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) boolean isDefault, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String url) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mIsDefault = isDefault;
            this.mMetadata = metadata;
            this.mUrl = url;
        }

        public Images(Set<Integer> indicatorSet, boolean isDefault, DefaultMetadataImpl metadata, String url) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mIsDefault = isDefault;
            this.mMetadata = metadata;
            this.mUrl = url;
        }

        public boolean isDefault() {
            return this.mIsDefault;
        }

        public boolean hasIsDefault() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getUrl() {
            return this.mUrl;
        }

        public boolean hasUrl() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public Images setIsDefault(boolean newValue) {
            this.mIsDefault = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Images setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Images setUrl(String newValue) {
            this.mUrl = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_ImagesCreator defaultPersonImpl_ImagesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_ImagesCreator defaultPersonImpl_ImagesCreator = CREATOR;
            DefaultPersonImpl_ImagesCreator.writeToParcel(this, out, flags);
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
                    return Boolean.valueOf(this.mIsDefault);
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mUrl;
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

        public static Images fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Images object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mIsDefault = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mUrl = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Images)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Images entity = (Images) obj;
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

    @Class(creator = "DefaultPersonImpl_InstantMessagingCreator")
    public static final class InstantMessaging extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_InstantMessagingCreator CREATOR;
        public static final String FORMATTED_PROTOCOL = "formattedProtocol";
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String PROTOCOL = "protocol";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mFormattedProtocol;
        @SafeParcelable.Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 4)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 5)
        String mProtocol;
        @SafeParcelable.Field(id = 6)
        String mType;
        @SafeParcelable.Field(id = 7)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_InstantMessagingCreator();
            sFields = new HashMap();
            sFields.put(FORMATTED_PROTOCOL, Field.forString(FORMATTED_PROTOCOL, 2));
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 3));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 4, DefaultMetadataImpl.class));
            sFields.put(PROTOCOL, Field.forString(PROTOCOL, 5));
            sFields.put(TYPE, Field.forString(TYPE, 6));
            sFields.put(VALUE, Field.forString(VALUE, 7));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public InstantMessaging() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        InstantMessaging(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String formattedProtocol, @Param(id = 3) String formattedType, @Param(id = 4) DefaultMetadataImpl metadata, @Param(id = 5) String protocol, @Param(id = 6) String type, @Param(id = 7) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mFormattedProtocol = formattedProtocol;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mProtocol = protocol;
            this.mType = type;
            this.mValue = value;
        }

        public InstantMessaging(Set<Integer> indicatorSet, String formattedProtocol, String formattedType, DefaultMetadataImpl metadata, String protocol, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mFormattedProtocol = formattedProtocol;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mProtocol = protocol;
            this.mType = type;
            this.mValue = value;
        }

        public String getFormattedProtocol() {
            return this.mFormattedProtocol;
        }

        public boolean hasFormattedProtocol() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getProtocol() {
            return this.mProtocol;
        }

        public boolean hasProtocol() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public InstantMessaging setFormattedProtocol(String newValue) {
            this.mFormattedProtocol = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public InstantMessaging setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public InstantMessaging setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public InstantMessaging setProtocol(String newValue) {
            this.mProtocol = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public InstantMessaging setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public InstantMessaging setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(7));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_InstantMessagingCreator defaultPersonImpl_InstantMessagingCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_InstantMessagingCreator defaultPersonImpl_InstantMessagingCreator = CREATOR;
            DefaultPersonImpl_InstantMessagingCreator.writeToParcel(this, out, flags);
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
                    return this.mFormattedProtocol;
                case Type.CUSTOM /*3*/:
                    return this.mFormattedType;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mProtocol;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return this.mType;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    return this.mValue;
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

        public static InstantMessaging fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            InstantMessaging object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mFormattedProtocol = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mFormattedType = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mProtocol = value;
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mType = value;
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof InstantMessaging)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            InstantMessaging entity = (InstantMessaging) obj;
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

    @Class(creator = "DefaultPersonImpl_LegacyFieldsCreator")
    public static final class LegacyFields extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_LegacyFieldsCreator CREATOR;
        public static final String MOBILE_OWNER_ID = "mobileOwnerId";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        String mMobileOwnerId;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_LegacyFieldsCreator();
            sFields = new HashMap();
            sFields.put(MOBILE_OWNER_ID, Field.forString(MOBILE_OWNER_ID, 2));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public LegacyFields() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        LegacyFields(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String mobileOwnerId) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMobileOwnerId = mobileOwnerId;
        }

        public LegacyFields(Set<Integer> indicatorSet, String mobileOwnerId) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMobileOwnerId = mobileOwnerId;
        }

        public String getMobileOwnerId() {
            return this.mMobileOwnerId;
        }

        public boolean hasMobileOwnerId() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public LegacyFields setMobileOwnerId(String newValue) {
            this.mMobileOwnerId = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_LegacyFieldsCreator defaultPersonImpl_LegacyFieldsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_LegacyFieldsCreator defaultPersonImpl_LegacyFieldsCreator = CREATOR;
            DefaultPersonImpl_LegacyFieldsCreator.writeToParcel(this, out, flags);
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
                    return this.mMobileOwnerId;
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

        public static LegacyFields fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            LegacyFields object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMobileOwnerId = value;
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
            if (!(obj instanceof LegacyFields)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            LegacyFields entity = (LegacyFields) obj;
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

    @Class(creator = "DefaultPersonImpl_MembershipsCreator")
    public static final class Memberships extends FastJsonResponse implements SafeParcelable {
        public static final String CIRCLE = "circle";
        public static final String CONTACT_GROUP = "contactGroup";
        public static final DefaultPersonImpl_MembershipsCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String SYSTEM_CONTACT_GROUP = "systemContactGroup";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mCircle;
        @SafeParcelable.Field(id = 3)
        String mContactGroup;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 4)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 5)
        String mSystemContactGroup;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_MembershipsCreator();
            sFields = new HashMap();
            sFields.put(CIRCLE, Field.forString(CIRCLE, 2));
            sFields.put(CONTACT_GROUP, Field.forString(CONTACT_GROUP, 3));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 4, DefaultMetadataImpl.class));
            sFields.put(SYSTEM_CONTACT_GROUP, Field.forString(SYSTEM_CONTACT_GROUP, 5));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Memberships() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Memberships(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String circle, @Param(id = 3) String contactGroup, @Param(id = 4) DefaultMetadataImpl metadata, @Param(id = 5) String systemContactGroup) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mCircle = circle;
            this.mContactGroup = contactGroup;
            this.mMetadata = metadata;
            this.mSystemContactGroup = systemContactGroup;
        }

        public Memberships(Set<Integer> indicatorSet, String circle, String contactGroup, DefaultMetadataImpl metadata, String systemContactGroup) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mCircle = circle;
            this.mContactGroup = contactGroup;
            this.mMetadata = metadata;
            this.mSystemContactGroup = systemContactGroup;
        }

        public String getCircle() {
            return this.mCircle;
        }

        public boolean hasCircle() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getContactGroup() {
            return this.mContactGroup;
        }

        public boolean hasContactGroup() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getSystemContactGroup() {
            return this.mSystemContactGroup;
        }

        public boolean hasSystemContactGroup() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public Memberships setCircle(String newValue) {
            this.mCircle = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Memberships setContactGroup(String newValue) {
            this.mContactGroup = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Memberships setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Memberships setSystemContactGroup(String newValue) {
            this.mSystemContactGroup = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_MembershipsCreator defaultPersonImpl_MembershipsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_MembershipsCreator defaultPersonImpl_MembershipsCreator = CREATOR;
            DefaultPersonImpl_MembershipsCreator.writeToParcel(this, out, flags);
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
                    return this.mCircle;
                case Type.CUSTOM /*3*/:
                    return this.mContactGroup;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mSystemContactGroup;
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

        public static Memberships fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Memberships object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mCircle = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mContactGroup = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mSystemContactGroup = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Memberships)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Memberships entity = (Memberships) obj;
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

    @Class(creator = "DefaultPersonImpl_MetadataCreator")
    public static final class Metadata extends FastJsonResponse implements SafeParcelable {
        public static final String AFFINITIES = "affinities";
        public static final String ATTRIBUTIONS = "attributions";
        public static final String BLOCKED = "blocked";
        public static final String BLOCK_TYPES = "blockTypes";
        public static final String CIRCLES = "circles";
        public static final String CONTACTS = "contacts";
        public static final DefaultPersonImpl_MetadataCreator CREATOR;
        public static final String DELETED = "deleted";
        public static final String GROUPS = "groups";
        public static final String INCOMING_BLOCK_TYPES = "incomingBlockTypes";
        public static final String IN_VIEWER_DOMAIN = "inViewerDomain";
        public static final String LAST_UPDATE_TIME_MICROS = "lastUpdateTimeMicros";
        public static final String OBJECT_TYPE = "objectType";
        public static final String OWNER_ID = "ownerId";
        public static final String OWNER_USER_TYPES = "ownerUserTypes";
        public static final String PEOPLE_IN_COMMON = "peopleInCommon";
        public static final String PLUS_PAGE_TYPE = "plusPageType";
        public static final String PROFILE_OWNER_STATS = "profileOwnerStats";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        List<Affinities> mAffinities;
        @SafeParcelable.Field(id = 3)
        List<String> mAttributions;
        @SafeParcelable.Field(id = 4)
        List<String> mBlockTypes;
        @SafeParcelable.Field(id = 5)
        boolean mBlocked;
        @SafeParcelable.Field(id = 6)
        List<String> mCircles;
        @SafeParcelable.Field(id = 7)
        List<String> mContacts;
        @SafeParcelable.Field(id = 8)
        boolean mDeleted;
        @SafeParcelable.Field(id = 9)
        List<String> mGroups;
        @SafeParcelable.Field(id = 10)
        boolean mInViewerDomain;
        @SafeParcelable.Field(id = 11)
        List<String> mIncomingBlockTypes;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 12)
        long mLastUpdateTimeMicros;
        @SafeParcelable.Field(id = 13)
        String mObjectType;
        @SafeParcelable.Field(id = 14)
        String mOwnerId;
        @SafeParcelable.Field(id = 15)
        List<String> mOwnerUserTypes;
        @SafeParcelable.Field(id = 16)
        List<DefaultPersonImpl> mPeopleInCommon;
        @SafeParcelable.Field(id = 17)
        String mPlusPageType;
        @SafeParcelable.Field(id = 18)
        ProfileOwnerStats mProfileOwnerStats;
        @VersionField(id = 1)
        final int mVersionCode;

        @Class(creator = "DefaultPersonImpl_Metadata_AffinitiesCreator")
        public static final class Affinities extends FastJsonResponse implements SafeParcelable {
            public static final DefaultPersonImpl_Metadata_AffinitiesCreator CREATOR;
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
                CREATOR = new DefaultPersonImpl_Metadata_AffinitiesCreator();
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
                DefaultPersonImpl_Metadata_AffinitiesCreator defaultPersonImpl_Metadata_AffinitiesCreator = CREATOR;
                return 0;
            }

            public void writeToParcel(Parcel out, int flags) {
                DefaultPersonImpl_Metadata_AffinitiesCreator defaultPersonImpl_Metadata_AffinitiesCreator = CREATOR;
                DefaultPersonImpl_Metadata_AffinitiesCreator.writeToParcel(this, out, flags);
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

        @Class(creator = "DefaultPersonImpl_Metadata_ProfileOwnerStatsCreator")
        public static final class ProfileOwnerStats extends FastJsonResponse implements SafeParcelable {
            public static final DefaultPersonImpl_Metadata_ProfileOwnerStatsCreator CREATOR;
            public static final String INCOMING_ANY_CIRCLE_COUNT = "incomingAnyCircleCount";
            private static final int VERSION_CODE = 1;
            public static final String VIEW_COUNT = "viewCount";
            private static final HashMap<String, Field<?, ?>> sFields;
            @SafeParcelable.Field(id = 2)
            long mIncomingAnyCircleCount;
            @Indicator
            final Set<Integer> mIndicatorSet;
            @VersionField(id = 1)
            final int mVersionCode;
            @SafeParcelable.Field(id = 3)
            long mViewCount;

            static {
                CREATOR = new DefaultPersonImpl_Metadata_ProfileOwnerStatsCreator();
                sFields = new HashMap();
                sFields.put(INCOMING_ANY_CIRCLE_COUNT, Field.forLong(INCOMING_ANY_CIRCLE_COUNT, 2));
                sFields.put(VIEW_COUNT, Field.forLong(VIEW_COUNT, 3));
            }

            public HashMap<String, Field<?, ?>> getFieldMappings() {
                return sFields;
            }

            public ProfileOwnerStats() {
                this.mVersionCode = VERSION_CODE;
                this.mIndicatorSet = new HashSet();
            }

            @Constructor
            ProfileOwnerStats(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) long incomingAnyCircleCount, @Param(id = 3) long viewCount) {
                this.mIndicatorSet = indicatorSet;
                this.mVersionCode = versionCode;
                this.mIncomingAnyCircleCount = incomingAnyCircleCount;
                this.mViewCount = viewCount;
            }

            public ProfileOwnerStats(Set<Integer> indicatorSet, long incomingAnyCircleCount, long viewCount) {
                this.mIndicatorSet = indicatorSet;
                this.mVersionCode = VERSION_CODE;
                this.mIncomingAnyCircleCount = incomingAnyCircleCount;
                this.mViewCount = viewCount;
            }

            public long getIncomingAnyCircleCount() {
                return this.mIncomingAnyCircleCount;
            }

            public boolean hasIncomingAnyCircleCount() {
                return this.mIndicatorSet.contains(Integer.valueOf(2));
            }

            public long getViewCount() {
                return this.mViewCount;
            }

            public boolean hasViewCount() {
                return this.mIndicatorSet.contains(Integer.valueOf(3));
            }

            public ProfileOwnerStats setIncomingAnyCircleCount(long newValue) {
                this.mIncomingAnyCircleCount = newValue;
                this.mIndicatorSet.add(Integer.valueOf(2));
                return this;
            }

            public ProfileOwnerStats setViewCount(long newValue) {
                this.mViewCount = newValue;
                this.mIndicatorSet.add(Integer.valueOf(3));
                return this;
            }

            public int describeContents() {
                DefaultPersonImpl_Metadata_ProfileOwnerStatsCreator defaultPersonImpl_Metadata_ProfileOwnerStatsCreator = CREATOR;
                return 0;
            }

            public void writeToParcel(Parcel out, int flags) {
                DefaultPersonImpl_Metadata_ProfileOwnerStatsCreator defaultPersonImpl_Metadata_ProfileOwnerStatsCreator = CREATOR;
                DefaultPersonImpl_Metadata_ProfileOwnerStatsCreator.writeToParcel(this, out, flags);
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
                        return Long.valueOf(this.mIncomingAnyCircleCount);
                    case Type.CUSTOM /*3*/:
                        return Long.valueOf(this.mViewCount);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.getSafeParcelableFieldId());
                }
            }

            public static ProfileOwnerStats fromByteArray(byte[] bytes) {
                Parcel parcel = Parcel.obtain();
                parcel.unmarshall(bytes, 0, bytes.length);
                parcel.setDataPosition(0);
                ProfileOwnerStats object = CREATOR.createFromParcel(parcel);
                parcel.recycle();
                return object;
            }

            protected void setLongInternal(Field<?, ?> field, String outputField, long value) {
                int safeParcelableFieldId = field.getSafeParcelableFieldId();
                switch (safeParcelableFieldId) {
                    case Type.INDEFINITELY /*2*/:
                        this.mIncomingAnyCircleCount = value;
                        break;
                    case Type.CUSTOM /*3*/:
                        this.mViewCount = value;
                        break;
                    default:
                        throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a double.");
                }
                this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
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
                if (!(obj instanceof ProfileOwnerStats)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                ProfileOwnerStats entity = (ProfileOwnerStats) obj;
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
            CREATOR = new DefaultPersonImpl_MetadataCreator();
            sFields = new HashMap();
            sFields.put(AFFINITIES, Field.forConcreteTypeArray(AFFINITIES, 2, Affinities.class));
            sFields.put(ATTRIBUTIONS, Field.forStrings(ATTRIBUTIONS, 3));
            sFields.put(BLOCK_TYPES, Field.forStrings(BLOCK_TYPES, 4));
            sFields.put(BLOCKED, Field.forBoolean(BLOCKED, 5));
            sFields.put(CIRCLES, Field.forStrings(CIRCLES, 6));
            sFields.put(CONTACTS, Field.forStrings(CONTACTS, 7));
            sFields.put(DELETED, Field.forBoolean(DELETED, 8));
            sFields.put(GROUPS, Field.forStrings(GROUPS, 9));
            sFields.put(IN_VIEWER_DOMAIN, Field.forBoolean(IN_VIEWER_DOMAIN, 10));
            sFields.put(INCOMING_BLOCK_TYPES, Field.forStrings(INCOMING_BLOCK_TYPES, 11));
            sFields.put(LAST_UPDATE_TIME_MICROS, Field.forLong(LAST_UPDATE_TIME_MICROS, 12));
            sFields.put(OBJECT_TYPE, Field.forString(OBJECT_TYPE, 13));
            sFields.put(OWNER_ID, Field.forString(OWNER_ID, 14));
            sFields.put(OWNER_USER_TYPES, Field.forStrings(OWNER_USER_TYPES, 15));
            sFields.put(PEOPLE_IN_COMMON, Field.forConcreteTypeArray(PEOPLE_IN_COMMON, 16, DefaultPersonImpl.class));
            sFields.put(PLUS_PAGE_TYPE, Field.forString(PLUS_PAGE_TYPE, 17));
            sFields.put(PROFILE_OWNER_STATS, Field.forConcreteType(PROFILE_OWNER_STATS, 18, ProfileOwnerStats.class));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Metadata() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Metadata(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) List<Affinities> affinities, @Param(id = 3) List<String> attributions, @Param(id = 4) List<String> blockTypes, @Param(id = 5) boolean blocked, @Param(id = 6) List<String> circles, @Param(id = 7) List<String> contacts, @Param(id = 8) boolean deleted, @Param(id = 9) List<String> groups, @Param(id = 10) boolean inViewerDomain, @Param(id = 11) List<String> incomingBlockTypes, @Param(id = 12) long lastUpdateTimeMicros, @Param(id = 13) String objectType, @Param(id = 14) String ownerId, @Param(id = 15) List<String> ownerUserTypes, @Param(id = 16) List<DefaultPersonImpl> peopleInCommon, @Param(id = 17) String plusPageType, @Param(id = 18) ProfileOwnerStats profileOwnerStats) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mAffinities = affinities;
            this.mAttributions = attributions;
            this.mBlockTypes = blockTypes;
            this.mBlocked = blocked;
            this.mCircles = circles;
            this.mContacts = contacts;
            this.mDeleted = deleted;
            this.mGroups = groups;
            this.mInViewerDomain = inViewerDomain;
            this.mIncomingBlockTypes = incomingBlockTypes;
            this.mLastUpdateTimeMicros = lastUpdateTimeMicros;
            this.mObjectType = objectType;
            this.mOwnerId = ownerId;
            this.mOwnerUserTypes = ownerUserTypes;
            this.mPeopleInCommon = peopleInCommon;
            this.mPlusPageType = plusPageType;
            this.mProfileOwnerStats = profileOwnerStats;
        }

        public Metadata(Set<Integer> indicatorSet, List<Affinities> affinities, List<String> attributions, List<String> blockTypes, boolean blocked, List<String> circles, List<String> contacts, boolean deleted, List<String> groups, boolean inViewerDomain, List<String> incomingBlockTypes, long lastUpdateTimeMicros, String objectType, String ownerId, List<String> ownerUserTypes, List<DefaultPersonImpl> peopleInCommon, String plusPageType, ProfileOwnerStats profileOwnerStats) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mAffinities = affinities;
            this.mAttributions = attributions;
            this.mBlockTypes = blockTypes;
            this.mBlocked = blocked;
            this.mCircles = circles;
            this.mContacts = contacts;
            this.mDeleted = deleted;
            this.mGroups = groups;
            this.mInViewerDomain = inViewerDomain;
            this.mIncomingBlockTypes = incomingBlockTypes;
            this.mLastUpdateTimeMicros = lastUpdateTimeMicros;
            this.mObjectType = objectType;
            this.mOwnerId = ownerId;
            this.mOwnerUserTypes = ownerUserTypes;
            this.mPeopleInCommon = peopleInCommon;
            this.mPlusPageType = plusPageType;
            this.mProfileOwnerStats = profileOwnerStats;
        }

        public List<Affinities> getAffinities() {
            return this.mAffinities;
        }

        public boolean hasAffinities() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public List<String> getAttributions() {
            return this.mAttributions;
        }

        public boolean hasAttributions() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public List<String> getBlockTypes() {
            return this.mBlockTypes;
        }

        public boolean hasBlockTypes() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public boolean isBlocked() {
            return this.mBlocked;
        }

        public boolean hasBlocked() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public List<String> getCircles() {
            return this.mCircles;
        }

        public boolean hasCircles() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public List<String> getContacts() {
            return this.mContacts;
        }

        public boolean hasContacts() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public boolean isDeleted() {
            return this.mDeleted;
        }

        public boolean hasDeleted() {
            return this.mIndicatorSet.contains(Integer.valueOf(8));
        }

        public List<String> getGroups() {
            return this.mGroups;
        }

        public boolean hasGroups() {
            return this.mIndicatorSet.contains(Integer.valueOf(9));
        }

        public boolean isInViewerDomain() {
            return this.mInViewerDomain;
        }

        public boolean hasInViewerDomain() {
            return this.mIndicatorSet.contains(Integer.valueOf(10));
        }

        public List<String> getIncomingBlockTypes() {
            return this.mIncomingBlockTypes;
        }

        public boolean hasIncomingBlockTypes() {
            return this.mIndicatorSet.contains(Integer.valueOf(11));
        }

        public long getLastUpdateTimeMicros() {
            return this.mLastUpdateTimeMicros;
        }

        public boolean hasLastUpdateTimeMicros() {
            return this.mIndicatorSet.contains(Integer.valueOf(12));
        }

        public String getObjectType() {
            return this.mObjectType;
        }

        public boolean hasObjectType() {
            return this.mIndicatorSet.contains(Integer.valueOf(13));
        }

        public String getOwnerId() {
            return this.mOwnerId;
        }

        public boolean hasOwnerId() {
            return this.mIndicatorSet.contains(Integer.valueOf(14));
        }

        public List<String> getOwnerUserTypes() {
            return this.mOwnerUserTypes;
        }

        public boolean hasOwnerUserTypes() {
            return this.mIndicatorSet.contains(Integer.valueOf(15));
        }

        public List<DefaultPersonImpl> getPeopleInCommon() {
            return this.mPeopleInCommon;
        }

        public boolean hasPeopleInCommon() {
            return this.mIndicatorSet.contains(Integer.valueOf(16));
        }

        public String getPlusPageType() {
            return this.mPlusPageType;
        }

        public boolean hasPlusPageType() {
            return this.mIndicatorSet.contains(Integer.valueOf(17));
        }

        public ProfileOwnerStats getProfileOwnerStats() {
            return this.mProfileOwnerStats;
        }

        public boolean hasProfileOwnerStats() {
            return this.mIndicatorSet.contains(Integer.valueOf(18));
        }

        public Metadata setAffinities(List<Affinities> newValue) {
            this.mAffinities = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Metadata addAffinities(Affinities newValue) {
            if (this.mAffinities == null) {
                this.mAffinities = new ArrayList();
            }
            this.mAffinities.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Metadata setAttributions(List<String> newValue) {
            this.mAttributions = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Metadata addAttributions(String newValue) {
            if (this.mAttributions == null) {
                this.mAttributions = new ArrayList();
            }
            this.mAttributions.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Metadata setBlockTypes(List<String> newValue) {
            this.mBlockTypes = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Metadata addBlockTypes(String newValue) {
            if (this.mBlockTypes == null) {
                this.mBlockTypes = new ArrayList();
            }
            this.mBlockTypes.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Metadata setBlocked(boolean newValue) {
            this.mBlocked = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public Metadata setCircles(List<String> newValue) {
            this.mCircles = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public Metadata addCircles(String newValue) {
            if (this.mCircles == null) {
                this.mCircles = new ArrayList();
            }
            this.mCircles.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public Metadata setContacts(List<String> newValue) {
            this.mContacts = newValue;
            this.mIndicatorSet.add(Integer.valueOf(7));
            return this;
        }

        public Metadata addContacts(String newValue) {
            if (this.mContacts == null) {
                this.mContacts = new ArrayList();
            }
            this.mContacts.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(7));
            return this;
        }

        public Metadata setDeleted(boolean newValue) {
            this.mDeleted = newValue;
            this.mIndicatorSet.add(Integer.valueOf(8));
            return this;
        }

        public Metadata setGroups(List<String> newValue) {
            this.mGroups = newValue;
            this.mIndicatorSet.add(Integer.valueOf(9));
            return this;
        }

        public Metadata addGroups(String newValue) {
            if (this.mGroups == null) {
                this.mGroups = new ArrayList();
            }
            this.mGroups.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(9));
            return this;
        }

        public Metadata setInViewerDomain(boolean newValue) {
            this.mInViewerDomain = newValue;
            this.mIndicatorSet.add(Integer.valueOf(10));
            return this;
        }

        public Metadata setIncomingBlockTypes(List<String> newValue) {
            this.mIncomingBlockTypes = newValue;
            this.mIndicatorSet.add(Integer.valueOf(11));
            return this;
        }

        public Metadata addIncomingBlockTypes(String newValue) {
            if (this.mIncomingBlockTypes == null) {
                this.mIncomingBlockTypes = new ArrayList();
            }
            this.mIncomingBlockTypes.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(11));
            return this;
        }

        public Metadata setLastUpdateTimeMicros(long newValue) {
            this.mLastUpdateTimeMicros = newValue;
            this.mIndicatorSet.add(Integer.valueOf(12));
            return this;
        }

        public Metadata setObjectType(String newValue) {
            this.mObjectType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(13));
            return this;
        }

        public Metadata setOwnerId(String newValue) {
            this.mOwnerId = newValue;
            this.mIndicatorSet.add(Integer.valueOf(14));
            return this;
        }

        public Metadata setOwnerUserTypes(List<String> newValue) {
            this.mOwnerUserTypes = newValue;
            this.mIndicatorSet.add(Integer.valueOf(15));
            return this;
        }

        public Metadata addOwnerUserTypes(String newValue) {
            if (this.mOwnerUserTypes == null) {
                this.mOwnerUserTypes = new ArrayList();
            }
            this.mOwnerUserTypes.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(15));
            return this;
        }

        public Metadata setPeopleInCommon(List<DefaultPersonImpl> newValue) {
            this.mPeopleInCommon = newValue;
            this.mIndicatorSet.add(Integer.valueOf(16));
            return this;
        }

        public Metadata addPeopleInCommon(DefaultPersonImpl newValue) {
            if (this.mPeopleInCommon == null) {
                this.mPeopleInCommon = new ArrayList();
            }
            this.mPeopleInCommon.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(16));
            return this;
        }

        public Metadata setPlusPageType(String newValue) {
            this.mPlusPageType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(17));
            return this;
        }

        public Metadata setProfileOwnerStats(ProfileOwnerStats newValue) {
            this.mProfileOwnerStats = newValue;
            this.mIndicatorSet.add(Integer.valueOf(18));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_MetadataCreator defaultPersonImpl_MetadataCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_MetadataCreator defaultPersonImpl_MetadataCreator = CREATOR;
            DefaultPersonImpl_MetadataCreator.writeToParcel(this, out, flags);
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
                    return this.mAttributions;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mBlockTypes;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return Boolean.valueOf(this.mBlocked);
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return this.mCircles;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    return this.mContacts;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    return Boolean.valueOf(this.mDeleted);
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    return this.mGroups;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    return Boolean.valueOf(this.mInViewerDomain);
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    return this.mIncomingBlockTypes;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    return Long.valueOf(this.mLastUpdateTimeMicros);
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    return this.mObjectType;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    return this.mOwnerId;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    return this.mOwnerUserTypes;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    return this.mPeopleInCommon;
                case LogSource.LE /*17*/:
                    return this.mPlusPageType;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    return this.mProfileOwnerStats;
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

        public static Metadata fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Metadata object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setLongInternal(Field<?, ?> field, String outputField, long value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    this.mLastUpdateTimeMicros = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a long.");
            }
        }

        protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mBlocked = value;
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    this.mDeleted = value;
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    this.mInViewerDomain = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    this.mObjectType = value;
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    this.mOwnerId = value;
                    break;
                case LogSource.LE /*17*/:
                    this.mPlusPageType = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        protected void setStringsInternal(Field<?, ?> field, String outputField, ArrayList<String> value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mAttributions = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mBlockTypes = value;
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mCircles = value;
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    this.mContacts = value;
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    this.mGroups = value;
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    this.mIncomingBlockTypes = value;
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    this.mOwnerUserTypes = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be an array of " + "String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    this.mProfileOwnerStats = (ProfileOwnerStats) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String outputField, ArrayList<T> value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mAffinities = value;
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    this.mPeopleInCommon = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known array of" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
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
            if (!(obj instanceof Metadata)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Metadata entity = (Metadata) obj;
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

    @Class(creator = "DefaultPersonImpl_NamesCreator")
    public static final class Names extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_NamesCreator CREATOR;
        public static final String DISPLAY_NAME = "displayName";
        public static final String FAMILY_NAME = "familyName";
        public static final String FORMATTED = "formatted";
        public static final String GIVEN_NAME = "givenName";
        public static final String HONORIFIC_PREFIX = "honorificPrefix";
        public static final String HONORIFIC_SUFFIX = "honorificSuffix";
        public static final String METADATA = "metadata";
        public static final String MIDDLE_NAME = "middleName";
        public static final String PHONETIC_FAMILY_NAME = "phoneticFamilyName";
        public static final String PHONETIC_GIVEN_NAME = "phoneticGivenName";
        public static final String PHONETIC_HONORIFIC_PREFIX = "phoneticHonorificPrefix";
        public static final String PHONETIC_HONORIFIC_SUFFIX = "phoneticHonorificSuffix";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mDisplayName;
        @SafeParcelable.Field(id = 3)
        String mFamilyName;
        @SafeParcelable.Field(id = 4)
        String mFormatted;
        @SafeParcelable.Field(id = 5)
        String mGivenName;
        @SafeParcelable.Field(id = 6)
        String mHonorificPrefix;
        @SafeParcelable.Field(id = 7)
        String mHonorificSuffix;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 8)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 9)
        String mMiddleName;
        @SafeParcelable.Field(id = 10)
        String mPhoneticFamilyName;
        @SafeParcelable.Field(id = 11)
        String mPhoneticGivenName;
        @SafeParcelable.Field(id = 12)
        String mPhoneticHonorificPrefix;
        @SafeParcelable.Field(id = 13)
        String mPhoneticHonorificSuffix;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_NamesCreator();
            sFields = new HashMap();
            sFields.put(DISPLAY_NAME, Field.forString(DISPLAY_NAME, 2));
            sFields.put(FAMILY_NAME, Field.forString(FAMILY_NAME, 3));
            sFields.put(FORMATTED, Field.forString(FORMATTED, 4));
            sFields.put(GIVEN_NAME, Field.forString(GIVEN_NAME, 5));
            sFields.put(HONORIFIC_PREFIX, Field.forString(HONORIFIC_PREFIX, 6));
            sFields.put(HONORIFIC_SUFFIX, Field.forString(HONORIFIC_SUFFIX, 7));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 8, DefaultMetadataImpl.class));
            sFields.put(MIDDLE_NAME, Field.forString(MIDDLE_NAME, 9));
            sFields.put(PHONETIC_FAMILY_NAME, Field.forString(PHONETIC_FAMILY_NAME, 10));
            sFields.put(PHONETIC_GIVEN_NAME, Field.forString(PHONETIC_GIVEN_NAME, 11));
            sFields.put(PHONETIC_HONORIFIC_PREFIX, Field.forString(PHONETIC_HONORIFIC_PREFIX, 12));
            sFields.put(PHONETIC_HONORIFIC_SUFFIX, Field.forString(PHONETIC_HONORIFIC_SUFFIX, 13));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Names() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Names(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String displayName, @Param(id = 3) String familyName, @Param(id = 4) String formatted, @Param(id = 5) String givenName, @Param(id = 6) String honorificPrefix, @Param(id = 7) String honorificSuffix, @Param(id = 8) DefaultMetadataImpl metadata, @Param(id = 9) String middleName, @Param(id = 10) String phoneticFamilyName, @Param(id = 11) String phoneticGivenName, @Param(id = 12) String phoneticHonorificPrefix, @Param(id = 13) String phoneticHonorificSuffix) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mDisplayName = displayName;
            this.mFamilyName = familyName;
            this.mFormatted = formatted;
            this.mGivenName = givenName;
            this.mHonorificPrefix = honorificPrefix;
            this.mHonorificSuffix = honorificSuffix;
            this.mMetadata = metadata;
            this.mMiddleName = middleName;
            this.mPhoneticFamilyName = phoneticFamilyName;
            this.mPhoneticGivenName = phoneticGivenName;
            this.mPhoneticHonorificPrefix = phoneticHonorificPrefix;
            this.mPhoneticHonorificSuffix = phoneticHonorificSuffix;
        }

        public Names(Set<Integer> indicatorSet, String displayName, String familyName, String formatted, String givenName, String honorificPrefix, String honorificSuffix, DefaultMetadataImpl metadata, String middleName, String phoneticFamilyName, String phoneticGivenName, String phoneticHonorificPrefix, String phoneticHonorificSuffix) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mDisplayName = displayName;
            this.mFamilyName = familyName;
            this.mFormatted = formatted;
            this.mGivenName = givenName;
            this.mHonorificPrefix = honorificPrefix;
            this.mHonorificSuffix = honorificSuffix;
            this.mMetadata = metadata;
            this.mMiddleName = middleName;
            this.mPhoneticFamilyName = phoneticFamilyName;
            this.mPhoneticGivenName = phoneticGivenName;
            this.mPhoneticHonorificPrefix = phoneticHonorificPrefix;
            this.mPhoneticHonorificSuffix = phoneticHonorificSuffix;
        }

        public String getDisplayName() {
            return this.mDisplayName;
        }

        public boolean hasDisplayName() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getFamilyName() {
            return this.mFamilyName;
        }

        public boolean hasFamilyName() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getFormatted() {
            return this.mFormatted;
        }

        public boolean hasFormatted() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getGivenName() {
            return this.mGivenName;
        }

        public boolean hasGivenName() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public String getHonorificPrefix() {
            return this.mHonorificPrefix;
        }

        public boolean hasHonorificPrefix() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public String getHonorificSuffix() {
            return this.mHonorificSuffix;
        }

        public boolean hasHonorificSuffix() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(8));
        }

        public String getMiddleName() {
            return this.mMiddleName;
        }

        public boolean hasMiddleName() {
            return this.mIndicatorSet.contains(Integer.valueOf(9));
        }

        public String getPhoneticFamilyName() {
            return this.mPhoneticFamilyName;
        }

        public boolean hasPhoneticFamilyName() {
            return this.mIndicatorSet.contains(Integer.valueOf(10));
        }

        public String getPhoneticGivenName() {
            return this.mPhoneticGivenName;
        }

        public boolean hasPhoneticGivenName() {
            return this.mIndicatorSet.contains(Integer.valueOf(11));
        }

        public String getPhoneticHonorificPrefix() {
            return this.mPhoneticHonorificPrefix;
        }

        public boolean hasPhoneticHonorificPrefix() {
            return this.mIndicatorSet.contains(Integer.valueOf(12));
        }

        public String getPhoneticHonorificSuffix() {
            return this.mPhoneticHonorificSuffix;
        }

        public boolean hasPhoneticHonorificSuffix() {
            return this.mIndicatorSet.contains(Integer.valueOf(13));
        }

        public Names setDisplayName(String newValue) {
            this.mDisplayName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Names setFamilyName(String newValue) {
            this.mFamilyName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Names setFormatted(String newValue) {
            this.mFormatted = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Names setGivenName(String newValue) {
            this.mGivenName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public Names setHonorificPrefix(String newValue) {
            this.mHonorificPrefix = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public Names setHonorificSuffix(String newValue) {
            this.mHonorificSuffix = newValue;
            this.mIndicatorSet.add(Integer.valueOf(7));
            return this;
        }

        public Names setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(8));
            return this;
        }

        public Names setMiddleName(String newValue) {
            this.mMiddleName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(9));
            return this;
        }

        public Names setPhoneticFamilyName(String newValue) {
            this.mPhoneticFamilyName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(10));
            return this;
        }

        public Names setPhoneticGivenName(String newValue) {
            this.mPhoneticGivenName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(11));
            return this;
        }

        public Names setPhoneticHonorificPrefix(String newValue) {
            this.mPhoneticHonorificPrefix = newValue;
            this.mIndicatorSet.add(Integer.valueOf(12));
            return this;
        }

        public Names setPhoneticHonorificSuffix(String newValue) {
            this.mPhoneticHonorificSuffix = newValue;
            this.mIndicatorSet.add(Integer.valueOf(13));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_NamesCreator defaultPersonImpl_NamesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_NamesCreator defaultPersonImpl_NamesCreator = CREATOR;
            DefaultPersonImpl_NamesCreator.writeToParcel(this, out, flags);
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
                    return this.mDisplayName;
                case Type.CUSTOM /*3*/:
                    return this.mFamilyName;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mFormatted;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mGivenName;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return this.mHonorificPrefix;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    return this.mHonorificSuffix;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    return this.mMiddleName;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    return this.mPhoneticFamilyName;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    return this.mPhoneticGivenName;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    return this.mPhoneticHonorificPrefix;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    return this.mPhoneticHonorificSuffix;
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

        public static Names fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Names object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mDisplayName = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mFamilyName = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mFormatted = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mGivenName = value;
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mHonorificPrefix = value;
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    this.mHonorificSuffix = value;
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    this.mMiddleName = value;
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    this.mPhoneticFamilyName = value;
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    this.mPhoneticGivenName = value;
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    this.mPhoneticHonorificPrefix = value;
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    this.mPhoneticHonorificSuffix = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Names)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Names entity = (Names) obj;
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

    @Class(creator = "DefaultPersonImpl_NicknamesCreator")
    public static final class Nicknames extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_NicknamesCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mType;
        @SafeParcelable.Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_NicknamesCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 3));
            sFields.put(VALUE, Field.forString(VALUE, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Nicknames() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Nicknames(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String type, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public Nicknames(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public Nicknames setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Nicknames setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Nicknames setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_NicknamesCreator defaultPersonImpl_NicknamesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_NicknamesCreator defaultPersonImpl_NicknamesCreator = CREATOR;
            DefaultPersonImpl_NicknamesCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mType;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mValue;
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

        public static Nicknames fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Nicknames object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mType = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Nicknames)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Nicknames entity = (Nicknames) obj;
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

    @Class(creator = "DefaultPersonImpl_OccupationsCreator")
    public static final class Occupations extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_OccupationsCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_OccupationsCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Occupations() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Occupations(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public Occupations(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public Occupations setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Occupations setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_OccupationsCreator defaultPersonImpl_OccupationsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_OccupationsCreator defaultPersonImpl_OccupationsCreator = CREATOR;
            DefaultPersonImpl_OccupationsCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mValue;
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

        public static Occupations fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Occupations object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Occupations)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Occupations entity = (Occupations) obj;
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

    @Class(creator = "DefaultPersonImpl_OrganizationsCreator")
    public static final class Organizations extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_OrganizationsCreator CREATOR;
        public static final String CURRENT = "current";
        public static final String DEPARTMENT = "department";
        public static final String DESCRIPTION = "description";
        public static final String DOMAIN = "domain";
        public static final String END_DATE = "endDate";
        public static final String LOCATION = "location";
        public static final String METADATA = "metadata";
        public static final String NAME = "name";
        public static final String PHONETIC_NAME = "phoneticName";
        public static final String START_DATE = "startDate";
        public static final String SYMBOL = "symbol";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        boolean mCurrent;
        @SafeParcelable.Field(id = 3)
        String mDepartment;
        @SafeParcelable.Field(id = 4)
        String mDescription;
        @SafeParcelable.Field(id = 5)
        String mDomain;
        @SafeParcelable.Field(id = 6)
        String mEndDate;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 7)
        String mLocation;
        @SafeParcelable.Field(id = 8)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 9)
        String mName;
        @SafeParcelable.Field(id = 10)
        String mPhoneticName;
        @SafeParcelable.Field(id = 11)
        String mStartDate;
        @SafeParcelable.Field(id = 12)
        String mSymbol;
        @SafeParcelable.Field(id = 13)
        String mTitle;
        @SafeParcelable.Field(id = 14)
        String mType;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_OrganizationsCreator();
            sFields = new HashMap();
            sFields.put(CURRENT, Field.forBoolean(CURRENT, 2));
            sFields.put(DEPARTMENT, Field.forString(DEPARTMENT, 3));
            sFields.put(DESCRIPTION, Field.forString(DESCRIPTION, 4));
            sFields.put(DOMAIN, Field.forString(DOMAIN, 5));
            sFields.put(END_DATE, Field.forString(END_DATE, 6));
            sFields.put(LOCATION, Field.forString(LOCATION, 7));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 8, DefaultMetadataImpl.class));
            sFields.put(NAME, Field.forString(NAME, 9));
            sFields.put(PHONETIC_NAME, Field.forString(PHONETIC_NAME, 10));
            sFields.put(START_DATE, Field.forString(START_DATE, 11));
            sFields.put(SYMBOL, Field.forString(SYMBOL, 12));
            sFields.put(TITLE, Field.forString(TITLE, 13));
            sFields.put(TYPE, Field.forString(TYPE, 14));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Organizations() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Organizations(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) boolean current, @Param(id = 3) String department, @Param(id = 4) String description, @Param(id = 5) String domain, @Param(id = 6) String endDate, @Param(id = 7) String location, @Param(id = 8) DefaultMetadataImpl metadata, @Param(id = 9) String name, @Param(id = 10) String phoneticName, @Param(id = 11) String startDate, @Param(id = 12) String symbol, @Param(id = 13) String title, @Param(id = 14) String type) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mCurrent = current;
            this.mDepartment = department;
            this.mDescription = description;
            this.mDomain = domain;
            this.mEndDate = endDate;
            this.mLocation = location;
            this.mMetadata = metadata;
            this.mName = name;
            this.mPhoneticName = phoneticName;
            this.mStartDate = startDate;
            this.mSymbol = symbol;
            this.mTitle = title;
            this.mType = type;
        }

        public Organizations(Set<Integer> indicatorSet, boolean current, String department, String description, String domain, String endDate, String location, DefaultMetadataImpl metadata, String name, String phoneticName, String startDate, String symbol, String title, String type) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mCurrent = current;
            this.mDepartment = department;
            this.mDescription = description;
            this.mDomain = domain;
            this.mEndDate = endDate;
            this.mLocation = location;
            this.mMetadata = metadata;
            this.mName = name;
            this.mPhoneticName = phoneticName;
            this.mStartDate = startDate;
            this.mSymbol = symbol;
            this.mTitle = title;
            this.mType = type;
        }

        public boolean isCurrent() {
            return this.mCurrent;
        }

        public boolean hasCurrent() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getDepartment() {
            return this.mDepartment;
        }

        public boolean hasDepartment() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getDescription() {
            return this.mDescription;
        }

        public boolean hasDescription() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getDomain() {
            return this.mDomain;
        }

        public boolean hasDomain() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public String getEndDate() {
            return this.mEndDate;
        }

        public boolean hasEndDate() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public String getLocation() {
            return this.mLocation;
        }

        public boolean hasLocation() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(8));
        }

        public String getName() {
            return this.mName;
        }

        public boolean hasName() {
            return this.mIndicatorSet.contains(Integer.valueOf(9));
        }

        public String getPhoneticName() {
            return this.mPhoneticName;
        }

        public boolean hasPhoneticName() {
            return this.mIndicatorSet.contains(Integer.valueOf(10));
        }

        public String getStartDate() {
            return this.mStartDate;
        }

        public boolean hasStartDate() {
            return this.mIndicatorSet.contains(Integer.valueOf(11));
        }

        public String getSymbol() {
            return this.mSymbol;
        }

        public boolean hasSymbol() {
            return this.mIndicatorSet.contains(Integer.valueOf(12));
        }

        public String getTitle() {
            return this.mTitle;
        }

        public boolean hasTitle() {
            return this.mIndicatorSet.contains(Integer.valueOf(13));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(14));
        }

        public Organizations setCurrent(boolean newValue) {
            this.mCurrent = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Organizations setDepartment(String newValue) {
            this.mDepartment = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Organizations setDescription(String newValue) {
            this.mDescription = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Organizations setDomain(String newValue) {
            this.mDomain = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public Organizations setEndDate(String newValue) {
            this.mEndDate = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public Organizations setLocation(String newValue) {
            this.mLocation = newValue;
            this.mIndicatorSet.add(Integer.valueOf(7));
            return this;
        }

        public Organizations setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(8));
            return this;
        }

        public Organizations setName(String newValue) {
            this.mName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(9));
            return this;
        }

        public Organizations setPhoneticName(String newValue) {
            this.mPhoneticName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(10));
            return this;
        }

        public Organizations setStartDate(String newValue) {
            this.mStartDate = newValue;
            this.mIndicatorSet.add(Integer.valueOf(11));
            return this;
        }

        public Organizations setSymbol(String newValue) {
            this.mSymbol = newValue;
            this.mIndicatorSet.add(Integer.valueOf(12));
            return this;
        }

        public Organizations setTitle(String newValue) {
            this.mTitle = newValue;
            this.mIndicatorSet.add(Integer.valueOf(13));
            return this;
        }

        public Organizations setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(14));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_OrganizationsCreator defaultPersonImpl_OrganizationsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_OrganizationsCreator defaultPersonImpl_OrganizationsCreator = CREATOR;
            DefaultPersonImpl_OrganizationsCreator.writeToParcel(this, out, flags);
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
                    return Boolean.valueOf(this.mCurrent);
                case Type.CUSTOM /*3*/:
                    return this.mDepartment;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mDescription;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mDomain;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return this.mEndDate;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    return this.mLocation;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    return this.mName;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    return this.mPhoneticName;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    return this.mStartDate;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    return this.mSymbol;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    return this.mTitle;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    return this.mType;
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

        public static Organizations fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Organizations object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mCurrent = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mDepartment = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mDescription = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mDomain = value;
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mEndDate = value;
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    this.mLocation = value;
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    this.mName = value;
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    this.mPhoneticName = value;
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    this.mStartDate = value;
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    this.mSymbol = value;
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    this.mTitle = value;
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    this.mType = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Organizations)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Organizations entity = (Organizations) obj;
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

    @Class(creator = "DefaultPersonImpl_PhoneNumbersCreator")
    public static final class PhoneNumbers extends FastJsonResponse implements SafeParcelable {
        public static final String CANONICALIZED_FORM = "canonicalizedForm";
        public static final DefaultPersonImpl_PhoneNumbersCreator CREATOR;
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mCanonicalizedForm;
        @SafeParcelable.Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 4)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 5)
        String mType;
        @SafeParcelable.Field(id = 6)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_PhoneNumbersCreator();
            sFields = new HashMap();
            sFields.put(CANONICALIZED_FORM, Field.forString(CANONICALIZED_FORM, 2));
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 3));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 4, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 5));
            sFields.put(VALUE, Field.forString(VALUE, 6));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public PhoneNumbers() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        PhoneNumbers(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String canonicalizedForm, @Param(id = 3) String formattedType, @Param(id = 4) DefaultMetadataImpl metadata, @Param(id = 5) String type, @Param(id = 6) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mCanonicalizedForm = canonicalizedForm;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public PhoneNumbers(Set<Integer> indicatorSet, String canonicalizedForm, String formattedType, DefaultMetadataImpl metadata, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mCanonicalizedForm = canonicalizedForm;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public String getCanonicalizedForm() {
            return this.mCanonicalizedForm;
        }

        public boolean hasCanonicalizedForm() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public PhoneNumbers setCanonicalizedForm(String newValue) {
            this.mCanonicalizedForm = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public PhoneNumbers setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public PhoneNumbers setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public PhoneNumbers setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public PhoneNumbers setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(6));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_PhoneNumbersCreator defaultPersonImpl_PhoneNumbersCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_PhoneNumbersCreator defaultPersonImpl_PhoneNumbersCreator = CREATOR;
            DefaultPersonImpl_PhoneNumbersCreator.writeToParcel(this, out, flags);
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
                    return this.mCanonicalizedForm;
                case Type.CUSTOM /*3*/:
                    return this.mFormattedType;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mMetadata;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mType;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    return this.mValue;
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

        public static PhoneNumbers fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            PhoneNumbers object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mCanonicalizedForm = value;
                    break;
                case Type.CUSTOM /*3*/:
                    this.mFormattedType = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mType = value;
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof PhoneNumbers)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            PhoneNumbers entity = (PhoneNumbers) obj;
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

    @Class(creator = "DefaultPersonImpl_PlacesLivedCreator")
    public static final class PlacesLived extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_PlacesLivedCreator CREATOR;
        public static final String CURRENT = "current";
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        boolean mCurrent;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_PlacesLivedCreator();
            sFields = new HashMap();
            sFields.put(CURRENT, Field.forBoolean(CURRENT, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public PlacesLived() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        PlacesLived(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) boolean current, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mCurrent = current;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public PlacesLived(Set<Integer> indicatorSet, boolean current, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mCurrent = current;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public boolean isCurrent() {
            return this.mCurrent;
        }

        public boolean hasCurrent() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public PlacesLived setCurrent(boolean newValue) {
            this.mCurrent = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public PlacesLived setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public PlacesLived setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_PlacesLivedCreator defaultPersonImpl_PlacesLivedCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_PlacesLivedCreator defaultPersonImpl_PlacesLivedCreator = CREATOR;
            DefaultPersonImpl_PlacesLivedCreator.writeToParcel(this, out, flags);
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
                    return Boolean.valueOf(this.mCurrent);
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mValue;
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

        public static PlacesLived fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            PlacesLived object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setBooleanInternal(Field<?, ?> field, String outputField, boolean value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mCurrent = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a boolean.");
            }
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof PlacesLived)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            PlacesLived entity = (PlacesLived) obj;
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

    @Class(creator = "DefaultPersonImpl_RelationsCreator")
    public static final class Relations extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_RelationsCreator CREATOR;
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mType;
        @SafeParcelable.Field(id = 5)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_RelationsCreator();
            sFields = new HashMap();
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 4));
            sFields.put(VALUE, Field.forString(VALUE, 5));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Relations() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Relations(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String formattedType, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String type, @Param(id = 5) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public Relations(Set<Integer> indicatorSet, String formattedType, DefaultMetadataImpl metadata, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public Relations setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Relations setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Relations setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Relations setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_RelationsCreator defaultPersonImpl_RelationsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_RelationsCreator defaultPersonImpl_RelationsCreator = CREATOR;
            DefaultPersonImpl_RelationsCreator.writeToParcel(this, out, flags);
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
                    return this.mFormattedType;
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mType;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mValue;
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

        public static Relations fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Relations object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mFormattedType = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mType = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Relations)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Relations entity = (Relations) obj;
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

    @Class(creator = "DefaultPersonImpl_RelationshipInterestsCreator")
    public static final class RelationshipInterests extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_RelationshipInterestsCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_RelationshipInterestsCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public RelationshipInterests() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        RelationshipInterests(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public RelationshipInterests(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public RelationshipInterests setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public RelationshipInterests setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_RelationshipInterestsCreator defaultPersonImpl_RelationshipInterestsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_RelationshipInterestsCreator defaultPersonImpl_RelationshipInterestsCreator = CREATOR;
            DefaultPersonImpl_RelationshipInterestsCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mValue;
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

        public static RelationshipInterests fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            RelationshipInterests object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof RelationshipInterests)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            RelationshipInterests entity = (RelationshipInterests) obj;
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

    @Class(creator = "DefaultPersonImpl_RelationshipStatusesCreator")
    public static final class RelationshipStatuses extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_RelationshipStatusesCreator CREATOR;
        public static final String FORMATTED_VALUE = "formattedValue";
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mFormattedValue;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_RelationshipStatusesCreator();
            sFields = new HashMap();
            sFields.put(FORMATTED_VALUE, Field.forString(FORMATTED_VALUE, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public RelationshipStatuses() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        RelationshipStatuses(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String formattedValue, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mFormattedValue = formattedValue;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public RelationshipStatuses(Set<Integer> indicatorSet, String formattedValue, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mFormattedValue = formattedValue;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public String getFormattedValue() {
            return this.mFormattedValue;
        }

        public boolean hasFormattedValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public RelationshipStatuses setFormattedValue(String newValue) {
            this.mFormattedValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public RelationshipStatuses setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public RelationshipStatuses setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_RelationshipStatusesCreator defaultPersonImpl_RelationshipStatusesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_RelationshipStatusesCreator defaultPersonImpl_RelationshipStatusesCreator = CREATOR;
            DefaultPersonImpl_RelationshipStatusesCreator.writeToParcel(this, out, flags);
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
                    return this.mFormattedValue;
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mValue;
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

        public static RelationshipStatuses fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            RelationshipStatuses object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mFormattedValue = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof RelationshipStatuses)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            RelationshipStatuses entity = (RelationshipStatuses) obj;
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

    @Class(creator = "DefaultPersonImpl_SkillsCreator")
    public static final class Skills extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_SkillsCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_SkillsCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Skills() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Skills(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public Skills(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public Skills setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Skills setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_SkillsCreator defaultPersonImpl_SkillsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_SkillsCreator defaultPersonImpl_SkillsCreator = CREATOR;
            DefaultPersonImpl_SkillsCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mValue;
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

        public static Skills fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Skills object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Skills)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Skills entity = (Skills) obj;
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

    @Class(creator = "DefaultPersonImpl_SortKeysCreator")
    public static final class SortKeys extends FastJsonResponse implements SafeParcelable {
        public static final String AFFINITIES = "affinities";
        public static final DefaultPersonImpl_SortKeysCreator CREATOR;
        public static final String INTERACTION_RANK = "interactionRank";
        public static final String NAME = "name";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        List<Affinities> mAffinities;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        String mInteractionRank;
        @SafeParcelable.Field(id = 4)
        String mName;
        @VersionField(id = 1)
        final int mVersionCode;

        @Class(creator = "DefaultPersonImpl_SortKeys_AffinitiesCreator")
        public static final class Affinities extends FastJsonResponse implements SafeParcelable {
            public static final DefaultPersonImpl_SortKeys_AffinitiesCreator CREATOR;
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
                CREATOR = new DefaultPersonImpl_SortKeys_AffinitiesCreator();
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
                DefaultPersonImpl_SortKeys_AffinitiesCreator defaultPersonImpl_SortKeys_AffinitiesCreator = CREATOR;
                return 0;
            }

            public void writeToParcel(Parcel out, int flags) {
                DefaultPersonImpl_SortKeys_AffinitiesCreator defaultPersonImpl_SortKeys_AffinitiesCreator = CREATOR;
                DefaultPersonImpl_SortKeys_AffinitiesCreator.writeToParcel(this, out, flags);
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
            CREATOR = new DefaultPersonImpl_SortKeysCreator();
            sFields = new HashMap();
            sFields.put(AFFINITIES, Field.forConcreteTypeArray(AFFINITIES, 2, Affinities.class));
            sFields.put(INTERACTION_RANK, Field.forString(INTERACTION_RANK, 3));
            sFields.put(NAME, Field.forString(NAME, 4));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public SortKeys() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        SortKeys(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) List<Affinities> affinities, @Param(id = 3) String interactionRank, @Param(id = 4) String name) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mAffinities = affinities;
            this.mInteractionRank = interactionRank;
            this.mName = name;
        }

        public SortKeys(Set<Integer> indicatorSet, List<Affinities> affinities, String interactionRank, String name) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mAffinities = affinities;
            this.mInteractionRank = interactionRank;
            this.mName = name;
        }

        public List<Affinities> getAffinities() {
            return this.mAffinities;
        }

        public boolean hasAffinities() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getInteractionRank() {
            return this.mInteractionRank;
        }

        public boolean hasInteractionRank() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getName() {
            return this.mName;
        }

        public boolean hasName() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public SortKeys setAffinities(List<Affinities> newValue) {
            this.mAffinities = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public SortKeys addAffinities(Affinities newValue) {
            if (this.mAffinities == null) {
                this.mAffinities = new ArrayList();
            }
            this.mAffinities.add(newValue);
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public SortKeys setInteractionRank(String newValue) {
            this.mInteractionRank = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public SortKeys setName(String newValue) {
            this.mName = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_SortKeysCreator defaultPersonImpl_SortKeysCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_SortKeysCreator defaultPersonImpl_SortKeysCreator = CREATOR;
            DefaultPersonImpl_SortKeysCreator.writeToParcel(this, out, flags);
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
                    return this.mInteractionRank;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mName;
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

        public static SortKeys fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            SortKeys object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mInteractionRank = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mName = value;
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
            if (!(obj instanceof SortKeys)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            SortKeys entity = (SortKeys) obj;
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

    @Class(creator = "DefaultPersonImpl_TaglinesCreator")
    public static final class Taglines extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_TaglinesCreator CREATOR;
        public static final String METADATA = "metadata";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 2)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_TaglinesCreator();
            sFields = new HashMap();
            sFields.put(METADATA, Field.forConcreteType(METADATA, 2, DefaultMetadataImpl.class));
            sFields.put(VALUE, Field.forString(VALUE, 3));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Taglines() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Taglines(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) DefaultMetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public Taglines(Set<Integer> indicatorSet, DefaultMetadataImpl metadata, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public Taglines setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Taglines setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_TaglinesCreator defaultPersonImpl_TaglinesCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_TaglinesCreator defaultPersonImpl_TaglinesCreator = CREATOR;
            DefaultPersonImpl_TaglinesCreator.writeToParcel(this, out, flags);
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
                    return this.mMetadata;
                case Type.CUSTOM /*3*/:
                    return this.mValue;
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

        public static Taglines fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Taglines object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mValue = value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Taglines)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Taglines entity = (Taglines) obj;
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

    @Class(creator = "DefaultPersonImpl_UrlsCreator")
    public static final class Urls extends FastJsonResponse implements SafeParcelable {
        public static final DefaultPersonImpl_UrlsCreator CREATOR;
        public static final String FORMATTED_TYPE = "formattedType";
        public static final String METADATA = "metadata";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
        private static final int VERSION_CODE = 1;
        private static final HashMap<String, Field<?, ?>> sFields;
        @SafeParcelable.Field(id = 2)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @SafeParcelable.Field(id = 3)
        DefaultMetadataImpl mMetadata;
        @SafeParcelable.Field(id = 4)
        String mType;
        @SafeParcelable.Field(id = 5)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new DefaultPersonImpl_UrlsCreator();
            sFields = new HashMap();
            sFields.put(FORMATTED_TYPE, Field.forString(FORMATTED_TYPE, 2));
            sFields.put(METADATA, Field.forConcreteType(METADATA, 3, DefaultMetadataImpl.class));
            sFields.put(TYPE, Field.forString(TYPE, 4));
            sFields.put(VALUE, Field.forString(VALUE, 5));
        }

        public HashMap<String, Field<?, ?>> getFieldMappings() {
            return sFields;
        }

        public Urls() {
            this.mVersionCode = VERSION_CODE;
            this.mIndicatorSet = new HashSet();
        }

        @Constructor
        Urls(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String formattedType, @Param(id = 3) DefaultMetadataImpl metadata, @Param(id = 4) String type, @Param(id = 5) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public Urls(Set<Integer> indicatorSet, String formattedType, DefaultMetadataImpl metadata, String type, String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = VERSION_CODE;
            this.mFormattedType = formattedType;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public boolean hasFormattedType() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public DefaultMetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public boolean hasMetadata() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public String getType() {
            return this.mType;
        }

        public boolean hasType() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasValue() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public Urls setFormattedType(String newValue) {
            this.mFormattedType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(2));
            return this;
        }

        public Urls setMetadata(DefaultMetadataImpl newValue) {
            this.mMetadata = newValue;
            this.mIndicatorSet.add(Integer.valueOf(3));
            return this;
        }

        public Urls setType(String newValue) {
            this.mType = newValue;
            this.mIndicatorSet.add(Integer.valueOf(4));
            return this;
        }

        public Urls setValue(String newValue) {
            this.mValue = newValue;
            this.mIndicatorSet.add(Integer.valueOf(5));
            return this;
        }

        public int describeContents() {
            DefaultPersonImpl_UrlsCreator defaultPersonImpl_UrlsCreator = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            DefaultPersonImpl_UrlsCreator defaultPersonImpl_UrlsCreator = CREATOR;
            DefaultPersonImpl_UrlsCreator.writeToParcel(this, out, flags);
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
                    return this.mFormattedType;
                case Type.CUSTOM /*3*/:
                    return this.mMetadata;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    return this.mType;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    return this.mValue;
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

        public static Urls fromByteArray(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0);
            Urls object = CREATOR.createFromParcel(parcel);
            parcel.recycle();
            return object;
        }

        protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.INDEFINITELY /*2*/:
                    this.mFormattedType = value;
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    this.mType = value;
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    this.mValue = value;
                    break;
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
            }
            this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
        }

        public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
            int safeParcelableFieldId = field.getSafeParcelableFieldId();
            switch (safeParcelableFieldId) {
                case Type.CUSTOM /*3*/:
                    this.mMetadata = (DefaultMetadataImpl) value;
                    this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
                default:
                    throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
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
            if (!(obj instanceof Urls)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            Urls entity = (Urls) obj;
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
        CREATOR = new DefaultPersonImplCreator();
        sFields = new HashMap();
        sFields.put(ABOUTS, Field.forConcreteTypeArray(ABOUTS, 2, Abouts.class));
        sFields.put(ADDRESSES, Field.forConcreteTypeArray(ADDRESSES, 3, Addresses.class));
        sFields.put(AGE_RANGE, Field.forString(AGE_RANGE, 4));
        sFields.put(BIRTHDAYS, Field.forConcreteTypeArray(BIRTHDAYS, 5, Birthdays.class));
        sFields.put(BRAGGING_RIGHTS, Field.forConcreteTypeArray(BRAGGING_RIGHTS, 6, BraggingRights.class));
        sFields.put(COVER_PHOTOS, Field.forConcreteTypeArray(COVER_PHOTOS, 7, CoverPhotos.class));
        sFields.put(CUSTOM_FIELDS, Field.forConcreteTypeArray(CUSTOM_FIELDS, 8, CustomFields.class));
        sFields.put(EMAILS, Field.forConcreteTypeArray(EMAILS, 9, Emails.class));
        sFields.put(ETAG, Field.forString(ETAG, 10));
        sFields.put(EVENTS, Field.forConcreteTypeArray(EVENTS, 11, Events.class));
        sFields.put(GENDERS, Field.forConcreteTypeArray(GENDERS, 12, Genders.class));
        sFields.put(ID, Field.forString(ID, 13));
        sFields.put(IMAGES, Field.forConcreteTypeArray(IMAGES, 14, Images.class));
        sFields.put(INSTANT_MESSAGING, Field.forConcreteTypeArray(INSTANT_MESSAGING, 15, InstantMessaging.class));
        sFields.put(LANGUAGE, Field.forString(LANGUAGE, 17));
        sFields.put(LEGACY_FIELDS, Field.forConcreteType(LEGACY_FIELDS, 18, LegacyFields.class));
        sFields.put(LINKED_PEOPLE, Field.forConcreteTypeArray(LINKED_PEOPLE, 19, DefaultPersonImpl.class));
        sFields.put(MEMBERSHIPS, Field.forConcreteTypeArray(MEMBERSHIPS, 20, Memberships.class));
        sFields.put(METADATA, Field.forConcreteType(METADATA, 21, Metadata.class));
        sFields.put(NAMES, Field.forConcreteTypeArray(NAMES, 22, Names.class));
        sFields.put(NICKNAMES, Field.forConcreteTypeArray(NICKNAMES, 23, Nicknames.class));
        sFields.put(OCCUPATIONS, Field.forConcreteTypeArray(OCCUPATIONS, 24, Occupations.class));
        sFields.put(ORGANIZATIONS, Field.forConcreteTypeArray(ORGANIZATIONS, 25, Organizations.class));
        sFields.put(PHONE_NUMBERS, Field.forConcreteTypeArray(PHONE_NUMBERS, 26, PhoneNumbers.class));
        sFields.put(PLACES_LIVED, Field.forConcreteTypeArray(PLACES_LIVED, 27, PlacesLived.class));
        sFields.put(PROFILE_URL, Field.forString(PROFILE_URL, 28));
        sFields.put(RELATIONS, Field.forConcreteTypeArray(RELATIONS, 29, Relations.class));
        sFields.put(RELATIONSHIP_INTERESTS, Field.forConcreteTypeArray(RELATIONSHIP_INTERESTS, 30, RelationshipInterests.class));
        sFields.put(RELATIONSHIP_STATUSES, Field.forConcreteTypeArray(RELATIONSHIP_STATUSES, 31, RelationshipStatuses.class));
        sFields.put(SKILLS, Field.forConcreteTypeArray(SKILLS, 32, Skills.class));
        sFields.put(SORT_KEYS, Field.forConcreteType(SORT_KEYS, 33, SortKeys.class));
        sFields.put(TAGLINES, Field.forConcreteTypeArray(TAGLINES, 34, Taglines.class));
        sFields.put(URLS, Field.forConcreteTypeArray(URLS, 35, Urls.class));
    }

    public HashMap<String, Field<?, ?>> getFieldMappings() {
        return sFields;
    }

    public DefaultPersonImpl() {
        this.mVersionCode = VERSION_CODE;
        this.mIndicatorSet = new HashSet();
    }

    @Constructor
    DefaultPersonImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) List<Abouts> abouts, @Param(id = 3) List<Addresses> addresses, @Param(id = 4) String ageRange, @Param(id = 5) List<Birthdays> birthdays, @Param(id = 6) List<BraggingRights> braggingRights, @Param(id = 7) List<CoverPhotos> coverPhotos, @Param(id = 8) List<CustomFields> customFields, @Param(id = 9) List<Emails> emails, @Param(id = 10) String etag, @Param(id = 11) List<Events> events, @Param(id = 12) List<Genders> genders, @Param(id = 13) String id, @Param(id = 14) List<Images> images, @Param(id = 15) List<InstantMessaging> instantMessaging, @Param(id = 17) String language, @Param(id = 18) LegacyFields legacyFields, @Param(id = 19) List<DefaultPersonImpl> linkedPeople, @Param(id = 20) List<Memberships> memberships, @Param(id = 21) Metadata metadata, @Param(id = 22) List<Names> names, @Param(id = 23) List<Nicknames> nicknames, @Param(id = 24) List<Occupations> occupations, @Param(id = 25) List<Organizations> organizations, @Param(id = 26) List<PhoneNumbers> phoneNumbers, @Param(id = 27) List<PlacesLived> placesLived, @Param(id = 28) String profileUrl, @Param(id = 29) List<Relations> relations, @Param(id = 30) List<RelationshipInterests> relationshipInterests, @Param(id = 31) List<RelationshipStatuses> relationshipStatuses, @Param(id = 32) List<Skills> skills, @Param(id = 33) SortKeys sortKeys, @Param(id = 34) List<Taglines> taglines, @Param(id = 35) List<Urls> urls) {
        this.mIndicatorSet = indicatorSet;
        this.mVersionCode = versionCode;
        this.mAbouts = abouts;
        this.mAddresses = addresses;
        this.mAgeRange = ageRange;
        this.mBirthdays = birthdays;
        this.mBraggingRights = braggingRights;
        this.mCoverPhotos = coverPhotos;
        this.mCustomFields = customFields;
        this.mEmails = emails;
        this.mEtag = etag;
        this.mEvents = events;
        this.mGenders = genders;
        this.mId = id;
        this.mImages = images;
        this.mInstantMessaging = instantMessaging;
        this.mLanguage = language;
        this.mLegacyFields = legacyFields;
        this.mLinkedPeople = linkedPeople;
        this.mMemberships = memberships;
        this.mMetadata = metadata;
        this.mNames = names;
        this.mNicknames = nicknames;
        this.mOccupations = occupations;
        this.mOrganizations = organizations;
        this.mPhoneNumbers = phoneNumbers;
        this.mPlacesLived = placesLived;
        this.mProfileUrl = profileUrl;
        this.mRelations = relations;
        this.mRelationshipInterests = relationshipInterests;
        this.mRelationshipStatuses = relationshipStatuses;
        this.mSkills = skills;
        this.mSortKeys = sortKeys;
        this.mTaglines = taglines;
        this.mUrls = urls;
    }

    public DefaultPersonImpl(Set<Integer> indicatorSet, List<Abouts> abouts, List<Addresses> addresses, String ageRange, List<Birthdays> birthdays, List<BraggingRights> braggingRights, List<CoverPhotos> coverPhotos, List<CustomFields> customFields, List<Emails> emails, String etag, List<Events> events, List<Genders> genders, String id, List<Images> images, List<InstantMessaging> instantMessaging, String language, LegacyFields legacyFields, List<DefaultPersonImpl> linkedPeople, List<Memberships> memberships, Metadata metadata, List<Names> names, List<Nicknames> nicknames, List<Occupations> occupations, List<Organizations> organizations, List<PhoneNumbers> phoneNumbers, List<PlacesLived> placesLived, String profileUrl, List<Relations> relations, List<RelationshipInterests> relationshipInterests, List<RelationshipStatuses> relationshipStatuses, List<Skills> skills, SortKeys sortKeys, List<Taglines> taglines, List<Urls> urls) {
        this.mIndicatorSet = indicatorSet;
        this.mVersionCode = VERSION_CODE;
        this.mAbouts = abouts;
        this.mAddresses = addresses;
        this.mAgeRange = ageRange;
        this.mBirthdays = birthdays;
        this.mBraggingRights = braggingRights;
        this.mCoverPhotos = coverPhotos;
        this.mCustomFields = customFields;
        this.mEmails = emails;
        this.mEtag = etag;
        this.mEvents = events;
        this.mGenders = genders;
        this.mId = id;
        this.mImages = images;
        this.mInstantMessaging = instantMessaging;
        this.mLanguage = language;
        this.mLegacyFields = legacyFields;
        this.mLinkedPeople = linkedPeople;
        this.mMemberships = memberships;
        this.mMetadata = metadata;
        this.mNames = names;
        this.mNicknames = nicknames;
        this.mOccupations = occupations;
        this.mOrganizations = organizations;
        this.mPhoneNumbers = phoneNumbers;
        this.mPlacesLived = placesLived;
        this.mProfileUrl = profileUrl;
        this.mRelations = relations;
        this.mRelationshipInterests = relationshipInterests;
        this.mRelationshipStatuses = relationshipStatuses;
        this.mSkills = skills;
        this.mSortKeys = sortKeys;
        this.mTaglines = taglines;
        this.mUrls = urls;
    }

    public List<Abouts> getAbouts() {
        return this.mAbouts;
    }

    public boolean hasAbouts() {
        return this.mIndicatorSet.contains(Integer.valueOf(2));
    }

    public List<Addresses> getAddresses() {
        return this.mAddresses;
    }

    public boolean hasAddresses() {
        return this.mIndicatorSet.contains(Integer.valueOf(3));
    }

    public String getAgeRange() {
        return this.mAgeRange;
    }

    public boolean hasAgeRange() {
        return this.mIndicatorSet.contains(Integer.valueOf(4));
    }

    public List<Birthdays> getBirthdays() {
        return this.mBirthdays;
    }

    public boolean hasBirthdays() {
        return this.mIndicatorSet.contains(Integer.valueOf(5));
    }

    public List<BraggingRights> getBraggingRights() {
        return this.mBraggingRights;
    }

    public boolean hasBraggingRights() {
        return this.mIndicatorSet.contains(Integer.valueOf(6));
    }

    public List<CoverPhotos> getCoverPhotos() {
        return this.mCoverPhotos;
    }

    public boolean hasCoverPhotos() {
        return this.mIndicatorSet.contains(Integer.valueOf(7));
    }

    public List<CustomFields> getCustomFields() {
        return this.mCustomFields;
    }

    public boolean hasCustomFields() {
        return this.mIndicatorSet.contains(Integer.valueOf(8));
    }

    public List<Emails> getEmails() {
        return this.mEmails;
    }

    public boolean hasEmails() {
        return this.mIndicatorSet.contains(Integer.valueOf(9));
    }

    public String getEtag() {
        return this.mEtag;
    }

    public boolean hasEtag() {
        return this.mIndicatorSet.contains(Integer.valueOf(10));
    }

    public List<Events> getEvents() {
        return this.mEvents;
    }

    public boolean hasEvents() {
        return this.mIndicatorSet.contains(Integer.valueOf(11));
    }

    public List<Genders> getGenders() {
        return this.mGenders;
    }

    public boolean hasGenders() {
        return this.mIndicatorSet.contains(Integer.valueOf(12));
    }

    public String getId() {
        return this.mId;
    }

    public boolean hasId() {
        return this.mIndicatorSet.contains(Integer.valueOf(13));
    }

    public List<Images> getImages() {
        return this.mImages;
    }

    public boolean hasImages() {
        return this.mIndicatorSet.contains(Integer.valueOf(14));
    }

    public List<InstantMessaging> getInstantMessaging() {
        return this.mInstantMessaging;
    }

    public boolean hasInstantMessaging() {
        return this.mIndicatorSet.contains(Integer.valueOf(15));
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public boolean hasLanguage() {
        return this.mIndicatorSet.contains(Integer.valueOf(17));
    }

    public LegacyFields getLegacyFields() {
        return this.mLegacyFields;
    }

    public boolean hasLegacyFields() {
        return this.mIndicatorSet.contains(Integer.valueOf(18));
    }

    public List<DefaultPersonImpl> getLinkedPeople() {
        return this.mLinkedPeople;
    }

    public boolean hasLinkedPeople() {
        return this.mIndicatorSet.contains(Integer.valueOf(19));
    }

    public List<Memberships> getMemberships() {
        return this.mMemberships;
    }

    public boolean hasMemberships() {
        return this.mIndicatorSet.contains(Integer.valueOf(20));
    }

    public Metadata getMetadata() {
        return this.mMetadata;
    }

    public boolean hasMetadata() {
        return this.mIndicatorSet.contains(Integer.valueOf(21));
    }

    public List<Names> getNames() {
        return this.mNames;
    }

    public boolean hasNames() {
        return this.mIndicatorSet.contains(Integer.valueOf(22));
    }

    public List<Nicknames> getNicknames() {
        return this.mNicknames;
    }

    public boolean hasNicknames() {
        return this.mIndicatorSet.contains(Integer.valueOf(23));
    }

    public List<Occupations> getOccupations() {
        return this.mOccupations;
    }

    public boolean hasOccupations() {
        return this.mIndicatorSet.contains(Integer.valueOf(24));
    }

    public List<Organizations> getOrganizations() {
        return this.mOrganizations;
    }

    public boolean hasOrganizations() {
        return this.mIndicatorSet.contains(Integer.valueOf(25));
    }

    public List<PhoneNumbers> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    public boolean hasPhoneNumbers() {
        return this.mIndicatorSet.contains(Integer.valueOf(26));
    }

    public List<PlacesLived> getPlacesLived() {
        return this.mPlacesLived;
    }

    public boolean hasPlacesLived() {
        return this.mIndicatorSet.contains(Integer.valueOf(27));
    }

    public String getProfileUrl() {
        return this.mProfileUrl;
    }

    public boolean hasProfileUrl() {
        return this.mIndicatorSet.contains(Integer.valueOf(28));
    }

    public List<Relations> getRelations() {
        return this.mRelations;
    }

    public boolean hasRelations() {
        return this.mIndicatorSet.contains(Integer.valueOf(29));
    }

    public List<RelationshipInterests> getRelationshipInterests() {
        return this.mRelationshipInterests;
    }

    public boolean hasRelationshipInterests() {
        return this.mIndicatorSet.contains(Integer.valueOf(30));
    }

    public List<RelationshipStatuses> getRelationshipStatuses() {
        return this.mRelationshipStatuses;
    }

    public boolean hasRelationshipStatuses() {
        return this.mIndicatorSet.contains(Integer.valueOf(31));
    }

    public List<Skills> getSkills() {
        return this.mSkills;
    }

    public boolean hasSkills() {
        return this.mIndicatorSet.contains(Integer.valueOf(32));
    }

    public SortKeys getSortKeys() {
        return this.mSortKeys;
    }

    public boolean hasSortKeys() {
        return this.mIndicatorSet.contains(Integer.valueOf(33));
    }

    public List<Taglines> getTaglines() {
        return this.mTaglines;
    }

    public boolean hasTaglines() {
        return this.mIndicatorSet.contains(Integer.valueOf(34));
    }

    public List<Urls> getUrls() {
        return this.mUrls;
    }

    public boolean hasUrls() {
        return this.mIndicatorSet.contains(Integer.valueOf(35));
    }

    public DefaultPersonImpl setAbouts(List<Abouts> newValue) {
        this.mAbouts = newValue;
        this.mIndicatorSet.add(Integer.valueOf(2));
        return this;
    }

    public DefaultPersonImpl addAbouts(Abouts newValue) {
        if (this.mAbouts == null) {
            this.mAbouts = new ArrayList();
        }
        this.mAbouts.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(2));
        return this;
    }

    public DefaultPersonImpl setAddresses(List<Addresses> newValue) {
        this.mAddresses = newValue;
        this.mIndicatorSet.add(Integer.valueOf(3));
        return this;
    }

    public DefaultPersonImpl addAddresses(Addresses newValue) {
        if (this.mAddresses == null) {
            this.mAddresses = new ArrayList();
        }
        this.mAddresses.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(3));
        return this;
    }

    public DefaultPersonImpl setAgeRange(String newValue) {
        this.mAgeRange = newValue;
        this.mIndicatorSet.add(Integer.valueOf(4));
        return this;
    }

    public DefaultPersonImpl setBirthdays(List<Birthdays> newValue) {
        this.mBirthdays = newValue;
        this.mIndicatorSet.add(Integer.valueOf(5));
        return this;
    }

    public DefaultPersonImpl addBirthdays(Birthdays newValue) {
        if (this.mBirthdays == null) {
            this.mBirthdays = new ArrayList();
        }
        this.mBirthdays.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(5));
        return this;
    }

    public DefaultPersonImpl setBraggingRights(List<BraggingRights> newValue) {
        this.mBraggingRights = newValue;
        this.mIndicatorSet.add(Integer.valueOf(6));
        return this;
    }

    public DefaultPersonImpl addBraggingRights(BraggingRights newValue) {
        if (this.mBraggingRights == null) {
            this.mBraggingRights = new ArrayList();
        }
        this.mBraggingRights.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(6));
        return this;
    }

    public DefaultPersonImpl setCoverPhotos(List<CoverPhotos> newValue) {
        this.mCoverPhotos = newValue;
        this.mIndicatorSet.add(Integer.valueOf(7));
        return this;
    }

    public DefaultPersonImpl addCoverPhotos(CoverPhotos newValue) {
        if (this.mCoverPhotos == null) {
            this.mCoverPhotos = new ArrayList();
        }
        this.mCoverPhotos.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(7));
        return this;
    }

    public DefaultPersonImpl setCustomFields(List<CustomFields> newValue) {
        this.mCustomFields = newValue;
        this.mIndicatorSet.add(Integer.valueOf(8));
        return this;
    }

    public DefaultPersonImpl addCustomFields(CustomFields newValue) {
        if (this.mCustomFields == null) {
            this.mCustomFields = new ArrayList();
        }
        this.mCustomFields.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(8));
        return this;
    }

    public DefaultPersonImpl setEmails(List<Emails> newValue) {
        this.mEmails = newValue;
        this.mIndicatorSet.add(Integer.valueOf(9));
        return this;
    }

    public DefaultPersonImpl addEmails(Emails newValue) {
        if (this.mEmails == null) {
            this.mEmails = new ArrayList();
        }
        this.mEmails.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(9));
        return this;
    }

    public DefaultPersonImpl setEtag(String newValue) {
        this.mEtag = newValue;
        this.mIndicatorSet.add(Integer.valueOf(10));
        return this;
    }

    public DefaultPersonImpl setEvents(List<Events> newValue) {
        this.mEvents = newValue;
        this.mIndicatorSet.add(Integer.valueOf(11));
        return this;
    }

    public DefaultPersonImpl addEvents(Events newValue) {
        if (this.mEvents == null) {
            this.mEvents = new ArrayList();
        }
        this.mEvents.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(11));
        return this;
    }

    public DefaultPersonImpl setGenders(List<Genders> newValue) {
        this.mGenders = newValue;
        this.mIndicatorSet.add(Integer.valueOf(12));
        return this;
    }

    public DefaultPersonImpl addGenders(Genders newValue) {
        if (this.mGenders == null) {
            this.mGenders = new ArrayList();
        }
        this.mGenders.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(12));
        return this;
    }

    public DefaultPersonImpl setId(String newValue) {
        this.mId = newValue;
        this.mIndicatorSet.add(Integer.valueOf(13));
        return this;
    }

    public DefaultPersonImpl setImages(List<Images> newValue) {
        this.mImages = newValue;
        this.mIndicatorSet.add(Integer.valueOf(14));
        return this;
    }

    public DefaultPersonImpl addImages(Images newValue) {
        if (this.mImages == null) {
            this.mImages = new ArrayList();
        }
        this.mImages.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(14));
        return this;
    }

    public DefaultPersonImpl setInstantMessaging(List<InstantMessaging> newValue) {
        this.mInstantMessaging = newValue;
        this.mIndicatorSet.add(Integer.valueOf(15));
        return this;
    }

    public DefaultPersonImpl addInstantMessaging(InstantMessaging newValue) {
        if (this.mInstantMessaging == null) {
            this.mInstantMessaging = new ArrayList();
        }
        this.mInstantMessaging.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(15));
        return this;
    }

    public DefaultPersonImpl setLanguage(String newValue) {
        this.mLanguage = newValue;
        this.mIndicatorSet.add(Integer.valueOf(17));
        return this;
    }

    public DefaultPersonImpl setLegacyFields(LegacyFields newValue) {
        this.mLegacyFields = newValue;
        this.mIndicatorSet.add(Integer.valueOf(18));
        return this;
    }

    public DefaultPersonImpl setLinkedPeople(List<DefaultPersonImpl> newValue) {
        this.mLinkedPeople = newValue;
        this.mIndicatorSet.add(Integer.valueOf(19));
        return this;
    }

    public DefaultPersonImpl addLinkedPeople(DefaultPersonImpl newValue) {
        if (this.mLinkedPeople == null) {
            this.mLinkedPeople = new ArrayList();
        }
        this.mLinkedPeople.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(19));
        return this;
    }

    public DefaultPersonImpl setMemberships(List<Memberships> newValue) {
        this.mMemberships = newValue;
        this.mIndicatorSet.add(Integer.valueOf(20));
        return this;
    }

    public DefaultPersonImpl addMemberships(Memberships newValue) {
        if (this.mMemberships == null) {
            this.mMemberships = new ArrayList();
        }
        this.mMemberships.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(20));
        return this;
    }

    public DefaultPersonImpl setMetadata(Metadata newValue) {
        this.mMetadata = newValue;
        this.mIndicatorSet.add(Integer.valueOf(21));
        return this;
    }

    public DefaultPersonImpl setNames(List<Names> newValue) {
        this.mNames = newValue;
        this.mIndicatorSet.add(Integer.valueOf(22));
        return this;
    }

    public DefaultPersonImpl addNames(Names newValue) {
        if (this.mNames == null) {
            this.mNames = new ArrayList();
        }
        this.mNames.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(22));
        return this;
    }

    public DefaultPersonImpl setNicknames(List<Nicknames> newValue) {
        this.mNicknames = newValue;
        this.mIndicatorSet.add(Integer.valueOf(23));
        return this;
    }

    public DefaultPersonImpl addNicknames(Nicknames newValue) {
        if (this.mNicknames == null) {
            this.mNicknames = new ArrayList();
        }
        this.mNicknames.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(23));
        return this;
    }

    public DefaultPersonImpl setOccupations(List<Occupations> newValue) {
        this.mOccupations = newValue;
        this.mIndicatorSet.add(Integer.valueOf(24));
        return this;
    }

    public DefaultPersonImpl addOccupations(Occupations newValue) {
        if (this.mOccupations == null) {
            this.mOccupations = new ArrayList();
        }
        this.mOccupations.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(24));
        return this;
    }

    public DefaultPersonImpl setOrganizations(List<Organizations> newValue) {
        this.mOrganizations = newValue;
        this.mIndicatorSet.add(Integer.valueOf(25));
        return this;
    }

    public DefaultPersonImpl addOrganizations(Organizations newValue) {
        if (this.mOrganizations == null) {
            this.mOrganizations = new ArrayList();
        }
        this.mOrganizations.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(25));
        return this;
    }

    public DefaultPersonImpl setPhoneNumbers(List<PhoneNumbers> newValue) {
        this.mPhoneNumbers = newValue;
        this.mIndicatorSet.add(Integer.valueOf(26));
        return this;
    }

    public DefaultPersonImpl addPhoneNumbers(PhoneNumbers newValue) {
        if (this.mPhoneNumbers == null) {
            this.mPhoneNumbers = new ArrayList();
        }
        this.mPhoneNumbers.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(26));
        return this;
    }

    public DefaultPersonImpl setPlacesLived(List<PlacesLived> newValue) {
        this.mPlacesLived = newValue;
        this.mIndicatorSet.add(Integer.valueOf(27));
        return this;
    }

    public DefaultPersonImpl addPlacesLived(PlacesLived newValue) {
        if (this.mPlacesLived == null) {
            this.mPlacesLived = new ArrayList();
        }
        this.mPlacesLived.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(27));
        return this;
    }

    public DefaultPersonImpl setProfileUrl(String newValue) {
        this.mProfileUrl = newValue;
        this.mIndicatorSet.add(Integer.valueOf(28));
        return this;
    }

    public DefaultPersonImpl setRelations(List<Relations> newValue) {
        this.mRelations = newValue;
        this.mIndicatorSet.add(Integer.valueOf(29));
        return this;
    }

    public DefaultPersonImpl addRelations(Relations newValue) {
        if (this.mRelations == null) {
            this.mRelations = new ArrayList();
        }
        this.mRelations.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(29));
        return this;
    }

    public DefaultPersonImpl setRelationshipInterests(List<RelationshipInterests> newValue) {
        this.mRelationshipInterests = newValue;
        this.mIndicatorSet.add(Integer.valueOf(30));
        return this;
    }

    public DefaultPersonImpl addRelationshipInterests(RelationshipInterests newValue) {
        if (this.mRelationshipInterests == null) {
            this.mRelationshipInterests = new ArrayList();
        }
        this.mRelationshipInterests.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(30));
        return this;
    }

    public DefaultPersonImpl setRelationshipStatuses(List<RelationshipStatuses> newValue) {
        this.mRelationshipStatuses = newValue;
        this.mIndicatorSet.add(Integer.valueOf(31));
        return this;
    }

    public DefaultPersonImpl addRelationshipStatuses(RelationshipStatuses newValue) {
        if (this.mRelationshipStatuses == null) {
            this.mRelationshipStatuses = new ArrayList();
        }
        this.mRelationshipStatuses.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(31));
        return this;
    }

    public DefaultPersonImpl setSkills(List<Skills> newValue) {
        this.mSkills = newValue;
        this.mIndicatorSet.add(Integer.valueOf(32));
        return this;
    }

    public DefaultPersonImpl addSkills(Skills newValue) {
        if (this.mSkills == null) {
            this.mSkills = new ArrayList();
        }
        this.mSkills.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(32));
        return this;
    }

    public DefaultPersonImpl setSortKeys(SortKeys newValue) {
        this.mSortKeys = newValue;
        this.mIndicatorSet.add(Integer.valueOf(33));
        return this;
    }

    public DefaultPersonImpl setTaglines(List<Taglines> newValue) {
        this.mTaglines = newValue;
        this.mIndicatorSet.add(Integer.valueOf(34));
        return this;
    }

    public DefaultPersonImpl addTaglines(Taglines newValue) {
        if (this.mTaglines == null) {
            this.mTaglines = new ArrayList();
        }
        this.mTaglines.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(34));
        return this;
    }

    public DefaultPersonImpl setUrls(List<Urls> newValue) {
        this.mUrls = newValue;
        this.mIndicatorSet.add(Integer.valueOf(35));
        return this;
    }

    public DefaultPersonImpl addUrls(Urls newValue) {
        if (this.mUrls == null) {
            this.mUrls = new ArrayList();
        }
        this.mUrls.add(newValue);
        this.mIndicatorSet.add(Integer.valueOf(35));
        return this;
    }

    public int describeContents() {
        DefaultPersonImplCreator defaultPersonImplCreator = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        DefaultPersonImplCreator defaultPersonImplCreator = CREATOR;
        DefaultPersonImplCreator.writeToParcel(this, out, flags);
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
                return this.mAbouts;
            case Type.CUSTOM /*3*/:
                return this.mAddresses;
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                return this.mAgeRange;
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                return this.mBirthdays;
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                return this.mBraggingRights;
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                return this.mCoverPhotos;
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                return this.mCustomFields;
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                return this.mEmails;
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                return this.mEtag;
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                return this.mEvents;
            case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                return this.mGenders;
            case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                return this.mId;
            case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                return this.mImages;
            case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                return this.mInstantMessaging;
            case LogSource.LE /*17*/:
                return this.mLanguage;
            case LogSource.GOOGLE_ANALYTICS /*18*/:
                return this.mLegacyFields;
            case LogSource.LB_D /*19*/:
                return this.mLinkedPeople;
            case LogSource.ANDROID_GSA /*20*/:
                return this.mMemberships;
            case LogSource.LB_T /*21*/:
                return this.mMetadata;
            case LogSource.PERSONAL_LOGGER /*22*/:
                return this.mNames;
            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                return this.mNicknames;
            case LogSource.LB_C /*24*/:
                return this.mOccupations;
            case LogSource.ANDROID_AUTH /*25*/:
                return this.mOrganizations;
            case LogSource.ANDROID_CAMERA /*26*/:
                return this.mPhoneNumbers;
            case LogSource.CW /*27*/:
                return this.mPlacesLived;
            case LogSource.GEL /*28*/:
                return this.mProfileUrl;
            case LogSource.DNA_PROBER /*29*/:
                return this.mRelations;
            case LogSource.UDR /*30*/:
                return this.mRelationshipInterests;
            case LogSource.GMS_CORE_WALLET /*31*/:
                return this.mRelationshipStatuses;
            case LogSource.SOCIAL /*32*/:
                return this.mSkills;
            case LogSource.INSTORE_WALLET /*33*/:
                return this.mSortKeys;
            case LogSource.NOVA /*34*/:
                return this.mTaglines;
            case LogSource.LB_CA /*35*/:
                return this.mUrls;
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

    public static DefaultPersonImpl fromByteArray(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        DefaultPersonImpl object = CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return object;
    }

    protected void setStringInternal(Field<?, ?> field, String outputField, String value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                this.mAgeRange = value;
                break;
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                this.mEtag = value;
                break;
            case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                this.mId = value;
                break;
            case LogSource.LE /*17*/:
                this.mLanguage = value;
                break;
            case LogSource.GEL /*28*/:
                this.mProfileUrl = value;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not known to be a String.");
        }
        this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
    }

    public <T extends FastJsonResponse> void addConcreteTypeInternal(Field<?, ?> field, String outputField, T value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case LogSource.GOOGLE_ANALYTICS /*18*/:
                this.mLegacyFields = (LegacyFields) value;
                break;
            case LogSource.LB_T /*21*/:
                this.mMetadata = (Metadata) value;
                break;
            case LogSource.INSTORE_WALLET /*33*/:
                this.mSortKeys = (SortKeys) value;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
        }
        this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
    }

    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(Field<?, ?> field, String outputField, ArrayList<T> value) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        switch (safeParcelableFieldId) {
            case Type.INDEFINITELY /*2*/:
                this.mAbouts = value;
                break;
            case Type.CUSTOM /*3*/:
                this.mAddresses = value;
                break;
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                this.mBirthdays = value;
                break;
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                this.mBraggingRights = value;
                break;
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
                this.mCoverPhotos = value;
                break;
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                this.mCustomFields = value;
                break;
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                this.mEmails = value;
                break;
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                this.mEvents = value;
                break;
            case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                this.mGenders = value;
                break;
            case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                this.mImages = value;
                break;
            case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                this.mInstantMessaging = value;
                break;
            case LogSource.LB_D /*19*/:
                this.mLinkedPeople = value;
                break;
            case LogSource.ANDROID_GSA /*20*/:
                this.mMemberships = value;
                break;
            case LogSource.PERSONAL_LOGGER /*22*/:
                this.mNames = value;
                break;
            case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                this.mNicknames = value;
                break;
            case LogSource.LB_C /*24*/:
                this.mOccupations = value;
                break;
            case LogSource.ANDROID_AUTH /*25*/:
                this.mOrganizations = value;
                break;
            case LogSource.ANDROID_CAMERA /*26*/:
                this.mPhoneNumbers = value;
                break;
            case LogSource.CW /*27*/:
                this.mPlacesLived = value;
                break;
            case LogSource.DNA_PROBER /*29*/:
                this.mRelations = value;
                break;
            case LogSource.UDR /*30*/:
                this.mRelationshipInterests = value;
                break;
            case LogSource.GMS_CORE_WALLET /*31*/:
                this.mRelationshipStatuses = value;
                break;
            case LogSource.SOCIAL /*32*/:
                this.mSkills = value;
                break;
            case LogSource.NOVA /*34*/:
                this.mTaglines = value;
                break;
            case LogSource.LB_CA /*35*/:
                this.mUrls = value;
                break;
            default:
                throw new IllegalArgumentException("Field with id=" + safeParcelableFieldId + " is not a known array of" + " custom type.  Found " + value.getClass().getCanonicalName() + ".");
        }
        this.mIndicatorSet.add(Integer.valueOf(safeParcelableFieldId));
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
        if (!(obj instanceof DefaultPersonImpl)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        DefaultPersonImpl entity = (DefaultPersonImpl) obj;
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
