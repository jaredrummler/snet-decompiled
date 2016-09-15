package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PhotoImplCreator implements Creator<PhotoImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PhotoImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mSource = 0;
        String _local_safe_0a1b_mLocation = null;
        boolean _local_safe_0a1b_mIsDefault = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mSource = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mLocation = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mIsDefault = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PhotoImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mSource, _local_safe_0a1b_mLocation, _local_safe_0a1b_mIsDefault);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PhotoImpl[] newArray(int size) {
        return new PhotoImpl[size];
    }

    static void writeToParcel(PhotoImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.mSource);
        SafeParcelWriter.writeString(parcel, 3, obj.mLocation, false);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.mIsDefault);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
