package com.google.android.gms.people.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.People;
import com.google.android.gms.people.PeopleConstants.PeopleEmail;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.PeopleConstants.TempGaiaToOrdinal;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Metadata;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.EmailDecoder;
import com.google.android.gms.people.internal.agg.PhoneEmailDecoder.PhoneDecoder;
import com.google.android.gms.people.model.EmailAddress;
import com.google.android.gms.people.model.Person;
import com.google.android.gms.people.model.PhoneNumber;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import com.google.android.snet.Csv;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class PersonRef extends DataBufferRef implements Person {
    public static final String[] ALL_COLUMNS;
    private final EmailDecoder mEmailDecoder;
    private final boolean mEmailsWithAffinities;
    private final Bundle mMetadata;
    private final PhoneDecoder mPhoneDecoder;

    static {
        ALL_COLUMNS = new String[]{PeoplePostalAddress._ID, TempGaiaToOrdinal.QUALIFIED_ID, AcHolderColumns.GAIA_ID, AccountSettingsColumns.NAME, People.SORT_KEY, People.INTERACTION_RANK_SORT_KEY, People.COMPRESSED_AVATAR_URL, AcHolderColumns.PROFILE_TYPE, People.VIRTUAL_BELONGING_CIRCLE_IDS, Metadata.BLOCKED, People.IN_VIEWER_DOMAIN, People.LAST_MODIFIED_TIME, People.NAME_VERIFIED, People.GIVEN_NAME, People.FAMILY_NAME, PeopleEmail.AFFINITY_1, PeopleEmail.AFFINITY_2, PeopleEmail.AFFINITY_3, PeopleEmail.AFFINITY_4, PeopleEmail.AFFINITY_5, People.PEOPLE_IN_COMMON, People.VIRTUAL_EMAILS, People.VIRTUAL_PHONES};
    }

    public static HashMap<String, Object> toMap(long id, String qualifiedId, String gaiaId, String displayName, String sortKey, String interactionRankSortKey, String compressedAvatarUrl, int profileType, String[] belongingCircleIds, boolean blocked, int inViewerDomain, long lastModifiedTime, boolean isNameVerified, String givenName, String familyName, double affinity1, double affinity2, double affinity3, double affinity4, double affinity5, String[] peopleInCommon, String virtualEmails, String virtualPhones) {
        HashMap<String, Object> map = new HashMap();
        map.put(PeoplePostalAddress._ID, Long.valueOf(id));
        map.put(TempGaiaToOrdinal.QUALIFIED_ID, qualifiedId);
        map.put(AcHolderColumns.GAIA_ID, gaiaId);
        map.put(AccountSettingsColumns.NAME, displayName);
        map.put(People.GIVEN_NAME, givenName);
        map.put(People.FAMILY_NAME, familyName);
        map.put(People.SORT_KEY, sortKey);
        map.put(People.INTERACTION_RANK_SORT_KEY, interactionRankSortKey);
        map.put(People.COMPRESSED_AVATAR_URL, compressedAvatarUrl);
        map.put(AcHolderColumns.PROFILE_TYPE, Integer.valueOf(profileType));
        map.put(People.VIRTUAL_BELONGING_CIRCLE_IDS, belongingCircleIds == null ? null : TextUtils.join(Csv.COMMA, belongingCircleIds));
        map.put(Metadata.BLOCKED, Integer.valueOf(blocked ? 1 : 0));
        map.put(People.NAME_VERIFIED, Integer.valueOf(isNameVerified ? 1 : 0));
        map.put(People.IN_VIEWER_DOMAIN, Integer.valueOf(inViewerDomain));
        map.put(People.LAST_MODIFIED_TIME, Long.valueOf(lastModifiedTime));
        map.put(PeopleEmail.AFFINITY_1, Double.valueOf(affinity1));
        map.put(PeopleEmail.AFFINITY_2, Double.valueOf(affinity2));
        map.put(PeopleEmail.AFFINITY_3, Double.valueOf(affinity3));
        map.put(PeopleEmail.AFFINITY_4, Double.valueOf(affinity4));
        map.put(PeopleEmail.AFFINITY_5, Double.valueOf(affinity5));
        map.put(People.PEOPLE_IN_COMMON, peopleInCommon);
        map.put(People.VIRTUAL_EMAILS, virtualEmails);
        map.put(People.VIRTUAL_EMAILS, virtualPhones);
        return map;
    }

    public PersonRef(DataHolder holder, int dataRow, Bundle metadata, PhoneDecoder phoneDecoder, EmailDecoder emailDecoder) {
        super(holder, dataRow);
        this.mMetadata = metadata;
        this.mPhoneDecoder = phoneDecoder;
        this.mEmailDecoder = emailDecoder;
        this.mEmailsWithAffinities = this.mMetadata.getBoolean(BundleKeys.EMAILS_WITH_AFFINITIES, false);
    }

    public long getRowId() {
        return getLong(PeoplePostalAddress._ID);
    }

    public String getOwnerAccountName() {
        return this.mMetadata.getString(OutgoingRmqColumns.ACCOUNT_ID);
    }

    public String getOwnerPlusPageId() {
        return this.mMetadata.getString(BundleKeys.PAGE_GAIA_ID);
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

    public String getQualifiedId() {
        return getString(TempGaiaToOrdinal.QUALIFIED_ID);
    }

    public String getGaiaId() {
        return getString(AcHolderColumns.GAIA_ID);
    }

    public String getName() {
        return getString(AccountSettingsColumns.NAME);
    }

    public String getGivenName() {
        return getString(People.GIVEN_NAME);
    }

    public String getFamilyName() {
        return getString(People.FAMILY_NAME);
    }

    public String getNameSortKey() {
        return getString(People.SORT_KEY);
    }

    public String getInteractionRankSortKey() {
        return getString(People.INTERACTION_RANK_SORT_KEY);
    }

    public String getAvatarUrl() {
        return PeopleClientFifeImageUrlDecompressor.INSTANCE.decompress(getString(People.COMPRESSED_AVATAR_URL));
    }

    public int getProfileType() {
        return getInteger(AcHolderColumns.PROFILE_TYPE);
    }

    public String[] getBelongingCircleIds() {
        String ids = getString(People.VIRTUAL_BELONGING_CIRCLE_IDS);
        return TextUtils.isEmpty(ids) ? PeopleUtils.EMPTY_STRINGS : PeopleUtils.REGEX_COMMA.split(ids, -1);
    }

    public boolean isBlocked() {
        return getInteger(Metadata.BLOCKED) != 0;
    }

    public int getInViewerDomain() {
        return getInteger(People.IN_VIEWER_DOMAIN);
    }

    public long getLastModifiedTime() {
        return getLong(People.LAST_MODIFIED_TIME);
    }

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

    public Iterable<EmailAddress> getEmailAddresses() {
        return this.mEmailDecoder.decode(getString(People.VIRTUAL_EMAILS), this.mEmailsWithAffinities);
    }

    public Iterable<PhoneNumber> getPhoneNumbers() {
        return this.mPhoneDecoder.decode(getString(People.VIRTUAL_PHONES), false);
    }
}
