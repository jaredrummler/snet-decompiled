package com.google.android.gms.people;

import android.net.Uri;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface ContactsSync {
    @VisibleForTesting
    PendingResult<BooleanResult> isSyncToContactsEnabled(@Nonnull GoogleApiClient googleApiClient);

    @VisibleForTesting
    PendingResult<Result> setSyncToContactsEnabled(@Nonnull GoogleApiClient googleApiClient, boolean z);

    @VisibleForTesting
    PendingResult<Result> setSyncToContactsSettings(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, boolean z, @Nullable String[] strArr);

    @VisibleForTesting
    PendingResult<Result> syncRawContact(@Nonnull GoogleApiClient googleApiClient, @Nonnull Uri uri);
}
