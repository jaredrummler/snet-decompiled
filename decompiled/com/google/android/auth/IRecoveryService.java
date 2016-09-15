package com.google.android.auth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.RecoveryDecision;
import com.google.android.gms.auth.RecoveryReadResponse;
import com.google.android.gms.auth.RecoveryWriteRequest;
import com.google.android.gms.auth.RecoveryWriteResponse;

public interface IRecoveryService extends IInterface {

    public static abstract class Stub extends Binder implements IRecoveryService {
        private static final String DESCRIPTOR = "com.google.android.auth.IRecoveryService";
        static final int TRANSACTION_getAccountRecoveryDecision = 1;
        static final int TRANSACTION_getRecoveryData = 2;
        static final int TRANSACTION_updateRecoveryData = 3;

        private static class Proxy implements IRecoveryService {
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

            public RecoveryDecision getAccountRecoveryDecision(String account, String displayMsg, boolean isMessageBroadUse, Bundle extras) throws RemoteException {
                int i = Stub.TRANSACTION_getAccountRecoveryDecision;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    RecoveryDecision _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(displayMsg);
                    if (!isMessageBroadUse) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    if (extras != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountRecoveryDecision);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountRecoveryDecision, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RecoveryDecision.CREATOR.createFromParcel(_reply);
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

            public RecoveryReadResponse getRecoveryData(String account, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    RecoveryReadResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getRecoveryData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RecoveryReadResponse.CREATOR.createFromParcel(_reply);
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

            public RecoveryWriteResponse updateRecoveryData(RecoveryWriteRequest request, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    RecoveryWriteResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountRecoveryDecision);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_updateRecoveryData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RecoveryWriteResponse.CREATOR.createFromParcel(_reply);
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

        public static IRecoveryService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRecoveryService)) {
                return new Proxy(obj);
            }
            return (IRecoveryService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_getAccountRecoveryDecision /*1*/:
                    boolean _arg2;
                    Bundle _arg3;
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = true;
                    } else {
                        _arg2 = false;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    RecoveryDecision _result = getAccountRecoveryDecision(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getAccountRecoveryDecision);
                        _result.writeToParcel(reply, TRANSACTION_getAccountRecoveryDecision);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getRecoveryData /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    RecoveryReadResponse _result2 = getRecoveryData(data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountRecoveryDecision);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountRecoveryDecision);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_updateRecoveryData /*3*/:
                    RecoveryWriteRequest _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = RecoveryWriteRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    RecoveryWriteResponse _result3 = updateRecoveryData(_arg02, data.readString());
                    reply.writeNoException();
                    if (_result3 != null) {
                        reply.writeInt(TRANSACTION_getAccountRecoveryDecision);
                        _result3.writeToParcel(reply, TRANSACTION_getAccountRecoveryDecision);
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

    RecoveryDecision getAccountRecoveryDecision(String str, String str2, boolean z, Bundle bundle) throws RemoteException;

    RecoveryReadResponse getRecoveryData(String str, String str2) throws RemoteException;

    RecoveryWriteResponse updateRecoveryData(RecoveryWriteRequest recoveryWriteRequest, String str) throws RemoteException;
}
