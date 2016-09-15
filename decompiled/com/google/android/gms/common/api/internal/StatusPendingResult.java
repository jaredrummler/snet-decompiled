package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

public class StatusPendingResult extends BasePendingResult<Status> {
    public StatusPendingResult(GoogleApiClient apiClient) {
        super(apiClient);
    }

    @Deprecated
    public StatusPendingResult(Looper looper) {
        super(looper);
    }

    protected Status createFailedResult(Status status) {
        return status;
    }
}
