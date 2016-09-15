package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface ISignInButtonCreator extends IInterface {

    public static abstract class Stub extends Binder implements ISignInButtonCreator {
        private static final String DESCRIPTOR = "com.google.android.gms.common.internal.ISignInButtonCreator";
        static final int TRANSACTION_newSignInButton = 1;
        static final int TRANSACTION_newSignInButtonFromConfig = 2;

        private static class Proxy implements ISignInButtonCreator {
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

            public IObjectWrapper newSignInButton(IObjectWrapper wrappedContext, int buttonSize, int colorScheme) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(wrappedContext != null ? wrappedContext.asBinder() : null);
                    _data.writeInt(buttonSize);
                    _data.writeInt(colorScheme);
                    this.mRemote.transact(Stub.TRANSACTION_newSignInButton, _data, _reply, 0);
                    _reply.readException();
                    IObjectWrapper _result = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IObjectWrapper newSignInButtonFromConfig(IObjectWrapper wrappedContext, SignInButtonConfig config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(wrappedContext != null ? wrappedContext.asBinder() : null);
                    if (config != null) {
                        _data.writeInt(Stub.TRANSACTION_newSignInButton);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_newSignInButtonFromConfig, _data, _reply, 0);
                    _reply.readException();
                    IObjectWrapper _result = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(_reply.readStrongBinder());
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

        public static ISignInButtonCreator asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISignInButtonCreator)) {
                return new Proxy(obj);
            }
            return (ISignInButtonCreator) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IBinder iBinder = null;
            IObjectWrapper _result;
            switch (code) {
                case TRANSACTION_newSignInButton /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = newSignInButton(com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readInt());
                    reply.writeNoException();
                    if (_result != null) {
                        iBinder = _result.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_newSignInButtonFromConfig /*2*/:
                    SignInButtonConfig _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    IObjectWrapper _arg0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = (SignInButtonConfig) SignInButtonConfig.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    _result = newSignInButtonFromConfig(_arg0, _arg1);
                    reply.writeNoException();
                    if (_result != null) {
                        iBinder = _result.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2) throws RemoteException;

    IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) throws RemoteException;
}
