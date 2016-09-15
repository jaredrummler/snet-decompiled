package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class WakeLockEventCreator implements Creator<WakeLockEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    public WakeLockEvent createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        long _local_safe_0a1b_mTimeMillis = 0;
        int _local_safe_0a1b_mEventType = 0;
        String _local_safe_0a1b_mWakeLockName = null;
        int _local_safe_0a1b_mWakeLockType = 0;
        List<String> _local_safe_0a1b_mCallingPackages = null;
        String _local_safe_0a1b_mEventKey = null;
        long _local_safe_0a1b_mElapsedRealtime = 0;
        int _local_safe_0a1b_mDeviceState = 0;
        String _local_safe_0a1b_mSecondaryWakeLockName = null;
        String _local_safe_0a1b_mHostPackageName = null;
        float _local_safe_0a1b_mBeginPowerPercentage = 0.0f;
        long _local_safe_0a1b_mTimeout = 0;
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
                    _local_safe_0a1b_mWakeLockName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mWakeLockType = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mCallingPackages = SafeParcelReader.createStringList(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mElapsedRealtime = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mSecondaryWakeLockName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mEventType = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mEventKey = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mHostPackageName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                    _local_safe_0a1b_mDeviceState = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                    _local_safe_0a1b_mBeginPowerPercentage = SafeParcelReader.readFloat(parcel, header);
                    break;
                case LogSource.GMS_CORE_PEOPLE /*16*/:
                    _local_safe_0a1b_mTimeout = SafeParcelReader.readLong(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new WakeLockEvent(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mTimeMillis, _local_safe_0a1b_mEventType, _local_safe_0a1b_mWakeLockName, _local_safe_0a1b_mWakeLockType, _local_safe_0a1b_mCallingPackages, _local_safe_0a1b_mEventKey, _local_safe_0a1b_mElapsedRealtime, _local_safe_0a1b_mDeviceState, _local_safe_0a1b_mSecondaryWakeLockName, _local_safe_0a1b_mHostPackageName, _local_safe_0a1b_mBeginPowerPercentage, _local_safe_0a1b_mTimeout);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public WakeLockEvent[] newArray(int size) {
        return new WakeLockEvent[size];
    }

    static void writeToParcel(WakeLockEvent obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeLong(parcel, 2, obj.getTimeMillis());
        SafeParcelWriter.writeString(parcel, 4, obj.getWakeLockName(), false);
        SafeParcelWriter.writeInt(parcel, 5, obj.getWakeLockType());
        SafeParcelWriter.writeStringList(parcel, 6, obj.getCallingPackages(), false);
        SafeParcelWriter.writeLong(parcel, 8, obj.getElapsedRealtime());
        SafeParcelWriter.writeString(parcel, 10, obj.getSecondaryWakeLockName(), false);
        SafeParcelWriter.writeInt(parcel, 11, obj.getEventType());
        SafeParcelWriter.writeString(parcel, 12, obj.getEventKey(), false);
        SafeParcelWriter.writeString(parcel, 13, obj.getHostPackage(), false);
        SafeParcelWriter.writeInt(parcel, 14, obj.getDeviceState());
        SafeParcelWriter.writeFloat(parcel, 15, obj.getBeginPowerPercentage());
        SafeParcelWriter.writeLong(parcel, 16, obj.getTimeout());
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
