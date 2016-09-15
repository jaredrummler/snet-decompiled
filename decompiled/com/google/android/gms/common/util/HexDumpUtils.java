package com.google.android.gms.common.util;

import android.support.v4.view.MotionEventCompat;
import java.io.ByteArrayOutputStream;
import java.util.StringTokenizer;

public final class HexDumpUtils {
    private static final int BYTES_PER_LINE = 16;

    public static String dump(byte[] data, int offset, int length, boolean outputText) {
        if (data == null || data.length == 0 || offset < 0 || length <= 0 || offset + length > data.length) {
            return null;
        }
        int lineLength = 57;
        if (outputText) {
            lineLength = 57 + 18;
        }
        StringBuilder sb = new StringBuilder(lineLength * (((length + BYTES_PER_LINE) - 1) / BYTES_PER_LINE));
        int col = 0;
        int lineStart = 0;
        int remaining = length;
        int i = offset;
        while (remaining > 0) {
            if (col == 0) {
                lineStart = i;
                if (length < 65536) {
                    sb.append(String.format("%04X:", new Object[]{Integer.valueOf(i)}));
                } else {
                    sb.append(String.format("%08X:", new Object[]{Integer.valueOf(i)}));
                }
            } else if (col == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", new Object[]{Integer.valueOf(data[i] & MotionEventCompat.ACTION_MASK)}));
            remaining--;
            col++;
            if (outputText && (col == BYTES_PER_LINE || remaining == 0)) {
                int j;
                int pad = 16 - col;
                if (pad > 0) {
                    for (j = 0; j < pad; j++) {
                        sb.append("   ");
                    }
                }
                if (pad >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (j = 0; j < col; j++) {
                    char c = (char) data[lineStart + j];
                    if (c < ' ' || c > '~') {
                        c = '.';
                    }
                    sb.append(c);
                }
            }
            if (col == BYTES_PER_LINE || remaining == 0) {
                sb.append('\n');
                col = 0;
            }
            i++;
        }
        return sb.toString();
    }

    public static byte[] bytesFromString(String hexDump) {
        StringTokenizer st = new StringTokenizer(hexDump, " \t\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        while (st.hasMoreTokens()) {
            try {
                out.write(Integer.parseInt(st.nextToken(), BYTES_PER_LINE) & MotionEventCompat.ACTION_MASK);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return out.toByteArray();
    }
}
