package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "StatusCreator")
public final class Status implements SafeParcelable, Result {
    public static final Creator<Status> CREATOR;
    public static final Status RESULT_CANCELED;
    public static final Status RESULT_INTERNAL_ERROR;
    public static final Status RESULT_INTERRUPTED;
    @VisibleForTesting
    public static final Status RESULT_SUCCESS;
    public static final Status RESULT_TIMEOUT;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent mPendingIntent;
    @Field(getter = "getStatusCode", id = 1)
    private final int mStatusCode;
    @Field(getter = "getStatusMessage", id = 2)
    private final String mStatusMessage;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        RESULT_SUCCESS = new Status(0);
        RESULT_INTERRUPTED = new Status(14);
        RESULT_INTERNAL_ERROR = new Status(8);
        RESULT_TIMEOUT = new Status(15);
        RESULT_CANCELED = new Status(16);
        CREATOR = new StatusCreator();
    }

    @Constructor
    Status(@Param(id = 1000) int versionCode, @Param(id = 1) int statusCode, @Param(id = 2) String statusMessage, @Param(id = 3) PendingIntent pendingIntent) {
        this.mVersionCode = versionCode;
        this.mStatusCode = statusCode;
        this.mStatusMessage = statusMessage;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int statusCode) {
        this(statusCode, null);
    }

    public Status(int statusCode, String statusMessage) {
        this(VERSION_CODE, statusCode, statusMessage, null);
    }

    public Status(int statusCode, String statusMessage, PendingIntent pendingIntent) {
        this(VERSION_CODE, statusCode, statusMessage, pendingIntent);
    }

    public void startResolutionForResult(Activity activity, int requestCode) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), requestCode, null, 0, 0, 0);
        }
    }

    PendingIntent getPendingIntent() {
        return this.mPendingIntent;
    }

    public String getStatusMessage() {
        return this.mStatusMessage;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    @VisibleForTesting
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public boolean isSuccess() {
        return this.mStatusCode <= 0;
    }

    public boolean isCanceled() {
        return this.mStatusCode == 16;
    }

    public boolean isInterrupted() {
        return this.mStatusCode == 14;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.mStatusCode), this.mStatusMessage, this.mPendingIntent);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.mVersionCode == status.mVersionCode && this.mStatusCode == status.mStatusCode && Objects.equal(this.mStatusMessage, status.mStatusMessage) && Objects.equal(this.mPendingIntent, status.mPendingIntent)) {
            return true;
        }
        return false;
    }

    private String getStatusString() {
        if (this.mStatusMessage != null) {
            return this.mStatusMessage;
        }
        return CommonStatusCodes.getStatusCodeString(this.mStatusCode);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("statusCode", getStatusString()).add("resolution", this.mPendingIntent).toString();
    }

    @Deprecated
    public ConnectionResult asConnectionResult() {
        return new ConnectionResult(this.mStatusCode, this.mPendingIntent);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        StatusCreator.writeToParcel(this, out, flags);
    }

    public Status getStatus() {
        return this;
    }
}
