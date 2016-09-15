package com.google.android.gms.auth.firstparty.delegate;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountSetupWorkflowService extends IInterface {

    public static abstract class Stub extends Binder implements IAccountSetupWorkflowService {
        private static final String DESCRIPTOR = "com.google.android.gms.auth.firstparty.delegate.IAccountSetupWorkflowService";
        static final int TRANSACTION_getAccountSetupWorkflowIntent = 1;

        private static class Proxy implements IAccountSetupWorkflowService {
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

            public PendingIntent getAccountSetupWorkflowIntent(SetupAccountWorkflowRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PendingIntent _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountSetupWorkflowIntent);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountSetupWorkflowIntent, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (PendingIntent) PendingIntent.CREATOR.createFromParcel(_reply);
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

        public static IAccountSetupWorkflowService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAccountSetupWorkflowService)) {
                return new Proxy(obj);
            }
            return (IAccountSetupWorkflowService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_getAccountSetupWorkflowIntent /*1*/:
                    SetupAccountWorkflowRequest _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = SetupAccountWorkflowRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    PendingIntent _result = getAccountSetupWorkflowIntent(_arg0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getAccountSetupWorkflowIntent);
                        _result.writeToParcel(reply, TRANSACTION_getAccountSetupWorkflowIntent);
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

    PendingIntent getAccountSetupWorkflowIntent(SetupAccountWorkflowRequest setupAccountWorkflowRequest) throws RemoteException;
}
