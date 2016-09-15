package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

public final class DeviceProperties {
    public static boolean isTablet(Resources resources) {
        if (resources == null) {
            return false;
        }
        boolean isXlarge;
        if ((resources.getConfiguration().screenLayout & 15) > 3) {
            isXlarge = true;
        } else {
            isXlarge = false;
        }
        if ((PlatformVersion.isAtLeastHoneycomb() && isXlarge) || isSevenInchTablet(resources)) {
            return true;
        }
        return false;
    }

    @TargetApi(13)
    private static boolean isSevenInchTablet(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        if (!PlatformVersion.isAtLeastHoneycombMR2() || (configuration.screenLayout & 15) > 3 || configuration.smallestScreenWidthDp < 600) {
            return false;
        }
        return true;
    }

    @TargetApi(20)
    public static boolean isWearable(Context context) {
        return PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    private DeviceProperties() {
    }
}
