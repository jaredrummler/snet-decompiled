package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.GoogleAuthUtilLight;
import com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService.Stub;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gsf.GoogleLoginServiceConstants;

public class GoogleAccountDataServiceClient implements GoogleAccountDataClient {
    private static final String LOG_PREFIX = "[GoogleAccountDataServiceClient] ";
    private static final String SERVICE_ACTION = "com.google.android.gms.auth.DATA_PROXY";
    private static final String TAG = "GoogleAccountDataServiceClient";
    private final Context mContext;

    private interface Call<R> {
        R exec(IGoogleAccountDataService iGoogleAccountDataService) throws RemoteException;
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.10 */
    class AnonymousClass10 implements Call<TokenResponse> {
        final /* synthetic */ TokenRequest val$tokenRequest;

        AnonymousClass10(TokenRequest tokenRequest) {
            this.val$tokenRequest = tokenRequest;
        }

        public TokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getToken(this.val$tokenRequest);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.11 */
    class AnonymousClass11 implements Call<ClearTokenResponse> {
        final /* synthetic */ ClearTokenRequest val$clearTokenRequest;

        AnonymousClass11(ClearTokenRequest clearTokenRequest) {
            this.val$clearTokenRequest = clearTokenRequest;
        }

        public ClearTokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.clearToken(this.val$clearTokenRequest);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.12 */
    class AnonymousClass12 implements Call<TokenResponse> {
        final /* synthetic */ AccountSignInRequest val$request;

        AnonymousClass12(AccountSignInRequest accountSignInRequest) {
            this.val$request = accountSignInRequest;
        }

