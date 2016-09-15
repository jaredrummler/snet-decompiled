package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PlayLoggerContextCreator implements Creator<PlayLoggerContext> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PlayLoggerContext createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_packageName = null;
        int _local_safe_0a1b_packageVersionCode = 0;
        int _local_safe_0a1b_logSource = 0;
        String _local_safe_0a1b_uploadAccountName = null;
        String _local_safe_0a1b_loggingId = null;
        boolean _local_safe_0a1b_logAndroidId = true;
        String _local_safe_0a1b_logSourceName = null;
        boolean _local_safe_0a1b_isAnonymous = false;
        int _local_safe_0a1b_qosTier = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_packageName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_packageVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_logSource = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_uploadAccountName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_loggingId = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_logAndroidId = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_logSourceName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_isAnonymous = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_qosTier = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PlayLoggerContext(_local_safe_0a1b_versionCode, _local_safe_0a1b_packageName, _local_safe_0a1b_packageVersionCode, _local_safe_0a1b_logSource, _local_safe_0a1b_uploadAccountName, _local_safe_0a1b_loggingId, _local_safe_0a1b_logAndroidId, _local_safe_0a1b_logSourceName, _local_safe_0a1b_isAnonymous, _local_safe_0a1b_qosTier);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PlayLoggerContext[] newArray(int size) {
        return new PlayLoggerContext[size];
    }

    static void writeToParcel(PlayLoggerContext obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.packageName, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.packageVersionCode);
        SafeParcelWriter.writeInt(parcel, 4, obj.logSource);
        SafeParcelWriter.writeString(parcel, 5, obj.uploadAccountName, false);
        SafeParcelWriter.writeString(parcel, 6, obj.loggingId, false);
        SafeParcelWriter.writeBoolean(parcel, 7, obj.logAndroidId);
        SafeParcelWriter.writeString(parcel, 8, obj.logSourceName, false);
        SafeParcelWriter.writeBoolean(parcel, 9, obj.isAnonymous);
        SafeParcelWriter.writeInt(parcel, 10, obj.qosTier);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
