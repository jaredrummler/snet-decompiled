package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Circle {
    @Nonnull
    @Deprecated
    String getAccountName();

    @Nonnull
    @VisibleForTesting
    String getCircleId();

    @Nullable
    @VisibleForTesting
    String getCircleName();

    @VisibleForTesting
    int getCircleType();

    @VisibleForTesting
    long getLastModifiedTime();

    @Nonnull
    @VisibleForTesting
    String getOwnerAccountName();

    @Nullable
    @VisibleForTesting
    String getOwnerPlusPageId();

    @VisibleForTesting
    int getPeopleCount();

    @Deprecated
    @Nullable
    String getPlusPageGaiaId();

    @Nonnull
    long getRowId();

    @Nullable
    @VisibleForTesting
    String getSortKey();

    @VisibleForTesting
    int getVisibility();

    @VisibleForTesting
    boolean isEnabledForSharing();

    @VisibleForTesting
    boolean isSyncToContactsEnabled();

    @VisibleForTesting
    boolean policyCannotAclTo();

    @VisibleForTesting
    boolean policyCannotModifyMembership();

    @VisibleForTesting
    boolean policyCannotViewMembership();

    @VisibleForTesting
    boolean policyVisibleOnlyWhenPopulated();
}
