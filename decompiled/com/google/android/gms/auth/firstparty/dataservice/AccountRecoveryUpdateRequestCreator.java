package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountRecoveryUpdateRequestCreator implements Creator<AccountRecoveryUpdateRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountRecoveryUpdateRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountName = null;
        String _local_safe_0a1b_secondaryEmail = null;
        String _local_safe_0a1b_phoneNumber = null;
        String _local_safe_0a1b_phoneCountryCode = null;
        boolean _local_safe_0a1b_isBroadUse = false;
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        Account _local_safe_0a1b_account = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_secondaryEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_phoneNumber = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_phoneCountryCode = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_isBroadUse = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountRecoveryUpdateRequest(_local_safe_0a1b_version, _local_safe_0a1b_accountName, _local_safe_0a1b_secondaryEmail, _local_safe_0a1b_phoneNumber, _local_safe_0a1b_phoneCountryCode, _local_safe_0a1b_isBroadUse, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_account);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountRecoveryUpdateRequest[] newArray(int size) {
        return new AccountRecoveryUpdateRequest[size];
    }

    static void writeToParcel(AccountRecoveryUpdateRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeString(parcel, 3, obj.secondaryEmail, false);
        SafeParcelWriter.writeString(parcel, 4, obj.phoneNumber, false);
        SafeParcelWriter.writeString(parcel, 5, obj.phoneCountryCode, false);
        SafeParcelWriter.writeBoolean(parcel, 6, obj.isBroadUse);
        SafeParcelWriter.writeParcelable(parcel, 7, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 8, obj.account, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
