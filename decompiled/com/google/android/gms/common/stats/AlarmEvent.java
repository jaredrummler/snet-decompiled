package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.snet.Csv;
import java.util.List;
import javax.annotation.Nullable;

@Class(creator = "AlarmEventCreator")
public final class AlarmEvent implements SafeParcelable {
    public static final Creator<AlarmEvent> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getCallingPackages", id = 8)
    private final List<String> mCallingPackages;
    @Field(getter = "getDeviceState", id = 10)
    private int mDeviceState;
    @Field(getter = "getFlags", id = 9)
    private final int mFlags;
    @Field(getter = "getHostPackage", id = 11)
    private final String mHostPackageName;
    @Field(getter = "getIntervalMillis", id = 7)
    private final long mIntervalMillis;
    @Field(getter = "getName", id = 4)
    private final String mName;
    @Field(getter = "getPowerPercentage", id = 12)
    private final float mPowerPercentage;
    @Field(getter = "getTimeMillis", id = 2)
    private final long mTimeMillis;
    @Field(getter = "getTriggerAfterMillis", id = 5)
    private final long mTriggerAfterMillis;
    @Field(getter = "getType", id = 3)
    private int mType;
    @VersionField(id = 1)
    final int mVersionCode;
    @Field(getter = "getWindowMillis", id = 6)
    private final long mWindowMillis;

    static {
        CREATOR = new AlarmEventCreator();
    }

    @Constructor
    AlarmEvent(@Param(id = 1) int versionCode, @Param(id = 2) long timeMillis, @Param(id = 3) int type, @Param(id = 4) String name, @Param(id = 5) long triggerAfterMillis, @Param(id = 6) long windowMillis, @Param(id = 7) long intervalMillis, @Param(id = 8) List<String> callingPackages, @Param(id = 9) int flags, @Param(id = 10) int deviceState, @Param(id = 11) String hostPackageName, @Param(id = 12) float beginPowerPercentage) {
        this.mVersionCode = versionCode;
        this.mTimeMillis = timeMillis;
        this.mType = type;
        this.mName = name;
        this.mTriggerAfterMillis = triggerAfterMillis;
        this.mWindowMillis = windowMillis;
        this.mIntervalMillis = intervalMillis;
        this.mCallingPackages = callingPackages;
        this.mFlags = flags;
        this.mDeviceState = deviceState;
        this.mHostPackageName = hostPackageName;
        this.mPowerPercentage = beginPowerPercentage;
    }

    public AlarmEvent(long timeMillis, int type, String name, long triggerAfterMillis, long windowMillis, long intervalMillis, List<String> callingPackages, int flags, int deviceState, String hostPackageName, float beginPowerPercentage) {
        this(VERSION_CODE, timeMillis, type, name, triggerAfterMillis, windowMillis, intervalMillis, callingPackages, flags, deviceState, hostPackageName, beginPowerPercentage);
    }

    public long getTimeMillis() {
        return this.mTimeMillis;
    }

    public int getType() {
        return this.mType;
    }

    public String getName() {
        return this.mName;
    }

    public long getTriggerAfterMillis() {
        return this.mTriggerAfterMillis;
    }

    public long getWindowMillis() {
        return this.mWindowMillis;
    }

    public long getIntervalMillis() {
        return this.mIntervalMillis;
    }

    public int getFlags() {
        return this.mFlags;
    }

    @Nullable
    public List<String> getCallingPackages() {
        return this.mCallingPackages;
    }

    public int getDeviceState() {
        return this.mDeviceState;
    }

    public String getHostPackage() {
        return this.mHostPackageName;
    }

    public float getPowerPercentage() {
        return this.mPowerPercentage;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        AlarmEventCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        return getTimeMillis() + "\t" + getType() + "\t" + getName() + "\t" + getTriggerAfterMillis() + "\t" + getWindowMillis() + "\t" + getIntervalMillis() + "\t" + getFlags() + "\t" + (getCallingPackages() == null ? BuildConfig.VERSION_NAME : TextUtils.join(Csv.COMMA, getCallingPackages())) + "\t" + getDeviceState() + "\t" + (getHostPackage() == null ? BuildConfig.VERSION_NAME : getHostPackage()) + "\t" + getPowerPercentage();
    }
}
