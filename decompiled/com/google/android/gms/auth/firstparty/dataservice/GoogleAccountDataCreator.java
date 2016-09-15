package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class GoogleAccountDataCreator implements Creator<GoogleAccountData> {
    public static final int CONTENT_DESCRIPTION = 0;

    public GoogleAccountData createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_accountName = null;
        boolean _local_safe_0a1b_isBrowserFlowRequired = false;
        List<String> _local_safe_0a1b_services = null;
        String _local_safe_0a1b_firstName = null;
        String _local_safe_0a1b_lastName = null;
        Account _local_safe_0a1b_account = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_accountName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_isBrowserFlowRequired = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_services = SafeParcelReader.createStringList(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_firstName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_lastName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_account = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new GoogleAccountData(_local_safe_0a1b_version, _local_safe_0a1b_accountName, _local_safe_0a1b_isBrowserFlowRequired, _local_safe_0a1b_services, _local_safe_0a1b_firstName, _local_safe_0a1b_lastName, _local_safe_0a1b_account);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public GoogleAccountData[] newArray(int size) {
        return new GoogleAccountData[size];
    }

    static void writeToParcel(GoogleAccountData obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.accountName, false);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isBrowserFlowRequired);
        SafeParcelWriter.writeStringList(parcel, 4, obj.services, false);
        SafeParcelWriter.writeString(parcel, 5, obj.firstName, false);
        SafeParcelWriter.writeString(parcel, 6, obj.lastName, false);
        SafeParcelWriter.writeParcelable(parcel, 7, obj.account, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
