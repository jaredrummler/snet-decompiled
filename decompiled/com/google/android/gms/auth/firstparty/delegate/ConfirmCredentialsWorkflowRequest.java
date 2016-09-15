package com.google.android.gms.auth.firstparty.delegate;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "ConfirmCredentialsWorkflowRequestCreator")
public class ConfirmCredentialsWorkflowRequest implements SafeParcelable {
    public static final ConfirmCredentialsWorkflowRequestCreator CREATOR;
    private static final int VERSION = 3;
    @Field(id = 5)
    Account account;
    @Field(id = 2)
    @Deprecated
    String accountName;
    @Field(id = 6)
    AccountAuthenticatorResponse amResponse;
    @Field(id = 3)
    AppDescription callingAppDescription;
    @Field(defaultValueUnchecked = "new android.os.Bundle()", id = 4)
    Bundle options;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new ConfirmCredentialsWorkflowRequestCreator();
    }

    @Constructor
    ConfirmCredentialsWorkflowRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) AppDescription callingAppDescription, @Param(id = 4) Bundle options, @Param(id = 5) Account account, @Param(id = 6) AccountAuthenticatorResponse amResponse) {
        this.version = version;
        this.accountName = accountName;
        this.callingAppDescription = callingAppDescription;
        this.options = options;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
        this.amResponse = amResponse;
    }

    public ConfirmCredentialsWorkflowRequest() {
        this.version = VERSION;
        this.options = new Bundle();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ConfirmCredentialsWorkflowRequestCreator.writeToParcel(this, dest, flags);
    }

    public ConfirmCredentialsWorkflowRequest setAmResponse(AccountAuthenticatorResponse amResponse) {
        this.amResponse = amResponse;
        return this;
    }

    public AccountAuthenticatorResponse getAmResponse() {
        return this.amResponse;
    }

    @Deprecated
    public String getAccountName() {
        return this.accountName;
    }

    @Deprecated
    public ConfirmCredentialsWorkflowRequest setAccountName(String accountName) {
        this.account = TextUtils.isEmpty(accountName) ? null : new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        this.accountName = accountName;
        return this;
    }

    public Account getAccount() {
        return this.account;
    }

    public ConfirmCredentialsWorkflowRequest setAccount(Account account) {
        this.accountName = account == null ? null : account.name;
        this.account = account;
        return this;
    }

    public ConfirmCredentialsWorkflowRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public Bundle getOptions() {
        return new Bundle(this.options);
    }

    public ConfirmCredentialsWorkflowRequest setOptions(Bundle options) {
        this.options.clear();
        if (options != null) {
            this.options.putAll(options);
        }
        return this;
    }
}
