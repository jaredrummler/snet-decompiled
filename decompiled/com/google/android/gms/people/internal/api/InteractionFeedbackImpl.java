package com.google.android.gms.people.internal.api;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.people.InteractionFeedback;
import com.google.android.gms.people.People.BasePeopleSimpleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.model.AutocompleteBuffer;

public class InteractionFeedbackImpl implements InteractionFeedback {

    /* renamed from: com.google.android.gms.people.internal.api.InteractionFeedbackImpl.1 */
    class AnonymousClass1 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ String val$contactItem;
        final /* synthetic */ int val$interactionType;

        AnonymousClass1(GoogleApiClient x0, String str, int i) {
            this.val$contactItem = str;
            this.val$interactionType = i;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.sendInteractionFeedback(this, this.val$contactItem, this.val$interactionType);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.InteractionFeedbackImpl.2 */
    class AnonymousClass2 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ int val$interactionType;
        final /* synthetic */ String val$joinedContactItems;

        AnonymousClass2(GoogleApiClient x0, String str, int i) {
            this.val$joinedContactItems = str;
            this.val$interactionType = i;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.sendInteractionFeedback(this, this.val$joinedContactItems, this.val$interactionType);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.InteractionFeedbackImpl.3 */
    class AnonymousClass3 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ AutocompleteBuffer val$autocompleteBuffer;
        final /* synthetic */ int val$interactionType;
        final /* synthetic */ long val$sessionId;

        AnonymousClass3(GoogleApiClient x0, AutocompleteBuffer autocompleteBuffer, int i, long j) {
            this.val$autocompleteBuffer = autocompleteBuffer;
            this.val$interactionType = i;
            this.val$sessionId = j;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.sendAutocompleteFeedback(this, this.val$autocompleteBuffer, -1, this.val$interactionType, this.val$sessionId);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.InteractionFeedbackImpl.4 */
    class AnonymousClass4 extends BasePeopleSimpleApiMethodImpl {
        final /* synthetic */ AutocompleteBuffer val$autocompleteBuffer;
        final /* synthetic */ int val$interactionType;
        final /* synthetic */ int val$selectedIndex;
        final /* synthetic */ long val$sessionId;

        AnonymousClass4(GoogleApiClient x0, AutocompleteBuffer autocompleteBuffer, int i, int i2, long j) {
            this.val$autocompleteBuffer = autocompleteBuffer;
            this.val$selectedIndex = i;
            this.val$interactionType = i2;
            this.val$sessionId = j;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) throws RemoteException {
            clientImpl.sendAutocompleteFeedback(this, this.val$autocompleteBuffer, this.val$selectedIndex, this.val$interactionType, this.val$sessionId);
        }
    }

    public PendingResult<Result> sendFeedback(GoogleApiClient googleApiClient, String contactItem, int interactionType) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("sendFeedback", contactItem, Integer.valueOf(interactionType));
        }
        return googleApiClient.execute(new AnonymousClass1(googleApiClient, contactItem, interactionType));
    }

    public PendingResult<Result> sendFeedback(GoogleApiClient googleApiClient, String[] contactItems, int interactionType) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("sendFeedback", contactItems, Integer.valueOf(interactionType));
        }
        return googleApiClient.execute(new AnonymousClass2(googleApiClient, TextUtils.join(PeopleUtils.SEP_1_STR, contactItems), interactionType));
    }

    public PendingResult<Result> sendAutocompleteShownFeedback(GoogleApiClient client, AutocompleteBuffer autocompleteBuffer, int interactionType) {
        return sendAutocompleteShownFeedback(client, autocompleteBuffer, interactionType, 0);
    }

    public PendingResult<Result> sendAutocompleteShownFeedback(GoogleApiClient client, AutocompleteBuffer autocompleteBuffer, int interactionType, long sessionId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("sendAutocompleteShownFeedback", autocompleteBuffer, Integer.valueOf(interactionType), Long.valueOf(sessionId));
        }
        return client.execute(new AnonymousClass3(client, autocompleteBuffer, interactionType, sessionId));
    }

    public PendingResult<Result> sendAutocompleteSelectedFeedback(GoogleApiClient client, AutocompleteBuffer autocompleteBuffer, int selectedIndex, int interactionType) {
        return sendAutocompleteSelectedFeedback(client, autocompleteBuffer, selectedIndex, interactionType, 0);
    }

    public PendingResult<Result> sendAutocompleteSelectedFeedback(GoogleApiClient client, AutocompleteBuffer autocompleteBuffer, int selectedIndex, int interactionType, long sessionId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("sendAutocompleteSelectedFeedback", autocompleteBuffer, Integer.valueOf(interactionType), Long.valueOf(sessionId));
        }
        return client.execute(new AnonymousClass4(client, autocompleteBuffer, selectedIndex, interactionType, sessionId));
    }
}
