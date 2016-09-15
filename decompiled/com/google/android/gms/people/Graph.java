package com.google.android.gms.people;

import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.People.ReleasableResult;
import com.google.android.gms.people.PeopleConstants.CircleTypes;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.android.gms.people.exp.ContactGaiaIdRawBuffer;
import com.google.android.gms.people.exp.PersonForAggregationRawBuffer;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.model.AggregatedPersonBuffer;
import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.ContactGaiaIdBuffer;
import com.google.android.gms.people.model.OwnerBuffer;
import com.google.android.gms.people.model.PersonBuffer;
import com.google.android.gms.people.model.PhoneNumberBuffer;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface Graph {

    @VisibleForTesting
    public static class LoadAggregatedPeopleOptions {
        public static final LoadAggregatedPeopleOptions DEFAULT;
        private int mExtraColumns;
        private int mFilterGaiaEdgeTypes;
        private String mFilterGaiaId;
        private boolean mIncludeEvergreenPeople;
        private boolean mIncludeInvisible;
        private boolean mPeopleOnly;
        private int mProjection;
        private String mQuery;
        private int mSearchFields;
        private int mSortOrder;

        public LoadAggregatedPeopleOptions() {
            this.mProjection = PeopleColumnBitmask.ALL;
            this.mSearchFields = 7;
            this.mFilterGaiaEdgeTypes = 3;
            this.mSortOrder = 0;
        }

        static {
            DEFAULT = new LoadAggregatedPeopleOptions();
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("mIncludeInvisible", Boolean.valueOf(this.mIncludeInvisible), "mQuery", this.mQuery, "mPeopleOnly", Boolean.valueOf(this.mPeopleOnly), "mProjection", Integer.valueOf(this.mProjection), "mExtraColumns", Integer.valueOf(this.mExtraColumns), "mFilterGaiaId", this.mFilterGaiaId, "mIncludeEvergreenPeople", Boolean.valueOf(this.mIncludeEvergreenPeople), "mSearchFields", Integer.valueOf(this.mSearchFields), "mFilterGaiaEdgeTypes", Integer.valueOf(this.mFilterGaiaEdgeTypes), "mSortOrder", Integer.valueOf(this.mSortOrder));
        }

        public boolean isIncludeInvisible() {
            return this.mIncludeInvisible;
        }

        public LoadAggregatedPeopleOptions setIncludeInvisible(boolean includeInvisible) {
            this.mIncludeInvisible = includeInvisible;
            return this;
        }

        public String getQuery() {
            return this.mQuery;
        }

        public LoadAggregatedPeopleOptions setQuery(String query) {
            this.mQuery = query;
            return this;
        }

        public boolean isPeopleOnly() {
            return this.mPeopleOnly;
        }

        public LoadAggregatedPeopleOptions setPeopleOnly(boolean peopleOnly) {
            this.mPeopleOnly = peopleOnly;
            return this;
        }

        public int getProjection() {
            return this.mProjection;
        }

        public LoadAggregatedPeopleOptions setProjection(int projection) {
            this.mProjection = projection;
            return this;
        }

        public int getExtraColumns() {
            return this.mExtraColumns;
        }

        public LoadAggregatedPeopleOptions setExtraColumns(int extraColumns) {
            this.mExtraColumns = extraColumns;
            return this;
        }

        public String getFilterGaiaId() {
            return this.mFilterGaiaId;
        }

        public LoadAggregatedPeopleOptions setFilterGaiaId(String filterGaiaId) {
            this.mFilterGaiaId = filterGaiaId;
            return this;
        }

        public boolean isIncludeEvergreenPeople() {
            return this.mIncludeEvergreenPeople;
        }

        public LoadAggregatedPeopleOptions setIncludeEvergreenPeople(boolean includeEvergreenPeople) {
            this.mIncludeEvergreenPeople = includeEvergreenPeople;
            return this;
        }

        public LoadAggregatedPeopleOptions setSearchFields(int searchFields) {
            this.mSearchFields = searchFields;
            return this;
        }

        public int getSearchFields() {
            return this.mSearchFields;
        }

        public LoadAggregatedPeopleOptions setFilterGaiaEdgeType(int filterGaiaEdgeTypes) {
            this.mFilterGaiaEdgeTypes = filterGaiaEdgeTypes;
            return this;
        }

        public int getFilterGaiaEdgeTypes() {
            return this.mFilterGaiaEdgeTypes;
        }

        public LoadAggregatedPeopleOptions setSortOrder(int sortOrder) {
            this.mSortOrder = sortOrder;
            return this;
        }

        public int getSortOrder() {
            return this.mSortOrder;
        }
    }

    @VisibleForTesting
    public interface LoadAggregatedPeopleResult extends ReleasableResult {
        AggregatedPersonBuffer getAggregatedPeople();
    }

    @VisibleForTesting
    public static class LoadCirclesOptions {
        public static final LoadCirclesOptions DEFAULT;
        private String mFilterCircleId;
        private String mFilterCircleNamePrefix;
        private int mFilterCircleType;
        private boolean mGetVisibility;

        public LoadCirclesOptions() {
            this.mFilterCircleType = CircleTypes.ALL;
        }

        static {
            DEFAULT = new LoadCirclesOptions();
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("mFilterCircleType", Integer.valueOf(this.mFilterCircleType), "mFilterCircleId", this.mFilterCircleId, "mFilterCircleNamePrefix", this.mFilterCircleNamePrefix, "mGetVisibility", Boolean.valueOf(this.mGetVisibility));
        }

        public String getFilterCircleId() {
            return this.mFilterCircleId;
        }

        public LoadCirclesOptions setFilterCircleId(String filterCircleId) {
            this.mFilterCircleId = filterCircleId;
            return this;
        }

        public String getFilterCircleNamePrefix() {
            return this.mFilterCircleNamePrefix;
        }

        public LoadCirclesOptions setFilterCircleNamePrefix(String filterCircleNamePrefix) {
            this.mFilterCircleNamePrefix = filterCircleNamePrefix;
            return this;
        }

        public boolean isGetVisibility() {
            return this.mGetVisibility;
        }

        public LoadCirclesOptions setGetVisibility(boolean getVisibility) {
            this.mGetVisibility = getVisibility;
            return this;
        }

        public int getFilterCircleType() {
            return this.mFilterCircleType;
        }

        public LoadCirclesOptions setFilterCircleType(int filterCircleType) {
            this.mFilterCircleType = filterCircleType;
            return this;
        }
    }

    @VisibleForTesting
    public interface LoadCirclesResult extends ReleasableResult {
        CircleBuffer getCircles();
    }

    @VisibleForTesting
    public static class LoadContactsGaiaIdsOptions {
        public static final LoadContactsGaiaIdsOptions DEFAULT;
        private String mFilterContactInfo;
        private int mFilterGaiaEdgeTypes;
        private String mFilterGaiaId;

        public LoadContactsGaiaIdsOptions() {
            this.mFilterGaiaEdgeTypes = 3;
        }

        static {
            DEFAULT = new LoadContactsGaiaIdsOptions();
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("mFilterContactInfo", this.mFilterContactInfo, "mFilterGaiaId", this.mFilterGaiaId, "mFilterGaiaEdgeTypes", Integer.valueOf(this.mFilterGaiaEdgeTypes));
        }

        public String getFilterContactInfo() {
            return this.mFilterContactInfo;
        }

        public LoadContactsGaiaIdsOptions setFilterContactInfo(String filterContactInfo) {
            this.mFilterContactInfo = filterContactInfo;
            return this;
        }

        public String getFilterGaiaId() {
            return this.mFilterGaiaId;
        }

        public LoadContactsGaiaIdsOptions setFilterGaiaId(String filterGaiaId) {
            this.mFilterGaiaId = filterGaiaId;
            return this;
        }

        public int getFilterGaiaEdgeTypes() {
            return this.mFilterGaiaEdgeTypes;
        }

        public LoadContactsGaiaIdsOptions setFilterGaiaEdgeTypes(int filterGaiaEdgeTypes) {
            this.mFilterGaiaEdgeTypes = filterGaiaEdgeTypes;
            return this;
        }
    }

    @VisibleForTesting
    public interface LoadContactsGaiaIdsResult extends ReleasableResult {
        ContactGaiaIdBuffer getContactsGaiaIds();
    }

    public interface LoadMeResult extends Result {
        Bundle getBundle();
    }

    @VisibleForTesting
    public static class LoadOwnersOptions {
        public static final LoadOwnersOptions DEFAULT;
        private boolean mIncludePlusPages;
        private int mSortOrder;

        public LoadOwnersOptions() {
            this.mSortOrder = 0;
        }

        static {
            DEFAULT = new LoadOwnersOptions();
        }

        public boolean isIncludePlusPages() {
            return this.mIncludePlusPages;
        }

        public LoadOwnersOptions setIncludePlusPages(boolean includePlusPages) {
            this.mIncludePlusPages = includePlusPages;
            return this;
        }

        public int getSortOrder() {
            return this.mSortOrder;
        }

        public LoadOwnersOptions setSortOrder(int sortOrder) {
            this.mSortOrder = sortOrder;
            return this;
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("mIncludePlusPages", Boolean.valueOf(this.mIncludePlusPages), "mSortOrder", Integer.valueOf(this.mSortOrder));
        }
    }

    @VisibleForTesting
    public interface LoadOwnersResult extends ReleasableResult {
        OwnerBuffer getOwners();
    }

    @VisibleForTesting
    public interface LoadPeopleForAggregationResult extends ReleasableResult {
        Bundle getEmailTypeMapBundle();

        ContactGaiaIdRawBuffer getGaiaMap();

        PersonForAggregationRawBuffer getPeople();

        Bundle getPhoneTypeMapBundle();
    }

    @VisibleForTesting
    public static class LoadPeopleOptions {
        public static final LoadPeopleOptions DEFAULT;
        private long mChangedSince;
        private String mCircleId;
        private int mExtraColumns;
        private boolean mPeopleOnly;
        private int mProjection;
        private Collection<String> mQualifiedIds;
        private String mQuery;
        private int mSearchFields;
        private int mSortOrder;

        public LoadPeopleOptions() {
            this.mProjection = PeopleColumnBitmask.ALL;
            this.mSearchFields = 7;
            this.mSortOrder = 0;
        }

        static {
            DEFAULT = new LoadPeopleOptions();
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("mCircleId", this.mCircleId, "mQualifiedIds", this.mQualifiedIds, "mProjection", Integer.valueOf(this.mProjection), "mPeopleOnly", Boolean.valueOf(this.mPeopleOnly), "mChangedSince", Long.valueOf(this.mChangedSince), "mQuery", this.mQuery, "mSearchFields", Integer.valueOf(this.mSearchFields), "mSortOrder", Integer.valueOf(this.mSortOrder), "mExtraColumns", Integer.valueOf(this.mExtraColumns));
        }

        public LoadPeopleOptions setCircleId(@Nullable String circleId) {
            this.mCircleId = circleId;
            return this;
        }

        public LoadPeopleOptions setQualifiedIds(@Nullable Collection<String> qualifiedIds) {
            this.mQualifiedIds = qualifiedIds;
            return this;
        }

        public LoadPeopleOptions setProjection(int projection) {
            this.mProjection = projection;
            return this;
        }

        public LoadPeopleOptions setPeopleOnly(boolean peopleOnly) {
            this.mPeopleOnly = peopleOnly;
            return this;
        }

        public LoadPeopleOptions setChangedSince(long changedSince) {
            this.mChangedSince = changedSince;
            return this;
        }

        public LoadPeopleOptions setQuery(String query) {
            this.mQuery = query;
            return this;
        }

        public LoadPeopleOptions setSearchFields(int searchFields) {
            this.mSearchFields = searchFields;
            return this;
        }

        public LoadPeopleOptions setSortOrder(int sortOrder) {
            this.mSortOrder = sortOrder;
            return this;
        }

        public LoadPeopleOptions setExtraColumns(int extraColumns) {
            this.mExtraColumns = extraColumns;
            return this;
        }

        public String getCircleId() {
            return this.mCircleId;
        }

        public Collection<String> getQualifiedIds() {
            return this.mQualifiedIds;
        }

        public int getProjection() {
            return this.mProjection;
        }

        public boolean isPeopleOnly() {
            return this.mPeopleOnly;
        }

        public long getChangedSince() {
            return this.mChangedSince;
        }

        public String getQuery() {
            return this.mQuery;
        }

        public int getSearchFields() {
            return this.mSearchFields;
        }

        public int getSortOrder() {
            return this.mSortOrder;
        }

        public int getExtraColumns() {
            return this.mExtraColumns;
        }
    }

    @VisibleForTesting
    public interface LoadPeopleResult extends ReleasableResult {
        PersonBuffer getPeople();
    }

    @VisibleForTesting
    public interface LoadPhoneNumbersResult extends ReleasableResult {
        PhoneNumberBuffer getPhoneNumbers();
    }

    @VisibleForTesting
    PendingResult<LoadPeopleForAggregationResult> expLoadPeopleForAggregation(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nullable LoadAggregatedPeopleOptions loadAggregatedPeopleOptions);

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    PendingResult<LoadAggregatedPeopleResult> loadAggregatedPeople(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nullable LoadAggregatedPeopleOptions loadAggregatedPeopleOptions);

    @VisibleForTesting
    PendingResult<LoadCirclesResult> loadCircles(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nullable LoadCirclesOptions loadCirclesOptions);

    @VisibleForTesting
    PendingResult<LoadContactsGaiaIdsResult> loadContactsGaiaIds(@Nonnull GoogleApiClient googleApiClient, @Nullable LoadContactsGaiaIdsOptions loadContactsGaiaIdsOptions);

    PendingResult<LoadMeResult> loadMe(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);

    @VisibleForTesting
    PendingResult<LoadOwnersResult> loadOwner(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);

    @VisibleForTesting
    PendingResult<LoadOwnersResult> loadOwners(@Nonnull GoogleApiClient googleApiClient, @Nullable LoadOwnersOptions loadOwnersOptions);

    @VisibleForTesting
    PendingResult<LoadPeopleResult> loadPeople(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nullable LoadPeopleOptions loadPeopleOptions);

    @VisibleForTesting
    PendingResult<LoadPhoneNumbersResult> loadPhoneNumbers(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable Bundle bundle);
}
