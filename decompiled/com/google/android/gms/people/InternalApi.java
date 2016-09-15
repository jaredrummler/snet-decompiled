package com.google.android.gms.people;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.people.Graph.LoadPeopleResult;
import com.google.android.gms.people.People.BundleResult;
import com.google.android.gms.people.internal.PeopleCallLog;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface InternalApi {

    public static class LoadPeopleForAspenOptions {
        public static final LoadPeopleForAspenOptions DEFAULT;
        private static final int DEFAULT_PAGE_SIZE = 20;
        private int mPageSize;
        private String mPageToken;
        private String mQuery;

        public LoadPeopleForAspenOptions() {
            this.mPageSize = DEFAULT_PAGE_SIZE;
        }

        static {
            DEFAULT = new LoadPeopleForAspenOptions();
        }

        public LoadPeopleForAspenOptions setQuery(String query) {
            this.mQuery = query;
            return this;
        }

        public String getQuery() {
            return this.mQuery;
        }

        public LoadPeopleForAspenOptions setPageSize(int pageSize) {
            this.mPageSize = pageSize;
            return this;
        }

        public int getPageSize() {
            return this.mPageSize;
        }

        public LoadPeopleForAspenOptions setPageToken(String pageToken) {
            this.mPageToken = pageToken;
            return this;
        }

        public String getPageToken() {
            return this.mPageToken;
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("mQuery", this.mQuery, "mPageToken", this.mPageToken, "mPageSize", Integer.valueOf(this.mPageSize));
        }
    }

    public interface LoadPeopleForAspenResult extends LoadPeopleResult {
        String getNextPageToken();
    }

    PendingResult<BundleResult> internalCall(@Nonnull GoogleApiClient googleApiClient, @Nonnull Bundle bundle);

    PendingResult<LoadPeopleForAspenResult> loadPeopleForAspen(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nullable LoadPeopleForAspenOptions loadPeopleForAspenOptions);
}
