package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IJingleInfoStanzaListener extends IInterface {

    public static abstract class Stub extends Binder implements IJingleInfoStanzaListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IJingleInfoStanzaListener";
        static final int TRANSACTION_getAccountId = 2;
        static final int TRANSACTION_onStanzaReceived = 1;

        private static class Proxy implements IJingleInfoStanzaListener {
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

            public void onStanzaReceived(String stanza) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(stanza);
                    this.mRemote.transact(Stub.TRANSACTION_onStanzaReceived, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getAccountId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountId, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IJingleInfoStanzaListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IJingleInfoStanzaListener)) {
                return new Proxy(obj);
            }
            return (IJingleInfoStanzaListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onStanzaReceived /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    onStanzaReceived(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAccountId /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    long _result = getAccountId();
                    reply.writeNoException();
                    reply.writeLong(_result);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    long getAccountId() throws RemoteException;

    void onStanzaReceived(String str) throws RemoteException;
}
