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

@Class(creator = "AccountRecoveryGuidanceRequestCreator")
public class AccountRecoveryGuidanceRequest implements SafeParcelable {
    public static final AccountRecoveryGuidanceRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    public final Account account;
    @Field(id = 2)
    @Deprecated
    public final String accountName;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountRecoveryGuidanceRequestCreator();
    }

    @Constructor
    AccountRecoveryGuidanceRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) Account account) {
        this.version = version;
        this.accountName = accountName;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    public AccountRecoveryGuidanceRequest(String accountName) {
        this(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE));
    }

    public AccountRecoveryGuidanceRequest(Account account) {
        this(VERSION, account.name, account);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountRecoveryGuidanceRequestCreator.writeToParcel(this, dest, flags);
    }
}
