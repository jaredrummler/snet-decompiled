package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "RecoveryWriteRequestCreator")
public class RecoveryWriteRequest implements SafeParcelable {
    public static final RecoveryWriteRequestCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    public String mAccount;
    @Field(id = 6)
    public boolean mIsBroadUse;
    @Field(id = 5)
    public String mPhoneCountryCode;
    @Field(id = 4)
    public String mPhoneNumber;
    @Field(id = 3)
    public String mSecondaryEmail;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new RecoveryWriteRequestCreator();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        RecoveryWriteRequestCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public RecoveryWriteRequest() {
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    RecoveryWriteRequest(@Param(id = 1) int versionCode, @Param(id = 2) String account, @Param(id = 3) String secondaryEmail, @Param(id = 4) String phoneNumber, @Param(id = 5) String phoneCountryCode, @Param(id = 6) boolean isBroadUse) {
        this.mVersionCode = versionCode;
        this.mAccount = account;
        this.mSecondaryEmail = secondaryEmail;
        this.mPhoneNumber = phoneNumber;
        this.mPhoneCountryCode = phoneCountryCode;
        this.mIsBroadUse = isBroadUse;
    }
}
