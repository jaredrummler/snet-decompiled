package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "AccountNameCheckRequestCreator")
public class AccountNameCheckRequest implements SafeParcelable {
    public static final AccountNameCheckRequestCreator CREATOR;
    private static final int VERSION = 2;
    @Field(id = 2)
    @Deprecated
    String accountNameToCheck;
    @Field(id = 7)
    Account accountToCheck;
    @Field(id = 5)
    AppDescription callingAppDescription;
    @Field(id = 6)
    CaptchaSolution optionalCaptchaSolution;
    @Field(id = 3)
    String optionalFirstName;
    @Field(id = 4)
    String optionalLastName;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountNameCheckRequestCreator();
    }

    public AccountNameCheckRequest() {
        this.version = VERSION;
    }

    @Deprecated
    public AccountNameCheckRequest(String accountNameToCheck) {
        this.version = VERSION;
        this.accountNameToCheck = accountNameToCheck;
    }

    public AccountNameCheckRequest(Account account) {
        this.version = VERSION;
        this.accountToCheck = account;
    }

    @Constructor
    AccountNameCheckRequest(@Param(id = 1) int version, @Param(id = 2) String accountNameToCheck, @Param(id = 3) String optionalFirstName, @Param(id = 4) String optionalLastName, @Param(id = 5) AppDescription callingAppDescription, @Param(id = 6) CaptchaSolution optionalCaptchaSolution, @Param(id = 7) Account accountToCheck) {
        this.version = version;
        this.accountNameToCheck = accountNameToCheck;
        this.optionalFirstName = optionalFirstName;
        this.optionalLastName = optionalLastName;
        this.callingAppDescription = callingAppDescription;
        this.optionalCaptchaSolution = optionalCaptchaSolution;
        if (accountToCheck != null || TextUtils.isEmpty(accountNameToCheck)) {
            this.accountToCheck = accountToCheck;
        } else {
            this.accountToCheck = new Account(accountNameToCheck, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountNameCheckRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    @Deprecated
    public String getAccountNameToCheck() {
        return this.accountNameToCheck;
    }

    @Deprecated
    public AccountNameCheckRequest setAccountNameToCheck(String accountName) {
        this.accountNameToCheck = accountName;
        return this;
    }

    public String getFirstName() {
        return this.optionalFirstName;
    }

    public AccountNameCheckRequest setFirstName(String optionalFirstName) {
        this.optionalFirstName = optionalFirstName;
        return this;
    }

    public String getLastName() {
        return this.optionalLastName;
    }

    public AccountNameCheckRequest setLastName(String optionalLastName) {
        this.optionalLastName = optionalLastName;
        return this;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public AccountNameCheckRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public CaptchaSolution getCaptchaSolution() {
        return this.optionalCaptchaSolution;
    }

    public AccountNameCheckRequest setCaptchaSolution(CaptchaSolution captchaSolution) {
        this.optionalCaptchaSolution = captchaSolution;
        return this;
    }

    public Account getAccountToCheck() {
        return this.accountToCheck;
    }

    public AccountNameCheckRequest setAccountToCheck(Account accountToCheck) {
        this.accountToCheck = accountToCheck;
        return this;
    }
}
