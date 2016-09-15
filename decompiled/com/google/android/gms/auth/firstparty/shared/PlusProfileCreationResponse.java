package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PlusProfileCreationResponseCreator")
public class PlusProfileCreationResponse implements SafeParcelable {
    public static final PlusProfileCreationResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    String mStatus;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new PlusProfileCreationResponseCreator();
    }

    @Constructor
    PlusProfileCreationResponse(@Param(id = 1) int version, @Param(id = 2) String status) {
        this.version = version;
        this.mStatus = status;
    }

    public PlusProfileCreationResponse(Status status) {
        this.version = VERSION;
        this.mStatus = ((Status) Preconditions.checkNotNull(status)).getWire();
    }

    public void writeToParcel(Parcel dest, int flags) {
        PlusProfileCreationResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.mStatus);
    }
}
