package com.google.android.gtalkservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGroupChatInvitationListener extends IInterface {

    public static abstract class Stub extends Binder implements IGroupChatInvitationListener {
        private static final String DESCRIPTOR = "com.google.android.gtalkservice.IGroupChatInvitationListener";
        static final int TRANSACTION_onInvitationReceived = 1;

        private static class Proxy implements IGroupChatInvitationListener {
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

            public boolean onInvitationReceived(GroupChatInvitation invitation) throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (invitation != null) {
                        _data.writeInt(Stub.TRANSACTION_onInvitationReceived);
                        invitation.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onInvitationReceived, _data, _reply, 0);
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
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGroupChatInvitationListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGroupChatInvitationListener)) {
                return new Proxy(obj);
            }
            return (IGroupChatInvitationListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onInvitationReceived /*1*/:
                    GroupChatInvitation _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (GroupChatInvitation) GroupChatInvitation.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    boolean _result = onInvitationReceived(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result ? TRANSACTION_onInvitationReceived : 0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean onInvitationReceived(GroupChatInvitation groupChatInvitation) throws RemoteException;
}
