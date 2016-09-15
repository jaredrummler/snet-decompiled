package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.internal.EmptyIterable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface PhoneNumber extends ValueAndType {
    public static final Iterable<PhoneNumber> EMPTY_PHONES;

    @Nullable
    String getType();

    @Nonnull
    String getValue();

    static {
        EMPTY_PHONES = new EmptyIterable();
    }
}
