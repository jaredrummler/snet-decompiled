package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountNameCheckRequestCreator implements Creator<AccountNameCheckRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountNameCheckRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountNameToCheck = null;
        String _local_safe_0a1b_optionalFirstName = null;
        String _local_safe_0a1b_optionalLastName = null;
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        CaptchaSolution _local_safe_0a1b_optionalCaptchaSolution = null;
        Account _local_safe_0a1b_accountToCheck = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_accountNameToCheck = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_optionalFirstName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_optionalLastName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_optionalCaptchaSolution = (CaptchaSolution) SafeParcelReader.createParcelable(parcel, header, CaptchaSolution.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_accountToCheck = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountNameCheckRequest(_local_safe_0a1b_version, _local_safe_0a1b_accountNameToCheck, _local_safe_0a1b_optionalFirstName, _local_safe_0a1b_optionalLastName, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_optionalCaptchaSolution, _local_safe_0a1b_accountToCheck);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountNameCheckRequest[] newArray(int size) {
        return new AccountNameCheckRequest[size];
    }

    static void writeToParcel(AccountNameCheckRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountNameToCheck, false);
        SafeParcelWriter.writeString(parcel, 3, obj.optionalFirstName, false);
        SafeParcelWriter.writeString(parcel, 4, obj.optionalLastName, false);
        SafeParcelWriter.writeParcelable(parcel, 5, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.optionalCaptchaSolution, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 7, obj.accountToCheck, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
