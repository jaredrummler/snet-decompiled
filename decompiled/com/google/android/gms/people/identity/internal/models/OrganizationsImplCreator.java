package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.OrganizationsImpl;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.Set;

public class OrganizationsImplCreator implements Creator<OrganizationsImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public OrganizationsImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        MetadataImpl _local_safe_0a1b_mMetadata = null;
        boolean _local_safe_0a1b_mCurrent = false;
        String _local_safe_0a1b_mDepartment = null;
        String _local_safe_0a1b_mDescription = null;
        String _local_safe_0a1b_mDomain = null;
        String _local_safe_0a1b_mEndDate = null;
        String _local_safe_0a1b_mLocation = null;
        String _local_safe_0a1b_mName = null;
        String _local_safe_0a1b_mPhoneticName = null;
        String _local_safe_0a1b_mStartDate = null;
        String _local_safe_0a1b_mSymbol = null;
        String _local_safe_0a1b_mTitle = null;
        String _local_safe_0a1b_mType = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mMetadata = (MetadataImpl) SafeParcelReader.createParcelable(parcel, header, MetadataImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mCurrent = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mDepartment = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mDescription = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mDomain = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mEndDate = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mLocation = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mPhoneticName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mStartDate = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(11));
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mSymbol = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(12));
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mTitle = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(13));
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_mType = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(14));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new OrganizationsImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mMetadata, _local_safe_0a1b_mCurrent, _local_safe_0a1b_mDepartment, _local_safe_0a1b_mDescription, _local_safe_0a1b_mDomain, _local_safe_0a1b_mEndDate, _local_safe_0a1b_mLocation, _local_safe_0a1b_mName, _local_safe_0a1b_mPhoneticName, _local_safe_0a1b_mStartDate, _local_safe_0a1b_mSymbol, _local_safe_0a1b_mTitle, _local_safe_0a1b_mType);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public OrganizationsImpl[] newArray(int size) {
        return new OrganizationsImpl[size];
    }

    static void writeToParcel(OrganizationsImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeParcelable(parcel, 2, obj.mMetadata, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeBoolean(parcel, 3, obj.mCurrent);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeString(parcel, 4, obj.mDepartment, true);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeString(parcel, 5, obj.mDescription, true);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeString(parcel, 6, obj.mDomain, true);
        }
        if (__setFields.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeString(parcel, 7, obj.mEndDate, true);
        }
        if (__setFields.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeString(parcel, 8, obj.mLocation, true);
        }
        if (__setFields.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeString(parcel, 9, obj.mName, true);
        }
        if (__setFields.contains(Integer.valueOf(10))) {
            SafeParcelWriter.writeString(parcel, 10, obj.mPhoneticName, true);
        }
        if (__setFields.contains(Integer.valueOf(11))) {
            SafeParcelWriter.writeString(parcel, 11, obj.mStartDate, true);
        }
        if (__setFields.contains(Integer.valueOf(12))) {
            SafeParcelWriter.writeString(parcel, 12, obj.mSymbol, true);
        }
        if (__setFields.contains(Integer.valueOf(13))) {
            SafeParcelWriter.writeString(parcel, 13, obj.mTitle, true);
        }
        if (__setFields.contains(Integer.valueOf(14))) {
            SafeParcelWriter.writeString(parcel, 14, obj.mType, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
