package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.IAccountAccessor.Stub;
import com.google.android.gsf.GoogleLoginServiceConstants;

public class AccountAccessor extends Stub {
    private static final String TAG = "AccountAccessor";
    private Account mAccount;
    private Context mContext;
    int mGmsCoreUid;

    public static AccountAccessor fromGoogleAccountName(Context context, String accountName) {
        return new AccountAccessor(context, TextUtils.isEmpty(accountName) ? null : new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE));
    }

    public static Account getAccountBinderSafe(IAccountAccessor accountAccessor) {
        Account account = null;
        if (accountAccessor != null) {
            long token = Binder.clearCallingIdentity();
            try {
                account = accountAccessor.getAccount();
            } catch (RemoteException e) {
                Log.w(TAG, "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
        return account;
    }

    public AccountAccessor(Context context, Account account) {
        this.mGmsCoreUid = -1;
        this.mContext = context.getApplicationContext();
        this.mAccount = account;
    }

    public Account getAccount() {
        int uid = Binder.getCallingUid();
        if (uid == this.mGmsCoreUid) {
            return this.mAccount;
        }
        if (GooglePlayServicesUtilLight.isGooglePlayServicesUid(this.mContext, uid)) {
            this.mGmsCoreUid = uid;
            return this.mAccount;
        }
        throw new SecurityException("Caller is not GooglePlayServices");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof AccountAccessor) {
            return this.mAccount.equals(((AccountAccessor) o).mAccount);
        }
        return false;
    }
}
