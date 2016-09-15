package com.google.android.gms.common.util;

public final class RuntimeUtils {
    private RuntimeUtils() {
    }

    public static String createStackTrace() {
        StringBuilder sb = new StringBuilder();
        Throwable exception = new Throwable();
        StackTraceElement[] stack = exception.getStackTrace();
        sb.append("Async stack trace:");
        for (StackTraceElement element : stack) {
            sb.append("\n\tat ").append(element);
        }
        StackTraceElement[] parentStack = stack;
        for (Throwable throwable = exception.getCause(); throwable != null; throwable = throwable.getCause()) {
            sb.append("\nCaused by: ");
            sb.append(throwable);
            StackTraceElement[] currentStack = throwable.getStackTrace();
            int duplicates = countDuplicates(currentStack, parentStack);
            for (int i = 0; i < currentStack.length - duplicates; i++) {
                sb.append("\n\tat " + currentStack[i]);
            }
            if (duplicates > 0) {
                sb.append("\n\t... " + duplicates + " more");
            }
            parentStack = currentStack;
        }
        return sb.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int countDuplicates(java.lang.StackTraceElement[] r5, java.lang.StackTraceElement[] r6) {
        /*
        r0 = 0;
        r3 = r6.length;
        r1 = r5.length;
    L_0x0003:
        r1 = r1 + -1;
        if (r1 < 0) goto L_0x0018;
    L_0x0007:
        r3 = r3 + -1;
        if (r3 < 0) goto L_0x0018;
    L_0x000b:
        r2 = r6[r3];
        r4 = r5[r1];
        r4 = r2.equals(r4);
        if (r4 == 0) goto L_0x0018;
    L_0x0015:
        r0 = r0 + 1;
        goto L_0x0003;
    L_0x0018:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.RuntimeUtils.countDuplicates(java.lang.StackTraceElement[], java.lang.StackTraceElement[]):int");
    }
}
