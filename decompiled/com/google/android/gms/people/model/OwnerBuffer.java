package com.google.android.gms.people.model;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.internal.OwnerRef;

public final class OwnerBuffer extends AbstractDataBuffer<Owner> {
    public OwnerBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public Owner get(int position) {
        return new OwnerRef(this.mDataHolder, position);
    }

    public String toString() {
        return "Owner:size=" + getCount();
    }
}
