package com.google.android.gms.auth.appcert;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.appcert.IAppCertService.Stub;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.io.IOException;

public class AppCertServiceClient {
    private static final ComponentName APP_CERT_MGMT_SERVICE_COMPONENT_NAME;
    private static final String MAIN_THREAD_ERROR_MESSAGE = "Calling this from your main thread can lead to deadlock";
    private static final String TAG = "AppCertServiceClient";
    private final Context mContext;

    private interface Call<R> {
        R exec(IAppCertService iAppCertService) throws RemoteException;
    }

    /* renamed from: com.google.android.gms.auth.appcert.AppCertServiceClient.2 */
    class AnonymousClass2 implements Call<String> {
        final /* synthetic */ String val$packageNameToCertify;

        AnonymousClass2(String str) {
            this.val$packageNameToCertify = str;
        }

        public String exec(IAppCertService stub) throws RemoteException {
            return stub.getAppCert(this.val$packageNameToCertify);
        }
    }

    static {
        APP_CERT_MGMT_SERVICE_COMPONENT_NAME = new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.auth.be.appcert.AppCertService");
    }

    private static Intent newAppCertServiceIntent() {
        return new Intent().setComponent(APP_CERT_MGMT_SERVICE_COMPONENT_NAME);
    }

    public AppCertServiceClient(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public boolean initializeDeviceKey() throws IOException, GoogleAuthException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        ensurePlayServicesAvailable(this.mContext);
        return ((Boolean) exec(new Call<Boolean>() {
            public Boolean exec(IAppCertService stub) throws RemoteException {
                return Boolean.valueOf(stub.initializeDeviceKey());
            }
        })).booleanValue();
    }

    public String getAppCert(String packageNameToCertify) throws IOException, GoogleAuthException {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        Preconditions.checkNotNull(packageNameToCertify, "Package name cannot be null!");
        ensurePlayServicesAvailable(this.mContext);
        return (String) exec(new AnonymousClass2(packageNameToCertify));
    }

    private <R> R exec(Call<R> call) throws IOException, GoogleAuthException {
        Intent intent = newAppCertServiceIntent();
        long callingId = Binder.clearCallingIdentity();
        try {
            BlockingServiceConnection sc = new BlockingServiceConnection();
            if (ConnectionTracker.getInstance().bindService(this.mContext, intent, sc, 1)) {
                try {
                    R exec = call.exec(Stub.asInterface(sc.getService()));
                    ConnectionTracker.getInstance().unbindService(this.mContext, sc);
                    return exec;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Log.w(TAG, "Interrupted when getting service: " + e);
                    throw new GoogleAuthException(GoogleAuthUtil.STATUS_INTERRUPTED);
                } catch (RemoteException e2) {
                    Log.w(TAG, "RemoteException when executing call!", e2);
                    throw new IOException("remote exception");
                } catch (Throwable th) {
                    ConnectionTracker.getInstance().unbindService(this.mContext, sc);
                }
            } else {
                throw new IOException("Could not bind to service with the given context.");
            }
        } finally {
            Binder.restoreCallingIdentity(callingId);
        }
    }

    private static void ensurePlayServicesAvailable(Context context) throws GoogleAuthException {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context);
        } catch (Throwable e) {
            throw new GoogleAuthException(e);
        } catch (Throwable e2) {
            throw new GoogleAuthException(e2);
        }
    }
}
