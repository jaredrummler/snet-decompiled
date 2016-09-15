package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GplusInfoResponseCreator")
public class GplusInfoResponse implements SafeParcelable {
    public static final GplusInfoResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    String firstName;
    @Field(id = 2)
    boolean isAllowed;
    @Field(id = 7)
    boolean isEsMobileEnabled;
    @Field(id = 6)
    boolean isGooglePlusEnabled;
    @Field(id = 4)
    String lastName;
    @Field(id = 5)
    String picasaUserName;
    @Field(id = 9)
    String ropRevision;
    @Field(id = 8)
    String ropText;
    @VersionField(id = 1)
    final int version;
    @Field(id = 10)
    String wireCode;

    static {
        CREATOR = new GplusInfoResponseCreator();
    }

    @Constructor
    GplusInfoResponse(@Param(id = 1) int version, @Param(id = 2) boolean isAllowed, @Param(id = 3) String firstName, @Param(id = 4) String lastName, @Param(id = 5) String picasaUserName, @Param(id = 6) boolean isGooglePlusEnabled, @Param(id = 7) boolean isEsMobileEnabled, @Param(id = 8) String ropText, @Param(id = 9) String ropRevision, @Param(id = 10) String wireCode) {
        this.version = version;
        this.isAllowed = isAllowed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picasaUserName = picasaUserName;
        this.isGooglePlusEnabled = isGooglePlusEnabled;
        this.isEsMobileEnabled = isEsMobileEnabled;
        this.ropText = ropText;
        this.ropRevision = ropRevision;
        this.wireCode = wireCode;
    }

    public GplusInfoResponse(boolean isAllowed, String firstName, String lastName, String picasaUserName, boolean isGooglePlusEnabled, boolean isEsMobileEnabled, String ropText, String ropRevision, String wireCode) {
        this.version = VERSION;
        this.isAllowed = isAllowed;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picasaUserName = picasaUserName;
        this.ropText = ropText;
        this.ropRevision = ropRevision;
        this.isGooglePlusEnabled = isGooglePlusEnabled;
        this.isEsMobileEnabled = isEsMobileEnabled;
        this.wireCode = wireCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        GplusInfoResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public boolean isAllowed() {
        return this.isAllowed;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPicasaUser() {
        return this.picasaUserName;
    }

    public String getRopText() {
        return this.ropText;
    }

    public String getRopRevision() {
        return this.ropRevision;
    }

    public boolean hasGooglePlus() {
        return this.isGooglePlusEnabled;
    }

    public boolean hasEsMobile() {
        return this.isEsMobileEnabled;
    }

    public String getWireCode() {
        return this.wireCode;
    }
}
