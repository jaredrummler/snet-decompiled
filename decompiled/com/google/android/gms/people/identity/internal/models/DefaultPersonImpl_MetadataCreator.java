package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata.Affinities;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata.ProfileOwnerStats;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultPersonImpl_MetadataCreator implements Creator<Metadata> {
    public static final int CONTENT_DESCRIPTION = 0;

    public Metadata createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        List<Affinities> _local_safe_0a1b_mAffinities = null;
        List<String> _local_safe_0a1b_mAttributions = null;
        List<String> _local_safe_0a1b_mBlockTypes = null;
        boolean _local_safe_0a1b_mBlocked = false;
        List<String> _local_safe_0a1b_mCircles = null;
        List<String> _local_safe_0a1b_mContacts = null;
        boolean _local_safe_0a1b_mDeleted = false;
        List<String> _local_safe_0a1b_mGroups = null;
        boolean _local_safe_0a1b_mInViewerDomain = false;
        List<String> _local_safe_0a1b_mIncomingBlockTypes = null;
        long _local_safe_0a1b_mLastUpdateTimeMicros = 0;
        String _local_safe_0a1b_mObjectType = null;
        String _local_safe_0a1b_mOwnerId = null;
        List<String> _local_safe_0a1b_mOwnerUserTypes = null;
        List<DefaultPersonImpl> _local_safe_0a1b_mPeopleInCommon = null;
        String _local_safe_0a1b_mPlusPageType = null;
        ProfileOwnerStats _local_safe_0a1b_mProfileOwnerStats = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAffinities = SafeParcelReader.createTypedList(parcel, header, Affinities.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAttributions = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mBlockTypes = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mBlocked = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mCircles = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mContacts = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mDeleted = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mGroups = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mInViewerDomain = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mIncomingBlockTypes = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(11));
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mLastUpdateTimeMicros = SafeParcelReader.readLong(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(12));
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mObjectType = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(13));
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_mOwnerId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(14));
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_mOwnerUserTypes = SafeParcelReader.createStringList(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(15));
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    _local_safe_0a1b_mPeopleInCommon = SafeParcelReader.createTypedList(parcel, header, DefaultPersonImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(16));
                    break;
                case LogSource.LE /*17*/:
                    _local_safe_0a1b_mPlusPageType = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(17));
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    _local_safe_0a1b_mProfileOwnerStats = (ProfileOwnerStats) SafeParcelReader.createParcelable(parcel, header, ProfileOwnerStats.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(18));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new Metadata(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAffinities, _local_safe_0a1b_mAttributions, _local_safe_0a1b_mBlockTypes, _local_safe_0a1b_mBlocked, _local_safe_0a1b_mCircles, _local_safe_0a1b_mContacts, _local_safe_0a1b_mDeleted, _local_safe_0a1b_mGroups, _local_safe_0a1b_mInViewerDomain, _local_safe_0a1b_mIncomingBlockTypes, _local_safe_0a1b_mLastUpdateTimeMicros, _local_safe_0a1b_mObjectType, _local_safe_0a1b_mOwnerId, _local_safe_0a1b_mOwnerUserTypes, _local_safe_0a1b_mPeopleInCommon, _local_safe_0a1b_mPlusPageType, _local_safe_0a1b_mProfileOwnerStats);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public Metadata[] newArray(int size) {
        return new Metadata[size];
    }

    static void writeToParcel(Metadata obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeTypedList(parcel, 2, obj.mAffinities, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeStringList(parcel, 3, obj.mAttributions, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeStringList(parcel, 4, obj.mBlockTypes, true);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeBoolean(parcel, 5, obj.mBlocked);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeStringList(parcel, 6, obj.mCircles, true);
        }
        if (__setFields.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeStringList(parcel, 7, obj.mContacts, true);
        }
        if (__setFields.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeBoolean(parcel, 8, obj.mDeleted);
        }
        if (__setFields.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeStringList(parcel, 9, obj.mGroups, true);
        }
        if (__setFields.contains(Integer.valueOf(10))) {
            SafeParcelWriter.writeBoolean(parcel, 10, obj.mInViewerDomain);
        }
        if (__setFields.contains(Integer.valueOf(11))) {
            SafeParcelWriter.writeStringList(parcel, 11, obj.mIncomingBlockTypes, true);
        }
        if (__setFields.contains(Integer.valueOf(12))) {
            SafeParcelWriter.writeLong(parcel, 12, obj.mLastUpdateTimeMicros);
        }
        if (__setFields.contains(Integer.valueOf(13))) {
            SafeParcelWriter.writeString(parcel, 13, obj.mObjectType, true);
        }
        if (__setFields.contains(Integer.valueOf(14))) {
            SafeParcelWriter.writeString(parcel, 14, obj.mOwnerId, true);
        }
        if (__setFields.contains(Integer.valueOf(15))) {
            SafeParcelWriter.writeStringList(parcel, 15, obj.mOwnerUserTypes, true);
        }
        if (__setFields.contains(Integer.valueOf(17))) {
            SafeParcelWriter.writeString(parcel, 17, obj.mPlusPageType, true);
        }
        if (__setFields.contains(Integer.valueOf(16))) {
            SafeParcelWriter.writeTypedList(parcel, 16, obj.mPeopleInCommon, true);
        }
        if (__setFields.contains(Integer.valueOf(18))) {
            SafeParcelWriter.writeParcelable(parcel, 18, obj.mProfileOwnerStats, flags, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
