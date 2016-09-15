package com.google.android.gms.clearcut.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;

public interface IClearcutLoggerService extends IInterface {

    public static abstract class Stub extends Binder implements IClearcutLoggerService {
        private static final String DESCRIPTOR = "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
        static final int TRANSACTION_logEvent = 1;

        private static class Proxy implements IClearcutLoggerService {
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

            public void logEvent(IClearcutLoggerCallbacks callback, LogEventParcelable logEvent) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (callback != null) {
                        iBinder = callback.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    if (logEvent != null) {
                        _data.writeInt(Stub.TRANSACTION_logEvent);
                        logEvent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_logEvent, _data, null, Stub.TRANSACTION_logEvent);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IClearcutLoggerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IClearcutLoggerService)) {
                return new Proxy(obj);
            }
            return (IClearcutLoggerService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case TRANSACTION_logEvent /*1*/:
                    LogEventParcelable _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    IClearcutLoggerCallbacks _arg0 = com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg1 = LogEventParcelable.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    logEvent(_arg0, _arg1);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void logEvent(IClearcutLoggerCallbacks iClearcutLoggerCallbacks, LogEventParcelable logEventParcelable) throws RemoteException;
}
