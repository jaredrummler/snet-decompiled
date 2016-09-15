package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PlayLoggerContextCreator")
public class PlayLoggerContext implements SafeParcelable {
    private static final char COMMA = ',';
    public static final PlayLoggerContextCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 9)
    public final boolean isAnonymous;
    @Field(defaultValue = "true", id = 7)
    public final boolean logAndroidId;
    @Field(id = 4)
    public final int logSource;
    @Field(id = 8)
    public final String logSourceName;
    @Field(id = 6)
    public final String loggingId;
    @Field(id = 2)
    public final String packageName;
    @Field(id = 3)
    public final int packageVersionCode;
    @Field(id = 10)
    public final int qosTier;
    @Field(id = 5)
    public final String uploadAccountName;
    @VersionField(id = 1)
    public final int versionCode;

    static {
        CREATOR = new PlayLoggerContextCreator();
    }

    @Constructor
    public PlayLoggerContext(@Param(id = 1) int versionCode, @Param(id = 2) String packageName, @Param(id = 3) int packageVersionCode, @Param(id = 4) int logSource, @Param(id = 5) String uploadAccountName, @Param(id = 6) String loggingId, @Param(id = 7) boolean logAndroidId, @Param(id = 8) String logSourceName, @Param(id = 9) boolean isAnonymous, @Param(id = 10) int qosTier) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.packageVersionCode = packageVersionCode;
        this.logSource = logSource;
        this.uploadAccountName = uploadAccountName;
        this.loggingId = loggingId;
        this.logAndroidId = logAndroidId;
        this.logSourceName = logSourceName;
        this.isAnonymous = isAnonymous;
        this.qosTier = qosTier;
    }

    @Deprecated
    public PlayLoggerContext(String packageName, int packageVersionCode, int logSource, String uploadAccountName, String loggingId, boolean logAndroidId) {
        this.versionCode = VERSION_CODE;
        this.packageName = (String) Preconditions.checkNotNull(packageName);
        this.packageVersionCode = packageVersionCode;
        this.logSource = logSource;
        this.logSourceName = null;
        this.uploadAccountName = uploadAccountName;
        this.loggingId = loggingId;
        this.logAndroidId = logAndroidId;
        this.isAnonymous = false;
        this.qosTier = 0;
    }

    public PlayLoggerContext(String packageName, int packageVersionCode, int logSource, String logSourceName, String uploadAccountName, String loggingId, boolean isAnonymous, int qosTier) {
        this.versionCode = VERSION_CODE;
        this.packageName = (String) Preconditions.checkNotNull(packageName);
        this.packageVersionCode = packageVersionCode;
        this.logSource = logSource;
        this.logSourceName = logSourceName;
        this.uploadAccountName = uploadAccountName;
        this.loggingId = loggingId;
        this.logAndroidId = !isAnonymous;
        this.isAnonymous = isAnonymous;
        this.qosTier = qosTier;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        PlayLoggerContextCreator.writeToParcel(this, out, flags);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), this.packageName, Integer.valueOf(this.packageVersionCode), Integer.valueOf(this.logSource), this.logSourceName, this.uploadAccountName, this.loggingId, Boolean.valueOf(this.logAndroidId), Boolean.valueOf(this.isAnonymous), Integer.valueOf(this.qosTier));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlayLoggerContext)) {
            return false;
        }
        PlayLoggerContext loggerContext = (PlayLoggerContext) object;
        if (this.versionCode == loggerContext.versionCode && this.packageName.equals(loggerContext.packageName) && this.packageVersionCode == loggerContext.packageVersionCode && this.logSource == loggerContext.logSource && Objects.equal(this.logSourceName, loggerContext.logSourceName) && Objects.equal(this.uploadAccountName, loggerContext.uploadAccountName) && Objects.equal(this.loggingId, loggerContext.loggingId) && this.logAndroidId == loggerContext.logAndroidId && this.isAnonymous == loggerContext.isAnonymous && this.qosTier == loggerContext.qosTier) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("versionCode=").append(this.versionCode).append(COMMA);
        sb.append("package=").append(this.packageName).append(COMMA);
        sb.append("packageVersionCode=").append(this.packageVersionCode).append(COMMA);
        sb.append("logSource=").append(this.logSource).append(COMMA);
        sb.append("logSourceName=").append(this.logSourceName).append(COMMA);
        sb.append("uploadAccount=").append(this.uploadAccountName).append(COMMA);
        sb.append("loggingId=").append(this.loggingId).append(COMMA);
        sb.append("logAndroidId=").append(this.logAndroidId).append(COMMA);
        sb.append("isAnonymous=").append(this.isAnonymous).append(COMMA);
        sb.append("qosTier=").append(this.qosTier);
        sb.append("]");
        return sb.toString();
    }
}
