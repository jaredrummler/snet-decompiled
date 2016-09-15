package com.google.android.gms.common.util;

import android.content.Context;

@Deprecated
public final class BuildUtils {
    private BuildUtils() {
    }

    @Deprecated
    public static boolean isWearable(Context context) {
        return DeviceProperties.isWearable(context);
    }
}
