package com.google.android.gms.common.config;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import com.google.android.gsf.Gservices;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class GservicesValue<T> {
    private static String READ_PERMISSION = null;
    private static final String TAG = "GservicesValue";
    private static GservicesReader sGservicesReader;
    private static final Object sLock;
    private static int sSharedUserId;
    protected final T mDefaultValue;
    protected final String mKey;
    private T mOverride;

    /* renamed from: com.google.android.gms.common.config.GservicesValue.1 */
    static class AnonymousClass1 extends GservicesValue<Boolean> {
        AnonymousClass1(String x0, Boolean x1) {
            super(x0, x1);
        }

        protected Boolean retrieve(String key) {
            return GservicesValue.sGservicesReader.getBoolean(this.mKey, (Boolean) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gms.common.config.GservicesValue.2 */
    static class AnonymousClass2 extends GservicesValue<Long> {
        AnonymousClass2(String x0, Long x1) {
            super(x0, x1);
        }

        protected Long retrieve(String key) {
            return GservicesValue.sGservicesReader.getLong(this.mKey, (Long) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gms.common.config.GservicesValue.3 */
    static class AnonymousClass3 extends GservicesValue<Integer> {
        AnonymousClass3(String x0, Integer x1) {
            super(x0, x1);
        }

        protected Integer retrieve(String key) {
            return GservicesValue.sGservicesReader.getInt(this.mKey, (Integer) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gms.common.config.GservicesValue.4 */
    static class AnonymousClass4 extends GservicesValue<Double> {
        AnonymousClass4(String x0, Double x1) {
            super(x0, x1);
        }

        protected Double retrieve(String key) {
            return GservicesValue.sGservicesReader.getDouble(this.mKey, (Double) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gms.common.config.GservicesValue.5 */
    static class AnonymousClass5 extends GservicesValue<Float> {
        AnonymousClass5(String x0, Float x1) {
            super(x0, x1);
        }

        protected Float retrieve(String key) {
            return GservicesValue.sGservicesReader.getFloat(this.mKey, (Float) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gms.common.config.GservicesValue.6 */
    static class AnonymousClass6 extends GservicesValue<String> {
        AnonymousClass6(String x0, String x1) {
            super(x0, x1);
        }

        protected String retrieve(String key) {
            return GservicesValue.sGservicesReader.getString(this.mKey, (String) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gms.common.config.GservicesValue.7 */
    static class AnonymousClass7 extends GservicesValue<String> {
        AnonymousClass7(String x0, String x1) {
            super(x0, x1);
        }

        protected String retrieve(String key) {
            return GservicesValue.sGservicesReader.getPartnerString(this.mKey, (String) this.mDefaultValue);
        }
    }

    private interface GservicesReader {
        Boolean getBoolean(String str, Boolean bool);

        Double getDouble(String str, Double d);

        Float getFloat(String str, Float f);

        Integer getInt(String str, Integer num);

        Long getLong(String str, Long l);

        String getPartnerString(String str, String str2);

        String getString(String str, String str2);
    }

    private static class GservicesReaderDefaultValues implements GservicesReader {
        private static final Collection<GservicesValue<?>> sOverriddenValues;

        private GservicesReaderDefaultValues() {
        }

        static {
            sOverriddenValues = new HashSet();
        }

        public Boolean getBoolean(String key, Boolean defaultValue) {
            return defaultValue;
        }

        public Integer getInt(String key, Integer defaultValue) {
            return defaultValue;
        }

        public Double getDouble(String key, Double defaultValue) {
            return defaultValue;
        }

        public Float getFloat(String key, Float defaultValue) {
            return defaultValue;
        }

        public Long getLong(String key, Long defaultValue) {
            return defaultValue;
        }

        public String getString(String key, String defaultValue) {
            return defaultValue;
        }

        public String getPartnerString(String key, String defaultValue) {
            return defaultValue;
        }
    }

    @Deprecated
    private static class GservicesReaderForTests implements GservicesReader {
        private final Map<String, ?> values;

        public GservicesReaderForTests(Map<String, ?> values) {
            this.values = values;
        }

        public Boolean getBoolean(String key, Boolean defaultValue) {
            return (Boolean) getValue(key, defaultValue);
        }

        public Integer getInt(String key, Integer defaultValue) {
            return (Integer) getValue(key, defaultValue);
        }

        public Double getDouble(String key, Double defaultValue) {
            return (Double) getValue(key, defaultValue);
        }

        public Float getFloat(String key, Float defaultValue) {
            return (Float) getValue(key, defaultValue);
        }

        public Long getLong(String key, Long defaultValue) {
            return (Long) getValue(key, defaultValue);
        }

        public String getString(String key, String defaultValue) {
            return (String) getValue(key, defaultValue);
        }

        public String getPartnerString(String key, String defaultValue) {
            return (String) getValue(key, defaultValue);
        }

        private <T> T getValue(String key, T defaultValue) {
            if (this.values.containsKey(key)) {
                return this.values.get(key);
            }
            return defaultValue;
        }
    }

    private static class GservicesReaderImpl implements GservicesReader {
        private final ContentResolver mContentResolver;

        public GservicesReaderImpl(ContentResolver contentResolver) {
            this.mContentResolver = contentResolver;
        }

        public Boolean getBoolean(String key, Boolean defaultValue) {
            return Boolean.valueOf(Gservices.getBoolean(this.mContentResolver, key, defaultValue.booleanValue()));
        }

        public Integer getInt(String key, Integer defaultValue) {
            return Integer.valueOf(Gservices.getInt(this.mContentResolver, key, defaultValue.intValue()));
        }

        public Double getDouble(String key, Double defaultValue) {
            String doubleStr = Gservices.getString(this.mContentResolver, key, null);
            if (doubleStr != null) {
                try {
                    defaultValue = Double.valueOf(Double.parseDouble(doubleStr));
                } catch (NumberFormatException e) {
                }
            }
            return defaultValue;
        }

        public Float getFloat(String key, Float defaultValue) {
            String floatStr = Gservices.getString(this.mContentResolver, key, null);
            if (floatStr != null) {
                try {
                    defaultValue = Float.valueOf(Float.parseFloat(floatStr));
                } catch (NumberFormatException e) {
                }
            }
            return defaultValue;
        }

        public Long getLong(String key, Long defaultValue) {
            return Long.valueOf(Gservices.getLong(this.mContentResolver, key, defaultValue.longValue()));
        }

        public String getString(String key, String defaultValue) {
            return Gservices.getString(this.mContentResolver, key, defaultValue);
        }

        public String getPartnerString(String key, String defaultValue) {
            return Partner.getString(this.mContentResolver, key, defaultValue);
        }
    }

    protected abstract T retrieve(String str);

    static {
        sLock = new Object();
        sGservicesReader = null;
        sSharedUserId = 0;
        READ_PERMISSION = Gservices.PERMISSION_READ_GSERVICES;
    }

    public static void init(Context context) {
        synchronized (sLock) {
            if (sGservicesReader == null) {
                sGservicesReader = new GservicesReaderImpl(context.getContentResolver());
            }
            if (sSharedUserId == 0) {
                try {
                    sSharedUserId = context.getPackageManager().getApplicationInfo(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, 0).uid;
                } catch (NameNotFoundException e) {
                    Log.e(TAG, e.toString());
                }
            }
        }
    }

    public static int getSharedUserId() {
        return sSharedUserId;
    }

    @VisibleForTesting
    public static void initForTests() {
        synchronized (sLock) {
            sGservicesReader = new GservicesReaderDefaultValues();
        }
    }

    @VisibleForTesting
    public static void forceInit(Context context) {
        synchronized (sLock) {
            sGservicesReader = new GservicesReaderImpl(context.getContentResolver());
        }
    }

    public static boolean isInitialized() {
        return sGservicesReader != null;
    }

    @Deprecated
    @VisibleForTesting
    public static void initForTests(Map<String, ?> values) {
        synchronized (sLock) {
            sGservicesReader = new GservicesReaderForTests(values);
        }
    }

    @Deprecated
    @VisibleForTesting
    public static void initForTests(String key, Object value) {
        Map<String, Object> values = new HashMap(1);
        values.put(key, value);
        initForTests(values);
    }

    private static boolean initializedForTests() {
        boolean z;
        synchronized (sLock) {
            z = (sGservicesReader instanceof GservicesReaderDefaultValues) || (sGservicesReader instanceof GservicesReaderForTests);
        }
        return z;
    }

    protected GservicesValue(String key, T defaultValue) {
        this.mOverride = null;
        this.mKey = key;
        this.mDefaultValue = defaultValue;
    }

    @VisibleForTesting
    public void override(T value) {
        if (!(sGservicesReader instanceof GservicesReaderDefaultValues)) {
            Log.w(TAG, "GservicesValue.override(): test should probably call initForTests() first");
        }
        this.mOverride = value;
        synchronized (sLock) {
            if (initializedForTests()) {
                GservicesReaderDefaultValues.sOverriddenValues.add(this);
            }
        }
    }

    @VisibleForTesting
    public void resetOverride() {
        this.mOverride = null;
    }

    @VisibleForTesting
    public static void resetAllOverrides() {
        synchronized (sLock) {
            if (initializedForTests()) {
                for (GservicesValue<?> value : GservicesReaderDefaultValues.sOverriddenValues) {
                    value.resetOverride();
                }
                GservicesReaderDefaultValues.sOverriddenValues.clear();
            }
        }
    }

    public final T get() {
        if (this.mOverride != null) {
            return this.mOverride;
        }
        return retrieve(this.mKey);
    }

    public final T getBinderSafe() {
        long bid = Binder.clearCallingIdentity();
        try {
            T t = get();
            return t;
        } finally {
            Binder.restoreCallingIdentity(bid);
        }
    }

    public String getKey() {
        return this.mKey;
    }

    public static GservicesValue<Boolean> value(String key, boolean defaultValue) {
        return new AnonymousClass1(key, Boolean.valueOf(defaultValue));
    }

    public static GservicesValue<Long> value(String key, Long defaultValue) {
        return new AnonymousClass2(key, defaultValue);
    }

    public static GservicesValue<Integer> value(String key, Integer defaultValue) {
        return new AnonymousClass3(key, defaultValue);
    }

    public static GservicesValue<Double> value(String key, Double defaultValue) {
        return new AnonymousClass4(key, defaultValue);
    }

    public static GservicesValue<Float> value(String key, Float defaultValue) {
        return new AnonymousClass5(key, defaultValue);
    }

    public static GservicesValue<String> value(String key, String defaultValue) {
        return new AnonymousClass6(key, defaultValue);
    }

    public static GservicesValue<String> partnerSetting(String key, String defaultValue) {
        return new AnonymousClass7(key, defaultValue);
    }
}
