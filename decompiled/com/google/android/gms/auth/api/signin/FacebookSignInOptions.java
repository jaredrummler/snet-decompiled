package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "FacebookSignInOptionsCreator")
public class FacebookSignInOptions implements SafeParcelable {
    public static final Creator<FacebookSignInOptions> CREATOR;
    private static final String KEY_SCOPES = "scopes";
    private static final int VERSION_CODE = 1;
    @Field(getter = "getCustomFacebookSignInActivityIntent", id = 2)
    private Intent mIntent;
    @Field(getter = "getScopes", id = 3)
    private final ArrayList<String> mScopes;
    @VersionField(id = 1)
    final int versionCode;

    static {
        CREATOR = new FacebookSignInOptionsCreator();
    }

    @Nullable
    public static FacebookSignInOptions fromJsonString(@Nullable String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        FacebookSignInOptions facebookSignInOptions = new FacebookSignInOptions();
        JSONArray jsonArray = new JSONObject(jsonString).getJSONArray(KEY_SCOPES);
        int size = jsonArray.length();
        for (int i = 0; i < size; i += VERSION_CODE) {
            facebookSignInOptions.addScope(jsonArray.getString(i));
        }
        return facebookSignInOptions;
    }

    @Constructor
    FacebookSignInOptions(@Param(id = 1) int versionCode, @Param(id = 2) Intent intent, @Param(id = 3) ArrayList<String> scopes) {
        this.versionCode = versionCode;
        this.mIntent = intent;
        this.mScopes = scopes;
    }

    public FacebookSignInOptions() {
        this(VERSION_CODE, null, new ArrayList());
    }

    public FacebookSignInOptions setCustomFacebookSignInActivityIntent(Intent intent) {
        this.mIntent = (Intent) Preconditions.checkNotNull(intent, "Intent cannot be null");
        return this;
    }

    @Nullable
    public Intent getCustomFacebookSignInActivityIntent() {
        return this.mIntent;
    }

    public FacebookSignInOptions addScope(String scope) {
        if (!(TextUtils.isEmpty(scope) || this.mScopes.contains(scope))) {
            this.mScopes.add(scope);
        }
        return this;
    }

    public ArrayList<String> getScopes() {
        return new ArrayList(this.mScopes);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        FacebookSignInOptionsCreator.writeToParcel(this, out, flags);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            FacebookSignInOptions config = (FacebookSignInOptions) obj;
            if (this.mScopes.size() == config.getScopes().size() && this.mScopes.containsAll(config.getScopes())) {
                return true;
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        Collections.sort(this.mScopes);
        return this.mScopes.hashCode();
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            Iterator i$ = this.mScopes.iterator();
            while (i$.hasNext()) {
                jsonArray.put((String) i$.next());
            }
            json.put(KEY_SCOPES, jsonArray);
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
