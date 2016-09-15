package com.google.android.snet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

class SslHandshakeAnalyzer {
    private static final String CERT_FACTORY_TYPE = "X.509";
    private static final String RSA_ALGORITHM = "RSA";
    private static final int SSL_PORT = 443;
    private static final String SYSTEM_CA_STORE_PATH = "/system/etc/security/cacerts";
    private static final String SYSTEM_CA_STORE_PRE_ICS_PATH = "/system/etc/security/cacerts.bks";
    private static final String TAG;
    private static final String USER_ADDED_CA_STORE_PATH = "/data/misc/keychain/cacerts-added";
    private static Set<X509Certificate> sPreIcsCaStoreCache;
    private final SslHandshakeAnalyzerValues mAnalyzerValues;
    private final Context mContext;
    private final SnetLogger mSnetLogger;
    private List<SslInfo> mSslInfo;

    public static class CertPinInfo {
        public boolean chainIsTrusted;
        public boolean inCaStore;
        public boolean pinTestError;
        public boolean userAdded;
    }

    public static class SslInfo {
        public boolean certInCaStore;
        public boolean certUserAdded;
        public boolean chainIsTrusted;
        public boolean chainIsValid;
        public String cipherSuite;
        public boolean extendedKeyUsageVerified;
        public String host;
        public boolean hostnameVerificationSucceeded;
        public Certificate[] peerCertificates;
        public boolean pinTestError;
        public String protocol;
        public boolean sslPeerCertificatesRetrieved;
        public boolean sslSessionValid;
        public boolean sslSocketCreated;
        public List<Boolean> x509TrustManagerAcceptedConnection;
        public boolean x509TrustManagerExists;
    }

