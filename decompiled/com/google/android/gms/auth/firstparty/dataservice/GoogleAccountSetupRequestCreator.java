package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class GoogleAccountSetupRequestCreator implements Creator<GoogleAccountSetupRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GoogleAccountSetupRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        Bundle _local_safe_0a1b_options = new Bundle();
        boolean _local_safe_0a1b_isAgreedToWebHistory = false;
        boolean _local_safe_0a1b_isAgreedToPersonalizedContent = false;
        boolean _local_safe_0a1b_isAgreedToMobileTos = false;
        String _local_safe_0a1b_firstName = null;
        String _local_safe_0a1b_lastName = null;
        String _local_safe_0a1b_secondaryEmail = null;
        String _local_safe_0a1b_gender = null;
        boolean _local_safe_0a1b_isCreatingAccount = false;
        boolean _local_safe_0a1b_isAddingAccount = false;
        boolean _local_safe_0a1b_isSetupWizardInProgress = false;
        String _local_safe_0a1b_ropRevision = null;
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        AccountCredentials _local_safe_0a1b_accountCredentials = null;
        CaptchaSolution _local_safe_0a1b_optionalCaptchaSolution = null;
        String _local_safe_0a1b_phoneNumber = null;
        String _local_safe_0a1b_phoneCountryCode = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_options = SafeParcelReader.createBundle(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_isAgreedToWebHistory = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_isAgreedToPersonalizedContent = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_isAgreedToMobileTos = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_firstName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_lastName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_secondaryEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_gender = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_isCreatingAccount = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_isAddingAccount = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_isSetupWizardInProgress = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_ropRevision = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_accountCredentials = (AccountCredentials) SafeParcelReader.createParcelable(parcel, header, AccountCredentials.CREATOR);
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    _local_safe_0a1b_optionalCaptchaSolution = (CaptchaSolution) SafeParcelReader.createParcelable(parcel, header, CaptchaSolution.CREATOR);
                    break;
                case LogSource.LE /*17*/:
                    _local_safe_0a1b_phoneNumber = SafeParcelReader.createString(parcel, header);
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    _local_safe_0a1b_phoneCountryCode = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GoogleAccountSetupRequest(_local_safe_0a1b_version, _local_safe_0a1b_options, _local_safe_0a1b_isAgreedToWebHistory, _local_safe_0a1b_isAgreedToPersonalizedContent, _local_safe_0a1b_isAgreedToMobileTos, _local_safe_0a1b_firstName, _local_safe_0a1b_lastName, _local_safe_0a1b_secondaryEmail, _local_safe_0a1b_gender, _local_safe_0a1b_isCreatingAccount, _local_safe_0a1b_isAddingAccount, _local_safe_0a1b_isSetupWizardInProgress, _local_safe_0a1b_ropRevision, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_accountCredentials, _local_safe_0a1b_optionalCaptchaSolution, _local_safe_0a1b_phoneNumber, _local_safe_0a1b_phoneCountryCode);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GoogleAccountSetupRequest[] newArray(int size) {
        return new GoogleAccountSetupRequest[size];
    }

    static void writeToParcel(GoogleAccountSetupRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeBundle(parcel, 2, obj.options, false);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isAgreedToWebHistory);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isAgreedToPersonalizedContent);
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isAgreedToMobileTos);
        SafeParcelWriter.writeString(parcel, 6, obj.firstName, false);
        SafeParcelWriter.writeString(parcel, 7, obj.lastName, false);
        SafeParcelWriter.writeString(parcel, 8, obj.secondaryEmail, false);
        SafeParcelWriter.writeString(parcel, 9, obj.gender, false);
        SafeParcelWriter.writeBoolean(parcel, 10, obj.isCreatingAccount);
        SafeParcelWriter.writeBoolean(parcel, 11, obj.isAddingAccount);
        SafeParcelWriter.writeBoolean(parcel, 12, obj.isSetupWizardInProgress);
        SafeParcelWriter.writeString(parcel, 13, obj.ropRevision, false);
        SafeParcelWriter.writeParcelable(parcel, 14, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 15, obj.accountCredentials, flags, false);
        SafeParcelWriter.writeString(parcel, 17, obj.phoneNumber, false);
        SafeParcelWriter.writeParcelable(parcel, 16, obj.optionalCaptchaSolution, flags, false);
        SafeParcelWriter.writeString(parcel, 18, obj.phoneCountryCode, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
