package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ContactPreferredFieldsEntityCreator implements Creator<ContactPreferredFieldsEntity> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ContactPreferredFieldsEntity createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mEmail = null;
        String _local_safe_0a1b_mName = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mName = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ContactPreferredFieldsEntity(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mEmail, _local_safe_0a1b_mName);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ContactPreferredFieldsEntity[] newArray(int size) {
        return new ContactPreferredFieldsEntity[size];
    }

    static void writeToParcel(ContactPreferredFieldsEntity obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.mEmail, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mName, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
