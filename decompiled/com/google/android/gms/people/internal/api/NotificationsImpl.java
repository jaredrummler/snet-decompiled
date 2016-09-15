package com.google.android.gms.people.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.Notifications;
import com.google.android.gms.people.Notifications.OnDataChanged;
import com.google.android.gms.people.People;
import com.google.android.gms.people.People.BasePeopleSimpleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.PeopleClientImpl.OnDataChangedBinderCallback;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NotificationsImpl implements Notifications {

    /* renamed from: com.google.android.gms.people.internal.api.NotificationsImpl.1 */
    class AnonymousClass1 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ OnDataChangedBinderCallback val$callbackProxy;
        final /* synthetic */ String val$pageId;
        final /* synthetic */ int val$scopes;

        AnonymousClass1(GoogleApiClient x0, OnDataChangedBinderCallback onDataChangedBinderCallback, String str, String str2, int i) {
            this.val$callbackProxy = onDataChangedBinderCallback;
            this.val$account = str;
            this.val$pageId = str2;
            this.val$scopes = i;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.registerOnDataChangedListener(this.val$callbackProxy, this.val$account, this.val$pageId, this.val$scopes);
            setResult(Status.RESULT_SUCCESS);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.NotificationsImpl.2 */
    class AnonymousClass2 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ OnDataChanged val$listener;

        AnonymousClass2(GoogleApiClient x0, OnDataChanged onDataChanged) {
            this.val$listener = onDataChanged;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.unregisterOnDataChangedListener(this.val$listener);
            setResult(Status.RESULT_SUCCESS);
        }
    }

    public PendingResult<Result> registerOnDataChangedListenerForOwner(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged listener, @Nonnull String account, @Nullable String pageId, int scopes) {
        if (account == null) {
            throw new IllegalArgumentException("account must not be null");
        }
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("registerOnDataChangedListenerForOwner", account, pageId, Integer.valueOf(scopes));
        }
        return registerOnDataChangedListenerInner(googleApiClient, listener, account, pageId, scopes);
    }

    public PendingResult<Result> registerOnDataChangedListenerForAllOwners(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged listener, int scopes) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("registerOnDataChangedListenerForAllOwners", Integer.valueOf(scopes));
        }
        return registerOnDataChangedListenerInner(googleApiClient, listener, null, null, scopes);
    }

    private PendingResult<Result> registerOnDataChangedListenerInner(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged listener, @Nonnull String account, @Nullable String pageId, int scopes) {
        return googleApiClient.enqueue(new AnonymousClass1(googleApiClient, ((PeopleClientImpl) googleApiClient.getClient(People.CLIENT_KEY_1P)).getDataChangedBinderCallback(googleApiClient, listener), account, pageId, scopes));
    }

    public PendingResult<Result> unregisterOnDataChangedListener(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged listener) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("unregisterOnDataChangedListener", new Object[0]);
        }
        return googleApiClient.enqueue(new AnonymousClass2(googleApiClient, listener));
    }
}
