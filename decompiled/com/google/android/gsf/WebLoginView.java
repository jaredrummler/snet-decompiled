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

public class WebLoginView {
    private static final String TAG = "WebLoginView";
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
            Log.v(WebLoginView.TAG, "onCreateWindow");
            resultMsg.obj = WebLoginView.this.mWebView;
            resultMsg.sendToTarget();
            return true;
        }

        public void onProgressChanged(WebView view, int newProgress) {
            WebLoginView.this.mProgressBar.setProgress(newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        private boolean mOAuthDone;
        private String mOAuthUrl;

        private MyWebViewClient() {
            this.mOAuthDone = false;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(WebLoginView.TAG, "Web view is loading " + url);
            Uri uri = Uri.parse(url);
            if (uri.getScheme().compareTo("oauth") != 0 || !uri.getSchemeSpecificPart().startsWith("//gls/callback?")) {
                return false;
            }
            Log.i(WebLoginView.TAG, "We will handle oauth:gls URL " + url);
            this.mOAuthDone = true;
            this.mOAuthUrl = url;
            return maybeFinish(view);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(WebLoginView.TAG, "onPageStarted " + url + (WebLoginView.this.mIsLoading ? " - loading" : " - not loading"));
            if (!WebLoginView.this.mIsLoading) {
                WebLoginView.this.mWebView.stopLoading();
            } else if (!maybeFinish(view)) {
                super.onPageStarted(view, url, favicon);
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.w(WebLoginView.TAG, "onReceivedError " + description);
            WebLoginView.this.mIsLoading = false;
            hideWebUI();
            WebLoginView.this.mCallback.onWebLoginError(Error.HttpError, errorCode, description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
            Log.e(WebLoginView.TAG, "onTooManyRedirects");
            hideWebUI();
            super.onTooManyRedirects(view, cancelMsg, continueMsg);
            WebLoginView.this.mCallback.onWebLoginError(Error.TooManyRedirects, 0, BuildConfig.VERSION_NAME);
        }

        private void hideWebUI() {
            CookieManager.getInstance().removeAllCookie();
            WebLoginView.this.mWebView.clearView();
            WebLoginView.this.mWebView.setVisibility(8);
            WebLoginView.this.mTitleView.setVisibility(8);
            WebLoginView.this.mBottomBar.setVisibility(0);
        }

        public void onPageFinished(WebView view, String url) {
            if (!WebLoginView.this.mIsLoading) {
                Log.i(WebLoginView.TAG, "Web view ingoring loaded url " + url);
            } else if (maybeFinish(view)) {
                Log.v(WebLoginView.TAG, "Finished at " + url);
            } else {
                Log.v(WebLoginView.TAG, "Not finished at " + url);
                super.onPageFinished(view, url);
                WebLoginView.this.mProgressView.setVisibility(8);
                WebLoginView.this.mTitleView.setVisibility(0);
                Uri uri = Uri.parse(url);
                String titleString = BuildConfig.VERSION_NAME;
                if ("https".equalsIgnoreCase(uri.getScheme())) {
                    titleString = titleString + "https://";
                }
                String pageTitle = WebLoginView.this.mWebView.getTitle();
                if (TextUtils.isEmpty(pageTitle)) {
                    titleString = titleString + uri.getAuthority();
                } else {
                    titleString = titleString + uri.getAuthority() + " : " + pageTitle;
                }
                WebLoginView.this.mTitleTextView.setText(titleString);
                WebLoginView.this.mBottomBar.setVisibility(8);
                WebLoginView.this.mCancelButton.setVisibility(8);
                WebLoginView.this.mWebView.setVisibility(0);
                WebLoginView.this.mWebView.requestFocus();
            }
        }

        private boolean maybeFinish(WebView view) {
            if (!this.mOAuthDone) {
                return false;
            }
            view.stopLoading();
            WebLoginView.this.mIsLoading = false;
            hideWebUI();
            WebLoginView.this.mCallback.onWebLoginCompleted(this.mOAuthUrl);
            return true;
        }
    }

    public WebLoginView(WebView webView, View progressView, ProgressBar progressBar, View backButton, View cancelButton, View titleView, TextView titleTextView, View bottomBar, Callback callback) {
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
