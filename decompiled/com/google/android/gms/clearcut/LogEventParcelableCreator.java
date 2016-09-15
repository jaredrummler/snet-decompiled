package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class LogEventParcelableCreator implements Creator<LogEventParcelable> {
    public static final int CONTENT_DESCRIPTION = 0;

    public LogEventParcelable createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        PlayLoggerContext _local_safe_0a1b_playLoggerContext = null;
        byte[] _local_safe_0a1b_logEventBytes = null;
        int[] _local_safe_0a1b_testCodes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_playLoggerContext = (PlayLoggerContext) SafeParcelReader.createParcelable(parcel, header, PlayLoggerContext.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_logEventBytes = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_testCodes = SafeParcelReader.createIntArray(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new LogEventParcelable(_local_safe_0a1b_versionCode, _local_safe_0a1b_playLoggerContext, _local_safe_0a1b_logEventBytes, _local_safe_0a1b_testCodes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public LogEventParcelable[] newArray(int size) {
        return new LogEventParcelable[size];
    }

    static void writeToParcel(LogEventParcelable obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.playLoggerContext, flags, false);
        SafeParcelWriter.writeByteArray(parcel, 3, obj.logEventBytes, false);
        SafeParcelWriter.writeIntArray(parcel, 4, obj.testCodes, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
