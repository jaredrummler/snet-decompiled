package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ResolveAccountRequestCreator")
public class ResolveAccountRequest implements SafeParcelable {
    public static final Creator<ResolveAccountRequest> CREATOR;
    private static final int VERSION_CODE = 2;
    @Field(getter = "getAccount", id = 2)
    private final Account mAccount;
    @Field(getter = "getSessionId", id = 3)
    private final int mSessionId;
    @Field(getter = "getSignInAccountHint", id = 4)
    private final GoogleSignInAccount mSignInAccountHint;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ResolveAccountRequestCreator();
    }

    @Constructor
    ResolveAccountRequest(@Param(id = 1) int versionCode, @Param(id = 2) Account account, @Param(id = 3) int sessionId, @Param(id = 4) GoogleSignInAccount signInAccountHint) {
        this.mVersionCode = versionCode;
        this.mAccount = account;
        this.mSessionId = sessionId;
        this.mSignInAccountHint = signInAccountHint;
    }

    public ResolveAccountRequest(Account account, int sessionId, GoogleSignInAccount signInAccountHint) {
        this(VERSION_CODE, account, sessionId, signInAccountHint);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public int getSessionId() {
        return this.mSessionId;
    }

    @Nullable
    public GoogleSignInAccount getSignInAccountHint() {
        return this.mSignInAccountHint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ResolveAccountRequestCreator.writeToParcel(this, dest, flags);
    }
}
