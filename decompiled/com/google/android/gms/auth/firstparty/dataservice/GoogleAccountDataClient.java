package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Bundle;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;

public interface GoogleAccountDataClient {
    AccountNameCheckResponse checkAccountName(AccountNameCheckRequest accountNameCheckRequest);

    CheckFactoryResetPolicyComplianceResponse checkFactoryResetPolicyCompliance(CheckFactoryResetPolicyComplianceRequest checkFactoryResetPolicyComplianceRequest);

    PasswordCheckResponse checkPassword(PasswordCheckRequest passwordCheckRequest);

    CheckRealNameResponse checkRealName(CheckRealNameRequest checkRealNameRequest);

    void clearFactoryResetChallenges();

    ClearTokenResponse clearToken(ClearTokenRequest clearTokenRequest);

    boolean clearWorkAccountAppWhitelist();

    TokenResponse confirmCredentials(ConfirmCredentialsRequest confirmCredentialsRequest);

    TokenResponse createAccount(GoogleAccountSetupRequest googleAccountSetupRequest);

    TokenResponse createPlusProfile(GoogleAccountSetupRequest googleAccountSetupRequest);

    @Deprecated
    GoogleAccountData getAccountData(String str);

    Bundle getAccountExportData(String str);

    String getAccountId(String str);

    AccountRecoveryData getAccountRecoveryCountryInfo();

    AccountRecoveryData getAccountRecoveryData(AccountRecoveryDataRequest accountRecoveryDataRequest);

    AccountRecoveryGuidance getAccountRecoveryGuidance(AccountRecoveryGuidanceRequest accountRecoveryGuidanceRequest);

    GetAndAdvanceOtpCounterResponse getAndAdvanceOtpCounter(String str);

    GoogleAccountData getGoogleAccountData(Account account);

    String getGoogleAccountId(Account account);

    GplusInfoResponse getGplusInfo(GplusInfoRequest gplusInfoRequest);

    OtpResponse getOtp(OtpRequest otpRequest);

    ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest reauthSettingsRequest);

    TokenResponse getToken(TokenRequest tokenRequest);

    boolean installAccountFromExportData(String str, Bundle bundle);

    AccountRemovalResponse removeAccount(AccountRemovalRequest accountRemovalRequest);

    boolean setWorkAccountAppWhitelistFingerprint(String str, String str2);

    TokenResponse signIn(AccountSignInRequest accountSignInRequest);

    AccountRecoveryUpdateResult updateAccountRecoveryData(AccountRecoveryUpdateRequest accountRecoveryUpdateRequest);

    TokenResponse updateCredentials(UpdateCredentialsRequest updateCredentialsRequest);

    ValidateAccountCredentialsResponse validateAccountCredentials(AccountCredentials accountCredentials);

    VerifyPinResponse verifyPin(VerifyPinRequest verifyPinRequest);
}
