package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class RecoveryReadResponseCreator implements Creator<RecoveryReadResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public RecoveryReadResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mSecondaryEmail = null;
        String _local_safe_0a1b_mPhoneNumber = null;
        String _local_safe_0a1b_mPhoneCountryCode = null;
        List<Country> _local_safe_0a1b_mCountryList = null;
        String _local_safe_0a1b_mError = null;
        String _local_safe_0a1b_mAction = null;
        String _local_safe_0a1b_mAllowedOptions = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mSecondaryEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mPhoneNumber = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mPhoneCountryCode = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mCountryList = SafeParcelReader.createTypedList(parcel, header, Country.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mError = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mAction = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mAllowedOptions = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new RecoveryReadResponse(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mSecondaryEmail, _local_safe_0a1b_mPhoneNumber, _local_safe_0a1b_mPhoneCountryCode, _local_safe_0a1b_mCountryList, _local_safe_0a1b_mError, _local_safe_0a1b_mAction, _local_safe_0a1b_mAllowedOptions);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public RecoveryReadResponse[] newArray(int size) {
        return new RecoveryReadResponse[size];
    }

    static void writeToParcel(RecoveryReadResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.mSecondaryEmail, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mPhoneNumber, false);
        SafeParcelWriter.writeString(parcel, 4, obj.mPhoneCountryCode, false);
        SafeParcelWriter.writeTypedList(parcel, 5, obj.mCountryList, false);
        SafeParcelWriter.writeString(parcel, 6, obj.mError, false);
        SafeParcelWriter.writeString(parcel, 7, obj.mAction, false);
        SafeParcelWriter.writeString(parcel, 8, obj.mAllowedOptions, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
