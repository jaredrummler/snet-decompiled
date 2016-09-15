package com.google.android.gms.people.exp;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.model.ContactGaiaId;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import javax.annotation.Nonnull;

@VisibleForTesting
public class ContactGaiaIdRawBuffer extends RawBuffer implements ContactGaiaId {
    @VisibleForTesting
    public ContactGaiaIdRawBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @Nonnull
    @VisibleForTesting
    public String getContactInfo() {
        return getString(AccountSettingsColumns.VALUE);
    }

    @Nonnull
    @VisibleForTesting
    public String getGaiaId() {
        return getString(AcHolderColumns.GAIA_ID);
    }

    public int getType() {
        return getInteger(OutgoingRmqColumns.PROTOBUF_TAG);
    }
}
