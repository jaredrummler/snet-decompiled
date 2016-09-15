package com.google.android.gms.people.identity.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.IdentityApi.GetOptions;

@Class(creator = "ParcelableGetOptionsCreator")
@VisibleForTesting
public final class ParcelableGetOptions implements SafeParcelable {
    public static final ParcelableGetOptionsCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    final String mEndpoint;
    @Field(id = 5)
    final Bundle mEndpointArguments;
    @Field(id = 4)
    final boolean mUseCp2;
    @Field(id = 1)
    final boolean mUseOfflineDatabase;
    @Field(id = 2)
    final boolean mUseWebData;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        CREATOR = new ParcelableGetOptionsCreator();
    }

    @Constructor
    ParcelableGetOptions(@Param(id = 1000) int versionCode, @Param(id = 1) boolean useOfflineDatabase, @Param(id = 2) boolean useWebData, @Param(id = 4) boolean useCp2, @Param(id = 3) String endpoint, @Param(id = 5) Bundle endpointArguments) {
        this.mVersionCode = versionCode;
        this.mUseOfflineDatabase = useOfflineDatabase;
        this.mUseWebData = useWebData;
        this.mEndpoint = endpoint;
        this.mUseCp2 = useCp2;
        if (endpointArguments == null) {
            endpointArguments = new Bundle();
        }
        this.mEndpointArguments = endpointArguments;
    }

    @VisibleForTesting
    public ParcelableGetOptions(boolean useOfflineDatabase, boolean useWebData, boolean useCp2, String endpoint, Bundle endpointArguments) {
        this(VERSION_CODE, useOfflineDatabase, useWebData, useCp2, endpoint, endpointArguments);
    }

    public ParcelableGetOptions(GetOptions options) {
        this(options.useCachedData, options.useWebData, options.useContactData, options.firstPartyOptions.endpoint, options.firstPartyOptions.endpointArguments);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean getUseCp2() {
        return this.mUseCp2;
    }

    public boolean getUseOfflineDatabase() {
        return this.mUseOfflineDatabase;
    }

    public boolean getUseWebData() {
        return this.mUseWebData;
    }

    public String getEndpoint() {
        return this.mEndpoint;
    }

    public Bundle getEndpointArguments() {
        return this.mEndpointArguments;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ParcelableGetOptionsCreator.writeToParcel(this, out, flags);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("useOfflineDatabase", Boolean.valueOf(this.mUseOfflineDatabase)).add("useWebData", Boolean.valueOf(this.mUseWebData)).add("endpoint", this.mEndpoint).toString();
    }
}
