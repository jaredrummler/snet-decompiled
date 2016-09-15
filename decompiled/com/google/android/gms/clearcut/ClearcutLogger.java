package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl;
import com.google.android.gms.clearcut.internal.ClearcutLoggerClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.protobuf.nano.MessageNano;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogEvent;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogEventKeyValues;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@VisibleForTesting
public final class ClearcutLogger {
    public static final Api<NoOptions> API;
    public static final AbstractClientBuilder<ClearcutLoggerClientImpl, NoOptions> CLIENT_BUILDER;
    public static final ClientKey<ClearcutLoggerClientImpl> CLIENT_KEY;
    public static final ClearcutLoggerApi ClearcutLoggerApi;
    private static final String TAG = "ClearcutLogger";
    private final BootCount mBootCount;
    private final Clock mClock;
    private final Context mContext;
    private final boolean mIsAnonymous;
    private int mLogSource;
    private String mLogSourceName;
    private final ClearcutLoggerApi mLoggerApi;
    private String mLoggingId;
    private TimeZoneOffsetProvider mOffsetProvider;
    private final String mPackageName;
    private final int mPackageVersionCode;
    private int mQosTier;
    private String mUploadAccountName;

    public class LogEventBuilder {
        private MessageProducer mClientVisualElementsProducer;
        private final MessageProducer mExtensionProducer;
        private boolean mIsConsumed;
        private final LogEvent mLogEvent;
        private int mLogSource;
        private String mLogSourceName;
        private String mLoggingId;
        private int mQosTier;
        private ArrayList<Integer> mTestCodes;
        private String mUploadAccountName;

        private LogEventBuilder(ClearcutLogger clearcutLogger, byte[] extensionBytes) {
            this(extensionBytes, null);
        }

        private LogEventBuilder(ClearcutLogger clearcutLogger, MessageProducer extensionProducer) {
            this(null, extensionProducer);
        }

        private LogEventBuilder(byte[] extensionBytes, MessageProducer extensionProducer) {
            this.mLogSource = ClearcutLogger.this.mLogSource;
            this.mLogSourceName = ClearcutLogger.this.mLogSourceName;
            this.mUploadAccountName = ClearcutLogger.this.mUploadAccountName;
            this.mLoggingId = ClearcutLogger.this.mLoggingId;
            this.mQosTier = ClearcutLogger.this.mQosTier;
            this.mTestCodes = null;
            this.mLogEvent = new LogEvent();
            this.mIsConsumed = false;
            this.mUploadAccountName = ClearcutLogger.this.mUploadAccountName;
            this.mLoggingId = ClearcutLogger.this.mLoggingId;
            this.mLogEvent.eventTimeMs = ClearcutLogger.this.mClock.currentTimeMillis();
            this.mLogEvent.eventUptimeMs = ClearcutLogger.this.mClock.elapsedRealtime();
            this.mLogEvent.bootCount = (long) ClearcutLogger.this.mBootCount.getBootCount(ClearcutLogger.this.mContext);
            this.mLogEvent.timezoneOffsetSeconds = ClearcutLogger.this.mOffsetProvider.getOffsetSeconds(this.mLogEvent.eventTimeMs);
            if (extensionBytes != null) {
                this.mLogEvent.sourceExtension = extensionBytes;
            }
            this.mExtensionProducer = extensionProducer;
        }

        @Deprecated
        public LogEventBuilder setLogSource(int logSource) {
            this.mLogSource = logSource;
            return this;
        }

        public LogEventBuilder setLogSourceName(String logSourceName) {
            this.mLogSourceName = logSourceName;
            return this;
        }

        public LogEventBuilder setQosTier(int qosTier) {
            this.mQosTier = qosTier;
            return this;
        }

        public LogEventBuilder setUploadAccountName(@Nullable String uploadAccountName) {
            if (ClearcutLogger.this.mIsAnonymous) {
                throw new IllegalArgumentException("setUploadAccountName forbidden on anonymous logger");
            }
            this.mUploadAccountName = uploadAccountName;
            return this;
        }

        @Deprecated
        public LogEventBuilder setLoggingId(@Nullable String loggingId) {
            this.mLoggingId = loggingId;
            return this;
        }

        @Deprecated
        public LogEventBuilder setTag(String tag) {
            this.mLogEvent.tag = tag;
            return this;
        }

        public LogEventBuilder setEventCode(int eventCode) {
            this.mLogEvent.eventCode = eventCode;
            return this;
        }

        public LogEventBuilder setEventFlowId(int eventFlowId) {
            this.mLogEvent.eventFlowId = eventFlowId;
            return this;
        }

