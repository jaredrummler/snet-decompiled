package com.google.android.gms.people.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.People.BasePeopleSimpleApiMethodImpl;
import com.google.android.gms.people.Sync;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SyncImpl implements Sync {

    /* renamed from: com.google.android.gms.people.internal.api.SyncImpl.1 */
    class AnonymousClass1 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ long val$allowanceSecond;
        final /* synthetic */ boolean val$byUserAction;
        final /* synthetic */ boolean val$isDisabledByBackgroundSync;
        final /* synthetic */ String val$pageId;

        AnonymousClass1(GoogleApiClient x0, String str, String str2, long j, boolean z, boolean z2) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$allowanceSecond = j;
            this.val$byUserAction = z;
            this.val$isDisabledByBackgroundSync = z2;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.requestSync(this.val$account, this.val$pageId, this.val$allowanceSecond, this.val$byUserAction, this.val$isDisabledByBackgroundSync);
            setResult(Status.RESULT_SUCCESS);
        }
    }

    public PendingResult<Result> requestSync(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("requestSync", account, pageId);
        }
        return requestSync(googleApiClient, account, pageId, 0, false, false);
    }

    public PendingResult<Result> requestSync(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, long allowanceSecond) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("requestSync", account, pageId, Long.valueOf(allowanceSecond));
        }
        return requestSync(googleApiClient, account, pageId, allowanceSecond, false, false);
    }

    public PendingResult<Result> requestSync(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, long allowanceSecond, boolean isDisabledByBackgroundSync) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("requestSync", account, pageId, Long.valueOf(allowanceSecond), Boolean.valueOf(isDisabledByBackgroundSync));
        }
        return requestSync(googleApiClient, account, pageId, allowanceSecond, false, isDisabledByBackgroundSync);
    }

    public PendingResult<Result> requestSyncByUserAction(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("requestSyncByUserAction", account, pageId);
        }
        return requestSync(googleApiClient, account, pageId, 0, true, false);
    }

    private PendingResult<Result> requestSync(GoogleApiClient googleApiClient, String account, String pageId, long allowanceSecond, boolean byUserAction, boolean isDisabledByBackgroundSync) {
        return googleApiClient.enqueue(new AnonymousClass1(googleApiClient, account, pageId, allowanceSecond, byUserAction, isDisabledByBackgroundSync));
    }
}
