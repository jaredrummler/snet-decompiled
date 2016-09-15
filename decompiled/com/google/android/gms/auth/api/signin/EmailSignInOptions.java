package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
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

@Class(creator = "EmailSignInOptionsCreator")
public class EmailSignInOptions implements SafeParcelable {
    public static final Creator<EmailSignInOptions> CREATOR;
    private static final String KEY_MODE_QUERY_NAME = "modeQueryName";
    private static final String KEY_SERVER_WIDGET_URL = "serverWidgetUrl";
    private static final String KEY_TOS_URL = "tosUrl";
    private static final int VERSION_CODE = 1;
    @Field(getter = "getModeQueryName", id = 3)
    private String mModeQueryName;
    @Field(getter = "getServerWidgetUrl", id = 2)
    private final Uri mServerWidgetUrl;
    @Field(getter = "getTermsOfServiceUrl", id = 4)
    private Uri mTosUrl;
    @VersionField(id = 1)
    final int versionCode;

    static {
        CREATOR = new EmailSignInOptionsCreator();
    }

    @Nullable
    public static EmailSignInOptions fromJsonString(@Nullable String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JSONObject json = new JSONObject(jsonString);
        EmailSignInOptions emailSignInOptions = new EmailSignInOptions(Uri.parse(json.getString(KEY_SERVER_WIDGET_URL)));
        String tosUrl = json.optString(KEY_TOS_URL);
        if (!TextUtils.isEmpty(tosUrl)) {
            emailSignInOptions.setTermsOfServiceUrl(Uri.parse(tosUrl));
        }
        String modeQueryName = json.optString(KEY_MODE_QUERY_NAME);
        if (TextUtils.isEmpty(modeQueryName)) {
            return emailSignInOptions;
        }
        emailSignInOptions.setModeQueryName(modeQueryName);
        return emailSignInOptions;
    }

    @Constructor
    EmailSignInOptions(@Param(id = 1) int versionCode, @Param(id = 2) Uri serverWidgetUrl, @Param(id = 3) String modeQueryName, @Param(id = 4) Uri tosUrl) {
        Preconditions.checkNotNull(serverWidgetUrl, "Server widget url cannot be null in order to use email/password sign in.");
        Preconditions.checkNotEmpty(serverWidgetUrl.toString(), "Server widget url cannot be null in order to use email/password sign in.");
        Preconditions.checkArgument(Patterns.WEB_URL.matcher(serverWidgetUrl.toString()).matches(), "Invalid server widget url");
        this.versionCode = versionCode;
        this.mServerWidgetUrl = serverWidgetUrl;
        this.mModeQueryName = modeQueryName;
        this.mTosUrl = tosUrl;
    }

    public EmailSignInOptions(Uri serverWidgetUrl) {
        this(VERSION_CODE, serverWidgetUrl, null, null);
    }

    public Uri getServerWidgetUrl() {
        return this.mServerWidgetUrl;
    }

    public EmailSignInOptions setTermsOfServiceUrl(Uri url) {
        this.mTosUrl = (Uri) Preconditions.checkNotNull(url, "Uri cannot be null.");
        Preconditions.checkNotEmpty(url.toString(), "Uri String cannot be empty.");
        Preconditions.checkArgument(Patterns.WEB_URL.matcher(url.toString()).matches(), "Invalid Terms of Service url");
        return this;
    }

    @Nullable
    public Uri getTermsOfServiceUrl() {
        return this.mTosUrl;
    }

    public EmailSignInOptions setModeQueryName(String modeQueryName) {
        this.mModeQueryName = Preconditions.checkNotEmpty(modeQueryName);
        return this;
    }

    @Nullable
    public String getModeQueryName() {
        return this.mModeQueryName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        EmailSignInOptionsCreator.writeToParcel(this, out, flags);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            EmailSignInOptions config = (EmailSignInOptions) obj;
            if (!this.mServerWidgetUrl.equals(config.getServerWidgetUrl())) {
                return false;
            }
            if (this.mTosUrl == null) {
                if (config.getTermsOfServiceUrl() != null) {
                    return false;
                }
            } else if (!this.mTosUrl.equals(config.getTermsOfServiceUrl())) {
                return false;
            }
            if (TextUtils.isEmpty(this.mModeQueryName)) {
                if (!TextUtils.isEmpty(config.getModeQueryName())) {
                    return false;
                }
            } else if (!this.mModeQueryName.equals(config.getModeQueryName())) {
                return false;
            }
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        return new HashAccumulator().addObject(this.mServerWidgetUrl).addObject(this.mTosUrl).addObject(this.mModeQueryName).hash();
    }

    public String toJson() {
        return toJsonObject().toString();
    }

    private JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put(KEY_SERVER_WIDGET_URL, this.mServerWidgetUrl.toString());
            if (!TextUtils.isEmpty(this.mModeQueryName)) {
                json.put(KEY_MODE_QUERY_NAME, this.mModeQueryName);
            }
            if (this.mTosUrl != null) {
                json.put(KEY_TOS_URL, this.mTosUrl.toString());
            }
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
