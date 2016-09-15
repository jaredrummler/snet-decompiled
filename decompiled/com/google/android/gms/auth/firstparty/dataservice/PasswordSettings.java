package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PasswordSettingsCreator")
public class PasswordSettings implements SafeParcelable {
    public static final PasswordSettingsCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final String status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new PasswordSettingsCreator();
    }

    @Constructor
    PasswordSettings(@Param(id = 1) int version, @Param(id = 2) String status) {
        this.version = version;
        this.status = status;
    }

    public PasswordSettings(String status) {
        this(VERSION, status);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PasswordSettingsCreator.writeToParcel(this, dest, flags);
    }
}
