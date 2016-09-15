package com.google.android.gms.people.identity.models;

import android.os.Parcelable;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public interface PersonRef extends Parcelable {
    AvatarRef getAvatarRef();

    String getName();

    String getQualifiedId();
}
