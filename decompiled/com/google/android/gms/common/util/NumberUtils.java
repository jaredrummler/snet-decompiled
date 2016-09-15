package com.google.android.gms.common.util;

@VisibleForTesting
public class NumberUtils {
    private static final int MAX_HEX_DIGITS_FOR_LONG = 16;

    public static long parseHexLong(String value) {
        if (value.length() > MAX_HEX_DIGITS_FOR_LONG) {
            throw new NumberFormatException("Invalid input: " + value + " exceeds " + MAX_HEX_DIGITS_FOR_LONG + " characters");
        } else if (value.length() != MAX_HEX_DIGITS_FOR_LONG) {
            return Long.parseLong(value, MAX_HEX_DIGITS_FOR_LONG);
        } else {
            return (Long.parseLong(value.substring(0, 8), MAX_HEX_DIGITS_FOR_LONG) << 32) | Long.parseLong(value.substring(8), MAX_HEX_DIGITS_FOR_LONG);
        }
    }

    public static boolean isNumeric(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int compare(long x, long y) {
        if (x < y) {
            return -1;
        }
        return x == y ? 0 : 1;
    }

    public static int compare(int x, int y) {
        if (x < y) {
            return -1;
        }
        return x == y ? 0 : 1;
    }

    private NumberUtils() {
    }
}
