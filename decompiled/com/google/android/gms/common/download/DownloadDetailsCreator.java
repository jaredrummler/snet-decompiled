package com.google.android.gms.common.download;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class DownloadDetailsCreator implements Creator<DownloadDetails> {
    public static final int CONTENT_DESCRIPTION = 0;

    public DownloadDetails createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        String _local_safe_0a1b_filename = null;
        String _local_safe_0a1b_url = null;
        long _local_safe_0a1b_sizeBytes = 0;
        String _local_safe_0a1b_sha1 = null;
        String _local_safe_0a1b_destination = null;
        int _local_safe_0a1b_minVersion = 0;
        int _local_safe_0a1b_maxVersion = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_filename = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_url = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_sizeBytes = SafeParcelReader.readLong(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_sha1 = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_destination = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_minVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_maxVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new DownloadDetails(_local_safe_0a1b_versionCode, _local_safe_0a1b_filename, _local_safe_0a1b_url, _local_safe_0a1b_sizeBytes, _local_safe_0a1b_sha1, _local_safe_0a1b_destination, _local_safe_0a1b_minVersion, _local_safe_0a1b_maxVersion);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public DownloadDetails[] newArray(int size) {
        return new DownloadDetails[size];
    }

    static void writeToParcel(DownloadDetails obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.filename, false);
        SafeParcelWriter.writeString(parcel, 3, obj.url, false);
        SafeParcelWriter.writeLong(parcel, 4, obj.sizeBytes);
        SafeParcelWriter.writeString(parcel, 5, obj.sha1, false);
        SafeParcelWriter.writeString(parcel, 6, obj.destination, false);
        SafeParcelWriter.writeInt(parcel, 7, obj.minVersion);
        SafeParcelWriter.writeInt(parcel, 8, obj.maxVersion);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
