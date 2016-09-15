package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class CheckServerAuthResultCreator implements Creator<CheckServerAuthResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    public CheckServerAuthResult createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        boolean _local_safe_0a1b_mNewAuthCodeRequired = false;
        List<Scope> _local_safe_0a1b_mAdditionalScopes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mNewAuthCodeRequired = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAdditionalScopes = SafeParcelReader.createTypedList(parcel, header, Scope.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new CheckServerAuthResult(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mNewAuthCodeRequired, _local_safe_0a1b_mAdditionalScopes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public CheckServerAuthResult[] newArray(int size) {
        return new CheckServerAuthResult[size];
    }

    static void writeToParcel(CheckServerAuthResult obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.mNewAuthCodeRequired);
        SafeParcelWriter.writeTypedList(parcel, 3, obj.mAdditionalScopes, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
