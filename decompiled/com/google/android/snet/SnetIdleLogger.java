package com.google.android.snet;

import android.content.Context;
import android.os.Build;
import android.os.DropBoxManager;
import android.text.TextUtils;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.snet.FileFinder.FilesInfo;
import com.google.android.snet.IdleLogs.AppInfo;
import com.google.android.snet.IdleLogs.DalvikCacheInfo;
import com.google.android.snet.IdleLogs.DeviceState;
import com.google.android.snet.IdleLogs.EventLog;
import com.google.android.snet.IdleLogs.LogcatEntry;
import com.google.android.snet.IdleLogs.LogcatResults;
import com.google.android.snet.IdleLogs.LogcatValue;
import com.google.android.snet.IdleLogs.RunSettings;
import com.google.android.snet.IdleLogs.SetuidFileInfo;
import com.google.android.snet.IdleLogs.SetuidFileInfo.FileInfo;
import com.google.android.snet.IdleLogs.SicFileInfo;
import com.google.android.snet.IdleLogs.SnetIdleLog;
import com.google.android.snet.IdleLogs.SystemCaCertStoreInfo;
import com.google.android.snet.IdleLogs.SystemPartitionFileInfo;
import com.google.android.snet.SnetLogcat.LogcatInfo;
import com.google.android.snet.SnetLogcat.Value;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

class SnetIdleLogger {
    private static final String CLEARCUT_ANDROID_SNET_IDLE_LOG_SOURCE = "ANDROID_SNET_IDLE";
    private static final boolean DEBUG = false;
    private static final int DROPBOX_FLAG_NONE = 0;
    static final String DROPBOX_IDLE_MODE_SNET_TAG = "snet_idle";
    private static final String SNET_SUBDIR = "/snet";
    private static final String TAG;
    private final Context mContext;
    private DropBoxManager mDropBoxManager;
    private List<String> mExceptionsList;
    private GBundle mGBundle;
    private SnetIdleLog mSnetLog;

    static {
        TAG = SnetIdleLogger.class.getCanonicalName();
    }

    SnetIdleLogger(Context context, GBundle gBundle) {
        this.mSnetLog = new SnetIdleLog();
        this.mDropBoxManager = (DropBoxManager) context.getSystemService("dropbox");
        this.mGBundle = gBundle;
        this.mContext = context;
        this.mExceptionsList = new ArrayList();
    }

    void writeException(Exception e) {
        this.mExceptionsList.add(e.toString());
    }

    void writeExceptionDetailed(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String exceptionString = stringWriter.toString();
        if (this.mGBundle != null) {
            this.mExceptionsList.add(exceptionString.substring(DROPBOX_FLAG_NONE, Math.min(this.mGBundle.getMaxExceptionStringLength(), exceptionString.length())));
            return;
        }
        this.mExceptionsList.add(exceptionString);
    }

    void setSignalTagsWhitelist(String signalTagsWhitelist) {
        if (signalTagsWhitelist != null) {
            this.mSnetLog.signalTagsWhitelist = signalTagsWhitelist;
        }
    }

