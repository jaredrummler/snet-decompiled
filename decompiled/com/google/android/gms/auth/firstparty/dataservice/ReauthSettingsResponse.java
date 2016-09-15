package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ReauthSettingsResponseCreator")
public class ReauthSettingsResponse implements SafeParcelable {
    public static final ReauthSettingsResponseCreator CREATOR;
    public static final String CREDENTIAL_STATUS_ACTIVE = "ACTIVE";
    public static final String CREDENTIAL_STATUS_CONFIGURABLE = "CONFIGURABLE";
    public static final String CREDENTIAL_STATUS_LOCKED = "LOCKED";
    private static final int VERSION = 1;
    @Field(id = 3)
    public final PasswordSettings password;
    @Field(id = 4)
    public final PinSettings pin;
    @Field(id = 2)
    public final int status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new ReauthSettingsResponseCreator();
    }

    @Constructor
    ReauthSettingsResponse(@Param(id = 1) int version, @Param(id = 2) int status, @Param(id = 3) PasswordSettings password, @Param(id = 4) PinSettings pin) {
        this.version = version;
        this.status = status;
        this.password = password;
        this.pin = pin;
    }

    public ReauthSettingsResponse(PasswordSettings password, PinSettings pin) {
        this(VERSION, 0, password, pin);
    }

    public ReauthSettingsResponse(int errorStatus) {
        this(VERSION, errorStatus, null, null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ReauthSettingsResponseCreator.writeToParcel(this, dest, flags);
    }
}
