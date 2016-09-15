package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "SignInRequestCreator")
public class SignInRequest implements SafeParcelable {
    public static final Creator<SignInRequest> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getResolveAccountRequest", id = 2)
    final ResolveAccountRequest mResolveAccountRequest;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new SignInRequestCreator();
    }

    @Constructor
    SignInRequest(@Param(id = 1) int versionCode, @Param(id = 2) ResolveAccountRequest resolveAccountRequest) {
        this.mVersionCode = versionCode;
        this.mResolveAccountRequest = resolveAccountRequest;
    }

    public SignInRequest(ResolveAccountRequest resolveAccountRequest) {
        this(VERSION_CODE, resolveAccountRequest);
    }

    public ResolveAccountRequest getResolveAccountRequest() {
        return this.mResolveAccountRequest;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        SignInRequestCreator.writeToParcel(this, dest, flags);
    }
}
