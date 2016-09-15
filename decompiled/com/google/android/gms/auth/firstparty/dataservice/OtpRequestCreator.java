package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class OtpRequestCreator implements Creator<OtpRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public OtpRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        String _local_safe_0a1b_accountName = null;
        AppDescription _local_safe_0a1b_callerDescription = null;
        byte[] _local_safe_0a1b_challenge = null;
        boolean _local_safe_0a1b_isLegacyRequest = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_callerDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_challenge = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_isLegacyRequest = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new OtpRequest(_local_safe_0a1b_mVersion, _local_safe_0a1b_accountName, _local_safe_0a1b_callerDescription, _local_safe_0a1b_challenge, _local_safe_0a1b_isLegacyRequest);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public OtpRequest[] newArray(int size) {
        return new OtpRequest[size];
    }

    static void writeToParcel(OtpRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.callerDescription, flags, false);
        SafeParcelWriter.writeByteArray(parcel, 4, obj.challenge, false);
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isLegacyRequest);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
