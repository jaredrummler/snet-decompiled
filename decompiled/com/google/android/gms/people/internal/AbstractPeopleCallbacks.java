package com.google.android.gms.people.internal;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.internal.IPeopleCallbacks.Stub;

public class AbstractPeopleCallbacks extends Stub {
    public void onBundleLoaded(int statusCode, Bundle resolution, Bundle content) {
    }

    public void onDataHolderLoaded(int statusCode, Bundle resolution, DataHolder dataHolder) {
    }

    public void onDataHoldersLoaded(int statusCode, Bundle resolution, DataHolder[] dataHolders) {
    }

    public final void onParcelFileDescriptorLoaded_old(int statusCode, Bundle resolution, ParcelFileDescriptor pfd) {
        throw new RuntimeException("Shouldn't be called");
    }

    public void onParcelFileDescriptorLoaded(int statusCode, Bundle resolution, ParcelFileDescriptor pfd, Bundle metadata) {
    }
}
