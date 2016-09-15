package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult.CallbackHandler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public class BaseImplementation {

    @VisibleForTesting
    public interface ResultHolder<R> {
        void setFailedResult(Status status);

        void setResult(R r);
    }

    @VisibleForTesting
    public static abstract class ApiMethodImpl<R extends Result, A extends Client> extends BasePendingResult<R> implements ResultHolder<R>, Runner<A> {
        private final ClientKey<A> mClientKey;
        private AtomicReference<ResultConsumedCallback> mConsumedCallback;

        protected abstract void doExecute(A a) throws RemoteException;

        public /* bridge */ /* synthetic */ void setResult(Object obj) {
            super.setResult((Result) obj);
        }

        protected ApiMethodImpl(ClientKey<A> clientKey, GoogleApiClient apiClient) {
            super((GoogleApiClient) Preconditions.checkNotNull(apiClient, "GoogleApiClient must not be null"));
            this.mConsumedCallback = new AtomicReference();
            this.mClientKey = (ClientKey) Preconditions.checkNotNull(clientKey);
        }

        @VisibleForTesting
        protected ApiMethodImpl(CallbackHandler<R> handler) {
            super((CallbackHandler) handler);
            this.mConsumedCallback = new AtomicReference();
            this.mClientKey = null;
        }

        public final ClientKey<A> getClientKey() {
            return this.mClientKey;
        }

        public final void run(A client) throws DeadObjectException {
            try {
                doExecute(client);
            } catch (RemoteException e) {
                setFailedResult(e);
                throw e;
            } catch (RemoteException e2) {
                setFailedResult(e2);
            }
        }

        public final void setFailedResult(Status status) {
            Preconditions.checkArgument(!status.isSuccess(), "Failed result must not be success");
            setResult(createFailedResult(status));
        }

        public void setResultConsumedCallback(ResultConsumedCallback callback) {
            this.mConsumedCallback.set(callback);
        }

        public void clearResultCallback() {
            setResultCallback(null);
        }

        protected void onResultConsumed() {
            ResultConsumedCallback callback = (ResultConsumedCallback) this.mConsumedCallback.getAndSet(null);
            if (callback != null) {
                callback.onConsumed(this);
            }
        }

        private void setFailedResult(RemoteException e) {
            setFailedResult(new Status(8, e.getLocalizedMessage(), null));
        }
    }
}
