package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "AccountSignInRequestCreator")
public class AccountSignInRequest implements SafeParcelable {
    public static final AccountSignInRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 6)
    AccountCredentials accountCredentials;
    @Field(id = 2)
    AppDescription callingAppDescription;
    @Field(id = 3)
    boolean isCreatingAccount;
    @Field(id = 4)
    boolean isSetupWizardInProgress;
    @Field(id = 5)
    CaptchaSolution optionalCaptchaSolution;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new AccountSignInRequestCreator();
    }

    @Constructor
    AccountSignInRequest(@Param(id = 1) int version, @Param(id = 2) AppDescription callingAppDescription, @Param(id = 3) boolean isCreatingAccount, @Param(id = 4) boolean isSetupWizardInProgress, @Param(id = 5) CaptchaSolution optionalCaptchaSolution, @Param(id = 6) AccountCredentials accountCredentials) {
        this.version = version;
        this.callingAppDescription = callingAppDescription;
        this.isCreatingAccount = isCreatingAccount;
        this.isSetupWizardInProgress = isSetupWizardInProgress;
        this.optionalCaptchaSolution = optionalCaptchaSolution;
        this.accountCredentials = accountCredentials;
    }

    public AccountSignInRequest() {
        this.version = VERSION;
    }

    public AccountSignInRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public AccountSignInRequest setAccountCredentials(AccountCredentials credentials) {
        this.accountCredentials = credentials;
        return this;
    }

    public AccountCredentials getAccountCredentials() {
        return this.accountCredentials;
    }

    public AccountSignInRequest setAccountCreationInProgress(boolean isCreating) {
        this.isCreatingAccount = isCreating;
        return this;
    }

    public boolean isAccountCreationInProgress() {
        return this.isCreatingAccount;
    }

    @Deprecated
    public AccountSignInRequest setSetupWizardInProgress(boolean isInProgress) {
        this.isSetupWizardInProgress = isInProgress;
        return this;
    }

    public AccountSignInRequest setBackupAccount(boolean isBackupAccount) {
        this.isSetupWizardInProgress = isBackupAccount;
        return this;
    }

    public boolean isSetupWizardInProgress() {
        return this.isSetupWizardInProgress;
    }

    public AccountSignInRequest setCaptchaSolution(CaptchaSolution captchaSolution) {
        this.optionalCaptchaSolution = captchaSolution;
        return this;
    }

    public CaptchaSolution getCaptchaSolution() {
        return this.optionalCaptchaSolution;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountSignInRequestCreator.writeToParcel(this, dest, flags);
    }
}
