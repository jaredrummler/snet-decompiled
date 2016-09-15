package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.PersonImpl.AboutsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.AddressesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.BirthdaysImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.BraggingRightsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.CoverPhotosImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.CustomFieldsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.EmailsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.EventsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.GendersImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.ImagesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.InstantMessagingImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.LegacyFieldsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MembershipsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NamesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NicknamesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NotesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.OccupationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.OrganizationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PersonMetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PhoneNumbersImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PlacesLivedImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationshipInterestsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.RelationshipStatusesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.SkillsImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.SortKeysImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.TaglinesImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.UrlsImpl;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonImplCreator implements Creator<PersonImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PersonImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        List<AboutsImpl> _local_safe_0a1b_mAbouts = null;
        List<AddressesImpl> _local_safe_0a1b_mAddresses = null;
        String _local_safe_0a1b_mAgeRange = null;
        List<BirthdaysImpl> _local_safe_0a1b_mBirthdays = null;
        List<BraggingRightsImpl> _local_safe_0a1b_mBraggingRights = null;
        List<CoverPhotosImpl> _local_safe_0a1b_mCoverPhotos = null;
        List<CustomFieldsImpl> _local_safe_0a1b_mCustomFields = null;
        List<EmailsImpl> _local_safe_0a1b_mEmails = null;
        String _local_safe_0a1b_mEtag = null;
        List<EventsImpl> _local_safe_0a1b_mEvents = null;
        List<GendersImpl> _local_safe_0a1b_mGenders = null;
        String _local_safe_0a1b_mId = null;
        List<ImagesImpl> _local_safe_0a1b_mImages = null;
        List<InstantMessagingImpl> _local_safe_0a1b_mInstantMessaging = null;
        String _local_safe_0a1b_mLanguage = null;
        LegacyFieldsImpl _local_safe_0a1b_mLegacyFields = null;
        List<PersonImpl> _local_safe_0a1b_mLinkedPeople = null;
        List<MembershipsImpl> _local_safe_0a1b_mMemberships = null;
        PersonMetadataImpl _local_safe_0a1b_mMetadata = null;
        List<NamesImpl> _local_safe_0a1b_mNames = null;
        List<NicknamesImpl> _local_safe_0a1b_mNicknames = null;
        List<OccupationsImpl> _local_safe_0a1b_mOccupations = null;
        List<OrganizationsImpl> _local_safe_0a1b_mOrganizations = null;
        List<PhoneNumbersImpl> _local_safe_0a1b_mPhoneNumbers = null;
        List<PlacesLivedImpl> _local_safe_0a1b_mPlacesLived = null;
        String _local_safe_0a1b_mProfileUrl = null;
        List<RelationsImpl> _local_safe_0a1b_mRelations = null;
        List<RelationshipInterestsImpl> _local_safe_0a1b_mRelationshipInterests = null;
        List<RelationshipStatusesImpl> _local_safe_0a1b_mRelationshipStatuses = null;
        List<SkillsImpl> _local_safe_0a1b_mSkills = null;
        SortKeysImpl _local_safe_0a1b_mSortKeys = null;
        List<TaglinesImpl> _local_safe_0a1b_mTaglines = null;
        List<UrlsImpl> _local_safe_0a1b_mUrls = null;
        List<NotesImpl> _local_safe_0a1b_mNotes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAbouts = SafeParcelReader.createTypedList(parcel, header, AboutsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAddresses = SafeParcelReader.createTypedList(parcel, header, AddressesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mAgeRange = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mBirthdays = SafeParcelReader.createTypedList(parcel, header, BirthdaysImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mBraggingRights = SafeParcelReader.createTypedList(parcel, header, BraggingRightsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mCoverPhotos = SafeParcelReader.createTypedList(parcel, header, CoverPhotosImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mCustomFields = SafeParcelReader.createTypedList(parcel, header, CustomFieldsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mEmails = SafeParcelReader.createTypedList(parcel, header, EmailsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mEtag = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mEvents = SafeParcelReader.createTypedList(parcel, header, EventsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(11));
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mGenders = SafeParcelReader.createTypedList(parcel, header, GendersImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(12));
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(13));
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_mImages = SafeParcelReader.createTypedList(parcel, header, ImagesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(14));
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_mInstantMessaging = SafeParcelReader.createTypedList(parcel, header, InstantMessagingImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(15));
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    _local_safe_0a1b_mLanguage = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(16));
                    break;
                case LogSource.LE /*17*/:
                    _local_safe_0a1b_mLegacyFields = (LegacyFieldsImpl) SafeParcelReader.createParcelable(parcel, header, LegacyFieldsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(17));
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    _local_safe_0a1b_mLinkedPeople = SafeParcelReader.createTypedList(parcel, header, PersonImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(18));
                    break;
                case LogSource.LB_D /*19*/:
                    _local_safe_0a1b_mMemberships = SafeParcelReader.createTypedList(parcel, header, MembershipsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(19));
                    break;
                case LogSource.ANDROID_GSA /*20*/:
                    _local_safe_0a1b_mMetadata = (PersonMetadataImpl) SafeParcelReader.createParcelable(parcel, header, PersonMetadataImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(20));
                    break;
                case LogSource.LB_T /*21*/:
                    _local_safe_0a1b_mNames = SafeParcelReader.createTypedList(parcel, header, NamesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(21));
                    break;
                case LogSource.PERSONAL_LOGGER /*22*/:
                    _local_safe_0a1b_mNicknames = SafeParcelReader.createTypedList(parcel, header, NicknamesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(22));
                    break;
                case LogSource.GMS_CORE_WALLET_MERCHANT_ERROR /*23*/:
                    _local_safe_0a1b_mOccupations = SafeParcelReader.createTypedList(parcel, header, OccupationsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(23));
                    break;
                case LogSource.LB_C /*24*/:
                    _local_safe_0a1b_mOrganizations = SafeParcelReader.createTypedList(parcel, header, OrganizationsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(24));
                    break;
                case LogSource.ANDROID_AUTH /*25*/:
                    _local_safe_0a1b_mPhoneNumbers = SafeParcelReader.createTypedList(parcel, header, PhoneNumbersImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(25));
                    break;
                case LogSource.ANDROID_CAMERA /*26*/:
                    _local_safe_0a1b_mPlacesLived = SafeParcelReader.createTypedList(parcel, header, PlacesLivedImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(26));
                    break;
                case LogSource.CW /*27*/:
                    _local_safe_0a1b_mProfileUrl = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(27));
                    break;
                case LogSource.GEL /*28*/:
                    _local_safe_0a1b_mRelations = SafeParcelReader.createTypedList(parcel, header, RelationsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(28));
                    break;
                case LogSource.DNA_PROBER /*29*/:
                    _local_safe_0a1b_mRelationshipInterests = SafeParcelReader.createTypedList(parcel, header, RelationshipInterestsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(29));
                    break;
                case LogSource.UDR /*30*/:
                    _local_safe_0a1b_mRelationshipStatuses = SafeParcelReader.createTypedList(parcel, header, RelationshipStatusesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(30));
                    break;
                case LogSource.GMS_CORE_WALLET /*31*/:
                    _local_safe_0a1b_mSkills = SafeParcelReader.createTypedList(parcel, header, SkillsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(31));
                    break;
                case LogSource.SOCIAL /*32*/:
                    _local_safe_0a1b_mSortKeys = (SortKeysImpl) SafeParcelReader.createParcelable(parcel, header, SortKeysImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(32));
                    break;
                case LogSource.INSTORE_WALLET /*33*/:
                    _local_safe_0a1b_mTaglines = SafeParcelReader.createTypedList(parcel, header, TaglinesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(33));
                    break;
                case LogSource.NOVA /*34*/:
                    _local_safe_0a1b_mUrls = SafeParcelReader.createTypedList(parcel, header, UrlsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(34));
                    break;
                case LogSource.LB_CA /*35*/:
                    _local_safe_0a1b_mNotes = SafeParcelReader.createTypedList(parcel, header, NotesImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(35));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PersonImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAbouts, _local_safe_0a1b_mAddresses, _local_safe_0a1b_mAgeRange, _local_safe_0a1b_mBirthdays, _local_safe_0a1b_mBraggingRights, _local_safe_0a1b_mCoverPhotos, _local_safe_0a1b_mCustomFields, _local_safe_0a1b_mEmails, _local_safe_0a1b_mEtag, _local_safe_0a1b_mEvents, _local_safe_0a1b_mGenders, _local_safe_0a1b_mId, _local_safe_0a1b_mImages, _local_safe_0a1b_mInstantMessaging, _local_safe_0a1b_mLanguage, _local_safe_0a1b_mLegacyFields, _local_safe_0a1b_mLinkedPeople, _local_safe_0a1b_mMemberships, _local_safe_0a1b_mMetadata, _local_safe_0a1b_mNames, _local_safe_0a1b_mNicknames, _local_safe_0a1b_mOccupations, _local_safe_0a1b_mOrganizations, _local_safe_0a1b_mPhoneNumbers, _local_safe_0a1b_mPlacesLived, _local_safe_0a1b_mProfileUrl, _local_safe_0a1b_mRelations, _local_safe_0a1b_mRelationshipInterests, _local_safe_0a1b_mRelationshipStatuses, _local_safe_0a1b_mSkills, _local_safe_0a1b_mSortKeys, _local_safe_0a1b_mTaglines, _local_safe_0a1b_mUrls, _local_safe_0a1b_mNotes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PersonImpl[] newArray(int size) {
        return new PersonImpl[size];
    }

    static void writeToParcel(PersonImpl obj, Parcel parcel, int flags) {
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
            SafeParcelWriter.writeParcelable(parcel, 17, obj.mLegacyFields, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(16))) {
            SafeParcelWriter.writeString(parcel, 16, obj.mLanguage, true);
        }
        if (__setFields.contains(Integer.valueOf(19))) {
            SafeParcelWriter.writeTypedList(parcel, 19, obj.mMemberships, true);
        }
        if (__setFields.contains(Integer.valueOf(18))) {
            SafeParcelWriter.writeTypedList(parcel, 18, obj.mLinkedPeople, true);
        }
        if (__setFields.contains(Integer.valueOf(21))) {
            SafeParcelWriter.writeTypedList(parcel, 21, obj.mNames, true);
        }
        if (__setFields.contains(Integer.valueOf(20))) {
            SafeParcelWriter.writeParcelable(parcel, 20, obj.mMetadata, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(23))) {
            SafeParcelWriter.writeTypedList(parcel, 23, obj.mOccupations, true);
        }
        if (__setFields.contains(Integer.valueOf(22))) {
            SafeParcelWriter.writeTypedList(parcel, 22, obj.mNicknames, true);
        }
        if (__setFields.contains(Integer.valueOf(25))) {
            SafeParcelWriter.writeTypedList(parcel, 25, obj.mPhoneNumbers, true);
        }
        if (__setFields.contains(Integer.valueOf(24))) {
            SafeParcelWriter.writeTypedList(parcel, 24, obj.mOrganizations, true);
        }
        if (__setFields.contains(Integer.valueOf(27))) {
            SafeParcelWriter.writeString(parcel, 27, obj.mProfileUrl, true);
        }
        if (__setFields.contains(Integer.valueOf(26))) {
            SafeParcelWriter.writeTypedList(parcel, 26, obj.mPlacesLived, true);
        }
        if (__setFields.contains(Integer.valueOf(29))) {
            SafeParcelWriter.writeTypedList(parcel, 29, obj.mRelationshipInterests, true);
        }
        if (__setFields.contains(Integer.valueOf(28))) {
            SafeParcelWriter.writeTypedList(parcel, 28, obj.mRelations, true);
        }
        if (__setFields.contains(Integer.valueOf(31))) {
            SafeParcelWriter.writeTypedList(parcel, 31, obj.mSkills, true);
        }
        if (__setFields.contains(Integer.valueOf(30))) {
            SafeParcelWriter.writeTypedList(parcel, 30, obj.mRelationshipStatuses, true);
        }
        if (__setFields.contains(Integer.valueOf(34))) {
            SafeParcelWriter.writeTypedList(parcel, 34, obj.mUrls, true);
        }
        if (__setFields.contains(Integer.valueOf(35))) {
            SafeParcelWriter.writeTypedList(parcel, 35, obj.mNotes, true);
        }
        if (__setFields.contains(Integer.valueOf(32))) {
            SafeParcelWriter.writeParcelable(parcel, 32, obj.mSortKeys, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(33))) {
            SafeParcelWriter.writeTypedList(parcel, 33, obj.mTaglines, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
