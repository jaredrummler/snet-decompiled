package com.google.android.gms.signin.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Scope;
import java.util.List;

public interface IOfflineAccessCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IOfflineAccessCallbacks {
        private static final String DESCRIPTOR = "com.google.android.gms.signin.internal.IOfflineAccessCallbacks";
        static final int TRANSACTION_checkServerAuthorization = 2;
        static final int TRANSACTION_uploadServerAuthCode = 3;

        private static class Proxy implements IOfflineAccessCallbacks {
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

            public void checkServerAuthorization(String idToken, List<Scope> scopes, ISignInService signInService) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(idToken);
                    _data.writeTypedList(scopes);
                    _data.writeStrongBinder(signInService != null ? signInService.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_checkServerAuthorization, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void uploadServerAuthCode(String idToken, String serverAuthCode, ISignInService signInService) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(idToken);
                    _data.writeString(serverAuthCode);
                    _data.writeStrongBinder(signInService != null ? signInService.asBinder() : null);
                    this.mRemote.transact(Stub.TRANSACTION_uploadServerAuthCode, _data, _reply, 0);
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

        public static IOfflineAccessCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IOfflineAccessCallbacks)) {
                return new Proxy(obj);
            }
            return (IOfflineAccessCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_checkServerAuthorization /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    checkServerAuthorization(data.readString(), data.createTypedArrayList(Scope.CREATOR), com.google.android.gms.signin.internal.ISignInService.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_uploadServerAuthCode /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    uploadServerAuthCode(data.readString(), data.readString(), com.google.android.gms.signin.internal.ISignInService.Stub.asInterface(data.readStrongBinder()));
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

    void checkServerAuthorization(String str, List<Scope> list, ISignInService iSignInService) throws RemoteException;

    void uploadServerAuthCode(String str, String str2, ISignInService iSignInService) throws RemoteException;
}
