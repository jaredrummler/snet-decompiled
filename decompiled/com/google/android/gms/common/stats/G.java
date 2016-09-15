package com.google.android.gms.common.stats;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.lint.BuildConfig;

public final class G {
    public static GservicesValue<Integer> maxChunkSize;
    public static GservicesValue<Integer> maxNumOfEvents;

    public static final class alarms {
        public static GservicesValue<Integer> level;

        private alarms() {
        }

        static {
            level = GservicesValue.value("gms:common:stats:alarms:level", Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
        }
    }

    public static final class connections {
        public static GservicesValue<String> ignoredCallingProcesses;
        public static GservicesValue<String> ignoredCallingServices;
        public static GservicesValue<String> ignoredTargetProcesses;
        public static GservicesValue<String> ignoredTargetServices;
        public static GservicesValue<Integer> level;
        public static GservicesValue<Long> timeOutDurationMillis;

        private connections() {
        }

        static {
            level = GservicesValue.value("gms:common:stats:connections:level", Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
            ignoredCallingProcesses = GservicesValue.value("gms:common:stats:connections:ignored_calling_processes", BuildConfig.VERSION_NAME);
            ignoredCallingServices = GservicesValue.value("gms:common:stats:connections:ignored_calling_services", BuildConfig.VERSION_NAME);
            ignoredTargetProcesses = GservicesValue.value("gms:common:stats:connections:ignored_target_processes", BuildConfig.VERSION_NAME);
            ignoredTargetServices = GservicesValue.value("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
            timeOutDurationMillis = GservicesValue.value("gms:common:stats:connections:time_out_duration", Long.valueOf(600000));
        }
    }

    public static final class networkUsage {
        public static final GservicesValue<Long> flexTimeInSeconds;
        public static final GservicesValue<Long> intervalInSeconds;
        public static final GservicesValue<Boolean> isNetworkCallbackActive;
        public static GservicesValue<Integer> level;

        private networkUsage() {
        }

        static {
            level = GservicesValue.value("gms:common:stats:network_usage:level", Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
            intervalInSeconds = GservicesValue.value("gms:common:stats:network_usage:interval_in_seconds", Long.valueOf(0));
            flexTimeInSeconds = GservicesValue.value("gms:common:stats:network_usage:flex_time_in_seconds", Long.valueOf(3600));
            isNetworkCallbackActive = GservicesValue.value("gms:common:stats:is_receiver_active", false);
        }
    }

    public static final class wakeLocks {
        public static GservicesValue<Integer> level;
        public static GservicesValue<Long> timeOutDurationMillis;

        private wakeLocks() {
        }

        static {
            level = GservicesValue.value("gms:common:stats:wakeLocks:level", Integer.valueOf(LoggingConstants.LOG_LEVEL_OFF));
            timeOutDurationMillis = GservicesValue.value("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000));
        }
    }

    static {
        maxNumOfEvents = GservicesValue.value("gms:common:stats:max_num_of_events", Integer.valueOf(100));
        maxChunkSize = GservicesValue.value("gms:common:stats:max_chunk_size", Integer.valueOf(100));
    }

    private G() {
    }
}
