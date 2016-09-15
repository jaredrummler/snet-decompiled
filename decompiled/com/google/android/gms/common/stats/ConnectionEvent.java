package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.lint.BuildConfig;
import javax.annotation.Nullable;

@Class(creator = "ConnectionEventCreator")
public final class ConnectionEvent extends StatsEvent implements SafeParcelable {
    public static final Creator<ConnectionEvent> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getCallingProcess", id = 4)
    private final String mCallingProcess;
    @Field(getter = "getCallingService", id = 5)
    private final String mCallingService;
    @Field(getter = "getEventKey", id = 13)
    private final String mConnKey;
    private long mDurationMillis;
    @Field(getter = "getElapsedRealtime", id = 10)
    private final long mElapsedRealtime;
    @Field(getter = "getEventType", id = 12)
    private int mEventType;
    @Field(getter = "getHeapAlloc", id = 11)
    private final long mHeapAlloc;
    @Field(getter = "getStackTrace", id = 8)
    private final String mStackTrace;
    @Field(getter = "getTargetProcess", id = 6)
    private final String mTargetProcess;
    @Field(getter = "getTargetService", id = 7)
    private final String mTargetService;
    @Field(getter = "getTimeMillis", id = 2)
    private final long mTimeMillis;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ConnectionEventCreator();
    }

    @Constructor
    ConnectionEvent(@Param(id = 1) int versionCode, @Param(id = 2) long timeMillis, @Param(id = 12) int eventType, @Param(id = 4) String callingProcess, @Param(id = 5) String callingService, @Param(id = 6) String targetProcess, @Param(id = 7) String targetService, @Param(id = 8) String stackTrace, @Param(id = 13) String connKey, @Param(id = 10) long elapsedRealtime, @Param(id = 11) long heapAlloc) {
        this.mVersionCode = versionCode;
        this.mTimeMillis = timeMillis;
        this.mEventType = eventType;
        this.mCallingProcess = callingProcess;
        this.mCallingService = callingService;
        this.mTargetProcess = targetProcess;
        this.mTargetService = targetService;
        this.mDurationMillis = -1;
        this.mStackTrace = stackTrace;
        this.mConnKey = connKey;
        this.mElapsedRealtime = elapsedRealtime;
        this.mHeapAlloc = heapAlloc;
    }

    public ConnectionEvent(long timeMillis, int eventType, String callingProcess, String callingService, String targetProcess, String targetService, String stackTrace, String connKey, long elapsedRealtime, long heapAlloc) {
        this(VERSION_CODE, timeMillis, eventType, callingProcess, callingService, targetProcess, targetService, stackTrace, connKey, elapsedRealtime, heapAlloc);
    }

    public ConnectionEvent(ConnectionEvent event) {
        this(event.mVersionCode, event.getTimeMillis(), event.getEventType(), event.getCallingProcess(), event.getCallingService(), event.getTargetProcess(), event.getTargetService(), event.getStackTrace(), event.getEventKey(), event.getElapsedRealtime(), event.getHeapAlloc());
    }

    public long getTimeMillis() {
        return this.mTimeMillis;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public ConnectionEvent setEventType(int eventType) {
        this.mEventType = eventType;
        return this;
    }

    public String getCallingProcess() {
        return this.mCallingProcess;
    }

    public String getCallingService() {
        return this.mCallingService;
    }

    public String getTargetProcess() {
        return this.mTargetProcess;
    }

    public String getTargetService() {
        return this.mTargetService;
    }

    @Nullable
    public String getStackTrace() {
        return this.mStackTrace;
    }

    public String getEventKey() {
        return this.mConnKey;
    }

    public long getDurationMillis() {
        return this.mDurationMillis;
    }

    public long getHeapAlloc() {
        return this.mHeapAlloc;
    }

    public ConnectionEvent setDurationMillis(long durationMilis) {
        this.mDurationMillis = durationMilis;
        return this;
    }

    public long getElapsedRealtime() {
        return this.mElapsedRealtime;
    }

    public StatsEvent ReconstructCloseEvent(StatsEvent openEvent) {
        if (!(openEvent instanceof ConnectionEvent)) {
            return openEvent;
        }
        ConnectionEvent event = (ConnectionEvent) openEvent;
        return new ConnectionEvent(event).setEventType(getEventType()).setDurationMillis(getElapsedRealtime() - event.getElapsedRealtime());
    }

    public long getTimeout() {
        return 0;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ConnectionEventCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String getSpecificString() {
        return "\t" + getCallingProcess() + "/" + getCallingService() + "\t" + getTargetProcess() + "/" + getTargetService() + "\t" + (this.mStackTrace == null ? BuildConfig.VERSION_NAME : this.mStackTrace) + "\t" + getHeapAlloc();
    }

    public ConnectionEvent markTimeOut() {
        this.mEventType = 6;
        return this;
    }

    public static boolean checkEventType(StatsEvent event) {
        if (2 == event.getEventType() || 3 == event.getEventType() || 4 == event.getEventType() || VERSION_CODE == event.getEventType() || 6 == event.getEventType() || 13 == event.getEventType() || 14 == event.getEventType() || 15 == event.getEventType()) {
            return true;
        }
        return false;
    }
}
