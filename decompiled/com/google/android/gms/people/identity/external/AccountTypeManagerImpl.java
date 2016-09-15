package com.google.android.gms.people.identity.external;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncAdapterType;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@VisibleForTesting
/* compiled from: AccountTypeManager */
class AccountTypeManagerImpl extends AccountTypeManager {
    private static final String TAG = "ExAccountTypeManager";
    private final AccountManager mAccountManager;
    private final Context mContext;

    public AccountTypeManagerImpl(Context context) {
        this.mContext = context;
        this.mAccountManager = AccountManager.get(this.mContext);
    }

    public List<AccountType> getAccountTypes() {
        AccountType accountType;
        long startTime = SystemClock.currentThreadTimeMillis();
        long startTimeWall = SystemClock.elapsedRealtime();
        List<AccountType> accountTypes = new ArrayList();
        Set<String> extensionPackages = new HashSet();
        AccountManager accountManager = this.mAccountManager;
        SyncAdapterType[] syncs = ContentResolver.getSyncAdapterTypes();
        AuthenticatorDescription[] auths = accountManager.getAuthenticatorTypes();
        for (SyncAdapterType sync : syncs) {
            if ("com.android.contacts".equals(sync.authority)) {
                String type = sync.accountType;
                AuthenticatorDescription auth = findAuthenticator(auths, type);
                if (auth == null) {
                    Log.w(TAG, "No authenticator found for type=" + type + ", ignoring it.");
                } else {
                    Log.d(TAG, new StringBuilder().append("Registering external account type=").append(type).append(", resourcePackageName=").append(auth.packageName).toString());
                    accountType = new AccountType(this.mContext, auth.packageName, false);
                    if (accountType.isInitialized()) {
                        accountType.accountType = auth.type;
                        accountType.titleRes = auth.labelId;
                        accountType.iconRes = auth.iconId;
                        accountTypes.add(accountType);
                        extensionPackages.addAll(accountType.getExtensionPackageNames());
                    }
                }
            }
        }
        if (!extensionPackages.isEmpty()) {
            Log.d(TAG, "Registering " + extensionPackages.size() + " extension packages");
            for (String extensionPackage : extensionPackages) {
                accountType = new AccountType(this.mContext, extensionPackage, true);
                if (accountType.isInitialized()) {
                    if (accountType.hasContactsMetadata()) {
                        if (TextUtils.isEmpty(accountType.accountType)) {
                            Log.w(TAG, "Skipping extension package " + extensionPackage + " because" + " the CONTACTS_STRUCTURE metadata doesn't have the accountType" + " attribute");
                        } else {
                            Log.d(TAG, "Registering extension package account type=" + accountType.accountType + ", dataSet=" + accountType.dataSet + ", packageName=" + extensionPackage);
                            accountTypes.add(accountType);
                        }
                    } else {
                        Log.w(TAG, "Skipping extension package " + extensionPackage + " because" + " it doesn't have the CONTACTS_STRUCTURE metadata");
                    }
                }
            }
        }
        Log.i(TAG, "Loaded meta-data for " + accountTypes.size() + " account types in " + (SystemClock.elapsedRealtime() - startTimeWall) + "ms(wall) " + (SystemClock.currentThreadTimeMillis() - startTime) + "ms(cpu)");
        return accountTypes;
    }

    private static AuthenticatorDescription findAuthenticator(AuthenticatorDescription[] auths, String accountType) {
        for (AuthenticatorDescription auth : auths) {
            if (accountType.equals(auth.type)) {
                return auth;
            }
        }
        return null;
    }
}
