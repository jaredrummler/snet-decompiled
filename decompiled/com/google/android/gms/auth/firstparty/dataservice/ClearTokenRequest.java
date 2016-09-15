package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ClearTokenRequestCreator")
public class ClearTokenRequest implements SafeParcelable {
    public static final ClearTokenRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    String token;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new ClearTokenRequestCreator();
    }

    public ClearTokenRequest() {
        this.version = VERSION;
    }

    @Constructor
    ClearTokenRequest(@Param(id = 1) int version, @Param(id = 2) String token) {
        this.version = version;
        this.token = token;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ClearTokenRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getToken() {
        return this.token;
    }

    public ClearTokenRequest setToken(String token) {
        this.token = token;
        return this;
    }
}
