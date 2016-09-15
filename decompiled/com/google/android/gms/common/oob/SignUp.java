package com.google.android.gms.common.oob;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gsf.GoogleLoginServiceConstants;
import java.io.IOException;

@VisibleForTesting
public class SignUp {
    public static final String ACTION_OOB_SIGN_UP = "com.google.android.gms.common.oob.OOB_SIGN_UP";
    public static final String EXTRAS_CALLING_APP_DESCRIPTION = "com.google.android.gms.common.oob.EXTRAS_APP_DESCRIPTION";
    public static final String EXTRAS_CLIENT_CALLING_APP_PACKAGE = "com.google.android.gms.common.oob.EXTRAS_CLIENT_CALLING_APP_PACKAGE";
    public static final String EXTRAS_PROMO_APP_PACKAGE = "com.google.android.gms.common.oob.EXTRAS_PROMO_APP_PACKAGE";
    public static final String EXTRAS_PROMO_APP_TEXT = "com.google.android.gms.common.oob.EXTRAS_PROMO_APP_TEXT";
    public static final String EXTRA_ACCOUNT_NAME = "com.google.android.gms.common.oob.EXTRA_ACCOUNT_NAME";
    public static final String EXTRA_BACK_BUTTON_NAME = "com.google.android.gms.common.oob.EXTRA_BACK_BUTTON_NAME";
    public static final String EXTRA_GPSRC = "com.google.android.gms.plus.GPSRC";
    public static final String EXTRA_OVERRIDE_THEME = "com.google.android.gms.plus.OVERRIDE_THEME";
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES;
    public static final int THEME_DEFAULT = 0;
    public static final int THEME_FULL = 1;
    public static final int THEME_SETUP_WIZARD = 2;

    static {
        GOOGLE_PLUS_REQUIRED_FEATURES = GmsClient.GOOGLE_PLUS_REQUIRED_FEATURES;
    }

    private SignUp() {
    }

    public static Intent newSignUpIntent(String accountName) {
        return newSignUpIntent(accountName, null);
    }

    public static Intent newSignUpIntent(String accountName, String backButtonName) {
        Intent intent = new Intent();
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        intent.setAction(ACTION_OOB_SIGN_UP);
        intent.putExtra(EXTRA_ACCOUNT_NAME, accountName);
        intent.putExtra(EXTRA_BACK_BUTTON_NAME, backButtonName);
        return intent;
    }

    public static Intent newSignUpIntent(String accountName, String backButtonName, String iconPackage, String promoText) {
        Intent intent = new Intent();
        intent.setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
        intent.setAction(ACTION_OOB_SIGN_UP);
        intent.putExtra(EXTRA_ACCOUNT_NAME, accountName);
        intent.putExtra(EXTRA_BACK_BUTTON_NAME, backButtonName);
        intent.putExtra(EXTRAS_PROMO_APP_PACKAGE, iconPackage);
        intent.putExtra(EXTRAS_PROMO_APP_TEXT, promoText);
        return intent;
    }

    public static AccountManagerFuture<Boolean> checkSignUpState(Context context, String accountName, String[] requiredFeatures, AccountManagerCallback<Boolean> callback, Handler handler) {
        boolean z;
        boolean z2 = true;
        if (TextUtils.isEmpty(accountName)) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "The accountName is required");
        if (requiredFeatures == null) {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "The requiredFeatures parameter is required");
        AccountManager accountManager = AccountManager.get(context);
        boolean foundAccount = false;
        Account[] arr$ = accountManager.getAccountsByType(GoogleLoginServiceConstants.ACCOUNT_TYPE);
        int len$ = arr$.length;
        for (int i$ = THEME_DEFAULT; i$ < len$; i$ += THEME_FULL) {
            if (accountName.equals(arr$[i$].name)) {
                foundAccount = true;
            }
        }
        if (foundAccount) {
            return accountManager.hasFeatures(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE), requiredFeatures, callback, handler);
        }
        throw new IllegalStateException("Given account does not exist on the device");
    }

    public static boolean isSignedUpBlocking(Context context, String accountName, String[] requiredFeatures) throws AuthenticatorException, OperationCanceledException, IOException {
        return ((Boolean) checkSignUpState(context, accountName, requiredFeatures, null, null).getResult()).booleanValue();
    }
}
