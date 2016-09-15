package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.PersonImpl.PersonMetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.ProfileOwnerStatsImpl;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonMetadataImplCreator implements Creator<PersonMetadataImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PersonMetadataImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        List<String> _local_safe_0a1b_mAttributions = null;
        List<String> _local_safe_0a1b_mBlockTypes = null;
        List<String> _local_safe_0a1b_mCircles = null;
        List<String> _local_safe_0a1b_mContacts = null;
        List<String> _local_safe_0a1b_mGroups = null;
        List<String> _local_safe_0a1b_mIncomingBlockTypes = null;
        String _local_safe_0a1b_mObjectType = null;
        String _local_safe_0a1b_mOwnerId = null;
        List<String> _local_safe_0a1b_mOwnerUserTypes = null;
        String _local_safe_0a1b_mPlusPageType = null;
        ProfileOwnerStatsImpl _local_safe_0a1b_mProfileOwnerStats = null;
        boolean _local_safe_0a1b_mBlocked = false;
        boolean _local_safe_0a1b_mDeleted = false;
        boolean _local_safe_0a1b_mInViewerDomain = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAttributions = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mBlockTypes = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mCircles = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mContacts = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mGroups = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mIncomingBlockTypes = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mObjectType = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mOwnerId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mOwnerUserTypes = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mPlusPageType = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(11));
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mProfileOwnerStats = (ProfileOwnerStatsImpl) SafeParcelReader.createParcelable(parcel, header, ProfileOwnerStatsImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(12));
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mBlocked = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(13));
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_mDeleted = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(14));
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_mInViewerDomain = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(15));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PersonMetadataImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAttributions, _local_safe_0a1b_mBlockTypes, _local_safe_0a1b_mCircles, _local_safe_0a1b_mContacts, _local_safe_0a1b_mGroups, _local_safe_0a1b_mIncomingBlockTypes, _local_safe_0a1b_mObjectType, _local_safe_0a1b_mOwnerId, _local_safe_0a1b_mOwnerUserTypes, _local_safe_0a1b_mPlusPageType, _local_safe_0a1b_mProfileOwnerStats, _local_safe_0a1b_mBlocked, _local_safe_0a1b_mDeleted, _local_safe_0a1b_mInViewerDomain);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PersonMetadataImpl[] newArray(int size) {
        return new PersonMetadataImpl[size];
    }

    static void writeToParcel(PersonMetadataImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeStringList(parcel, 2, obj.mAttributions, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeStringList(parcel, 3, obj.mBlockTypes, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeStringList(parcel, 4, obj.mCircles, true);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeStringList(parcel, 5, obj.mContacts, true);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeStringList(parcel, 6, obj.mGroups, true);
        }
        if (__setFields.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeStringList(parcel, 7, obj.mIncomingBlockTypes, true);
        }
        if (__setFields.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeString(parcel, 8, obj.mObjectType, true);
        }
        if (__setFields.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeString(parcel, 9, obj.mOwnerId, true);
        }
        if (__setFields.contains(Integer.valueOf(10))) {
            SafeParcelWriter.writeStringList(parcel, 10, obj.mOwnerUserTypes, true);
        }
        if (__setFields.contains(Integer.valueOf(11))) {
            SafeParcelWriter.writeString(parcel, 11, obj.mPlusPageType, true);
        }
        if (__setFields.contains(Integer.valueOf(12))) {
            SafeParcelWriter.writeParcelable(parcel, 12, obj.mProfileOwnerStats, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(13))) {
            SafeParcelWriter.writeBoolean(parcel, 13, obj.mBlocked);
        }
        if (__setFields.contains(Integer.valueOf(14))) {
            SafeParcelWriter.writeBoolean(parcel, 14, obj.mDeleted);
        }
        if (__setFields.contains(Integer.valueOf(15))) {
            SafeParcelWriter.writeBoolean(parcel, 15, obj.mInViewerDomain);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
