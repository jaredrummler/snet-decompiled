package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AppDescription;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "CheckRealNameRequestCreator")
public class CheckRealNameRequest implements SafeParcelable {
    public static final CheckRealNameRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    AppDescription callingAppDescription;
    @Field(id = 3)
    String firstName;
    @Field(id = 4)
    String lastName;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new CheckRealNameRequestCreator();
    }

    @Constructor
    CheckRealNameRequest(@Param(id = 1) int version, @Param(id = 2) AppDescription callingAppDescription, @Param(id = 3) String firstName, @Param(id = 4) String lastName) {
        this.version = version;
        this.callingAppDescription = callingAppDescription;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CheckRealNameRequest() {
        this.version = VERSION;
    }

    public void writeToParcel(Parcel dest, int flags) {
        CheckRealNameRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public AppDescription getCallingAppDescription() {
        return this.callingAppDescription;
    }

    public CheckRealNameRequest setCallingAppDescription(AppDescription appDescription) {
        this.callingAppDescription = appDescription;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public CheckRealNameRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return this.lastName;
    }

    public CheckRealNameRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
