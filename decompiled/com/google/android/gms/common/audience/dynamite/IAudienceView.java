package com.google.android.gms.common.audience.dynamite;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.gms.dynamic.IObjectWrapper;

public interface IAudienceView extends IInterface {

    public static abstract class Stub extends Binder implements IAudienceView {
        private static final String DESCRIPTOR = "com.google.android.gms.common.audience.dynamite.IAudienceView";
        static final int TRANSACTION_getView = 8;
        static final int TRANSACTION_initialize = 2;
        static final int TRANSACTION_onRestoreInstanceState = 7;
        static final int TRANSACTION_onSaveInstanceState = 6;
        static final int TRANSACTION_setAudience = 5;
        static final int TRANSACTION_setEditMode = 3;
        static final int TRANSACTION_setIsUnderageAccount = 9;
        static final int TRANSACTION_setShowEmptyText = 4;

        private static class Proxy implements IAudienceView {
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

            public void initialize(IObjectWrapper clientContext, IObjectWrapper packageContext, IAudienceViewCallbacks callbacks) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    IBinder asBinder;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(clientContext != null ? clientContext.asBinder() : null);
                    if (packageContext != null) {
                        asBinder = packageContext.asBinder();
                    } else {
                        asBinder = null;
                    }
                    _data.writeStrongBinder(asBinder);
                    if (callbacks != null) {
                        iBinder = callbacks.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_initialize, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setEditMode(int editMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(editMode);
                    this.mRemote.transact(Stub.TRANSACTION_setEditMode, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setShowEmptyText(boolean showEmptyText) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (showEmptyText) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_setShowEmptyText, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setAudience(Audience audience) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (audience != null) {
                        _data.writeInt(1);
                        audience.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_setAudience, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle onSaveInstanceState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_onSaveInstanceState, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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

            public void onRestoreInstanceState(Bundle state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (state != null) {
                        _data.writeInt(1);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onRestoreInstanceState, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IObjectWrapper getView() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getView, _data, _reply, 0);
                    _reply.readException();
                    IObjectWrapper _result = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setIsUnderageAccount(boolean isUnderageAccount) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (isUnderageAccount) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_setIsUnderageAccount, _data, _reply, 0);
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

        public static IAudienceView asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IAudienceView)) {
                return new Proxy(obj);
            }
            return (IAudienceView) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0 = false;
            switch (code) {
                case TRANSACTION_initialize /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    initialize(com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(data.readStrongBinder()), com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(data.readStrongBinder()), com.google.android.gms.common.audience.dynamite.IAudienceViewCallbacks.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setEditMode /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    setEditMode(data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setShowEmptyText /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    setShowEmptyText(_arg0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setAudience /*5*/:
                    Audience _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = Audience.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    setAudience(_arg02);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_onSaveInstanceState /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    Bundle _result = onSaveInstanceState();
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_onRestoreInstanceState /*7*/:
                    Bundle _arg03;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    onRestoreInstanceState(_arg03);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getView /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    IObjectWrapper _result2 = getView();
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_setIsUnderageAccount /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    }
                    setIsUnderageAccount(_arg0);
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

    IObjectWrapper getView() throws RemoteException;

    void initialize(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IAudienceViewCallbacks iAudienceViewCallbacks) throws RemoteException;

    void onRestoreInstanceState(Bundle bundle) throws RemoteException;

    Bundle onSaveInstanceState() throws RemoteException;

    void setAudience(Audience audience) throws RemoteException;

    void setEditMode(int i) throws RemoteException;

    void setIsUnderageAccount(boolean z) throws RemoteException;

    void setShowEmptyText(boolean z) throws RemoteException;
}
