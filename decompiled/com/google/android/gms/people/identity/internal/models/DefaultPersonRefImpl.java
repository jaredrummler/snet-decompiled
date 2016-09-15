package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.identity.models.AvatarRef;
import com.google.android.gms.people.identity.models.PersonRef;

@Class(creator = "DefaultPersonRefImplCreator")
public class DefaultPersonRefImpl implements PersonRef, SafeParcelable {
    public static final DefaultPersonRefImplCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    DefaultAvatarRefImpl mAvatar;
    @Field(id = 2)
    String mName;
    @Field(id = 3)
    String mQualifiedId;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new DefaultPersonRefImplCreator();
    }

    @Constructor
    DefaultPersonRefImpl(@Param(id = 1) int versionCode, @Param(id = 2) String name, @Param(id = 3) String qualifiedId, @Param(id = 4) DefaultAvatarRefImpl avatar) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mQualifiedId = qualifiedId;
        this.mAvatar = avatar;
    }

    public DefaultPersonRefImpl(String name, String qualifiedId, DefaultAvatarRefImpl avatar) {
        this(VERSION_CODE, name, qualifiedId, avatar);
    }

    public String getName() {
        return this.mName;
    }

    public String getQualifiedId() {
        return this.mQualifiedId;
    }

    public AvatarRef getAvatarRef() {
        return this.mAvatar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        DefaultPersonRefImplCreator.writeToParcel(this, out, flags);
    }
}
