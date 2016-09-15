package com.google.android.gms.common.api;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.internal.LifecycleTrackingFragment;
import com.google.android.gms.common.api.internal.SupportLifecycleTrackingFragment;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

public abstract class ResultStore {
    @GuardedBy("sLock")
    private static final Map<Activity, ResultStore> sActivityStoreMap;
    private static final Object sLock;

    public abstract boolean hasPendingResult(int i);

    public abstract void remove(int i);

    public abstract void setResultCallbacks(int i, @NonNull ResultCallbacks<?> resultCallbacks);

    public <R extends Result> void store(int resultId, @NonNull PendingResult<R> pendingResult) {
        throw new UnsupportedOperationException();
    }

    static {
        sActivityStoreMap = new WeakHashMap();
        sLock = new Object();
    }

    @NonNull
    public static ResultStore getInstance(@NonNull Activity activity, @NonNull GoogleApiClient apiClient) {
        boolean z;
        ResultStore store;
        boolean z2 = false;
        Preconditions.checkNotNull(activity, "Activity must not be null.");
        if (apiClient != null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkNotNull(Boolean.valueOf(z), "GoogleApiClient must not be null.");
        try {
            boolean isSupport = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
            isSupport = false;
        }
        if (isSupport || PlatformVersion.isAtLeastHoneycomb()) {
            z2 = true;
        }
        Preconditions.checkState(z2, "Expected at least Honeycomb (3.0) platform version.");
        synchronized (sLock) {
            store = (ResultStore) sActivityStoreMap.get(activity);
            if (store == null) {
                if (isSupport) {
                    store = getInstance((FragmentActivity) activity);
                } else {
                    store = getInstance(activity);
                }
                sActivityStoreMap.put(activity, store);
            }
            apiClient.setResultStore(store);
        }
        return store;
    }

    @TargetApi(11)
    private static ResultStore getInstance(Activity activity) {
        String tag = SupportLifecycleTrackingFragment.TAG;
        FragmentManager fragmentManager = activity.getFragmentManager();
        try {
            LifecycleTrackingFragment fragment = (LifecycleTrackingFragment) fragmentManager.findFragmentByTag(tag);
            if (fragment == null) {
                fragment = new LifecycleTrackingFragment();
                fragmentManager.beginTransaction().add(fragment, tag).commit();
            }
            return fragment.getPendingResultStore();
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment tag " + tag + " is reserved for " + "ResultStore.");
        }
    }

    private static ResultStore getInstance(FragmentActivity activity) {
        String tag = SupportLifecycleTrackingFragment.TAG;
        android.support.v4.app.FragmentManager fragmentManager = activity.getSupportFragmentManager();
        try {
            SupportLifecycleTrackingFragment fragment = (SupportLifecycleTrackingFragment) fragmentManager.findFragmentByTag(tag);
            if (fragment == null) {
                fragment = new SupportLifecycleTrackingFragment();
                fragmentManager.beginTransaction().add((Fragment) fragment, tag).commit();
            }
            return fragment.getPendingResultStore();
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment tag " + tag + " is reserved for " + "ResultStore.");
        }
    }

    protected static void removeActivityReference(Activity activity) {
        synchronized (sLock) {
            sActivityStoreMap.remove(activity);
        }
    }
}
