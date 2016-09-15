package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class SubstringImplCreator implements Creator<SubstringImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SubstringImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mStartIndex = 0;
        int _local_safe_0a1b_mLength = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mStartIndex = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mLength = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SubstringImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mStartIndex, _local_safe_0a1b_mLength);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SubstringImpl[] newArray(int size) {
        return new SubstringImpl[size];
    }

    static void writeToParcel(SubstringImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.mStartIndex);
        SafeParcelWriter.writeInt(parcel, 3, obj.mLength);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
