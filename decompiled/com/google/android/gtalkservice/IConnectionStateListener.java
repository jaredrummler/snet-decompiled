package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IConnectionStateListener extends IInterface {

    public static abstract class Stub extends Binder implements IConnectionStateListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IConnectionStateListener";
        static final int TRANSACTION_connectionStateChanged = 1;

        private static class Proxy implements IConnectionStateListener {
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

            public void connectionStateChanged(ConnectionState state, ConnectionError error, long accountId, String username) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(Stub.TRANSACTION_connectionStateChanged);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (error != null) {
                        _data.writeInt(Stub.TRANSACTION_connectionStateChanged);
                        error.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(accountId);
                    _data.writeString(username);
                    this.mRemote.transact(Stub.TRANSACTION_connectionStateChanged, _data, _reply, 0);
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

        public static IConnectionStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IConnectionStateListener)) {
                return new Proxy(obj);
            }
            return (IConnectionStateListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_connectionStateChanged /*1*/:
                    ConnectionState _arg0;
                    ConnectionError _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (ConnectionState) ConnectionState.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (ConnectionError) ConnectionError.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    connectionStateChanged(_arg0, _arg1, data.readLong(), data.readString());
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

    void connectionStateChanged(ConnectionState connectionState, ConnectionError connectionError, long j, String str) throws RemoteException;
}
