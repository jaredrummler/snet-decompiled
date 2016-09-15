package com.google.android.gms.people.exp;

import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.People;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.PeopleConstants.TempGaiaToOrdinal;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.PeopleClientFifeImageUrlDecompressor;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.EmailDecoder;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.PhoneDecoder;
import com.google.android.gms.people.model.EmailAddress;
import com.google.android.gms.people.model.Person;
import com.google.android.gms.people.model.PhoneNumber;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public class PersonForAggregationRawBuffer extends RawBuffer implements Person {
    private final EmailDecoder mEmailDecoder;
    private final boolean mEmailsWithAffinities;
    private final PhoneDecoder mPhoneDecoder;

    @VisibleForTesting
    public PersonForAggregationRawBuffer(DataHolder dataHolder, PhoneDecoder phoneDecoder, EmailDecoder emailDecoder) {
        super(dataHolder);
        this.mPhoneDecoder = phoneDecoder;
        this.mEmailDecoder = emailDecoder;
        this.mEmailsWithAffinities = dataHolder.getMetadata().getBoolean(BundleKeys.EMAILS_WITH_AFFINITIES, false);
    }

    public long getRowId() {
        return getLong(PeoplePostalAddress._ID);
    }

    @VisibleForTesting
    public String getOwnerAccountName() {
        return getMetadata().getString(OutgoingRmqColumns.ACCOUNT_ID);
    }

    @VisibleForTesting
    public String getOwnerPlusPageId() {
        return getMetadata().getString(BundleKeys.PAGE_GAIA_ID);
    }

    @Nonnull
    @Deprecated
    public String getAccountName() {
        return getOwnerAccountName();
    }

    @Deprecated
    @Nullable
    public String getPlusPageGaiaId() {
        return getOwnerPlusPageId();
    }

    @VisibleForTesting
    public String getQualifiedId() {
        return getString(TempGaiaToOrdinal.QUALIFIED_ID);
    }

    @VisibleForTesting
    public String getGaiaId() {
        return getString(AcHolderColumns.GAIA_ID);
    }

    @VisibleForTesting
    public String getName() {
        return getString(AccountSettingsColumns.NAME);
    }

    @VisibleForTesting
    public String getGivenName() {
        return getString(People.GIVEN_NAME);
    }

    @VisibleForTesting
    public String getFamilyName() {
        return getString(People.FAMILY_NAME);
    }

    @VisibleForTesting
    public String getNameSortKey() {
        return getString(People.SORT_KEY);
    }

    @VisibleForTesting
    public String getInteractionRankSortKey() {
        return getString(People.INTERACTION_RANK_SORT_KEY);
    }

    @VisibleForTesting
    public String getAvatarUrl() {
        return PeopleClientFifeImageUrlDecompressor.INSTANCE.decompress(getString(People.COMPRESSED_AVATAR_URL));
    }

    @VisibleForTesting
    public int getProfileType() {
        return getInteger(AcHolderColumns.PROFILE_TYPE);
    }

    @VisibleForTesting
    public String[] getBelongingCircleIds() {
        String ids = getString(People.VIRTUAL_BELONGING_CIRCLE_IDS);
        return TextUtils.isEmpty(ids) ? PeopleUtils.EMPTY_STRINGS : PeopleUtils.REGEX_COMMA.split(ids, -1);
    }

    @VisibleForTesting
    public boolean isBlocked() {
        return getInteger(Metadata.BLOCKED) != 0;
    }

    @VisibleForTesting
    public int getInViewerDomain() {
        return getInteger(People.IN_VIEWER_DOMAIN);
    }

    @VisibleForTesting
    public long getLastModifiedTime() {
        return getLong(People.LAST_MODIFIED_TIME);
    }

    @VisibleForTesting
    public boolean isNameVerified() {
        return getInteger(People.NAME_VERIFIED) != 0;
    }

    public double getAffinity1() {
        return getDouble(PeopleEmail.AFFINITY_1);
    }

    public double getAffinity2() {
        return getDouble(PeopleEmail.AFFINITY_2);
    }

    public double getAffinity3() {
        return getDouble(PeopleEmail.AFFINITY_3);
    }

    public double getAffinity4() {
        return getDouble(PeopleEmail.AFFINITY_4);
    }

    public double getAffinity5() {
        return getDouble(PeopleEmail.AFFINITY_5);
    }

    public String getLoggingId1() {
        return getString(PeopleEmail.LOGGING_ID_1);
    }

    public String getLoggingId2() {
        return getString(Partner.LOGGING_ID2);
    }

    public String getLoggingId3() {
        return getString(PeopleEmail.LOGGING_ID_3);
    }

    public String getLoggingId4() {
        return getString(PeopleEmail.LOGGING_ID_4);
    }

    public String getLoggingId5() {
        return getString(PeopleEmail.LOGGING_ID_5);
    }

    public String[] getPeopleInCommon() {
        return PeopleUtils.commaSplit(getString(People.PEOPLE_IN_COMMON));
    }

    @Nullable
    @VisibleForTesting
    public String getEncodedEmails() {
        return getString(People.VIRTUAL_EMAILS);
    }

    @Nullable
    @VisibleForTesting
    public String getEncodedPhones() {
        return getString(People.VIRTUAL_PHONES);
    }

    @Nonnull
    @VisibleForTesting
    public Iterable<EmailAddress> getEmailAddresses() {
        return this.mEmailDecoder.decode(getEncodedEmails(), this.mEmailsWithAffinities);
    }

    @Nonnull
    @VisibleForTesting
    public Iterable<PhoneNumber> getPhoneNumbers() {
        return this.mPhoneDecoder.decode(getEncodedPhones(), false);
    }
}
