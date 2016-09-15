package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor.Stub;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.Collection;

@Class(creator = "GetServiceRequestCreator")
public class GetServiceRequest implements SafeParcelable {
    public static final Creator<GetServiceRequest> CREATOR;
    private static final String TAG = "GetServiceRequest";
    private static final int VERSION_CODE = 2;
    @Field(id = 5)
    IBinder accountAccessorBinder;
    @Field(id = 4)
    String callingPackage;
    @Field(id = 8)
    Account clientRequestedAccount;
    @Field(id = 3)
    int clientVersion;
    @Field(id = 7)
    Bundle extraArgs;
    @Field(id = 6)
    Scope[] scopes;
    @Field(id = 2)
    final int serviceId;
    @VersionField(id = 1)
    final int version;

    static {
        CREATOR = new GetServiceRequestCreator();
    }

    public GetServiceRequest(int serviceId) {
        this.version = VERSION_CODE;
        this.clientVersion = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.serviceId = serviceId;
    }

    @Constructor
    GetServiceRequest(@Param(id = 1) int version, @Param(id = 2) int serviceId, @Param(id = 3) int clientVersion, @Param(id = 4) String callingPackage, @Param(id = 5) IBinder accountAccessorBinder, @Param(id = 6) Scope[] scopes, @Param(id = 7) Bundle extraArgs, @Param(id = 8) Account clientRequestedAccount) {
        this.version = version;
        this.serviceId = serviceId;
        this.clientVersion = clientVersion;
        this.callingPackage = callingPackage;
        if (version < VERSION_CODE) {
            this.clientRequestedAccount = getAccountFromAccessor(accountAccessorBinder);
        } else {
            this.accountAccessorBinder = accountAccessorBinder;
            this.clientRequestedAccount = clientRequestedAccount;
        }
        this.scopes = scopes;
        this.extraArgs = extraArgs;
    }

    public int getClientVersion() {
        return this.clientVersion;
    }

    public GetServiceRequest setClientVersion(int clientVersion) {
        this.clientVersion = clientVersion;
        return this;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public String getCallingPackage() {
        return this.callingPackage;
    }

    public GetServiceRequest setCallingPackage(String callingPackage) {
        this.callingPackage = callingPackage;
        return this;
    }

    public Account getClientRequestedAccount() {
        return this.clientRequestedAccount;
    }

    public GetServiceRequest setClientRequestedAccount(Account clientRequestedAccount) {
        this.clientRequestedAccount = clientRequestedAccount;
        return this;
    }

    public Account getAuthenticatedAccount() {
        return getAccountFromAccessor(this.accountAccessorBinder);
    }

    public GetServiceRequest setAuthenticatedAccount(IAccountAccessor authenticatedAccountAccessor) {
        if (authenticatedAccountAccessor != null) {
            this.accountAccessorBinder = authenticatedAccountAccessor.asBinder();
        }
        return this;
    }

    public Scope[] getScopes() {
        return this.scopes;
    }

    public GetServiceRequest setScopes(Collection<Scope> scopes) {
        this.scopes = (Scope[]) scopes.toArray(new Scope[scopes.size()]);
        return this;
    }

    public Bundle getExtraArgs() {
        return this.extraArgs;
    }

    public GetServiceRequest setExtraArgs(Bundle extraArgs) {
        this.extraArgs = extraArgs;
        return this;
    }

    public static Creator<GetServiceRequest> getCreator() {
        return CREATOR;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        GetServiceRequestCreator.writeToParcel(this, dest, flags);
    }

    private Account getAccountFromAccessor(IBinder accountAccessorBinder) {
        if (accountAccessorBinder != null) {
            return AccountAccessor.getAccountBinderSafe(Stub.asInterface(accountAccessorBinder));
        }
        return null;
    }
}
