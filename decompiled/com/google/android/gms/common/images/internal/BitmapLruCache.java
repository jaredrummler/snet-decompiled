package com.google.android.gms.common.images.internal;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class BitmapLruCache<K> extends LruCache<K, Bitmap> {
    public BitmapLruCache(int sizeInBytes) {
        super(sizeInBytes);
    }

    protected int sizeOf(K k, Bitmap value) {
        return value.getHeight() * value.getRowBytes();
    }
}
