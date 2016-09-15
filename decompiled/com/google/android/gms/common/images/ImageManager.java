package com.google.android.gms.common.images;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.auth.firstparty.recovery.RecoveryParamConstants;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.images.ImageRequest.ImageViewImageRequest;
import com.google.android.gms.common.images.ImageRequest.ListenerImageRequest;
import com.google.android.gms.common.images.internal.PostProcessedResourceCache;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    private static final long BLACKLIST_TIME_TO_LIVE = 3600000;
    static final String EXTRA_FILE_DESCRIPTOR = "com.google.android.gms.extra.fileDescriptor";
    private static final Object IN_FLIGHT_LOCK;
    private static final int LOAD_BITMAP_FROM_DISK_MAX_THREADS = 4;
    private static final boolean MEM_CACHE_DBG = false;
    private static final float MEM_CACHE_PERCENTAGE = 0.33f;
    private static final String MEM_CACHE_TAG = "BitmapMemCache";
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_MEDIUM = 2;
    private static final String TAG = "ImageManager";
    private static HashSet<Uri> sInFlightImages;
    private static ImageManager sInstance;
    private static ImageManager sMemoryCacheInstance;
    private final BitmapMemoryCache mBitmapMemoryCache;
    private final Map<Uri, Long> mBlacklistUriMap;
    private final Context mContext;
    private final Handler mHandler;
    private final Map<ImageRequest, ImageReceiver> mImageRequestImageReceiverMap;
    private final ExecutorService mLoadBitmapFromDiskThreadPool;
    private final PostProcessedResourceCache mPostProcessedResourceCache;
    private final Map<Uri, ImageReceiver> mUriImageReceiverMap;

    @TargetApi(11)
    private static final class ActivityManagerHoneycomb {
        private ActivityManagerHoneycomb() {
        }

        static int getLargeMemoryClass(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    private static final class BitmapMemoryCache extends LruCache<ImageData, Bitmap> {
        public BitmapMemoryCache(Context context) {
            super(computeSize(context));
        }

        protected void entryRemoved(boolean evicted, ImageData key, Bitmap oldValue, Bitmap newValue) {
            super.entryRemoved(evicted, key, oldValue, newValue);
        }

        @TargetApi(11)
        private static int computeSize(Context context) {
            int memoryClass;
            ActivityManager am = (ActivityManager) context.getSystemService(RecoveryParamConstants.VALUE_ACTIVITY);
            if (((context.getApplicationInfo().flags & PeopleColumnBitmask.PEOPLE_IN_COMMON) != 0 ? true : ImageManager.MEM_CACHE_DBG) && PlatformVersion.isAtLeastHoneycomb()) {
                memoryClass = ActivityManagerHoneycomb.getLargeMemoryClass(am);
            } else {
                memoryClass = am.getMemoryClass();
            }
            return (int) (ImageManager.MEM_CACHE_PERCENTAGE * ((float) (PeopleColumnBitmask.PEOPLE_IN_COMMON * memoryClass)));
        }

        protected int sizeOf(ImageData key, Bitmap value) {
            return value.getHeight() * value.getRowBytes();
        }
    }

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final ArrayList<ImageRequest> mImageRequestList;
        private final Uri mUri;

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
            this.mImageRequestList = new ArrayList();
        }

        public void addImageRequest(ImageRequest imageRequest) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.mImageRequestList.add(imageRequest);
        }

        public void removeImageRequest(ImageRequest imageRequest) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.mImageRequestList.remove(imageRequest);
        }

        public void sendBroadcast() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.putExtra(Constants.EXTRA_URI, this.mUri);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, ImageManager.PRIORITY_HIGH);
            ImageManager.this.mContext.sendBroadcast(intent);
        }

        public void onReceiveResult(int resultCode, Bundle resultData) {
            ImageManager.this.mLoadBitmapFromDiskThreadPool.execute(new LoadBitmapFromDiskRunnable(this.mUri, (ParcelFileDescriptor) resultData.getParcelable(ImageManager.EXTRA_FILE_DESCRIPTOR)));
        }
    }

    private final class LoadBitmapFromDiskRunnable implements Runnable {
        private final ParcelFileDescriptor mParcelFileDescriptor;
        private final Uri mUri;

        public LoadBitmapFromDiskRunnable(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.mParcelFileDescriptor = parcelFileDescriptor;
        }

        public void run() {
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean oomTriggered = ImageManager.MEM_CACHE_DBG;
            Bitmap bitmap = null;
            if (this.mParcelFileDescriptor != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.mParcelFileDescriptor.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    Log.e(ImageManager.TAG, "OOM while loading bitmap for uri: " + this.mUri, e);
                    oomTriggered = true;
                }
                try {
                    this.mParcelFileDescriptor.close();
                } catch (IOException e2) {
                    Log.e(ImageManager.TAG, "closed failed", e2);
                }
            }
            CountDownLatch latch = new CountDownLatch(ImageManager.PRIORITY_LOW);
            ImageManager.this.mHandler.post(new OnBitmapLoadedRunnable(this.mUri, bitmap, oomTriggered, latch));
            try {
                latch.await();
            } catch (InterruptedException e3) {
                Log.w(ImageManager.TAG, "Latch interrupted while posting " + this.mUri);
            }
        }
    }

    private final class LoadImageRunnable implements Runnable {
        private final ImageRequest mImageRequest;

        public LoadImageRunnable(ImageRequest imageRequest) {
            this.mImageRequest = imageRequest;
        }

        public void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.mImageRequestImageReceiverMap.get(this.mImageRequest);
            if (imageReceiver != null) {
                ImageManager.this.mImageRequestImageReceiverMap.remove(this.mImageRequest);
                imageReceiver.removeImageRequest(this.mImageRequest);
            }
            ImageData imageData = this.mImageRequest.mImageData;
            if (imageData.uri == null) {
                this.mImageRequest.loadNoDataPlaceholder(ImageManager.this.mContext, ImageManager.this.mPostProcessedResourceCache, true);
                return;
            }
            Bitmap cachedBitmap = ImageManager.this.getCachedBitmap(imageData);
            if (cachedBitmap != null) {
                this.mImageRequest.onImageLoaded(ImageManager.this.mContext, cachedBitmap, true);
                return;
            }
            Long blacklistTimestamp = (Long) ImageManager.this.mBlacklistUriMap.get(imageData.uri);
            if (blacklistTimestamp != null) {
                if (SystemClock.elapsedRealtime() - blacklistTimestamp.longValue() < ImageManager.BLACKLIST_TIME_TO_LIVE) {
                    this.mImageRequest.loadNoDataPlaceholder(ImageManager.this.mContext, ImageManager.this.mPostProcessedResourceCache, true);
                    return;
                }
                ImageManager.this.mBlacklistUriMap.remove(imageData.uri);
            }
            this.mImageRequest.loadLoadingPlaceholder(ImageManager.this.mContext, ImageManager.this.mPostProcessedResourceCache);
            imageReceiver = (ImageReceiver) ImageManager.this.mUriImageReceiverMap.get(imageData.uri);
            if (imageReceiver == null) {
                imageReceiver = new ImageReceiver(imageData.uri);
                ImageManager.this.mUriImageReceiverMap.put(imageData.uri, imageReceiver);
            }
            imageReceiver.addImageRequest(this.mImageRequest);
            if (!(this.mImageRequest instanceof ListenerImageRequest)) {
                ImageManager.this.mImageRequestImageReceiverMap.put(this.mImageRequest, imageReceiver);
            }
            synchronized (ImageManager.IN_FLIGHT_LOCK) {
                if (!ImageManager.sInFlightImages.contains(imageData.uri)) {
                    ImageManager.sInFlightImages.add(imageData.uri);
                    imageReceiver.sendBroadcast();
                }
            }
        }
    }

    @TargetApi(14)
    private static final class LowMemoryListener implements ComponentCallbacks2 {
        private final BitmapMemoryCache mBitmapMemoryCache;

        public LowMemoryListener(BitmapMemoryCache bitmapMemoryCache) {
            this.mBitmapMemoryCache = bitmapMemoryCache;
        }

        public void onConfigurationChanged(Configuration newConfig) {
        }

        public void onLowMemory() {
            this.mBitmapMemoryCache.evictAll();
        }

        public void onTrimMemory(int level) {
            if (level >= 60) {
                this.mBitmapMemoryCache.evictAll();
            } else if (level >= 20) {
                this.mBitmapMemoryCache.trimToSize(this.mBitmapMemoryCache.size() / ImageManager.PRIORITY_MEDIUM);
            }
        }
    }

    private final class OnBitmapLoadedRunnable implements Runnable {
        private final Bitmap mBitmap;
        private final CountDownLatch mLatch;
        private boolean mOomTriggered;
        private final Uri mUri;

        public OnBitmapLoadedRunnable(Uri uri, Bitmap bitmap, boolean oomTriggered, CountDownLatch latch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.mOomTriggered = oomTriggered;
            this.mLatch = latch;
        }

        public void run() {
            boolean wasLoadSuccessful;
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            if (this.mBitmap != null) {
                wasLoadSuccessful = true;
            } else {
                wasLoadSuccessful = ImageManager.MEM_CACHE_DBG;
            }
            if (ImageManager.this.mBitmapMemoryCache != null) {
                if (this.mOomTriggered) {
                    ImageManager.this.mBitmapMemoryCache.evictAll();
                    System.gc();
                    this.mOomTriggered = ImageManager.MEM_CACHE_DBG;
                    ImageManager.this.mHandler.post(this);
                    return;
                } else if (wasLoadSuccessful) {
                    ImageManager.this.mBitmapMemoryCache.put(new ImageData(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.mUriImageReceiverMap.remove(this.mUri);
            if (imageReceiver != null) {
                dispatchResults(imageReceiver, wasLoadSuccessful);
            }
            this.mLatch.countDown();
            synchronized (ImageManager.IN_FLIGHT_LOCK) {
                ImageManager.sInFlightImages.remove(this.mUri);
            }
        }

        private void dispatchResults(ImageReceiver imageReceiver, boolean wasLoadSuccessful) {
            ArrayList<ImageRequest> imageRequestList = imageReceiver.mImageRequestList;
            int n = imageRequestList.size();
            for (int i = 0; i < n; i += ImageManager.PRIORITY_LOW) {
                ImageRequest imageRequest = (ImageRequest) imageRequestList.get(i);
                if (wasLoadSuccessful) {
                    imageRequest.onImageLoaded(ImageManager.this.mContext, this.mBitmap, ImageManager.MEM_CACHE_DBG);
                } else {
                    ImageManager.this.mBlacklistUriMap.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    imageRequest.loadNoDataPlaceholder(ImageManager.this.mContext, ImageManager.this.mPostProcessedResourceCache, ImageManager.MEM_CACHE_DBG);
                }
                if (!(imageRequest instanceof ListenerImageRequest)) {
                    ImageManager.this.mImageRequestImageReceiverMap.remove(imageRequest);
                }
            }
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    static {
        IN_FLIGHT_LOCK = new Object();
        sInFlightImages = new HashSet();
    }

    public static ImageManager create(Context context) {
        return create(context, MEM_CACHE_DBG);
    }

    public static ImageManager create(Context context, boolean withMemoryCache) {
        if (withMemoryCache) {
            if (sMemoryCacheInstance == null) {
                sMemoryCacheInstance = new ImageManager(context, true);
            }
            return sMemoryCacheInstance;
        }
        if (sInstance == null) {
            sInstance = new ImageManager(context, MEM_CACHE_DBG);
        }
        return sInstance;
    }

    private ImageManager(Context context, boolean withMemoryCache) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mLoadBitmapFromDiskThreadPool = Executors.newFixedThreadPool(LOAD_BITMAP_FROM_DISK_MAX_THREADS);
        if (withMemoryCache) {
            this.mBitmapMemoryCache = new BitmapMemoryCache(this.mContext);
            if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                registerComponentCallbacksV14();
            }
        } else {
            this.mBitmapMemoryCache = null;
        }
        this.mPostProcessedResourceCache = new PostProcessedResourceCache();
        this.mImageRequestImageReceiverMap = new HashMap();
        this.mUriImageReceiverMap = new HashMap();
        this.mBlacklistUriMap = new HashMap();
    }

    @TargetApi(14)
    private void registerComponentCallbacksV14() {
        this.mContext.registerComponentCallbacks(new LowMemoryListener(this.mBitmapMemoryCache));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        loadImage(new ImageViewImageRequest(imageView, uri));
    }

    public void loadImage(ImageView imageView, int resId) {
        loadImage(new ImageViewImageRequest(imageView, resId));
    }

    public void loadImage(ImageView imageView, Uri uri, int defaultResId) {
        ImageRequest imageRequest = new ImageViewImageRequest(imageView, uri);
        imageRequest.setNoDataPlaceholder(defaultResId);
        loadImage(imageRequest);
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri) {
        loadImage(new ListenerImageRequest(listener, uri));
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri, int defaultResId) {
        ImageRequest imageRequest = new ListenerImageRequest(listener, uri);
        imageRequest.setNoDataPlaceholder(defaultResId);
        loadImage(imageRequest);
    }

    public void loadImage(ImageRequest imageRequest) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new LoadImageRunnable(imageRequest).run();
    }

    private Bitmap getCachedBitmap(ImageData imageData) {
        if (this.mBitmapMemoryCache == null) {
            return null;
        }
        return (Bitmap) this.mBitmapMemoryCache.get(imageData);
    }
}
