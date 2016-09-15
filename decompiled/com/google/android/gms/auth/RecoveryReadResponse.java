package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@Class(creator = "RecoveryReadResponseCreator")
public class RecoveryReadResponse implements SafeParcelable {
    public static final RecoveryReadResponseCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 7)
    public String mAction;
    @Field(id = 8)
    public String mAllowedOptions;
    @Field(id = 5)
    public List<Country> mCountryList;
    @Field(id = 6)
    public String mError;
    @Field(id = 4)
    public String mPhoneCountryCode;
    @Field(id = 3)
    public String mPhoneNumber;
    @Field(id = 2)
    public String mSecondaryEmail;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new RecoveryReadResponseCreator();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        RecoveryReadResponseCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public RecoveryReadResponse() {
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    RecoveryReadResponse(@Param(id = 1) int versionCode, @Param(id = 2) String secondaryEmail, @Param(id = 3) String phoneNumber, @Param(id = 4) String phoneCountryCode, @Param(id = 5) List<Country> countryList, @Param(id = 6) String error, @Param(id = 7) String action, @Param(id = 8) String allowedOptions) {
        this.mVersionCode = versionCode;
        this.mSecondaryEmail = secondaryEmail;
        this.mPhoneNumber = phoneNumber;
        this.mPhoneCountryCode = phoneCountryCode;
        this.mCountryList = countryList;
        this.mError = error;
        this.mAction = action;
        this.mAllowedOptions = allowedOptions;
    }
}
