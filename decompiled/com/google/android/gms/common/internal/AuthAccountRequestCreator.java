package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AuthAccountRequestCreator implements Creator<AuthAccountRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AuthAccountRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        IBinder _local_safe_0a1b_mAccountAccessorBinder = null;
        Scope[] _local_safe_0a1b_mScopes = null;
        Integer _local_safe_0a1b_mOauthPolicy = null;
        Integer _local_safe_0a1b_mPolicyAction = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAccountAccessorBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mScopes = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mOauthPolicy = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mPolicyAction = SafeParcelReader.readIntegerObject(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AuthAccountRequest(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAccountAccessorBinder, _local_safe_0a1b_mScopes, _local_safe_0a1b_mOauthPolicy, _local_safe_0a1b_mPolicyAction);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AuthAccountRequest[] newArray(int size) {
        return new AuthAccountRequest[size];
    }

    static void writeToParcel(AuthAccountRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, obj.mAccountAccessorBinder, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, obj.mScopes, flags, false);
        SafeParcelWriter.writeIntegerObject(parcel, 4, obj.mOauthPolicy, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, obj.mPolicyAction, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
