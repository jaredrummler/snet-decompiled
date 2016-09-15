package com.google.android.gtalkservice;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IImSession extends IInterface {

    public static abstract class Stub extends Binder implements IImSession {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IImSession";
        static final int TRANSACTION_addConnectionStateListener = 7;
        static final int TRANSACTION_addContact = 13;
        static final int TRANSACTION_addGroupChatInvitationListener = 27;
        static final int TRANSACTION_addRemoteChatListener = 29;
        static final int TRANSACTION_addRemoteJingleInfoStanzaListener = 42;
        static final int TRANSACTION_addRemoteRosterListener = 31;
        static final int TRANSACTION_addRemoteSessionStanzaListener = 39;
        static final int TRANSACTION_approveSubscriptionRequest = 20;
        static final int TRANSACTION_blockContact = 16;
        static final int TRANSACTION_clearContactFlags = 19;
        static final int TRANSACTION_closeAllChatSessions = 36;
        static final int TRANSACTION_createChatSession = 22;
        static final int TRANSACTION_createGroupChatSession = 24;
        static final int TRANSACTION_declineGroupChatInvitation = 26;
        static final int TRANSACTION_declineSubscriptionRequest = 21;
        static final int TRANSACTION_editContact = 14;
        static final int TRANSACTION_getAccountId = 1;
        static final int TRANSACTION_getChatSession = 23;
        static final int TRANSACTION_getConnectionState = 6;
        static final int TRANSACTION_getJid = 3;
        static final int TRANSACTION_getPresence = 10;
        static final int TRANSACTION_getUsername = 2;
        static final int TRANSACTION_goOffRecordInRoom = 34;
        static final int TRANSACTION_goOffRecordWithContacts = 33;
        static final int TRANSACTION_hideContact = 18;
        static final int TRANSACTION_inviteContactsToGroupchat = 46;
        static final int TRANSACTION_isOffRecordWithContact = 35;
        static final int TRANSACTION_joinGroupChatSession = 25;
        static final int TRANSACTION_login = 4;
        static final int TRANSACTION_logout = 5;
        static final int TRANSACTION_pinContact = 17;
        static final int TRANSACTION_pruneOldChatSessions = 37;
        static final int TRANSACTION_queryJingleInfo = 41;
        static final int TRANSACTION_removeConnectionStateListener = 8;
        static final int TRANSACTION_removeContact = 15;
        static final int TRANSACTION_removeGroupChatInvitationListener = 28;
        static final int TRANSACTION_removeRemoteChatListener = 30;
        static final int TRANSACTION_removeRemoteJingleInfoStanzaListener = 43;
        static final int TRANSACTION_removeRemoteRosterListener = 32;
        static final int TRANSACTION_removeRemoteSessionStanzaListener = 40;
        static final int TRANSACTION_requestBatchedBuddyPresence = 44;
        static final int TRANSACTION_sendCallPerfStatsStanza = 45;
        static final int TRANSACTION_sendSessionStanza = 38;
        static final int TRANSACTION_setPresence = 9;
        static final int TRANSACTION_uploadAvatar = 11;
        static final int TRANSACTION_uploadAvatarFromDb = 12;

        private static class Proxy implements IImSession {
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

            public long getAccountId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountId, _data, _reply, 0);
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
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

            public void login(String username, boolean keepSignedIn) throws RemoteException {
                int i = Stub.TRANSACTION_getAccountId;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(username);
                    if (!keepSignedIn) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_login, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void logout() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_logout, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public ConnectionState getConnectionState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    ConnectionState _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getConnectionState, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (ConnectionState) ConnectionState.CREATOR.createFromParcel(_reply);
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

            public void addConnectionStateListener(IConnectionStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_addConnectionStateListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeConnectionStateListener(IConnectionStateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_removeConnectionStateListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setPresence(Presence presence) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (presence != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountId);
                        presence.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_setPresence, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Presence getPresence() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Presence _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getPresence, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Presence) Presence.CREATOR.createFromParcel(_reply);
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

            public void uploadAvatar(Bitmap avatar) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (avatar != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountId);
                        avatar.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_uploadAvatar, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void uploadAvatarFromDb() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_uploadAvatarFromDb, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void addContact(String contact, String nickname, String[] groups) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    _data.writeString(nickname);
                    _data.writeStringArray(groups);
                    this.mRemote.transact(Stub.TRANSACTION_addContact, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void editContact(String contact, String nickname, String[] groups) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    _data.writeString(nickname);
                    _data.writeStringArray(groups);
                    this.mRemote.transact(Stub.TRANSACTION_editContact, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void removeContact(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_removeContact, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void blockContact(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_blockContact, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void pinContact(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_pinContact, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void hideContact(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_hideContact, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void clearContactFlags(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_clearContactFlags, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void approveSubscriptionRequest(String contact, String nickname, String[] groups) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    _data.writeString(nickname);
                    _data.writeStringArray(groups);
                    this.mRemote.transact(Stub.TRANSACTION_approveSubscriptionRequest, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void declineSubscriptionRequest(String contact) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_declineSubscriptionRequest, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public IChatSession createChatSession(String to) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(to);
                    this.mRemote.transact(Stub.TRANSACTION_createChatSession, _data, _reply, 0);
                    _reply.readException();
                    IChatSession _result = com.google.android.gtalkservice.IChatSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IChatSession getChatSession(String to) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(to);
                    this.mRemote.transact(Stub.TRANSACTION_getChatSession, _data, _reply, 0);
                    _reply.readException();
                    IChatSession _result = com.google.android.gtalkservice.IChatSession.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void createGroupChatSession(String nickname, String[] contacts) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(nickname);
                    _data.writeStringArray(contacts);
                    this.mRemote.transact(Stub.TRANSACTION_createGroupChatSession, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void joinGroupChatSession(String roomAddress, String nickname, String password) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(roomAddress);
                    _data.writeString(nickname);
                    _data.writeString(password);
                    this.mRemote.transact(Stub.TRANSACTION_joinGroupChatSession, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void declineGroupChatInvitation(String roomAddress, String inviter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(roomAddress);
                    _data.writeString(inviter);
                    this.mRemote.transact(Stub.TRANSACTION_declineGroupChatInvitation, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addGroupChatInvitationListener(IGroupChatInvitationListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_addGroupChatInvitationListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeGroupChatInvitationListener(IGroupChatInvitationListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_removeGroupChatInvitationListener, _data, _reply, 0);
                    _reply.readException();
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

            public void addRemoteRosterListener(IRosterListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_addRemoteRosterListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeRemoteRosterListener(IRosterListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_removeRemoteRosterListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void goOffRecordWithContacts(List contacts, boolean offRecordFlag) throws RemoteException {
                int i = Stub.TRANSACTION_getAccountId;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeList(contacts);
                    if (!offRecordFlag) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_goOffRecordWithContacts, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void goOffRecordInRoom(String room, boolean offRecordFlag) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(room);
                    if (offRecordFlag) {
                        i = Stub.TRANSACTION_getAccountId;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_goOffRecordInRoom, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isOffRecordWithContact(String contact) throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(contact);
                    this.mRemote.transact(Stub.TRANSACTION_isOffRecordWithContact, _data, _reply, 0);
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

            public void closeAllChatSessions() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_closeAllChatSessions, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void pruneOldChatSessions(long expire, long expireHard, long expireForChatsOnOtherClient, boolean closeChatIfTimeStampIsZero) throws RemoteException {
                int i = Stub.TRANSACTION_getAccountId;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(expire);
                    _data.writeLong(expireHard);
                    _data.writeLong(expireForChatsOnOtherClient);
                    if (!closeChatIfTimeStampIsZero) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_pruneOldChatSessions, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void sendSessionStanza(String stanza) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(stanza);
                    this.mRemote.transact(Stub.TRANSACTION_sendSessionStanza, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void addRemoteSessionStanzaListener(ISessionStanzaListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_addRemoteSessionStanzaListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeRemoteSessionStanzaListener(ISessionStanzaListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_removeRemoteSessionStanzaListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void queryJingleInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_queryJingleInfo, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void addRemoteJingleInfoStanzaListener(IJingleInfoStanzaListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_addRemoteJingleInfoStanzaListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeRemoteJingleInfoStanzaListener(IJingleInfoStanzaListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_removeRemoteJingleInfoStanzaListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void requestBatchedBuddyPresence() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_requestBatchedBuddyPresence, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void sendCallPerfStatsStanza(String callPerfStanza) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callPerfStanza);
                    this.mRemote.transact(Stub.TRANSACTION_sendCallPerfStatsStanza, _data, null, Stub.TRANSACTION_getAccountId);
                } finally {
                    _data.recycle();
                }
            }

            public void inviteContactsToGroupchat(String roomJid, String[] contacts) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(roomJid);
                    _data.writeStringArray(contacts);
                    this.mRemote.transact(Stub.TRANSACTION_inviteContactsToGroupchat, _data, _reply, 0);
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

        public static IImSession asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IImSession)) {
                return new Proxy(obj);
            }
            return (IImSession) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String _result;
            IChatSession _result2;
            switch (code) {
                case TRANSACTION_getAccountId /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    long _result3 = getAccountId();
                    reply.writeNoException();
                    reply.writeLong(_result3);
                    return true;
                case TRANSACTION_getUsername /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getUsername();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_getJid /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getJid();
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                case TRANSACTION_login /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    login(data.readString(), data.readInt() != 0);
                    return true;
                case TRANSACTION_logout /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    logout();
                    return true;
                case TRANSACTION_getConnectionState /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    ConnectionState _result4 = getConnectionState();
                    reply.writeNoException();
                    if (_result4 != null) {
                        reply.writeInt(TRANSACTION_getAccountId);
                        _result4.writeToParcel(reply, TRANSACTION_getAccountId);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_addConnectionStateListener /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    addConnectionStateListener(com.google.android.gtalkservice.IConnectionStateListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeConnectionStateListener /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeConnectionStateListener(com.google.android.gtalkservice.IConnectionStateListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setPresence /*9*/:
                    Presence _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Presence) Presence.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    setPresence(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPresence /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    Presence _result5 = getPresence();
                    reply.writeNoException();
                    if (_result5 != null) {
                        reply.writeInt(TRANSACTION_getAccountId);
                        _result5.writeToParcel(reply, TRANSACTION_getAccountId);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_uploadAvatar /*11*/:
                    Bitmap _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    uploadAvatar(_arg02);
                    return true;
                case TRANSACTION_uploadAvatarFromDb /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    uploadAvatarFromDb();
                    return true;
                case TRANSACTION_addContact /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    addContact(data.readString(), data.readString(), data.createStringArray());
                    return true;
                case TRANSACTION_editContact /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    editContact(data.readString(), data.readString(), data.createStringArray());
                    return true;
                case TRANSACTION_removeContact /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeContact(data.readString());
                    return true;
                case TRANSACTION_blockContact /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    blockContact(data.readString());
                    return true;
                case TRANSACTION_pinContact /*17*/:
                    data.enforceInterface(DESCRIPTOR);
                    pinContact(data.readString());
                    return true;
                case TRANSACTION_hideContact /*18*/:
                    data.enforceInterface(DESCRIPTOR);
                    hideContact(data.readString());
                    return true;
                case TRANSACTION_clearContactFlags /*19*/:
                    data.enforceInterface(DESCRIPTOR);
                    clearContactFlags(data.readString());
                    return true;
                case TRANSACTION_approveSubscriptionRequest /*20*/:
                    data.enforceInterface(DESCRIPTOR);
                    approveSubscriptionRequest(data.readString(), data.readString(), data.createStringArray());
                    return true;
                case TRANSACTION_declineSubscriptionRequest /*21*/:
                    data.enforceInterface(DESCRIPTOR);
                    declineSubscriptionRequest(data.readString());
                    return true;
                case TRANSACTION_createChatSession /*22*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = createChatSession(data.readString());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_getChatSession /*23*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = getChatSession(data.readString());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_createGroupChatSession /*24*/:
                    data.enforceInterface(DESCRIPTOR);
                    createGroupChatSession(data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_joinGroupChatSession /*25*/:
                    data.enforceInterface(DESCRIPTOR);
                    joinGroupChatSession(data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_declineGroupChatInvitation /*26*/:
                    data.enforceInterface(DESCRIPTOR);
                    declineGroupChatInvitation(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addGroupChatInvitationListener /*27*/:
                    data.enforceInterface(DESCRIPTOR);
                    addGroupChatInvitationListener(com.google.android.gtalkservice.IGroupChatInvitationListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeGroupChatInvitationListener /*28*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeGroupChatInvitationListener(com.google.android.gtalkservice.IGroupChatInvitationListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addRemoteChatListener /*29*/:
                    data.enforceInterface(DESCRIPTOR);
                    addRemoteChatListener(com.google.android.gtalkservice.IChatListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeRemoteChatListener /*30*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeRemoteChatListener(com.google.android.gtalkservice.IChatListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addRemoteRosterListener /*31*/:
                    data.enforceInterface(DESCRIPTOR);
                    addRemoteRosterListener(com.google.android.gtalkservice.IRosterListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeRemoteRosterListener /*32*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeRemoteRosterListener(com.google.android.gtalkservice.IRosterListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_goOffRecordWithContacts /*33*/:
                    data.enforceInterface(DESCRIPTOR);
                    goOffRecordWithContacts(data.readArrayList(getClass().getClassLoader()), data.readInt() != 0);
                    return true;
                case TRANSACTION_goOffRecordInRoom /*34*/:
                    data.enforceInterface(DESCRIPTOR);
                    goOffRecordInRoom(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isOffRecordWithContact /*35*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result6 = isOffRecordWithContact(data.readString());
                    reply.writeNoException();
                    reply.writeInt(_result6 ? TRANSACTION_getAccountId : 0);
                    return true;
                case TRANSACTION_closeAllChatSessions /*36*/:
                    data.enforceInterface(DESCRIPTOR);
                    closeAllChatSessions();
                    return true;
                case TRANSACTION_pruneOldChatSessions /*37*/:
                    data.enforceInterface(DESCRIPTOR);
                    pruneOldChatSessions(data.readLong(), data.readLong(), data.readLong(), data.readInt() != 0);
                    return true;
                case TRANSACTION_sendSessionStanza /*38*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendSessionStanza(data.readString());
                    return true;
                case TRANSACTION_addRemoteSessionStanzaListener /*39*/:
                    data.enforceInterface(DESCRIPTOR);
                    addRemoteSessionStanzaListener(com.google.android.gtalkservice.ISessionStanzaListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeRemoteSessionStanzaListener /*40*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeRemoteSessionStanzaListener(com.google.android.gtalkservice.ISessionStanzaListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_queryJingleInfo /*41*/:
                    data.enforceInterface(DESCRIPTOR);
                    queryJingleInfo();
                    return true;
                case TRANSACTION_addRemoteJingleInfoStanzaListener /*42*/:
                    data.enforceInterface(DESCRIPTOR);
                    addRemoteJingleInfoStanzaListener(com.google.android.gtalkservice.IJingleInfoStanzaListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeRemoteJingleInfoStanzaListener /*43*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeRemoteJingleInfoStanzaListener(com.google.android.gtalkservice.IJingleInfoStanzaListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_requestBatchedBuddyPresence /*44*/:
                    data.enforceInterface(DESCRIPTOR);
                    requestBatchedBuddyPresence();
                    return true;
                case TRANSACTION_sendCallPerfStatsStanza /*45*/:
                    data.enforceInterface(DESCRIPTOR);
                    sendCallPerfStatsStanza(data.readString());
                    return true;
                case TRANSACTION_inviteContactsToGroupchat /*46*/:
                    data.enforceInterface(DESCRIPTOR);
                    inviteContactsToGroupchat(data.readString(), data.createStringArray());
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

    void addConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException;

    void addContact(String str, String str2, String[] strArr) throws RemoteException;

    void addGroupChatInvitationListener(IGroupChatInvitationListener iGroupChatInvitationListener) throws RemoteException;

    void addRemoteChatListener(IChatListener iChatListener) throws RemoteException;

    void addRemoteJingleInfoStanzaListener(IJingleInfoStanzaListener iJingleInfoStanzaListener) throws RemoteException;

    void addRemoteRosterListener(IRosterListener iRosterListener) throws RemoteException;

    void addRemoteSessionStanzaListener(ISessionStanzaListener iSessionStanzaListener) throws RemoteException;

    void approveSubscriptionRequest(String str, String str2, String[] strArr) throws RemoteException;

    void blockContact(String str) throws RemoteException;

    void clearContactFlags(String str) throws RemoteException;

    void closeAllChatSessions() throws RemoteException;

    IChatSession createChatSession(String str) throws RemoteException;

    void createGroupChatSession(String str, String[] strArr) throws RemoteException;

    void declineGroupChatInvitation(String str, String str2) throws RemoteException;

    void declineSubscriptionRequest(String str) throws RemoteException;

    void editContact(String str, String str2, String[] strArr) throws RemoteException;

    long getAccountId() throws RemoteException;

    IChatSession getChatSession(String str) throws RemoteException;

    ConnectionState getConnectionState() throws RemoteException;

    String getJid() throws RemoteException;

    Presence getPresence() throws RemoteException;

    String getUsername() throws RemoteException;

    void goOffRecordInRoom(String str, boolean z) throws RemoteException;

    void goOffRecordWithContacts(List list, boolean z) throws RemoteException;

    void hideContact(String str) throws RemoteException;

    void inviteContactsToGroupchat(String str, String[] strArr) throws RemoteException;

    boolean isOffRecordWithContact(String str) throws RemoteException;

    void joinGroupChatSession(String str, String str2, String str3) throws RemoteException;

    void login(String str, boolean z) throws RemoteException;

    void logout() throws RemoteException;

    void pinContact(String str) throws RemoteException;

    void pruneOldChatSessions(long j, long j2, long j3, boolean z) throws RemoteException;

    void queryJingleInfo() throws RemoteException;

    void removeConnectionStateListener(IConnectionStateListener iConnectionStateListener) throws RemoteException;

    void removeContact(String str) throws RemoteException;

    void removeGroupChatInvitationListener(IGroupChatInvitationListener iGroupChatInvitationListener) throws RemoteException;

    void removeRemoteChatListener(IChatListener iChatListener) throws RemoteException;

    void removeRemoteJingleInfoStanzaListener(IJingleInfoStanzaListener iJingleInfoStanzaListener) throws RemoteException;

    void removeRemoteRosterListener(IRosterListener iRosterListener) throws RemoteException;

    void removeRemoteSessionStanzaListener(ISessionStanzaListener iSessionStanzaListener) throws RemoteException;

    void requestBatchedBuddyPresence() throws RemoteException;

    void sendCallPerfStatsStanza(String str) throws RemoteException;

    void sendSessionStanza(String str) throws RemoteException;

    void setPresence(Presence presence) throws RemoteException;

    void uploadAvatar(Bitmap bitmap) throws RemoteException;

    void uploadAvatarFromDb() throws RemoteException;
}
