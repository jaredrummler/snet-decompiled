package com.google.android.gms.auth.firstparty.dataservice;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;

public interface IGoogleAccountDataService extends IInterface {

    public static abstract class Stub extends Binder implements IGoogleAccountDataService {
        private static final String DESCRIPTOR = "com.google.android.gms.auth.firstparty.dataservice.IGoogleAccountDataService";
        static final int TRANSACTION_checkAccountName = 2;
        static final int TRANSACTION_checkFrpCompliance = 27;
        static final int TRANSACTION_checkPassword = 3;
        static final int TRANSACTION_checkRealName = 4;
        static final int TRANSACTION_clearFactoryResetChallenges = 29;
        static final int TRANSACTION_clearToken = 19;
        static final int TRANSACTION_clearWorkAccountAppWhitelist = 35;
        static final int TRANSACTION_confirmCredentials = 10;
        static final int TRANSACTION_createAccount = 5;
        static final int TRANSACTION_createPlusProfile = 7;
        static final int TRANSACTION_getAccountChangeEvents = 23;
        static final int TRANSACTION_getAccountData = 1;
        static final int TRANSACTION_getAccountExportData = 16;
        static final int TRANSACTION_getAccountId = 25;
        static final int TRANSACTION_getAccountRecoveryCountryInfo = 12;
        static final int TRANSACTION_getAccountRecoveryData = 13;
        static final int TRANSACTION_getAccountRecoveryGuidance = 15;
        static final int TRANSACTION_getAndAdvanceOtpCounter = 37;
        static final int TRANSACTION_getGoogleAccountData = 30;
        static final int TRANSACTION_getGoogleAccountId = 31;
        static final int TRANSACTION_getGplusInfo = 6;
        static final int TRANSACTION_getOtp = 24;
        static final int TRANSACTION_getReauthSettings = 21;
        static final int TRANSACTION_getToken = 8;
        static final int TRANSACTION_getWebSetupConfig = 18;
        static final int TRANSACTION_installAccountFromExportData = 17;
        static final int TRANSACTION_removeAccount = 20;
        static final int TRANSACTION_setWorkAccountAppWhitelistFingerprint = 34;
        static final int TRANSACTION_signIn = 9;
        static final int TRANSACTION_updateAccountRecoveryData = 14;
        static final int TRANSACTION_updateCredentials = 11;
        static final int TRANSACTION_validateAccountCredentials = 36;
        static final int TRANSACTION_verifyPin = 22;

        private static class Proxy implements IGoogleAccountDataService {
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

            public GoogleAccountData getAccountData(String accountName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    GoogleAccountData _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountName);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GoogleAccountData.CREATOR.createFromParcel(_reply);
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

