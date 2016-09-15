package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtil;

abstract class GooglePlayServicesUpdatedReceiver extends BroadcastReceiver {
    protected Context mContext;

    protected abstract void onUpdated();

    GooglePlayServicesUpdatedReceiver() {
    }

    @Nullable
    public static <T extends GooglePlayServicesUpdatedReceiver> T register(Context context, T receiver) {
        return register(context, receiver, GoogleApiAvailabilityLight.getInstance());
    }

    @Nullable
    public static <T extends GooglePlayServicesUpdatedReceiver> T register(Context context, T receiver, GoogleApiAvailabilityLight apiAvailability) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(receiver, intentFilter);
        receiver.mContext = context;
        if (apiAvailability.isUninstalledAppPossiblyUpdating(context, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE)) {
            return receiver;
        }
        receiver.onUpdated();
        receiver.unregister();
        return null;
    }

    public synchronized void unregister() {
        if (this.mContext != null) {
            this.mContext.unregisterReceiver(this);
        }
        this.mContext = null;
    }

    public void onReceive(Context context, Intent intent) {
        Uri packageUri = intent.getData();
        String packageName = null;
        if (packageUri != null) {
            packageName = packageUri.getSchemeSpecificPart();
        }
        if (GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE.equals(packageName)) {
            onUpdated();
            unregister();
        }
    }
}
