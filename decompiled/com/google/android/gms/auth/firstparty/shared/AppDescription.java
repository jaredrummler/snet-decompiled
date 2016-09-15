package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "AppDescriptionCreator")
public class AppDescription implements SafeParcelable {
    public static final AppDescriptionCreator CREATOR;
    private static final String LOG_PREFIX;
    protected static final String TAG = "GLSSession";
    private static final int VERSION = 1;
    private final String LOG_MSG_TMPL;
    @Field(id = 5)
    String callingPkg;
    @Field(id = 2)
    int callingUid;
    @Field(id = 6)
    boolean isSetupWizardInProgress;
    @Field(id = 3)
    String sessionId;
    @Field(id = 4)
    String sessionSig;
    @VersionField(id = 1)
    final int version;

    static {
        LOG_PREFIX = "[" + AppDescription.class.getSimpleName() + "]";
        CREATOR = new AppDescriptionCreator();
    }

    @Constructor
    AppDescription(@Param(id = 1) int version, @Param(id = 2) int callingUid, @Param(id = 3) String sessionId, @Param(id = 4) String sessionSig, @Param(id = 5) String callingPkg, @Param(id = 6) boolean isSetupWizardInProgress) {
        this.LOG_MSG_TMPL = "[" + getClass().getSimpleName() + "] %s - %s: %s";
        this.version = version;
        this.sessionId = sessionId;
        this.sessionSig = sessionSig;
        this.callingPkg = Preconditions.checkNotEmpty(callingPkg, LOG_PREFIX + " callingPkg cannot be null or empty!");
        Preconditions.checkArgument(callingUid != 0, "Invalid callingUid! Cannot be 0!");
        this.callingUid = callingUid;
        this.isSetupWizardInProgress = isSetupWizardInProgress;
    }

    public AppDescription(String callingPkg, int callingUid, String sessionId, String sessionSig) {
        this(VERSION, callingUid, sessionId, sessionSig, callingPkg, false);
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "New " + getClass().getSimpleName() + " (" + "sessiondId: " + this.sessionId + ", " + "sessiondSig: " + this.sessionSig + ", " + "callingPkg: " + this.callingPkg + ", " + "callingUid: " + this.callingUid + ", ");
        }
    }

    @VisibleForTesting
    public AppDescription(String callingPkg, int callingUid) {
        this(callingPkg, callingUid, null, null);
    }

    public void writeToParcel(Parcel dest, int flags) {
        AppDescriptionCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getSessionSignature() {
        return this.sessionSig;
    }

    public String getPackageName() {
        return this.callingPkg;
    }

    @Deprecated
    public String getCallingPackage() {
        return this.callingPkg;
    }

    public int getUid() {
        return this.callingUid;
    }

    @Deprecated
    public int getCallingUid() {
        return this.callingUid;
    }

    public boolean isSetupWizardInProgress() {
        return this.isSetupWizardInProgress;
    }

    public AppDescription setSetupWizardInProgress(boolean isSetupWizard) {
        this.isSetupWizardInProgress = isSetupWizard;
        return this;
    }

    protected void log(String methodName, String value) {
        if (Log.isLoggable(TAG, 2)) {
            String valueStr;
            if (value == null) {
                valueStr = "<NULL>";
            } else if (value.isEmpty()) {
                valueStr = "<EMPTY>";
            } else {
                valueStr = "<MEANINFGUL>";
            }
            Log.v(TAG, String.format(this.LOG_MSG_TMPL, new Object[]{this.sessionId, methodName, valueStr}));
        }
    }

    protected void log(String methodName, boolean value) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, String.format(this.LOG_MSG_TMPL, new Object[]{this.sessionId, methodName, Boolean.valueOf(value)}));
        }
    }

    protected void log(String methodName, int value) {
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, String.format(this.LOG_MSG_TMPL, new Object[]{this.sessionId, methodName, Integer.valueOf(value)}));
        }
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("<").append(this.callingPkg).append(", ").append(this.callingUid).append(">").toString();
    }
}
