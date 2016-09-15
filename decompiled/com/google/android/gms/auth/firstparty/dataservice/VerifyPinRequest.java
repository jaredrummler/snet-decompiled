package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "VerifyPinRequestCreator")
public class VerifyPinRequest implements SafeParcelable {
    public static final VerifyPinRequestCreator CREATOR;
    private static final int VERSION = 3;
    @Field(id = 4)
    public final Account account;
    @Field(id = 2)
    @Deprecated
    public final String accountName;
    @Field(id = 5)
    public String callingPackageName;
    @Field(id = 3)
    public final String pin;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new VerifyPinRequestCreator();
    }

    @Constructor
    VerifyPinRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) String pin, @Param(id = 4) Account account, @Param(id = 5) String callingPackageName) {
        this.version = version;
        this.accountName = accountName;
        this.pin = pin;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
        this.callingPackageName = callingPackageName;
    }

    @Deprecated
    public VerifyPinRequest(String accountName, String pin) {
        this(VERSION, accountName, pin, null, null);
    }

    public VerifyPinRequest(Account account, String pin) {
        this(VERSION, null, pin, account, null);
    }

    void setCallingPackageName(String callingPackageName) {
        this.callingPackageName = callingPackageName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        VerifyPinRequestCreator.writeToParcel(this, dest, flags);
    }
}
