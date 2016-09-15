package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

public class Storage {
    @VisibleForTesting
    static final String DELIMITER = ":";
    @VisibleForTesting
    static final String KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID = "defaultGoogleSignInAccount";
    @VisibleForTesting
    static final String KEY_DEFAULT_SIGN_IN_ACCOUNT_ID = "defaultSignInAccount";
    @VisibleForTesting
    static final String KEY_GOOGLE_SIGN_IN_ACCOUNT_PREFIX = "googleSignInAccount";
    @VisibleForTesting
    static final String KEY_GOOGLE_SIGN_IN_OPTIONS_PREFIX = "googleSignInOptions";
    @VisibleForTesting
    static final String KEY_SIGN_IN_ACCOUNT_PREFIX = "signInAccount";
    @VisibleForTesting
    static final String KEY_SIGN_IN_CONFIGURATION_PREFIX = "signInConfiguration";
    private static final String PREFERENCE = "com.google.android.gms.signin";
    @GuardedBy("sLk")
    private static Storage sInstance;
    private static final Lock sLk;
    private final Lock mLk;
    @GuardedBy("mLk")
    private final SharedPreferences mPrefs;

    static {
        sLk = new ReentrantLock();
    }

    @GuardedBy("sLk")
    @VisibleForTesting
    static void setInstance(@Nullable Storage storage) {
        sLk.lock();
        try {
            sInstance = storage;
            sInstance = storage;
        } finally {
            sLk.unlock();
        }
    }

    public static Storage getInstance(Context context) {
        Preconditions.checkNotNull(context);
        sLk.lock();
        try {
            if (sInstance == null) {
                sInstance = new Storage(context.getApplicationContext());
            }
            Storage storage = sInstance;
            return storage;
        } finally {
            sLk.unlock();
        }
    }

    @VisibleForTesting
    Storage(Context context) {
        this.mLk = new ReentrantLock();
        this.mPrefs = context.getSharedPreferences(PREFERENCE, 0);
    }

    @VisibleForTesting
    void saveSignInAccount(SignInAccount signInAccount, SignInConfiguration configuration) {
        Preconditions.checkNotNull(signInAccount);
        Preconditions.checkNotNull(configuration);
        String userId = signInAccount.getUserId();
        SignInAccount savedSignInAccount = getSavedSignInAccount(userId);
        if (!(savedSignInAccount == null || savedSignInAccount.getGoogleSignInAccount() == null)) {
            removeSavedGoogleSignInAccount(savedSignInAccount.getGoogleSignInAccount().getObfuscatedIdentifier());
        }
        saveToStore(getKey(KEY_SIGN_IN_CONFIGURATION_PREFIX, userId), configuration.toJson());
        saveToStore(getKey(KEY_SIGN_IN_ACCOUNT_PREFIX, userId), signInAccount.toJson());
        if (signInAccount.getGoogleSignInAccount() != null) {
            saveGoogleSignInAccount(signInAccount.getGoogleSignInAccount(), configuration.getGoogleConfig());
        }
    }

    public void saveDefaultSignInAccount(SignInAccount signInAccount, SignInConfiguration configuration) {
        Preconditions.checkNotNull(signInAccount);
        Preconditions.checkNotNull(configuration);
        removeSavedDefaultSignInAccount();
        saveToStore(KEY_DEFAULT_SIGN_IN_ACCOUNT_ID, signInAccount.getUserId());
        if (signInAccount.getGoogleSignInAccount() != null) {
            saveToStore(KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID, signInAccount.getGoogleSignInAccount().getObfuscatedIdentifier());
        }
        saveSignInAccount(signInAccount, configuration);
    }

