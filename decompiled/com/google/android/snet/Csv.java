package com.google.android.snet;

public class Csv {
    public static final String COMMA = ",";
    public static final String NEWLINE = "\n";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean parseLine(java.io.BufferedReader r10, java.util.List<java.lang.String> r11) throws java.io.IOException {
        /*
        r6 = 1;
        r9 = 34;
        r8 = -1;
        r4 = r10.readLine();
        if (r4 != 0) goto L_0x000c;
    L_0x000a:
        r5 = 0;
    L_0x000b:
        return r5;
    L_0x000c:
        r2 = 0;
    L_0x000d:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
    L_0x0012:
        r5 = 44;
        r1 = r4.indexOf(r5, r2);
        r3 = r4.indexOf(r9, r2);
        if (r3 == r8) goto L_0x0022;
    L_0x001e:
        if (r1 == r8) goto L_0x0038;
    L_0x0020:
        if (r1 >= r3) goto L_0x0038;
    L_0x0022:
        if (r1 != r8) goto L_0x0074;
    L_0x0024:
        r5 = r4.length();
    L_0x0028:
        r0.append(r4, r2, r5);
        r5 = r0.toString();
        r11.add(r5);
        r2 = r1 + 1;
        if (r2 > 0) goto L_0x000d;
    L_0x0036:
        r5 = r6;
        goto L_0x000b;
    L_0x0038:
        if (r2 <= 0) goto L_0x0045;
    L_0x003a:
        r5 = r2 + -1;
        r5 = r4.charAt(r5);
        if (r5 != r9) goto L_0x0045;
    L_0x0042:
        r0.append(r9);
    L_0x0045:
        r0.append(r4, r2, r3);
    L_0x0048:
        r2 = r3 + 1;
        r3 = r4.indexOf(r9, r2);
        if (r3 != r8) goto L_0x006e;
    L_0x0050:
        r5 = r4.length();
        r5 = r0.append(r4, r2, r5);
        r7 = 10;
        r5.append(r7);
        r4 = r10.readLine();
        if (r4 != 0) goto L_0x006c;
    L_0x0063:
        r5 = r0.toString();
        r11.add(r5);
        r5 = r6;
        goto L_0x000b;
    L_0x006c:
        r3 = -1;
        goto L_0x0048;
    L_0x006e:
        r0.append(r4, r2, r3);
        r2 = r3 + 1;
        goto L_0x0012;
    L_0x0074:
        r5 = r1;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.Csv.parseLine(java.io.BufferedReader, java.util.List):boolean");
    }

    private Csv() {
    }
}
