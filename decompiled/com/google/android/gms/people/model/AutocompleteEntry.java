package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface AutocompleteEntry {
    long getAndroidContactDataId();

    long getAndroidContactId();

    int getAutocompleteItemType();

    @Nullable
    AvatarReference getAvatarReference();

    int getDataSource();

    @Nullable
    String getDirectoryAccountName();

    @Nullable
    String getDirectoryAccountType();

    @Nullable
    String getFocusContactId();

    @Nullable
    String getGaiaId();

    double getItemAffinity1();

    double getItemAffinity2();

    double getItemAffinity3();

    double getItemAffinity4();

    double getItemAffinity5();

    String getItemLoggingId1();

    String getItemLoggingId2();

    String getItemLoggingId3();

    String getItemLoggingId4();

    String getItemLoggingId5();

    @Nonnull
    String getItemValue();

    @Nullable
    String getItemValueCustomLabel();

    @Nonnull
    int getItemValueType();

    int getMatchType();

    @Nullable
    String getOwnerAccountName();

    @Nullable
    String getOwnerPlusPageId();

    @Nullable
    String getPeopleV2Id();

    double getPersonAffinity1();

    double getPersonAffinity2();

    double getPersonAffinity3();

    double getPersonAffinity4();

    double getPersonAffinity5();

    @Nullable
    String getPersonDisplayName();

    @Nonnull
    String getPersonKey();

    String getPersonLoggingId1();

    String getPersonLoggingId2();

    String getPersonLoggingId3();

    String getPersonLoggingId4();

    String getPersonLoggingId5();

    double getPrimarySortedAffinity();

    String getPrimarySortedLoggingId();

    int getRowIndex();

    double getSortedItemAffinity();

    String getSortedItemLoggingId();

    double getSortedPersonAffinity();

    String getSortedPersonLoggingId();
}
