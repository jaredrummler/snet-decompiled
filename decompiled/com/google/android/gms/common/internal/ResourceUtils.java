package com.google.android.gms.common.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.common.GooglePlayServicesUtil;

public final class ResourceUtils {
    private static final Uri DRAWABLE_URI_BASE;

    public interface SignInResources {
        public static final String BUTTON_DARK_TEXT_DEFAULT = "common_google_signin_btn_text_dark_normal";
    }

    static {
        DRAWABLE_URI_BASE = new Builder().scheme("android.resource").authority(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE).appendPath("drawable").build();
    }

    public static Uri getDrawableUri(String resourceName) {
        Preconditions.checkNotNull(resourceName, "Resource name must not be null.");
        return DRAWABLE_URI_BASE.buildUpon().appendPath(resourceName).build();
    }

    private ResourceUtils() {
    }
}
