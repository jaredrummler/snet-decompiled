package com.google.android.gms.people;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface Sync {
    @VisibleForTesting
    PendingResult<Result> requestSync(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);

    @VisibleForTesting
    PendingResult<Result> requestSync(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, long j);

    @VisibleForTesting
    PendingResult<Result> requestSync(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, long j, boolean z);

    @VisibleForTesting
    PendingResult<Result> requestSyncByUserAction(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);
}
