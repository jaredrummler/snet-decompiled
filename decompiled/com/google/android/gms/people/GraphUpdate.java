package com.google.android.gms.people;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface GraphUpdate {

    @VisibleForTesting
    public interface AddCircleResult extends Result {
        @VisibleForTesting
        String getCircleId();

        @VisibleForTesting
        String getCircleName();
    }

    @VisibleForTesting
    public interface AddPeopleToCircleResult extends Result {
        String getCircleId();

        String getCircleName();

        String[] getPeopleQualifiedIds();
    }

    @VisibleForTesting
    public interface LoadAddToCircleConsentResult extends Result {
        @VisibleForTesting
        String getConsentButtonText();

        @VisibleForTesting
        String getConsentHtml();

        @VisibleForTesting
        String getConsentTitleText();

        @VisibleForTesting
        boolean getShowConsent();
    }

    public interface SetMeResult extends Result {
        Bundle getBundle();
    }

    @VisibleForTesting
    public interface UpdatePersonCircleResult extends Result {
        List<String> getAddedCircles();

        List<String> getRemovedCircles();
    }

    @VisibleForTesting
    PendingResult<AddCircleResult> addCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable String str4);

    @VisibleForTesting
    PendingResult<AddCircleResult> addCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable String str4, boolean z);

    @VisibleForTesting
    PendingResult<AddPeopleToCircleResult> addPeopleToCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nonnull List<String> list);

    @VisibleForTesting
    PendingResult<Result> blockPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3);

    @VisibleForTesting
    PendingResult<LoadAddToCircleConsentResult> loadAddToCircleConsent(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);

    @VisibleForTesting
    PendingResult<Result> removeCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3);

    @VisibleForTesting
    PendingResult<Result> setHasShownAddToCircleConsent(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);

    PendingResult<SetMeResult> setMe(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull Bundle bundle);

    @VisibleForTesting
    PendingResult<Result> starPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nonnull String str2);

    @VisibleForTesting
    PendingResult<Result> unblockPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3);

    @VisibleForTesting
    PendingResult<Result> unstarPerson(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nonnull String str2);

    @VisibleForTesting
    PendingResult<Result> updateCircle(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable String str4, @Nullable Boolean bool, @Nullable String str5);

    @VisibleForTesting
    PendingResult<UpdatePersonCircleResult> updatePersonCircles(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable List<String> list, @Nullable List<String> list2);

    @VisibleForTesting
    PendingResult<UpdatePersonCircleResult> updatePersonCircles(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull String str3, @Nullable List<String> list, @Nullable List<String> list2, @Nullable FavaDiagnosticsEntity favaDiagnosticsEntity);
}
