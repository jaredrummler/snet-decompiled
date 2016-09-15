package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "SignInAccountCreator")
public class SignInAccount implements SafeParcelable {
    public static final Creator<SignInAccount> CREATOR;
    private static final String DEFAULT_USER_ID = "<<default user id>>";
    @VisibleForTesting
    static final String KEY_DISPLAY_NAME = "displayName";
    @VisibleForTesting
    static final String KEY_EMAIL = "email";
    @VisibleForTesting
    static final String KEY_GOOGLE_SIGN_IN_ACCOUNT = "googleSignInAccount";
    @VisibleForTesting
    static final String KEY_ID_TOKEN = "tokenId";
    @VisibleForTesting
    static final String KEY_LOCAL_ID = "localId";
    @VisibleForTesting
    static final String KEY_PHOTO_URL = "photoUrl";
    @VisibleForTesting
    static final String KEY_PROVIDER_ID = "providerId";
    @VisibleForTesting
    static final String KEY_REFRESH_TOKEN = "refreshToken";
    private static final int VERSION_CODE = 2;
    @Field(getter = "getDisplayName", id = 5)
    private String mDisplayName;
    @Field(getter = "getEmail", id = 4)
    private String mEmail;
    @Field(getter = "getGoogleSignInAccount", id = 7)
    private GoogleSignInAccount mGoogleSignInAccount;
    @Field(getter = "getIdToken", id = 3)
    private String mIdToken;
    @Field(getter = "getPhotoUrl", id = 6)
    private Uri mPhotoUrl;
    @Field(getter = "getProviderId", id = 2)
    private String mProviderId;
    @Field(getter = "getRefreshToken", id = 9)
    private String mRefreshToken;
    @Field(defaultValue = "", getter = "getUserId", id = 8)
    private String mUserId;
    @VersionField(id = 1)
    final int versionCode;

    static {
        CREATOR = new SignInAccountCreator();
    }

    @Nullable
    public static SignInAccount fromJsonString(@Nullable String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject json = new JSONObject(jsonString);
        Uri photoUrl = null;
        String photoUrlStr = json.optString(KEY_PHOTO_URL, null);
        if (!TextUtils.isEmpty(photoUrlStr)) {
            photoUrl = Uri.parse(photoUrlStr);
        }
        return create(IdProvider.fromProviderId(json.optString(KEY_PROVIDER_ID, null)), json.optString(KEY_ID_TOKEN, null), json.getString(KEY_EMAIL), json.optString(KEY_DISPLAY_NAME, null), photoUrl, json.getString(KEY_LOCAL_ID), json.optString(KEY_REFRESH_TOKEN)).setGoogleSignInAccount(GoogleSignInAccount.fromJsonString(json.optString(KEY_GOOGLE_SIGN_IN_ACCOUNT)));
    }

    public static SignInAccount create(@Nullable IdProvider idProvider, @Nullable String idToken, String email, String displayName, Uri photoUrl, String userId) {
        String providerId = null;
        if (idProvider != null) {
            providerId = idProvider.getProviderId();
        }
        return new SignInAccount(VERSION_CODE, providerId, idToken, email, displayName, photoUrl, null, userId, null);
    }

    public static SignInAccount create(@Nullable IdProvider idProvider, @Nullable String idToken, String email, String displayName, Uri photoUrl, String userId, String refreshToken) {
        String providerId = null;
        if (idProvider != null) {
            providerId = idProvider.getProviderId();
        }
        return new SignInAccount(VERSION_CODE, providerId, idToken, email, displayName, photoUrl, null, userId, refreshToken);
    }

    public static SignInAccount createFromGoogleSignInAccount(GoogleSignInAccount account) {
        Preconditions.checkNotNull(account);
        SignInAccount signInAccount = create(null, null, !TextUtils.isEmpty(account.getEmail()) ? account.getEmail() : GoogleApiClient.DEFAULT_ACCOUNT, null, null, DEFAULT_USER_ID);
        signInAccount.setGoogleSignInAccount(account);
        return signInAccount;
    }

    @Constructor
    SignInAccount(@Param(id = 1) int versionCode, @Param(id = 2) String providerId, @Param(id = 3) String idToken, @Param(id = 4) String email, @Param(id = 5) String displayName, @Param(id = 6) Uri photoUrl, @Param(id = 7) GoogleSignInAccount googleSignInAccount, @Param(id = 8) String userId, @Param(id = 9) String refreshToken) {
        this.versionCode = versionCode;
        this.mEmail = Preconditions.checkNotEmpty(email, "Email cannot be empty.");
        this.mDisplayName = displayName;
        this.mPhotoUrl = photoUrl;
        this.mProviderId = providerId;
        this.mIdToken = idToken;
        this.mGoogleSignInAccount = googleSignInAccount;
        this.mUserId = Preconditions.checkNotEmpty(userId);
        this.mRefreshToken = refreshToken;
    }

    @Nullable
    public String getIdToken() {
        return this.mIdToken;
    }

    public String getEmail() {
        return this.mEmail;
    }

    @Nullable
    public String getDisplayName() {
        return this.mDisplayName;
    }

    @Nullable
    public Uri getPhotoUrl() {
        return this.mPhotoUrl;
    }

    @Nullable
    public IdProvider getIdProvider() {
        return IdProvider.fromProviderId(this.mProviderId);
    }

    @Nullable
    public GoogleSignInAccount getGoogleSignInAccount() {
        return this.mGoogleSignInAccount;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public SignInAccount setGoogleSignInAccount(GoogleSignInAccount account) {
        this.mGoogleSignInAccount = account;
        return this;
    }

    public SignInAccount setRefreshToken(String refreshToken) {
        this.mRefreshToken = Preconditions.checkNotEmpty(refreshToken);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        SignInAccountCreator.writeToParcel(this, out, flags);
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    String getProviderId() {
        return this.mProviderId;
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put(KEY_EMAIL, getEmail());
            if (!TextUtils.isEmpty(this.mDisplayName)) {
                json.put(KEY_DISPLAY_NAME, this.mDisplayName);
            }
            if (this.mPhotoUrl != null) {
                json.put(KEY_PHOTO_URL, this.mPhotoUrl.toString());
            }
            if (!TextUtils.isEmpty(this.mProviderId)) {
                json.put(KEY_PROVIDER_ID, this.mProviderId);
            }
            if (!TextUtils.isEmpty(this.mIdToken)) {
                json.put(KEY_ID_TOKEN, this.mIdToken);
            }
            if (this.mGoogleSignInAccount != null) {
                json.put(KEY_GOOGLE_SIGN_IN_ACCOUNT, this.mGoogleSignInAccount.toJson());
            }
            if (!TextUtils.isEmpty(this.mRefreshToken)) {
                json.put(KEY_REFRESH_TOKEN, this.mRefreshToken);
            }
            json.put(KEY_LOCAL_ID, getUserId());
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
