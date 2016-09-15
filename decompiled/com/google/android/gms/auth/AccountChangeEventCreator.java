package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountChangeEventCreator implements Creator<AccountChangeEvent> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountChangeEvent createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersion = 0;
        long _local_safe_0a1b_mId = 0;
        String _local_safe_0a1b_mAccountName = null;
        int _local_safe_0a1b_mChangeType = 0;
        int _local_safe_0a1b_mEventIndex = 0;
        String _local_safe_0a1b_mChangeData = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersion = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mId = SafeParcelReader.readLong(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mAccountName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mChangeType = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mEventIndex = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mChangeData = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountChangeEvent(_local_safe_0a1b_mVersion, _local_safe_0a1b_mId, _local_safe_0a1b_mAccountName, _local_safe_0a1b_mChangeType, _local_safe_0a1b_mEventIndex, _local_safe_0a1b_mChangeData);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountChangeEvent[] newArray(int size) {
        return new AccountChangeEvent[size];
    }

    static void writeToParcel(AccountChangeEvent obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersion);
        SafeParcelWriter.writeLong(parcel, 2, obj.mId);
        SafeParcelWriter.writeString(parcel, 3, obj.mAccountName, false);
        SafeParcelWriter.writeInt(parcel, 4, obj.mChangeType);
        SafeParcelWriter.writeInt(parcel, 5, obj.mEventIndex);
        SafeParcelWriter.writeString(parcel, 6, obj.mChangeData, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
