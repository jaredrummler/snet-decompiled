package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.IResolveAccountCallbacks;
import com.google.android.gms.common.internal.ResolveAccountRequest;

public interface ISignInService extends IInterface {

    public static abstract class Stub extends Binder implements ISignInService {
        private static final String DESCRIPTOR = "com.google.android.gms.signin.internal.ISignInService";
        static final int TRANSACTION_authAccount = 2;
        static final int TRANSACTION_clearAccountFromSessionStore = 7;
        static final int TRANSACTION_getCurrentAccount = 11;
        static final int TRANSACTION_onCheckServerAuthorization = 3;
        static final int TRANSACTION_onUploadServerAuthCode = 4;
        static final int TRANSACTION_recordConsent = 10;
        static final int TRANSACTION_resolveAccount = 5;
        static final int TRANSACTION_saveAccountToSessionStore = 8;
        static final int TRANSACTION_saveDefaultAccountToSharedPref = 9;
        static final int TRANSACTION_signIn = 12;

        private static class Proxy implements ISignInService {
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

            public void authAccount(AuthAccountRequest authAccountRequest, ISignInCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (authAccountRequest != null) {
                        _data.writeInt(1);
                        authAccountRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_authAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onCheckServerAuthorization(CheckServerAuthResult result) throws RemoteException {
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
                    this.mRemote.transact(Stub.TRANSACTION_onCheckServerAuthorization, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onUploadServerAuthCode(boolean successful) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (successful) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_onUploadServerAuthCode, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void resolveAccount(ResolveAccountRequest resolveAccountRequest, IResolveAccountCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (resolveAccountRequest != null) {
                        _data.writeInt(1);
                        resolveAccountRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_resolveAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void clearAccountFromSessionStore(int sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    this.mRemote.transact(Stub.TRANSACTION_clearAccountFromSessionStore, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void saveAccountToSessionStore(int sessionId, Account account, ISignInCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sessionId);
                    if (account != null) {
                        _data.writeInt(1);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_saveAccountToSessionStore, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void saveDefaultAccountToSharedPref(IAccountAccessor accountAccessor, int sessionId, boolean showCrossClientAuthToast) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(accountAccessor != null ? accountAccessor.asBinder() : null);
                    _data.writeInt(sessionId);
                    if (showCrossClientAuthToast) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_saveDefaultAccountToSharedPref, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void recordConsent(RecordConsentRequest request, ISignInCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_recordConsent, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getCurrentAccount(ISignInCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_getCurrentAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void signIn(SignInRequest request, ISignInCallbacks callbacks) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_signIn, _data, _reply, 0);
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

        public static ISignInService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISignInService)) {
                return new Proxy(obj);
            }
            return (ISignInService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_authAccount /*2*/:
                    AuthAccountRequest _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (AuthAccountRequest) AuthAccountRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    authAccount(_arg0, com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onCheckServerAuthorization /*3*/:
                    CheckServerAuthResult _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (CheckServerAuthResult) CheckServerAuthResult.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    onCheckServerAuthorization(_arg02);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onUploadServerAuthCode /*4*/:
                    boolean _arg03;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = true;
                    } else {
                        _arg03 = false;
                    }
                    onUploadServerAuthCode(_arg03);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_resolveAccount /*5*/:
                    ResolveAccountRequest _arg04;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = (ResolveAccountRequest) ResolveAccountRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    resolveAccount(_arg04, com.google.android.gms.common.internal.IResolveAccountCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_clearAccountFromSessionStore /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    clearAccountFromSessionStore(data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_saveAccountToSessionStore /*8*/:
                    Account _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    int _arg05 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    saveAccountToSessionStore(_arg05, _arg1, com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_saveDefaultAccountToSharedPref /*9*/:
                    boolean _arg2;
                    data.enforceInterface(DESCRIPTOR);
                    IAccountAccessor _arg06 = com.google.android.gms.common.internal.IAccountAccessor.Stub.asInterface(data.readStrongBinder());
                    int _arg12 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg2 = true;
                    } else {
                        _arg2 = false;
                    }
                    saveDefaultAccountToSharedPref(_arg06, _arg12, _arg2);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_recordConsent /*10*/:
                    RecordConsentRequest _arg07;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg07 = (RecordConsentRequest) RecordConsentRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg07 = null;
                    }
                    recordConsent(_arg07, com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getCurrentAccount /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    getCurrentAccount(com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_signIn /*12*/:
                    SignInRequest _arg08;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg08 = (SignInRequest) SignInRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg08 = null;
                    }
                    signIn(_arg08, com.google.android.gms.signin.internal.ISignInCallbacks.Stub.asInterface(data.readStrongBinder()));
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

    void authAccount(AuthAccountRequest authAccountRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void clearAccountFromSessionStore(int i) throws RemoteException;

    void getCurrentAccount(ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void onCheckServerAuthorization(CheckServerAuthResult checkServerAuthResult) throws RemoteException;

    void onUploadServerAuthCode(boolean z) throws RemoteException;

    void recordConsent(RecordConsentRequest recordConsentRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void resolveAccount(ResolveAccountRequest resolveAccountRequest, IResolveAccountCallbacks iResolveAccountCallbacks) throws RemoteException;

    void saveAccountToSessionStore(int i, Account account, ISignInCallbacks iSignInCallbacks) throws RemoteException;

    void saveDefaultAccountToSharedPref(IAccountAccessor iAccountAccessor, int i, boolean z) throws RemoteException;

    void signIn(SignInRequest signInRequest, ISignInCallbacks iSignInCallbacks) throws RemoteException;
}
