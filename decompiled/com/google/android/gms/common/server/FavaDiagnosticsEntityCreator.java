package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class FavaDiagnosticsEntityCreator implements Creator<FavaDiagnosticsEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    public FavaDiagnosticsEntity createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_namespace = null;
        int _local_safe_0a1b_typeNum = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_namespace = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_typeNum = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new FavaDiagnosticsEntity(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_namespace, _local_safe_0a1b_typeNum);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public FavaDiagnosticsEntity[] newArray(int size) {
        return new FavaDiagnosticsEntity[size];
    }

    static void writeToParcel(FavaDiagnosticsEntity obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.namespace, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.typeNum);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
