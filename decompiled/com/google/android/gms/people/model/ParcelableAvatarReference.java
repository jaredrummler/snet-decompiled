package com.google.android.gms.people.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ParcelableAvatarReference implements Creator<AvatarReference> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AvatarReference createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mSource = 0;
        String _local_safe_0a1b_mLocation = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mSource = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mLocation = SafeParcelReader.createString(parcel, header);
                    break;
                case 1000:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AvatarReference(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mSource, _local_safe_0a1b_mLocation);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AvatarReference[] newArray(int size) {
        return new AvatarReference[size];
    }

    static void writeToParcel(AvatarReference obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getSource());
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeString(parcel, 2, obj.getLocation(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