        public LogEventBuilder setClientVisualElements(byte[] clientVisualElements) {
            if (clientVisualElements != null) {
                this.mLogEvent.clientVe = clientVisualElements;
            }
            return this;
        }

        public LogEventBuilder setClientVisualElementsProducer(MessageProducer clientVisualElementsProducer) {
            this.mClientVisualElementsProducer = clientVisualElementsProducer;
            return this;
        }

        public LogEventBuilder addTestCode(int testCode) {
            if (this.mTestCodes == null) {
                this.mTestCodes = new ArrayList();
            }
            this.mTestCodes.add(Integer.valueOf(testCode));
            return this;
        }

        LogEventBuilder setKeyValuePairs(@Nullable Map<String, String> keyValues) {
            if (keyValues != null) {
                this.mLogEvent.value = new LogEventKeyValues[keyValues.size()];
                int i = 0;
                for (Entry<String, String> entry : keyValues.entrySet()) {
                    LogEventKeyValues keyValue = new LogEventKeyValues();
                    keyValue.key = (String) entry.getKey();
                    keyValue.value = (String) entry.getValue();
                    int i2 = i + 1;
                    this.mLogEvent.value[i] = keyValue;
                    i = i2;
                }
            } else {
                this.mLogEvent.value = LogEventKeyValues.emptyArray();
            }
            return this;
        }

        @VisibleForTesting
        public LogEventParcelable getLogEventParcelable() {
            return new LogEventParcelable(new PlayLoggerContext(ClearcutLogger.this.mPackageName, ClearcutLogger.this.mPackageVersionCode, this.mLogSource, this.mLogSourceName, this.mUploadAccountName, this.mLoggingId, ClearcutLogger.this.mIsAnonymous, this.mQosTier), this.mLogEvent, this.mExtensionProducer, this.mClientVisualElementsProducer, ClearcutLogger.toArray(this.mTestCodes));
        }

        public PendingResult<Status> log(GoogleApiClient apiClient) {
            if (this.mIsConsumed) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.mIsConsumed = true;
            return ClearcutLogger.this.mLoggerApi.logEvent(apiClient, getLogEventParcelable());
        }

        public PendingResult<Status> logAsync(GoogleApiClient apiClient) {
            if (this.mIsConsumed) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.mIsConsumed = true;
            return ClearcutLogger.this.mLoggerApi.logEventAsync(apiClient, getLogEventParcelable());
        }

        public PendingResult<Status> logAsync() {
            if (this.mIsConsumed) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.mIsConsumed = true;
            return ClearcutLogger.this.mLoggerApi.logEventAsync(ClearcutLogger.this.mContext, getLogEventParcelable());
        }
    }

    public interface MessageProducer {
        byte[] toProtoBytes();
    }

    public static class TimeZoneOffsetProvider {
        public long getOffsetSeconds(long timeMillis) {
            return (long) (TimeZone.getDefault().getOffset(timeMillis) / 1000);
        }
    }

    static {
        CLIENT_KEY = new ClientKey();
        CLIENT_BUILDER = new AbstractClientBuilder<ClearcutLoggerClientImpl, NoOptions>() {
            public ClearcutLoggerClientImpl buildClient(Context context, Looper looper, ClientSettings commonSettings, NoOptions apiOptions, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
                return new ClearcutLoggerClientImpl(context, looper, commonSettings, connectedListener, connectionFailedListener);
            }
        };
        API = new Api("ClearcutLogger.API", CLIENT_BUILDER, CLIENT_KEY);
        ClearcutLoggerApi = new ClearcutLoggerApiImpl();
    }

    @Deprecated
    public ClearcutLogger(Context context, String logSourceName, @Nullable String uploadAccountName, @Nullable String loggingId) {
        this(context, -1, logSourceName, uploadAccountName, loggingId, false, ClearcutLoggerApi, DefaultClock.getInstance(), null, BootCount.INSTANCE);
    }

    public ClearcutLogger(Context context, String logSourceName, @Nullable String uploadAccountName) {
        this(context, -1, logSourceName, uploadAccountName, null, false, ClearcutLoggerApi, DefaultClock.getInstance(), null, BootCount.INSTANCE);
    }

    @Deprecated
    public ClearcutLogger(Context context, String logSourceName, String uploadAccountName, String loggingId, ClearcutLoggerApi loggerApi, Clock clock, TimeZoneOffsetProvider offsetProvider) {
        this(context, -1, logSourceName, uploadAccountName, loggingId, false, loggerApi, clock, offsetProvider, BootCount.INSTANCE);
    }

