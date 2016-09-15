package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PersonImplCreator implements Creator<PersonImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PersonImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        PersonMetadataImpl _local_safe_0a1b_mMetadata = null;
        NameImpl[] _local_safe_0a1b_mNames = null;
        EmailImpl[] _local_safe_0a1b_mEmails = null;
        PhoneImpl[] _local_safe_0a1b_mPhones = null;
        PhotoImpl[] _local_safe_0a1b_mPhotos = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mMetadata = (PersonMetadataImpl) SafeParcelReader.createParcelable(parcel, header, PersonMetadataImpl.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mNames = (NameImpl[]) SafeParcelReader.createTypedArray(parcel, header, NameImpl.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mEmails = (EmailImpl[]) SafeParcelReader.createTypedArray(parcel, header, EmailImpl.CREATOR);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mPhones = (PhoneImpl[]) SafeParcelReader.createTypedArray(parcel, header, PhoneImpl.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mPhotos = (PhotoImpl[]) SafeParcelReader.createTypedArray(parcel, header, PhotoImpl.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PersonImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mMetadata, _local_safe_0a1b_mNames, _local_safe_0a1b_mEmails, _local_safe_0a1b_mPhones, _local_safe_0a1b_mPhotos);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PersonImpl[] newArray(int size) {
        return new PersonImpl[size];
    }

    static void writeToParcel(PersonImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.mMetadata, flags, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, obj.mNames, flags, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, obj.mEmails, flags, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, obj.mPhones, flags, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, obj.mPhotos, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
