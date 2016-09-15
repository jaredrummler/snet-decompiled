package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class GoogleSignInAccountCreator implements Creator<GoogleSignInAccount> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GoogleSignInAccount createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_mId = null;
        String _local_safe_0a1b_mIdToken = null;
        String _local_safe_0a1b_mEmail = null;
        String _local_safe_0a1b_mDisplayName = null;
        Uri _local_safe_0a1b_mPhotoUrl = null;
        String _local_safe_0a1b_mServerAuthCode = null;
        long _local_safe_0a1b_mExpirationTimeSecs = 0;
        String _local_safe_0a1b_mObfuscatedIdentifier = null;
        List<Scope> _local_safe_0a1b_mGrantedScopes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
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
                    _local_safe_0a1b_mServerAuthCode = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mExpirationTimeSecs = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mObfuscatedIdentifier = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mGrantedScopes = SafeParcelReader.createTypedList(parcel, header, Scope.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GoogleSignInAccount(_local_safe_0a1b_versionCode, _local_safe_0a1b_mId, _local_safe_0a1b_mIdToken, _local_safe_0a1b_mEmail, _local_safe_0a1b_mDisplayName, _local_safe_0a1b_mPhotoUrl, _local_safe_0a1b_mServerAuthCode, _local_safe_0a1b_mExpirationTimeSecs, _local_safe_0a1b_mObfuscatedIdentifier, _local_safe_0a1b_mGrantedScopes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GoogleSignInAccount[] newArray(int size) {
        return new GoogleSignInAccount[size];
    }

    static void writeToParcel(GoogleSignInAccount obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.getId(), false);
        SafeParcelWriter.writeString(parcel, 3, obj.getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, obj.getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, obj.getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.getPhotoUrl(), flags, false);
        SafeParcelWriter.writeString(parcel, 7, obj.getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel, 8, obj.getExpirationTimeSecs());
        SafeParcelWriter.writeString(parcel, 9, obj.getObfuscatedIdentifier(), false);
        SafeParcelWriter.writeTypedList(parcel, 10, obj.mGrantedScopes, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
