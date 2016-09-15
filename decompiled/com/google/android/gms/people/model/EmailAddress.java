package com.google.android.gms.people.model;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.internal.EmptyIterable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@VisibleForTesting
public interface EmailAddress extends ValueAndType, Affinities {
    public static final Iterable<EmailAddress> EMPTY_EMAILS;

    @Nullable
    String getType();

    @Nonnull
    String getValue();

    static {
        EMPTY_EMAILS = new EmptyIterable();
    }
}
