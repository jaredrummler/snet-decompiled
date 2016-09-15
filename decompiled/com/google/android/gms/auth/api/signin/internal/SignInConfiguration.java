package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.EmailSignInOptions;
import com.google.android.gms.auth.api.signin.FacebookSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "SignInConfigurationCreator")
public final class SignInConfiguration implements SafeParcelable {
    public static final Creator<SignInConfiguration> CREATOR;
    public static final String DEFAULT_MODE_QUERY_NAME = "mode";
    private static final String KEY_API_KEY = "apiKey";
    private static final String KEY_CONSUMER_PACKAGE_NAME = "consumerPackageName";
    private static final String KEY_EMAIL_SIGN_IN_OPTIONS = "emailSignInOptions";
    private static final String KEY_FACEBOOK_SIGN_IN_OPTIONS = "facebookSignInOptions";
    private static final String KEY_GOOGLE_SIGN_IN_OPTIONS = "googleSignInOptions";
    private static final String KEY_SERVER_CLIENT_ID = "serverClientId";
    private static final int VERSION_CODE = 2;
    @Field(getter = "getApiKey", id = 7)
    private String mApiKey;
    @Field(getter = "getConsumerPkgName", id = 2)
    private final String mConsumerPkgName;
    @Field(getter = "getEmailConfig", id = 4)
    private EmailSignInOptions mEmailSignInOptions;
    @Field(getter = "getFacebookConfig", id = 6)
    private FacebookSignInOptions mFacebookSignInOptions;
    @Field(getter = "getGoogleConfig", id = 5)
    private GoogleSignInOptions mGoogleSignInOptions;
    @Field(getter = "getServerClientId", id = 3)
    private String mServerClientId;
    @VersionField(id = 1)
    final int versionCode;

    static {
        CREATOR = new SignInConfigurationCreator();
    }

    @Nullable
    public static SignInConfiguration fromJsonString(@Nullable String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject json = new JSONObject(jsonString);
        SignInConfiguration signInConfiguration = new SignInConfiguration(json.getString(KEY_CONSUMER_PACKAGE_NAME));
        String serverClientId = json.optString(KEY_SERVER_CLIENT_ID);
        if (!TextUtils.isEmpty(serverClientId)) {
            signInConfiguration.setServerClientId(serverClientId);
        }
        String emailSignInOptions = json.optString(KEY_EMAIL_SIGN_IN_OPTIONS);
        if (!TextUtils.isEmpty(emailSignInOptions)) {
            signInConfiguration.setEmailConfig(EmailSignInOptions.fromJsonString(emailSignInOptions));
        }
        String googleSignInOptions = json.optString(KEY_GOOGLE_SIGN_IN_OPTIONS);
        if (!TextUtils.isEmpty(googleSignInOptions)) {
            signInConfiguration.setGoogleConfig(GoogleSignInOptions.fromJsonString(googleSignInOptions));
        }
        String facebookSignInOptions = json.optString(KEY_FACEBOOK_SIGN_IN_OPTIONS);
        if (!TextUtils.isEmpty(facebookSignInOptions)) {
            signInConfiguration.setFacebookConfig(FacebookSignInOptions.fromJsonString(facebookSignInOptions));
        }
        String apiKey = json.optString(KEY_API_KEY);
        if (TextUtils.isEmpty(apiKey)) {
            return signInConfiguration;
        }
        signInConfiguration.setApiKey(apiKey);
        return signInConfiguration;
    }

    @Constructor
    SignInConfiguration(@Param(id = 1) int versionCode, @Param(id = 2) String consumerPkgName, @Param(id = 3) String serverClientId, @Param(id = 4) EmailSignInOptions emailConfig, @Param(id = 5) GoogleSignInOptions googleConfig, @Param(id = 6) FacebookSignInOptions facebookConfig, @Param(id = 7) String apiKey) {
        this.versionCode = versionCode;
        this.mConsumerPkgName = Preconditions.checkNotEmpty(consumerPkgName);
        this.mServerClientId = serverClientId;
        this.mEmailSignInOptions = emailConfig;
        this.mGoogleSignInOptions = googleConfig;
        this.mFacebookSignInOptions = facebookConfig;
        this.mApiKey = apiKey;
    }

    public SignInConfiguration(String consumerPkgName) {
        this(VERSION_CODE, consumerPkgName, null, null, null, null, null);
    }

