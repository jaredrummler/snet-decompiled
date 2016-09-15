package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.auth.IAuthManagerService;
import com.google.android.auth.IAuthManagerService.Stub;
import com.google.android.auth.IRecoveryService;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class GoogleAuthUtilLight {
    public static final String ACCOUNT_ID_SERVICE = "^^_account_id_^^";
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String CONSENT_COOKIE_WRAPPER = "consentCookieWrapper";
    private static final String CONTENT_AUTHORITY_ACCOUNTS_PROVIDER = "com.google.android.gms.auth.accounts";
    public static final String CONTENT_METHOD_CLEAR_PASSWORD = "clear_password";
    public static final String CONTENT_METHOD_GET_ACCOUNTS = "get_accounts";
    public static final int DELEGATION_TYPE_CHILD_IMPERSONATION = 1;
    public static final int DELEGATION_TYPE_UNKNOWN = 0;
    private static final ComponentName GET_TOKEN_SERVICE_COMPONENT_NAME;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ACCOUNTS = "accounts";
    public static final String KEY_ACCOUNT_FEATURES = "account_features";
    public static final String KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_AUTHORITY = "authority";
    public static final String KEY_CALLBACK_INTENT = "callback_intent";
    public static final String KEY_CALLER_UID;
    public static final String KEY_CLIENT_PACKAGE_NAME = "clientPackageName";
    public static final String KEY_CONTENT_CLEAR_PASSWORD = "clear_password";
    public static final String KEY_DELEGATEE_USER_ID = "delegatee_user_id";
    public static final String KEY_DELEGATION_TYPE = "delegation_type";
    public static final String KEY_GADS_SERVICE_CONNECTION_START_TIME_MILLIS = "gads_service_connection_start_time_millis";
    public static final String KEY_HANDLE_NOTIFICATION = "handle_notification";
    public static final String KEY_INCLUDE_EMAIL = "oauth2_include_email";
    public static final String KEY_INCLUDE_PROFILE = "oauth2_include_profile";
    public static final String KEY_IS_CONSENT_AUTO_APPROVED_BY_GOOGLE_NOW = "is_consent_auto_approved_by_google_now";
    public static final String KEY_NETWORK_TO_USE = "networkToUse";
    public static final String KEY_OAUTH_POLICY = "policy";
    public static final String KEY_POLICY_ACTION = "policy_action";
    public static final String KEY_PROMPT = "oauth2_prompt";
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    @Deprecated
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SERVICE_CONNECTION_START_TIME_MILLIS = "service_connection_start_time_millis";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String KEY_SYNC_EXTRAS = "sync_extras";
    public static final String KEY_TOKEN_DATA = "tokenDetails";
    public static final String KEY_TOKEN_USE_CACHE = "UseCache";
    public static final String KEY_USER_RECOVERY_INTENT = "userRecoveryIntent";
    private static final String LOGIN_GET_TOKEN = ".android.gms.auth.GetToken";
    private static final String LOGIN_PKG = ".android.gms";
    private static final String LOGIN_RECOVERY = ".android.gms.recovery.RecoveryService";
    private static final String MAIN_THREAD_ERROR_MESSAGE = "Calling this from your main thread can lead to deadlock";
    public static final String OEM_ONLY_KEY_REDIRECT_URI = "oauth2_redirect_uri";
    public static final String OEM_ONLY_KEY_TARGET_ANDROID_ID = "oauth2_target_device_id";
    public static final String OEM_ONLY_KEY_VERIFIER = "oauth2_authcode_verifier";
    public static final String OEM_ONLY_SCOPE_ACCOUNT_BOOTSTRAP = "_account_setup";
    public static final String PROMPT_MODE_AUTO = "auto";
    public static final String PROMPT_MODE_CONSENT = "consent";
    private static final ComponentName RECOVERY_MGMT_SERVICE_COMPONENT_NAME;
    public static final String SIDEWINDER_ACCOUNT_TYPE = "cn.google";
    public static final String STATUS_CAPTCHA_REQUIRED = "CaptchaRequired";
    @Deprecated
    public static final String STATUS_DEVICE_MANAGEMENT = "DeviceManagementRequiredOrSyncDisabled";
    public static final String STATUS_INTERRUPTED = "Interrupted";
    public static final String STATUS_NEEDS_PERMISSION = "NeedPermission";
    public static final String STATUS_NEED_APP = "AppDownloadRequired";
    public static final String STATUS_NETWORK_ERROR = "NetworkError";
    public static final String STATUS_SERVICE_DISABLED = "ServiceDisabled";
    public static final String STATUS_USER_CANCEL = "UserCancel";
    static final String TAG = "GoogleAuthUtil";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";

    private interface ConnectionExecutor<T> {
        T exec(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException;
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.1 */
    static class AnonymousClass1 implements ConnectionExecutor<TokenData> {
        final /* synthetic */ Account val$account;
        final /* synthetic */ Bundle val$options;
        final /* synthetic */ String val$scope;

        AnonymousClass1(Account account, String str, Bundle bundle) {
            this.val$account = account;
            this.val$scope = str;
            this.val$options = bundle;
        }

        public TokenData exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            Bundle res = (Bundle) GoogleAuthUtilLight.verifyResultNotNull(Stub.asInterface(binder).getTokenByAccount(this.val$account, this.val$scope, this.val$options));
            TokenData tokenData = TokenData.fromWrappedBundle(res, GoogleAuthUtilLight.KEY_TOKEN_DATA);
            if (tokenData != null) {
                return tokenData;
            }
            String errorStatus = res.getString(Status.EXTRA_KEY_STATUS);
            Intent recoveryIntent = (Intent) res.getParcelable(GoogleAuthUtilLight.KEY_USER_RECOVERY_INTENT);
            Status status = Status.fromWireCode(errorStatus);
            if (Status.isUserRecoverableError(status)) {
                throw new UserRecoverableAuthException(errorStatus, recoveryIntent);
            } else if (Status.isRetryableError(status)) {
                throw new IOException(errorStatus);
            } else {
                throw new GoogleAuthException(errorStatus);
            }
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.2 */
    static class AnonymousClass2 implements ConnectionExecutor<Void> {
        final /* synthetic */ Bundle val$extras;
        final /* synthetic */ String val$token;

        AnonymousClass2(String str, Bundle bundle) {
            this.val$token = str;
            this.val$extras = bundle;
        }

        public Void exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            Bundle result = (Bundle) GoogleAuthUtilLight.verifyResultNotNull(Stub.asInterface(binder).clearToken(this.val$token, this.val$extras));
            String errorStatus = result.getString(Status.EXTRA_KEY_STATUS);
            if (result.getBoolean("booleanResult")) {
                return null;
            }
            throw new GoogleAuthException(errorStatus);
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.3 */
    static class AnonymousClass3 implements ConnectionExecutor<RecoveryDecision> {
        final /* synthetic */ String val$displayMessage;
        final /* synthetic */ String val$email;
        final /* synthetic */ Bundle val$extras;
        final /* synthetic */ boolean val$isMessageBroadUse;

        AnonymousClass3(String str, String str2, boolean z, Bundle bundle) {
            this.val$email = str;
            this.val$displayMessage = str2;
            this.val$isMessageBroadUse = z;
            this.val$extras = bundle;
        }

        public RecoveryDecision exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            return (RecoveryDecision) GoogleAuthUtilLight.verifyResultNotNull(IRecoveryService.Stub.asInterface(binder).getAccountRecoveryDecision(this.val$email, this.val$displayMessage, this.val$isMessageBroadUse, this.val$extras));
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.4 */
    static class AnonymousClass4 implements ConnectionExecutor<RecoveryReadResponse> {
        final /* synthetic */ Context val$ctx;
        final /* synthetic */ String val$email;

        AnonymousClass4(String str, Context context) {
            this.val$email = str;
            this.val$ctx = context;
        }

        public RecoveryReadResponse exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            return (RecoveryReadResponse) GoogleAuthUtilLight.verifyResultNotNull(IRecoveryService.Stub.asInterface(binder).getRecoveryData(this.val$email, this.val$ctx.getPackageName()));
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.5 */
    static class AnonymousClass5 implements ConnectionExecutor<RecoveryWriteResponse> {
        final /* synthetic */ Context val$ctx;
        final /* synthetic */ RecoveryWriteRequest val$req;

        AnonymousClass5(RecoveryWriteRequest recoveryWriteRequest, Context context) {
            this.val$req = recoveryWriteRequest;
            this.val$ctx = context;
        }

        public RecoveryWriteResponse exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            return (RecoveryWriteResponse) GoogleAuthUtilLight.verifyResultNotNull(IRecoveryService.Stub.asInterface(binder).updateRecoveryData(this.val$req, this.val$ctx.getPackageName()));
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.6 */
    static class AnonymousClass6 implements ConnectionExecutor<List<AccountChangeEvent>> {
        final /* synthetic */ String val$accountName;
        final /* synthetic */ int val$eventIndex;

        AnonymousClass6(String str, int i) {
            this.val$accountName = str;
            this.val$eventIndex = i;
        }

        public List<AccountChangeEvent> exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            return ((AccountChangeEventsResponse) GoogleAuthUtilLight.verifyResultNotNull(Stub.asInterface(binder).getAccountChangeEvents(new AccountChangeEventsRequest().setAccountName(this.val$accountName).setEventIndex(this.val$eventIndex)))).getEvents();
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.7 */
    static class AnonymousClass7 implements ConnectionExecutor<Account[]> {
        final /* synthetic */ String val$accountType;
        final /* synthetic */ String[] val$optionalFeatures;

        AnonymousClass7(String str, String[] strArr) {
            this.val$accountType = str;
            this.val$optionalFeatures = strArr;
        }

        public Account[] exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            IAuthManagerService service = Stub.asInterface(binder);
            Bundle request = new Bundle();
            request.putString("accountType", this.val$accountType);
            request.putStringArray(GoogleAuthUtilLight.KEY_ACCOUNT_FEATURES, this.val$optionalFeatures);
            Parcelable[] parcels = ((Bundle) GoogleAuthUtilLight.verifyResultNotNull(service.getAccounts(request))).getParcelableArray(GoogleAuthUtilLight.KEY_ACCOUNTS);
            Account[] accounts = new Account[parcels.length];
            for (int i = GoogleAuthUtilLight.DELEGATION_TYPE_UNKNOWN; i < parcels.length; i += GoogleAuthUtilLight.DELEGATION_TYPE_CHILD_IMPERSONATION) {
                accounts[i] = (Account) parcels[i];
            }
            return accounts;
        }
    }

    /* renamed from: com.google.android.gms.auth.GoogleAuthUtilLight.8 */
    static class AnonymousClass8 implements ConnectionExecutor<Bundle> {
        final /* synthetic */ Account val$account;

        AnonymousClass8(Account account) {
            this.val$account = account;
        }

        public Bundle exec(IBinder binder) throws RemoteException, IOException, GoogleAuthException {
            return (Bundle) GoogleAuthUtilLight.verifyResultNotNull(Stub.asInterface(binder).removeAccount(this.val$account));
        }
    }

    static {
        String str;
        KEY_CALLER_UID = VERSION.SDK_INT >= 11 ? "callerUid" : "callerUid";
        if (VERSION.SDK_INT >= 14) {
            str = "androidPackageName";
        } else {
            str = "androidPackageName";
        }
        KEY_ANDROID_PACKAGE_NAME = str;
        GET_TOKEN_SERVICE_COMPONENT_NAME = new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.auth.GetToken");
        RECOVERY_MGMT_SERVICE_COMPONENT_NAME = new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.recovery.RecoveryService");
    }

    @Deprecated
    public static String getToken(Context context, String accountName, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope);
    }

    @Deprecated
    public static String getToken(Context context, String accountName, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(accountName, GOOGLE_ACCOUNT_TYPE), scope, extras);
    }

    public static String getToken(Context context, Account account, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, scope, new Bundle());
    }

    public static String getToken(Context context, Account account, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getTokenWithDetails(context, account, scope, extras).getToken();
    }

    public static TokenData getTokenWithDetails(Context context, Account account, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getTokenWithDetails(context, account, scope, new Bundle());
    }

    public static TokenData getTokenWithDetails(Context context, Account account, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(context);
        Bundle options = extras == null ? new Bundle() : new Bundle(extras);
        String clientPackageName = context.getApplicationInfo().packageName;
        options.putString(KEY_CLIENT_PACKAGE_NAME, clientPackageName);
        if (TextUtils.isEmpty(options.getString(KEY_ANDROID_PACKAGE_NAME))) {
            options.putString(KEY_ANDROID_PACKAGE_NAME, clientPackageName);
        }
        options.putLong(KEY_SERVICE_CONNECTION_START_TIME_MILLIS, SystemClock.elapsedRealtime());
        return (TokenData) connectAndExecute(context, GET_TOKEN_SERVICE_COMPONENT_NAME, new AnonymousClass1(account, scope, options));
    }

    @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
    @Deprecated
    public static void invalidateToken(Context context, String token) {
        AccountManager.get(context).invalidateAuthToken(GOOGLE_ACCOUNT_TYPE, token);
    }

    public static void clearToken(Context context, String token) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(context);
        Bundle extras = new Bundle();
        String clientPackageName = context.getApplicationInfo().packageName;
        extras.putString(KEY_CLIENT_PACKAGE_NAME, clientPackageName);
        if (!extras.containsKey(KEY_ANDROID_PACKAGE_NAME)) {
            extras.putString(KEY_ANDROID_PACKAGE_NAME, clientPackageName);
        }
        connectAndExecute(context, GET_TOKEN_SERVICE_COMPONENT_NAME, new AnonymousClass2(token, extras));
    }

    public static PendingIntent getRecoveryIfSuggested(Context ctx, String email, String displayMessage, boolean isMessageBroadUse) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        RecoveryDecision decision = getRecoveryDetails(ctx, email, displayMessage, isMessageBroadUse);
        if (decision.showRecoveryInterstitial && decision.isRecoveryInterstitialAllowed) {
            return decision.recoveryIntent;
        }
        return null;
    }

    public static RecoveryDecision getRecoveryDetails(Context ctx, String email, String displayMessage, boolean isMessageBroadUse) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(ctx);
        Bundle extras = new Bundle();
        extras.putString(KEY_ANDROID_PACKAGE_NAME, ctx.getPackageName());
        return (RecoveryDecision) connectAndExecute(ctx, RECOVERY_MGMT_SERVICE_COMPONENT_NAME, new AnonymousClass3(email, displayMessage, isMessageBroadUse, extras));
    }

    public static RecoveryReadResponse fetchCurrentRecoveryData(Context ctx, String email) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(ctx);
        return (RecoveryReadResponse) connectAndExecute(ctx, RECOVERY_MGMT_SERVICE_COMPONENT_NAME, new AnonymousClass4(email, ctx));
    }

    public static RecoveryWriteResponse updateRecoveryData(Context ctx, RecoveryWriteRequest req) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(ctx);
        return (RecoveryWriteResponse) connectAndExecute(ctx, RECOVERY_MGMT_SERVICE_COMPONENT_NAME, new AnonymousClass5(req, ctx));
    }

    public static List<AccountChangeEvent> getAccountChangeEvents(Context context, int eventIndex, String accountName) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(accountName, "accountName must be provided");
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(context);
        return (List) connectAndExecute(context, GET_TOKEN_SERVICE_COMPONENT_NAME, new AnonymousClass6(accountName, eventIndex));
    }

    public static String getAccountId(Context ctx, String accountName) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(accountName, "accountName must be provided");
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(ctx);
        return getToken(ctx, accountName, ACCOUNT_ID_SERVICE, new Bundle());
    }

    public static void clearPassword(Context context, Account account) throws RemoteException, GoogleAuthException, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(account);
        if (PlatformVersion.isAtLeastM()) {
            clearPasswordPostM(context, account);
        } else {
            AccountManager.get(context).clearPassword(account);
        }
    }

    @TargetApi(23)
    private static void clearPasswordPostM(Context context, Account account) throws RemoteException, GoogleAuthException, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        ensurePlayServicesAvailable(context);
        ContentProviderClient client = ((Context) Preconditions.checkNotNull(context)).getContentResolver().acquireContentProviderClient(CONTENT_AUTHORITY_ACCOUNTS_PROVIDER);
        if (client == null) {
            Log.w(TAG, "ContentProviderClient is null. Can't clear password");
            return;
        }
        try {
            Bundle extra = new Bundle();
            extra.putParcelable(KEY_CONTENT_CLEAR_PASSWORD, account);
            client.call(KEY_CONTENT_CLEAR_PASSWORD, null, extra);
        } finally {
            client.release();
        }
    }

    public static Account[] getAccounts(Context context, String accountType) throws RemoteException, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Preconditions.checkNotEmpty(accountType);
        if (PlatformVersion.isAtLeastM()) {
            return getAccountsPostM(context, accountType);
        }
        return AccountManager.get(context).getAccountsByType(accountType);
    }

    @TargetApi(23)
    private static Account[] getAccountsPostM(Context context, String accountType) throws RemoteException, GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GoogleApiAvailabilityLight.getInstance().verifyGooglePlayServicesIsAvailable(context);
        ContentProviderClient client = ((Context) Preconditions.checkNotNull(context)).getContentResolver().acquireContentProviderClient(CONTENT_AUTHORITY_ACCOUNTS_PROVIDER);
        if (client == null) {
            return new Account[DELEGATION_TYPE_UNKNOWN];
        }
        try {
            Parcelable[] parcels = client.call(CONTENT_METHOD_GET_ACCOUNTS, accountType, new Bundle()).getParcelableArray(KEY_ACCOUNTS);
            Account[] accounts = new Account[parcels.length];
            for (int i = DELEGATION_TYPE_UNKNOWN; i < parcels.length; i += DELEGATION_TYPE_CHILD_IMPERSONATION) {
                accounts[i] = (Account) parcels[i];
            }
            return accounts;
        } finally {
            client.release();
        }
    }

    public static Account[] getAccounts(Context context, String accountType, String[] optionalFeatures) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(accountType);
        ensurePlayServicesAvailable(context);
        return (Account[]) connectAndExecute(context, GET_TOKEN_SERVICE_COMPONENT_NAME, new AnonymousClass7(accountType, optionalFeatures));
    }

    @TargetApi(23)
    public static Bundle removeAccount(Context context, Account account) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context);
        ensurePlayServicesAvailable(context);
        return (Bundle) connectAndExecute(context, GET_TOKEN_SERVICE_COMPONENT_NAME, new AnonymousClass8(account));
    }

    static void validateCallbackIntent(Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        try {
            Intent.parseUri(intent.toUri(DELEGATION_TYPE_CHILD_IMPERSONATION), DELEGATION_TYPE_CHILD_IMPERSONATION);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }

    private static void ensurePlayServicesAvailable(Context context) throws GoogleAuthException {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            throw new GooglePlayServicesAvailabilityException(e.getConnectionStatusCode(), e.getMessage(), e.getIntent());
        } catch (GooglePlayServicesNotAvailableException e2) {
            throw new GoogleAuthException(e2.getMessage());
        }
    }

    private static <T> T verifyResultNotNull(T result) throws IOException {
        if (result != null) {
            return result;
        }
        Log.w(TAG, "Binder call returned null.");
        throw new IOException("Service unavailable.");
    }

    private static <T> T connectAndExecute(Context context, ComponentName componentName, ConnectionExecutor<T> executor) throws IOException, GoogleAuthException {
        Exception e;
        ServiceConnection bsc = new BlockingServiceConnection();
        GmsClientSupervisor supervisor = GmsClientSupervisor.getInstance(context);
        if (supervisor.bindService(componentName, bsc, TAG)) {
            try {
                T exec = executor.exec(bsc.getService());
                supervisor.unbindService(componentName, bsc, TAG);
                return exec;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.i(TAG, "Error on service connection.", e);
                    throw new IOException("Error on service connection.", e);
                } catch (Throwable th) {
                    supervisor.unbindService(componentName, bsc, TAG);
                }
            } catch (Exception e22) {
                e = e22;
                Log.i(TAG, "Error on service connection.", e);
                throw new IOException("Error on service connection.", e);
            }
        }
        throw new IOException("Could not bind to service.");
    }

    GoogleAuthUtilLight() {
    }
}
