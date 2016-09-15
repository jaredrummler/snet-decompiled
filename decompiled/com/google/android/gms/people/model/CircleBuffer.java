package com.google.android.gms.people.model;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.internal.CircleRef;

public final class CircleBuffer extends DataBufferWithSyncInfo<Circle> {
    public CircleBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public Circle get(int position) {
        return new CircleRef(this.mDataHolder, position, getMetadata());
    }

    public String toString() {
        return "Circles:size=" + getCount();
    }
}
