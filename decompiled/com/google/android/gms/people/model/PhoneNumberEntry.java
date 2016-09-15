package com.google.android.gms.people.model;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface PhoneNumberEntry {
    @Nonnull
    String getFocusContactId();

    @Nullable
    Long getLastUpdateTime();

    @Nullable
    String getName();

    @Nonnull
    String getOwnerAccountName();

    @Nonnull
    String getPhoneNumber();

    @RequiresPermission("android.permission.READ_CONTACTS")
    @Nullable
    String getPhotoUri();
}
