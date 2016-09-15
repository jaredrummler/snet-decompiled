package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class SignInResponseCreator implements Creator<SignInResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SignInResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        ConnectionResult _local_safe_0a1b_mConnectionResult = null;
        ResolveAccountResponse _local_safe_0a1b_mResolveAccountResponse = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mConnectionResult = (ConnectionResult) SafeParcelReader.createParcelable(parcel, header, ConnectionResult.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mResolveAccountResponse = (ResolveAccountResponse) SafeParcelReader.createParcelable(parcel, header, ResolveAccountResponse.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SignInResponse(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mConnectionResult, _local_safe_0a1b_mResolveAccountResponse);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SignInResponse[] newArray(int size) {
        return new SignInResponse[size];
    }

    static void writeToParcel(SignInResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getConnectionResult(), flags, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.getResolveAccountResponse(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
