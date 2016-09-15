package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PACLConfigCreator implements Creator<PACLConfig> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PACLConfig createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_visibleActions = null;
        String _local_safe_0a1b_pacl = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_visibleActions = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_pacl = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PACLConfig(_local_safe_0a1b_version, _local_safe_0a1b_visibleActions, _local_safe_0a1b_pacl);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PACLConfig[] newArray(int size) {
        return new PACLConfig[size];
    }

    static void writeToParcel(PACLConfig obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.visibleActions, false);
        SafeParcelWriter.writeString(parcel, 3, obj.pacl, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
