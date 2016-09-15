package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ContactGroupImplCreator implements Creator<ContactGroupImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ContactGroupImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        ContactGroupIdImpl _local_safe_0a1b_mId = null;
        ContactGroupNameImpl _local_safe_0a1b_mName = null;
        GroupExtendedDataImpl _local_safe_0a1b_mExtendedData = null;
        int _local_safe_0a1b_mMemberCount = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mId = (ContactGroupIdImpl) SafeParcelReader.createParcelable(parcel, header, ContactGroupIdImpl.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mName = (ContactGroupNameImpl) SafeParcelReader.createParcelable(parcel, header, ContactGroupNameImpl.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mExtendedData = (GroupExtendedDataImpl) SafeParcelReader.createParcelable(parcel, header, GroupExtendedDataImpl.CREATOR);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mMemberCount = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ContactGroupImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mId, _local_safe_0a1b_mName, _local_safe_0a1b_mExtendedData, _local_safe_0a1b_mMemberCount);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ContactGroupImpl[] newArray(int size) {
        return new ContactGroupImpl[size];
    }

    static void writeToParcel(ContactGroupImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.mId, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.mName, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.mExtendedData, flags, false);
        SafeParcelWriter.writeInt(parcel, 5, obj.mMemberCount);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
