package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "CheckFactoryResetPolicyComplianceRequestCreator")
public class CheckFactoryResetPolicyComplianceRequest implements SafeParcelable {
    public static final CheckFactoryResetPolicyComplianceRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final String accountId;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new CheckFactoryResetPolicyComplianceRequestCreator();
    }

    public static CheckFactoryResetPolicyComplianceRequest from(String accountId) {
        return new CheckFactoryResetPolicyComplianceRequest(VERSION, accountId);
    }

    @Constructor
    CheckFactoryResetPolicyComplianceRequest(@Param(id = 1) int version, @Param(id = 2) String accountId) {
        this.version = version;
        this.accountId = accountId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        CheckFactoryResetPolicyComplianceRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
