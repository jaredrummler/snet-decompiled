package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MetadataImpl;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.Set;

public class MetadataImplCreator implements Creator<MetadataImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public MetadataImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mContainer = null;
        String _local_safe_0a1b_mContainerContactId = null;
        String _local_safe_0a1b_mContainerId = null;
        String _local_safe_0a1b_mVisibility = null;
        boolean _local_safe_0a1b_mEdgeKey = false;
        boolean _local_safe_0a1b_mPrimary = false;
        boolean _local_safe_0a1b_mVerified = false;
        boolean _local_safe_0a1b_mWriteable = false;
        int _local_safe_0a1b_mRawContactId = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mContainer = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mContainerContactId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mContainerId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mVisibility = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mEdgeKey = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mPrimary = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mVerified = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mWriteable = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mRawContactId = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new MetadataImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mContainer, _local_safe_0a1b_mContainerContactId, _local_safe_0a1b_mContainerId, _local_safe_0a1b_mVisibility, _local_safe_0a1b_mEdgeKey, _local_safe_0a1b_mPrimary, _local_safe_0a1b_mVerified, _local_safe_0a1b_mWriteable, _local_safe_0a1b_mRawContactId);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public MetadataImpl[] newArray(int size) {
        return new MetadataImpl[size];
    }

    static void writeToParcel(MetadataImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeString(parcel, 2, obj.mContainer, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeString(parcel, 3, obj.mContainerContactId, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeString(parcel, 4, obj.mContainerId, true);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeString(parcel, 5, obj.mVisibility, true);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeBoolean(parcel, 6, obj.mEdgeKey);
        }
        if (__setFields.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeBoolean(parcel, 7, obj.mPrimary);
        }
        if (__setFields.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeBoolean(parcel, 8, obj.mVerified);
        }
        if (__setFields.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeBoolean(parcel, 9, obj.mWriteable);
        }
        if (__setFields.contains(Integer.valueOf(10))) {
            SafeParcelWriter.writeInt(parcel, 10, obj.mRawContactId);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
