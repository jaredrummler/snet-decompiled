package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FieldMappingDictionary.FieldMapPair;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class FieldMapPairCreator implements Creator<FieldMapPair> {
    public static final int CONTENT_DESCRIPTION = 0;

    public FieldMapPair createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_key = null;
        Field<?, ?> _local_safe_0a1b_value = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_key = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_value = (Field) SafeParcelReader.createParcelable(parcel, header, Field.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new FieldMapPair(_local_safe_0a1b_versionCode, _local_safe_0a1b_key, _local_safe_0a1b_value);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public FieldMapPair[] newArray(int size) {
        return new FieldMapPair[size];
    }

    static void writeToParcel(FieldMapPair obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.key, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.value, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
