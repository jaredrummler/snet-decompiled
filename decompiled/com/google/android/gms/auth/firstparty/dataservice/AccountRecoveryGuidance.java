package com.google.android.gms.auth.firstparty.dataservice;

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

@Class(creator = "AccountRecoveryGuidanceCreator")
public class AccountRecoveryGuidance implements SafeParcelable {
    public static final AccountRecoveryGuidanceCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 6)
    public final Account account;
    @Field(id = 2)
    @Deprecated
    public final String accountName;
    @Field(id = 3)
    public final boolean isRecoveryInfoNeeded;
    @Field(id = 4)
    public final boolean isRecoveryInterstitialSuggested;
    @Field(id = 5)
    public final boolean isRecoveryUpdateAllowed;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountRecoveryGuidanceCreator();
    }

    @Constructor
    AccountRecoveryGuidance(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) boolean isRecoveryInfoNeeded, @Param(id = 4) boolean isRecoveryInterstitialSuggested, @Param(id = 5) boolean isRecoveryInterstitialAllowed, @Param(id = 6) Account account) {
        this.version = version;
        this.accountName = accountName;
        this.isRecoveryInfoNeeded = isRecoveryInfoNeeded;
        this.isRecoveryInterstitialSuggested = isRecoveryInterstitialSuggested;
        this.isRecoveryUpdateAllowed = isRecoveryInterstitialAllowed;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    @Deprecated
    public AccountRecoveryGuidance(String accountName, boolean isRecoveryInfoNeeded, boolean isRecoveryInterstitialSuggested, boolean isRecoveryInterstitialAllowed) {
        this(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE), isRecoveryInfoNeeded, isRecoveryInterstitialSuggested, isRecoveryInterstitialAllowed);
    }

    public AccountRecoveryGuidance(Account account, boolean isRecoveryInfoNeeded, boolean isRecoveryInterstitialSuggested, boolean isRecoveryInterstitialAllowed) {
        this(VERSION, Preconditions.checkNotEmpty(account.name), isRecoveryInfoNeeded, isRecoveryInterstitialSuggested, isRecoveryInterstitialAllowed, account);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountRecoveryGuidanceCreator.writeToParcel(this, dest, flags);
    }
}
