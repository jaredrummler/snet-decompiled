package com.google.android.gms.common.internal;

import android.content.ContentValues;
import android.os.Looper;
import android.util.Log;

public final class Asserts {
    private static final String TAG = "Asserts";

    public static void checkNull(Object reference) {
        if (reference != null) {
            throw new IllegalArgumentException("non-null reference");
        }
    }

    public static void checkNotNull(Object reference) {
        if (reference == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    public static void checkNotNull(Object reference, Object errorMessage) {
        if (reference == null) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void checkNotNullIfPresent(String field, ContentValues values) {
        if (values.containsKey(field) && values.get(field) == null) {
            throw new IllegalArgumentException(field + " cannot be set to null");
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkState(boolean expression, String format, Object... args) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(String.format(format, args)));
        }
    }

    public static void fail(Object errorMessage) {
        throw new IllegalStateException(String.valueOf(errorMessage));
    }

    public static void checkMainThread(String errorMessage) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            Log.e(TAG, "checkMainThread: current thread " + Thread.currentThread() + " IS NOT the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(errorMessage);
        }
    }

    public static void checkNotMainThread(String errorMessage) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Log.e(TAG, "checkNotMainThread: current thread " + Thread.currentThread() + " IS the main thread " + Looper.getMainLooper().getThread() + "!");
            throw new IllegalStateException(errorMessage);
        }
    }

    private Asserts() {
        throw new AssertionError("Uninstantiable");
    }
}
