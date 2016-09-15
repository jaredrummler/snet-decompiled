package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class GplusInfoResponseCreator implements Creator<GplusInfoResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GplusInfoResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        boolean _local_safe_0a1b_isAllowed = false;
        String _local_safe_0a1b_firstName = null;
        String _local_safe_0a1b_lastName = null;
        String _local_safe_0a1b_picasaUserName = null;
        boolean _local_safe_0a1b_isGooglePlusEnabled = false;
        boolean _local_safe_0a1b_isEsMobileEnabled = false;
        String _local_safe_0a1b_ropText = null;
        String _local_safe_0a1b_ropRevision = null;
        String _local_safe_0a1b_wireCode = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_isAllowed = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_firstName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_lastName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_picasaUserName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_isGooglePlusEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_isEsMobileEnabled = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_ropText = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_ropRevision = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_wireCode = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GplusInfoResponse(_local_safe_0a1b_version, _local_safe_0a1b_isAllowed, _local_safe_0a1b_firstName, _local_safe_0a1b_lastName, _local_safe_0a1b_picasaUserName, _local_safe_0a1b_isGooglePlusEnabled, _local_safe_0a1b_isEsMobileEnabled, _local_safe_0a1b_ropText, _local_safe_0a1b_ropRevision, _local_safe_0a1b_wireCode);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GplusInfoResponse[] newArray(int size) {
        return new GplusInfoResponse[size];
    }

    static void writeToParcel(GplusInfoResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.isAllowed);
        SafeParcelWriter.writeString(parcel, 3, obj.firstName, false);
        SafeParcelWriter.writeString(parcel, 4, obj.lastName, false);
        SafeParcelWriter.writeString(parcel, 5, obj.picasaUserName, false);
        SafeParcelWriter.writeBoolean(parcel, 6, obj.isGooglePlusEnabled);
        SafeParcelWriter.writeBoolean(parcel, 7, obj.isEsMobileEnabled);
        SafeParcelWriter.writeString(parcel, 8, obj.ropText, false);
        SafeParcelWriter.writeString(parcel, 9, obj.ropRevision, false);
        SafeParcelWriter.writeString(parcel, 10, obj.wireCode, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
