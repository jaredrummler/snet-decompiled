package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface AggregatedPerson extends Person {
    @Nonnull
    @Deprecated
    String getAccountName();

    String getAvatarUrl();

    Iterable<Long> getContactIds();

    String getFamilyName();

    String getGaiaId();

    String getGivenName();

    String getName();

    String getOwnerAccountName();

    String getOwnerPlusPageId();

    @Deprecated
    @Nullable
    String getPlusPageGaiaId();

    String getQualifiedId();

    long getRowId();

    boolean hasContact();

    boolean hasPlusPerson();
}
