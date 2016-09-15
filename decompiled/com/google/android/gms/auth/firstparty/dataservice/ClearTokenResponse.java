package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ClearTokenResponseCreator")
public class ClearTokenResponse implements SafeParcelable {
    public static final ClearTokenResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    final String statusWireCode;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new ClearTokenResponseCreator();
    }

    @Constructor
    ClearTokenResponse(@Param(id = 1) int version, @Param(id = 2) String statusWireCode) {
        this.version = version;
        this.statusWireCode = statusWireCode;
    }

    public ClearTokenResponse(Status status) {
        this.version = VERSION;
        this.statusWireCode = ((Status) Preconditions.checkNotNull(status)).getWire();
    }

    public void writeToParcel(Parcel dest, int flags) {
        ClearTokenResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.statusWireCode);
    }
}
