package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.google.android.gms.auth.firstparty.shared.PACLConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "TokenRequestCreator")
public class TokenRequest implements SafeParcelable {
    public static final TokenRequestCreator CREATOR;
    public static final int DELEGATION_TYPE_CHILD_IMPERSONATION = 1;
    public static final int DELEGATION_TYPE_UNKNOWN = 0;
    private static final int VERSION = 4;
    @Field(id = 3)
    String accountName;
    @Field(defaultValue = "com.google", id = 15)
    String accountType;
    @Field(id = 10)
    AppDescription callingAppDescription;
    @Field(id = 18)
    String consentCookieWrapper;
    @Field(id = 17)
    String delegateeUserId;
    @Field(defaultValueUnchecked = "TokenRequest.DELEGATION_TYPE_UNKNOWN", id = 16)
    int delegationType;
    @Field(id = 5)
    FACLConfig faclData;
    @Field(id = 7)
    boolean isAddingAccount;
    @Field(id = 8)
    boolean isCreatingAccount;
    @Field(defaultValue = "false", id = 13)
    boolean isForcingDroidguardRun;
    @Field(defaultValue = "true", id = 14)
    boolean isUsingCache;
    @Field(defaultValueUnchecked = "TokenRequest.Consent.UNKNOWN.toString()", id = 9)
    String mConsent;
    @Field(id = 11)
    CaptchaSolution optionalCaptchaSolution;
    @Field(defaultValueUnchecked = "new android.os.Bundle()", id = 4)
    Bundle options;
    @Field(id = 6)
    PACLConfig paclData;
    @Field(id = 2)
    String service;
    @VersionField(id = 1)
    final int version;

    public enum Consent {
        UNKNOWN,
        GRANTED,
        REJECTED
    }

    static {
        CREATOR = new TokenRequestCreator();
    }

    @Constructor
    TokenRequest(@Param(id = 1) int version, @Param(id = 2) String service, @Param(id = 3) String accountName, @Param(id = 4) Bundle options, @Param(id = 5) FACLConfig faclData, @Param(id = 6) PACLConfig paclData, @Param(id = 7) boolean isAddingAccount, @Param(id = 8) boolean isCreatingAccount, @Param(id = 9) String consent, @Param(id = 10) AppDescription callingAppDescription, @Param(id = 11) CaptchaSolution optionalCaptchaSolution, @Param(id = 13) boolean isForcingDroidguardRun, @Param(id = 14) boolean isUsingCache, @Param(id = 15) String accountType, @Param(id = 16) int delegationType, @Param(id = 17) String delegateeUserId, @Param(id = 18) String consentCookieWrapper) {
        this.options = new Bundle();
        this.mConsent = Consent.UNKNOWN.toString();
        this.isForcingDroidguardRun = false;
        this.isUsingCache = true;
        this.accountType = GoogleLoginServiceConstants.ACCOUNT_TYPE;
        this.delegationType = DELEGATION_TYPE_UNKNOWN;
        this.version = version;
        this.service = service;
        this.accountName = accountName;
        this.options = options;
        this.faclData = faclData;
        this.paclData = paclData;
        this.isAddingAccount = isAddingAccount;
        this.isCreatingAccount = isCreatingAccount;
        this.mConsent = consent;
        this.callingAppDescription = callingAppDescription;
        this.optionalCaptchaSolution = optionalCaptchaSolution;
        this.isForcingDroidguardRun = isForcingDroidguardRun;
        this.isUsingCache = isUsingCache;
        this.accountType = accountType;
        this.delegationType = delegationType;
        this.delegateeUserId = delegateeUserId;
        this.consentCookieWrapper = consentCookieWrapper;
    }

    public TokenRequest(Account account, String service) {
        this(account.name, account.type, service);
    }

    @Deprecated
    public TokenRequest(@Nullable String accountName, String accountType, String service) {
        this.options = new Bundle();
        this.mConsent = Consent.UNKNOWN.toString();
        this.isForcingDroidguardRun = false;
        this.isUsingCache = true;
        this.accountType = GoogleLoginServiceConstants.ACCOUNT_TYPE;
        this.delegationType = DELEGATION_TYPE_UNKNOWN;
        this.version = VERSION;
        this.accountName = accountName;
        this.accountType = accountType;
        this.service = service;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public TokenRequest setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public Account getAccount() {
        if (TextUtils.isEmpty(this.accountName)) {
            return null;
        }
        return new Account(this.accountName, this.accountType);
    }

    public String getService() {
        return this.service;
    }

    public TokenRequest setService(String service) {
        this.service = service;
        return this;
    }

    public Bundle getOptions() {
        return new Bundle(this.options);
    }

    public TokenRequest setOptions(Bundle options) {
        this.options.clear();
        if (options != null) {
            this.options.putAll(options);
        }
        return this;
    }

    public TokenRequest setPaclData(PACLConfig paclData) {
        this.paclData = paclData;
        return this;
    }

    public PACLConfig getPaclData() {
        return this.paclData;
    }

    public TokenRequest setFaclData(FACLConfig faclData) {
        this.faclData = faclData;
        return this;
    }

    public FACLConfig getFaclData() {
        return this.faclData;
    }

    public TokenRequest setAddingAccount(boolean isAdding) {
        this.isAddingAccount = isAdding;
        return this;
    }

    public boolean isAddingAccount() {
        return this.isAddingAccount;
    }

    public TokenRequest setCreatingAccount(boolean isCreating) {
        this.isCreatingAccount = isCreating;
        return this;
    }

    public boolean isCreatingAccount() {
        return this.isCreatingAccount;
    }

    public boolean isForcingDroidguardRun() {
        return this.isForcingDroidguardRun;
    }

    public TokenRequest setForcingDroidguardRun(boolean isForcingDroidguardRun) {
        this.isForcingDroidguardRun = isForcingDroidguardRun;
        return this;
    }

    public boolean isUsingCache() {
        return this.isUsingCache;
    }

    public TokenRequest setUsingCache(boolean isUsingCache) {
        this.isUsingCache = isUsingCache;
        return this;
    }

    public TokenRequest setConsent(Consent consent) {
        this.mConsent = ((Consent) Preconditions.checkNotNull(consent, " Consent cannot be null")).toString();
        return this;
    }

    public Consent getConsent() {
        return Consent.valueOf(this.mConsent);
    }

    public TokenRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public TokenRequest setCaptchaSolution(CaptchaSolution captchaSolution) {
        this.optionalCaptchaSolution = captchaSolution;
        return this;
    }

    public CaptchaSolution getCaptchaSolution() {
        return this.optionalCaptchaSolution;
    }

    public TokenRequest setDelegatee(int type, String userId) {
        this.delegationType = type;
        this.delegateeUserId = Preconditions.checkNotEmpty(userId);
        return this;
    }

    public int getDelegationType() {
        return this.delegationType;
    }

    public String getDelegateeUserId() {
        return this.delegateeUserId;
    }

    public String getConsentCookieWrapper() {
        return this.consentCookieWrapper;
    }

    public TokenRequest setConsentCookieWrapper(String consentCookieWrapper) {
        this.consentCookieWrapper = consentCookieWrapper;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        TokenRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return DELEGATION_TYPE_UNKNOWN;
    }
}
