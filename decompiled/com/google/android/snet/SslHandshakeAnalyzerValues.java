package com.google.android.snet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SslHandshakeAnalyzerValues {
    private static final Set<String> ACCEPTED_EKU_OIDS;
    private static final String EKU_ANY = "2.5.29.37.0";
    private static final String EKU_MS_SGC = "1.3.6.1.4.1.311.10.3.3";
    private static final String EKU_NS_SGC = "2.16.840.1.113730.4.1";
    private static final String EKU_SERVER_AUTH = "1.3.6.1.5.5.7.3.1";
    private static final int MIN_RSA_MODULUS_BITS = 2048;
    private static final byte[] PINNED_INTERMEDIATE;
    private static final List<String> SSL_HANDSHAKE_HOSTS;
    private static final String WEAK_RSA_ALGORITHM = "MD5withRSA";

    SslHandshakeAnalyzerValues() {
    }

    static {
        SSL_HANDSHAKE_HOSTS = Arrays.asList(new String[]{"accounts.google.com", "www.google.com", "pubads.g.doubleclick.net"});
        PINNED_INTERMEDIATE = new byte[]{(byte) -111, (byte) 34, (byte) -82, (byte) 88, (byte) -18, (byte) 125, (byte) 21, (byte) 125, (byte) -106, (byte) 86, (byte) -19, (byte) -88, (byte) 53, (byte) 57, (byte) -47, (byte) -36, (byte) 26, (byte) -118, MxRecordDnsMessageTool.MX_QTYPE, (byte) -69, (byte) 104, (byte) -18, (byte) 8, (byte) 8, (byte) 55, (byte) 63, (byte) -37, (byte) 83, (byte) -71, (byte) -119, (byte) 94, (byte) 48, (byte) 24, (byte) 14, (byte) 118, (byte) -102, (byte) -10, (byte) -77, (byte) -117, (byte) 12, Byte.MIN_VALUE, (byte) 40, (byte) -53, (byte) -27, (byte) 69, (byte) -34, (byte) 61, (byte) -122, (byte) -105, (byte) -44, (byte) -25, (byte) -1, (byte) 26, (byte) -82, (byte) 8, (byte) -62, (byte) 123, (byte) -3, (byte) -69, (byte) -87, (byte) -122, (byte) 107, (byte) -117, (byte) 4};
        ACCEPTED_EKU_OIDS = new HashSet(Arrays.asList(new String[]{EKU_ANY, EKU_SERVER_AUTH, EKU_NS_SGC, EKU_MS_SGC}));
    }

    List<String> getHostsList() {
        return SSL_HANDSHAKE_HOSTS;
    }

    byte[] getPinnedIntermediate() {
        return PINNED_INTERMEDIATE;
    }

    Set<String> getAcceptedEkuOidsSet() {
        return ACCEPTED_EKU_OIDS;
    }

    String getWeakRsaAlgorithm() {
        return WEAK_RSA_ALGORITHM;
    }

    int getMinRsaModulusBits() {
        return MIN_RSA_MODULUS_BITS;
    }
}
