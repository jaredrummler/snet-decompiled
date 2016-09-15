package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ReauthSettingsResponseCreator implements Creator<ReauthSettingsResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ReauthSettingsResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        int _local_safe_0a1b_status = 0;
        PasswordSettings _local_safe_0a1b_password = null;
        PinSettings _local_safe_0a1b_pin = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_status = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_password = (PasswordSettings) SafeParcelReader.createParcelable(parcel, header, PasswordSettings.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_pin = (PinSettings) SafeParcelReader.createParcelable(parcel, header, PinSettings.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ReauthSettingsResponse(_local_safe_0a1b_version, _local_safe_0a1b_status, _local_safe_0a1b_password, _local_safe_0a1b_pin);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ReauthSettingsResponse[] newArray(int size) {
        return new ReauthSettingsResponse[size];
    }

    static void writeToParcel(ReauthSettingsResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeInt(parcel, 2, obj.status);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.password, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.pin, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
