package com.google.android.gms.common.download.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.download.internal.IDownloadServiceCallbacks.Stub;

public abstract class AbstractDownloadServiceCallbacks extends Stub {
    public void onDownloadStatus(Status status) throws RemoteException {
    }

    public void onRegisterStatusUpdates(Status status) throws RemoteException {
    }
}
