package com.google.android.gms.people.internal;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.lint.BuildConfig;

public class PeopleCallLog {
    private static final String TAG = "PeopleClientCall";

    public static boolean isEnabled() {
        return Log.isLoggable(TAG, 3);
    }

    public static void log(String method, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(method);
        sb.append("(");
        String separator = BuildConfig.VERSION_NAME;
        for (Object arg : args) {
            sb.append(separator);
            if (arg instanceof Bundle) {
                sb.append(PeopleUtils.writeBundle((Bundle) arg));
            } else {
                sb.append(arg);
            }
            separator = ", ";
        }
        sb.append(")");
        Log.d(TAG, sb.toString(), Log.isLoggable(TAG, 2) ? new Throwable("STACK TRACE (It's not crash!)") : null);
    }

    public static String toStringHelper(Object... namesAndArgs) {
        StringBuilder sb = new StringBuilder();
        Preconditions.checkArgument(namesAndArgs.length % 2 == 0);
        String sep = BuildConfig.VERSION_NAME;
        for (int i = 0; i < namesAndArgs.length; i += 2) {
            sb.append(sep);
            sb.append(namesAndArgs[i]);
            sb.append("=");
            sb.append(namesAndArgs[i + 1]);
            sep = ", ";
        }
        return sb.toString();
    }
}
