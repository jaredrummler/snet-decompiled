package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gsf.GoogleLoginServiceConstants;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountCredentialsCreator implements Creator<AccountCredentials> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountCredentials createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        boolean _local_safe_0a1b_mIsBrowserRequired = false;
        String _local_safe_0a1b_mAccountName = null;
        String _local_safe_0a1b_mLongLivedLoginToken = null;
        String _local_safe_0a1b_mAuthorizationCode = null;
        String _local_safe_0a1b_mPassword = null;
        String _local_safe_0a1b_mVerifier = null;
        String _local_safe_0a1b_mRedirectUri = null;
        String _local_safe_0a1b_mAccountType = GoogleLoginServiceConstants.ACCOUNT_TYPE;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mIsBrowserRequired = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAccountName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mLongLivedLoginToken = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mAuthorizationCode = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mPassword = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mVerifier = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mRedirectUri = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mAccountType = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountCredentials(_local_safe_0a1b_version, _local_safe_0a1b_mIsBrowserRequired, _local_safe_0a1b_mAccountName, _local_safe_0a1b_mLongLivedLoginToken, _local_safe_0a1b_mAuthorizationCode, _local_safe_0a1b_mPassword, _local_safe_0a1b_mVerifier, _local_safe_0a1b_mRedirectUri, _local_safe_0a1b_mAccountType);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountCredentials[] newArray(int size) {
        return new AccountCredentials[size];
    }

    static void writeToParcel(AccountCredentials obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.mIsBrowserRequired);
        SafeParcelWriter.writeString(parcel, 3, obj.mAccountName, false);
        SafeParcelWriter.writeString(parcel, 4, obj.mLongLivedLoginToken, false);
        SafeParcelWriter.writeString(parcel, 5, obj.mAuthorizationCode, false);
        SafeParcelWriter.writeString(parcel, 6, obj.mPassword, false);
        SafeParcelWriter.writeString(parcel, 7, obj.mVerifier, false);
        SafeParcelWriter.writeString(parcel, 8, obj.mRedirectUri, false);
        SafeParcelWriter.writeString(parcel, 9, obj.mAccountType, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
