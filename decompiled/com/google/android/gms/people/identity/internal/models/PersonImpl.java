package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.internal.models.PersonBase.AboutsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.AddressesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.BirthdaysBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.BraggingRightsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.CoverPhotosBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.CustomFieldsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.EmailsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.EventsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.GendersBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.ImagesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.InstantMessagingBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.LegacyFieldsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.MembershipsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.MetadataBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.MetadataHolderBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.NamesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.NicknamesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.NotesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.OccupationsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.OrganizationsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.PersonMetadataBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.PhoneNumbersBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.PlacesLivedBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.ProfileOwnerStatsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.RelationsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.RelationshipInterestsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.RelationshipStatusesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.SkillsBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.SortKeysBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.TaglinesBase;
import com.google.android.gms.people.identity.internal.models.PersonBase.UrlsBase;
import com.google.android.gms.people.identity.models.Person;
import com.google.android.gms.people.identity.models.Person.Abouts;
import com.google.android.gms.people.identity.models.Person.Addresses;
import com.google.android.gms.people.identity.models.Person.Birthdays;
import com.google.android.gms.people.identity.models.Person.BraggingRights;
import com.google.android.gms.people.identity.models.Person.CoverPhotos;
import com.google.android.gms.people.identity.models.Person.CustomFields;
import com.google.android.gms.people.identity.models.Person.Emails;
import com.google.android.gms.people.identity.models.Person.Events;
import com.google.android.gms.people.identity.models.Person.Genders;
import com.google.android.gms.people.identity.models.Person.Images;
import com.google.android.gms.people.identity.models.Person.InstantMessaging;
import com.google.android.gms.people.identity.models.Person.LegacyFields;
import com.google.android.gms.people.identity.models.Person.Memberships;
import com.google.android.gms.people.identity.models.Person.Metadata;
import com.google.android.gms.people.identity.models.Person.MetadataHolder;
import com.google.android.gms.people.identity.models.Person.Names;
import com.google.android.gms.people.identity.models.Person.Nicknames;
import com.google.android.gms.people.identity.models.Person.Notes;
import com.google.android.gms.people.identity.models.Person.Occupations;
import com.google.android.gms.people.identity.models.Person.Organizations;
import com.google.android.gms.people.identity.models.Person.PersonMetadata;
import com.google.android.gms.people.identity.models.Person.PhoneNumbers;
import com.google.android.gms.people.identity.models.Person.PlacesLived;
import com.google.android.gms.people.identity.models.Person.ProfileOwnerStats;
import com.google.android.gms.people.identity.models.Person.Relations;
import com.google.android.gms.people.identity.models.Person.RelationshipInterests;
import com.google.android.gms.people.identity.models.Person.RelationshipStatuses;
import com.google.android.gms.people.identity.models.Person.Skills;
import com.google.android.gms.people.identity.models.Person.SortKeys;
import com.google.android.gms.people.identity.models.Person.Taglines;
import com.google.android.gms.people.identity.models.Person.Urls;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Class(creator = "PersonImplCreator")
@VisibleForTesting
public class PersonImpl implements SafeParcelable, Person {
    public static final PersonImplCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    List<AboutsImpl> mAbouts;
    @Field(id = 3)
    List<AddressesImpl> mAddresses;
    @Field(id = 4)
    String mAgeRange;
    @Field(id = 5)
    List<BirthdaysImpl> mBirthdays;
    @Field(id = 6)
    List<BraggingRightsImpl> mBraggingRights;
    @Field(id = 7)
    List<CoverPhotosImpl> mCoverPhotos;
    @Field(id = 8)
    List<CustomFieldsImpl> mCustomFields;
    @Field(id = 9)
    List<EmailsImpl> mEmails;
    @Field(id = 10)
    String mEtag;
    @Field(id = 11)
    List<EventsImpl> mEvents;
    @Field(id = 12)
    List<GendersImpl> mGenders;
    @Field(id = 13)
    String mId;
    @Field(id = 14)
    List<ImagesImpl> mImages;
    @Indicator
    final Set<Integer> mIndicatorSet;
    @Field(id = 15)
    List<InstantMessagingImpl> mInstantMessaging;
    @Field(id = 16)
    String mLanguage;
    @Field(id = 17)
    LegacyFieldsImpl mLegacyFields;
    @Field(id = 18)
    List<PersonImpl> mLinkedPeople;
    @Field(id = 19)
    List<MembershipsImpl> mMemberships;
    @Field(id = 20)
    PersonMetadataImpl mMetadata;
    @Field(id = 21)
    List<NamesImpl> mNames;
    @Field(id = 22)
    List<NicknamesImpl> mNicknames;
    @Field(id = 35)
    List<NotesImpl> mNotes;
    @Field(id = 23)
    List<OccupationsImpl> mOccupations;
    @Field(id = 24)
    List<OrganizationsImpl> mOrganizations;
    @Field(id = 25)
    List<PhoneNumbersImpl> mPhoneNumbers;
    @Field(id = 26)
    List<PlacesLivedImpl> mPlacesLived;
    @Field(id = 27)
    String mProfileUrl;
    @Field(id = 28)
    List<RelationsImpl> mRelations;
    @Field(id = 29)
    List<RelationshipInterestsImpl> mRelationshipInterests;
    @Field(id = 30)
    List<RelationshipStatusesImpl> mRelationshipStatuses;
    @Field(id = 31)
    List<SkillsImpl> mSkills;
    @Field(id = 32)
    SortKeysImpl mSortKeys;
    @Field(id = 33)
    List<TaglinesImpl> mTaglines;
    @Field(id = 34)
    List<UrlsImpl> mUrls;
    @VersionField(id = 1)
    final int mVersionCode;

    @Class(creator = "AboutsImplCreator")
    @VisibleForTesting
    public static class AboutsImpl implements SafeParcelable, Abouts {
        public static final AboutsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mType;
        @Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new AboutsImplCreator();
        }

        public AboutsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        AboutsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String type, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public AboutsImpl(AboutsBase other) {
            this();
            importData(other);
        }

        public AboutsImpl importData(AboutsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public AboutsImpl mergeData(AboutsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public AboutsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public AboutsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public AboutsImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public AboutsImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public AboutsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public AboutsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            AboutsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "AddressesImplCreator")
    @VisibleForTesting
    public static class AddressesImpl implements SafeParcelable, Addresses {
        public static final AddressesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mCity;
        @Field(id = 4)
        String mCountry;
        @Field(id = 5)
        String mCountryCode;
        @Field(id = 6)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 7)
        String mPoBox;
        @Field(id = 8)
        String mPostalCode;
        @Field(id = 9)
        String mRegion;
        @Field(id = 10)
        String mStreetAddress;
        @Field(id = 11)
        String mType;
        @Field(id = 12)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new AddressesImplCreator();
        }

