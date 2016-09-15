package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface Person extends Affinities {
    @Nonnull
    @Deprecated
    String getAccountName();

    double getAffinity1();

    double getAffinity2();

    double getAffinity3();

    double getAffinity4();

    double getAffinity5();

    @Nullable
    String getAvatarUrl();

    @Nonnull
    String[] getBelongingCircleIds();

    Iterable<EmailAddress> getEmailAddresses();

    @Nullable
    String getFamilyName();

    @Nullable
    String getGaiaId();

    @Nullable
    String getGivenName();

    int getInViewerDomain();

    @Nullable
    String getInteractionRankSortKey();

    long getLastModifiedTime();

    @Nullable
    String getName();

    @Nullable
    String getNameSortKey();

    @Nonnull
    String getOwnerAccountName();

    @Nullable
    String getOwnerPlusPageId();

    String[] getPeopleInCommon();

    Iterable<PhoneNumber> getPhoneNumbers();

    @Deprecated
    @Nullable
    String getPlusPageGaiaId();

    int getProfileType();

    @Nonnull
    String getQualifiedId();

    long getRowId();

    boolean isBlocked();

    boolean isNameVerified();
}
