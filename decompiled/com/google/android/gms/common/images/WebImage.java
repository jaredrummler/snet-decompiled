package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "WebImageCreator")
public final class WebImage implements SafeParcelable {
    public static final Creator<WebImage> CREATOR;
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_URL = "url";
    private static final String KEY_WIDTH = "width";
    private static final int VERSION_CODE = 1;
    @Field(getter = "getHeight", id = 4)
    private final int mHeight;
    @Field(getter = "getUrl", id = 2)
    private final Uri mUrl;
    @VersionField(getter = "getVersionCode", id = 1)
    private final int mVersionCode;
    @Field(getter = "getWidth", id = 3)
    private final int mWidth;

    static {
        CREATOR = new WebImageCreator();
    }

    @Constructor
    WebImage(@Param(id = 1) int versionCode, @Param(id = 2) Uri url, @Param(id = 3) int width, @Param(id = 4) int height) {
        this.mVersionCode = versionCode;
        this.mUrl = url;
        this.mWidth = width;
        this.mHeight = height;
    }

    public WebImage(Uri url, int width, int height) throws IllegalArgumentException {
        this(VERSION_CODE, url, width, height);
        if (url == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    public WebImage(Uri url) throws IllegalArgumentException {
        this(url, 0, 0);
    }

    public WebImage(JSONObject json) throws IllegalArgumentException {
        this(extractUrl(json), json.optInt(KEY_WIDTH, 0), json.optInt(KEY_HEIGHT, 0));
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    private static Uri extractUrl(JSONObject json) {
        Uri url = null;
        if (json.has(KEY_URL)) {
            try {
                url = Uri.parse(json.getString(KEY_URL));
            } catch (JSONException e) {
            }
        }
        return url;
    }

    public Uri getUrl() {
        return this.mUrl;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public String toString() {
        return String.format("Image %dx%d %s", new Object[]{Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), this.mUrl.toString()});
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put(KEY_URL, this.mUrl.toString());
            json.put(KEY_WIDTH, this.mWidth);
            json.put(KEY_HEIGHT, this.mHeight);
        } catch (JSONException e) {
        }
        return json;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof WebImage)) {
            return false;
        }
        WebImage otherImage = (WebImage) other;
        if (Objects.equal(this.mUrl, otherImage.mUrl) && this.mWidth == otherImage.mWidth && this.mHeight == otherImage.mHeight) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.mUrl, Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        WebImageCreator.writeToParcel(this, out, flags);
    }
}
