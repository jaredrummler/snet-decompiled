package com.google.android.gsf;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gsf.GoogleSettingsContract.Partner;
import java.util.Collection;
import java.util.HashSet;

public class UseLocationForServices {
    public static final String ACTION_SET_USE_LOCATION_FOR_SERVICES = "com.google.android.gsf.action.SET_USE_LOCATION_FOR_SERVICES";
    public static final String EXTRA_DISABLE_USE_LOCATION_FOR_SERVICES = "disable";
    private static final String[] GOOGLE_GEOLOCATION_ORIGINS;
    private static final String TAG = "UseLocationForServices";
    public static final int USE_LOCATION_FOR_SERVICES_NOT_SET = 2;
    public static final int USE_LOCATION_FOR_SERVICES_OFF = 0;
    public static final int USE_LOCATION_FOR_SERVICES_ON = 1;

    static {
        String[] strArr = new String[USE_LOCATION_FOR_SERVICES_NOT_SET];
        strArr[USE_LOCATION_FOR_SERVICES_OFF] = "http://www.google.com";
        strArr[USE_LOCATION_FOR_SERVICES_ON] = "http://www.google.co.uk";
        GOOGLE_GEOLOCATION_ORIGINS = strArr;
    }

    public static int getUseLocationForServices(Context context) {
        return Partner.getInt(context.getContentResolver(), Partner.USE_LOCATION_FOR_SERVICES, USE_LOCATION_FOR_SERVICES_NOT_SET);
    }

    @Deprecated
    public static boolean setUseLocationForServices(Context context, boolean value) {
        if (!value) {
            return forceSetUseLocationForServices(context, value);
        }
        if (getUseLocationForServices(context) != USE_LOCATION_FOR_SERVICES_ON) {
            Intent intent = new Intent("com.google.android.gsf.GOOGLE_APPS_LOCATION_SETTINGS");
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
        return false;
    }

    @Deprecated
    public static boolean forceSetUseLocationForServices(Context context, boolean value) {
        setGoogleBrowserGeolocation(context, value);
        boolean success = Partner.putInt(context.getContentResolver(), Partner.USE_LOCATION_FOR_SERVICES, value ? USE_LOCATION_FOR_SERVICES_ON : USE_LOCATION_FOR_SERVICES_OFF);
        context.sendBroadcast(new Intent("com.google.android.gsf.settings.GoogleLocationSettings.UPDATE_LOCATION_SETTINGS"));
        return success;
    }

    public static void registerUseLocationForServicesObserver(Context context, ContentObserver observer) {
        context.getContentResolver().registerContentObserver(Partner.getUriFor(Partner.USE_LOCATION_FOR_SERVICES), false, observer);
    }

    private static void setGoogleBrowserGeolocation(Context context, boolean allow) {
        try {
            ContentResolver resolver = context.getContentResolver();
            String oldValue = Secure.getString(resolver, "allowed_geolocation_origins");
            Secure.putString(resolver, "allowed_geolocation_origins", allow ? addGoogleOrigins(oldValue) : removeGoogleOrigins(oldValue));
        } catch (RuntimeException ex) {
            Log.e(TAG, "Failed to set browser geolocation permissions: " + ex);
        }
    }

    private static String addGoogleOrigins(String setting) {
        HashSet<String> origins = parseAllowGeolocationOrigins(setting);
        String[] arr$ = GOOGLE_GEOLOCATION_ORIGINS;
        int len$ = arr$.length;
        for (int i$ = USE_LOCATION_FOR_SERVICES_OFF; i$ < len$; i$ += USE_LOCATION_FOR_SERVICES_ON) {
            origins.add(arr$[i$]);
        }
        return formatAllowGeolocationOrigins(origins);
    }

    private static String removeGoogleOrigins(String setting) {
        HashSet<String> origins = parseAllowGeolocationOrigins(setting);
        String[] arr$ = GOOGLE_GEOLOCATION_ORIGINS;
        int len$ = arr$.length;
        for (int i$ = USE_LOCATION_FOR_SERVICES_OFF; i$ < len$; i$ += USE_LOCATION_FOR_SERVICES_ON) {
            origins.remove(arr$[i$]);
        }
        return formatAllowGeolocationOrigins(origins);
    }

    private static HashSet<String> parseAllowGeolocationOrigins(String setting) {
        HashSet<String> origins = new HashSet();
        if (!TextUtils.isEmpty(setting)) {
            String[] arr$ = setting.split("\\s+");
            int len$ = arr$.length;
            for (int i$ = USE_LOCATION_FOR_SERVICES_OFF; i$ < len$; i$ += USE_LOCATION_FOR_SERVICES_ON) {
                String origin = arr$[i$];
                if (!TextUtils.isEmpty(origin)) {
                    origins.add(origin);
                }
            }
        }
        return origins;
    }

    private static String formatAllowGeolocationOrigins(Collection<String> origins) {
        StringBuilder sb = new StringBuilder();
        for (String origin : origins) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(origin);
        }
        return sb.toString();
    }
}
