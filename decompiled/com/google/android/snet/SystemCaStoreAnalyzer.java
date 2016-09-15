package com.google.android.snet;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.snet.IdleLogs.SystemCaStoreWhitelist;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SystemCaStoreAnalyzer {
    private static final String SYSTEM_CA_STORE_PATH = "/system/etc/security/cacerts";
    private static final String SYSTEM_CA_STORE_PRE_ICS_PATH = "/system/etc/security/cacerts.bks";
    private static final String TAG;
    private final Context mContext;
    private final GBundle mGBundle;
    private Set<Bytes> mRequestedCertsBySha256Set;
    private Set<Bytes> mSpkiSha256WhitelistSet;

    static class CaCertInfo {
        byte[] cert;
        String issuerDn;
        byte[] sha256;
        byte[] spkiSha256;
        String subjectDn;

        CaCertInfo() {
        }
    }

    static {
        TAG = SystemCaStoreAnalyzer.class.getCanonicalName();
    }

    SystemCaStoreAnalyzer(Context context, GBundle gBundle) {
        this.mGBundle = gBundle;
        this.mContext = context;
        this.mRequestedCertsBySha256Set = requestedCertsBySha256Set();
        this.mSpkiSha256WhitelistSet = spkiSha256Whitelist();
    }

    List<CaCertInfo> getSystemCaCerts(int numCerts) {
        if (numCerts <= 0) {
            return null;
        }
        if (VERSION.SDK_INT < 14) {
            return getSystemCaCertsPreIcs(numCerts);
        }
        return getSystemCaCertsNew(numCerts);
    }

    private List<CaCertInfo> getSystemCaCertsPreIcs(int numCerts) {
        List<CaCertInfo> list;
        Throwable th;
        InputStream inputStream = null;
        try {
            KeyStore bksKeyStore = KeyStore.getInstance("BKS");
            InputStream inputStream2 = new FileInputStream(new File(SYSTEM_CA_STORE_PRE_ICS_PATH));
            try {
                bksKeyStore.load(inputStream2, null);
                if (bksKeyStore.size() == 0) {
                    list = null;
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e) {
                        }
                    }
                    inputStream = inputStream2;
                } else {
                    List<Integer> intList = getRandomIndicesPermutation(bksKeyStore.size(), bksKeyStore.size());
                    Enumeration<String> aliases = bksKeyStore.aliases();
                    list = new ArrayList();
                    int numFullRecordsLogged = 0;
                    while (aliases.hasMoreElements()) {
                        X509Certificate cert = (X509Certificate) bksKeyStore.getCertificate((String) aliases.nextElement());
                        CaCertInfo certInfo = new CaCertInfo();
                        if (!this.mSpkiSha256WhitelistSet.isEmpty()) {
                            certInfo.spkiSha256 = compressSha256(Utils.getSha256(cert.getPublicKey().getEncoded()));
                        }
                        byte[] derEncodedCertSha256 = derEncodedCertSha256(cert);
                        if (numFullRecordsLogged < numCerts && certInfo.spkiSha256 != null && certInfo.spkiSha256.length > 4) {
                            numFullRecordsLogged++;
                            if (this.mGBundle.getLogEntireSystemCaCert()) {
                                certInfo.cert = cert.getEncoded();
                            } else {
                                certInfo.subjectDn = cert.getSubjectX500Principal().toString();
                                certInfo.issuerDn = cert.getIssuerX500Principal().toString();
                                certInfo.sha256 = derEncodedCertSha256;
                            }
                        }
                        list.add(certInfo);
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e2) {
                        }
                    }
                    inputStream = inputStream2;
                }
            } catch (KeyStoreException e3) {
                inputStream = inputStream2;
            } catch (IOException e4) {
                inputStream = inputStream2;
            } catch (NoSuchAlgorithmException e5) {
                inputStream = inputStream2;
            } catch (CertificateException e6) {
                inputStream = inputStream2;
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStream2;
            }
        } catch (KeyStoreException e7) {
            list = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e8) {
                }
            }
            return list;
        } catch (IOException e9) {
            list = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e10) {
                }
            }
            return list;
        } catch (NoSuchAlgorithmException e11) {
            list = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e12) {
                }
            }
            return list;
        } catch (CertificateException e13) {
            list = null;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e14) {
                }
            }
            return list;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e15) {
                }
            }
            throw th;
        }
        return list;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.google.android.snet.SystemCaStoreAnalyzer.CaCertInfo> getSystemCaCertsNew(int r17) {
        /*
        r16 = this;
        r13 = new java.io.File;
        r14 = "/system/etc/security/cacerts";
        r13.<init>(r14);
        r14 = r13.exists();
        if (r14 == 0) goto L_0x0013;
    L_0x000d:
        r14 = r13.isDirectory();
        if (r14 != 0) goto L_0x0015;
    L_0x0013:
        r5 = 0;
    L_0x0014:
        return r5;
    L_0x0015:
        r12 = r13.listFiles();
        r14 = r12.length;
        if (r14 != 0) goto L_0x001e;
    L_0x001c:
        r5 = 0;
        goto L_0x0014;
    L_0x001e:
        r14 = r12.length;
        r15 = r12.length;
        r0 = r16;
        r10 = r0.getRandomIndicesPermutation(r14, r15);
        r2 = 0;
        r14 = "X.509";
        r2 = java.security.cert.CertificateFactory.getInstance(r14);	 Catch:{ CertificateException -> 0x00a3 }
        r5 = new java.util.ArrayList;
        r5.<init>();
        r11 = 0;
        r7 = 0;
    L_0x0034:
        r14 = r12.length;
        if (r7 >= r14) goto L_0x0014;
    L_0x0037:
        r8 = 0;
        r14 = r10.get(r7);	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r14 = (java.lang.Integer) r14;	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r14 = r14.intValue();	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r3 = r12[r14];	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r4 = new com.google.android.snet.SystemCaStoreAnalyzer$CaCertInfo;	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r4.<init>();	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r9 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r14 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r14.<init>(r3);	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r9.<init>(r14);	 Catch:{ IOException -> 0x00e9, CertificateException -> 0x00d1, all -> 0x00da }
        r1 = r2.generateCertificate(r9);	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r1 = (java.security.cert.X509Certificate) r1;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r0 = r16;
        r14 = r0.mSpkiSha256WhitelistSet;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r14.isEmpty();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        if (r14 != 0) goto L_0x0077;
    L_0x0063:
        r14 = r1.getPublicKey();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r14.getEncoded();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = com.google.android.snet.Utils.getSha256(r14);	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r0 = r16;
        r14 = r0.compressSha256(r14);	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r4.spkiSha256 = r14;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
    L_0x0077:
        r0 = r17;
        if (r11 >= r0) goto L_0x0097;
    L_0x007b:
        r14 = r4.spkiSha256;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        if (r14 == 0) goto L_0x0097;
    L_0x007f:
        r14 = r4.spkiSha256;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r14.length;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r15 = 4;
        if (r14 <= r15) goto L_0x0097;
    L_0x0085:
        r11 = r11 + 1;
        r0 = r16;
        r14 = r0.mGBundle;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r14.getLogEntireSystemCaCert();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        if (r14 == 0) goto L_0x00a7;
    L_0x0091:
        r14 = r1.getEncoded();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r4.cert = r14;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
    L_0x0097:
        r5.add(r4);	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        if (r9 == 0) goto L_0x00eb;
    L_0x009c:
        r9.close();	 Catch:{ IOException -> 0x00ce }
        r8 = r9;
    L_0x00a0:
        r7 = r7 + 1;
        goto L_0x0034;
    L_0x00a3:
        r6 = move-exception;
        r5 = 0;
        goto L_0x0014;
    L_0x00a7:
        r14 = r1.getIssuerX500Principal();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r14.toString();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r4.issuerDn = r14;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r1.getSubjectX500Principal();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r14 = r14.toString();	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r4.subjectDn = r14;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r0 = r16;
        r14 = r0.derEncodedCertSha256(r1);	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        r4.sha256 = r14;	 Catch:{ IOException -> 0x00c4, CertificateException -> 0x00e6, all -> 0x00e3 }
        goto L_0x0097;
    L_0x00c4:
        r14 = move-exception;
        r8 = r9;
    L_0x00c6:
        if (r8 == 0) goto L_0x00a0;
    L_0x00c8:
        r8.close();	 Catch:{ IOException -> 0x00cc }
        goto L_0x00a0;
    L_0x00cc:
        r14 = move-exception;
        goto L_0x00a0;
    L_0x00ce:
        r14 = move-exception;
        r8 = r9;
        goto L_0x00a0;
    L_0x00d1:
        r14 = move-exception;
    L_0x00d2:
        if (r8 == 0) goto L_0x00a0;
    L_0x00d4:
        r8.close();	 Catch:{ IOException -> 0x00d8 }
        goto L_0x00a0;
    L_0x00d8:
        r14 = move-exception;
        goto L_0x00a0;
    L_0x00da:
        r14 = move-exception;
    L_0x00db:
        if (r8 == 0) goto L_0x00e0;
    L_0x00dd:
        r8.close();	 Catch:{ IOException -> 0x00e1 }
    L_0x00e0:
        throw r14;
    L_0x00e1:
        r15 = move-exception;
        goto L_0x00e0;
    L_0x00e3:
        r14 = move-exception;
        r8 = r9;
        goto L_0x00db;
    L_0x00e6:
        r14 = move-exception;
        r8 = r9;
        goto L_0x00d2;
    L_0x00e9:
        r14 = move-exception;
        goto L_0x00c6;
    L_0x00eb:
        r8 = r9;
        goto L_0x00a0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.SystemCaStoreAnalyzer.getSystemCaCertsNew(int):java.util.List<com.google.android.snet.SystemCaStoreAnalyzer$CaCertInfo>");
    }

    private byte[] derEncodedCertSha256(X509Certificate cert) {
        byte[] bArr = null;
        try {
            bArr = MessageDigest.getInstance("SHA-256").digest(cert.getEncoded());
        } catch (NoSuchAlgorithmException e) {
        } catch (CertificateEncodingException e2) {
        }
        return bArr;
    }

    private Set<Bytes> requestedCertsBySha256Set() {
        Set<Bytes> requestedCertsBySha256Set = new HashSet();
        String requestedCertsBySha256 = this.mGBundle.getRequestedCertsBySha256();
        if (!TextUtils.isEmpty(requestedCertsBySha256)) {
            for (String hexStringSha256 : requestedCertsBySha256.split(Csv.COMMA)) {
                requestedCertsBySha256Set.add(new Bytes(Utils.hexStringToByteArray(hexStringSha256)));
            }
        }
        return requestedCertsBySha256Set;
    }

    private Set<Bytes> spkiSha256Whitelist() {
        Set<Bytes> whitelist = new HashSet();
        int spkiWhitelistResId = this.mGBundle.getSpkiWhitelistResId();
        if (spkiWhitelistResId != 0) {
            byte[] whitelistRawProtoBytes = Utils.readInputStream(this.mContext.getResources().openRawResource(spkiWhitelistResId));
            if (whitelistRawProtoBytes != null) {
                try {
                    SystemCaStoreWhitelist whitelistProto = SystemCaStoreWhitelist.parseFrom(whitelistRawProtoBytes);
                    if (whitelistProto.subjectPublicKeyInfoSha256 != null) {
                        for (byte[] spkiHash : whitelistProto.subjectPublicKeyInfoSha256) {
                            whitelist.add(new Bytes(spkiHash));
                        }
                    }
                } catch (InvalidProtocolBufferNanoException e) {
                }
            }
        }
        return whitelist;
    }

    private byte[] compressSha256(byte[] sha256) {
        if (sha256 == null) {
            return null;
        }
        if (this.mSpkiSha256WhitelistSet.contains(new Bytes(sha256))) {
            return Arrays.copyOfRange(sha256, 0, 4);
        }
        return sha256;
    }

    private void generateWhitelist(List<CaCertInfo> caCertInfoList) {
        SystemCaStoreWhitelist whitelistProto = new SystemCaStoreWhitelist();
        whitelistProto.subjectPublicKeyInfoSha256 = new byte[caCertInfoList.size()][];
        for (int i = 0; i < caCertInfoList.size(); i++) {
            whitelistProto.subjectPublicKeyInfoSha256[i] = ((CaCertInfo) caCertInfoList.get(i)).spkiSha256;
        }
        Utils.writeBytes(MessageNano.toByteArray(whitelistProto), new File(new File(this.mContext.getApplicationInfo().dataDir, "/snet"), "debug_whitelist"));
    }

    List<Integer> getRandomIndicesPermutation(int size, int subsetSize) {
        List<Integer> intList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            intList.add(Integer.valueOf(i));
        }
        Collections.shuffle(intList);
        return intList.subList(0, subsetSize);
    }
}
