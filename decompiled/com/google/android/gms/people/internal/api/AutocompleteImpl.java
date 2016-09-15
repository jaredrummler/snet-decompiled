package com.google.android.gms.people.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.people.Autocomplete;
import com.google.android.gms.people.Autocomplete.AutocompleteOptions;
import com.google.android.gms.people.Autocomplete.AutocompleteResult;
import com.google.android.gms.people.Autocomplete.AutocompleteSession;
import com.google.android.gms.people.Autocomplete.AutocompletionListener;
import com.google.android.gms.people.Autocomplete.ClientConfig;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.autocomplete.AutocompleteSessionImpl;
import com.google.android.gms.people.model.AutocompleteBuffer;

public class AutocompleteImpl implements Autocomplete {

    /* renamed from: com.google.android.gms.people.internal.api.AutocompleteImpl.1 */
    class AnonymousClass1 extends BasePeopleApiMethodImpl<AutocompleteResult> {
        final /* synthetic */ AutocompleteOptions val$options;
        final /* synthetic */ String val$query;

        /* renamed from: com.google.android.gms.people.internal.api.AutocompleteImpl.1.1 */
        class AnonymousClass1 implements AutocompleteResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public AutocompleteBuffer getAutocompleteEntries() {
                return null;
            }

            public void release() {
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass1(GoogleApiClient x0, String str, AutocompleteOptions autocompleteOptions) {
            this.val$query = str;
            this.val$options = autocompleteOptions;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.loadAutocompleteList(this, this.val$query, this.val$options);
        }

        protected AutocompleteResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    public AutocompleteSession beginAutocompleteSession(GoogleApiClient googleApiClient, ClientConfig clientConfig, String account, AutocompletionListener autocompletionListener) {
        return new AutocompleteSessionImpl(googleApiClient, clientConfig, account, autocompletionListener);
    }

    public PendingResult<AutocompleteResult> loadAutocompleteList(GoogleApiClient googleApiClient, String query, AutocompleteOptions options) {
        Preconditions.checkNotNull(options);
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadAutocompleteList", query, options);
        }
        return googleApiClient.enqueue(new AnonymousClass1(googleApiClient, query, options));
    }
}
