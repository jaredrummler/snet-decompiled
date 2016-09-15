package com.google.android.gms.people.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;

public interface IPeopleCallbacks extends IInterface {

    public static abstract class Stub extends Binder implements IPeopleCallbacks {
        private static final String DESCRIPTOR = "com.google.android.gms.people.internal.IPeopleCallbacks";
        static final int TRANSACTION_onBundleLoaded = 1;
        static final int TRANSACTION_onDataHolderLoaded = 2;
        static final int TRANSACTION_onDataHoldersLoaded = 4;
        static final int TRANSACTION_onParcelFileDescriptorLoaded = 5;
        static final int TRANSACTION_onParcelFileDescriptorLoaded_old = 3;

        private static class Proxy implements IPeopleCallbacks {
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

            public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    if (resolution != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        resolution.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (content != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        content.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onBundleLoaded, _data, null, Stub.TRANSACTION_onBundleLoaded);
                } finally {
                    _data.recycle();
                }
            }

            public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder dataHolder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    if (resolution != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        resolution.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (dataHolder != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        dataHolder.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onDataHolderLoaded, _data, null, Stub.TRANSACTION_onBundleLoaded);
                } finally {
                    _data.recycle();
                }
            }

            public void onParcelFileDescriptorLoaded_old(int statusCode, Bundle resolution, ParcelFileDescriptor pfd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    if (resolution != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        resolution.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pfd != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onParcelFileDescriptorLoaded_old, _data, null, Stub.TRANSACTION_onBundleLoaded);
                } finally {
                    _data.recycle();
                }
            }

            public void onDataHoldersLoaded(int statusCode, Bundle resolution, DataHolder[] dataHolders) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    if (resolution != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        resolution.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(dataHolders, 0);
                    this.mRemote.transact(Stub.TRANSACTION_onDataHoldersLoaded, _data, null, Stub.TRANSACTION_onBundleLoaded);
                } finally {
                    _data.recycle();
                }
            }

            public void onParcelFileDescriptorLoaded(int statusCode, Bundle resolution, ParcelFileDescriptor pfd, Bundle metadata) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(statusCode);
                    if (resolution != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        resolution.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pfd != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (metadata != null) {
                        _data.writeInt(Stub.TRANSACTION_onBundleLoaded);
                        metadata.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_onParcelFileDescriptorLoaded, _data, null, Stub.TRANSACTION_onBundleLoaded);
                } finally {
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPeopleCallbacks asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPeopleCallbacks)) {
                return new Proxy(obj);
            }
            return (IPeopleCallbacks) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            Bundle _arg1;
            ParcelFileDescriptor _arg2;
            switch (code) {
                case TRANSACTION_onBundleLoaded /*1*/:
                    Bundle _arg22;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg22 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg22 = null;
                    }
                    onBundleLoaded(_arg0, _arg1, _arg22);
                    return true;
                case TRANSACTION_onDataHolderLoaded /*2*/:
                    DataHolder _arg23;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg23 = DataHolder.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }
                    onDataHolderLoaded(_arg0, _arg1, _arg23);
                    return true;
                case TRANSACTION_onParcelFileDescriptorLoaded_old /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg2 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    onParcelFileDescriptorLoaded_old(_arg0, _arg1, _arg2);
                    return true;
                case TRANSACTION_onDataHoldersLoaded /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    onDataHoldersLoaded(_arg0, _arg1, (DataHolder[]) data.createTypedArray(DataHolder.CREATOR));
                    return true;
                case TRANSACTION_onParcelFileDescriptorLoaded /*5*/:
                    Bundle _arg3;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg2 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                    } else {
                        _arg2 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    onParcelFileDescriptorLoaded(_arg0, _arg1, _arg2, _arg3);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onBundleLoaded(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void onDataHolderLoaded(int i, Bundle bundle, DataHolder dataHolder) throws RemoteException;

    void onDataHoldersLoaded(int i, Bundle bundle, DataHolder[] dataHolderArr) throws RemoteException;

    void onParcelFileDescriptorLoaded(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle2) throws RemoteException;

    void onParcelFileDescriptorLoaded_old(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;
}
