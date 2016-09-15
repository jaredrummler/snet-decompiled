package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.List;

public class ScopeDetailCreator implements Creator<ScopeDetail> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ScopeDetail createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_description = null;
        String _local_safe_0a1b_detail = null;
        String _local_safe_0a1b_iconBase64 = null;
        String _local_safe_0a1b_paclPickerDataBase64 = null;
        String _local_safe_0a1b_service = null;
        List<String> _local_safe_0a1b_warnings = new ArrayList();
        FACLData _local_safe_0a1b_friendPickerData = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_description = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_detail = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_iconBase64 = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_paclPickerDataBase64 = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_service = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_warnings = SafeParcelReader.createStringList(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_friendPickerData = (FACLData) SafeParcelReader.createParcelable(parcel, header, FACLData.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ScopeDetail(_local_safe_0a1b_version, _local_safe_0a1b_description, _local_safe_0a1b_detail, _local_safe_0a1b_iconBase64, _local_safe_0a1b_paclPickerDataBase64, _local_safe_0a1b_service, _local_safe_0a1b_warnings, _local_safe_0a1b_friendPickerData);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ScopeDetail[] newArray(int size) {
        return new ScopeDetail[size];
    }

    static void writeToParcel(ScopeDetail obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.description, false);
        SafeParcelWriter.writeString(parcel, 3, obj.detail, false);
        SafeParcelWriter.writeString(parcel, 4, obj.iconBase64, false);
        SafeParcelWriter.writeString(parcel, 5, obj.paclPickerDataBase64, false);
        SafeParcelWriter.writeString(parcel, 6, obj.service, false);
        SafeParcelWriter.writeStringList(parcel, 7, obj.warnings, false);
        SafeParcelWriter.writeParcelable(parcel, 8, obj.friendPickerData, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
