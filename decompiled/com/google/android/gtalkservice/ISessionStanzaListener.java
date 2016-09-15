package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ISessionStanzaListener extends IInterface {

    public static abstract class Stub extends Binder implements ISessionStanzaListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.ISessionStanzaListener";
        static final int TRANSACTION_getAccountId = 3;
        static final int TRANSACTION_onStanzaReceived = 1;
        static final int TRANSACTION_onStanzaResponse = 2;

        private static class Proxy implements ISessionStanzaListener {
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

            public void onStanzaReceived(String jid, String stanza) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(jid);
                    _data.writeString(stanza);
                    this.mRemote.transact(Stub.TRANSACTION_onStanzaReceived, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onStanzaResponse(String jid, String original, String response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(jid);
                    _data.writeString(original);
                    _data.writeString(response);
                    this.mRemote.transact(Stub.TRANSACTION_onStanzaResponse, _data, _reply, 0);
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

        public static ISessionStanzaListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISessionStanzaListener)) {
                return new Proxy(obj);
            }
            return (ISessionStanzaListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onStanzaReceived /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    onStanzaReceived(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onStanzaResponse /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    onStanzaResponse(data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAccountId /*3*/:
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

    void onStanzaReceived(String str, String str2) throws RemoteException;

    void onStanzaResponse(String str, String str2, String str3) throws RemoteException;
}
