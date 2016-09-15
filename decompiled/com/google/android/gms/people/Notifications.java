package com.google.android.gms.people;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface Notifications {

    @VisibleForTesting
    public interface OnDataChanged {
        void onDataChanged(@Nullable String str, @Nullable String str2, int i);
    }

    @VisibleForTesting
    PendingResult<Result> registerOnDataChangedListenerForAllOwners(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged onDataChanged, int i);

    @VisibleForTesting
    PendingResult<Result> registerOnDataChangedListenerForOwner(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged onDataChanged, @Nonnull String str, @Nullable String str2, int i);

    @VisibleForTesting
    PendingResult<Result> unregisterOnDataChangedListener(@Nonnull GoogleApiClient googleApiClient, @Nonnull OnDataChanged onDataChanged);
}
