package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ConnectionResultCreator implements Creator<ConnectionResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ConnectionResult createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mStatusCode = 0;
        PendingIntent _local_safe_0a1b_mPendingIntent = null;
        String _local_safe_0a1b_mStatusMessage = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mStatusCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mPendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mStatusMessage = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ConnectionResult(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mStatusCode, _local_safe_0a1b_mPendingIntent, _local_safe_0a1b_mStatusMessage);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ConnectionResult[] newArray(int size) {
        return new ConnectionResult[size];
    }

    static void writeToParcel(ConnectionResult obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.getErrorCode());
        SafeParcelWriter.writeParcelable(parcel, 3, obj.getResolution(), flags, false);
        SafeParcelWriter.writeString(parcel, 4, obj.getErrorMessage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
