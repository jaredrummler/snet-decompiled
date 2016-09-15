package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class AlarmEventCreator implements Creator<AlarmEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AlarmEvent createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        long _local_safe_0a1b_mTimeMillis = 0;
        int _local_safe_0a1b_mType = 0;
        String _local_safe_0a1b_mName = null;
        long _local_safe_0a1b_mTriggerAfterMillis = 0;
        long _local_safe_0a1b_mWindowMillis = 0;
        long _local_safe_0a1b_mIntervalMillis = 0;
        List<String> _local_safe_0a1b_mCallingPackages = null;
        int _local_safe_0a1b_mFlags = 0;
        int _local_safe_0a1b_mDeviceState = 0;
        String _local_safe_0a1b_mHostPackageName = null;
        float _local_safe_0a1b_mPowerPercentage = 0.0f;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mTimeMillis = SafeParcelReader.readLong(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mType = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mTriggerAfterMillis = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mWindowMillis = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mIntervalMillis = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mCallingPackages = SafeParcelReader.createStringList(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mFlags = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mDeviceState = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mHostPackageName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mPowerPercentage = SafeParcelReader.readFloat(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AlarmEvent(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mTimeMillis, _local_safe_0a1b_mType, _local_safe_0a1b_mName, _local_safe_0a1b_mTriggerAfterMillis, _local_safe_0a1b_mWindowMillis, _local_safe_0a1b_mIntervalMillis, _local_safe_0a1b_mCallingPackages, _local_safe_0a1b_mFlags, _local_safe_0a1b_mDeviceState, _local_safe_0a1b_mHostPackageName, _local_safe_0a1b_mPowerPercentage);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AlarmEvent[] newArray(int size) {
        return new AlarmEvent[size];
    }

    static void writeToParcel(AlarmEvent obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeLong(parcel, 2, obj.getTimeMillis());
        SafeParcelWriter.writeInt(parcel, 3, obj.getType());
        SafeParcelWriter.writeString(parcel, 4, obj.getName(), false);
        SafeParcelWriter.writeLong(parcel, 5, obj.getTriggerAfterMillis());
        SafeParcelWriter.writeLong(parcel, 6, obj.getWindowMillis());
        SafeParcelWriter.writeLong(parcel, 7, obj.getIntervalMillis());
        SafeParcelWriter.writeStringList(parcel, 8, obj.getCallingPackages(), false);
        SafeParcelWriter.writeInt(parcel, 9, obj.getFlags());
        SafeParcelWriter.writeInt(parcel, 10, obj.getDeviceState());
        SafeParcelWriter.writeString(parcel, 11, obj.getHostPackage(), false);
        SafeParcelWriter.writeFloat(parcel, 12, obj.getPowerPercentage());
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
