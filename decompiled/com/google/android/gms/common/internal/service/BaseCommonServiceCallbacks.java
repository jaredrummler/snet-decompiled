package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.internal.service.ICommonCallbacks.Stub;

public class BaseCommonServiceCallbacks extends Stub {
    public void onDefaultAccountCleared(int statusCode) throws RemoteException {
    }
}
