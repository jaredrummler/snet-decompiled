package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "RecordConsentRequestCreator")
public class RecordConsentRequest implements SafeParcelable {
    public static final Creator<RecordConsentRequest> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getAccount", id = 2)
    private final Account mAccount;
    @Field(getter = "getScopesToConsent", id = 3)
    private final Scope[] mScopesToConsent;
    @Field(getter = "getServerClientId", id = 4)
    private final String mServerClientId;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new RecordConsentRequestCreator();
    }

    @Constructor
    RecordConsentRequest(@Param(id = 1) int versionCode, @Param(id = 2) Account account, @Param(id = 3) Scope[] scopesToConsent, @Param(id = 4) String serverClientId) {
        this.mVersionCode = versionCode;
        this.mAccount = account;
        this.mScopesToConsent = scopesToConsent;
        this.mServerClientId = serverClientId;
    }

    public RecordConsentRequest(Account account, Scope[] scopesToConsent, String serverClientId) {
        this(VERSION_CODE, account, scopesToConsent, serverClientId);
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public Scope[] getScopesToConsent() {
        return this.mScopesToConsent;
    }

    public String getServerClientId() {
        return this.mServerClientId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        RecordConsentRequestCreator.writeToParcel(this, dest, flags);
    }
}
