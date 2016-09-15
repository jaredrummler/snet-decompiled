package com.google.android.gms.common.images.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;
import com.google.android.gms.common.images.ImageRequest;
import com.google.android.gms.common.images.ImageRequest.ImageViewImageRequest;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.util.PlatformVersion;

public final class LoadingImageView extends ImageView {
    public static final int ASPECT_RATIO_ADJUST_HEIGHT = 2;
    public static final int ASPECT_RATIO_ADJUST_NONE = 0;
    public static final int ASPECT_RATIO_ADJUST_WIDTH = 1;
    private static final int NO_DEFAULT_IMAGE = 0;
    private static ImageManager mImageManager;
    private int mAspectRatioAdjust;
    private ClipPathProvider mClipPathProvider;
    private boolean mCrossFade;
    private boolean mCrossFadeAlways;
    private float mFixedAspectRatio;
    private boolean mForceNextCrossFade;
    private int mLoadedNoDataPlaceholderResId;
    private Uri mLoadedUri;
    private OnImageLoadedListener mOnImageLoadedListener;
    private int mPostProcessingFlags;
    private int mTintColor;

    public interface ClipPathProvider {
        Path getClipPath(int i, int i2);
    }

    public LoadingImageView(Context context) {
        this(context, null, ASPECT_RATIO_ADJUST_NONE);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, ASPECT_RATIO_ADJUST_NONE);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mLoadedNoDataPlaceholderResId = ASPECT_RATIO_ADJUST_NONE;
        this.mCrossFade = true;
        this.mCrossFadeAlways = false;
        this.mForceNextCrossFade = false;
        this.mTintColor = ASPECT_RATIO_ADJUST_NONE;
        this.mPostProcessingFlags = ASPECT_RATIO_ADJUST_NONE;
        this.mAspectRatioAdjust = ASPECT_RATIO_ADJUST_NONE;
        this.mFixedAspectRatio = 1.0f;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadingImageView);
        this.mAspectRatioAdjust = a.getInt(R.styleable.LoadingImageView_imageAspectRatioAdjust, ASPECT_RATIO_ADJUST_NONE);
        this.mFixedAspectRatio = a.getFloat(R.styleable.LoadingImageView_imageAspectRatio, 1.0f);
        setCircleCropEnabled(a.getBoolean(R.styleable.LoadingImageView_circleCrop, false));
        a.recycle();
    }

    public void clearImage() {
        loadUri(null);
        this.mForceNextCrossFade = true;
    }

    public void loadUri(Uri uri) {
        loadUri(uri, ASPECT_RATIO_ADJUST_NONE, true, false);
    }

    public void loadUri(Uri uri, int noDataResIdToLoad) {
        loadUri(uri, noDataResIdToLoad, true, false);
    }

    public void loadUri(Uri uri, int noDataResIdToLoad, boolean enableLoadingPlaceholder) {
        loadUri(uri, noDataResIdToLoad, enableLoadingPlaceholder, false);
    }

    public void loadUri(Uri uri, int noDataResIdToLoad, boolean enableLoadingPlaceholder, boolean useNewDrawable) {
        boolean crossFadeAlways = true;
        boolean sameUri = uri != null ? uri.equals(this.mLoadedUri) : this.mLoadedUri == null;
        if (sameUri) {
            if (this.mLoadedUri != null) {
                informListener(true);
                return;
            } else if (this.mLoadedNoDataPlaceholderResId == noDataResIdToLoad) {
                informListener(false);
                return;
            }
        }
        if (mImageManager == null) {
            mImageManager = ImageManager.create(getContext(), getContext().getApplicationContext().getPackageName().equals(GooglePlayServicesUtilLight.GOOGLE_PLAY_GAMES_PACKAGE));
        }
        if (!(this.mCrossFadeAlways || this.mForceNextCrossFade)) {
            crossFadeAlways = false;
        }
        this.mForceNextCrossFade = false;
        ImageRequest imageRequest = new ImageViewImageRequest((ImageView) this, uri);
        imageRequest.setNoDataPlaceholder(noDataResIdToLoad);
        imageRequest.setCrossFadeEnabled(this.mCrossFade);
        imageRequest.setCrossFadeAlwaysEnabled(crossFadeAlways);
        imageRequest.setLoadingPlaceholderEnabled(enableLoadingPlaceholder);
        imageRequest.setPostProcessingFlags(this.mPostProcessingFlags);
        imageRequest.setOnImageLoadedListener(this.mOnImageLoadedListener);
        imageRequest.setUseNewDrawable(useNewDrawable);
        mImageManager.loadImage(imageRequest);
    }

    public Uri getLoadedUri() {
        return this.mLoadedUri;
    }

    public void setLoadedUri(Uri loadedUri) {
        this.mLoadedUri = loadedUri;
    }

    public void setOnImageLoadedListener(OnImageLoadedListener onImageLoadedListener) {
        this.mOnImageLoadedListener = onImageLoadedListener;
    }

    public int getLoadedNoDataPlaceholderResId() {
        return this.mLoadedNoDataPlaceholderResId;
    }

    public void setLoadedNoDataPlaceholderResId(int loadedNoDataPlaceholderResId) {
        this.mLoadedNoDataPlaceholderResId = loadedNoDataPlaceholderResId;
    }

    public void setCrossFadeEnabled(boolean enabled) {
        this.mCrossFade = enabled;
    }

    public void setCrossFadeAlwaysEnabled(boolean enabled) {
        this.mCrossFadeAlways = enabled;
    }

    public void setCircleCropEnabled(boolean enabled) {
        if (enabled) {
            this.mPostProcessingFlags |= ASPECT_RATIO_ADJUST_WIDTH;
        } else {
            this.mPostProcessingFlags &= -2;
        }
    }

    public final void setTintColorId(int colorResId) {
        int color = ASPECT_RATIO_ADJUST_NONE;
        if (colorResId > 0) {
            Resources resources = getResources();
            if (resources != null) {
                color = resources.getColor(colorResId);
            }
        }
        setTintColor(color);
    }

    public final void setTintColor(int color) {
        this.mTintColor = color;
        if (this.mTintColor != 0) {
            setColorFilter(ColorFilters.COLOR_FILTER_BW);
        } else {
            setColorFilter(null);
        }
        invalidate();
    }

    @TargetApi(14)
    public final void setClipPathProvider(ClipPathProvider clipPathProvider) {
        this.mClipPathProvider = clipPathProvider;
        if (PlatformVersion.isAtLeastIceCreamSandwich() && !PlatformVersion.isAtLeastJellyBean()) {
            setLayerType(ASPECT_RATIO_ADJUST_WIDTH, null);
        }
    }

    public final void setImageAspectRatioAdjust(int aspectRatioAdjust, float aspectRatio) {
        boolean z = true;
        boolean z2 = aspectRatioAdjust == 0 || aspectRatioAdjust == ASPECT_RATIO_ADJUST_WIDTH || aspectRatioAdjust == ASPECT_RATIO_ADJUST_HEIGHT;
        Asserts.checkState(z2);
        if (aspectRatio <= 0.0f) {
            z = false;
        }
        Asserts.checkState(z);
        this.mAspectRatioAdjust = aspectRatioAdjust;
        this.mFixedAspectRatio = aspectRatio;
        requestLayout();
    }

    public final void clearAspectRatioAdjust() {
        if (this.mAspectRatioAdjust != 0) {
            this.mAspectRatioAdjust = ASPECT_RATIO_ADJUST_NONE;
            requestLayout();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height;
        int width;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        switch (this.mAspectRatioAdjust) {
            case ASPECT_RATIO_ADJUST_WIDTH /*1*/:
                height = getMeasuredHeight();
                width = (int) (((float) height) * this.mFixedAspectRatio);
                break;
            case ASPECT_RATIO_ADJUST_HEIGHT /*2*/:
                width = getMeasuredWidth();
                height = (int) (((float) width) / this.mFixedAspectRatio);
                break;
            default:
                return;
        }
        setMeasuredDimension(width, height);
    }

    protected void onDraw(Canvas canvas) {
        if (this.mClipPathProvider != null) {
            canvas.clipPath(this.mClipPathProvider.getClipPath(getWidth(), getHeight()));
        }
        super.onDraw(canvas);
        if (this.mTintColor != 0) {
            canvas.drawColor(this.mTintColor);
        }
    }

    private void informListener(boolean isRequestedDrawable) {
        if (this.mOnImageLoadedListener != null) {
            this.mOnImageLoadedListener.onImageLoaded(this.mLoadedUri, null, isRequestedDrawable);
        }
    }
}
