package com.google.android.gms.people.identity.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ParcelableGetOptionsCreator implements Creator<ParcelableGetOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ParcelableGetOptions createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        boolean _local_safe_0a1b_mUseOfflineDatabase = false;
        boolean _local_safe_0a1b_mUseWebData = false;
        boolean _local_safe_0a1b_mUseCp2 = false;
        String _local_safe_0a1b_mEndpoint = null;
        Bundle _local_safe_0a1b_mEndpointArguments = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mUseOfflineDatabase = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mUseWebData = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mEndpoint = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mUseCp2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mEndpointArguments = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 1000:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ParcelableGetOptions(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mUseOfflineDatabase, _local_safe_0a1b_mUseWebData, _local_safe_0a1b_mUseCp2, _local_safe_0a1b_mEndpoint, _local_safe_0a1b_mEndpointArguments);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ParcelableGetOptions[] newArray(int size) {
        return new ParcelableGetOptions[size];
    }

    static void writeToParcel(ParcelableGetOptions obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, obj.mUseOfflineDatabase);
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeBoolean(parcel, 2, obj.mUseWebData);
        SafeParcelWriter.writeString(parcel, 3, obj.mEndpoint, false);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.mUseCp2);
        SafeParcelWriter.writeBundle(parcel, 5, obj.mEndpointArguments, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
