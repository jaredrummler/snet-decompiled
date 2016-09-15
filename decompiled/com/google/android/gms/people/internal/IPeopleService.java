package com.google.android.gms.people.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.google.android.gms.people.identity.internal.AccountToken;
import com.google.android.gms.people.identity.internal.ParcelableGetOptions;
import com.google.android.gms.people.identity.internal.ParcelableListOptions;
import com.google.android.gms.people.internal.autocomplete.ParcelableLoadAutocompleteResultsOptions;
import com.google.android.gms.people.internal.autocomplete.ParcelableLoadContactGroupFieldsOptions;
import com.google.android.gms.people.model.AvatarReference;
import java.util.List;

public interface IPeopleService extends IInterface {

    public static abstract class Stub extends Binder implements IPeopleService {
        private static final String DESCRIPTOR = "com.google.android.gms.people.internal.IPeopleService";
        static final int TRANSACTION_addCircle = 701;
        static final int TRANSACTION_addCircle26 = 27;
        static final int TRANSACTION_addPeopleToCircle = 28;
        static final int TRANSACTION_blockPerson = 7;
        static final int TRANSACTION_identityGetByIds = 501;
        static final int TRANSACTION_identityList = 601;
        static final int TRANSACTION_internalCall = 304;
        static final int TRANSACTION_isSyncToContactsEnabled = 16;
        static final int TRANSACTION_loadAddToCircleConsent = 101;
        static final int TRANSACTION_loadAutocompleteList = 507;
        static final int TRANSACTION_loadAutocompleteResults = 1301;
        static final int TRANSACTION_loadAvatar = 502;
        static final int TRANSACTION_loadAvatarByReference = 508;
        static final int TRANSACTION_loadAvatarLegacy = 5;
        static final int TRANSACTION_loadCircles = 19;
        static final int TRANSACTION_loadCirclesOld = 3;
        static final int TRANSACTION_loadContactGroupPreferredFields = 1302;
        static final int TRANSACTION_loadContactImage = 503;
        static final int TRANSACTION_loadContactImageLegacy = 6;
        static final int TRANSACTION_loadContactsGaiaIds = 403;
        static final int TRANSACTION_loadContactsGaiaIds24 = 25;
        static final int TRANSACTION_loadLog = 302;
        static final int TRANSACTION_loadMe = 1101;
        static final int TRANSACTION_loadOwnerAvatar = 505;
        static final int TRANSACTION_loadOwnerAvatarLegacy = 29;
        static final int TRANSACTION_loadOwnerCoverPhoto = 506;
        static final int TRANSACTION_loadOwnerCoverPhotoLegacy = 301;
        static final int TRANSACTION_loadOwners = 305;
        static final int TRANSACTION_loadOwners1 = 2;
        static final int TRANSACTION_loadPeople = 404;
        static final int TRANSACTION_loadPeople20 = 21;
        static final int TRANSACTION_loadPeople400 = 401;
        static final int TRANSACTION_loadPeopleForAggregation = 402;
        static final int TRANSACTION_loadPeopleForAggregation200 = 201;
        static final int TRANSACTION_loadPeopleForAggregation201 = 202;
        static final int TRANSACTION_loadPeopleForAggregation202 = 203;
        static final int TRANSACTION_loadPeopleForAggregation8 = 9;
        static final int TRANSACTION_loadPeopleLive = 22;
        static final int TRANSACTION_loadPeopleOld = 4;
        static final int TRANSACTION_loadPhoneNumbers = 1201;
        static final int TRANSACTION_loadRemoteImage = 504;
        static final int TRANSACTION_loadRemoteImageLegacy = 24;
        static final int TRANSACTION_registerDataChangedListener = 11;
        static final int TRANSACTION_removeCircle = 204;
        static final int TRANSACTION_requestSync = 205;
        static final int TRANSACTION_requestSyncOld = 17;
        static final int TRANSACTION_requestSyncOld19 = 20;
        static final int TRANSACTION_requestSyncOld25 = 26;
        static final int TRANSACTION_sendAutocompleteFeedback = 602;
        static final int TRANSACTION_sendInteractionFeedback = 509;
        static final int TRANSACTION_setAvatar = 18;
        static final int TRANSACTION_setAvatarOld = 13;
        static final int TRANSACTION_setHasShownAddToCircleConsent = 102;
        static final int TRANSACTION_setMe = 1102;
        static final int TRANSACTION_setSyncToContactsEnabled = 15;
        static final int TRANSACTION_setSyncToContactsSettings = 10;
        static final int TRANSACTION_starPerson = 603;
        static final int TRANSACTION_startSync = 12;
        static final int TRANSACTION_syncRawContact = 8;
        static final int TRANSACTION_updateCircle = 303;
        static final int TRANSACTION_updatePersonCircles = 23;
        static final int TRANSACTION_updatePersonCirclesOld = 14;

