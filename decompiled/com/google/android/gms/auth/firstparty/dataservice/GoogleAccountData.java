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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "GoogleAccountDataCreator")
public class GoogleAccountData implements SafeParcelable {
    public static final GoogleAccountDataCreator CREATOR;
    private static final int VERSION = 2;
    @Field(id = 7)
    public Account account;
    @Field(id = 2)
    @Deprecated
    String accountName;
    @Field(id = 5)
    public String firstName;
    @Field(id = 3)
    boolean isBrowserFlowRequired;
    @Field(id = 6)
    public String lastName;
    @Field(id = 4)
    public List<String> services;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new GoogleAccountDataCreator();
    }

    @Constructor
    GoogleAccountData(@Param(id = 1) int version, @Param(id = 2) String accountName, @Param(id = 3) boolean isBrowserFlowRequired, @Param(id = 4) List<String> services, @Param(id = 5) String firstName, @Param(id = 6) String lastName, @Param(id = 7) Account account) {
        this.version = version;
        this.accountName = accountName;
        this.isBrowserFlowRequired = isBrowserFlowRequired;
        this.services = services;
        this.firstName = firstName;
        this.lastName = lastName;
        if (account != null || TextUtils.isEmpty(accountName)) {
            this.account = account;
        } else {
            this.account = new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE);
        }
    }

    @Deprecated
    public GoogleAccountData(String accountName, boolean isBrowserFlowRequired, List<String> services, String firstName, String lastName) {
        this(new Account(accountName, GoogleLoginServiceConstants.ACCOUNT_TYPE), isBrowserFlowRequired, (List) services, firstName, lastName);
    }

    public GoogleAccountData(Account account, boolean isBrowserFlowRequired, List<String> services, String firstName, String lastName) {
        this.version = VERSION;
        this.account = account;
        this.isBrowserFlowRequired = isBrowserFlowRequired;
        this.services = services == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(new ArrayList(services));
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        GoogleAccountDataCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }

    @Deprecated
    public String getAccountName() {
        return this.account == null ? this.accountName : this.account.name;
    }

    public boolean isBrowserFlowRequired() {
        return this.isBrowserFlowRequired;
    }

    public List<String> getServices() {
        return this.services;
    }

    public Account getAccount() {
        return this.account;
    }
}
