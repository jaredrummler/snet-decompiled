package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

@Class(creator = "AccountChangeEventCreator")
public class AccountChangeEvent implements SafeParcelable {
    public static final Creator<AccountChangeEvent> CREATOR;
    private static final int VERSION = 1;
    @Field(id = 3)
    final String mAccountName;
    @Field(id = 6)
    final String mChangeData;
    @Field(id = 4)
    final int mChangeType;
    @Field(id = 5)
    final int mEventIndex;
    @Field(id = 2)
    final long mId;
    @VersionField(id = 1)
    final int mVersion;

    static {
        CREATOR = new AccountChangeEventCreator();
    }

    @Constructor
    AccountChangeEvent(@Param(id = 1) int version, @Param(id = 2) long id, @Param(id = 3) String accountName, @Param(id = 4) int changeType, @Param(id = 5) int eventIndex, @Param(id = 6) String changeData) {
        this.mVersion = version;
        this.mId = id;
        this.mAccountName = (String) Preconditions.checkNotNull(accountName);
        this.mChangeType = changeType;
        this.mEventIndex = eventIndex;
        this.mChangeData = changeData;
    }

    public AccountChangeEvent(long id, String accountName, int changeType, int eventIndex, String changeData) {
        this.mVersion = VERSION;
        this.mId = id;
        this.mAccountName = (String) Preconditions.checkNotNull(accountName);
        this.mChangeType = changeType;
        this.mEventIndex = eventIndex;
        this.mChangeData = changeData;
    }

    public String getAccountName() {
        return this.mAccountName;
    }

    public int getChangeType() {
        return this.mChangeType;
    }

    public int getEventIndex() {
        return this.mEventIndex;
    }

    public String getChangeData() {
        return this.mChangeData;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventCreator.writeToParcel(this, dest, flags);
    }

    public String toString() {
        String changeType = "UNKNOWN";
        switch (this.mChangeType) {
            case VERSION /*1*/:
                changeType = "ADDED";
                break;
            case Type.INDEFINITELY /*2*/:
                changeType = "REMOVED";
                break;
            case Type.CUSTOM /*3*/:
                changeType = "RENAMED_FROM";
                break;
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                changeType = "RENAMED_TO";
                break;
        }
        return "AccountChangeEvent {accountName = " + this.mAccountName + ", changeType = " + changeType + ", changeData = " + this.mChangeData + ", eventIndex = " + this.mEventIndex + "}";
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mVersion), Long.valueOf(this.mId), this.mAccountName, Integer.valueOf(this.mChangeType), Integer.valueOf(this.mEventIndex), this.mChangeData);
    }

    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent other = (AccountChangeEvent) that;
        if (this.mVersion == other.mVersion && this.mId == other.mId && Objects.equal(this.mAccountName, other.mAccountName) && this.mChangeType == other.mChangeType && this.mEventIndex == other.mEventIndex && Objects.equal(this.mChangeData, other.mChangeData)) {
            return true;
        }
        return false;
    }
}
