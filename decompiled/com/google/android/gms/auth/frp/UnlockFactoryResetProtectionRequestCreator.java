package com.google.android.gms.auth.frp;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class UnlockFactoryResetProtectionRequestCreator implements Creator<UnlockFactoryResetProtectionRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public UnlockFactoryResetProtectionRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountName = null;
        String _local_safe_0a1b_password = null;
        String _local_safe_0a1b_accountType = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_password = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_accountType = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new UnlockFactoryResetProtectionRequest(_local_safe_0a1b_version, _local_safe_0a1b_accountName, _local_safe_0a1b_password, _local_safe_0a1b_accountType);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public UnlockFactoryResetProtectionRequest[] newArray(int size) {
        return new UnlockFactoryResetProtectionRequest[size];
    }

    static void writeToParcel(UnlockFactoryResetProtectionRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeString(parcel, 3, obj.password, false);
        SafeParcelWriter.writeString(parcel, 4, obj.accountType, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
