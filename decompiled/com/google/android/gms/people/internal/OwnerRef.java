package com.google.android.gms.people.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.Owners;
import com.google.android.gms.people.PeopleConstants.People;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.PhoneNumberEntryRef.PhoneNumberHolderColumns;
import com.google.android.gms.people.model.AccountMetadata;
import com.google.android.gms.people.model.Owner;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class OwnerRef extends DataBufferRef implements Owner {
    public OwnerRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public long getRowId() {
        return getLong(PeoplePostalAddress._ID);
    }

    public String getAccountName() {
        return getString(Owners.ACCOUNT_NAME);
    }

    public boolean isPlusPage() {
        return getPlusPageId() != null;
    }

    public String getDisplayName() {
        String name = getString(PhoneNumberHolderColumns.CONTACT_NAME);
        return TextUtils.isEmpty(name) ? getAccountName() : name;
    }

    public String getAccountId() {
        return getString(AcHolderColumns.GAIA_ID);
    }

    @Nonnull
    @Deprecated
    public String getAccountGaiaId() {
        return getAccountId();
    }

    public String getAvatarUrl() {
        return PeopleClientFifeImageUrlDecompressor.INSTANCE.decompress(getString(People.COMPRESSED_AVATAR_URL));
    }

    public String getPlusPageId() {
        return getString(Owners.PAGE_GAIA_ID);
    }

    @Deprecated
    @Nullable
    public String getPlusPageGaiaId() {
        return getPlusPageId();
    }

    public long getLastSyncStartTimestamp() {
        return getLong(Owners.LAST_SYNC_START_TIME);
    }

    public long getLastSyncFinishTimestamp() {
        return getLong(Owners.LAST_SYNC_FINISH_TIME);
    }

    public int getLastSyncStatus() {
        return getInteger(Owners.LAST_SYNC_STATUS);
    }

    public long getLastSuccessfulSyncFinishTimestamp() {
        return getLong(Owners.LAST_SUCCESSFUL_SYNC_FINISH_TIME);
    }

    @Deprecated
    public boolean isSyncToContactsEnabled() {
        return isSyncCirclesToContactsEnabled();
    }

    public boolean isSyncCirclesToContactsEnabled() {
        return getBoolean(Owners.SYNC_CIRCLES_TO_CONTACTS);
    }

    public boolean isSyncEvergreenToContactsEnabled() {
        return getBoolean(Owners.SYNC_EVERGREEN_TO_CONTACTS);
    }

    public boolean isSyncMeToContactsEnabled() {
        return getBoolean(Owners.SYNC_ME_TO_CONTACTS);
    }

    public boolean isSyncEnabled() {
        AccountMetadata am = getAccountMetadata();
        if (am == null) {
            return false;
        }
        if (isPlusPage()) {
            return am.isPageTickleSyncEnabled;
        }
        return am.isSyncEnabled;
    }

    public boolean isPeriodicSyncEnabled() {
        AccountMetadata am = getAccountMetadata();
        if (am == null) {
            return false;
        }
        if (isPlusPage()) {
            return am.isPagePeriodicSyncEnabled;
        }
        return am.isSyncEnabled;
    }

    public boolean isPlusEnabled() {
        if (isPlusPage()) {
            return true;
        }
        AccountMetadata am = getAccountMetadata();
        return am == null ? false : am.isPlusEnabled;
    }

    public int isDasherAccount() {
        return getInteger(Owners.IS_DASHER_ACCOUNT);
    }

    public String getDasherDomain() {
        return getString(Owners.DASHER_DOMAIN);
    }

    public String getCoverPhotoUrl() {
        return PeopleClientFifeImageUrlDecompressor.INSTANCE.decompress(getString(Owners.COMPRESSED_COVER_PHOTO_URL));
    }

    public int getCoverPhotoHeight() {
        return getInteger(Owners.COVER_PHOTO_HEIGHT);
    }

    public int getCoverPhotoWidth() {
        return getInteger(Owners.COVER_PHOTO_WIDTH);
    }

    public String getCoverPhotoId() {
        return getString(Owners.COVER_PHOTO_ID);
    }

    public Owner freeze() {
        throw new UnsupportedOperationException("Method not supported for object Owner");
    }

    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }

    private AccountMetadata getAccountMetadata() {
        Bundle accountMetadataBundle = (Bundle) this.mDataHolder.getMetadata().getParcelable(BundleKeys.ACCOUNT_METADATA);
        if (accountMetadataBundle == null) {
            return null;
        }
        accountMetadataBundle.setClassLoader(getClass().getClassLoader());
        return (AccountMetadata) accountMetadataBundle.getParcelable(getAccountName());
    }
}
