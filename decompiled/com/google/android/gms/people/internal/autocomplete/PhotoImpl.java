package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.Photo;

@Class(creator = "PhotoImplCreator")
public class PhotoImpl implements Photo, SafeParcelable {
    public static final Creator<PhotoImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    final boolean mIsDefault;
    @Field(id = 3)
    final String mLocation;
    @Field(id = 2)
    final int mSource;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new PhotoImplCreator();
    }

    @Constructor
    PhotoImpl(@Param(id = 1) int versionCode, @Param(id = 2) int source, @Param(id = 3) String location, @Param(id = 4) boolean isDefault) {
        this.mVersionCode = versionCode;
        this.mSource = source;
        this.mLocation = location;
        this.mIsDefault = isDefault;
    }

    public PhotoImpl(int source, String location, boolean isDefault) {
        this(VERSION_CODE, source, location, isDefault);
    }

    public int getSource() {
        return this.mSource;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public boolean isDefault() {
        return this.mIsDefault;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PhotoImplCreator.writeToParcel(this, dest, flags);
    }
}
