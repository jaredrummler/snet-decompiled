package com.google.android.gms.common.images.internal;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;

public final class ColorFilters {
    public static final ColorFilter COLOR_FILTER_BW;
    private static final ColorMatrix COLOR_MATRIX_WB;

    private ColorFilters() {
    }

    static {
        COLOR_MATRIX_WB = new ColorMatrix();
        COLOR_MATRIX_WB.setSaturation(0.0f);
        COLOR_FILTER_BW = new ColorMatrixColorFilter(COLOR_MATRIX_WB);
    }
}
