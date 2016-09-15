package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AutocompletionImplCreator implements Creator<AutocompletionImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AutocompletionImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mObjectType = 0;
        PersonImpl _local_safe_0a1b_mPerson = null;
        ContactGroupImpl _local_safe_0a1b_mContactGroup = null;
        DisplayableFieldImpl[] _local_safe_0a1b_mMatches = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mObjectType = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mPerson = (PersonImpl) SafeParcelReader.createParcelable(parcel, header, PersonImpl.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mContactGroup = (ContactGroupImpl) SafeParcelReader.createParcelable(parcel, header, ContactGroupImpl.CREATOR);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mMatches = (DisplayableFieldImpl[]) SafeParcelReader.createTypedArray(parcel, header, DisplayableFieldImpl.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AutocompletionImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mObjectType, _local_safe_0a1b_mPerson, _local_safe_0a1b_mContactGroup, _local_safe_0a1b_mMatches);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AutocompletionImpl[] newArray(int size) {
        return new AutocompletionImpl[size];
    }

    static void writeToParcel(AutocompletionImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.mObjectType);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.mPerson, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.mContactGroup, flags, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, obj.mMatches, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
