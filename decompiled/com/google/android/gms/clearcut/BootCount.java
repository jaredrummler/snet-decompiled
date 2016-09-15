package com.google.android.gms.clearcut;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

public class BootCount {
    private static final String BOOT_COUNT = "bootCount";
    public static final BootCount INSTANCE;
    private static int sBootCount;

    static {
        sBootCount = -1;
        INSTANCE = new BootCount();
    }

    @VisibleForTesting
    protected BootCount() {
    }

    public int getBootCount(Context context) {
        if (sBootCount < 0) {
            sBootCount = context.getSharedPreferences(BOOT_COUNT, 0).getInt(BOOT_COUNT, 1);
        }
        return sBootCount;
    }
}
