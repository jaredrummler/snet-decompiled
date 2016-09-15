package com.google.android.gms.auth.firstparty.delegate;

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
import java.util.List;

public class SetupAccountWorkflowRequestCreator implements Creator<SetupAccountWorkflowRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SetupAccountWorkflowRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        boolean _local_safe_0a1b_isMultiUser = false;
        boolean _local_safe_0a1b_isSetupWizard = false;
        List<String> _local_safe_0a1b_allowedDomains = null;
        Bundle _local_safe_0a1b_options = new Bundle();
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        boolean _local_safe_0a1b_isCreditCardAllowed = false;
        String _local_safe_0a1b_accountType = null;
        AccountAuthenticatorResponse _local_safe_0a1b_amResponse = null;
        boolean _local_safe_0a1b_suppressD2d = false;
        boolean _local_safe_0a1b_useImmersiveMode = false;
        String _local_safe_0a1b_purchaserGaiaEmail = "null";
        String _local_safe_0a1b_purchaserName = "null";
        String _local_safe_0a1b_accountName = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_isMultiUser = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_isSetupWizard = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_allowedDomains = SafeParcelReader.createStringList(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_options = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_isCreditCardAllowed = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_accountType = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_amResponse = (AccountAuthenticatorResponse) SafeParcelReader.createParcelable(parcel, header, AccountAuthenticatorResponse.CREATOR);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_suppressD2d = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_useImmersiveMode = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_purchaserGaiaEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_purchaserName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SetupAccountWorkflowRequest(_local_safe_0a1b_version, _local_safe_0a1b_isMultiUser, _local_safe_0a1b_isSetupWizard, _local_safe_0a1b_allowedDomains, _local_safe_0a1b_options, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_isCreditCardAllowed, _local_safe_0a1b_accountType, _local_safe_0a1b_amResponse, _local_safe_0a1b_suppressD2d, _local_safe_0a1b_useImmersiveMode, _local_safe_0a1b_purchaserGaiaEmail, _local_safe_0a1b_purchaserName, _local_safe_0a1b_accountName);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SetupAccountWorkflowRequest[] newArray(int size) {
        return new SetupAccountWorkflowRequest[size];
    }

    static void writeToParcel(SetupAccountWorkflowRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.isMultiUser);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isSetupWizard);
        SafeParcelWriter.writeStringList(parcel, 4, obj.allowedDomains, false);
        SafeParcelWriter.writeBundle(parcel, 5, obj.options, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeBoolean(parcel, 7, obj.isCreditCardAllowed);
        SafeParcelWriter.writeString(parcel, 8, obj.accountType, false);
        SafeParcelWriter.writeParcelable(parcel, 9, obj.amResponse, flags, false);
        SafeParcelWriter.writeBoolean(parcel, 10, obj.suppressD2d);
        SafeParcelWriter.writeBoolean(parcel, 11, obj.useImmersiveMode);
        SafeParcelWriter.writeString(parcel, 12, obj.purchaserGaiaEmail, false);
        SafeParcelWriter.writeString(parcel, 13, obj.purchaserName, false);
        SafeParcelWriter.writeString(parcel, 14, obj.accountName, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
