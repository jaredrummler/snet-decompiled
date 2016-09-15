package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ConfirmCredentialsRequestCreator")
public class ConfirmCredentialsRequest implements SafeParcelable {
    public static final ConfirmCredentialsRequestCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    AccountCredentials accountCredentials;
    @Field(id = 3)
    CaptchaSolution optionalCaptchaSolution;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new ConfirmCredentialsRequestCreator();
    }

    @Constructor
    ConfirmCredentialsRequest(@Param(id = 1) int version, @Param(id = 2) AccountCredentials accountCredentials, @Param(id = 3) CaptchaSolution optionalCaptchaSolution) {
        this.version = version;
        this.accountCredentials = accountCredentials;
        this.optionalCaptchaSolution = optionalCaptchaSolution;
    }

    public ConfirmCredentialsRequest() {
        this.version = VERSION;
    }

    public ConfirmCredentialsRequest setAccountCredentials(AccountCredentials accountCredentials) {
        this.accountCredentials = accountCredentials;
        return this;
    }

    public AccountCredentials getAccountCredentials() {
        return this.accountCredentials;
    }

    public ConfirmCredentialsRequest setCaptchaSolution(CaptchaSolution captchaSolution) {
        this.optionalCaptchaSolution = captchaSolution;
        return this;
    }

    public CaptchaSolution getCaptchaSolution() {
        return this.optionalCaptchaSolution;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ConfirmCredentialsRequestCreator.writeToParcel(this, dest, flags);
    }
}
