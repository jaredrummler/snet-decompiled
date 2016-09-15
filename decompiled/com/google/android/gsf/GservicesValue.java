package com.google.android.gsf;

import android.content.ContentResolver;
import android.content.Context;
import com.google.android.gsf.GoogleSettingsContract.Partner;

public abstract class GservicesValue<T> {
    private static ContentResolver sContentResolver;
    protected final T mDefaultValue;
    protected final String mKey;

    /* renamed from: com.google.android.gsf.GservicesValue.1 */
    static class AnonymousClass1 extends GservicesValue<Boolean> {
        AnonymousClass1(String x0, Boolean x1) {
            super(x0, x1);
        }

        protected Boolean retrieve(String key) {
            return Boolean.valueOf(Gservices.getBoolean(GservicesValue.sContentResolver, this.mKey, ((Boolean) this.mDefaultValue).booleanValue()));
        }
    }

    /* renamed from: com.google.android.gsf.GservicesValue.2 */
    static class AnonymousClass2 extends GservicesValue<Long> {
        AnonymousClass2(String x0, Long x1) {
            super(x0, x1);
        }

        protected Long retrieve(String key) {
            return Long.valueOf(Gservices.getLong(GservicesValue.sContentResolver, this.mKey, ((Long) this.mDefaultValue).longValue()));
        }
    }

    /* renamed from: com.google.android.gsf.GservicesValue.3 */
    static class AnonymousClass3 extends GservicesValue<Integer> {
        AnonymousClass3(String x0, Integer x1) {
            super(x0, x1);
        }

        protected Integer retrieve(String key) {
            return Integer.valueOf(Gservices.getInt(GservicesValue.sContentResolver, this.mKey, ((Integer) this.mDefaultValue).intValue()));
        }
    }

    /* renamed from: com.google.android.gsf.GservicesValue.4 */
    static class AnonymousClass4 extends GservicesValue<Float> {
        AnonymousClass4(String x0, Float x1) {
            super(x0, x1);
        }

        protected Float retrieve(String key) {
            return Float.valueOf(Gservices.getFloat(GservicesValue.sContentResolver, this.mKey, ((Float) this.mDefaultValue).floatValue()));
        }
    }

    /* renamed from: com.google.android.gsf.GservicesValue.5 */
    static class AnonymousClass5 extends GservicesValue<String> {
        AnonymousClass5(String x0, String x1) {
            super(x0, x1);
        }

        protected String retrieve(String key) {
            return Gservices.getString(GservicesValue.sContentResolver, this.mKey, (String) this.mDefaultValue);
        }
    }

    /* renamed from: com.google.android.gsf.GservicesValue.6 */
    static class AnonymousClass6 extends GservicesValue<String> {
        AnonymousClass6(String x0, String x1) {
            super(x0, x1);
        }

        protected String retrieve(String key) {
            return Partner.getString(GservicesValue.sContentResolver, this.mKey, (String) this.mDefaultValue);
        }
    }

    protected abstract T retrieve(String str);

    static {
        sContentResolver = null;
    }

    public static void init(Context context) {
        sContentResolver = context.getContentResolver();
    }

    protected GservicesValue(String key, T defaultValue) {
        this.mKey = key;
        this.mDefaultValue = defaultValue;
    }

    public final T get() {
        return retrieve(this.mKey);
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

    public static GservicesValue<Float> value(String key, Float defaultValue) {
        return new AnonymousClass4(key, defaultValue);
    }

    public static GservicesValue<String> value(String key, String defaultValue) {
        return new AnonymousClass5(key, defaultValue);
    }

    public static GservicesValue<String> partnerSetting(String key, String defaultValue) {
        return new AnonymousClass6(key, defaultValue);
    }
}
