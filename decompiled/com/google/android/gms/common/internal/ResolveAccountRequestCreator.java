package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ResolveAccountRequestCreator implements Creator<ResolveAccountRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ResolveAccountRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        Account _local_safe_0a1b_mAccount = null;
        int _local_safe_0a1b_mSessionId = 0;
        GoogleSignInAccount _local_safe_0a1b_mSignInAccountHint = null;
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
                    _local_safe_0a1b_mSessionId = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mSignInAccountHint = (GoogleSignInAccount) SafeParcelReader.createParcelable(parcel, header, GoogleSignInAccount.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ResolveAccountRequest(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAccount, _local_safe_0a1b_mSessionId, _local_safe_0a1b_mSignInAccountHint);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ResolveAccountRequest[] newArray(int size) {
        return new ResolveAccountRequest[size];
    }

    static void writeToParcel(ResolveAccountRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getAccount(), flags, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.getSessionId());
        SafeParcelWriter.writeParcelable(parcel, 4, obj.getSignInAccountHint(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
