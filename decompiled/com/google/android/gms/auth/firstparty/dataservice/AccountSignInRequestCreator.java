package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountSignInRequestCreator implements Creator<AccountSignInRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountSignInRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        boolean _local_safe_0a1b_isCreatingAccount = false;
        boolean _local_safe_0a1b_isSetupWizardInProgress = false;
        CaptchaSolution _local_safe_0a1b_optionalCaptchaSolution = null;
        AccountCredentials _local_safe_0a1b_accountCredentials = null;
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
                    _local_safe_0a1b_isCreatingAccount = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_isSetupWizardInProgress = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_optionalCaptchaSolution = (CaptchaSolution) SafeParcelReader.createParcelable(parcel, header, CaptchaSolution.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_accountCredentials = (AccountCredentials) SafeParcelReader.createParcelable(parcel, header, AccountCredentials.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountSignInRequest(_local_safe_0a1b_version, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_isCreatingAccount, _local_safe_0a1b_isSetupWizardInProgress, _local_safe_0a1b_optionalCaptchaSolution, _local_safe_0a1b_accountCredentials);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountSignInRequest[] newArray(int size) {
        return new AccountSignInRequest[size];
    }

    static void writeToParcel(AccountSignInRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isCreatingAccount);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isSetupWizardInProgress);
        SafeParcelWriter.writeParcelable(parcel, 5, obj.optionalCaptchaSolution, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.accountCredentials, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
