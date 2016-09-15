package com.google.android.snet;

import java.util.BitSet;

class BitSetUtils {
    BitSetUtils() {
    }

    static byte[] toByteArray(BitSet bitSet) {
        int bitCount = bitSet.length();
        byte[] result = new byte[((bitCount + 7) / 8)];
        for (int i = 0; i < bitCount; i++) {
            int arrayIndex = i / 8;
            result[arrayIndex] = (byte) (result[arrayIndex] | ((bitSet.get(i) ? 1 : 0) << (i % 8)));
        }
        return result;
    }
}
