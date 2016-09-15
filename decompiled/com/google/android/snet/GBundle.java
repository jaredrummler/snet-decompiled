package com.google.android.snet;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.lint.BuildConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GBundle {
    private final Bundle mBundle;
    private Map<String, String> mOtherValues;

    GBundle(Bundle bundle) {
        this.mOtherValues = null;
        this.mBundle = bundle;
    }

    int getMaxExceptionStringLength() {
        return this.mBundle.getInt("snet_max_exception_string_size");
    }

    String getEventLogTags() {
        return this.mBundle.getString("snet_report_event_logs");
    }

    int getMaxSetuidFileChars() {
        return this.mBundle.getInt("snet_max_setuid_files_chars", 2000);
    }

    String getUuid() {
        return this.mBundle.getString("snet_uuid");
    }

    boolean getLogExecutionPoints() {
        return this.mBundle.getBoolean("snet_log_execution_points");
    }

    boolean getReportNonSystemApps() {
        return this.mBundle.getBoolean("snet_report_non_system_apps");
    }

    boolean getReportSystemApps() {
        return this.mBundle.getBoolean("snet_report_system_apps");
    }

    boolean getReportMoreAppInfo() {
        return this.mBundle.getBoolean("snet_report_more_app_info");
    }

    boolean getReportGooglePage() {
        return this.mBundle.getBoolean("snet_report_google_page");
    }

    boolean getReportSuspiciousGooglePage() {
        return this.mBundle.getBoolean("snet_report_suspicious_google_page");
    }

    boolean getReportGooglePageInfo() {
        return this.mBundle.getBoolean("snet_report_google_page_info");
    }

    int getGoogleWebpageThreshold() {
        return this.mBundle.getInt("snet_google_webpage_threshold");
    }

    boolean getReportSslV3Tests() {
        return this.mBundle.getBoolean("snet_report_ssl_v3_tests");
    }

    boolean getReportProxy() {
        return this.mBundle.getBoolean("snet_report_proxy");
    }

    int getMaxDnsQueryResponseLength() {
        return this.mBundle.getInt("snet_max_dns_query_response_length");
    }

    String getDebugStatus() {
        return this.mBundle.getString("snet_debug_status");
    }

    long getWakeIntervalMs() {
        return this.mBundle.getLong("snet_wake_interval_ms");
    }

    String getSdCardJpegName() {
        String value = this.mBundle.getString("snet_sd_card_jpeg_name");
        if (TextUtils.isEmpty(value)) {
            return "gmsnet2.jpg";
        }
        return value;
    }

    String getLogcatTags() {
        return this.mBundle.getString("snet_logcat_tags");
    }

    int getNumLogcatLines() {
        return this.mBundle.getInt("snet_logcat_lines");
    }

    private String getOtherValue(String key) {
        return (String) getOtherValues().get(key);
    }

    Map<String, String> getOtherValues() {
        if (this.mOtherValues != null) {
            return this.mOtherValues;
        }
        String otherValues = this.mBundle.getString("snet_other_values");
        if (TextUtils.isEmpty(otherValues)) {
            this.mOtherValues = new HashMap();
            return this.mOtherValues;
        }
        List<String> parsedValues = new ArrayList();
        try {
            Csv.parseLine(new BufferedReader(new StringReader(otherValues)), parsedValues);
            Map<String, String> valuesMap = new HashMap();
            for (String parsedValue : parsedValues) {
                String[] splitValues = parsedValue.split("=", 2);
                if (splitValues.length == 1) {
                    valuesMap.put(splitValues[0], BuildConfig.VERSION_NAME);
                } else {
                    valuesMap.put(splitValues[0], splitValues[1]);
                }
            }
            this.mOtherValues = valuesMap;
            return valuesMap;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    String getSignalTagsWhitelist() {
        return this.mBundle.getString("snet_tags_whitelist");
    }

    String getIdleSignalTagsWhitelist() {
        return this.mBundle.getString("snet_idle_tags_whitelist");
    }

    String getSharedUuid() {
        return this.mBundle.getString("snet_shared_uuid");
    }

    Bundle getAttestBundle() {
        return this.mBundle.getBundle("snet_attest_bundle");
    }

    int getNumRandomSystemPartitionFiles() {
        return this.mBundle.getInt("snet_num_sys_part_files");
    }

    String getSystemPartitionFilesOfInterest() {
        return this.mBundle.getString("snet_sys_part_files");
    }

    int getNumRandomSystemCaCerts() {
        return this.mBundle.getInt("snet_num_sys_ca_certs");
    }

    boolean getLogEntireSystemCaCert() {
        return this.mBundle.getBoolean("snet_log_sys_ca_cert");
    }

    String getIdleModeCachedProtoFileName() {
        String value = this.mBundle.getString("snet_idle_mode_cached_proto_file_name");
        if (TextUtils.isEmpty(value)) {
            return "snet_idle.proto";
        }
        return value;
    }

    boolean getIsSidewinderDevice() {
        return this.mBundle.getBoolean("snet_is_sidewinder_device");
    }

    boolean getClearcutIdleLoggingEnabled() {
        return this.mBundle.getBoolean("snet_clearcut_idle_logging_enabled");
    }

    boolean getClearcutJarLoggingEnabled() {
        return this.mBundle.getBoolean("snet_clearcut_jar_logging_enabled");
    }

    boolean getDropboxIdleLoggingEnabled() {
        return this.mBundle.getBoolean("snet_dropbox_idle_logging_enabled");
    }

    boolean getDropboxJarLoggingEnabled() {
        return this.mBundle.getBoolean("snet_dropbox_jar_logging_enabled");
    }

    String getRequestedCertsBySha256() {
        return this.mBundle.getString("snet_requested_ca_certs");
    }

    Bundle getSmartLockBundle() {
        return this.mBundle.getBundle("snet_smart_lock_bundle");
    }

    String getSicServerUrl() {
        return this.mBundle.getString("snet_sic_server_url");
    }

    int getSpkiWhitelistResId() {
        return this.mBundle.getInt("snet_spki_whitelist_res_id");
    }
}
