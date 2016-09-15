package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ConnectionEventCreator implements Creator<ConnectionEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ConnectionEvent createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        long _local_safe_0a1b_mTimeMillis = 0;
        int _local_safe_0a1b_mEventType = 0;
        String _local_safe_0a1b_mCallingProcess = null;
        String _local_safe_0a1b_mCallingService = null;
        String _local_safe_0a1b_mTargetProcess = null;
        String _local_safe_0a1b_mTargetService = null;
        String _local_safe_0a1b_mStackTrace = null;
        String _local_safe_0a1b_mConnKey = null;
        long _local_safe_0a1b_mElapsedRealtime = 0;
        long _local_safe_0a1b_mHeapAlloc = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mTimeMillis = SafeParcelReader.readLong(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mCallingProcess = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mCallingService = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mTargetProcess = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mTargetService = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mStackTrace = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mElapsedRealtime = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mHeapAlloc = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mEventType = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mConnKey = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ConnectionEvent(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mTimeMillis, _local_safe_0a1b_mEventType, _local_safe_0a1b_mCallingProcess, _local_safe_0a1b_mCallingService, _local_safe_0a1b_mTargetProcess, _local_safe_0a1b_mTargetService, _local_safe_0a1b_mStackTrace, _local_safe_0a1b_mConnKey, _local_safe_0a1b_mElapsedRealtime, _local_safe_0a1b_mHeapAlloc);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ConnectionEvent[] newArray(int size) {
        return new ConnectionEvent[size];
    }

    static void writeToParcel(ConnectionEvent obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeLong(parcel, 2, obj.getTimeMillis());
        SafeParcelWriter.writeString(parcel, 4, obj.getCallingProcess(), false);
        SafeParcelWriter.writeString(parcel, 5, obj.getCallingService(), false);
        SafeParcelWriter.writeString(parcel, 6, obj.getTargetProcess(), false);
        SafeParcelWriter.writeString(parcel, 7, obj.getTargetService(), false);
        SafeParcelWriter.writeString(parcel, 8, obj.getStackTrace(), false);
        SafeParcelWriter.writeLong(parcel, 10, obj.getElapsedRealtime());
        SafeParcelWriter.writeLong(parcel, 11, obj.getHeapAlloc());
        SafeParcelWriter.writeInt(parcel, 12, obj.getEventType());
        SafeParcelWriter.writeString(parcel, 13, obj.getEventKey(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
