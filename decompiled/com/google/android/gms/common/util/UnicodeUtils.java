package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
public class UnicodeUtils {
    private static final Pattern sEscapedUnicodePattern;

    static {
        sEscapedUnicodePattern = Pattern.compile("\\\\u[0-9a-fA-F]{4}");
    }

    public static String unescape(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        Matcher matcher = sEscapedUnicodePattern.matcher(text);
        StringBuffer sb = null;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuffer();
            }
            matcher.appendReplacement(sb, new String(Character.toChars(Integer.parseInt(matcher.group().substring(2), 16))));
        }
        if (sb == null) {
            return text;
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
