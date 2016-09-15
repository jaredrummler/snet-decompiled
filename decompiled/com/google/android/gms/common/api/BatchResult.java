package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final PendingResult<?>[] mPendingResults;
    private final Status mStatus;

    BatchResult(Status status, PendingResult<?>[] pendingResults) {
        this.mStatus = status;
        this.mPendingResults = pendingResults;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public <R extends Result> R take(BatchResultToken<R> resultToken) {
        Preconditions.checkArgument(resultToken.mId < this.mPendingResults.length, "The result token does not belong to this batch");
        return this.mPendingResults[resultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
