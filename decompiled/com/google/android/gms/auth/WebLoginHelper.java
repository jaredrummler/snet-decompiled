package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.CookieManager;
import com.google.android.gms.auth.oauthmultilogin.proto.Cookie;
import com.google.android.gms.auth.oauthmultilogin.proto.OAuthLoginForOsidResponse;
import com.google.android.gms.auth.oauthmultilogin.proto.OAuthMultiLoginJsonResponse;
import com.google.android.gms.auth.oauthmultilogin.proto.OAuthMultiLoginJsonResponse.FailedAccount;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.internal.models.DefaultPersonImpl.Images;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WebLoginHelper {
    private static final String TAG = "WebLoginHelper";
    private final Context mContext;
    private final CookieManager mCookieManager;
    private final WebLoginResponseGetter mWebLoginGetter;

    public static class RecoverableException extends Exception {
        private static final long serialVersionUID = 1;
        private final String mRecoveryUrl;

        private RecoverableException(String recoveryUrl) {
            this.mRecoveryUrl = recoveryUrl;
        }

        public String getRecoveryUrl() {
            return this.mRecoveryUrl;
        }
    }

    @VisibleForTesting
    static class WebLoginResponseGetter {
        WebLoginResponseGetter() {
        }

        public OAuthLoginForOsidResponse getWebLoginResponse(Context context, Account account, String scope) throws IOException, GoogleAuthException {
            try {
                return OAuthLoginForOsidResponse.parseFrom(Base64.decode(GoogleAuthUtilLight.getToken(context, account, scope), 9));
            } catch (InvalidProtocolBufferNanoException e) {
                throw new GoogleAuthException("Couldn't read data from server.", e);
            }
        }
    }

    public WebLoginHelper(Context context) {
        this(context, new WebLoginResponseGetter(), CookieManager.getInstance());
    }

    @VisibleForTesting
    WebLoginHelper(Context context, WebLoginResponseGetter webLoginGetter, CookieManager cookieManager) {
        this.mContext = context;
        this.mWebLoginGetter = webLoginGetter;
        this.mCookieManager = cookieManager;
    }

    public void getAndSetCookies(Account account, String... urls) throws RecoverableException, IOException, GoogleAuthException {
        Preconditions.checkNotNull(account);
        boolean z = urls != null && urls.length > 0;
        Preconditions.checkArgument(z, "Must have at least one URL.");
        handleResponse(this.mWebLoginGetter.getWebLoginResponse(this.mContext, account, buildScope(urls)));
    }

    private void handleResponse(OAuthLoginForOsidResponse osidResponse) throws RecoverableException, IOException, GoogleAuthException {
        if (osidResponse == null || osidResponse.response == null) {
            throw new GoogleAuthException("Invalid response.");
        }
        OAuthMultiLoginJsonResponse response = osidResponse.response;
        switch (response.status.intValue()) {
            case Type.TEMPORARY /*1*/:
                setCookies(response.cookies);
            case Type.INDEFINITELY /*2*/:
                throw new IOException("Request failed, but server said RETRY.");
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                setCookies(response.cookies);
                recoverToken(response.failedAccounts);
            default:
                Log.w(TAG, "Unexpected response: " + response);
                throw new GoogleAuthException("Unknown response status: " + response.status);
        }
    }

    private void setCookies(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            String domain = !TextUtils.isEmpty(cookie.host) ? cookie.host : cookie.domain;
            if (TextUtils.isEmpty(domain) || TextUtils.isEmpty(cookie.name) || TextUtils.isEmpty(cookie.value)) {
                Log.w(TAG, "Invalid cookie.");
            } else {
                String url = (unbox(cookie.isSecure) ? "https" : "http") + "://" + domain;
                String value = getCookieValue(cookie);
                Log.d(TAG, "Setting cookie for url: " + url);
                this.mCookieManager.setCookie(url, value);
            }
        }
    }

    private void recoverToken(FailedAccount[] failedAccounts) throws RecoverableException, GoogleAuthException {
        for (FailedAccount failedAccount : failedAccounts) {
            switch (failedAccount.status.intValue()) {
                case Type.TEMPORARY /*1*/:
                case Type.CUSTOM /*3*/:
                    break;
                case Type.INDEFINITELY /*2*/:
                    throw new RecoverableException(null);
                default:
                    Log.w(TAG, "Unrecognized failed account status: " + failedAccount.status);
                    break;
            }
        }
        throw new GoogleAuthException("Authorization failed, but no recoverable accounts.");
    }

    @VisibleForTesting
    static String buildScope(String... urls) {
        Builder urlBuilder = new Builder();
        String[] arr$ = urls;
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String rawUrl = arr$[i$];
            try {
                URL url = new URL(rawUrl);
                urlBuilder.appendQueryParameter(Images.URL, url.getProtocol() + "://" + url.getHost());
                i$++;
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid URL: " + rawUrl);
            }
        }
        return "weblogin:" + urlBuilder.build().getQuery();
    }

    @VisibleForTesting
    static String getCookieValue(Cookie cookie) {
        StringBuilder b = new StringBuilder(cookie.name).append('=');
        if (!TextUtils.isEmpty(cookie.value)) {
            b.append(cookie.value);
        }
        if (unbox(cookie.isHttpOnly)) {
            b.append(";HttpOnly");
        }
        if (unbox(cookie.isSecure)) {
            b.append(";Secure");
        }
        if (!TextUtils.isEmpty(cookie.domain)) {
            b.append(";Domain=").append(cookie.domain);
        }
        if (!TextUtils.isEmpty(cookie.path)) {
            b.append(";Path=").append(cookie.path);
        }
        if (cookie.maxAge != null && cookie.maxAge.intValue() > 0) {
            b.append(";Max-Age=").append(cookie.maxAge);
        }
        return b.toString();
    }

    private static boolean unbox(Boolean b) {
        return b != null && b.booleanValue();
    }
}
