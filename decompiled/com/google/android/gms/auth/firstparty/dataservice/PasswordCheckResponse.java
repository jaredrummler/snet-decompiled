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

@Class(creator = "PasswordCheckResponseCreator")
public class PasswordCheckResponse implements SafeParcelable {
    public static final PasswordCheckResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 4)
    String detail;
    @Field(id = 3)
    String passwordStrength;
    @Field(id = 2)
    String status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new PasswordCheckResponseCreator();
    }

    @Constructor
    PasswordCheckResponse(@Param(id = 1) int version, @Param(id = 2) String status, @Param(id = 3) String passwordStrength, @Param(id = 4) String detail) {
        this.version = version;
        this.status = status;
        this.passwordStrength = passwordStrength;
        this.detail = detail;
    }

    public PasswordCheckResponse(Status status) {
        this(status, null, null);
    }

    public PasswordCheckResponse(Status status, String passwordStrength, String detail) {
        this.version = VERSION;
        this.status = ((Status) Preconditions.checkNotNull(status)).getWire();
        this.passwordStrength = passwordStrength;
        this.detail = detail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PasswordCheckResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public Status getStatus() {
        return Status.fromWireCode(this.status);
    }

    public String getPasswordStrength() {
        return this.passwordStrength;
    }

    public String getDetail() {
        return this.detail;
    }
}
