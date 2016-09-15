package com.google.android.gms.common.download.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.download.DownloadDetails;

public interface IDownloadService extends IInterface {

    public static abstract class Stub extends Binder implements IDownloadService {
        private static final String DESCRIPTOR = "com.google.android.gms.common.download.internal.IDownloadService";
        static final int TRANSACTION_getDownloadStatus = 1;
        static final int TRANSACTION_registerDownload = 4;
        static final int TRANSACTION_registerDownloadStatusUpdates = 3;
        static final int TRANSACTION_tryDownload = 2;
        static final int TRANSACTION_unregisterDownload = 5;

        private static class Proxy implements IDownloadService {
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

            public void getDownloadStatus(IDownloadServiceCallbacks callbacks, String filename) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(filename);
                    this.mRemote.transact(Stub.TRANSACTION_getDownloadStatus, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void tryDownload(IDownloadServiceCallbacks callbacks, String filename) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(filename);
                    this.mRemote.transact(Stub.TRANSACTION_tryDownload, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void registerDownloadStatusUpdates(IDownloadServiceCallbacks callbacks, String filename) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(filename);
                    this.mRemote.transact(Stub.TRANSACTION_registerDownloadStatusUpdates, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void registerDownload(IDownloadServiceCallbacks callbacks, DownloadDetails details) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (details != null) {
                        _data.writeInt(Stub.TRANSACTION_getDownloadStatus);
                        details.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_registerDownload, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void unregisterDownload(IDownloadServiceCallbacks callbacks, String filename) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(filename);
                    this.mRemote.transact(Stub.TRANSACTION_unregisterDownload, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDownloadService)) {
                return new Proxy(obj);
            }
            return (IDownloadService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_getDownloadStatus /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    getDownloadStatus(com.google.android.gms.common.download.internal.IDownloadServiceCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_tryDownload /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    tryDownload(com.google.android.gms.common.download.internal.IDownloadServiceCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_registerDownloadStatusUpdates /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    registerDownloadStatusUpdates(com.google.android.gms.common.download.internal.IDownloadServiceCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_registerDownload /*4*/:
                    DownloadDetails _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    IDownloadServiceCallbacks _arg0 = com.google.android.gms.common.download.internal.IDownloadServiceCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (DownloadDetails) DownloadDetails.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    registerDownload(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_unregisterDownload /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterDownload(com.google.android.gms.common.download.internal.IDownloadServiceCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void getDownloadStatus(IDownloadServiceCallbacks iDownloadServiceCallbacks, String str) throws RemoteException;

    void registerDownload(IDownloadServiceCallbacks iDownloadServiceCallbacks, DownloadDetails downloadDetails) throws RemoteException;

    void registerDownloadStatusUpdates(IDownloadServiceCallbacks iDownloadServiceCallbacks, String str) throws RemoteException;

    void tryDownload(IDownloadServiceCallbacks iDownloadServiceCallbacks, String str) throws RemoteException;

    void unregisterDownload(IDownloadServiceCallbacks iDownloadServiceCallbacks, String str) throws RemoteException;
}
