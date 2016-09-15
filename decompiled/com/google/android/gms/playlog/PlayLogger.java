package com.google.android.gms.playlog;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.playlog.internal.LogEvent;
import com.google.android.gms.playlog.internal.LoggerConnectionCallbacks;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.gms.playlog.internal.PlayLoggerImpl;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics;

@Deprecated
public class PlayLogger {
    private static final String TAG = "PlayLogger";
    private PlayLoggerContext mLoggerContext;
    private final PlayLoggerImpl mLoggerImpl;

    public interface LoggerCallbacks {
        void onLoggerConnected();

        void onLoggerFailedConnection();

        void onLoggerFailedConnectionWithResolution(PendingIntent pendingIntent);
    }

    public final class LogSource {
        public static final int ANDROID_AUTH = 25;
        public static final int ANDROID_CAMERA = 26;
        public static final int ANDROID_GSA = 20;
        public static final int ANDROID_IDE = 7;
        public static final int APP_USAGE_1P = 11;
        public static final int BOOKS = 2;
        public static final int COPRESENCE = 40;
        public static final int CW = 27;
        public static final int DNA_PROBER = 29;
        public static final int EDU_STORE = 15;
        public static final int GAMES = 5;
        public static final int GCM = 43;
        public static final int GEL = 28;
        public static final int GMS_CORE = 10;
        public static final int GMS_CORE_PEOPLE = 16;
        public static final int GMS_CORE_WALLET = 31;
        public static final int GMS_CORE_WALLET_MERCHANT_ERROR = 23;
        public static final int GOKART = 44;
        public static final int GOOGLE_ANALYTICS = 18;
        public static final int GOOGLE_TV = 14;
        public static final int HERREVAD = 13;
        public static final int ICING = 12;
        public static final int INSTORE_WALLET = 33;
        public static final int LATIN_IME = 36;
        public static final int LB_A = 6;
        public static final int LB_C = 24;
        public static final int LB_D = 19;
        public static final int LB_P = 8;
        public static final int LB_S = 9;
        public static final int LB_T = 21;
        public static final int LE = 17;
        public static final int MAGAZINES = 4;
        public static final int MUSIC = 1;
        public static final int NOVA = 34;
        public static final int PERSONAL_LOGGER = 22;
        public static final int SOCIAL = 32;
        public static final int SOCIAL_AFFINITY = 41;
        public static final int STORE = 0;
        public static final int UDR = 30;
        public static final int UNKNOWN = -1;
        public static final int VIDEO = 3;

        private LogSource() {
        }
    }

    public PlayLogger(Context context, int logSource, LoggerCallbacks listener, String realClientName) {
        this(context, logSource, null, null, listener, realClientName);
    }

    public PlayLogger(Context context, int logSource, String uploadAccountName, LoggerCallbacks listener, String realClientName) {
        this(context, logSource, uploadAccountName, null, listener, realClientName);
    }

    public PlayLogger(Context context, int logSource, String uploadAccountName, String loggingId, LoggerCallbacks listener, String realClientName) {
        this(context, logSource, uploadAccountName, loggingId, listener, true, realClientName);
    }

    public PlayLogger(Context context, int logSource, String uploadAccountName, String loggingId, LoggerCallbacks listener, boolean logAndroidId, String realClientName) {
        String packageName = context.getPackageName();
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf(TAG, "This can't happen.", e);
        }
        this.mLoggerContext = new PlayLoggerContext(packageName, versionCode, logSource, uploadAccountName, loggingId, logAndroidId);
        this.mLoggerImpl = new PlayLoggerImpl(context, context.getMainLooper(), new LoggerConnectionCallbacks(listener), new ClientSettings(null, null, null, 49, null, packageName, realClientName, null));
    }

    public PlayLogger setUploadAccountName(String uploadAccountName) {
        if (!Objects.equal(uploadAccountName, this.mLoggerContext.uploadAccountName)) {
            this.mLoggerContext = new PlayLoggerContext(this.mLoggerContext.packageName, this.mLoggerContext.packageVersionCode, this.mLoggerContext.logSource, uploadAccountName, this.mLoggerContext.loggingId, this.mLoggerContext.logAndroidId);
        }
        return this;
    }

    public PlayLogger setLoggingId(String loggingId) {
        if (!Objects.equal(loggingId, this.mLoggerContext.loggingId)) {
            this.mLoggerContext = new PlayLoggerContext(this.mLoggerContext.packageName, this.mLoggerContext.packageVersionCode, this.mLoggerContext.logSource, this.mLoggerContext.uploadAccountName, loggingId, this.mLoggerContext.logAndroidId);
        }
        return this;
    }

    public PlayLogger setIsAllowToLogAndroidId(boolean logAndroidId) {
        if (logAndroidId != this.mLoggerContext.logAndroidId) {
            this.mLoggerContext = new PlayLoggerContext(this.mLoggerContext.packageName, this.mLoggerContext.packageVersionCode, this.mLoggerContext.logSource, this.mLoggerContext.uploadAccountName, this.mLoggerContext.loggingId, logAndroidId);
        }
        return this;
    }

    public void start() {
        this.mLoggerImpl.start();
    }

    public void stop() {
        this.mLoggerImpl.stop();
    }

    public void logEvent(String tag, String... extras) {
        logEvent(tag, null, extras);
    }

    public void logEvent(String tag, byte[] sourceExtensionBytes, String... extras) {
        logEvent(System.currentTimeMillis(), tag, sourceExtensionBytes, extras);
    }

    public void logEvent(long eventTime, String tag, byte[] sourceExtensionBytes, String... extras) {
        this.mLoggerImpl.logEvent(this.mLoggerContext, new LogEvent(eventTime, 0, tag, sourceExtensionBytes, extras));
    }

    public void logEvent(long eventTime, long eventUptime, String tag, byte[] sourceExtensionBytes, String... extras) {
        this.mLoggerImpl.logEvent(this.mLoggerContext, new LogEvent(eventTime, eventUptime, tag, sourceExtensionBytes, extras));
    }

    public void logProtoEvent(byte[] protoEvent) {
        try {
            this.mLoggerImpl.logEvent(this.mLoggerContext, (ClientAnalytics.LogEvent) MessageNano.mergeFrom(new ClientAnalytics.LogEvent(), protoEvent));
        } catch (InvalidProtocolBufferNanoException e) {
            throw new IllegalArgumentException("Must be a ClientAnalytics.LogEvent", e);
        }
    }

    public int getCacheSize() {
        return this.mLoggerImpl.getCacheSize();
    }

    public int getCacheCapacity() {
        return this.mLoggerImpl.getCacheCapacity();
    }

    public void setCacheCapacity(int capacity) {
        this.mLoggerImpl.setCacheCapacity(capacity);
    }

    public boolean isCacheEmpty() {
        return this.mLoggerImpl.isCacheEmpty();
    }

    public boolean isCacheFull() {
        return this.mLoggerImpl.isCacheFull();
    }

    public String getUploadAccountName() {
        return this.mLoggerContext.uploadAccountName;
    }
}
