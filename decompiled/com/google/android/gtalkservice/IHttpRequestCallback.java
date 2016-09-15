package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IHttpRequestCallback extends IInterface {

    public static abstract class Stub extends Binder implements IHttpRequestCallback {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IHttpRequestCallback";
        static final int TRANSACTION_requestComplete = 1;

        private static class Proxy implements IHttpRequestCallback {
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

            public void requestComplete(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    this.mRemote.transact(Stub.TRANSACTION_requestComplete, _data, null, Stub.TRANSACTION_requestComplete);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHttpRequestCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IHttpRequestCallback)) {
                return new Proxy(obj);
            }
            return (IHttpRequestCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_requestComplete /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    requestComplete(data.createByteArray());
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void requestComplete(byte[] bArr) throws RemoteException;
}