    void setSystemPartitionFileInfo(int state, Set<FilesInfo> fileInfos) {
        this.mSnetLog.systemPartitionFileInfo = new SystemPartitionFileInfo();
        if (fileInfos == null) {
            this.mSnetLog.systemPartitionFileInfo.couldNotCheck = true;
            return;
        }
        this.mSnetLog.systemPartitionFileInfo.state = state;
        List<SicFileInfo> fileInfoList = new ArrayList();
        for (FilesInfo fileInfo : fileInfos) {
            if (fileInfo.present) {
                SicFileInfo info = new SicFileInfo();
                if (fileInfo.filename != null) {
                    info.path = fileInfo.filename;
                }
                if (fileInfo.sha256 != null) {
                    info.sha256 = fileInfo.sha256;
                }
                info.symlink = fileInfo.symlink;
                if (fileInfo.symlinkTarget != null) {
                    info.symlinkTarget = fileInfo.symlinkTarget;
                }
                if (fileInfo.lstatStruct != null) {
                    info.permissions = fileInfo.lstatStruct.mode;
                    info.fileOwner = fileInfo.lstatStruct.uid;
                    info.fileGroup = fileInfo.lstatStruct.gid;
                    if (fileInfo.lstatStruct.seLinuxSecurityContext != null) {
                        info.seLinuxSecurityContext = fileInfo.lstatStruct.seLinuxSecurityContext;
                    }
                }
                fileInfoList.add(info);
            }
        }
        if (!fileInfoList.isEmpty()) {
            this.mSnetLog.systemPartitionFileInfo.fileInfo = (SicFileInfo[]) fileInfoList.toArray(new SicFileInfo[DROPBOX_FLAG_NONE]);
        }
    }

    void setSystemCaCertsInfo(List<CaCertInfo> caCertInfoList) {
        if (caCertInfoList != null && !caCertInfoList.isEmpty()) {
            List<SystemCaCertStoreInfo> certList = new ArrayList();
            for (CaCertInfo certInfo : caCertInfoList) {
                SystemCaCertStoreInfo info = new SystemCaCertStoreInfo();
                if (certInfo.subjectDn != null) {
                    info.subjectDn = certInfo.subjectDn;
                }
                if (certInfo.issuerDn != null) {
                    info.issuerDn = certInfo.issuerDn;
                }
                if (certInfo.sha256 != null) {
                    info.derEncodedCertSha256 = certInfo.sha256;
                }
                if (!(certInfo.cert == null || certInfo.cert.length == 0)) {
                    info.derEncodedCert = certInfo.cert;
                }
                if (!(certInfo.spkiSha256 == null || certInfo.spkiSha256.length == 0)) {
                    info.subjectPublicKeyInfoSha256 = certInfo.spkiSha256;
                }
                certList.add(info);
            }
            if (!certList.isEmpty()) {
                this.mSnetLog.systemCaCertStoreInfo = (SystemCaCertStoreInfo[]) certList.toArray(new SystemCaCertStoreInfo[DROPBOX_FLAG_NONE]);
            }
        }
    }

    void setSetuidFileInfo(List<FilesInfo> fileInfos) {
        int maxSetuidFileChars = this.mGBundle.getMaxSetuidFileChars();
        this.mSnetLog.setuidFileInfo = new SetuidFileInfo();
        if (fileInfos == null) {
            this.mSnetLog.setuidFileInfo.couldNotCheck = true;
            return;
        }
        this.mSnetLog.setuidFileInfo.numberOfSetuidFiles = 0;
        List<FileInfo> fileInfoList = new ArrayList();
        int totalNumberOfChars = DROPBOX_FLAG_NONE;
        for (FilesInfo fileInfo : fileInfos) {
            if (fileInfo.present) {
                if (fileInfo.filename != null) {
                    totalNumberOfChars += fileInfo.filename.length();
                    if (totalNumberOfChars > maxSetuidFileChars) {
                        break;
                    }
                }
                SetuidFileInfo setuidFileInfo = this.mSnetLog.setuidFileInfo;
                setuidFileInfo.numberOfSetuidFiles++;
                FileInfo info = new FileInfo();
                if (fileInfo.filename != null) {
                    info.path = fileInfo.filename;
                }
                if (fileInfo.sha256 != null) {
                    info.sha256 = fileInfo.sha256;
                }
                fileInfoList.add(info);
            }
        }
        if (!fileInfoList.isEmpty()) {
            this.mSnetLog.setuidFileInfo.fileInfo = (FileInfo[]) fileInfoList.toArray(new FileInfo[DROPBOX_FLAG_NONE]);
        }
    }

