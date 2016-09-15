package com.google.android.gms.people;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.People.ReleasableResult;
import com.google.android.gms.people.model.AvatarReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface Images {

    @VisibleForTesting
    public static class LoadImageOptions {
        public static final LoadImageOptions DEFAULT;
        public final int avatarOptions;
        public final int imageSize;
        public final boolean useLargePictureForCp2Images;

        @VisibleForTesting
        public static class Builder {
            private int mAvatarOptions;
            private int mImageSize;
            public boolean mUseLargePictureForCp2Images;

            public Builder() {
                this.mImageSize = 1;
                this.mAvatarOptions = 0;
            }

            @VisibleForTesting
            public Builder setImageSize(int size) {
                this.mImageSize = size;
                return this;
            }

            @VisibleForTesting
            public Builder setAvatarOptions(int options) {
                this.mAvatarOptions = options;
                return this;
            }

            @VisibleForTesting
            public Builder setUseLargePictureForCp2Images(boolean useLargePicture) {
                this.mUseLargePictureForCp2Images = useLargePicture;
                return this;
            }

            @VisibleForTesting
            public final LoadImageOptions build() {
                return new LoadImageOptions();
            }
        }

        static {
            DEFAULT = new Builder().build();
        }

        private LoadImageOptions(Builder b) {
            this.imageSize = b.mImageSize;
            this.avatarOptions = b.mAvatarOptions;
            this.useLargePictureForCp2Images = b.mUseLargePictureForCp2Images;
        }
    }

    @VisibleForTesting
    public interface LoadImageResult extends ReleasableResult {
        int getHeight();

        ParcelFileDescriptor getParcelFileDescriptor();

        int getWidth();

        boolean isRewindable();
    }

    @VisibleForTesting
    public interface OnAvatarSetCallback {
        void onAvatarSet(SetAvatarResult setAvatarResult);
    }

    @VisibleForTesting
    public interface SetAvatarResult extends Result {
        String getUrl();
    }

    @VisibleForTesting
    PendingResult<LoadImageResult> loadByReference(@Nonnull GoogleApiClient googleApiClient, @Nonnull AvatarReference avatarReference, LoadImageOptions loadImageOptions);

    @VisibleForTesting
    PendingResult<LoadImageResult> loadByUrl(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, int i, int i2);

    @VisibleForTesting
    PendingResult<LoadImageResult> loadContactThumbnailByContactId(@Nonnull GoogleApiClient googleApiClient, long j);

    @VisibleForTesting
    PendingResult<LoadImageResult> loadOwnerAvatar(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, int i, int i2);

    @VisibleForTesting
    PendingResult<LoadImageResult> loadOwnerCoverPhoto(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2);

    @VisibleForTesting
    PendingResult<LoadImageResult> loadOwnerCoverPhoto(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, int i);

    @VisibleForTesting
    PendingResult<LoadImageResult> loadRemoteImage(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str);

    @VisibleForTesting
    PendingResult<SetAvatarResult> setAvatar(@Nonnull GoogleApiClient googleApiClient, @Nonnull String str, @Nullable String str2, @Nonnull Uri uri, boolean z);
}
