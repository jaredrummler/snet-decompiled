package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.snet.Csv;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting
public final class JsonUtils {
    private static final Pattern sEscapedJsonCharacterPattern;
    private static final Pattern sUnescapedJsonCharacterPattern;

    static {
        sEscapedJsonCharacterPattern = Pattern.compile("\\\\.");
        sUnescapedJsonCharacterPattern = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    private JsonUtils() {
    }

    public static String unescapeString(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        String output = UnicodeUtils.unescape(text);
        Matcher matcher = sEscapedJsonCharacterPattern.matcher(output);
        StringBuffer sb = null;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuffer();
            }
            switch (matcher.group().charAt(1)) {
                case LogSource.NOVA /*34*/:
                    matcher.appendReplacement(sb, "\"");
                    break;
                case LogSource.SOCIAL_WEB /*47*/:
                    matcher.appendReplacement(sb, "/");
                    break;
                case LogSource.DISK_STATS /*92*/:
                    matcher.appendReplacement(sb, "\\\\");
                    break;
                case LogSource.TACHYON_LOG_REQUEST /*98*/:
                    matcher.appendReplacement(sb, "\b");
                    break;
                case LogSource.LAUNCHPAD_TOYS /*102*/:
                    matcher.appendReplacement(sb, "\f");
                    break;
                case LogSource.GMM_UE3 /*110*/:
                    matcher.appendReplacement(sb, Csv.NEWLINE);
                    break;
                case LogSource.TRANSOM /*114*/:
                    matcher.appendReplacement(sb, "\r");
                    break;
                case LogSource.LB_AS /*116*/:
                    matcher.appendReplacement(sb, "\t");
                    break;
                default:
                    throw new IllegalStateException("Found an escaped character that should never be.");
            }
        }
        if (sb == null) {
            return output;
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String escapeString(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        Matcher matcher = sUnescapedJsonCharacterPattern.matcher(text);
        StringBuffer sb = null;
        while (matcher.find()) {
            if (sb == null) {
                sb = new StringBuffer();
            }
            switch (matcher.group().charAt(0)) {
                case Type.TAP_GET_LINK /*8*/:
                    matcher.appendReplacement(sb, "\\\\b");
                    break;
                case Type.CREATE_LINK /*9*/:
                    matcher.appendReplacement(sb, "\\\\t");
                    break;
                case Type.TAP_ABOUT /*10*/:
                    matcher.appendReplacement(sb, "\\\\n");
                    break;
                case Type.SWITCH_ACCOUNT /*12*/:
                    matcher.appendReplacement(sb, "\\\\f");
                    break;
                case Type.DISPLAY_ERROR /*13*/:
                    matcher.appendReplacement(sb, "\\\\r");
                    break;
                case LogSource.NOVA /*34*/:
                    matcher.appendReplacement(sb, "\\\\\\\"");
                    break;
                case LogSource.SOCIAL_WEB /*47*/:
                    matcher.appendReplacement(sb, "\\\\/");
                    break;
                case LogSource.DISK_STATS /*92*/:
                    matcher.appendReplacement(sb, "\\\\\\\\");
                    break;
                default:
                    break;
            }
        }
        if (sb == null) {
            return text;
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static boolean areJsonStringsEquivalent(String expectedJson, String actualJson) {
        boolean z = false;
        if (expectedJson == null && actualJson == null) {
            return true;
        }
        if (expectedJson == null || actualJson == null) {
            return z;
        }
        try {
            return areJsonValuesEquivalent(new JSONObject(expectedJson), new JSONObject(actualJson));
        } catch (JSONException e) {
            try {
                return areJsonValuesEquivalent(new JSONArray(expectedJson), new JSONArray(actualJson));
            } catch (JSONException e2) {
                return z;
            }
        }
    }

    public static boolean areJsonValuesEquivalent(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if ((a instanceof JSONObject) && (b instanceof JSONObject)) {
            JSONObject objectA = (JSONObject) a;
            JSONObject objectB = (JSONObject) b;
            if (objectA.length() != objectB.length()) {
                return false;
            }
            Iterator<?> iter = objectA.keys();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                if (!objectB.has(key)) {
                    return false;
                }
                try {
                    if (!areJsonValuesEquivalent(objectA.get(key), objectB.get(key))) {
                        return false;
                    }
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } else if (!(a instanceof JSONArray) || !(b instanceof JSONArray)) {
            return a.equals(b);
        } else {
            JSONArray arrayA = (JSONArray) a;
            JSONArray arrayB = (JSONArray) b;
            if (arrayA.length() != arrayB.length()) {
                return false;
            }
            int i = 0;
            while (i < arrayA.length()) {
                try {
                    if (!areJsonValuesEquivalent(arrayA.get(i), arrayB.get(i))) {
                        return false;
                    }
                    i++;
                } catch (JSONException e2) {
                    return false;
                }
            }
            return true;
        }
    }
}
