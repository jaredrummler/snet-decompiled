package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ValidateAccountCredentialsResponseCreator")
public class ValidateAccountCredentialsResponse implements SafeParcelable {
    public static final ValidateAccountCredentialsResponseCreator CREATOR;
    public static final int STATUS_ERROR_INVALID_CREDENTIALS = 3;
    public static final int STATUS_ERROR_NETWORK = 2;
    public static final int STATUS_ERROR_UNKNOWN = 1;
    public static final int STATUS_OK = 0;
    private static final int VERSION = 1;
    @Field(id = 3)
    public final String accountId;
    @Field(id = 2)
    public final int status;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new ValidateAccountCredentialsResponseCreator();
    }

    @Constructor
    ValidateAccountCredentialsResponse(@Param(id = 1) int version, @Param(id = 2) int status, @Param(id = 3) String accountId) {
        this.version = version;
        this.status = status;
        this.accountId = accountId;
    }

    public ValidateAccountCredentialsResponse(String accountId) {
        this(VERSION, STATUS_OK, accountId);
    }

    public ValidateAccountCredentialsResponse(int errorStatus) {
        this(VERSION, errorStatus, null);
    }

    public int describeContents() {
        return STATUS_OK;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ValidateAccountCredentialsResponseCreator.writeToParcel(this, dest, flags);
    }
}