        private static class Proxy implements IPeopleService {
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

            public void loadOwners1(IPeopleCallbacks callbacks, boolean loadSingleOwner, boolean includePlusPages, String account, String pageId) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    int i2;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (loadSingleOwner) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    if (!includePlusPages) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    this.mRemote.transact(Stub.TRANSACTION_loadOwners1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadOwners(IPeopleCallbacks callbacks, boolean loadSingleOwner, boolean includePlusPages, String account, String pageId, int sortOrder) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    int i2;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (loadSingleOwner) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    if (!includePlusPages) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeInt(sortOrder);
                    this.mRemote.transact(Stub.TRANSACTION_loadOwners, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadCirclesOld(IPeopleCallbacks callbacks, String account, String pageId, String circleId, int circleType, String circleNamePrefix) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeInt(circleType);
                    _data.writeString(circleNamePrefix);
                    this.mRemote.transact(Stub.TRANSACTION_loadCirclesOld, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeopleOld(IPeopleCallbacks callbacks, String account, String pageId, String circleId, List<String> qualifiedIds, int projection, boolean peopleOnly, long changedSince) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeStringList(qualifiedIds);
                    _data.writeInt(projection);
                    if (peopleOnly) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeLong(changedSince);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleOld, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadAvatarLegacy(IPeopleCallbacks callbacks, String avatarUrl, int avatarSize, int options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(avatarUrl);
                    _data.writeInt(avatarSize);
                    _data.writeInt(options);
                    this.mRemote.transact(Stub.TRANSACTION_loadAvatarLegacy, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadContactImageLegacy(IPeopleCallbacks callbacks, long contactId, boolean thumbnail) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeLong(contactId);
                    if (thumbnail) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_loadContactImageLegacy, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void blockPerson(IPeopleCallbacks callbacks, String account, String pageId, String gaiaId, boolean blockOrUnblock) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(gaiaId);
                    if (blockOrUnblock) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_blockPerson, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void starPerson(IPeopleCallbacks callbacks, String account, String pageId, String peopleV2PersonId, boolean starOrUnstar) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(peopleV2PersonId);
                    if (starOrUnstar) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_starPerson, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle syncRawContact(Uri rawContactUri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rawContactUri != null) {
                        _data.writeInt(1);
                        rawContactUri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_syncRawContact, _data, _reply, 0);
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

            public void loadPeopleForAggregation8(IPeopleCallbacks callbacks, String account, String pageId, String query, boolean peopleOnly, int projection) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(query);
                    if (peopleOnly) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeInt(projection);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleForAggregation8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeopleForAggregation200(IPeopleCallbacks callbacks, String account, String pageId, String query, boolean peopleOnly, int projection, int extraColumns) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(query);
                    if (peopleOnly) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeInt(projection);
                    _data.writeInt(extraColumns);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleForAggregation200, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeopleForAggregation201(IPeopleCallbacks callbacks, String account, String pageId, String query, int searchField, boolean peopleOnly, int projection, int extraColumns, String filterGaiaId) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(query);
                    _data.writeInt(searchField);
                    if (peopleOnly) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeInt(projection);
                    _data.writeInt(extraColumns);
                    _data.writeString(filterGaiaId);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleForAggregation201, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeopleForAggregation202(IPeopleCallbacks callbacks, String account, String pageId, String query, int searchField, boolean peopleOnly, int projection, int extraColumns, String filterGaiaId, boolean includeEvergreenPeople) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    int i2;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(query);
                    _data.writeInt(searchField);
                    if (peopleOnly) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    _data.writeInt(projection);
                    _data.writeInt(extraColumns);
                    _data.writeString(filterGaiaId);
                    if (!includeEvergreenPeople) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleForAggregation202, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeopleForAggregation(IPeopleCallbacks callbacks, String account, String pageId, String query, int searchField, boolean peopleOnly, int projection, int extraColumns, String filterGaiaId, boolean includeEvergreenPeople, int sortOrder, int gaiaEdgeTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(query);
                    _data.writeInt(searchField);
                    _data.writeInt(peopleOnly ? 1 : 0);
                    _data.writeInt(projection);
                    _data.writeInt(extraColumns);
                    _data.writeString(filterGaiaId);
                    _data.writeInt(includeEvergreenPeople ? 1 : 0);
                    _data.writeInt(sortOrder);
                    _data.writeInt(gaiaEdgeTypes);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleForAggregation, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setSyncToContactsSettings(IPeopleCallbacks callbacks, String account, boolean enableForAccount, String[] circlesToSync) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    if (enableForAccount) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeStringArray(circlesToSync);
                    this.mRemote.transact(Stub.TRANSACTION_setSyncToContactsSettings, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle registerDataChangedListener(IPeopleCallbacks callbacks, boolean registerOrUnregister, String account, String pageId, int scopes) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (registerOrUnregister) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeInt(scopes);
                    this.mRemote.transact(Stub.TRANSACTION_registerDataChangedListener, _data, _reply, 0);
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

            public Bundle startSync(String account, String pageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    this.mRemote.transact(Stub.TRANSACTION_startSync, _data, _reply, 0);
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

            public void setAvatarOld(IPeopleCallbacks callbacks, String account, String pageId, Uri uri) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_setAvatarOld, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void updatePersonCirclesOld(IPeopleCallbacks callbacks, String account, String pageId, String qualifiedId, List<String> circleIdsToAdd, List<String> circleIdsToRemove) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(qualifiedId);
                    _data.writeStringList(circleIdsToAdd);
                    _data.writeStringList(circleIdsToRemove);
                    this.mRemote.transact(Stub.TRANSACTION_updatePersonCirclesOld, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setSyncToContactsEnabled(boolean enable) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (enable) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_setSyncToContactsEnabled, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isSyncToContactsEnabled() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_isSyncToContactsEnabled, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle requestSyncOld(String account, String pageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    this.mRemote.transact(Stub.TRANSACTION_requestSyncOld, _data, _reply, 0);
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

            public void setAvatar(IPeopleCallbacks callbacks, String account, String pageId, Uri uri, boolean insertCameraImage) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    if (uri != null) {
                        _data.writeInt(1);
                        uri.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!insertCameraImage) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_setAvatar, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadCircles(IPeopleCallbacks callbacks, String account, String pageId, String circleId, int circleType, String circleNamePrefix, boolean getVisibility) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeInt(circleType);
                    _data.writeString(circleNamePrefix);
                    if (getVisibility) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_loadCircles, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle requestSyncOld19(String account, String pageId, long allowanceSecond) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeLong(allowanceSecond);
                    this.mRemote.transact(Stub.TRANSACTION_requestSyncOld19, _data, _reply, 0);
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

            public void loadPeople20(IPeopleCallbacks callbacks, String account, String pageId, String circleId, List<String> qualifiedIds, int projection, boolean peopleOnly, long changedSince, String query, int searchFields) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeStringList(qualifiedIds);
                    _data.writeInt(projection);
                    _data.writeInt(peopleOnly ? 1 : 0);
                    _data.writeLong(changedSince);
                    _data.writeString(query);
                    _data.writeInt(searchFields);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeople20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeople400(IPeopleCallbacks callbacks, String account, String pageId, String circleId, List<String> qualifiedIds, int projection, boolean peopleOnly, long changedSince, String query, int searchFields, int sortOrder) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeStringList(qualifiedIds);
                    _data.writeInt(projection);
                    _data.writeInt(peopleOnly ? 1 : 0);
                    _data.writeLong(changedSince);
                    _data.writeString(query);
                    _data.writeInt(searchFields);
                    _data.writeInt(sortOrder);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeople400, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeople(IPeopleCallbacks callbacks, String account, String pageId, String circleId, List<String> qualifiedIds, int projection, boolean peopleOnly, long changedSince, String query, int searchFields, int sortOrder, int extraColumns) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeStringList(qualifiedIds);
                    _data.writeInt(projection);
                    _data.writeInt(peopleOnly ? 1 : 0);
                    _data.writeLong(changedSince);
                    _data.writeString(query);
                    _data.writeInt(searchFields);
                    _data.writeInt(sortOrder);
                    _data.writeInt(extraColumns);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeople, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadPeopleLive(IPeopleCallbacks callbacks, String account, String pageId, String query, int pageSize, String pageToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(query);
                    _data.writeInt(pageSize);
                    _data.writeString(pageToken);
                    this.mRemote.transact(Stub.TRANSACTION_loadPeopleLive, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void updatePersonCircles(IPeopleCallbacks callbacks, String account, String pageId, String qualifiedId, List<String> circleIdsToAdd, List<String> circleIdsToRemove, FavaDiagnosticsEntity startView) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(qualifiedId);
                    _data.writeStringList(circleIdsToAdd);
                    _data.writeStringList(circleIdsToRemove);
                    if (startView != null) {
                        _data.writeInt(1);
                        startView.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_updatePersonCircles, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadRemoteImageLegacy(IPeopleCallbacks callbacks, String url) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(url);
                    this.mRemote.transact(Stub.TRANSACTION_loadRemoteImageLegacy, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadContactsGaiaIds24(IPeopleCallbacks callbacks, String filterContactInfo, String filterGaiaId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(filterContactInfo);
                    _data.writeString(filterGaiaId);
                    this.mRemote.transact(Stub.TRANSACTION_loadContactsGaiaIds24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadContactsGaiaIds(IPeopleCallbacks callbacks, String filterContactInfo, String filterGaiaId, int filterGaiaEdgeTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(filterContactInfo);
                    _data.writeString(filterGaiaId);
                    _data.writeInt(filterGaiaEdgeTypes);
                    this.mRemote.transact(Stub.TRANSACTION_loadContactsGaiaIds, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bundle requestSyncOld25(String account, String pageId, long allowanceSecond, boolean byUserAction) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeLong(allowanceSecond);
                    if (byUserAction) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_requestSyncOld25, _data, _reply, 0);
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

            public Bundle requestSync(String account, String pageId, long allowanceSecond, boolean byUserAction, boolean isDisabledByBackgroundSync) throws RemoteException {
                int i = 1;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    int i2;
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeLong(allowanceSecond);
                    if (byUserAction) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    if (!isDisabledByBackgroundSync) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_requestSync, _data, _reply, 0);
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

            public void loadAddToCircleConsent(IPeopleCallbacks callbacks, String account, String pageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    this.mRemote.transact(Stub.TRANSACTION_loadAddToCircleConsent, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setHasShownAddToCircleConsent(IPeopleCallbacks callbacks, String account, String pageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    this.mRemote.transact(Stub.TRANSACTION_setHasShownAddToCircleConsent, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addCircle26(IPeopleCallbacks callbacks, String account, String pageId, String circleName, String circleDescription) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleName);
                    _data.writeString(circleDescription);
                    this.mRemote.transact(Stub.TRANSACTION_addCircle26, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addCircle(IPeopleCallbacks callbacks, String account, String pageId, String circleName, String circleDescription, boolean enabledForSharing) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleName);
                    _data.writeString(circleDescription);
                    if (enabledForSharing) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_addCircle, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void addPeopleToCircle(IPeopleCallbacks callbacks, String account, String pageId, String circleId, List<String> qualifiedPersonIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeStringList(qualifiedPersonIds);
                    this.mRemote.transact(Stub.TRANSACTION_addPeopleToCircle, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadOwnerAvatarLegacy(IPeopleCallbacks callbacks, String ownerAccount, String pageId, int avatarSize, int options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(ownerAccount);
                    _data.writeString(pageId);
                    _data.writeInt(avatarSize);
                    _data.writeInt(options);
                    this.mRemote.transact(Stub.TRANSACTION_loadOwnerAvatarLegacy, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeCircle(IPeopleCallbacks callbacks, String account, String pageId, String circleId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    this.mRemote.transact(Stub.TRANSACTION_removeCircle, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadOwnerCoverPhotoLegacy(IPeopleCallbacks callbacks, String ownerAccount, String pageId, int minimumWidth) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(ownerAccount);
                    _data.writeString(pageId);
                    _data.writeInt(minimumWidth);
                    this.mRemote.transact(Stub.TRANSACTION_loadOwnerCoverPhotoLegacy, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadLog(IPeopleCallbacks callbacks, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_loadLog, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void updateCircle(IPeopleCallbacks callbacks, String account, String pageId, String circleId, String newName, int newEnabledForSharingTriState, String newCircleDescription) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    _data.writeString(circleId);
                    _data.writeString(newName);
                    _data.writeInt(newEnabledForSharingTriState);
                    _data.writeString(newCircleDescription);
                    this.mRemote.transact(Stub.TRANSACTION_updateCircle, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void internalCall(IPeopleCallbacks callbacks, Bundle arguments) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (arguments != null) {
                        _data.writeInt(1);
                        arguments.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_internalCall, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void identityGetByIds(IPeopleCallbacks callbacks, AccountToken accountToken, List<String> qualifiedIds, ParcelableGetOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (accountToken != null) {
                        _data.writeInt(1);
                        accountToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStringList(qualifiedIds);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_identityGetByIds, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadAvatar(IPeopleCallbacks callbacks, String avatarUrl, int avatarSize, int options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(avatarUrl);
                    _data.writeInt(avatarSize);
                    _data.writeInt(options);
                    this.mRemote.transact(Stub.TRANSACTION_loadAvatar, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadContactImage(IPeopleCallbacks callbacks, long contactId, boolean thumbnail) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeLong(contactId);
                    if (thumbnail) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_loadContactImage, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadRemoteImage(IPeopleCallbacks callbacks, String url) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(url);
                    this.mRemote.transact(Stub.TRANSACTION_loadRemoteImage, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadOwnerAvatar(IPeopleCallbacks callbacks, String ownerAccount, String pageId, int avatarSize, int options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(ownerAccount);
                    _data.writeString(pageId);
                    _data.writeInt(avatarSize);
                    _data.writeInt(options);
                    this.mRemote.transact(Stub.TRANSACTION_loadOwnerAvatar, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadOwnerCoverPhoto(IPeopleCallbacks callbacks, String ownerAccount, String pageId, int minimumWidth) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(ownerAccount);
                    _data.writeString(pageId);
                    _data.writeInt(minimumWidth);
                    this.mRemote.transact(Stub.TRANSACTION_loadOwnerCoverPhoto, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadAutocompleteList(IPeopleCallbacks callbacks, String ownerAccount, String pageId, boolean isDirectory, String directoryAccountType, String query, int autocompleteType, int searchOptions, int numberOfResults, boolean useCp2Fallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(ownerAccount);
                    _data.writeString(pageId);
                    _data.writeInt(isDirectory ? 1 : 0);
                    _data.writeString(directoryAccountType);
                    _data.writeString(query);
                    _data.writeInt(autocompleteType);
                    _data.writeInt(searchOptions);
                    _data.writeInt(numberOfResults);
                    _data.writeInt(useCp2Fallback ? 1 : 0);
                    this.mRemote.transact(Stub.TRANSACTION_loadAutocompleteList, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadAvatarByReference(IPeopleCallbacks callbacks, AvatarReference avatarReference, ParcelableLoadImageOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (avatarReference != null) {
                        _data.writeInt(1);
                        avatarReference.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_loadAvatarByReference, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken sendInteractionFeedback(IPeopleCallbacks callbacks, String contactItem, int interactionType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(contactItem);
                    _data.writeInt(interactionType);
                    this.mRemote.transact(Stub.TRANSACTION_sendInteractionFeedback, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken identityList(IPeopleCallbacks callbacks, AccountToken accountToken, ParcelableListOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (accountToken != null) {
                        _data.writeInt(1);
                        accountToken.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_identityList, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken sendAutocompleteFeedback(IPeopleCallbacks callbacks, DataHolder dataHolder, int selectedIndex, int interactionType, long sessionId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    if (dataHolder != null) {
                        _data.writeInt(1);
                        dataHolder.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(selectedIndex);
                    _data.writeInt(interactionType);
                    _data.writeLong(sessionId);
                    this.mRemote.transact(Stub.TRANSACTION_sendAutocompleteFeedback, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void loadMe(IPeopleCallbacks callbacks, String account, String pageId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    this.mRemote.transact(Stub.TRANSACTION_loadMe, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setMe(IPeopleCallbacks callbacks, String account, String pageId, Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_setMe, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadPhoneNumbers(IPeopleCallbacks callbacks, String account, String pageId, Bundle options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    _data.writeString(pageId);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_loadPhoneNumbers, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadAutocompleteResults(IPeopleCallbacks callbacks, String account, ParcelableLoadAutocompleteResultsOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_loadAutocompleteResults, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public ICancelToken loadContactGroupPreferredFields(IPeopleCallbacks callbacks, String account, ParcelableLoadContactGroupFieldsOptions options) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callbacks != null ? callbacks.asBinder() : null);
                    _data.writeString(account);
                    if (options != null) {
                        _data.writeInt(1);
                        options.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_loadContactGroupPreferredFields, _data, _reply, 0);
                    _reply.readException();
                    ICancelToken _result = com.google.android.gms.common.internal.ICancelToken.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPeopleService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPeopleService)) {
                return new Proxy(obj);
            }
            return (IPeopleService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _result;
            IPeopleCallbacks _arg0;
            String _arg1;
            String _arg2;
            Uri _arg3;
            Bundle _arg12;
            AccountToken _arg13;
            ICancelToken _result2;
            Bundle _arg32;
            switch (code) {
                case TRANSACTION_loadOwners1 /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadOwners1(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt() != 0, data.readInt() != 0, data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadCirclesOld /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadCirclesOld(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeopleOld /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleOld(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadAvatarLegacy /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadAvatarLegacy(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadContactImageLegacy /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadContactImageLegacy(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readLong(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_blockPerson /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    blockPerson(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_syncRawContact /*8*/:
                    Uri _arg02;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    _result = syncRawContact(_arg02);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_loadPeopleForAggregation8 /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleForAggregation8(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0, data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setSyncToContactsSettings /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    setSyncToContactsSettings(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readInt() != 0, data.createStringArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_registerDataChangedListener /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = registerDataChangedListener(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt() != 0, data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_startSync /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = startSync(data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_setAvatarOld /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    setAvatarOld(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updatePersonCirclesOld /*14*/:
                    data.enforceInterface(DESCRIPTOR);
                    updatePersonCirclesOld(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.createStringArrayList());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setSyncToContactsEnabled /*15*/:
                    data.enforceInterface(DESCRIPTOR);
                    setSyncToContactsEnabled(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_isSyncToContactsEnabled /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result3 = isSyncToContactsEnabled();
                    reply.writeNoException();
                    reply.writeInt(_result3 ? 1 : 0);
                    return true;
                case TRANSACTION_requestSyncOld /*17*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = requestSyncOld(data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_setAvatar /*18*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg3 = (Uri) Uri.CREATOR.createFromParcel(data);
                    } else {
                        _arg3 = null;
                    }
                    setAvatar(_arg0, _arg1, _arg2, _arg3, data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadCircles /*19*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadCircles(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_requestSyncOld19 /*20*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = requestSyncOld19(data.readString(), data.readString(), data.readLong());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_loadPeople20 /*21*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeople20(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeopleLive /*22*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleLive(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updatePersonCircles /*23*/:
                    FavaDiagnosticsEntity _arg6;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    _arg2 = data.readString();
                    String _arg33 = data.readString();
                    List<String> _arg4 = data.createStringArrayList();
                    List<String> _arg5 = data.createStringArrayList();
                    if (data.readInt() != 0) {
                        _arg6 = FavaDiagnosticsEntity.CREATOR.createFromParcel(data);
                    } else {
                        _arg6 = null;
                    }
                    updatePersonCircles(_arg0, _arg1, _arg2, _arg33, _arg4, _arg5, _arg6);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadRemoteImageLegacy /*24*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadRemoteImageLegacy(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadContactsGaiaIds24 /*25*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadContactsGaiaIds24(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_requestSyncOld25 /*26*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = requestSyncOld25(data.readString(), data.readString(), data.readLong(), data.readInt() != 0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_addCircle26 /*27*/:
                    data.enforceInterface(DESCRIPTOR);
                    addCircle26(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addPeopleToCircle /*28*/:
                    data.enforceInterface(DESCRIPTOR);
                    addPeopleToCircle(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadOwnerAvatarLegacy /*29*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadOwnerAvatarLegacy(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadAddToCircleConsent /*101*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadAddToCircleConsent(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setHasShownAddToCircleConsent /*102*/:
                    data.enforceInterface(DESCRIPTOR);
                    setHasShownAddToCircleConsent(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeopleForAggregation200 /*201*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleForAggregation200(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0, data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeopleForAggregation201 /*202*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleForAggregation201(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeopleForAggregation202 /*203*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleForAggregation202(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt(), data.readInt(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeCircle /*204*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeCircle(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_requestSync /*205*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = requestSync(data.readString(), data.readString(), data.readLong(), data.readInt() != 0, data.readInt() != 0);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case TRANSACTION_loadOwnerCoverPhotoLegacy /*301*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadOwnerCoverPhotoLegacy(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadLog /*302*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg12 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    loadLog(_arg0, _arg12);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_updateCircle /*303*/:
                    data.enforceInterface(DESCRIPTOR);
                    updateCircle(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_internalCall /*304*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg12 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg12 = null;
                    }
                    internalCall(_arg0, _arg12);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadOwners /*305*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadOwners(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readInt() != 0, data.readInt() != 0, data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeople400 /*401*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeople400(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeopleForAggregation /*402*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeopleForAggregation(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt(), data.readInt(), data.readString(), data.readInt() != 0, data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadContactsGaiaIds /*403*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadContactsGaiaIds(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPeople /*404*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadPeople(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong(), data.readString(), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_identityGetByIds /*501*/:
                    ParcelableGetOptions _arg34;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg13 = AccountToken.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    List<String> _arg22 = data.createStringArrayList();
                    if (data.readInt() != 0) {
                        _arg34 = ParcelableGetOptions.CREATOR.createFromParcel(data);
                    } else {
                        _arg34 = null;
                    }
                    identityGetByIds(_arg0, _arg13, _arg22, _arg34);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadAvatar /*502*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = loadAvatar(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadContactImage /*503*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = loadContactImage(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readLong(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadRemoteImage /*504*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = loadRemoteImage(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadOwnerAvatar /*505*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = loadOwnerAvatar(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadOwnerCoverPhoto /*506*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = loadOwnerCoverPhoto(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadAutocompleteList /*507*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = loadAutocompleteList(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readInt() != 0, data.readString(), data.readString(), data.readInt(), data.readInt(), data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadAvatarByReference /*508*/:
                    AvatarReference _arg14;
                    ParcelableLoadImageOptions _arg23;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg14 = AvatarReference.CREATOR.createFromParcel(data);
                    } else {
                        _arg14 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg23 = ParcelableLoadImageOptions.CREATOR.createFromParcel(data);
                    } else {
                        _arg23 = null;
                    }
                    _result2 = loadAvatarByReference(_arg0, _arg14, _arg23);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_sendInteractionFeedback /*509*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result2 = sendInteractionFeedback(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_identityList /*601*/:
                    ParcelableListOptions _arg24;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg13 = AccountToken.CREATOR.createFromParcel(data);
                    } else {
                        _arg13 = null;
                    }
                    if (data.readInt() != 0) {
                        _arg24 = ParcelableListOptions.CREATOR.createFromParcel(data);
                    } else {
                        _arg24 = null;
                    }
                    _result2 = identityList(_arg0, _arg13, _arg24);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_sendAutocompleteFeedback /*602*/:
                    DataHolder _arg15;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        _arg15 = DataHolder.CREATOR.createFromParcel(data);
                    } else {
                        _arg15 = null;
                    }
                    _result2 = sendAutocompleteFeedback(_arg0, _arg15, data.readInt(), data.readInt(), data.readLong());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_starPerson /*603*/:
                    data.enforceInterface(DESCRIPTOR);
                    starPerson(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_addCircle /*701*/:
                    data.enforceInterface(DESCRIPTOR);
                    addCircle(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadMe /*1101*/:
                    data.enforceInterface(DESCRIPTOR);
                    loadMe(com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_setMe /*1102*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    setMe(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_loadPhoneNumbers /*1201*/:
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    _arg2 = data.readString();
                    if (data.readInt() != 0) {
                        _arg32 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg32 = null;
                    }
                    _result2 = loadPhoneNumbers(_arg0, _arg1, _arg2, _arg32);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadAutocompleteResults /*1301*/:
                    ParcelableLoadAutocompleteResultsOptions _arg25;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg25 = ParcelableLoadAutocompleteResultsOptions.CREATOR.createFromParcel(data);
                    } else {
                        _arg25 = null;
                    }
                    _result2 = loadAutocompleteResults(_arg0, _arg1, _arg25);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case TRANSACTION_loadContactGroupPreferredFields /*1302*/:
                    ParcelableLoadContactGroupFieldsOptions _arg26;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = com.google.android.gms.people.internal.IPeopleCallbacks.Stub.asInterface(data.readStrongBinder());
                    _arg1 = data.readString();
                    if (data.readInt() != 0) {
                        _arg26 = ParcelableLoadContactGroupFieldsOptions.CREATOR.createFromParcel(data);
                    } else {
                        _arg26 = null;
                    }
                    _result2 = loadContactGroupPreferredFields(_arg0, _arg1, _arg26);
                    reply.writeNoException();
                    reply.writeStrongBinder(_result2 != null ? _result2.asBinder() : null);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void addCircle(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, String str4, boolean z) throws RemoteException;

    void addCircle26(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, String str4) throws RemoteException;

    void addPeopleToCircle(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list) throws RemoteException;

    void blockPerson(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, boolean z) throws RemoteException;

    void identityGetByIds(IPeopleCallbacks iPeopleCallbacks, AccountToken accountToken, List<String> list, ParcelableGetOptions parcelableGetOptions) throws RemoteException;

    ICancelToken identityList(IPeopleCallbacks iPeopleCallbacks, AccountToken accountToken, ParcelableListOptions parcelableListOptions) throws RemoteException;

    void internalCall(IPeopleCallbacks iPeopleCallbacks, Bundle bundle) throws RemoteException;

    boolean isSyncToContactsEnabled() throws RemoteException;

    void loadAddToCircleConsent(IPeopleCallbacks iPeopleCallbacks, String str, String str2) throws RemoteException;

    ICancelToken loadAutocompleteList(IPeopleCallbacks iPeopleCallbacks, String str, String str2, boolean z, String str3, String str4, int i, int i2, int i3, boolean z2) throws RemoteException;

    ICancelToken loadAutocompleteResults(IPeopleCallbacks iPeopleCallbacks, String str, ParcelableLoadAutocompleteResultsOptions parcelableLoadAutocompleteResultsOptions) throws RemoteException;

    ICancelToken loadAvatar(IPeopleCallbacks iPeopleCallbacks, String str, int i, int i2) throws RemoteException;

    ICancelToken loadAvatarByReference(IPeopleCallbacks iPeopleCallbacks, AvatarReference avatarReference, ParcelableLoadImageOptions parcelableLoadImageOptions) throws RemoteException;

    void loadAvatarLegacy(IPeopleCallbacks iPeopleCallbacks, String str, int i, int i2) throws RemoteException;

    void loadCircles(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, int i, String str4, boolean z) throws RemoteException;

    void loadCirclesOld(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, int i, String str4) throws RemoteException;

    ICancelToken loadContactGroupPreferredFields(IPeopleCallbacks iPeopleCallbacks, String str, ParcelableLoadContactGroupFieldsOptions parcelableLoadContactGroupFieldsOptions) throws RemoteException;

    ICancelToken loadContactImage(IPeopleCallbacks iPeopleCallbacks, long j, boolean z) throws RemoteException;

    void loadContactImageLegacy(IPeopleCallbacks iPeopleCallbacks, long j, boolean z) throws RemoteException;

    void loadContactsGaiaIds(IPeopleCallbacks iPeopleCallbacks, String str, String str2, int i) throws RemoteException;

    void loadContactsGaiaIds24(IPeopleCallbacks iPeopleCallbacks, String str, String str2) throws RemoteException;

    void loadLog(IPeopleCallbacks iPeopleCallbacks, Bundle bundle) throws RemoteException;

    void loadMe(IPeopleCallbacks iPeopleCallbacks, String str, String str2) throws RemoteException;

    ICancelToken loadOwnerAvatar(IPeopleCallbacks iPeopleCallbacks, String str, String str2, int i, int i2) throws RemoteException;

    void loadOwnerAvatarLegacy(IPeopleCallbacks iPeopleCallbacks, String str, String str2, int i, int i2) throws RemoteException;

    ICancelToken loadOwnerCoverPhoto(IPeopleCallbacks iPeopleCallbacks, String str, String str2, int i) throws RemoteException;

    void loadOwnerCoverPhotoLegacy(IPeopleCallbacks iPeopleCallbacks, String str, String str2, int i) throws RemoteException;

    void loadOwners(IPeopleCallbacks iPeopleCallbacks, boolean z, boolean z2, String str, String str2, int i) throws RemoteException;

    void loadOwners1(IPeopleCallbacks iPeopleCallbacks, boolean z, boolean z2, String str, String str2) throws RemoteException;

    void loadPeople(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2, int i3, int i4) throws RemoteException;

    void loadPeople20(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2) throws RemoteException;

    void loadPeople400(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2, int i3) throws RemoteException;

    void loadPeopleForAggregation(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4, boolean z2, int i4, int i5) throws RemoteException;

    void loadPeopleForAggregation200(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, boolean z, int i, int i2) throws RemoteException;

    void loadPeopleForAggregation201(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4) throws RemoteException;

    void loadPeopleForAggregation202(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4, boolean z2) throws RemoteException;

    void loadPeopleForAggregation8(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, boolean z, int i) throws RemoteException;

    void loadPeopleLive(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, int i, String str4) throws RemoteException;

    void loadPeopleOld(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list, int i, boolean z, long j) throws RemoteException;

    ICancelToken loadPhoneNumbers(IPeopleCallbacks iPeopleCallbacks, String str, String str2, Bundle bundle) throws RemoteException;

    ICancelToken loadRemoteImage(IPeopleCallbacks iPeopleCallbacks, String str) throws RemoteException;

    void loadRemoteImageLegacy(IPeopleCallbacks iPeopleCallbacks, String str) throws RemoteException;

    Bundle registerDataChangedListener(IPeopleCallbacks iPeopleCallbacks, boolean z, String str, String str2, int i) throws RemoteException;

    void removeCircle(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3) throws RemoteException;

    Bundle requestSync(String str, String str2, long j, boolean z, boolean z2) throws RemoteException;

    Bundle requestSyncOld(String str, String str2) throws RemoteException;

    Bundle requestSyncOld19(String str, String str2, long j) throws RemoteException;

    Bundle requestSyncOld25(String str, String str2, long j, boolean z) throws RemoteException;

    ICancelToken sendAutocompleteFeedback(IPeopleCallbacks iPeopleCallbacks, DataHolder dataHolder, int i, int i2, long j) throws RemoteException;

    ICancelToken sendInteractionFeedback(IPeopleCallbacks iPeopleCallbacks, String str, int i) throws RemoteException;

    void setAvatar(IPeopleCallbacks iPeopleCallbacks, String str, String str2, Uri uri, boolean z) throws RemoteException;

    void setAvatarOld(IPeopleCallbacks iPeopleCallbacks, String str, String str2, Uri uri) throws RemoteException;

    void setHasShownAddToCircleConsent(IPeopleCallbacks iPeopleCallbacks, String str, String str2) throws RemoteException;

    void setMe(IPeopleCallbacks iPeopleCallbacks, String str, String str2, Bundle bundle) throws RemoteException;

    void setSyncToContactsEnabled(boolean z) throws RemoteException;

    void setSyncToContactsSettings(IPeopleCallbacks iPeopleCallbacks, String str, boolean z, String[] strArr) throws RemoteException;

    void starPerson(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, boolean z) throws RemoteException;

    Bundle startSync(String str, String str2) throws RemoteException;

    Bundle syncRawContact(Uri uri) throws RemoteException;

    void updateCircle(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, String str4, int i, String str5) throws RemoteException;

    void updatePersonCircles(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list, List<String> list2, FavaDiagnosticsEntity favaDiagnosticsEntity) throws RemoteException;

    void updatePersonCirclesOld(IPeopleCallbacks iPeopleCallbacks, String str, String str2, String str3, List<String> list, List<String> list2) throws RemoteException;
}
