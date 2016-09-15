package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "OtpResponseCreator")
public class OtpResponse implements SafeParcelable {
    public static final OtpResponseCreator CREATOR;
    private static final int VERSION = 1;
    @VersionField(id = 1)
    final int mVersion;
    @Field(id = 2)
    public final String otp;

    static {
        CREATOR = new OtpResponseCreator();
    }

    @Constructor
    OtpResponse(@Param(id = 1) int version, @Param(id = 2) String otp) {
        this.mVersion = version;
        this.otp = otp;
    }

    public OtpResponse(String otp) {
        this(VERSION, otp);
    }

    public void writeToParcel(Parcel dest, int flags) {
        OtpResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
