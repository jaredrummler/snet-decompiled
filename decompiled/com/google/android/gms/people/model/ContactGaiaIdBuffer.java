package com.google.android.gms.people.model;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.internal.ContactGaiaIdRef;

public final class ContactGaiaIdBuffer extends AbstractDataBuffer<ContactGaiaId> {
    public ContactGaiaIdBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public ContactGaiaId get(int position) {
        return new ContactGaiaIdRef(this.mDataHolder, position);
    }

    public String toString() {
        return "ContactGaiaId:size=" + getCount();
    }
}
