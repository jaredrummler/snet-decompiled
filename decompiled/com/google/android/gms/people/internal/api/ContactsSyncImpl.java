package com.google.android.gms.people.internal.api;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.ContactsSync;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.People.BasePeopleSimpleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ContactsSyncImpl implements ContactsSync {

    /* renamed from: com.google.android.gms.people.internal.api.ContactsSyncImpl.1 */
    class AnonymousClass1 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ Uri val$rawContactUri;

        AnonymousClass1(GoogleApiClient x0, Uri uri) {
            this.val$rawContactUri = uri;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.syncRawContact(this.val$rawContactUri);
            setResult(Status.RESULT_SUCCESS);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ContactsSyncImpl.2 */
    class AnonymousClass2 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ boolean val$enable;

        AnonymousClass2(GoogleApiClient x0, boolean z) {
            this.val$enable = z;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.setSyncToContactsEnabled(this.val$enable);
            setResult(Status.RESULT_SUCCESS);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ContactsSyncImpl.3 */
    class AnonymousClass3 extends BasePeopleApiMethodImpl<BooleanResult> {
        AnonymousClass3(GoogleApiClient x0) {
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            setResult(new BooleanResult(Status.RESULT_SUCCESS, clientImpl.isSyncToContactsEnabled()));
        }

        protected BooleanResult createFailedResult(Status status) {
            return new BooleanResult(status, false);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ContactsSyncImpl.4 */
    class AnonymousClass4 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ boolean val$enableForAccount;
        final /* synthetic */ String[] val$syncTarget;

        AnonymousClass4(GoogleApiClient x0, String str, boolean z, String[] strArr) {
            this.val$account = str;
            this.val$enableForAccount = z;
            this.val$syncTarget = strArr;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.setSyncToContactsSettings(this, this.val$account, this.val$enableForAccount, this.val$syncTarget);
        }
    }

    public PendingResult<Result> syncRawContact(@Nonnull GoogleApiClient googleApiClient, @Nonnull Uri rawContactUri) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("syncRawContact", rawContactUri);
        }
        return googleApiClient.execute(new AnonymousClass1(googleApiClient, rawContactUri));
    }

    public PendingResult<Result> setSyncToContactsEnabled(@Nonnull GoogleApiClient googleApiClient, boolean enable) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("setSyncToContactsEnabled", Boolean.valueOf(enable));
        }
        return googleApiClient.execute(new AnonymousClass2(googleApiClient, enable));
    }

    public PendingResult<BooleanResult> isSyncToContactsEnabled(@Nonnull GoogleApiClient googleApiClient) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("isSyncToContactsEnabled", new Object[0]);
        }
        return googleApiClient.enqueue(new AnonymousClass3(googleApiClient));
    }

    public PendingResult<Result> setSyncToContactsSettings(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, boolean enableForAccount, @Nullable String[] syncTarget) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("setSyncToContactsSettings", account, Boolean.valueOf(enableForAccount), syncTarget);
        }
        return googleApiClient.execute(new AnonymousClass4(googleApiClient, account, enableForAccount, syncTarget));
    }
}
