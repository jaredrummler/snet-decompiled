package com.google.android.gms.people.internal;

import android.content.Context;
import android.util.Log;

public final class PeopleServiceLog {
    private static final boolean LOG_PII_ENABLED = false;
    private static final String LOG_TAG = "PeopleService";
    private static final String PII_SUFFIX = " PII_LOG";
    private static volatile boolean sLogAll;

    public static void setLogAll(Context context, boolean logAll) {
        if (!PeopleUtils.isTestContext(context)) {
            sLogAll = logAll;
        }
    }

    public static boolean canDebugLog() {
        return canLog(3);
    }

    public static boolean canVerboseLog() {
        return canLog(2);
    }

    public static boolean canPii() {
        if (canLog(4)) {
        }
        return LOG_PII_ENABLED;
    }

    public static boolean canLog(int level) {
        return (sLogAll || Log.isLoggable(LOG_TAG, level)) ? true : LOG_PII_ENABLED;
    }

    public static void d(String tag, String message) {
        if (canLog(3)) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String message, Throwable thr) {
        if (canLog(3)) {
            Log.d(tag, message, thr);
        }
    }

    public static void v(String tag, String message) {
        if (canLog(2)) {
            Log.v(tag, message);
        }
    }

    public static void v(String tag, String message, Throwable thr) {
        if (canLog(2)) {
            Log.v(tag, message, thr);
        }
    }

    public static void i(String tag, String message) {
        if (canLog(4)) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String message, Throwable thr) {
        if (canLog(4)) {
            Log.i(tag, message, thr);
        }
    }

    public static void w(String tag, String message) {
        if (canLog(5)) {
            Log.w(tag, message);
        }
    }

    public static void w(String tag, String message, Throwable thr) {
        if (canLog(5)) {
            Log.w(tag, message, thr);
        }
    }

    public static void e(String tag, String message) {
        if (canLog(6)) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable thr) {
        if (canLog(6)) {
            Log.e(tag, message, thr);
        }
    }

    public static void wtf(String tag, String message) {
        if (canLog(7)) {
            Log.wtf(tag, message, new Exception());
        }
    }

    public static void wtf(String tag, String message, Throwable thr) {
        if (canLog(7)) {
            Log.wtf(tag, message, thr);
        }
    }

    private static String getPiiTag(String tag) {
        return canPii() ? tag + PII_SUFFIX : tag;
    }

    public static void pii(String tag, String message) {
        if (canPii()) {
            Log.i(getPiiTag(tag), message);
        }
    }

    public static void pii(String tag, String message, Throwable thr) {
        if (canPii()) {
            Log.i(getPiiTag(tag), message, thr);
        }
    }

    private static String getSecretMessage(String message, String secret) {
        StringBuilder sb = PeopleUtils.getTemporaryStringBuilder();
        sb.append(message);
        sb.append(" [");
        if (!canPii()) {
            secret = PeopleUtils.signature(secret);
        }
        sb.append(secret);
        sb.append("]");
        return sb.toString();
    }

    public static void logWithSecret(String tag, String message, String secret) {
        logWithSecret(tag, message, secret, null);
    }

    public static void logWithSecret(String tag, String message, String secret, Throwable thr) {
        i(getPiiTag(tag), getSecretMessage(message, secret), thr);
    }

    public static void warnWithSecret(String tag, String message, String secret) {
        warnWithSecret(tag, message, secret, null);
    }

    public static void warnWithSecret(String tag, String message, String secret, Throwable thr) {
        w(getPiiTag(tag), getSecretMessage(message, secret), thr);
    }

    public static void errorWithSecret(String tag, String message, String secret) {
        errorWithSecret(tag, message, secret, null);
    }

    public static void errorWithSecret(String tag, String message, String secret, Throwable thr) {
        e(getPiiTag(tag), getSecretMessage(message, secret), thr);
    }

    public static long getStandardizedThreadId(Thread thread) {
        return thread.getId();
    }
}
