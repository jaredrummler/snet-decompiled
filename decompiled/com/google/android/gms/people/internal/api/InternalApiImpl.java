package com.google.android.gms.people.internal.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.InternalApi;
import com.google.android.gms.people.InternalApi.LoadPeopleForAspenOptions;
import com.google.android.gms.people.InternalApi.LoadPeopleForAspenResult;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.People.BundleResult;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.model.PersonBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InternalApiImpl implements InternalApi {

    /* renamed from: com.google.android.gms.people.internal.api.InternalApiImpl.1 */
    class AnonymousClass1 extends BasePeopleApiMethodImpl<LoadPeopleForAspenResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ LoadPeopleForAspenOptions val$options;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.InternalApiImpl.1.1 */
        class AnonymousClass1 implements LoadPeopleForAspenResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public PersonBuffer getPeople() {
                return null;
            }

            public String getNextPageToken() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass1(GoogleApiClient x0, String str, String str2, LoadPeopleForAspenOptions loadPeopleForAspenOptions) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$options = loadPeopleForAspenOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadPeopleForAspen(this, this.val$account, this.val$pageId, this.val$options);
        }

        protected LoadPeopleForAspenResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.InternalApiImpl.2 */
    class AnonymousClass2 extends BasePeopleApiMethodImpl<BundleResult> {
        final /* synthetic */ Bundle val$arguments;

        /* renamed from: com.google.android.gms.people.internal.api.InternalApiImpl.2.1 */
        class AnonymousClass1 implements BundleResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Bundle getBundle() {
                return null;
            }

            public void release() {
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass2(GoogleApiClient x0, Bundle bundle) {
            this.val$arguments = bundle;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.internalCall(this, this.val$arguments);
        }

        protected BundleResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    public PendingResult<LoadPeopleForAspenResult> loadPeopleForAspen(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nullable LoadPeopleForAspenOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadPeopleForAspen", account, pageId, options);
        }
        return googleApiClient.enqueue(new AnonymousClass1(googleApiClient, account, pageId, options));
    }

    public PendingResult<BundleResult> internalCall(@Nonnull GoogleApiClient googleApiClient, @Nonnull Bundle arguments) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("internalCall", arguments);
        }
        return googleApiClient.enqueue(new AnonymousClass2(googleApiClient, arguments));
    }
}
