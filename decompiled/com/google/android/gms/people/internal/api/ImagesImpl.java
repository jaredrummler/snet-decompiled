package com.google.android.gms.people.internal.api;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.Images;
import com.google.android.gms.people.Images.LoadImageOptions;
import com.google.android.gms.people.Images.LoadImageResult;
import com.google.android.gms.people.Images.SetAvatarResult;
import com.google.android.gms.people.People.BasePeopleApiMethodImpl;
import com.google.android.gms.people.internal.PeopleCallLog;
import com.google.android.gms.people.internal.PeopleClientImpl;
import com.google.android.gms.people.model.AvatarReference;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressLint({"MissingRemoteException"})
public class ImagesImpl implements Images {

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.1 */
    class AnonymousClass1 extends BasePeopleApiMethodImpl<SetAvatarResult> {
        final /* synthetic */ String val$account;
        final /* synthetic */ Uri val$imageUri;
        final /* synthetic */ boolean val$insertCameraImage;
        final /* synthetic */ String val$pageId;

        /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.1.1 */
        class AnonymousClass1 implements SetAvatarResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public String getUrl() {
                return null;
            }

            public Status getStatus() {
                return this.val$status;
            }
        }

        AnonymousClass1(GoogleApiClient x0, String str, String str2, Uri uri, boolean z) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$imageUri = uri;
            this.val$insertCameraImage = z;
            super(x0);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            clientImpl.setAvatar(this, this.val$account, this.val$pageId, this.val$imageUri, this.val$insertCameraImage);
        }

        protected SetAvatarResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    private static abstract class BaseLoadImageImpl extends BasePeopleApiMethodImpl<LoadImageResult> {

        /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.BaseLoadImageImpl.1 */
        class AnonymousClass1 implements LoadImageResult {
            final /* synthetic */ Status val$status;

            AnonymousClass1(Status status) {
                this.val$status = status;
            }

            public ParcelFileDescriptor getParcelFileDescriptor() {
                return null;
            }

            public boolean isRewindable() {
                return false;
            }

            public int getWidth() {
                return 0;
            }

            public int getHeight() {
                return 0;
            }

            public Status getStatus() {
                return this.val$status;
            }

            public void release() {
            }
        }

        private BaseLoadImageImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public final LoadImageResult createFailedResult(Status status) {
            return new AnonymousClass1(status);
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.2 */
    class AnonymousClass2 extends BaseLoadImageImpl {
        final /* synthetic */ int val$avatarSize;
        final /* synthetic */ int val$options;
        final /* synthetic */ String val$url;

        AnonymousClass2(GoogleApiClient x0, String str, int i, int i2) {
            this.val$url = str;
            this.val$avatarSize = i;
            this.val$options = i2;
            super(null);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            setCancelToken(clientImpl.loadAvatarByUrl(this, this.val$url, this.val$avatarSize, this.val$options));
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.3 */
    class AnonymousClass3 extends BaseLoadImageImpl {
        final /* synthetic */ String val$url;

        AnonymousClass3(GoogleApiClient x0, String str) {
            this.val$url = str;
            super(null);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            setCancelToken(clientImpl.loadRemoteImage(this, this.val$url));
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.4 */
    class AnonymousClass4 extends BaseLoadImageImpl {
        final /* synthetic */ long val$contactId;

        AnonymousClass4(GoogleApiClient x0, long j) {
            this.val$contactId = j;
            super(null);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            setCancelToken(clientImpl.loadContactThumbnailByContactId(this, this.val$contactId));
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.5 */
    class AnonymousClass5 extends BaseLoadImageImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ int val$avatarOptions;
        final /* synthetic */ int val$avatarSize;
        final /* synthetic */ String val$pageId;

        AnonymousClass5(GoogleApiClient x0, String str, String str2, int i, int i2) {
            this.val$account = str;
            this.val$pageId = str2;
            this.val$avatarSize = i;
            this.val$avatarOptions = i2;
            super(null);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            setCancelToken(clientImpl.loadOwnerAvatar(this, this.val$account, this.val$pageId, this.val$avatarSize, this.val$avatarOptions));
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.6 */
    class AnonymousClass6 extends BaseLoadImageImpl {
        final /* synthetic */ String val$account;
        final /* synthetic */ String val$pageId;

        AnonymousClass6(GoogleApiClient x0, String str, String str2) {
            this.val$account = str;
            this.val$pageId = str2;
            super(null);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            setCancelToken(clientImpl.loadOwnerCoverPhoto(this, this.val$account, this.val$pageId, 0));
        }
    }

    /* renamed from: com.google.android.gms.people.internal.api.ImagesImpl.7 */
    class AnonymousClass7 extends BaseLoadImageImpl {
        final /* synthetic */ LoadImageOptions val$options;
        final /* synthetic */ AvatarReference val$ref;

        AnonymousClass7(GoogleApiClient x0, AvatarReference avatarReference, LoadImageOptions loadImageOptions) {
            this.val$ref = avatarReference;
            this.val$options = loadImageOptions;
            super(null);
        }

        protected void doExecute(PeopleClientImpl clientImpl) {
            setCancelToken(clientImpl.loadAvatarByReference(this, this.val$ref, this.val$options));
        }
    }

    public PendingResult<SetAvatarResult> setAvatar(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, @Nonnull Uri imageUri, boolean insertCameraImage) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("setAvatar", account, pageId, imageUri, Boolean.valueOf(insertCameraImage));
        }
        return googleApiClient.execute(new AnonymousClass1(googleApiClient, account, pageId, imageUri, insertCameraImage));
    }

    @VisibleForTesting
    public PendingResult<LoadImageResult> loadByUrl(@Nonnull GoogleApiClient googleApiClient, @Nonnull String url, int avatarSize, int options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadByUrl", url, Integer.valueOf(avatarSize), Integer.valueOf(options));
        }
        return googleApiClient.enqueue(new AnonymousClass2(googleApiClient, url, avatarSize, options));
    }

    @VisibleForTesting
    public PendingResult<LoadImageResult> loadRemoteImage(@Nonnull GoogleApiClient googleApiClient, @Nonnull String url) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadRemoteImage", url);
        }
        return googleApiClient.enqueue(new AnonymousClass3(googleApiClient, url));
    }

    @VisibleForTesting
    public PendingResult<LoadImageResult> loadContactThumbnailByContactId(@Nonnull GoogleApiClient googleApiClient, long contactId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadContactThumbnailByContactId", Long.valueOf(contactId));
        }
        return googleApiClient.enqueue(new AnonymousClass4(googleApiClient, contactId));
    }

    public PendingResult<LoadImageResult> loadOwnerAvatar(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, int avatarSize, int avatarOptions) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadOwnerAvatar", account, pageId, Integer.valueOf(avatarSize), Integer.valueOf(avatarOptions));
        }
        return googleApiClient.enqueue(new AnonymousClass5(googleApiClient, account, pageId, avatarSize, avatarOptions));
    }

    public PendingResult<LoadImageResult> loadOwnerCoverPhoto(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadOwnerCoverPhoto", account, pageId);
        }
        return googleApiClient.enqueue(new AnonymousClass6(googleApiClient, account, pageId));
    }

    public PendingResult<LoadImageResult> loadOwnerCoverPhoto(@Nonnull GoogleApiClient googleApiClient, @Nonnull String account, @Nullable String pageId, int minimumWidth) {
        return loadOwnerCoverPhoto(googleApiClient, account, pageId);
    }

    public PendingResult<LoadImageResult> loadByReference(@Nonnull GoogleApiClient googleApiClient, @Nonnull AvatarReference ref, LoadImageOptions options) {
        if (PeopleCallLog.isEnabled()) {
            PeopleCallLog.log("loadByReference", ref, options);
        }
        return googleApiClient.enqueue(new AnonymousClass7(googleApiClient, ref, options));
    }
}
