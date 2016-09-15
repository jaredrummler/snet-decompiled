package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "OtpRequestCreator")
public class OtpRequest implements SafeParcelable {
    public static final OtpRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final String accountName;
    @Field(id = 3)
    public final AppDescription callerDescription;
    @Field(id = 4)
    public final byte[] challenge;
    @Field(id = 5)
    public final boolean isLegacyRequest;
    @VersionField(id = 1)
    final int mVersion;

    static {
        CREATOR = new OtpRequestCreator();
    }

    public static OtpRequest newLegacyOtpRequest(String accountName, AppDescription appDescription) {
        return new OtpRequest(VERSION, accountName, appDescription, null, true);
    }

    @Constructor
    OtpRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) AppDescription callerDescription, @Param(id = 4) byte[] challenge, @Param(id = 5) boolean isLegacyRequest) {
        this.mVersion = version;
        this.accountName = accountName;
        this.challenge = challenge;
        this.callerDescription = (AppDescription) Preconditions.checkNotNull(callerDescription, "Caller's app description cannot be null!");
        this.isLegacyRequest = isLegacyRequest;
    }

    public OtpRequest(String accountName, AppDescription appDescription) {
        this(VERSION, accountName, appDescription, null, false);
    }

    public OtpRequest(String accountName, AppDescription appDescription, byte[] challenge) {
        this(VERSION, accountName, appDescription, challenge, false);
    }

    public void writeToParcel(Parcel dest, int flags) {
        OtpRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
