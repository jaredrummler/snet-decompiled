package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.CoverPhotos;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.Set;

public class DefaultPersonImpl_CoverPhotosCreator implements Creator<CoverPhotos> {
    public static final int CONTENT_DESCRIPTION = 0;

    public CoverPhotos createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mHeight = 0;
        String _local_safe_0a1b_mId = null;
        boolean _local_safe_0a1b_mIsDefault = false;
        String _local_safe_0a1b_mUrl = null;
        int _local_safe_0a1b_mWidth = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mHeight = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mIsDefault = SafeParcelReader.readBoolean(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mUrl = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mWidth = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new CoverPhotos(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mHeight, _local_safe_0a1b_mId, _local_safe_0a1b_mIsDefault, _local_safe_0a1b_mUrl, _local_safe_0a1b_mWidth);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public CoverPhotos[] newArray(int size) {
        return new CoverPhotos[size];
    }

    static void writeToParcel(CoverPhotos obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeInt(parcel, 2, obj.mHeight);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeString(parcel, 3, obj.mId, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeBoolean(parcel, 4, obj.mIsDefault);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeString(parcel, 5, obj.mUrl, true);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeInt(parcel, 6, obj.mWidth);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
