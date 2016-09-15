package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.Name;

@Class(creator = "NameImplCreator")
public class NameImpl implements Name, SafeParcelable {
    public static final Creator<NameImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final String mDisplayName;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new NameImplCreator();
    }

    @Constructor
    NameImpl(@Param(id = 1) int versionCode, @Param(id = 2) String displayName) {
        this.mVersionCode = versionCode;
        this.mDisplayName = displayName;
    }

    public NameImpl(String displayName) {
        this(VERSION_CODE, displayName);
    }

    public CharSequence getDisplayName() {
        return this.mDisplayName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        NameImplCreator.writeToParcel(this, dest, flags);
    }
}
