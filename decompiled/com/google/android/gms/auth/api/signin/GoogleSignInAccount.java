package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "GoogleSignInAccountCreator")
public class GoogleSignInAccount implements SafeParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR;
    @VisibleForTesting
    static final int EXPIRATION_MARGIN_TIME_SEC = 300;
    @VisibleForTesting
    static final String KEY_DISPLAY_NAME = "displayName";
    @VisibleForTesting
    static final String KEY_EMAIL = "email";
    @VisibleForTesting
    static final String KEY_EXPIRATION_TIME = "expirationTime";
    @VisibleForTesting
    static final String KEY_GRANTED_SCOPES = "grantedScopes";
    @VisibleForTesting
    static final String KEY_ID = "id";
    @VisibleForTesting
    static final String KEY_ID_TOKEN = "tokenId";
    @VisibleForTesting
    static final String KEY_OBFUSCATED_IDENTIFIER = "obfuscatedIdentifier";
    @VisibleForTesting
    static final String KEY_PHOTO_URL = "photoUrl";
    @VisibleForTesting
    static final String KEY_SERVER_AUTH_CODE = "serverAuthCode";
    private static Comparator<Scope> SCOPE_ORDER = null;
    private static final int VERSION_CODE = 2;
    @VisibleForTesting
    public static Clock sClock;
    @Field(getter = "getDisplayName", id = 5)
    private String mDisplayName;
    @Field(getter = "getEmail", id = 4)
    private String mEmail;
    @Field(getter = "getExpirationTimeSecs", id = 8)
    private long mExpirationTimeSecs;
    @Field(id = 10)
    List<Scope> mGrantedScopes;
    @Field(getter = "getId", id = 2)
    private String mId;
    @Field(getter = "getIdToken", id = 3)
    private String mIdToken;
    @Field(getter = "getObfuscatedIdentifier", id = 9)
    private String mObfuscatedIdentifier;
    @Field(getter = "getPhotoUrl", id = 6)
    private Uri mPhotoUrl;
    @Field(getter = "getServerAuthCode", id = 7)
    private String mServerAuthCode;
    @VersionField(id = 1)
    final int versionCode;

    static {
        CREATOR = new GoogleSignInAccountCreator();
        sClock = DefaultClock.getInstance();
        SCOPE_ORDER = new Comparator<Scope>() {
            public int compare(Scope scope1, Scope scope2) {
                return scope1.getScopeUri().compareTo(scope2.getScopeUri());
            }
        };
    }

    @Nullable
    public static GoogleSignInAccount fromJsonString(@Nullable String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject json = new JSONObject(jsonString);
        Uri photoUrl = null;
        String photoUrlStr = json.optString(KEY_PHOTO_URL, null);
        if (!TextUtils.isEmpty(photoUrlStr)) {
            photoUrl = Uri.parse(photoUrlStr);
        }
        long expirationTime = Long.parseLong(json.getString(KEY_EXPIRATION_TIME));
        Set<Scope> scopeSet = new HashSet();
        JSONArray jsonArray = json.getJSONArray(KEY_GRANTED_SCOPES);
        int size = jsonArray.length();
        for (int i = 0; i < size; i++) {
            scopeSet.add(new Scope(jsonArray.getString(i)));
        }
        return create(json.optString(KEY_ID), json.optString(KEY_ID_TOKEN, null), json.optString(KEY_EMAIL, null), json.optString(KEY_DISPLAY_NAME, null), photoUrl, Long.valueOf(expirationTime), json.getString(KEY_OBFUSCATED_IDENTIFIER), scopeSet).setServerAuthCode(json.optString(KEY_SERVER_AUTH_CODE, null));
    }

    public static GoogleSignInAccount create(@Nullable String id, @Nullable String idToken, @Nullable String email, @Nullable String displayName, @Nullable Uri photoUrl, @Nullable Long expirationTimeSecs, @NonNull String obfuscatedIdentifier, @NonNull Set<Scope> grantedScopeSet) {
        if (expirationTimeSecs == null) {
            expirationTimeSecs = Long.valueOf(sClock.currentTimeMillis() / 1000);
        }
        return new GoogleSignInAccount(VERSION_CODE, id, idToken, email, displayName, photoUrl, null, expirationTimeSecs.longValue(), Preconditions.checkNotEmpty(obfuscatedIdentifier), new ArrayList((Collection) Preconditions.checkNotNull(grantedScopeSet)));
    }

    @Constructor
    GoogleSignInAccount(@Param(id = 1) int versionCode, @Param(id = 2) String id, @Param(id = 3) String idToken, @Param(id = 4) String email, @Param(id = 5) String displayName, @Param(id = 6) Uri photoUrl, @Param(id = 7) String serverAuthCode, @Param(id = 8) long expirationTimeSecs, @Param(id = 9) String obfuscatedIdentifier, @Param(id = 10) List<Scope> grantedScopes) {
        this.versionCode = versionCode;
        this.mId = id;
        this.mIdToken = idToken;
        this.mEmail = email;
        this.mDisplayName = displayName;
        this.mPhotoUrl = photoUrl;
        this.mServerAuthCode = serverAuthCode;
        this.mExpirationTimeSecs = expirationTimeSecs;
        this.mObfuscatedIdentifier = obfuscatedIdentifier;
        this.mGrantedScopes = grantedScopes;
    }

    @Nullable
    public String getId() {
        return this.mId;
    }

    @Nullable
    public String getIdToken() {
        return this.mIdToken;
    }

    @Nullable
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

    public GoogleSignInAccount setServerAuthCode(String serverAuthCode) {
        this.mServerAuthCode = serverAuthCode;
        return this;
    }

    @Nullable
    public String getServerAuthCode() {
        return this.mServerAuthCode;
    }

    public long getExpirationTimeSecs() {
        return this.mExpirationTimeSecs;
    }

    public boolean isExpired() {
        return sClock.currentTimeMillis() / 1000 >= this.mExpirationTimeSecs - 300;
    }

    @NonNull
    public String getObfuscatedIdentifier() {
        return this.mObfuscatedIdentifier;
    }

    @NonNull
    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.mGrantedScopes);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        GoogleSignInAccountCreator.writeToParcel(this, out, flags);
    }

    public boolean equals(Object obj) {
        if (obj instanceof GoogleSignInAccount) {
            return ((GoogleSignInAccount) obj).toJson().equals(toJson());
        }
        return false;
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    public String toJsonForStorage() {
        JSONObject json = toJsonObject();
        json.remove(KEY_SERVER_AUTH_CODE);
        return json.toString();
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            if (getId() != null) {
                json.put(KEY_ID, getId());
            }
            if (getIdToken() != null) {
                json.put(KEY_ID_TOKEN, getIdToken());
            }
            if (getEmail() != null) {
                json.put(KEY_EMAIL, getEmail());
            }
            if (getDisplayName() != null) {
                json.put(KEY_DISPLAY_NAME, getDisplayName());
            }
            if (getPhotoUrl() != null) {
                json.put(KEY_PHOTO_URL, getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                json.put(KEY_SERVER_AUTH_CODE, getServerAuthCode());
            }
            json.put(KEY_EXPIRATION_TIME, this.mExpirationTimeSecs);
            json.put(KEY_OBFUSCATED_IDENTIFIER, getObfuscatedIdentifier());
            JSONArray jsonArray = new JSONArray();
            Collections.sort(this.mGrantedScopes, SCOPE_ORDER);
            for (Scope scope : this.mGrantedScopes) {
                jsonArray.put(scope.getScopeUri());
            }
            json.put(KEY_GRANTED_SCOPES, jsonArray);
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
