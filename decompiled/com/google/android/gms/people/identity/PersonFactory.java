package com.google.android.gms.people.identity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.Circles;
import com.google.android.gms.people.PeopleConstants.People;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gms.people.PeopleConstants.PeoplePhone;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.PeopleConstants.TempGaiaToOrdinal;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.PhoneNumberEntryRef.PhoneNumberHolderColumns;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface PersonFactory<PersonType> {

    public static class ContactData {
        private final List<RawContactData> mRawData;

        public ContactData(List<RawContactData> rawData) {
            this.mRawData = new ArrayList(rawData);
        }

        public ContactData(RawContactData... rawData) {
            this.mRawData = Arrays.asList(rawData);
        }

        public List<RawContactData> getRawData() {
            return this.mRawData;
        }
    }

    public static class ExternalContactData {
        protected final String mAccountType;
        protected final Uri mDataUri;
        protected final String mDetail;
        protected final String mHeader;
        protected final int mIconRes;
        protected final String mMimeType;
        protected final String mResourcePackageName;
        protected final int mTitleRes;

        public ExternalContactData(Uri dataUri, String header, int iconRes, String detail, String resourcePackageName, String mimeType, int titleRes, String accountType) {
            this.mDataUri = dataUri;
            this.mHeader = header;
            this.mIconRes = iconRes;
            this.mDetail = detail;
            this.mResourcePackageName = resourcePackageName;
            this.mMimeType = mimeType;
            this.mTitleRes = titleRes;
            this.mAccountType = accountType;
        }

        public Uri getDataUri() {
            return this.mDataUri;
        }

        public String getHeader() {
            return this.mHeader;
        }

        public int getIconRes() {
            return this.mIconRes;
        }

        public String getDetail() {
            return this.mDetail;
        }

        public String getResourcePackageName() {
            return this.mResourcePackageName;
        }

        public String getMimeType() {
            return this.mMimeType;
        }

        public int getTitleRes() {
            return this.mTitleRes;
        }

        public String getAccountType() {
            return this.mAccountType;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(ExternalContactData.class.getSimpleName());
            buffer.append("<dataUri=").append(this.mDataUri);
            buffer.append(" header=").append(this.mHeader);
            buffer.append(" detail=").append(this.mDetail);
            buffer.append(" resourcePackageName=").append(this.mResourcePackageName);
            buffer.append(" mimeType=").append(this.mMimeType);
            buffer.append(" titleRes=").append(this.mTitleRes);
            buffer.append(" iconRes=").append(this.mIconRes);
            buffer.append(" accountType=").append(this.mAccountType);
            buffer.append(">");
            return buffer.toString();
        }
    }

    public static abstract class OfflineDatabaseData {
        protected final VisibleDataBufferRef mRow;

        public static final class AddressData {
            private final String mAddress;
            private final String mType;

            public AddressData(String address, String type) {
                this.mAddress = address;
                this.mType = type;
            }

            public String getAddress() {
                return this.mAddress;
            }

            public String getType() {
                return this.mType;
            }
        }

        public static class Circle {
            public static final int UNKNOWN_MEMBER_COUNT = -1;
            private final VisibleDataBufferRef mRow;

            public Circle(VisibleDataBufferRef row) {
                this.mRow = row;
            }

            public String getId() {
                return this.mRow.getString(Circles.CIRCLE_ID);
            }

            public String getName() {
                return this.mRow.getString(AccountSettingsColumns.NAME);
            }

            public int getMemberCount() {
                return this.mRow.getInteger(Circles.PEOPLE_COUNT, UNKNOWN_MEMBER_COUNT);
            }
        }

        public static final class EmailData {
            private final String mEmail;
            private final String mType;

            public EmailData(String email, String type) {
                this.mEmail = email;
                this.mType = type;
            }

            public String getEmailAddress() {
                return this.mEmail;
            }

            public String getType() {
                return this.mType;
            }
        }

        public static final class PhoneData {
            private final String mPhone;
            private final String mType;

            public PhoneData(String phone, String type) {
                this.mPhone = phone;
                this.mType = type;
            }

            public String getPhone() {
                return this.mPhone;
            }

            public String getType() {
                return this.mType;
            }
        }

        protected static final class VisibleDataBufferRef extends DataBufferRef {
            public VisibleDataBufferRef(DataHolder holder, int dataRow) {
                super(holder, dataRow);
            }

            public int getInteger(String column, int defaultValue) {
                return !hasColumn(column) ? defaultValue : super.getInteger(column);
            }

            public boolean getBoolean(String column, boolean defaultValue) {
                return !hasColumn(column) ? defaultValue : super.getBoolean(column);
            }

            public String getString(String column) {
                return getString(column, null);
            }

            public String getString(String column, String defaultValue) {
                return !hasColumn(column) ? defaultValue : super.getString(column);
            }
        }

        public abstract List<AddressData> getAddresses();

        public abstract List<Circle> getCircles();

        public abstract String getCompressedAvatarUrl();

        public abstract String getDisplayName();

        public abstract List<EmailData> getEmails();

        public abstract String getGaiaId();

        public abstract boolean getNameVerified();

        public abstract List<PhoneData> getPhones();

        public abstract int getProfileType();

        public abstract String getTagline();

        public OfflineDatabaseData(VisibleDataBufferRef row) {
            this.mRow = row;
        }

        protected String getPersonString(String column) {
            return getPersonString(column, null);
        }

        protected String getPersonString(String column, String defaultValue) {
            return this.mRow.getString(column, defaultValue);
        }

        protected int getPersonInteger(String column, int defaultValue) {
            return this.mRow.getInteger(column, defaultValue);
        }

        protected boolean getPersonBoolean(String column, boolean defaultValue) {
            return this.mRow.getBoolean(column, defaultValue);
        }

        public static OfflineDatabaseData build(DataHolder personHolder, DataHolder personAddressHolder, DataHolder personEmailHolder, DataHolder personPhoneHolder, DataHolder ownerHolder, DataHolder ownerAddressHolder, DataHolder ownerEmailHolder, DataHolder ownerPhoneHolder, DataHolder circleHolder, int ordinal) {
            VisibleDataBufferRef personRow = (VisibleDataBufferRef) oneNoneOrError(findRows(personHolder, ordinal));
            if (personRow != null) {
                return new OfflineDatabasePersonData(personRow, personAddressHolder, personEmailHolder, personPhoneHolder, circleHolder, ordinal);
            }
            VisibleDataBufferRef ownerRow = (VisibleDataBufferRef) oneNoneOrError(findRows(ownerHolder, ordinal));
            if (ownerRow != null) {
                return new OfflineDatabaseOwnerData(ownerRow, ownerAddressHolder, ownerEmailHolder, ownerPhoneHolder, ordinal);
            }
            return null;
        }

        protected static ArrayList<VisibleDataBufferRef> findRows(DataHolder holder, int ordinal) {
            ArrayList<VisibleDataBufferRef> result = new ArrayList();
            if (holder != null) {
                for (int i = 0; i < holder.getCount(); i++) {
                    if (ordinal == holder.getInteger(TempGaiaToOrdinal.ORDINAL, i, holder.getWindowIndex(i))) {
                        result.add(new VisibleDataBufferRef(holder, i));
                    } else if (!result.isEmpty()) {
                        break;
                    }
                }
            }
            return result;
        }

        private static <T> T oneNoneOrError(ArrayList<T> list) {
            boolean z = true;
            if (list == null || list.isEmpty()) {
                return null;
            }
            if (list.size() != 1) {
                z = false;
            }
            Preconditions.checkState(z);
            return list.get(0);
        }
    }

    public static class OfflineDatabaseOwnerData extends OfflineDatabaseData {
        private final List<AddressData> mAddresses;
        private final List<EmailData> mEmails;
        private final List<PhoneData> mPhones;

        public OfflineDatabaseOwnerData(VisibleDataBufferRef ownerRow, DataHolder addressHolder, DataHolder emailHolder, DataHolder phoneHolder, int ordinal) {
            super(ownerRow);
            ArrayList<AddressData> addresses = new ArrayList();
            Iterator i$ = OfflineDatabaseData.findRows(addressHolder, ordinal).iterator();
            while (i$.hasNext()) {
                VisibleDataBufferRef row = (VisibleDataBufferRef) i$.next();
                addresses.add(new AddressData(row.getString(PeoplePostalAddress.POSTAL_ADDRESS), row.getString(OutgoingRmqColumns.PROTOBUF_TAG)));
            }
            ArrayList<EmailData> emails = new ArrayList();
            i$ = OfflineDatabaseData.findRows(emailHolder, ordinal).iterator();
            while (i$.hasNext()) {
                row = (VisibleDataBufferRef) i$.next();
                emails.add(new EmailData(row.getString(PeopleEmail.EMAIL_ADDRESS), row.getString(OutgoingRmqColumns.PROTOBUF_TAG)));
            }
            ArrayList<PhoneData> phones = new ArrayList();
            i$ = OfflineDatabaseData.findRows(phoneHolder, ordinal).iterator();
            while (i$.hasNext()) {
                row = (VisibleDataBufferRef) i$.next();
                phones.add(new PhoneData(row.getString(PeoplePhone.PHONE_NUMBER), row.getString(OutgoingRmqColumns.PROTOBUF_TAG)));
            }
            this.mAddresses = Collections.unmodifiableList(addresses);
            this.mEmails = Collections.unmodifiableList(emails);
            this.mPhones = Collections.unmodifiableList(phones);
        }

        public String getDisplayName() {
            return getPersonString(PhoneNumberHolderColumns.CONTACT_NAME);
        }

        public String getGaiaId() {
            return getPersonString(AcHolderColumns.GAIA_ID);
        }

        public boolean getNameVerified() {
            return false;
        }

        public String getTagline() {
            return null;
        }

        public int getProfileType() {
            return 0;
        }

        public String getCompressedAvatarUrl() {
            return null;
        }

        public List<Circle> getCircles() {
            return null;
        }

        public List<AddressData> getAddresses() {
            return this.mAddresses;
        }

        public List<PhoneData> getPhones() {
            return this.mPhones;
        }

        public List<EmailData> getEmails() {
            return this.mEmails;
        }
    }

    public static class OfflineDatabasePersonData extends OfflineDatabaseData {
        private final List<AddressData> mAddresses;
        private final List<Circle> mCircles;
        private final List<EmailData> mEmails;
        private final List<PhoneData> mPhones;

        public OfflineDatabasePersonData(VisibleDataBufferRef personRow, DataHolder addressHolder, DataHolder emailHolder, DataHolder phoneHolder, DataHolder circleHolder, int ordinal) {
            super(personRow);
            ArrayList<Circle> circles = new ArrayList();
            Iterator i$ = OfflineDatabaseData.findRows(circleHolder, ordinal).iterator();
            while (i$.hasNext()) {
                circles.add(new Circle((VisibleDataBufferRef) i$.next()));
            }
            ArrayList<AddressData> addresses = new ArrayList();
            i$ = OfflineDatabaseData.findRows(addressHolder, ordinal).iterator();
            while (i$.hasNext()) {
                VisibleDataBufferRef row = (VisibleDataBufferRef) i$.next();
                addresses.add(new AddressData(row.getString(PeoplePostalAddress.POSTAL_ADDRESS), row.getString(OutgoingRmqColumns.PROTOBUF_TAG)));
            }
            ArrayList<EmailData> emails = new ArrayList();
            i$ = OfflineDatabaseData.findRows(emailHolder, ordinal).iterator();
            while (i$.hasNext()) {
                row = (VisibleDataBufferRef) i$.next();
                emails.add(new EmailData(row.getString(PeopleEmail.EMAIL_ADDRESS), row.getString(OutgoingRmqColumns.PROTOBUF_TAG)));
            }
            ArrayList<PhoneData> phones = new ArrayList();
            i$ = OfflineDatabaseData.findRows(phoneHolder, ordinal).iterator();
            while (i$.hasNext()) {
                row = (VisibleDataBufferRef) i$.next();
                phones.add(new PhoneData(row.getString(PeoplePhone.PHONE_NUMBER), row.getString(OutgoingRmqColumns.PROTOBUF_TAG)));
            }
            this.mCircles = Collections.unmodifiableList(circles);
            this.mAddresses = Collections.unmodifiableList(addresses);
            this.mEmails = Collections.unmodifiableList(emails);
            this.mPhones = Collections.unmodifiableList(phones);
        }

        public String getDisplayName() {
            return getPersonString(AccountSettingsColumns.NAME);
        }

        public String getGaiaId() {
            return getPersonString(AcHolderColumns.GAIA_ID);
        }

        public boolean getNameVerified() {
            return getPersonBoolean(People.NAME_VERIFIED, false);
        }

        public String getTagline() {
            return getPersonString(People.TAGLINE);
        }

        public int getProfileType() {
            return getPersonInteger(AcHolderColumns.PROFILE_TYPE, -1);
        }

        public String getCompressedAvatarUrl() {
            return getPersonString(People.COMPRESSED_AVATAR_URL);
        }

        public List<Circle> getCircles() {
            return this.mCircles;
        }

        public List<AddressData> getAddresses() {
            return this.mAddresses;
        }

        public List<PhoneData> getPhones() {
            return this.mPhones;
        }

        public List<EmailData> getEmails() {
            return this.mEmails;
        }
    }

    public static class RawContactData {
        private final String mContactId;
        private final String[] mData;
        private final ExternalContactData mExternalContactData;
        private final String mMimeType;
        private final boolean mPrimary;
        private final String mRawContactId;
        private final boolean mReadOnly;
        private final int mTimesUsed;

        public RawContactData(String contactId, String rawContactId, String mimeType, int timesUsed, String[] data, boolean readOnly, boolean primary, ExternalContactData externalContactData) {
            this.mContactId = contactId;
            this.mRawContactId = rawContactId;
            this.mMimeType = mimeType;
            this.mData = data;
            this.mReadOnly = readOnly;
            this.mPrimary = primary;
            this.mExternalContactData = externalContactData;
            this.mTimesUsed = timesUsed;
        }

        public String getContactId() {
            return this.mContactId;
        }

        public String getRawContactId() {
            return this.mRawContactId;
        }

        public String getMimeType() {
            return this.mMimeType;
        }

        public int getTimesUsed() {
            return this.mTimesUsed;
        }

        @Nullable
        public String getData(int column) {
            return column < this.mData.length ? this.mData[column] : null;
        }

        public boolean isReadOnly() {
            return this.mReadOnly;
        }

        public boolean isPrimary() {
            return this.mPrimary;
        }

        public ExternalContactData getExternalContactData() {
            return this.mExternalContactData;
        }
    }

    public static class ServiceData {
        private static final ServiceData FAILED;
        public static final int FAILED_RESPONSE_CODE = -1;
        public final byte[] blob;
        public final int format;
        public final Map<String, String> headers;
        public final int responseCode;

        static {
            FAILED = new ServiceData(FAILED_RESPONSE_CODE, 0, null, null);
        }

        public ServiceData(int responseCode, int format, byte[] blob, Map<String, String> headers) {
            this.responseCode = responseCode;
            this.format = format;
            this.blob = blob;
            this.headers = headers;
        }

        public static ServiceData fromBundle(Bundle bundle) {
            if (bundle == null) {
                return FAILED;
            }
            int responseCode = bundle.getInt(BundleKeys.PEOPLE_SERVER_BLOB_RESPONSE_CODE, FAILED_RESPONSE_CODE);
            if (responseCode == FAILED_RESPONSE_CODE) {
                return FAILED;
            }
            return new ServiceData(responseCode, bundle.getInt(BundleKeys.PEOPLE_SERVER_BLOB_FORMAT), bundle.getByteArray(BundleKeys.PEOPLE_SERVER_BLOB_BODY), (HashMap) bundle.getSerializable(BundleKeys.PEOPLE_SERVER_BLOB_HEADERS));
        }
    }

    PersonType build(@Nonnull Context context, @Nonnull Object obj, @Nullable ServiceData serviceData, @Nullable ContactData contactData, @Nullable OfflineDatabaseData offlineDatabaseData);
}
