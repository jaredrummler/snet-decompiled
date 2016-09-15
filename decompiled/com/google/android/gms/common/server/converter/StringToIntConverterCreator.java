package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.converter.StringToIntConverter.Entry;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;

public class StringToIntConverterCreator implements Creator<StringToIntConverter> {
    public static final int CONTENT_DESCRIPTION = 0;

    public StringToIntConverter createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        ArrayList<Entry> _local_safe_0a1b_mSerializedMap = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mSerializedMap = SafeParcelReader.createTypedList(parcel, header, Entry.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new StringToIntConverter(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mSerializedMap);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public StringToIntConverter[] newArray(int size) {
        return new StringToIntConverter[size];
    }

    static void writeToParcel(StringToIntConverter obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getVersionCode());
        SafeParcelWriter.writeTypedList(parcel, 2, obj.getSerializedMap(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
