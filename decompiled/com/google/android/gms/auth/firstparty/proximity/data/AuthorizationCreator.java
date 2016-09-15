package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AuthorizationCreator implements Creator<Authorization> {
    public static final int CONTENT_DESCRIPTION = 0;

    public Authorization createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        String _local_safe_0a1b_mPermitId = null;
        String _local_safe_0a1b_mPermitAccessId = null;
        byte[] _local_safe_0a1b_mData = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mPermitId = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mPermitAccessId = SafeParcelReader.createString(parcel, header);
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
            return new Authorization(_local_safe_0a1b_mVersion, _local_safe_0a1b_mPermitId, _local_safe_0a1b_mPermitAccessId, _local_safe_0a1b_mData);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public Authorization[] newArray(int size) {
        return new Authorization[size];
    }

    static void writeToParcel(Authorization obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeString(parcel, 2, obj.mPermitId, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mPermitAccessId, false);
        SafeParcelWriter.writeByteArray(parcel, 4, obj.mData, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
