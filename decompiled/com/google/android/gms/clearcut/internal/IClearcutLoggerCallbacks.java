package com.google.android.gms.clearcut.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public interface IClearcutLoggerCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IClearcutLoggerCallbacks {
        private static final String DESCRIPTOR = "com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks";
        static final int TRANSACTION_onLogEvent = 1;

        private static class Proxy implements IClearcutLoggerCallbacks {
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

            public void onLogEvent(Status status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (status != null) {
                        _data.writeInt(Stub.TRANSACTION_onLogEvent);
                        status.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onLogEvent, _data, null, Stub.TRANSACTION_onLogEvent);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IClearcutLoggerCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IClearcutLoggerCallbacks)) {
                return new Proxy(obj);
            }
            return (IClearcutLoggerCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_onLogEvent /*1*/:
                    Status _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = (Status) Status.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    onLogEvent(_arg0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onLogEvent(Status status) throws RemoteException;
}
