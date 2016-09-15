package com.google.android.gms.people.internal.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.google.android.gms.people.GraphUpdate;
import com.google.android.gms.people.GraphUpdate.AddCircleResult;
import com.google.android.gms.people.GraphUpdate.AddPeopleToCircleResult;
import com.google.android.gms.people.GraphUpdate.LoadAddToCircleConsentResult;
import com.google.android.gms.people.GraphUpdate.SetMeResult;
import com.google.android.gms.people.GraphUpdate.UpdatePersonCircleResult;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.People.BasePeopleSimpleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GraphUpdateImpl implements GraphUpdate {

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.10 */
    class AnonymousClass10 extends BasePeopleApiMethodImpl<SetMeResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ Bundle val$bundle;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.10.1 */
        class AnonymousClass1 implements SetMeResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public Bundle getBundle() {
                return null;
            }
        }

        AnonymousClass10(GoogleApiClient x0, String str, String str2, Bundle bundle) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$bundle = bundle;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.setMe(this, this.val$account, this.val$pageId, this.val$bundle);
        }

        protected SetMeResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.1 */
    class AnonymousClass1 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ boolean val$block;
        final /* synthetic */ String val$gaiaId;
        final /* synthetic */ String val$pageId;

        AnonymousClass1(GoogleApiClient x0, String str, String str2, String str3, boolean z) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$gaiaId = str3;
            this.val$block = z;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.blockPerson(this, this.val$account, this.val$pageId, this.val$gaiaId, this.val$block);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.2 */
    class AnonymousClass2 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$peopleV2PersonId;
        final /* synthetic */ boolean val$star;

        AnonymousClass2(GoogleApiClient x0, String str, String str2, boolean z) {
            this.val$account = str;
            this.val$peopleV2PersonId = str2;
            this.val$star = z;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.starPerson(this, this.val$account, this.val$peopleV2PersonId, this.val$star);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.3 */
    class AnonymousClass3 extends BasePeopleApiMethodImpl<AddCircleResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$circleDescription;
        final /* synthetic */ String val$circleName;
        final /* synthetic */ boolean val$enabledForSharing;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.3.1 */
        class AnonymousClass1 implements AddCircleResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public String getCircleId() {
                return null;
            }

            public String getCircleName() {
                return null;
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass3(GoogleApiClient x0, String str, String str2, String str3, String str4, boolean z) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$circleName = str3;
            this.val$circleDescription = str4;
            this.val$enabledForSharing = z;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.addCircle(this, this.val$account, this.val$pageId, this.val$circleName, this.val$circleDescription, this.val$enabledForSharing);
        }

        protected AddCircleResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.4 */
    class AnonymousClass4 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$circleId;
        final /* synthetic */ String val$pageId;

        AnonymousClass4(GoogleApiClient x0, String str, String str2, String str3) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$circleId = str3;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.removeCircle(this, this.val$account, this.val$pageId, this.val$circleId);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.5 */
    class AnonymousClass5 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$circleId;
        final /* synthetic */ String val$newCircleDescription;
        final /* synthetic */ Boolean val$newEnabledForSharing;
        final /* synthetic */ String val$newName;
        final /* synthetic */ String val$pageId;

        AnonymousClass5(GoogleApiClient x0, String str, String str2, String str3, String str4, Boolean bool, String str5) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$circleId = str3;
            this.val$newName = str4;
            this.val$newEnabledForSharing = bool;
            this.val$newCircleDescription = str5;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.updateCircle(this, this.val$account, this.val$pageId, this.val$circleId, this.val$newName, this.val$newEnabledForSharing, this.val$newCircleDescription);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.6 */
    class AnonymousClass6 extends BasePeopleApiMethodImpl<AddPeopleToCircleResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$circleId;
        final /* synthetic */ String val$pageId;
        final /* synthetic */ List val$qualifiedPersonIds;

        /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.6.1 */
        class AnonymousClass1 implements AddPeopleToCircleResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public String getCircleId() {
                return null;
            }

            public String getCircleName() {
                return null;
            }

            public String[] getPeopleQualifiedIds() {
                return null;
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass6(GoogleApiClient x0, String str, String str2, String str3, List list) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$circleId = str3;
            this.val$qualifiedPersonIds = list;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.addPeopleToCircle(this, this.val$account, this.val$pageId, this.val$circleId, this.val$qualifiedPersonIds);
        }

        protected AddPeopleToCircleResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.7 */
    class AnonymousClass7 extends BasePeopleApiMethodImpl<UpdatePersonCircleResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ List val$circleIdsToAdd;
        final /* synthetic */ List val$circleIdsToRemove;
        final /* synthetic */ String val$pageId;
        final /* synthetic */ String val$qualifiedId;
        final /* synthetic */ FavaDiagnosticsEntity val$startView;

        /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.7.1 */
        class AnonymousClass1 implements UpdatePersonCircleResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public List<String> getAddedCircles() {
                return null;
            }

            public List<String> getRemovedCircles() {
                return null;
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass7(GoogleApiClient x0, String str, String str2, String str3, List list, List list2, FavaDiagnosticsEntity favaDiagnosticsEntity) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$qualifiedId = str3;
            this.val$circleIdsToAdd = list;
            this.val$circleIdsToRemove = list2;
            this.val$startView = favaDiagnosticsEntity;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.updatePersonCircles(this, this.val$account, this.val$pageId, this.val$qualifiedId, this.val$circleIdsToAdd, this.val$circleIdsToRemove, this.val$startView);
        }

        protected UpdatePersonCircleResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.8 */
    class AnonymousClass8 extends BasePeopleApiMethodImpl<LoadAddToCircleConsentResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.8.1 */
        class AnonymousClass1 implements LoadAddToCircleConsentResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public boolean getShowConsent() {
                return false;
            }

            public String getConsentHtml() {
                return null;
            }

            public String getConsentTitleText() {
                return null;
            }

            public String getConsentButtonText() {
                return null;
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass8(GoogleApiClient x0, String str, String str2) {
            this.val$account = str;
            this.val$pageId = str2;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadAddToCircleConsent(this, this.val$account, this.val$pageId);
        }

        protected LoadAddToCircleConsentResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphUpdateImpl.9 */
    class AnonymousClass9 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$pageId;

        AnonymousClass9(GoogleApiClient x0, String str, String str2) {
            this.val$account = str;
            this.val$pageId = str2;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.setHasShownAddToCircleConsent(this, this.val$account, this.val$pageId);
        }
    }

    public PendingResult<Result> blockPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String gaiaId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("blockPerson", account, pageId, gaiaId);
        }
        return blockOrUnblockPerson(googleApiClient, account, pageId, gaiaId, true);
    }

    public PendingResult<Result> unblockPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String gaiaId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("unblockPerson", account, pageId, gaiaId);
        }
        return blockOrUnblockPerson(googleApiClient, account, pageId, gaiaId, false);
    }

    private PendingResult<Result> blockOrUnblockPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String gaiaId, boolean block) {
        return googleApiClient.execute(new AnonymousClass1(googleApiClient, account, pageId, gaiaId, block));
    }

    public PendingResult<Result> starPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nonnull String peopleV2PersonId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("starPerson", account, peopleV2PersonId);
        }
        return starOrUnstarPerson(googleApiClient, account, peopleV2PersonId, true);
    }

    public PendingResult<Result> unstarPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nonnull String peopleV2PersonId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("unstarPerson", account, peopleV2PersonId);
        }
        return starOrUnstarPerson(googleApiClient, account, peopleV2PersonId, false);
    }

    private PendingResult<Result> starOrUnstarPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nonnull String peopleV2PersonId, boolean star) {
        return googleApiClient.execute(new AnonymousClass2(googleApiClient, account, peopleV2PersonId, star));
    }

    public PendingResult<AddCircleResult> addCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String circleName, @Nullable String circleDescription) {
        return addCircle(googleApiClient, account, pageId, circleName, circleDescription, true);
    }

    public PendingResult<AddCircleResult> addCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String circleName, @Nullable String circleDescription, boolean enabledForSharing) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("addCircle", account, pageId, circleName, circleDescription, Boolean.valueOf(enabledForSharing));
        }
        return googleApiClient.execute(new AnonymousClass3(googleApiClient, account, pageId, circleName, circleDescription, enabledForSharing));
    }

    public PendingResult<Result> removeCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String circleId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("removeCircle", account, pageId, circleId);
        }
        return googleApiClient.execute(new AnonymousClass4(googleApiClient, account, pageId, circleId));
    }

    public PendingResult<Result> updateCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String circleId, @Nullable String newName, @Nullable Boolean newEnabledForSharing, @Nullable String newCircleDescription) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("updateCircle", account, pageId, circleId, newName, newEnabledForSharing, newCircleDescription);
        }
        return googleApiClient.execute(new AnonymousClass5(googleApiClient, account, pageId, circleId, newName, newEnabledForSharing, newCircleDescription));
    }

    public PendingResult<AddPeopleToCircleResult> addPeopleToCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String circleId, @Nonnull List<String> qualifiedPersonIds) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("addPeopleToCircle", account, pageId, circleId, qualifiedPersonIds);
        }
        return googleApiClient.execute(new AnonymousClass6(googleApiClient, account, pageId, circleId, qualifiedPersonIds));
    }

    public PendingResult<UpdatePersonCircleResult> updatePersonCircles(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String qualifiedId, @Nullable List<String> circleIdsToAdd, @Nullable List<String> circleIdsToRemove) {
        return updatePersonCircles(googleApiClient, account, pageId, qualifiedId, circleIdsToAdd, circleIdsToRemove, null);
    }

    public PendingResult<UpdatePersonCircleResult> updatePersonCircles(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull String qualifiedId, @Nullable List<String> circleIdsToAdd, @Nullable List<String> circleIdsToRemove, @Nullable FavaDiagnosticsEntity startView) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("updatePersonCircles", account, pageId, qualifiedId, circleIdsToAdd, circleIdsToRemove, startView);
        }
        return googleApiClient.execute(new AnonymousClass7(googleApiClient, account, pageId, qualifiedId, circleIdsToAdd, circleIdsToRemove, startView));
    }

    public PendingResult<LoadAddToCircleConsentResult> loadAddToCircleConsent(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadAddToCircleConsent", account, pageId);
        }
        return googleApiClient.enqueue(new AnonymousClass8(googleApiClient, account, pageId));
    }

    public PendingResult<Result> setHasShownAddToCircleConsent(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("setHasShownAddToCircleConsent", account, pageId);
        }
        return googleApiClient.execute(new AnonymousClass9(googleApiClient, account, pageId));
    }

    public PendingResult<SetMeResult> setMe(GoogleApiClient googleApiClient, String account, String pageId, Bundle bundle) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("setMe", account, pageId);
        }
        return googleApiClient.enqueue(new AnonymousClass10(googleApiClient, account, pageId, bundle));
    }
}
