package com.google.android.gms.auth.firstparty.devicesignals;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDeviceSignalsService extends IInterface {

    public static abstract class Stub extends Binder implements IDeviceSignalsService {
        private static final String DESCRIPTOR = "com.google.android.gms.auth.firstparty.devicesignals.IDeviceSignalsService";
        static final int TRANSACTION_getLastSecureUnlockTime = 1;
        static final int TRANSACTION_getLockScreenSecureDuration = 2;

        private static class Proxy implements IDeviceSignalsService {
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

            public long getLastSecureUnlockTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getLastSecureUnlockTime, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getLockScreenSecureDuration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getLockScreenSecureDuration, _data, _reply, 0);
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

        public static IDeviceSignalsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDeviceSignalsService)) {
                return new Proxy(obj);
            }
            return (IDeviceSignalsService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            long _result;
            switch (code) {
                case TRANSACTION_getLastSecureUnlockTime /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getLastSecureUnlockTime();
                    reply.writeNoException();
                    reply.writeLong(_result);
                    return true;
                case TRANSACTION_getLockScreenSecureDuration /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getLockScreenSecureDuration();
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

    long getLastSecureUnlockTime() throws RemoteException;

    long getLockScreenSecureDuration() throws RemoteException;
}
