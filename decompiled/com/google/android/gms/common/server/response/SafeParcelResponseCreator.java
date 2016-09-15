package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class SafeParcelResponseCreator implements Creator<SafeParcelResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SafeParcelResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        Parcel _local_safe_0a1b_mParcel = null;
        FieldMappingDictionary _local_safe_0a1b_mDictionary = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mParcel = SafeParcelReader.createParcel(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mDictionary = (FieldMappingDictionary) SafeParcelReader.createParcelable(parcel, header, FieldMappingDictionary.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SafeParcelResponse(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mParcel, _local_safe_0a1b_mDictionary);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SafeParcelResponse[] newArray(int size) {
        return new SafeParcelResponse[size];
    }

    static void writeToParcel(SafeParcelResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getVersionCode());
        SafeParcelWriter.writeParcel(parcel, 2, obj.getParcel(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.getFieldMappingDictionary(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
