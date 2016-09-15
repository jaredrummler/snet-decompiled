package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ResolveAccountResponseCreator implements Creator<ResolveAccountResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ResolveAccountResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        IBinder _local_safe_0a1b_mAccountAccessorBinder = null;
        ConnectionResult _local_safe_0a1b_mConnectionResult = null;
        boolean _local_safe_0a1b_mSaveDefaultAccount = false;
        boolean _local_safe_0a1b_mIsFromCrossClientAuth = false;
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
                    _local_safe_0a1b_mConnectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, header, ConnectionResult.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mSaveDefaultAccount = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mIsFromCrossClientAuth = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ResolveAccountResponse(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAccountAccessorBinder, _local_safe_0a1b_mConnectionResult, _local_safe_0a1b_mSaveDefaultAccount, _local_safe_0a1b_mIsFromCrossClientAuth);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ResolveAccountResponse[] newArray(int size) {
        return new ResolveAccountResponse[size];
    }

    static void writeToParcel(ResolveAccountResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeIBinder(parcel, 2, obj.mAccountAccessorBinder, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.getConnectionResult(), flags, false);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.getSaveDefaultAccount());
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isFromCrossClientAuth());
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
