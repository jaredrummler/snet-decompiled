package com.google.android.gms.common.stats;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.common.stats.G.alarms;
import com.google.android.gms.common.stats.G.connections;
import com.google.android.gms.common.stats.G.networkUsage;
import com.google.android.gms.common.stats.G.wakeLocks;
import com.google.android.gms.lint.BuildConfig;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;

public class StatsUtils {
    public static void turnOffLogging() {
        alarms.level.override(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
        connections.level.override(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
        networkUsage.level.override(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
        wakeLocks.level.override(Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
    }

    public static void resetLogging() {
        alarms.level.resetOverride();
        connections.level.resetOverride();
        networkUsage.level.resetOverride();
        wakeLocks.level.resetOverride();
    }

    public static String getEventKey(Context context, Intent intent) {
        return String.valueOf((((long) System.identityHashCode(context)) << 32) | ((long) System.identityHashCode(intent)));
    }

    public static String getEventKey(WakeLock wakeLock, String secondaryName) {
        StringBuilder append = new StringBuilder().append(String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(wakeLock))));
        if (TextUtils.isEmpty(secondaryName)) {
            secondaryName = BuildConfig.VERSION_NAME;
        }
        return append.append(secondaryName).toString();
    }

    public static String getEventKey(AbstractThreadedSyncAdapter adapter, String name) {
        StringBuilder append = new StringBuilder().append(String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(adapter))));
        if (TextUtils.isEmpty(name)) {
            name = BuildConfig.VERSION_NAME;
        }
        return append.append(name).toString();
    }

    public static boolean isTimeOutEvent(StatsEvent event) {
        switch (event.getEventType()) {
            case Type.REMOVE_SHARE /*6*/:
            case Type.CREATE_LINK /*9*/:
            case Type.SWITCH_ACCOUNT /*12*/:
                return true;
            default:
                return false;
        }
    }
}
