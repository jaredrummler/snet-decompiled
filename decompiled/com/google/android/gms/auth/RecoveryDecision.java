package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "RecoveryDecisionCreator")
public class RecoveryDecision implements SafeParcelable {
    public static final RecoveryDecisionCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    public boolean isRecoveryInfoNeeded;
    @Field(id = 5)
    public boolean isRecoveryInterstitialAllowed;
    @VersionField(id = 1)
    final int mVersionCode;
    @Field(id = 2)
    public PendingIntent recoveryIntent;
    @Field(id = 6)
    public PendingIntent recoveryIntentWithoutIntro;
    @Field(id = 3)
    public boolean showRecoveryInterstitial;

    static {
        CREATOR = new RecoveryDecisionCreator();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        RecoveryDecisionCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public RecoveryDecision() {
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    RecoveryDecision(@Param(id = 1) int versionCode, @Param(id = 2) PendingIntent recoveryIntent, @Param(id = 3) boolean showRecoveryInterstitial, @Param(id = 4) boolean isRecoveryInfoNeeded, @Param(id = 5) boolean isRecoveryInterstitialAllowed, @Param(id = 6) PendingIntent recoveryIntentWithoutIntro) {
        this.mVersionCode = versionCode;
        this.recoveryIntent = recoveryIntent;
        this.showRecoveryInterstitial = showRecoveryInterstitial;
        this.isRecoveryInfoNeeded = isRecoveryInfoNeeded;
        this.isRecoveryInterstitialAllowed = isRecoveryInterstitialAllowed;
        this.recoveryIntentWithoutIntro = recoveryIntentWithoutIntro;
    }
}
