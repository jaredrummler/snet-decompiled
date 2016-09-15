package com.google.android.gsf;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.lint.BuildConfig;

public class WebAddAccountView {
    private static final String TAG = "WebAddAccountView";
    private View mBackButton;
    private View mBottomBar;
    private Callback mCallback;
    private View mCancelButton;
    private String mDomainName;
    private boolean mIsLoading;
    private ProgressBar mProgressBar;
    private View mProgressView;
    private String mStartUrl;
    private TextView mTitleTextView;
    private View mTitleView;
    private WebView mWebView;

    public interface Callback {
        void onWebLoginCompleted(String str);

        void onWebLoginError(Error error, int i, String str);
    }

    public enum Error {
        HttpError,
        TooManyRedirects
    }

    private class MyChromeClient extends WebChromeClient {
        private MyChromeClient() {
        }

        public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {
            Log.v(WebAddAccountView.TAG, "onCreateWindow");
            resultMsg.obj = WebAddAccountView.this.mWebView;
            resultMsg.sendToTarget();
            return true;
        }

        public void onProgressChanged(WebView view, int newProgress) {
            WebAddAccountView.this.mProgressBar.setProgress(newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        private boolean mOAuthDone;
        private String mOAuthUrl;

        private MyWebViewClient() {
            this.mOAuthDone = false;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(WebAddAccountView.TAG, "Web view is loading " + url);
            return foundOauthURL(view, url);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(WebAddAccountView.TAG, "onPageStarted " + url + (WebAddAccountView.this.mIsLoading ? " - loading" : " - not loading"));
            if (!foundOauthURL(view, url)) {
                if (!WebAddAccountView.this.mIsLoading) {
                    WebAddAccountView.this.mWebView.stopLoading();
                } else if (!maybeFinish(view)) {
                    super.onPageStarted(view, url, favicon);
                }
            }
        }

        private boolean foundOauthURL(WebView view, String url) {
            if (Uri.parse(url).getScheme().compareTo("oauth") != 0) {
                return false;
            }
            Log.i(WebAddAccountView.TAG, "We will handle oauth:gls URL " + url);
            this.mOAuthDone = true;
            this.mOAuthUrl = url;
            return maybeFinish(view);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.w(WebAddAccountView.TAG, "onReceivedError " + description);
            WebAddAccountView.this.mIsLoading = false;
            hideWebUI();
            WebAddAccountView.this.mCallback.onWebLoginError(Error.HttpError, errorCode, description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
            Log.e(WebAddAccountView.TAG, "onTooManyRedirects");
            hideWebUI();
            super.onTooManyRedirects(view, cancelMsg, continueMsg);
            WebAddAccountView.this.mCallback.onWebLoginError(Error.TooManyRedirects, 0, BuildConfig.VERSION_NAME);
        }

        private void hideWebUI() {
            CookieManager.getInstance().removeAllCookie();
            WebAddAccountView.this.mWebView.clearView();
            WebAddAccountView.this.mWebView.setVisibility(8);
            WebAddAccountView.this.mTitleView.setVisibility(8);
            WebAddAccountView.this.mBottomBar.setVisibility(0);
        }

        public void onPageFinished(WebView view, String url) {
            if (!WebAddAccountView.this.mIsLoading) {
                Log.i(WebAddAccountView.TAG, "Web view ingoring loaded url " + url);
            } else if (maybeFinish(view)) {
                Log.v(WebAddAccountView.TAG, "Finished at " + url);
            } else {
                Log.v(WebAddAccountView.TAG, "Not finished at " + url);
                super.onPageFinished(view, url);
                WebAddAccountView.this.mProgressView.setVisibility(8);
                WebAddAccountView.this.mTitleView.setVisibility(0);
                Uri uri = Uri.parse(url);
                String titleString = BuildConfig.VERSION_NAME;
                if ("https".equalsIgnoreCase(uri.getScheme())) {
                    titleString = titleString + "https://";
                }
                String pageTitle = WebAddAccountView.this.mWebView.getTitle();
                if (TextUtils.isEmpty(pageTitle)) {
                    titleString = titleString + uri.getAuthority();
                } else {
                    titleString = titleString + uri.getAuthority() + " : " + pageTitle;
                }
                WebAddAccountView.this.mTitleTextView.setText(titleString);
                WebAddAccountView.this.mBottomBar.setVisibility(8);
                WebAddAccountView.this.mCancelButton.setVisibility(8);
                WebAddAccountView.this.mWebView.setVisibility(0);
                WebAddAccountView.this.mWebView.requestFocus();
            }
        }

        private boolean maybeFinish(WebView view) {
            if (!this.mOAuthDone) {
                return false;
            }
            view.stopLoading();
            WebAddAccountView.this.mIsLoading = false;
            hideWebUI();
            WebAddAccountView.this.mCallback.onWebLoginCompleted(this.mOAuthUrl);
            return true;
        }
    }

    public WebAddAccountView(WebView webView, View progressView, ProgressBar progressBar, View backButton, View cancelButton, View titleView, TextView titleTextView, View bottomBar, Callback callback) {
        this.mWebView = webView;
        this.mProgressView = progressView;
        this.mProgressBar = progressBar;
        this.mProgressBar.setMax(100);
        this.mCallback = callback;
        this.mBackButton = backButton;
        this.mCancelButton = cancelButton;
        this.mTitleView = titleView;
        this.mTitleTextView = titleTextView;
        this.mBottomBar = bottomBar;
        setupOptionsAndCallbacks();
    }

    public void login(String startUrl, String domainName) {
        this.mStartUrl = startUrl;
        this.mDomainName = domainName;
        doLogin();
    }

    private void doLogin() {
        this.mTitleView.setVisibility(8);
        this.mWebView.setVisibility(8);
        this.mBackButton.setVisibility(8);
        this.mCancelButton.setVisibility(0);
        this.mProgressBar.setProgress(0);
        this.mProgressView.setVisibility(0);
        this.mWebView.loadUrl(this.mStartUrl);
        this.mIsLoading = true;
    }

    public void stop() {
        if (this.mIsLoading) {
            this.mWebView.stopLoading();
            this.mIsLoading = false;
        }
    }

    private void setupOptionsAndCallbacks() {
        this.mWebView.setWebViewClient(new MyWebViewClient());
        this.mWebView.setWebChromeClient(new MyChromeClient());
        WebSettings s = this.mWebView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setSupportMultipleWindows(false);
        s.setSaveFormData(false);
        s.setSavePassword(false);
        s.setAllowFileAccess(false);
        s.setDatabaseEnabled(false);
        s.setJavaScriptCanOpenWindowsAutomatically(false);
        s.setLoadsImagesAutomatically(true);
        s.setLightTouchEnabled(false);
        s.setNeedInitialFocus(false);
        s.setUseWideViewPort(true);
        this.mWebView.setMapTrackballToArrowKeys(false);
        this.mWebView.setFocusable(true);
        this.mWebView.setFocusableInTouchMode(true);
    }
}
