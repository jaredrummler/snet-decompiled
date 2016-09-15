package com.google.android.gms.common.download;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Api.SimpleClientBuilder;
import com.google.android.gms.common.internal.BuildConstants;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "DownloadDetailsCreator")
public final class DownloadDetails implements SafeParcelable {
    public static final Creator<DownloadDetails> CREATOR;
    static final String KEY_DESTINATION = "destination";
    static final String KEY_FILENAME = "filename";
    static final String KEY_MAX_VERSION = "maxVersion";
    static final String KEY_MIN_VERSION = "minVersion";
    static final String KEY_SHA1 = "sha1";
    static final String KEY_SIZE_BYTES = "sizeBytes";
    static final String KEY_URL = "url";
    private static final int VERSION_CODE = 1;
    @Field(id = 6)
    public final String destination;
    @Field(id = 2)
    public final String filename;
    @Field(id = 8)
    public final int maxVersion;
    @Field(id = 7)
    public final int minVersion;
    @Field(id = 5)
    public final String sha1;
    @Field(id = 4)
    public final long sizeBytes;
    @Field(id = 3)
    public final String url;
    @VersionField(id = 1)
    final int versionCode;

    public static class Builder {
        public String destination;
        public String filename;
        public int maxVersion;
        public int minVersion;
        public String sha1;
        public long sizeBytes;
        public String url;

        public Builder(String filename, String url, long sizeBytes, String sha1) {
            this.minVersion = 0;
            this.maxVersion = SimpleClientBuilder.API_PRIORITY_OTHER;
            this.filename = filename;
            this.url = url;
            this.sizeBytes = sizeBytes;
            this.sha1 = sha1;
        }

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder setMinVersion(int minVersion) {
            this.minVersion = minVersion;
            return this;
        }

        public Builder setMaxVersion(int maxVersion) {
            this.maxVersion = maxVersion;
            return this;
        }

        public DownloadDetails build() {
            return new DownloadDetails();
        }
    }

    static {
        CREATOR = new DownloadDetailsCreator();
    }

    @VisibleForTesting
    public DownloadDetails(String filename, String url, long sizeBytes, String sha1) {
        this(filename, url, sizeBytes, sha1, null, 0, SimpleClientBuilder.API_PRIORITY_OTHER);
    }

    @Deprecated
    @VisibleForTesting
    public DownloadDetails(String filename, String url, long sizeBytes, String sha1, String destination) {
        this(filename, url, sizeBytes, sha1, destination, 0, SimpleClientBuilder.API_PRIORITY_OTHER);
    }

    @Constructor
    DownloadDetails(@Param(id = 1) int versionCode, @Param(id = 2) String filename, @Param(id = 3) String url, @Param(id = 4) long sizeBytes, @Param(id = 5) String sha1, @Param(id = 6) String destination, @Param(id = 7) int minVersion, @Param(id = 8) int maxVersion) {
        boolean z = true;
        Preconditions.checkArgument(minVersion <= maxVersion, "The minVersion (" + minVersion + ") must be less than or equal to the maxVersion" + " (" + maxVersion + ").");
        if (sizeBytes <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "sizeBytes (" + sizeBytes + ") must be greater than zero");
        this.versionCode = versionCode;
        this.filename = (String) Preconditions.checkNotNull(filename);
        this.url = (String) Preconditions.checkNotNull(url);
        this.sizeBytes = sizeBytes;
        this.sha1 = (String) Preconditions.checkNotNull(sha1);
        this.destination = destination;
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
    }

    @Deprecated
    @VisibleForTesting
    public DownloadDetails(String filename, String url, long sizeBytes, String sha1, String destination, int minVersion, int maxVersion) {
        this(VERSION_CODE, filename, url, sizeBytes, sha1, destination, minVersion, maxVersion);
    }

    private DownloadDetails(Builder builder) {
        boolean z = false;
        Preconditions.checkArgument(builder.sizeBytes > 0, "sizeBytes (" + builder.sizeBytes + ") must be greater than zero");
        if (builder.minVersion <= builder.maxVersion) {
            z = true;
        }
        Preconditions.checkArgument(z, "The minVersion (" + builder.minVersion + ") must be less than or equal to the " + "maxVersion (" + builder.maxVersion + ").");
        this.versionCode = VERSION_CODE;
        this.filename = (String) Preconditions.checkNotNull(builder.filename);
        this.url = (String) Preconditions.checkNotNull(builder.url);
        this.sizeBytes = builder.sizeBytes;
        this.sha1 = (String) Preconditions.checkNotNull(builder.sha1);
        this.destination = builder.destination;
        this.minVersion = builder.minVersion;
        this.maxVersion = builder.maxVersion;
    }

    public DownloadDetails(JSONObject obj) throws JSONException {
        this(obj.getString(KEY_FILENAME), obj.getString(KEY_URL), obj.getLong(KEY_SIZE_BYTES), obj.getString(KEY_SHA1), obj.has(KEY_DESTINATION) ? obj.getString(KEY_DESTINATION) : null, obj.has(KEY_MIN_VERSION) ? obj.getInt(KEY_MIN_VERSION) : 0, obj.has(KEY_MAX_VERSION) ? obj.getInt(KEY_MAX_VERSION) : SimpleClientBuilder.API_PRIORITY_OTHER);
    }

    @VisibleForTesting
    protected JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put(KEY_FILENAME, this.filename);
            obj.put(KEY_URL, this.url);
            obj.put(KEY_SIZE_BYTES, this.sizeBytes);
            obj.put(KEY_SHA1, this.sha1);
            if (this.destination != null) {
                obj.put(KEY_DESTINATION, this.destination);
            }
            if (this.minVersion != 0) {
                obj.put(KEY_MIN_VERSION, this.minVersion);
            }
            if (this.maxVersion != SimpleClientBuilder.API_PRIORITY_OTHER) {
                obj.put(KEY_MAX_VERSION, this.maxVersion);
            }
        } catch (JSONException e) {
        }
        return obj;
    }

    boolean isSupportedGmsCoreVersion() {
        return BuildConstants.JAR_BUILD_VERSION_CODE >= this.minVersion && BuildConstants.JAR_BUILD_VERSION_CODE <= this.maxVersion;
    }

    public boolean equals(Object o) {
        if (o instanceof DownloadDetails) {
            DownloadDetails d = (DownloadDetails) o;
            if (this.filename.equals(d.filename) && this.url.equals(d.url) && this.sizeBytes == d.sizeBytes && this.sha1.equals(d.sha1) && (((this.destination == null && d.destination == null) || this.destination.equals(d.destination)) && this.minVersion == d.minVersion && this.maxVersion == d.maxVersion)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.filename, this.url, Long.valueOf(this.sizeBytes), this.sha1, this.destination, Integer.valueOf(this.minVersion), Integer.valueOf(this.maxVersion));
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        DownloadDetailsCreator.writeToParcel(this, out, flags);
    }
}
