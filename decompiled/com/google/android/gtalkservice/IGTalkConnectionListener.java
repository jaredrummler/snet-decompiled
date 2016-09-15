package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGTalkConnectionListener extends IInterface {

    public static abstract class Stub extends Binder implements IGTalkConnectionListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IGTalkConnectionListener";
        static final int TRANSACTION_onConnectionCreated = 1;
        static final int TRANSACTION_onConnectionCreationFailed = 2;

        private static class Proxy implements IGTalkConnectionListener {
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

            public void onConnectionCreated(IGTalkConnection connection) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(connection != null ? connection.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_onConnectionCreated, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onConnectionCreationFailed(String username) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    this.mRemote.transact(Stub.TRANSACTION_onConnectionCreationFailed, _data, _reply, 0);
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

        public static IGTalkConnectionListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGTalkConnectionListener)) {
                return new Proxy(obj);
            }
            return (IGTalkConnectionListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onConnectionCreated /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    onConnectionCreated(com.google.android.gtalkservice.IGTalkConnection.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onConnectionCreationFailed /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    onConnectionCreationFailed(data.readString());
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

    void onConnectionCreated(IGTalkConnection iGTalkConnection) throws RemoteException;

    void onConnectionCreationFailed(String str) throws RemoteException;
}
