package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class GetAndAdvanceOtpCounterResponseCreator implements Creator<GetAndAdvanceOtpCounterResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GetAndAdvanceOtpCounterResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        Long _local_safe_0a1b_counter = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_counter = SafeParcelReader.readLongObject(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GetAndAdvanceOtpCounterResponse(_local_safe_0a1b_mVersion, _local_safe_0a1b_counter);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GetAndAdvanceOtpCounterResponse[] newArray(int size) {
        return new GetAndAdvanceOtpCounterResponse[size];
    }

    static void writeToParcel(GetAndAdvanceOtpCounterResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeLongObject(parcel, 2, obj.counter, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
