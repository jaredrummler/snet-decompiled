package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.TokenData;
import com.google.android.gms.auth.TokenData.Builder;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.internal.Preconditions;
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

@Class(creator = "TokenResponseCreator")
public class TokenResponse implements SafeParcelable {
    public static final TokenResponseCreator CREATOR;
    public static final String LOGGING_EXTRA_GADS_CONNECTION_LATENCY_MILLIS = "logging.gads_connection_latency_millis";
    public static final String LOGGING_EXTRA_INTERNAL_SERVICE_LATENCY_MILLIS = "logging.internal_service_latency_millis";
    private static final int VERSION = 6;
    @Field(id = 22)
    Account account;
    @Field(id = 2)
    @Deprecated
    String accountName;
    @Field(id = 14)
    CaptchaChallenge captcha;
    @Field(id = 29)
    String consentCookieWrapper;
    @Field(defaultValueUnchecked = "new android.os.Bundle()", id = 28)
    Bundle dataForLogging;
    @Field(id = 6)
    String detail;
    @Field(id = 26)
    String dmStatus;
    @Field(id = 8)
    String firstName;
    @Field(id = 19)
    boolean hasTitle;
    @Field(id = 13)
    boolean isBrowserSignInSuggested;
    @Field(id = 12)
    boolean isEsMobileServiceEnabled;
    @Field(id = 10)
    boolean isGPlusServiceAllowed;
    @Field(id = 11)
    boolean isGPlusServiceEnabled;
    @Field(id = 9)
    String lastName;
    @Field(id = 7)
    String picasaUser;
    @Field(id = 21)
    PostSignInData postSignInData;
    @Field(id = 17)
    String ropRevision;
    @Field(id = 16)
    String ropText;
    @Field(defaultValueUnchecked = "new java.util.ArrayList<com.google.android.gms.auth.firstparty.shared.ScopeDetail>()", id = 15)
    List<ScopeDetail> scopeData;
    @Field(id = 5)
    String signInUrl;
    @Field(id = 3)
    String statusWireCode;
    @Field(id = 20)
    int title;
    @Field(id = 4)
    @Deprecated
    String token;
    @Field(id = 27)
    TokenData tokenData;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new TokenResponseCreator();
    }

    @Constructor
    TokenResponse(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) String statusWireCode, @Param(id = 4) String token, @Param(id = 5) String signInUrl, @Param(id = 6) String detail, @Param(id = 7) String picasaUser, @Param(id = 8) String firstName, @Param(id = 9) String lastName, @Param(id = 10) boolean isGPlusServiceAllowed, @Param(id = 11) boolean isGPlusServiceEnabled, @Param(id = 12) boolean isEsMobileServiceEnabled, @Param(id = 13) boolean isBrowserSignInSuggested, @Param(id = 14) CaptchaChallenge captcha, @Param(id = 15) List<ScopeDetail> list, @Param(id = 16) String ropText, @Param(id = 17) String ropRevision, @Param(id = 19) boolean hasTitle, @Param(id = 20) int title, @Param(id = 21) PostSignInData postSignInData, @Param(id = 22) Account account, @Param(id = 26) String dmStatus, @Param(id = 27) TokenData tokenData, @Param(id = 28) Bundle dataForLogging, @Param(id = 29) String consentCookieWrapper) {
        this.dataForLogging = new Bundle();
        this.version = version;
        this.statusWireCode = statusWireCode;
        this.token = token;
        this.signInUrl = signInUrl;
        this.detail = detail;
        this.picasaUser = picasaUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isGPlusServiceAllowed = isGPlusServiceAllowed;
        this.isGPlusServiceEnabled = isGPlusServiceEnabled;
        this.isEsMobileServiceEnabled = isEsMobileServiceEnabled;
        this.isBrowserSignInSuggested = isBrowserSignInSuggested;
        this.captcha = captcha;
        if (list == null) {
            list = new ArrayList();
        }
        this.scopeData = list;
        this.ropText = ropText;
        this.ropRevision = ropRevision;
        this.hasTitle = hasTitle;
        this.title = title;
        this.postSignInData = postSignInData;
        this.dmStatus = dmStatus;
        this.dataForLogging = dataForLogging;
        this.consentCookieWrapper = consentCookieWrapper;
        if (account != null) {
            setAccount(account);
        } else {
            setAccountName(accountName);
        }
        if (token != null) {
            setTokenData(new Builder().setToken(token).build());
        } else {
            setTokenData(tokenData);
        }
    }

    public TokenResponse() {
        this.dataForLogging = new Bundle();
        this.version = VERSION;
        this.scopeData = new ArrayList();
    }

    @Deprecated
    public String getAccountName() {
        Account account = getAccount();
        return account == null ? null : account.name;
    }

    @Deprecated
    public TokenResponse setAccountName(String accountName) {
        if (!TextUtils.isEmpty(accountName)) {
            return setAccount(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE));
        }
        this.accountName = null;
        this.account = null;
        return this;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.statusWireCode);
    }

    public TokenResponse setStatus(Status status) {
        this.statusWireCode = ((Status) Preconditions.checkNotNull(status)).getWire();
        return this;
    }

    public String getToken() {
        return this.token;
    }

    public String getSignInUrl() {
        return this.signInUrl;
    }

    public TokenResponse setSignInUrl(String url) {
        this.signInUrl = url;
        return this;
    }

    public String getDetail() {
        return this.detail;
    }

    public TokenResponse setDetail(String detail) {
        this.detail = detail;
        return this;
    }

    public String getPicasaUser() {
        return this.picasaUser;
    }

    public TokenResponse setPicasaUser(String picasaUser) {
        this.picasaUser = picasaUser;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public TokenResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public TokenResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getRopText() {
        return this.ropText;
    }

    public TokenResponse setRopText(String ropText) {
        this.ropText = ropText;
        return this;
    }

    public String getRopRevision() {
        return this.ropRevision;
    }

    public TokenResponse setRopRevision(String ropRevision) {
        this.ropRevision = ropRevision;
        return this;
    }

    public boolean isGPlusServiceEnabled() {
        return this.isGPlusServiceEnabled;
    }

    public TokenResponse setGPlusServiceEnabled(boolean isEnabled) {
        this.isGPlusServiceEnabled = isEnabled;
        return this;
    }

    public boolean isEsMobileServiceEnabled() {
        return this.isEsMobileServiceEnabled;
    }

    public TokenResponse setEsMobileServiceEnabled(boolean isEnabled) {
        this.isEsMobileServiceEnabled = isEnabled;
        return this;
    }

    public boolean isBrowserSignInSuggested() {
        return this.isBrowserSignInSuggested;
    }

    public TokenResponse setBrowserSignInSuggested(boolean isSuggested) {
        this.isBrowserSignInSuggested = isSuggested;
        return this;
    }

    public CaptchaChallenge getCaptchaChallenge() {
        return this.captcha;
    }

    public TokenResponse setCaptchaChallenge(CaptchaChallenge challenge) {
        this.captcha = challenge;
        return this;
    }

    public List<ScopeDetail> getScopeData() {
        return Collections.unmodifiableList(this.scopeData);
    }

    public TokenResponse setScopeData(List<ScopeDetail> scopeData) {
        this.scopeData.clear();
        this.scopeData.addAll(scopeData);
        return this;
    }

    public boolean hasTitle() {
        return this.hasTitle;
    }

    public TokenResponse setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
        return this;
    }

    public int getTitle() {
        return this.title;
    }

    public TokenResponse setTitle(int title) {
        this.title = title;
        return this;
    }

    public TokenResponse setGPlusServiceAllowed(boolean isAllowed) {
        this.isGPlusServiceAllowed = isAllowed;
        return this;
    }

    public boolean isGPlusServiceAllowed() {
        return this.isGPlusServiceAllowed;
    }

    public PostSignInData getPostSignInData() {
        return this.postSignInData;
    }

    public TokenResponse setPostSignInData(PostSignInData postSignInData) {
        this.postSignInData = postSignInData;
        return this;
    }

    public Account getAccount() {
        return this.account;
    }

    public TokenResponse setAccount(Account account) {
        this.account = (Account) Preconditions.checkNotNull(account, "Account can't be null.");
        this.accountName = account.name;
        return this;
    }

    public String getConsentCookieWrapper() {
        return this.consentCookieWrapper;
    }

    public TokenResponse setConsentCookieWrapper(String consentCookieWrapper) {
        this.consentCookieWrapper = consentCookieWrapper;
        return this;
    }

    @Nullable
    public String getDmStatus() {
        return this.dmStatus;
    }

    public TokenResponse setDmStatus(String dmStatus) {
        this.dmStatus = dmStatus;
        return this;
    }

    @Nullable
    public TokenData getTokenData() {
        return this.tokenData;
    }

    public TokenResponse setTokenData(TokenData tokenData) {
        if (tokenData == null) {
            this.token = null;
            this.tokenData = null;
        } else {
            this.token = tokenData.getToken();
            this.tokenData = tokenData;
        }
        return this;
    }

    public Bundle getDataForLogging() {
        return this.dataForLogging;
    }

    public void writeToParcel(Parcel dest, int flags) {
        TokenResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
