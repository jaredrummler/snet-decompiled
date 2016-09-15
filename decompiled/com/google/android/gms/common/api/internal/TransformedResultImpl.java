package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import java.lang.ref.WeakReference;
import javax.annotation.concurrent.GuardedBy;

public class TransformedResultImpl<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private static final String TAG = "TransformedResultImpl";
    private final WeakReference<GoogleApiClient> mApiClient;
    private ResultCallbacks<? super R> mCallbacks;
    private Status mFailure;
    private final TransformationResultHandler mHandler;
    private PendingResult<R> mPreviousPendingResult;
    private final Object mSyncToken;
    private ResultTransform<? super R, ? extends Result> mTransform;
    private TransformedResultImpl<? extends Result> mTransformedResult;

    /* renamed from: com.google.android.gms.common.api.internal.TransformedResultImpl.1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Result val$result;

        AnonymousClass1(Result result) {
            this.val$result = result;
        }

        @WorkerThread
        public void run() {
            GoogleApiClient apiClient;
            try {
                TransformedResultImpl.this.mHandler.sendMessage(TransformedResultImpl.this.mHandler.obtainMessage(0, TransformedResultImpl.this.mTransform.onSuccess(this.val$result)));
                TransformedResultImpl.this.maybeReleaseResult(this.val$result);
                apiClient = (GoogleApiClient) TransformedResultImpl.this.mApiClient.get();
                if (apiClient != null) {
                    apiClient.unregisterPendingTransform(TransformedResultImpl.this);
                }
            } catch (RuntimeException e) {
                TransformedResultImpl.this.mHandler.sendMessage(TransformedResultImpl.this.mHandler.obtainMessage(1, e));
                TransformedResultImpl.this.maybeReleaseResult(this.val$result);
                apiClient = (GoogleApiClient) TransformedResultImpl.this.mApiClient.get();
                if (apiClient != null) {
                    apiClient.unregisterPendingTransform(TransformedResultImpl.this);
                }
            } catch (Throwable th) {
                TransformedResultImpl.this.maybeReleaseResult(this.val$result);
                apiClient = (GoogleApiClient) TransformedResultImpl.this.mApiClient.get();
                if (apiClient != null) {
                    apiClient.unregisterPendingTransform(TransformedResultImpl.this);
                }
            }
        }
    }

    private final class TransformationResultHandler extends Handler {
        public static final int MSG_EXCEPTION = 1;
        public static final int MSG_RESULT = 0;

        public TransformationResultHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Action.UNKNOWN /*0*/:
                    PendingResult<? extends Result> pendingResult = msg.obj;
                    synchronized (TransformedResultImpl.this.mSyncToken) {
                        if (pendingResult != null) {
                            if (pendingResult instanceof SentinelPendingResult) {
                                TransformedResultImpl.this.mTransformedResult.setFailure(((SentinelPendingResult) pendingResult).getStatus());
                            } else {
                                TransformedResultImpl.this.mTransformedResult.setPendingResult(pendingResult);
                            }
                            break;
                        }
                        TransformedResultImpl.this.mTransformedResult.setFailure(new Status(13, "Transform returned null"));
                        break;
                    }
                case MSG_EXCEPTION /*1*/:
                    RuntimeException exception = msg.obj;
                    Log.e(TransformedResultImpl.TAG, "Runtime exception on the transformation worker thread: " + exception.getMessage());
                    throw exception;
                default:
                    Log.e(TransformedResultImpl.TAG, "TransformationResultHandler received unknown message type: " + msg.what);
            }
        }
    }

    public TransformedResultImpl(WeakReference<GoogleApiClient> apiClient) {
        this.mTransform = null;
        this.mTransformedResult = null;
        this.mCallbacks = null;
        this.mPreviousPendingResult = null;
        this.mSyncToken = new Object();
        this.mFailure = null;
        Preconditions.checkNotNull(apiClient, "GoogleApiClient reference must not be null");
        this.mApiClient = apiClient;
        GoogleApiClient client = (GoogleApiClient) this.mApiClient.get();
        this.mHandler = new TransformationResultHandler(client != null ? client.getLooper() : Looper.getMainLooper());
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> transform) {
        TransformedResultImpl<S> transformedResult;
        boolean z = true;
        synchronized (this.mSyncToken) {
            boolean z2;
            if (this.mTransform == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkState(z2, "Cannot call then() twice.");
            if (this.mCallbacks != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.mTransform = transform;
            transformedResult = new TransformedResultImpl(this.mApiClient);
            this.mTransformedResult = transformedResult;
            tryGetResult();
        }
        return transformedResult;
    }

    public void andFinally(@NonNull ResultCallbacks<? super R> callbacks) {
        boolean z = true;
        synchronized (this.mSyncToken) {
            Preconditions.checkState(this.mCallbacks == null, "Cannot call andFinally() twice.");
            if (this.mTransform != null) {
                z = false;
            }
            Preconditions.checkState(z, "Cannot call then() and andFinally() on the same TransformedResult.");
            this.mCallbacks = callbacks;
            tryGetResult();
        }
    }

    public void onResult(R result) {
        synchronized (this.mSyncToken) {
            if (!result.getStatus().isSuccess()) {
                setFailure(result.getStatus());
                maybeReleaseResult(result);
            } else if (this.mTransform != null) {
                ResultTransformExecutor.getInstance().submit(new AnonymousClass1(result));
            } else if (shouldCallCallbacks()) {
                this.mCallbacks.onSuccess(result);
            }
        }
    }

    public void setPendingResult(PendingResult<?> pendingResult) {
        synchronized (this.mSyncToken) {
            this.mPreviousPendingResult = pendingResult;
            tryGetResult();
        }
    }

    @GuardedBy("mSyncToken")
    private void tryGetResult() {
        if (this.mTransform != null || this.mCallbacks != null) {
            GoogleApiClient client = (GoogleApiClient) this.mApiClient.get();
            if (!(this.mTransform == null || client == null)) {
                client.registerPendingTransform(this);
            }
            if (this.mFailure != null) {
                tryHandleFailure(this.mFailure);
            } else if (this.mPreviousPendingResult != null) {
                this.mPreviousPendingResult.setResultCallback(this);
            }
        }
    }

    private void setFailure(Status failure) {
        synchronized (this.mSyncToken) {
            this.mFailure = failure;
            tryHandleFailure(this.mFailure);
        }
    }

    private void tryHandleFailure(Status failure) {
        synchronized (this.mSyncToken) {
            if (this.mTransform != null) {
                Status transformedFailure = this.mTransform.onFailure(failure);
                Preconditions.checkNotNull(transformedFailure, "onFailure must not return null");
                this.mTransformedResult.setFailure(transformedFailure);
            } else if (shouldCallCallbacks()) {
                this.mCallbacks.onFailure(failure);
            }
        }
    }

    void clearCallbacks() {
        synchronized (this.mSyncToken) {
            this.mCallbacks = null;
        }
    }

    private boolean shouldCallCallbacks() {
        return (this.mCallbacks == null || ((GoogleApiClient) this.mApiClient.get()) == null) ? false : true;
    }

    private void maybeReleaseResult(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unable to release " + result, e);
            }
        }
    }
}
