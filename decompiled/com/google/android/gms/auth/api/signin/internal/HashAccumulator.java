package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.util.VisibleForTesting;

public class HashAccumulator {
    @VisibleForTesting
    static int PRIME;
    private int mHash;

    static {
        PRIME = 31;
    }

    public HashAccumulator() {
        this.mHash = 1;
    }

    public HashAccumulator addObject(Object object) {
        this.mHash = (object == null ? 0 : object.hashCode()) + (this.mHash * PRIME);
        return this;
    }

    public HashAccumulator addBoolean(boolean condition) {
        this.mHash = (condition ? 1 : 0) + (this.mHash * PRIME);
        return this;
    }

    public int hash() {
        return this.mHash;
    }
}
