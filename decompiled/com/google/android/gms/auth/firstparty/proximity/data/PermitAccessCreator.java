package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PermitAccessCreator implements Creator<PermitAccess> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PermitAccess createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        String _local_safe_0a1b_mId = null;
        String _local_safe_0a1b_mType = null;
        byte[] _local_safe_0a1b_mData = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mType = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mData = SafeParcelReader.createByteArray(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PermitAccess(_local_safe_0a1b_mVersion, _local_safe_0a1b_mId, _local_safe_0a1b_mType, _local_safe_0a1b_mData);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PermitAccess[] newArray(int size) {
        return new PermitAccess[size];
    }

    static void writeToParcel(PermitAccess obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeString(parcel, 2, obj.mId, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mType, false);
        SafeParcelWriter.writeByteArray(parcel, 4, obj.mData, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
