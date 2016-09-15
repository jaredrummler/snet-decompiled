package com.google.android.snet;

import android.content.Context;
import android.text.TextUtils;
import android.util.EventLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class EventLogger {
    private static final String DO_NOT_LOG_EVENT_LOG_SUBTAG_PREFIX = "do-not-log-";
    private static final int LOG_OPERATION = 1;
    private static final int PACKAGES_OPERATION = 2;
    private static final int SNET_EVENT_LOG_TAG = 1397638484;
    private static final int SNET_OPERATION = 3;
    private static final Set<Integer> SUPPORTED_OPERATIONS;
    private static final String TAG;
    private static final String WATCHDOG_TRACKING_SUBTAG = "do-not-log-execution-checkpoint-tag";
    private final Context mContext;
    private final GBundle mGBundle;
    private final SnetLogger mSnetLogger;
    private Map<Integer, Integer> mTagToOperationMap;
    private int[] mTagsOfInterest;

    static class EventData {
        public List<AppInfo> appInfoList;
        public String data;
        public String subTag;
        public int tag;
        public long timeNanos;

        EventData() {
        }
    }

    static class EventLogInfo {
        List<EventData> eventDataList;
        long firstEventLogTimeNano;

        EventLogInfo() {
        }
    }

    static {
        TAG = EventLogger.class.getCanonicalName();
        Integer[] numArr = new Integer[SNET_OPERATION];
        numArr[0] = Integer.valueOf(LOG_OPERATION);
        numArr[LOG_OPERATION] = Integer.valueOf(PACKAGES_OPERATION);
        numArr[PACKAGES_OPERATION] = Integer.valueOf(SNET_OPERATION);
        SUPPORTED_OPERATIONS = new HashSet(Arrays.asList(numArr));
    }

    EventLogger(Context context, GBundle gBundle, SnetLogger snetLogger) {
        this.mContext = context;
        this.mGBundle = gBundle;
        this.mSnetLogger = snetLogger;
        String tagsOfInterest = this.mGBundle.getEventLogTags();
        if (!TextUtils.isEmpty(tagsOfInterest)) {
            parseTagsAndSubTagsOfInterest(tagsOfInterest);
        }
    }

    public static void writeSnetEventLog(String subTag, int uid, String data) {
        if (subTag != null && data != null) {
            Object[] objArr = new Object[SNET_OPERATION];
            objArr[0] = subTag;
            objArr[LOG_OPERATION] = Integer.valueOf(uid);
            objArr[PACKAGES_OPERATION] = data;
            EventLog.writeEvent(SNET_EVENT_LOG_TAG, objArr);
        }
    }

    static void writeExecutionCheckpointTag(String signalTag) {
        writeSnetEventLog(WATCHDOG_TRACKING_SUBTAG, -1, signalTag);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    com.google.android.snet.EventLogger.EventLogInfo getEventLogLogs() {
        /*
        r34 = this;
        r0 = r34;
        r0 = r0.mTagsOfInterest;
        r31 = r0;
        if (r31 == 0) goto L_0x0015;
    L_0x0008:
        r0 = r34;
        r0 = r0.mTagsOfInterest;
        r31 = r0;
        r0 = r31;
        r0 = r0.length;
        r31 = r0;
        if (r31 != 0) goto L_0x0017;
    L_0x0015:
        r12 = 0;
    L_0x0016:
        return r12;
    L_0x0017:
        r13 = new java.util.ArrayList;
        r13.<init>();
        r0 = r34;
        r0 = r0.mTagsOfInterest;	 Catch:{ IOException -> 0x0126 }
        r31 = r0;
        r0 = r31;
        android.util.EventLog.readEvents(r0, r13);	 Catch:{ IOException -> 0x0126 }
        r4 = new com.google.android.snet.ApplicationInfoUtils;
        r0 = r34;
        r0 = r0.mContext;
        r31 = r0;
        r0 = r34;
        r0 = r0.mGBundle;
        r32 = r0;
        r0 = r31;
        r1 = r32;
        r4.<init>(r0, r1);
        r26 = r4.pidToUidMap();
        r11 = new java.util.ArrayList;
        r11.<init>();
        r27 = new com.google.android.snet.SnetSharedPreferences;
        r0 = r34;
        r0 = r0.mContext;
        r31 = r0;
        r0 = r27;
        r1 = r31;
        r0.<init>(r1);
        r14 = -1;
        r18 = r27.lastEventLogTimestampMs();
        r6 = -1;
        r0 = r34;
        r0 = r0.mContext;
        r31 = r0;
        r22 = r31.getPackageManager();
        r16 = r13.iterator();
    L_0x006a:
        r31 = r16.hasNext();
        if (r31 == 0) goto L_0x01ce;
    L_0x0070:
        r9 = r16.next();
        r9 = (android.util.EventLog.Event) r9;
        r6 = r9.getTimeNanos();
        r32 = -1;
        r31 = (r14 > r32 ? 1 : (r14 == r32 ? 0 : -1));
        if (r31 != 0) goto L_0x0081;
    L_0x0080:
        r14 = r6;
    L_0x0081:
        r31 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1));
        if (r31 <= 0) goto L_0x006a;
    L_0x0085:
        r10 = new com.google.android.snet.EventLogger$EventData;
        r10.<init>();
        r31 = r9.getTag();
        r0 = r31;
        r10.tag = r0;
        r32 = r9.getTimeNanos();
        r0 = r32;
        r10.timeNanos = r0;
        r0 = r34;
        r0 = r0.mTagToOperationMap;
        r31 = r0;
        r32 = r9.getTag();
        r32 = java.lang.Integer.valueOf(r32);
        r31 = r31.get(r32);
        r31 = (java.lang.Integer) r31;
        r21 = r31.intValue();
        r30 = -1;
        switch(r21) {
            case 1: goto L_0x00b8;
            case 2: goto L_0x0135;
            case 3: goto L_0x014c;
            default: goto L_0x00b7;
        };
    L_0x00b7:
        goto L_0x006a;
    L_0x00b8:
        r31 = r9.getData();
        r0 = r31;
        r0 = r0 instanceof java.lang.String;
        r31 = r0;
        if (r31 == 0) goto L_0x00ce;
    L_0x00c4:
        r31 = r9.getData();
        r31 = (java.lang.String) r31;
        r0 = r31;
        r10.data = r0;
    L_0x00ce:
        r25 = r9.getProcessId();
        r31 = java.lang.Integer.valueOf(r25);
        r0 = r26;
        r1 = r31;
        r31 = r0.containsKey(r1);
        if (r31 == 0) goto L_0x00f2;
    L_0x00e0:
        r31 = java.lang.Integer.valueOf(r25);
        r0 = r26;
        r1 = r31;
        r31 = r0.get(r1);
        r31 = (java.lang.Integer) r31;
        r30 = r31.intValue();
    L_0x00f2:
        r31 = -1;
        r0 = r30;
        r1 = r31;
        if (r0 == r1) goto L_0x01c9;
    L_0x00fa:
        r0 = r22;
        r1 = r30;
        r24 = r0.getPackagesForUid(r1);
        if (r24 == 0) goto L_0x01c9;
    L_0x0104:
        r3 = new java.util.ArrayList;
        r3.<init>();
        r5 = r24;
        r0 = r5.length;
        r20 = r0;
        r17 = 0;
    L_0x0110:
        r0 = r17;
        r1 = r20;
        if (r0 >= r1) goto L_0x01c1;
    L_0x0116:
        r23 = r5[r17];
        r0 = r23;
        r2 = r4.appInfo(r0);
        if (r2 == 0) goto L_0x0123;
    L_0x0120:
        r3.add(r2);
    L_0x0123:
        r17 = r17 + 1;
        goto L_0x0110;
    L_0x0126:
        r8 = move-exception;
        r0 = r34;
        r0 = r0.mSnetLogger;
        r31 = r0;
        r0 = r31;
        r0.writeException(r8);
        r12 = 0;
        goto L_0x0016;
    L_0x0135:
        r31 = r9.getData();
        r0 = r31;
        r0 = r0 instanceof java.lang.Integer;
        r31 = r0;
        if (r31 == 0) goto L_0x006a;
    L_0x0141:
        r31 = r9.getData();
        r31 = (java.lang.Integer) r31;
        r30 = r31.intValue();
        goto L_0x00f2;
    L_0x014c:
        r28 = 0;
        r31 = r9.getData();	 Catch:{ ClassCastException -> 0x01bb }
        r31 = (java.lang.Object[]) r31;	 Catch:{ ClassCastException -> 0x01bb }
        r0 = r31;
        r0 = (java.lang.Object[]) r0;	 Catch:{ ClassCastException -> 0x01bb }
        r28 = r0;
        if (r28 == 0) goto L_0x006a;
    L_0x015c:
        r0 = r28;
        r0 = r0.length;
        r31 = r0;
        r32 = 3;
        r0 = r31;
        r1 = r32;
        if (r0 != r1) goto L_0x006a;
    L_0x0169:
        r31 = 0;
        r31 = r28[r31];
        r0 = r31;
        r0 = r0 instanceof java.lang.String;
        r31 = r0;
        if (r31 == 0) goto L_0x006a;
    L_0x0175:
        r31 = 0;
        r29 = r28[r31];
        r29 = (java.lang.String) r29;
        if (r29 == 0) goto L_0x006a;
    L_0x017d:
        r31 = "do-not-log-";
        r0 = r29;
        r1 = r31;
        r31 = r0.startsWith(r1);
        if (r31 != 0) goto L_0x006a;
    L_0x0189:
        r0 = r29;
        r10.subTag = r0;
        r31 = 1;
        r31 = r28[r31];
        r0 = r31;
        r0 = r0 instanceof java.lang.Integer;
        r31 = r0;
        if (r31 == 0) goto L_0x01be;
    L_0x0199:
        r31 = 1;
        r31 = r28[r31];
        r31 = (java.lang.Integer) r31;
        r30 = r31.intValue();
    L_0x01a3:
        r31 = 2;
        r31 = r28[r31];
        r0 = r31;
        r0 = r0 instanceof java.lang.String;
        r31 = r0;
        if (r31 == 0) goto L_0x00f2;
    L_0x01af:
        r31 = 2;
        r31 = r28[r31];
        r31 = (java.lang.String) r31;
        r0 = r31;
        r10.data = r0;
        goto L_0x00f2;
    L_0x01bb:
        r8 = move-exception;
        goto L_0x006a;
    L_0x01be:
        r30 = -1;
        goto L_0x01a3;
    L_0x01c1:
        r31 = r3.size();
        if (r31 == 0) goto L_0x01c9;
    L_0x01c7:
        r10.appInfoList = r3;
    L_0x01c9:
        r11.add(r10);
        goto L_0x006a;
    L_0x01ce:
        r32 = -1;
        r31 = (r6 > r32 ? 1 : (r6 == r32 ? 0 : -1));
        if (r31 == 0) goto L_0x01d9;
    L_0x01d4:
        r0 = r27;
        r0.saveLastEventLogTimestampMs(r6);
    L_0x01d9:
        r12 = new com.google.android.snet.EventLogger$EventLogInfo;
        r12.<init>();
        r12.firstEventLogTimeNano = r14;
        r12.eventDataList = r11;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.EventLogger.getEventLogLogs():com.google.android.snet.EventLogger$EventLogInfo");
    }

    private void parseTagsAndSubTagsOfInterest(String tagsOfInterestString) {
        String[] tagsOpStringArray = tagsOfInterestString.split(Csv.COMMA);
        this.mTagsOfInterest = new int[tagsOpStringArray.length];
        this.mTagToOperationMap = new HashMap();
        this.mTagToOperationMap.put(Integer.valueOf(SNET_EVENT_LOG_TAG), Integer.valueOf(SNET_OPERATION));
        for (int i = 0; i < tagsOpStringArray.length; i += LOG_OPERATION) {
            try {
                String[] parts = tagsOpStringArray[i].split(":");
                if (parts.length != PACKAGES_OPERATION) {
                    this.mTagsOfInterest[i] = -1;
                } else {
                    String tag = parts[0];
                    int operationInt = Integer.parseInt(parts[LOG_OPERATION]);
                    if (operationInt == SNET_OPERATION) {
                        this.mTagsOfInterest[i] = SNET_EVENT_LOG_TAG;
                    } else if (SUPPORTED_OPERATIONS.contains(Integer.valueOf(operationInt))) {
                        int tagInt = Integer.parseInt(tag);
                        this.mTagsOfInterest[i] = tagInt;
                        if (!this.mTagToOperationMap.containsKey(Integer.valueOf(tagInt))) {
                            this.mTagToOperationMap.put(Integer.valueOf(tagInt), Integer.valueOf(operationInt));
                        }
                    } else {
                        this.mTagsOfInterest[i] = -1;
                    }
                }
            } catch (NumberFormatException e) {
                this.mTagsOfInterest[i] = -1;
            }
        }
    }
}
