package com.google.android.gms.auth.frp;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "UnlockFactoryResetProtectionRequestCreator")
public class UnlockFactoryResetProtectionRequest implements SafeParcelable {
    public static final UnlockFactoryResetProtectionRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final String accountName;
    @Field(id = 4)
    public final String accountType;
    @Field(id = 3)
    public final String password;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new UnlockFactoryResetProtectionRequestCreator();
    }

    public static final UnlockFactoryResetProtectionRequest from(String accountName, String password) {
        return new UnlockFactoryResetProtectionRequest(VERSION, accountName, password, null);
    }

    public static final UnlockFactoryResetProtectionRequest from(String accountName, String password, String accountType) {
        return new UnlockFactoryResetProtectionRequest(VERSION, accountName, password, accountType);
    }

    @Constructor
    UnlockFactoryResetProtectionRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) String password, @Param(id = 4) String accountType) {
        this.version = version;
        this.accountName = accountName;
        this.password = password;
        this.accountType = accountType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        UnlockFactoryResetProtectionRequestCreator.writeToParcel(this, dest, flags);
    }
}
