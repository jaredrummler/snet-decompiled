package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class LatencyTrackerCreator implements Creator<LatencyTracker> {
    public static final int CONTENT_DESCRIPTION = 0;

    public LatencyTracker createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        String _local_safe_0a1b_mName = null;
        long _local_safe_0a1b_mStartRealtimeMillis = 0;
        LatencyTracker _local_safe_0a1b_parent = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mStartRealtimeMillis = SafeParcelReader.readLong(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_parent = (LatencyTracker) SafeParcelReader.createParcelable(parcel, header, LatencyTracker.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new LatencyTracker(_local_safe_0a1b_mVersion, _local_safe_0a1b_mName, _local_safe_0a1b_mStartRealtimeMillis, _local_safe_0a1b_parent);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public LatencyTracker[] newArray(int size) {
        return new LatencyTracker[size];
    }

    static void writeToParcel(LatencyTracker obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeString(parcel, 2, obj.mName, false);
        SafeParcelWriter.writeLong(parcel, 3, obj.mStartRealtimeMillis);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.parent, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
