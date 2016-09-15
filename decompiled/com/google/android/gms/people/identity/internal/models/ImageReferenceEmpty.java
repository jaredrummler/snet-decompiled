package com.google.android.gms.people.identity.internal.models;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public class ImageReferenceEmpty implements ImageReferenceBase {
    public boolean hasType() {
        return false;
    }

    public int getType() {
        return ((Integer) null).intValue();
    }

    public boolean hasLocation() {
        return false;
    }

    public String getLocation() {
        return null;
    }

    public boolean hasData() {
        return false;
    }

    public byte[] getData() {
        return null;
    }
}
