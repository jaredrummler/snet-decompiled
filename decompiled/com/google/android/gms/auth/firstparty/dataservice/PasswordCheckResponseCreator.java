package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PasswordCheckResponseCreator implements Creator<PasswordCheckResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PasswordCheckResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_status = null;
        String _local_safe_0a1b_passwordStrength = null;
        String _local_safe_0a1b_detail = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_status = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_passwordStrength = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_detail = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PasswordCheckResponse(_local_safe_0a1b_version, _local_safe_0a1b_status, _local_safe_0a1b_passwordStrength, _local_safe_0a1b_detail);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PasswordCheckResponse[] newArray(int size) {
        return new PasswordCheckResponse[size];
    }

    static void writeToParcel(PasswordCheckResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.status, false);
        SafeParcelWriter.writeString(parcel, 3, obj.passwordStrength, false);
        SafeParcelWriter.writeString(parcel, 4, obj.detail, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
