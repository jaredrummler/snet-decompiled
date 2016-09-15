package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;

public class GoogleSignInOptionsCreator implements Creator<GoogleSignInOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GoogleSignInOptions createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        ArrayList<Scope> _local_safe_0a1b_mScopes = null;
        Account _local_safe_0a1b_mAccount = null;
        boolean _local_safe_0a1b_mIdTokenRequested = false;
        boolean _local_safe_0a1b_mServerAuthCodeRequested = false;
        boolean _local_safe_0a1b_mForceCodeForRefreshToken = false;
        String _local_safe_0a1b_mServerClientId = null;
        String _local_safe_0a1b_mHostedDomain = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mScopes = SafeParcelReader.createTypedList(parcel, header, Scope.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAccount = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mIdTokenRequested = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mServerAuthCodeRequested = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mForceCodeForRefreshToken = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mServerClientId = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mHostedDomain = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GoogleSignInOptions(_local_safe_0a1b_versionCode, (ArrayList) _local_safe_0a1b_mScopes, _local_safe_0a1b_mAccount, _local_safe_0a1b_mIdTokenRequested, _local_safe_0a1b_mServerAuthCodeRequested, _local_safe_0a1b_mForceCodeForRefreshToken, _local_safe_0a1b_mServerClientId, _local_safe_0a1b_mHostedDomain);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GoogleSignInOptions[] newArray(int size) {
        return new GoogleSignInOptions[size];
    }

    static void writeToParcel(GoogleSignInOptions obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeTypedList(parcel, 2, obj.getScopes(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.getAccount(), flags, false);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isIdTokenRequested());
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(parcel, 6, obj.isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(parcel, 7, obj.getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 8, obj.getHostedDomain(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
