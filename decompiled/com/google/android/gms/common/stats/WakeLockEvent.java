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

@Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent implements SafeParcelable {
    public static final Creator<WakeLockEvent> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getBeginPowerPercentage", id = 15)
    private final float mBeginPowerPercentage;
    @Field(getter = "getCallingPackages", id = 6)
    private final List<String> mCallingPackages;
    @Field(getter = "getDeviceState", id = 14)
    private int mDeviceState;
    private long mDurationMillis;
    @Field(getter = "getElapsedRealtime", id = 8)
    private final long mElapsedRealtime;
    @Field(getter = "getEventKey", id = 12)
    private final String mEventKey;
    @Field(getter = "getEventType", id = 11)
    private int mEventType;
    @Field(getter = "getHostPackage", id = 13)
    private final String mHostPackageName;
    @Field(getter = "getSecondaryWakeLockName", id = 10)
    private final String mSecondaryWakeLockName;
    @Field(getter = "getTimeMillis", id = 2)
    private final long mTimeMillis;
    @Field(getter = "getTimeout", id = 16)
    private final long mTimeout;
    @VersionField(id = 1)
    final int mVersionCode;
    @Field(getter = "getWakeLockName", id = 4)
    private final String mWakeLockName;
    @Field(getter = "getWakeLockType", id = 5)
    private final int mWakeLockType;

    static {
        CREATOR = new WakeLockEventCreator();
    }

    @Constructor
    WakeLockEvent(@Param(id = 1) int versionCode, @Param(id = 2) long timeMillis, @Param(id = 11) int eventType, @Param(id = 4) String wakelockName, @Param(id = 5) int wakelockType, @Param(id = 6) List<String> callingPackages, @Param(id = 12) String eventKey, @Param(id = 8) long elapsedRealtime, @Param(id = 14) int deviceState, @Param(id = 10) String secondaryWakeLockName, @Param(id = 13) String hostPackageName, @Param(id = 15) float beginPowerPercentage, @Param(id = 16) long timeout) {
        this.mVersionCode = versionCode;
        this.mTimeMillis = timeMillis;
        this.mEventType = eventType;
        this.mWakeLockName = wakelockName;
        this.mSecondaryWakeLockName = secondaryWakeLockName;
        this.mWakeLockType = wakelockType;
        this.mDurationMillis = -1;
        this.mCallingPackages = callingPackages;
        this.mEventKey = eventKey;
        this.mElapsedRealtime = elapsedRealtime;
        this.mDeviceState = deviceState;
        this.mHostPackageName = hostPackageName;
        this.mBeginPowerPercentage = beginPowerPercentage;
        this.mTimeout = timeout;
    }

    public WakeLockEvent(long timeMillis, int eventType, String wakelockName, int wakelockType, List<String> callingPackages, String eventKey, long elapsedRealtime, int deviceState, String secondaryWakeLockName, String hostPackageName, float beginPowerPercentage, long timeout) {
        this(VERSION_CODE, timeMillis, eventType, wakelockName, wakelockType, callingPackages, eventKey, elapsedRealtime, deviceState, secondaryWakeLockName, hostPackageName, beginPowerPercentage, timeout);
    }

    public WakeLockEvent(WakeLockEvent event) {
        this(event.getVersionCode(), event.getTimeMillis(), event.getEventType(), event.getWakeLockName(), event.getWakeLockType(), event.getCallingPackages(), event.getEventKey(), event.getElapsedRealtime(), event.getDeviceState(), event.getSecondaryWakeLockName(), event.getHostPackage(), event.getBeginPowerPercentage(), event.getTimeout());
    }

    public long getTimeMillis() {
        return this.mTimeMillis;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public StatsEvent setEventType(int eventType) {
        this.mEventType = eventType;
        return this;
    }

    public String getWakeLockName() {
        return this.mWakeLockName;
    }

    public String getSecondaryWakeLockName() {
        return this.mSecondaryWakeLockName;
    }

    public int getWakeLockType() {
        return this.mWakeLockType;
    }

    @Nullable
    public List<String> getCallingPackages() {
        return this.mCallingPackages;
    }

    public String getEventKey() {
        return this.mEventKey;
    }

    public long getDurationMillis() {
        return this.mDurationMillis;
    }

    public StatsEvent setDurationMillis(long durationMilis) {
        this.mDurationMillis = durationMilis;
        return this;
    }

    public long getElapsedRealtime() {
        return this.mElapsedRealtime;
    }

    public StatsEvent ReconstructCloseEvent(StatsEvent openEvent) {
        if (!(openEvent instanceof WakeLockEvent)) {
            return openEvent;
        }
        WakeLockEvent event = (WakeLockEvent) openEvent;
        return new WakeLockEvent(event).setEventType(getEventType()).setDurationMillis(getElapsedRealtime() - event.getElapsedRealtime());
    }

    public int getDeviceState() {
        return this.mDeviceState;
    }

    public String getHostPackage() {
        return this.mHostPackageName;
    }

    public float getBeginPowerPercentage() {
        return this.mBeginPowerPercentage;
    }

    public long getTimeout() {
        return this.mTimeout;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        WakeLockEventCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String getSpecificString() {
        return "\t" + getWakeLockName() + "\t" + getWakeLockType() + "\t" + (getCallingPackages() == null ? BuildConfig.VERSION_NAME : TextUtils.join(Csv.COMMA, getCallingPackages())) + "\t" + getDeviceState() + "\t" + (getSecondaryWakeLockName() == null ? BuildConfig.VERSION_NAME : getSecondaryWakeLockName()) + "\t" + (getHostPackage() == null ? BuildConfig.VERSION_NAME : getHostPackage()) + "\t" + getBeginPowerPercentage();
    }

    public WakeLockEvent markTimeOut() {
        if (this.mTimeout != 0) {
            this.mDurationMillis = this.mTimeout;
        }
        if (7 == this.mEventType) {
            this.mEventType = 9;
        } else if (10 == this.mEventType) {
            this.mEventType = 12;
        }
        return this;
    }

    public static boolean checkEventType(StatsEvent event) {
        if (7 == event.getEventType() || 8 == event.getEventType() || 9 == event.getEventType() || 10 == event.getEventType() || 11 == event.getEventType() || 12 == event.getEventType()) {
            return true;
        }
        return false;
    }
}
