package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGmsServiceBroker extends IInterface {

    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        private static final String DESCRIPTOR = "com.google.android.gms.common.internal.IGmsServiceBroker";
        static final int TRANSACTION_getAccountDataService = 17;
        static final int TRANSACTION_getAdMobService = 12;
        static final int TRANSACTION_getAddressService = 24;
        static final int TRANSACTION_getAppDataSearchService = 3;
        static final int TRANSACTION_getAppStateService = 10;
        static final int TRANSACTION_getAuthService_DEPRECATED = 28;
        static final int TRANSACTION_getAutoBackupService = 23;
        static final int TRANSACTION_getCarService = 25;
        static final int TRANSACTION_getCastMirroringService = 15;
        static final int TRANSACTION_getCastService = 19;
        static final int TRANSACTION_getClearcutLoggerService = 41;
        static final int TRANSACTION_getCloudSaveService = 33;
        static final int TRANSACTION_getCommonService = 40;
        static final int TRANSACTION_getDeviceConnectionService = 44;
        static final int TRANSACTION_getDeviceManagerService = 36;
        static final int TRANSACTION_getDriveService = 20;
        static final int TRANSACTION_getDroidGuardService = 13;
        static final int TRANSACTION_getFeedbackService = 18;
        static final int TRANSACTION_getFitnessService = 30;
        static final int TRANSACTION_getGamesService = 9;
        static final int TRANSACTION_getGlobalSearchAdminService = 32;
        static final int TRANSACTION_getGoogleLocationManagerService = 8;
        static final int TRANSACTION_getIdentityService = 27;
        static final int TRANSACTION_getKidsService = 45;
        static final int TRANSACTION_getLightweightAppDataSearchService = 21;
        static final int TRANSACTION_getLocationService = 7;
        static final int TRANSACTION_getLockboxService = 14;
        static final int TRANSACTION_getNetworkQualityService = 16;
        static final int TRANSACTION_getPanoramaService = 2;
        static final int TRANSACTION_getPeopleService = 5;
        static final int TRANSACTION_getPlayLogService = 11;
        static final int TRANSACTION_getPlusService = 1;
        static final int TRANSACTION_getPseudonymousIdService = 37;
        static final int TRANSACTION_getRemindersService = 38;
        static final int TRANSACTION_getReportingService = 6;
        static final int TRANSACTION_getSearchAdministrationService = 22;
        static final int TRANSACTION_getSearchCorporaService = 35;
        static final int TRANSACTION_getSearchQueriesService = 31;
        static final int TRANSACTION_getService = 46;
        static final int TRANSACTION_getUdcService = 34;
        static final int TRANSACTION_getUsageReportingService = 43;
        static final int TRANSACTION_getWalletService = 4;
        static final int TRANSACTION_getWalletServiceWithPackage = 42;
        static final int TRANSACTION_getWearableService = 26;
        static final int TRANSACTION_validateAccount = 47;

        private static class Proxy implements IGmsServiceBroker {
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

            public void getPlusService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, String authPackage, String[] scopes, String accountName, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    _data.writeString(authPackage);
                    _data.writeStringArray(scopes);
                    _data.writeString(accountName);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getPlusService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getPanoramaService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getPanoramaService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAppDataSearchService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getAppDataSearchService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getWalletService(IGmsCallbacks callbacks, int clientVersion) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    this.mRemote.transact(Stub.TRANSACTION_getWalletService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getPeopleService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getPeopleService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getReportingService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getReportingService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getLocationService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getLocationService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getGoogleLocationManagerService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getGoogleLocationManagerService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getGamesService(IGmsCallbacks callbacks, int clientVersion, String packageName, String accountName, String[] scopes, String gamePackageName, IBinder windowToken, String desiredLocale, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    _data.writeString(accountName);
                    _data.writeStringArray(scopes);
                    _data.writeString(gamePackageName);
                    _data.writeStrongBinder(windowToken);
                    _data.writeString(desiredLocale);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getGamesService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAppStateService(IGmsCallbacks callbacks, int clientVersion, String packageName, String accountName, String[] scopes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    _data.writeString(accountName);
                    _data.writeStringArray(scopes);
                    this.mRemote.transact(Stub.TRANSACTION_getAppStateService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getPlayLogService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getPlayLogService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAdMobService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAdMobService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getDroidGuardService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getDroidGuardService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getLockboxService(IGmsCallbacks callbacks, int clientVersion, String packageName, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getLockboxService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getCastMirroringService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getCastMirroringService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getNetworkQualityService(IGmsCallbacks callbacks, int clientVersion, String packageName, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getNetworkQualityService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAccountDataService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountDataService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getFeedbackService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getFeedbackService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getCastService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, IBinder listener, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    _data.writeStrongBinder(listener);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getCastService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getDriveService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, String[] scopes, String accountName, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    _data.writeStringArray(scopes);
                    _data.writeString(accountName);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getDriveService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getLightweightAppDataSearchService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getLightweightAppDataSearchService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getSearchAdministrationService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getSearchAdministrationService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAutoBackupService(IGmsCallbacks callbacks, int clientVersion, String packageName, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAutoBackupService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAddressService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getAddressService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getCarService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getCarService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getWearableService(IGmsCallbacks callbacks, int clientVersion, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    this.mRemote.transact(Stub.TRANSACTION_getWearableService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getIdentityService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getIdentityService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getAuthService_DEPRECATED() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAuthService_DEPRECATED, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getFitnessService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, String accountName, String[] scopes, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    _data.writeString(accountName);
                    _data.writeStringArray(scopes);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getFitnessService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getSearchQueriesService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getSearchQueriesService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getGlobalSearchAdminService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getGlobalSearchAdminService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getCloudSaveService(IGmsCallbacks callbacks, int clientVersion, String packageName, String accountName, String workspace, String[] scopes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    _data.writeString(accountName);
                    _data.writeString(workspace);
                    _data.writeStringArray(scopes);
                    this.mRemote.transact(Stub.TRANSACTION_getCloudSaveService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getUdcService(IGmsCallbacks callbacks, int clientVersion, String packageName, String accountName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    _data.writeString(accountName);
                    this.mRemote.transact(Stub.TRANSACTION_getUdcService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getSearchCorporaService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getSearchCorporaService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getDeviceManagerService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getDeviceManagerService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getPseudonymousIdService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getPseudonymousIdService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getRemindersService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getRemindersService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getCommonService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getCommonService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getClearcutLoggerService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getClearcutLoggerService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getWalletServiceWithPackage(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getWalletServiceWithPackage, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getUsageReportingService(IGmsCallbacks callbacks, int clientVersion, String callingPackage, Bundle extraArgs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    if (extraArgs != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        extraArgs.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getUsageReportingService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getDeviceConnectionService(IGmsCallbacks callbacks, int clientVersion, String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(packageName);
                    this.mRemote.transact(Stub.TRANSACTION_getDeviceConnectionService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getKidsService(IGmsCallbacks callbacks, int clientVersion, String callingPackage) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeInt(clientVersion);
                    _data.writeString(callingPackage);
                    this.mRemote.transact(Stub.TRANSACTION_getKidsService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void getService(IGmsCallbacks callbacks, GetServiceRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getService, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void validateAccount(IGmsCallbacks callbacks, ValidateAccountRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getPlusService);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_validateAccount, _data, _reply, 0);
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

        public static IGmsServiceBroker asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGmsServiceBroker)) {
                return new Proxy(obj);
            }
            return (IGmsServiceBroker) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IGmsCallbacks _arg0;
            int _arg1;
            String _arg2;
            String _arg3;
            String[] _arg4;
            String _arg5;
            Bundle _arg32;
            Bundle _arg52;
            switch (code) {
                case TRANSACTION_getPlusService /*1*/:
                    Bundle _arg6;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    _arg3 = data.readString();
                    _arg4 = data.createStringArray();
                    _arg5 = data.readString();
                    if (data.readInt() != 0) {
                        _arg6 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg6 = null;
                    }
                    getPlusService(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPanoramaService /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getPanoramaService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAppDataSearchService /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAppDataSearchService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getWalletService /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    getWalletService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPeopleService /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getPeopleService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getReportingService /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getReportingService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getLocationService /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getLocationService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getGoogleLocationManagerService /*8*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getGoogleLocationManagerService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getGamesService /*9*/:
                    Bundle _arg8;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    _arg3 = data.readString();
                    _arg4 = data.createStringArray();
                    _arg5 = data.readString();
                    IBinder _arg62 = data.readStrongBinder();
                    String _arg7 = data.readString();
                    if (data.readInt() != 0) {
                        _arg8 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg8 = null;
                    }
                    getGamesService(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg62, _arg7, _arg8);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAppStateService /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAppStateService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPlayLogService /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getPlayLogService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAdMobService /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getAdMobService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getDroidGuardService /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getDroidGuardService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getLockboxService /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getLockboxService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getCastMirroringService /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getCastMirroringService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getNetworkQualityService /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getNetworkQualityService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAccountDataService /*17*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getAccountDataService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getFeedbackService /*18*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getFeedbackService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getCastService /*19*/:
                    Bundle _arg42;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    IBinder _arg33 = data.readStrongBinder();
                    if (data.readInt() != 0) {
                        _arg42 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg42 = null;
                    }
                    getCastService(_arg0, _arg1, _arg2, _arg33, _arg42);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getDriveService /*20*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    String[] _arg34 = data.createStringArray();
                    String _arg43 = data.readString();
                    if (data.readInt() != 0) {
                        _arg52 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg52 = null;
                    }
                    getDriveService(_arg0, _arg1, _arg2, _arg34, _arg43, _arg52);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getLightweightAppDataSearchService /*21*/:
                    data.enforceInterface(DESCRIPTOR);
                    getLightweightAppDataSearchService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getSearchAdministrationService /*22*/:
                    data.enforceInterface(DESCRIPTOR);
                    getSearchAdministrationService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAutoBackupService /*23*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getAutoBackupService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAddressService /*24*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAddressService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getCarService /*25*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getCarService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getWearableService /*26*/:
                    data.enforceInterface(DESCRIPTOR);
                    getWearableService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getIdentityService /*27*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getIdentityService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getAuthService_DEPRECATED /*28*/:
                    data.enforceInterface(DESCRIPTOR);
                    getAuthService_DEPRECATED();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getFitnessService /*30*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    _arg3 = data.readString();
                    _arg4 = data.createStringArray();
                    if (data.readInt() != 0) {
                        _arg52 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg52 = null;
                    }
                    getFitnessService(_arg0, _arg1, _arg2, _arg3, _arg4, _arg52);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getSearchQueriesService /*31*/:
                    data.enforceInterface(DESCRIPTOR);
                    getSearchQueriesService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getGlobalSearchAdminService /*32*/:
                    data.enforceInterface(DESCRIPTOR);
                    getGlobalSearchAdminService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getCloudSaveService /*33*/:
                    data.enforceInterface(DESCRIPTOR);
                    getCloudSaveService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getUdcService /*34*/:
                    data.enforceInterface(DESCRIPTOR);
                    getUdcService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getSearchCorporaService /*35*/:
                    data.enforceInterface(DESCRIPTOR);
                    getSearchCorporaService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getDeviceManagerService /*36*/:
                    data.enforceInterface(DESCRIPTOR);
                    getDeviceManagerService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getPseudonymousIdService /*37*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getPseudonymousIdService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getRemindersService /*38*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getRemindersService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getCommonService /*40*/:
                    data.enforceInterface(DESCRIPTOR);
                    getCommonService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getClearcutLoggerService /*41*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getClearcutLoggerService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getWalletServiceWithPackage /*42*/:
                    data.enforceInterface(DESCRIPTOR);
                    getWalletServiceWithPackage(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getUsageReportingService /*43*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readInt();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    getUsageReportingService(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getDeviceConnectionService /*44*/:
                    data.enforceInterface(DESCRIPTOR);
                    getDeviceConnectionService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getKidsService /*45*/:
                    data.enforceInterface(DESCRIPTOR);
                    getKidsService(com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getService /*46*/:
                    GetServiceRequest _arg12;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg12 = (GetServiceRequest) GetServiceRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    getService(_arg0, _arg12);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_validateAccount /*47*/:
                    ValidateAccountRequest _arg13;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg13 = (ValidateAccountRequest) ValidateAccountRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    validateAccount(_arg0, _arg13);
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

    void getAccountDataService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getAdMobService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getAddressService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getAppDataSearchService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getAppStateService(IGmsCallbacks iGmsCallbacks, int i, String str, String str2, String[] strArr) throws RemoteException;

    void getAuthService_DEPRECATED() throws RemoteException;

    void getAutoBackupService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getCarService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getCastMirroringService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getCastService(IGmsCallbacks iGmsCallbacks, int i, String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void getClearcutLoggerService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getCloudSaveService(IGmsCallbacks iGmsCallbacks, int i, String str, String str2, String str3, String[] strArr) throws RemoteException;

    void getCommonService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getDeviceConnectionService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getDeviceManagerService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getDriveService(IGmsCallbacks iGmsCallbacks, int i, String str, String[] strArr, String str2, Bundle bundle) throws RemoteException;

    void getDroidGuardService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getFeedbackService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getFitnessService(IGmsCallbacks iGmsCallbacks, int i, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException;

    void getGamesService(IGmsCallbacks iGmsCallbacks, int i, String str, String str2, String[] strArr, String str3, IBinder iBinder, String str4, Bundle bundle) throws RemoteException;

    void getGlobalSearchAdminService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getGoogleLocationManagerService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getIdentityService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getKidsService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getLightweightAppDataSearchService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getLocationService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getLockboxService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getNetworkQualityService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getPanoramaService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getPeopleService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getPlayLogService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getPlusService(IGmsCallbacks iGmsCallbacks, int i, String str, String str2, String[] strArr, String str3, Bundle bundle) throws RemoteException;

    void getPseudonymousIdService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getRemindersService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getReportingService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getSearchAdministrationService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getSearchCorporaService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getSearchQueriesService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException;

    void getUdcService(IGmsCallbacks iGmsCallbacks, int i, String str, String str2) throws RemoteException;

    void getUsageReportingService(IGmsCallbacks iGmsCallbacks, int i, String str, Bundle bundle) throws RemoteException;

    void getWalletService(IGmsCallbacks iGmsCallbacks, int i) throws RemoteException;

    void getWalletServiceWithPackage(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void getWearableService(IGmsCallbacks iGmsCallbacks, int i, String str) throws RemoteException;

    void validateAccount(IGmsCallbacks iGmsCallbacks, ValidateAccountRequest validateAccountRequest) throws RemoteException;
}
