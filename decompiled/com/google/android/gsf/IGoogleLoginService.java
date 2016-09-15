package com.google.android.gsf;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGoogleLoginService extends IInterface {

    public static abstract class Stub extends Binder implements IGoogleLoginService {
        private static final String DESCRIPTOR = "com.google.android.gsf.IGoogleLoginService";
        static final int TRANSACTION_blockingGetCredentials = 5;
        static final int TRANSACTION_deleteAllAccounts = 15;
        static final int TRANSACTION_deleteOneAccount = 14;
        static final int TRANSACTION_getAccount = 3;
        static final int TRANSACTION_getAccounts = 1;
        static final int TRANSACTION_getAndroidId = 7;
        static final int TRANSACTION_getPrimaryAccount = 2;
        static final int TRANSACTION_invalidateAuthToken = 6;
        static final int TRANSACTION_peekCredentials = 4;
        static final int TRANSACTION_saveAuthToken = 10;
        static final int TRANSACTION_saveNewAccount = 9;
        static final int TRANSACTION_saveUsernameAndPassword = 13;
        static final int TRANSACTION_tryNewAccount = 8;
        static final int TRANSACTION_updatePassword = 11;
        static final int TRANSACTION_verifyStoredPassword = 12;
        static final int TRANSACTION_waitForAndroidId = 16;

        private static class Proxy implements IGoogleLoginService {
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

            public String[] getAccounts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAccounts, _data, _reply, 0);
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getPrimaryAccount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getPrimaryAccount, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getAccount(boolean requireGoogle) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (requireGoogle) {
                        i = Stub.TRANSACTION_getAccounts;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_getAccount, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String peekCredentials(String username, String service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    _data.writeString(service);
                    this.mRemote.transact(Stub.TRANSACTION_peekCredentials, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public GoogleLoginCredentialsResult blockingGetCredentials(String username, String service, boolean notifyAuthFailure) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    GoogleLoginCredentialsResult _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    _data.writeString(service);
                    if (notifyAuthFailure) {
                        i = Stub.TRANSACTION_getAccounts;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_blockingGetCredentials, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (GoogleLoginCredentialsResult) GoogleLoginCredentialsResult.CREATOR.createFromParcel(_reply);
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

            public void invalidateAuthToken(String authTokenToInvalidate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(authTokenToInvalidate);
                    this.mRemote.transact(Stub.TRANSACTION_invalidateAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getAndroidId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAndroidId, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void tryNewAccount(LoginData data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccounts);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_tryNewAccount, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        data.readFromParcel(_reply);
                    }
                    _reply.recycle();
                    _data.recycle();
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void saveNewAccount(LoginData data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccounts);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_saveNewAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void saveAuthToken(String username, String service, String authToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    _data.writeString(service);
                    _data.writeString(authToken);
                    this.mRemote.transact(Stub.TRANSACTION_saveAuthToken, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void updatePassword(LoginData data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccounts);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_updatePassword, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        data.readFromParcel(_reply);
                    }
                    _reply.recycle();
                    _data.recycle();
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean verifyStoredPassword(String username, String password) throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    _data.writeString(password);
                    this.mRemote.transact(Stub.TRANSACTION_verifyStoredPassword, _data, _reply, 0);
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

            public void saveUsernameAndPassword(String username, String password, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    _data.writeString(password);
                    _data.writeInt(flags);
                    this.mRemote.transact(Stub.TRANSACTION_saveUsernameAndPassword, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void deleteOneAccount(String username) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    this.mRemote.transact(Stub.TRANSACTION_deleteOneAccount, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void deleteAllAccounts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_deleteAllAccounts, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int waitForAndroidId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_waitForAndroidId, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
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

        public static IGoogleLoginService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGoogleLoginService)) {
                return new Proxy(obj);
            }
            return (IGoogleLoginService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            String _result;
            LoginData _arg0;
            switch (code) {
                case TRANSACTION_getAccounts /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result2 = getAccounts();
                    reply.writeNoException();
                    reply.writeStringArray(_result2);
                    return true;
                case TRANSACTION_getPrimaryAccount /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getPrimaryAccount();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getAccount /*3*/:
                    boolean _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = true;
                    } else {
                        _arg02 = false;
                    }
                    _result = getAccount(_arg02);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_peekCredentials /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = peekCredentials(data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_blockingGetCredentials /*5*/:
                    boolean _arg2;
                    data.enforceInterface(DESCRIPTOR);
                    String _arg03 = data.readString();
                    String _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = true;
                    } else {
                        _arg2 = false;
                    }
                    GoogleLoginCredentialsResult _result3 = blockingGetCredentials(_arg03, _arg1, _arg2);
                    reply.writeNoException();
                    if (_result3 != null) {
                        reply.writeInt(TRANSACTION_getAccounts);
                        _result3.writeToParcel(reply, TRANSACTION_getAccounts);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_invalidateAuthToken /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    invalidateAuthToken(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAndroidId /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    long _result4 = getAndroidId();
                    reply.writeNoException();
                    reply.writeLong(_result4);
                    return true;
                case TRANSACTION_tryNewAccount /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (LoginData) LoginData.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    tryNewAccount(_arg0);
                    reply.writeNoException();
                    if (_arg0 != null) {
                        reply.writeInt(TRANSACTION_getAccounts);
                        _arg0.writeToParcel(reply, TRANSACTION_getAccounts);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_saveNewAccount /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (LoginData) LoginData.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    saveNewAccount(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_saveAuthToken /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    saveAuthToken(data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updatePassword /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (LoginData) LoginData.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    updatePassword(_arg0);
                    reply.writeNoException();
                    if (_arg0 != null) {
                        reply.writeInt(TRANSACTION_getAccounts);
                        _arg0.writeToParcel(reply, TRANSACTION_getAccounts);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_verifyStoredPassword /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = verifyStoredPassword(data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result5) {
                        i = TRANSACTION_getAccounts;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_saveUsernameAndPassword /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    saveUsernameAndPassword(data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_deleteOneAccount /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    deleteOneAccount(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_deleteAllAccounts /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    deleteAllAccounts();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_waitForAndroidId /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = waitForAndroidId();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    GoogleLoginCredentialsResult blockingGetCredentials(String str, String str2, boolean z) throws RemoteException;

    void deleteAllAccounts() throws RemoteException;

    void deleteOneAccount(String str) throws RemoteException;

    String getAccount(boolean z) throws RemoteException;

    String[] getAccounts() throws RemoteException;

    long getAndroidId() throws RemoteException;

    String getPrimaryAccount() throws RemoteException;

    void invalidateAuthToken(String str) throws RemoteException;

    String peekCredentials(String str, String str2) throws RemoteException;

    void saveAuthToken(String str, String str2, String str3) throws RemoteException;

    void saveNewAccount(LoginData loginData) throws RemoteException;

    void saveUsernameAndPassword(String str, String str2, int i) throws RemoteException;

    void tryNewAccount(LoginData loginData) throws RemoteException;

    void updatePassword(LoginData loginData) throws RemoteException;

    boolean verifyStoredPassword(String str, String str2) throws RemoteException;

    int waitForAndroidId() throws RemoteException;
}
