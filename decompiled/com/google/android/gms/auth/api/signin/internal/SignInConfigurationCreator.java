package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.EmailSignInOptions;
import com.google.android.gms.auth.api.signin.FacebookSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class SignInConfigurationCreator implements Creator<SignInConfiguration> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SignInConfiguration createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_mConsumerPkgName = null;
        String _local_safe_0a1b_mServerClientId = null;
        EmailSignInOptions _local_safe_0a1b_mEmailSignInOptions = null;
        GoogleSignInOptions _local_safe_0a1b_mGoogleSignInOptions = null;
        FacebookSignInOptions _local_safe_0a1b_mFacebookSignInOptions = null;
        String _local_safe_0a1b_mApiKey = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mConsumerPkgName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mServerClientId = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mEmailSignInOptions = (EmailSignInOptions) SafeParcelReader.createParcelable(parcel, header, EmailSignInOptions.CREATOR);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mGoogleSignInOptions = (GoogleSignInOptions) SafeParcelReader.createParcelable(parcel, header, GoogleSignInOptions.CREATOR);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mFacebookSignInOptions = (FacebookSignInOptions) SafeParcelReader.createParcelable(parcel, header, FacebookSignInOptions.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mApiKey = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SignInConfiguration(_local_safe_0a1b_versionCode, _local_safe_0a1b_mConsumerPkgName, _local_safe_0a1b_mServerClientId, _local_safe_0a1b_mEmailSignInOptions, _local_safe_0a1b_mGoogleSignInOptions, _local_safe_0a1b_mFacebookSignInOptions, _local_safe_0a1b_mApiKey);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SignInConfiguration[] newArray(int size) {
        return new SignInConfiguration[size];
    }

    static void writeToParcel(SignInConfiguration obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.getConsumerPkgName(), false);
        SafeParcelWriter.writeString(parcel, 3, obj.getServerClientId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.getEmailConfig(), flags, false);
        SafeParcelWriter.writeParcelable(parcel, 5, obj.getGoogleConfig(), flags, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.getFacebookConfig(), flags, false);
        SafeParcelWriter.writeString(parcel, 7, obj.getApiKey(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
