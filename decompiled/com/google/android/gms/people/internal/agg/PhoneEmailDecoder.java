package com.google.android.gms.people.internal.agg;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gms.people.model.EmailAddress;
import com.google.android.gms.people.model.PhoneNumber;
import java.util.ArrayList;
import java.util.regex.Pattern;

public abstract class PhoneEmailDecoder<T> {
    public static EmailDecoder DummyEmailDecoder = null;
    public static PhoneDecoder DummyPhoneDecoder = null;
    private static final String TAG = "PhoneEmailDecoder";
    private final char mSep1;
    private final String mSep1StrPattern;
    private final char mSep2;
    private final String mSep2StrPattern;
    private final Bundle mTypeLabelMap;

    public static class EmailDecoder extends PhoneEmailDecoder<EmailAddress> {
        @VisibleForTesting
        EmailDecoder(Bundle typeLabelMap, char sep1, char sep2) {
            super(typeLabelMap, sep1, sep2);
        }

        public EmailDecoder(Bundle typeLabelMap) {
            super(typeLabelMap, '\u0001', '\u0002');
        }

        protected EmailAddress build(String value, String label, double affinity1, double affinity2, double affinity3, double affinity4, double affinity5, String loggingId1, String loggingId2, String loggingId3, String loggingId4, String loggingId5) {
            return new EmailAddressImpl(label, value, affinity1, affinity2, affinity3, affinity4, affinity5, loggingId1, loggingId2, loggingId3, loggingId4, loggingId5);
        }
    }

    public static class PhoneDecoder extends PhoneEmailDecoder<PhoneNumber> {
        @VisibleForTesting
        PhoneDecoder(Bundle typeLabelMap, char sep1, char sep2) {
            super(typeLabelMap, sep1, sep2);
        }

        public PhoneDecoder(Bundle typeLabelMap) {
            super(typeLabelMap, '\u0001', '\u0002');
        }

        protected PhoneNumber build(String value, String label, double affinity1, double affinity2, double affinity3, double affinity4, double affinity5, String loggingId1, String loggingId2, String loggingId3, String loggingId4, String loggingId5) {
            return new PhoneNumberImpl(label, value);
        }
    }

    protected abstract T build(String str, String str2, double d, double d2, double d3, double d4, double d5, String str3, String str4, String str5, String str6, String str7);

    PhoneEmailDecoder(Bundle typeLabelMap, char sep1, char sep2) {
        this.mTypeLabelMap = typeLabelMap;
        this.mSep1 = sep1;
        this.mSep2 = sep2;
        this.mSep1StrPattern = Pattern.quote(String.valueOf(this.mSep1));
        this.mSep2StrPattern = Pattern.quote(String.valueOf(this.mSep2));
    }

    private static int indexOfInRange(String s, char ch, int start, int last) {
        int found = s.indexOf(ch, start);
        return (found < 0 || found >= last) ? -1 : found;
    }

    public final ArrayList<T> decode(String s, boolean hasAffinities) {
        ArrayList<T> result = new ArrayList();
        if (!TextUtils.isEmpty(s)) {
            if (hasAffinities) {
                decodeWithAffinities(result, s);
            } else {
                decodeWithoutAffinities(result, s);
            }
        }
        return result;
    }

    private final void decodeWithoutAffinities(ArrayList<T> result, String s) {
        int currentPos = 0;
        int len = s.length();
        while (currentPos < len) {
            int sep2pos = s.indexOf(this.mSep2, currentPos);
            int currentLast = sep2pos >= 0 ? sep2pos : s.length();
            int firstSep1 = indexOfInRange(s, this.mSep1, currentPos, currentLast);
            int secondSep1 = indexOfInRange(s, this.mSep1, firstSep1 + 1, currentLast);
            if (firstSep1 >= 0 && secondSep1 >= 0) {
                append(result, s.substring(currentPos, firstSep1), s.substring(firstSep1 + 1, secondSep1), s.substring(secondSep1 + 1, currentLast), 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, null, null, null, null, null);
            }
            currentPos = currentLast + 1;
        }
    }

    private final void decodeWithAffinities(ArrayList<T> result, String s) {
        for (String item : TextUtils.split(s, this.mSep2StrPattern)) {
            String[] values = TextUtils.split(item, this.mSep1StrPattern);
            if (values.length < 13) {
                PeopleServiceLog.w(TAG, "Invalid string");
            } else {
                int index = 0 + 1;
                String str = values[0];
                int index2 = index + 1;
                String str2 = values[index];
                index = index2 + 1;
                String str3 = values[index2];
                index2 = index + 1;
                double parseDouble = parseDouble(values[index]);
                index = index2 + 1;
                double parseDouble2 = parseDouble(values[index2]);
                index2 = index + 1;
                double parseDouble3 = parseDouble(values[index]);
                index = index2 + 1;
                double parseDouble4 = parseDouble(values[index2]);
                index2 = index + 1;
                double parseDouble5 = parseDouble(values[index]);
                index = index2 + 1;
                String emptyToNull = PeopleUtils.emptyToNull(values[index2]);
                index2 = index + 1;
                String emptyToNull2 = PeopleUtils.emptyToNull(values[index]);
                index = index2 + 1;
                String emptyToNull3 = PeopleUtils.emptyToNull(values[index2]);
                index2 = index + 1;
                String emptyToNull4 = PeopleUtils.emptyToNull(values[index]);
                index = index2 + 1;
                append(result, str, str2, str3, parseDouble, parseDouble2, parseDouble3, parseDouble4, parseDouble5, emptyToNull, emptyToNull2, emptyToNull3, emptyToNull4, PeopleUtils.emptyToNull(values[index2]));
            }
        }
    }

    private static double parseDouble(String s) {
        double d = 0.0d;
        if (!TextUtils.isEmpty(s)) {
            try {
                d = Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                PeopleServiceLog.e(TAG, "NumberFormatException", nfe);
            }
        }
        return d;
    }

    private void append(ArrayList<T> list, String typeString, String customLabel, String value, double affinity1, double affinity2, double affinity3, double affinity4, double affinity5, String loggingId1, String loggingId2, String loggingId3, String loggingId4, String loggingId5) {
        if (!TextUtils.isEmpty(value)) {
            String type = this.mTypeLabelMap.getString(typeString);
            list.add(build(value, TextUtils.isEmpty(type) ? customLabel : type, affinity1, affinity2, affinity3, affinity4, affinity5, loggingId1, loggingId2, loggingId3, loggingId4, loggingId5));
        }
    }

    static {
        DummyPhoneDecoder = new PhoneDecoder(Bundle.EMPTY);
        DummyEmailDecoder = new EmailDecoder(Bundle.EMPTY);
    }
}
