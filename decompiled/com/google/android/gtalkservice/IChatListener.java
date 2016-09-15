package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IChatListener extends IInterface {

    public static abstract class Stub extends Binder implements IChatListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IChatListener";
        static final int TRANSACTION_callEnded = 4;
        static final int TRANSACTION_chatClosed = 6;
        static final int TRANSACTION_chatRead = 5;
        static final int TRANSACTION_convertedToGroupChat = 8;
        static final int TRANSACTION_missedCall = 3;
        static final int TRANSACTION_newMessageReceived = 1;
        static final int TRANSACTION_newMessageSent = 2;
        static final int TRANSACTION_participantJoined = 9;
        static final int TRANSACTION_participantLeft = 10;
        static final int TRANSACTION_useLightweightNotify = 11;
        static final int TRANSACTION_willConvertToGroupChat = 7;

        private static class Proxy implements IChatListener {
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

            public void newMessageReceived(String from, String body, boolean notify) throws RemoteException {
                int i = Stub.TRANSACTION_newMessageReceived;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(from);
                    _data.writeString(body);
                    if (!notify) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_newMessageReceived, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void newMessageSent(String body) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(body);
                    this.mRemote.transact(Stub.TRANSACTION_newMessageSent, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void missedCall() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_missedCall, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void callEnded() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_callEnded, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void chatRead(String from) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(from);
                    this.mRemote.transact(Stub.TRANSACTION_chatRead, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void chatClosed(String from) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(from);
                    this.mRemote.transact(Stub.TRANSACTION_chatClosed, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void willConvertToGroupChat(String oldJid, String groupChatRoom, long groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(oldJid);
                    _data.writeString(groupChatRoom);
                    _data.writeLong(groupId);
                    this.mRemote.transact(Stub.TRANSACTION_willConvertToGroupChat, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void convertedToGroupChat(String oldJid, String groupChatRoom, long groupId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(oldJid);
                    _data.writeString(groupChatRoom);
                    _data.writeLong(groupId);
                    this.mRemote.transact(Stub.TRANSACTION_convertedToGroupChat, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void participantJoined(String groupChatRoom, String nickname) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(groupChatRoom);
                    _data.writeString(nickname);
                    this.mRemote.transact(Stub.TRANSACTION_participantJoined, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void participantLeft(String groupChatRoom, String nickname) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(groupChatRoom);
                    _data.writeString(nickname);
                    this.mRemote.transact(Stub.TRANSACTION_participantLeft, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean useLightweightNotify() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_useLightweightNotify, _data, _reply, 0);
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
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IChatListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IChatListener)) {
                return new Proxy(obj);
            }
            return (IChatListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg2 = 0;
            switch (code) {
                case TRANSACTION_newMessageReceived /*1*/:
                    boolean _arg22;
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg22 = true;
                    }
                    newMessageReceived(_arg0, _arg1, _arg22);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_newMessageSent /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    newMessageSent(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_missedCall /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    missedCall();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_callEnded /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    callEnded();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_chatRead /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    chatRead(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_chatClosed /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    chatClosed(data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_willConvertToGroupChat /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    willConvertToGroupChat(data.readString(), data.readString(), data.readLong());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_convertedToGroupChat /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    convertedToGroupChat(data.readString(), data.readString(), data.readLong());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_participantJoined /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    participantJoined(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_participantLeft /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    participantLeft(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_useLightweightNotify /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = useLightweightNotify();
                    reply.writeNoException();
                    if (_result) {
                        _arg2 = TRANSACTION_newMessageReceived;
                    }
                    reply.writeInt(_arg2);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void callEnded() throws RemoteException;

    void chatClosed(String str) throws RemoteException;

    void chatRead(String str) throws RemoteException;

    void convertedToGroupChat(String str, String str2, long j) throws RemoteException;

    void missedCall() throws RemoteException;

    void newMessageReceived(String str, String str2, boolean z) throws RemoteException;

    void newMessageSent(String str) throws RemoteException;

    void participantJoined(String str, String str2) throws RemoteException;

    void participantLeft(String str, String str2) throws RemoteException;

    boolean useLightweightNotify() throws RemoteException;

    void willConvertToGroupChat(String str, String str2, long j) throws RemoteException;
}
