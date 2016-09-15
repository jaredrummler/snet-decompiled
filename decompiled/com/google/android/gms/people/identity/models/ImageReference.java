package com.google.android.gms.people.identity.models;

import android.os.Parcelable;
import com.google.android.gms.people.identity.internal.models.ImageReferenceBase;

public interface ImageReference extends ImageReferenceBase, Parcelable {

    public interface Types extends Parcelable {
        public static final int CP2_BLOB = 3;
        public static final int CP2_CONTENT_URI = 2;
        public static final int WEB_URL = 1;
    }
}
