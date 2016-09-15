package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;
import com.google.android.gms.common.images.internal.CrossFadingDrawable;
import com.google.android.gms.common.images.internal.ImageUtils;
import com.google.android.gms.common.images.internal.LoadingImageView;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache.PostProcessedResource;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.PlatformVersion;
import java.lang.ref.WeakReference;

public abstract class ImageRequest {
    private static final int CROSS_FADING_DURATION = 250;
    private static final int NO_DEFAULT_IMAGE = 0;
    private boolean mCrossFade;
    private boolean mCrossFadeAlways;
    final ImageData mImageData;
    protected int mLoadingPlaceholderResId;
    protected int mNoDataPlaceholderResId;
    protected OnImageLoadedListener mOnImageLoadedListener;
    protected int mPostProcessingFlags;
    private boolean mUseLoadingPlaceholder;
    protected boolean mUseNewDrawable;

    static final class ImageData {
        public final Uri uri;

        public ImageData(Uri uri) {
            this.uri = uri;
        }

        public int hashCode() {
            return Objects.hashCode(this.uri);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ImageData)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return Objects.equal(((ImageData) obj).uri, this.uri);
        }
    }

    public static final class ImageViewImageRequest extends ImageRequest {
        private WeakReference<ImageView> mImageViewRef;

        public ImageViewImageRequest(ImageView imageView, Uri uri) {
            super(uri, 0);
            Asserts.checkNotNull(imageView);
            this.mImageViewRef = new WeakReference(imageView);
        }

        public ImageViewImageRequest(ImageView imageView, int resId) {
            super(null, resId);
            Asserts.checkNotNull(imageView);
            this.mImageViewRef = new WeakReference(imageView);
        }

        public int hashCode() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ImageViewImageRequest)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = (ImageView) this.mImageViewRef.get();
            ImageView otherImageView = (ImageView) ((ImageViewImageRequest) obj).mImageViewRef.get();
            if (otherImageView == null || imageView == null || !Objects.equal(otherImageView, imageView)) {
                return false;
            }
            return true;
        }

        protected void loadImage(Drawable drawable, boolean isCachedBitmap, boolean isLoadingPlaceholder, boolean isRequestedImage) {
            ImageView imageView = (ImageView) this.mImageViewRef.get();
            if (imageView != null) {
                setImageViewDrawable(imageView, drawable, isCachedBitmap, isLoadingPlaceholder, isRequestedImage);
            }
        }

        private void setImageViewDrawable(ImageView imageView, Drawable drawable, boolean isCachedBitmap, boolean isLoadingPlaceholder, boolean isRequestedImage) {
            boolean isNoDataPlaceholder;
            int noDataPlaceholderResId = 0;
            if (isLoadingPlaceholder || isRequestedImage) {
                isNoDataPlaceholder = false;
            } else {
                isNoDataPlaceholder = true;
            }
            if (isNoDataPlaceholder && (imageView instanceof LoadingImageView)) {
                int loadedNoDataPlaceholderResId = ((LoadingImageView) imageView).getLoadedNoDataPlaceholderResId();
                if (this.mNoDataPlaceholderResId != 0 && loadedNoDataPlaceholderResId == this.mNoDataPlaceholderResId) {
                    return;
                }
            }
            boolean crossFade = shouldCrossFade(isCachedBitmap, isLoadingPlaceholder);
            if (this.mUseNewDrawable && drawable != null) {
                drawable = drawable.getConstantState().newDrawable();
            }
            Drawable targetDrawable = drawable;
            if (crossFade) {
                targetDrawable = createTransitionDrawable(imageView.getDrawable(), drawable);
            }
            imageView.setImageDrawable(targetDrawable);
            if (imageView instanceof LoadingImageView) {
                LoadingImageView loadingImageView = (LoadingImageView) imageView;
                loadingImageView.setLoadedUri(isRequestedImage ? this.mImageData.uri : null);
                if (isNoDataPlaceholder) {
                    noDataPlaceholderResId = this.mNoDataPlaceholderResId;
                }
                loadingImageView.setLoadedNoDataPlaceholderResId(noDataPlaceholderResId);
            }
            if (crossFade) {
                ((CrossFadingDrawable) targetDrawable).startTransition(ImageRequest.CROSS_FADING_DURATION);
            }
        }
    }

    public static final class ListenerImageRequest extends ImageRequest {
        private WeakReference<OnImageLoadedListener> mOnImageLoadedListenerRef;

        public ListenerImageRequest(OnImageLoadedListener listener, Uri uri) {
            super(uri, 0);
            Asserts.checkNotNull(listener);
            this.mOnImageLoadedListenerRef = new WeakReference(listener);
        }

        public int hashCode() {
            return Objects.hashCode(this.mImageData);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ListenerImageRequest)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ListenerImageRequest other = (ListenerImageRequest) obj;
            OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.mOnImageLoadedListenerRef.get();
            OnImageLoadedListener otherOnImageLoadedListener = (OnImageLoadedListener) other.mOnImageLoadedListenerRef.get();
            if (otherOnImageLoadedListener == null || onImageLoadedListener == null || !Objects.equal(otherOnImageLoadedListener, onImageLoadedListener) || !Objects.equal(other.mImageData, this.mImageData)) {
                return false;
            }
            return true;
        }

        protected void loadImage(Drawable drawable, boolean isCachedBitmap, boolean isLoadingPlaceholder, boolean isRequestedImage) {
            if (!isLoadingPlaceholder) {
                OnImageLoadedListener onImageLoadedListener = (OnImageLoadedListener) this.mOnImageLoadedListenerRef.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.mImageData.uri, drawable, isRequestedImage);
                }
            }
        }
    }

    public static final class PostProcessingFlags {
        public static final int CIRCLE_CROP = 1;
    }

    public static final class TextViewImageRequest extends ImageRequest {
        public static final int POSITION_BOTTOM = 3;
        public static final int POSITION_END = 2;
        public static final int POSITION_START = 0;
        public static final int POSITION_TOP = 1;
        private int mTextViewPosition;
        private WeakReference<TextView> mTextViewRef;

        public TextViewImageRequest(TextView textView, int position, Uri uri) {
            boolean z = true;
            super(uri, POSITION_START);
            this.mTextViewPosition = -1;
            Asserts.checkNotNull(textView);
            if (position == 0 || position == POSITION_TOP || position == POSITION_END || position == POSITION_BOTTOM) {
                z = false;
            }
            Asserts.checkState(z, "Invalid position: " + position);
            this.mTextViewRef = new WeakReference(textView);
            this.mTextViewPosition = position;
        }

        public TextViewImageRequest(TextView textView, int position, int resId) {
            boolean z = true;
            super(null, resId);
            this.mTextViewPosition = -1;
            Asserts.checkNotNull(textView);
            if (position == 0 || position == POSITION_TOP || position == POSITION_END || position == POSITION_BOTTOM) {
                z = false;
            }
            Asserts.checkState(z, "Invalid position: " + position);
            this.mTextViewRef = new WeakReference(textView);
            this.mTextViewPosition = position;
        }

        public int hashCode() {
            Object[] objArr = new Object[POSITION_TOP];
            objArr[POSITION_START] = Integer.valueOf(this.mTextViewPosition);
            return Objects.hashCode(objArr);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TextViewImageRequest)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            TextViewImageRequest other = (TextViewImageRequest) obj;
            TextView textView = (TextView) this.mTextViewRef.get();
            TextView otherTextView = (TextView) other.mTextViewRef.get();
            if (otherTextView == null || textView == null || !Objects.equal(otherTextView, textView) || !Objects.equal(Integer.valueOf(other.mTextViewPosition), Integer.valueOf(this.mTextViewPosition))) {
                return false;
            }
            return true;
        }

        protected void loadImage(Drawable drawable, boolean isCachedBitmap, boolean isLoadingPlaceholder, boolean isRequestedImage) {
            TextView textView = (TextView) this.mTextViewRef.get();
            if (textView != null) {
                setTextViewCompoundDrawable(textView, this.mTextViewPosition, drawable, isCachedBitmap, isLoadingPlaceholder);
            }
        }

        @TargetApi(17)
        private void setTextViewCompoundDrawable(TextView textView, int position, Drawable drawable, boolean isCachedBitmap, boolean isLoadingPlaceholder) {
            Drawable[] compoundDrawables;
            boolean crossFade = shouldCrossFade(isCachedBitmap, isLoadingPlaceholder);
            Drawable targetDrawable = drawable;
            if (PlatformVersion.isAtLeastJellyBeanMR1()) {
                compoundDrawables = textView.getCompoundDrawablesRelative();
            } else {
                compoundDrawables = textView.getCompoundDrawables();
            }
            Drawable currentDrawable = compoundDrawables[position];
            if (crossFade) {
                targetDrawable = createTransitionDrawable(currentDrawable, drawable);
            }
            Drawable compoundDrawableStart = position == 0 ? targetDrawable : compoundDrawables[POSITION_START];
            Drawable compoundDrawableTop = position == POSITION_TOP ? targetDrawable : compoundDrawables[POSITION_TOP];
            Drawable compoundDrawableEnd = position == POSITION_END ? targetDrawable : compoundDrawables[POSITION_END];
            Drawable compoundDrawableBottom = position == POSITION_BOTTOM ? targetDrawable : compoundDrawables[POSITION_BOTTOM];
            if (PlatformVersion.isAtLeastJellyBeanMR1()) {
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawableStart, compoundDrawableTop, compoundDrawableEnd, compoundDrawableBottom);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawableStart, compoundDrawableTop, compoundDrawableEnd, compoundDrawableBottom);
            }
            if (crossFade) {
                ((CrossFadingDrawable) targetDrawable).startTransition(ImageRequest.CROSS_FADING_DURATION);
            }
        }
    }

    protected abstract void loadImage(Drawable drawable, boolean z, boolean z2, boolean z3);

    public ImageRequest(Uri uri, int noDataPlaceholderResId) {
        this.mLoadingPlaceholderResId = 0;
        this.mNoDataPlaceholderResId = 0;
        this.mUseNewDrawable = false;
        this.mCrossFade = true;
        this.mCrossFadeAlways = false;
        this.mUseLoadingPlaceholder = true;
        this.mImageData = new ImageData(uri);
        this.mNoDataPlaceholderResId = noDataPlaceholderResId;
    }

    public void setLoadingPlaceholder(int loadingPlaceholderResId) {
        this.mLoadingPlaceholderResId = loadingPlaceholderResId;
    }

    public void setOnImageLoadedListener(OnImageLoadedListener onImageLoadedListener) {
        this.mOnImageLoadedListener = onImageLoadedListener;
    }

    public void setNoDataPlaceholder(int noDataPlaceholderResId) {
        this.mNoDataPlaceholderResId = noDataPlaceholderResId;
    }

    public void setCrossFadeEnabled(boolean enabled) {
        this.mCrossFade = enabled;
    }

    public void setCrossFadeAlwaysEnabled(boolean enabled) {
        this.mCrossFadeAlways = enabled;
        if (enabled) {
            setCrossFadeEnabled(true);
        }
    }

    public void setLoadingPlaceholderEnabled(boolean enabled) {
        this.mUseLoadingPlaceholder = enabled;
    }

    public void setPostProcessingFlags(int flags) {
        this.mPostProcessingFlags = flags;
    }

    public void setUseNewDrawable(boolean enabled) {
        this.mUseNewDrawable = enabled;
    }

    void onImageLoaded(Context context, Bitmap bitmap, boolean isCachedBitmap) {
        Asserts.checkNotNull(bitmap);
        if ((this.mPostProcessingFlags & 1) != 0) {
            bitmap = ImageUtils.frameBitmapInCircle(bitmap);
        }
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.mImageData.uri, drawable, true);
        }
        loadImage(drawable, isCachedBitmap, false, true);
    }

    void loadLoadingPlaceholder(Context context, PostProcessedResourceCache cache) {
        if (this.mUseLoadingPlaceholder) {
            Drawable drawable = null;
            if (this.mLoadingPlaceholderResId != 0) {
                drawable = loadDrawableFromResource(context, cache, this.mLoadingPlaceholderResId);
            }
            loadImage(drawable, false, true, false);
        }
    }

    void loadNoDataPlaceholder(Context context, PostProcessedResourceCache cache, boolean displayAsCachedBitmap) {
        Drawable drawable = null;
        if (this.mNoDataPlaceholderResId != 0) {
            drawable = loadDrawableFromResource(context, cache, this.mNoDataPlaceholderResId);
        }
        if (this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.mImageData.uri, drawable, false);
        }
        loadImage(drawable, displayAsCachedBitmap, false, false);
    }

    private Drawable loadDrawableFromResource(Context context, PostProcessedResourceCache cache, int resId) {
        Resources res = context.getResources();
        if (this.mPostProcessingFlags <= 0) {
            return res.getDrawable(resId);
        }
        PostProcessedResource key = new PostProcessedResource(resId, this.mPostProcessingFlags);
        Drawable postProcessedDrawable = (Drawable) cache.get(key);
        if (postProcessedDrawable != null) {
            return postProcessedDrawable;
        }
        postProcessedDrawable = res.getDrawable(resId);
        if ((this.mPostProcessingFlags & 1) != 0) {
            postProcessedDrawable = frameDrawableInCircle(res, postProcessedDrawable);
        }
        cache.put(key, postProcessedDrawable);
        return postProcessedDrawable;
    }

    protected Drawable frameDrawableInCircle(Resources res, Drawable drawable) {
        return ImageUtils.frameDrawableInCircle(res, drawable);
    }

    protected boolean shouldCrossFade(boolean isCachedBitmap, boolean isLoadingPlaceholder) {
        return this.mCrossFade && !isLoadingPlaceholder && (!isCachedBitmap || this.mCrossFadeAlways);
    }

    protected CrossFadingDrawable createTransitionDrawable(Drawable currentDrawable, Drawable targetDrawable) {
        Drawable originDrawable = null;
        if (currentDrawable != null) {
            if (currentDrawable instanceof CrossFadingDrawable) {
                originDrawable = ((CrossFadingDrawable) currentDrawable).getEndDrawable();
            } else {
                originDrawable = currentDrawable;
            }
        }
        return new CrossFadingDrawable(originDrawable, targetDrawable);
    }
}