    @VisibleForTesting
    void saveGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        String id = googleSignInAccount.getObfuscatedIdentifier();
        saveToStore(getKey(KEY_GOOGLE_SIGN_IN_ACCOUNT_PREFIX, id), googleSignInAccount.toJsonForStorage());
        saveToStore(getKey(KEY_GOOGLE_SIGN_IN_OPTIONS_PREFIX, id), googleSignInOptions.toJson());
    }

    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull(googleSignInAccount);
        Preconditions.checkNotNull(googleSignInOptions);
        saveToStore(KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID, googleSignInAccount.getObfuscatedIdentifier());
        saveGoogleSignInAccount(googleSignInAccount, googleSignInOptions);
    }

    protected void saveToStore(String key, String value) {
        this.mLk.lock();
        try {
            this.mPrefs.edit().putString(key, value).apply();
        } finally {
            this.mLk.unlock();
        }
    }

    @Nullable
    public SignInConfiguration getSavedDefaultSignInConfiguration() {
        return getSavedSignInConfiguration(getFromStore(KEY_DEFAULT_SIGN_IN_ACCOUNT_ID));
    }

    @Nullable
    @VisibleForTesting
    SignInConfiguration getSavedSignInConfiguration(String userId) {
        SignInConfiguration signInConfiguration = null;
        if (!TextUtils.isEmpty(userId)) {
            String value = getFromStore(getKey(KEY_SIGN_IN_CONFIGURATION_PREFIX, userId));
            if (!TextUtils.isEmpty(value)) {
                try {
                    signInConfiguration = SignInConfiguration.fromJsonString(value);
                } catch (JSONException e) {
                }
            }
        }
        return signInConfiguration;
    }

    @Nullable
    public SignInAccount getSavedDefaultSignInAccount() {
        return getSavedSignInAccount(getFromStore(KEY_DEFAULT_SIGN_IN_ACCOUNT_ID));
    }

    @Nullable
    @VisibleForTesting
    SignInAccount getSavedSignInAccount(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return null;
        }
        String value = getFromStore(getKey(KEY_SIGN_IN_ACCOUNT_PREFIX, userId));
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        try {
            SignInAccount signInAccount = SignInAccount.fromJsonString(value);
            if (signInAccount.getGoogleSignInAccount() == null) {
                return signInAccount;
            }
            GoogleSignInAccount googleSignInAccount = getSavedGoogleSignInAccount(signInAccount.getGoogleSignInAccount().getObfuscatedIdentifier());
            if (googleSignInAccount == null) {
                return signInAccount;
            }
            signInAccount.setGoogleSignInAccount(googleSignInAccount);
            return signInAccount;
        } catch (JSONException e) {
            return null;
        }
    }

    @Nullable
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return getSavedGoogleSignInAccount(getFromStore(KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID));
    }

    @Nullable
    @VisibleForTesting
    GoogleSignInAccount getSavedGoogleSignInAccount(String id) {
        GoogleSignInAccount googleSignInAccount = null;
        if (!TextUtils.isEmpty(id)) {
            String value = getFromStore(getKey(KEY_GOOGLE_SIGN_IN_ACCOUNT_PREFIX, id));
            if (value != null) {
                try {
                    googleSignInAccount = GoogleSignInAccount.fromJsonString(value);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInAccount;
    }

    @Nullable
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return getSavedGoogleSignInOptions(getFromStore(KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID));
    }

    @Nullable
    @VisibleForTesting
    GoogleSignInOptions getSavedGoogleSignInOptions(String id) {
        GoogleSignInOptions googleSignInOptions = null;
        if (!TextUtils.isEmpty(id)) {
            String value = getFromStore(getKey(KEY_GOOGLE_SIGN_IN_OPTIONS_PREFIX, id));
            if (value != null) {
                try {
                    googleSignInOptions = GoogleSignInOptions.fromJsonString(value);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInOptions;
    }

    @Nullable
    protected String getFromStore(String key) {
        this.mLk.lock();
        try {
            String string = this.mPrefs.getString(key, null);
            return string;
        } finally {
            this.mLk.unlock();
        }
    }

    public void removeSavedDefaultSignInAccount() {
        String userId = getFromStore(KEY_DEFAULT_SIGN_IN_ACCOUNT_ID);
        removeFromStore(KEY_DEFAULT_SIGN_IN_ACCOUNT_ID);
        removeSavedDefaultGoogleSignInAccount();
        removeSavedSignInAccount(userId);
    }

    @VisibleForTesting
    void removeSavedSignInAccount(String userId) {
        if (!TextUtils.isEmpty(userId)) {
            SignInAccount signInAccount = getSavedSignInAccount(userId);
            removeFromStore(getKey(KEY_SIGN_IN_ACCOUNT_PREFIX, userId));
            removeFromStore(getKey(KEY_SIGN_IN_CONFIGURATION_PREFIX, userId));
            if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                removeSavedGoogleSignInAccount(signInAccount.getGoogleSignInAccount().getObfuscatedIdentifier());
            }
        }
    }

    public void removeSavedDefaultGoogleSignInAccount() {
        String id = getFromStore(KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID);
        removeFromStore(KEY_DEFAULT_GOOGLE_SIGN_IN_ACCOUNT_ID);
        removeSavedGoogleSignInAccount(id);
    }

    @VisibleForTesting
    void removeSavedGoogleSignInAccount(String id) {
        if (!TextUtils.isEmpty(id)) {
            removeFromStore(getKey(KEY_GOOGLE_SIGN_IN_ACCOUNT_PREFIX, id));
            removeFromStore(getKey(KEY_GOOGLE_SIGN_IN_OPTIONS_PREFIX, id));
        }
    }

    protected void removeFromStore(String key) {
        this.mLk.lock();
        try {
            this.mPrefs.edit().remove(key).apply();
        } finally {
            this.mLk.unlock();
        }
    }

    public void clear() {
        this.mLk.lock();
        try {
            this.mPrefs.edit().clear().apply();
        } finally {
            this.mLk.unlock();
        }
    }

    private String getKey(String prefix, String id) {
        return prefix + DELIMITER + id;
    }
}
