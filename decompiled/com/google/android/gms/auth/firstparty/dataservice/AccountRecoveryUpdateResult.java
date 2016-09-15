package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.RecoveryResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "AccountRecoveryUpdateResultCreator")
public class AccountRecoveryUpdateResult implements SafeParcelable, RecoveryResponse {
    public static final AccountRecoveryUpdateResultCreator CREATOR;
    private static final int VERSION = 0;
    @Field(id = 2)
    public final String error;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountRecoveryUpdateResultCreator();
    }

    @Constructor
    AccountRecoveryUpdateResult(@Param(id = 1) int version, @Param(id = 2) String error) {
        this.version = version;
        this.error = error;
    }

    public AccountRecoveryUpdateResult(String error) {
        this(0, error);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountRecoveryUpdateResultCreator.writeToParcel(this, dest, flags);
    }
}
