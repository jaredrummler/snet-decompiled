package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ContactGroupNameImplCreator implements Creator<ContactGroupNameImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ContactGroupNameImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mValue = null;
        String _local_safe_0a1b_mFormattedValue = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mValue = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mFormattedValue = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ContactGroupNameImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mValue, _local_safe_0a1b_mFormattedValue);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ContactGroupNameImpl[] newArray(int size) {
        return new ContactGroupNameImpl[size];
    }

    static void writeToParcel(ContactGroupNameImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.mValue, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mFormattedValue, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
