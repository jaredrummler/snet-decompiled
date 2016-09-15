package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "SignInResponseCreator")
public class SignInResponse implements SafeParcelable {
    public static final Creator<SignInResponse> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getConnectionResult", id = 2)
    private final ConnectionResult mConnectionResult;
    @Field(getter = "getResolveAccountResponse", id = 3)
    private final ResolveAccountResponse mResolveAccountResponse;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new SignInResponseCreator();
    }

    @Constructor
    SignInResponse(@Param(id = 1) int versionCode, @Param(id = 2) ConnectionResult connectionResult, @Param(id = 3) ResolveAccountResponse resolveAccountResponse) {
        this.mVersionCode = versionCode;
        this.mConnectionResult = connectionResult;
        this.mResolveAccountResponse = resolveAccountResponse;
    }

    public SignInResponse(int connectionResultStatusCode) {
        this(new ConnectionResult(connectionResultStatusCode, null), null);
    }

    public SignInResponse(ConnectionResult result, ResolveAccountResponse resolveAccountResponse) {
        this(VERSION_CODE, result, resolveAccountResponse);
    }

    public ConnectionResult getConnectionResult() {
        return this.mConnectionResult;
    }

    public ResolveAccountResponse getResolveAccountResponse() {
        return this.mResolveAccountResponse;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        SignInResponseCreator.writeToParcel(this, dest, flags);
    }
}
