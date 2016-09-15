package com.google.android.gms.people.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountMetadataCreator implements Creator<AccountMetadata> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountMetadata createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        boolean _local_safe_0a1b_isPlusEnabled = false;
        boolean _local_safe_0a1b_isSyncEnabled = false;
        boolean _local_safe_0a1b_isPagePeriodicSyncEnabled = false;
        boolean _local_safe_0a1b_isPageTickleSyncEnabled = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_isPlusEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_isSyncEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_isPagePeriodicSyncEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_isPageTickleSyncEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountMetadata(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_isPlusEnabled, _local_safe_0a1b_isSyncEnabled, _local_safe_0a1b_isPagePeriodicSyncEnabled, _local_safe_0a1b_isPageTickleSyncEnabled);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountMetadata[] newArray(int size) {
        return new AccountMetadata[size];
    }

    static void writeToParcel(AccountMetadata obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.isPlusEnabled);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isSyncEnabled);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isPagePeriodicSyncEnabled);
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isPageTickleSyncEnabled);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
