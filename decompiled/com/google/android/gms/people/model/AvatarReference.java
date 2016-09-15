package com.google.android.gms.people.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Organizations;
import com.google.android.gms.people.internal.PeopleUtils;

@Class(creator = "ParcelableAvatarReference")
@VisibleForTesting
public final class AvatarReference implements SafeParcelable {
    public static final ParcelableAvatarReference CREATOR;
    public static final int SOURCE_NO_AVATAR = 0;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getLocation", id = 2)
    final String mLocation;
    @Field(getter = "getSource", id = 1)
    final int mSource;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        CREATOR = new ParcelableAvatarReference();
    }

    @Constructor
    AvatarReference(@Param(id = 1000) int versionCode, @Param(id = 1) int source, @Param(id = 2) String location) {
        Preconditions.checkState(source != 0);
        this.mVersionCode = versionCode;
        this.mSource = source;
        this.mLocation = location;
    }

    public AvatarReference(int source, String location) {
        this(VERSION_CODE, source, location);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int getSource() {
        return this.mSource;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public int describeContents() {
        return SOURCE_NO_AVATAR;
    }

    public void writeToParcel(Parcel out, int flags) {
        ParcelableAvatarReference.writeToParcel(this, out, flags);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("source", Integer.valueOf(this.mSource)).add(Organizations.LOCATION, this.mLocation).toString();
    }

    @VisibleForTesting
    public String toPersistableString() {
        return this.mVersionCode + '\u0001' + this.mSource + '\u0001' + this.mLocation;
    }

    @VisibleForTesting
    public static AvatarReference fromPersistableString(String s) {
        boolean z = true;
        Preconditions.checkNotEmpty(s);
        String[] parts = PeopleUtils.SEP_1_RE.split(s);
        if (parts.length != 3) {
            z = false;
        }
        Preconditions.checkArgument(z, "Malformed string");
        try {
            return new AvatarReference(Integer.parseInt(parts[SOURCE_NO_AVATAR]), Integer.parseInt(parts[VERSION_CODE]), parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Malformed string");
        }
    }
}
