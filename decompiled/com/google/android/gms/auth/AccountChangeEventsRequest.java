package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "AccountChangeEventsRequestCreator")
public class AccountChangeEventsRequest implements SafeParcelable {
    public static final Creator<AccountChangeEventsRequest> CREATOR;
    private static final int VERSION = 1;
    @Field(id = 4)
    Account mAccount;
    @Field(id = 3)
    @Deprecated
    String mAccountName;
    @Field(id = 2)
    int mEventIndex;
    @VersionField(id = 1)
    final int mVersion;

    static {
        CREATOR = new AccountChangeEventsRequestCreator();
    }

    @Constructor
    AccountChangeEventsRequest(@Param(id = 1) int version, @Param(id = 2) int eventIndex, @Param(id = 3) String accountName, @Param(id = 4) Account account) {
        this.mVersion = version;
        this.mEventIndex = eventIndex;
        this.mAccountName = accountName;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.mAccount = account;
        } else {
            this.mAccount = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    public AccountChangeEventsRequest() {
        this.mVersion = VERSION;
    }

    public AccountChangeEventsRequest setEventIndex(int eventIndex) {
        this.mEventIndex = eventIndex;
        return this;
    }

    public AccountChangeEventsRequest setAccountName(String accountName) {
        this.mAccountName = accountName;
        return this;
    }

    public String getAccountName() {
        return this.mAccountName;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.mAccount = account;
        return this;
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public int getEventIndex() {
        return this.mEventIndex;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventsRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
