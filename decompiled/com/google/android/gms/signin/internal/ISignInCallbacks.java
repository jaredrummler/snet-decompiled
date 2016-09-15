package com.google.android.gms.signin.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public interface ISignInCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements ISignInCallbacks {
        private static final String DESCRIPTOR = "com.google.android.gms.signin.internal.ISignInCallbacks";
        static final int TRANSACTION_onAuthAccountComplete = 3;
        static final int TRANSACTION_onGetCurrentAccountComplete = 7;
        static final int TRANSACTION_onRecordConsentComplete = 6;
        static final int TRANSACTION_onSaveAccountToSessionStoreComplete = 4;
        static final int TRANSACTION_onSignInComplete = 8;

        private static class Proxy implements ISignInCallbacks {
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

            public void onAuthAccountComplete(ConnectionResult result, AuthAccountResult authResult) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (authResult != null) {
                        _data.writeInt(1);
                        authResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onAuthAccountComplete, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(1);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onSaveAccountToSessionStoreComplete, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onRecordConsentComplete(Status status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(1);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onRecordConsentComplete, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onGetCurrentAccountComplete(Status status, GoogleSignInAccount account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(1);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (account != null) {
                        _data.writeInt(1);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onGetCurrentAccountComplete, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onSignInComplete(SignInResponse signInResponse) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (signInResponse != null) {
                        _data.writeInt(1);
                        signInResponse.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onSignInComplete, _data, _reply, 0);
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

        public static ISignInCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISignInCallbacks)) {
                return new Proxy(obj);
            }
            return (ISignInCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Status _arg0;
            switch (code) {
                case TRANSACTION_onAuthAccountComplete /*3*/:
                    ConnectionResult _arg02;
                    AuthAccountResult _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (ConnectionResult) ConnectionResult.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg1 = (AuthAccountResult) AuthAccountResult.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    onAuthAccountComplete(_arg02, _arg1);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onSaveAccountToSessionStoreComplete /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Status) Status.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onSaveAccountToSessionStoreComplete(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onRecordConsentComplete /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Status) Status.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onRecordConsentComplete(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onGetCurrentAccountComplete /*7*/:
                    GoogleSignInAccount _arg12;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Status) Status.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg12 = (GoogleSignInAccount) GoogleSignInAccount.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    onGetCurrentAccountComplete(_arg0, _arg12);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onSignInComplete /*8*/:
                    SignInResponse _arg03;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (SignInResponse) SignInResponse.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    onSignInComplete(_arg03);
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

    void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult) throws RemoteException;

    void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException;

    void onRecordConsentComplete(Status status) throws RemoteException;

    void onSaveAccountToSessionStoreComplete(Status status) throws RemoteException;

    void onSignInComplete(SignInResponse signInResponse) throws RemoteException;
}
