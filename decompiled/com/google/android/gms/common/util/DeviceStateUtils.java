package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class DeviceStateUtils {
    private static IntentFilter sFilter;
    private static float sPowerPercentage;
    private static long sTimeStampPowerPercentage;

    private DeviceStateUtils() {
    }

    static {
        sFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        sPowerPercentage = Float.NaN;
    }

    @TargetApi(20)
    public static int getDeviceState(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        boolean isCharging;
        Intent batteryStatus = context.getApplicationContext().registerReceiver(null, sFilter);
        if (((batteryStatus == null ? 0 : batteryStatus.getIntExtra("plugged", 0)) & 7) != 0) {
            isCharging = true;
        } else {
            isCharging = false;
        }
        PowerManager pm = (PowerManager) context.getSystemService("power");
        if (pm == null) {
            return -1;
        }
        boolean isInteractive;
        int i2;
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            isInteractive = pm.isInteractive();
        } else {
            isInteractive = pm.isScreenOn();
        }
        if (isInteractive) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        i2 <<= 1;
        if (!isCharging) {
            i = 0;
        }
        return i | i2;
    }

    public static synchronized float getPowerPercentage(Context context) {
        float f;
        synchronized (DeviceStateUtils.class) {
            if (SystemClock.elapsedRealtime() - sTimeStampPowerPercentage >= 60000 || sPowerPercentage == Float.NaN) {
                Intent batteryStatus = context.getApplicationContext().registerReceiver(null, sFilter);
                if (batteryStatus != null) {
                    sPowerPercentage = ((float) batteryStatus.getIntExtra("level", -1)) / ((float) batteryStatus.getIntExtra("scale", -1));
                }
                sTimeStampPowerPercentage = SystemClock.elapsedRealtime();
                f = sPowerPercentage;
            } else {
                f = sPowerPercentage;
            }
        }
        return f;
    }
}
