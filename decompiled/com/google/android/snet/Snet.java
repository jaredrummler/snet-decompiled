package com.google.android.snet;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.snet.MxRecordHijackAnalyzer.MxInfo;
import com.google.android.snet.NetworkAnalyzer.SslRedirectInfo;
import com.google.android.snet.ProxyAnalyzer.ProxyInfo;
import com.google.android.snet.SnetLogcat.LogcatInfo;
import com.google.android.snet.SslHandshakeAnalyzer.SslInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Snet {
    static final String DONE_TAG = "done";
    private static final String GOOGLE_ACCOUNT_SUFFIX = "@google.com";
    private static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    private static final String LOG_APPS_TAG = "apps";
    private static final String LOG_ATTESTATION_TAG = "attest";
    private static final String LOG_CAPTIVE_PORTAL_TEST_TAG = "captive_portal_test";
    private static final String LOG_DALVIK_CACHE_TAG = "dalvik_cache_monitor";
    private static final String LOG_DEVICE_ADMIN_TAG = "device_admin_deactivator";
    private static final String LOG_DEVICE_STATE_TAG = "device_state";
    private static final String LOG_EVENT_LOG_TAG = "event_log";
    private static final String LOG_FILES_TAG = "su_files";
    private static final String LOG_GMSCORE_INFO_TAG = "gmscore";
    private static final String LOG_GOOGLE_PAGE_INFO_TAG = "google_page_info";
    private static final String LOG_GOOGLE_PAGE_TAG = "google_page";
    private static final String LOG_HANDSHAKE_TAG = "ssl_handshake";
    private static final String LOG_LOCALE_TAG = "locale";
    private static final String LOG_LOGCAT_TAG = "logcat";
    private static final String LOG_MX_RECORDS_TAG = "mx_record";
    private static final String LOG_PACKAGES_TAG = "default_packages";
    private static final String LOG_PROXY_TAG = "proxy";
    private static final String LOG_REDIRECT_TAG = "ssl_redirect";
    private static final String LOG_SD_CARD_TAG = "sd_card_test";
    private static final String LOG_SELINUX_TAG = "selinux_status";
    private static final String LOG_SETTINGS_TAG = "settings";
    private static final String LOG_SETUID_TAG = "setuid_files";
    private static final String LOG_SSLV3_TAG = "sslv3_fallback";
    private static final String LOG_SUSPICIOUS_PAGE_TAG = "suspicious_google_page";
    private static final String LOG_SYSTEM_CA_CERT_STORE_TAG = "system_ca_cert_store";
    private static final String LOG_SYSTEM_PARTITION_FILES_TAG = "system_partition_files";
    private static final String TAG;
    static final int VERSION_INVALID = -1;
    private Context mCtx;
    private GBundle mGBundle;
    private SnetIdleLogger mSnetIdleLogger;
    private SnetLogger mSnetLogger;

    private void logAllInfoIdleMode() {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x01da in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:58)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r24 = this;
        r18 = java.lang.System.currentTimeMillis();
        r13 = new com.google.android.snet.SnetSharedPreferences;
        r0 = r24;
        r0 = r0.mCtx;
        r20 = r0;
        r0 = r20;
        r13.<init>(r0);
        r10 = r13.lastIdleModeTag();
        r20 = TAG;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r21 = "Hi Snet!";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        android.util.Log.d(r20, r21);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r0.mGBundle;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r15 = r20.getIdleSignalTagsWhitelist();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r0.mSnetIdleLogger;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0.setSignalTagsWhitelist(r15);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r15 == 0) goto L_0x00db;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0033:
        r20 = ",";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r16 = r15.split(r0);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = "done";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r10);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 != 0) goto L_0x007f;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0045:
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r0.mSnetIdleLogger;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20.loadSavedProto();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r17 = new java.util.ArrayList;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r17.<init>();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r4 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r5 = r16;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r11 = r5.length;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r8 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0058:
        if (r8 >= r11) goto L_0x006d;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x005a:
        r14 = r5[r8];	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r4 == 0) goto L_0x0063;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x005e:
        r0 = r17;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0.add(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0063:
        r20 = r10.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x006a;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0069:
        r4 = 1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x006a:
        r8 = r8 + 1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x0058;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x006d:
        r20 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = new java.lang.String[r0];	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r17;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r1 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r16 = r0.toArray(r1);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r16 = (java.lang.String[]) r16;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x007f:
        r9 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r5 = r16;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r11 = r5.length;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r8 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0084:
        if (r8 >= r11) goto L_0x00d7;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0086:
        r14 = r5[r8];	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r10 = r14;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = "system_partition_files";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x00fc;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0093:
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r1 = r18;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0.logSystemPartitionFiles(r1);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x009a:
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r0.mCtx;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r21 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r22 = new android.content.IntentFilter;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r23 = "android.intent.action.BATTERY_CHANGED";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r22.<init>(r23);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r6 = r20.registerReceiver(r21, r22);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = "plugged";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r21 = -1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r1 = r21;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r12 = r6.getIntExtra(r0, r1);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = 1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r12 == r0) goto L_0x01c1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x00bf:
        r20 = 2;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r12 == r0) goto L_0x01c1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x00c5:
        r0 = r16;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r0.length;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r20 + -1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r16[r20];	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r14.equals(r0);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 != 0) goto L_0x01c1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x00d6:
        r9 = 1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x00d7:
        if (r9 != 0) goto L_0x00db;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x00d9:
        r10 = "done";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x00db:
        r20 = "done";
        r0 = r20;
        r20 = r0.equals(r10);
        if (r20 == 0) goto L_0x01c5;
    L_0x00e5:
        r0 = r24;
        r0 = r0.mSnetIdleLogger;
        r20 = r0;
        r21 = getVersion();
        r0 = r20;
        r1 = r21;
        r2 = r18;
        r0.writeToBackend(r1, r2);
    L_0x00f8:
        r13.saveLastIdleModeTag(r10);
    L_0x00fb:
        return;
    L_0x00fc:
        r20 = "system_ca_cert_store";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x0137;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0106:
        r24.logSystemCaCertStore();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;
    L_0x010a:
        r7 = move-exception;
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r0.mSnetIdleLogger;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0.writeExceptionDetailed(r7);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = "done";
        r0 = r20;
        r20 = r0.equals(r10);
        if (r20 == 0) goto L_0x01da;
    L_0x0120:
        r0 = r24;
        r0 = r0.mSnetIdleLogger;
        r20 = r0;
        r21 = getVersion();
        r0 = r20;
        r1 = r21;
        r2 = r18;
        r0.writeToBackend(r1, r2);
    L_0x0133:
        r13.saveLastIdleModeTag(r10);
        goto L_0x00fb;
    L_0x0137:
        r20 = "setuid_files";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x016c;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0141:
        r0 = r24;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r1 = r18;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0.logSetuidFiles(r1);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;
    L_0x014a:
        r20 = move-exception;
        r21 = "done";
        r0 = r21;
        r21 = r0.equals(r10);
        if (r21 == 0) goto L_0x01ef;
    L_0x0155:
        r0 = r24;
        r0 = r0.mSnetIdleLogger;
        r21 = r0;
        r22 = getVersion();
        r0 = r21;
        r1 = r22;
        r2 = r18;
        r0.writeToBackend(r1, r2);
    L_0x0168:
        r13.saveLastIdleModeTag(r10);
        throw r20;
    L_0x016c:
        r20 = "logcat";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x017b;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0176:
        r24.logLogcatTagsOfInterest();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x017b:
        r20 = "event_log";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x018a;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0185:
        r24.logEventLogTagsOfInterestIdleMode();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x018a:
        r20 = "dalvik_cache_monitor";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x0199;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0194:
        r24.logDalvikCacheFiles();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x0199:
        r20 = "device_state";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r20;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r20 = r0.equals(r14);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        if (r20 == 0) goto L_0x01a8;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x01a3:
        r24.logDeviceState();	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
    L_0x01a8:
        r20 = TAG;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r21 = "Unknown tag: %s";	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r22 = 1;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = r22;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r0 = new java.lang.Object[r0];	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r22 = r0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r23 = 0;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r22[r23] = r14;	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        r21 = java.lang.String.format(r21, r22);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        android.util.Log.e(r20, r21);	 Catch:{ RuntimeException -> 0x010a, all -> 0x014a }
        goto L_0x009a;
    L_0x01c1:
        r8 = r8 + 1;
        goto L_0x0084;
    L_0x01c5:
        r0 = r24;
        r0 = r0.mSnetIdleLogger;
        r20 = r0;
        r21 = getVersion();
        r0 = r20;
        r1 = r21;
        r2 = r18;
        r0.saveProto(r1, r2);
        goto L_0x00f8;
    L_0x01da:
        r0 = r24;
        r0 = r0.mSnetIdleLogger;
        r20 = r0;
        r21 = getVersion();
        r0 = r20;
        r1 = r21;
        r2 = r18;
        r0.saveProto(r1, r2);
        goto L_0x0133;
    L_0x01ef:
        r0 = r24;
        r0 = r0.mSnetIdleLogger;
        r21 = r0;
        r22 = getVersion();
        r0 = r21;
        r1 = r22;
        r2 = r18;
        r0.saveProto(r1, r2);
        goto L_0x0168;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.Snet.logAllInfoIdleMode():void");
    }

    static {
        TAG = Snet.class.getCanonicalName();
    }

    static int getVersion() {
        IOException ex;
        Throwable th;
        NumberFormatException ex2;
        int i = VERSION_INVALID;
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(Snet.class.getResourceAsStream("/version")));
            try {
                i = Integer.parseInt(reader2.readLine());
                if (reader2 != null) {
                    try {
                        reader2.close();
                    } catch (IOException ex3) {
                        Log.e(TAG, "Error occurred while closing version file", ex3);
                    }
                }
            } catch (IOException e) {
                ex3 = e;
                reader = reader2;
                try {
                    Log.e(TAG, "Error occurred while reading version", ex3);
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex32) {
                            Log.e(TAG, "Error occurred while closing version file", ex32);
                        }
                    }
                    return i;
                } catch (Throwable th2) {
                    th = th2;
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex322) {
                            Log.e(TAG, "Error occurred while closing version file", ex322);
                        }
                    }
                    throw th;
                }
            } catch (NumberFormatException e2) {
                ex2 = e2;
                reader = reader2;
                Log.e(TAG, "Error occurred while parsing version", ex2);
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex3222) {
                        Log.e(TAG, "Error occurred while closing version file", ex3222);
                    }
                }
                return i;
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (IOException e3) {
            ex3222 = e3;
            Log.e(TAG, "Error occurred while reading version", ex3222);
            if (reader != null) {
                reader.close();
            }
            return i;
        } catch (NumberFormatException e4) {
            ex2 = e4;
            Log.e(TAG, "Error occurred while parsing version", ex2);
            if (reader != null) {
                reader.close();
            }
            return i;
        }
        return i;
    }

    public static void enterSnet(Context ctx, Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "Missing bundle.");
        } else {
            new Snet(ctx, bundle).logAllInfoNormalMode();
        }
    }

    public static void enterSnetIdle(Context ctx, Bundle bundle) {
        if (bundle == null) {
            Log.e(TAG, "Missing bundle.");
        } else {
            new Snet(ctx, bundle).logAllInfoIdleMode();
        }
    }

    private Snet(Context ctx, Bundle bundle) {
        this.mCtx = ctx;
        this.mGBundle = new GBundle(bundle);
        this.mSnetLogger = new SnetLogger(ctx, this.mGBundle);
        this.mSnetIdleLogger = new SnetIdleLogger(ctx, this.mGBundle);
    }

    private void logAllInfoNormalMode() {
        long startTimeMs = System.currentTimeMillis();
        Log.d(TAG, "Hello Snet!");
        String signalTags = this.mGBundle.getSignalTagsWhitelist();
        this.mSnetLogger.setSignalTagsWhitelist(signalTags);
        if (signalTags != null) {
            List<SslRedirectInfo> sslRedirectInfoList = null;
            List<SslInfo> sslHandshakeInfoList = null;
            String googleWebpageHtml = null;
            for (String signalTag : signalTags.split(Csv.COMMA)) {
                updateExecutionPoint(signalTag);
                if (LOG_PACKAGES_TAG.equals(signalTag)) {
                    logPreferredPackages();
                } else if (LOG_FILES_TAG.equals(signalTag)) {
                    logFiles();
                } else if (LOG_SETTINGS_TAG.equals(signalTag)) {
                    logSettings();
                } else {
                    try {
                        if (LOG_LOCALE_TAG.equals(signalTag)) {
                            logLocale();
                        } else if (LOG_REDIRECT_TAG.equals(signalTag)) {
                            sslRedirectInfoList = logSslRedirectAnalysis();
                        } else if (LOG_HANDSHAKE_TAG.equals(signalTag)) {
                            sslHandshakeInfoList = logSslHandshakeAnalysis();
                        } else if (LOG_MX_RECORDS_TAG.equals(signalTag)) {
                            logMxRecords();
                        } else if (LOG_SSLV3_TAG.equals(signalTag)) {
                            logHttpsSslv3FallbackAnalysis();
                        } else if (LOG_PROXY_TAG.equals(signalTag)) {
                            logProxyInfo();
                        } else if (LOG_SELINUX_TAG.equals(signalTag)) {
                            logSeLinuxInfo();
                        } else if (LOG_SD_CARD_TAG.equals(signalTag)) {
                            logSdCardAnalysis();
                        } else if (LOG_APPS_TAG.equals(signalTag)) {
                            if (hasGoogleAccount()) {
                                logApps();
                            }
                        } else if (LOG_GOOGLE_PAGE_TAG.equals(signalTag)) {
                            if (googleWebpageHtml == null) {
                                googleWebpageHtml = getGoogleWebpageHtml();
                            }
                            logGoogleWebpageHtml(googleWebpageHtml);
                        } else if (LOG_SUSPICIOUS_PAGE_TAG.equals(signalTag)) {
                            if (SuspiciousGoogleWebPageDetector.isSuspicious(sslHandshakeInfoList, sslRedirectInfoList)) {
                                if (googleWebpageHtml == null) {
                                    googleWebpageHtml = getGoogleWebpageHtml();
                                }
                                logGoogleWebpageHtml(googleWebpageHtml);
                            }
                        } else if (LOG_GOOGLE_PAGE_INFO_TAG.equals(signalTag)) {
                            if (googleWebpageHtml == null) {
                                googleWebpageHtml = getGoogleWebpageHtml();
                            }
                            logGooglePageInfo(googleWebpageHtml);
                        } else if (LOG_CAPTIVE_PORTAL_TEST_TAG.equals(signalTag)) {
                            logCaptivePortalTest();
                        } else if (LOG_GMSCORE_INFO_TAG.equals(signalTag)) {
                            logGmsCoreInfo();
                        } else if (LOG_ATTESTATION_TAG.equals(signalTag)) {
                            logAttestationResult();
                        } else if (LOG_EVENT_LOG_TAG.equals(signalTag)) {
                            logEventLogTagsOfInterestNormalMode();
                        } else if (LOG_DEVICE_ADMIN_TAG.equals(signalTag)) {
                            logDeactivateDeviceAdmins();
                        } else {
                            Log.e(TAG, String.format("Unknown tag: %s", new Object[]{signalTag}));
                        }
                    } catch (RuntimeException e) {
                        this.mSnetLogger.writeExceptionDetailed(e);
                        this.mSnetLogger.writeToBackend(getVersion(), startTimeMs);
                        return;
                    } catch (Throwable th) {
                        this.mSnetLogger.writeToBackend(getVersion(), startTimeMs);
                    }
                }
            }
        }
        updateExecutionPoint(DONE_TAG);
        this.mSnetLogger.writeToBackend(getVersion(), startTimeMs);
    }

    private void updateExecutionPoint(String signalTag) {
        if (this.mGBundle.getLogExecutionPoints()) {
            EventLogger.writeExecutionCheckpointTag(signalTag);
        }
    }

    private void logPreferredPackages() {
        PreferredPackageFinder finder = new PreferredPackageFinder(this.mCtx);
        this.mSnetLogger.setPreferredBrowser(finder.findWhitelistedWebBrowser());
        this.mSnetLogger.setPreferredInstaller(finder.findPackageInstaller());
    }

    private void logFiles() {
        this.mSnetLogger.setFiles(FileFinder.findFiles());
    }

    private void logSettings() {
        this.mSnetLogger.setDeviceSettings(new SettingsFinder(this.mCtx, this.mGBundle).deviceSettings());
    }

    private void logLocale() {
        Resources resources = this.mCtx.getResources();
        if (resources != null) {
            this.mSnetLogger.setLocale(resources.getConfiguration().locale.toString());
        }
    }

    private List<SslRedirectInfo> logSslRedirectAnalysis() {
        NetworkAnalyzer networkAnalyzer = new NetworkAnalyzer(this.mCtx, this.mSnetLogger);
        this.mSnetLogger.setConnectionInfo(networkAnalyzer.connectionInfo());
        List<SslRedirectInfo> infoList = networkAnalyzer.analyzeSslRedirects();
        this.mSnetLogger.setSslRedirectInfo(infoList);
        return infoList;
    }

    private List<SslInfo> logSslHandshakeAnalysis() {
        SslHandshakeAnalyzer sslHandshakeAnalyzer = new SslHandshakeAnalyzer(this.mCtx, this.mSnetLogger, new SslHandshakeAnalyzerValues());
        sslHandshakeAnalyzer.analyzeSslHandshake();
        List<SslInfo> infoList = sslHandshakeAnalyzer.sslInfoList();
        if (!infoList.isEmpty()) {
            this.mSnetLogger.setSslHandshakeInfo(infoList);
        }
        return infoList;
    }

    private void logHttpsSslv3FallbackAnalysis() {
        this.mSnetLogger.setSslv3Fallback(new HttpsSslv3FallbackAnalyzer(this.mCtx).analyze());
    }

    private void logSeLinuxInfo() {
        this.mSnetLogger.setSeLinuxInfo(SeLinuxCheckerSingleton.getInfo());
    }

    private void logEventLogTagsOfInterestNormalMode() {
        EventLogInfo eventLogInfo = new EventLogger(this.mCtx, this.mGBundle, this.mSnetLogger).getEventLogLogs();
        if (eventLogInfo != null) {
            this.mSnetLogger.setEventLogInfo(eventLogInfo);
        }
    }

    private void logProxyInfo() {
        ProxyInfo proxyInfo = new ProxyAnalyzer(this.mCtx).proxyInfo();
        if (proxyInfo != null) {
            proxyInfo.address = null;
            this.mSnetLogger.setProxyInfo(proxyInfo);
        }
    }

    private void logSdCardAnalysis() {
        this.mSnetLogger.setSdCardTampered(new SdCardAnalyzer(this.mCtx, this.mGBundle).sdCardTampered());
    }

    private void logApps() {
        this.mSnetLogger.setInstalledApps(new ApplicationInfoUtils(this.mCtx, this.mGBundle).apps());
    }

    private boolean hasGoogleAccount() {
        for (Account account : AccountManager.get(this.mCtx).getAccountsByType(GOOGLE_ACCOUNT_TYPE)) {
            if (account.name.endsWith(GOOGLE_ACCOUNT_SUFFIX)) {
                return true;
            }
        }
        return false;
    }

    private String getGoogleWebpageHtml() {
        return new NetworkAnalyzer(this.mCtx, this.mSnetLogger).getGoogleWebPage();
    }

    private void logGoogleWebpageHtml(String googleWebpageHtml) {
        this.mSnetLogger.setGoogleWebpageHtml(googleWebpageHtml);
    }

    private void logGooglePageInfo(String googleWebpageHtml) {
        if (googleWebpageHtml != null) {
            int threshold = this.mGBundle.getGoogleWebpageThreshold();
            if (threshold > 0) {
                this.mSnetLogger.setGoogleWebpageInfo(googleWebpageHtml.length(), threshold);
            }
        }
    }

    private void logMxRecords() {
        List<MxInfo> mxInfoList = new MxRecordHijackAnalyzer().getMxRecords();
        if (mxInfoList != null && !mxInfoList.isEmpty()) {
            this.mSnetLogger.setMxRecords(mxInfoList);
        }
    }

    private void logCaptivePortalTest() {
        this.mSnetLogger.setCaptivePortalTestResults(CaptivePortalDetector.captivePortalTest());
    }

    private void logAttestationResult() {
        Bundle attestBundle = this.mGBundle.getAttestBundle();
        if (attestBundle != null) {
            this.mSnetLogger.setAttestationResult(AttestationClient.attest(attestBundle));
        }
    }

    private void logGmsCoreInfo() {
        this.mSnetLogger.setGmsCoreInfo(new ApplicationInfoUtils(this.mCtx, this.mGBundle).appInfo(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE));
    }

    private void logDeactivateDeviceAdmins() {
        try {
            this.mSnetLogger.setDeactivatedAdmins(DeviceAdminDeactivator.deactivateDeviceAdmins(this.mCtx));
        } catch (BadBlacklistException ex) {
            this.mSnetLogger.setInvalidBlacklistedDeviceAdmins(ex.blacklist);
        }
    }

    private void logSetuidFiles(long currentTimeMs) {
        this.mSnetIdleLogger.setSetuidFileInfo(new SetuidFileFinder(this.mCtx, new Os(), currentTimeMs).find());
    }

    private void logSystemPartitionFiles(long currentTimeMs) {
        SystemPartitionFileFinder systemPartitionFileFinder = new SystemPartitionFileFinder(this.mCtx, new Os(), currentTimeMs);
        String systemPartitionFilesOfInterest = this.mGBundle.getSystemPartitionFilesOfInterest();
        int numRandomSystemPartitionFiles = this.mGBundle.getNumRandomSystemPartitionFiles();
        this.mSnetIdleLogger.setSystemPartitionFileInfo(new SystemIntegrityChecker(this.mCtx, this.mGBundle).systemPartitionState(), systemPartitionFileFinder.getHashes(systemPartitionFilesOfInterest, numRandomSystemPartitionFiles));
    }

    private void logSystemCaCertStore() {
        this.mSnetIdleLogger.setSystemCaCertsInfo(new SystemCaStoreAnalyzer(this.mCtx, this.mGBundle).getSystemCaCerts(this.mGBundle.getNumRandomSystemCaCerts()));
    }

    private void logLogcatTagsOfInterest() {
        LogcatInfo info = new SnetLogcat(this.mCtx, this.mGBundle).scrape();
        if (info != null) {
            this.mSnetIdleLogger.setLogcatInfo(info);
        }
    }

    private void logEventLogTagsOfInterestIdleMode() {
        EventLogInfo eventLogInfo = new EventLogger(this.mCtx, this.mGBundle, this.mSnetLogger).getEventLogLogs();
        if (eventLogInfo != null) {
            this.mSnetIdleLogger.setEventLogInfo(eventLogInfo);
        }
    }

    private void logDalvikCacheFiles() {
        this.mSnetIdleLogger.setDalvikCacheChangedFiles(new DalvikCacheMonitor(this.mCtx).findModifiedDalvikCacheFiles(5));
    }

    private void logDeviceState() {
        this.mSnetIdleLogger.setDeviceState(DeviceStateChecker.getDeviceState());
    }
}
