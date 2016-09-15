package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class CheckRealNameRequestCreator implements Creator<CheckRealNameRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public CheckRealNameRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        String _local_safe_0a1b_firstName = null;
        String _local_safe_0a1b_lastName = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_firstName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_lastName = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new CheckRealNameRequest(_local_safe_0a1b_version, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_firstName, _local_safe_0a1b_lastName);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public CheckRealNameRequest[] newArray(int size) {
        return new CheckRealNameRequest[size];
    }

    static void writeToParcel(CheckRealNameRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeString(parcel, 3, obj.firstName, false);
        SafeParcelWriter.writeString(parcel, 4, obj.lastName, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
