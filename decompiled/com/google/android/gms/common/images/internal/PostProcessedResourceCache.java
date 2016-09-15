package com.google.android.gms.common.images.internal;

import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import com.google.android.gms.common.internal.Objects;

public final class PostProcessedResourceCache extends LruCache<PostProcessedResource, Drawable> {

    public static final class PostProcessedResource {
        public final int postProcessingFlags;
        public final int resId;

        public PostProcessedResource(int resId, int postProcessingFlags) {
            this.resId = resId;
            this.postProcessingFlags = postProcessingFlags;
        }

        public int hashCode() {
            return Objects.hashCode(Integer.valueOf(this.resId), Integer.valueOf(this.postProcessingFlags));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PostProcessedResource)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            PostProcessedResource other = (PostProcessedResource) obj;
            if (other.resId == this.resId && other.postProcessingFlags == this.postProcessingFlags) {
                return true;
            }
            return false;
        }
    }

    public PostProcessedResourceCache() {
        super(10);
    }
}
