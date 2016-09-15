package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gsf.GoogleLoginServiceConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "GoogleSignInOptionsCreator")
public class GoogleSignInOptions implements SafeParcelable, Optional {
    public static final Creator<GoogleSignInOptions> CREATOR;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN;
    private static final String KEY_ACCOUNT_NAME = "accountName";
    private static final String KEY_FORCE_CODE_FOR_REFRESH_TOKEN = "forceCodeForRefreshToken";
    private static final String KEY_HOSTED_DOMAIN = "hostedDomain";
    private static final String KEY_ID_TOKEN_REQUESTED = "idTokenRequested";
    private static final String KEY_SCOPES = "scopes";
    private static final String KEY_SERVER_AUTH_REQUESTED = "serverAuthRequested";
    private static final String KEY_SERVER_CLIENT_ID = "serverClientId";
    @VisibleForTesting
    public static final Scope SCOPE_EMAIL;
    @VisibleForTesting
    public static final Scope SCOPE_OPEN_ID;
    private static Comparator<Scope> SCOPE_ORDER = null;
    @VisibleForTesting
    public static final Scope SCOPE_PROFILE;
    private static final int VERSION_CODE = 2;
    @Field(getter = "getAccount", id = 3)
    private Account mAccount;
    @Field(getter = "isForceCodeForRefreshToken", id = 6)
    private final boolean mForceCodeForRefreshToken;
    @Field(getter = "getHostedDomain", id = 8)
    private String mHostedDomain;
    @Field(getter = "isIdTokenRequested", id = 4)
    private boolean mIdTokenRequested;
    @Field(getter = "getScopes", id = 2)
    private final ArrayList<Scope> mScopes;
    @Field(getter = "isServerAuthCodeRequested", id = 5)
    private final boolean mServerAuthCodeRequested;
    @Field(getter = "getServerClientId", id = 7)
    private String mServerClientId;
    @VersionField(id = 1)
    final int versionCode;

    public static final class Builder {
        private Account mAccount;
        private boolean mForceCodeForRefreshToken;
        private String mHostedDomain;
        private boolean mIdTokenRequested;
        private Set<Scope> mScopes;
        private boolean mServerAuthCodeRequested;
        private String mServerClientId;

        public Builder() {
            this.mScopes = new HashSet();
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            this.mScopes = new HashSet();
            Preconditions.checkNotNull(googleSignInOptions);
            this.mScopes = new HashSet(googleSignInOptions.mScopes);
            this.mServerAuthCodeRequested = googleSignInOptions.mServerAuthCodeRequested;
            this.mForceCodeForRefreshToken = googleSignInOptions.mForceCodeForRefreshToken;
            this.mIdTokenRequested = googleSignInOptions.mIdTokenRequested;
            this.mServerClientId = googleSignInOptions.mServerClientId;
            this.mAccount = googleSignInOptions.mAccount;
            this.mHostedDomain = googleSignInOptions.mHostedDomain;
        }

