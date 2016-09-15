package com.google.android.gms.common.api.internal;

import android.util.SparseArray;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultStore;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.concurrent.GuardedBy;

public class ResultStoreImpl extends ResultStore {
    private final Object mLock;
    @GuardedBy("mLock")
    private final SparseArray<ResultCallbacks<?>> mResultCallbacks;
    @GuardedBy("mLock")
    private final SparseArray<PendingResult<?>> mStoredPendingResults;

    public ResultStoreImpl() {
        this.mLock = new Object();
        this.mStoredPendingResults = new SparseArray();
        this.mResultCallbacks = new SparseArray();
    }

    public boolean hasPendingResult(int resultId) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mStoredPendingResults.get(resultId) != null;
        }
        return z;
    }

    public <R extends Result> void store(int resultId, PendingResult<R> pendingResult) {
        boolean z = true;
        synchronized (this.mLock) {
            boolean z2;
            if (this.mStoredPendingResults.get(resultId) == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "ResultStore ResultId must be unique within the current activity. Violating ResultId: " + resultId);
            if (pendingResult.getResultId() != null) {
                z = false;
            }
            Preconditions.checkArgument(z, "PendingResult has already been saved.");
            pendingResult.setResultId(resultId);
            this.mStoredPendingResults.put(resultId, pendingResult);
            ResultCallbacks<R> resultCallbacks = (ResultCallbacks) this.mResultCallbacks.get(resultId);
            if (resultCallbacks != null) {
                pendingResult.setResultCallback(resultCallbacks);
            }
        }
    }

    public void remove(int resultId) {
        synchronized (this.mLock) {
            if (((PendingResult) this.mStoredPendingResults.get(resultId)) != null) {
                this.mStoredPendingResults.remove(resultId);
            }
        }
    }

    public void setResultCallbacks(int resultId, ResultCallbacks resultCallbacks) {
        Preconditions.checkNotNull(resultCallbacks, "ResultCallbacks cannot be null.");
        synchronized (this.mLock) {
            this.mResultCallbacks.put(resultId, resultCallbacks);
            PendingResult<?> pendingResult = (PendingResult) this.mStoredPendingResults.get(resultId);
            if (pendingResult != null) {
                pendingResult.setResultCallback(resultCallbacks);
            }
        }
    }

    public void onSaveInstanceState() {
        synchronized (this.mLock) {
            this.mResultCallbacks.clear();
            for (int i = 0; i < this.mStoredPendingResults.size(); i++) {
                ((PendingResult) this.mStoredPendingResults.valueAt(i)).setResultCallback(null);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy(android.app.Activity r5) {
        /*
        r4 = this;
        r3 = r4.mLock;
        monitor-enter(r3);
        r0 = 0;
    L_0x0004:
        r2 = r4.mStoredPendingResults;	 Catch:{ all -> 0x001f }
        r2 = r2.size();	 Catch:{ all -> 0x001f }
        if (r0 >= r2) goto L_0x001a;
    L_0x000c:
        r2 = r4.mStoredPendingResults;	 Catch:{ all -> 0x001f }
        r1 = r2.valueAt(r0);	 Catch:{ all -> 0x001f }
        r1 = (com.google.android.gms.common.api.PendingResult) r1;	 Catch:{ all -> 0x001f }
        r1.cancel();	 Catch:{ all -> 0x001f }
        r0 = r0 + 1;
        goto L_0x0004;
    L_0x001a:
        monitor-exit(r3);	 Catch:{ all -> 0x001f }
        com.google.android.gms.common.api.ResultStore.removeActivityReference(r5);
        return;
    L_0x001f:
        r2 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x001f }
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.ResultStoreImpl.onDestroy(android.app.Activity):void");
    }
}
