package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Bundle;
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

@Class(creator = "GoogleAccountSetupRequestCreator")
public class GoogleAccountSetupRequest implements SafeParcelable {
    public static final GoogleAccountSetupRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 15)
    AccountCredentials accountCredentials;
    @Field(id = 14)
    AppDescription callingAppDescription;
    @Field(id = 6)
    String firstName;
    @Field(id = 9)
    String gender;
    @Field(id = 11)
    boolean isAddingAccount;
    @Field(id = 5)
    boolean isAgreedToMobileTos;
    @Field(id = 4)
    boolean isAgreedToPersonalizedContent;
    @Field(id = 3)
    boolean isAgreedToWebHistory;
    @Field(id = 10)
    boolean isCreatingAccount;
    @Field(id = 12)
    boolean isSetupWizardInProgress;
    @Field(id = 7)
    String lastName;
    @Field(id = 16)
    CaptchaSolution optionalCaptchaSolution;
    @Field(defaultValueUnchecked = "new android.os.Bundle()", id = 2)
    Bundle options;
    @Field(id = 18)
    String phoneCountryCode;
    @Field(id = 17)
    String phoneNumber;
    @Field(id = 13)
    String ropRevision;
    @Field(id = 8)
    String secondaryEmail;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new GoogleAccountSetupRequestCreator();
    }

    @Constructor
    GoogleAccountSetupRequest(@Param(id = 1) int version, @Param(id = 2) Bundle options, @Param(id = 3) boolean isAgreedToWebHistory, @Param(id = 4) boolean isAgreedToPersonalizedContent, @Param(id = 5) boolean isAgreedToMobileTos, @Param(id = 6) String firstName, @Param(id = 7) String lastName, @Param(id = 8) String secondaryEmail, @Param(id = 9) String gender, @Param(id = 10) boolean isCreatingAccount, @Param(id = 11) boolean isAddingAccount, @Param(id = 12) boolean isSetupWizardInProgress, @Param(id = 13) String ropRevision, @Param(id = 14) AppDescription callingAppDescription, @Param(id = 15) AccountCredentials accountCredentials, @Param(id = 16) CaptchaSolution optionalCaptchaSolution, @Param(id = 17) String phoneNumber, @Param(id = 18) String phoneCountryCode) {
        this.version = version;
        this.options = options;
        this.isAgreedToWebHistory = isAgreedToWebHistory;
        this.isAgreedToPersonalizedContent = isAgreedToPersonalizedContent;
        this.isAgreedToMobileTos = isAgreedToMobileTos;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondaryEmail = secondaryEmail;
        this.gender = gender;
        this.isCreatingAccount = isCreatingAccount;
        this.isAddingAccount = isAddingAccount;
        this.isSetupWizardInProgress = isSetupWizardInProgress;
        this.ropRevision = ropRevision;
        this.callingAppDescription = callingAppDescription;
        this.accountCredentials = accountCredentials;
        this.optionalCaptchaSolution = optionalCaptchaSolution;
        this.phoneNumber = phoneNumber;
        this.phoneCountryCode = phoneCountryCode;
    }

    public GoogleAccountSetupRequest() {
        this.version = VERSION;
        this.options = new Bundle();
    }

    public void writeToParcel(Parcel dest, int flags) {
        GoogleAccountSetupRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getOptions() {
        return new Bundle(this.options);
    }

    public GoogleAccountSetupRequest setOptions(Bundle options) {
        this.options.clear();
        this.options.putAll(options);
        return this;
    }

    public boolean isAgreedToMobileTos() {
        return this.isAgreedToMobileTos;
    }

    public GoogleAccountSetupRequest setAgreedToMobileTos(boolean isAgreed) {
        this.isAgreedToMobileTos = isAgreed;
        return this;
    }

    public boolean isAgreedToWebHistory() {
        return this.isAgreedToWebHistory;
    }

    public GoogleAccountSetupRequest setAgreedToWebHistory(boolean isAgreedToWebHistory) {
        this.isAgreedToWebHistory = isAgreedToWebHistory;
        return this;
    }

    public boolean isAgreedToPersonalizedContent() {
        return this.isAgreedToPersonalizedContent;
    }

    public GoogleAccountSetupRequest setAgreedToPersonalizedContent(boolean isAgreedToPersonalizedContent) {
        this.isAgreedToPersonalizedContent = isAgreedToPersonalizedContent;
        return this;
    }

    public boolean isCreatingAccount() {
        return this.isCreatingAccount;
    }

    public GoogleAccountSetupRequest setCreatingAccount(boolean isCreating) {
        this.isCreatingAccount = isCreating;
        return this;
    }

    public boolean isAddingAccount() {
        return this.isAddingAccount;
    }

    public GoogleAccountSetupRequest setAddingAccount(boolean isAdding) {
        this.isAddingAccount = isAdding;
        return this;
    }

    public boolean isSetupWizardInProgress() {
        return this.isSetupWizardInProgress;
    }

    public GoogleAccountSetupRequest setSetupWizardInProgress(boolean isProgress) {
        this.isSetupWizardInProgress = isProgress;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public GoogleAccountSetupRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public GoogleAccountSetupRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getRopRevision() {
        return this.ropRevision;
    }

    public GoogleAccountSetupRequest setRopRevision(String ropRevision) {
        this.ropRevision = ropRevision;
        return this;
    }

    public String getSecondaryEmail() {
        return this.secondaryEmail;
    }

    public GoogleAccountSetupRequest setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
        return this;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public GoogleAccountSetupRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    public GoogleAccountSetupRequest setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
        return this;
    }

    public String getGender() {
        return this.gender;
    }

    public GoogleAccountSetupRequest setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public GoogleAccountSetupRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public AccountCredentials getAccountCredentials() {
        return this.accountCredentials;
    }

    public GoogleAccountSetupRequest setAccountCredentials(AccountCredentials accountCredentials) {
        this.accountCredentials = accountCredentials;
        return this;
    }

    public CaptchaSolution getCaptchaSolution() {
        return this.optionalCaptchaSolution;
    }

    public GoogleAccountSetupRequest setCaptchaSolution(CaptchaSolution captchaSolution) {
        this.optionalCaptchaSolution = captchaSolution;
        return this;
    }
}
