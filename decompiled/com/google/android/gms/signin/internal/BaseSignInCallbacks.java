package com.google.android.gms.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.signin.internal.ISignInCallbacks.Stub;

public class BaseSignInCallbacks extends Stub {
    public void onAuthAccountComplete(ConnectionResult result, AuthAccountResult authResult) throws RemoteException {
    }

    public void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException {
    }

    public void onRecordConsentComplete(Status status) throws RemoteException {
    }

    public void onGetCurrentAccountComplete(Status status, GoogleSignInAccount account) throws RemoteException {
    }

    public void onSignInComplete(SignInResponse signInResponse) throws RemoteException {
    }
}
