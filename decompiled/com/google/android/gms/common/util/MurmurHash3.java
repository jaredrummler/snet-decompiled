package com.google.android.gms.common.util;

public class MurmurHash3 {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int murmurhash3_x86_32(byte[] r10, int r11, int r12, int r13) {
        /*
        r9 = 461845907; // 0x1b873593 float:2.2368498E-22 double:2.281821963E-315;
        r8 = -862048943; // 0xffffffffcc9e2d51 float:-8.2930312E7 double:NaN;
        r0 = -862048943; // 0xffffffffcc9e2d51 float:-8.2930312E7 double:NaN;
        r1 = 461845907; // 0x1b873593 float:2.2368498E-22 double:2.281821963E-315;
        r2 = r13;
        r6 = r12 & -4;
        r5 = r11 + r6;
        r3 = r11;
    L_0x0012:
        if (r3 >= r5) goto L_0x004b;
    L_0x0014:
        r6 = r10[r3];
        r6 = r6 & 255;
        r7 = r3 + 1;
        r7 = r10[r7];
        r7 = r7 & 255;
        r7 = r7 << 8;
        r6 = r6 | r7;
        r7 = r3 + 2;
        r7 = r10[r7];
        r7 = r7 & 255;
        r7 = r7 << 16;
        r6 = r6 | r7;
        r7 = r3 + 3;
        r7 = r10[r7];
        r7 = r7 << 24;
        r4 = r6 | r7;
        r4 = r4 * r8;
        r6 = r4 << 15;
        r7 = r4 >>> 17;
        r4 = r6 | r7;
        r4 = r4 * r9;
        r2 = r2 ^ r4;
        r6 = r2 << 13;
        r7 = r2 >>> 19;
        r2 = r6 | r7;
        r6 = r2 * 5;
        r7 = -430675100; // 0xffffffffe6546b64 float:-2.5078068E23 double:NaN;
        r2 = r6 + r7;
        r3 = r3 + 4;
        goto L_0x0012;
    L_0x004b:
        r4 = 0;
        r6 = r12 & 3;
        switch(r6) {
            case 1: goto L_0x0075;
            case 2: goto L_0x006c;
            case 3: goto L_0x0064;
            default: goto L_0x0051;
        };
    L_0x0051:
        r2 = r2 ^ r12;
        r6 = r2 >>> 16;
        r2 = r2 ^ r6;
        r6 = -2048144789; // 0xffffffff85ebca6b float:-2.217365E-35 double:NaN;
        r2 = r2 * r6;
        r6 = r2 >>> 13;
        r2 = r2 ^ r6;
        r6 = -1028477387; // 0xffffffffc2b2ae35 float:-89.34025 double:NaN;
        r2 = r2 * r6;
        r6 = r2 >>> 16;
        r2 = r2 ^ r6;
        return r2;
    L_0x0064:
        r6 = r5 + 2;
        r6 = r10[r6];
        r6 = r6 & 255;
        r4 = r6 << 16;
    L_0x006c:
        r6 = r5 + 1;
        r6 = r10[r6];
        r6 = r6 & 255;
        r6 = r6 << 8;
        r4 = r4 | r6;
    L_0x0075:
        r6 = r10[r5];
        r6 = r6 & 255;
        r4 = r4 | r6;
        r4 = r4 * r8;
        r6 = r4 << 15;
        r7 = r4 >>> 17;
        r4 = r6 | r7;
        r4 = r4 * r9;
        r2 = r2 ^ r4;
        goto L_0x0051;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.MurmurHash3.murmurhash3_x86_32(byte[], int, int, int):int");
    }

    private MurmurHash3() {
    }
}
