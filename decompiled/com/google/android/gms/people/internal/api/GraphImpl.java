package com.google.android.gms.people.internal.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.Graph;
import com.google.android.gms.people.Graph.LoadAggregatedPeopleOptions;
import com.google.android.gms.people.Graph.LoadAggregatedPeopleResult;
import com.google.android.gms.people.Graph.LoadCirclesOptions;
import com.google.android.gms.people.Graph.LoadCirclesResult;
import com.google.android.gms.people.Graph.LoadContactsGaiaIdsOptions;
import com.google.android.gms.people.Graph.LoadContactsGaiaIdsResult;
import com.google.android.gms.people.Graph.LoadMeResult;
import com.google.android.gms.people.Graph.LoadOwnersOptions;
import com.google.android.gms.people.Graph.LoadOwnersResult;
import com.google.android.gms.people.Graph.LoadPeopleForAggregationResult;
import com.google.android.gms.people.Graph.LoadPeopleOptions;
import com.google.android.gms.people.Graph.LoadPeopleResult;
import com.google.android.gms.people.Graph.LoadPhoneNumbersResult;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.exp.ContactGaiaIdRawBuffer;
import com.google.android.gms.people.exp.PersonForAggregationRawBuffer;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.model.AggregatedPersonBuffer;
import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.ContactGaiaIdBuffer;
import com.google.android.gms.people.model.OwnerBuffer;
import com.google.android.gms.people.model.PersonBuffer;
import com.google.android.gms.people.model.PhoneNumberBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GraphImpl implements Graph {

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.1 */
    class AnonymousClass1 extends BasePeopleApiMethodImpl<LoadOwnersResult> {
        final /* synthetic */ LoadOwnersOptions val$o;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.1.1 */
        class AnonymousClass1 implements LoadOwnersResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public OwnerBuffer getOwners() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass1(GoogleApiClient x0, LoadOwnersOptions loadOwnersOptions) {
            this.val$o = loadOwnersOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadOwners(this, false, this.val$o.isIncludePlusPages(), null, null, this.val$o.getSortOrder());
        }

        protected LoadOwnersResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.2 */
    class AnonymousClass2 extends BasePeopleApiMethodImpl<LoadOwnersResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.2.1 */
        class AnonymousClass1 implements LoadOwnersResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public OwnerBuffer getOwners() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass2(GoogleApiClient x0, String str, String str2) {
            this.val$account = str;
            this.val$pageId = str2;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadOwners(this, true, true, this.val$account, this.val$pageId, 0);
        }

        protected LoadOwnersResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.3 */
    class AnonymousClass3 extends BasePeopleApiMethodImpl<LoadCirclesResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ LoadCirclesOptions val$o;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.3.1 */
        class AnonymousClass1 implements LoadCirclesResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public CircleBuffer getCircles() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass3(GoogleApiClient x0, String str, String str2, LoadCirclesOptions loadCirclesOptions) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$o = loadCirclesOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadCircles(this, this.val$account, this.val$pageId, this.val$o.getFilterCircleId(), this.val$o.getFilterCircleType(), this.val$o.getFilterCircleNamePrefix(), this.val$o.isGetVisibility());
        }

        protected LoadCirclesResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.4 */
    class AnonymousClass4 extends BasePeopleApiMethodImpl<LoadPeopleResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ LoadPeopleOptions val$options;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.4.1 */
        class AnonymousClass1 implements LoadPeopleResult {
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

            public void release() {
            }
        }

        AnonymousClass4(GoogleApiClient x0, String str, String str2, LoadPeopleOptions loadPeopleOptions) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$options = loadPeopleOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadPeople(this, this.val$account, this.val$pageId, this.val$options);
        }

        protected LoadPeopleResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.5 */
    class AnonymousClass5 extends BasePeopleApiMethodImpl<LoadAggregatedPeopleResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ LoadAggregatedPeopleOptions val$o;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.5.1 */
        class AnonymousClass1 implements LoadAggregatedPeopleResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public AggregatedPersonBuffer getAggregatedPeople() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass5(GoogleApiClient x0, String str, String str2, LoadAggregatedPeopleOptions loadAggregatedPeopleOptions) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$o = loadAggregatedPeopleOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadAggregatedPeople(this, this.val$account, this.val$pageId, this.val$o.isIncludeInvisible(), this.val$o.getQuery(), this.val$o.isPeopleOnly(), this.val$o.getProjection(), this.val$o.getExtraColumns(), this.val$o.getFilterGaiaId(), this.val$o.isIncludeEvergreenPeople(), this.val$o.getSortOrder());
        }

        protected LoadAggregatedPeopleResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.6 */
    class AnonymousClass6 extends BasePeopleApiMethodImpl<LoadPeopleForAggregationResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ LoadAggregatedPeopleOptions val$o;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.6.1 */
        class AnonymousClass1 implements LoadPeopleForAggregationResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public PersonForAggregationRawBuffer getPeople() {
                return null;
            }

            public ContactGaiaIdRawBuffer getGaiaMap() {
                return null;
            }

            public Bundle getPhoneTypeMapBundle() {
                return null;
            }

            public Bundle getEmailTypeMapBundle() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass6(GoogleApiClient x0, String str, String str2, LoadAggregatedPeopleOptions loadAggregatedPeopleOptions) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$o = loadAggregatedPeopleOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.expLoadPeopleForAggregation(this, this.val$account, this.val$pageId, this.val$o.getQuery(), this.val$o.getSearchFields(), this.val$o.isPeopleOnly(), this.val$o.getProjection(), this.val$o.getExtraColumns(), this.val$o.getFilterGaiaId(), this.val$o.isIncludeEvergreenPeople(), this.val$o.getSortOrder(), this.val$o.getFilterGaiaEdgeTypes());
        }

        protected LoadPeopleForAggregationResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.7 */
    class AnonymousClass7 extends BasePeopleApiMethodImpl<LoadContactsGaiaIdsResult> {
        final /* synthetic */ LoadContactsGaiaIdsOptions val$o;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.7.1 */
        class AnonymousClass1 implements LoadContactsGaiaIdsResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public ContactGaiaIdBuffer getContactsGaiaIds() {
                return null;
            }

            public void release() {
            }
        }

        AnonymousClass7(GoogleApiClient x0, LoadContactsGaiaIdsOptions loadContactsGaiaIdsOptions) {
            this.val$o = loadContactsGaiaIdsOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadContactsGaiaIds(this, this.val$o.getFilterContactInfo(), this.val$o.getFilterGaiaId(), this.val$o.getFilterGaiaEdgeTypes());
        }

        protected LoadContactsGaiaIdsResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.8 */
    class AnonymousClass8 extends BasePeopleApiMethodImpl<LoadMeResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.8.1 */
        class AnonymousClass1 implements LoadMeResult {
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

        AnonymousClass8(GoogleApiClient x0, String str, String str2) {
            this.val$account = str;
            this.val$pageId = str2;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadMe(this, this.val$account, this.val$pageId);
        }

        protected LoadMeResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.9 */
    class AnonymousClass9 extends BasePeopleApiMethodImpl<LoadPhoneNumbersResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ Bundle val$options;

        /* renamed from: com.google.android.gms.people.internal.api.GraphImpl.9.1 */
        class AnonymousClass1 implements LoadPhoneNumbersResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public void release() {
            }

            public PhoneNumberBuffer getPhoneNumbers() {
                return null;
            }
        }

        AnonymousClass9(GoogleApiClient x0, String str, Bundle bundle) {
            this.val$account = str;
            this.val$options = bundle;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadPhoneNumbers(this, this.val$account, this.val$options);
        }

        protected LoadPhoneNumbersResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    public PendingResult<LoadOwnersResult> loadOwners(@Nonnull GoogleApiClient googleApiClient, @Nullable LoadOwnersOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadOwners", options);
        }
        return googleApiClient.enqueue(new AnonymousClass1(googleApiClient, options != null ? options : LoadOwnersOptions.DEFAULT));
    }

    public PendingResult<LoadOwnersResult> loadOwner(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadOwner", account, pageId);
        }
        return googleApiClient.enqueue(new AnonymousClass2(googleApiClient, account, pageId));
    }

    public PendingResult<LoadCirclesResult> loadCircles(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nullable LoadCirclesOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadCircles", account, pageId, options);
        }
        return googleApiClient.enqueue(new AnonymousClass3(googleApiClient, account, pageId, options != null ? options : LoadCirclesOptions.DEFAULT));
    }

    public PendingResult<LoadPeopleResult> loadPeople(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nullable LoadPeopleOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadPeople", account, pageId, options);
        }
        return googleApiClient.enqueue(new AnonymousClass4(googleApiClient, account, pageId, options));
    }

    public PendingResult<LoadAggregatedPeopleResult> loadAggregatedPeople(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nullable LoadAggregatedPeopleOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadAggregatedPeople", account, pageId, options);
        }
        return googleApiClient.enqueue(new AnonymousClass5(googleApiClient, account, pageId, options != null ? options : LoadAggregatedPeopleOptions.DEFAULT));
    }

    public PendingResult<LoadPeopleForAggregationResult> expLoadPeopleForAggregation(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nullable LoadAggregatedPeopleOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("expLoadPeopleForAggregation", account, pageId, options);
        }
        return googleApiClient.enqueue(new AnonymousClass6(googleApiClient, account, pageId, options != null ? options : LoadAggregatedPeopleOptions.DEFAULT));
    }

    public PendingResult<LoadContactsGaiaIdsResult> loadContactsGaiaIds(@Nonnull GoogleApiClient googleApiClient, @Nullable LoadContactsGaiaIdsOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadContactsGaiaIds", options);
        }
        return googleApiClient.enqueue(new AnonymousClass7(googleApiClient, options != null ? options : LoadContactsGaiaIdsOptions.DEFAULT));
    }

    public PendingResult<LoadMeResult> loadMe(GoogleApiClient googleApiClient, String account, String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadMe", account, pageId);
        }
        return googleApiClient.enqueue(new AnonymousClass8(googleApiClient, account, pageId));
    }

    public PendingResult<LoadPhoneNumbersResult> loadPhoneNumbers(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable Bundle options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadPhoneNumbers", account, PeopleUtils.writeBundle(options));
        }
        return googleApiClient.enqueue(new AnonymousClass9(googleApiClient, account, options));
    }
}
