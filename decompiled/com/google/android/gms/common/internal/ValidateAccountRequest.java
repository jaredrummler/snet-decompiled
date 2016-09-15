package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor.Stub;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ValidateAccountRequestCreator")
@Deprecated
public class ValidateAccountRequest implements SafeParcelable {
    public static final Creator<ValidateAccountRequest> CREATOR;
    private static final String TAG = "ValidateAccountRequest";
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    final IBinder mAccountAccessorBinder;
    @Field(getter = "getCallingPackage", id = 6)
    private final String mCallingPackage;
    @Field(getter = "getClientVersion", id = 2)
    private final int mClientVersion;
    @Field(getter = "getExtraArgs", id = 5)
    private final Bundle mExtraArgs;
    @Field(getter = "getScopes", id = 4)
    private final Scope[] mScopes;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ValidateAccountRequestCreator();
    }

    @Constructor
    ValidateAccountRequest(@Param(id = 1) int versionCode, @Param(id = 2) int clientVersion, @Param(id = 3) IBinder accountAccessorBinder, @Param(id = 4) Scope[] scopes, @Param(id = 5) Bundle extraArgs, @Param(id = 6) String callingPackage) {
        this.mVersionCode = versionCode;
        this.mClientVersion = clientVersion;
        this.mAccountAccessorBinder = accountAccessorBinder;
        this.mScopes = scopes;
        this.mExtraArgs = extraArgs;
        this.mCallingPackage = callingPackage;
    }

    public ValidateAccountRequest(IAccountAccessor accountAccessor, Scope[] scopes, String callingPackage, Bundle extraArgs) {
        this(VERSION_CODE, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, accountAccessor == null ? null : accountAccessor.asBinder(), scopes, extraArgs, callingPackage);
    }

    public int getClientVersion() {
        return this.mClientVersion;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public Scope[] getScopes() {
        return this.mScopes;
    }

    public Account getAccount() throws RemoteException {
        if (this.mAccountAccessorBinder != null) {
            return AccountAccessor.getAccountBinderSafe(Stub.asInterface(this.mAccountAccessorBinder));
        }
        return null;
    }

    public String getCallingPackage() {
        return this.mCallingPackage;
    }

    public Bundle getExtraArgs() {
        return this.mExtraArgs;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ValidateAccountRequestCreator.writeToParcel(this, dest, flags);
    }
}
