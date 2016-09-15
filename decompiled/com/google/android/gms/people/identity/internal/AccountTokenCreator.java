package com.google.android.gms.people.identity.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AccountTokenCreator implements Creator<AccountToken> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountToken createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mAccountName = null;
        String _local_safe_0a1b_mPageId = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mAccountName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mPageId = SafeParcelReader.createString(parcel, header);
                    break;
                case 1000:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountToken(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAccountName, _local_safe_0a1b_mPageId);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountToken[] newArray(int size) {
        return new AccountToken[size];
    }

    static void writeToParcel(AccountToken obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, obj.getAccountName(), false);
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeString(parcel, 2, obj.getPageId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
