package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "AccountRecoveryDataRequestCreator")
public class AccountRecoveryDataRequest implements SafeParcelable {
    public static final AccountRecoveryDataRequestCreator CREATOR;
    private static final String LOG_PREFIX;
    private static final int VERSION = 1;
    @Field(id = 6)
    public final Account account;
    @Field(id = 2)
    @Deprecated
    public final String accountName;
    @Field(id = 4)
    public final AppDescription callingAppDescription;
    @Field(id = 3)
    public final boolean isClearUpdateSuggested;
    @Field(id = 5)
    public final String requestDescription;
    @VersionField(id = 1)
    final int version;

    static {
        LOG_PREFIX = "[" + AccountRecoveryDataRequest.class.getSimpleName() + "]";
        CREATOR = new AccountRecoveryDataRequestCreator();
    }

    @Constructor
    AccountRecoveryDataRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) boolean isClearUpdateSuggestion, @Param(id = 4) AppDescription callingAppDescription, @Param(id = 5) String requestDescription, @Param(id = 6) Account account) {
        this.accountName = Preconditions.checkNotEmpty(accountName, LOG_PREFIX + " accountName cannot be empty or null!");
        Preconditions.checkNotEmpty(requestDescription, LOG_PREFIX + " requestDescription cannot be empty or null!");
        this.version = version;
        this.isClearUpdateSuggested = isClearUpdateSuggestion;
        this.callingAppDescription = (AppDescription) Preconditions.checkNotNull(callingAppDescription);
        this.requestDescription = requestDescription;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
        Preconditions.checkNotNull(this.account);
    }

    public AccountRecoveryDataRequest(String accountName, boolean isClearUpdateSuggestion, AppDescription callingAppDescription, String requestDescription) {
        this(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE), isClearUpdateSuggestion, callingAppDescription, requestDescription);
    }

    public AccountRecoveryDataRequest(Account account, boolean isClearUpdateSuggestion, AppDescription callingAppDescription, String requestDescription) {
        this(VERSION, account.name, isClearUpdateSuggestion, callingAppDescription, requestDescription, account);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountRecoveryDataRequestCreator.writeToParcel(this, dest, flags);
    }
}
