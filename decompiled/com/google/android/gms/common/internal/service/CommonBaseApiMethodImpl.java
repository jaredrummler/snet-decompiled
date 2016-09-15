package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class CommonBaseApiMethodImpl<R extends Result> extends ApiMethodImpl<R, CommonClient> {

    static abstract class StatusMethod extends CommonBaseApiMethodImpl<Status> {
        public StatusMethod(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public Status createFailedResult(Status status) {
            return status;
        }
    }

    public CommonBaseApiMethodImpl(GoogleApiClient googleApiClient) {
        super(Common.CLIENT_KEY, googleApiClient);
    }
}
