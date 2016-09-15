package com.google.android.gms.common.stats;

import android.content.ComponentName;
import com.google.android.gms.common.GooglePlayServicesUtil;

public final class LoggingConstants {
    public static final String ALARM_LOG_FILE_NAME = ".alarms";
    public static final String CONNECTION_LOG_FILE_NAME = ".service_connections";
    static final boolean DEBUG = false;
    static final String DELIMITER = "\t";
    public static final String EXTRA_LOG_EVENT = "com.google.android.gms.common.stats.EXTRA_LOG_EVENT";
    public static final String EXTRA_STARTED_BY = "com.google.android.gms.common.statsEXTRA_STARTED_BY_COMPONENT";
    public static final String EXTRA_STARTED_BY_PROCESS = "com.google.android.gms.common.statsEXTRA_STARTED_BY_PROCESS";
    public static final String EXTRA_WAKE_LOCK_KEY = "WAKE_LOCK_KEY";
    public static int IGNORE_INTRA_PROCESS = 0;
    public static int LOG_CLOSE_EVENTS = 0;
    public static final String LOG_FILE_PREFIX = "stats";
    public static int LOG_LEVEL_OFF = 0;
    public static int LOG_MEM_INFO = 0;
    public static int LOG_OPEN_EVENTS = 0;
    public static int LOG_TOO_LONG_EVENTS = 0;
    public static int LOG_VERBOSE = 0;
    public static int LOG_WAKEFUL_ALARMS = 0;
    private static final String PACKAGE_NAME = "com.google.android.gms.common.stats";
    static final String REPEAT_FIELD_SEPARATOR = ",";
    public static final ComponentName STATS_SERVICE_COMPONENT_NAME;
    public static final String WAKE_LOCK_LOG_FILE_NAME = ".wakelocks";

    private LoggingConstants() {
    }

    static {
        STATS_SERVICE_COMPONENT_NAME = new ComponentName(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, "com.google.android.gms.common.stats.GmsCoreStatsService");
        LOG_LEVEL_OFF = 0;
        LOG_TOO_LONG_EVENTS = 1;
        LOG_OPEN_EVENTS = 2;
        LOG_CLOSE_EVENTS = 4;
        LOG_VERBOSE = 8;
        IGNORE_INTRA_PROCESS = 16;
        LOG_MEM_INFO = 32;
        LOG_WAKEFUL_ALARMS = 1;
    }
}
