package com.google.android.snet;

import com.google.android.snet.NetworkAnalyzer.SslRedirectInfo;
import com.google.android.snet.SslHandshakeAnalyzer.SslInfo;
import java.util.List;

class SuspiciousGoogleWebPageDetector {
    SuspiciousGoogleWebPageDetector() {
    }

    static boolean isSuspicious(List<SslInfo> sslHandshakeInfoList, List<SslRedirectInfo> sslRedirectInfoList) {
        if (sslHandshakeInfoList == null || sslRedirectInfoList == null) {
            return false;
        }
        boolean chainTrusted = false;
        for (SslInfo handshakeInfo : sslHandshakeInfoList) {
            if (handshakeInfo != null && "www.google.com".equals(handshakeInfo.host) && handshakeInfo.chainIsTrusted) {
                chainTrusted = true;
                break;
            }
        }
        if (!chainTrusted) {
            return false;
        }
        for (SslRedirectInfo redirectInfo : sslRedirectInfoList) {
            if (redirectInfo != null && "http://www.google.com".equals(redirectInfo.host) && redirectInfo.endPointConnected && redirectInfo.endPointUrl != null && redirectInfo.endPointUrl.startsWith("http://www.google.com")) {
                return true;
            }
        }
        return false;
    }
}