        public Builder requestId() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_OPEN_ID);
            return this;
        }

        public Builder requestEmail() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_EMAIL);
            return this;
        }

        public Builder requestProfile() {
            this.mScopes.add(GoogleSignInOptions.SCOPE_PROFILE);
            return this;
        }

        public Builder requestScopes(Scope scope, Scope... scopes) {
            this.mScopes.add(scope);
            this.mScopes.addAll(Arrays.asList(scopes));
            return this;
        }

        public Builder requestIdToken(String serverClientId) {
            this.mIdTokenRequested = true;
            this.mServerClientId = checkServerClientId(serverClientId);
            return this;
        }

        public Builder requestPhatIdToken(String serverClientId) {
            return requestIdToken(serverClientId).requestProfile().requestEmail();
        }

        public Builder requestServerAuthCode(String serverClientId) {
            return requestServerAuthCode(serverClientId, false);
        }

        public Builder requestServerAuthCode(String serverClientId, boolean forceCodeForRefreshToken) {
            this.mServerAuthCodeRequested = true;
            this.mServerClientId = checkServerClientId(serverClientId);
            this.mForceCodeForRefreshToken = forceCodeForRefreshToken;
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.mAccount = new Account(Preconditions.checkNotEmpty(accountName), GoogleLoginServiceConstants.ACCOUNT_TYPE);
            return this;
        }

        public Builder setAccount(Account account) {
            this.mAccount = (Account) Preconditions.checkNotNull(account);
            return this;
        }

        public Builder setHostedDomain(String hostedDomain) {
            this.mHostedDomain = Preconditions.checkNotEmpty(hostedDomain);
            return this;
        }

        public GoogleSignInOptions build() {
            if (this.mIdTokenRequested && (this.mAccount == null || !this.mScopes.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(this.mAccount, this.mIdTokenRequested, this.mServerAuthCodeRequested, this.mForceCodeForRefreshToken, this.mServerClientId, this.mHostedDomain, null);
        }

        private String checkServerClientId(String id) {
            Preconditions.checkNotEmpty(id);
            boolean z = this.mServerClientId == null || this.mServerClientId.equals(id);
            Preconditions.checkArgument(z, "two different server client ids provided");
            return id;
        }
    }

    static {
        SCOPE_PROFILE = new Scope(Scopes.PROFILE);
        SCOPE_EMAIL = new Scope(PeopleEmail.EMAIL_ADDRESS);
        SCOPE_OPEN_ID = new Scope(Scopes.OPEN_ID);
        DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
        CREATOR = new GoogleSignInOptionsCreator();
        SCOPE_ORDER = new Comparator<Scope>() {
            public int compare(Scope scope1, Scope scope2) {
                return scope1.getScopeUri().compareTo(scope2.getScopeUri());
            }
        };
    }

    @Nullable
    public static GoogleSignInOptions fromJsonString(@Nullable String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject json = new JSONObject(jsonString);
        Set<Scope> scopes = new HashSet();
        JSONArray jsonArray = json.getJSONArray(KEY_SCOPES);
        int size = jsonArray.length();
        for (int i = 0; i < size; i++) {
            scopes.add(new Scope(jsonArray.getString(i)));
        }
        Account account = null;
        String accountName = json.optString(KEY_ACCOUNT_NAME, null);
        if (!TextUtils.isEmpty(accountName)) {
            account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
        return new GoogleSignInOptions(scopes, account, json.getBoolean(KEY_ID_TOKEN_REQUESTED), json.getBoolean(KEY_SERVER_AUTH_REQUESTED), json.getBoolean(KEY_FORCE_CODE_FOR_REFRESH_TOKEN), json.optString(KEY_SERVER_CLIENT_ID, null), json.optString(KEY_HOSTED_DOMAIN, null));
    }

    @Constructor
    GoogleSignInOptions(@Param(id = 1) int versionCode, @Param(id = 2) ArrayList<Scope> scopes, @Param(id = 3) Account account, @Param(id = 4) boolean idTokenRequested, @Param(id = 5) boolean serverAuthCodeRequested, @Param(id = 6) boolean forceCodeForRefreshToken, @Param(id = 7) String serverClientId, @Param(id = 8) String hostedDomain) {
        this.versionCode = versionCode;
        this.mScopes = scopes;
        this.mAccount = account;
        this.mIdTokenRequested = idTokenRequested;
        this.mServerAuthCodeRequested = serverAuthCodeRequested;
        this.mForceCodeForRefreshToken = forceCodeForRefreshToken;
        this.mServerClientId = serverClientId;
        this.mHostedDomain = hostedDomain;
    }

    private GoogleSignInOptions(Set<Scope> scopes, Account account, boolean idTokenRequested, boolean serverAuthCodeRequested, boolean forceCodeForRefreshToken, String serverClientId, String hostedDomain) {
        this((int) VERSION_CODE, new ArrayList(scopes), account, idTokenRequested, serverAuthCodeRequested, forceCodeForRefreshToken, serverClientId, hostedDomain);
    }

    public ArrayList<Scope> getScopes() {
        return new ArrayList(this.mScopes);
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.mScopes.toArray(new Scope[this.mScopes.size()]);
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public boolean isIdTokenRequested() {
        return this.mIdTokenRequested;
    }

    public boolean isServerAuthCodeRequested() {
        return this.mServerAuthCodeRequested;
    }

    public boolean isForceCodeForRefreshToken() {
        return this.mForceCodeForRefreshToken;
    }

    public String getServerClientId() {
        return this.mServerClientId;
    }

    public String getHostedDomain() {
        return this.mHostedDomain;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        GoogleSignInOptionsCreator.writeToParcel(this, out, flags);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions config = (GoogleSignInOptions) obj;
            if (this.mScopes.size() != config.getScopes().size() || !this.mScopes.containsAll(config.getScopes())) {
                return false;
            }
            if (this.mAccount == null) {
                if (config.getAccount() != null) {
                    return false;
                }
            } else if (!this.mAccount.equals(config.getAccount())) {
                return false;
            }
            if (TextUtils.isEmpty(this.mServerClientId)) {
                if (!TextUtils.isEmpty(config.getServerClientId())) {
                    return false;
                }
            } else if (!this.mServerClientId.equals(config.getServerClientId())) {
                return false;
            }
            if (this.mForceCodeForRefreshToken == config.isForceCodeForRefreshToken() && this.mIdTokenRequested == config.isIdTokenRequested() && this.mServerAuthCodeRequested == config.isServerAuthCodeRequested()) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        ArrayList<String> scopes = new ArrayList();
        Iterator i$ = this.mScopes.iterator();
        while (i$.hasNext()) {
            scopes.add(((Scope) i$.next()).getScopeUri());
        }
        Collections.sort(scopes);
        return new HashAccumulator().addObject(scopes).addObject(this.mAccount).addObject(this.mServerClientId).addBoolean(this.mForceCodeForRefreshToken).addBoolean(this.mIdTokenRequested).addBoolean(this.mServerAuthCodeRequested).hash();
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            Collections.sort(this.mScopes, SCOPE_ORDER);
            Iterator i$ = this.mScopes.iterator();
            while (i$.hasNext()) {
                jsonArray.put(((Scope) i$.next()).getScopeUri());
            }
            json.put(KEY_SCOPES, jsonArray);
            if (this.mAccount != null) {
                json.put(KEY_ACCOUNT_NAME, this.mAccount.name);
            }
            json.put(KEY_ID_TOKEN_REQUESTED, this.mIdTokenRequested);
            json.put(KEY_FORCE_CODE_FOR_REFRESH_TOKEN, this.mForceCodeForRefreshToken);
            json.put(KEY_SERVER_AUTH_REQUESTED, this.mServerAuthCodeRequested);
            if (!TextUtils.isEmpty(this.mServerClientId)) {
                json.put(KEY_SERVER_CLIENT_ID, this.mServerClientId);
            }
            if (!TextUtils.isEmpty(this.mHostedDomain)) {
                json.put(KEY_HOSTED_DOMAIN, this.mHostedDomain);
            }
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
