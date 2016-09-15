package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ContactGroupIdImplCreator implements Creator<ContactGroupIdImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ContactGroupIdImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mId = null;
        String[] _local_safe_0a1b_mLegacyId = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mLegacyId = SafeParcelReader.createStringArray(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ContactGroupIdImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mId, _local_safe_0a1b_mLegacyId);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ContactGroupIdImpl[] newArray(int size) {
        return new ContactGroupIdImpl[size];
    }

    static void writeToParcel(ContactGroupIdImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.mId, false);
        SafeParcelWriter.writeStringArray(parcel, 3, obj.mLegacyId, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
