package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public interface ValueAndType {
    String getType();

    String getValue();
}
