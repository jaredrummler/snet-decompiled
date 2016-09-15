package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.Country;
import com.google.android.gms.auth.RecoveryResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "AccountRecoveryDataCreator")
public class AccountRecoveryData implements SafeParcelable, RecoveryResponse {
    public static final int ACTION_NONE = 3;
    public static final int ACTION_REQUEST_RECOVERY_INFO = 1;
    public static final int ACTION_VERIFY_RECOVERY_INFO = 2;
    public static final AccountRecoveryDataCreator CREATOR;
    public static final int DETAIL_EMAIL_AND_PHONE = 1003;
    public static final int DETAIL_EMAIL_ONLY = 1001;
    public static final int DETAIL_PHONE_ONLY = 1002;
    private static final int VERSION = 1;
    @Field(id = 11)
    public final Account account;
    @Field(id = 5)
    @Deprecated
    public final String accountName;
    @Field(id = 3)
    public final String action;
    @Field(id = 4)
    public final String allowedRecoveryOption;
    @Field(id = 8)
    public final List<Country> countries;
    @Field(id = 9)
    public final String defaultCountryCode;
    @Field(id = 10)
    public final String error;
    @Field(id = 2)
    public final AccountRecoveryGuidance guidance;
    @Field(id = 7)
    public final String phoneNumber;
    @Field(id = 6)
    public final String secondaryEmail;
    @VersionField(id = 1)
    public final int version;

    public static class Builder {
        private Account mmAccount;
        private String mmAccountName;
        private String mmAction;
        private List<Country> mmCountryList;
        private String mmDefaultCountryCode;
        private String mmDetail;
        private String mmError;
        private AccountRecoveryGuidance mmGuidance;
        private String mmPhoneNumber;
        private String mmSecondaryEmail;

        public Builder setError(String error) {
            this.mmError = error;
            return this;
        }

        public Builder setDefaultCountryCode(String countryCode) {
            this.mmDefaultCountryCode = countryCode;
            return this;
        }

        public Builder setCountryList(List<Country> countryList) {
            this.mmCountryList = Collections.unmodifiableList(new ArrayList(countryList));
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.mmPhoneNumber = phoneNumber;
            return this;
        }

        public Builder setSecondaryEmail(String secondaryEmail) {
            this.mmSecondaryEmail = secondaryEmail;
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.mmAccountName = accountName;
            return this;
        }

        public Builder setAllowedRecoveryOption(String detail) {
            this.mmDetail = detail;
            return this;
        }

        public Builder setAction(String action) {
            this.mmAction = action;
            return this;
        }

        public Builder setAccountRecoveryGuidance(AccountRecoveryGuidance guidance) {
            this.mmGuidance = guidance;
            return this;
        }

        public Builder setAccount(Account account) {
            this.mmAccount = account;
            return this;
        }

        public AccountRecoveryData build() {
            return new AccountRecoveryData(AccountRecoveryData.VERSION, this.mmGuidance, this.mmAction, this.mmDetail, this.mmAccountName, this.mmSecondaryEmail, this.mmPhoneNumber, this.mmCountryList, this.mmDefaultCountryCode, this.mmError, this.mmAccount);
        }
    }

    static {
        CREATOR = new AccountRecoveryDataCreator();
    }

    @Constructor
    AccountRecoveryData(@Param(id = 1) int version, @Param(id = 2) AccountRecoveryGuidance guidance, @Param(id = 3) String action, @Param(id = 4) String detail, @Param(id = 5) String accountName, @Param(id = 6) String secondaryEmail, @Param(id = 7) String phoneNumber, @Param(id = 8) List<Country> countries, @Param(id = 9) String defaultCountryCode, @Param(id = 10) String error, @Param(id = 11) Account account) {
        this.version = version;
        this.guidance = guidance;
        this.action = action;
        this.allowedRecoveryOption = detail;
        this.accountName = accountName;
        this.secondaryEmail = secondaryEmail;
        this.phoneNumber = phoneNumber;
        this.countries = countries == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(countries);
        this.defaultCountryCode = defaultCountryCode;
        this.error = error;
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
        AccountRecoveryDataCreator.writeToParcel(this, dest, flags);
    }
}
