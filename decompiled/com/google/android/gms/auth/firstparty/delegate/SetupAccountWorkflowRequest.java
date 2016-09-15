package com.google.android.gms.auth.firstparty.delegate;

import android.accounts.AccountAuthenticatorResponse;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Class(creator = "SetupAccountWorkflowRequestCreator")
public class SetupAccountWorkflowRequest implements SafeParcelable {
    public static final SetupAccountWorkflowRequestCreator CREATOR;
    private static final int VERSION = 5;
    @Field(id = 14)
    public String accountName;
    @Field(id = 8)
    public final String accountType;
    @Field(id = 4)
    public List<String> allowedDomains;
    @Field(id = 9)
    public AccountAuthenticatorResponse amResponse;
    @Field(id = 6)
    public final AppDescription callingAppDescription;
    @Field(id = 7)
    public boolean isCreditCardAllowed;
    @Field(id = 2)
    public boolean isMultiUser;
    @Field(id = 3)
    public boolean isSetupWizard;
    @Field(defaultValueUnchecked = "new android.os.Bundle()", id = 5)
    public Bundle options;
    @Field(defaultValue = "null", id = 12)
    public String purchaserGaiaEmail;
    @Field(defaultValue = "null", id = 13)
    public String purchaserName;
    @Field(defaultValue = "false", id = 10)
    public boolean suppressD2d;
    @Field(defaultValue = "false", id = 11)
    public boolean useImmersiveMode;
    @VersionField(id = 1)
    public final int version;

    static {
        CREATOR = new SetupAccountWorkflowRequestCreator();
    }

    @Constructor
    public SetupAccountWorkflowRequest(@Param(id = 1) int version, @Param(id = 2) boolean isMultiUser, @Param(id = 3) boolean isSetupWizard, @Param(id = 4) List<String> allowedDomains, @Param(id = 5) Bundle options, @Param(id = 6) AppDescription callingAppDescription, @Param(id = 7) boolean isCreditCardAllowed, @Param(id = 8) String accountType, @Param(id = 9) AccountAuthenticatorResponse amResponse, @Param(id = 10) boolean suppressD2d, @Param(id = 11) boolean useImmersiveMode, @Param(id = 12) String purchaserGaiaEmail, @Param(id = 13) String purchaserName, @Param(id = 14) String accountName) {
        this.version = version;
        this.isMultiUser = isMultiUser;
        this.isSetupWizard = isSetupWizard;
        this.allowedDomains = allowedDomains;
        this.options = options;
        this.callingAppDescription = (AppDescription) Preconditions.checkNotNull(callingAppDescription);
        this.isCreditCardAllowed = isCreditCardAllowed;
        this.accountType = accountType;
        this.amResponse = amResponse;
        this.suppressD2d = suppressD2d;
        this.useImmersiveMode = useImmersiveMode;
        this.purchaserGaiaEmail = purchaserGaiaEmail;
        this.purchaserName = purchaserName;
        this.accountName = accountName;
    }

    public SetupAccountWorkflowRequest(AppDescription callingAppDescription, String accountType) {
        this.version = VERSION;
        this.options = new Bundle();
        this.callingAppDescription = callingAppDescription;
        this.accountType = accountType;
    }

    public AccountAuthenticatorResponse getAmResponse() {
        return this.amResponse;
    }

    public SetupAccountWorkflowRequest setAmResponse(AccountAuthenticatorResponse amResponse) {
        this.amResponse = amResponse;
        return this;
    }

    public boolean isMultiUser() {
        return this.isMultiUser;
    }

    public SetupAccountWorkflowRequest setMultiUser(boolean isMultiUser) {
        this.isMultiUser = isMultiUser;
        return this;
    }

    @Deprecated
    public boolean isBackupAccount() {
        return this.isSetupWizard;
    }

    public boolean isSetupWizard() {
        return this.isSetupWizard;
    }

    public boolean useImmersiveMode() {
        return this.useImmersiveMode;
    }

    public boolean suppressD2d() {
        return this.suppressD2d;
    }

    public String getPurchaserGaiaEmail() {
        return this.purchaserGaiaEmail;
    }

    public String getPurchaserName() {
        return this.purchaserName;
    }

    @Deprecated
    public SetupAccountWorkflowRequest setBackupAccount(boolean isBackup) {
        return setIsSetupWizard(isBackup);
    }

    public SetupAccountWorkflowRequest setIsSetupWizard(boolean isSetupWizard) {
        this.isSetupWizard = isSetupWizard;
        return this;
    }

    public SetupAccountWorkflowRequest setUseImmersiveMode(boolean useImmersiveMode) {
        this.useImmersiveMode = useImmersiveMode;
        return this;
    }

    public SetupAccountWorkflowRequest setSuppressD2d(boolean suppressD2d) {
        this.suppressD2d = suppressD2d;
        return this;
    }

    public SetupAccountWorkflowRequest setPurchaserGaiaEmail(String purchaserGaiaEmail) {
        this.purchaserGaiaEmail = purchaserGaiaEmail;
        return this;
    }

    public SetupAccountWorkflowRequest setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName;
        return this;
    }

    public List<String> getAllowedDomains() {
        return this.allowedDomains == null ? null : Collections.unmodifiableList(this.allowedDomains);
    }

    public SetupAccountWorkflowRequest setAllowedDomains(Collection<String> allowedDomains) {
        if (allowedDomains != null) {
            this.allowedDomains = new ArrayList(allowedDomains);
        } else {
            this.allowedDomains = null;
        }
        return this;
    }

    public Bundle getOptions() {
        return new Bundle(this.options);
    }

    public SetupAccountWorkflowRequest setOptions(Bundle options) {
        this.options.clear();
        if (options != null) {
            this.options.putAll(options);
        }
        return this;
    }

    public SetupAccountWorkflowRequest setIsCreditCardAllowed(boolean isCreditCardAllowed) {
        this.isCreditCardAllowed = isCreditCardAllowed;
        return this;
    }

    public boolean isCreditCardAllowed() {
        return this.isCreditCardAllowed;
    }

    @Nullable
    public String getAccountName() {
        return this.accountName;
    }

    public SetupAccountWorkflowRequest setAccountName(@Nullable String accountName) {
        this.accountName = accountName;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        SetupAccountWorkflowRequestCreator.writeToParcel(this, dest, flags);
    }
}
