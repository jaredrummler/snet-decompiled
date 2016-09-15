package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRosterListener extends IInterface {

    public static abstract class Stub extends Binder implements IRosterListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IRosterListener";
        static final int TRANSACTION_presenceChanged = 2;
        static final int TRANSACTION_rosterChanged = 1;
        static final int TRANSACTION_selfPresenceChanged = 3;

        private static class Proxy implements IRosterListener {
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

            public void rosterChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_rosterChanged, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void presenceChanged(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_presenceChanged, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void selfPresenceChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_selfPresenceChanged, _data, _reply, 0);
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

        public static IRosterListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRosterListener)) {
                return new Proxy(obj);
            }
            return (IRosterListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_rosterChanged /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    rosterChanged();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_presenceChanged /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    presenceChanged(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_selfPresenceChanged /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    selfPresenceChanged();
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

    void presenceChanged(String str) throws RemoteException;

    void rosterChanged() throws RemoteException;

    void selfPresenceChanged() throws RemoteException;
}
