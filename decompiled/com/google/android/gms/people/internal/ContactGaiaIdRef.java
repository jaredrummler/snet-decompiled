package com.google.android.gms.people.internal;

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.model.ContactGaiaId;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import javax.annotation.Nonnull;

public class ContactGaiaIdRef extends DataBufferRef implements ContactGaiaId {
    public ContactGaiaIdRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    @Nonnull
    public String getContactInfo() {
        return getString(AccountSettingsColumns.VALUE);
    }

    @Nonnull
    public String getGaiaId() {
        return getString(AcHolderColumns.GAIA_ID);
    }

    public int getType() {
        return getInteger(OutgoingRmqColumns.PROTOBUF_TAG);
    }
}
