package com.google.android.gms.auth.frp;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "UnlockFactoryResetProtectionResponseCreator")
public class UnlockFactoryResetProtectionResponse implements SafeParcelable {
    public static final UnlockFactoryResetProtectionResponseCreator CREATOR;
    public static final int STATUS_ERROR_INVALID_CREDENTIALS = 3;
    public static final int STATUS_ERROR_NETWORK = 2;
    public static final int STATUS_ERROR_NOT_COMPLIANT = 4;
    public static final int STATUS_ERROR_UNKNOWN = 1;
    public static final int STATUS_OK = 0;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final int status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new UnlockFactoryResetProtectionResponseCreator();
    }

    @Constructor
    UnlockFactoryResetProtectionResponse(@Param(id = 1) int version, @Param(id = 2) int status) {
        this.version = version;
        this.status = status;
    }

    public UnlockFactoryResetProtectionResponse(int status) {
        this(VERSION, status);
    }

    public int describeContents() {
        return STATUS_OK;
    }

    public void writeToParcel(Parcel dest, int flags) {
        UnlockFactoryResetProtectionResponseCreator.writeToParcel(this, dest, flags);
    }
}
