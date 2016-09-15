package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends BasePendingResult<BatchResult> {
    private boolean mAnyFailures;
    private final Object mLock;
    private boolean mPendingResultCanceled;
    private final PendingResult<?>[] mPendingResults;
    private int mRemainingItems;

    public static final class Builder {
        private GoogleApiClient mApiClient;
        private List<PendingResult<?>> mResultList;

        public Builder(GoogleApiClient googleApiClient) {
            this.mResultList = new ArrayList();
            this.mApiClient = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> resultToken = new BatchResultToken(this.mResultList.size());
            this.mResultList.add(pendingResult);
            return resultToken;
        }

        public Batch build() {
            return new Batch(this.mApiClient, null);
        }
    }

    private Batch(List<PendingResult<?>> pendingResultList, GoogleApiClient apiClient) {
        super(apiClient);
        this.mLock = new Object();
        this.mRemainingItems = pendingResultList.size();
        this.mPendingResults = new PendingResult[this.mRemainingItems];
        if (pendingResultList.isEmpty()) {
            setResult(new BatchResult(Status.RESULT_SUCCESS, this.mPendingResults));
            return;
        }
        for (int i = 0; i < pendingResultList.size(); i++) {
            PendingResult<?> pendingResult = (PendingResult) pendingResultList.get(i);
            this.mPendingResults[i] = pendingResult;
            pendingResult.addStatusListener(new StatusListener() {
                public void onComplete(Status status) {
                    synchronized (Batch.this.mLock) {
                        if (Batch.this.isCanceled()) {
                            return;
                        }
                        if (status.isCanceled()) {
                            Batch.this.mPendingResultCanceled = true;
                        } else if (!status.isSuccess()) {
                            Batch.this.mAnyFailures = true;
                        }
                        Batch.this.mRemainingItems = Batch.this.mRemainingItems - 1;
                        if (Batch.this.mRemainingItems == 0) {
                            if (Batch.this.mPendingResultCanceled) {
                                super.cancel();
                            } else {
                                Status resultStatus;
                                if (Batch.this.mAnyFailures) {
                                    resultStatus = new Status(13);
                                } else {
                                    resultStatus = Status.RESULT_SUCCESS;
                                }
                                Batch.this.setResult(new BatchResult(resultStatus, Batch.this.mPendingResults));
                            }
                        }
                    }
                }
            });
        }
    }

    public void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.mPendingResults) {
            pendingResult.cancel();
        }
    }

    public BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.mPendingResults);
    }
}
