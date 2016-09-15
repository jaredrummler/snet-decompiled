package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGTalkConnection extends IInterface {

    public static abstract class Stub extends Binder implements IGTalkConnection {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IGTalkConnection";
        static final int TRANSACTION_clearConnectionStatistics = 13;
        static final int TRANSACTION_createImSession = 5;
        static final int TRANSACTION_getConnectionUptime = 12;
        static final int TRANSACTION_getDefaultImSession = 7;
        static final int TRANSACTION_getDeviceId = 3;
        static final int TRANSACTION_getImSessionForAccountId = 6;
        static final int TRANSACTION_getJid = 2;
        static final int TRANSACTION_getLastActivityFromServerTime = 8;
        static final int TRANSACTION_getLastActivityToServerTime = 9;
        static final int TRANSACTION_getNumberOfConnectionsAttempted = 11;
        static final int TRANSACTION_getNumberOfConnectionsMade = 10;
        static final int TRANSACTION_getUsername = 1;
        static final int TRANSACTION_isConnected = 4;
        static final int TRANSACTION_sendHeartbeat = 15;
        static final int TRANSACTION_sendHttpRequest = 14;

        private static class Proxy implements IGTalkConnection {
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

            public String getUsername() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getUsername, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getJid() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getJid, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getDeviceId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getDeviceId, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isConnected() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isConnected, _data, _reply, 0);
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

            public IImSession createImSession() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_createImSession, _data, _reply, 0);
                    _reply.readException();
                    IImSession _result = com.google.android.gtalkservice.IImSession.Stub.asInterface(_reply.readStrongBinder());
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

            public IImSession getDefaultImSession() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getDefaultImSession, _data, _reply, 0);
                    _reply.readException();
                    IImSession _result = com.google.android.gtalkservice.IImSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getLastActivityFromServerTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getLastActivityFromServerTime, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getLastActivityToServerTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getLastActivityToServerTime, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getNumberOfConnectionsMade() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getNumberOfConnectionsMade, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getNumberOfConnectionsAttempted() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getNumberOfConnectionsAttempted, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getConnectionUptime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getConnectionUptime, _data, _reply, 0);
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void clearConnectionStatistics() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_clearConnectionStatistics, _data, null, Stub.TRANSACTION_getUsername);
                } finally {
                    _data.recycle();
                }
            }

            public void sendHttpRequest(byte[] url, IHttpRequestCallback cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(url);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_sendHttpRequest, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void sendHeartbeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_sendHeartbeat, _data, _reply, 0);
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

        public static IGTalkConnection asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGTalkConnection)) {
                return new Proxy(obj);
            }
            return (IGTalkConnection) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IBinder iBinder = null;
            String _result;
            IImSession _result2;
            long _result3;
            int _result4;
            switch (code) {
                case TRANSACTION_getUsername /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getUsername();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getJid /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getJid();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getDeviceId /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getDeviceId();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_isConnected /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = isConnected();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? TRANSACTION_getUsername : 0);
                    return true;
                case TRANSACTION_createImSession /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = createImSession();
                    reply.writeNoException();
                    if (_result2 != null) {
                        iBinder = _result2.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_getImSessionForAccountId /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = getImSessionForAccountId(data.readLong());
                    reply.writeNoException();
                    if (_result2 != null) {
                        iBinder = _result2.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_getDefaultImSession /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = getDefaultImSession();
                    reply.writeNoException();
                    if (_result2 != null) {
                        iBinder = _result2.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case TRANSACTION_getLastActivityFromServerTime /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result3 = getLastActivityFromServerTime();
                    reply.writeNoException();
                    reply.writeLong(_result3);
                    return true;
                case TRANSACTION_getLastActivityToServerTime /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result3 = getLastActivityToServerTime();
                    reply.writeNoException();
                    reply.writeLong(_result3);
                    return true;
                case TRANSACTION_getNumberOfConnectionsMade /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result4 = getNumberOfConnectionsMade();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case TRANSACTION_getNumberOfConnectionsAttempted /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result4 = getNumberOfConnectionsAttempted();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case TRANSACTION_getConnectionUptime /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result4 = getConnectionUptime();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case TRANSACTION_clearConnectionStatistics /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    clearConnectionStatistics();
                    return true;
                case TRANSACTION_sendHttpRequest /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendHttpRequest(data.createByteArray(), com.google.android.gtalkservice.IHttpRequestCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_sendHeartbeat /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendHeartbeat();
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

    void clearConnectionStatistics() throws RemoteException;

    IImSession createImSession() throws RemoteException;

    int getConnectionUptime() throws RemoteException;

    IImSession getDefaultImSession() throws RemoteException;

    String getDeviceId() throws RemoteException;

    IImSession getImSessionForAccountId(long j) throws RemoteException;

    String getJid() throws RemoteException;

    long getLastActivityFromServerTime() throws RemoteException;

    long getLastActivityToServerTime() throws RemoteException;

    int getNumberOfConnectionsAttempted() throws RemoteException;

    int getNumberOfConnectionsMade() throws RemoteException;

    String getUsername() throws RemoteException;

    boolean isConnected() throws RemoteException;

    void sendHeartbeat() throws RemoteException;

    void sendHttpRequest(byte[] bArr, IHttpRequestCallback iHttpRequestCallback) throws RemoteException;
}
