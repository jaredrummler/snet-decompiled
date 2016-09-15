package com.google.android.gms.people.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.cp2.AndroidContactsUtils;
import com.google.android.gms.people.model.PhoneNumberEntry;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import com.google.android.gsf.TalkContract.PresenceColumns;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PhoneNumberEntryRef extends DataBufferRef implements PhoneNumberEntry {
    private final Context mContext;
    private final Bundle mMetadata;

    public interface PhoneNumberHolderColumns {
        public static final String[] ALL;
        public static final String CONTACT_ID = "contact_id";
        public static final String CONTACT_NAME = "display_name";
        public static final String LAST_UPDATE_TIME = "last_update_time";
        public static final String PHONE_NUMBER = "phone_number";

        static {
            ALL = new String[]{CONTACT_ID, CONTACT_NAME, PHONE_NUMBER, LAST_UPDATE_TIME};
        }
    }

    public PhoneNumberEntryRef(DataHolder holder, int dataRow, Bundle metadata, Context context) {
        super(holder, dataRow);
        this.mMetadata = metadata;
        this.mContext = context;
    }

    @Nonnull
    public String getOwnerAccountName() {
        return this.mMetadata.getString(OutgoingRmqColumns.ACCOUNT_ID);
    }

    @Nullable
    public String getName() {
        return getString(PhoneNumberHolderColumns.CONTACT_NAME);
    }

    @Nonnull
    public String getPhoneNumber() {
        return getString(PhoneNumberHolderColumns.PHONE_NUMBER);
    }

    @Nonnull
    public String getFocusContactId() {
        return getString(PresenceColumns.CONTACT_ID);
    }

    @Nullable
    public Long getLastUpdateTime() {
        return Long.valueOf(getLong(PhoneNumberHolderColumns.LAST_UPDATE_TIME));
    }

    @Nullable
    public String getPhotoUri() {
        return AndroidContactsUtils.getPhotoUriFromFocusContactId(this.mContext, getOwnerAccountName(), getFocusContactId());
    }
}
