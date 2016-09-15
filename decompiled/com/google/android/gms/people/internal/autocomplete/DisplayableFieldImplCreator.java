package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class DisplayableFieldImplCreator implements Creator<DisplayableFieldImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public DisplayableFieldImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mType = 0;
        int _local_safe_0a1b_mIndex = 0;
        String _local_safe_0a1b_mValue = null;
        SubstringImpl[] _local_safe_0a1b_mMatchingSubstrings = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mType = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mIndex = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mValue = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mMatchingSubstrings = (SubstringImpl[]) SafeParcelReader.createTypedArray(parcel, header, SubstringImpl.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new DisplayableFieldImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mType, _local_safe_0a1b_mIndex, _local_safe_0a1b_mValue, _local_safe_0a1b_mMatchingSubstrings);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public DisplayableFieldImpl[] newArray(int size) {
        return new DisplayableFieldImpl[size];
    }

    static void writeToParcel(DisplayableFieldImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.mType);
        SafeParcelWriter.writeInt(parcel, 3, obj.mIndex);
        SafeParcelWriter.writeString(parcel, 4, obj.mValue, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, obj.mMatchingSubstrings, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
