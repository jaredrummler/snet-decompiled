package com.google.android.gms.people.model;

import com.google.android.gms.common.data.DataHolder;

public abstract class AggregatedPersonBuffer extends DataBufferWithSyncInfo<AggregatedPerson> {
    public AggregatedPersonBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }
}
