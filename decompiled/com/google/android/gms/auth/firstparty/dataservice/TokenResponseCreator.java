package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.TokenData;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.List;

public class TokenResponseCreator implements Creator<TokenResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public TokenResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountName = null;
        String _local_safe_0a1b_statusWireCode = null;
        String _local_safe_0a1b_token = null;
        String _local_safe_0a1b_signInUrl = null;
        String _local_safe_0a1b_detail = null;
        String _local_safe_0a1b_picasaUser = null;
        String _local_safe_0a1b_firstName = null;
        String _local_safe_0a1b_lastName = null;
        boolean _local_safe_0a1b_isGPlusServiceAllowed = false;
        boolean _local_safe_0a1b_isGPlusServiceEnabled = false;
        boolean _local_safe_0a1b_isEsMobileServiceEnabled = false;
        boolean _local_safe_0a1b_isBrowserSignInSuggested = false;
        CaptchaChallenge _local_safe_0a1b_captcha = null;
        List<ScopeDetail> _local_safe_0a1b_scopeData = new ArrayList();
        String _local_safe_0a1b_ropText = null;
        String _local_safe_0a1b_ropRevision = null;
        boolean _local_safe_0a1b_hasTitle = false;
        int _local_safe_0a1b_title = 0;
        PostSignInData _local_safe_0a1b_postSignInData = null;
        Account _local_safe_0a1b_account = null;
        String _local_safe_0a1b_dmStatus = null;
        TokenData _local_safe_0a1b_tokenData = null;
        Bundle _local_safe_0a1b_dataForLogging = new Bundle();
        String _local_safe_0a1b_consentCookieWrapper = null;
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
                    _local_safe_0a1b_statusWireCode = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_token = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_signInUrl = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_detail = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_picasaUser = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_firstName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_lastName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_isGPlusServiceAllowed = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_isGPlusServiceEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_isEsMobileServiceEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_isBrowserSignInSuggested = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_captcha = (CaptchaChallenge) SafeParcelReader.createParcelable(parcel, header, CaptchaChallenge.CREATOR);
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_scopeData = SafeParcelReader.createTypedList(parcel, header, ScopeDetail.CREATOR);
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    _local_safe_0a1b_ropText = SafeParcelReader.createString(parcel, header);
                    break;
                case LogSource.LE /*17*/:
                    _local_safe_0a1b_ropRevision = SafeParcelReader.createString(parcel, header);
                    break;
                case LogSource.LB_D /*19*/:
                    _local_safe_0a1b_hasTitle = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LogSource.ANDROID_GSA /*20*/:
                    _local_safe_0a1b_title = SafeParcelReader.readInt(parcel, header);
                    break;
                case LogSource.LB_T /*21*/:
                    _local_safe_0a1b_postSignInData = (PostSignInData) SafeParcelReader.createParcelable(parcel, header, PostSignInData.CREATOR);
                    break;
                case LogSource.PERSONAL_LOGGER /*22*/:
                    _local_safe_0a1b_account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                case LogSource.ANDROID_CAMERA /*26*/:
                    _local_safe_0a1b_dmStatus = SafeParcelReader.createString(parcel, header);
                    break;
                case LogSource.CW /*27*/:
                    _local_safe_0a1b_tokenData = (TokenData) SafeParcelReader.createParcelable(parcel, header, TokenData.CREATOR);
                    break;
                case LogSource.GEL /*28*/:
                    _local_safe_0a1b_dataForLogging = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LogSource.DNA_PROBER /*29*/:
                    _local_safe_0a1b_consentCookieWrapper = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new TokenResponse(_local_safe_0a1b_version, _local_safe_0a1b_accountName, _local_safe_0a1b_statusWireCode, _local_safe_0a1b_token, _local_safe_0a1b_signInUrl, _local_safe_0a1b_detail, _local_safe_0a1b_picasaUser, _local_safe_0a1b_firstName, _local_safe_0a1b_lastName, _local_safe_0a1b_isGPlusServiceAllowed, _local_safe_0a1b_isGPlusServiceEnabled, _local_safe_0a1b_isEsMobileServiceEnabled, _local_safe_0a1b_isBrowserSignInSuggested, _local_safe_0a1b_captcha, _local_safe_0a1b_scopeData, _local_safe_0a1b_ropText, _local_safe_0a1b_ropRevision, _local_safe_0a1b_hasTitle, _local_safe_0a1b_title, _local_safe_0a1b_postSignInData, _local_safe_0a1b_account, _local_safe_0a1b_dmStatus, _local_safe_0a1b_tokenData, _local_safe_0a1b_dataForLogging, _local_safe_0a1b_consentCookieWrapper);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public TokenResponse[] newArray(int size) {
        return new TokenResponse[size];
    }

    static void writeToParcel(TokenResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeString(parcel, 3, obj.statusWireCode, false);
        SafeParcelWriter.writeString(parcel, 4, obj.token, false);
        SafeParcelWriter.writeString(parcel, 5, obj.signInUrl, false);
        SafeParcelWriter.writeString(parcel, 6, obj.detail, false);
        SafeParcelWriter.writeString(parcel, 7, obj.picasaUser, false);
        SafeParcelWriter.writeString(parcel, 8, obj.firstName, false);
        SafeParcelWriter.writeString(parcel, 9, obj.lastName, false);
        SafeParcelWriter.writeBoolean(parcel, 10, obj.isGPlusServiceAllowed);
        SafeParcelWriter.writeBoolean(parcel, 11, obj.isGPlusServiceEnabled);
        SafeParcelWriter.writeBoolean(parcel, 12, obj.isEsMobileServiceEnabled);
        SafeParcelWriter.writeBoolean(parcel, 13, obj.isBrowserSignInSuggested);
        SafeParcelWriter.writeParcelable(parcel, 14, obj.captcha, flags, false);
        SafeParcelWriter.writeTypedList(parcel, 15, obj.scopeData, false);
        SafeParcelWriter.writeString(parcel, 17, obj.ropRevision, false);
        SafeParcelWriter.writeString(parcel, 16, obj.ropText, false);
        SafeParcelWriter.writeBoolean(parcel, 19, obj.hasTitle);
        SafeParcelWriter.writeParcelable(parcel, 21, obj.postSignInData, flags, false);
        SafeParcelWriter.writeInt(parcel, 20, obj.title);
        SafeParcelWriter.writeParcelable(parcel, 22, obj.account, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 27, obj.tokenData, flags, false);
        SafeParcelWriter.writeString(parcel, 26, obj.dmStatus, false);
        SafeParcelWriter.writeString(parcel, 29, obj.consentCookieWrapper, false);
        SafeParcelWriter.writeBundle(parcel, 28, obj.dataForLogging, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
