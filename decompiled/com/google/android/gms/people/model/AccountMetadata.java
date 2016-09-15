package com.google.android.gms.people.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "AccountMetadataCreator")
public class AccountMetadata implements SafeParcelable {
    public static final AccountMetadataCreator CREATOR;
    private static final int VERSION_CODE = 2;
    @Field(id = 4)
    public boolean isPagePeriodicSyncEnabled;
    @Field(id = 5)
    public boolean isPageTickleSyncEnabled;
    @Field(id = 2)
    public boolean isPlusEnabled;
    @Field(id = 3)
    public boolean isSyncEnabled;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new AccountMetadataCreator();
    }

    @Constructor
    AccountMetadata(@Param(id = 1) int versionCode, @Param(id = 2) boolean isPlusEnabled, @Param(id = 3) boolean isSyncEnabled, @Param(id = 4) boolean isPagePeriodicSyncEnabled, @Param(id = 5) boolean isPageTickleSyncEnabled) {
        this.mVersionCode = versionCode;
        this.isPlusEnabled = isPlusEnabled;
        this.isSyncEnabled = isSyncEnabled;
        this.isPagePeriodicSyncEnabled = isPagePeriodicSyncEnabled;
        this.isPageTickleSyncEnabled = isPageTickleSyncEnabled;
    }

    public AccountMetadata() {
        this.mVersionCode = VERSION_CODE;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        AccountMetadataCreator.writeToParcel(this, out, flags);
    }
}
