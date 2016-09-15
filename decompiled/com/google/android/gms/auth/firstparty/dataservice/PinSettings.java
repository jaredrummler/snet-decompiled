package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PinSettingsCreator")
public class PinSettings implements SafeParcelable {
    public static final PinSettingsCreator CREATOR;
    private static final int VERSION = 2;
    @Field(id = 5)
    public final int length;
    @Field(id = 6)
    public final String recoveryUrl;
    @Field(id = 3)
    public final String resetUrl;
    @Field(id = 4)
    public final String setupUrl;
    @Field(id = 2)
    public final String status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new PinSettingsCreator();
    }

    @Constructor
    PinSettings(@Param(id = 1) int version, @Param(id = 2) String status, @Param(id = 3) String resetUrl, @Param(id = 4) String setupUrl, @Param(id = 6) String recoveryUrl, @Param(id = 5) int length) {
        this.version = version;
        this.status = status;
        this.resetUrl = resetUrl;
        this.setupUrl = setupUrl;
        this.recoveryUrl = recoveryUrl;
        this.length = length;
    }

    public PinSettings(String status, String resetUrl, String setupUrl, String recoveryUrl, int length) {
        this(VERSION, status, resetUrl, setupUrl, recoveryUrl, length);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PinSettingsCreator.writeToParcel(this, dest, flags);
    }
}
