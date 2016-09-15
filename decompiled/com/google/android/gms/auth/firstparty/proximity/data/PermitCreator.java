package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class PermitCreator implements Creator<Permit> {
    public static final int CONTENT_DESCRIPTION = 0;

    public Permit createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        String _local_safe_0a1b_mId = null;
        String _local_safe_0a1b_mAccountId = null;
        String _local_safe_0a1b_mType = null;
        PermitAccess _local_safe_0a1b_mLicense = null;
        List<PermitAccess> _local_safe_0a1b_mRequesterAccessesCache = null;
        List<String> _local_safe_0a1b_mAllowedChannelsCache = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mId = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAccountId = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mType = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mLicense = (PermitAccess) SafeParcelReader.createParcelable(parcel, header, PermitAccess.CREATOR);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mRequesterAccessesCache = SafeParcelReader.createTypedList(parcel, header, PermitAccess.CREATOR);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mAllowedChannelsCache = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new Permit(_local_safe_0a1b_mVersion, _local_safe_0a1b_mId, _local_safe_0a1b_mAccountId, _local_safe_0a1b_mType, _local_safe_0a1b_mLicense, (List) _local_safe_0a1b_mRequesterAccessesCache, (List) _local_safe_0a1b_mAllowedChannelsCache);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public Permit[] newArray(int size) {
        return new Permit[size];
    }

    static void writeToParcel(Permit obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeString(parcel, 2, obj.mId, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mAccountId, false);
        SafeParcelWriter.writeString(parcel, 5, obj.mType, false);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.mLicense, flags, false);
        SafeParcelWriter.writeTypedList(parcel, 7, obj.mRequesterAccessesCache, false);
        SafeParcelWriter.writeStringList(parcel, 8, obj.mAllowedChannelsCache, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
