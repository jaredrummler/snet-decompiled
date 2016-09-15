package com.google.android.gms.people.identity.models;

import android.os.Parcelable;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public interface AvatarRef extends Parcelable {
    String getUrl();
}
