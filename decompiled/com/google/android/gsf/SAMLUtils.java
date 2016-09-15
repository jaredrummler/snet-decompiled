package com.google.android.gsf;

import android.webkit.CookieManager;
import com.google.android.gms.lint.BuildConfig;

public class SAMLUtils {
    private static final String DEFAULT_HOSTED_BASE_PATH = "https://www.google.com";
    private static final String HOSTED_PREFIX = "/a/";
    private static final String TEST_GAIA_HOSTED_BASE_PATH = "http://dasher-qa.corp.google.com";

    private SAMLUtils() {
    }

    private static final String getHostedBaseUrl(boolean useTestGaia) {
        return (useTestGaia ? TEST_GAIA_HOSTED_BASE_PATH : DEFAULT_HOSTED_BASE_PATH) + HOSTED_PREFIX;
    }

    private static String makeHostedGaiaBasePath(boolean useTestGaia, String domainName) {
        return getHostedBaseUrl(useTestGaia) + domainName + "/";
    }

    public static String makeWebLoginStartUrl(boolean useTestGaia, String domainName) {
        return makeHostedGaiaBasePath(useTestGaia, domainName) + "ServiceLogin";
    }

    private static String makeHIDCookieExtractionPath(boolean useTestGaia, String domainName) {
        return makeHostedGaiaBasePath(useTestGaia, domainName);
    }

    private static String makeLSIDCookieExtractionPath(boolean useTestGaia, String domainName) {
        return (useTestGaia ? TEST_GAIA_HOSTED_BASE_PATH : DEFAULT_HOSTED_BASE_PATH) + "/accounts/";
    }

    public static String extractHID(CookieManager cookieManager, String domainName) {
        String HID = extractCookie(cookieManager.getCookie(makeHIDCookieExtractionPath(false, domainName)), "HID");
        if (HID.length() == 0) {
            return extractCookie(cookieManager.getCookie(makeLSIDCookieExtractionPath(false, domainName)), "LSID");
        }
        return HID;
    }

    private static String extractCookie(String cookieString, String name) {
        if (cookieString == null) {
            return BuildConfig.VERSION_NAME;
        }
        for (String cookieFragment : cookieString.split("; ")) {
            String[] nameValue = cookieFragment.split("=");
            if (nameValue.length == 2 && nameValue[0].equalsIgnoreCase(name)) {
                return nameValue[1];
            }
        }
        return BuildConfig.VERSION_NAME;
    }
}
