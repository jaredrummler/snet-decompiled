package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.Set;

public class ImageReferenceImplCreator implements Creator<ImageReferenceImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ImageReferenceImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mType = 0;
        String _local_safe_0a1b_mLocation = null;
        byte[] _local_safe_0a1b_mData = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mType = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mLocation = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mData = SafeParcelReader.createByteArray(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ImageReferenceImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mType, _local_safe_0a1b_mLocation, _local_safe_0a1b_mData);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ImageReferenceImpl[] newArray(int size) {
        return new ImageReferenceImpl[size];
    }

    static void writeToParcel(ImageReferenceImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeInt(parcel, 2, obj.mType);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeString(parcel, 3, obj.mLocation, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeByteArray(parcel, 4, obj.mData, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
