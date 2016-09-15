package com.google.android.gms.common.images.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.lint.BuildConfig;

public final class ImageUrlUtils {
    private static final String HTTPS_SCHEME_PREFIX = "https:";
    public static final String QUERY_PARAMETER_BOUNDING_BOX = "bounding_box";

    public static abstract class ImageUrlBuilder {
        protected static final boolean DBG = false;
        protected boolean mIsBlackAndWhite;
        protected int mSize;
        protected String mUrl;

        public abstract String build();

        protected ImageUrlBuilder(String url) {
            this.mSize = -1;
            this.mIsBlackAndWhite = false;
            this.mUrl = url;
        }

        public ImageUrlBuilder setSize(Context context, int resId) {
            this.mSize = context.getResources().getDimensionPixelSize(resId);
            return this;
        }

        public ImageUrlBuilder setSize(int size) {
            this.mSize = size;
            return this;
        }

        public ImageUrlBuilder setBlackAndWhite(boolean enabled) {
            this.mIsBlackAndWhite = enabled;
            return this;
        }
    }

    public static final class ContentImageUrlBuilder extends ImageUrlBuilder {
        private static final String TAG = "ContentImageUrlBuilder";

        public ContentImageUrlBuilder(String url) {
            super(url);
        }

        public String build() {
            if (this.mUrl == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder(this.mUrl);
            boolean firstParam = this.mUrl.lastIndexOf("=") == -1;
            if (this.mSize > -1) {
                sb.append(firstParam ? "=" : "-").append("s").append(this.mSize);
                firstParam = false;
            }
            if (this.mIsBlackAndWhite) {
                sb.append(firstParam ? "=" : "-").append("fbw=1");
            }
            return sb.toString();
        }
    }

    public static final class ProfileImageUrlBuilder extends ImageUrlBuilder {
        private static final String TAG = "ProfileImageUrlBuilder";
        private boolean mAllowSilhouette;

        public ProfileImageUrlBuilder(String url) {
            super(url);
            this.mAllowSilhouette = true;
        }

        public ProfileImageUrlBuilder setAllowSilhouette(boolean flag) {
            this.mAllowSilhouette = flag;
            return this;
        }

        public String build() {
            if (this.mUrl == null) {
                return null;
            }
            String[] urlParts = this.mUrl.split("/");
            int urlPartsLength = urlParts.length;
            if (urlPartsLength < 7 || urlPartsLength > 9) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            boolean firstParam = true;
            int i = 0;
            while (i < urlPartsLength) {
                if (i < 7) {
                    sb.append(urlParts[i]).append("/");
                    i++;
                } else {
                    if (!urlParts[i].contains(".")) {
                        sb.append(urlParts[i]);
                        firstParam = false;
                    }
                    if (this.mSize > -1) {
                        if (!firstParam) {
                            sb.append('-');
                        }
                        sb.append('s').append(this.mSize);
                        firstParam = false;
                    }
                    if (this.mIsBlackAndWhite) {
                        if (!firstParam) {
                            sb.append('-');
                        }
                        sb.append("fbw=1");
                        firstParam = false;
                    }
                    if (!this.mAllowSilhouette) {
                        if (!firstParam) {
                            sb.append('-');
                        }
                        sb.append("ns");
                        firstParam = false;
                    }
                    sb.append(firstParam ? BuildConfig.VERSION_NAME : "/");
                    return sb.toString();
                }
            }
            if (this.mSize > -1) {
                if (firstParam) {
                    sb.append('-');
                }
                sb.append('s').append(this.mSize);
                firstParam = false;
            }
            if (this.mIsBlackAndWhite) {
                if (firstParam) {
                    sb.append('-');
                }
                sb.append("fbw=1");
                firstParam = false;
            }
            if (this.mAllowSilhouette) {
                if (firstParam) {
                    sb.append('-');
                }
                sb.append("ns");
                firstParam = false;
            }
            if (firstParam) {
            }
            sb.append(firstParam ? BuildConfig.VERSION_NAME : "/");
            return sb.toString();
        }
    }

    private ImageUrlUtils() {
    }

    public static int getImageDensity(Uri uri) {
        String densityText = uri.getQueryParameter(Constants.PARAM_DENSITY);
        if (TextUtils.isEmpty(densityText)) {
            return 0;
        }
        return Integer.parseInt(densityText);
    }

    public static String setImageUrlSize(String url, int size, boolean crop, boolean killAnimation) {
        if (FIFEUtil.isFifeHostedUrl(url)) {
            return FIFEUtil.setImageUrlSize(size, url, crop, killAnimation);
        }
        return ImageProxyUtil.setImageUrlSize(size, url);
    }

    public static String normalizeImageUrl(String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            return null;
        }
        if (imageUrl.startsWith("//")) {
            return HTTPS_SCHEME_PREFIX + imageUrl;
        }
        return imageUrl;
    }
}
