package com.google.android.gms.auth.frp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IFrpService extends IInterface {

    public static abstract class Stub extends Binder implements IFrpService {
        private static final String DESCRIPTOR = "com.google.android.gms.auth.frp.IFrpService";
        static final int TRANSACTION_isChallengeRequired = 1;
        static final int TRANSACTION_isChallengeSupported = 2;
        static final int TRANSACTION_unlockFactoryResetProtection = 3;

        private static class Proxy implements IFrpService {
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

            public boolean isChallengeRequired() throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isChallengeRequired, _data, _reply, 0);
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

            public boolean isChallengeSupported() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isChallengeSupported, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public UnlockFactoryResetProtectionResponse unlockFactoryResetProtection(UnlockFactoryResetProtectionRequest unlockRequest) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    UnlockFactoryResetProtectionResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (unlockRequest != null) {
                        _data.writeInt(Stub.TRANSACTION_isChallengeRequired);
                        unlockRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_unlockFactoryResetProtection, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = UnlockFactoryResetProtectionResponse.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IFrpService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IFrpService)) {
                return new Proxy(obj);
            }
            return (IFrpService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            boolean _result;
            switch (code) {
                case TRANSACTION_isChallengeRequired /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = isChallengeRequired();
                    reply.writeNoException();
                    if (_result) {
                        i = TRANSACTION_isChallengeRequired;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_isChallengeSupported /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = isChallengeSupported();
                    reply.writeNoException();
                    if (_result) {
                        i = TRANSACTION_isChallengeRequired;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_unlockFactoryResetProtection /*3*/:
                    UnlockFactoryResetProtectionRequest _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = UnlockFactoryResetProtectionRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    UnlockFactoryResetProtectionResponse _result2 = unlockFactoryResetProtection(_arg0);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_isChallengeRequired);
                        _result2.writeToParcel(reply, TRANSACTION_isChallengeRequired);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean isChallengeRequired() throws RemoteException;

    boolean isChallengeSupported() throws RemoteException;

    UnlockFactoryResetProtectionResponse unlockFactoryResetProtection(UnlockFactoryResetProtectionRequest unlockFactoryResetProtectionRequest) throws RemoteException;
}
