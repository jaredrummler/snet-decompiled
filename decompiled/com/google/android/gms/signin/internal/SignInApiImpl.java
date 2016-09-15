package com.google.android.gms.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.signin.SignInApi;

public class SignInApiImpl implements SignInApi {

    /* renamed from: com.google.android.gms.signin.internal.SignInApiImpl.1 */
    class AnonymousClass1 extends SignInApiMethod<GoogleSignInResult> {
        AnonymousClass1(GoogleApiClient x0) {
            super(x0);
        }

        protected void doExecute(SignInClientImpl signInClient) throws RemoteException {
            ((ISignInService) signInClient.getService()).getCurrentAccount(new BaseSignInCallbacks() {
                public void onGetCurrentAccountComplete(Status status, GoogleSignInAccount account) throws RemoteException {
                    AnonymousClass1.this.setResult(new GoogleSignInResult(account, status));
                }
            });
        }

        protected GoogleSignInResult createFailedResult(Status status) {
            return new GoogleSignInResult(null, status);
        }
    }

    public PendingResult<GoogleSignInResult> getCurrentAccount(GoogleApiClient client) {
        return client.enqueue(new AnonymousClass1(client));
    }
}
