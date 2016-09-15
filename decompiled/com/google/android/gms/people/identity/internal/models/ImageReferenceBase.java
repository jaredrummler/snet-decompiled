package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public interface ImageReferenceBase {
    byte[] getData();

    String getLocation();

    int getType();

    boolean hasData();

    boolean hasLocation();

    boolean hasType();
}
