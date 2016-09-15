package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "WebSetupConfigRequestCreator")
public class WebSetupConfigRequest implements SafeParcelable {
    public static final WebSetupConfigRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final AppDescription callingAppDescription;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new WebSetupConfigRequestCreator();
    }

    @Constructor
    WebSetupConfigRequest(@Param(id = 1) int version, @Param(id = 2) AppDescription callingAppDescription) {
        this.version = version;
        this.callingAppDescription = (AppDescription) Preconditions.checkNotNull(callingAppDescription);
    }

    public WebSetupConfigRequest(AppDescription callingAppDescription) {
        this(VERSION, callingAppDescription);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        WebSetupConfigRequestCreator.writeToParcel(this, dest, flags);
    }
}
