package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@Class(creator = "LogEventCreator")
public class LogEvent implements SafeParcelable {
    private static final String CLOSE_PAREN = ")";
    private static final String COMMA = ",";
    public static final LogEventCreator CREATOR;
    private static final String OPEN_PAREN = "(";
    private static final String SPACE = " ";
    private static final String TAG = "LogEvent";
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    public final long eventTime;
    @Field(id = 6)
    public final long eventUptime;
    @Field(id = 5)
    public final Bundle keyValuePairs;
    @Field(id = 4)
    public final byte[] sourceExtensionBytes;
    @Field(id = 3)
    public final String tag;
    @VersionField(id = 1)
    public final int versionCode;

    static {
        CREATOR = new LogEventCreator();
    }

    @Constructor
    LogEvent(@Param(id = 1) int versionCode, @Param(id = 2) long eventTime, @Param(id = 6) long eventUptime, @Param(id = 3) String tag, @Param(id = 4) byte[] sourceExtensionBytes, @Param(id = 5) Bundle keyValuePairs) {
        this.versionCode = versionCode;
        this.eventTime = eventTime;
        this.eventUptime = eventUptime;
        this.tag = tag;
        this.sourceExtensionBytes = sourceExtensionBytes;
        this.keyValuePairs = keyValuePairs;
    }

    @VisibleForTesting
    public LogEvent(long eventTime, long eventUptime, String tag, byte[] sourceExtensionBytes, String... extras) {
        this.versionCode = VERSION_CODE;
        this.eventTime = eventTime;
        this.eventUptime = eventUptime;
        this.tag = tag;
        this.sourceExtensionBytes = sourceExtensionBytes;
        this.keyValuePairs = createFromStringArray(extras);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        LogEventCreator.writeToParcel(this, out, flags);
    }

    private static Bundle createFromStringArray(String... extras) {
        Bundle bundle = null;
        if (extras != null) {
            if (extras.length % 2 != 0) {
                throw new IllegalArgumentException("extras must have an even number of elements");
            }
            int numPairs = extras.length / 2;
            if (numPairs != 0) {
                bundle = new Bundle(numPairs);
                for (int i = 0; i < numPairs; i += VERSION_CODE) {
                    bundle.putString(extras[i * 2], extras[(i * 2) + VERSION_CODE]);
                }
            }
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tag=").append(this.tag).append(COMMA);
        sb.append("eventTime=").append(this.eventTime).append(COMMA);
        sb.append("eventUptime=").append(this.eventUptime).append(COMMA);
        if (!(this.keyValuePairs == null || this.keyValuePairs.isEmpty())) {
            sb.append("keyValues=");
            for (String key : this.keyValuePairs.keySet()) {
                sb.append(OPEN_PAREN).append(key).append(COMMA);
                sb.append(this.keyValuePairs.getString(key)).append(CLOSE_PAREN);
                sb.append(SPACE);
            }
        }
        return sb.toString();
    }
}
