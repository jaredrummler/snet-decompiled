package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.stats.G.wakeLocks;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceStateUtils;
import java.util.Arrays;
import java.util.List;

public class WakeLockTracker {
    private static final boolean DEBUG = false;
    private static String TAG;
    private static WakeLockTracker sInstance;
    private static Integer sLogLevel;

    static {
        TAG = "WakeLockTracker";
        sInstance = new WakeLockTracker();
    }

    public static WakeLockTracker getInstance() {
        return sInstance;
    }

    public void registerSyncStart(Context context, AbstractThreadedSyncAdapter adapter, String adapterId, String adapterOptionalTag) {
        registerEvent(context, StatsUtils.getEventKey(adapter, adapterId), 10, adapterId, adapterOptionalTag, 0, null);
    }

    public void registerSyncEnd(Context context, AbstractThreadedSyncAdapter adapter, String adapterId, String adapterOptionalTag, boolean syncedData) {
        registerEvent(context, StatsUtils.getEventKey(adapter, adapterId), 11, adapterId, adapterOptionalTag, 0, null);
    }

    public void registerAcquireEvent(Context context, Intent intent, String wakeLockName, String secondaryWakeLockName, int levelAndFlags, String callingPackage) {
        registerAcquireEvent(context, intent, wakeLockName, secondaryWakeLockName, levelAndFlags, Arrays.asList(new String[]{callingPackage}));
    }

    public void registerAcquireEvent(Context context, Intent intent, String wakeLockName, String secondaryWakeLockName, int levelAndFlags, List<String> callingPackage) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 7, wakeLockName, secondaryWakeLockName, levelAndFlags, callingPackage);
    }

    public void registerReleaseEvent(Context context, Intent intent) {
        registerEvent(context, intent.getStringExtra(LoggingConstants.EXTRA_WAKE_LOCK_KEY), 8, null, null, 0, null);
    }

    public void registerEvent(Context context, String eventKey, int eventType, String wakeLockName, String secondaryWakeLockName, int levelAndFlags, List<String> callingPackages) {
        registerEvent(context, eventKey, eventType, wakeLockName, secondaryWakeLockName, levelAndFlags, callingPackages, 0);
    }

    public void registerEvent(Context context, String eventKey, int eventType, String wakeLockName, String secondaryWakeLockName, int levelAndFlags, List<String> callingPackage, long timeout) {
        if (!shouldRegister(context)) {
            return;
        }
        if (TextUtils.isEmpty(eventKey)) {
            Log.e(TAG, "missing wakeLock key. " + eventKey);
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (7 == eventType || 8 == eventType || 10 == eventType || 11 == eventType) {
            try {
                context.startService(new Intent().setComponent(LoggingConstants.STATS_SERVICE_COMPONENT_NAME).putExtra(LoggingConstants.EXTRA_LOG_EVENT, new WakeLockEvent(currentTime, eventType, wakeLockName, levelAndFlags, callingPackage, eventKey, SystemClock.elapsedRealtime(), DeviceStateUtils.getDeviceState(context), secondaryWakeLockName, context.getPackageName(), DeviceStateUtils.getPowerPercentage(context), timeout)));
            } catch (Throwable e) {
                Log.wtf(TAG, e);
            }
        }
    }

    private static boolean shouldRegister(Context context) {
        if (sLogLevel == null) {
            sLogLevel = Integer.valueOf(getLogLevel());
        }
        return sLogLevel.intValue() != LoggingConstants.LOG_LEVEL_OFF;
    }

    private static int getLogLevel() {
        try {
            return ClientLibraryUtils.isPackageSide() ? ((Integer) wakeLocks.level.get()).intValue() : LoggingConstants.LOG_LEVEL_OFF;
        } catch (SecurityException e) {
            return LoggingConstants.LOG_LEVEL_OFF;
        }
    }
}
