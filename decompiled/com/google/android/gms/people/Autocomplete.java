package com.google.android.gms.people;

import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.People.ReleasableResult;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.model.AutocompleteBuffer;
import com.google.android.gms.people.model.ContactGroupPreferredFieldsBuffer;
import com.google.android.gsf.GoogleLoginServiceConstants;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@VisibleForTesting
public interface Autocomplete {
    public static final int CLIENT_ID_ANDROID_UPDATING_MERGED_CACHE = 3;
    public static final int CLIENT_ID_CONTACTS_PLUS = 1;
    public static final int CLIENT_ID_DEFAULT = 0;
    public static final int CLIENT_ID_GMAIL_ANDROID_COMPOSE = 2;
    public static final int FIELD_TYPE_PERSON_EMAIL = 2;
    public static final int FIELD_TYPE_PERSON_NAME = 1;
    public static final int FIELD_TYPE_PERSON_NICKNAME = 3;
    public static final int FIELD_TYPE_PERSON_ORGANIZATION_DOMAIN = 7;
    public static final int FIELD_TYPE_PERSON_ORGANIZATION_NAME = 6;
    public static final int FIELD_TYPE_PERSON_PHONE = 4;
    public static final int FIELD_TYPE_PERSON_PHONE_CANONICALIZED_FORM = 5;
    public static final int FIELD_TYPE_UNSPECIFIED = 0;
    public static final int OBJECT_TYPE_COMMUNITY = 3;
    public static final int OBJECT_TYPE_CONTACT_GROUP = 5;
    public static final int OBJECT_TYPE_GOOGLE_GROUP = 4;
    public static final int OBJECT_TYPE_PERSON = 1;
    public static final int OBJECT_TYPE_PLUS_PAGE = 2;
    public static final int OBJECT_TYPE_UNSPECIFIED = 0;

    @VisibleForTesting
    public static final class AutocompleteOptions {
        public static final int DEFAULT_NUMBER_OF_RESULTS = 10;
        public final String account;
        public final int autocompleteType;
        public final String directoryAccountType;
        public final boolean isDirectorySearch;
        public final int numberOfResults;
        public final String pageId;
        public final int searchOptions;
        public final boolean useAndroidContactFallback;

        @VisibleForTesting
        public static final class Builder {
            private String mAccount;
            private int mAutocompleteType;
            private String mDirectoryAccountType;
            private boolean mIsDirectorySearch;
            private int mNumberOfResults;
            private String mPageId;
            private int mSearchOptions;
            private boolean mUseAndroidContactFallback;

            public Builder() {
                this.mDirectoryAccountType = GoogleLoginServiceConstants.ACCOUNT_TYPE;
                this.mAutocompleteType = Autocomplete.FIELD_TYPE_UNSPECIFIED;
                this.mNumberOfResults = AutocompleteOptions.DEFAULT_NUMBER_OF_RESULTS;
                this.mUseAndroidContactFallback = true;
            }

            public Builder setIsDirectorySearch(boolean isDirectorySearch) {
                this.mIsDirectorySearch = isDirectorySearch;
                return this;
            }

            public Builder setAccount(String account) {
                this.mAccount = account;
                return this;
            }

            public Builder setPageId(String pageId) {
                this.mPageId = pageId;
                return this;
            }

            public Builder setDirectoryAccountType(String directoryAccountType) {
                this.mDirectoryAccountType = directoryAccountType;
                return this;
            }

            public Builder setAutocompleteType(int autocompleteType) {
                this.mAutocompleteType = autocompleteType;
                return this;
            }

            public Builder setSearchOptions(int searchOptions) {
                this.mSearchOptions = searchOptions;
                return this;
            }

            public Builder setNumberOfResults(int numberOfResults) {
                this.mNumberOfResults = numberOfResults;
                return this;
            }

            public Builder setUseAndroidContactFallback(boolean useAndroidContactFallback) {
                this.mUseAndroidContactFallback = useAndroidContactFallback;
                return this;
            }

            public AutocompleteOptions build() {
                return new AutocompleteOptions();
            }
        }

        private AutocompleteOptions(Builder b) {
            this.isDirectorySearch = b.mIsDirectorySearch;
            this.directoryAccountType = b.mDirectoryAccountType;
            this.account = b.mAccount;
            this.pageId = b.mPageId;
            this.autocompleteType = b.mAutocompleteType;
            this.searchOptions = b.mSearchOptions;
            this.numberOfResults = b.mNumberOfResults;
            this.useAndroidContactFallback = b.mUseAndroidContactFallback;
        }

        public String toString() {
            return PeopleCallLog.toStringHelper("isDirectorySearch", Boolean.valueOf(this.isDirectorySearch), "directoryAccountType", this.directoryAccountType, OutgoingRmqColumns.ACCOUNT_ID, this.account, "pageId", this.pageId, "autocompleteType", Integer.valueOf(this.autocompleteType), "searchOptions", Integer.valueOf(this.searchOptions), "numberOfResults", Integer.valueOf(this.numberOfResults), "useAndroidContactFallback", Boolean.valueOf(this.useAndroidContactFallback));
        }
    }

