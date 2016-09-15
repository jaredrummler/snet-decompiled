package com.google.android.gms.common.stats;

public abstract class StatsEvent {

    public interface Types {
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        public static final int EVENT_TYPE_BIND = 2;
        public static final int EVENT_TYPE_CONNECT = 3;
        public static final int EVENT_TYPE_CONNECTION_TIME_OUT = 6;
        public static final int EVENT_TYPE_DISCONNECT = 4;
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
        public static final int EVENT_TYPE_START_SERVICE = 13;
        public static final int EVENT_TYPE_STOP_ALL_SERVICE = 15;
        public static final int EVENT_TYPE_STOP_SERVICE = 14;
        public static final int EVENT_TYPE_SYNC_END = 11;
        public static final int EVENT_TYPE_SYNC_START = 10;
        public static final int EVENT_TYPE_SYNC_TIME_OUT = 12;
        public static final int EVENT_TYPE_UNBIND = 1;
        public static final int EVENT_TYPE_UNKNOWN = -1;
        public static final int EVENT_TYPE_WAKE_LOCK_TIME_OUT = 9;
    }

    public abstract StatsEvent ReconstructCloseEvent(StatsEvent statsEvent);

    public abstract long getDurationMillis();

    public abstract long getElapsedRealtime();

    public abstract String getEventKey();

    public abstract int getEventType();

    public abstract String getSpecificString();

    public abstract long getTimeMillis();

    public abstract long getTimeout();

    public abstract StatsEvent markTimeOut();

    public abstract StatsEvent setDurationMillis(long j);

    public abstract StatsEvent setEventType(int i);

    public String toString() {
        return getTimeMillis() + "\t" + getEventType() + "\t" + getDurationMillis() + getSpecificString();
    }
}
