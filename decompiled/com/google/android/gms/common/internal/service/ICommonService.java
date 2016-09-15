package com.google.android.gms.common.internal.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICommonService extends IInterface {

    public static abstract class Stub extends Binder implements ICommonService {
        private static final String DESCRIPTOR = "com.google.android.gms.common.internal.service.ICommonService";
        static final int TRANSACTION_clearDefaultAccount = 1;

        private static class Proxy implements ICommonService {
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

            public void clearDefaultAccount(ICommonCallbacks commonCallbacks) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (commonCallbacks != null) {
                        iBinder = commonCallbacks.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_clearDefaultAccount, _data, null, Stub.TRANSACTION_clearDefaultAccount);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICommonService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICommonService)) {
                return new Proxy(obj);
            }
            return (ICommonService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_clearDefaultAccount /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    clearDefaultAccount(com.google.android.gms.common.internal.service.ICommonCallbacks.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void clearDefaultAccount(ICommonCallbacks iCommonCallbacks) throws RemoteException;
}