    @VisibleForTesting
    public interface AutocompleteResult extends ReleasableResult {
        AutocompleteBuffer getAutocompleteEntries();
    }

    public interface AutocompleteSession {
        void adjustQuery(GoogleApiClient googleApiClient, String str);

        void close(GoogleApiClient googleApiClient);

        PendingResult<PreferredFieldsResult> getAllPreferredFields(GoogleApiClient googleApiClient, ContactGroup contactGroup);

        PendingResult<LoadPhotoResult> loadPrimaryPhoto(GoogleApiClient googleApiClient, Person person, LoadPhotoOptions loadPhotoOptions);

        void reportDisplay(GoogleApiClient googleApiClient, Autocompletion autocompletion);

        void reportSelection(GoogleApiClient googleApiClient, Autocompletion autocompletion);

        void reportSubmissionSave(GoogleApiClient googleApiClient, Autocompletion autocompletion, String[] strArr);

        void reportSubmissionSend(GoogleApiClient googleApiClient, Autocompletion autocompletion, String[] strArr);

        void startNewQuery(GoogleApiClient googleApiClient);
    }

    public interface Autocompletion {
        ContactGroup getContactGroup();

        DisplayableField[] getMatches();

        int getObjectType();

        Person getPerson();
    }

    public interface AutocompletionListener {
        void onAutocompletionsAvailable(Autocompletion[] autocompletionArr, int i, int i2);
    }

    public static final class ClientConfig {
        public final int clientId;

        public ClientConfig(int clientId) {
            this.clientId = clientId;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClientId {
    }

    public interface ContactGroup {
        GroupExtendedData getExtendedData();

        ContactGroupId getId();

        int getMemberCount();

        ContactGroupName getName();
    }

    public interface ContactGroupId {
        String getId();

        String[] getLegacyId();
    }

    public interface ContactGroupName {
        CharSequence getFormattedValue();

        CharSequence getValue();
    }

    public interface ContactPreferredFields extends Freezable<ContactPreferredFields> {
        CharSequence getEmail();

        CharSequence getName();
    }

    public interface DisplayableField {
        int getIndex();

        Substring[] getMatchingSubstrings();

        int getType();

        CharSequence getValue();
    }

    public interface Email {
        CharSequence getValue();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FieldType {
    }

    public interface GroupExtendedData {
        ContactPreferredFields[] getContactPreferences();
    }

    public static final class LoadPhotoOptions {
        private final int mImageSize;
        private final int mPhotoOptions;

        public LoadPhotoOptions(int imageSize, int photoOptions) {
            this.mImageSize = imageSize;
            this.mPhotoOptions = photoOptions;
        }

        public int getImageSize() {
            return this.mImageSize;
        }

        public int getPhotoOptions() {
            return this.mPhotoOptions;
        }
    }

    public static class LoadPhotoResult implements ReleasableResult {
        public static final LoadPhotoResult FAILED_RESULT;
        final int mHeight;
        final boolean mIsRewindable;
        final ParcelFileDescriptor mParcelFileDescriptor;
        final Status mStatus;
        final int mWidth;

        static {
            FAILED_RESULT = new LoadPhotoResult(new Status(13), null, false, Autocomplete.FIELD_TYPE_UNSPECIFIED, Autocomplete.FIELD_TYPE_UNSPECIFIED);
        }

        public LoadPhotoResult(Status status, ParcelFileDescriptor parcelFileDescriptor, boolean isRewindable, int width, int height) {
            this.mStatus = status;
            this.mParcelFileDescriptor = parcelFileDescriptor;
            this.mIsRewindable = isRewindable;
            this.mWidth = width;
            this.mHeight = height;
        }

        public Status getStatus() {
            return this.mStatus;
        }

        public ParcelFileDescriptor getParcelFileDescriptor() {
            return this.mParcelFileDescriptor;
        }

        public boolean isRewindable() {
            return this.mIsRewindable;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public void release() {
        }
    }

    public interface Name {
        CharSequence getDisplayName();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ObjectType {
    }

    public interface Person {
        Email[] getEmails();

        PersonMetadata getMetadata();

        Name[] getNames();

        Phone[] getPhones();

        Photo[] getPhotos();
    }

    public interface PersonMetadata {
        String getOwnerId();
    }

    public interface Phone {
        CharSequence getValue();
    }

    public interface Photo {
        boolean isDefault();
    }

    public interface PreferredFieldsResult extends ReleasableResult {
        ContactGroupPreferredFieldsBuffer getPreferredFields();
    }

    public interface Substring {
        int getLength();

        int getStartIndex();
    }

    AutocompleteSession beginAutocompleteSession(GoogleApiClient googleApiClient, ClientConfig clientConfig, String str, AutocompletionListener autocompletionListener);

    @RequiresPermission("android.permission.READ_CONTACTS")
    @VisibleForTesting
    PendingResult<AutocompleteResult> loadAutocompleteList(GoogleApiClient googleApiClient, String str, AutocompleteOptions autocompleteOptions);
}
