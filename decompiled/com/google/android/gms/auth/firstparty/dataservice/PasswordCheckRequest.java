package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "PasswordCheckRequestCreator")
public class PasswordCheckRequest implements SafeParcelable {
    public static final PasswordCheckRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    String accountName;
    @Field(id = 6)
    AppDescription appDescription;
    @Field(id = 4)
    String optionalFirstName;
    @Field(id = 5)
    String optionalLastName;
    @Field(id = 3)
    String password;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new PasswordCheckRequestCreator();
    }

    @Constructor
    PasswordCheckRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) String password, @Param(id = 4) String optionalFirstName, @Param(id = 5) String optionalLastName, @Param(id = 6) AppDescription appDescription) {
        this.version = version;
        this.accountName = accountName;
        this.password = password;
        this.optionalFirstName = optionalFirstName;
        this.optionalLastName = optionalLastName;
        this.appDescription = appDescription;
    }

    public PasswordCheckRequest(String accountName, String passwordToCheck) {
        this.version = VERSION;
        this.accountName = accountName;
        this.password = passwordToCheck;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PasswordCheckRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getFirstName() {
        return this.optionalFirstName;
    }

    public PasswordCheckRequest setFirstName(String optionalFirstName) {
        this.optionalFirstName = optionalFirstName;
        return this;
    }

    public String getLastName() {
        return this.optionalLastName;
    }

    public PasswordCheckRequest setLastName(String optionalLastName) {
        this.optionalLastName = optionalLastName;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public PasswordCheckRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public AppDescription getCallingAppDescription() {
        return this.appDescription;
    }

    public PasswordCheckRequest setCallingAppDescription(AppDescription appDescription) {
        this.appDescription = appDescription;
        return this;
    }
}
