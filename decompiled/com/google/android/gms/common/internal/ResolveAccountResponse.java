package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor.Stub;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ResolveAccountResponseCreator")
public class ResolveAccountResponse implements SafeParcelable {
    public static final Creator<ResolveAccountResponse> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    IBinder mAccountAccessorBinder;
    @Field(getter = "getConnectionResult", id = 3)
    private ConnectionResult mConnectionResult;
    @Field(getter = "isFromCrossClientAuth", id = 5)
    private boolean mIsFromCrossClientAuth;
    @Field(getter = "getSaveDefaultAccount", id = 4)
    private boolean mSaveDefaultAccount;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ResolveAccountResponseCreator();
    }

    @Constructor
    ResolveAccountResponse(@Param(id = 1) int versionCode, @Param(id = 2) IBinder accountAccessorBinder, @Param(id = 3) ConnectionResult connectionResult, @Param(id = 4) boolean saveDefaultAccount, @Param(id = 5) boolean isFromCrossClientAuth) {
        this.mVersionCode = versionCode;
        this.mAccountAccessorBinder = accountAccessorBinder;
        this.mConnectionResult = connectionResult;
        this.mSaveDefaultAccount = saveDefaultAccount;
        this.mIsFromCrossClientAuth = isFromCrossClientAuth;
    }

    public ResolveAccountResponse(ConnectionResult result) {
        this(VERSION_CODE, null, result, false, false);
    }

    public ResolveAccountResponse(int connectionResultStatusCode) {
        this(new ConnectionResult(connectionResultStatusCode, null));
    }

    public IAccountAccessor getAccountAccessor() {
        return Stub.asInterface(this.mAccountAccessorBinder);
    }

    public ResolveAccountResponse setAccountAccessor(IAccountAccessor accountAccessor) {
        this.mAccountAccessorBinder = accountAccessor == null ? null : accountAccessor.asBinder();
        return this;
    }

    public ConnectionResult getConnectionResult() {
        return this.mConnectionResult;
    }

    public boolean getSaveDefaultAccount() {
        return this.mSaveDefaultAccount;
    }

    public ResolveAccountResponse setSaveDefaultAccount(boolean saveDefaultAccount) {
        this.mSaveDefaultAccount = saveDefaultAccount;
        return this;
    }

    public boolean isFromCrossClientAuth() {
        return this.mIsFromCrossClientAuth;
    }

    public ResolveAccountResponse setIsFromCrossClientAuth(boolean isFromCrossClientAuth) {
        this.mIsFromCrossClientAuth = isFromCrossClientAuth;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ResolveAccountResponseCreator.writeToParcel(this, dest, flags);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse or = (ResolveAccountResponse) o;
        if (this.mConnectionResult.equals(or.mConnectionResult) && getAccountAccessor().equals(or.getAccountAccessor())) {
            return true;
        }
        return false;
    }
}
