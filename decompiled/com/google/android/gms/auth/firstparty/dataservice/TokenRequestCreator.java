package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.dataservice.TokenRequest.Consent;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gsf.GoogleLoginServiceConstants;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class TokenRequestCreator implements Creator<TokenRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public TokenRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_service = null;
        String _local_safe_0a1b_accountName = null;
        Bundle _local_safe_0a1b_options = new Bundle();
        FACLConfig _local_safe_0a1b_faclData = null;
        PACLConfig _local_safe_0a1b_paclData = null;
        boolean _local_safe_0a1b_isAddingAccount = false;
        boolean _local_safe_0a1b_isCreatingAccount = false;
        String _local_safe_0a1b_mConsent = Consent.UNKNOWN.toString();
        AppDescription _local_safe_0a1b_callingAppDescription = null;
        CaptchaSolution _local_safe_0a1b_optionalCaptchaSolution = null;
        boolean _local_safe_0a1b_isForcingDroidguardRun = false;
        boolean _local_safe_0a1b_isUsingCache = true;
        String _local_safe_0a1b_accountType = GoogleLoginServiceConstants.ACCOUNT_TYPE;
        int _local_safe_0a1b_delegationType = 0;
        String _local_safe_0a1b_delegateeUserId = null;
        String _local_safe_0a1b_consentCookieWrapper = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_service = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_options = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_faclData = (FACLConfig) SafeParcelReader.createParcelable(parcel, header, FACLConfig.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_paclData = (PACLConfig) SafeParcelReader.createParcelable(parcel, header, PACLConfig.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_isAddingAccount = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_isCreatingAccount = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mConsent = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_callingAppDescription = (AppDescription) SafeParcelReader.createParcelable(parcel, header, AppDescription.CREATOR);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_optionalCaptchaSolution = (CaptchaSolution) SafeParcelReader.createParcelable(parcel, header, CaptchaSolution.CREATOR);
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_isForcingDroidguardRun = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_isUsingCache = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_accountType = SafeParcelReader.createString(parcel, header);
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    _local_safe_0a1b_delegationType = SafeParcelReader.readInt(parcel, header);
                    break;
                case LogSource.LE /*17*/:
                    _local_safe_0a1b_delegateeUserId = SafeParcelReader.createString(parcel, header);
                    break;
                case LogSource.GOOGLE_ANALYTICS /*18*/:
                    _local_safe_0a1b_consentCookieWrapper = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new TokenRequest(_local_safe_0a1b_version, _local_safe_0a1b_service, _local_safe_0a1b_accountName, _local_safe_0a1b_options, _local_safe_0a1b_faclData, _local_safe_0a1b_paclData, _local_safe_0a1b_isAddingAccount, _local_safe_0a1b_isCreatingAccount, _local_safe_0a1b_mConsent, _local_safe_0a1b_callingAppDescription, _local_safe_0a1b_optionalCaptchaSolution, _local_safe_0a1b_isForcingDroidguardRun, _local_safe_0a1b_isUsingCache, _local_safe_0a1b_accountType, _local_safe_0a1b_delegationType, _local_safe_0a1b_delegateeUserId, _local_safe_0a1b_consentCookieWrapper);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public TokenRequest[] newArray(int size) {
        return new TokenRequest[size];
    }

    static void writeToParcel(TokenRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.service, false);
        SafeParcelWriter.writeString(parcel, 3, obj.accountName, false);
        SafeParcelWriter.writeBundle(parcel, 4, obj.options, false);
        SafeParcelWriter.writeParcelable(parcel, 5, obj.faclData, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.paclData, flags, false);
        SafeParcelWriter.writeBoolean(parcel, 7, obj.isAddingAccount);
        SafeParcelWriter.writeBoolean(parcel, 8, obj.isCreatingAccount);
        SafeParcelWriter.writeString(parcel, 9, obj.mConsent, false);
        SafeParcelWriter.writeParcelable(parcel, 10, obj.callingAppDescription, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 11, obj.optionalCaptchaSolution, flags, false);
        SafeParcelWriter.writeBoolean(parcel, 13, obj.isForcingDroidguardRun);
        SafeParcelWriter.writeBoolean(parcel, 14, obj.isUsingCache);
        SafeParcelWriter.writeString(parcel, 15, obj.accountType, false);
        SafeParcelWriter.writeString(parcel, 17, obj.delegateeUserId, false);
        SafeParcelWriter.writeInt(parcel, 16, obj.delegationType);
        SafeParcelWriter.writeString(parcel, 18, obj.consentCookieWrapper, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
