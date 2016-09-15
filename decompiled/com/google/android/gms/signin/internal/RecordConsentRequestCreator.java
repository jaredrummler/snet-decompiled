package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class RecordConsentRequestCreator implements Creator<RecordConsentRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public RecordConsentRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        Account _local_safe_0a1b_mAccount = null;
        Scope[] _local_safe_0a1b_mScopesToConsent = null;
        String _local_safe_0a1b_mServerClientId = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAccount = (Account) SafeParcelReader.createParcelable(parcel, header, Account.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mScopesToConsent = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mServerClientId = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new RecordConsentRequest(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAccount, _local_safe_0a1b_mScopesToConsent, _local_safe_0a1b_mServerClientId);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public RecordConsentRequest[] newArray(int size) {
        return new RecordConsentRequest[size];
    }

    static void writeToParcel(RecordConsentRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getAccount(), flags, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, obj.getScopesToConsent(), flags, false);
        SafeParcelWriter.writeString(parcel, 4, obj.getServerClientId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
