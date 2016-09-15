package com.google.android.gms.auth.appcert;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAppCertService extends IInterface {

    public static abstract class Stub extends Binder implements IAppCertService {
        private static final String DESCRIPTOR = "com.google.android.gms.auth.appcert.IAppCertService";
        static final int TRANSACTION_getAppCert = 2;
        static final int TRANSACTION_initializeDeviceKey = 1;

        private static class Proxy implements IAppCertService {
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

            public boolean initializeDeviceKey() throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_initializeDeviceKey, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getAppCert(String packageNameToCertify) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageNameToCertify);
                    this.mRemote.transact(Stub.TRANSACTION_getAppCert, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
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

        public static IAppCertService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAppCertService)) {
                return new Proxy(obj);
            }
            return (IAppCertService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_initializeDeviceKey /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = initializeDeviceKey();
                    reply.writeNoException();
                    reply.writeInt(_result ? TRANSACTION_initializeDeviceKey : 0);
                    return true;
                case TRANSACTION_getAppCert /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _result2 = getAppCert(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    String getAppCert(String str) throws RemoteException;

    boolean initializeDeviceKey() throws RemoteException;
}