    private class TrustAllX509TrustManager implements X509TrustManager {
        private TrustAllX509TrustManager() {
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    static {
        TAG = SslHandshakeAnalyzer.class.getCanonicalName();
        sPreIcsCaStoreCache = new HashSet();
    }

    List<SslInfo> sslInfoList() {
        return this.mSslInfo;
    }

    SslHandshakeAnalyzer(Context context, SnetLogger snetLog, SslHandshakeAnalyzerValues analyzerValues) {
        this.mContext = context;
        this.mSnetLogger = snetLog;
        this.mAnalyzerValues = analyzerValues;
        this.mSslInfo = new ArrayList();
    }

    void analyzeSslHandshake() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            for (String host : this.mAnalyzerValues.getHostsList()) {
                SSLSocket sslSocket = null;
                try {
                    SslInfo sslInfo = new SslInfo();
                    sslInfo.host = host;
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    TrustManager trustAllX509TrustManager = new TrustAllX509TrustManager(null);
                    sslContext.init((KeyManager[]) null, new TrustManager[]{trustAllX509TrustManager}, (SecureRandom) null);
                    try {
                        sslSocket = (SSLSocket) sslContext.getSocketFactory().createSocket(host, SSL_PORT);
                        sslInfo.sslSocketCreated = true;
                        SSLSession sslSession = sslSocket.getSession();
                        if (sslSession == null || !sslSession.isValid()) {
                            sslInfo.sslSessionValid = false;
                            this.mSslInfo.add(sslInfo);
                            if (!(sslSocket == null || sslSocket.isClosed())) {
                                try {
                                    sslSocket.close();
                                } catch (IOException e) {
                                }
                            }
                        } else {
                            sslInfo.sslSessionValid = true;
                            sslInfo.protocol = sslSession.getProtocol();
                            sslInfo.cipherSuite = sslSession.getCipherSuite();
                            try {
                                Certificate[] peerCertificates = sslSession.getPeerCertificates();
                                if (peerCertificates == null || peerCertificates.length == 0) {
                                    sslInfo.sslPeerCertificatesRetrieved = false;
                                    this.mSslInfo.add(sslInfo);
                                    if (!(sslSocket == null || sslSocket.isClosed())) {
                                        try {
                                            sslSocket.close();
                                        } catch (IOException e2) {
                                        }
                                    }
                                } else {
                                    sslInfo.sslPeerCertificatesRetrieved = true;
                                    List<X509TrustManager> trustManagersList = findX509TrustManagers();
                                    sslInfo.x509TrustManagerAcceptedConnection = new ArrayList();
                                    sslInfo.x509TrustManagerExists = !trustManagersList.isEmpty();
                                    for (X509TrustManager trustManager : trustManagersList) {
                                        try {
                                            trustManager.checkServerTrusted((X509Certificate[]) peerCertificates, RSA_ALGORITHM);
                                            sslInfo.x509TrustManagerAcceptedConnection.add(Boolean.valueOf(true));
                                        } catch (CertificateException e3) {
                                            sslInfo.x509TrustManagerAcceptedConnection.add(Boolean.valueOf(false));
                                            sslInfo.peerCertificates = peerCertificates;
                                        }
                                    }
                                    if (HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sslSession)) {
                                        sslInfo.hostnameVerificationSucceeded = true;
                                        sslInfo.chainIsValid = validChain(peerCertificates);
                                        CertPinInfo certPinInfo = certPinInfo(peerCertificates);
                                        sslInfo.chainIsTrusted = certPinInfo.chainIsTrusted;
                                        sslInfo.pinTestError = certPinInfo.pinTestError;
                                        sslInfo.certUserAdded = certPinInfo.userAdded;
                                        sslInfo.certInCaStore = certPinInfo.inCaStore;
                                        sslInfo.extendedKeyUsageVerified = ekuContainsAcceptedOid(peerCertificates);
                                        if (!(sslInfo.chainIsValid && sslInfo.chainIsTrusted && sslInfo.extendedKeyUsageVerified)) {
                                            sslInfo.peerCertificates = peerCertificates;
                                        }
                                        this.mSslInfo.add(sslInfo);
                                        if (!(sslSocket == null || sslSocket.isClosed())) {
                                            try {
                                                sslSocket.close();
                                            } catch (IOException e4) {
                                            }
                                        }
                                    } else {
                                        sslInfo.hostnameVerificationSucceeded = false;
                                        sslInfo.peerCertificates = peerCertificates;
                                        this.mSslInfo.add(sslInfo);
                                        if (!(sslSocket == null || sslSocket.isClosed())) {
                                            try {
                                                sslSocket.close();
                                            } catch (IOException e5) {
                                            }
                                        }
                                    }
                                }
                            } catch (SSLPeerUnverifiedException e6) {
                                sslInfo.sslPeerCertificatesRetrieved = false;
                                this.mSslInfo.add(sslInfo);
                                if (!(sslSocket == null || sslSocket.isClosed())) {
                                    try {
                                        sslSocket.close();
                                    } catch (IOException e7) {
                                    }
                                }
                            }
                        }
                    } catch (IOException e8) {
                        sslInfo.sslSocketCreated = false;
                        this.mSslInfo.add(sslInfo);
                        if (!(sslSocket == null || sslSocket.isClosed())) {
                            try {
                                sslSocket.close();
                            } catch (IOException e9) {
                            }
                        }
                    }
                } catch (NoSuchAlgorithmException e10) {
                    this.mSnetLogger.writeException(e10);
                    if (!(sslSocket == null || sslSocket.isClosed())) {
                        try {
                            sslSocket.close();
                        } catch (IOException e11) {
                        }
                    }
                } catch (KeyManagementException e12) {
                    this.mSnetLogger.writeException(e12);
                    if (!(sslSocket == null || sslSocket.isClosed())) {
                        try {
                            sslSocket.close();
                        } catch (IOException e13) {
                        }
                    }
                } catch (Throwable th) {
                    if (!(sslSocket == null || sslSocket.isClosed())) {
                        try {
                            sslSocket.close();
                        } catch (IOException e14) {
                        }
                    }
                }
            }
        }
    }

    boolean validChain(Certificate[] peerCertificates) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance(CERT_FACTORY_TYPE);
            int certIndex = peerCertificates.length - 1;
            while (certIndex >= 0) {
                X509Certificate x509Cert = peerCertificates[certIndex];
                try {
                    x509Cert.checkValidity();
                    if (this.mAnalyzerValues.getWeakRsaAlgorithm().equals(x509Cert.getSigAlgName()) || ((RSAPublicKey) x509Cert.getPublicKey()).getModulus().bitLength() < this.mAnalyzerValues.getMinRsaModulusBits()) {
                        return false;
                    }
                    if (certIndex != peerCertificates.length - 1) {
                        X509Certificate parent = peerCertificates[certIndex + 1];
                        int pathLenConstraint = parent.getBasicConstraints();
                        int numCaCertsLeftInChain = certIndex;
                        if (pathLenConstraint == -1 || pathLenConstraint < numCaCertsLeftInChain) {
                            return false;
                        }
                        if (!certSignedByParent(x509Cert, parent)) {
                            return false;
                        }
                    }
                    certIndex--;
                } catch (CertificateExpiredException e) {
                    return false;
                } catch (CertificateNotYetValidException e2) {
                    return false;
                }
            }
            return true;
        } catch (CertificateException e3) {
            this.mSnetLogger.writeException(e3);
            return false;
        }
    }

    CertPinInfo certPinInfo(Certificate[] peerCertificates) {
        byte[] pinnedIntermediate = this.mAnalyzerValues.getPinnedIntermediate();
        CertPinInfo certPinInfo = new CertPinInfo();
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-512");
            for (int i = peerCertificates.length - 1; i >= 0; i--) {
                if (!certPinInfo.inCaStore) {
                    certPinInfo.inCaStore = issuerInCaStore(peerCertificates[i]);
                }
                if (!(certPinInfo.inCaStore || certPinInfo.userAdded)) {
                    certPinInfo.userAdded = issuerUserAdded(peerCertificates[i]);
                }
                if (Arrays.equals(digester.digest(peerCertificates[i].getPublicKey().getEncoded()), pinnedIntermediate)) {
                    certPinInfo.chainIsTrusted = true;
                }
            }
        } catch (NoSuchAlgorithmException e) {
            certPinInfo.pinTestError = true;
        }
        return certPinInfo;
    }

    boolean ekuContainsAcceptedOid(Certificate[] peerCertificates) {
        X509Certificate leaf = peerCertificates[0];
        try {
            Set<String> acceptedEkuOidsSet = this.mAnalyzerValues.getAcceptedEkuOidsSet();
            List<String> ekuOids = leaf.getExtendedKeyUsage();
            if (ekuOids == null) {
                return true;
            }
            for (String ekuOid : ekuOids) {
                if (acceptedEkuOidsSet.contains(ekuOid)) {
                    return true;
                }
            }
            return false;
        } catch (CertificateParsingException e) {
            return false;
        }
    }

    private List<X509TrustManager> findX509TrustManagers() {
        List<X509TrustManager> trustManagersList = new ArrayList();
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    trustManagersList.add((X509TrustManager) trustManager);
                }
            }
        } catch (KeyStoreException e) {
            this.mSnetLogger.writeException(e);
        } catch (NoSuchAlgorithmException e2) {
            this.mSnetLogger.writeException(e2);
        }
        return trustManagersList;
    }

    static boolean issuerInCaStore(Certificate cert) {
        if (VERSION.SDK_INT < 14) {
            return issuerInCaStorePreIcs(cert);
        }
        return issuerAnchoredInDirectory((X509Certificate) cert, new File(SYSTEM_CA_STORE_PATH));
    }

    static boolean issuerUserAdded(Certificate cert) {
        return issuerAnchoredInDirectory((X509Certificate) cert, new File(USER_ADDED_CA_STORE_PATH));
    }

    private static boolean issuerInCaStorePreIcs(Certificate cert) {
        Throwable th;
        for (X509Certificate cachedRoot : sPreIcsCaStoreCache) {
            if (certSignedByParent(cert, cachedRoot)) {
                return true;
            }
        }
        InputStream inputStream = null;
        try {
            KeyStore bksKeyStore = KeyStore.getInstance("BKS");
            InputStream inputStream2 = new FileInputStream(new File(SYSTEM_CA_STORE_PRE_ICS_PATH));
            try {
                bksKeyStore.load(inputStream2, null);
                Enumeration<String> aliases = bksKeyStore.aliases();
                while (aliases.hasMoreElements()) {
                    X509Certificate storedCert = (X509Certificate) bksKeyStore.getCertificate((String) aliases.nextElement());
                    if (certSignedByParent(cert, storedCert)) {
                        sPreIcsCaStoreCache.add(storedCert);
                        if (inputStream2 == null) {
                            return true;
                        }
                        try {
                            inputStream2.close();
                            return true;
                        } catch (IOException e) {
                            return true;
                        }
                    }
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e2) {
                    }
                }
                return false;
            } catch (KeyStoreException e3) {
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                    }
                }
                return false;
            } catch (IOException e5) {
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                    }
                }
                return false;
            } catch (NoSuchAlgorithmException e7) {
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e8) {
                    }
                }
                return false;
            } catch (CertificateException e9) {
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e10) {
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e11) {
                    }
                }
                throw th;
            }
        } catch (KeyStoreException e12) {
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (IOException e13) {
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (NoSuchAlgorithmException e14) {
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (CertificateException e15) {
            if (inputStream != null) {
                inputStream.close();
            }
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private static boolean issuerAnchoredInDirectory(X509Certificate cert, File directory) {
        Throwable th;
        String fileNamePrefix = String.format("%08x", new Object[]{Integer.valueOf(x509NameHashMd5((X500Principal) cert.getIssuerDN()))});
        int index = 0;
        while (true) {
            File certFile = new File(directory, fileNamePrefix + '.' + index);
            if (certFile.isFile()) {
                if (certFile.length() != 0) {
                    break;
                }
                index++;
            } else {
                return false;
            }
        }
        InputStream inputStream = null;
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance(CERT_FACTORY_TYPE);
            InputStream inputStream2 = new BufferedInputStream(new FileInputStream(certFile));
            try {
                X509Certificate storedCert = (X509Certificate) certFactory.generateCertificate(inputStream2);
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e) {
                    }
                }
                return certSignedByParent(cert, storedCert);
            } catch (IOException e2) {
                inputStream = inputStream2;
                if (inputStream != null) {
                    return false;
                }
                try {
                    inputStream.close();
                    return false;
                } catch (IOException e3) {
                    return false;
                }
            } catch (CertificateException e4) {
                inputStream = inputStream2;
                if (inputStream != null) {
                    return false;
                }
                try {
                    inputStream.close();
                    return false;
                } catch (IOException e5) {
                    return false;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStream2;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e6) {
                    }
                }
                throw th;
            }
        } catch (IOException e7) {
            if (inputStream != null) {
                return false;
            }
            inputStream.close();
            return false;
        } catch (CertificateException e8) {
            if (inputStream != null) {
                return false;
            }
            inputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private static boolean certSignedByParent(Certificate cert, Certificate parent) {
        Set<TrustAnchor> anchors = Collections.singleton(new TrustAnchor((X509Certificate) parent, null));
        try {
            CertPath certPath = CertificateFactory.getInstance(CERT_FACTORY_TYPE).generateCertPath(Arrays.asList(new Certificate[]{cert}));
            PKIXParameters params = new PKIXParameters(anchors);
            params.setRevocationEnabled(false);
            CertPathValidator.getInstance(CertPathValidator.getDefaultType()).validate(certPath, params);
            return true;
        } catch (NoSuchAlgorithmException e) {
            return false;
        } catch (CertificateException e2) {
            return false;
        } catch (InvalidAlgorithmParameterException e3) {
            return false;
        } catch (CertPathValidatorException e4) {
            return false;
        }
    }

    private static int x509NameHashMd5(X500Principal principal) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(principal.getEncoded());
            int offset = 0 + 1;
            int offset2 = offset + 1;
            return ((((digest[0] & MotionEventCompat.ACTION_MASK) << 0) | ((digest[offset] & MotionEventCompat.ACTION_MASK) << 8)) | ((digest[offset2] & MotionEventCompat.ACTION_MASK) << 16)) | ((digest[offset2 + 1] & MotionEventCompat.ACTION_MASK) << 24);
        } catch (NoSuchAlgorithmException e) {
            return 0;
        }
    }
}
