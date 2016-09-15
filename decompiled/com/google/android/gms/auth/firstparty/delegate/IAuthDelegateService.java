package com.google.android.gms.auth.firstparty.delegate;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAuthDelegateService extends IInterface {

    public static abstract class Stub extends Binder implements IAuthDelegateService {
        private static final String DESCRIPTOR = "com.google.android.gms.auth.firstparty.delegate.IAuthDelegateService";
        static final int TRANSACTION_getConfirmCredentialsWorkflowIntent = 4;
        static final int TRANSACTION_getSetupAccountWorkflowIntent = 1;
        static final int TRANSACTION_getTokenRetrievalWorkflowIntent = 2;
        static final int TRANSACTION_getUpdateCredentialsWorkflowIntent = 3;

        private static class Proxy implements IAuthDelegateService {
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

            public PendingIntent getSetupAccountWorkflowIntent(SetupAccountWorkflowRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PendingIntent _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getSetupAccountWorkflowIntent);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getSetupAccountWorkflowIntent, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
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

            public PendingIntent getTokenRetrievalWorkflowIntent(TokenWorkflowRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PendingIntent _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getSetupAccountWorkflowIntent);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getTokenRetrievalWorkflowIntent, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
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

            public PendingIntent getUpdateCredentialsWorkflowIntent(UpdateCredentialsWorkflowRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PendingIntent _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getSetupAccountWorkflowIntent);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getUpdateCredentialsWorkflowIntent, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
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

            public PendingIntent getConfirmCredentialsWorkflowIntent(ConfirmCredentialsWorkflowRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PendingIntent _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getSetupAccountWorkflowIntent);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getConfirmCredentialsWorkflowIntent, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
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

        public static IAuthDelegateService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAuthDelegateService)) {
                return new Proxy(obj);
            }
            return (IAuthDelegateService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            PendingIntent _result;
            switch (code) {
                case TRANSACTION_getSetupAccountWorkflowIntent /*1*/:
                    SetupAccountWorkflowRequest _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = SetupAccountWorkflowRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _result = getSetupAccountWorkflowIntent(_arg0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getSetupAccountWorkflowIntent);
                        _result.writeToParcel(reply, TRANSACTION_getSetupAccountWorkflowIntent);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getTokenRetrievalWorkflowIntent /*2*/:
                    TokenWorkflowRequest _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = TokenWorkflowRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    _result = getTokenRetrievalWorkflowIntent(_arg02);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getSetupAccountWorkflowIntent);
                        _result.writeToParcel(reply, TRANSACTION_getSetupAccountWorkflowIntent);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getUpdateCredentialsWorkflowIntent /*3*/:
                    UpdateCredentialsWorkflowRequest _arg03;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = UpdateCredentialsWorkflowRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    _result = getUpdateCredentialsWorkflowIntent(_arg03);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getSetupAccountWorkflowIntent);
                        _result.writeToParcel(reply, TRANSACTION_getSetupAccountWorkflowIntent);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getConfirmCredentialsWorkflowIntent /*4*/:
                    ConfirmCredentialsWorkflowRequest _arg04;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = ConfirmCredentialsWorkflowRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    _result = getConfirmCredentialsWorkflowIntent(_arg04);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getSetupAccountWorkflowIntent);
                        _result.writeToParcel(reply, TRANSACTION_getSetupAccountWorkflowIntent);
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

    PendingIntent getConfirmCredentialsWorkflowIntent(ConfirmCredentialsWorkflowRequest confirmCredentialsWorkflowRequest) throws RemoteException;

    PendingIntent getSetupAccountWorkflowIntent(SetupAccountWorkflowRequest setupAccountWorkflowRequest) throws RemoteException;

    PendingIntent getTokenRetrievalWorkflowIntent(TokenWorkflowRequest tokenWorkflowRequest) throws RemoteException;

    PendingIntent getUpdateCredentialsWorkflowIntent(UpdateCredentialsWorkflowRequest updateCredentialsWorkflowRequest) throws RemoteException;
}