        public TokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.signIn(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.13 */
    class AnonymousClass13 implements Call<AccountRemovalResponse> {
        final /* synthetic */ AccountRemovalRequest val$request;

        AnonymousClass13(AccountRemovalRequest accountRemovalRequest) {
            this.val$request = accountRemovalRequest;
        }

        public AccountRemovalResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.removeAccount(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.14 */
    class AnonymousClass14 implements Call<TokenResponse> {
        final /* synthetic */ ConfirmCredentialsRequest val$request;

        AnonymousClass14(ConfirmCredentialsRequest confirmCredentialsRequest) {
            this.val$request = confirmCredentialsRequest;
        }

        public TokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.confirmCredentials(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.15 */
    class AnonymousClass15 implements Call<TokenResponse> {
        final /* synthetic */ UpdateCredentialsRequest val$request;

        AnonymousClass15(UpdateCredentialsRequest updateCredentialsRequest) {
            this.val$request = updateCredentialsRequest;
        }

        public TokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.updateCredentials(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.17 */
    class AnonymousClass17 implements Call<AccountRecoveryData> {
        final /* synthetic */ AccountRecoveryDataRequest val$request;

        AnonymousClass17(AccountRecoveryDataRequest accountRecoveryDataRequest) {
            this.val$request = accountRecoveryDataRequest;
        }

        public AccountRecoveryData exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getAccountRecoveryData(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.18 */
    class AnonymousClass18 implements Call<AccountRecoveryGuidance> {
        final /* synthetic */ AccountRecoveryGuidanceRequest val$request;

        AnonymousClass18(AccountRecoveryGuidanceRequest accountRecoveryGuidanceRequest) {
            this.val$request = accountRecoveryGuidanceRequest;
        }

        public AccountRecoveryGuidance exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getAccountRecoveryGuidance(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.19 */
    class AnonymousClass19 implements Call<AccountRecoveryUpdateResult> {
        final /* synthetic */ AccountRecoveryUpdateRequest val$request;

        AnonymousClass19(AccountRecoveryUpdateRequest accountRecoveryUpdateRequest) {
            this.val$request = accountRecoveryUpdateRequest;
        }

        public AccountRecoveryUpdateResult exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.updateAccountRecoveryData(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.1 */
    class AnonymousClass1 implements Call<GoogleAccountData> {
        final /* synthetic */ Account val$account;

        AnonymousClass1(Account account) {
            this.val$account = account;
        }

        public GoogleAccountData exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getGoogleAccountData(this.val$account);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.20 */
    class AnonymousClass20 implements Call<Bundle> {
        final /* synthetic */ String val$accountName;

        AnonymousClass20(String str) {
            this.val$accountName = str;
        }

        public Bundle exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getAccountExportData(this.val$accountName);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.21 */
    class AnonymousClass21 implements Call<Boolean> {
        final /* synthetic */ String val$accountName;
        final /* synthetic */ Bundle val$exportData;

        AnonymousClass21(String str, Bundle bundle) {
            this.val$accountName = str;
            this.val$exportData = bundle;
        }

        public Boolean exec(IGoogleAccountDataService stub) throws RemoteException {
            return Boolean.valueOf(stub.installAccountFromExportData(this.val$accountName, this.val$exportData));
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.22 */
    class AnonymousClass22 implements Call<ReauthSettingsResponse> {
        final /* synthetic */ ReauthSettingsRequest val$request;

        AnonymousClass22(ReauthSettingsRequest reauthSettingsRequest) {
            this.val$request = reauthSettingsRequest;
        }

        public ReauthSettingsResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getReauthSettings(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.23 */
    class AnonymousClass23 implements Call<VerifyPinResponse> {
        final /* synthetic */ VerifyPinRequest val$request;

        AnonymousClass23(VerifyPinRequest verifyPinRequest) {
            this.val$request = verifyPinRequest;
        }

        public VerifyPinResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.verifyPin(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.24 */
    class AnonymousClass24 implements Call<AccountChangeEventsResponse> {
        final /* synthetic */ AccountChangeEventsRequest val$request;

        AnonymousClass24(AccountChangeEventsRequest accountChangeEventsRequest) {
            this.val$request = accountChangeEventsRequest;
        }

        public AccountChangeEventsResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getAccountChangeEvents(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.25 */
    class AnonymousClass25 implements Call<OtpResponse> {
        final /* synthetic */ OtpRequest val$request;

        AnonymousClass25(OtpRequest otpRequest) {
            this.val$request = otpRequest;
        }

        public OtpResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getOtp(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.26 */
    class AnonymousClass26 implements Call<CheckFactoryResetPolicyComplianceResponse> {
        final /* synthetic */ CheckFactoryResetPolicyComplianceRequest val$complianceRequest;

        AnonymousClass26(CheckFactoryResetPolicyComplianceRequest checkFactoryResetPolicyComplianceRequest) {
            this.val$complianceRequest = checkFactoryResetPolicyComplianceRequest;
        }

        public CheckFactoryResetPolicyComplianceResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.checkFrpCompliance(this.val$complianceRequest);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.28 */
    class AnonymousClass28 implements Call<Boolean> {
        final /* synthetic */ String val$packageName;
        final /* synthetic */ String val$signatureFingerprint;

        AnonymousClass28(String str, String str2) {
            this.val$packageName = str;
            this.val$signatureFingerprint = str2;
        }

        public Boolean exec(IGoogleAccountDataService stub) throws RemoteException {
            return Boolean.valueOf(stub.setWorkAccountAppWhitelistFingerprint(this.val$packageName, this.val$signatureFingerprint));
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.2 */
    class AnonymousClass2 implements Call<AccountNameCheckResponse> {
        final /* synthetic */ AccountNameCheckRequest val$accountCheckRequest;

        AnonymousClass2(AccountNameCheckRequest accountNameCheckRequest) {
            this.val$accountCheckRequest = accountNameCheckRequest;
        }

        public AccountNameCheckResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.checkAccountName(this.val$accountCheckRequest);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.30 */
    class AnonymousClass30 implements Call<ValidateAccountCredentialsResponse> {
        final /* synthetic */ AccountCredentials val$accountCreds;

        AnonymousClass30(AccountCredentials accountCredentials) {
            this.val$accountCreds = accountCredentials;
        }

        public ValidateAccountCredentialsResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.validateAccountCredentials(this.val$accountCreds);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.31 */
    class AnonymousClass31 implements Call<GetAndAdvanceOtpCounterResponse> {
        final /* synthetic */ String val$accountName;

        AnonymousClass31(String str) {
            this.val$accountName = str;
        }

        public GetAndAdvanceOtpCounterResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getAndAdvanceOtpCounter(this.val$accountName);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.3 */
    class AnonymousClass3 implements Call<PasswordCheckResponse> {
        final /* synthetic */ PasswordCheckRequest val$request;

        AnonymousClass3(PasswordCheckRequest passwordCheckRequest) {
            this.val$request = passwordCheckRequest;
        }

        public PasswordCheckResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.checkPassword(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.4 */
    class AnonymousClass4 implements Call<CheckRealNameResponse> {
        final /* synthetic */ CheckRealNameRequest val$request;

        AnonymousClass4(CheckRealNameRequest checkRealNameRequest) {
            this.val$request = checkRealNameRequest;
        }

        public CheckRealNameResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.checkRealName(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.5 */
    class AnonymousClass5 implements Call<TokenResponse> {
        final /* synthetic */ GoogleAccountSetupRequest val$signUpRequest;

        AnonymousClass5(GoogleAccountSetupRequest googleAccountSetupRequest) {
            this.val$signUpRequest = googleAccountSetupRequest;
        }

        public TokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.createAccount(this.val$signUpRequest);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.6 */
    class AnonymousClass6 implements Call<GplusInfoResponse> {
        final /* synthetic */ GplusInfoRequest val$request;

        AnonymousClass6(GplusInfoRequest gplusInfoRequest) {
            this.val$request = gplusInfoRequest;
        }

        public GplusInfoResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getGplusInfo(this.val$request);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.7 */
    class AnonymousClass7 implements Call<TokenResponse> {
        final /* synthetic */ GoogleAccountSetupRequest val$signUpRequest;

        AnonymousClass7(GoogleAccountSetupRequest googleAccountSetupRequest) {
            this.val$signUpRequest = googleAccountSetupRequest;
        }

        public TokenResponse exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.createPlusProfile(this.val$signUpRequest);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.8 */
    class AnonymousClass8 implements Call<String> {
        final /* synthetic */ String val$accountName;

        AnonymousClass8(String str) {
            this.val$accountName = str;
        }

        public String exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getAccountId(this.val$accountName);
        }
    }

    /* renamed from: com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient.9 */
    class AnonymousClass9 implements Call<String> {
        final /* synthetic */ Account val$account;

        AnonymousClass9(Account account) {
            this.val$account = account;
        }

        public String exec(IGoogleAccountDataService stub) throws RemoteException {
            return stub.getGoogleAccountId(this.val$account);
        }
    }

    public static class RuntimeInterruptedException extends RuntimeException {
        public RuntimeInterruptedException(InterruptedException e) {
            super(e);
        }
    }

    public static class RuntimeRemoteException extends RuntimeException {
        private final RemoteException mWrappedException;

        public RuntimeRemoteException(RemoteException e) {
            super(e);
            this.mWrappedException = e;
        }

        public RemoteException getWrappedException() {
            return this.mWrappedException;
        }
    }

    public GoogleAccountDataServiceClient(Context context) {
        this.mContext = (Context) Preconditions.checkNotNull(context);
    }

    @Deprecated
    public GoogleAccountData getAccountData(String accountName) {
        return getGoogleAccountData(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE));
    }

    public GoogleAccountData getGoogleAccountData(Account account) {
        return (GoogleAccountData) exec(new AnonymousClass1(account));
    }

    public AccountNameCheckResponse checkAccountName(AccountNameCheckRequest accountCheckRequest) {
        return (AccountNameCheckResponse) exec(new AnonymousClass2(accountCheckRequest));
    }

    public PasswordCheckResponse checkPassword(PasswordCheckRequest request) {
        return (PasswordCheckResponse) exec(new AnonymousClass3(request));
    }

    public CheckRealNameResponse checkRealName(CheckRealNameRequest request) {
        return (CheckRealNameResponse) exec(new AnonymousClass4(request));
    }

    public TokenResponse createAccount(GoogleAccountSetupRequest signUpRequest) {
        return (TokenResponse) exec(new AnonymousClass5(signUpRequest));
    }

    public GplusInfoResponse getGplusInfo(GplusInfoRequest request) {
        return (GplusInfoResponse) exec(new AnonymousClass6(request));
    }

    public TokenResponse createPlusProfile(GoogleAccountSetupRequest signUpRequest) {
        return (TokenResponse) exec(new AnonymousClass7(signUpRequest));
    }

    public String getAccountId(String accountName) {
        return (String) exec(new AnonymousClass8(accountName));
    }

    public String getGoogleAccountId(Account account) {
        return (String) exec(new AnonymousClass9(account));
    }

    public TokenResponse getToken(TokenRequest tokenRequest) {
        Preconditions.checkNotNull(tokenRequest, "TokenRequest cannot be null!");
        Bundle options = tokenRequest.getOptions();
        options.putLong(GoogleAuthUtilLight.KEY_GADS_SERVICE_CONNECTION_START_TIME_MILLIS, SystemClock.elapsedRealtime());
        tokenRequest.setOptions(options);
        return (TokenResponse) exec(new AnonymousClass10(tokenRequest));
    }

    public ClearTokenResponse clearToken(ClearTokenRequest clearTokenRequest) {
        Preconditions.checkNotNull(clearTokenRequest, "ClearTokenRequest cannot be null!");
        return (ClearTokenResponse) exec(new AnonymousClass11(clearTokenRequest));
    }

    public TokenResponse signIn(AccountSignInRequest request) {
        return (TokenResponse) exec(new AnonymousClass12(request));
    }

    @VisibleForTesting
    public AccountRemovalResponse removeAccount(AccountRemovalRequest request) {
        return (AccountRemovalResponse) exec(new AnonymousClass13(request));
    }

    public TokenResponse confirmCredentials(ConfirmCredentialsRequest request) {
        return (TokenResponse) exec(new AnonymousClass14(request));
    }

    public TokenResponse updateCredentials(UpdateCredentialsRequest request) {
        return (TokenResponse) exec(new AnonymousClass15(request));
    }

    public AccountRecoveryData getAccountRecoveryCountryInfo() {
        return (AccountRecoveryData) exec(new Call<AccountRecoveryData>() {
            public AccountRecoveryData exec(IGoogleAccountDataService stub) throws RemoteException {
                return stub.getAccountRecoveryCountryInfo();
            }
        });
    }

    public AccountRecoveryData getAccountRecoveryData(AccountRecoveryDataRequest request) {
        return (AccountRecoveryData) exec(new AnonymousClass17(request));
    }

    public AccountRecoveryGuidance getAccountRecoveryGuidance(AccountRecoveryGuidanceRequest request) {
        return (AccountRecoveryGuidance) exec(new AnonymousClass18(request));
    }

    public AccountRecoveryUpdateResult updateAccountRecoveryData(AccountRecoveryUpdateRequest request) {
        return (AccountRecoveryUpdateResult) exec(new AnonymousClass19(request));
    }

    public Bundle getAccountExportData(String accountName) {
        return (Bundle) exec(new AnonymousClass20(accountName));
    }

    public boolean installAccountFromExportData(String accountName, Bundle exportData) {
        return ((Boolean) exec(new AnonymousClass21(accountName, exportData))).booleanValue();
    }

    @Deprecated
    public WebSetupConfig getWebSetupConfig(WebSetupConfigRequest request) {
        throw new UnsupportedOperationException();
    }

    public ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest request) {
        Preconditions.checkNotNull(request);
        request.setCallingPackageName(this.mContext.getPackageName());
        return (ReauthSettingsResponse) exec(new AnonymousClass22(request));
    }

    public VerifyPinResponse verifyPin(VerifyPinRequest request) {
        Preconditions.checkNotNull(request);
        request.setCallingPackageName(this.mContext.getPackageName());
        return (VerifyPinResponse) exec(new AnonymousClass23(request));
    }

    public AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest request) {
        Preconditions.checkNotNull(request);
        return (AccountChangeEventsResponse) exec(new AnonymousClass24(request));
    }

    public OtpResponse getOtp(OtpRequest request) {
        return (OtpResponse) exec(new AnonymousClass25(request));
    }

    public CheckFactoryResetPolicyComplianceResponse checkFactoryResetPolicyCompliance(CheckFactoryResetPolicyComplianceRequest complianceRequest) {
        return (CheckFactoryResetPolicyComplianceResponse) exec(new AnonymousClass26(complianceRequest));
    }

    @Deprecated
    public void clearFactoryResetChallenges() {
        exec(new Call<Void>() {
            public Void exec(IGoogleAccountDataService stub) throws RemoteException {
                stub.clearFactoryResetChallenges();
                return null;
            }
        });
    }

    public boolean setWorkAccountAppWhitelistFingerprint(String packageName, String signatureFingerprint) {
        Preconditions.checkNotEmpty(packageName, "Package name must not be empty");
        return ((Boolean) exec(new AnonymousClass28(packageName, signatureFingerprint))).booleanValue();
    }

    public boolean clearWorkAccountAppWhitelist() {
        return ((Boolean) exec(new Call<Boolean>() {
            public Boolean exec(IGoogleAccountDataService stub) throws RemoteException {
                return Boolean.valueOf(stub.clearWorkAccountAppWhitelist());
            }
        })).booleanValue();
    }

    public ValidateAccountCredentialsResponse validateAccountCredentials(AccountCredentials accountCreds) {
        return (ValidateAccountCredentialsResponse) exec(new AnonymousClass30(accountCreds));
    }

    public GetAndAdvanceOtpCounterResponse getAndAdvanceOtpCounter(String accountName) {
        return (GetAndAdvanceOtpCounterResponse) exec(new AnonymousClass31(accountName));
    }

    private <R> R exec(Call<R> call) {
        ServiceConnection connection;
        GmsClientSupervisor supervisor;
        R r = null;
        long callingId = Binder.clearCallingIdentity();
        try {
            connection = new BlockingServiceConnection();
            supervisor = GmsClientSupervisor.getInstance(this.mContext);
            if (supervisor.bindService(SERVICE_ACTION, connection, TAG)) {
                r = call.exec(Stub.asInterface(connection.getService()));
                supervisor.unbindService(SERVICE_ACTION, connection, TAG);
                Binder.restoreCallingIdentity(callingId);
            } else {
                Binder.restoreCallingIdentity(callingId);
            }
        } catch (InterruptedException e) {
            Log.w(TAG, "[GoogleAccountDataServiceClient] Interrupted when getting service.", e);
            supervisor.unbindService(SERVICE_ACTION, connection, TAG);
            Binder.restoreCallingIdentity(callingId);
        } catch (RemoteException e2) {
            Log.w(TAG, "[GoogleAccountDataServiceClient] RemoteException when executing call.", e2);
            supervisor.unbindService(SERVICE_ACTION, connection, TAG);
            Binder.restoreCallingIdentity(callingId);
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(callingId);
        }
        return r;
    }
}
