package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "GetAndAdvanceOtpCounterResponseCreator")
public class GetAndAdvanceOtpCounterResponse implements SafeParcelable {
    public static final GetAndAdvanceOtpCounterResponseCreator CREATOR;
    private static final int VERSION = 1;
    @Field(id = 2)
    public final Long counter;
    @VersionField(id = 1)
    final int mVersion;

    static {
        CREATOR = new GetAndAdvanceOtpCounterResponseCreator();
    }

    @Constructor
    GetAndAdvanceOtpCounterResponse(@Param(id = 1) int version, @Param(id = 2) Long counter) {
        this.mVersion = version;
        this.counter = counter;
    }

    public GetAndAdvanceOtpCounterResponse(Long counter) {
        this(VERSION, counter);
    }

    public void writeToParcel(Parcel dest, int flags) {
        GetAndAdvanceOtpCounterResponseCreator.writeToParcel(this, dest, flags);
    }

    public int describeContents() {
        return 0;
    }
}