    public String getConsumerPkgName() {
        return this.mConsumerPkgName;
    }

    public SignInConfiguration setServerClientId(String clientId) {
        this.mServerClientId = Preconditions.checkNotEmpty(clientId, "Server client id cannot be empty.");
        return this;
    }

    public String getServerClientId() {
        return this.mServerClientId;
    }

    public SignInConfiguration setEmailConfig(EmailSignInOptions emailConfig) {
        this.mEmailSignInOptions = (EmailSignInOptions) Preconditions.checkNotNull(emailConfig, "EmailSignInOptions cannot be null.");
        return this;
    }

    public EmailSignInOptions getEmailConfig() {
        return this.mEmailSignInOptions;
    }

    public SignInConfiguration setGoogleConfig(GoogleSignInOptions googleConfig) {
        this.mGoogleSignInOptions = (GoogleSignInOptions) Preconditions.checkNotNull(googleConfig, "GoogleSignInOptions cannot be null.");
        return this;
    }

    public GoogleSignInOptions getGoogleConfig() {
        return this.mGoogleSignInOptions;
    }

    public SignInConfiguration setFacebookConfig(FacebookSignInOptions facebookSignInOptions) {
        this.mFacebookSignInOptions = (FacebookSignInOptions) Preconditions.checkNotNull(facebookSignInOptions, "FacebookSignInOptions cannot be null.");
        return this;
    }

    public FacebookSignInOptions getFacebookConfig() {
        return this.mFacebookSignInOptions;
    }

    public SignInConfiguration setApiKey(String apiKey) {
        this.mApiKey = apiKey;
        return this;
    }

    public String getApiKey() {
        return this.mApiKey;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        SignInConfigurationCreator.writeToParcel(this, out, flags);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            SignInConfiguration config = (SignInConfiguration) obj;
            if (!this.mConsumerPkgName.equals(config.getConsumerPkgName())) {
                return false;
            }
            if (TextUtils.isEmpty(this.mServerClientId)) {
                if (!TextUtils.isEmpty(config.getServerClientId())) {
                    return false;
                }
            } else if (!this.mServerClientId.equals(config.getServerClientId())) {
                return false;
            }
            if (TextUtils.isEmpty(this.mApiKey)) {
                if (!TextUtils.isEmpty(config.getApiKey())) {
                    return false;
                }
            } else if (!this.mApiKey.equals(config.getApiKey())) {
                return false;
            }
            if (this.mEmailSignInOptions == null) {
                if (config.getEmailConfig() != null) {
                    return false;
                }
            } else if (!this.mEmailSignInOptions.equals(config.getEmailConfig())) {
                return false;
            }
            if (this.mFacebookSignInOptions == null) {
                if (config.getFacebookConfig() != null) {
                    return false;
                }
            } else if (!this.mFacebookSignInOptions.equals(config.getFacebookConfig())) {
                return false;
            }
            if (this.mGoogleSignInOptions == null) {
                if (config.getGoogleConfig() != null) {
                    return false;
                }
            } else if (!this.mGoogleSignInOptions.equals(config.getGoogleConfig())) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        return new HashAccumulator().addObject(this.mConsumerPkgName).addObject(this.mServerClientId).addObject(this.mApiKey).addObject(this.mEmailSignInOptions).addObject(this.mGoogleSignInOptions).addObject(this.mFacebookSignInOptions).hash();
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put(KEY_CONSUMER_PACKAGE_NAME, this.mConsumerPkgName);
            if (!TextUtils.isEmpty(this.mServerClientId)) {
                json.put(KEY_SERVER_CLIENT_ID, this.mServerClientId);
            }
            if (this.mEmailSignInOptions != null) {
                json.put(KEY_EMAIL_SIGN_IN_OPTIONS, this.mEmailSignInOptions.toJson());
            }
            if (this.mGoogleSignInOptions != null) {
                json.put(KEY_GOOGLE_SIGN_IN_OPTIONS, this.mGoogleSignInOptions.toJson());
            }
            if (this.mFacebookSignInOptions != null) {
                json.put(KEY_FACEBOOK_SIGN_IN_OPTIONS, this.mFacebookSignInOptions.toJson());
            }
            if (!TextUtils.isEmpty(this.mApiKey)) {
                json.put(KEY_API_KEY, this.mApiKey);
            }
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
