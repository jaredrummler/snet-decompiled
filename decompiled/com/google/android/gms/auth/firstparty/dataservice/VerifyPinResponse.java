package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "VerifyPinResponseCreator")
public class VerifyPinResponse implements SafeParcelable {
    public static final VerifyPinResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    public final String rapt;
    @Field(id = 2)
    public final int status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new VerifyPinResponseCreator();
    }

    @Constructor
    VerifyPinResponse(@Param(id = 1) int version, @Param(id = 2) int status, @Param(id = 3) String rapt) {
        this.version = version;
        this.status = status;
        this.rapt = rapt;
    }

    public VerifyPinResponse(String rapt) {
        this(VERSION, 0, rapt);
    }

    public VerifyPinResponse(int errorStatus) {
        this(VERSION, errorStatus, null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        VerifyPinResponseCreator.writeToParcel(this, dest, flags);
    }
}
