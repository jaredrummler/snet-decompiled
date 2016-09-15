package com.google.android.gms.people.model;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.Owners;

public abstract class DataBufferWithSyncInfo<T> extends AbstractDataBuffer<T> {
    protected DataBufferWithSyncInfo(DataHolder dataHolder) {
        super(dataHolder);
    }

    public boolean isTickleSyncEnabled() {
        return this.mDataHolder.getMetadata().getBoolean(BundleKeys.IS_TICKLE_SYNC_ENABLED);
    }

    public boolean isPeriodicSyncEnabled() {
        return this.mDataHolder.getMetadata().getBoolean(BundleKeys.IS_PERIODIC_SYNC_ENABLED);
    }

    public long getLastSyncStartTimestamp() {
        return this.mDataHolder.getMetadata().getLong(BundleKeys.LAST_SYNC_START_TIMESTAMP);
    }

    public long getLastSyncFinishTimestamp() {
        return this.mDataHolder.getMetadata().getLong(BundleKeys.LAST_SYNC_FINISH_TIMESTAMP);
    }

    public long getLastSuccessfulSyncFinishTimestamp() {
        return this.mDataHolder.getMetadata().getLong(BundleKeys.LAST_SUCCESSFUL_SYNC_FINISH_TIMESTAMP);
    }

    public int getLastSyncStatus() {
        return this.mDataHolder.getMetadata().getInt(Owners.LAST_SYNC_STATUS);
    }
}
