package com.google.android.gms.common.internal;

import android.os.Bundle;
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

public class ValidateAccountRequestCreator implements Creator<ValidateAccountRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ValidateAccountRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mClientVersion = 0;
        IBinder _local_safe_0a1b_mAccountAccessorBinder = null;
        Scope[] _local_safe_0a1b_mScopes = null;
        Bundle _local_safe_0a1b_mExtraArgs = null;
        String _local_safe_0a1b_mCallingPackage = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mClientVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAccountAccessorBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mScopes = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mExtraArgs = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mCallingPackage = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ValidateAccountRequest(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mClientVersion, _local_safe_0a1b_mAccountAccessorBinder, _local_safe_0a1b_mScopes, _local_safe_0a1b_mExtraArgs, _local_safe_0a1b_mCallingPackage);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ValidateAccountRequest[] newArray(int size) {
        return new ValidateAccountRequest[size];
    }

    static void writeToParcel(ValidateAccountRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.getClientVersion());
        SafeParcelWriter.writeIBinder(parcel, 3, obj.mAccountAccessorBinder, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, obj.getScopes(), flags, false);
        SafeParcelWriter.writeBundle(parcel, 5, obj.getExtraArgs(), false);
        SafeParcelWriter.writeString(parcel, 6, obj.getCallingPackage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
