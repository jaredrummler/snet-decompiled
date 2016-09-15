package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultStore;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class BasePendingResult<R extends Result> extends PendingResult<R> {
    private static final String TAG = "BasePendingResult";
    private final WeakReference<GoogleApiClient> mApiClient;
    private ResultCallback<? super R> mCallback;
    private ICancelToken mCancelToken;
    private boolean mCanceled;
    private boolean mCanceledUnlessTransformed;
    private volatile boolean mConsumed;
    private boolean mForcedFailure;
    protected final CallbackHandler<R> mHandler;
    private final CountDownLatch mLatch;
    private volatile R mResult;
    private Integer mResultId;
    private final ArrayList<StatusListener> mStatusListeners;
    private final Object mSyncToken;
    private volatile TransformedResultImpl<R> mTransformRoot;

    @VisibleForTesting
    public static class CallbackHandler<R extends Result> extends Handler {
        public static final int CALLBACK_ON_COMPLETE = 1;
        public static final int CALLBACK_ON_TIMEOUT = 2;

        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        public void sendResultCallback(ResultCallback<? super R> callback, R result) {
            sendMessage(obtainMessage(CALLBACK_ON_COMPLETE, new Pair(callback, result)));
        }

        public void sendTimeoutResultCallback(BasePendingResult<R> pendingResult, long millis) {
            sendMessageDelayed(obtainMessage(CALLBACK_ON_TIMEOUT, pendingResult), millis);
        }

        public void removeTimeoutMessages() {
            removeMessages(CALLBACK_ON_TIMEOUT);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CALLBACK_ON_COMPLETE /*1*/:
                    Pair<ResultCallback<? super R>, R> resultCall = msg.obj;
                    deliverResultCallback((ResultCallback) resultCall.first, (Result) resultCall.second);
                case CALLBACK_ON_TIMEOUT /*2*/:
                    msg.obj.forceFailureUnlessReady(Status.RESULT_TIMEOUT);
                default:
                    Log.wtf(BasePendingResult.TAG, "Don't know how to handle message: " + msg.what, new Exception());
            }
        }

        protected void deliverResultCallback(ResultCallback<? super R> callback, R result) {
            try {
                callback.onResult(result);
            } catch (RuntimeException e) {
                BasePendingResult.maybeReleaseResult(result);
                throw e;
            }
        }
    }

    protected abstract R createFailedResult(Status status);

    protected BasePendingResult(GoogleApiClient apiClient) {
        this.mSyncToken = new Object();
        this.mLatch = new CountDownLatch(1);
        this.mStatusListeners = new ArrayList();
        this.mHandler = new CallbackHandler(apiClient != null ? apiClient.getLooper() : Looper.getMainLooper());
        this.mApiClient = new WeakReference(apiClient);
    }

    @Deprecated
    protected BasePendingResult(Looper looper) {
        this.mSyncToken = new Object();
        this.mLatch = new CountDownLatch(1);
        this.mStatusListeners = new ArrayList();
        this.mHandler = new CallbackHandler(looper);
        this.mApiClient = new WeakReference(null);
    }

    @VisibleForTesting
    protected BasePendingResult(CallbackHandler<R> callbackHandler) {
        this.mSyncToken = new Object();
        this.mLatch = new CountDownLatch(1);
        this.mStatusListeners = new ArrayList();
        this.mHandler = (CallbackHandler) Preconditions.checkNotNull(callbackHandler, "CallbackHandler must not be null");
        this.mApiClient = new WeakReference(null);
    }

    public final boolean isReady() {
        return this.mLatch.getCount() == 0;
    }

    public final R await() {
        boolean z;
        boolean z2 = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
        if (this.mConsumed) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkState(z, "Result has already been consumed");
        if (this.mTransformRoot != null) {
            z2 = false;
        }
        Preconditions.checkState(z2, "Cannot await if then() has been called.");
        try {
            this.mLatch.await();
        } catch (InterruptedException e) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    public final R await(long time, TimeUnit units) {
        boolean z = true;
        boolean z2 = time <= 0 || Looper.myLooper() != Looper.getMainLooper();
        Preconditions.checkState(z2, "await must not be called on the UI thread when time is greater than zero.");
        if (this.mConsumed) {
            z2 = false;
        } else {
            z2 = true;
        }
        Preconditions.checkState(z2, "Result has already been consumed.");
        if (this.mTransformRoot != null) {
            z = false;
        }
        Preconditions.checkState(z, "Cannot await if then() has been called.");
        try {
            if (!this.mLatch.await(time, units)) {
                forceFailureUnlessReady(Status.RESULT_TIMEOUT);
            }
        } catch (InterruptedException e) {
            forceFailureUnlessReady(Status.RESULT_INTERRUPTED);
        }
        Preconditions.checkState(isReady(), "Result is not ready.");
        return get();
    }

    public final void setResultCallback(ResultCallback<? super R> callback) {
        boolean z = true;
        Preconditions.checkState(!this.mConsumed, "Result has already been consumed.");
        synchronized (this.mSyncToken) {
            if (this.mTransformRoot != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
            } else if (!this.mCanceledUnlessTransformed || (((GoogleApiClient) this.mApiClient.get()) != null && (callback instanceof TransformedResultImpl))) {
                if (isReady()) {
                    this.mHandler.sendResultCallback(callback, get());
                } else {
                    this.mCallback = callback;
                }
            } else {
                cancel();
            }
        }
    }

    public final void setResultCallback(ResultCallback<? super R> callback, long time, TimeUnit units) {
        boolean z = true;
        Preconditions.checkState(!this.mConsumed, "Result has already been consumed.");
        synchronized (this.mSyncToken) {
            if (this.mTransformRoot != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot set callbacks if then() has been called.");
            if (isCanceled()) {
            } else if (!this.mCanceledUnlessTransformed || (((GoogleApiClient) this.mApiClient.get()) != null && (callback instanceof TransformedResultImpl))) {
                if (isReady()) {
                    this.mHandler.sendResultCallback(callback, get());
                } else {
                    this.mCallback = callback;
                    this.mHandler.sendTimeoutResultCallback(this, units.toMillis(time));
                }
            } else {
                cancel();
            }
        }
    }

    public final void addStatusListener(StatusListener callback) {
        boolean z = true;
        Preconditions.checkState(!this.mConsumed, "Result has already been consumed.");
        if (callback == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Callback cannot be null.");
        synchronized (this.mSyncToken) {
            if (isReady()) {
                callback.onComplete(this.mResult.getStatus());
            } else {
                this.mStatusListeners.add(callback);
            }
        }
    }

    public void cancel() {
        synchronized (this.mSyncToken) {
            if (this.mCanceled || this.mConsumed) {
                return;
            }
            if (this.mCancelToken != null) {
                try {
                    this.mCancelToken.cancel();
                } catch (RemoteException e) {
                }
            }
            maybeReleaseResult(this.mResult);
            this.mCallback = null;
            this.mCanceled = true;
            setResultAndNotifyListeners(createFailedResult(Status.RESULT_CANCELED));
        }
    }

    public void cancelUnlessPossibleTransform() {
        synchronized (this.mSyncToken) {
            if (((GoogleApiClient) this.mApiClient.get()) == null) {
                cancel();
                return;
            }
            if (this.mCallback == null || (this.mCallback instanceof TransformedResultImpl)) {
                this.mCanceledUnlessTransformed = true;
            } else {
                cancel();
            }
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.mSyncToken) {
            z = this.mCanceled;
        }
        return z;
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> transform) {
        boolean z;
        TransformedResult<S> transformedResult;
        boolean z2 = true;
        if (this.mConsumed) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkState(z, "Result has already been consumed.");
        synchronized (this.mSyncToken) {
            if (this.mTransformRoot == null) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() twice.");
            if (this.mCallback != null) {
                z2 = false;
            }
            Preconditions.checkState(z2, "Cannot call then() if callbacks are set.");
            this.mTransformRoot = new TransformedResultImpl(this.mApiClient);
            transformedResult = this.mTransformRoot.then(transform);
            if (isReady()) {
                this.mHandler.sendResultCallback(this.mTransformRoot, get());
            } else {
                this.mCallback = this.mTransformRoot;
            }
        }
        return transformedResult;
    }

    public final void setResult(R result) {
        boolean z = true;
        synchronized (this.mSyncToken) {
            if (this.mForcedFailure || this.mCanceled) {
                maybeReleaseResult(result);
                return;
            }
            boolean z2;
            if (isReady()) {
                z2 = false;
            } else {
                z2 = true;
            }
            Preconditions.checkState(z2, "Results have already been set");
            if (this.mConsumed) {
                z = false;
            }
            Preconditions.checkState(z, "Result has already been consumed");
            setResultAndNotifyListeners(result);
        }
    }

    public final void forceFailureUnlessReady(Status status) {
        synchronized (this.mSyncToken) {
            if (!isReady()) {
                setResult(createFailedResult(status));
                this.mForcedFailure = true;
            }
        }
    }

    protected void onResultConsumed() {
    }

    public Integer getResultId() {
        return this.mResultId;
    }

    public void setResultId(int resultId) {
        Preconditions.checkArgument(this.mResultId == null, "PendingResult should only be stored once.");
        this.mResultId = Integer.valueOf(resultId);
    }

    protected final void setCancelToken(ICancelToken cancelToken) {
        synchronized (this.mSyncToken) {
            this.mCancelToken = cancelToken;
        }
    }

    private R get() {
        R result;
        boolean z = true;
        synchronized (this.mSyncToken) {
            if (this.mConsumed) {
                z = false;
            }
            Preconditions.checkState(z, "Result has already been consumed.");
            Preconditions.checkState(isReady(), "Result is not ready.");
            result = this.mResult;
            this.mResult = null;
            this.mCallback = null;
            this.mConsumed = true;
        }
        onResultConsumed();
        return result;
    }

    public void store(ResultStore resultStore, int resultId) {
        Preconditions.checkNotNull(resultStore, "ResultStore must not be null.");
        synchronized (this.mSyncToken) {
            Preconditions.checkState(!this.mConsumed, "Result has already been consumed.");
            resultStore.store(resultId, this);
        }
    }

    private void setResultAndNotifyListeners(R result) {
        this.mResult = result;
        this.mCancelToken = null;
        this.mLatch.countDown();
        Status status = this.mResult.getStatus();
        if (this.mCallback != null) {
            this.mHandler.removeTimeoutMessages();
            if (!this.mCanceled) {
                this.mHandler.sendResultCallback(this.mCallback, get());
            }
        }
        Iterator i$ = this.mStatusListeners.iterator();
        while (i$.hasNext()) {
            ((StatusListener) i$.next()).onComplete(status);
        }
        this.mStatusListeners.clear();
    }

    public static void maybeReleaseResult(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unable to release " + result, e);
            }
        }
    }
}
