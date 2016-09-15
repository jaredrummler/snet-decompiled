package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;

public interface ContactGaiaId {
    @Nonnull
    @VisibleForTesting
    String getContactInfo();

    @Nonnull
    @VisibleForTesting
    String getGaiaId();

    @VisibleForTesting
    int getType();
}
