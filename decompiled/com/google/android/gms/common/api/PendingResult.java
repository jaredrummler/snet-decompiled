package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public abstract class PendingResult<R extends Result> {

    public interface StatusListener {
        void onComplete(Status status);
    }

    @NonNull
    public abstract R await();

    @NonNull
    public abstract R await(long j, @NonNull TimeUnit timeUnit);

    public abstract void cancel();

    public abstract boolean isCanceled();

    public abstract void setResultCallback(@NonNull ResultCallback<? super R> resultCallback);

    public abstract void setResultCallback(@NonNull ResultCallback<? super R> resultCallback, long j, @NonNull TimeUnit timeUnit);

    public void store(@NonNull ResultStore resultStore, int resultId) {
        throw new UnsupportedOperationException();
    }

    public void addStatusListener(@NonNull StatusListener callback) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        throw new UnsupportedOperationException();
    }

    public void setResultId(int resultId) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    public Integer getResultId() {
        throw new UnsupportedOperationException();
    }
}
