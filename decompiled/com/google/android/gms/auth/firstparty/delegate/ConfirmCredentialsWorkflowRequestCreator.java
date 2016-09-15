package com.google.android.gms.auth.firstparty.delegate;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ConfirmCredentialsWorkflowRequestCreator implements Creator<ConfirmCredentialsWorkflowRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ConfirmCredentialsWorkflowRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountName = null;
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        Bundle _local_safe_0a1b_options = new Bundle();
        Account _local_safe_0a1b_account = null;
        AccountAuthenticatorResponse _local_safe_0a1b_amResponse = null;
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
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_options = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_amResponse = (AccountAuthenticatorResponse) SafeParcelReader.createParcelable(parcel, header, AccountAuthenticatorResponse.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ConfirmCredentialsWorkflowRequest(_local_safe_0a1b_version, _local_safe_0a1b_accountName, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_options, _local_safe_0a1b_account, _local_safe_0a1b_amResponse);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ConfirmCredentialsWorkflowRequest[] newArray(int size) {
        return new ConfirmCredentialsWorkflowRequest[size];
    }

    static void writeToParcel(ConfirmCredentialsWorkflowRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeBundle(parcel, 4, obj.options, false);
        SafeParcelWriter.writeParcelable(parcel, 5, obj.account, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.amResponse, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
