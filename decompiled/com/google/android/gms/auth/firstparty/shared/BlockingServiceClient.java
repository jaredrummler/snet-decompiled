package com.google.android.gms.auth.firstparty.shared;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public abstract class BlockingServiceClient {
    private static final String MAIN_THREAD_ERROR_MESSAGE = "Calling this from your main thread can lead to deadlock";
    private final String TAG;
    private final Context mContext;

    protected interface Call<R> {
        R exec(IBinder iBinder) throws RemoteException;
    }

    protected abstract Intent getServiceIntent();

    protected BlockingServiceClient(Context context) {
        this.TAG = "BlockingServiceClient";
        this.mContext = context.getApplicationContext();
    }

    protected <R> R exec(Call<R> call) {
        Preconditions.checkNotMainThread(MAIN_THREAD_ERROR_MESSAGE);
        Intent intent = getServiceIntent();
        PackageManager pm = this.mContext.getPackageManager();
        ResolveInfo inf = pm.resolveService(intent, 0);
        if (inf == null || inf.serviceInfo == null) {
            throw new IllegalStateException("Can't resolve a service for intent: " + intent.toString());
        }
        ServiceInfo sInf = inf.serviceInfo;
        if (GooglePlayServicesUtilLight.isPackageGoogleSigned(pm, sInf.packageName)) {
            intent.setPackage(sInf.packageName);
            BlockingServiceConnection sc = new BlockingServiceConnection();
            if (!ConnectionTracker.getInstance().bindService(this.mContext, intent, sc, 1)) {
                return null;
            }
            try {
                R exec = call.exec(sc.getService());
                ConnectionTracker.getInstance().unbindService(this.mContext, sc);
                return exec;
            } catch (InterruptedException e) {
                Log.w("BlockingServiceClient", e);
                throw new RuntimeException(e);
            } catch (RemoteException e2) {
                Log.w("BlockingServiceClient", e2);
                throw new RuntimeException(e2);
            } catch (Throwable th) {
                ConnectionTracker.getInstance().unbindService(this.mContext, sc);
            }
        } else {
            SecurityException secEx = new SecurityException("Resolving service is not provided by Google!");
            Log.w("BlockingServiceClient", secEx);
            throw secEx;
        }
    }
}
