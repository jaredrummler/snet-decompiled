package com.google.android.gms.people.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.internal.PhoneNumberEntryRef.PhoneNumberHolderColumns;
import com.google.android.gms.people.model.AutocompleteBuffer;
import com.google.android.gms.people.model.AutocompleteEntry;
import com.google.android.gms.people.model.AvatarReference;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.PresenceColumns;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AutocompleteEntryRef extends DataBufferRef implements AutocompleteEntry {
    private final AutocompleteBuffer mBuffer;
    private final Bundle mMetadata;

    public interface AcHolderColumns {
        public static final String[] ALL;
        public static final String AVATAR_LOCATION = "avatar_location";
        public static final String AVATAR_SOURCE = "avatar_source";
        public static final String CONTACT_ID = "contact_id";
        public static final String CONTAINER_TYPE = "container_type";
        public static final String CP2_CONTACT_ID = "cp2_contact_id";
        public static final String CP2_DATA_ID = "cp2_data_id";
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String DATA_SOURCE = "data_source";
        public static final String DIRECTORY_ACCOUNT = "directory_account";
        public static final String DIRECTORY_ACCOUNT_TYPE = "directory_account_type";
        public static final String DISPLAY_NAME = "display_name";
        public static final String GAIA_ID = "gaia_id";
        public static final String ITEM_AFFINITY_1 = "item_affinity1";
        public static final String ITEM_AFFINITY_2 = "item_affinity2";
        public static final String ITEM_AFFINITY_3 = "item_affinity3";
        public static final String ITEM_AFFINITY_4 = "item_affinity4";
        public static final String ITEM_AFFINITY_5 = "item_affinity5";
        public static final String ITEM_AFFINITY_SORTED = "item_affinity_sorted";
        public static final String ITEM_LOGGING_ID_1 = "item_logging_id1";
        public static final String ITEM_LOGGING_ID_2 = "item_logging_id2";
        public static final String ITEM_LOGGING_ID_3 = "item_logging_id3";
        public static final String ITEM_LOGGING_ID_4 = "item_logging_id4";
        public static final String ITEM_LOGGING_ID_5 = "item_logging_id5";
        public static final String ITEM_LOGGING_ID_SORTED = "item_logging_id_sorted";
        public static final String ITEM_TYPE = "item_type";
        public static final String MATCH_TYPE = "match_type";
        public static final String NICKNAME = "nickname";
        public static final String OWNER_ACCOUNT = "owner_account";
        public static final String OWNER_PAGE_ID = "owner_page_id";
        public static final String PEOPLE_V2_ID = "people_v2_id";
        public static final String PERSON_AFFINITY_1 = "person_affinity1";
        public static final String PERSON_AFFINITY_2 = "person_affinity2";
        public static final String PERSON_AFFINITY_3 = "person_affinity3";
        public static final String PERSON_AFFINITY_4 = "person_affinity4";
        public static final String PERSON_AFFINITY_5 = "person_affinity5";
        public static final String PERSON_AFFINITY_SORTED = "person_affinity_sorted";
        public static final String PERSON_KEY = "person_key";
        public static final String PERSON_LOGGING_ID_1 = "person_logging_id1";
        public static final String PERSON_LOGGING_ID_2 = "person_logging_id2";
        public static final String PERSON_LOGGING_ID_3 = "person_logging_id3";
        public static final String PERSON_LOGGING_ID_4 = "person_logging_id4";
        public static final String PERSON_LOGGING_ID_5 = "person_logging_id5";
        public static final String PERSON_LOGGING_ID_SORTED = "person_logging_id_sorted";
        public static final String PRIMARY_AFFINITY_SORTED = "primary_affinity_sorted";
        public static final String PRIMARY_LOGGING_ID_SORTED = "primary_logging_id_sorted";
        public static final String PROFILE_TYPE = "profile_type";
        public static final String VALUE = "value";
        public static final String VALUE2 = "value2";
        public static final String VALUE_TYPE = "value_type";

        static {
            ALL = new String[]{OWNER_ACCOUNT, OWNER_PAGE_ID, DIRECTORY_ACCOUNT, DIRECTORY_ACCOUNT_TYPE, PERSON_KEY, PEOPLE_V2_ID, DATA_SOURCE, CONTAINER_TYPE, PROFILE_TYPE, GAIA_ID, CONTACT_ID, AVATAR_SOURCE, AVATAR_LOCATION, DISPLAY_NAME, NICKNAME, PRIMARY_AFFINITY_SORTED, PRIMARY_LOGGING_ID_SORTED, PERSON_AFFINITY_SORTED, PERSON_AFFINITY_1, PERSON_AFFINITY_2, PERSON_AFFINITY_3, PERSON_AFFINITY_4, PERSON_AFFINITY_5, PERSON_LOGGING_ID_SORTED, PERSON_LOGGING_ID_1, PERSON_LOGGING_ID_2, PERSON_LOGGING_ID_3, PERSON_LOGGING_ID_4, PERSON_LOGGING_ID_5, ITEM_AFFINITY_SORTED, ITEM_AFFINITY_1, ITEM_AFFINITY_2, ITEM_AFFINITY_3, ITEM_AFFINITY_4, ITEM_AFFINITY_5, ITEM_LOGGING_ID_SORTED, ITEM_LOGGING_ID_1, ITEM_LOGGING_ID_2, ITEM_LOGGING_ID_3, ITEM_LOGGING_ID_4, ITEM_LOGGING_ID_5, ITEM_TYPE, MATCH_TYPE, VALUE, VALUE2, VALUE_TYPE, CUSTOM_LABEL, CP2_CONTACT_ID, CP2_DATA_ID};
        }
    }

    public AutocompleteEntryRef(AutocompleteBuffer buffer, DataHolder holder, int dataRow, Bundle metadata) {
        super(holder, dataRow);
        this.mMetadata = metadata;
        this.mBuffer = buffer;
    }

    public int getRowIndex() {
        return getDataRow();
    }

    @Nullable
    public String getOwnerAccountName() {
        return getString(AcHolderColumns.OWNER_ACCOUNT);
    }

    @Nullable
    public String getOwnerPlusPageId() {
        return getString(AcHolderColumns.OWNER_PAGE_ID);
    }

    @Nullable
    public String getDirectoryAccountName() {
        return getString(AcHolderColumns.DIRECTORY_ACCOUNT);
    }

    @Nullable
    public String getDirectoryAccountType() {
        return getString(AcHolderColumns.DIRECTORY_ACCOUNT_TYPE);
    }

    public int getDataSource() {
        return getInteger(AcHolderColumns.DATA_SOURCE);
    }

    public int getMatchType() {
        return getInteger(AcHolderColumns.MATCH_TYPE);
    }

    @Nonnull
    public String getPersonKey() {
        return getString(AcHolderColumns.PERSON_KEY);
    }

    @Nullable
    public String getPeopleV2Id() {
        return getString(AcHolderColumns.PEOPLE_V2_ID);
    }

    @Nullable
    public String getGaiaId() {
        return getString(AcHolderColumns.GAIA_ID);
    }

    @Nullable
    public String getFocusContactId() {
        return getString(PresenceColumns.CONTACT_ID);
    }

    public long getAndroidContactId() {
        return getLong(AcHolderColumns.CP2_CONTACT_ID);
    }

    public long getAndroidContactDataId() {
        return getLong(AcHolderColumns.CP2_DATA_ID);
    }

    @Nullable
    public String getPersonDisplayName() {
        return getString(PhoneNumberHolderColumns.CONTACT_NAME);
    }

    @Nonnull
    public String getItemValue() {
        return getString(AccountSettingsColumns.VALUE);
    }

    @Nonnull
    public int getItemValueType() {
        return getInteger(AcHolderColumns.VALUE_TYPE);
    }

    @Nullable
    public String getItemValueCustomLabel() {
        return getString(AcHolderColumns.CUSTOM_LABEL);
    }

    @Nullable
    public AvatarReference getAvatarReference() {
        String location = getString(AcHolderColumns.AVATAR_LOCATION);
        if (TextUtils.isEmpty(location)) {
            return null;
        }
        return new AvatarReference(getInteger(AcHolderColumns.AVATAR_SOURCE), location);
    }

    public double getPrimarySortedAffinity() {
        return getDouble(AcHolderColumns.PRIMARY_AFFINITY_SORTED);
    }

    public String getPrimarySortedLoggingId() {
        return getString(AcHolderColumns.PRIMARY_LOGGING_ID_SORTED);
    }

    public double getSortedPersonAffinity() {
        return getDouble(AcHolderColumns.PERSON_AFFINITY_SORTED);
    }

    public double getPersonAffinity1() {
        return getDouble(AcHolderColumns.PERSON_AFFINITY_1);
    }

    public double getPersonAffinity2() {
        return getDouble(AcHolderColumns.PERSON_AFFINITY_2);
    }

    public double getPersonAffinity3() {
        return getDouble(AcHolderColumns.PERSON_AFFINITY_3);
    }

    public double getPersonAffinity4() {
        return getDouble(AcHolderColumns.PERSON_AFFINITY_4);
    }

    public double getPersonAffinity5() {
        return getDouble(AcHolderColumns.PERSON_AFFINITY_5);
    }

    public String getSortedPersonLoggingId() {
        return getString(AcHolderColumns.PERSON_LOGGING_ID_SORTED);
    }

    public String getPersonLoggingId1() {
        return getString(AcHolderColumns.PERSON_LOGGING_ID_1);
    }

    public String getPersonLoggingId2() {
        return getString(AcHolderColumns.PERSON_LOGGING_ID_2);
    }

    public String getPersonLoggingId3() {
        return getString(AcHolderColumns.PERSON_LOGGING_ID_3);
    }

    public String getPersonLoggingId4() {
        return getString(AcHolderColumns.PERSON_LOGGING_ID_4);
    }

    public String getPersonLoggingId5() {
        return getString(AcHolderColumns.PERSON_LOGGING_ID_5);
    }

    public double getSortedItemAffinity() {
        return getDouble(AcHolderColumns.ITEM_AFFINITY_SORTED);
    }

    public double getItemAffinity1() {
        return getDouble(AcHolderColumns.ITEM_AFFINITY_1);
    }

    public double getItemAffinity2() {
        return getDouble(AcHolderColumns.ITEM_AFFINITY_2);
    }

    public double getItemAffinity3() {
        return getDouble(AcHolderColumns.ITEM_AFFINITY_3);
    }

    public double getItemAffinity4() {
        return getDouble(AcHolderColumns.ITEM_AFFINITY_4);
    }

    public double getItemAffinity5() {
        return getDouble(AcHolderColumns.ITEM_AFFINITY_5);
    }

    public String getSortedItemLoggingId() {
        return getString(AcHolderColumns.ITEM_LOGGING_ID_SORTED);
    }

    public String getItemLoggingId1() {
        return getString(AcHolderColumns.ITEM_LOGGING_ID_1);
    }

    public String getItemLoggingId2() {
        return getString(AcHolderColumns.ITEM_LOGGING_ID_2);
    }

    public String getItemLoggingId3() {
        return getString(AcHolderColumns.ITEM_LOGGING_ID_3);
    }

    public String getItemLoggingId4() {
        return getString(AcHolderColumns.ITEM_LOGGING_ID_4);
    }

    public String getItemLoggingId5() {
        return getString(AcHolderColumns.ITEM_LOGGING_ID_5);
    }

    public int getAutocompleteItemType() {
        return getInteger(AcHolderColumns.ITEM_TYPE);
    }
}
