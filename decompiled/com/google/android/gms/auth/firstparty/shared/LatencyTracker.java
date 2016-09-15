package com.google.android.gms.auth.firstparty.shared;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

@Class(creator = "LatencyTrackerCreator")
public class LatencyTracker implements SafeParcelable {
    public static final LatencyTrackerCreator CREATOR;
    private static final String KEY_LATENCY_TRACKER = "latency.tracker";
    private static final String LOG_PREFIX = "[LatencyTracker]";
    private static final String TAG = "GLSLogging";
    private static final int VERSION = 1;
    @Field(id = 2)
    final String mName;
    @Field(id = 3)
    final long mStartRealtimeMillis;
    @VersionField(id = 1)
    final int mVersion;
    @Field(id = 4)
    public final LatencyTracker parent;

    static {
        CREATOR = new LatencyTrackerCreator();
    }

    public static LatencyTracker create(String name) {
        return new LatencyTracker(VERSION, name, SystemClock.elapsedRealtime(), null);
    }

    public static LatencyTracker fromIntent(Intent intent) {
        return (LatencyTracker) intent.getParcelableExtra(KEY_LATENCY_TRACKER);
    }

    public static LatencyTracker fromBundle(Bundle bundle) {
        return (LatencyTracker) bundle.getParcelable(KEY_LATENCY_TRACKER);
    }

    @Constructor
    LatencyTracker(@Param(id = 1) int version, @Param(id = 2) String name, @Param(id = 3) long startRealtimeMillis, @Param(id = 4) LatencyTracker parent) {
        this.mVersion = version;
        this.mName = name;
        this.mStartRealtimeMillis = startRealtimeMillis;
        this.parent = parent;
        log(name, "constructed");
    }

    public void log(String prefix, String eventDescription) {
        if (Log.isLoggable(TAG, 2)) {
            Log.println(2, TAG, prefix + " " + getTrace(SystemClock.elapsedRealtime()) + " > " + eventDescription);
        }
    }

    String getTrace(long currentTimeMillis) {
        LinkedList<String> traces = new LinkedList();
        for (LatencyTracker tracker = this; tracker != null; tracker = tracker.parent) {
            long durationMillis = currentTimeMillis - tracker.mStartRealtimeMillis;
            long durationRemainder = durationMillis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(durationMillis));
            traces.addFirst(String.format("[%s, %,d.%03ds]", new Object[]{tracker.mName, Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(durationMillis)), Long.valueOf(durationRemainder)}));
        }
        return TextUtils.join(" > ", traces);
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable(KEY_LATENCY_TRACKER, this);
    }

    public void toIntent(Intent intent) {
        intent.putExtra(KEY_LATENCY_TRACKER, this);
    }

    public LatencyTracker createChild(String name) {
        return new LatencyTracker(VERSION, name, SystemClock.elapsedRealtime(), this);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        log(this.mName, "writing to parcel");
        LatencyTrackerCreator.writeToParcel(this, dest, flags);
    }
}
