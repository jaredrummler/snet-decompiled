package com.google.android.gms.auth.firstparty.shared;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "AccountCredentialsCreator")
public class AccountCredentials implements SafeParcelable {
    public static final AccountCredentialsCreator CREATOR;
    private static final int VERSION_CODE = 2;
    @Field(id = 3)
    String mAccountName;
    @Field(defaultValue = "com.google", id = 9)
    final String mAccountType;
    @Field(id = 5)
    String mAuthorizationCode;
    @Field(id = 2)
    boolean mIsBrowserRequired;
    @Field(id = 4)
    String mLongLivedLoginToken;
    @Field(id = 6)
    String mPassword;
    @Field(id = 8)
    String mRedirectUri;
    @Field(id = 7)
    String mVerifier;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountCredentialsCreator();
    }

    @Deprecated
    public AccountCredentials() {
        this(GoogleLoginServiceConstants.ACCOUNT_TYPE);
    }

    public AccountCredentials(Account account) {
        this(account.type);
        this.mAccountName = account.name;
    }

    public AccountCredentials(String accountType) {
        this.version = VERSION_CODE;
        this.mAccountType = Preconditions.checkNotEmpty(accountType, "Account type can't be empty.");
    }

    public AccountCredentials(Parcel src) {
        boolean z = true;
        this.version = VERSION_CODE;
        if (src.readInt() != 1) {
            z = false;
        }
        this.mIsBrowserRequired = z;
        this.mLongLivedLoginToken = src.readString();
        this.mAccountName = src.readString();
        this.mAuthorizationCode = src.readString();
        this.mPassword = src.readString();
        this.mVerifier = src.readString();
        this.mRedirectUri = src.readString();
        this.mAccountType = src.readString();
    }

    @Constructor
    AccountCredentials(@Param(id = 1) int version, @Param(id = 2) boolean isBrowserRequired, @Param(id = 3) String accountName, @Param(id = 4) String longLivedLoginToken, @Param(id = 5) String authorizationCode, @Param(id = 6) String password, @Param(id = 7) String verifier, @Param(id = 8) String redirectUri, @Param(id = 9) String accountType) {
        this.version = version;
        this.mIsBrowserRequired = isBrowserRequired;
        this.mAccountName = accountName;
        this.mLongLivedLoginToken = longLivedLoginToken;
        this.mAuthorizationCode = authorizationCode;
        this.mPassword = password;
        this.mVerifier = verifier;
        this.mRedirectUri = redirectUri;
        this.mAccountType = accountType;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountCredentialsCreator.writeToParcel(this, dest, flags);
    }

    public String getAccountName() {
        return this.mAccountName;
    }

    public AccountCredentials setAccountName(String accountName) {
        this.mAccountName = accountName;
        return this;
    }

    public String getAccountType() {
        return this.mAccountType;
    }

    public Account getAccount() {
        if (TextUtils.isEmpty(this.mAccountName)) {
            return null;
        }
        return new Account(this.mAccountName, this.mAccountType);
    }

    public String getAuthorizationCode() {
        return this.mAuthorizationCode;
    }

    public AccountCredentials setAuthorizationCode(String authorizationCode) {
        this.mAuthorizationCode = authorizationCode;
        return this;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public AccountCredentials setPassword(String password) {
        this.mPassword = password;
        return this;
    }

    public String getVerifier() {
        return this.mVerifier;
    }

    public String getRedirectUri() {
        return this.mRedirectUri;
    }

    public AccountCredentials setVerifier(String verifier) {
        this.mVerifier = verifier;
        return this;
    }

    public AccountCredentials setRedirectUri(String redirectUri) {
        this.mRedirectUri = redirectUri;
        return this;
    }

    public boolean isBrowserAuthenticationRequired() {
        return this.mIsBrowserRequired;
    }

    public AccountCredentials setBrowserAuthenticationRequired(boolean isRequired) {
        this.mIsBrowserRequired = isRequired;
        return this;
    }

    public String getLongLivedLoginToken() {
        return this.mLongLivedLoginToken;
    }

    public AccountCredentials setLongLivedLoginToken(String token) {
        this.mLongLivedLoginToken = token;
        return this;
    }

    public int describeContents() {
        return 0;
    }
}
