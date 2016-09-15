package com.google.android.gms.auth.frp;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.frp.IFrpService.Stub;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.stats.ConnectionTracker;

public class FrpClient {
    static final String ACTION = "com.google.android.gms.auth.frp.FRP_BIND";
    private static final String TAG = "FrpClient";
    private final Context mContext;

    private interface Call<R> {
        R exec(IFrpService iFrpService) throws RemoteException;
    }

    /* renamed from: com.google.android.gms.auth.frp.FrpClient.3 */
    class AnonymousClass3 implements Call<UnlockFactoryResetProtectionResponse> {
        final /* synthetic */ UnlockFactoryResetProtectionRequest val$unlockRequest;

        AnonymousClass3(UnlockFactoryResetProtectionRequest unlockFactoryResetProtectionRequest) {
            this.val$unlockRequest = unlockFactoryResetProtectionRequest;
        }

        public UnlockFactoryResetProtectionResponse exec(IFrpService stub) throws RemoteException {
            return stub.unlockFactoryResetProtection(this.val$unlockRequest);
        }
    }

    private static Intent newFrpServiceIntent() {
        return new Intent().setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE).setAction(ACTION).addCategory("android.intent.category.DEFAULT");
    }

    public FrpClient(Context context) {
        this.mContext = context;
    }

    public boolean isChallengeSupported() {
        Exception e;
        try {
            return ((Boolean) exec(new Call<Boolean>() {
                public Boolean exec(IFrpService stub) throws RemoteException {
                    return Boolean.valueOf(stub.isChallengeSupported());
                }
            })).booleanValue();
        } catch (RemoteException e2) {
            e = e2;
            Log.w(TAG, e);
            return false;
        } catch (InterruptedException e3) {
            e = e3;
            Log.w(TAG, e);
            return false;
        }
    }

    public boolean isChallengeRequired() {
        Exception e;
        try {
            return ((Boolean) exec(new Call<Boolean>() {
                public Boolean exec(IFrpService stub) throws RemoteException {
                    return Boolean.valueOf(stub.isChallengeRequired());
                }
            })).booleanValue();
        } catch (RemoteException e2) {
            e = e2;
            Log.w(TAG, e);
            return false;
        } catch (InterruptedException e3) {
            e = e3;
            Log.w(TAG, e);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.auth.frp.UnlockFactoryResetProtectionResponse unlockFactoryResetProtection(com.google.android.gms.auth.frp.UnlockFactoryResetProtectionRequest r4) {
        /*
        r3 = this;
        r1 = new com.google.android.gms.auth.frp.FrpClient$3;	 Catch:{ RemoteException -> 0x0019, InterruptedException -> 0x000c }
        r1.<init>(r4);	 Catch:{ RemoteException -> 0x0019, InterruptedException -> 0x000c }
        r1 = r3.exec(r1);	 Catch:{ RemoteException -> 0x0019, InterruptedException -> 0x000c }
        r1 = (com.google.android.gms.auth.frp.UnlockFactoryResetProtectionResponse) r1;	 Catch:{ RemoteException -> 0x0019, InterruptedException -> 0x000c }
    L_0x000b:
        return r1;
    L_0x000c:
        r0 = move-exception;
    L_0x000d:
        r1 = "FrpClient";
        android.util.Log.w(r1, r0);
        r1 = new com.google.android.gms.auth.frp.UnlockFactoryResetProtectionResponse;
        r2 = 1;
        r1.<init>(r2);
        goto L_0x000b;
    L_0x0019:
        r0 = move-exception;
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.frp.FrpClient.unlockFactoryResetProtection(com.google.android.gms.auth.frp.UnlockFactoryResetProtectionRequest):com.google.android.gms.auth.frp.UnlockFactoryResetProtectionResponse");
    }

    private <R> R exec(Call<R> call) throws RemoteException, InterruptedException {
        BlockingServiceConnection sc;
        Intent intent = newFrpServiceIntent();
        long callingId = Binder.clearCallingIdentity();
        try {
            sc = new BlockingServiceConnection();
            if (ConnectionTracker.getInstance().bindService(this.mContext, intent, sc, 1)) {
                R exec = call.exec(Stub.asInterface(sc.getService()));
                ConnectionTracker.getInstance().unbindService(this.mContext, sc);
                Binder.restoreCallingIdentity(callingId);
                return exec;
            }
            Binder.restoreCallingIdentity(callingId);
            return null;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(callingId);
        }
    }
}
