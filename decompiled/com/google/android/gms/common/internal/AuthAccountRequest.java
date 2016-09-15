package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor.Stub;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@Class(creator = "AuthAccountRequestCreator")
public class AuthAccountRequest implements SafeParcelable {
    public static final Creator<AuthAccountRequest> CREATOR;
    private static final int VERSION_CODE = 2;
    @Field(id = 2)
    final IBinder mAccountAccessorBinder;
    @Field(id = 4)
    Integer mOauthPolicy;
    @Field(id = 5)
    Integer mPolicyAction;
    @Field(id = 3)
    final Scope[] mScopes;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new AuthAccountRequestCreator();
    }

    @Constructor
    AuthAccountRequest(@Param(id = 1) int versionCode, @Param(id = 2) IBinder accountAccessorBinder, @Param(id = 3) Scope[] scopes, @Param(id = 4) Integer oauthPolicy, @Param(id = 5) Integer policyAction) {
        this.mVersionCode = versionCode;
        this.mAccountAccessorBinder = accountAccessorBinder;
        this.mScopes = scopes;
        this.mOauthPolicy = oauthPolicy;
        this.mPolicyAction = policyAction;
    }

    public AuthAccountRequest(IAccountAccessor accountAccessor, Set<Scope> scopes) {
        this(VERSION_CODE, accountAccessor.asBinder(), (Scope[]) scopes.toArray(new Scope[scopes.size()]), null, null);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public Account getAccount() {
        if (this.mAccountAccessorBinder != null) {
            return AccountAccessor.getAccountBinderSafe(Stub.asInterface(this.mAccountAccessorBinder));
        }
        return null;
    }

    public Set<Scope> getScopes() {
        return new HashSet(Arrays.asList(this.mScopes));
    }

    public AuthAccountRequest setOauthPolicy(@Nullable Integer oauthPolicy) {
        this.mOauthPolicy = oauthPolicy;
        return this;
    }

    @Nullable
    public Integer getOauthPolicy() {
        return this.mOauthPolicy;
    }

    public AuthAccountRequest setPolicyAction(@Nullable Integer policyAction) {
        this.mPolicyAction = policyAction;
        return this;
    }

    @Nullable
    public Integer getPolicyAction() {
        return this.mPolicyAction;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AuthAccountRequestCreator.writeToParcel(this, dest, flags);
    }
}
