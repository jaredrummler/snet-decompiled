package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Abouts;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Addresses;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Birthdays;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.BraggingRights;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.CoverPhotos;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.CustomFields;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Emails;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Events;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Genders;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Images;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.InstantMessaging;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.LegacyFields;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Memberships;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Names;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Nicknames;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Occupations;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Organizations;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.PhoneNumbers;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.PlacesLived;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Relations;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.RelationshipInterests;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.RelationshipStatuses;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Skills;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.SortKeys;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Taglines;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Urls;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultPersonImplCreator implements Creator<DefaultPersonImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public DefaultPersonImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        List<Abouts> _local_safe_0a1b_mAbouts = null;
        List<Addresses> _local_safe_0a1b_mAddresses = null;
        String _local_safe_0a1b_mAgeRange = null;
        List<Birthdays> _local_safe_0a1b_mBirthdays = null;
        List<BraggingRights> _local_safe_0a1b_mBraggingRights = null;
        List<CoverPhotos> _local_safe_0a1b_mCoverPhotos = null;
        List<CustomFields> _local_safe_0a1b_mCustomFields = null;
        List<Emails> _local_safe_0a1b_mEmails = null;
        String _local_safe_0a1b_mEtag = null;
        List<Events> _local_safe_0a1b_mEvents = null;
        List<Genders> _local_safe_0a1b_mGenders = null;
        String _local_safe_0a1b_mId = null;
        List<Images> _local_safe_0a1b_mImages = null;
        List<InstantMessaging> _local_safe_0a1b_mInstantMessaging = null;
        String _local_safe_0a1b_mLanguage = null;
        LegacyFields _local_safe_0a1b_mLegacyFields = null;
        List<DefaultPersonImpl> _local_safe_0a1b_mLinkedPeople = null;
        List<Memberships> _local_safe_0a1b_mMemberships = null;
        Metadata _local_safe_0a1b_mMetadata = null;
        List<Names> _local_safe_0a1b_mNames = null;
        List<Nicknames> _local_safe_0a1b_mNicknames = null;
        List<Occupations> _local_safe_0a1b_mOccupations = null;
        List<Organizations> _local_safe_0a1b_mOrganizations = null;
        List<PhoneNumbers> _local_safe_0a1b_mPhoneNumbers = null;
        List<PlacesLived> _local_safe_0a1b_mPlacesLived = null;
        String _local_safe_0a1b_mProfileUrl = null;
        List<Relations> _local_safe_0a1b_mRelations = null;
        List<RelationshipInterests> _local_safe_0a1b_mRelationshipInterests = null;
        List<RelationshipStatuses> _local_safe_0a1b_mRelationshipStatuses = null;
        List<Skills> _local_safe_0a1b_mSkills = null;
        SortKeys _local_safe_0a1b_mSortKeys = null;
        List<Taglines> _local_safe_0a1b_mTaglines = null;
        List<Urls> _local_safe_0a1b_mUrls = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAbouts = SafeParcelReader.createTypedList(parcel, header, Abouts.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAddresses = SafeParcelReader.createTypedList(parcel, header, Addresses.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mAgeRange = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mBirthdays = SafeParcelReader.createTypedList(parcel, header, Birthdays.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mBraggingRights = SafeParcelReader.createTypedList(parcel, header, BraggingRights.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mCoverPhotos = SafeParcelReader.createTypedList(parcel, header, CoverPhotos.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mCustomFields = SafeParcelReader.createTypedList(parcel, header, CustomFields.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mEmails = SafeParcelReader.createTypedList(parcel, header, Emails.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mEtag = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mEvents = SafeParcelReader.createTypedList(parcel, header, Events.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(11));
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mGenders = SafeParcelReader.createTypedList(parcel, header, Genders.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(12));
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(13));
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_mImages = SafeParcelReader.createTypedList(parcel, header, Images.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(14));
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_mInstantMessaging = SafeParcelReader.createTypedList(parcel, header, InstantMessaging.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(15));
                    break;
                case LogSource.LE /*17*/:
                    _local_safe_0a1b_mLanguage = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(17));
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    _local_safe_0a1b_mLegacyFields = (LegacyFields) SafeParcelReader.createParcelable(parcel, header, LegacyFields.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(18));
                    break;
                case LogSource.LB_D /*19*/:
                    _local_safe_0a1b_mLinkedPeople = SafeParcelReader.createTypedList(parcel, header, DefaultPersonImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(19));
                    break;
                case LogSource.ANDROID_GSA /*20*/:
                    _local_safe_0a1b_mMemberships = SafeParcelReader.createTypedList(parcel, header, Memberships.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(20));
                    break;
                case LogSource.LB_T /*21*/:
                    _local_safe_0a1b_mMetadata = (Metadata) SafeParcelReader.createParcelable(parcel, header, Metadata.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(21));
                    break;
                case LogSource.PERSONAL_LOGGER /*22*/:
                    _local_safe_0a1b_mNames = SafeParcelReader.createTypedList(parcel, header, Names.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(22));
                    break;
                case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                    _local_safe_0a1b_mNicknames = SafeParcelReader.createTypedList(parcel, header, Nicknames.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(23));
                    break;
                case LogSource.LB_C /*24*/:
                    _local_safe_0a1b_mOccupations = SafeParcelReader.createTypedList(parcel, header, Occupations.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(24));
                    break;
                case LogSource.ANDROID_AUTH /*25*/:
                    _local_safe_0a1b_mOrganizations = SafeParcelReader.createTypedList(parcel, header, Organizations.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(25));
                    break;
                case LogSource.ANDROID_CAMERA /*26*/:
                    _local_safe_0a1b_mPhoneNumbers = SafeParcelReader.createTypedList(parcel, header, PhoneNumbers.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(26));
                    break;
                case LogSource.CW /*27*/:
                    _local_safe_0a1b_mPlacesLived = SafeParcelReader.createTypedList(parcel, header, PlacesLived.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(27));
                    break;
                case LogSource.GEL /*28*/:
                    _local_safe_0a1b_mProfileUrl = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(28));
                    break;
                case LogSource.DNA_PROBER /*29*/:
                    _local_safe_0a1b_mRelations = SafeParcelReader.createTypedList(parcel, header, Relations.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(29));
                    break;
                case LogSource.UDR /*30*/:
                    _local_safe_0a1b_mRelationshipInterests = SafeParcelReader.createTypedList(parcel, header, RelationshipInterests.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(30));
                    break;
                case LogSource.GMS_CORE_WALLET /*31*/:
                    _local_safe_0a1b_mRelationshipStatuses = SafeParcelReader.createTypedList(parcel, header, RelationshipStatuses.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(31));
                    break;
                case LogSource.SOCIAL /*32*/:
                    _local_safe_0a1b_mSkills = SafeParcelReader.createTypedList(parcel, header, Skills.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(32));
                    break;
                case LogSource.INSTORE_WALLET /*33*/:
                    _local_safe_0a1b_mSortKeys = (SortKeys) SafeParcelReader.createParcelable(parcel, header, SortKeys.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(33));
                    break;
                case LogSource.NOVA /*34*/:
                    _local_safe_0a1b_mTaglines = SafeParcelReader.createTypedList(parcel, header, Taglines.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(34));
                    break;
                case LogSource.LB_CA /*35*/:
                    _local_safe_0a1b_mUrls = SafeParcelReader.createTypedList(parcel, header, Urls.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(35));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new DefaultPersonImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAbouts, _local_safe_0a1b_mAddresses, _local_safe_0a1b_mAgeRange, _local_safe_0a1b_mBirthdays, _local_safe_0a1b_mBraggingRights, _local_safe_0a1b_mCoverPhotos, _local_safe_0a1b_mCustomFields, _local_safe_0a1b_mEmails, _local_safe_0a1b_mEtag, _local_safe_0a1b_mEvents, _local_safe_0a1b_mGenders, _local_safe_0a1b_mId, _local_safe_0a1b_mImages, _local_safe_0a1b_mInstantMessaging, _local_safe_0a1b_mLanguage, _local_safe_0a1b_mLegacyFields, _local_safe_0a1b_mLinkedPeople, _local_safe_0a1b_mMemberships, _local_safe_0a1b_mMetadata, _local_safe_0a1b_mNames, _local_safe_0a1b_mNicknames, _local_safe_0a1b_mOccupations, _local_safe_0a1b_mOrganizations, _local_safe_0a1b_mPhoneNumbers, _local_safe_0a1b_mPlacesLived, _local_safe_0a1b_mProfileUrl, _local_safe_0a1b_mRelations, _local_safe_0a1b_mRelationshipInterests, _local_safe_0a1b_mRelationshipStatuses, _local_safe_0a1b_mSkills, _local_safe_0a1b_mSortKeys, _local_safe_0a1b_mTaglines, _local_safe_0a1b_mUrls);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public DefaultPersonImpl[] newArray(int size) {
        return new DefaultPersonImpl[size];
    }

    static void writeToParcel(DefaultPersonImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeTypedList(parcel, 2, obj.mAbouts, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeTypedList(parcel, 3, obj.mAddresses, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeString(parcel, 4, obj.mAgeRange, true);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeTypedList(parcel, 5, obj.mBirthdays, true);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeTypedList(parcel, 6, obj.mBraggingRights, true);
        }
        if (__setFields.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeTypedList(parcel, 7, obj.mCoverPhotos, true);
        }
        if (__setFields.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeTypedList(parcel, 8, obj.mCustomFields, true);
        }
        if (__setFields.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeTypedList(parcel, 9, obj.mEmails, true);
        }
        if (__setFields.contains(Integer.valueOf(10))) {
            SafeParcelWriter.writeString(parcel, 10, obj.mEtag, true);
        }
        if (__setFields.contains(Integer.valueOf(11))) {
            SafeParcelWriter.writeTypedList(parcel, 11, obj.mEvents, true);
        }
        if (__setFields.contains(Integer.valueOf(12))) {
            SafeParcelWriter.writeTypedList(parcel, 12, obj.mGenders, true);
        }
        if (__setFields.contains(Integer.valueOf(13))) {
            SafeParcelWriter.writeString(parcel, 13, obj.mId, true);
        }
        if (__setFields.contains(Integer.valueOf(14))) {
            SafeParcelWriter.writeTypedList(parcel, 14, obj.mImages, true);
        }
        if (__setFields.contains(Integer.valueOf(15))) {
            SafeParcelWriter.writeTypedList(parcel, 15, obj.mInstantMessaging, true);
        }
        if (__setFields.contains(Integer.valueOf(17))) {
            SafeParcelWriter.writeString(parcel, 17, obj.mLanguage, true);
        }
        if (__setFields.contains(Integer.valueOf(19))) {
            SafeParcelWriter.writeTypedList(parcel, 19, obj.mLinkedPeople, true);
        }
        if (__setFields.contains(Integer.valueOf(18))) {
            SafeParcelWriter.writeParcelable(parcel, 18, obj.mLegacyFields, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(21))) {
            SafeParcelWriter.writeParcelable(parcel, 21, obj.mMetadata, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(20))) {
            SafeParcelWriter.writeTypedList(parcel, 20, obj.mMemberships, true);
        }
        if (__setFields.contains(Integer.valueOf(23))) {
            SafeParcelWriter.writeTypedList(parcel, 23, obj.mNicknames, true);
        }
        if (__setFields.contains(Integer.valueOf(22))) {
            SafeParcelWriter.writeTypedList(parcel, 22, obj.mNames, true);
        }
        if (__setFields.contains(Integer.valueOf(25))) {
            SafeParcelWriter.writeTypedList(parcel, 25, obj.mOrganizations, true);
        }
        if (__setFields.contains(Integer.valueOf(24))) {
            SafeParcelWriter.writeTypedList(parcel, 24, obj.mOccupations, true);
        }
        if (__setFields.contains(Integer.valueOf(27))) {
            SafeParcelWriter.writeTypedList(parcel, 27, obj.mPlacesLived, true);
        }
        if (__setFields.contains(Integer.valueOf(26))) {
            SafeParcelWriter.writeTypedList(parcel, 26, obj.mPhoneNumbers, true);
        }
        if (__setFields.contains(Integer.valueOf(29))) {
            SafeParcelWriter.writeTypedList(parcel, 29, obj.mRelations, true);
        }
        if (__setFields.contains(Integer.valueOf(28))) {
            SafeParcelWriter.writeString(parcel, 28, obj.mProfileUrl, true);
        }
        if (__setFields.contains(Integer.valueOf(31))) {
            SafeParcelWriter.writeTypedList(parcel, 31, obj.mRelationshipStatuses, true);
        }
        if (__setFields.contains(Integer.valueOf(30))) {
            SafeParcelWriter.writeTypedList(parcel, 30, obj.mRelationshipInterests, true);
        }
        if (__setFields.contains(Integer.valueOf(34))) {
            SafeParcelWriter.writeTypedList(parcel, 34, obj.mTaglines, true);
        }
        if (__setFields.contains(Integer.valueOf(35))) {
            SafeParcelWriter.writeTypedList(parcel, 35, obj.mUrls, true);
        }
        if (__setFields.contains(Integer.valueOf(32))) {
            SafeParcelWriter.writeTypedList(parcel, 32, obj.mSkills, true);
        }
        if (__setFields.contains(Integer.valueOf(33))) {
            SafeParcelWriter.writeParcelable(parcel, 33, obj.mSortKeys, flags, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
