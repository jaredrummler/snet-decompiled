package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.RecoveryResponse;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "AccountRecoveryUpdateRequestCreator")
public class AccountRecoveryUpdateRequest implements SafeParcelable, RecoveryResponse {
    public static final AccountRecoveryUpdateRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 8)
    public final Account account;
    @Field(id = 2)
    public final String accountName;
    @Field(id = 7)
    public final AppDescription callingAppDescription;
    @Field(id = 6)
    public final boolean isBroadUse;
    @Field(id = 5)
    public final String phoneCountryCode;
    @Field(id = 4)
    public final String phoneNumber;
    @Field(id = 3)
    public final String secondaryEmail;
    @VersionField(id = 1)
    final int version;

    public static class Builder {
        private Account mmAccount;
        private String mmAccountName;
        private AppDescription mmCallingAppDescription;
        private boolean mmIsBroadUse;
        private String mmPhoneCountryCode;
        private String mmPhoneNumber;
        private String mmSecondaryEmail;

        public Builder setAccountName(String accountName) {
            this.mmAccountName = accountName;
            return this;
        }

        public Builder setSecondaryEmail(String secondaryEmail) {
            this.mmSecondaryEmail = secondaryEmail;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.mmPhoneNumber = phoneNumber;
            return this;
        }

        public Builder setPhoneCountryCode(String countryCode) {
            this.mmPhoneCountryCode = countryCode;
            return this;
        }

        public Builder setBroadUse(boolean isBroadUse) {
            this.mmIsBroadUse = isBroadUse;
            return this;
        }

        public Builder setCallingAppDescription(AppDescription appDescription) {
            this.mmCallingAppDescription = appDescription;
            return this;
        }

        public Builder setAccount(Account account) {
            this.mmAccount = account;
            return this;
        }

        public AccountRecoveryUpdateRequest build() {
            return new AccountRecoveryUpdateRequest(AccountRecoveryUpdateRequest.VERSION, this.mmAccountName, this.mmSecondaryEmail, this.mmPhoneNumber, this.mmPhoneCountryCode, this.mmIsBroadUse, this.mmCallingAppDescription, this.mmAccount);
        }
    }

    static {
        CREATOR = new AccountRecoveryUpdateRequestCreator();
    }

    @Constructor
    AccountRecoveryUpdateRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) String secondaryEmail, @Param(id = 4) String phoneNumber, @Param(id = 5) String phoneCountryCode, @Param(id = 6) boolean isBroadUse, @Param(id = 7) AppDescription callingAppDescription, @Param(id = 8) Account account) {
        this.version = version;
        this.accountName = accountName;
        this.secondaryEmail = secondaryEmail;
        this.phoneNumber = phoneNumber;
        this.phoneCountryCode = phoneCountryCode;
        this.isBroadUse = isBroadUse;
        this.callingAppDescription = callingAppDescription;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountRecoveryUpdateRequestCreator.writeToParcel(this, dest, flags);
    }
}
