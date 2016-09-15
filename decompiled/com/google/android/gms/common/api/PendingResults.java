package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;

public final class PendingResults {

    private static final class FailedPendingResult<R extends Result> extends BasePendingResult<R> {
        private final R mFailedResult;

        public FailedPendingResult(R failedResult) {
            super(Looper.getMainLooper());
            this.mFailedResult = failedResult;
        }

        protected R createFailedResult(Status status) {
            if (status.getStatusCode() == this.mFailedResult.getStatus().getStatusCode()) {
                return this.mFailedResult;
            }
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    private static final class ImmediateFailedResult<R extends Result> extends BasePendingResult<R> {
        private final R mResult;

        public ImmediateFailedResult(GoogleApiClient apiClient, R result) {
            super(apiClient);
            this.mResult = result;
        }

        protected R createFailedResult(Status status) {
            return this.mResult;
        }
    }

    private static final class ImmediatePendingResult<R extends Result> extends BasePendingResult<R> {
        public ImmediatePendingResult(GoogleApiClient apiClient) {
            super(apiClient);
        }

        protected R createFailedResult(Status status) {
            throw new UnsupportedOperationException("Creating failed results is not supported");
        }
    }

    public static PendingResult<Status> immediatePendingResult(Status result) {
        Preconditions.checkNotNull(result, "Result must not be null");
        StatusPendingResult pendingResult = new StatusPendingResult(Looper.getMainLooper());
        pendingResult.setResult(result);
        return pendingResult;
    }

    public static PendingResult<Status> immediatePendingResult(Status result, GoogleApiClient apiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        StatusPendingResult pendingResult = new StatusPendingResult(apiClient);
        pendingResult.setResult(result);
        return pendingResult;
    }

    public static <R extends Result> PendingResult<R> immediateFailedResult(R result, GoogleApiClient apiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        Preconditions.checkArgument(!result.getStatus().isSuccess(), "Status code must not be SUCCESS");
        ImmediateFailedResult<R> pendingResult = new ImmediateFailedResult(apiClient, result);
        pendingResult.setResult(result);
        return pendingResult;
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R result) {
        Preconditions.checkNotNull(result, "Result must not be null");
        ImmediatePendingResult<R> pendingResult = new ImmediatePendingResult(null);
        pendingResult.setResult(result);
        return new OptionalPendingResultImpl(pendingResult);
    }

    public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R result, GoogleApiClient apiClient) {
        Preconditions.checkNotNull(result, "Result must not be null");
        ImmediatePendingResult<R> pendingResult = new ImmediatePendingResult(apiClient);
        pendingResult.setResult(result);
        return new OptionalPendingResultImpl(pendingResult);
    }

    public static PendingResult<Status> canceledPendingResult() {
        StatusPendingResult pendingResult = new StatusPendingResult(Looper.getMainLooper());
        pendingResult.cancel();
        return pendingResult;
    }

    public static <R extends Result> PendingResult<R> canceledPendingResult(R result) {
        Preconditions.checkNotNull(result, "Result must not be null");
        Preconditions.checkArgument(result.getStatus().getStatusCode() == 16, "Status code must be CommonStatusCodes.CANCELED");
        FailedPendingResult<R> pendingResult = new FailedPendingResult(result);
        pendingResult.cancel();
        return pendingResult;
    }

    private PendingResults() {
    }
}
