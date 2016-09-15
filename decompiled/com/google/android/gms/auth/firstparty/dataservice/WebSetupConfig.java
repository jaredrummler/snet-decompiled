package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "WebSetupConfigCreator")
public class WebSetupConfig implements SafeParcelable {
    public static final WebSetupConfigCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final String url;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new WebSetupConfigCreator();
    }

    @Constructor
    WebSetupConfig(@Param(id = 1) int version, @Param(id = 2) String url) {
        this.version = version;
        this.url = url;
    }

    public WebSetupConfig(String url) {
        this(VERSION, url);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        WebSetupConfigCreator.writeToParcel(this, dest, flags);
    }
}
