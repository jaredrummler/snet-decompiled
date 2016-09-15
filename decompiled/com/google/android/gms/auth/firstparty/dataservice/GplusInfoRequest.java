package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gsf.GoogleLoginServiceConstants;

@Class(creator = "GplusInfoRequestCreator")
public class GplusInfoRequest implements SafeParcelable {
    public static final GplusInfoRequestCreator CREATOR;
    private static final int VERSION = 2;
    @Field(id = 4)
    Account account;
    @Field(id = 2)
    String accountName;
    @Field(id = 3)
    CaptchaSolution optionalCaptchaSolution;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new GplusInfoRequestCreator();
    }

    @Constructor
    GplusInfoRequest(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) CaptchaSolution optionalCaptchaSolution, @Param(id = 4) Account account) {
        this.version = version;
        this.accountName = accountName;
        this.optionalCaptchaSolution = optionalCaptchaSolution;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    public GplusInfoRequest() {
        this.version = VERSION;
    }

    public void writeToParcel(Parcel dest, int flags) {
        GplusInfoRequestCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public GplusInfoRequest setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public CaptchaSolution getCaptchaSolution() {
        return this.optionalCaptchaSolution;
    }

    public GplusInfoRequest setCaptchaSolution(CaptchaSolution captchaSolution) {
        this.optionalCaptchaSolution = captchaSolution;
        return this;
    }

    public Account getAccount() {
        return this.account;
    }

    public GplusInfoRequest setAccount(Account account) {
        this.account = account;
        return this;
    }
}
