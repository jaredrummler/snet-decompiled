package com.google.android.snet;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class AttestationClient {
    private static final String KEY_ATTEST_BUNDLE_DATA = "data";
    private static final String KEY_ATTEST_BUNDLE_ERROR_MESSAGE = "errorMsg";
    private static final String KEY_ATTEST_BUNDLE_STATUS_CODE = "statusCode";
    private static final String TAG;

    static class AttestationInfo {
        byte[] certChainSha256;
        String data;
        String errorMsg;
        String signature;
        int statusCode;

        AttestationInfo() {
        }
    }

    AttestationClient() {
    }

    static {
        TAG = AttestationClient.class.getCanonicalName();
    }

    static AttestationInfo attest(Bundle outputBundle) {
        AttestationInfo info = new AttestationInfo();
        String errorMsg = outputBundle.getString(KEY_ATTEST_BUNDLE_ERROR_MESSAGE);
        if (TextUtils.isEmpty(errorMsg)) {
            info.statusCode = outputBundle.getInt(KEY_ATTEST_BUNDLE_STATUS_CODE);
            if (info.statusCode == 0) {
                String[] parts = outputBundle.getString(KEY_ATTEST_BUNDLE_DATA).split("[.]");
                if (parts.length != 3) {
                    info.errorMsg = "Bad JWS format";
                } else {
                    try {
                        info.certChainSha256 = MessageDigest.getInstance("SHA-256").digest(parts[0].getBytes(Charset.forName("UTF-8")));
                        info.data = new String(Base64.decode(parts[1], 8));
                        info.signature = parts[2];
                    } catch (NoSuchAlgorithmException e) {
                        info.errorMsg = "Missing SHA-256";
                    }
                }
            }
        } else {
            info.errorMsg = errorMsg;
        }
        return info;
    }
}
