package com.google.android.gms.common;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

public class GoogleSignatureVerifier {
    private static final GoogleSignatureVerifier INSTANCE;
    private static final String TAG = "GoogleSignatureVerifier";

    static {
        INSTANCE = new GoogleSignatureVerifier();
    }

    private GoogleSignatureVerifier() {
    }

    public static GoogleSignatureVerifier getInstance() {
        return INSTANCE;
    }

    public void verifyUidIsGoogleSigned(PackageManager packageManager, int uid) throws SecurityException {
        if (packageManager == null) {
            throw new SecurityException("Unknown error: invalid Package Manager");
        } else if (!isUidGoogleSigned(packageManager, uid)) {
            throw new SecurityException("Uid is not Google Signed");
        }
    }

    public boolean isUidGoogleSigned(PackageManager packageManager, int uid) {
        String[] packages = packageManager.getPackagesForUid(uid);
        if (packages == null || packages.length == 0) {
            return false;
        }
        for (String curPackage : packages) {
            if (isPackageGoogleSigned(packageManager, curPackage)) {
                return true;
            }
        }
        return false;
    }

    public void verifyPackageIsGoogleSigned(PackageManager packageManager, String callingPackage) throws SecurityException {
        if (!isPackageGoogleSigned(packageManager, callingPackage)) {
            throw new SecurityException("Signature check failed for " + callingPackage);
        }
    }

    public boolean isPackageGoogleSigned(PackageManager packageManager, String callingPackage) {
        try {
            return isPackageGoogleSigned(packageManager, packageManager.getPackageInfo(callingPackage, 64));
        } catch (NameNotFoundException e) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Package manager can't find package " + callingPackage + ", defaulting to false");
            }
            return false;
        }
    }

    public boolean isPackageGoogleSigned(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (GooglePlayServicesUtilLight.honorsDebugCertificates(packageManager)) {
            return verifyGoogleSignature(packageInfo, true);
        }
        boolean okay = verifyGoogleSignature(packageInfo, false);
        if (okay || !verifyGoogleSignature(packageInfo, true)) {
            return okay;
        }
        Log.w(TAG, "Test-keys aren't accepted on this build.");
        return okay;
    }

    public Set<byte[]> getAllGoogleSignatures(boolean includeDebugCerts) {
        Set<CertData> certificates;
        if (includeDebugCerts) {
            certificates = GoogleCertificates.getGoogleCertificates();
        } else {
            certificates = GoogleCertificates.getGoogleReleaseCertificates();
        }
        Set<byte[]> signatures = new HashSet(certificates.size());
        for (CertData certData : certificates) {
            signatures.add(certData.getBytes());
        }
        return signatures;
    }

    public boolean isGooglePublicSignedPackage(PackageInfo packageInfo, boolean allowTestKeys) {
        if (!(packageInfo == null || packageInfo.signatures == null)) {
            CertData verifySignature;
            if (allowTestKeys) {
                verifySignature = verifySignature(packageInfo, VALID_PUBLIC_SIGNATURES.KEYS);
            } else {
                verifySignature = verifySignature(packageInfo, VALID_PUBLIC_SIGNATURES.KEYS[0]);
            }
            if (verifySignature != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isGooglePublicSignedPackage(PackageManager packageManager, PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (GooglePlayServicesUtilLight.honorsDebugCertificates(packageManager)) {
            return isGooglePublicSignedPackage(packageInfo, true);
        }
        boolean okay = isGooglePublicSignedPackage(packageInfo, false);
        if (okay || !isGooglePublicSignedPackage(packageInfo, true)) {
            return okay;
        }
        Log.w(TAG, "Test-keys aren't accepted on this build.");
        return okay;
    }

    private boolean verifyGoogleSignature(PackageInfo packageInfo, boolean allowTestKeys) {
        if (packageInfo.signatures.length != 1) {
            Log.w(TAG, "Package has more than one signature.");
            return false;
        }
        Set<CertData> certificates;
        CertData signature = new FullCertData(packageInfo.signatures[0].toByteArray());
        if (allowTestKeys) {
            certificates = GoogleCertificates.getGoogleCertificates();
        } else {
            certificates = GoogleCertificates.getGoogleReleaseCertificates();
        }
        if (certificates.contains(signature)) {
            return true;
        }
        if (!Log.isLoggable(TAG, 2)) {
            return false;
        }
        Log.v(TAG, "Signature not valid.  Found: \n" + Base64.encodeToString(signature.getBytes(), 0));
        return false;
    }

    CertData verifySignature(PackageInfo packageInfo, CertData... allValidSignatures) {
        if (packageInfo.signatures.length != 1) {
            Log.w(TAG, "Package has more than one signature.");
            return null;
        }
        CertData signature = new FullCertData(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < allValidSignatures.length; i++) {
            if (allValidSignatures[i].equals(signature)) {
                return allValidSignatures[i];
            }
        }
        if (!Log.isLoggable(TAG, 2)) {
            return null;
        }
        Log.v(TAG, "Signature not valid.  Found: \n" + Base64.encodeToString(signature.getBytes(), 0));
        return null;
    }
}
