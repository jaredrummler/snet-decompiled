package com.google.android.gms.common.internal;

import android.accounts.Account;
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

public class GetServiceRequestCreator implements Creator<GetServiceRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GetServiceRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        int _local_safe_0a1b_serviceId = 0;
        int _local_safe_0a1b_clientVersion = 0;
        String _local_safe_0a1b_callingPackage = null;
        IBinder _local_safe_0a1b_accountAccessorBinder = null;
        Scope[] _local_safe_0a1b_scopes = null;
        Bundle _local_safe_0a1b_extraArgs = null;
        Account _local_safe_0a1b_clientRequestedAccount = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_serviceId = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_clientVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_callingPackage = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_accountAccessorBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_scopes = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_extraArgs = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_clientRequestedAccount = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GetServiceRequest(_local_safe_0a1b_version, _local_safe_0a1b_serviceId, _local_safe_0a1b_clientVersion, _local_safe_0a1b_callingPackage, _local_safe_0a1b_accountAccessorBinder, _local_safe_0a1b_scopes, _local_safe_0a1b_extraArgs, _local_safe_0a1b_clientRequestedAccount);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GetServiceRequest[] newArray(int size) {
        return new GetServiceRequest[size];
    }

    static void writeToParcel(GetServiceRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeInt(parcel, 2, obj.serviceId);
        SafeParcelWriter.writeInt(parcel, 3, obj.clientVersion);
        SafeParcelWriter.writeString(parcel, 4, obj.callingPackage, false);
        SafeParcelWriter.writeIBinder(parcel, 5, obj.accountAccessorBinder, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, obj.scopes, flags, false);
        SafeParcelWriter.writeBundle(parcel, 7, obj.extraArgs, false);
        SafeParcelWriter.writeParcelable(parcel, 8, obj.clientRequestedAccount, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
