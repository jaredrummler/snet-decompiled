package com.google.android.gms.clearcut.internal;

import android.content.Context;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.ClearcutLoggerApi;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks.Stub;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.protobuf.nano.MessageNano;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ClearcutLoggerApiImpl implements ClearcutLoggerApi {
    private static final long MANAGED_API_CLIENT_IDLE_MILLIS;
    private static final String TAG = "ClearcutLoggerApiImpl";
    private static ScheduledExecutorService mExecutor;
    private static final Object mExecutorLock;
    private static final QueueLength sQueueLength;
    private final Runnable DISCONNECTOR;
    private GoogleApiClient mApiClient;
    private final ApiClientMaker mApiClientMaker;
    private final Clock mClock;
    private final long mDisconnectTimeMillis;
    private ScheduledFuture<?> mManagedApiClientDisconnect;
    private final Object mManagedApiClientLock;
    private long mMinimumTimeToDisconnectMillis;

    /* renamed from: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.3 */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ GoogleApiClient val$apiClient;
        final /* synthetic */ BaseClearcutLoggerApiMethodImpl val$pendingResult;

        AnonymousClass3(GoogleApiClient googleApiClient, BaseClearcutLoggerApiMethodImpl baseClearcutLoggerApiMethodImpl) {
            this.val$apiClient = googleApiClient;
            this.val$pendingResult = baseClearcutLoggerApiMethodImpl;
        }

        public void run() {
            this.val$apiClient.enqueue(this.val$pendingResult);
        }
    }

    static abstract class BaseClearcutLoggerApiMethodImpl<R extends Result> extends ApiMethodImpl<R, ClearcutLoggerClientImpl> {
        public BaseClearcutLoggerApiMethodImpl(GoogleApiClient googleApiClient) {
            super(ClearcutLogger.CLIENT_KEY, googleApiClient);
        }

        @VisibleForTesting
        void doExecuteForTest(ClearcutLoggerClientImpl client) throws RemoteException {
            doExecute(client);
        }
    }

    /* renamed from: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.4 */
    class AnonymousClass4 extends BaseClearcutLoggerApiMethodImpl<Status> {
        private int mRetries;
        final /* synthetic */ GoogleApiClient val$apiClient;

        /* renamed from: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.4.1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ BaseClearcutLoggerApiMethodImpl val$thisDisconnect;

            AnonymousClass1(BaseClearcutLoggerApiMethodImpl baseClearcutLoggerApiMethodImpl) {
                this.val$thisDisconnect = baseClearcutLoggerApiMethodImpl;
            }

            public void run() {
                ClearcutLoggerApiImpl.this.enqueue(AnonymousClass4.this.val$apiClient, this.val$thisDisconnect);
            }
        }

        AnonymousClass4(GoogleApiClient x0, GoogleApiClient googleApiClient) {
            this.val$apiClient = googleApiClient;
            super(x0);
            this.mRetries = 5;
        }

        protected void doExecute(ClearcutLoggerClientImpl client) throws RemoteException {
            if (this.mRetries < 0) {
                this.val$apiClient.disconnect();
                return;
            }
            if (ClearcutLoggerApiImpl.sQueueLength.isZero()) {
                this.mRetries = 0;
            }
            this.mRetries--;
            ClearcutLoggerApiImpl.this.getExecutor().schedule(new AnonymousClass1(this), 200, TimeUnit.MILLISECONDS);
        }

        protected Status createFailedResult(Status status) {
            return status;
        }
    }

    /* renamed from: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.5 */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ GoogleApiClient val$apiClient;
        final /* synthetic */ BaseClearcutLoggerApiMethodImpl val$methodImpl;

        AnonymousClass5(GoogleApiClient googleApiClient, BaseClearcutLoggerApiMethodImpl baseClearcutLoggerApiMethodImpl) {
            this.val$apiClient = googleApiClient;
            this.val$methodImpl = baseClearcutLoggerApiMethodImpl;
        }

        public void run() {
            this.val$apiClient.enqueue(this.val$methodImpl);
        }
    }

    @VisibleForTesting
    public interface ApiClientMaker {
        GoogleApiClient make(Context context);
    }

    @VisibleForTesting
    public static class ApiClientMakerImpl implements ApiClientMaker {
        public GoogleApiClient make(Context context) {
            return new Builder(context).addApi(ClearcutLogger.API).build();
        }
    }

    @VisibleForTesting
    final class MethodImpl extends BaseClearcutLoggerApiMethodImpl<Status> {
        private final LogEventParcelable mLogEvent;

        @VisibleForTesting
        MethodImpl(LogEventParcelable logEvent, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.mLogEvent = logEvent;
        }

        protected void doExecute(ClearcutLoggerClientImpl client) throws RemoteException {
            Stub callback = new Stub() {
                public void onLogEvent(Status status) {
                    MethodImpl.this.setResult(status);
                }
            };
            try {
                ClearcutLoggerApiImpl.resolveToBytes(this.mLogEvent);
                client.logEvent(callback, this.mLogEvent);
            } catch (Throwable e) {
                Log.e(ClearcutLoggerApiImpl.TAG, "MessageNanoProducer " + this.mLogEvent.extensionProducer.toString() + " threw: " + e.toString());
            }
        }

        protected Status createFailedResult(Status status) {
            return status;
        }

        public boolean equals(Object rhs) {
            if (!(rhs instanceof MethodImpl)) {
                return false;
            }
            return this.mLogEvent.equals(((MethodImpl) rhs).mLogEvent);
        }

        public String toString() {
            return "MethodImpl(" + this.mLogEvent + ")";
        }
    }

    private static final class QueueLength {
        private int mSize;

        private QueueLength() {
            this.mSize = 0;
        }

        public synchronized void increment() {
            this.mSize++;
        }

        public synchronized void decrement() {
            if (this.mSize == 0) {
                throw new RuntimeException("too many decrements");
            }
            this.mSize--;
            if (this.mSize == 0) {
                notifyAll();
            }
        }

        public synchronized boolean isZero() {
            return this.mSize == 0;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean waitForZero(long r8, java.util.concurrent.TimeUnit r10) throws java.lang.InterruptedException {
            /*
            r7 = this;
            r0 = java.lang.System.currentTimeMillis();
            r4 = java.util.concurrent.TimeUnit.MILLISECONDS;
            r2 = r4.convert(r8, r10);
            monitor-enter(r7);
        L_0x000b:
            r4 = r7.mSize;	 Catch:{ all -> 0x001b }
            if (r4 != 0) goto L_0x0012;
        L_0x000f:
            r4 = 1;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
        L_0x0011:
            return r4;
        L_0x0012:
            r4 = 0;
            r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r4 > 0) goto L_0x001e;
        L_0x0018:
            r4 = 0;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            goto L_0x0011;
        L_0x001b:
            r4 = move-exception;
            monitor-exit(r7);	 Catch:{ all -> 0x001b }
            throw r4;
        L_0x001e:
            r7.wait(r2);	 Catch:{ all -> 0x001b }
            r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x001b }
            r4 = r4 - r0;
            r2 = r2 - r4;
            goto L_0x000b;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.QueueLength.waitForZero(long, java.util.concurrent.TimeUnit):boolean");
        }
    }

    static {
        mExecutorLock = new Object();
        sQueueLength = new QueueLength();
        MANAGED_API_CLIENT_IDLE_MILLIS = TimeUnit.MILLISECONDS.convert(2, TimeUnit.MINUTES);
    }

    public ClearcutLoggerApiImpl() {
        this(new DefaultClock(), MANAGED_API_CLIENT_IDLE_MILLIS, new ApiClientMakerImpl());
    }

    @VisibleForTesting
    public ClearcutLoggerApiImpl(Clock clock, long disconnectTimeMillis, ApiClientMaker apiClientMaker) {
        this.mManagedApiClientLock = new Object();
        this.mMinimumTimeToDisconnectMillis = MANAGED_API_CLIENT_IDLE_MILLIS;
        this.mManagedApiClientDisconnect = null;
        this.mApiClient = null;
        this.DISCONNECTOR = new Runnable() {
            public void run() {
                synchronized (ClearcutLoggerApiImpl.this.mManagedApiClientLock) {
                    if (ClearcutLoggerApiImpl.this.mMinimumTimeToDisconnectMillis <= ClearcutLoggerApiImpl.this.mClock.elapsedRealtime() && ClearcutLoggerApiImpl.this.mApiClient != null) {
                        Log.i(ClearcutLoggerApiImpl.TAG, "disconnect managed GoogleApiClient");
                        ClearcutLoggerApiImpl.this.mApiClient.disconnect();
                        ClearcutLoggerApiImpl.this.mApiClient = null;
                    }
                }
            }
        };
        this.mClock = clock;
        this.mDisconnectTimeMillis = disconnectTimeMillis;
        this.mApiClientMaker = apiClientMaker;
    }

    private ScheduledExecutorService getExecutor() {
        synchronized (mExecutorLock) {
            if (mExecutor == null) {
                mExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {

                    /* renamed from: com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl.2.1 */
                    class AnonymousClass1 implements Runnable {
                        final /* synthetic */ Runnable val$r;

                        AnonymousClass1(Runnable runnable) {
                            this.val$r = runnable;
                        }

                        public void run() {
                            Process.setThreadPriority(10);
                            this.val$r.run();
                        }
                    }

                    public Thread newThread(Runnable r) {
                        return new Thread(new AnonymousClass1(r), ClearcutLoggerApiImpl.TAG);
                    }
                });
            }
        }
        return mExecutor;
    }

    private PendingResult<Status> enqueue(GoogleApiClient apiClient, BaseClearcutLoggerApiMethodImpl<Status> pendingResult) {
        getExecutor().execute(new AnonymousClass3(apiClient, pendingResult));
        return pendingResult;
    }

    public boolean flush(GoogleApiClient apiClient, long waitTime, TimeUnit unit) {
        try {
            return sQueueLength.waitForZero(waitTime, unit);
        } catch (InterruptedException e) {
            Log.e(TAG, "flush interrupted");
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void disconnectAsync(GoogleApiClient apiClient) {
        BaseClearcutLoggerApiMethodImpl<Status> methodImpl = new AnonymousClass4(apiClient, apiClient);
        synchronized (mExecutorLock) {
            if (mExecutor == null) {
                apiClient.enqueue(methodImpl);
            } else {
                mExecutor.execute(new AnonymousClass5(apiClient, methodImpl));
            }
        }
    }

    public PendingResult<Status> logEventAsync(GoogleApiClient apiClient, LogEventParcelable logEvent) {
        return enqueue(apiClient, pendingResult(apiClient, logEvent));
    }

    public PendingResult<Status> logEventAsync(Context context, LogEventParcelable logEvent) {
        PendingResult<Status> logEventAsync;
        synchronized (this.mManagedApiClientLock) {
            if (this.mApiClient == null) {
                this.mApiClient = this.mApiClientMaker.make(context);
                this.mApiClient.connect();
            }
            this.mMinimumTimeToDisconnectMillis = this.mClock.elapsedRealtime() + this.mDisconnectTimeMillis;
            if (this.mManagedApiClientDisconnect != null) {
                this.mManagedApiClientDisconnect.cancel(false);
            }
            this.mManagedApiClientDisconnect = getExecutor().schedule(this.DISCONNECTOR, this.mDisconnectTimeMillis, TimeUnit.MILLISECONDS);
            logEventAsync = logEventAsync(this.mApiClient, logEvent);
        }
        return logEventAsync;
    }

    public PendingResult<Status> logEvent(GoogleApiClient apiClient, LogEventParcelable logEvent) {
        resolveToBytes(logEvent);
        return apiClient.enqueue(pendingResult(apiClient, logEvent));
    }

    private MethodImpl pendingResult(GoogleApiClient apiClient, LogEventParcelable logEvent) {
        sQueueLength.increment();
        MethodImpl methodImpl = new MethodImpl(logEvent, apiClient);
        methodImpl.addStatusListener(new StatusListener() {
            public void onComplete(Status status) {
                ClearcutLoggerApiImpl.sQueueLength.decrement();
            }
        });
        return methodImpl;
    }

    private static void resolveToBytes(LogEventParcelable logEvent) {
        if (logEvent.extensionProducer != null && logEvent.logEvent.sourceExtension.length == 0) {
            logEvent.logEvent.sourceExtension = logEvent.extensionProducer.toProtoBytes();
        }
        if (logEvent.clientVisualElementsProducer != null && logEvent.logEvent.clientVe.length == 0) {
            logEvent.logEvent.clientVe = logEvent.clientVisualElementsProducer.toProtoBytes();
        }
        logEvent.logEventBytes = MessageNano.toByteArray(logEvent.logEvent);
    }
}
