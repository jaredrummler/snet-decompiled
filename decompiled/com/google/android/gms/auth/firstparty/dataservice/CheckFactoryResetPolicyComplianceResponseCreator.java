package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class CheckFactoryResetPolicyComplianceResponseCreator implements Creator<CheckFactoryResetPolicyComplianceResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public CheckFactoryResetPolicyComplianceResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        boolean _local_safe_0a1b_isCompliant = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_isCompliant = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new CheckFactoryResetPolicyComplianceResponse(_local_safe_0a1b_version, _local_safe_0a1b_isCompliant);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public CheckFactoryResetPolicyComplianceResponse[] newArray(int size) {
        return new CheckFactoryResetPolicyComplianceResponse[size];
    }

    static void writeToParcel(CheckFactoryResetPolicyComplianceResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.isCompliant);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
