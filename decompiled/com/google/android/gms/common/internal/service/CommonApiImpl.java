package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

public final class CommonApiImpl implements CommonApi {

    /* renamed from: com.google.android.gms.common.internal.service.CommonApiImpl.1 */
    class AnonymousClass1 extends StatusMethod {
        AnonymousClass1(GoogleApiClient x0) {
            super(x0);
        }

        protected void doExecute(CommonClient clientImpl) throws RemoteException {
            ((ICommonService) clientImpl.getService()).clearDefaultAccount(new ClearDefaultAccountCallback(this));
        }
    }

    private static class ClearDefaultAccountCallback extends BaseCommonServiceCallbacks {
        private final ResultHolder<Status> mResultHolder;

        public ClearDefaultAccountCallback(ResultHolder<Status> resultHolder) {
            this.mResultHolder = resultHolder;
        }

        public void onDefaultAccountCleared(int statusCode) throws RemoteException {
            this.mResultHolder.setResult(new Status(statusCode));
        }
    }

    public PendingResult<Status> clearDefaultAccount(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new AnonymousClass1(googleApiClient));
    }
}
