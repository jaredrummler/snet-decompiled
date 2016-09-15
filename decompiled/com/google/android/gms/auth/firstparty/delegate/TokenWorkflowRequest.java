package com.google.android.gms.auth.firstparty.delegate;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "TokenWorkflowRequestCreator")
public class TokenWorkflowRequest implements SafeParcelable {
    public static final TokenWorkflowRequestCreator CREATOR;
    private static final int VERSION = 2;
    @Field(id = 9)
    Account account;
    @Field(id = 3)
    @Deprecated
    String accountName;
    @Field(id = 10)
    AccountAuthenticatorResponse amResponse;
    @Field(id = 8)
    AppDescription callingAppDescription;
    @Field(id = 5)
    FACLConfig faclData;
    @Field(id = 7)
    boolean isSuppressingProgressUx;
    @Field(defaultValueUnchecked = "new android.os.Bundle()", id = 4)
    Bundle options;
    @Field(id = 6)
    PACLConfig paclData;
    @Field(id = 2)
    String service;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new TokenWorkflowRequestCreator();
    }

    @Constructor
    TokenWorkflowRequest(@Param(id = 1) int version, @Param(id = 2) String service, @Param(id = 3) String accountName, @Param(id = 4) Bundle options, @Param(id = 5) FACLConfig faclData, @Param(id = 6) PACLConfig paclData, @Param(id = 7) boolean isSuppressingProgressUx, @Param(id = 8) AppDescription callingAppDescription, @Param(id = 9) Account account, @Param(id = 10) AccountAuthenticatorResponse amResponse) {
        this.version = version;
        this.service = service;
        this.accountName = accountName;
        this.options = options;
        this.faclData = faclData;
        this.paclData = paclData;
        this.isSuppressingProgressUx = isSuppressingProgressUx;
        this.callingAppDescription = callingAppDescription;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
        this.amResponse = amResponse;
    }

    public TokenWorkflowRequest() {
        this.version = VERSION;
        this.options = new Bundle();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        TokenWorkflowRequestCreator.writeToParcel(this, dest, flags);
    }

    public AccountAuthenticatorResponse getAmResponse() {
        return this.amResponse;
    }

    public TokenWorkflowRequest setAmResponse(AccountAuthenticatorResponse amResponse) {
        this.amResponse = amResponse;
        return this;
    }

    @Deprecated
    public String getAccountName() {
        return this.accountName;
    }

    @Deprecated
    public TokenWorkflowRequest setAccountName(String accountName) {
        this.account = TextUtils.isEmpty(accountName) ? null : new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        this.accountName = accountName;
        return this;
    }

    public String getService() {
        return this.service;
    }

    public TokenWorkflowRequest setService(String service) {
        this.service = service;
        return this;
    }

    public Bundle getOptions() {
        return new Bundle(this.options);
    }

    public TokenWorkflowRequest setOptions(Bundle options) {
        this.options.clear();
        if (options != null) {
            this.options.putAll(options);
        }
        return this;
    }

    public PACLConfig getPaclData() {
        return this.paclData;
    }

    public TokenWorkflowRequest setPaclData(PACLConfig paclData) {
        this.paclData = paclData;
        return this;
    }

    public FACLConfig getFaclData() {
        return this.faclData;
    }

    public TokenWorkflowRequest setFaclData(FACLConfig faclData) {
        this.faclData = faclData;
        return this;
    }

    public TokenWorkflowRequest setSuppressingProgressUx(boolean isSuppressingProgressUx) {
        this.isSuppressingProgressUx = isSuppressingProgressUx;
        return this;
    }

    public boolean isSuppressingProgressUx() {
        return this.isSuppressingProgressUx;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public TokenWorkflowRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public Account getAccount() {
        return this.account;
    }

    public TokenWorkflowRequest setAccount(Account account) {
        this.accountName = account == null ? null : account.name;
        this.account = account;
        return this;
    }
}
