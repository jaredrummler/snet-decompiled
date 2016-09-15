package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "CheckFactoryResetPolicyComplianceResponseCreator")
public class CheckFactoryResetPolicyComplianceResponse implements SafeParcelable {
    public static final CheckFactoryResetPolicyComplianceResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final boolean isCompliant;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new CheckFactoryResetPolicyComplianceResponseCreator();
    }

    public static CheckFactoryResetPolicyComplianceResponse from(boolean isCompliant) {
        return new CheckFactoryResetPolicyComplianceResponse(VERSION, isCompliant);
    }

    @Constructor
    CheckFactoryResetPolicyComplianceResponse(@Param(id = 1) int version, @Param(id = 2) boolean isCompliant) {
        this.version = version;
        this.isCompliant = isCompliant;
    }

    public void writeToParcel(Parcel dest, int flags) {
        CheckFactoryResetPolicyComplianceResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
