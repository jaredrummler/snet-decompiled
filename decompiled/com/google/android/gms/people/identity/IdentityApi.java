package com.google.android.gms.people.identity;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.models.Person;
import com.google.android.gms.people.identity.models.PersonReference;

@VisibleForTesting
public interface IdentityApi {

    @VisibleForTesting
    public interface CustomPersonListResult<PersonRefType> extends Result, Releasable {
        PendingResult<CustomPersonListResult<PersonRefType>> getNextPendingResult();

        DataBuffer<PersonRefType> getPersonBuffer();

        boolean isResultComplete();

        void release();
    }

    @VisibleForTesting
    public interface CustomPersonResult<PersonType> extends Result, Releasable {
        PendingResult<CustomPersonResult<PersonType>> getNextPendingResult();

        DataBuffer<PersonType> getPersonBuffer();

        boolean isLocalResultComplete();

        boolean isResultComplete();

        void release();
    }

    @VisibleForTesting
    public static final class FirstPartyOptions {
        public final String accountName;
        public final String endpoint;
        public final Bundle endpointArguments;
        public final String pageId;

        @VisibleForTesting
        public static final class Builder {
            public String mAccountName;
            public String mEndpoint;
            public Bundle mEndpointArguments;
            public String mPageId;

            public Builder() {
                this.mEndpointArguments = new Bundle();
            }

            public static Builder fromOptions(FirstPartyOptions source) {
                return new Builder().setAccountName(source.accountName).setPageId(source.pageId).setEndpoint(source.endpoint);
            }

            public Builder setAccountName(String accountName) {
                this.mAccountName = accountName;
                return this;
            }

            public Builder setPageId(String pageId) {
                this.mPageId = pageId;
                return this;
            }

            public Builder setEndpoint(String endpoint) {
                this.mEndpoint = endpoint;
                return this;
            }

            public Builder addEndpointArgument(String key, String value) {
                this.mEndpointArguments.putString(key, value);
                return this;
            }

            public FirstPartyOptions build() {
                return new FirstPartyOptions();
            }
        }

        private FirstPartyOptions(Builder builder) {
            this.accountName = builder.mAccountName;
            this.pageId = builder.mPageId;
            this.endpoint = builder.mEndpoint;
            this.endpointArguments = builder.mEndpointArguments;
        }
    }

    @VisibleForTesting
    public static class GetOptions {
        public final FirstPartyOptions firstPartyOptions;
        public final boolean useCachedData;
        public final boolean useContactData;
        public final boolean useWebData;

        @VisibleForTesting
        public static final class Builder {
            private FirstPartyOptions mFirstPartyOptions;
            private boolean mUseCachedData;
            private boolean mUseContactData;
            private boolean mUseWebData;

            public Builder() {
                this.mFirstPartyOptions = new Builder().build();
                this.mUseCachedData = true;
                this.mUseWebData = false;
                this.mUseContactData = true;
            }

            public GetOptions build() {
                return new GetOptions();
            }

            public Builder setUseCachedData(boolean useCachedData) {
                this.mUseCachedData = useCachedData;
                return this;
            }

            public Builder setUseWebData(boolean useWebData) {
                this.mUseWebData = useWebData;
                return this;
            }

            public Builder setUseContactData(boolean useContactData) {
                this.mUseContactData = useContactData;
                return this;
            }

            public Builder setFirstPartyOptions(FirstPartyOptions firstPartyOptions) {
                this.mFirstPartyOptions = (FirstPartyOptions) Preconditions.checkNotNull(firstPartyOptions);
                return this;
            }

            public Builder setFirstPartyOptions(Builder firstPartyOptions) {
                return setFirstPartyOptions(firstPartyOptions.build());
            }
        }

        private GetOptions(Builder builder) {
            this.firstPartyOptions = builder.mFirstPartyOptions;
            this.useCachedData = builder.mUseCachedData;
            this.useWebData = builder.mUseWebData;
            this.useContactData = builder.mUseContactData;
        }
    }

    @VisibleForTesting
    public static class ListOptions {
        public final FirstPartyOptions firstPartyOptions;
        public final boolean useCachedData;
        public final boolean useContactData;
        public final boolean useWebData;

        @VisibleForTesting
        public static final class Builder {
            private FirstPartyOptions mFirstPartyOptions;
            private boolean mUseCachedData;
            private boolean mUseContactData;
            private boolean mUseWebData;

            public Builder() {
                this.mFirstPartyOptions = new Builder().build();
                this.mUseCachedData = true;
                this.mUseWebData = false;
                this.mUseContactData = true;
            }

            public ListOptions build() {
                return new ListOptions();
            }

            public static Builder fromOptions(ListOptions source) {
                return new Builder().setUseCachedData(source.useCachedData).setUseWebData(source.useWebData).setUseContactData(source.useContactData).setFirstPartyOptions(Builder.fromOptions(source.firstPartyOptions));
            }

            public Builder setUseCachedData(boolean useCachedData) {
                this.mUseCachedData = useCachedData;
                return this;
            }

            public Builder setUseWebData(boolean useWebData) {
                this.mUseWebData = useWebData;
                return this;
            }

            public Builder setUseContactData(boolean useContactData) {
                this.mUseContactData = useContactData;
                return this;
            }

            public Builder setFirstPartyOptions(FirstPartyOptions firstPartyOptions) {
                this.mFirstPartyOptions = (FirstPartyOptions) Preconditions.checkNotNull(firstPartyOptions);
                return this;
            }

            public Builder setFirstPartyOptions(Builder firstPartyOptions) {
                return setFirstPartyOptions(firstPartyOptions.build());
            }
        }

        private ListOptions(Builder builder) {
            this.firstPartyOptions = builder.mFirstPartyOptions;
            this.useCachedData = builder.mUseCachedData;
            this.useWebData = builder.mUseWebData;
            this.useContactData = builder.mUseContactData;
        }
    }

    @VisibleForTesting
    public interface PersonListResult extends Result, Releasable {
        PendingResult<PersonListResult> getNextPendingResult();

        DataBuffer<PersonReference> getPersonBuffer();

        boolean isResultComplete();

        void release();
    }

    @VisibleForTesting
    public interface PersonResult extends Result, Releasable {
        PendingResult<PersonResult> getNextPendingResult();

        DataBuffer<Person> getPersonBuffer();

        boolean isLocalResultComplete();

        boolean isResultComplete();

        void release();
    }

    <PersonType> PendingResult<CustomPersonResult<PersonType>> getByIds(GoogleApiClient googleApiClient, GetOptions getOptions, PersonFactory<PersonType> personFactory, String... strArr);

    PendingResult<PersonResult> getByIds(GoogleApiClient googleApiClient, GetOptions getOptions, String... strArr);

    PendingResult<PersonListResult> list(GoogleApiClient googleApiClient, ListOptions listOptions);

    <PersonRefType> PendingResult<CustomPersonListResult<PersonRefType>> list(GoogleApiClient googleApiClient, ListOptions listOptions, PersonListFactory<PersonRefType> personListFactory);

    PendingResult<PersonListResult> listByEmail(GoogleApiClient googleApiClient, ListOptions listOptions, String str);

    PendingResult<PersonListResult> listByPhone(GoogleApiClient googleApiClient, ListOptions listOptions, String str);
}