    public ClearcutLogger(Context context, String logSourceName, String uploadAccountName, ClearcutLoggerApi loggerApi, Clock clock, TimeZoneOffsetProvider offsetProvider) {
        this(context, -1, logSourceName, uploadAccountName, null, false, loggerApi, clock != null ? clock : DefaultClock.getInstance(), offsetProvider, BootCount.INSTANCE);
    }

    @Deprecated
    public ClearcutLogger(Context context, int logSource, @Nullable String uploadAccountName, @Nullable String loggingId) {
        this(context, logSource, uploadAccountName, loggingId, ClearcutLoggerApi, DefaultClock.getInstance(), new TimeZoneOffsetProvider());
    }

    @Deprecated
    public ClearcutLogger(Context context, int logSource, String uploadAccountName, String loggingId, ClearcutLoggerApi loggerApi, Clock clock, TimeZoneOffsetProvider offsetProvider) {
        this(context, logSource, BuildConfig.VERSION_NAME, uploadAccountName, loggingId, false, loggerApi, clock, offsetProvider, BootCount.INSTANCE);
    }

    @VisibleForTesting
    public ClearcutLogger(Context context, int logSource, String logSourceName, String uploadAccountName, String loggingId, boolean isAnonymous, ClearcutLoggerApi loggerApi, Clock clock, TimeZoneOffsetProvider offsetProvider, BootCount bootCount) {
        boolean z = false;
        this.mLogSource = -1;
        this.mQosTier = 0;
        Context maybeApplicationContext = context.getApplicationContext();
        if (maybeApplicationContext == null) {
            maybeApplicationContext = context;
        }
        this.mContext = maybeApplicationContext;
        this.mPackageName = context.getPackageName();
        this.mPackageVersionCode = getVersionCode(context);
        this.mLogSource = logSource;
        this.mLogSourceName = logSourceName;
        this.mUploadAccountName = uploadAccountName;
        this.mLoggingId = loggingId;
        this.mIsAnonymous = isAnonymous;
        this.mLoggerApi = loggerApi;
        this.mClock = clock;
        if (offsetProvider == null) {
            offsetProvider = new TimeZoneOffsetProvider();
        }
        this.mOffsetProvider = offsetProvider;
        this.mBootCount = bootCount;
        this.mQosTier = 0;
        if (this.mIsAnonymous) {
            if (this.mUploadAccountName == null) {
                z = true;
            }
            Preconditions.checkArgument(z, "can't be anonymous with an upload account");
        }
    }

    public static ClearcutLogger anonymousLogger(Context context, String logSourceName) {
        return new ClearcutLogger(context, -1, logSourceName, null, null, true, ClearcutLoggerApi, DefaultClock.getInstance(), null, BootCount.INSTANCE);
    }

    public boolean isAnonymous() {
        return this.mIsAnonymous;
    }

    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf(TAG, "This can't happen.");
            return versionCode;
        }
    }

    public ClearcutLogger setTimeZoneOffsetProvider(@Nullable TimeZoneOffsetProvider provider) {
        if (provider == null) {
            provider = new TimeZoneOffsetProvider();
        }
        this.mOffsetProvider = provider;
        return this;
    }

    public ClearcutLogger setQosTier(int qosTier) {
        if (qosTier < 0 || qosTier > 4) {
            qosTier = 0;
        }
        this.mQosTier = qosTier;
        return this;
    }

    public LogEventBuilder newEvent(MessageNano extension) {
        return new LogEventBuilder(MessageNano.toByteArray(extension), null);
    }

    public LogEventBuilder newEvent(byte[] extensionBytes) {
        return new LogEventBuilder(extensionBytes, null);
    }

    public LogEventBuilder newEvent(MessageProducer extensionProducer) {
        return new LogEventBuilder(extensionProducer, null);
    }

    public boolean flush(GoogleApiClient apiClient, long time, TimeUnit unit) {
        return this.mLoggerApi.flush(apiClient, time, unit);
    }

    public void disconnectAsync(GoogleApiClient apiClient) {
        this.mLoggerApi.disconnectAsync(apiClient);
    }

    @Deprecated
    public int getLogSource() {
        return this.mLogSource;
    }

    public String getLogSourceName() {
        return this.mLogSourceName;
    }

    public String getUploadAccountName() {
        return this.mUploadAccountName;
    }

    @Deprecated
    public String getLoggingId() {
        return this.mLoggingId;
    }

    private static int[] toArray(ArrayList<Integer> list) {
        if (list == null) {
            return null;
        }
        int[] result = new int[list.size()];
        int i = 0;
        Iterator i$ = list.iterator();
        while (i$.hasNext()) {
            int i2 = i + 1;
            result[i] = ((Integer) i$.next()).intValue();
            i = i2;
        }
        return result;
    }
}
