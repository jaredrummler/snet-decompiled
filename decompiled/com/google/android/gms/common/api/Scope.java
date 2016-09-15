package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ScopeCreator")
public final class Scope implements SafeParcelable {
    public static final Creator<Scope> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getScopeUri", id = 2)
    private final String mScopeUri;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ScopeCreator();
    }

    @Constructor
    Scope(@Param(id = 1) int versionCode, @Param(id = 2) String scopeUri) {
        Preconditions.checkNotEmpty(scopeUri, "scopeUri must not be null or empty");
        this.mVersionCode = versionCode;
        this.mScopeUri = scopeUri;
    }

    public Scope(String scopeUri) {
        this(VERSION_CODE, scopeUri);
    }

    public String getScopeUri() {
        return this.mScopeUri;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Scope) {
            return this.mScopeUri.equals(((Scope) o).mScopeUri);
        }
        return false;
    }

    public int hashCode() {
        return this.mScopeUri.hashCode();
    }

    public String toString() {
        return this.mScopeUri;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ScopeCreator.writeToParcel(this, dest, flags);
    }
}
