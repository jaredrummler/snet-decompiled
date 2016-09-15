package com.google.android.auth;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public interface IAuthManagerService extends IInterface {

    public static abstract class Stub extends Binder implements IAuthManagerService {
        private static final String DESCRIPTOR = "com.google.android.auth.IAuthManagerService";
        static final int TRANSACTION_clearToken = 2;
        static final int TRANSACTION_getAccountChangeEvents = 3;
        static final int TRANSACTION_getAccounts = 6;
        static final int TRANSACTION_getToken = 1;
        static final int TRANSACTION_getTokenByAccount = 5;
        static final int TRANSACTION_removeAccount = 7;

        private static class Proxy implements IAuthManagerService {
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

            public Bundle getToken(String account, String tokenType, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(tokenType);
                    if (extras != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getToken, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            public Bundle clearToken(String token, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(token);
                    if (extras != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_clearToken, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            public AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountChangeEventsResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountChangeEvents, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (AccountChangeEventsResponse) AccountChangeEventsResponse.CREATOR.createFromParcel(_reply);
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

            public Bundle getTokenByAccount(Account account, String tokenType, Bundle extras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(tokenType);
                    if (extras != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getTokenByAccount, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            public Bundle getAccounts(Bundle data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (data != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccounts, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            public Bundle removeAccount(Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getToken);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_removeAccount, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

        public static IAuthManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAuthManagerService)) {
                return new Proxy(obj);
            }
            return (IAuthManagerService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String _arg0;
            String _arg1;
            Bundle _arg2;
            Bundle _result;
            Account _arg02;
            switch (code) {
                case TRANSACTION_getToken /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    _result = getToken(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getToken);
                        _result.writeToParcel(reply, TRANSACTION_getToken);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_clearToken /*2*/:
                    Bundle _arg12;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg12 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    _result = clearToken(_arg0, _arg12);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getToken);
                        _result.writeToParcel(reply, TRANSACTION_getToken);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountChangeEvents /*3*/:
                    AccountChangeEventsRequest _arg03;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (AccountChangeEventsRequest) AccountChangeEventsRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    AccountChangeEventsResponse _result2 = getAccountChangeEvents(_arg03);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getToken);
                        _result2.writeToParcel(reply, TRANSACTION_getToken);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getTokenByAccount /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    _result = getTokenByAccount(_arg02, _arg1, _arg2);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getToken);
                        _result.writeToParcel(reply, TRANSACTION_getToken);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccounts /*6*/:
                    Bundle _arg04;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    _result = getAccounts(_arg04);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getToken);
                        _result.writeToParcel(reply, TRANSACTION_getToken);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_removeAccount /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    _result = removeAccount(_arg02);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getToken);
                        _result.writeToParcel(reply, TRANSACTION_getToken);
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

    Bundle clearToken(String str, Bundle bundle) throws RemoteException;

    AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException;

    Bundle getAccounts(Bundle bundle) throws RemoteException;

    Bundle getToken(String str, String str2, Bundle bundle) throws RemoteException;

    Bundle getTokenByAccount(Account account, String str, Bundle bundle) throws RemoteException;

    Bundle removeAccount(Account account) throws RemoteException;
}
