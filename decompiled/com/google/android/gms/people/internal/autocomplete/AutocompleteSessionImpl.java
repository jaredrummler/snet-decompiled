package com.google.android.gms.people.internal.autocomplete;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.PendingResultFacade;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.Autocomplete.AutocompleteSession;
import com.google.android.gms.people.Autocomplete.Autocompletion;
import com.google.android.gms.people.Autocomplete.AutocompletionListener;
import com.google.android.gms.people.Autocomplete.ClientConfig;
import com.google.android.gms.people.Autocomplete.ContactGroup;
import com.google.android.gms.people.Autocomplete.LoadPhotoOptions;
import com.google.android.gms.people.Autocomplete.LoadPhotoResult;
import com.google.android.gms.people.Autocomplete.Person;
import com.google.android.gms.people.Autocomplete.Photo;
import com.google.android.gms.people.Autocomplete.PreferredFieldsResult;
import com.google.android.gms.people.Images.LoadImageOptions.Builder;
import com.google.android.gms.people.Images.LoadImageResult;
import com.google.android.gms.people.People;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.PeopleClientImpl.AutocompleteResultListener;
import com.google.android.gms.people.internal.PeopleClientImpl.LoadAutocompleteResult;
import com.google.android.gms.people.model.AvatarReference;
import com.google.android.gms.people.model.ContactGroupPreferredFieldsBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

public class AutocompleteSessionImpl implements AutocompleteSession {
    private static long nextId;
    public String mAccount;
    private AutocompletionListener mAutocompletionListener;
    private ClientConfig mClientConfig;
    private Context mContext;
    private boolean mIsClosed;
    private AutocompleteResultListenerImpl mListener;
    private BasePeopleApiMethodImpl<LoadAutocompleteResult> mLoadAutocompleteResult;
    private long mSessionId;

    /* renamed from: com.google.android.gms.people.internal.autocomplete.AutocompleteSessionImpl.1 */
    class AnonymousClass1 extends BasePeopleApiMethodImpl<LoadAutocompleteResult> {
        final /* synthetic */ AutocompleteResultListenerImpl val$listener;
        final /* synthetic */ String val$query;

        /* renamed from: com.google.android.gms.people.internal.autocomplete.AutocompleteSessionImpl.1.1 */
        class AnonymousClass1 implements LoadAutocompleteResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public ArrayList<AutocompletionImpl> getAutocompletions() {
                return null;
            }

            public void release() {
            }

            public Status getStatus() {
                return this.val$status;
            }

            public int getCallbackNumber() {
                return 0;
            }

            public int getCallbackTotal() {
                return 0;
            }
        }

        AnonymousClass1(GoogleApiClient x0, AutocompleteResultListenerImpl autocompleteResultListenerImpl, String str) {
            this.val$listener = autocompleteResultListenerImpl;
            this.val$query = str;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            setCancelToken(clientImpl.loadAutocompleteResults(this.val$listener, AutocompleteSessionImpl.this.mAccount, this.val$query, AutocompleteSessionImpl.this.mSessionId, AutocompleteSessionImpl.this.mClientConfig.clientId));
        }

        protected LoadAutocompleteResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.autocomplete.AutocompleteSessionImpl.2 */
    static class AnonymousClass2 extends PendingResultFacade<LoadImageResult, LoadPhotoResult> {
        AnonymousClass2(PendingResult x0) {
            super(x0);
        }

        protected LoadPhotoResult translate(LoadImageResult value) {
            return new LoadPhotoResult(value.getStatus(), value.getParcelFileDescriptor(), value.isRewindable(), value.getWidth(), value.getHeight());
        }
    }

    /* renamed from: com.google.android.gms.people.internal.autocomplete.AutocompleteSessionImpl.3 */
    class AnonymousClass3 extends BasePeopleApiMethodImpl<PreferredFieldsResult> {
        final /* synthetic */ ContactGroup val$contactGroup;

        /* renamed from: com.google.android.gms.people.internal.autocomplete.AutocompleteSessionImpl.3.1 */
        class AnonymousClass1 implements PreferredFieldsResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public void release() {
            }

            public ContactGroupPreferredFieldsBuffer getPreferredFields() {
                return null;
            }
        }

