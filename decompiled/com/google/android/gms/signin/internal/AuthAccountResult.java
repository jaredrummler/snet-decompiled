package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "AuthAccountResultCreator")
public class AuthAccountResult implements SafeParcelable, Result {
    public static final Creator<AuthAccountResult> CREATOR;
    private static final int VERSION_CODE = 2;
    @Field(getter = "getConnectionResultCode", id = 2)
    private int mConnectionResultCode;
    @Field(getter = "getRawAuthResolutionIntent", id = 3)
    private Intent mRawAuthResultionIntent;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new AuthAccountResultCreator();
    }

    @Constructor
    AuthAccountResult(@Param(id = 1) int versionCode, @Param(id = 2) int connectionResultCode, @Param(id = 3) Intent rawAuthResultionIntent) {
        this.mVersionCode = versionCode;
        this.mConnectionResultCode = connectionResultCode;
        this.mRawAuthResultionIntent = rawAuthResultionIntent;
    }

    public AuthAccountResult() {
        this(0, null);
    }

    public AuthAccountResult(int connectionResultCode, Intent rawAuthResolutionIntent) {
        this(VERSION_CODE, connectionResultCode, rawAuthResolutionIntent);
    }

    public int getConnectionResultCode() {
        return this.mConnectionResultCode;
    }

    public Intent getRawAuthResolutionIntent() {
        return this.mRawAuthResultionIntent;
    }

    public Status getStatus() {
        if (this.mConnectionResultCode == 0) {
            return Status.RESULT_SUCCESS;
        }
        return Status.RESULT_CANCELED;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AuthAccountResultCreator.writeToParcel(this, dest, flags);
    }
}
