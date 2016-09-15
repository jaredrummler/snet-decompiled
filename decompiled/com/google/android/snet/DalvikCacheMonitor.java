package com.google.android.snet;

import android.content.Context;
import com.google.android.snet.IdleLogs.DalvikCacheInfo;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class DalvikCacheMonitor {
    private static final String DALVIK_CACHE_DB_FILE_VERSION = "1";
    private static final String DALVIK_CACHE_INFO_FILENAME = "dcache.info";
    private static final String NEW_DALVIK_CACHE_DIR = "/data/dalvik-cache/arm";
    private static final String OLD_DALVIK_CACHE_DIR = "/data/dalvik-cache";
    private static final String[] PATHS_TO_MONITOR;
    private static final String SNET_DIR = "/snet";
    private static final String TAG;
    private final Context mContext;
    private File mDalvikCacheDir;

    static class FileInfo {
        String filename;
        long lastModTimeSec;
        byte[] sha256;

        FileInfo() {
        }
    }

    static {
        TAG = DalvikCacheMonitor.class.getCanonicalName();
        PATHS_TO_MONITOR = new String[]{"/system/app", "/system/framework", "/system/priv-app"};
    }

    DalvikCacheMonitor(Context context) {
        this.mContext = context;
        this.mDalvikCacheDir = new File(NEW_DALVIK_CACHE_DIR);
        if (!validDalvikCacheDir(this.mDalvikCacheDir)) {
            this.mDalvikCacheDir = new File(OLD_DALVIK_CACHE_DIR);
            if (!validDalvikCacheDir(this.mDalvikCacheDir)) {
                this.mDalvikCacheDir = null;
            }
        }
    }

    List<FileInfo> findModifiedDalvikCacheFiles(int numFilesThreshold) {
        if (this.mDalvikCacheDir == null) {
            return null;
        }
        Os os = new Os();
        List<FileInfo> modifiedFilesList = new ArrayList();
        boolean dbFileExists = getDalvikCacheDbFile().exists();
        Map<String, byte[]> existingDb = readDalvikCacheDb();
        Map<String, byte[]> newDb = new HashMap();
        for (String apkFilename : getFullFilenamesOfInterest()) {
            File file = dalvikCacheFile(apkFilename);
            if (file != null) {
                try {
                    LstatStruct lstatStruct = os.getLstatStruct(file);
                    FileInfo filesInfo = new FileInfo();
                    filesInfo.filename = file.getAbsolutePath();
                    filesInfo.sha256 = Utils.getSha256(file);
                    if (lstatStruct != null) {
                        filesInfo.lastModTimeSec = lstatStruct.mtime;
                    }
                    if (dbFileExists && !(existingDb.containsKey(filesInfo.filename) && Arrays.equals((byte[]) existingDb.get(filesInfo.filename), filesInfo.sha256))) {
                        modifiedFilesList.add(filesInfo);
                    }
                    if (filesInfo.sha256 != null) {
                        newDb.put(filesInfo.filename, filesInfo.sha256);
                    }
                } catch (OsException e) {
                }
            }
        }
        if (!dbFileExists) {
            writeDalvikCacheDb(newDb);
            return null;
        } else if (modifiedFilesList.isEmpty()) {
            return null;
        } else {
            writeDalvikCacheDb(newDb);
            if (modifiedFilesList.size() > numFilesThreshold) {
                return null;
            }
            return modifiedFilesList;
        }
    }

    private List<String> getFullFilenamesOfInterest() {
        List<String> fullFilenamesList = new ArrayList();
        for (String filename : new SnetFilesDataStore(this.mContext).getAll().keySet()) {
            if (filename.endsWith(".apk")) {
                for (String pathToMonitor : PATHS_TO_MONITOR) {
                    if (filename.startsWith(pathToMonitor)) {
                        fullFilenamesList.add(filename);
                        break;
                    }
                }
            }
        }
        return fullFilenamesList;
    }

    private File dalvikCacheFile(String correspondingApkFile) {
        String constructedFilename = correspondingApkFile.replaceAll("/", "@");
        if (constructedFilename.length() <= 1) {
            return null;
        }
        return new File(this.mDalvikCacheDir, constructedFilename.substring(1) + "@classes.dex");
    }

    File getDalvikCacheDbFile() {
        return new File(new File(this.mContext.getApplicationInfo().dataDir, SNET_DIR), DALVIK_CACHE_INFO_FILENAME);
    }

    Map<String, byte[]> readDalvikCacheDb() {
        Map<String, byte[]> map = new HashMap();
        File dalvikCacheDbFile = getDalvikCacheDbFile();
        if (dalvikCacheDbFile.exists() && dalvikCacheDbFile.length() != 0) {
            try {
                DalvikCacheInfo dalvikCacheInfoProto = DalvikCacheInfo.parseFrom(Utils.readBytes(dalvikCacheDbFile));
                if (!(dalvikCacheInfoProto == null || dalvikCacheInfoProto.changedFiles == null)) {
                    for (com.google.android.snet.IdleLogs.DalvikCacheInfo.FileInfo fileInfo : dalvikCacheInfoProto.changedFiles) {
                        map.put(fileInfo.filename, fileInfo.sha256);
                    }
                }
            } catch (InvalidProtocolBufferNanoException e) {
            }
        }
        return map;
    }

    void writeDalvikCacheDb(Map<String, byte[]> map) {
        String tempFilename = "dcache.info.tmp";
        File tempDalvikCacheDbFile = new File(new File(this.mContext.getApplicationInfo().dataDir, SNET_DIR), DALVIK_CACHE_INFO_FILENAME);
        DalvikCacheInfo dalvikCacheInfoProto = new DalvikCacheInfo();
        dalvikCacheInfoProto.changedFiles = new com.google.android.snet.IdleLogs.DalvikCacheInfo.FileInfo[map.size()];
        int i = 0;
        for (Entry<String, byte[]> entry : map.entrySet()) {
            dalvikCacheInfoProto.changedFiles[i] = new com.google.android.snet.IdleLogs.DalvikCacheInfo.FileInfo();
            dalvikCacheInfoProto.changedFiles[i].filename = (String) entry.getKey();
            dalvikCacheInfoProto.changedFiles[i].sha256 = (byte[]) entry.getValue();
            i++;
        }
        Utils.writeBytes(MessageNano.toByteArray(dalvikCacheInfoProto), tempDalvikCacheDbFile);
        if (tempDalvikCacheDbFile.length() != 0) {
            tempDalvikCacheDbFile.renameTo(getDalvikCacheDbFile());
        }
    }

    private boolean validDalvikCacheDir(File dalvikCacheDir) {
        return dalvikCacheDir.exists() && dalvikCacheDir.isDirectory();
    }
}
