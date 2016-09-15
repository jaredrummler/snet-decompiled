package com.google.android.gms.playlog.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IPlayLogService extends IInterface {

    public static abstract class Stub extends Binder implements IPlayLogService {
        private static final String DESCRIPTOR = "com.google.android.gms.playlog.internal.IPlayLogService";
        static final int TRANSACTION_sendLogEvent = 2;
        static final int TRANSACTION_sendLogEvents = 3;
        static final int TRANSACTION_sendSerializedLogEvent = 4;

        private static class Proxy implements IPlayLogService {
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

            public void sendLogEvent(String callingPackageName, PlayLoggerContext loggerContext, LogEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackageName);
                    if (loggerContext != null) {
                        _data.writeInt(1);
                        loggerContext.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_sendLogEvent, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void sendLogEvents(String callingPackageName, PlayLoggerContext loggerContext, List<LogEvent> events) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackageName);
                    if (loggerContext != null) {
                        _data.writeInt(1);
                        loggerContext.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedList(events);
                    this.mRemote.transact(Stub.TRANSACTION_sendLogEvents, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }

            public void sendSerializedLogEvent(String callingPackageName, PlayLoggerContext loggerContext, byte[] serializedEvent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(callingPackageName);
                    if (loggerContext != null) {
                        _data.writeInt(1);
                        loggerContext.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeByteArray(serializedEvent);
                    this.mRemote.transact(Stub.TRANSACTION_sendSerializedLogEvent, _data, null, 1);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlayLogService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPlayLogService)) {
                return new Proxy(obj);
            }
            return (IPlayLogService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String _arg0;
            PlayLoggerContext _arg1;
            switch (code) {
                case TRANSACTION_sendLogEvent /*2*/:
                    LogEvent _arg2;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = PlayLoggerContext.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg2 = LogEvent.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    sendLogEvent(_arg0, _arg1, _arg2);
                    return true;
                case TRANSACTION_sendLogEvents /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = PlayLoggerContext.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    sendLogEvents(_arg0, _arg1, data.createTypedArrayList(LogEvent.CREATOR));
                    return true;
                case TRANSACTION_sendSerializedLogEvent /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = PlayLoggerContext.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    sendSerializedLogEvent(_arg0, _arg1, data.createByteArray());
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void sendLogEvent(String str, PlayLoggerContext playLoggerContext, LogEvent logEvent) throws RemoteException;

    void sendLogEvents(String str, PlayLoggerContext playLoggerContext, List<LogEvent> list) throws RemoteException;

    void sendSerializedLogEvent(String str, PlayLoggerContext playLoggerContext, byte[] bArr) throws RemoteException;
}
