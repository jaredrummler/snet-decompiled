package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class LogEventCreator implements Creator<LogEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    public LogEvent createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        long _local_safe_0a1b_eventTime = 0;
        long _local_safe_0a1b_eventUptime = 0;
        String _local_safe_0a1b_tag = null;
        byte[] _local_safe_0a1b_sourceExtensionBytes = null;
        Bundle _local_safe_0a1b_keyValuePairs = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_eventTime = SafeParcelReader.readLong(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_tag = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_sourceExtensionBytes = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_keyValuePairs = SafeParcelReader.createBundle(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_eventUptime = SafeParcelReader.readLong(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new LogEvent(_local_safe_0a1b_versionCode, _local_safe_0a1b_eventTime, _local_safe_0a1b_eventUptime, _local_safe_0a1b_tag, _local_safe_0a1b_sourceExtensionBytes, _local_safe_0a1b_keyValuePairs);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public LogEvent[] newArray(int size) {
        return new LogEvent[size];
    }

    static void writeToParcel(LogEvent obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, obj.eventTime);
        SafeParcelWriter.writeString(parcel, 3, obj.tag, false);
        SafeParcelWriter.writeByteArray(parcel, 4, obj.sourceExtensionBytes, false);
        SafeParcelWriter.writeBundle(parcel, 5, obj.keyValuePairs, false);
        SafeParcelWriter.writeLong(parcel, 6, obj.eventUptime);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
