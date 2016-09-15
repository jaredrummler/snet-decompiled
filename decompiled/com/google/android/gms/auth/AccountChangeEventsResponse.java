package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@Class(creator = "AccountChangeEventsResponseCreator")
public class AccountChangeEventsResponse implements SafeParcelable {
    public static final Creator<AccountChangeEventsResponse> CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    final List<AccountChangeEvent> mEvents;
    @VersionField(id = 1)
    final int mVersion;

    static {
        CREATOR = new AccountChangeEventsResponseCreator();
    }

    @Constructor
    AccountChangeEventsResponse(@Param(id = 1) int version, @Param(id = 2) List<AccountChangeEvent> events) {
        this.mVersion = version;
        this.mEvents = (List) Preconditions.checkNotNull(events);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> events) {
        this.mVersion = VERSION;
        this.mEvents = (List) Preconditions.checkNotNull(events);
    }

    public List<AccountChangeEvent> getEvents() {
        return this.mEvents;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventsResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
