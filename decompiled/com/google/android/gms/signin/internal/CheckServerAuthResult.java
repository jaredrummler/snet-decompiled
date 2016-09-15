package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Class(creator = "CheckServerAuthResultCreator")
public class CheckServerAuthResult implements SafeParcelable {
    public static final Creator<CheckServerAuthResult> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    final List<Scope> mAdditionalScopes;
    @Field(id = 2)
    final boolean mNewAuthCodeRequired;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new CheckServerAuthResultCreator();
    }

    @Constructor
    CheckServerAuthResult(@Param(id = 1) int versionCode, @Param(id = 2) boolean newAuthCodeRequired, @Param(id = 3) List<Scope> additionalScopes) {
        this.mVersionCode = versionCode;
        this.mNewAuthCodeRequired = newAuthCodeRequired;
        this.mAdditionalScopes = additionalScopes;
    }

    public CheckServerAuthResult(boolean newAuthCodeRequired, Set<Scope> additionalScopes) {
        this(VERSION_CODE, newAuthCodeRequired, unmodifiableListCopy(additionalScopes));
    }

    public boolean isNewAuthCodeRequired() {
        return this.mNewAuthCodeRequired;
    }

    public Set<Scope> getAdditionalScopes() {
        return new HashSet(this.mAdditionalScopes);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        CheckServerAuthResultCreator.writeToParcel(this, dest, flags);
    }

    private static List<Scope> unmodifiableListCopy(Set<Scope> scopes) {
        if (scopes == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(scopes));
    }
}
