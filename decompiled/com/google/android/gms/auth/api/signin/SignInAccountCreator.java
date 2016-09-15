package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.lint.BuildConfig;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class SignInAccountCreator implements Creator<SignInAccount> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SignInAccount createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_mProviderId = null;
        String _local_safe_0a1b_mIdToken = null;
        String _local_safe_0a1b_mEmail = null;
        String _local_safe_0a1b_mDisplayName = null;
        Uri _local_safe_0a1b_mPhotoUrl = null;
        GoogleSignInAccount _local_safe_0a1b_mGoogleSignInAccount = null;
        String _local_safe_0a1b_mUserId = BuildConfig.VERSION_NAME;
        String _local_safe_0a1b_mRefreshToken = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mProviderId = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mIdToken = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mEmail = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mDisplayName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mPhotoUrl = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mGoogleSignInAccount = (GoogleSignInAccount) SafeParcelReader.createParcelable(parcel, header, GoogleSignInAccount.CREATOR);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mUserId = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mRefreshToken = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SignInAccount(_local_safe_0a1b_versionCode, _local_safe_0a1b_mProviderId, _local_safe_0a1b_mIdToken, _local_safe_0a1b_mEmail, _local_safe_0a1b_mDisplayName, _local_safe_0a1b_mPhotoUrl, _local_safe_0a1b_mGoogleSignInAccount, _local_safe_0a1b_mUserId, _local_safe_0a1b_mRefreshToken);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SignInAccount[] newArray(int size) {
        return new SignInAccount[size];
    }

    static void writeToParcel(SignInAccount obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 3, obj.getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, obj.getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, obj.getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.getPhotoUrl(), flags, false);
        SafeParcelWriter.writeParcelable(parcel, 7, obj.getGoogleSignInAccount(), flags, false);
        SafeParcelWriter.writeString(parcel, 8, obj.getUserId(), false);
        SafeParcelWriter.writeString(parcel, 9, obj.getRefreshToken(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
