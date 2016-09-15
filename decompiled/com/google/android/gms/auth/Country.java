package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "CountryCreator")
public class Country implements SafeParcelable {
    public static final CountryCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    public String mCode;
    @Field(id = 2)
    public String mName;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new CountryCreator();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        CountryCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public Country() {
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    Country(@Param(id = 1) int versionCode, @Param(id = 2) String name, @Param(id = 3) String code) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mCode = code;
    }
}
