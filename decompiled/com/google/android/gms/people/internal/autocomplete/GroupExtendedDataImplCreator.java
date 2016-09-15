package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class GroupExtendedDataImplCreator implements Creator<GroupExtendedDataImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GroupExtendedDataImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        ContactPreferredFieldsEntity[] _local_safe_0a1b_mContactPreferences = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mContactPreferences = (ContactPreferredFieldsEntity[]) SafeParcelReader.createTypedArray(parcel, header, ContactPreferredFieldsEntity.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GroupExtendedDataImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mContactPreferences);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GroupExtendedDataImpl[] newArray(int size) {
        return new GroupExtendedDataImpl[size];
    }

    static void writeToParcel(GroupExtendedDataImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeTypedArray(parcel, 2, obj.mContactPreferences, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
