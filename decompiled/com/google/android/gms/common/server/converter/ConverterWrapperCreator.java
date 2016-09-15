package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ConverterWrapperCreator implements Creator<ConverterWrapper> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ConverterWrapper createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        StringToIntConverter _local_safe_0a1b_mStringToIntConverter = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mStringToIntConverter = (StringToIntConverter) SafeParcelReader.createParcelable(parcel, header, StringToIntConverter.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ConverterWrapper(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mStringToIntConverter);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ConverterWrapper[] newArray(int size) {
        return new ConverterWrapper[size];
    }

    static void writeToParcel(ConverterWrapper obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getVersionCode());
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getStringToIntConverter(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