        public AddressesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        AddressesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String city, @Param(id = 4) String country, @Param(id = 5) String countryCode, @Param(id = 6) String formattedType, @Param(id = 7) String poBox, @Param(id = 8) String postalCode, @Param(id = 9) String region, @Param(id = 10) String streetAddress, @Param(id = 11) String type, @Param(id = 12) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mCity = city;
            this.mCountry = country;
            this.mCountryCode = countryCode;
            this.mFormattedType = formattedType;
            this.mPoBox = poBox;
            this.mPostalCode = postalCode;
            this.mRegion = region;
            this.mStreetAddress = streetAddress;
            this.mType = type;
            this.mValue = value;
        }

        public AddressesImpl(AddressesBase other) {
            this();
            importData(other);
        }

        public AddressesImpl importData(AddressesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearCity();
            if (other.hasCity()) {
                setCity(other.getCity());
            }
            clearCountry();
            if (other.hasCountry()) {
                setCountry(other.getCountry());
            }
            clearCountryCode();
            if (other.hasCountryCode()) {
                setCountryCode(other.getCountryCode());
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearPoBox();
            if (other.hasPoBox()) {
                setPoBox(other.getPoBox());
            }
            clearPostalCode();
            if (other.hasPostalCode()) {
                setPostalCode(other.getPostalCode());
            }
            clearRegion();
            if (other.hasRegion()) {
                setRegion(other.getRegion());
            }
            clearStreetAddress();
            if (other.hasStreetAddress()) {
                setStreetAddress(other.getStreetAddress());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public AddressesImpl mergeData(AddressesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasCity()) {
                setCity(other.getCity());
            }
            if (other.hasCountry()) {
                setCountry(other.getCountry());
            }
            if (other.hasCountryCode()) {
                setCountryCode(other.getCountryCode());
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasPoBox()) {
                setPoBox(other.getPoBox());
            }
            if (other.hasPostalCode()) {
                setPostalCode(other.getPostalCode());
            }
            if (other.hasRegion()) {
                setRegion(other.getRegion());
            }
            if (other.hasStreetAddress()) {
                setStreetAddress(other.getStreetAddress());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public AddressesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public AddressesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasCity() {
            return this.mCity != null;
        }

        public String getCity() {
            return this.mCity;
        }

        public AddressesImpl setCity(String value) {
            this.mCity = value;
            return this;
        }

        public AddressesImpl clearCity() {
            return setCity(null);
        }

        public boolean hasCountry() {
            return this.mCountry != null;
        }

        public String getCountry() {
            return this.mCountry;
        }

        public AddressesImpl setCountry(String value) {
            this.mCountry = value;
            return this;
        }

        public AddressesImpl clearCountry() {
            return setCountry(null);
        }

        public boolean hasCountryCode() {
            return this.mCountryCode != null;
        }

        public String getCountryCode() {
            return this.mCountryCode;
        }

        public AddressesImpl setCountryCode(String value) {
            this.mCountryCode = value;
            return this;
        }

        public AddressesImpl clearCountryCode() {
            return setCountryCode(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public AddressesImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public AddressesImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasPoBox() {
            return this.mPoBox != null;
        }

        public String getPoBox() {
            return this.mPoBox;
        }

        public AddressesImpl setPoBox(String value) {
            this.mPoBox = value;
            return this;
        }

        public AddressesImpl clearPoBox() {
            return setPoBox(null);
        }

        public boolean hasPostalCode() {
            return this.mPostalCode != null;
        }

        public String getPostalCode() {
            return this.mPostalCode;
        }

        public AddressesImpl setPostalCode(String value) {
            this.mPostalCode = value;
            return this;
        }

        public AddressesImpl clearPostalCode() {
            return setPostalCode(null);
        }

        public boolean hasRegion() {
            return this.mRegion != null;
        }

        public String getRegion() {
            return this.mRegion;
        }

        public AddressesImpl setRegion(String value) {
            this.mRegion = value;
            return this;
        }

        public AddressesImpl clearRegion() {
            return setRegion(null);
        }

        public boolean hasStreetAddress() {
            return this.mStreetAddress != null;
        }

        public String getStreetAddress() {
            return this.mStreetAddress;
        }

        public AddressesImpl setStreetAddress(String value) {
            this.mStreetAddress = value;
            return this;
        }

        public AddressesImpl clearStreetAddress() {
            return setStreetAddress(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public AddressesImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public AddressesImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public AddressesImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public AddressesImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            AddressesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "BirthdaysImplCreator")
    @VisibleForTesting
    public static class BirthdaysImpl implements SafeParcelable, Birthdays {
        public static final BirthdaysImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mDate;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new BirthdaysImplCreator();
        }

        public BirthdaysImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        BirthdaysImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String date) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mDate = date;
        }

        public BirthdaysImpl(BirthdaysBase other) {
            this();
            importData(other);
        }

        public BirthdaysImpl importData(BirthdaysBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearDate();
            if (other.hasDate()) {
                setDate(other.getDate());
            }
            return this;
        }

        public BirthdaysImpl mergeData(BirthdaysBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasDate()) {
                setDate(other.getDate());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public BirthdaysImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public BirthdaysImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasDate() {
            return this.mDate != null;
        }

        public String getDate() {
            return this.mDate;
        }

        public BirthdaysImpl setDate(String value) {
            this.mDate = value;
            return this;
        }

        public BirthdaysImpl clearDate() {
            return setDate(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            BirthdaysImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "BraggingRightsImplCreator")
    @VisibleForTesting
    public static class BraggingRightsImpl implements SafeParcelable, BraggingRights {
        public static final BraggingRightsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new BraggingRightsImplCreator();
        }

        public BraggingRightsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        BraggingRightsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public BraggingRightsImpl(BraggingRightsBase other) {
            this();
            importData(other);
        }

        public BraggingRightsImpl importData(BraggingRightsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public BraggingRightsImpl mergeData(BraggingRightsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public BraggingRightsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public BraggingRightsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public BraggingRightsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public BraggingRightsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            BraggingRightsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "CoverPhotosImplCreator")
    @VisibleForTesting
    public static class CoverPhotosImpl implements SafeParcelable, CoverPhotos {
        public static final CoverPhotosImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 6)
        boolean mDefault;
        @Field(id = 2)
        int mHeight;
        @Field(id = 3)
        String mId;
        @Field(id = 4)
        ImageReferenceImpl mImageReference;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @VersionField(id = 1)
        final int mVersionCode;
        @Field(id = 5)
        int mWidth;

        static {
            CREATOR = new CoverPhotosImplCreator();
        }

        public CoverPhotosImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        CoverPhotosImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) int height, @Param(id = 3) String id, @Param(id = 4) ImageReferenceImpl imageReference, @Param(id = 5) int width, @Param(id = 6) boolean isDefault) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mHeight = height;
            this.mId = id;
            this.mImageReference = imageReference;
            this.mWidth = width;
            this.mDefault = isDefault;
        }

        public CoverPhotosImpl(CoverPhotosBase other) {
            this();
            importData(other);
        }

        public CoverPhotosImpl importData(CoverPhotosBase other) {
            clearHeight();
            if (other.hasHeight()) {
                setHeight(other.getHeight());
            }
            clearId();
            if (other.hasId()) {
                setId(other.getId());
            }
            clearImageReference();
            if (other.hasImageReference()) {
                setImageReference(new ImageReferenceImpl(other.getImageReference()));
            }
            clearWidth();
            if (other.hasWidth()) {
                setWidth(other.getWidth());
            }
            clearDefault();
            if (other.hasDefault()) {
                setDefault(other.isDefault());
            }
            return this;
        }

        public CoverPhotosImpl mergeData(CoverPhotosBase other) {
            if (other.hasHeight()) {
                setHeight(other.getHeight());
            }
            if (other.hasId()) {
                setId(other.getId());
            }
            if (other.hasImageReference()) {
                setImageReference(new ImageReferenceImpl(other.getImageReference()));
            }
            if (other.hasWidth()) {
                setWidth(other.getWidth());
            }
            if (other.hasDefault()) {
                setDefault(other.isDefault());
            }
            return this;
        }

        public boolean hasHeight() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public int getHeight() {
            return this.mHeight;
        }

        public CoverPhotosImpl setHeight(int value) {
            this.mIndicatorSet.add(Integer.valueOf(2));
            this.mHeight = value;
            return this;
        }

        public CoverPhotosImpl clearHeight() {
            this.mIndicatorSet.remove(Integer.valueOf(2));
            return this;
        }

        public boolean hasId() {
            return this.mId != null;
        }

        public String getId() {
            return this.mId;
        }

        public CoverPhotosImpl setId(String value) {
            this.mId = value;
            return this;
        }

        public CoverPhotosImpl clearId() {
            return setId(null);
        }

        public boolean hasImageReference() {
            return this.mImageReference != null;
        }

        public ImageReferenceImpl getImageReference() {
            return this.mImageReference;
        }

        public CoverPhotosImpl setImageReference(ImageReferenceImpl value) {
            this.mImageReference = value;
            return this;
        }

        public CoverPhotosImpl clearImageReference() {
            return setImageReference(null);
        }

        public boolean hasWidth() {
            return this.mIndicatorSet.contains(Integer.valueOf(5));
        }

        public int getWidth() {
            return this.mWidth;
        }

        public CoverPhotosImpl setWidth(int value) {
            this.mIndicatorSet.add(Integer.valueOf(5));
            this.mWidth = value;
            return this;
        }

        public CoverPhotosImpl clearWidth() {
            this.mIndicatorSet.remove(Integer.valueOf(5));
            return this;
        }

        public boolean hasDefault() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public boolean isDefault() {
            return this.mDefault;
        }

        public CoverPhotosImpl setDefault(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(6));
            this.mDefault = value;
            return this;
        }

        public CoverPhotosImpl clearDefault() {
            this.mIndicatorSet.remove(Integer.valueOf(6));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            CoverPhotosImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "CustomFieldsImplCreator")
    @VisibleForTesting
    public static class CustomFieldsImpl implements SafeParcelable, CustomFields {
        public static final CustomFieldsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        String mKey;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new CustomFieldsImplCreator();
        }

        public CustomFieldsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        CustomFieldsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String key, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mKey = key;
            this.mValue = value;
        }

        public CustomFieldsImpl(CustomFieldsBase other) {
            this();
            importData(other);
        }

        public CustomFieldsImpl importData(CustomFieldsBase other) {
            clearKey();
            if (other.hasKey()) {
                setKey(other.getKey());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public CustomFieldsImpl mergeData(CustomFieldsBase other) {
            if (other.hasKey()) {
                setKey(other.getKey());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasKey() {
            return this.mKey != null;
        }

        public String getKey() {
            return this.mKey;
        }

        public CustomFieldsImpl setKey(String value) {
            this.mKey = value;
            return this;
        }

        public CustomFieldsImpl clearKey() {
            return setKey(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public CustomFieldsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public CustomFieldsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            CustomFieldsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "EmailsImplCreator")
    @VisibleForTesting
    public static class EmailsImpl implements SafeParcelable, Emails {
        public static final EmailsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 6)
        int mTimesUsed;
        @Field(id = 4)
        String mType;
        @Field(id = 5)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new EmailsImplCreator();
        }

        public EmailsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        EmailsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedType, @Param(id = 4) String type, @Param(id = 5) String value, @Param(id = 6) int timesUsed) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedType = formattedType;
            this.mType = type;
            this.mValue = value;
            this.mTimesUsed = timesUsed;
        }

        public EmailsImpl(EmailsBase other) {
            this();
            importData(other);
        }

        public EmailsImpl importData(EmailsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            clearTimesUsed();
            if (other.hasTimesUsed()) {
                setTimesUsed(other.getTimesUsed());
            }
            return this;
        }

        public EmailsImpl mergeData(EmailsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            if (other.hasTimesUsed()) {
                setTimesUsed(other.getTimesUsed());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public EmailsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public EmailsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public EmailsImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public EmailsImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public EmailsImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public EmailsImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public EmailsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public EmailsImpl clearValue() {
            return setValue(null);
        }

        public boolean hasTimesUsed() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public int getTimesUsed() {
            return this.mTimesUsed;
        }

        public EmailsImpl setTimesUsed(int value) {
            this.mIndicatorSet.add(Integer.valueOf(6));
            this.mTimesUsed = value;
            return this;
        }

        public EmailsImpl clearTimesUsed() {
            this.mIndicatorSet.remove(Integer.valueOf(6));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            EmailsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "EventsImplCreator")
    @VisibleForTesting
    public static class EventsImpl implements SafeParcelable, Events {
        public static final EventsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 5)
        String mDate;
        @Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 4)
        String mType;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new EventsImplCreator();
        }

        public EventsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        EventsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedType, @Param(id = 4) String type, @Param(id = 5) String date) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedType = formattedType;
            this.mType = type;
            this.mDate = date;
        }

        public EventsImpl(EventsBase other) {
            this();
            importData(other);
        }

        public EventsImpl importData(EventsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearDate();
            if (other.hasDate()) {
                setDate(other.getDate());
            }
            return this;
        }

        public EventsImpl mergeData(EventsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasDate()) {
                setDate(other.getDate());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public EventsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public EventsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public EventsImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public EventsImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public EventsImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public EventsImpl clearType() {
            return setType(null);
        }

        public boolean hasDate() {
            return this.mDate != null;
        }

        public String getDate() {
            return this.mDate;
        }

        public EventsImpl setDate(String value) {
            this.mDate = value;
            return this;
        }

        public EventsImpl clearDate() {
            return setDate(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            EventsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "GendersImplCreator")
    @VisibleForTesting
    public static class GendersImpl implements SafeParcelable, Genders {
        public static final GendersImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mFormattedValue;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new GendersImplCreator();
        }

        public GendersImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        GendersImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedValue, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedValue = formattedValue;
            this.mValue = value;
        }

        public GendersImpl(GendersBase other) {
            this();
            importData(other);
        }

        public GendersImpl importData(GendersBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedValue();
            if (other.hasFormattedValue()) {
                setFormattedValue(other.getFormattedValue());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public GendersImpl mergeData(GendersBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedValue()) {
                setFormattedValue(other.getFormattedValue());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public GendersImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public GendersImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedValue() {
            return this.mFormattedValue != null;
        }

        public String getFormattedValue() {
            return this.mFormattedValue;
        }

        public GendersImpl setFormattedValue(String value) {
            this.mFormattedValue = value;
            return this;
        }

        public GendersImpl clearFormattedValue() {
            return setFormattedValue(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public GendersImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public GendersImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            GendersImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "ImagesImplCreator")
    @VisibleForTesting
    public static class ImagesImpl implements SafeParcelable, Images {
        public static final ImagesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 4)
        boolean mDefault;
        @Field(id = 3)
        ImageReferenceImpl mImageReference;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new ImagesImplCreator();
        }

        public ImagesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        ImagesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) ImageReferenceImpl imageReference, @Param(id = 4) boolean isDefault) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mImageReference = imageReference;
            this.mDefault = isDefault;
        }

        public ImagesImpl(ImagesBase other) {
            this();
            importData(other);
        }

        public ImagesImpl importData(ImagesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearImageReference();
            if (other.hasImageReference()) {
                setImageReference(new ImageReferenceImpl(other.getImageReference()));
            }
            clearDefault();
            if (other.hasDefault()) {
                setDefault(other.isDefault());
            }
            return this;
        }

        public ImagesImpl mergeData(ImagesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasImageReference()) {
                setImageReference(new ImageReferenceImpl(other.getImageReference()));
            }
            if (other.hasDefault()) {
                setDefault(other.isDefault());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public ImagesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public ImagesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasImageReference() {
            return this.mImageReference != null;
        }

        public ImageReferenceImpl getImageReference() {
            return this.mImageReference;
        }

        public ImagesImpl setImageReference(ImageReferenceImpl value) {
            this.mImageReference = value;
            return this;
        }

        public ImagesImpl clearImageReference() {
            return setImageReference(null);
        }

        public boolean hasDefault() {
            return this.mIndicatorSet.contains(Integer.valueOf(4));
        }

        public boolean isDefault() {
            return this.mDefault;
        }

        public ImagesImpl setDefault(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(4));
            this.mDefault = value;
            return this;
        }

        public ImagesImpl clearDefault() {
            this.mIndicatorSet.remove(Integer.valueOf(4));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            ImagesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "InstantMessagingImplCreator")
    @VisibleForTesting
    public static class InstantMessagingImpl implements SafeParcelable, InstantMessaging {
        public static final InstantMessagingImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mFormattedProtocol;
        @Field(id = 4)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 5)
        String mProtocol;
        @Field(id = 6)
        String mType;
        @Field(id = 7)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new InstantMessagingImplCreator();
        }

        public InstantMessagingImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        InstantMessagingImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedProtocol, @Param(id = 4) String formattedType, @Param(id = 5) String protocol, @Param(id = 6) String type, @Param(id = 7) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedProtocol = formattedProtocol;
            this.mFormattedType = formattedType;
            this.mProtocol = protocol;
            this.mType = type;
            this.mValue = value;
        }

        public InstantMessagingImpl(InstantMessagingBase other) {
            this();
            importData(other);
        }

        public InstantMessagingImpl importData(InstantMessagingBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedProtocol();
            if (other.hasFormattedProtocol()) {
                setFormattedProtocol(other.getFormattedProtocol());
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearProtocol();
            if (other.hasProtocol()) {
                setProtocol(other.getProtocol());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public InstantMessagingImpl mergeData(InstantMessagingBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedProtocol()) {
                setFormattedProtocol(other.getFormattedProtocol());
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasProtocol()) {
                setProtocol(other.getProtocol());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public InstantMessagingImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public InstantMessagingImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedProtocol() {
            return this.mFormattedProtocol != null;
        }

        public String getFormattedProtocol() {
            return this.mFormattedProtocol;
        }

        public InstantMessagingImpl setFormattedProtocol(String value) {
            this.mFormattedProtocol = value;
            return this;
        }

        public InstantMessagingImpl clearFormattedProtocol() {
            return setFormattedProtocol(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public InstantMessagingImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public InstantMessagingImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasProtocol() {
            return this.mProtocol != null;
        }

        public String getProtocol() {
            return this.mProtocol;
        }

        public InstantMessagingImpl setProtocol(String value) {
            this.mProtocol = value;
            return this;
        }

        public InstantMessagingImpl clearProtocol() {
            return setProtocol(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public InstantMessagingImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public InstantMessagingImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public InstantMessagingImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public InstantMessagingImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            InstantMessagingImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "LegacyFieldsImplCreator")
    @VisibleForTesting
    public static class LegacyFieldsImpl implements SafeParcelable, LegacyFields {
        public static final LegacyFieldsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        String mMobileOwnerId;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new LegacyFieldsImplCreator();
        }

        public LegacyFieldsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        LegacyFieldsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String mobileOwnerId) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMobileOwnerId = mobileOwnerId;
        }

        public LegacyFieldsImpl(LegacyFieldsBase other) {
            this();
            importData(other);
        }

        public LegacyFieldsImpl importData(LegacyFieldsBase other) {
            clearMobileOwnerId();
            if (other.hasMobileOwnerId()) {
                setMobileOwnerId(other.getMobileOwnerId());
            }
            return this;
        }

        public LegacyFieldsImpl mergeData(LegacyFieldsBase other) {
            if (other.hasMobileOwnerId()) {
                setMobileOwnerId(other.getMobileOwnerId());
            }
            return this;
        }

        public boolean hasMobileOwnerId() {
            return this.mMobileOwnerId != null;
        }

        public String getMobileOwnerId() {
            return this.mMobileOwnerId;
        }

        public LegacyFieldsImpl setMobileOwnerId(String value) {
            this.mMobileOwnerId = value;
            return this;
        }

        public LegacyFieldsImpl clearMobileOwnerId() {
            return setMobileOwnerId(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            LegacyFieldsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "MembershipsImplCreator")
    @VisibleForTesting
    public static class MembershipsImpl implements SafeParcelable, Memberships {
        public static final MembershipsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mCircle;
        @Field(id = 4)
        String mContactGroup;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 5)
        String mSystemContactGroup;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new MembershipsImplCreator();
        }

        public MembershipsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        MembershipsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String circle, @Param(id = 4) String contactGroup, @Param(id = 5) String systemContactGroup) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mCircle = circle;
            this.mContactGroup = contactGroup;
            this.mSystemContactGroup = systemContactGroup;
        }

        public MembershipsImpl(MembershipsBase other) {
            this();
            importData(other);
        }

        public MembershipsImpl importData(MembershipsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearCircle();
            if (other.hasCircle()) {
                setCircle(other.getCircle());
            }
            clearContactGroup();
            if (other.hasContactGroup()) {
                setContactGroup(other.getContactGroup());
            }
            clearSystemContactGroup();
            if (other.hasSystemContactGroup()) {
                setSystemContactGroup(other.getSystemContactGroup());
            }
            return this;
        }

        public MembershipsImpl mergeData(MembershipsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasCircle()) {
                setCircle(other.getCircle());
            }
            if (other.hasContactGroup()) {
                setContactGroup(other.getContactGroup());
            }
            if (other.hasSystemContactGroup()) {
                setSystemContactGroup(other.getSystemContactGroup());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public MembershipsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public MembershipsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasCircle() {
            return this.mCircle != null;
        }

        public String getCircle() {
            return this.mCircle;
        }

        public MembershipsImpl setCircle(String value) {
            this.mCircle = value;
            return this;
        }

        public MembershipsImpl clearCircle() {
            return setCircle(null);
        }

        public boolean hasContactGroup() {
            return this.mContactGroup != null;
        }

        public String getContactGroup() {
            return this.mContactGroup;
        }

        public MembershipsImpl setContactGroup(String value) {
            this.mContactGroup = value;
            return this;
        }

        public MembershipsImpl clearContactGroup() {
            return setContactGroup(null);
        }

        public boolean hasSystemContactGroup() {
            return this.mSystemContactGroup != null;
        }

        public String getSystemContactGroup() {
            return this.mSystemContactGroup;
        }

        public MembershipsImpl setSystemContactGroup(String value) {
            this.mSystemContactGroup = value;
            return this;
        }

        public MembershipsImpl clearSystemContactGroup() {
            return setSystemContactGroup(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            MembershipsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "MetadataHolderImplCreator")
    @VisibleForTesting
    public static class MetadataHolderImpl implements SafeParcelable, MetadataHolder {
        public static final MetadataHolderImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new MetadataHolderImplCreator();
        }

        public MetadataHolderImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        MetadataHolderImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
        }

        public MetadataHolderImpl(MetadataHolderBase other) {
            this();
            importData(other);
        }

        public MetadataHolderImpl importData(MetadataHolderBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            return this;
        }

        public MetadataHolderImpl mergeData(MetadataHolderBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public MetadataHolderImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public MetadataHolderImpl clearMetadata() {
            return setMetadata(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            MetadataHolderImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "MetadataImplCreator")
    @VisibleForTesting
    public static class MetadataImpl implements SafeParcelable, Metadata {
        public static final MetadataImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 2)
        String mContainer;
        @Field(id = 3)
        String mContainerContactId;
        @Field(id = 4)
        String mContainerId;
        @Field(id = 6)
        boolean mEdgeKey;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 7)
        boolean mPrimary;
        @Field(id = 10)
        int mRawContactId;
        @Field(id = 8)
        boolean mVerified;
        @VersionField(id = 1)
        final int mVersionCode;
        @Field(id = 5)
        String mVisibility;
        @Field(id = 9)
        boolean mWriteable;

        static {
            CREATOR = new MetadataImplCreator();
        }

        public MetadataImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        MetadataImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String container, @Param(id = 3) String containerContactId, @Param(id = 4) String containerId, @Param(id = 5) String visibility, @Param(id = 6) boolean edgeKey, @Param(id = 7) boolean primary, @Param(id = 8) boolean verified, @Param(id = 9) boolean writeable, @Param(id = 10) int rawContactId) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mContainer = container;
            this.mContainerContactId = containerContactId;
            this.mContainerId = containerId;
            this.mVisibility = visibility;
            this.mEdgeKey = edgeKey;
            this.mPrimary = primary;
            this.mVerified = verified;
            this.mWriteable = writeable;
            this.mRawContactId = rawContactId;
        }

        public MetadataImpl(MetadataBase other) {
            this();
            importData(other);
        }

        public MetadataImpl importData(MetadataBase other) {
            clearContainer();
            if (other.hasContainer()) {
                setContainer(other.getContainer());
            }
            clearContainerContactId();
            if (other.hasContainerContactId()) {
                setContainerContactId(other.getContainerContactId());
            }
            clearContainerId();
            if (other.hasContainerId()) {
                setContainerId(other.getContainerId());
            }
            clearVisibility();
            if (other.hasVisibility()) {
                setVisibility(other.getVisibility());
            }
            clearEdgeKey();
            if (other.hasEdgeKey()) {
                setEdgeKey(other.isEdgeKey());
            }
            clearPrimary();
            if (other.hasPrimary()) {
                setPrimary(other.isPrimary());
            }
            clearVerified();
            if (other.hasVerified()) {
                setVerified(other.isVerified());
            }
            clearWriteable();
            if (other.hasWriteable()) {
                setWriteable(other.isWriteable());
            }
            clearRawContactId();
            if (other.hasRawContactId()) {
                setRawContactId(other.getRawContactId());
            }
            return this;
        }

        public MetadataImpl mergeData(MetadataBase other) {
            if (other.hasContainer()) {
                setContainer(other.getContainer());
            }
            if (other.hasContainerContactId()) {
                setContainerContactId(other.getContainerContactId());
            }
            if (other.hasContainerId()) {
                setContainerId(other.getContainerId());
            }
            if (other.hasVisibility()) {
                setVisibility(other.getVisibility());
            }
            if (other.hasEdgeKey()) {
                setEdgeKey(other.isEdgeKey());
            }
            if (other.hasPrimary()) {
                setPrimary(other.isPrimary());
            }
            if (other.hasVerified()) {
                setVerified(other.isVerified());
            }
            if (other.hasWriteable()) {
                setWriteable(other.isWriteable());
            }
            if (other.hasRawContactId()) {
                setRawContactId(other.getRawContactId());
            }
            return this;
        }

        public boolean hasContainer() {
            return this.mContainer != null;
        }

        public String getContainer() {
            return this.mContainer;
        }

        public MetadataImpl setContainer(String value) {
            this.mContainer = value;
            return this;
        }

        public MetadataImpl clearContainer() {
            return setContainer(null);
        }

        public boolean hasContainerContactId() {
            return this.mContainerContactId != null;
        }

        public String getContainerContactId() {
            return this.mContainerContactId;
        }

        public MetadataImpl setContainerContactId(String value) {
            this.mContainerContactId = value;
            return this;
        }

        public MetadataImpl clearContainerContactId() {
            return setContainerContactId(null);
        }

        public boolean hasContainerId() {
            return this.mContainerId != null;
        }

        public String getContainerId() {
            return this.mContainerId;
        }

        public MetadataImpl setContainerId(String value) {
            this.mContainerId = value;
            return this;
        }

        public MetadataImpl clearContainerId() {
            return setContainerId(null);
        }

        public boolean hasVisibility() {
            return this.mVisibility != null;
        }

        public String getVisibility() {
            return this.mVisibility;
        }

        public MetadataImpl setVisibility(String value) {
            this.mVisibility = value;
            return this;
        }

        public MetadataImpl clearVisibility() {
            return setVisibility(null);
        }

        public boolean hasEdgeKey() {
            return this.mIndicatorSet.contains(Integer.valueOf(6));
        }

        public boolean isEdgeKey() {
            return this.mEdgeKey;
        }

        public MetadataImpl setEdgeKey(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(6));
            this.mEdgeKey = value;
            return this;
        }

        public MetadataImpl clearEdgeKey() {
            this.mIndicatorSet.remove(Integer.valueOf(6));
            return this;
        }

        public boolean hasPrimary() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public boolean isPrimary() {
            return this.mPrimary;
        }

        public MetadataImpl setPrimary(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(7));
            this.mPrimary = value;
            return this;
        }

        public MetadataImpl clearPrimary() {
            this.mIndicatorSet.remove(Integer.valueOf(7));
            return this;
        }

        public boolean hasVerified() {
            return this.mIndicatorSet.contains(Integer.valueOf(8));
        }

        public boolean isVerified() {
            return this.mVerified;
        }

        public MetadataImpl setVerified(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(8));
            this.mVerified = value;
            return this;
        }

        public MetadataImpl clearVerified() {
            this.mIndicatorSet.remove(Integer.valueOf(8));
            return this;
        }

        public boolean hasWriteable() {
            return this.mIndicatorSet.contains(Integer.valueOf(9));
        }

        public boolean isWriteable() {
            return this.mWriteable;
        }

        public MetadataImpl setWriteable(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(9));
            this.mWriteable = value;
            return this;
        }

        public MetadataImpl clearWriteable() {
            this.mIndicatorSet.remove(Integer.valueOf(9));
            return this;
        }

        public boolean hasRawContactId() {
            return this.mIndicatorSet.contains(Integer.valueOf(10));
        }

        public int getRawContactId() {
            return this.mRawContactId;
        }

        public MetadataImpl setRawContactId(int value) {
            this.mIndicatorSet.add(Integer.valueOf(10));
            this.mRawContactId = value;
            return this;
        }

        public MetadataImpl clearRawContactId() {
            this.mIndicatorSet.remove(Integer.valueOf(10));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            MetadataImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "NamesImplCreator")
    @VisibleForTesting
    public static class NamesImpl implements SafeParcelable, Names {
        public static final NamesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mDisplayName;
        @Field(id = 4)
        String mFamilyName;
        @Field(id = 5)
        String mFormatted;
        @Field(id = 6)
        String mGivenName;
        @Field(id = 7)
        String mHonorificPrefix;
        @Field(id = 8)
        String mHonorificSuffix;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 9)
        String mMiddleName;
        @Field(id = 10)
        String mPhoneticFamilyName;
        @Field(id = 11)
        String mPhoneticGivenName;
        @Field(id = 12)
        String mPhoneticHonorificPrefix;
        @Field(id = 13)
        String mPhoneticHonorificSuffix;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new NamesImplCreator();
        }

        public NamesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        NamesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String displayName, @Param(id = 4) String familyName, @Param(id = 5) String formatted, @Param(id = 6) String givenName, @Param(id = 7) String honorificPrefix, @Param(id = 8) String honorificSuffix, @Param(id = 9) String middleName, @Param(id = 10) String phoneticFamilyName, @Param(id = 11) String phoneticGivenName, @Param(id = 12) String phoneticHonorificPrefix, @Param(id = 13) String phoneticHonorificSuffix) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mDisplayName = displayName;
            this.mFamilyName = familyName;
            this.mFormatted = formatted;
            this.mGivenName = givenName;
            this.mHonorificPrefix = honorificPrefix;
            this.mHonorificSuffix = honorificSuffix;
            this.mMiddleName = middleName;
            this.mPhoneticFamilyName = phoneticFamilyName;
            this.mPhoneticGivenName = phoneticGivenName;
            this.mPhoneticHonorificPrefix = phoneticHonorificPrefix;
            this.mPhoneticHonorificSuffix = phoneticHonorificSuffix;
        }

        public NamesImpl(NamesBase other) {
            this();
            importData(other);
        }

        public NamesImpl importData(NamesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearDisplayName();
            if (other.hasDisplayName()) {
                setDisplayName(other.getDisplayName());
            }
            clearFamilyName();
            if (other.hasFamilyName()) {
                setFamilyName(other.getFamilyName());
            }
            clearFormatted();
            if (other.hasFormatted()) {
                setFormatted(other.getFormatted());
            }
            clearGivenName();
            if (other.hasGivenName()) {
                setGivenName(other.getGivenName());
            }
            clearHonorificPrefix();
            if (other.hasHonorificPrefix()) {
                setHonorificPrefix(other.getHonorificPrefix());
            }
            clearHonorificSuffix();
            if (other.hasHonorificSuffix()) {
                setHonorificSuffix(other.getHonorificSuffix());
            }
            clearMiddleName();
            if (other.hasMiddleName()) {
                setMiddleName(other.getMiddleName());
            }
            clearPhoneticFamilyName();
            if (other.hasPhoneticFamilyName()) {
                setPhoneticFamilyName(other.getPhoneticFamilyName());
            }
            clearPhoneticGivenName();
            if (other.hasPhoneticGivenName()) {
                setPhoneticGivenName(other.getPhoneticGivenName());
            }
            clearPhoneticHonorificPrefix();
            if (other.hasPhoneticHonorificPrefix()) {
                setPhoneticHonorificPrefix(other.getPhoneticHonorificPrefix());
            }
            clearPhoneticHonorificSuffix();
            if (other.hasPhoneticHonorificSuffix()) {
                setPhoneticHonorificSuffix(other.getPhoneticHonorificSuffix());
            }
            return this;
        }

        public NamesImpl mergeData(NamesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasDisplayName()) {
                setDisplayName(other.getDisplayName());
            }
            if (other.hasFamilyName()) {
                setFamilyName(other.getFamilyName());
            }
            if (other.hasFormatted()) {
                setFormatted(other.getFormatted());
            }
            if (other.hasGivenName()) {
                setGivenName(other.getGivenName());
            }
            if (other.hasHonorificPrefix()) {
                setHonorificPrefix(other.getHonorificPrefix());
            }
            if (other.hasHonorificSuffix()) {
                setHonorificSuffix(other.getHonorificSuffix());
            }
            if (other.hasMiddleName()) {
                setMiddleName(other.getMiddleName());
            }
            if (other.hasPhoneticFamilyName()) {
                setPhoneticFamilyName(other.getPhoneticFamilyName());
            }
            if (other.hasPhoneticGivenName()) {
                setPhoneticGivenName(other.getPhoneticGivenName());
            }
            if (other.hasPhoneticHonorificPrefix()) {
                setPhoneticHonorificPrefix(other.getPhoneticHonorificPrefix());
            }
            if (other.hasPhoneticHonorificSuffix()) {
                setPhoneticHonorificSuffix(other.getPhoneticHonorificSuffix());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public NamesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public NamesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasDisplayName() {
            return this.mDisplayName != null;
        }

        public String getDisplayName() {
            return this.mDisplayName;
        }

        public NamesImpl setDisplayName(String value) {
            this.mDisplayName = value;
            return this;
        }

        public NamesImpl clearDisplayName() {
            return setDisplayName(null);
        }

        public boolean hasFamilyName() {
            return this.mFamilyName != null;
        }

        public String getFamilyName() {
            return this.mFamilyName;
        }

        public NamesImpl setFamilyName(String value) {
            this.mFamilyName = value;
            return this;
        }

        public NamesImpl clearFamilyName() {
            return setFamilyName(null);
        }

        public boolean hasFormatted() {
            return this.mFormatted != null;
        }

        public String getFormatted() {
            return this.mFormatted;
        }

        public NamesImpl setFormatted(String value) {
            this.mFormatted = value;
            return this;
        }

        public NamesImpl clearFormatted() {
            return setFormatted(null);
        }

        public boolean hasGivenName() {
            return this.mGivenName != null;
        }

        public String getGivenName() {
            return this.mGivenName;
        }

        public NamesImpl setGivenName(String value) {
            this.mGivenName = value;
            return this;
        }

        public NamesImpl clearGivenName() {
            return setGivenName(null);
        }

        public boolean hasHonorificPrefix() {
            return this.mHonorificPrefix != null;
        }

        public String getHonorificPrefix() {
            return this.mHonorificPrefix;
        }

        public NamesImpl setHonorificPrefix(String value) {
            this.mHonorificPrefix = value;
            return this;
        }

        public NamesImpl clearHonorificPrefix() {
            return setHonorificPrefix(null);
        }

        public boolean hasHonorificSuffix() {
            return this.mHonorificSuffix != null;
        }

        public String getHonorificSuffix() {
            return this.mHonorificSuffix;
        }

        public NamesImpl setHonorificSuffix(String value) {
            this.mHonorificSuffix = value;
            return this;
        }

        public NamesImpl clearHonorificSuffix() {
            return setHonorificSuffix(null);
        }

        public boolean hasMiddleName() {
            return this.mMiddleName != null;
        }

        public String getMiddleName() {
            return this.mMiddleName;
        }

        public NamesImpl setMiddleName(String value) {
            this.mMiddleName = value;
            return this;
        }

        public NamesImpl clearMiddleName() {
            return setMiddleName(null);
        }

        public boolean hasPhoneticFamilyName() {
            return this.mPhoneticFamilyName != null;
        }

        public String getPhoneticFamilyName() {
            return this.mPhoneticFamilyName;
        }

        public NamesImpl setPhoneticFamilyName(String value) {
            this.mPhoneticFamilyName = value;
            return this;
        }

        public NamesImpl clearPhoneticFamilyName() {
            return setPhoneticFamilyName(null);
        }

        public boolean hasPhoneticGivenName() {
            return this.mPhoneticGivenName != null;
        }

        public String getPhoneticGivenName() {
            return this.mPhoneticGivenName;
        }

        public NamesImpl setPhoneticGivenName(String value) {
            this.mPhoneticGivenName = value;
            return this;
        }

        public NamesImpl clearPhoneticGivenName() {
            return setPhoneticGivenName(null);
        }

        public boolean hasPhoneticHonorificPrefix() {
            return this.mPhoneticHonorificPrefix != null;
        }

        public String getPhoneticHonorificPrefix() {
            return this.mPhoneticHonorificPrefix;
        }

        public NamesImpl setPhoneticHonorificPrefix(String value) {
            this.mPhoneticHonorificPrefix = value;
            return this;
        }

        public NamesImpl clearPhoneticHonorificPrefix() {
            return setPhoneticHonorificPrefix(null);
        }

        public boolean hasPhoneticHonorificSuffix() {
            return this.mPhoneticHonorificSuffix != null;
        }

        public String getPhoneticHonorificSuffix() {
            return this.mPhoneticHonorificSuffix;
        }

        public NamesImpl setPhoneticHonorificSuffix(String value) {
            this.mPhoneticHonorificSuffix = value;
            return this;
        }

        public NamesImpl clearPhoneticHonorificSuffix() {
            return setPhoneticHonorificSuffix(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            NamesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "NicknamesImplCreator")
    @VisibleForTesting
    public static class NicknamesImpl implements SafeParcelable, Nicknames {
        public static final NicknamesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mType;
        @Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new NicknamesImplCreator();
        }

        public NicknamesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        NicknamesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String type, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mType = type;
            this.mValue = value;
        }

        public NicknamesImpl(NicknamesBase other) {
            this();
            importData(other);
        }

        public NicknamesImpl importData(NicknamesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public NicknamesImpl mergeData(NicknamesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public NicknamesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public NicknamesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public NicknamesImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public NicknamesImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public NicknamesImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public NicknamesImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            NicknamesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "NotesImplCreator")
    @VisibleForTesting
    public static class NotesImpl implements SafeParcelable, Notes {
        public static final NotesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new NotesImplCreator();
        }

        public NotesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        NotesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public NotesImpl(NotesBase other) {
            this();
            importData(other);
        }

        public NotesImpl importData(NotesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public NotesImpl mergeData(NotesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public NotesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public NotesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public NotesImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public NotesImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            NotesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "OccupationsImplCreator")
    @VisibleForTesting
    public static class OccupationsImpl implements SafeParcelable, Occupations {
        public static final OccupationsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new OccupationsImplCreator();
        }

        public OccupationsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        OccupationsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public OccupationsImpl(OccupationsBase other) {
            this();
            importData(other);
        }

        public OccupationsImpl importData(OccupationsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public OccupationsImpl mergeData(OccupationsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public OccupationsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public OccupationsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public OccupationsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public OccupationsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            OccupationsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "OrganizationsImplCreator")
    @VisibleForTesting
    public static class OrganizationsImpl implements SafeParcelable, Organizations {
        public static final OrganizationsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        boolean mCurrent;
        @Field(id = 4)
        String mDepartment;
        @Field(id = 5)
        String mDescription;
        @Field(id = 6)
        String mDomain;
        @Field(id = 7)
        String mEndDate;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 8)
        String mLocation;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 9)
        String mName;
        @Field(id = 10)
        String mPhoneticName;
        @Field(id = 11)
        String mStartDate;
        @Field(id = 12)
        String mSymbol;
        @Field(id = 13)
        String mTitle;
        @Field(id = 14)
        String mType;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new OrganizationsImplCreator();
        }

        public OrganizationsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        OrganizationsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) boolean current, @Param(id = 4) String department, @Param(id = 5) String description, @Param(id = 6) String domain, @Param(id = 7) String endDate, @Param(id = 8) String location, @Param(id = 9) String name, @Param(id = 10) String phoneticName, @Param(id = 11) String startDate, @Param(id = 12) String symbol, @Param(id = 13) String title, @Param(id = 14) String type) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mCurrent = current;
            this.mDepartment = department;
            this.mDescription = description;
            this.mDomain = domain;
            this.mEndDate = endDate;
            this.mLocation = location;
            this.mName = name;
            this.mPhoneticName = phoneticName;
            this.mStartDate = startDate;
            this.mSymbol = symbol;
            this.mTitle = title;
            this.mType = type;
        }

        public OrganizationsImpl(OrganizationsBase other) {
            this();
            importData(other);
        }

        public OrganizationsImpl importData(OrganizationsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearCurrent();
            if (other.hasCurrent()) {
                setCurrent(other.isCurrent());
            }
            clearDepartment();
            if (other.hasDepartment()) {
                setDepartment(other.getDepartment());
            }
            clearDescription();
            if (other.hasDescription()) {
                setDescription(other.getDescription());
            }
            clearDomain();
            if (other.hasDomain()) {
                setDomain(other.getDomain());
            }
            clearEndDate();
            if (other.hasEndDate()) {
                setEndDate(other.getEndDate());
            }
            clearLocation();
            if (other.hasLocation()) {
                setLocation(other.getLocation());
            }
            clearName();
            if (other.hasName()) {
                setName(other.getName());
            }
            clearPhoneticName();
            if (other.hasPhoneticName()) {
                setPhoneticName(other.getPhoneticName());
            }
            clearStartDate();
            if (other.hasStartDate()) {
                setStartDate(other.getStartDate());
            }
            clearSymbol();
            if (other.hasSymbol()) {
                setSymbol(other.getSymbol());
            }
            clearTitle();
            if (other.hasTitle()) {
                setTitle(other.getTitle());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            return this;
        }

        public OrganizationsImpl mergeData(OrganizationsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasCurrent()) {
                setCurrent(other.isCurrent());
            }
            if (other.hasDepartment()) {
                setDepartment(other.getDepartment());
            }
            if (other.hasDescription()) {
                setDescription(other.getDescription());
            }
            if (other.hasDomain()) {
                setDomain(other.getDomain());
            }
            if (other.hasEndDate()) {
                setEndDate(other.getEndDate());
            }
            if (other.hasLocation()) {
                setLocation(other.getLocation());
            }
            if (other.hasName()) {
                setName(other.getName());
            }
            if (other.hasPhoneticName()) {
                setPhoneticName(other.getPhoneticName());
            }
            if (other.hasStartDate()) {
                setStartDate(other.getStartDate());
            }
            if (other.hasSymbol()) {
                setSymbol(other.getSymbol());
            }
            if (other.hasTitle()) {
                setTitle(other.getTitle());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public OrganizationsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public OrganizationsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasCurrent() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public boolean isCurrent() {
            return this.mCurrent;
        }

        public OrganizationsImpl setCurrent(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(3));
            this.mCurrent = value;
            return this;
        }

        public OrganizationsImpl clearCurrent() {
            this.mIndicatorSet.remove(Integer.valueOf(3));
            return this;
        }

        public boolean hasDepartment() {
            return this.mDepartment != null;
        }

        public String getDepartment() {
            return this.mDepartment;
        }

        public OrganizationsImpl setDepartment(String value) {
            this.mDepartment = value;
            return this;
        }

        public OrganizationsImpl clearDepartment() {
            return setDepartment(null);
        }

        public boolean hasDescription() {
            return this.mDescription != null;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public OrganizationsImpl setDescription(String value) {
            this.mDescription = value;
            return this;
        }

        public OrganizationsImpl clearDescription() {
            return setDescription(null);
        }

        public boolean hasDomain() {
            return this.mDomain != null;
        }

        public String getDomain() {
            return this.mDomain;
        }

        public OrganizationsImpl setDomain(String value) {
            this.mDomain = value;
            return this;
        }

        public OrganizationsImpl clearDomain() {
            return setDomain(null);
        }

        public boolean hasEndDate() {
            return this.mEndDate != null;
        }

        public String getEndDate() {
            return this.mEndDate;
        }

        public OrganizationsImpl setEndDate(String value) {
            this.mEndDate = value;
            return this;
        }

        public OrganizationsImpl clearEndDate() {
            return setEndDate(null);
        }

        public boolean hasLocation() {
            return this.mLocation != null;
        }

        public String getLocation() {
            return this.mLocation;
        }

        public OrganizationsImpl setLocation(String value) {
            this.mLocation = value;
            return this;
        }

        public OrganizationsImpl clearLocation() {
            return setLocation(null);
        }

        public boolean hasName() {
            return this.mName != null;
        }

        public String getName() {
            return this.mName;
        }

        public OrganizationsImpl setName(String value) {
            this.mName = value;
            return this;
        }

        public OrganizationsImpl clearName() {
            return setName(null);
        }

        public boolean hasPhoneticName() {
            return this.mPhoneticName != null;
        }

        public String getPhoneticName() {
            return this.mPhoneticName;
        }

        public OrganizationsImpl setPhoneticName(String value) {
            this.mPhoneticName = value;
            return this;
        }

        public OrganizationsImpl clearPhoneticName() {
            return setPhoneticName(null);
        }

        public boolean hasStartDate() {
            return this.mStartDate != null;
        }

        public String getStartDate() {
            return this.mStartDate;
        }

        public OrganizationsImpl setStartDate(String value) {
            this.mStartDate = value;
            return this;
        }

        public OrganizationsImpl clearStartDate() {
            return setStartDate(null);
        }

        public boolean hasSymbol() {
            return this.mSymbol != null;
        }

        public String getSymbol() {
            return this.mSymbol;
        }

        public OrganizationsImpl setSymbol(String value) {
            this.mSymbol = value;
            return this;
        }

        public OrganizationsImpl clearSymbol() {
            return setSymbol(null);
        }

        public boolean hasTitle() {
            return this.mTitle != null;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public OrganizationsImpl setTitle(String value) {
            this.mTitle = value;
            return this;
        }

        public OrganizationsImpl clearTitle() {
            return setTitle(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public OrganizationsImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public OrganizationsImpl clearType() {
            return setType(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            OrganizationsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "PersonMetadataImplCreator")
    @VisibleForTesting
    public static class PersonMetadataImpl implements SafeParcelable, PersonMetadata {
        public static final PersonMetadataImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 2)
        List<String> mAttributions;
        @Field(id = 3)
        List<String> mBlockTypes;
        @Field(id = 13)
        boolean mBlocked;
        @Field(id = 4)
        List<String> mCircles;
        @Field(id = 5)
        List<String> mContacts;
        @Field(id = 14)
        boolean mDeleted;
        @Field(id = 6)
        List<String> mGroups;
        @Field(id = 15)
        boolean mInViewerDomain;
        @Field(id = 7)
        List<String> mIncomingBlockTypes;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 8)
        String mObjectType;
        @Field(id = 9)
        String mOwnerId;
        @Field(id = 10)
        List<String> mOwnerUserTypes;
        @Field(id = 11)
        String mPlusPageType;
        @Field(id = 12)
        ProfileOwnerStatsImpl mProfileOwnerStats;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new PersonMetadataImplCreator();
        }

        public PersonMetadataImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        PersonMetadataImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) List<String> attributions, @Param(id = 3) List<String> blockTypes, @Param(id = 4) List<String> circles, @Param(id = 5) List<String> contacts, @Param(id = 6) List<String> groups, @Param(id = 7) List<String> incomingBlockTypes, @Param(id = 8) String objectType, @Param(id = 9) String ownerId, @Param(id = 10) List<String> ownerUserTypes, @Param(id = 11) String plusPageType, @Param(id = 12) ProfileOwnerStatsImpl profileOwnerStats, @Param(id = 13) boolean blocked, @Param(id = 14) boolean deleted, @Param(id = 15) boolean inViewerDomain) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mAttributions = attributions;
            this.mBlockTypes = blockTypes;
            this.mCircles = circles;
            this.mContacts = contacts;
            this.mGroups = groups;
            this.mIncomingBlockTypes = incomingBlockTypes;
            this.mObjectType = objectType;
            this.mOwnerId = ownerId;
            this.mOwnerUserTypes = ownerUserTypes;
            this.mPlusPageType = plusPageType;
            this.mProfileOwnerStats = profileOwnerStats;
            this.mBlocked = blocked;
            this.mDeleted = deleted;
            this.mInViewerDomain = inViewerDomain;
        }

        public PersonMetadataImpl(PersonMetadataBase other) {
            this();
            importData(other);
        }

        public PersonMetadataImpl importData(PersonMetadataBase other) {
            clearAttributions();
            if (other.hasAttributions()) {
                addAllAttributions(other.getAttributions());
            }
            clearBlockTypes();
            if (other.hasBlockTypes()) {
                addAllBlockTypes(other.getBlockTypes());
            }
            clearCircles();
            if (other.hasCircles()) {
                addAllCircles(other.getCircles());
            }
            clearContacts();
            if (other.hasContacts()) {
                addAllContacts(other.getContacts());
            }
            clearGroups();
            if (other.hasGroups()) {
                addAllGroups(other.getGroups());
            }
            clearIncomingBlockTypes();
            if (other.hasIncomingBlockTypes()) {
                addAllIncomingBlockTypes(other.getIncomingBlockTypes());
            }
            clearObjectType();
            if (other.hasObjectType()) {
                setObjectType(other.getObjectType());
            }
            clearOwnerId();
            if (other.hasOwnerId()) {
                setOwnerId(other.getOwnerId());
            }
            clearOwnerUserTypes();
            if (other.hasOwnerUserTypes()) {
                addAllOwnerUserTypes(other.getOwnerUserTypes());
            }
            clearPlusPageType();
            if (other.hasPlusPageType()) {
                setPlusPageType(other.getPlusPageType());
            }
            clearProfileOwnerStats();
            if (other.hasProfileOwnerStats()) {
                setProfileOwnerStats(new ProfileOwnerStatsImpl(other.getProfileOwnerStats()));
            }
            clearBlocked();
            if (other.hasBlocked()) {
                setBlocked(other.isBlocked());
            }
            clearDeleted();
            if (other.hasDeleted()) {
                setDeleted(other.isDeleted());
            }
            clearInViewerDomain();
            if (other.hasInViewerDomain()) {
                setInViewerDomain(other.isInViewerDomain());
            }
            return this;
        }

        public PersonMetadataImpl mergeData(PersonMetadataBase other) {
            if (other.hasAttributions()) {
                addAllAttributions(other.getAttributions());
            }
            if (other.hasBlockTypes()) {
                addAllBlockTypes(other.getBlockTypes());
            }
            if (other.hasCircles()) {
                addAllCircles(other.getCircles());
            }
            if (other.hasContacts()) {
                addAllContacts(other.getContacts());
            }
            if (other.hasGroups()) {
                addAllGroups(other.getGroups());
            }
            if (other.hasIncomingBlockTypes()) {
                addAllIncomingBlockTypes(other.getIncomingBlockTypes());
            }
            if (other.hasObjectType()) {
                setObjectType(other.getObjectType());
            }
            if (other.hasOwnerId()) {
                setOwnerId(other.getOwnerId());
            }
            if (other.hasOwnerUserTypes()) {
                addAllOwnerUserTypes(other.getOwnerUserTypes());
            }
            if (other.hasPlusPageType()) {
                setPlusPageType(other.getPlusPageType());
            }
            if (other.hasProfileOwnerStats()) {
                setProfileOwnerStats(new ProfileOwnerStatsImpl(other.getProfileOwnerStats()));
            }
            if (other.hasBlocked()) {
                setBlocked(other.isBlocked());
            }
            if (other.hasDeleted()) {
                setDeleted(other.isDeleted());
            }
            if (other.hasInViewerDomain()) {
                setInViewerDomain(other.isInViewerDomain());
            }
            return this;
        }

        public boolean hasAttributions() {
            return this.mAttributions != null;
        }

        public List<String> getAttributions() {
            return this.mAttributions;
        }

        public PersonMetadataImpl setAttributions(List<String> value) {
            this.mAttributions = value;
            return this;
        }

        private List<String> createAttributions() {
            if (this.mAttributions == null) {
                this.mAttributions = new ArrayList();
            }
            return this.mAttributions;
        }

        public PersonMetadataImpl addAttributions(String value) {
            createAttributions().add(value);
            return this;
        }

        public PersonMetadataImpl addAllAttributions(Collection<String> values) {
            createAttributions().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeAttributions(String value) {
            if (this.mAttributions != null) {
                this.mAttributions.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearAttributions() {
            this.mAttributions = null;
            return this;
        }

        public boolean hasBlockTypes() {
            return this.mBlockTypes != null;
        }

        public List<String> getBlockTypes() {
            return this.mBlockTypes;
        }

        public PersonMetadataImpl setBlockTypes(List<String> value) {
            this.mBlockTypes = value;
            return this;
        }

        private List<String> createBlockTypes() {
            if (this.mBlockTypes == null) {
                this.mBlockTypes = new ArrayList();
            }
            return this.mBlockTypes;
        }

        public PersonMetadataImpl addBlockTypes(String value) {
            createBlockTypes().add(value);
            return this;
        }

        public PersonMetadataImpl addAllBlockTypes(Collection<String> values) {
            createBlockTypes().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeBlockTypes(String value) {
            if (this.mBlockTypes != null) {
                this.mBlockTypes.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearBlockTypes() {
            this.mBlockTypes = null;
            return this;
        }

        public boolean hasCircles() {
            return this.mCircles != null;
        }

        public List<String> getCircles() {
            return this.mCircles;
        }

        public PersonMetadataImpl setCircles(List<String> value) {
            this.mCircles = value;
            return this;
        }

        private List<String> createCircles() {
            if (this.mCircles == null) {
                this.mCircles = new ArrayList();
            }
            return this.mCircles;
        }

        public PersonMetadataImpl addCircles(String value) {
            createCircles().add(value);
            return this;
        }

        public PersonMetadataImpl addAllCircles(Collection<String> values) {
            createCircles().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeCircles(String value) {
            if (this.mCircles != null) {
                this.mCircles.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearCircles() {
            this.mCircles = null;
            return this;
        }

        public boolean hasContacts() {
            return this.mContacts != null;
        }

        public List<String> getContacts() {
            return this.mContacts;
        }

        public PersonMetadataImpl setContacts(List<String> value) {
            this.mContacts = value;
            return this;
        }

        private List<String> createContacts() {
            if (this.mContacts == null) {
                this.mContacts = new ArrayList();
            }
            return this.mContacts;
        }

        public PersonMetadataImpl addContacts(String value) {
            createContacts().add(value);
            return this;
        }

        public PersonMetadataImpl addAllContacts(Collection<String> values) {
            createContacts().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeContacts(String value) {
            if (this.mContacts != null) {
                this.mContacts.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearContacts() {
            this.mContacts = null;
            return this;
        }

        public boolean hasGroups() {
            return this.mGroups != null;
        }

        public List<String> getGroups() {
            return this.mGroups;
        }

        public PersonMetadataImpl setGroups(List<String> value) {
            this.mGroups = value;
            return this;
        }

        private List<String> createGroups() {
            if (this.mGroups == null) {
                this.mGroups = new ArrayList();
            }
            return this.mGroups;
        }

        public PersonMetadataImpl addGroups(String value) {
            createGroups().add(value);
            return this;
        }

        public PersonMetadataImpl addAllGroups(Collection<String> values) {
            createGroups().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeGroups(String value) {
            if (this.mGroups != null) {
                this.mGroups.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearGroups() {
            this.mGroups = null;
            return this;
        }

        public boolean hasIncomingBlockTypes() {
            return this.mIncomingBlockTypes != null;
        }

        public List<String> getIncomingBlockTypes() {
            return this.mIncomingBlockTypes;
        }

        public PersonMetadataImpl setIncomingBlockTypes(List<String> value) {
            this.mIncomingBlockTypes = value;
            return this;
        }

        private List<String> createIncomingBlockTypes() {
            if (this.mIncomingBlockTypes == null) {
                this.mIncomingBlockTypes = new ArrayList();
            }
            return this.mIncomingBlockTypes;
        }

        public PersonMetadataImpl addIncomingBlockTypes(String value) {
            createIncomingBlockTypes().add(value);
            return this;
        }

        public PersonMetadataImpl addAllIncomingBlockTypes(Collection<String> values) {
            createIncomingBlockTypes().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeIncomingBlockTypes(String value) {
            if (this.mIncomingBlockTypes != null) {
                this.mIncomingBlockTypes.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearIncomingBlockTypes() {
            this.mIncomingBlockTypes = null;
            return this;
        }

        public boolean hasObjectType() {
            return this.mObjectType != null;
        }

        public String getObjectType() {
            return this.mObjectType;
        }

        public PersonMetadataImpl setObjectType(String value) {
            this.mObjectType = value;
            return this;
        }

        public PersonMetadataImpl clearObjectType() {
            return setObjectType(null);
        }

        public boolean hasOwnerId() {
            return this.mOwnerId != null;
        }

        public String getOwnerId() {
            return this.mOwnerId;
        }

        public PersonMetadataImpl setOwnerId(String value) {
            this.mOwnerId = value;
            return this;
        }

        public PersonMetadataImpl clearOwnerId() {
            return setOwnerId(null);
        }

        public boolean hasOwnerUserTypes() {
            return this.mOwnerUserTypes != null;
        }

        public List<String> getOwnerUserTypes() {
            return this.mOwnerUserTypes;
        }

        public PersonMetadataImpl setOwnerUserTypes(List<String> value) {
            this.mOwnerUserTypes = value;
            return this;
        }

        private List<String> createOwnerUserTypes() {
            if (this.mOwnerUserTypes == null) {
                this.mOwnerUserTypes = new ArrayList();
            }
            return this.mOwnerUserTypes;
        }

        public PersonMetadataImpl addOwnerUserTypes(String value) {
            createOwnerUserTypes().add(value);
            return this;
        }

        public PersonMetadataImpl addAllOwnerUserTypes(Collection<String> values) {
            createOwnerUserTypes().addAll(values);
            return this;
        }

        public PersonMetadataImpl removeOwnerUserTypes(String value) {
            if (this.mOwnerUserTypes != null) {
                this.mOwnerUserTypes.remove(value);
            }
            return this;
        }

        public PersonMetadataImpl clearOwnerUserTypes() {
            this.mOwnerUserTypes = null;
            return this;
        }

        public boolean hasPlusPageType() {
            return this.mPlusPageType != null;
        }

        public String getPlusPageType() {
            return this.mPlusPageType;
        }

        public PersonMetadataImpl setPlusPageType(String value) {
            this.mPlusPageType = value;
            return this;
        }

        public PersonMetadataImpl clearPlusPageType() {
            return setPlusPageType(null);
        }

        public boolean hasProfileOwnerStats() {
            return this.mProfileOwnerStats != null;
        }

        public ProfileOwnerStatsImpl getProfileOwnerStats() {
            return this.mProfileOwnerStats;
        }

        public PersonMetadataImpl setProfileOwnerStats(ProfileOwnerStatsImpl value) {
            this.mProfileOwnerStats = value;
            return this;
        }

        public PersonMetadataImpl clearProfileOwnerStats() {
            return setProfileOwnerStats(null);
        }

        public boolean hasBlocked() {
            return this.mIndicatorSet.contains(Integer.valueOf(13));
        }

        public boolean isBlocked() {
            return this.mBlocked;
        }

        public PersonMetadataImpl setBlocked(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(13));
            this.mBlocked = value;
            return this;
        }

        public PersonMetadataImpl clearBlocked() {
            this.mIndicatorSet.remove(Integer.valueOf(13));
            return this;
        }

        public boolean hasDeleted() {
            return this.mIndicatorSet.contains(Integer.valueOf(14));
        }

        public boolean isDeleted() {
            return this.mDeleted;
        }

        public PersonMetadataImpl setDeleted(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(14));
            this.mDeleted = value;
            return this;
        }

        public PersonMetadataImpl clearDeleted() {
            this.mIndicatorSet.remove(Integer.valueOf(14));
            return this;
        }

        public boolean hasInViewerDomain() {
            return this.mIndicatorSet.contains(Integer.valueOf(15));
        }

        public boolean isInViewerDomain() {
            return this.mInViewerDomain;
        }

        public PersonMetadataImpl setInViewerDomain(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(15));
            this.mInViewerDomain = value;
            return this;
        }

        public PersonMetadataImpl clearInViewerDomain() {
            this.mIndicatorSet.remove(Integer.valueOf(15));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            PersonMetadataImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "PhoneNumbersImplCreator")
    @VisibleForTesting
    public static class PhoneNumbersImpl implements SafeParcelable, PhoneNumbers {
        public static final PhoneNumbersImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mCanonicalizedForm;
        @Field(id = 4)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 7)
        int mTimesUsed;
        @Field(id = 5)
        String mType;
        @Field(id = 6)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new PhoneNumbersImplCreator();
        }

        public PhoneNumbersImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        PhoneNumbersImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String canonicalizedForm, @Param(id = 4) String formattedType, @Param(id = 5) String type, @Param(id = 6) String value, @Param(id = 7) int timesUsed) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mCanonicalizedForm = canonicalizedForm;
            this.mFormattedType = formattedType;
            this.mType = type;
            this.mValue = value;
            this.mTimesUsed = timesUsed;
        }

        public PhoneNumbersImpl(PhoneNumbersBase other) {
            this();
            importData(other);
        }

        public PhoneNumbersImpl importData(PhoneNumbersBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearCanonicalizedForm();
            if (other.hasCanonicalizedForm()) {
                setCanonicalizedForm(other.getCanonicalizedForm());
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            clearTimesUsed();
            if (other.hasTimesUsed()) {
                setTimesUsed(other.getTimesUsed());
            }
            return this;
        }

        public PhoneNumbersImpl mergeData(PhoneNumbersBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasCanonicalizedForm()) {
                setCanonicalizedForm(other.getCanonicalizedForm());
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            if (other.hasTimesUsed()) {
                setTimesUsed(other.getTimesUsed());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public PhoneNumbersImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public PhoneNumbersImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasCanonicalizedForm() {
            return this.mCanonicalizedForm != null;
        }

        public String getCanonicalizedForm() {
            return this.mCanonicalizedForm;
        }

        public PhoneNumbersImpl setCanonicalizedForm(String value) {
            this.mCanonicalizedForm = value;
            return this;
        }

        public PhoneNumbersImpl clearCanonicalizedForm() {
            return setCanonicalizedForm(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public PhoneNumbersImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public PhoneNumbersImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public PhoneNumbersImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public PhoneNumbersImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public PhoneNumbersImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public PhoneNumbersImpl clearValue() {
            return setValue(null);
        }

        public boolean hasTimesUsed() {
            return this.mIndicatorSet.contains(Integer.valueOf(7));
        }

        public int getTimesUsed() {
            return this.mTimesUsed;
        }

        public PhoneNumbersImpl setTimesUsed(int value) {
            this.mIndicatorSet.add(Integer.valueOf(7));
            this.mTimesUsed = value;
            return this;
        }

        public PhoneNumbersImpl clearTimesUsed() {
            this.mIndicatorSet.remove(Integer.valueOf(7));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            PhoneNumbersImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "PlacesLivedImplCreator")
    @VisibleForTesting
    public static class PlacesLivedImpl implements SafeParcelable, PlacesLived {
        public static final PlacesLivedImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        boolean mCurrent;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new PlacesLivedImplCreator();
        }

        public PlacesLivedImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        PlacesLivedImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) boolean current, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mCurrent = current;
            this.mValue = value;
        }

        public PlacesLivedImpl(PlacesLivedBase other) {
            this();
            importData(other);
        }

        public PlacesLivedImpl importData(PlacesLivedBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearCurrent();
            if (other.hasCurrent()) {
                setCurrent(other.isCurrent());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public PlacesLivedImpl mergeData(PlacesLivedBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasCurrent()) {
                setCurrent(other.isCurrent());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public PlacesLivedImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public PlacesLivedImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasCurrent() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public boolean isCurrent() {
            return this.mCurrent;
        }

        public PlacesLivedImpl setCurrent(boolean value) {
            this.mIndicatorSet.add(Integer.valueOf(3));
            this.mCurrent = value;
            return this;
        }

        public PlacesLivedImpl clearCurrent() {
            this.mIndicatorSet.remove(Integer.valueOf(3));
            return this;
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public PlacesLivedImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public PlacesLivedImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            PlacesLivedImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "ProfileOwnerStatsImplCreator")
    @VisibleForTesting
    public static class ProfileOwnerStatsImpl implements SafeParcelable, ProfileOwnerStats {
        public static final ProfileOwnerStatsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 2)
        long mIncomingAnyCircleCount;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @VersionField(id = 1)
        final int mVersionCode;
        @Field(id = 3)
        long mViewCount;

        static {
            CREATOR = new ProfileOwnerStatsImplCreator();
        }

        public ProfileOwnerStatsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        ProfileOwnerStatsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) long incomingAnyCircleCount, @Param(id = 3) long viewCount) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mIncomingAnyCircleCount = incomingAnyCircleCount;
            this.mViewCount = viewCount;
        }

        public ProfileOwnerStatsImpl(ProfileOwnerStatsBase other) {
            this();
            importData(other);
        }

        public ProfileOwnerStatsImpl importData(ProfileOwnerStatsBase other) {
            clearIncomingAnyCircleCount();
            if (other.hasIncomingAnyCircleCount()) {
                setIncomingAnyCircleCount(other.getIncomingAnyCircleCount());
            }
            clearViewCount();
            if (other.hasViewCount()) {
                setViewCount(other.getViewCount());
            }
            return this;
        }

        public ProfileOwnerStatsImpl mergeData(ProfileOwnerStatsBase other) {
            if (other.hasIncomingAnyCircleCount()) {
                setIncomingAnyCircleCount(other.getIncomingAnyCircleCount());
            }
            if (other.hasViewCount()) {
                setViewCount(other.getViewCount());
            }
            return this;
        }

        public boolean hasIncomingAnyCircleCount() {
            return this.mIndicatorSet.contains(Integer.valueOf(2));
        }

        public long getIncomingAnyCircleCount() {
            return this.mIncomingAnyCircleCount;
        }

        public ProfileOwnerStatsImpl setIncomingAnyCircleCount(long value) {
            this.mIndicatorSet.add(Integer.valueOf(2));
            this.mIncomingAnyCircleCount = value;
            return this;
        }

        public ProfileOwnerStatsImpl clearIncomingAnyCircleCount() {
            this.mIndicatorSet.remove(Integer.valueOf(2));
            return this;
        }

        public boolean hasViewCount() {
            return this.mIndicatorSet.contains(Integer.valueOf(3));
        }

        public long getViewCount() {
            return this.mViewCount;
        }

        public ProfileOwnerStatsImpl setViewCount(long value) {
            this.mIndicatorSet.add(Integer.valueOf(3));
            this.mViewCount = value;
            return this;
        }

        public ProfileOwnerStatsImpl clearViewCount() {
            this.mIndicatorSet.remove(Integer.valueOf(3));
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            ProfileOwnerStatsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "RelationsImplCreator")
    @VisibleForTesting
    public static class RelationsImpl implements SafeParcelable, Relations {
        public static final RelationsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 4)
        String mType;
        @Field(id = 5)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new RelationsImplCreator();
        }

        public RelationsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        RelationsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedType, @Param(id = 4) String type, @Param(id = 5) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedType = formattedType;
            this.mType = type;
            this.mValue = value;
        }

        public RelationsImpl(RelationsBase other) {
            this();
            importData(other);
        }

        public RelationsImpl importData(RelationsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public RelationsImpl mergeData(RelationsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public RelationsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public RelationsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public RelationsImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public RelationsImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public RelationsImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public RelationsImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public RelationsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public RelationsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            RelationsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "RelationshipInterestsImplCreator")
    @VisibleForTesting
    public static class RelationshipInterestsImpl implements SafeParcelable, RelationshipInterests {
        public static final RelationshipInterestsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new RelationshipInterestsImplCreator();
        }

        public RelationshipInterestsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        RelationshipInterestsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public RelationshipInterestsImpl(RelationshipInterestsBase other) {
            this();
            importData(other);
        }

        public RelationshipInterestsImpl importData(RelationshipInterestsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public RelationshipInterestsImpl mergeData(RelationshipInterestsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public RelationshipInterestsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public RelationshipInterestsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public RelationshipInterestsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public RelationshipInterestsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            RelationshipInterestsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "RelationshipStatusesImplCreator")
    @VisibleForTesting
    public static class RelationshipStatusesImpl implements SafeParcelable, RelationshipStatuses {
        public static final RelationshipStatusesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mFormattedValue;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 4)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new RelationshipStatusesImplCreator();
        }

        public RelationshipStatusesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        RelationshipStatusesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedValue, @Param(id = 4) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedValue = formattedValue;
            this.mValue = value;
        }

        public RelationshipStatusesImpl(RelationshipStatusesBase other) {
            this();
            importData(other);
        }

        public RelationshipStatusesImpl importData(RelationshipStatusesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedValue();
            if (other.hasFormattedValue()) {
                setFormattedValue(other.getFormattedValue());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public RelationshipStatusesImpl mergeData(RelationshipStatusesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedValue()) {
                setFormattedValue(other.getFormattedValue());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public RelationshipStatusesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public RelationshipStatusesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedValue() {
            return this.mFormattedValue != null;
        }

        public String getFormattedValue() {
            return this.mFormattedValue;
        }

        public RelationshipStatusesImpl setFormattedValue(String value) {
            this.mFormattedValue = value;
            return this;
        }

        public RelationshipStatusesImpl clearFormattedValue() {
            return setFormattedValue(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public RelationshipStatusesImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public RelationshipStatusesImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            RelationshipStatusesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "SkillsImplCreator")
    @VisibleForTesting
    public static class SkillsImpl implements SafeParcelable, Skills {
        public static final SkillsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new SkillsImplCreator();
        }

        public SkillsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        SkillsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public SkillsImpl(SkillsBase other) {
            this();
            importData(other);
        }

        public SkillsImpl importData(SkillsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public SkillsImpl mergeData(SkillsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public SkillsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public SkillsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public SkillsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public SkillsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            SkillsImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "SortKeysImplCreator")
    @VisibleForTesting
    public static class SortKeysImpl implements SafeParcelable, SortKeys {
        public static final SortKeysImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        String mInteractionRank;
        @Field(id = 3)
        String mName;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new SortKeysImplCreator();
        }

        public SortKeysImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        SortKeysImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String interactionRank, @Param(id = 3) String name) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mInteractionRank = interactionRank;
            this.mName = name;
        }

        public SortKeysImpl(SortKeysBase other) {
            this();
            importData(other);
        }

        public SortKeysImpl importData(SortKeysBase other) {
            clearInteractionRank();
            if (other.hasInteractionRank()) {
                setInteractionRank(other.getInteractionRank());
            }
            clearName();
            if (other.hasName()) {
                setName(other.getName());
            }
            return this;
        }

        public SortKeysImpl mergeData(SortKeysBase other) {
            if (other.hasInteractionRank()) {
                setInteractionRank(other.getInteractionRank());
            }
            if (other.hasName()) {
                setName(other.getName());
            }
            return this;
        }

        public boolean hasInteractionRank() {
            return this.mInteractionRank != null;
        }

        public String getInteractionRank() {
            return this.mInteractionRank;
        }

        public SortKeysImpl setInteractionRank(String value) {
            this.mInteractionRank = value;
            return this;
        }

        public SortKeysImpl clearInteractionRank() {
            return setInteractionRank(null);
        }

        public boolean hasName() {
            return this.mName != null;
        }

        public String getName() {
            return this.mName;
        }

        public SortKeysImpl setName(String value) {
            this.mName = value;
            return this;
        }

        public SortKeysImpl clearName() {
            return setName(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            SortKeysImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "TaglinesImplCreator")
    @VisibleForTesting
    public static class TaglinesImpl implements SafeParcelable, Taglines {
        public static final TaglinesImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 3)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new TaglinesImplCreator();
        }

        public TaglinesImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        TaglinesImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mValue = value;
        }

        public TaglinesImpl(TaglinesBase other) {
            this();
            importData(other);
        }

        public TaglinesImpl importData(TaglinesBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public TaglinesImpl mergeData(TaglinesBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public TaglinesImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public TaglinesImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public TaglinesImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public TaglinesImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            TaglinesImplCreator.writeToParcel(this, out, flags);
        }
    }

    @Class(creator = "UrlsImplCreator")
    @VisibleForTesting
    public static class UrlsImpl implements SafeParcelable, Urls {
        public static final UrlsImplCreator CREATOR;
        private static final int VERSION_CODE = 1;
        @Field(id = 3)
        String mFormattedType;
        @Indicator
        final Set<Integer> mIndicatorSet;
        @Field(id = 2)
        MetadataImpl mMetadata;
        @Field(id = 4)
        String mType;
        @Field(id = 5)
        String mValue;
        @VersionField(id = 1)
        final int mVersionCode;

        static {
            CREATOR = new UrlsImplCreator();
        }

        public UrlsImpl() {
            this.mIndicatorSet = new HashSet();
            this.mVersionCode = VERSION_CODE;
        }

        @Constructor
        UrlsImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) MetadataImpl metadata, @Param(id = 3) String formattedType, @Param(id = 4) String type, @Param(id = 5) String value) {
            this.mIndicatorSet = indicatorSet;
            this.mVersionCode = versionCode;
            this.mMetadata = metadata;
            this.mFormattedType = formattedType;
            this.mType = type;
            this.mValue = value;
        }

        public UrlsImpl(UrlsBase other) {
            this();
            importData(other);
        }

        public UrlsImpl importData(UrlsBase other) {
            clearMetadata();
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            clearFormattedType();
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            clearType();
            if (other.hasType()) {
                setType(other.getType());
            }
            clearValue();
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public UrlsImpl mergeData(UrlsBase other) {
            if (other.hasMetadata()) {
                setMetadata(new MetadataImpl(other.getMetadata()));
            }
            if (other.hasFormattedType()) {
                setFormattedType(other.getFormattedType());
            }
            if (other.hasType()) {
                setType(other.getType());
            }
            if (other.hasValue()) {
                setValue(other.getValue());
            }
            return this;
        }

        public boolean hasMetadata() {
            return this.mMetadata != null;
        }

        public MetadataImpl getMetadata() {
            return this.mMetadata;
        }

        public UrlsImpl setMetadata(MetadataImpl value) {
            this.mMetadata = value;
            return this;
        }

        public UrlsImpl clearMetadata() {
            return setMetadata(null);
        }

        public boolean hasFormattedType() {
            return this.mFormattedType != null;
        }

        public String getFormattedType() {
            return this.mFormattedType;
        }

        public UrlsImpl setFormattedType(String value) {
            this.mFormattedType = value;
            return this;
        }

        public UrlsImpl clearFormattedType() {
            return setFormattedType(null);
        }

        public boolean hasType() {
            return this.mType != null;
        }

        public String getType() {
            return this.mType;
        }

        public UrlsImpl setType(String value) {
            this.mType = value;
            return this;
        }

        public UrlsImpl clearType() {
            return setType(null);
        }

        public boolean hasValue() {
            return this.mValue != null;
        }

        public String getValue() {
            return this.mValue;
        }

        public UrlsImpl setValue(String value) {
            this.mValue = value;
            return this;
        }

        public UrlsImpl clearValue() {
            return setValue(null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            UrlsImplCreator.writeToParcel(this, out, flags);
        }
    }

    static {
        CREATOR = new PersonImplCreator();
    }

    public PersonImpl() {
        this.mIndicatorSet = new HashSet();
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    PersonImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) List<AboutsImpl> abouts, @Param(id = 3) List<AddressesImpl> addresses, @Param(id = 4) String ageRange, @Param(id = 5) List<BirthdaysImpl> birthdays, @Param(id = 6) List<BraggingRightsImpl> braggingRights, @Param(id = 7) List<CoverPhotosImpl> coverPhotos, @Param(id = 8) List<CustomFieldsImpl> customFields, @Param(id = 9) List<EmailsImpl> emails, @Param(id = 10) String etag, @Param(id = 11) List<EventsImpl> events, @Param(id = 12) List<GendersImpl> genders, @Param(id = 13) String id, @Param(id = 14) List<ImagesImpl> images, @Param(id = 15) List<InstantMessagingImpl> instantMessaging, @Param(id = 16) String language, @Param(id = 17) LegacyFieldsImpl legacyFields, @Param(id = 18) List<PersonImpl> linkedPeople, @Param(id = 19) List<MembershipsImpl> memberships, @Param(id = 20) PersonMetadataImpl metadata, @Param(id = 21) List<NamesImpl> names, @Param(id = 22) List<NicknamesImpl> nicknames, @Param(id = 23) List<OccupationsImpl> occupations, @Param(id = 24) List<OrganizationsImpl> organizations, @Param(id = 25) List<PhoneNumbersImpl> phoneNumbers, @Param(id = 26) List<PlacesLivedImpl> placesLived, @Param(id = 27) String profileUrl, @Param(id = 28) List<RelationsImpl> relations, @Param(id = 29) List<RelationshipInterestsImpl> relationshipInterests, @Param(id = 30) List<RelationshipStatusesImpl> relationshipStatuses, @Param(id = 31) List<SkillsImpl> skills, @Param(id = 32) SortKeysImpl sortKeys, @Param(id = 33) List<TaglinesImpl> taglines, @Param(id = 34) List<UrlsImpl> urls, @Param(id = 35) List<NotesImpl> notes) {
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
        this.mNotes = notes;
    }

    public PersonImpl(PersonBase other) {
        this();
        importData(other);
    }

    public PersonImpl importData(PersonBase other) {
        clearAbouts();
        if (other.hasAbouts()) {
            for (AboutsBase item : other.getAbouts()) {
                addAbouts(new AboutsImpl(item));
            }
        }
        clearAddresses();
        if (other.hasAddresses()) {
            for (AddressesBase item2 : other.getAddresses()) {
                addAddresses(new AddressesImpl(item2));
            }
        }
        clearAgeRange();
        if (other.hasAgeRange()) {
            setAgeRange(other.getAgeRange());
        }
        clearBirthdays();
        if (other.hasBirthdays()) {
            for (BirthdaysBase item3 : other.getBirthdays()) {
                addBirthdays(new BirthdaysImpl(item3));
            }
        }
        clearBraggingRights();
        if (other.hasBraggingRights()) {
            for (BraggingRightsBase item4 : other.getBraggingRights()) {
                addBraggingRights(new BraggingRightsImpl(item4));
            }
        }
        clearCoverPhotos();
        if (other.hasCoverPhotos()) {
            for (CoverPhotosBase item5 : other.getCoverPhotos()) {
                addCoverPhotos(new CoverPhotosImpl(item5));
            }
        }
        clearCustomFields();
        if (other.hasCustomFields()) {
            for (CustomFieldsBase item6 : other.getCustomFields()) {
                addCustomFields(new CustomFieldsImpl(item6));
            }
        }
        clearEmails();
        if (other.hasEmails()) {
            for (EmailsBase item7 : other.getEmails()) {
                addEmails(new EmailsImpl(item7));
            }
        }
        clearEtag();
        if (other.hasEtag()) {
            setEtag(other.getEtag());
        }
        clearEvents();
        if (other.hasEvents()) {
            for (EventsBase item8 : other.getEvents()) {
                addEvents(new EventsImpl(item8));
            }
        }
        clearGenders();
        if (other.hasGenders()) {
            for (GendersBase item9 : other.getGenders()) {
                addGenders(new GendersImpl(item9));
            }
        }
        clearId();
        if (other.hasId()) {
            setId(other.getId());
        }
        clearImages();
        if (other.hasImages()) {
            for (ImagesBase item10 : other.getImages()) {
                addImages(new ImagesImpl(item10));
            }
        }
        clearInstantMessaging();
        if (other.hasInstantMessaging()) {
            for (InstantMessagingBase item11 : other.getInstantMessaging()) {
                addInstantMessaging(new InstantMessagingImpl(item11));
            }
        }
        clearLanguage();
        if (other.hasLanguage()) {
            setLanguage(other.getLanguage());
        }
        clearLegacyFields();
        if (other.hasLegacyFields()) {
            setLegacyFields(new LegacyFieldsImpl(other.getLegacyFields()));
        }
        clearLinkedPeople();
        if (other.hasLinkedPeople()) {
            for (PersonBase item12 : other.getLinkedPeople()) {
                addLinkedPeople(new PersonImpl(item12));
            }
        }
        clearMemberships();
        if (other.hasMemberships()) {
            for (MembershipsBase item13 : other.getMemberships()) {
                addMemberships(new MembershipsImpl(item13));
            }
        }
        clearMetadata();
        if (other.hasMetadata()) {
            setMetadata(new PersonMetadataImpl(other.getMetadata()));
        }
        clearNames();
        if (other.hasNames()) {
            for (NamesBase item14 : other.getNames()) {
                addNames(new NamesImpl(item14));
            }
        }
        clearNicknames();
        if (other.hasNicknames()) {
            for (NicknamesBase item15 : other.getNicknames()) {
                addNicknames(new NicknamesImpl(item15));
            }
        }
        clearOccupations();
        if (other.hasOccupations()) {
            for (OccupationsBase item16 : other.getOccupations()) {
                addOccupations(new OccupationsImpl(item16));
            }
        }
        clearOrganizations();
        if (other.hasOrganizations()) {
            for (OrganizationsBase item17 : other.getOrganizations()) {
                addOrganizations(new OrganizationsImpl(item17));
            }
        }
        clearPhoneNumbers();
        if (other.hasPhoneNumbers()) {
            for (PhoneNumbersBase item18 : other.getPhoneNumbers()) {
                addPhoneNumbers(new PhoneNumbersImpl(item18));
            }
        }
        clearPlacesLived();
        if (other.hasPlacesLived()) {
            for (PlacesLivedBase item19 : other.getPlacesLived()) {
                addPlacesLived(new PlacesLivedImpl(item19));
            }
        }
        clearProfileUrl();
        if (other.hasProfileUrl()) {
            setProfileUrl(other.getProfileUrl());
        }
        clearRelations();
        if (other.hasRelations()) {
            for (RelationsBase item20 : other.getRelations()) {
                addRelations(new RelationsImpl(item20));
            }
        }
        clearRelationshipInterests();
        if (other.hasRelationshipInterests()) {
            for (RelationshipInterestsBase item21 : other.getRelationshipInterests()) {
                addRelationshipInterests(new RelationshipInterestsImpl(item21));
            }
        }
        clearRelationshipStatuses();
        if (other.hasRelationshipStatuses()) {
            for (RelationshipStatusesBase item22 : other.getRelationshipStatuses()) {
                addRelationshipStatuses(new RelationshipStatusesImpl(item22));
            }
        }
        clearSkills();
        if (other.hasSkills()) {
            for (SkillsBase item23 : other.getSkills()) {
                addSkills(new SkillsImpl(item23));
            }
        }
        clearSortKeys();
        if (other.hasSortKeys()) {
            setSortKeys(new SortKeysImpl(other.getSortKeys()));
        }
        clearTaglines();
        if (other.hasTaglines()) {
            for (TaglinesBase item24 : other.getTaglines()) {
                addTaglines(new TaglinesImpl(item24));
            }
        }
        clearUrls();
        if (other.hasUrls()) {
            for (UrlsBase item25 : other.getUrls()) {
                addUrls(new UrlsImpl(item25));
            }
        }
        clearNotes();
        if (other.hasNotes()) {
            for (NotesBase item26 : other.getNotes()) {
                addNotes(new NotesImpl(item26));
            }
        }
        return this;
    }

    public PersonImpl mergeData(PersonBase other) {
        if (other.hasAbouts()) {
            for (AboutsBase item : other.getAbouts()) {
                addAbouts(new AboutsImpl(item));
            }
        }
        if (other.hasAddresses()) {
            for (AddressesBase item2 : other.getAddresses()) {
                addAddresses(new AddressesImpl(item2));
            }
        }
        if (other.hasAgeRange()) {
            setAgeRange(other.getAgeRange());
        }
        if (other.hasBirthdays()) {
            for (BirthdaysBase item3 : other.getBirthdays()) {
                addBirthdays(new BirthdaysImpl(item3));
            }
        }
        if (other.hasBraggingRights()) {
            for (BraggingRightsBase item4 : other.getBraggingRights()) {
                addBraggingRights(new BraggingRightsImpl(item4));
            }
        }
        if (other.hasCoverPhotos()) {
            for (CoverPhotosBase item5 : other.getCoverPhotos()) {
                addCoverPhotos(new CoverPhotosImpl(item5));
            }
        }
        if (other.hasCustomFields()) {
            for (CustomFieldsBase item6 : other.getCustomFields()) {
                addCustomFields(new CustomFieldsImpl(item6));
            }
        }
        if (other.hasEmails()) {
            for (EmailsBase item7 : other.getEmails()) {
                addEmails(new EmailsImpl(item7));
            }
        }
        if (other.hasEtag()) {
            setEtag(other.getEtag());
        }
        if (other.hasEvents()) {
            for (EventsBase item8 : other.getEvents()) {
                addEvents(new EventsImpl(item8));
            }
        }
        if (other.hasGenders()) {
            for (GendersBase item9 : other.getGenders()) {
                addGenders(new GendersImpl(item9));
            }
        }
        if (other.hasId()) {
            setId(other.getId());
        }
        if (other.hasImages()) {
            for (ImagesBase item10 : other.getImages()) {
                addImages(new ImagesImpl(item10));
            }
        }
        if (other.hasInstantMessaging()) {
            for (InstantMessagingBase item11 : other.getInstantMessaging()) {
                addInstantMessaging(new InstantMessagingImpl(item11));
            }
        }
        if (other.hasLanguage()) {
            setLanguage(other.getLanguage());
        }
        if (other.hasLegacyFields()) {
            setLegacyFields(new LegacyFieldsImpl(other.getLegacyFields()));
        }
        if (other.hasLinkedPeople()) {
            for (PersonBase item12 : other.getLinkedPeople()) {
                addLinkedPeople(new PersonImpl(item12));
            }
        }
        if (other.hasMemberships()) {
            for (MembershipsBase item13 : other.getMemberships()) {
                addMemberships(new MembershipsImpl(item13));
            }
        }
        if (other.hasMetadata()) {
            setMetadata(new PersonMetadataImpl(other.getMetadata()));
        }
        if (other.hasNames()) {
            for (NamesBase item14 : other.getNames()) {
                addNames(new NamesImpl(item14));
            }
        }
        if (other.hasNicknames()) {
            for (NicknamesBase item15 : other.getNicknames()) {
                addNicknames(new NicknamesImpl(item15));
            }
        }
        if (other.hasOccupations()) {
            for (OccupationsBase item16 : other.getOccupations()) {
                addOccupations(new OccupationsImpl(item16));
            }
        }
        if (other.hasOrganizations()) {
            for (OrganizationsBase item17 : other.getOrganizations()) {
                addOrganizations(new OrganizationsImpl(item17));
            }
        }
        if (other.hasPhoneNumbers()) {
            for (PhoneNumbersBase item18 : other.getPhoneNumbers()) {
                addPhoneNumbers(new PhoneNumbersImpl(item18));
            }
        }
        if (other.hasPlacesLived()) {
            for (PlacesLivedBase item19 : other.getPlacesLived()) {
                addPlacesLived(new PlacesLivedImpl(item19));
            }
        }
        if (other.hasProfileUrl()) {
            setProfileUrl(other.getProfileUrl());
        }
        if (other.hasRelations()) {
            for (RelationsBase item20 : other.getRelations()) {
                addRelations(new RelationsImpl(item20));
            }
        }
        if (other.hasRelationshipInterests()) {
            for (RelationshipInterestsBase item21 : other.getRelationshipInterests()) {
                addRelationshipInterests(new RelationshipInterestsImpl(item21));
            }
        }
        if (other.hasRelationshipStatuses()) {
            for (RelationshipStatusesBase item22 : other.getRelationshipStatuses()) {
                addRelationshipStatuses(new RelationshipStatusesImpl(item22));
            }
        }
        if (other.hasSkills()) {
            for (SkillsBase item23 : other.getSkills()) {
                addSkills(new SkillsImpl(item23));
            }
        }
        if (other.hasSortKeys()) {
            setSortKeys(new SortKeysImpl(other.getSortKeys()));
        }
        if (other.hasTaglines()) {
            for (TaglinesBase item24 : other.getTaglines()) {
                addTaglines(new TaglinesImpl(item24));
            }
        }
        if (other.hasUrls()) {
            for (UrlsBase item25 : other.getUrls()) {
                addUrls(new UrlsImpl(item25));
            }
        }
        if (other.hasNotes()) {
            for (NotesBase item26 : other.getNotes()) {
                addNotes(new NotesImpl(item26));
            }
        }
        return this;
    }

    public boolean hasAbouts() {
        return this.mAbouts != null;
    }

    public List<AboutsImpl> getAbouts() {
        return this.mAbouts;
    }

    public PersonImpl setAbouts(List<AboutsImpl> value) {
        this.mAbouts = value;
        return this;
    }

    private List<AboutsImpl> createAbouts() {
        if (this.mAbouts == null) {
            this.mAbouts = new ArrayList();
        }
        return this.mAbouts;
    }

    public PersonImpl addAbouts(AboutsImpl value) {
        createAbouts().add(value);
        return this;
    }

    public PersonImpl addAllAbouts(Collection<AboutsImpl> values) {
        createAbouts().addAll(values);
        return this;
    }

    public PersonImpl removeAbouts(AboutsImpl value) {
        if (this.mAbouts != null) {
            this.mAbouts.remove(value);
        }
        return this;
    }

    public PersonImpl clearAbouts() {
        this.mAbouts = null;
        return this;
    }

    public boolean hasAddresses() {
        return this.mAddresses != null;
    }

    public List<AddressesImpl> getAddresses() {
        return this.mAddresses;
    }

    public PersonImpl setAddresses(List<AddressesImpl> value) {
        this.mAddresses = value;
        return this;
    }

    private List<AddressesImpl> createAddresses() {
        if (this.mAddresses == null) {
            this.mAddresses = new ArrayList();
        }
        return this.mAddresses;
    }

    public PersonImpl addAddresses(AddressesImpl value) {
        createAddresses().add(value);
        return this;
    }

    public PersonImpl addAllAddresses(Collection<AddressesImpl> values) {
        createAddresses().addAll(values);
        return this;
    }

    public PersonImpl removeAddresses(AddressesImpl value) {
        if (this.mAddresses != null) {
            this.mAddresses.remove(value);
        }
        return this;
    }

    public PersonImpl clearAddresses() {
        this.mAddresses = null;
        return this;
    }

    public boolean hasAgeRange() {
        return this.mAgeRange != null;
    }

    public String getAgeRange() {
        return this.mAgeRange;
    }

    public PersonImpl setAgeRange(String value) {
        this.mAgeRange = value;
        return this;
    }

    public PersonImpl clearAgeRange() {
        return setAgeRange(null);
    }

    public boolean hasBirthdays() {
        return this.mBirthdays != null;
    }

    public List<BirthdaysImpl> getBirthdays() {
        return this.mBirthdays;
    }

    public PersonImpl setBirthdays(List<BirthdaysImpl> value) {
        this.mBirthdays = value;
        return this;
    }

    private List<BirthdaysImpl> createBirthdays() {
        if (this.mBirthdays == null) {
            this.mBirthdays = new ArrayList();
        }
        return this.mBirthdays;
    }

    public PersonImpl addBirthdays(BirthdaysImpl value) {
        createBirthdays().add(value);
        return this;
    }

    public PersonImpl addAllBirthdays(Collection<BirthdaysImpl> values) {
        createBirthdays().addAll(values);
        return this;
    }

    public PersonImpl removeBirthdays(BirthdaysImpl value) {
        if (this.mBirthdays != null) {
            this.mBirthdays.remove(value);
        }
        return this;
    }

    public PersonImpl clearBirthdays() {
        this.mBirthdays = null;
        return this;
    }

    public boolean hasBraggingRights() {
        return this.mBraggingRights != null;
    }

    public List<BraggingRightsImpl> getBraggingRights() {
        return this.mBraggingRights;
    }

    public PersonImpl setBraggingRights(List<BraggingRightsImpl> value) {
        this.mBraggingRights = value;
        return this;
    }

    private List<BraggingRightsImpl> createBraggingRights() {
        if (this.mBraggingRights == null) {
            this.mBraggingRights = new ArrayList();
        }
        return this.mBraggingRights;
    }

    public PersonImpl addBraggingRights(BraggingRightsImpl value) {
        createBraggingRights().add(value);
        return this;
    }

    public PersonImpl addAllBraggingRights(Collection<BraggingRightsImpl> values) {
        createBraggingRights().addAll(values);
        return this;
    }

    public PersonImpl removeBraggingRights(BraggingRightsImpl value) {
        if (this.mBraggingRights != null) {
            this.mBraggingRights.remove(value);
        }
        return this;
    }

    public PersonImpl clearBraggingRights() {
        this.mBraggingRights = null;
        return this;
    }

    public boolean hasCoverPhotos() {
        return this.mCoverPhotos != null;
    }

    public List<CoverPhotosImpl> getCoverPhotos() {
        return this.mCoverPhotos;
    }

    public PersonImpl setCoverPhotos(List<CoverPhotosImpl> value) {
        this.mCoverPhotos = value;
        return this;
    }

    private List<CoverPhotosImpl> createCoverPhotos() {
        if (this.mCoverPhotos == null) {
            this.mCoverPhotos = new ArrayList();
        }
        return this.mCoverPhotos;
    }

    public PersonImpl addCoverPhotos(CoverPhotosImpl value) {
        createCoverPhotos().add(value);
        return this;
    }

    public PersonImpl addAllCoverPhotos(Collection<CoverPhotosImpl> values) {
        createCoverPhotos().addAll(values);
        return this;
    }

    public PersonImpl removeCoverPhotos(CoverPhotosImpl value) {
        if (this.mCoverPhotos != null) {
            this.mCoverPhotos.remove(value);
        }
        return this;
    }

    public PersonImpl clearCoverPhotos() {
        this.mCoverPhotos = null;
        return this;
    }

    public boolean hasCustomFields() {
        return this.mCustomFields != null;
    }

    public List<CustomFieldsImpl> getCustomFields() {
        return this.mCustomFields;
    }

    public PersonImpl setCustomFields(List<CustomFieldsImpl> value) {
        this.mCustomFields = value;
        return this;
    }

    private List<CustomFieldsImpl> createCustomFields() {
        if (this.mCustomFields == null) {
            this.mCustomFields = new ArrayList();
        }
        return this.mCustomFields;
    }

    public PersonImpl addCustomFields(CustomFieldsImpl value) {
        createCustomFields().add(value);
        return this;
    }

    public PersonImpl addAllCustomFields(Collection<CustomFieldsImpl> values) {
        createCustomFields().addAll(values);
        return this;
    }

    public PersonImpl removeCustomFields(CustomFieldsImpl value) {
        if (this.mCustomFields != null) {
            this.mCustomFields.remove(value);
        }
        return this;
    }

    public PersonImpl clearCustomFields() {
        this.mCustomFields = null;
        return this;
    }

    public boolean hasEmails() {
        return this.mEmails != null;
    }

    public List<EmailsImpl> getEmails() {
        return this.mEmails;
    }

    public PersonImpl setEmails(List<EmailsImpl> value) {
        this.mEmails = value;
        return this;
    }

    private List<EmailsImpl> createEmails() {
        if (this.mEmails == null) {
            this.mEmails = new ArrayList();
        }
        return this.mEmails;
    }

    public PersonImpl addEmails(EmailsImpl value) {
        createEmails().add(value);
        return this;
    }

    public PersonImpl addAllEmails(Collection<EmailsImpl> values) {
        createEmails().addAll(values);
        return this;
    }

    public PersonImpl removeEmails(EmailsImpl value) {
        if (this.mEmails != null) {
            this.mEmails.remove(value);
        }
        return this;
    }

    public PersonImpl clearEmails() {
        this.mEmails = null;
        return this;
    }

    public boolean hasEtag() {
        return this.mEtag != null;
    }

    public String getEtag() {
        return this.mEtag;
    }

    public PersonImpl setEtag(String value) {
        this.mEtag = value;
        return this;
    }

    public PersonImpl clearEtag() {
        return setEtag(null);
    }

    public boolean hasEvents() {
        return this.mEvents != null;
    }

    public List<EventsImpl> getEvents() {
        return this.mEvents;
    }

    public PersonImpl setEvents(List<EventsImpl> value) {
        this.mEvents = value;
        return this;
    }

    private List<EventsImpl> createEvents() {
        if (this.mEvents == null) {
            this.mEvents = new ArrayList();
        }
        return this.mEvents;
    }

    public PersonImpl addEvents(EventsImpl value) {
        createEvents().add(value);
        return this;
    }

    public PersonImpl addAllEvents(Collection<EventsImpl> values) {
        createEvents().addAll(values);
        return this;
    }

    public PersonImpl removeEvents(EventsImpl value) {
        if (this.mEvents != null) {
            this.mEvents.remove(value);
        }
        return this;
    }

    public PersonImpl clearEvents() {
        this.mEvents = null;
        return this;
    }

    public boolean hasGenders() {
        return this.mGenders != null;
    }

    public List<GendersImpl> getGenders() {
        return this.mGenders;
    }

    public PersonImpl setGenders(List<GendersImpl> value) {
        this.mGenders = value;
        return this;
    }

    private List<GendersImpl> createGenders() {
        if (this.mGenders == null) {
            this.mGenders = new ArrayList();
        }
        return this.mGenders;
    }

    public PersonImpl addGenders(GendersImpl value) {
        createGenders().add(value);
        return this;
    }

    public PersonImpl addAllGenders(Collection<GendersImpl> values) {
        createGenders().addAll(values);
        return this;
    }

    public PersonImpl removeGenders(GendersImpl value) {
        if (this.mGenders != null) {
            this.mGenders.remove(value);
        }
        return this;
    }

    public PersonImpl clearGenders() {
        this.mGenders = null;
        return this;
    }

    public boolean hasId() {
        return this.mId != null;
    }

    public String getId() {
        return this.mId;
    }

    public PersonImpl setId(String value) {
        this.mId = value;
        return this;
    }

    public PersonImpl clearId() {
        return setId(null);
    }

    public boolean hasImages() {
        return this.mImages != null;
    }

    public List<ImagesImpl> getImages() {
        return this.mImages;
    }

    public PersonImpl setImages(List<ImagesImpl> value) {
        this.mImages = value;
        return this;
    }

    private List<ImagesImpl> createImages() {
        if (this.mImages == null) {
            this.mImages = new ArrayList();
        }
        return this.mImages;
    }

    public PersonImpl addImages(ImagesImpl value) {
        createImages().add(value);
        return this;
    }

    public PersonImpl addAllImages(Collection<ImagesImpl> values) {
        createImages().addAll(values);
        return this;
    }

    public PersonImpl removeImages(ImagesImpl value) {
        if (this.mImages != null) {
            this.mImages.remove(value);
        }
        return this;
    }

    public PersonImpl clearImages() {
        this.mImages = null;
        return this;
    }

    public boolean hasInstantMessaging() {
        return this.mInstantMessaging != null;
    }

    public List<InstantMessagingImpl> getInstantMessaging() {
        return this.mInstantMessaging;
    }

    public PersonImpl setInstantMessaging(List<InstantMessagingImpl> value) {
        this.mInstantMessaging = value;
        return this;
    }

    private List<InstantMessagingImpl> createInstantMessaging() {
        if (this.mInstantMessaging == null) {
            this.mInstantMessaging = new ArrayList();
        }
        return this.mInstantMessaging;
    }

    public PersonImpl addInstantMessaging(InstantMessagingImpl value) {
        createInstantMessaging().add(value);
        return this;
    }

    public PersonImpl addAllInstantMessaging(Collection<InstantMessagingImpl> values) {
        createInstantMessaging().addAll(values);
        return this;
    }

    public PersonImpl removeInstantMessaging(InstantMessagingImpl value) {
        if (this.mInstantMessaging != null) {
            this.mInstantMessaging.remove(value);
        }
        return this;
    }

    public PersonImpl clearInstantMessaging() {
        this.mInstantMessaging = null;
        return this;
    }

    public boolean hasLanguage() {
        return this.mLanguage != null;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public PersonImpl setLanguage(String value) {
        this.mLanguage = value;
        return this;
    }

    public PersonImpl clearLanguage() {
        return setLanguage(null);
    }

    public boolean hasLegacyFields() {
        return this.mLegacyFields != null;
    }

    public LegacyFieldsImpl getLegacyFields() {
        return this.mLegacyFields;
    }

    public PersonImpl setLegacyFields(LegacyFieldsImpl value) {
        this.mLegacyFields = value;
        return this;
    }

    public PersonImpl clearLegacyFields() {
        return setLegacyFields(null);
    }

    public boolean hasLinkedPeople() {
        return this.mLinkedPeople != null;
    }

    public List<PersonImpl> getLinkedPeople() {
        return this.mLinkedPeople;
    }

    public PersonImpl setLinkedPeople(List<PersonImpl> value) {
        this.mLinkedPeople = value;
        return this;
    }

    private List<PersonImpl> createLinkedPeople() {
        if (this.mLinkedPeople == null) {
            this.mLinkedPeople = new ArrayList();
        }
        return this.mLinkedPeople;
    }

    public PersonImpl addLinkedPeople(PersonImpl value) {
        createLinkedPeople().add(value);
        return this;
    }

    public PersonImpl addAllLinkedPeople(Collection<PersonImpl> values) {
        createLinkedPeople().addAll(values);
        return this;
    }

    public PersonImpl removeLinkedPeople(PersonImpl value) {
        if (this.mLinkedPeople != null) {
            this.mLinkedPeople.remove(value);
        }
        return this;
    }

    public PersonImpl clearLinkedPeople() {
        this.mLinkedPeople = null;
        return this;
    }

    public boolean hasMemberships() {
        return this.mMemberships != null;
    }

    public List<MembershipsImpl> getMemberships() {
        return this.mMemberships;
    }

    public PersonImpl setMemberships(List<MembershipsImpl> value) {
        this.mMemberships = value;
        return this;
    }

    private List<MembershipsImpl> createMemberships() {
        if (this.mMemberships == null) {
            this.mMemberships = new ArrayList();
        }
        return this.mMemberships;
    }

    public PersonImpl addMemberships(MembershipsImpl value) {
        createMemberships().add(value);
        return this;
    }

    public PersonImpl addAllMemberships(Collection<MembershipsImpl> values) {
        createMemberships().addAll(values);
        return this;
    }

    public PersonImpl removeMemberships(MembershipsImpl value) {
        if (this.mMemberships != null) {
            this.mMemberships.remove(value);
        }
        return this;
    }

    public PersonImpl clearMemberships() {
        this.mMemberships = null;
        return this;
    }

    public boolean hasMetadata() {
        return this.mMetadata != null;
    }

    public PersonMetadataImpl getMetadata() {
        return this.mMetadata;
    }

    public PersonImpl setMetadata(PersonMetadataImpl value) {
        this.mMetadata = value;
        return this;
    }

    public PersonImpl clearMetadata() {
        return setMetadata(null);
    }

    public boolean hasNames() {
        return this.mNames != null;
    }

    public List<NamesImpl> getNames() {
        return this.mNames;
    }

    public PersonImpl setNames(List<NamesImpl> value) {
        this.mNames = value;
        return this;
    }

    private List<NamesImpl> createNames() {
        if (this.mNames == null) {
            this.mNames = new ArrayList();
        }
        return this.mNames;
    }

    public PersonImpl addNames(NamesImpl value) {
        createNames().add(value);
        return this;
    }

    public PersonImpl addAllNames(Collection<NamesImpl> values) {
        createNames().addAll(values);
        return this;
    }

    public PersonImpl removeNames(NamesImpl value) {
        if (this.mNames != null) {
            this.mNames.remove(value);
        }
        return this;
    }

    public PersonImpl clearNames() {
        this.mNames = null;
        return this;
    }

    public boolean hasNicknames() {
        return this.mNicknames != null;
    }

    public List<NicknamesImpl> getNicknames() {
        return this.mNicknames;
    }

    public PersonImpl setNicknames(List<NicknamesImpl> value) {
        this.mNicknames = value;
        return this;
    }

    private List<NicknamesImpl> createNicknames() {
        if (this.mNicknames == null) {
            this.mNicknames = new ArrayList();
        }
        return this.mNicknames;
    }

    public PersonImpl addNicknames(NicknamesImpl value) {
        createNicknames().add(value);
        return this;
    }

    public PersonImpl addAllNicknames(Collection<NicknamesImpl> values) {
        createNicknames().addAll(values);
        return this;
    }

    public PersonImpl removeNicknames(NicknamesImpl value) {
        if (this.mNicknames != null) {
            this.mNicknames.remove(value);
        }
        return this;
    }

    public PersonImpl clearNicknames() {
        this.mNicknames = null;
        return this;
    }

    public boolean hasOccupations() {
        return this.mOccupations != null;
    }

    public List<OccupationsImpl> getOccupations() {
        return this.mOccupations;
    }

    public PersonImpl setOccupations(List<OccupationsImpl> value) {
        this.mOccupations = value;
        return this;
    }

    private List<OccupationsImpl> createOccupations() {
        if (this.mOccupations == null) {
            this.mOccupations = new ArrayList();
        }
        return this.mOccupations;
    }

    public PersonImpl addOccupations(OccupationsImpl value) {
        createOccupations().add(value);
        return this;
    }

    public PersonImpl addAllOccupations(Collection<OccupationsImpl> values) {
        createOccupations().addAll(values);
        return this;
    }

    public PersonImpl removeOccupations(OccupationsImpl value) {
        if (this.mOccupations != null) {
            this.mOccupations.remove(value);
        }
        return this;
    }

    public PersonImpl clearOccupations() {
        this.mOccupations = null;
        return this;
    }

    public boolean hasOrganizations() {
        return this.mOrganizations != null;
    }

    public List<OrganizationsImpl> getOrganizations() {
        return this.mOrganizations;
    }

    public PersonImpl setOrganizations(List<OrganizationsImpl> value) {
        this.mOrganizations = value;
        return this;
    }

    private List<OrganizationsImpl> createOrganizations() {
        if (this.mOrganizations == null) {
            this.mOrganizations = new ArrayList();
        }
        return this.mOrganizations;
    }

    public PersonImpl addOrganizations(OrganizationsImpl value) {
        createOrganizations().add(value);
        return this;
    }

    public PersonImpl addAllOrganizations(Collection<OrganizationsImpl> values) {
        createOrganizations().addAll(values);
        return this;
    }

    public PersonImpl removeOrganizations(OrganizationsImpl value) {
        if (this.mOrganizations != null) {
            this.mOrganizations.remove(value);
        }
        return this;
    }

    public PersonImpl clearOrganizations() {
        this.mOrganizations = null;
        return this;
    }

    public boolean hasPhoneNumbers() {
        return this.mPhoneNumbers != null;
    }

    public List<PhoneNumbersImpl> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    public PersonImpl setPhoneNumbers(List<PhoneNumbersImpl> value) {
        this.mPhoneNumbers = value;
        return this;
    }

    private List<PhoneNumbersImpl> createPhoneNumbers() {
        if (this.mPhoneNumbers == null) {
            this.mPhoneNumbers = new ArrayList();
        }
        return this.mPhoneNumbers;
    }

    public PersonImpl addPhoneNumbers(PhoneNumbersImpl value) {
        createPhoneNumbers().add(value);
        return this;
    }

    public PersonImpl addAllPhoneNumbers(Collection<PhoneNumbersImpl> values) {
        createPhoneNumbers().addAll(values);
        return this;
    }

    public PersonImpl removePhoneNumbers(PhoneNumbersImpl value) {
        if (this.mPhoneNumbers != null) {
            this.mPhoneNumbers.remove(value);
        }
        return this;
    }

    public PersonImpl clearPhoneNumbers() {
        this.mPhoneNumbers = null;
        return this;
    }

    public boolean hasPlacesLived() {
        return this.mPlacesLived != null;
    }

    public List<PlacesLivedImpl> getPlacesLived() {
        return this.mPlacesLived;
    }

    public PersonImpl setPlacesLived(List<PlacesLivedImpl> value) {
        this.mPlacesLived = value;
        return this;
    }

    private List<PlacesLivedImpl> createPlacesLived() {
        if (this.mPlacesLived == null) {
            this.mPlacesLived = new ArrayList();
        }
        return this.mPlacesLived;
    }

    public PersonImpl addPlacesLived(PlacesLivedImpl value) {
        createPlacesLived().add(value);
        return this;
    }

    public PersonImpl addAllPlacesLived(Collection<PlacesLivedImpl> values) {
        createPlacesLived().addAll(values);
        return this;
    }

    public PersonImpl removePlacesLived(PlacesLivedImpl value) {
        if (this.mPlacesLived != null) {
            this.mPlacesLived.remove(value);
        }
        return this;
    }

    public PersonImpl clearPlacesLived() {
        this.mPlacesLived = null;
        return this;
    }

    public boolean hasProfileUrl() {
        return this.mProfileUrl != null;
    }

    public String getProfileUrl() {
        return this.mProfileUrl;
    }

    public PersonImpl setProfileUrl(String value) {
        this.mProfileUrl = value;
        return this;
    }

    public PersonImpl clearProfileUrl() {
        return setProfileUrl(null);
    }

    public boolean hasRelations() {
        return this.mRelations != null;
    }

    public List<RelationsImpl> getRelations() {
        return this.mRelations;
    }

    public PersonImpl setRelations(List<RelationsImpl> value) {
        this.mRelations = value;
        return this;
    }

    private List<RelationsImpl> createRelations() {
        if (this.mRelations == null) {
            this.mRelations = new ArrayList();
        }
        return this.mRelations;
    }

    public PersonImpl addRelations(RelationsImpl value) {
        createRelations().add(value);
        return this;
    }

    public PersonImpl addAllRelations(Collection<RelationsImpl> values) {
        createRelations().addAll(values);
        return this;
    }

    public PersonImpl removeRelations(RelationsImpl value) {
        if (this.mRelations != null) {
            this.mRelations.remove(value);
        }
        return this;
    }

    public PersonImpl clearRelations() {
        this.mRelations = null;
        return this;
    }

    public boolean hasRelationshipInterests() {
        return this.mRelationshipInterests != null;
    }

    public List<RelationshipInterestsImpl> getRelationshipInterests() {
        return this.mRelationshipInterests;
    }

    public PersonImpl setRelationshipInterests(List<RelationshipInterestsImpl> value) {
        this.mRelationshipInterests = value;
        return this;
    }

    private List<RelationshipInterestsImpl> createRelationshipInterests() {
        if (this.mRelationshipInterests == null) {
            this.mRelationshipInterests = new ArrayList();
        }
        return this.mRelationshipInterests;
    }

    public PersonImpl addRelationshipInterests(RelationshipInterestsImpl value) {
        createRelationshipInterests().add(value);
        return this;
    }

    public PersonImpl addAllRelationshipInterests(Collection<RelationshipInterestsImpl> values) {
        createRelationshipInterests().addAll(values);
        return this;
    }

    public PersonImpl removeRelationshipInterests(RelationshipInterestsImpl value) {
        if (this.mRelationshipInterests != null) {
            this.mRelationshipInterests.remove(value);
        }
        return this;
    }

    public PersonImpl clearRelationshipInterests() {
        this.mRelationshipInterests = null;
        return this;
    }

    public boolean hasRelationshipStatuses() {
        return this.mRelationshipStatuses != null;
    }

    public List<RelationshipStatusesImpl> getRelationshipStatuses() {
        return this.mRelationshipStatuses;
    }

    public PersonImpl setRelationshipStatuses(List<RelationshipStatusesImpl> value) {
        this.mRelationshipStatuses = value;
        return this;
    }

    private List<RelationshipStatusesImpl> createRelationshipStatuses() {
        if (this.mRelationshipStatuses == null) {
            this.mRelationshipStatuses = new ArrayList();
        }
        return this.mRelationshipStatuses;
    }

    public PersonImpl addRelationshipStatuses(RelationshipStatusesImpl value) {
        createRelationshipStatuses().add(value);
        return this;
    }

    public PersonImpl addAllRelationshipStatuses(Collection<RelationshipStatusesImpl> values) {
        createRelationshipStatuses().addAll(values);
        return this;
    }

    public PersonImpl removeRelationshipStatuses(RelationshipStatusesImpl value) {
        if (this.mRelationshipStatuses != null) {
            this.mRelationshipStatuses.remove(value);
        }
        return this;
    }

    public PersonImpl clearRelationshipStatuses() {
        this.mRelationshipStatuses = null;
        return this;
    }

    public boolean hasSkills() {
        return this.mSkills != null;
    }

    public List<SkillsImpl> getSkills() {
        return this.mSkills;
    }

    public PersonImpl setSkills(List<SkillsImpl> value) {
        this.mSkills = value;
        return this;
    }

    private List<SkillsImpl> createSkills() {
        if (this.mSkills == null) {
            this.mSkills = new ArrayList();
        }
        return this.mSkills;
    }

    public PersonImpl addSkills(SkillsImpl value) {
        createSkills().add(value);
        return this;
    }

    public PersonImpl addAllSkills(Collection<SkillsImpl> values) {
        createSkills().addAll(values);
        return this;
    }

    public PersonImpl removeSkills(SkillsImpl value) {
        if (this.mSkills != null) {
            this.mSkills.remove(value);
        }
        return this;
    }

    public PersonImpl clearSkills() {
        this.mSkills = null;
        return this;
    }

    public boolean hasSortKeys() {
        return this.mSortKeys != null;
    }

    public SortKeysImpl getSortKeys() {
        return this.mSortKeys;
    }

    public PersonImpl setSortKeys(SortKeysImpl value) {
        this.mSortKeys = value;
        return this;
    }

    public PersonImpl clearSortKeys() {
        return setSortKeys(null);
    }

    public boolean hasTaglines() {
        return this.mTaglines != null;
    }

    public List<TaglinesImpl> getTaglines() {
        return this.mTaglines;
    }

    public PersonImpl setTaglines(List<TaglinesImpl> value) {
        this.mTaglines = value;
        return this;
    }

    private List<TaglinesImpl> createTaglines() {
        if (this.mTaglines == null) {
            this.mTaglines = new ArrayList();
        }
        return this.mTaglines;
    }

    public PersonImpl addTaglines(TaglinesImpl value) {
        createTaglines().add(value);
        return this;
    }

    public PersonImpl addAllTaglines(Collection<TaglinesImpl> values) {
        createTaglines().addAll(values);
        return this;
    }

    public PersonImpl removeTaglines(TaglinesImpl value) {
        if (this.mTaglines != null) {
            this.mTaglines.remove(value);
        }
        return this;
    }

    public PersonImpl clearTaglines() {
        this.mTaglines = null;
        return this;
    }

    public boolean hasUrls() {
        return this.mUrls != null;
    }

    public List<UrlsImpl> getUrls() {
        return this.mUrls;
    }

    public PersonImpl setUrls(List<UrlsImpl> value) {
        this.mUrls = value;
        return this;
    }

    private List<UrlsImpl> createUrls() {
        if (this.mUrls == null) {
            this.mUrls = new ArrayList();
        }
        return this.mUrls;
    }

    public PersonImpl addUrls(UrlsImpl value) {
        createUrls().add(value);
        return this;
    }

    public PersonImpl addAllUrls(Collection<UrlsImpl> values) {
        createUrls().addAll(values);
        return this;
    }

    public PersonImpl removeUrls(UrlsImpl value) {
        if (this.mUrls != null) {
            this.mUrls.remove(value);
        }
        return this;
    }

    public PersonImpl clearUrls() {
        this.mUrls = null;
        return this;
    }

    public boolean hasNotes() {
        return this.mNotes != null;
    }

    public List<NotesImpl> getNotes() {
        return this.mNotes;
    }

    public PersonImpl setNotes(List<NotesImpl> value) {
        this.mNotes = value;
        return this;
    }

    private List<NotesImpl> createNotes() {
        if (this.mNotes == null) {
            this.mNotes = new ArrayList();
        }
        return this.mNotes;
    }

    public PersonImpl addNotes(NotesImpl value) {
        createNotes().add(value);
        return this;
    }

    public PersonImpl addAllNotes(Collection<NotesImpl> values) {
        createNotes().addAll(values);
        return this;
    }

    public PersonImpl removeNotes(NotesImpl value) {
        if (this.mNotes != null) {
            this.mNotes.remove(value);
        }
        return this;
    }

    public PersonImpl clearNotes() {
        this.mNotes = null;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        PersonImplCreator.writeToParcel(this, out, flags);
    }
}
