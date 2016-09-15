package com.google.android.gms.auth.firstparty.proximity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.auth.firstparty.proximity.IAuthorizationService.Stub;
import com.google.android.gms.auth.firstparty.proximity.data.Authorization;
import com.google.android.gms.auth.firstparty.proximity.data.AuthorizationRequest;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.stats.ConnectionTracker;

public class AuthorizationClient {
    public static final String BUNDLE_KEY_AUTHORIZATION = "authorization";
    public static final String BUNDLE_KEY_EXCEPTION = "exception";
    private static final String SERVICE_ACTION = "com.google.android.gms.auth.proximity.AUTHORIZATION";
    private final Context mContext;

    private interface RemoteCall {
        Bundle exec(IAuthorizationService iAuthorizationService) throws RemoteException;
    }

    /* renamed from: com.google.android.gms.auth.firstparty.proximity.AuthorizationClient.1 */
    class AnonymousClass1 implements RemoteCall {
        final /* synthetic */ AuthorizationRequest val$request;

        AnonymousClass1(AuthorizationRequest authorizationRequest) {
            this.val$request = authorizationRequest;
        }

        public Bundle exec(IAuthorizationService stub) throws RemoteException {
            return stub.authorize(this.val$request);
        }
    }

    private static Intent createAuthorizationServiceIntent() {
        return new Intent().setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE).setAction(SERVICE_ACTION).addCategory("android.intent.category.DEFAULT");
    }

    public AuthorizationClient(Context context) {
        this.mContext = context;
    }

    public Authorization authorize(AuthorizationRequest request) throws ProximityAuthException {
        return (Authorization) exec(new AnonymousClass1(request)).getParcelable(BUNDLE_KEY_AUTHORIZATION);
    }

    private void checkGoogleSignedIntentCall(Intent intent) {
        PackageManager pm = this.mContext.getPackageManager();
        for (ResolveInfo info : this.mContext.getPackageManager().queryIntentServices(intent, 0)) {
            String pkg = info.serviceInfo.packageName;
            if (!GooglePlayServicesUtilLight.isPackageGoogleSigned(pm, pkg)) {
                throw new SecurityException("AuthorizationClient appears to have been spoofed by: " + pkg);
            }
        }
    }

    private Bundle exec(RemoteCall call) throws ProximityAuthException {
        Intent intent = createAuthorizationServiceIntent();
        checkGoogleSignedIntentCall(intent);
        BlockingServiceConnection sc = new BlockingServiceConnection();
        if (ConnectionTracker.getInstance().bindService(this.mContext, intent, sc, 1)) {
            try {
                Bundle result = call.exec(Stub.asInterface(sc.getService()));
                result.setClassLoader(getClass().getClassLoader());
                if (result.containsKey(BUNDLE_KEY_EXCEPTION)) {
                    throw ((ProximityAuthException) result.getSerializable(BUNDLE_KEY_EXCEPTION));
                }
                ConnectionTracker.getInstance().unbindService(this.mContext, sc);
                return result;
            } catch (InterruptedException e) {
                throw new ProximityAuthException(e.getMessage());
            } catch (RemoteException e2) {
                throw new ProximityAuthException(e2.getMessage());
            } catch (Throwable th) {
                ConnectionTracker.getInstance().unbindService(this.mContext, sc);
            }
        } else {
            throw new ProximityAuthException("Cannot bind to " + IAuthorizationService.class.getSimpleName() + "!");
        }
    }
}
