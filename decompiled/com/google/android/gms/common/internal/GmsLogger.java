package com.google.android.gms.common.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.playlog.OneTimePlayLogger;
import com.google.android.snet.Csv;

public final class GmsLogger {
    private static final String GMS_WTF = "GMS_WTF";
    private static final boolean LOG_PII_ENABLED = false;
    public static final int MAX_PII_TAG_LENGTH;
    public static final int MAX_TAG_LENGTH = 23;
    private static final int MAX_WTF_TRACE_ELEMENTS = 2;
    private static final String NO_PREFIX;
    private static final String PII_SUFFIX = " PII_LOG";
    private final String mLogTag;
    private final String mPrefix;

    static {
        MAX_PII_TAG_LENGTH = 23 - PII_SUFFIX.length();
        NO_PREFIX = null;
    }

    public static boolean isBuildPiiEnabled() {
        return LOG_PII_ENABLED;
    }

    public GmsLogger(String logTag, String messagePrefix) {
        Preconditions.checkNotNull(logTag, "log tag cannot be null");
        boolean z = logTag.length() <= MAX_TAG_LENGTH ? true : LOG_PII_ENABLED;
        Object[] objArr = new Object[MAX_WTF_TRACE_ELEMENTS];
        objArr[MAX_PII_TAG_LENGTH] = logTag;
        objArr[1] = Integer.valueOf(MAX_TAG_LENGTH);
        Preconditions.checkArgument(z, "tag \"%s\" is longer than the %d character maximum", objArr);
        this.mLogTag = logTag;
        if (messagePrefix == null || messagePrefix.length() <= 0) {
            this.mPrefix = NO_PREFIX;
        } else {
            this.mPrefix = messagePrefix;
        }
    }

    public GmsLogger(String logTag) {
        this(logTag, NO_PREFIX);
    }

    public GmsLogger withMessagePrefix(String prefix) {
        return new GmsLogger(this.mLogTag, prefix);
    }

    public String getTag() {
        return this.mLogTag;
    }

    public boolean canLog(int level) {
        return Log.isLoggable(this.mLogTag, level);
    }

    public boolean canLogPii() {
        return LOG_PII_ENABLED;
    }

    public void d(String tag, String message) {
        if (canLog(3)) {
            Log.d(tag, prefix(message));
        }
    }

    public void d(String tag, String message, Throwable thr) {
        if (canLog(3)) {
            Log.d(tag, prefix(message), thr);
        }
    }

    public void dfmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLog(3)) {
            Log.d(tag, prefixfmt(messageFormatString, messageParams));
        }
    }

    public void v(String tag, String message) {
        if (canLog(MAX_WTF_TRACE_ELEMENTS)) {
            Log.v(tag, prefix(message));
        }
    }

    public void v(String tag, String message, Throwable thr) {
        if (canLog(MAX_WTF_TRACE_ELEMENTS)) {
            Log.v(tag, prefix(message), thr);
        }
    }

    public void vfmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLog(MAX_WTF_TRACE_ELEMENTS)) {
            Log.v(tag, prefixfmt(messageFormatString, messageParams));
        }
    }

    public void i(String tag, String message) {
        if (canLog(4)) {
            Log.i(tag, prefix(message));
        }
    }

    public void i(String tag, String message, Throwable thr) {
        if (canLog(4)) {
            Log.i(tag, prefix(message), thr);
        }
    }

    public void ifmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLog(4)) {
            Log.i(tag, prefixfmt(messageFormatString, messageParams));
        }
    }

    public void w(String tag, String message) {
        if (canLog(5)) {
            Log.w(tag, prefix(message));
        }
    }

    public void w(String tag, String message, Throwable thr) {
        if (canLog(5)) {
            Log.w(tag, prefix(message), thr);
        }
    }

    public void wfmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLog(5)) {
            Log.w(this.mLogTag, prefixfmt(messageFormatString, messageParams));
        }
    }

    public void e(String tag, String message) {
        if (canLog(6)) {
            Log.e(tag, prefix(message));
        }
    }

    public void e(String tag, String message, Throwable thr) {
        if (canLog(6)) {
            Log.e(tag, prefix(message), thr);
        }
    }

    public void efmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLog(6)) {
            Log.e(tag, prefixfmt(messageFormatString, messageParams));
        }
    }

    public void wtf(Context context, String tag, String message, Throwable thr) {
        StackTraceElement[] trace = thr.getStackTrace();
        StringBuilder sb = new StringBuilder();
        int i = MAX_PII_TAG_LENGTH;
        while (i < trace.length && i < MAX_WTF_TRACE_ELEMENTS) {
            sb.append(trace[i].toString());
            sb.append(Csv.NEWLINE);
            i++;
        }
        OneTimePlayLogger logger = new OneTimePlayLogger(context, 10);
        String str = GMS_WTF;
        String[] strArr = new String[MAX_WTF_TRACE_ELEMENTS];
        strArr[MAX_PII_TAG_LENGTH] = GMS_WTF;
        strArr[1] = sb.toString();
        logger.cacheLogEvent(str, null, strArr);
        logger.send();
        if (canLog(7)) {
            Log.e(tag, prefix(message), thr);
            Log.wtf(tag, prefix(message), thr);
        }
    }

    public void pii(String tag, String message) {
        if (canLogPii()) {
            Log.i(tag + PII_SUFFIX, prefix(message));
        }
    }

    public void pii(String tag, String message, Throwable thr) {
        if (canLogPii()) {
            Log.i(tag + PII_SUFFIX, prefix(message), thr);
        }
    }

    public void piifmt(String tag, String messageFormatString, Object... messageParams) {
        if (canLogPii()) {
            Log.i(tag + PII_SUFFIX, prefixfmt(messageFormatString, messageParams));
        }
    }

    private String prefix(String message) {
        return this.mPrefix == null ? message : this.mPrefix.concat(message);
    }

    private String prefixfmt(String messageFormatString, Object... messageParams) {
        String msg = String.format(messageFormatString, messageParams);
        return this.mPrefix == null ? msg : this.mPrefix.concat(msg);
    }
}
