package com.google.android.gms.playlog;

import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.playlog.PlayLogger.LoggerCallbacks;

@Deprecated
public class OneTimePlayLogger implements LoggerCallbacks {
    private static final String TAG = "OneTimePlayLogger";
    private boolean mIsUsable;
    private final PlayLogger mPlayLogger;

    public OneTimePlayLogger(Context context, int logSource) {
        this(context, logSource, null);
    }

    public OneTimePlayLogger(Context context, int logSource, String accountName) {
        this(context, logSource, accountName, null, true);
    }

    public OneTimePlayLogger(Context context, int logSource, String accountName, String loggingId) {
        this(context, logSource, accountName, loggingId, true);
    }

    public OneTimePlayLogger(Context context, int logSource, String accountName, String loggingId, boolean logAndroidId) {
        String clientName;
        if (context != context.getApplicationContext()) {
            clientName = context.getClass().getName();
        } else {
            clientName = TAG;
        }
        this.mPlayLogger = new PlayLogger(context, logSource, accountName, loggingId, this, logAndroidId, clientName);
        this.mIsUsable = true;
    }

    public void cacheLogEvent(String tag, byte[] sourceExtensionBytes, String... extras) {
        ensureIsUsable();
        this.mPlayLogger.logEvent(tag, sourceExtensionBytes, extras);
    }

    public void cacheLogEvent(long eventTime, String tag, byte[] sourceExtensionBytes, String... extras) {
        ensureIsUsable();
        this.mPlayLogger.logEvent(eventTime, tag, sourceExtensionBytes, extras);
    }

    public void send() {
        ensureIsUsable();
        this.mPlayLogger.start();
        this.mIsUsable = false;
    }

    private void ensureIsUsable() {
        if (!this.mIsUsable) {
            throw new IllegalStateException("Cannot reuse one-time logger after sending.");
        }
    }

    public void onLoggerConnected() {
        this.mPlayLogger.stop();
    }

    public void onLoggerFailedConnectionWithResolution(PendingIntent resolutionIntent) {
        Log.w(TAG, "logger connection failed: " + resolutionIntent);
    }

    public void onLoggerFailedConnection() {
        Log.w(TAG, "logger connection failed");
    }
}
