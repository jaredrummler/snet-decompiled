package com.google.android.gms.people.identity.external;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;

@VisibleForTesting
public abstract class AccountTypeManager {
    private static AccountTypeManager mAccountTypeManager;
    private static final Object mInitializationLock;

    public abstract List<AccountType> getAccountTypes();

    static {
        mInitializationLock = new Object();
    }

    public static AccountTypeManager getInstance(Context context) {
        synchronized (mInitializationLock) {
            if (mAccountTypeManager == null) {
                mAccountTypeManager = new AccountTypeManagerImpl(context.getApplicationContext());
            }
        }
        return mAccountTypeManager;
    }

    @VisibleForTesting
    public static void setInstanceForTest(AccountTypeManager mockManager) {
        synchronized (mInitializationLock) {
            mAccountTypeManager = mockManager;
        }
    }
}
