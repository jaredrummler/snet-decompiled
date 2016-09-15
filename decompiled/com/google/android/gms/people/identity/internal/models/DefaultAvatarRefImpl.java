package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.identity.models.AvatarRef;

@Class(creator = "DefaultAvatarRefImplCreator")
public final class DefaultAvatarRefImpl implements SafeParcelable, AvatarRef {
    public static final DefaultAvatarRefImplCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    String mUrl;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new DefaultAvatarRefImplCreator();
    }

    @Constructor
    DefaultAvatarRefImpl(@Param(id = 1) int versionCode, @Param(id = 2) String url) {
        this.mVersionCode = versionCode;
        this.mUrl = url;
    }

    public DefaultAvatarRefImpl(String url) {
        this(VERSION_CODE, url);
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int describeContents() {
        DefaultAvatarRefImplCreator defaultAvatarRefImplCreator = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        DefaultAvatarRefImplCreator defaultAvatarRefImplCreator = CREATOR;
        DefaultAvatarRefImplCreator.writeToParcel(this, out, flags);
    }
}
