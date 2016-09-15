package com.google.android.gms.common.download.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public interface IDownloadServiceCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IDownloadServiceCallbacks {
        private static final String DESCRIPTOR = "com.google.android.gms.common.download.internal.IDownloadServiceCallbacks";
        static final int TRANSACTION_onDownloadStatus = 1;
        static final int TRANSACTION_onRegisterStatusUpdates = 2;

        private static class Proxy implements IDownloadServiceCallbacks {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void onDownloadStatus(Status status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(Stub.TRANSACTION_onDownloadStatus);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onDownloadStatus, _data, null, Stub.TRANSACTION_onDownloadStatus);
                } finally {
                    _data.recycle();
                }
            }

            public void onRegisterStatusUpdates(Status status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(Stub.TRANSACTION_onDownloadStatus);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onRegisterStatusUpdates, _data, null, Stub.TRANSACTION_onDownloadStatus);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadServiceCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDownloadServiceCallbacks)) {
                return new Proxy(obj);
            }
            return (IDownloadServiceCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Status _arg0;
            switch (code) {
                case TRANSACTION_onDownloadStatus /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Status) Status.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onDownloadStatus(_arg0);
                    return true;
                case TRANSACTION_onRegisterStatusUpdates /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Status) Status.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onRegisterStatusUpdates(_arg0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onDownloadStatus(Status status) throws RemoteException;

    void onRegisterStatusUpdates(Status status) throws RemoteException;
}
