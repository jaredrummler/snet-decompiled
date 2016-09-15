package com.google.android.gms.people.identity.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "AccountTokenCreator")
public final class AccountToken implements SafeParcelable {
    public static final AccountTokenCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getAccountName", id = 1)
    private final String mAccountName;
    @Field(getter = "getPageId", id = 2)
    private final String mPageId;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        CREATOR = new AccountTokenCreator();
    }

    @Constructor
    AccountToken(@Param(id = 1000) int versionCode, @Param(id = 1) String accountName, @Param(id = 2) String pageId) {
        this.mVersionCode = versionCode;
        this.mAccountName = accountName;
        this.mPageId = pageId;
    }

    @VisibleForTesting
    public AccountToken(String accountName) {
        this(VERSION_CODE, accountName, null);
    }

    @VisibleForTesting
    public AccountToken(String accountName, String pageId) {
        this(VERSION_CODE, accountName, pageId);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String getAccountName() {
        return this.mAccountName;
    }

    public String getPageId() {
        return this.mPageId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        AccountTokenCreator.writeToParcel(this, out, flags);
    }
}
