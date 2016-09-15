package com.google.android.gsf;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

public class HelpUrl {
    private static final String DEFAULT_HELP_URL = "https://support.google.com/mobile/?hl=%locale%";
    private static final String SMART_HELP_LINK_APP_VERSION = "version";
    private static final String SMART_HELP_LINK_PARAMETER_NAME = "p";
    private static final String TAG = "HelpUrl";

    public static Uri getHelpUrl(Context context, String fromWhere) {
        if (TextUtils.isEmpty(fromWhere)) {
            throw new IllegalArgumentException("getHelpUrl(): fromWhere must be non-empty");
        }
        Builder builder = Uri.parse(replaceLocale(Gservices.getString(context.getContentResolver(), GservicesKeys.CONTEXT_SENSITIVE_HELP_URL, DEFAULT_HELP_URL))).buildUpon();
        builder.appendQueryParameter(SMART_HELP_LINK_PARAMETER_NAME, fromWhere);
        try {
            builder.appendQueryParameter(SMART_HELP_LINK_APP_VERSION, String.valueOf(context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 0).versionCode));
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Error finding package " + context.getApplicationInfo().packageName);
        }
        return builder.build();
    }

    private static String replaceLocale(String str) {
        if (!str.contains("%locale%")) {
            return str;
        }
        Locale locale = Locale.getDefault();
        return str.replace("%locale%", locale.getLanguage() + "-" + locale.getCountry().toLowerCase());
    }
}
