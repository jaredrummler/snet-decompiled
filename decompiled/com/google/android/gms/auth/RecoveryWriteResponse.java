package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "RecoveryWriteResponseCreator")
public class RecoveryWriteResponse implements SafeParcelable {
    public static final RecoveryWriteResponseCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    public String mErrorCode;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new RecoveryWriteResponseCreator();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        RecoveryWriteResponseCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public RecoveryWriteResponse() {
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    RecoveryWriteResponse(@Param(id = 1) int versionCode, @Param(id = 2) String errorCode) {
        this.mVersionCode = versionCode;
        this.mErrorCode = errorCode;
    }
}
