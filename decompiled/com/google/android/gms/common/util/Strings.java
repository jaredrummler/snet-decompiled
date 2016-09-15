package com.google.android.gms.common.util;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.CharMatcher;
import com.google.android.gms.lint.BuildConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public class Strings {
    private static final Pattern FORMAT_PATTERN;

    private Strings() {
    }

    static {
        FORMAT_PATTERN = Pattern.compile("\\$\\{(.*?)\\}");
    }

    public static String format(String spec, Bundle values) {
        Matcher match = FORMAT_PATTERN.matcher(spec);
        if (!match.find()) {
            return spec;
        }
        StringBuffer buffer = new StringBuffer();
        do {
            String key = match.group(1);
            Object value = values.get(key);
            if (value != null) {
                match.appendReplacement(buffer, value.toString());
            } else if (values.containsKey(key)) {
                match.appendReplacement(buffer, "null");
            } else {
                match.appendReplacement(buffer, BuildConfig.VERSION_NAME);
            }
        } while (match.find());
        match.appendTail(buffer);
        return buffer.toString();
    }

    public static String nullToEmpty(String string) {
        return string == null ? BuildConfig.VERSION_NAME : string;
    }

    public static String emptyToNull(String string) {
        return TextUtils.isEmpty(string) ? null : string;
    }

    public static String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        char first = s.charAt(0);
        char capitalized = Character.toUpperCase(first);
        return first != capitalized ? capitalized + s.substring(1) : s;
    }

    public static boolean isEmptyOrWhitespace(String string) {
        return string == null || CharMatcher.WHITESPACE.matchesAllOf(string);
    }
}
