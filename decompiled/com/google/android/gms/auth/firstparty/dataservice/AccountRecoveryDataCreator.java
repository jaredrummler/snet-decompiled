package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.Country;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class AccountRecoveryDataCreator implements Creator<AccountRecoveryData> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountRecoveryData createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        AccountRecoveryGuidance _local_safe_0a1b_guidance = null;
        String _local_safe_0a1b_action = null;
        String _local_safe_0a1b_allowedRecoveryOption = null;
        String _local_safe_0a1b_accountName = null;
        String _local_safe_0a1b_secondaryEmail = null;
        String _local_safe_0a1b_phoneNumber = null;
        List<Country> _local_safe_0a1b_countries = null;
        String _local_safe_0a1b_defaultCountryCode = null;
        String _local_safe_0a1b_error = null;
        Account _local_safe_0a1b_account = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_guidance = (AccountRecoveryGuidance) SafeParcelReader.createParcelable(parcel, header, AccountRecoveryGuidance.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_action = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_allowedRecoveryOption = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_secondaryEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_phoneNumber = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_countries = SafeParcelReader.createTypedList(parcel, header, Country.CREATOR);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_defaultCountryCode = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_error = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountRecoveryData(_local_safe_0a1b_version, _local_safe_0a1b_guidance, _local_safe_0a1b_action, _local_safe_0a1b_allowedRecoveryOption, _local_safe_0a1b_accountName, _local_safe_0a1b_secondaryEmail, _local_safe_0a1b_phoneNumber, _local_safe_0a1b_countries, _local_safe_0a1b_defaultCountryCode, _local_safe_0a1b_error, _local_safe_0a1b_account);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountRecoveryData[] newArray(int size) {
        return new AccountRecoveryData[size];
    }

    static void writeToParcel(AccountRecoveryData obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.guidance, flags, false);
        SafeParcelWriter.writeString(parcel, 3, obj.action, false);
        SafeParcelWriter.writeString(parcel, 4, obj.allowedRecoveryOption, false);
        SafeParcelWriter.writeString(parcel, 5, obj.accountName, false);
        SafeParcelWriter.writeString(parcel, 6, obj.secondaryEmail, false);
        SafeParcelWriter.writeString(parcel, 7, obj.phoneNumber, false);
        SafeParcelWriter.writeTypedList(parcel, 8, obj.countries, false);
        SafeParcelWriter.writeString(parcel, 9, obj.defaultCountryCode, false);
        SafeParcelWriter.writeString(parcel, 10, obj.error, false);
        SafeParcelWriter.writeParcelable(parcel, 11, obj.account, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
