package com.google.android.gsf;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;

public final class GoogleSettingsContract {
    public static final String AUTHORITY = "com.google.settings";
    private static final String TAG = "GoogleSettings";

    public static class NameValueTable implements BaseColumns {
        public static final String NAME = "name";
        public static final String VALUE = "value";

        protected static boolean putString(ContentResolver resolver, Uri uri, String name, String value) {
            try {
                ContentValues values = new ContentValues();
                values.put(NAME, name);
                values.put(VALUE, value);
                resolver.insert(uri, values);
                return true;
            } catch (SQLException e) {
                Log.e(GoogleSettingsContract.TAG, "Can't set key " + name + " in " + uri, e);
                return false;
            } catch (IllegalArgumentException e2) {
                Log.e(GoogleSettingsContract.TAG, "Can't set key " + name + " in " + uri, e2);
                return false;
            }
        }

        public static Uri getUriFor(Uri uri, String name) {
            return Uri.withAppendedPath(uri, name);
        }
    }

    public static final class Partner extends NameValueTable {
        public static final String CHROME_CLIENT_ID = "chrome_client_id";
        public static final String CLIENT_ID = "client_id";
        public static final Uri CONTENT_URI;
        public static final String DATA_STORE_VERSION = "data_store_version";
        public static final String LOGGING_ID2 = "logging_id2";
        public static final String MAPS_CLIENT_ID = "maps_client_id";
        public static final String MARKET_CHECKIN = "market_checkin";
        public static final String MARKET_CLIENT_ID = "market_client_id";
        public static final String NETWORK_LOCATION_OPT_IN = "network_location_opt_in";
        public static final String RLZ = "rlz";
        public static final String SEARCH_CLIENT_ID = "search_client_id";
        public static final String SHOPPER_CLIENT_ID = "shopper_client_id";
        public static final String USE_LOCATION_FOR_ADS = "use_location_for_ads";
        public static final String USE_LOCATION_FOR_SERVICES = "use_location_for_services";
        public static final String VOICESEARCH_CLIENT_ID = "voicesearch_client_id";
        public static final String WALLET_CLIENT_ID = "wallet_client_id";
        public static final String YOUTUBE_CLIENT_ID = "youtube_client_id";

        public static String getString(ContentResolver resolver, String name) {
            String value = null;
            Cursor c = null;
            try {
                ContentResolver contentResolver = resolver;
                c = contentResolver.query(CONTENT_URI, new String[]{AccountSettingsColumns.VALUE}, "name=?", new String[]{name}, null);
                if (c != null && c.moveToNext()) {
                    value = c.getString(0);
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                Log.e(GoogleSettingsContract.TAG, "Can't get key " + name + " from " + CONTENT_URI, e);
                if (c != null) {
                    c.close();
                }
            } catch (Throwable th) {
                if (c != null) {
                    c.close();
                }
            }
            return value;
        }

        public static String getString(ContentResolver resolver, String name, String defaultValue) {
            String value = getString(resolver, name);
            if (value == null) {
                return defaultValue;
            }
            return value;
        }

        public static boolean putString(ContentResolver resolver, String name, String value) {
            return NameValueTable.putString(resolver, CONTENT_URI, name, value);
        }

        public static boolean putInt(ContentResolver resolver, String name, int value) {
            return putString(resolver, name, String.valueOf(value));
        }

        public static int getInt(ContentResolver resolver, String name, int defValue) {
            String valString = getString(resolver, name);
            if (valString == null) {
                return defValue;
            }
            try {
                return Integer.parseInt(valString);
            } catch (NumberFormatException e) {
                return defValue;
            }
        }

        public static long getLong(ContentResolver resolver, String name, long defValue) {
            String valString = getString(resolver, name);
            if (valString == null) {
                return defValue;
            }
            try {
                return Long.parseLong(valString);
            } catch (NumberFormatException e) {
                return defValue;
            }
        }

        public static Uri getUriFor(String name) {
            return NameValueTable.getUriFor(CONTENT_URI, name);
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.settings/partner");
        }
    }
}
