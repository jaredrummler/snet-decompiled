package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public class PersonReferenceEmpty implements PersonReferenceBase {
    public boolean hasName() {
        return false;
    }

    public String getName() {
        return null;
    }

    public boolean hasQualifiedId() {
        return false;
    }

    public String getQualifiedId() {
        return null;
    }

    public boolean hasAvatarReference() {
        return false;
    }

    public ImageReferenceBase getAvatarReference() {
        return null;
    }
}
