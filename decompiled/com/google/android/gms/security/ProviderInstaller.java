package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.Method;

public class ProviderInstaller {
    private static final String CLASS_NAME = "com.google.android.gms.common.security.ProviderInstallerImpl";
    private static final String METHOD_NAME = "insertProvider";
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final String TAG = "ProviderInstaller";
    private static final String TRACKING_SOURCE = "pi";
    private static final GoogleApiAvailabilityLight sApiAvailability;
    private static Method sInstallProviderMethod;
    private static final Object sLock;

    /* renamed from: com.google.android.gms.security.ProviderInstaller.1 */
    static class AnonymousClass1 extends AsyncTask<Void, Void, Integer> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ ProviderInstallListener val$listener;

        AnonymousClass1(Context context, ProviderInstallListener providerInstallListener) {
            this.val$context = context;
            this.val$listener = providerInstallListener;
        }

        protected Integer doInBackground(Void... params) {
            try {
                ProviderInstaller.installIfNeeded(this.val$context);
                return Integer.valueOf(0);
            } catch (GooglePlayServicesRepairableException e) {
                return Integer.valueOf(e.getConnectionStatusCode());
            } catch (GooglePlayServicesNotAvailableException e2) {
                return Integer.valueOf(e2.errorCode);
            }
        }

        protected void onPostExecute(Integer result) {
            if (result.intValue() == 0) {
                this.val$listener.onProviderInstalled();
                return;
            }
            this.val$listener.onProviderInstallFailed(result.intValue(), ProviderInstaller.sApiAvailability.getErrorResolutionIntent(this.val$context, result.intValue(), ProviderInstaller.TRACKING_SOURCE));
        }
    }

    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    static {
        sApiAvailability = GoogleApiAvailabilityLight.getInstance();
        sLock = new Object();
        sInstallProviderMethod = null;
    }

    public static void installIfNeeded(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Preconditions.checkNotNull(context, "Context must not be null");
        sApiAvailability.verifyGooglePlayServicesIsAvailable(context);
        Context gmsCoreContext = GooglePlayServicesUtilLight.getRemoteContext(context);
        if (gmsCoreContext == null) {
            Log.e(TAG, "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        synchronized (sLock) {
            try {
                if (sInstallProviderMethod == null) {
                    lookupMethod_locked(gmsCoreContext);
                }
                sInstallProviderMethod.invoke(null, new Object[]{gmsCoreContext});
            } catch (Exception e) {
                Log.e(TAG, "Failed to install provider: " + e.getMessage());
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener listener) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(listener, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new AnonymousClass1(context, listener).execute(new Void[0]);
    }

    private static void lookupMethod_locked(Context gmsCoreContext) throws ClassNotFoundException, NoSuchMethodException {
        sInstallProviderMethod = gmsCoreContext.getClassLoader().loadClass(CLASS_NAME).getMethod(METHOD_NAME, new Class[]{Context.class});
    }
}
