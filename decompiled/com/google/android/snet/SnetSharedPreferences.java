package com.google.android.snet;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

class SnetSharedPreferences {
    static final String DEFAULT_STRING = "";
    static final long DEFAULT_TIMESTAMP = -1;
    private static final String KEY_FILENAME_USED = "snet_jpeg_filename";
    private static final String KEY_JPEG_PREVIOUSLY_TAMPERED = "snet_jpeg_previously_tampered";
    private static final String KEY_JPEG_WRITTEN = "snet_jpeg_written_to_sdcard";
    private static final String KEY_LAST_SUCCESSFUL_IDLE_MODE_TAG = "snet_last_successful_idle_mode_tag";
    private static final String LAST_IDLE_MODE_RUN_TIMESTAMP_MS = "snet_last_idle_run_timestamp";
    private static final String LAST_NORMAL_MODE_RUN_TIMESTAMP_MS = "snet_last_run_timestamp";
    private static final String SNET_EVENT_LOG_LAST_TIMESTAMP_MS = "snet_event_log_last_timestamp";
    private SharedPreferences mPreferences;

    SnetSharedPreferences(Context context) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    long lastEventLogTimestampMs() {
        return this.mPreferences.getLong(SNET_EVENT_LOG_LAST_TIMESTAMP_MS, DEFAULT_TIMESTAMP);
    }

    void saveLastEventLogTimestampMs(long lastTimestamp) {
        putLong(SNET_EVENT_LOG_LAST_TIMESTAMP_MS, lastTimestamp);
    }

    boolean sdCardJpegWritten() {
        return this.mPreferences.getBoolean(KEY_JPEG_WRITTEN, false);
    }

    void saveSdCardJpegWritten(boolean written) {
        putBoolean(KEY_JPEG_WRITTEN, written);
    }

    boolean sdCardJpegPreviouslyTampered() {
        return this.mPreferences.getBoolean(KEY_JPEG_PREVIOUSLY_TAMPERED, false);
    }

    void saveSdCardJpegPreviouslyTampered(boolean tampered) {
        putBoolean(KEY_JPEG_PREVIOUSLY_TAMPERED, tampered);
    }

    String sdCardJpegFileNameUsed() {
        return this.mPreferences.getString(KEY_FILENAME_USED, DEFAULT_STRING);
    }

    void saveSdCardJpegFileNameUsed(String jpegFileName) {
        putString(KEY_FILENAME_USED, jpegFileName);
    }

    long lastIdleModeRunTimestampMs() {
        return this.mPreferences.getLong(LAST_IDLE_MODE_RUN_TIMESTAMP_MS, DEFAULT_TIMESTAMP);
    }

    void saveLastIdleModeRunTimestampMs(long lastRunTimestampMs) {
        putLong(LAST_IDLE_MODE_RUN_TIMESTAMP_MS, lastRunTimestampMs);
    }

    long lastNormalModeRunTimestampMs() {
        return this.mPreferences.getLong(LAST_NORMAL_MODE_RUN_TIMESTAMP_MS, DEFAULT_TIMESTAMP);
    }

    void saveLastNormalModeRunTimestampMs(long lastRunTimestampMs) {
        putLong(LAST_NORMAL_MODE_RUN_TIMESTAMP_MS, lastRunTimestampMs);
    }

    String lastIdleModeTag() {
        return this.mPreferences.getString(KEY_LAST_SUCCESSFUL_IDLE_MODE_TAG, "done");
    }

    void saveLastIdleModeTag(String idleModeTag) {
        putString(KEY_LAST_SUCCESSFUL_IDLE_MODE_TAG, idleModeTag);
    }

    private void putLong(String key, long value) {
        Editor editor = this.mPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    private void putBoolean(String key, boolean value) {
        Editor editor = this.mPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void putString(String key, String value) {
        Editor editor = this.mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
