package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gtalkservice.GTalkServiceConstants;

@Class(creator = "ConnectionResultCreator")
public final class ConnectionResult implements SafeParcelable {
    public static final int API_UNAVAILABLE = 16;
    public static final int CANCELED = 13;
    public static final Creator<ConnectionResult> CREATOR;
    public static final int DEVELOPER_ERROR = 10;
    @Deprecated
    public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 15;
    public static final int INVALID_ACCOUNT = 5;
    public static final int LICENSE_CHECK_FAILED = 11;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int RESTRICTED_PROFILE = 20;
    public static final ConnectionResult RESULT_SUCCESS;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_MISSING_PERMISSION = 19;
    public static final int SERVICE_UPDATING = 18;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_FAILED = 17;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 14;
    public static final int UPDATE_ANDROID_WEAR = 42;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getResolution", id = 3)
    private final PendingIntent mPendingIntent;
    @Field(getter = "getErrorCode", id = 2)
    private final int mStatusCode;
    @Field(getter = "getErrorMessage", id = 4)
    private final String mStatusMessage;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        RESULT_SUCCESS = new ConnectionResult(SUCCESS);
        CREATOR = new ConnectionResultCreator();
    }

    @Constructor
    ConnectionResult(@Param(id = 1) int versionCode, @Param(id = 2) int statusCode, @Param(id = 3) PendingIntent pendingIntent, @Param(id = 4) String statusMessage) {
        this.mVersionCode = versionCode;
        this.mStatusCode = statusCode;
        this.mPendingIntent = pendingIntent;
        this.mStatusMessage = statusMessage;
    }

    public ConnectionResult(int statusCode) {
        this(statusCode, null, null);
    }

    public ConnectionResult(int statusCode, PendingIntent pendingIntent) {
        this(statusCode, pendingIntent, null);
    }

    public ConnectionResult(int statusCode, PendingIntent pendingIntent, String message) {
        this(VERSION_CODE, statusCode, pendingIntent, message);
    }

    public void startResolutionForResult(Activity activity, int requestCode) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), requestCode, null, SUCCESS, SUCCESS, SUCCESS);
        }
    }

    public boolean hasResolution() {
        return (this.mStatusCode == 0 || this.mPendingIntent == null) ? false : true;
    }

    public boolean isSuccess() {
        return this.mStatusCode == 0;
    }

    public int getErrorCode() {
        return this.mStatusCode;
    }

    @Nullable
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    @Nullable
    public String getErrorMessage() {
        return this.mStatusMessage;
    }

    static String getStatusString(int statusCode) {
        switch (statusCode) {
            case SUCCESS /*0*/:
                return "SUCCESS";
            case VERSION_CODE /*1*/:
                return "SERVICE_MISSING";
            case SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case SERVICE_DISABLED /*3*/:
                return "SERVICE_DISABLED";
            case SIGN_IN_REQUIRED /*4*/:
                return "SIGN_IN_REQUIRED";
            case INVALID_ACCOUNT /*5*/:
                return "INVALID_ACCOUNT";
            case RESOLUTION_REQUIRED /*6*/:
                return "RESOLUTION_REQUIRED";
            case NETWORK_ERROR /*7*/:
                return "NETWORK_ERROR";
            case INTERNAL_ERROR /*8*/:
                return "INTERNAL_ERROR";
            case SERVICE_INVALID /*9*/:
                return "SERVICE_INVALID";
            case DEVELOPER_ERROR /*10*/:
                return "DEVELOPER_ERROR";
            case LICENSE_CHECK_FAILED /*11*/:
                return "LICENSE_CHECK_FAILED";
            case CANCELED /*13*/:
                return "CANCELED";
            case TIMEOUT /*14*/:
                return "TIMEOUT";
            case INTERRUPTED /*15*/:
                return "INTERRUPTED";
            case API_UNAVAILABLE /*16*/:
                return "API_UNAVAILABLE";
            case SIGN_IN_FAILED /*17*/:
                return "SIGN_IN_FAILED";
            case SERVICE_UPDATING /*18*/:
                return "SERVICE_UPDATING";
            case SERVICE_MISSING_PERMISSION /*19*/:
                return "SERVICE_MISSING_PERMISSION";
            case RESTRICTED_PROFILE /*20*/:
                return "RESTRICTED_PROFILE";
            default:
                return "UNKNOWN_ERROR_CODE(" + statusCode + ")";
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult other = (ConnectionResult) o;
        if (this.mStatusCode == other.mStatusCode && Objects.equal(this.mPendingIntent, other.mPendingIntent) && Objects.equal(this.mStatusMessage, other.mStatusMessage)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Object[] objArr = new Object[SERVICE_DISABLED];
        objArr[SUCCESS] = Integer.valueOf(this.mStatusCode);
        objArr[VERSION_CODE] = this.mPendingIntent;
        objArr[SERVICE_VERSION_UPDATE_REQUIRED] = this.mStatusMessage;
        return Objects.hashCode(objArr);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("statusCode", getStatusString(this.mStatusCode)).add("resolution", this.mPendingIntent).add(GTalkServiceConstants.STANZA_TYPE_MESSAGE, this.mStatusMessage).toString();
    }

    public int describeContents() {
        return SUCCESS;
    }

    public void writeToParcel(Parcel out, int flags) {
        ConnectionResultCreator.writeToParcel(this, out, flags);
    }
}
