package com.google.android.gms.people.model;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.internal.AutocompleteEntryRef;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;

@VisibleForTesting
public class AutocompleteBuffer extends DataBufferWithSyncInfo<AutocompleteEntry> {
    public AutocompleteBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public AutocompleteEntry get(int position) {
        return new AutocompleteEntryRef(this, this.mDataHolder, position, getMetadata());
    }

    public String toString() {
        return "AutocompleteList:size=" + getCount();
    }

    public String getQuery() {
        return this.mDataHolder.getMetadata().getString(BundleKeys.QUERY);
    }

    public String getOwnerAccountName() {
        return this.mDataHolder.getMetadata().getString(OutgoingRmqColumns.ACCOUNT_ID);
    }

    public String getOwnerPlusPageId() {
        return this.mDataHolder.getMetadata().getString(BundleKeys.PAGE_GAIA_ID);
    }

    public DataHolder getDataHolder() {
        return this.mDataHolder;
    }
}
