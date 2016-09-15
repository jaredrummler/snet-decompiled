package com.google.android.gms.clearcut;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.concurrent.TimeUnit;

public interface ClearcutLoggerApi {
    void disconnectAsync(GoogleApiClient googleApiClient);

    boolean flush(GoogleApiClient googleApiClient, long j, TimeUnit timeUnit);

    PendingResult<Status> logEvent(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable);

    PendingResult<Status> logEventAsync(Context context, LogEventParcelable logEventParcelable);

    PendingResult<Status> logEventAsync(GoogleApiClient googleApiClient, LogEventParcelable logEventParcelable);
}
