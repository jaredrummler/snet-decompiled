package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public interface PersonReferenceBase {
    ImageReferenceBase getAvatarReference();

    String getName();

    String getQualifiedId();

    boolean hasAvatarReference();

    boolean hasName();

    boolean hasQualifiedId();
}
