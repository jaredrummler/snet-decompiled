package com.google.android.gtalkservice;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IChatSession extends IInterface {

    public static abstract class Stub extends Binder implements IChatSession {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IChatSession";
        static final int TRANSACTION_addRemoteChatListener = 9;
        static final int TRANSACTION_clearChatHistory = 16;
        static final int TRANSACTION_ensureNonZeroLastMessageDate = 15;
        static final int TRANSACTION_getLightweightNotify = 12;
        static final int TRANSACTION_getParticipants = 3;
        static final int TRANSACTION_getUnsentComposedMessage = 8;
        static final int TRANSACTION_inviteContact = 4;
        static final int TRANSACTION_isGroupChat = 1;
        static final int TRANSACTION_isOffTheRecord = 11;
        static final int TRANSACTION_leave = 5;
        static final int TRANSACTION_markAsRead = 2;
        static final int TRANSACTION_removeRemoteChatListener = 10;
        static final int TRANSACTION_reportEndCause = 13;
        static final int TRANSACTION_reportMissedCall = 14;
        static final int TRANSACTION_saveUnsentComposedMessage = 7;
        static final int TRANSACTION_sendChatMessage = 6;

        private static class Proxy implements IChatSession {
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

            public boolean isGroupChat() throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isGroupChat, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void markAsRead() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_markAsRead, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public String[] getParticipants() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getParticipants, _data, _reply, 0);
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void inviteContact(String to) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(to);
                    this.mRemote.transact(Stub.TRANSACTION_inviteContact, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public void leave() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_leave, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public void sendChatMessage(String message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(message);
                    this.mRemote.transact(Stub.TRANSACTION_sendChatMessage, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public void saveUnsentComposedMessage(String unsentMessage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(unsentMessage);
                    this.mRemote.transact(Stub.TRANSACTION_saveUnsentComposedMessage, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public String getUnsentComposedMessage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getUnsentComposedMessage, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addRemoteChatListener(IChatListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_addRemoteChatListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeRemoteChatListener(IChatListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_removeRemoteChatListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isOffTheRecord() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isOffTheRecord, _data, _reply, 0);
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

            public boolean getLightweightNotify() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getLightweightNotify, _data, _reply, 0);
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

            public void reportEndCause(String nickname, boolean video, int endCause) throws RemoteException {
                int i = Stub.TRANSACTION_isGroupChat;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(nickname);
                    if (!video) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeInt(endCause);
                    this.mRemote.transact(Stub.TRANSACTION_reportEndCause, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public void reportMissedCall(String remoteBareJid, String nickname, boolean video, boolean noWifi) throws RemoteException {
                int i = Stub.TRANSACTION_isGroupChat;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(remoteBareJid);
                    _data.writeString(nickname);
                    _data.writeInt(video ? Stub.TRANSACTION_isGroupChat : 0);
                    if (!noWifi) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_reportMissedCall, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public void ensureNonZeroLastMessageDate() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_ensureNonZeroLastMessageDate, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }

            public void clearChatHistory(Uri queryUri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (queryUri != null) {
                        _data.writeInt(Stub.TRANSACTION_isGroupChat);
                        queryUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_clearChatHistory, _data, null, Stub.TRANSACTION_isGroupChat);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IChatSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IChatSession)) {
                return new Proxy(obj);
            }
            return (IChatSession) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            boolean _result;
            String _arg0;
            switch (code) {
                case TRANSACTION_isGroupChat /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = isGroupChat();
                    reply.writeNoException();
                    if (_result) {
                        i = TRANSACTION_isGroupChat;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_markAsRead /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    markAsRead();
                    return true;
                case TRANSACTION_getParticipants /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    String[] _result2 = getParticipants();
                    reply.writeNoException();
                    reply.writeStringArray(_result2);
                    return true;
                case TRANSACTION_inviteContact /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    inviteContact(data.readString());
                    return true;
                case TRANSACTION_leave /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    leave();
                    return true;
                case TRANSACTION_sendChatMessage /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendChatMessage(data.readString());
                    return true;
                case TRANSACTION_saveUnsentComposedMessage /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    saveUnsentComposedMessage(data.readString());
                    return true;
                case TRANSACTION_getUnsentComposedMessage /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    String _result3 = getUnsentComposedMessage();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case TRANSACTION_addRemoteChatListener /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    addRemoteChatListener(com.google.android.gtalkservice.IChatListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeRemoteChatListener /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeRemoteChatListener(com.google.android.gtalkservice.IChatListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isOffTheRecord /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = isOffTheRecord();
                    reply.writeNoException();
                    if (_result) {
                        i = TRANSACTION_isGroupChat;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_getLightweightNotify /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getLightweightNotify();
                    reply.writeNoException();
                    if (_result) {
                        i = TRANSACTION_isGroupChat;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_reportEndCause /*13*/:
                    boolean _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = true;
                    } else {
                        _arg1 = false;
                    }
                    reportEndCause(_arg0, _arg1, data.readInt());
                    return true;
                case TRANSACTION_reportMissedCall /*14*/:
                    boolean _arg2;
                    boolean _arg3;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    String _arg12 = data.readString();
                    if (data.readInt() != 0) {
                        _arg2 = true;
                    } else {
                        _arg2 = false;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = true;
                    } else {
                        _arg3 = false;
                    }
                    reportMissedCall(_arg0, _arg12, _arg2, _arg3);
                    return true;
                case TRANSACTION_ensureNonZeroLastMessageDate /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    ensureNonZeroLastMessageDate();
                    return true;
                case TRANSACTION_clearChatHistory /*16*/:
                    Uri _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    clearChatHistory(_arg02);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void addRemoteChatListener(IChatListener iChatListener) throws RemoteException;

    void clearChatHistory(Uri uri) throws RemoteException;

    void ensureNonZeroLastMessageDate() throws RemoteException;

    boolean getLightweightNotify() throws RemoteException;

    String[] getParticipants() throws RemoteException;

    String getUnsentComposedMessage() throws RemoteException;

    void inviteContact(String str) throws RemoteException;

    boolean isGroupChat() throws RemoteException;

    boolean isOffTheRecord() throws RemoteException;

    void leave() throws RemoteException;

    void markAsRead() throws RemoteException;

    void removeRemoteChatListener(IChatListener iChatListener) throws RemoteException;

    void reportEndCause(String str, boolean z, int i) throws RemoteException;

    void reportMissedCall(String str, String str2, boolean z, boolean z2) throws RemoteException;

    void saveUnsentComposedMessage(String str) throws RemoteException;

    void sendChatMessage(String str) throws RemoteException;
}
