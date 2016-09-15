package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountRecoveryGuidanceCreator implements Creator<AccountRecoveryGuidance> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountRecoveryGuidance createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountName = null;
        boolean _local_safe_0a1b_isRecoveryInfoNeeded = false;
        boolean _local_safe_0a1b_isRecoveryInterstitialSuggested = false;
        boolean _local_safe_0a1b_isRecoveryUpdateAllowed = false;
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
                    _local_safe_0a1b_isRecoveryInfoNeeded = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_isRecoveryInterstitialSuggested = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_isRecoveryUpdateAllowed = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountRecoveryGuidance(_local_safe_0a1b_version, _local_safe_0a1b_accountName, _local_safe_0a1b_isRecoveryInfoNeeded, _local_safe_0a1b_isRecoveryInterstitialSuggested, _local_safe_0a1b_isRecoveryUpdateAllowed, _local_safe_0a1b_account);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountRecoveryGuidance[] newArray(int size) {
        return new AccountRecoveryGuidance[size];
    }

    static void writeToParcel(AccountRecoveryGuidance obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isRecoveryInfoNeeded);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isRecoveryInterstitialSuggested);
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isRecoveryUpdateAllowed);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.account, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