        AnonymousClass3(GoogleApiClient x0, ContactGroup contactGroup) {
            this.val$contactGroup = contactGroup;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadContactGroupPreferredFields(this, AutocompleteSessionImpl.this.mAccount, this.val$contactGroup.getId().getId());
        }

        protected PreferredFieldsResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    private static class AutocompleteResultListenerImpl implements AutocompleteResultListener {
        private AutocompleteSessionImpl mAutocompleteSession;
        private AutocompletionListener mAutocompletionListener;
        private Set<Integer> mCallbacksMade;
        private boolean mIsCanceled;
        private volatile boolean mIsFinished;
        private Map<Integer, List<Autocompletion>> mSavedCallbacks;

        public AutocompleteResultListenerImpl(AutocompleteSessionImpl autocompleteSession, AutocompletionListener autocompletionListener) {
            this.mIsCanceled = false;
            this.mIsFinished = false;
            this.mCallbacksMade = new HashSet();
            this.mSavedCallbacks = new HashMap();
            this.mAutocompleteSession = autocompleteSession;
            this.mAutocompletionListener = autocompletionListener;
        }

        public final synchronized void cancel() {
            this.mIsCanceled = true;
        }

        public final boolean isFinished() {
            return this.mIsFinished;
        }

        public final void failed(Status status) {
        }

        public synchronized void resultsAvailable(LoadAutocompleteResult result) {
            if (!this.mIsCanceled) {
                if (result.getCallbackNumber() == 0 || this.mCallbacksMade.contains(Integer.valueOf(result.getCallbackNumber() - 1))) {
                    this.mCallbacksMade.add(Integer.valueOf(result.getCallbackNumber()));
                    onAutocompletionsAvailable((Autocompletion[]) createAutocompletionList(result).toArray(new Autocompletion[0]), result.getCallbackNumber(), result.getCallbackTotal());
                    for (int i = result.getCallbackNumber() + 1; this.mSavedCallbacks.containsKey(Integer.valueOf(i)); i++) {
                        onAutocompletionsAvailable((Autocompletion[]) ((List) this.mSavedCallbacks.get(Integer.valueOf(i))).toArray(new Autocompletion[0]), i, result.getCallbackTotal());
                        this.mSavedCallbacks.remove(Integer.valueOf(i));
                        this.mCallbacksMade.add(Integer.valueOf(i));
                    }
                } else {
                    this.mSavedCallbacks.put(Integer.valueOf(result.getCallbackNumber()), createAutocompletionList(result));
                }
            }
        }

        private void onAutocompletionsAvailable(Autocompletion[] autocompletions, int callbackNumber, int callbackTotal) {
            this.mAutocompletionListener.onAutocompletionsAvailable(autocompletions, callbackNumber, callbackTotal);
            if (callbackNumber == callbackTotal - 1) {
                this.mIsFinished = true;
            }
        }

        private List<Autocompletion> createAutocompletionList(LoadAutocompleteResult result) {
            List<Autocompletion> autocompletionList = new ArrayList(result.getAutocompletions().size());
            Iterator i$ = result.getAutocompletions().iterator();
            while (i$.hasNext()) {
                autocompletionList.add((AutocompletionImpl) i$.next());
            }
            return autocompletionList;
        }
    }

    public AutocompleteSessionImpl(GoogleApiClient googleApiClient, ClientConfig clientConfig, String account, AutocompletionListener autocompletionListener) {
        this.mIsClosed = false;
        this.mAccount = (String) Preconditions.checkNotNull(account);
        Preconditions.checkNotNull(googleApiClient);
        this.mAutocompletionListener = (AutocompletionListener) Preconditions.checkNotNull(autocompletionListener);
        this.mSessionId = createNewSessionId();
        this.mClientConfig = (ClientConfig) Preconditions.checkNotNull(clientConfig);
        this.mLoadAutocompleteResult = null;
    }

    private AutocompleteResultListenerImpl loadPeople(GoogleApiClient client, @Nonnull String query) {
        Preconditions.checkNotNull(client);
        AutocompleteResultListenerImpl listener = new AutocompleteResultListenerImpl(this, this.mAutocompletionListener);
        if (!(this.mLoadAutocompleteResult == null || this.mListener.isFinished())) {
            this.mLoadAutocompleteResult.cancel();
        }
        this.mLoadAutocompleteResult = (BasePeopleApiMethodImpl) client.enqueue(new AnonymousClass1(client, listener, query));
        return listener;
    }

