package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "AccountRemovalRequestCreator")
public class AccountRemovalRequest implements SafeParcelable {
    public static final AccountRemovalRequestCreator CREATOR;
    private static final int VERSION = 2;
    @Field(id = 3)
    Account account;
    @Field(id = 2)
    @Deprecated
    String accountName;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountRemovalRequestCreator();
    }

    public AccountRemovalRequest() {
        this.version = VERSION;
    }

    @Constructor
    AccountRemovalRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) Account account) {
        this.version = version;
        this.accountName = accountName;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountRemovalRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public AccountRemovalRequest setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public Account getAccount() {
        return this.account;
    }

    public AccountRemovalRequest setAccount(Account account) {
        this.account = account;
        return this;
    }
}
