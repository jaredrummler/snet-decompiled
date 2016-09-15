package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.converter.StringToIntConverter.Entry;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class StringToIntConverterEntryCreator implements Creator<Entry> {
    public static final int CONTENT_DESCRIPTION = 0;

    public Entry createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_stringValue = null;
        int _local_safe_0a1b_intValue = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_stringValue = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_intValue = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new Entry(_local_safe_0a1b_versionCode, _local_safe_0a1b_stringValue, _local_safe_0a1b_intValue);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public Entry[] newArray(int size) {
        return new Entry[size];
    }

    static void writeToParcel(Entry obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.stringValue, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.intValue);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