    void setLogcatInfo(LogcatInfo info) {
        this.mSnetLog.logcatResults = new LogcatResults();
        this.mSnetLog.logcatResults.numLogcatLines = info.numLogcatLines;
        this.mSnetLog.logcatResults.results = new LogcatEntry[info.results.size()];
        int i = DROPBOX_FLAG_NONE;
        for (Entry<String, List<Value>> result : info.results.entrySet()) {
            String key = (String) result.getKey();
            if (TextUtils.isEmpty(key)) {
                i++;
            } else {
                this.mSnetLog.logcatResults.results[i] = new LogcatEntry();
                this.mSnetLog.logcatResults.results[i].key = key;
                List<Value> valueList = (List) result.getValue();
                if (valueList == null) {
                    i++;
                } else {
                    this.mSnetLog.logcatResults.results[i].value = new LogcatValue[valueList.size()];
                    Value[] valueArray = (Value[]) valueList.toArray(new Value[DROPBOX_FLAG_NONE]);
                    for (int j = DROPBOX_FLAG_NONE; j < valueList.size(); j++) {
                        this.mSnetLog.logcatResults.results[i].value[j] = new LogcatValue();
                        if (valueArray[j] != null) {
                            if (valueArray[j].line != null) {
                                this.mSnetLog.logcatResults.results[i].value[j].line = valueArray[j].line;
                            }
                            if (valueArray[j].appInfoList != null) {
                                this.mSnetLog.logcatResults.results[i].value[j].appInfo = new AppInfo[valueArray[j].appInfoList.size()];
                                List<AppInfo> appInfoList = valueArray[j].appInfoList;
                                for (int k = DROPBOX_FLAG_NONE; k < appInfoList.size(); k++) {
                                    this.mSnetLog.logcatResults.results[i].value[j].appInfo[k] = convertAppInfo((AppInfo) appInfoList.get(k), true);
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        }
    }

    void setBuildFingerprint(String buildFingerprint) {
        if (buildFingerprint != null) {
            this.mSnetLog.buildFingerprint = buildFingerprint;
        }
    }

    void setBuildInfo() {
        AppInfo appInfo = new ApplicationInfoUtils(this.mContext, this.mGBundle).appInfo("android");
        this.mSnetLog.buildInfo = convertAppInfo(appInfo, true);
    }

    void setDalvikCacheChangedFiles(List<FileInfo> filesList) {
        if (filesList != null && !filesList.isEmpty()) {
            this.mSnetLog.dalvikCacheInfo = new DalvikCacheInfo();
            this.mSnetLog.dalvikCacheInfo.changedFiles = new DalvikCacheInfo.FileInfo[filesList.size()];
            for (int i = DROPBOX_FLAG_NONE; i < filesList.size(); i++) {
                this.mSnetLog.dalvikCacheInfo.changedFiles[i] = new DalvikCacheInfo.FileInfo();
                FileInfo fileInfo = (FileInfo) filesList.get(i);
                this.mSnetLog.dalvikCacheInfo.changedFiles[i].filename = fileInfo.filename;
                this.mSnetLog.dalvikCacheInfo.changedFiles[i].sha256 = fileInfo.sha256;
                this.mSnetLog.dalvikCacheInfo.changedFiles[i].timeSec = fileInfo.lastModTimeSec;
            }
        }
    }

    void setDeviceState(DeviceState deviceState) {
        this.mSnetLog.deviceState = new DeviceState();
        this.mSnetLog.deviceState.verifiedBootState = deviceState.verifiedBootState;
        this.mSnetLog.deviceState.verityMode = deviceState.verityMode;
        this.mSnetLog.deviceState.oemUnlockSupported = deviceState.oemUnlockSupported;
        this.mSnetLog.deviceState.oemLocked = deviceState.oemLocked;
        this.mSnetLog.deviceState.securityPatchLevel = deviceState.securityPatchLevel;
    }

    void writeToBackend(int version, long startTimeMs) {
        finalizeProto(version, startTimeMs, true);
        if (this.mGBundle.getClearcutIdleLoggingEnabled()) {
            GoogleApiClient googleApiClient = new Builder(this.mContext).addApi(ClearcutLogger.API).build();
            googleApiClient.connect();
            ClearcutLogger clearcutLogger = ClearcutLogger.anonymousLogger(this.mContext, CLEARCUT_ANDROID_SNET_IDLE_LOG_SOURCE);
            clearcutLogger.newEvent(MessageNano.toByteArray(this.mSnetLog)).logAsync(googleApiClient);
            clearcutLogger.disconnectAsync(googleApiClient);
        }
        if (this.mGBundle.getDropboxIdleLoggingEnabled()) {
            this.mDropBoxManager.addData(DROPBOX_IDLE_MODE_SNET_TAG, MessageNano.toByteArray(this.mSnetLog), DROPBOX_FLAG_NONE);
        }
        this.mSnetLog = new SnetIdleLog();
    }

    void saveProto(int version, long startTimeMs) {
        finalizeProto(version, startTimeMs, DEBUG);
        File snetDir = new File(this.mContext.getApplicationInfo().dataDir + SNET_SUBDIR);
        if (snetDir.exists()) {
            File snetProtoFile = new File(snetDir, this.mGBundle.getIdleModeCachedProtoFileName());
            snetProtoFile.delete();
            Utils.writeBytes(MessageNano.toByteArray(this.mSnetLog), snetProtoFile);
        }
    }

    void loadSavedProto() {
        File snetDir = new File(this.mContext.getApplicationInfo().dataDir + SNET_SUBDIR);
        if (snetDir.exists()) {
            File snetProtoFile = new File(snetDir, this.mGBundle.getIdleModeCachedProtoFileName());
            if (snetProtoFile.exists()) {
                byte[] protoBytes = Utils.readBytes(snetProtoFile);
                if (protoBytes != null) {
                    try {
                        this.mSnetLog = SnetIdleLog.parseFrom(protoBytes);
                        snetProtoFile.delete();
                    } catch (InvalidProtocolBufferNanoException e) {
                    }
                }
            }
        }
    }

    private void finalizeProto(int version, long startTimeMs, boolean completed) {
        this.mSnetLog.jarVersion = (long) version;
        String sharedUuid = this.mGBundle.getSharedUuid();
        if (sharedUuid != null) {
            this.mSnetLog.sharedUuid = sharedUuid;
        }
        String sessionUuid = this.mGBundle.getUuid();
        if (TextUtils.isEmpty(sessionUuid)) {
            this.mSnetLog.uuid = UUID.randomUUID().toString();
            this.mSnetLog.gmsCoreUuidUsed = DEBUG;
        } else {
            this.mSnetLog.uuid = sessionUuid;
            this.mSnetLog.gmsCoreUuidUsed = true;
        }
        String debugStatus = this.mGBundle.getDebugStatus();
        if (!TextUtils.isEmpty(debugStatus)) {
            this.mSnetLog.debugStatus = debugStatus;
        }
        this.mSnetLog.isSidewinderDevice = this.mGBundle.getIsSidewinderDevice();
        setBuildFingerprint(Build.FINGERPRINT);
        setBuildInfo();
        setRunSettings(startTimeMs, completed);
        if (this.mExceptionsList.size() > 0) {
            if (!(this.mSnetLog.jarExceptions == null || this.mSnetLog.jarExceptions.length == 0)) {
                this.mExceptionsList.addAll(Arrays.asList(this.mSnetLog.jarExceptions));
            }
            this.mSnetLog.jarExceptions = (String[]) this.mExceptionsList.toArray(new String[DROPBOX_FLAG_NONE]);
        }
    }

    private void setRunSettings(long startTimeMs, boolean completed) {
        if (this.mSnetLog.runSettings == null) {
            this.mSnetLog.runSettings = new RunSettings();
        }
        long currentTimeMs = System.currentTimeMillis();
        RunSettings runSettings = this.mSnetLog.runSettings;
        runSettings.executionTimeMs += currentTimeMs - startTimeMs;
        this.mSnetLog.runSettings.wakeIntervalMs = this.mGBundle.getWakeIntervalMs();
        runSettings = this.mSnetLog.runSettings;
        runSettings.numAttempts++;
        SnetSharedPreferences preferences = new SnetSharedPreferences(this.mContext);
        if (preferences != null) {
            long lastRunTimestampMs = preferences.lastIdleModeRunTimestampMs();
            if (lastRunTimestampMs == -1) {
                preferences.saveLastIdleModeRunTimestampMs(currentTimeMs);
                return;
            }
            this.mSnetLog.runSettings.durationSinceLastRunMs = currentTimeMs - lastRunTimestampMs;
            if (completed) {
                preferences.saveLastIdleModeRunTimestampMs(currentTimeMs);
            }
        }
    }

    private static void logProto(MessageNano message) {
    }

    private static AppInfo convertAppInfo(AppInfo appInfo, boolean logMoreAppInfo) {
        if (appInfo == null) {
            return null;
        }
        AppInfo appInfoProto = new AppInfo();
        appInfoProto.systemApp = appInfo.systemApp;
        if (appInfo.apkSha256Bytes != null) {
            appInfoProto.apkSha256Bytes = appInfo.apkSha256Bytes;
        }
        if (!logMoreAppInfo) {
            return appInfoProto;
        }
        if (appInfo.packageName != null) {
            appInfoProto.packageName = appInfo.packageName;
        }
        if (appInfo.signatureSha256Bytes == null || appInfo.signatureSha256Bytes.length == 0) {
            return appInfoProto;
        }
        int numSignatures = appInfo.signatureSha256Bytes.length;
        appInfoProto.signatureSha256Bytes = new byte[numSignatures][];
        for (int i = DROPBOX_FLAG_NONE; i < numSignatures; i++) {
            if (appInfo.signatureSha256Bytes[i] != null) {
                appInfoProto.signatureSha256Bytes[i] = appInfo.signatureSha256Bytes[i];
            }
        }
        return appInfoProto;
    }

    void setEventLogInfo(EventLogInfo eventLogInfo) {
        List<EventData> events = eventLogInfo.eventDataList;
        if (events != null && !events.isEmpty()) {
            this.mSnetLog.events = new EventLog[events.size()];
            EventData[] eventsArray = (EventData[]) events.toArray(new EventData[DROPBOX_FLAG_NONE]);
            int i = DROPBOX_FLAG_NONE;
            while (i < eventsArray.length) {
                this.mSnetLog.events[i] = new EventLog();
                if (eventsArray[i] != null) {
                    this.mSnetLog.events[i].tag = eventsArray[i].tag;
                    this.mSnetLog.events[i].subTag = eventsArray[i].subTag == null ? BuildConfig.VERSION_NAME : eventsArray[i].subTag;
                    this.mSnetLog.events[i].data = eventsArray[i].data == null ? BuildConfig.VERSION_NAME : eventsArray[i].data;
                    this.mSnetLog.events[i].timeNanos = eventsArray[i].timeNanos;
                    if (!(eventsArray[i].appInfoList == null || eventsArray[i].appInfoList.size() == 0)) {
                        this.mSnetLog.events[i].appInfo = new AppInfo[eventsArray[i].appInfoList.size()];
                        List<AppInfo> appInfoList = eventsArray[i].appInfoList;
                        for (int j = DROPBOX_FLAG_NONE; j < appInfoList.size(); j++) {
                            this.mSnetLog.events[i].appInfo[j] = convertAppInfo((AppInfo) appInfoList.get(j), true);
                        }
                    }
                }
                i++;
            }
        }
    }
}
