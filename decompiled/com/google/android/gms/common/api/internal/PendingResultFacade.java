package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultStore;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

public abstract class PendingResultFacade<A extends Result, B extends Result> extends PendingResult<B> {
    private final PendingResult<A> mInternalResult;

    /* renamed from: com.google.android.gms.common.api.internal.PendingResultFacade.1 */
    class AnonymousClass1 implements ResultCallback<A> {
        final /* synthetic */ ResultCallback val$callback;

        AnonymousClass1(ResultCallback resultCallback) {
            this.val$callback = resultCallback;
        }

        public void onResult(@NonNull A result) {
            this.val$callback.onResult(PendingResultFacade.this.translate(result));
        }
    }

    /* renamed from: com.google.android.gms.common.api.internal.PendingResultFacade.2 */
    class AnonymousClass2 implements ResultCallback<A> {
        final /* synthetic */ ResultCallback val$callback;

        AnonymousClass2(ResultCallback resultCallback) {
            this.val$callback = resultCallback;
        }

        public void onResult(@NonNull A result) {
            this.val$callback.onResult(PendingResultFacade.this.translate(result));
        }
    }

    /* renamed from: com.google.android.gms.common.api.internal.PendingResultFacade.3 */
    class AnonymousClass3 extends ResultTransform<A, T> {
        final /* synthetic */ ResultTransform val$transform;

        AnonymousClass3(ResultTransform resultTransform) {
            this.val$transform = resultTransform;
        }

        public PendingResult<T> onSuccess(A result) {
            return this.val$transform.onSuccess(PendingResultFacade.this.translate(result));
        }

        public Status onFailure(Status status) {
            return this.val$transform.onFailure(status);
        }
    }

    protected abstract B translate(A a);

    public PendingResultFacade(PendingResult<A> wrappedResult) {
        Preconditions.checkNotNull(wrappedResult);
        this.mInternalResult = wrappedResult;
    }

    public B await() {
        return translate(this.mInternalResult.await());
    }

    public B await(long time, TimeUnit units) {
        return translate(this.mInternalResult.await(time, units));
    }

    public void cancel() {
        this.mInternalResult.cancel();
    }

    public boolean isCanceled() {
        return this.mInternalResult.isCanceled();
    }

    public void setResultCallback(ResultCallback<? super B> callback) {
        this.mInternalResult.setResultCallback(new AnonymousClass1(callback));
    }

    public void setResultCallback(ResultCallback<? super B> callback, long time, TimeUnit units) {
        this.mInternalResult.setResultCallback(new AnonymousClass2(callback), time, units);
    }

    public void addStatusListener(StatusListener callback) {
        this.mInternalResult.addStatusListener(callback);
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super B, ? extends S> transform) {
        return this.mInternalResult.then(createInternalTransform(transform));
    }

    public void setResultId(int resultId) {
        this.mInternalResult.setResultId(resultId);
    }

    public Integer getResultId() {
        return this.mInternalResult.getResultId();
    }

    public void store(ResultStore resultStore, int resultId) {
        this.mInternalResult.store(resultStore, resultId);
    }

    private <T extends Result> ResultTransform<? super A, T> createInternalTransform(ResultTransform<? super B, T> transform) {
        return new AnonymousClass3(transform);
    }
}
