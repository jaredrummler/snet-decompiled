package com.google.android.gms.common.logging;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.snet.Csv;

public class Logger {
    @VisibleForTesting
    static final String ELISION_ELIDED_PATTERN = "<ELLIDED:%s>";
    @VisibleForTesting
    static final String ELISION_EMPTY = "<EMPTY>";
    @VisibleForTesting
    static final String ELISION_NULL = "<NULL>";
    private final GmsLogger mGmsLogger;
    private final int mLogLevel;
    private final String mPrefix;
    private final String mTag;

    private static String compilePrefix(String... categories) {
        if (categories.length == 0) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder prefixBuilder = new StringBuilder();
        prefixBuilder.append('[');
        for (String category : categories) {
            if (prefixBuilder.length() > 1) {
                prefixBuilder.append(Csv.COMMA);
            }
            prefixBuilder.append(category);
        }
        prefixBuilder.append(']').append(' ');
        return prefixBuilder.toString();
    }

    @VisibleForTesting
    static String elide(boolean isLoggable, Object o) {
        if (o == null) {
            return ELISION_NULL;
        }
        String str = o.toString().trim();
        if (str.isEmpty()) {
            return ELISION_EMPTY;
        }
        if (isLoggable) {
            return str;
        }
        return String.format(ELISION_ELIDED_PATTERN, new Object[]{Integer.valueOf(str.hashCode())});
    }

    public Logger(String tag, String... categories) {
        this(tag, compilePrefix(categories));
    }

    private Logger(String tag, String prefix) {
        this.mPrefix = prefix;
        this.mTag = tag;
        this.mGmsLogger = new GmsLogger(tag);
        this.mLogLevel = getLogLevel();
    }

    private int getLogLevel() {
        int logLevel = 2;
        while (7 >= logLevel && !Log.isLoggable(this.mTag, logLevel)) {
            logLevel++;
        }
        return logLevel;
    }

    public String getTag() {
        return this.mTag;
    }

    public boolean isLoggable(int level) {
        return this.mLogLevel <= level;
    }

    public boolean isPiiLoggable() {
        return this.mGmsLogger.canLogPii();
    }

    public String elidePii(Object o) {
        return elide(this.mGmsLogger.canLogPii(), o);
    }

    public void v(String msg, Object... optionalFormatArgs) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(msg, optionalFormatArgs));
        }
    }

    public void v(String msg, Throwable tr, Object... optionalFormatArgs) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(msg, optionalFormatArgs), tr);
        }
    }

    public void d(String msg, Object... optionalFormatArgs) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(msg, optionalFormatArgs));
        }
    }

    public void d(String msg, Throwable tr, Object... optionalFormatArgs) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(msg, optionalFormatArgs), tr);
        }
    }

    public void i(String msg, Object... optionalFormatArgs) {
        Log.i(this.mTag, format(msg, optionalFormatArgs));
    }

    public void i(String msg, Throwable tr, Object... optionalFormatArgs) {
        Log.i(this.mTag, format(msg, optionalFormatArgs), tr);
    }

    public void w(String msg, Object... optionalFormatArgs) {
        Log.w(this.mTag, format(msg, optionalFormatArgs));
    }

    public void w(String msg, Throwable tr, Object... optionalFormatArgs) {
        Log.w(this.mTag, format(msg, optionalFormatArgs), tr);
    }

    public void w(Throwable tr) {
        Log.w(this.mTag, tr);
    }

    public void e(String msg, Object... optionalFormatArgs) {
        Log.e(this.mTag, format(msg, optionalFormatArgs));
    }

    public void e(String msg, Throwable tr, Object... optionalFormatArgs) {
        Log.e(this.mTag, format(msg, optionalFormatArgs), tr);
    }

    @SuppressLint({"WtfWithoutException"})
    public void wtf(String msg, Object... optionalFormatArgs) {
        Log.wtf(this.mTag, format(msg, optionalFormatArgs));
    }

    public void wtf(String msg, Throwable tr, Object... optionalFormatArgs) {
        Log.wtf(this.mTag, format(msg, optionalFormatArgs), tr);
    }

    public void wtf(Throwable tr) {
        Log.wtf(this.mTag, tr);
    }

    protected String format(String message, Object... optionalFormatArgs) {
        if (optionalFormatArgs != null && optionalFormatArgs.length > 0) {
            message = String.format(message, optionalFormatArgs);
        }
        return this.mPrefix.concat(message);
    }
}
