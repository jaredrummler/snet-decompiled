package com.google.android.gms.common.images.internal;

import android.graphics.Point;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ImageProxyUtil {
    private static final String DEFAULT_CONTAINER = "esmobile";
    public static final int ORIGINAL_SIZE = -1;
    private static final String PARAM_CONTAINER = "container";
    private static final String PARAM_GADGET = "gadget";
    private static final String PARAM_HEIGHT = "resize_h";
    private static final String PARAM_NO_EXPAND = "no_expand";
    private static final String PARAM_REWRITE_MIME = "rewriteMime";
    private static final String PARAM_URL = "url";
    private static final String PARAM_WIDTH = "resize_w";
    private static final int PROXY_COUNT = 3;
    private static final String PROXY_DOMAIN_PREFIX = "images";
    private static final String PROXY_DOMAIN_SUFFIX = "-opensocial.googleusercontent.com";
    private static final Pattern PROXY_HOSTED_IMAGE_URL_RE;
    private static final String PROXY_PATH = "/gadgets/proxy";
    private static int sProxyIndex;

    static {
        PROXY_HOSTED_IMAGE_URL_RE = Pattern.compile("^(((http(s)?):)?\\/\\/images(\\d)?-.+-opensocial\\.googleusercontent\\.com\\/gadgets\\/proxy\\?)");
    }

    public static String setImageUrlSize(int size, String url) {
        if (url == null) {
            return url;
        }
        String proxyUrl;
        if (isProxyHostedUrl(url)) {
            proxyUrl = url;
            url = null;
        } else {
            proxyUrl = createProxyUrl();
        }
        return setImageUrlSizeOptions(size, size, Uri.parse(proxyUrl), url).toString();
    }

    public static String setImageUrlSize(int width, int height, String url) {
        if (url == null) {
            return url;
        }
        String proxyUrl;
        if (isProxyHostedUrl(url)) {
            proxyUrl = url;
            url = null;
        } else {
            proxyUrl = createProxyUrl();
        }
        return setImageUrlSizeOptions(width, height, Uri.parse(proxyUrl), url).toString();
    }

    public static Point getImageUrlSize(String url) {
        int i = 0;
        Point imageSize = new Point();
        if (url != null && isProxyHostedUrl(url)) {
            Uri proxyUri = Uri.parse(url);
            try {
                String width = proxyUri.getQueryParameter(PARAM_WIDTH);
                imageSize.x = TextUtils.isEmpty(width) ? 0 : Integer.parseInt(width);
            } catch (NumberFormatException e) {
            }
            try {
                String height = proxyUri.getQueryParameter(PARAM_HEIGHT);
                if (!TextUtils.isEmpty(height)) {
                    i = Integer.parseInt(height);
                }
                imageSize.y = i;
            } catch (NumberFormatException e2) {
            }
        }
        return imageSize;
    }

    private static String createProxyUrl() {
        StringBuffer proxy = new StringBuffer();
        proxy.append("https://").append(PROXY_DOMAIN_PREFIX).append(getNextProxyIndex()).append("-").append(DEFAULT_CONTAINER).append(PROXY_DOMAIN_SUFFIX).append(PROXY_PATH);
        return proxy.toString();
    }

    private static synchronized int getNextProxyIndex() {
        int toReturn;
        synchronized (ImageProxyUtil.class) {
            toReturn = sProxyIndex + 1;
            sProxyIndex = toReturn;
            sProxyIndex %= PROXY_COUNT;
        }
        return toReturn;
    }

    public static Uri setImageUrlSizeOptions(int width, int height, Uri proxyUri, String imageUrl) {
        Builder proxyUriBuilder = Uri.EMPTY.buildUpon();
        proxyUriBuilder.authority(proxyUri.getAuthority());
        proxyUriBuilder.scheme(proxyUri.getScheme());
        proxyUriBuilder.path(proxyUri.getPath());
        if (!(width == ORIGINAL_SIZE || height == ORIGINAL_SIZE)) {
            proxyUriBuilder.appendQueryParameter(PARAM_WIDTH, Integer.toString(width));
            proxyUriBuilder.appendQueryParameter(PARAM_HEIGHT, Integer.toString(height));
            proxyUriBuilder.appendQueryParameter(PARAM_NO_EXPAND, "1");
        }
        Uri newProxyUri = proxyUriBuilder.build();
        for (String key : getQueryParameterNames(proxyUri)) {
            if (newProxyUri.getQueryParameter(key) == null) {
                boolean isSizeParam = PARAM_WIDTH.equals(key) || PARAM_HEIGHT.equals(key) || PARAM_NO_EXPAND.equals(key);
                boolean isOriginalSize = width == ORIGINAL_SIZE || height == ORIGINAL_SIZE;
                proxyUriBuilder = newProxyUri.buildUpon();
                if (PARAM_URL.equals(key)) {
                    proxyUriBuilder.appendQueryParameter(PARAM_URL, proxyUri.getQueryParameter(PARAM_URL));
                } else if (!isOriginalSize || !isSizeParam) {
                    for (String value : proxyUri.getQueryParameters(key)) {
                        proxyUriBuilder.appendQueryParameter(key, value);
                    }
                }
                newProxyUri = proxyUriBuilder.build();
            }
        }
        if (imageUrl != null && newProxyUri.getQueryParameter(PARAM_URL) == null) {
            proxyUriBuilder = newProxyUri.buildUpon();
            proxyUriBuilder.appendQueryParameter(PARAM_URL, imageUrl);
            newProxyUri = proxyUriBuilder.build();
        }
        if (newProxyUri.getQueryParameter(PARAM_CONTAINER) == null) {
            proxyUriBuilder = newProxyUri.buildUpon();
            proxyUriBuilder.appendQueryParameter(PARAM_CONTAINER, DEFAULT_CONTAINER);
            newProxyUri = proxyUriBuilder.build();
        }
        if (newProxyUri.getQueryParameter(PARAM_GADGET) == null) {
            proxyUriBuilder = newProxyUri.buildUpon();
            proxyUriBuilder.appendQueryParameter(PARAM_GADGET, "a");
            newProxyUri = proxyUriBuilder.build();
        }
        if (newProxyUri.getQueryParameter(PARAM_REWRITE_MIME) != null) {
            return newProxyUri;
        }
        proxyUriBuilder = newProxyUri.buildUpon();
        proxyUriBuilder.appendQueryParameter(PARAM_REWRITE_MIME, "image/*");
        return proxyUriBuilder.build();
    }

    private static Set<String> getQueryParameterNames(Uri uri) {
        if (uri.isOpaque()) {
            throw new UnsupportedOperationException("This isn't a hierarchical URI.");
        }
        String query = uri.getEncodedQuery();
        if (query == null) {
            return Collections.emptySet();
        }
        Set<String> names = new LinkedHashSet();
        int start = 0;
        do {
            int end;
            int next = query.indexOf(38, start);
            if (next == ORIGINAL_SIZE) {
                end = query.length();
            } else {
                end = next;
            }
            int separator = query.indexOf(61, start);
            if (separator > end || separator == ORIGINAL_SIZE) {
                separator = end;
            }
            names.add(Uri.decode(query.substring(start, separator)));
            start = end + 1;
        } while (start < query.length());
        return Collections.unmodifiableSet(names);
    }

    public static boolean isProxyHostedUrl(String url) {
        if (url == null) {
            return false;
        }
        return PROXY_HOSTED_IMAGE_URL_RE.matcher(url).find();
    }

    public static boolean isProxyHostedUri(Uri uri) {
        return isProxyHostedUrl(uri.toString());
    }
}
