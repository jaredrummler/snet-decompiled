package com.google.android.gms.people.model;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface Owner extends Freezable<Owner> {
    Owner freeze();

    @Nonnull
    @Deprecated
    String getAccountGaiaId();

    @Nullable
    @VisibleForTesting
    String getAccountId();

    @Nonnull
    @VisibleForTesting
    String getAccountName();

    @Nullable
    @VisibleForTesting
    String getAvatarUrl();

    @VisibleForTesting
    int getCoverPhotoHeight();

    @Nullable
    @VisibleForTesting
    String getCoverPhotoId();

    @Nullable
    @VisibleForTesting
    String getCoverPhotoUrl();

    @VisibleForTesting
    int getCoverPhotoWidth();

    @VisibleForTesting
    String getDasherDomain();

    @Nullable
    @VisibleForTesting
    String getDisplayName();

    @VisibleForTesting
    long getLastSuccessfulSyncFinishTimestamp();

    @VisibleForTesting
    long getLastSyncFinishTimestamp();

    @VisibleForTesting
    long getLastSyncStartTimestamp();

    @VisibleForTesting
    int getLastSyncStatus();

    @Deprecated
    @Nullable
    String getPlusPageGaiaId();

    @Nullable
    @VisibleForTesting
    String getPlusPageId();

    @Nonnull
    long getRowId();

    @VisibleForTesting
    int isDasherAccount();

    @VisibleForTesting
    boolean isPeriodicSyncEnabled();

    @VisibleForTesting
    boolean isPlusEnabled();

    @VisibleForTesting
    boolean isPlusPage();

    @VisibleForTesting
    boolean isSyncCirclesToContactsEnabled();

    @VisibleForTesting
    boolean isSyncEnabled();

    @VisibleForTesting
    boolean isSyncEvergreenToContactsEnabled();

    @VisibleForTesting
    boolean isSyncMeToContactsEnabled();

    @Deprecated
    @VisibleForTesting
    boolean isSyncToContactsEnabled();
}
