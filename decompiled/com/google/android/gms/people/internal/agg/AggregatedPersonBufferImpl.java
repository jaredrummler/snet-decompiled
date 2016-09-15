package com.google.android.gms.people.internal.agg;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.People;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gms.people.PeopleConstants.TempGaiaToOrdinal;
import com.google.android.gms.people.identity.internal.ContactDataUtil;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.MultiIntArray;
import com.google.android.gms.people.internal.PeopleClientFifeImageUrlDecompressor;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.PhoneDecoder;
import com.google.android.gms.people.model.AggregatedPerson;
import com.google.android.gms.people.model.AggregatedPersonBuffer;
import com.google.android.gms.people.model.EmailAddress;
import com.google.android.gms.people.model.PhoneNumber;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class AggregatedPersonBufferImpl extends AggregatedPersonBuffer {
    private static final String TAG = "PeopleAggregator";
    private MultiIntArray mContactPos;
    private Cursor mContactsCursor;
    private Context mContext;
    private LabelCache mEmailLabelCache;
    private ArrayList<String> mGaiaIds;
    private final boolean mIncludeProfilePhones;
    private HashMap<String, String> mInfoToGaiaMap;
    private MultiIntArray mPeoplePos;
    private PhoneDecoder mPhoneDecoder;
    private LabelCache mPhoneLabelCache;
    private DataHolder mPlusHolder;
    private volatile boolean mReleased;
    private final int mTotalCount;

    private static abstract class LabelCache {
        private final ConcurrentHashMap<Integer, String> mCache;
        private final Resources mResources;

        protected abstract String getFromResource(Resources resources, int i);

        public LabelCache(Resources resources) {
            this.mCache = new ConcurrentHashMap();
            this.mResources = resources;
        }

        public String getLabel(int type) {
            if (type == 0) {
                return null;
            }
            String label = (String) this.mCache.get(Integer.valueOf(type));
            if (label != null) {
                return label;
            }
            label = getFromResource(this.mResources, type);
            this.mCache.put(Integer.valueOf(type), label);
            return label;
        }
    }

    /* renamed from: com.google.android.gms.people.internal.agg.AggregatedPersonBufferImpl.1 */
    class AnonymousClass1 extends LabelCache {
        AnonymousClass1(Resources x0) {
            super(x0);
        }

        protected String getFromResource(Resources resource, int type) {
            return (String) Email.getTypeLabel(resource, type, null);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.agg.AggregatedPersonBufferImpl.2 */
    class AnonymousClass2 extends LabelCache {
        AnonymousClass2(Resources x0) {
            super(x0);
        }

        protected String getFromResource(Resources resource, int type) {
            return (String) Phone.getTypeLabel(resource, type, null);
        }
    }

    private class AggregatedPersonImpl implements AggregatedPerson {
        private ArrayList<Long> mContactIds;
        private boolean mDetailsLoaded;
        private ArrayList<EmailAddress> mEmails;
        private EmailAddress mGaiaEmail;
        private final boolean mHasGaiaId;
        private ArrayList<PhoneNumber> mPhones;
        private final int mPosition;

        public AggregatedPersonImpl(int position) {
            this.mPosition = position;
            this.mHasGaiaId = !TextUtils.isEmpty(getGaiaId());
        }

        private int getPlusPersonCount() {
            return AggregatedPersonBufferImpl.this.mPeoplePos.getValueCount(this.mPosition);
        }

        private int getContactCount() {
            return AggregatedPersonBufferImpl.this.mContactPos.getValueCount(this.mPosition);
        }

        public boolean hasPlusPerson() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPlusPersonCount() > 0;
        }

        public boolean hasContact() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getContactCount() > 0;
        }

        public long getRowId() {
            throw new UnsupportedOperationException();
        }

        public String getOwnerAccountName() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return hasPlusPerson() ? AggregatedPersonBufferImpl.this.mPlusHolder.getMetadata().getString(OutgoingRmqColumns.ACCOUNT_ID) : null;
        }

        public String getOwnerPlusPageId() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return hasPlusPerson() ? AggregatedPersonBufferImpl.this.mPlusHolder.getMetadata().getString(BundleKeys.PAGE_GAIA_ID) : null;
        }

        @Nonnull
        @Deprecated
        public String getAccountName() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getOwnerAccountName();
        }

        @Deprecated
        @Nullable
        public String getPlusPageGaiaId() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getOwnerPlusPageId();
        }

        public String getName() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            if (hasPlusPerson()) {
                return getPeopleString(AccountSettingsColumns.NAME);
            }
            AggregatedPersonBufferImpl.this.mContactsCursor.moveToPosition(AggregatedPersonBufferImpl.this.mContactPos.get(this.mPosition, 0));
            return AggregatedPersonBufferImpl.this.mContactsCursor.getString(1);
        }

        public String getGivenName() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(People.GIVEN_NAME);
        }

        public String getFamilyName() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(People.FAMILY_NAME);
        }

        private String getPeopleString(String column) {
            if (!hasPlusPerson()) {
                return null;
            }
            int p = AggregatedPersonBufferImpl.this.mPeoplePos.get(this.mPosition, 0);
            return AggregatedPersonBufferImpl.this.mPlusHolder.getString(column, p, AggregatedPersonBufferImpl.this.mPlusHolder.getWindowIndex(p));
        }

        private long getPeopleLong(String column) {
            if (!hasPlusPerson()) {
                return 0;
            }
            int p = AggregatedPersonBufferImpl.this.mPeoplePos.get(this.mPosition, 0);
            return AggregatedPersonBufferImpl.this.mPlusHolder.getLong(column, p, AggregatedPersonBufferImpl.this.mPlusHolder.getWindowIndex(p));
        }

        private int getPeopleInteger(String column) {
            if (!hasPlusPerson()) {
                return 0;
            }
            int p = AggregatedPersonBufferImpl.this.mPeoplePos.get(this.mPosition, 0);
            return AggregatedPersonBufferImpl.this.mPlusHolder.getInteger(column, p, AggregatedPersonBufferImpl.this.mPlusHolder.getWindowIndex(p));
        }

        private double getPeopleDouble(String column) {
            if (!hasPlusPerson()) {
                return 0.0d;
            }
            int p = AggregatedPersonBufferImpl.this.mPeoplePos.get(this.mPosition, 0);
            return AggregatedPersonBufferImpl.this.mPlusHolder.getDouble(column, p, AggregatedPersonBufferImpl.this.mPlusHolder.getWindowIndex(p));
        }

        public String getQualifiedId() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(TempGaiaToOrdinal.QUALIFIED_ID);
        }

        public String getAvatarUrl() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return PeopleClientFifeImageUrlDecompressor.INSTANCE.decompress(getPeopleString(People.COMPRESSED_AVATAR_URL));
        }

        public String getGaiaId() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return (String) AggregatedPersonBufferImpl.this.mGaiaIds.get(this.mPosition);
        }

        public Iterable<Long> getContactIds() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            loadDetails();
            return this.mContactIds;
        }

        public long getLastModifiedTime() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleLong(People.LAST_MODIFIED_TIME);
        }

        public boolean isNameVerified() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleInteger(People.NAME_VERIFIED) != 0;
        }

        public boolean isBlocked() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleInteger(Metadata.BLOCKED) != 0;
        }

        public int getInViewerDomain() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleInteger(People.IN_VIEWER_DOMAIN);
        }

        public String getNameSortKey() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(People.SORT_KEY);
        }

        public String getInteractionRankSortKey() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(People.INTERACTION_RANK_SORT_KEY);
        }

        public int getProfileType() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleInteger(AcHolderColumns.PROFILE_TYPE);
        }

        public double getAffinity1() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleDouble(PeopleEmail.AFFINITY_1);
        }

        public double getAffinity2() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleDouble(PeopleEmail.AFFINITY_2);
        }

        public double getAffinity3() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleDouble(PeopleEmail.AFFINITY_3);
        }

        public double getAffinity4() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleDouble(PeopleEmail.AFFINITY_4);
        }

        public double getAffinity5() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleDouble(PeopleEmail.AFFINITY_5);
        }

        public String getLoggingId1() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(PeopleEmail.LOGGING_ID_1);
        }

        public String getLoggingId2() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(Partner.LOGGING_ID2);
        }

        public String getLoggingId3() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(PeopleEmail.LOGGING_ID_3);
        }

        public String getLoggingId4() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(PeopleEmail.LOGGING_ID_4);
        }

        public String getLoggingId5() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return getPeopleString(PeopleEmail.LOGGING_ID_5);
        }

        public String[] getPeopleInCommon() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return PeopleUtils.commaSplit(getPeopleString(People.PEOPLE_IN_COMMON));
        }

        public String[] getBelongingCircleIds() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            return PeopleUtils.commaSplit(getPeopleString(People.VIRTUAL_BELONGING_CIRCLE_IDS));
        }

        private Iterable<EmailAddress> buildSingleEmailAddress(EmailAddress email) {
            ArrayList<EmailAddress> ret = new ArrayList(1);
            ret.add(email);
            return ret;
        }

        public Iterable<EmailAddress> getEmailAddresses() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            String emailFromQualifiedId = PeopleUtils.peopleQualifiedIdToEmailAddress(getQualifiedId());
            if (!TextUtils.isEmpty(emailFromQualifiedId)) {
                return buildSingleEmailAddress(new EmailAddressImpl(BuildConfig.VERSION_NAME, emailFromQualifiedId));
            }
            loadDetails();
            if (this.mHasGaiaId) {
                if (this.mGaiaEmail != null) {
                    return buildSingleEmailAddress(this.mGaiaEmail);
                }
                return EmailAddress.EMPTY_EMAILS;
            } else if (hasContact()) {
                return this.mEmails;
            } else {
                if (PeopleServiceLog.canDebugLog()) {
                    PeopleServiceLog.d(AggregatedPersonBufferImpl.TAG, "Row should have a contact: " + getQualifiedId());
                }
                return EmailAddress.EMPTY_EMAILS;
            }
        }

        public Iterable<PhoneNumber> getPhoneNumbers() {
            AggregatedPersonBufferImpl.this.checkNotReleased();
            if (PeopleUtils.isQualifiedIdFromEmail(getQualifiedId())) {
                return PhoneNumber.EMPTY_PHONES;
            }
            loadDetails();
            return this.mPhones;
        }

        private void loadDetails() {
            if (!this.mDetailsLoaded) {
                this.mDetailsLoaded = true;
                int contactCount = getContactCount();
                this.mContactIds = new ArrayList(contactCount);
                this.mEmails = new ArrayList();
                this.mPhones = null;
                if (hasPlusPerson() && AggregatedPersonBufferImpl.this.mIncludeProfilePhones) {
                    this.mPhones = AggregatedPersonBufferImpl.this.mPhoneDecoder.decode(getPeopleString(People.VIRTUAL_PHONES), false);
                }
                if (this.mPhones == null) {
                    this.mPhones = new ArrayList();
                }
                this.mGaiaEmail = null;
                String gaia = getGaiaId();
                for (int i = 0; i < contactCount; i++) {
                    if (AggregatedPersonBufferImpl.this.mContactsCursor.moveToPosition(AggregatedPersonBufferImpl.this.mContactPos.get(this.mPosition, i))) {
                        this.mContactIds.add(Long.valueOf(AggregatedPersonBufferImpl.this.mContactsCursor.getLong(0)));
                        do {
                            String mimetype = AggregatedPersonBufferImpl.this.mContactsCursor.getString(2);
                            String label;
                            if (ContactDataUtil.MIMETYPE_EMAIL.equals(mimetype) && this.mGaiaEmail == null) {
                                label = getLabelFromCursorPosition(AggregatedPersonBufferImpl.this.mContactsCursor, AggregatedPersonBufferImpl.this.mEmailLabelCache);
                                String email = AggregatedPersonBufferImpl.this.mContactsCursor.getString(3);
                                if (!TextUtils.isEmpty(email)) {
                                    EmailAddress emailAddress = new EmailAddressImpl(label, email);
                                    if (!this.mEmails.contains(emailAddress)) {
                                        if (gaia != null && AggregatedPersonBufferImpl.this.mInfoToGaiaMap.containsKey(emailAddress.getValue()) && gaia.equals(AggregatedPersonBufferImpl.this.mInfoToGaiaMap.get(emailAddress.getValue()))) {
                                            this.mGaiaEmail = emailAddress;
                                            this.mEmails.clear();
                                        } else {
                                            this.mEmails.add(emailAddress);
                                        }
                                    }
                                }
                            } else if (ContactDataUtil.MIMETYPE_PHONE.equals(mimetype)) {
                                label = getLabelFromCursorPosition(AggregatedPersonBufferImpl.this.mContactsCursor, AggregatedPersonBufferImpl.this.mPhoneLabelCache);
                                String phone = AggregatedPersonBufferImpl.this.mContactsCursor.getString(3);
                                if (!TextUtils.isEmpty(phone)) {
                                    PhoneNumber phoneNumber = new PhoneNumberImpl(label, phone);
                                    if (!this.mPhones.contains(phoneNumber)) {
                                        this.mPhones.add(phoneNumber);
                                    }
                                }
                            }
                        } while (AggregationQueries.advanceToNextData(AggregatedPersonBufferImpl.this.mContactsCursor));
                    }
                }
            }
        }

        private String getLabelFromCursorPosition(Cursor cursor, LabelCache cache) {
            int labelType = AggregatedPersonBufferImpl.this.mContactsCursor.getInt(4);
            if (labelType == 0) {
                return cursor.getString(5);
            }
            return cache.getLabel(labelType);
        }

        private AggregatedPersonBufferImpl getParent() {
            return AggregatedPersonBufferImpl.this;
        }

        public boolean equals(Object o) {
            if (!(o instanceof AggregatedPersonImpl)) {
                return false;
            }
            AggregatedPersonImpl that = (AggregatedPersonImpl) o;
            if (this.mPosition == that.mPosition && getParent() == that.getParent()) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (getParent().hashCode() * 31) + this.mPosition;
        }
    }

    public void release() {
        if (!this.mReleased) {
            this.mReleased = true;
            this.mPlusHolder.close();
            this.mContactsCursor.close();
            this.mPlusHolder = null;
            this.mContactsCursor = null;
            this.mPeoplePos = null;
            this.mContactPos = null;
            this.mGaiaIds = null;
            this.mInfoToGaiaMap = null;
            this.mContext = null;
            this.mEmailLabelCache = null;
            this.mPhoneLabelCache = null;
            this.mPhoneDecoder = null;
        }
    }

    public AggregatedPersonBufferImpl(DataHolder plusHolder, Cursor contactsCursor, Context context, int totalCount, MultiIntArray resultPeoplePos, MultiIntArray resultContactPos, ArrayList<String> gaiaIds, HashMap<String, String> contactInfoToGaiaMap, int extraColumns, Bundle emailTypeMap, Bundle phoneTypeMap) {
        boolean z;
        boolean z2 = true;
        super(plusHolder);
        Preconditions.checkNotNull(plusHolder);
        Preconditions.checkNotNull(contactsCursor);
        Preconditions.checkNotNull(contactInfoToGaiaMap);
        Preconditions.checkArgument(totalCount == resultPeoplePos.size());
        if (totalCount == resultContactPos.size()) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (totalCount == gaiaIds.size()) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.mPlusHolder = plusHolder;
        this.mContactsCursor = contactsCursor;
        this.mTotalCount = totalCount;
        this.mGaiaIds = gaiaIds;
        this.mContext = context;
        this.mInfoToGaiaMap = contactInfoToGaiaMap;
        this.mEmailLabelCache = new AnonymousClass1(this.mContext.getResources());
        this.mPhoneLabelCache = new AnonymousClass2(this.mContext.getResources());
        this.mPeoplePos = resultPeoplePos;
        this.mContactPos = resultContactPos;
        if ((extraColumns & 1) != 0) {
            PeopleServiceLog.e(TAG, "PeopleExtraColumnBitmask.EMAILS is not supported in aggregation.  Ignored.");
        }
        if ((extraColumns & 2) == 0) {
            z2 = false;
        }
        this.mIncludeProfilePhones = z2;
        this.mPhoneDecoder = new PhoneDecoder(phoneTypeMap);
    }

    private void checkNotReleased() {
        if (this.mReleased) {
            throw new IllegalStateException("Already released");
        }
    }

    public int getCount() {
        checkNotReleased();
        return this.mTotalCount;
    }

    public AggregatedPerson get(int position) {
        checkNotReleased();
        return new AggregatedPersonImpl(position);
    }
}