            public AccountNameCheckResponse checkAccountName(AccountNameCheckRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountNameCheckResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_checkAccountName, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AccountNameCheckResponse.CREATOR.createFromParcel(_reply);
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

            public PasswordCheckResponse checkPassword(PasswordCheckRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    PasswordCheckResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_checkPassword, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PasswordCheckResponse.CREATOR.createFromParcel(_reply);
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

            public CheckRealNameResponse checkRealName(CheckRealNameRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    CheckRealNameResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_checkRealName, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CheckRealNameResponse.CREATOR.createFromParcel(_reply);
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

            public TokenResponse createAccount(GoogleAccountSetupRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    TokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_createAccount, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TokenResponse.CREATOR.createFromParcel(_reply);
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

            public GplusInfoResponse getGplusInfo(GplusInfoRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    GplusInfoResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getGplusInfo, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GplusInfoResponse.CREATOR.createFromParcel(_reply);
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

            public TokenResponse createPlusProfile(GoogleAccountSetupRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    TokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_createPlusProfile, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TokenResponse.CREATOR.createFromParcel(_reply);
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

            public TokenResponse getToken(TokenRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    TokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getToken, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TokenResponse.CREATOR.createFromParcel(_reply);
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

            public TokenResponse signIn(AccountSignInRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    TokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_signIn, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TokenResponse.CREATOR.createFromParcel(_reply);
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

            public TokenResponse confirmCredentials(ConfirmCredentialsRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    TokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_confirmCredentials, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TokenResponse.CREATOR.createFromParcel(_reply);
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

            public TokenResponse updateCredentials(UpdateCredentialsRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    TokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_updateCredentials, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TokenResponse.CREATOR.createFromParcel(_reply);
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

            public AccountRecoveryData getAccountRecoveryCountryInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountRecoveryData _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountRecoveryCountryInfo, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AccountRecoveryData.CREATOR.createFromParcel(_reply);
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

            public AccountRecoveryData getAccountRecoveryData(AccountRecoveryDataRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountRecoveryData _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountRecoveryData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AccountRecoveryData.CREATOR.createFromParcel(_reply);
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

            public AccountRecoveryUpdateResult updateAccountRecoveryData(AccountRecoveryUpdateRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountRecoveryUpdateResult _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_updateAccountRecoveryData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AccountRecoveryUpdateResult.CREATOR.createFromParcel(_reply);
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

            public AccountRecoveryGuidance getAccountRecoveryGuidance(AccountRecoveryGuidanceRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountRecoveryGuidance _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountRecoveryGuidance, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AccountRecoveryGuidance.CREATOR.createFromParcel(_reply);
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

            public Bundle getAccountExportData(String accountName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    Bundle _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountName);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountExportData, _data, _reply, 0);
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

            public boolean installAccountFromExportData(String accountName, Bundle exportData) throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountName);
                    if (exportData != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        exportData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_installAccountFromExportData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public WebSetupConfig getWebSetupConfig(WebSetupConfigRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    WebSetupConfig _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getWebSetupConfig, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = WebSetupConfig.CREATOR.createFromParcel(_reply);
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

            public ClearTokenResponse clearToken(ClearTokenRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    ClearTokenResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_clearToken, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ClearTokenResponse.CREATOR.createFromParcel(_reply);
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

            public AccountRemovalResponse removeAccount(AccountRemovalRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountRemovalResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_removeAccount, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AccountRemovalResponse.CREATOR.createFromParcel(_reply);
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

            public ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    ReauthSettingsResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getReauthSettings, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ReauthSettingsResponse.CREATOR.createFromParcel(_reply);
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

            public VerifyPinResponse verifyPin(VerifyPinRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    VerifyPinResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_verifyPin, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = VerifyPinResponse.CREATOR.createFromParcel(_reply);
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

            public AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    AccountChangeEventsResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getAccountChangeEvents, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (AccountChangeEventsResponse) AccountChangeEventsResponse.CREATOR.createFromParcel(_reply);
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

            public OtpResponse getOtp(OtpRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    OtpResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getOtp, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = OtpResponse.CREATOR.createFromParcel(_reply);
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

            public String getAccountId(String accountName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountName);
                    this.mRemote.transact(Stub.TRANSACTION_getAccountId, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public CheckFactoryResetPolicyComplianceResponse checkFrpCompliance(CheckFactoryResetPolicyComplianceRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    CheckFactoryResetPolicyComplianceResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_checkFrpCompliance, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CheckFactoryResetPolicyComplianceResponse.CREATOR.createFromParcel(_reply);
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

            public void clearFactoryResetChallenges() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_clearFactoryResetChallenges, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public GoogleAccountData getGoogleAccountData(Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    GoogleAccountData _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getGoogleAccountData, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GoogleAccountData.CREATOR.createFromParcel(_reply);
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

            public String getGoogleAccountId(Account account) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        account.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_getGoogleAccountId, _data, _reply, 0);
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean setWorkAccountAppWhitelistFingerprint(String packageName, String signatureFingerprint) throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    _data.writeString(signatureFingerprint);
                    this.mRemote.transact(Stub.TRANSACTION_setWorkAccountAppWhitelistFingerprint, _data, _reply, 0);
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

            public boolean clearWorkAccountAppWhitelist() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_clearWorkAccountAppWhitelist, _data, _reply, 0);
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

            public ValidateAccountCredentialsResponse validateAccountCredentials(AccountCredentials accountCreds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    ValidateAccountCredentialsResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accountCreds != null) {
                        _data.writeInt(Stub.TRANSACTION_getAccountData);
                        accountCreds.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_validateAccountCredentials, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ValidateAccountCredentialsResponse.CREATOR.createFromParcel(_reply);
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

            public GetAndAdvanceOtpCounterResponse getAndAdvanceOtpCounter(String accountName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    GetAndAdvanceOtpCounterResponse _result;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(accountName);
                    this.mRemote.transact(Stub.TRANSACTION_getAndAdvanceOtpCounter, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = GetAndAdvanceOtpCounterResponse.CREATOR.createFromParcel(_reply);
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

        public static IGoogleAccountDataService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGoogleAccountDataService)) {
                return new Proxy(obj);
            }
            return (IGoogleAccountDataService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            GoogleAccountData _result;
            GoogleAccountSetupRequest _arg0;
            TokenResponse _result2;
            AccountRecoveryData _result3;
            boolean _result4;
            String _result5;
            Account _arg02;
            switch (code) {
                case TRANSACTION_getAccountData /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result = getAccountData(data.readString());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_checkAccountName /*2*/:
                    AccountNameCheckRequest _arg03;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg03 = AccountNameCheckRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg03 = null;
                    }
                    AccountNameCheckResponse _result6 = checkAccountName(_arg03);
                    reply.writeNoException();
                    if (_result6 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result6.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_checkPassword /*3*/:
                    PasswordCheckRequest _arg04;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg04 = PasswordCheckRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg04 = null;
                    }
                    PasswordCheckResponse _result7 = checkPassword(_arg04);
                    reply.writeNoException();
                    if (_result7 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result7.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_checkRealName /*4*/:
                    CheckRealNameRequest _arg05;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg05 = CheckRealNameRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg05 = null;
                    }
                    CheckRealNameResponse _result8 = checkRealName(_arg05);
                    reply.writeNoException();
                    if (_result8 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result8.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_createAccount /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = GoogleAccountSetupRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _result2 = createAccount(_arg0);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getGplusInfo /*6*/:
                    GplusInfoRequest _arg06;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg06 = GplusInfoRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg06 = null;
                    }
                    GplusInfoResponse _result9 = getGplusInfo(_arg06);
                    reply.writeNoException();
                    if (_result9 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result9.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_createPlusProfile /*7*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = GoogleAccountSetupRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    _result2 = createPlusProfile(_arg0);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getToken /*8*/:
                    TokenRequest _arg07;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg07 = TokenRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg07 = null;
                    }
                    _result2 = getToken(_arg07);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_signIn /*9*/:
                    AccountSignInRequest _arg08;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg08 = AccountSignInRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg08 = null;
                    }
                    _result2 = signIn(_arg08);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_confirmCredentials /*10*/:
                    ConfirmCredentialsRequest _arg09;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg09 = ConfirmCredentialsRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg09 = null;
                    }
                    _result2 = confirmCredentials(_arg09);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_updateCredentials /*11*/:
                    UpdateCredentialsRequest _arg010;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg010 = UpdateCredentialsRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg010 = null;
                    }
                    _result2 = updateCredentials(_arg010);
                    reply.writeNoException();
                    if (_result2 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result2.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountRecoveryCountryInfo /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result3 = getAccountRecoveryCountryInfo();
                    reply.writeNoException();
                    if (_result3 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result3.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountRecoveryData /*13*/:
                    AccountRecoveryDataRequest _arg011;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg011 = AccountRecoveryDataRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg011 = null;
                    }
                    _result3 = getAccountRecoveryData(_arg011);
                    reply.writeNoException();
                    if (_result3 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result3.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_updateAccountRecoveryData /*14*/:
                    AccountRecoveryUpdateRequest _arg012;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg012 = AccountRecoveryUpdateRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg012 = null;
                    }
                    AccountRecoveryUpdateResult _result10 = updateAccountRecoveryData(_arg012);
                    reply.writeNoException();
                    if (_result10 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result10.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountRecoveryGuidance /*15*/:
                    AccountRecoveryGuidanceRequest _arg013;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg013 = AccountRecoveryGuidanceRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg013 = null;
                    }
                    AccountRecoveryGuidance _result11 = getAccountRecoveryGuidance(_arg013);
                    reply.writeNoException();
                    if (_result11 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result11.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountExportData /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    Bundle _result12 = getAccountExportData(data.readString());
                    reply.writeNoException();
                    if (_result12 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result12.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_installAccountFromExportData /*17*/:
                    Bundle _arg1;
                    data.enforceInterface(DESCRIPTOR);
                    String _arg014 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    _result4 = installAccountFromExportData(_arg014, _arg1);
                    reply.writeNoException();
                    if (_result4) {
                        i = TRANSACTION_getAccountData;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_getWebSetupConfig /*18*/:
                    WebSetupConfigRequest _arg015;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg015 = WebSetupConfigRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg015 = null;
                    }
                    WebSetupConfig _result13 = getWebSetupConfig(_arg015);
                    reply.writeNoException();
                    if (_result13 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result13.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_clearToken /*19*/:
                    ClearTokenRequest _arg016;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg016 = ClearTokenRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg016 = null;
                    }
                    ClearTokenResponse _result14 = clearToken(_arg016);
                    reply.writeNoException();
                    if (_result14 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result14.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_removeAccount /*20*/:
                    AccountRemovalRequest _arg017;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg017 = AccountRemovalRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg017 = null;
                    }
                    AccountRemovalResponse _result15 = removeAccount(_arg017);
                    reply.writeNoException();
                    if (_result15 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result15.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getReauthSettings /*21*/:
                    ReauthSettingsRequest _arg018;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg018 = ReauthSettingsRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg018 = null;
                    }
                    ReauthSettingsResponse _result16 = getReauthSettings(_arg018);
                    reply.writeNoException();
                    if (_result16 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result16.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_verifyPin /*22*/:
                    VerifyPinRequest _arg019;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg019 = VerifyPinRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg019 = null;
                    }
                    VerifyPinResponse _result17 = verifyPin(_arg019);
                    reply.writeNoException();
                    if (_result17 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result17.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountChangeEvents /*23*/:
                    AccountChangeEventsRequest _arg020;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg020 = (AccountChangeEventsRequest) AccountChangeEventsRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg020 = null;
                    }
                    AccountChangeEventsResponse _result18 = getAccountChangeEvents(_arg020);
                    reply.writeNoException();
                    if (_result18 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result18.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getOtp /*24*/:
                    OtpRequest _arg021;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg021 = OtpRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg021 = null;
                    }
                    OtpResponse _result19 = getOtp(_arg021);
                    reply.writeNoException();
                    if (_result19 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result19.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAccountId /*25*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result5 = getAccountId(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case TRANSACTION_checkFrpCompliance /*27*/:
                    CheckFactoryResetPolicyComplianceRequest _arg022;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg022 = CheckFactoryResetPolicyComplianceRequest.CREATOR.createFromParcel(data);
                    } else {
                        _arg022 = null;
                    }
                    CheckFactoryResetPolicyComplianceResponse _result20 = checkFrpCompliance(_arg022);
                    reply.writeNoException();
                    if (_result20 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result20.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_clearFactoryResetChallenges /*29*/:
                    data.enforceInterface(DESCRIPTOR);
                    clearFactoryResetChallenges();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getGoogleAccountData /*30*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    _result = getGoogleAccountData(_arg02);
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getGoogleAccountId /*31*/:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg02 = (Account) Account.CREATOR.createFromParcel(data);
                    } else {
                        _arg02 = null;
                    }
                    _result5 = getGoogleAccountId(_arg02);
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case TRANSACTION_setWorkAccountAppWhitelistFingerprint /*34*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result4 = setWorkAccountAppWhitelistFingerprint(data.readString(), data.readString());
                    reply.writeNoException();
                    if (_result4) {
                        i = TRANSACTION_getAccountData;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_clearWorkAccountAppWhitelist /*35*/:
                    data.enforceInterface(DESCRIPTOR);
                    _result4 = clearWorkAccountAppWhitelist();
                    reply.writeNoException();
                    if (_result4) {
                        i = TRANSACTION_getAccountData;
                    }
                    reply.writeInt(i);
                    return true;
                case TRANSACTION_validateAccountCredentials /*36*/:
                    AccountCredentials _arg023;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg023 = AccountCredentials.CREATOR.createFromParcel(data);
                    } else {
                        _arg023 = null;
                    }
                    ValidateAccountCredentialsResponse _result21 = validateAccountCredentials(_arg023);
                    reply.writeNoException();
                    if (_result21 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result21.writeToParcel(reply, TRANSACTION_getAccountData);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case TRANSACTION_getAndAdvanceOtpCounter /*37*/:
                    data.enforceInterface(DESCRIPTOR);
                    GetAndAdvanceOtpCounterResponse _result22 = getAndAdvanceOtpCounter(data.readString());
                    reply.writeNoException();
                    if (_result22 != null) {
                        reply.writeInt(TRANSACTION_getAccountData);
                        _result22.writeToParcel(reply, TRANSACTION_getAccountData);
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

    AccountNameCheckResponse checkAccountName(AccountNameCheckRequest accountNameCheckRequest) throws RemoteException;

    CheckFactoryResetPolicyComplianceResponse checkFrpCompliance(CheckFactoryResetPolicyComplianceRequest checkFactoryResetPolicyComplianceRequest) throws RemoteException;

    PasswordCheckResponse checkPassword(PasswordCheckRequest passwordCheckRequest) throws RemoteException;

    CheckRealNameResponse checkRealName(CheckRealNameRequest checkRealNameRequest) throws RemoteException;

    void clearFactoryResetChallenges() throws RemoteException;

    ClearTokenResponse clearToken(ClearTokenRequest clearTokenRequest) throws RemoteException;

    boolean clearWorkAccountAppWhitelist() throws RemoteException;

    TokenResponse confirmCredentials(ConfirmCredentialsRequest confirmCredentialsRequest) throws RemoteException;

    TokenResponse createAccount(GoogleAccountSetupRequest googleAccountSetupRequest) throws RemoteException;

    TokenResponse createPlusProfile(GoogleAccountSetupRequest googleAccountSetupRequest) throws RemoteException;

    AccountChangeEventsResponse getAccountChangeEvents(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException;

    GoogleAccountData getAccountData(String str) throws RemoteException;

    Bundle getAccountExportData(String str) throws RemoteException;

    String getAccountId(String str) throws RemoteException;

    AccountRecoveryData getAccountRecoveryCountryInfo() throws RemoteException;

    AccountRecoveryData getAccountRecoveryData(AccountRecoveryDataRequest accountRecoveryDataRequest) throws RemoteException;

    AccountRecoveryGuidance getAccountRecoveryGuidance(AccountRecoveryGuidanceRequest accountRecoveryGuidanceRequest) throws RemoteException;

    GetAndAdvanceOtpCounterResponse getAndAdvanceOtpCounter(String str) throws RemoteException;

    GoogleAccountData getGoogleAccountData(Account account) throws RemoteException;

    String getGoogleAccountId(Account account) throws RemoteException;

    GplusInfoResponse getGplusInfo(GplusInfoRequest gplusInfoRequest) throws RemoteException;

    OtpResponse getOtp(OtpRequest otpRequest) throws RemoteException;

    ReauthSettingsResponse getReauthSettings(ReauthSettingsRequest reauthSettingsRequest) throws RemoteException;

    TokenResponse getToken(TokenRequest tokenRequest) throws RemoteException;

    WebSetupConfig getWebSetupConfig(WebSetupConfigRequest webSetupConfigRequest) throws RemoteException;

    boolean installAccountFromExportData(String str, Bundle bundle) throws RemoteException;

    AccountRemovalResponse removeAccount(AccountRemovalRequest accountRemovalRequest) throws RemoteException;

    boolean setWorkAccountAppWhitelistFingerprint(String str, String str2) throws RemoteException;

    TokenResponse signIn(AccountSignInRequest accountSignInRequest) throws RemoteException;

    AccountRecoveryUpdateResult updateAccountRecoveryData(AccountRecoveryUpdateRequest accountRecoveryUpdateRequest) throws RemoteException;

    TokenResponse updateCredentials(UpdateCredentialsRequest updateCredentialsRequest) throws RemoteException;

    ValidateAccountCredentialsResponse validateAccountCredentials(AccountCredentials accountCredentials) throws RemoteException;

    VerifyPinResponse verifyPin(VerifyPinRequest verifyPinRequest) throws RemoteException;
}
