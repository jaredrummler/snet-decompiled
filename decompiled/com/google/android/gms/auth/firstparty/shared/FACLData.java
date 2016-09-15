package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "FACLDataCreator")
public class FACLData implements SafeParcelable {
    public static final FACLDataCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    String activityText;
    @Field(id = 2)
    FACLConfig faclConfig;
    @Field(id = 4)
    boolean isSpeedbumpNeeded;
    @Field(id = 5)
    String speedbumpText;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new FACLDataCreator();
    }

    @Constructor
    FACLData(@Param(id = 1) int version, @Param(id = 2) FACLConfig faclConfig, @Param(id = 3) String activityText, @Param(id = 4) boolean isSpeedbumpNeeded, @Param(id = 5) String speedbumpText) {
        this.version = version;
        this.faclConfig = faclConfig;
        this.activityText = activityText;
        this.isSpeedbumpNeeded = isSpeedbumpNeeded;
        this.speedbumpText = speedbumpText;
    }

    public FACLData(FACLConfig faclConfig, String activityText, String speedbumpText, boolean isSpeedbumpNeeded) {
        this.version = VERSION;
        this.faclConfig = faclConfig;
        this.activityText = activityText;
        this.speedbumpText = speedbumpText;
        this.isSpeedbumpNeeded = isSpeedbumpNeeded;
    }

    public void writeToParcel(Parcel dest, int flags) {
        FACLDataCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public FACLConfig getFaclConfig() {
        return this.faclConfig;
    }

    public boolean isSpeedbumpNeeded() {
        return this.isSpeedbumpNeeded;
    }

    public String getSpeedbumpText() {
        return this.speedbumpText;
    }

    public String getActivityText() {
        return this.activityText;
    }
}
