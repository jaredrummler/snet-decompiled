package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.stats.G.alarms;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceStateUtils;
import java.util.List;

public class AlarmTracker {
    private static final boolean DEBUG = false;
    private static String TAG;
    private static AlarmTracker sInstance;
    private static Integer sLogLevel;

    static {
        TAG = "AlarmTracker";
    }

    public static AlarmTracker getInstance() {
        if (sInstance == null) {
            sInstance = new AlarmTracker();
        }
        return sInstance;
    }

    public void registerEvent(Context context, int type, String name, long triggerAfterMillis, long windowMillis, long intervalMillis, List<String> callingPackages, int flags) {
        if (shouldRegister(context)) {
            try {
                context.startService(new Intent().setComponent(LoggingConstants.STATS_SERVICE_COMPONENT_NAME).putExtra(LoggingConstants.EXTRA_LOG_EVENT, new AlarmEvent(System.currentTimeMillis(), type, name, triggerAfterMillis, windowMillis, intervalMillis, callingPackages, flags, DeviceStateUtils.getDeviceState(context), context.getPackageName(), DeviceStateUtils.getPowerPercentage(context))));
            } catch (Exception e) {
                Log.wtf(TAG, e);
            }
        }
    }

    static boolean shouldRegister(Context context) {
        if (sLogLevel == null) {
            sLogLevel = Integer.valueOf(getLogLevel());
        }
        return sLogLevel.intValue() != LoggingConstants.LOG_LEVEL_OFF;
    }

    private static int getLogLevel() {
        try {
            return ClientLibraryUtils.isPackageSide() ? ((Integer) alarms.level.get()).intValue() : LoggingConstants.LOG_LEVEL_OFF;
        } catch (SecurityException e) {
            return LoggingConstants.LOG_LEVEL_OFF;
        }
    }
}
