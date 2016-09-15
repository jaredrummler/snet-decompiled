package com.google.android.gms.people.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.BundleKeys;
import com.google.android.gms.people.PeopleConstants.Circles;
import com.google.android.gms.people.PeopleConstants.Owners;
import com.google.android.gms.people.PeopleConstants.People;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.model.Circle;
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.OutgoingRmqColumns;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class CircleRef extends DataBufferRef implements Circle {
    private final Bundle mMetadata;

    public CircleRef(DataHolder holder, int dataRow, Bundle metadata) {
        super(holder, dataRow);
        this.mMetadata = metadata;
    }

    public long getRowId() {
        return getLong(PeoplePostalAddress._ID);
    }

    @VisibleForTesting
    public String getOwnerAccountName() {
        return this.mMetadata.getString(OutgoingRmqColumns.ACCOUNT_ID);
    }

    @VisibleForTesting
    public String getOwnerPlusPageId() {
        return this.mMetadata.getString(BundleKeys.PAGE_GAIA_ID);
    }

    @Nonnull
    @Deprecated
    public String getAccountName() {
        return getOwnerAccountName();
    }

    @Deprecated
    @Nullable
    public String getPlusPageGaiaId() {
        return getOwnerPlusPageId();
    }

    @VisibleForTesting
    public String getCircleId() {
        return getString(Circles.CIRCLE_ID);
    }

    @VisibleForTesting
    public String getCircleName() {
        int type = getCircleType();
        if (type != -1) {
            Bundle groupNames = this.mMetadata.getBundle(BundleKeys.LOCALIZED_GROUP_NAMES);
            if (groupNames != null) {
                String localized = groupNames.getString(String.valueOf(type));
                if (!TextUtils.isEmpty(localized)) {
                    return localized;
                }
            }
        }
        return getString(AccountSettingsColumns.NAME);
    }

    @VisibleForTesting
    public String getSortKey() {
        return getString(People.SORT_KEY);
    }

    @VisibleForTesting
    public int getCircleType() {
        int type = getInteger(OutgoingRmqColumns.PROTOBUF_TAG);
        switch (type) {
            case LogSource.UNKNOWN /*-1*/:
            case Type.TEMPORARY /*1*/:
            case Type.INDEFINITELY /*2*/:
            case Type.CUSTOM /*3*/:
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                return type;
            default:
                return -2;
        }
    }

    @VisibleForTesting
    public int getVisibility() {
        Bundle visibilities = this.mMetadata.getBundle(BundleKeys.CIRCLE_VISIBILITY);
        if (visibilities != null && visibilities.containsKey(getCircleId())) {
            return visibilities.getInt(getCircleId());
        }
        return 0;
    }

    @VisibleForTesting
    public int getPeopleCount() {
        return getInteger(Circles.PEOPLE_COUNT);
    }

    @VisibleForTesting
    public long getLastModifiedTime() {
        return getLong(People.LAST_MODIFIED_TIME);
    }

    private int getClientPolicy() {
        return getInteger(Circles.CLIENT_POLICIES);
    }

    @VisibleForTesting
    public boolean policyCannotViewMembership() {
        return (getClientPolicy() & 1) != 0;
    }

    @VisibleForTesting
    public boolean policyCannotModifyMembership() {
        return (getClientPolicy() & 2) != 0;
    }

    @VisibleForTesting
    public boolean policyCannotAclTo() {
        return (getClientPolicy() & 8) != 0;
    }

    @VisibleForTesting
    public boolean policyVisibleOnlyWhenPopulated() {
        return (getClientPolicy() & 16) != 0;
    }

    @VisibleForTesting
    public boolean isSyncToContactsEnabled() {
        return getBoolean(Owners.SYNC_TO_CONTACTS_DEPRECATED);
    }

    @VisibleForTesting
    public boolean isEnabledForSharing() {
        return getBoolean(Circles.FOR_SHARING);
    }
}
