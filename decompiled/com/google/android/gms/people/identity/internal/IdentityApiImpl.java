package com.google.android.gms.people.identity.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.PendingResultFacade;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataBufferIterator;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.People;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.Endpoints;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gms.people.PeopleConstants.PeoplePhone;
import com.google.android.gms.people.identity.IdentityApi;
import com.google.android.gms.people.identity.IdentityApi.CustomPersonListResult;
import com.google.android.gms.people.identity.IdentityApi.CustomPersonResult;
import com.google.android.gms.people.identity.IdentityApi.FirstPartyOptions;
import com.google.android.gms.people.identity.IdentityApi.GetOptions;
import com.google.android.gms.people.identity.IdentityApi.GetOptions.Builder;
import com.google.android.gms.people.identity.IdentityApi.ListOptions;
import com.google.android.gms.people.identity.IdentityApi.PersonListResult;
import com.google.android.gms.people.identity.IdentityApi.PersonResult;
import com.google.android.gms.people.identity.PersonFactory;
import com.google.android.gms.people.identity.PersonFactory.ContactData;
import com.google.android.gms.people.identity.PersonFactory.OfflineDatabaseData;
import com.google.android.gms.people.identity.PersonFactory.ServiceData;
import com.google.android.gms.people.identity.PersonListFactory;
import com.google.android.gms.people.identity.PersonListFactory.PersonListItemFactory;
import com.google.android.gms.people.identity.internal.ContactDataLoader.ContactDataLoaderCallback;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.identity.models.Person;
import com.google.android.gms.people.identity.models.PersonReference;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.PeopleClientImpl.GetByIdListener;
import com.google.android.gms.people.internal.PeopleClientImpl.IdentityListListener;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gsf.TalkContract.PresenceColumns;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IdentityApiImpl implements IdentityApi {
    private static final PersonFactory<Person> DEFAULT_PERSON_FACTORY;
    private static final PersonListFactory<PersonReference> DEFAULT_PERSON_LIST_FACTORY;
    private static final String TAG = "PeopleClient";

    /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.1 */
    static class AnonymousClass1 implements CustomPersonResult<T> {
        final /* synthetic */ Status val$status;

        AnonymousClass1(Status status) {
            this.val$status = status;
        }

        public Status getStatus() {
            return this.val$status;
        }

        public DataBuffer<T> getPersonBuffer() {
            return null;
        }

        public boolean isResultComplete() {
            return true;
        }

        public boolean isLocalResultComplete() {
            return true;
        }

        public PendingResult<CustomPersonResult<T>> getNextPendingResult() {
            return null;
        }

        public void release() {
        }
    }

    /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.2 */
    static class AnonymousClass2 implements CustomPersonListResult<T> {
        final /* synthetic */ Status val$status;

        AnonymousClass2(Status status) {
            this.val$status = status;
        }

        public Status getStatus() {
            return this.val$status;
        }

        public DataBuffer<T> getPersonBuffer() {
            return null;
        }

        public boolean isResultComplete() {
            return true;
        }

        public PendingResult<CustomPersonListResult<T>> getNextPendingResult() {
            return null;
        }

        public void release() {
        }
    }

    /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.3 */
    static class AnonymousClass3 extends PendingResultFacade<CustomPersonResult<Person>, PersonResult> {

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.3.1 */
        class AnonymousClass1 implements PersonResult {
            final /* synthetic */ CustomPersonResult val$value;

            AnonymousClass1(CustomPersonResult customPersonResult) {
                this.val$value = customPersonResult;
            }

            public Status getStatus() {
                return this.val$value.getStatus();
            }

            public DataBuffer<Person> getPersonBuffer() {
                return this.val$value.getPersonBuffer();
            }

            public boolean isResultComplete() {
                return this.val$value.isResultComplete();
            }

            public boolean isLocalResultComplete() {
                return this.val$value.isLocalResultComplete();
            }

            public PendingResult<PersonResult> getNextPendingResult() {
                if (this.val$value.getNextPendingResult() == null) {
                    return null;
                }
                return IdentityApiImpl.createFacade(this.val$value.getNextPendingResult());
            }

            public void release() {
            }
        }

        AnonymousClass3(PendingResult x0) {
            super(x0);
        }

        protected PersonResult translate(CustomPersonResult<Person> value) {
            return new AnonymousClass1(value);
        }
    }

    /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.4 */
    static class AnonymousClass4 extends PendingResultFacade<CustomPersonListResult<PersonReference>, PersonListResult> {

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.4.1 */
        class AnonymousClass1 implements PersonListResult {
            final /* synthetic */ CustomPersonListResult val$value;

            AnonymousClass1(CustomPersonListResult customPersonListResult) {
                this.val$value = customPersonListResult;
            }

            public Status getStatus() {
                return this.val$value.getStatus();
            }

            public DataBuffer<PersonReference> getPersonBuffer() {
                return this.val$value.getPersonBuffer();
            }

            public boolean isResultComplete() {
                return this.val$value.isResultComplete();
            }

            public PendingResult<PersonListResult> getNextPendingResult() {
                if (this.val$value.getNextPendingResult() == null) {
                    return null;
                }
                return IdentityApiImpl.createListFacade(this.val$value.getNextPendingResult());
            }

            public void release() {
            }
        }

        AnonymousClass4(PendingResult x0) {
            super(x0);
        }

        protected PersonListResult translate(CustomPersonListResult<PersonReference> value) {
            return new AnonymousClass1(value);
        }
    }

    @VisibleForTesting
    final class GetByIdProcessor<PersonType> extends BasePeopleApiMethodImpl<CustomPersonResult<PersonType>> implements GetByIdListener, ContactDataLoaderCallback {
        private ContactData[] mContactData;
        private boolean mContactQueryStarted;
        private Status mContactStatus;
        private Context mContext;
        private final Set<DataHolder> mDataHolders;
        private DataHolder mDbCircles;
        private DataHolder mDbOwnerAddresses;
        private DataHolder mDbOwnerEmails;
        private DataHolder mDbOwnerPhones;
        private DataHolder mDbOwners;
        private DataHolder mDbPeople;
        private DataHolder mDbPeopleAddresses;
        private DataHolder mDbPeopleEmails;
        private DataHolder mDbPeoplePhones;
        private final PersonFactory<PersonType> mFactory;
        private final WeakReference<GoogleApiClient> mGoogleApiClient;
        private final Object[] mKeys;
        private ResultHolder<CustomPersonResult<PersonType>> mListener;
        private final GetOptions mOptions;
        private final String[] mQualifiedIds;
        private ArrayList<Bundle> mServerBundles;
        private boolean mServiceComplete;
        private Status mServiceStatus;

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.GetByIdProcessor.1 */
        class AnonymousClass1 extends ApiMethodImpl<CustomPersonResult<PersonType>, PeopleClientImpl> {
            AnonymousClass1(ClientKey x0, GoogleApiClient x1) {
                super(x0, x1);
            }

            protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            }

            protected CustomPersonResult<PersonType> createFailedResult(Status status) {
                return IdentityApiImpl.createFailedGetResult(status);
            }
        }

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.GetByIdProcessor.2 */
        class AnonymousClass2 implements DataBuffer<PersonType> {
            final /* synthetic */ ApiMethodImpl val$nextListener;

            AnonymousClass2(ApiMethodImpl apiMethodImpl) {
                this.val$nextListener = apiMethodImpl;
            }

            public int getCount() {
                return GetByIdProcessor.this.mKeys.length;
            }

            public PersonType get(int position) {
                return GetByIdProcessor.this.mFactory.build(GetByIdProcessor.this.mContext, GetByIdProcessor.this.mKeys[position], GetByIdProcessor.this.mServerBundles == null ? null : ServiceData.fromBundle((Bundle) GetByIdProcessor.this.mServerBundles.get(position)), GetByIdProcessor.this.mContactData == null ? null : GetByIdProcessor.this.mContactData[position], GetByIdProcessor.this.mDbPeople == null ? null : OfflineDatabaseData.build(GetByIdProcessor.this.mDbPeople, GetByIdProcessor.this.mDbPeopleAddresses, GetByIdProcessor.this.mDbPeopleEmails, GetByIdProcessor.this.mDbPeoplePhones, GetByIdProcessor.this.mDbOwners, GetByIdProcessor.this.mDbOwnerAddresses, GetByIdProcessor.this.mDbOwnerEmails, GetByIdProcessor.this.mDbOwnerPhones, GetByIdProcessor.this.mDbCircles, position));
            }

            public Bundle getMetadata() {
                return null;
            }

            public void close() {
                release();
            }

            public boolean isClosed() {
                return false;
            }

            public Iterator<PersonType> iterator() {
                return new DataBufferIterator(this);
            }

            public Iterator<PersonType> singleRefIterator() {
                return iterator();
            }

            public void release() {
                if (this.val$nextListener != null) {
                    this.val$nextListener.cancel();
                }
                for (DataHolder holder : GetByIdProcessor.this.mDataHolders) {
                    holder.close();
                }
            }
        }

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.GetByIdProcessor.3 */
        class AnonymousClass3 implements CustomPersonResult<PersonType> {
            final /* synthetic */ DataBuffer val$buffer;
            final /* synthetic */ boolean val$isComplete;
            final /* synthetic */ boolean val$isLocalResultComplete;
            final /* synthetic */ ApiMethodImpl val$nextListener;
            final /* synthetic */ Status val$status;

            AnonymousClass3(Status status, DataBuffer dataBuffer, boolean z, boolean z2, ApiMethodImpl apiMethodImpl) {
                this.val$status = status;
                this.val$buffer = dataBuffer;
                this.val$isComplete = z;
                this.val$isLocalResultComplete = z2;
                this.val$nextListener = apiMethodImpl;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public DataBuffer<PersonType> getPersonBuffer() {
                return this.val$buffer;
            }

            public boolean isResultComplete() {
                return this.val$isComplete;
            }

            public boolean isLocalResultComplete() {
                return this.val$isLocalResultComplete;
            }

            public PendingResult<CustomPersonResult<PersonType>> getNextPendingResult() {
                return this.val$nextListener;
            }

            public void release() {
                this.val$buffer.release();
            }
        }

        public GetByIdProcessor(GoogleApiClient googleApiClient, GetOptions options, PersonFactory<PersonType> factory, String[] qualifiedIds) {
            super(googleApiClient);
            this.mDataHolders = new HashSet();
            this.mServiceComplete = false;
            this.mContactQueryStarted = false;
            this.mGoogleApiClient = new WeakReference(googleApiClient);
            this.mOptions = options;
            this.mFactory = factory;
            this.mListener = this;
            this.mQualifiedIds = qualifiedIds;
            this.mKeys = new Object[qualifiedIds.length];
            for (int i = 0; i < this.mKeys.length; i++) {
                this.mKeys[i] = new Object();
            }
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            this.mContext = clientImpl.getContext();
            if (this.mOptions.firstPartyOptions.accountName != null) {
                clientImpl.getById(this, this.mOptions, this.mQualifiedIds);
                return;
            }
            this.mServiceComplete = true;
            if (this.mOptions.useCachedData || this.mOptions.useWebData) {
                this.mServiceStatus = Status.RESULT_INTERNAL_ERROR;
            } else {
                this.mServiceStatus = Status.RESULT_SUCCESS;
            }
            startCp2Query(null);
        }

        protected CustomPersonResult<PersonType> createFailedResult(Status status) {
            return IdentityApiImpl.createFailedGetResult(status);
        }

        private void deliver() {
            if (this.mListener != null) {
                Status status;
                boolean isComplete;
                boolean isLocalResultComplete;
                ApiMethodImpl<CustomPersonResult<PersonType>, PeopleClientImpl> nextListener;
                if (this.mOptions.useContactData && this.mContactStatus == null) {
                    status = new Status(100);
                } else if (!this.mOptions.useWebData && !this.mOptions.useCachedData) {
                    status = Status.RESULT_SUCCESS;
                } else if (this.mServiceComplete) {
                    status = this.mServiceStatus;
                } else {
                    status = new Status(100);
                }
                if (status.getStatusCode() != 100) {
                    isComplete = true;
                } else {
                    isComplete = false;
                }
                if (this.mContactStatus != null) {
                    isLocalResultComplete = true;
                } else {
                    isLocalResultComplete = false;
                }
                ResultHolder<CustomPersonResult<PersonType>> oldListener = this.mListener;
                GoogleApiClient googleApiClient = (GoogleApiClient) this.mGoogleApiClient.get();
                if (isComplete || googleApiClient == null) {
                    nextListener = null;
                } else {
                    nextListener = new AnonymousClass1(People.CLIENT_KEY_1P, googleApiClient);
                    if (googleApiClient.isConnected()) {
                        googleApiClient.execute(nextListener);
                    }
                }
                this.mListener = nextListener;
                if (PeopleServiceLog.canDebugLog()) {
                    PeopleServiceLog.d(IdentityApiImpl.TAG, "Status: " + status + (isComplete ? " (Final Result)" : " (Staged Result)"));
                }
                if (PeopleServiceLog.canVerboseLog()) {
                    PeopleServiceLog.v(IdentityApiImpl.TAG, "old callback: " + oldListener);
                    PeopleServiceLog.v(IdentityApiImpl.TAG, "new callback: " + nextListener);
                }
                oldListener.setResult(new AnonymousClass3(status, new AnonymousClass2(nextListener), isComplete, isLocalResultComplete, nextListener));
            }
        }

        public synchronized void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(IdentityApiImpl.TAG, "GetById callback: status=" + statusCode + "\nresolution=" + resolution + "\ncontent=" + content);
            }
            try {
                content.setClassLoader(getClass().getClassLoader());
                this.mServiceStatus = new Status(statusCode);
                this.mServerBundles = content.getParcelableArrayList(BundleKeys.PEOPLE_SERVER_BLOBS);
                this.mServiceComplete = content.getBoolean(BundleKeys.IS_RESPONSE_COMPLETE);
                DataHolder gaiaMap = (DataHolder) content.getParcelable(BundleKeys.PEOPLE_MAP_GAIA);
                if (gaiaMap != null) {
                    if (this.mOptions.useContactData && !this.mContactQueryStarted) {
                        this.mContactQueryStarted = true;
                        startCp2Query(gaiaMap);
                    }
                    this.mDataHolders.add(gaiaMap);
                }
                Bundle databaseData = content.getBundle(BundleKeys.PEOPLE_DATABASE_TABLES);
                if (databaseData != null) {
                    for (String key : databaseData.keySet()) {
                        this.mDataHolders.add((DataHolder) databaseData.getParcelable(key));
                    }
                    this.mDbPeople = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_PEOPLE);
                    this.mDbPeopleAddresses = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_PEOPLE_ADDRESS);
                    this.mDbPeopleEmails = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_PEOPLE_EMAIL);
                    this.mDbPeoplePhones = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_PEOPLE_PHONE);
                    this.mDbOwners = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_OWNER);
                    this.mDbOwnerAddresses = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_OWNER_ADDRESS);
                    this.mDbOwnerEmails = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_OWNER_EMAIL);
                    this.mDbOwnerPhones = (DataHolder) databaseData.getParcelable(BundleKeys.PEOPLE_DATABASE_TABLE_OWNER_PHONE);
                    this.mDbCircles = (DataHolder) databaseData.getParcelable(Metadata.CIRCLES);
                }
                if (this.mServerBundles != null) {
                    Preconditions.checkState(this.mKeys.length == this.mServerBundles.size());
                }
                deliver();
            } catch (Throwable t) {
                PeopleServiceLog.w(IdentityApiImpl.TAG, "GetById callback error:", t);
            }
        }

        private void startCp2Query(DataHolder gaiaMap) {
            int i;
            Set<String>[] qualifiedIdClusters = new Set[this.mQualifiedIds.length];
            for (i = 0; i < qualifiedIdClusters.length; i++) {
                qualifiedIdClusters[i] = new HashSet();
                qualifiedIdClusters[i].add(this.mQualifiedIds[i]);
            }
            if (gaiaMap != null) {
                String focusId;
                Map<String, Set<String>> gaiaFocusMap = new HashMap();
                for (i = 0; i < gaiaMap.getCount(); i++) {
                    int window = gaiaMap.getWindowIndex(i);
                    String gaiaId = gaiaMap.getString(AcHolderColumns.GAIA_ID, i, window);
                    focusId = gaiaMap.getString(PresenceColumns.CONTACT_ID, i, window);
                    Set<String> set = (Set) gaiaFocusMap.get(gaiaId);
                    if (set == null) {
                        set = new HashSet();
                        gaiaFocusMap.put(gaiaId, set);
                    }
                    set.add(focusId);
                }
                for (i = 0; i < qualifiedIdClusters.length; i++) {
                    if (PeopleUtils.isQualifiedIdFromGaia(this.mQualifiedIds[i])) {
                        Set<String> focusIds = (Set) gaiaFocusMap.get(PeopleUtils.peopleQualifiedIdToGaiaId(this.mQualifiedIds[i]));
                        if (focusIds != null) {
                            for (String focusId2 : focusIds) {
                                qualifiedIdClusters[i].add(ContactDataUtil.focusIdToPeopleQualifiedId(focusId2));
                            }
                        }
                    }
                }
            }
            ContactDataLoader.queryByQualifiedIds(this, this.mContext, this.mOptions.firstPartyOptions.accountName, qualifiedIdClusters);
        }

        public synchronized void onContactResults(Status status, ContactData[] result) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(IdentityApiImpl.TAG, "GetById CP2 callback: status=" + status + " result=" + String.valueOf(result));
            }
            try {
                this.mContactStatus = status;
                this.mContactData = result;
                if (this.mContactData != null) {
                    Preconditions.checkState(this.mKeys.length == this.mContactData.length);
                }
                deliver();
            } catch (Throwable t) {
                PeopleServiceLog.w(IdentityApiImpl.TAG, "GetById CP2 callback error:", t);
            }
        }
    }

    @VisibleForTesting
    final class ListProcessor<PersonRefType> extends BasePeopleApiMethodImpl<CustomPersonListResult<PersonRefType>> implements ContactDataLoaderCallback {
        private PeopleClientImpl mClientImpl;
        private ContactData[] mContactData;
        private boolean mContactQueryStarted;
        private Status mContactStatus;
        private Context mContext;
        private final Set<DataHolder> mDataHolders;
        private final PersonListFactory<PersonRefType> mFactory;
        private final WeakReference<GoogleApiClient> mGoogleApiClient;
        private ResultHolder<CustomPersonListResult<PersonRefType>> mListener;
        private final ListOptions mOptions;
        private Bundle mServerBundle;
        private boolean mServiceComplete;
        private Status mServiceStatus;

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.ListProcessor.1 */
        class AnonymousClass1 extends ApiMethodImpl<CustomPersonListResult<PersonRefType>, PeopleClientImpl> {
            AnonymousClass1(ClientKey x0, GoogleApiClient x1) {
                super(x0, x1);
            }

            protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            }

            protected CustomPersonListResult<PersonRefType> createFailedResult(Status status) {
                return IdentityApiImpl.createFailedListResult(status);
            }
        }

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.ListProcessor.2 */
        class AnonymousClass2 implements DataBuffer<PersonRefType> {
            final /* synthetic */ PersonListItemFactory val$list;
            final /* synthetic */ ApiMethodImpl val$nextListener;

            AnonymousClass2(PersonListItemFactory personListItemFactory, ApiMethodImpl apiMethodImpl) {
                this.val$list = personListItemFactory;
                this.val$nextListener = apiMethodImpl;
            }

            public int getCount() {
                return this.val$list.getCount();
            }

            public PersonRefType get(int position) {
                return this.val$list.get(position);
            }

            public Bundle getMetadata() {
                return null;
            }

            public void close() {
                release();
            }

            public boolean isClosed() {
                return false;
            }

            public Iterator<PersonRefType> iterator() {
                return new DataBufferIterator(this);
            }

            public Iterator<PersonRefType> singleRefIterator() {
                return iterator();
            }

            public void release() {
                if (this.val$nextListener != null) {
                    this.val$nextListener.cancel();
                }
                for (DataHolder holder : ListProcessor.this.mDataHolders) {
                    holder.close();
                }
            }
        }

        /* renamed from: com.google.android.gms.people.identity.internal.IdentityApiImpl.ListProcessor.3 */
        class AnonymousClass3 implements CustomPersonListResult<PersonRefType> {
            final /* synthetic */ DataBuffer val$buffer;
            final /* synthetic */ boolean val$isComplete;
            final /* synthetic */ ApiMethodImpl val$nextListener;
            final /* synthetic */ Status val$status;

            AnonymousClass3(Status status, DataBuffer dataBuffer, boolean z, ApiMethodImpl apiMethodImpl) {
                this.val$status = status;
                this.val$buffer = dataBuffer;
                this.val$isComplete = z;
                this.val$nextListener = apiMethodImpl;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public DataBuffer<PersonRefType> getPersonBuffer() {
                return this.val$buffer;
            }

            public boolean isResultComplete() {
                return this.val$isComplete;
            }

            public PendingResult<CustomPersonListResult<PersonRefType>> getNextPendingResult() {
                return this.val$nextListener;
            }

            public void release() {
                this.val$buffer.release();
            }
        }

        private class GaiaMapListener implements GetByIdListener {
            private GaiaMapListener() {
            }

            public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
                content.setClassLoader(getClass().getClassLoader());
                DataHolder gaiaMap = (DataHolder) content.getParcelable(BundleKeys.PEOPLE_MAP_GAIA);
                boolean serviceComplete = content.getBoolean(BundleKeys.IS_RESPONSE_COMPLETE);
                if (gaiaMap != null) {
                    ListProcessor.this.mDataHolders.add(gaiaMap);
                }
                if (ListProcessor.this.mOptions.useContactData && !ListProcessor.this.mContactQueryStarted) {
                    if (gaiaMap != null || serviceComplete) {
                        ListProcessor.this.mContactQueryStarted = true;
                        ListProcessor.this.startCp2Query(gaiaMap);
                    }
                }
            }
        }

        private class ListListener implements IdentityListListener {
            private ListListener() {
            }

            public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
                content.setClassLoader(getClass().getClassLoader());
                ListProcessor.this.mServiceStatus = new Status(statusCode);
                ListProcessor.this.mServerBundle = content.getBundle(BundleKeys.PEOPLE_SERVER_BLOBS);
                ListProcessor.this.mServiceComplete = content.getBoolean(BundleKeys.IS_RESPONSE_COMPLETE);
                String[] qualifiedIds = ListProcessor.this.deliver();
                if (ListProcessor.this.mOptions.useContactData && !ListProcessor.this.mContactQueryStarted && qualifiedIds != null) {
                    if (qualifiedIds.length == 0) {
                        ListProcessor.this.mContactQueryStarted = true;
                        ListProcessor.this.startCp2Query(null);
                        return;
                    }
                    ListProcessor.this.mClientImpl.getById(new GaiaMapListener(null), new Builder().setFirstPartyOptions(ListProcessor.this.mOptions.firstPartyOptions).setUseCachedData(false).setUseWebData(false).setUseContactData(true).build(), qualifiedIds);
                }
            }
        }

        public ListProcessor(GoogleApiClient googleApiClient, ListOptions options, PersonListFactory<PersonRefType> factory) {
            super(googleApiClient);
            this.mDataHolders = new HashSet();
            this.mServiceComplete = false;
            this.mContactQueryStarted = false;
            this.mGoogleApiClient = new WeakReference(googleApiClient);
            this.mOptions = options;
            this.mFactory = factory;
            this.mListener = this;
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            this.mClientImpl = clientImpl;
            this.mContext = clientImpl.mContext;
            if (this.mOptions.firstPartyOptions.accountName == null || !(this.mOptions.useWebData || this.mOptions.useCachedData)) {
                this.mServiceComplete = true;
                if (this.mOptions.useCachedData || this.mOptions.useWebData) {
                    this.mServiceStatus = Status.RESULT_INTERNAL_ERROR;
                } else {
                    this.mServiceStatus = Status.RESULT_SUCCESS;
                }
                startCp2Query(null);
                return;
            }
            clientImpl.identityList(new ListListener(), this.mOptions);
        }

        private String[] deliver() {
            if (this.mListener == null) {
                return null;
            }
            Status status;
            ApiMethodImpl<CustomPersonListResult<PersonRefType>, PeopleClientImpl> nextListener;
            if (this.mOptions.useContactData && this.mContactStatus == null) {
                status = new Status(100);
            } else if (!this.mOptions.useWebData && !this.mOptions.useCachedData) {
                status = Status.RESULT_SUCCESS;
            } else if (this.mServiceComplete) {
                status = this.mServiceStatus;
            } else {
                status = new Status(100);
            }
            boolean isComplete = status.getStatusCode() != 100;
            ResultHolder<CustomPersonListResult<PersonRefType>> oldListener = this.mListener;
            GoogleApiClient googleApiClient = (GoogleApiClient) this.mGoogleApiClient.get();
            if (isComplete || googleApiClient == null) {
                nextListener = null;
            } else {
                nextListener = new AnonymousClass1(People.CLIENT_KEY_1P, googleApiClient);
                if (googleApiClient.isConnected()) {
                    googleApiClient.execute(nextListener);
                }
            }
            this.mListener = nextListener;
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(IdentityApiImpl.TAG, "Status: " + status + (isComplete ? " (Final Result)" : " (Staged Result)"));
            }
            if (PeopleServiceLog.canVerboseLog()) {
                PeopleServiceLog.v(IdentityApiImpl.TAG, "old callback: " + oldListener);
                PeopleServiceLog.v(IdentityApiImpl.TAG, "new callback: " + nextListener);
            }
            PersonListItemFactory<PersonRefType> list = this.mFactory.buildList(ServiceData.fromBundle(this.mServerBundle), this.mContactData, null);
            Set<String> qualifiedIds = new HashSet();
            for (int i = 0; i < list.getCount(); i++) {
                String qualifiedId = list.getQualifiedId(i);
                if (qualifiedId != null) {
                    qualifiedIds.add(qualifiedId);
                }
            }
            oldListener.setResult(new AnonymousClass3(status, new AnonymousClass2(list, nextListener), isComplete, nextListener));
            return (String[]) qualifiedIds.toArray(new String[qualifiedIds.size()]);
        }

        private void startCp2Query(DataHolder gaiaMap) {
            Set<String> ignoredQualifiedIds = new HashSet();
            if (gaiaMap != null) {
                for (int i = 0; i < gaiaMap.getCount(); i++) {
                    ignoredQualifiedIds.add(ContactDataUtil.focusIdToPeopleQualifiedId(gaiaMap.getString(PresenceColumns.CONTACT_ID, i, gaiaMap.getWindowIndex(i))));
                }
            }
            ContactDataLoader.listAll(this, this.mContext, this.mOptions.firstPartyOptions.accountName, ignoredQualifiedIds);
        }

        protected CustomPersonListResult<PersonRefType> createFailedResult(Status status) {
            return IdentityApiImpl.createFailedListResult(status);
        }

        public void onContactResults(Status status, ContactData[] result) {
            if (PeopleServiceLog.canDebugLog()) {
                PeopleServiceLog.d(IdentityApiImpl.TAG, "GetById CP2 callback: status=" + status + " result=" + String.valueOf(result));
            }
            try {
                this.mContactStatus = status;
                this.mContactData = result;
                deliver();
            } catch (Throwable t) {
                PeopleServiceLog.w(IdentityApiImpl.TAG, "GetById CP2 callback error:", t);
            }
        }
    }

    static {
        DEFAULT_PERSON_FACTORY = new DefaultPersonFactory();
        DEFAULT_PERSON_LIST_FACTORY = new DefaultPersonListFactory();
    }

    public <PersonType> PendingResult<CustomPersonResult<PersonType>> getByIds(GoogleApiClient googleApiClient, GetOptions options, PersonFactory<PersonType> factory, String... qualifiedIds) {
        for (String qualifiedId : qualifiedIds) {
            Preconditions.checkArgument(!TextUtils.isEmpty(qualifiedId));
        }
        return googleApiClient.enqueue(new GetByIdProcessor(googleApiClient, options, factory, qualifiedIds));
    }

    public PendingResult<PersonResult> getByIds(GoogleApiClient apiClient, GetOptions options, String... qualifiedIds) {
        return createFacade(getByIds(apiClient, options, DEFAULT_PERSON_FACTORY, qualifiedIds));
    }

    private static <T> CustomPersonResult<T> createFailedGetResult(Status status) {
        return new AnonymousClass1(status);
    }

    public <PersonRefType> PendingResult<CustomPersonListResult<PersonRefType>> list(GoogleApiClient googleApiClient, ListOptions options, PersonListFactory<PersonRefType> factory) {
        return googleApiClient.enqueue(new ListProcessor(googleApiClient, options, factory));
    }

    public PendingResult<PersonListResult> list(GoogleApiClient apiClient, ListOptions options) {
        return createListFacade(list(apiClient, options, DEFAULT_PERSON_LIST_FACTORY));
    }

    private static <T> CustomPersonListResult<T> createFailedListResult(Status status) {
        return new AnonymousClass2(status);
    }

    private static PendingResult<PersonResult> createFacade(PendingResult<CustomPersonResult<Person>> pendingResult) {
        return new AnonymousClass3(pendingResult);
    }

    private static PendingResult<PersonListResult> createListFacade(PendingResult<CustomPersonListResult<PersonReference>> pendingResult) {
        return new AnonymousClass4(pendingResult);
    }

    public PendingResult<PersonListResult> listByEmail(GoogleApiClient apiClient, ListOptions options, String emailAddress) {
        if (PeopleUtils.isQualifiedIdFromEmail(emailAddress)) {
            emailAddress = PeopleUtils.peopleQualifiedIdToEmailAddress(emailAddress);
        }
        return list(apiClient, ListOptions.Builder.fromOptions(options).setFirstPartyOptions(FirstPartyOptions.Builder.fromOptions(options.firstPartyOptions).setEndpoint(Endpoints.ENDPOINT_LIST_BY_EMAIL).addEndpointArgument(PeopleEmail.EMAIL_ADDRESS, emailAddress)).build());
    }

    public PendingResult<PersonListResult> listByPhone(GoogleApiClient apiClient, ListOptions options, String phoneNumber) {
        if (ContactDataUtil.isQualifiedIdFromPhoneNumber(phoneNumber)) {
            phoneNumber = ContactDataUtil.peopleQualifiedIdToPhoneNumber(phoneNumber);
        }
        return list(apiClient, ListOptions.Builder.fromOptions(options).setFirstPartyOptions(FirstPartyOptions.Builder.fromOptions(options.firstPartyOptions).setEndpoint(Endpoints.ENDPOINT_LIST_BY_PHONE).addEndpointArgument(PeoplePhone.PHONE_NUMBER, phoneNumber)).build());
    }
}
