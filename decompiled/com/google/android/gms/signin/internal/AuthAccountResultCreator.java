package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AuthAccountResultCreator implements Creator<AuthAccountResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AuthAccountResult createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mConnectionResultCode = 0;
        Intent _local_safe_0a1b_mRawAuthResultionIntent = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mConnectionResultCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mRawAuthResultionIntent = (Intent) SafeParcelReader.createParcelable(parcel, header, Intent.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AuthAccountResult(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mConnectionResultCode, _local_safe_0a1b_mRawAuthResultionIntent);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AuthAccountResult[] newArray(int size) {
        return new AuthAccountResult[size];
    }

    static void writeToParcel(AuthAccountResult obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.getConnectionResultCode());
        SafeParcelWriter.writeParcelable(parcel, 3, obj.getRawAuthResolutionIntent(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
