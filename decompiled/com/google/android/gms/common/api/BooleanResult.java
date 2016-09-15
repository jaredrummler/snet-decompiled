package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;

public class BooleanResult implements Result {
    private final Status mStatus;
    private final boolean mValue;

    public BooleanResult(Status status, boolean value) {
        this.mStatus = (Status) Preconditions.checkNotNull(status, "Status must not be null");
        this.mValue = value;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public boolean getValue() {
        return this.mValue;
    }

    public final int hashCode() {
        return ((this.mStatus.hashCode() + 527) * 31) + (this.mValue ? 1 : 0);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult other = (BooleanResult) obj;
        if (this.mStatus.equals(other.mStatus) && this.mValue == other.mValue) {
            return true;
        }
        return false;
    }
}
