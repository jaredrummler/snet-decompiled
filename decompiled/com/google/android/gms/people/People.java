package com.google.android.gms.people;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.IdentityApi;
import com.google.android.gms.people.identity.internal.IdentityApiImpl;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.internal.api.AutocompleteImpl;
import com.google.android.gms.people.internal.api.ContactsSyncImpl;
import com.google.android.gms.people.internal.api.GraphImpl;
import com.google.android.gms.people.internal.api.GraphUpdateImpl;
import com.google.android.gms.people.internal.api.ImagesImpl;
import com.google.android.gms.people.internal.api.InteractionFeedbackImpl;
import com.google.android.gms.people.internal.api.InternalApiImpl;
import com.google.android.gms.people.internal.api.NotificationsImpl;
import com.google.android.gms.people.internal.api.SyncImpl;

@VisibleForTesting
public final class People {
    public static final Api<PeopleOptions1p> API_1P;
    public static final Autocomplete AutocompleteApi;
    @VisibleForTesting
    static final AbstractClientBuilder<PeopleClientImpl, PeopleOptions1p> CLIENT_BUILDER_1P;
    @VisibleForTesting
    public static final ClientKey<PeopleClientImpl> CLIENT_KEY_1P;
    public static final ContactsSync ContactsSyncApi;
    public static final Graph GraphApi;
    public static final GraphUpdate GraphUpdateApi;
    public static final IdentityApi IdentityApi;
    public static final Images ImageApi;
    public static final InteractionFeedback InteractionFeedbackApi;
    public static final InternalApi InternalApi;
    public static final Notifications NotificationApi;
    public static final int STATUS_INCOMPLETE = 100;
    public static final int STATUS_NOT_ALLOWED = 101;
    public static final Sync SyncApi;
    public static final String TAG = "PeopleClient";

    @VisibleForTesting
    public interface ReleasableResult extends Result, Releasable {
    }

    public static abstract class BasePeopleApiMethodImpl<R extends Result> extends ApiMethodImpl<R, PeopleClientImpl> {
        public BasePeopleApiMethodImpl(GoogleApiClient googleApiClient) {
            super(People.CLIENT_KEY_1P, googleApiClient);
        }
    }

    public static abstract class BasePeopleSimpleApiMethodImpl extends BasePeopleApiMethodImpl<Result> {
        public BasePeopleSimpleApiMethodImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        protected Result createFailedResult(Status status) {
            return status;
        }
    }

    public interface BundleResult extends ReleasableResult {
        Bundle getBundle();
    }

    @VisibleForTesting
    public static final class PeopleOptions1p implements HasOptions {
        private final int clientApplicationId;

        @VisibleForTesting
        public static final class Builder {
            int clientApplicationId;

            public Builder() {
                this.clientApplicationId = -1;
            }

            @VisibleForTesting
            public Builder setClientApplicationId(int clientApplicationId) {
                this.clientApplicationId = clientApplicationId;
                return this;
            }

            @VisibleForTesting
            public PeopleOptions1p build() {
                Preconditions.checkArgument(this.clientApplicationId >= 0, "Must provide valid client application ID!");
                return new PeopleOptions1p();
            }
        }

        private PeopleOptions1p(Builder builder) {
            this.clientApplicationId = builder.clientApplicationId;
        }

        public static Builder builder() {
            return new Builder();
        }
    }

    private People() {
    }

    static {
        CLIENT_KEY_1P = new ClientKey();
        CLIENT_BUILDER_1P = new AbstractClientBuilder<PeopleClientImpl, PeopleOptions1p>() {
            public PeopleClientImpl buildClient(Context context, Looper looper, ClientSettings commonSettings, PeopleOptions1p peopleOptions, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
                Preconditions.checkNotNull(peopleOptions, "Must provide valid PeopleOptions!");
                return new PeopleClientImpl(context, looper, connectedListener, connectionFailedListener, String.valueOf(peopleOptions.clientApplicationId), commonSettings);
            }
        };
        API_1P = new Api("People.API_1P", CLIENT_BUILDER_1P, CLIENT_KEY_1P);
        IdentityApi = new IdentityApiImpl();
        GraphApi = new GraphImpl();
        GraphUpdateApi = new GraphUpdateImpl();
        ImageApi = new ImagesImpl();
        SyncApi = new SyncImpl();
        AutocompleteApi = new AutocompleteImpl();
        InteractionFeedbackApi = new InteractionFeedbackImpl();
        InternalApi = new InternalApiImpl();
        ContactsSyncApi = new ContactsSyncImpl();
        NotificationApi = new NotificationsImpl();
    }
}
