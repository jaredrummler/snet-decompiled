package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class RecoveryWriteRequestCreator implements Creator<RecoveryWriteRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public RecoveryWriteRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mAccount = null;
        String _local_safe_0a1b_mSecondaryEmail = null;
        String _local_safe_0a1b_mPhoneNumber = null;
        String _local_safe_0a1b_mPhoneCountryCode = null;
        boolean _local_safe_0a1b_mIsBroadUse = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAccount = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mSecondaryEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mPhoneNumber = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mPhoneCountryCode = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mIsBroadUse = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new RecoveryWriteRequest(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAccount, _local_safe_0a1b_mSecondaryEmail, _local_safe_0a1b_mPhoneNumber, _local_safe_0a1b_mPhoneCountryCode, _local_safe_0a1b_mIsBroadUse);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public RecoveryWriteRequest[] newArray(int size) {
        return new RecoveryWriteRequest[size];
    }

    static void writeToParcel(RecoveryWriteRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.mAccount, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mSecondaryEmail, false);
        SafeParcelWriter.writeString(parcel, 4, obj.mPhoneNumber, false);
        SafeParcelWriter.writeString(parcel, 5, obj.mPhoneCountryCode, false);
        SafeParcelWriter.writeBoolean(parcel, 6, obj.mIsBroadUse);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
