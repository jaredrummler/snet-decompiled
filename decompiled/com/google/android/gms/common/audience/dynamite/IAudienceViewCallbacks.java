package com.google.android.gms.common.audience.dynamite;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.people.data.AudienceMember;

public interface IAudienceViewCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IAudienceViewCallbacks {
        private static final String DESCRIPTOR = "com.google.android.gms.common.audience.dynamite.IAudienceViewCallbacks";
        static final int TRANSACTION_editAudience = 4;
        static final int TRANSACTION_removeAudienceMember = 3;

        private static class Proxy implements IAudienceViewCallbacks {
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

            public void removeAudienceMember(AudienceMember member) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (member != null) {
                        _data.writeInt(1);
                        member.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_removeAudienceMember, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void editAudience() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_editAudience, _data, _reply, 0);
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

        public static IAudienceViewCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAudienceViewCallbacks)) {
                return new Proxy(obj);
            }
            return (IAudienceViewCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_removeAudienceMember /*3*/:
                    AudienceMember _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = AudienceMember.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    removeAudienceMember(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_editAudience /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    editAudience();
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

    void editAudience() throws RemoteException;

    void removeAudienceMember(AudienceMember audienceMember) throws RemoteException;
}
