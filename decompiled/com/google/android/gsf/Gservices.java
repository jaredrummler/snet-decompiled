package com.google.android.gsf;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.lint.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Gservices {
    public static final String CHANGED_ACTION = "com.google.gservices.intent.action.GSERVICES_CHANGED";
    public static final Uri CONTENT_PREFIX_URI;
    public static final Uri CONTENT_URI;
    public static final Pattern FALSE_PATTERN;
    public static final String OVERRIDE_ACTION = "com.google.gservices.intent.action.GSERVICES_OVERRIDE";
    public static final String PERMISSION_READ_GSERVICES = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    public static final String TAG = "Gservices";
    public static final Pattern TRUE_PATTERN;
    private static HashMap<String, String> sCache;
    private static Context sPreCachePermissionCheckContext;
    private static String[] sPreloadedPrefixes;
    private static Object sVersionToken;

    /* renamed from: com.google.android.gsf.Gservices.1 */
    static class AnonymousClass1 extends Thread {
        final /* synthetic */ ContentResolver val$cr;

        /* renamed from: com.google.android.gsf.Gservices.1.1 */
        class AnonymousClass1 extends ContentObserver {
            AnonymousClass1(Handler x0) {
                super(x0);
            }

            public void onChange(boolean selfChange) {
                synchronized (Gservices.class) {
                    Gservices.sCache.clear();
                    Gservices.sVersionToken = new Object();
                    if (Gservices.sPreloadedPrefixes.length > 0) {
                        Gservices.bulkCacheByPrefix(AnonymousClass1.this.val$cr, Gservices.sPreloadedPrefixes);
                    }
                }
            }
        }

        AnonymousClass1(String x0, ContentResolver contentResolver) {
            this.val$cr = contentResolver;
            super(x0);
        }

        public void run() {
            Looper.prepare();
            this.val$cr.registerContentObserver(Gservices.CONTENT_URI, true, new AnonymousClass1(new Handler(Looper.myLooper())));
            Looper.loop();
        }
    }

    static {
        CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        CONTENT_PREFIX_URI = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        TRUE_PATTERN = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        FALSE_PATTERN = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        sPreloadedPrefixes = new String[0];
        sPreCachePermissionCheckContext = null;
    }

    private static void ensureCacheInitializedLocked(ContentResolver cr) {
        if (sCache == null) {
            sCache = new HashMap();
            sVersionToken = new Object();
            new AnonymousClass1(TAG, cr).start();
        }
    }

    public static void enablePreCachePermissionChecking(Context context) {
        sPreCachePermissionCheckContext = context.getApplicationContext();
    }

    private static void maybeEnforceCalingOrSelfPermission() {
        if (sPreCachePermissionCheckContext != null) {
            sPreCachePermissionCheckContext.enforceCallingOrSelfPermission(PERMISSION_READ_GSERVICES, "attempting to read gservices without permission");
        }
    }

    public static String getString(ContentResolver cr, String key, String defValue) {
        maybeEnforceCalingOrSelfPermission();
        synchronized (Gservices.class) {
            ensureCacheInitializedLocked(cr);
            Object version = sVersionToken;
            String value;
            if (sCache.containsKey(key)) {
                value = (String) sCache.get(key);
                if (value != null) {
                    defValue = value;
                }
                return defValue;
            }
            for (String prefix : sPreloadedPrefixes) {
                if (key.startsWith(prefix)) {
                    return defValue;
                }
            }
            Cursor cursor = cr.query(CONTENT_URI, null, null, new String[]{key}, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        value = cursor.getString(1);
                        synchronized (Gservices.class) {
                            if (version == sVersionToken) {
                                sCache.put(key, value);
                            }
                        }
                        if (value == null) {
                            value = defValue;
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        return value;
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
            }
            sCache.put(key, null);
            if (cursor == null) {
                return defValue;
            }
            cursor.close();
            return defValue;
        }
    }

    public static String getString(ContentResolver cr, String key) {
        return getString(cr, key, null);
    }

    public static int getInt(ContentResolver cr, String key, int defValue) {
        String valString = getString(cr, key);
        if (valString == null) {
            return defValue;
        }
        try {
            return Integer.parseInt(valString);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static float getFloat(ContentResolver cr, String key, float defValue) {
        String valString = getString(cr, key);
        if (valString == null) {
            return defValue;
        }
        try {
            return Float.parseFloat(valString);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static long getLong(ContentResolver cr, String key, long defValue) {
        String valString = getString(cr, key);
        if (valString == null) {
            return defValue;
        }
        try {
            return Long.parseLong(valString);
        } catch (NumberFormatException e) {
            return defValue;
        }
    }

    public static boolean getBoolean(ContentResolver cr, String key, boolean defValue) {
        String valString = getString(cr, key);
        if (valString == null || valString.equals(BuildConfig.VERSION_NAME)) {
            return defValue;
        }
        if (TRUE_PATTERN.matcher(valString).matches()) {
            return true;
        }
        if (FALSE_PATTERN.matcher(valString).matches()) {
            return false;
        }
        Log.w(TAG, "attempt to read gservices key " + key + " (value \"" + valString + "\") as boolean");
        return defValue;
    }

    public static Map<String, String> getStringsByPrefix(ContentResolver cr, String... prefixes) {
        maybeEnforceCalingOrSelfPermission();
        Cursor c = cr.query(CONTENT_PREFIX_URI, null, null, prefixes, null);
        TreeMap<String, String> out = new TreeMap();
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    out.put(c.getString(0), c.getString(1));
                } finally {
                    c.close();
                }
            }
        }
        return out;
    }

    public static void bulkCacheByPrefix(ContentResolver cr, String... prefixes) {
        maybeEnforceCalingOrSelfPermission();
        Map<String, String> entries = getStringsByPrefix(cr, prefixes);
        synchronized (Gservices.class) {
            ensureCacheInitializedLocked(cr);
            sPreloadedPrefixes = prefixes;
            for (Entry<String, String> entry : entries.entrySet()) {
                sCache.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static Object getVersionToken(ContentResolver cr) {
        Object obj;
        maybeEnforceCalingOrSelfPermission();
        synchronized (Gservices.class) {
            ensureCacheInitializedLocked(cr);
            obj = sVersionToken;
        }
        return obj;
    }
}
