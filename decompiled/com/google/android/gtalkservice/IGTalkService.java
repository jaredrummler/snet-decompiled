package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IGTalkService extends IInterface {

    public static abstract class Stub extends Binder implements IGTalkService {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IGTalkService";
        static final int TRANSACTION_createGTalkConnection = 1;
        static final int TRANSACTION_dismissAllNotifications = 6;
        static final int TRANSACTION_dismissNotificationFor = 8;
        static final int TRANSACTION_dismissNotificationsForAccount = 7;
        static final int TRANSACTION_getActiveConnections = 2;
        static final int TRANSACTION_getConnectionForUser = 3;
        static final int TRANSACTION_getDefaultConnection = 4;
        static final int TRANSACTION_getDeviceStorageLow = 9;
        static final int TRANSACTION_getImSessionForAccountId = 5;
        static final int TRANSACTION_printDiagnostics = 10;
        static final int TRANSACTION_setTalkForegroundState = 11;

        private static class Proxy implements IGTalkService {
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

            public void createGTalkConnection(String username, IGTalkConnectionListener listener) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    if (listener != null) {
                        iBinder = listener.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_createGTalkConnection, _data, null, Stub.TRANSACTION_createGTalkConnection);
                } finally {
                    _data.recycle();
                }
            }

            public List getActiveConnections() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getActiveConnections, _data, _reply, 0);
                    _reply.readException();
                    List _result = _reply.readArrayList(getClass().getClassLoader());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IGTalkConnection getConnectionForUser(String username) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    this.mRemote.transact(Stub.TRANSACTION_getConnectionForUser, _data, _reply, 0);
                    _reply.readException();
                    IGTalkConnection _result = com.google.android.gtalkservice.IGTalkConnection.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IGTalkConnection getDefaultConnection() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getDefaultConnection, _data, _reply, 0);
                    _reply.readException();
                    IGTalkConnection _result = com.google.android.gtalkservice.IGTalkConnection.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IImSession getImSessionForAccountId(long accountId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(accountId);
                    this.mRemote.transact(Stub.TRANSACTION_getImSessionForAccountId, _data, _reply, 0);
                    _reply.readException();
                    IImSession _result = com.google.android.gtalkservice.IImSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void dismissAllNotifications() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_dismissAllNotifications, _data, null, Stub.TRANSACTION_createGTalkConnection);
                } finally {
                    _data.recycle();
                }
            }

            public void dismissNotificationsForAccount(long accountId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(accountId);
                    this.mRemote.transact(Stub.TRANSACTION_dismissNotificationsForAccount, _data, null, Stub.TRANSACTION_createGTalkConnection);
                } finally {
                    _data.recycle();
                }
            }

            public void dismissNotificationFor(String contact, long accountId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    _data.writeLong(accountId);
                    this.mRemote.transact(Stub.TRANSACTION_dismissNotificationFor, _data, null, Stub.TRANSACTION_createGTalkConnection);
                } finally {
                    _data.recycle();
                }
            }

            public boolean getDeviceStorageLow() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getDeviceStorageLow, _data, _reply, 0);
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

            public String printDiagnostics() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_printDiagnostics, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setTalkForegroundState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_setTalkForegroundState, _data, null, Stub.TRANSACTION_createGTalkConnection);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGTalkService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGTalkService)) {
                return new Proxy(obj);
            }
            return (IGTalkService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IBinder iBinder = null;
            IGTalkConnection _result;
            switch (code) {
                case TRANSACTION_createGTalkConnection /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    createGTalkConnection(data.readString(), com.google.android.gtalkservice.IGTalkConnectionListener.Stub.asInterface(data.readStrongBinder()));
                    return true;
                case TRANSACTION_getActiveConnections /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    List _result2 = getActiveConnections();
                    reply.writeNoException();
                    reply.writeList(_result2);
                    return true;
                case TRANSACTION_getConnectionForUser /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getConnectionForUser(data.readString());
                    reply.writeNoException();
                    if (_result != null) {
                        iBinder = _result.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_getDefaultConnection /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getDefaultConnection();
                    reply.writeNoException();
                    if (_result != null) {
                        iBinder = _result.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_getImSessionForAccountId /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    IImSession _result3 = getImSessionForAccountId(data.readLong());
                    reply.writeNoException();
                    if (_result3 != null) {
                        iBinder = _result3.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_dismissAllNotifications /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    dismissAllNotifications();
                    return true;
                case TRANSACTION_dismissNotificationsForAccount /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    dismissNotificationsForAccount(data.readLong());
                    return true;
                case TRANSACTION_dismissNotificationFor /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    dismissNotificationFor(data.readString(), data.readLong());
                    return true;
                case TRANSACTION_getDeviceStorageLow /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result4 = getDeviceStorageLow();
                    reply.writeNoException();
                    reply.writeInt(_result4 ? TRANSACTION_createGTalkConnection : 0);
                    return true;
                case TRANSACTION_printDiagnostics /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = printDiagnostics();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case TRANSACTION_setTalkForegroundState /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    setTalkForegroundState();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void createGTalkConnection(String str, IGTalkConnectionListener iGTalkConnectionListener) throws RemoteException;

    void dismissAllNotifications() throws RemoteException;

    void dismissNotificationFor(String str, long j) throws RemoteException;

    void dismissNotificationsForAccount(long j) throws RemoteException;

    List getActiveConnections() throws RemoteException;

    IGTalkConnection getConnectionForUser(String str) throws RemoteException;

    IGTalkConnection getDefaultConnection() throws RemoteException;

    boolean getDeviceStorageLow() throws RemoteException;

    IImSession getImSessionForAccountId(long j) throws RemoteException;

    String printDiagnostics() throws RemoteException;

    void setTalkForegroundState() throws RemoteException;
}