    public synchronized void adjustQuery(GoogleApiClient client, String enteredText) {
        Preconditions.checkNotNull(client);
        Preconditions.checkNotNull(enteredText);
        throwIfClosed();
        if (this.mListener != null) {
            this.mListener.cancel();
        }
        if (!enteredText.isEmpty()) {
            this.mListener = loadPeople(client, enteredText);
        }
    }

    public void startNewQuery(GoogleApiClient client) {
        Preconditions.checkNotNull(client);
        adjustQuery(client, BuildConfig.VERSION_NAME);
    }

    public synchronized void close(GoogleApiClient client) {
        Preconditions.checkNotNull(client);
        throwIfClosed();
        if (this.mListener != null) {
            this.mListener.cancel();
        }
        if (this.mLoadAutocompleteResult != null) {
            this.mLoadAutocompleteResult.cancel();
        }
        this.mIsClosed = true;
    }

    public void reportDisplay(GoogleApiClient client, Autocompletion autocompletion) {
        Preconditions.checkNotNull(client, "The client cannot be null");
        Preconditions.checkNotNull(autocompletion, "The autocompletion cannot be null");
    }

    public void reportSelection(GoogleApiClient client, Autocompletion autocompletion) {
        Preconditions.checkNotNull(client, "The client cannot be null");
        Preconditions.checkNotNull(autocompletion, "The autocompletion cannot be null");
    }

    public void reportSubmissionSave(GoogleApiClient client, Autocompletion autocompletion, String[] ids) {
        Preconditions.checkNotNull(client, "The client cannot be null");
        Preconditions.checkNotNull(autocompletion, "The autocompletion cannot be null");
    }

    public void reportSubmissionSend(GoogleApiClient client, Autocompletion autocompletion, String[] ids) {
        Preconditions.checkNotNull(client, "The client cannot be null");
        Preconditions.checkNotNull(autocompletion, "The autocompletion cannot be null");
    }

    public PendingResult<LoadPhotoResult> loadPrimaryPhoto(GoogleApiClient client, Person person, LoadPhotoOptions photoOptions) {
        Preconditions.checkNotNull(client, "The client cannot be null");
        Preconditions.checkNotNull(person, "The person cannot be null");
        Preconditions.checkArgument(person instanceof PersonImpl, "The person must be provided by the Autocomplete Session.");
        for (Photo photo : person.getPhotos()) {
            if (photo.isDefault()) {
                return loadPhoto(client, (PhotoImpl) photo, photoOptions);
            }
        }
        return PendingResults.immediatePendingResult(LoadPhotoResult.FAILED_RESULT);
    }

    private PendingResult<LoadPhotoResult> loadPhoto(GoogleApiClient client, PhotoImpl photo, LoadPhotoOptions photoOptions) {
        Preconditions.checkNotNull(client);
        return createFacade(People.ImageApi.loadByReference(client, new AvatarReference(photo.getSource(), photo.getLocation()), new Builder().setImageSize(photoOptions.getImageSize()).setAvatarOptions(photoOptions.getPhotoOptions()).build()));
    }

    private static PendingResult<LoadPhotoResult> createFacade(PendingResult<LoadImageResult> pendingResult) {
        return new AnonymousClass2(pendingResult);
    }

    public PendingResult<PreferredFieldsResult> getAllPreferredFields(GoogleApiClient client, ContactGroup contactGroup) {
        Preconditions.checkNotNull(client, "The GoogleApiClient cannot be null.");
        Preconditions.checkNotNull(contactGroup, "The ContactGroup cannot be null.");
        return client.enqueue(new AnonymousClass3(client, contactGroup));
    }

    private synchronized void throwIfClosed() {
        if (this.mIsClosed) {
            throw new IllegalStateException("AutocompleteSession has already been closed.");
        }
    }

    static {
        nextId = 0;
    }

    private static synchronized long createNewSessionId() {
        long id;
        synchronized (AutocompleteSessionImpl.class) {
            id = nextId;
            nextId++;
        }
        return id;
    }
}
