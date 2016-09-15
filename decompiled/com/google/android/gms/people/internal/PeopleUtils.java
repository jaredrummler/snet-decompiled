package com.google.android.gms.people.internal;

import android.content.Context;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.android.snet.Csv;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent.Type;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class PeopleUtils {
    public static final String ACL_ENTITY_TYPE_FACL_ALL_CIRCLES = "allCircles";
    public static final String ACL_ENTITY_TYPE_FACL_ALL_CONTACTS = "allContacts";
    public static final String AUDIENCE_GROUP_DOMAIN = "domain";
    public static final String AUDIENCE_GROUP_EXTENDED = "extendedCircles";
    public static final String AUDIENCE_GROUP_PUBLIC = "public";
    public static final String AUDIENCE_GROUP_YOUR_CIRCLES = "myCircles";
    public static final String AUDIENCE_TYPE_CIRCLE = "circle";
    public static final String AUDIENCE_TYPE_PERSON = "person";
    @VisibleForTesting
    public static final String CLOCK_SERVICE_NAME = "gms.people.clock";
    public static final String EMAIL_QUALIFIER = "e:";
    public static Iterable<?> EMPTY_ITERABLE = null;
    public static final String[] EMPTY_STRINGS;
    public static final String GAIA_ID_QUALIFIER = "g:";
    public static final Map<String, Integer> MAP_CIRCLE_TYPE;
    public static final String PLUS_DATA_SET = "plus";
    public static final SecureRandom RANDOM;
    @VisibleForTesting
    public static final String RANDOM_SERVICE_NAME = "gms.people.random";
    public static final Pattern REGEX_COMMA;
    public static final Pattern REGEX_WHITESPACE;
    public static final char SEP_1 = '\u0001';
    public static final String SEP_1_CC = "||\"\u0001\"||";
    public static final String SEP_1_DQ = "\"\u0001\"";
    public static final Pattern SEP_1_RE;
    public static final String SEP_1_STR;
    public static final char SEP_2 = '\u0002';
    public static final String SEP_2_CC = "||\"\u0002\"||";
    public static final String SEP_2_DQ = "\"\u0002\"";
    public static final Pattern SEP_2_RE;
    public static final String SEP_2_STR;
    @VisibleForTesting
    public static final String TEST_CONTEXT_TAG = "gms.people.is_test_context";
    private static final String TRANSIENT_ETAG_PREFIX = "xtag:";
    private static final String WHITESPACE = "\u2028\u2029 \u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\t\u000b\f\u001c\u001d\u001e\u001f\n\r";
    public static final Handler sMainHandler;
    private static MessageDigest sMd5;
    private static final ThreadLocal<StringBuilder> sTemporaryStringBuilder;
    private static final ThreadLocal<String[]> sTemporaryStrings1;
    private static final ThreadLocal<String[]> sTemporaryStrings2;
    private static final ThreadLocal<String[]> sTemporaryStrings3;
    private static final ThreadLocal<String[]> sTemporaryStrings4;
    private static final ThreadLocal<String[]> sTemporaryStrings5;

    public static class StackTrace extends Exception {
        public StackTrace() {
            super("Stacktrace (IT'S NOT CRASH)");
        }
    }

    static {
        MAP_CIRCLE_TYPE = new HashMap();
        MAP_CIRCLE_TYPE.put(AUDIENCE_TYPE_CIRCLE, Integer.valueOf(-1));
        MAP_CIRCLE_TYPE.put(AUDIENCE_GROUP_EXTENDED, Integer.valueOf(4));
        MAP_CIRCLE_TYPE.put(AUDIENCE_GROUP_YOUR_CIRCLES, Integer.valueOf(3));
        MAP_CIRCLE_TYPE.put(AUDIENCE_GROUP_DOMAIN, Integer.valueOf(2));
        MAP_CIRCLE_TYPE.put(AUDIENCE_GROUP_PUBLIC, Integer.valueOf(1));
        MAP_CIRCLE_TYPE.put(null, Integer.valueOf(-2));
        EMPTY_ITERABLE = new EmptyIterable();
        sMainHandler = new Handler(Looper.getMainLooper());
        EMPTY_STRINGS = new String[0];
        REGEX_COMMA = Pattern.compile("\\,");
        REGEX_WHITESPACE = Pattern.compile("[\u2028\u2029 \u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\t\u000b\f\u001c\u001d\u001e\u001f\n\r]+");
        SEP_1_RE = Pattern.compile(Pattern.quote(String.valueOf(SEP_1)));
        SEP_2_RE = Pattern.compile(Pattern.quote(String.valueOf(SEP_2)));
        SEP_1_STR = String.valueOf(SEP_1);
        SEP_2_STR = String.valueOf(SEP_2);
        RANDOM = new SecureRandom();
        sTemporaryStringBuilder = new ThreadLocal<StringBuilder>() {
            protected StringBuilder initialValue() {
                return new StringBuilder();
            }
        };
        sTemporaryStrings1 = new ThreadLocal<String[]>() {
            protected String[] initialValue() {
                return new String[1];
            }
        };
        sTemporaryStrings2 = new ThreadLocal<String[]>() {
            protected String[] initialValue() {
                return new String[2];
            }
        };
        sTemporaryStrings3 = new ThreadLocal<String[]>() {
            protected String[] initialValue() {
                return new String[3];
            }
        };
        sTemporaryStrings4 = new ThreadLocal<String[]>() {
            protected String[] initialValue() {
                return new String[4];
            }
        };
        sTemporaryStrings5 = new ThreadLocal<String[]>() {
            protected String[] initialValue() {
                return new String[5];
            }
        };
    }

    private PeopleUtils() {
    }

    public static String trim(String s) {
        return s == null ? s : s.replaceAll("^[\u2028\u2029 \u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\t\u000b\f\u001c\u001d\u001e\u001f\n\r]*", BuildConfig.VERSION_NAME).replaceAll("[\u2028\u2029 \u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\t\u000b\f\u001c\u001d\u001e\u001f\n\r]*$", BuildConfig.VERSION_NAME);
    }

    public static String nullToEmpty(String s) {
        return s != null ? s : BuildConfig.VERSION_NAME;
    }

    public static String emptyToNull(String s) {
        return (s == null || s.length() == 0) ? null : s;
    }

    public static String[] commaSplit(String s) {
        if (TextUtils.isEmpty(s)) {
            return EMPTY_STRINGS;
        }
        return REGEX_COMMA.split(s, 0);
    }

    public static String signature(String s) {
        if (s == null) {
            return s;
        }
        if (sMd5 == null) {
            sMd5 = getMd5();
            if (sMd5 == null) {
                return "(unavailable)";
            }
        }
        byte[] h = sMd5.digest(s.getBytes());
        return String.format("%04x", new Object[]{Integer.valueOf((((i(h[0]) << 24) | (i(h[1]) << 16)) | (i(h[2]) << 8)) | i(h[3]))});
    }

    @VisibleForTesting
    static MessageDigest getMd5() {
        MessageDigest md5 = null;
        int numAttempts = 0;
        while (numAttempts < 2) {
            try {
                md5 = MessageDigest.getInstance("MD5");
                break;
            } catch (NoSuchAlgorithmException e) {
                numAttempts++;
            }
        }
        return md5;
    }

    private static int i(byte b) {
        return b & MotionEventCompat.ACTION_MASK;
    }

    public static String peopleQualifiedIdToGaiaId(String qualifiedId) {
        if (qualifiedId == null || !qualifiedId.startsWith(GAIA_ID_QUALIFIER)) {
            return null;
        }
        return qualifiedId.substring(GAIA_ID_QUALIFIER.length());
    }

    public static String gaiaIdToPeopleQualifiedId(String gaiaId) {
        Preconditions.checkNotNull(gaiaId);
        return GAIA_ID_QUALIFIER + gaiaId;
    }

    public static String peopleQualifiedIdToEmailAddress(String qualifiedId) {
        if (qualifiedId == null || !qualifiedId.startsWith(EMAIL_QUALIFIER)) {
            return null;
        }
        return qualifiedId.substring(EMAIL_QUALIFIER.length());
    }

    public static void checkGaiaId(String gaiaId, String paramName) {
        Preconditions.checkNotEmpty(gaiaId, paramName);
        boolean z = (gaiaId.startsWith(GAIA_ID_QUALIFIER) || gaiaId.startsWith(EMAIL_QUALIFIER)) ? false : true;
        Preconditions.checkArgument(z, paramName + ": Expecting gaia-id, not qualified-id");
    }

    public static void checkQualifiedId(String qualifiedId, String paramName) {
        Preconditions.checkNotEmpty(qualifiedId, paramName);
        boolean z = qualifiedId.startsWith(GAIA_ID_QUALIFIER) || qualifiedId.startsWith(EMAIL_QUALIFIER);
        Preconditions.checkArgument(z, paramName + ": Expecting qualified-id, not gaia-id");
    }

    public static void checkQualifiedIdIsGaiaId(String qualifiedId, String paramName) {
        Preconditions.checkNotEmpty(qualifiedId, paramName);
        Preconditions.checkArgument(qualifiedId.startsWith(GAIA_ID_QUALIFIER), paramName + ": Expecting gaia-id based qualified-id");
    }

    public static String emailToPeopleQualifiedId(String email) {
        Preconditions.checkNotEmpty(email);
        return EMAIL_QUALIFIER + email;
    }

    public static String collectionToSQLSelection(Collection<String> collection) {
        boolean needsComma = false;
        StringBuilder sb = getTemporaryStringBuilder();
        for (String member : collection) {
            if (needsComma) {
                sb.append(", ");
            }
            DatabaseUtils.appendEscapedSQLString(sb, member);
            needsComma = true;
        }
        return sb.toString();
    }

    @VisibleForTesting
    public static boolean isPhoneNumber(String text) {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            switch (ch) {
                case LogSource.SOCIAL /*32*/:
                case LogSource.LB_CA /*35*/:
                case LogSource.COPRESENCE /*40*/:
                case LogSource.SOCIAL_AFFINITY /*41*/:
                case LogSource.PHOTOS /*42*/:
                case LogSource.GCM /*43*/:
                case LogSource.FINDR /*45*/:
                case LogSource.SOCIAL_WEB /*47*/:
                case LogSource.BACKDROP /*48*/:
                case LogSource.TELEMATICS /*49*/:
                case LogSource.HANGOUT_LOG_REQUEST /*50*/:
                case LogSource.GVC_HARVESTER /*51*/:
                case LogSource.LB_IA /*52*/:
                case LogSource.CAR /*53*/:
                case LogSource.PIXEL_PERFECT /*54*/:
                case LogSource.DRIVE /*55*/:
                case LogSource.DOCS /*56*/:
                case LogSource.SHEETS /*57*/:
                    break;
                default:
                    if (!(Character.isWhitespace(ch) || Character.isDigit(ch))) {
                        return false;
                    }
            }
        }
        return true;
    }

    @VisibleForTesting
    public static boolean isWhiteSpace(char ch) {
        switch (ch) {
            case Type.CREATE_LINK /*9*/:
            case Type.TAP_ABOUT /*10*/:
            case Type.TAP_LEARN_MORE /*11*/:
            case Type.SWITCH_ACCOUNT /*12*/:
            case Type.DISPLAY_ERROR /*13*/:
            case LogSource.GEL /*28*/:
            case LogSource.DNA_PROBER /*29*/:
            case LogSource.UDR /*30*/:
            case LogSource.GMS_CORE_WALLET /*31*/:
            case LogSource.SOCIAL /*32*/:
            case '\u00a0':
            case '\u1680':
            case '\u180e':
            case PeopleColumnBitmask.FAMILY_NAME /*8192*/:
            case '\u2001':
            case InputDeviceCompat.SOURCE_MOUSE /*8194*/:
            case '\u2003':
            case '\u2004':
            case '\u2005':
            case '\u2006':
            case '\u2007':
            case '\u2008':
            case '\u2009':
            case '\u200a':
            case '\u2028':
            case '\u2029':
            case '\u202f':
            case '\u205f':
            case '\u3000':
                return true;
            default:
                return false;
        }
    }

    @VisibleForTesting
    public static String normalizePhoneNumber(String text) {
        StringBuilder sb = getTemporaryStringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(Character.digit(ch, 10));
            }
        }
        return sb.toString();
    }

    @VisibleForTesting
    public static boolean isEmailAddress(String toCheck) {
        return toCheck.contains("@");
    }

    public static boolean isQualifiedIdFromEmail(String qualifiedId) {
        return qualifiedId != null && qualifiedId.startsWith(EMAIL_QUALIFIER);
    }

    public static boolean isQualifiedIdFromGaia(String qualifiedId) {
        return qualifiedId != null && qualifiedId.startsWith(GAIA_ID_QUALIFIER);
    }

    public static boolean isQualifiedId(String qualifiedId) {
        return isQualifiedIdFromEmail(qualifiedId) || isQualifiedIdFromGaia(qualifiedId);
    }

    public static String escapeForLike(String input) {
        if (input == null) {
            return null;
        }
        int i;
        int len = input.length();
        boolean found = false;
        for (i = 0; i < len; i++) {
            switch (input.charAt(i)) {
                case LogSource.PERSONAL_BROWSER_LOGGER /*37*/:
                case LogSource.DISK_STATS /*92*/:
                case LogSource.A11YLOGGER /*95*/:
                    found = true;
                    break;
                default:
                    break;
            }
        }
        if (!found) {
            return input;
        }
        StringBuilder sb = len < 100 ? getTemporaryStringBuilder() : new StringBuilder();
        for (i = 0; i < len; i++) {
            char ch = input.charAt(i);
            switch (ch) {
                case LogSource.PERSONAL_BROWSER_LOGGER /*37*/:
                case LogSource.DISK_STATS /*92*/:
                case LogSource.A11YLOGGER /*95*/:
                    sb.append('\\');
                    break;
                default:
                    break;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static Clock getClock(Context context) {
        Clock injected = (Clock) context.getSystemService(CLOCK_SERVICE_NAME);
        return injected != null ? injected : DefaultClock.getInstance();
    }

    public static boolean isTestContext(Context context) {
        return context.getSystemService(TEST_CONTEXT_TAG) != null;
    }

    public static Random getRandom(Context context) {
        Random injected = (Random) context.getSystemService(RANDOM_SERVICE_NAME);
        return injected != null ? injected : RANDOM;
    }

    public static String getTransientEtag(Context context) {
        return TRANSIENT_ETAG_PREFIX + getClock(context).currentTimeMillis();
    }

    public static String[] removeEmptyElements(String[] array) {
        boolean emptyFound = false;
        for (CharSequence isEmpty : array) {
            if (TextUtils.isEmpty(isEmpty)) {
                emptyFound = true;
                break;
            }
        }
        if (!emptyFound) {
            return array;
        }
        ArrayList<String> list = new ArrayList(array.length);
        for (String s : array) {
            if (!TextUtils.isEmpty(s)) {
                list.add(s);
            }
        }
        return (String[]) list.toArray(EMPTY_STRINGS);
    }

    public static String stripLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        return s.substring(i);
    }

    public static StringBuilder getTemporaryStringBuilder() {
        StringBuilder ret = (StringBuilder) sTemporaryStringBuilder.get();
        ret.setLength(0);
        return ret;
    }

    public static String[] getTemporaryStrings1() {
        return getTemporaryStrings1(null);
    }

    public static String[] getTemporaryStrings1(String s1) {
        String[] ret = (String[]) sTemporaryStrings1.get();
        ret[0] = s1;
        return ret;
    }

    public static String[] getTemporaryStrings2() {
        return getTemporaryStrings2(null, null);
    }

    public static String[] getTemporaryStrings2(String s1, String s2) {
        String[] ret = (String[]) sTemporaryStrings2.get();
        ret[0] = s1;
        ret[1] = s2;
        return ret;
    }

    public static String[] getTemporaryStrings3() {
        return getTemporaryStrings3(null, null, null);
    }

    public static String[] getTemporaryStrings3(String s1, String s2, String s3) {
        String[] ret = (String[]) sTemporaryStrings3.get();
        ret[0] = s1;
        ret[1] = s2;
        ret[2] = s3;
        return ret;
    }

    public static String[] getTemporaryStrings4() {
        return getTemporaryStrings4(null, null, null, null);
    }

    @VisibleForTesting
    public static String[] getTemporaryStrings4(String s1, String s2, String s3, String s4) {
        String[] ret = (String[]) sTemporaryStrings4.get();
        ret[0] = s1;
        ret[1] = s2;
        ret[2] = s3;
        ret[3] = s4;
        return ret;
    }

    @VisibleForTesting
    public static String[] getTemporaryStrings5() {
        return getTemporaryStrings5(null, null, null, null, null);
    }

    @VisibleForTesting
    public static String[] getTemporaryStrings5(String s1, String s2, String s3, String s4, String s5) {
        String[] ret = (String[]) sTemporaryStrings5.get();
        ret[0] = s1;
        ret[1] = s2;
        ret[2] = s3;
        ret[3] = s4;
        ret[4] = s5;
        return ret;
    }

    public static String[] getTemporaryStrings(int n) {
        if (n == 1) {
            return getTemporaryStrings1();
        }
        if (n == 2) {
            return getTemporaryStrings2();
        }
        if (n == 3) {
            return getTemporaryStrings3();
        }
        if (n == 4) {
            return getTemporaryStrings4();
        }
        if (n == 5) {
            return getTemporaryStrings5();
        }
        return new String[n];
    }

    public static String writeBundle(Bundle bundle) {
        return writeBundleValue(bundle, BuildConfig.VERSION_NAME, new StringBuilder()).toString();
    }

    private static StringBuilder writeBundleValue(Object value, String indent, StringBuilder out) {
        if (value == null) {
            out.append("[null]\n");
        } else {
            String subIndent = indent + "  ";
            out.append("(").append(value.getClass().getSimpleName()).append(") ");
            if (value instanceof Bundle) {
                Bundle bundle = (Bundle) value;
                if (bundle.isEmpty()) {
                    out.append("{ }").append(Csv.NEWLINE);
                } else {
                    out.append("{\n");
                    for (String key : bundle.keySet()) {
                        out.append(subIndent).append(key).append(" : ");
                        writeBundleValue(bundle.get(key), subIndent, out);
                    }
                    out.append(indent).append("}\n");
                }
            } else if (value instanceof DataHolder) {
                DataHolder holder = (DataHolder) value;
                out.append(" [");
                if (holder.isClosed()) {
                    out.append("CLOSED");
                } else {
                    out.append(holder.getCount());
                }
                out.append("] ").append(value).append(Csv.NEWLINE);
            } else if (value instanceof ArrayList) {
                ArrayList<?> list = (ArrayList) value;
                if (list.isEmpty()) {
                    out.append("[ ]\n");
                } else {
                    out.append("[\n");
                    for (i = 0; i < list.size(); i++) {
                        out.append(subIndent).append(i).append(" : ");
                        writeBundleValue(list.get(i), subIndent, out);
                    }
                    out.append(indent).append("]\n");
                }
            } else if (value instanceof byte[]) {
                int length = ((byte[]) value).length;
                out.append(" [").append(length).append("] ");
                byte[] snippet = new byte[Math.min(length, 56)];
                System.arraycopy(value, 0, snippet, 0, snippet.length);
                out.append(Base64.encodeToString(snippet, 0));
            } else if (value instanceof char[]) {
                out.append("\"").append(new String((char[]) value)).append("\"\n");
            } else if (value.getClass().isArray()) {
                if (Array.getLength(value) == 0) {
                    out.append("[ ]\n");
                } else {
                    out.append("[ ");
                    out.append(Array.get(value, 0));
                    for (i = 1; i < Array.getLength(value); i++) {
                        out.append(", ").append(Array.get(value, i));
                    }
                    out.append(" ]\n");
                }
            } else if (value instanceof String) {
                out.append("\"").append(value).append("\"\n");
            } else {
                out.append(value).append(Csv.NEWLINE);
            }
        }
        return out;
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean areNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (!TextUtils.isEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <T> List<T> mergeLists(List<T>... lists) {
        List<T> mergedList = new ArrayList();
        if (lists != null) {
            for (List<T> list : lists) {
                if (!isNullOrEmpty(list)) {
                    mergedList.addAll(list);
                }
            }
        }
        return mergedList;
    }
}
