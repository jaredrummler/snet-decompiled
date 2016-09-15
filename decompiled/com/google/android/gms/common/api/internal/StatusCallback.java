package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.IStatusCallback.Stub;

public class StatusCallback extends Stub {
    private final ResultHolder<Status> mResultHolder;

    public StatusCallback(ResultHolder<Status> resultHolder) {
        this.mResultHolder = resultHolder;
    }

    public void onResult(Status result) {
        this.mResultHolder.setResult(result);
    }
}
