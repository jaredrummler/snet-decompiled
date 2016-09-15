package com.google.android.snet;

import android.content.Context;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

class FileFinder {
    private static final String[] FILE_NAMES;
    private static final String TAG;

    static class DifferingFiles {
        FilesInfo currentFilesInfo;
        FilesInfo previousFilesInfo;

        DifferingFiles() {
        }
    }

    public static class FilesInfo {
        public String filename;
        public long foundAtTimeMs;
        public boolean isSetuidRoot;
        public LstatStruct lstatStruct;
        public boolean present;
        public byte[] sha256;
        public boolean symlink;
        public String symlinkTarget;
    }

    static class SetuidFileFinder {
        private static final Set<String> PATH_BLACK_LIST;
        private long mCurrentTimeMs;
        private Stack<File> mDirectoriesToSearch;
        private SnetFilesDataStore mFilesDataStore;
        private Os mOs;
        private List<FilesInfo> mSetuidFiles;

        static {
            PATH_BLACK_LIST = new HashSet(Arrays.asList(new String[]{"/dev", "/proc", "/sys", "/system"}));
        }

        SetuidFileFinder(Context context, Os os, long currentTimeMs) {
            this.mOs = os;
            this.mCurrentTimeMs = currentTimeMs;
            this.mFilesDataStore = new SnetFilesDataStore(context);
        }

        List<FilesInfo> find() {
            if (!this.mOs.apiPresent()) {
                return null;
            }
            this.mSetuidFiles = new ArrayList();
            this.mDirectoriesToSearch = new Stack();
            processFile(File.listRoots()[0]);
            while (!this.mDirectoriesToSearch.empty()) {
                File[] files = ((File) this.mDirectoriesToSearch.pop()).listFiles();
                if (files != null) {
                    for (File file : files) {
                        processFile(file);
                    }
                }
            }
            List<FilesInfo> systemPartitionFiles = this.mFilesDataStore.getSetuidFiles();
            if (systemPartitionFiles != null) {
                this.mSetuidFiles.addAll(systemPartitionFiles);
            }
            return this.mSetuidFiles;
        }

        private void processFile(File file) {
            try {
                if (!this.mOs.isSymlink(file) && !PATH_BLACK_LIST.contains(file.getAbsolutePath())) {
                    if (file.isFile() && this.mOs.isSetuidRoot(file)) {
                        byte[] sha256 = Utils.getSha256(file);
                        FilesInfo filesInfo = new FilesInfo();
                        filesInfo.filename = file.getAbsolutePath();
                        filesInfo.present = true;
                        filesInfo.sha256 = sha256;
                        filesInfo.isSetuidRoot = true;
                        filesInfo.foundAtTimeMs = this.mCurrentTimeMs;
                        try {
                            filesInfo.lstatStruct = this.mOs.getLstatStruct(file);
                        } catch (OsException e) {
                        }
                        this.mSetuidFiles.add(filesInfo);
                        this.mFilesDataStore.put(filesInfo);
                    } else if (file.isDirectory()) {
                        this.mDirectoriesToSearch.push(file);
                    }
                }
            } catch (OsException e2) {
            }
        }
    }

    static class SystemPartitionFileFinder {
        private static byte[] defaultSha256;
        Map<String, FilesInfo> mAllFiles;
        private long mCurrentTimeMs;
        List<DifferingFiles> mDifferingFilesList;
        private SnetFilesDataStore mFilesDataStore;
        private final Os mOs;

        private static class HashTreeDirectoryNode {
            private MessageDigest mDigester;
            private File mFile;
            private int mIndex;
            private File[] mSubFiles;

            HashTreeDirectoryNode(File directory) throws NoSuchAlgorithmException {
                if (directory.isDirectory()) {
                    this.mFile = directory;
                    this.mSubFiles = directory.listFiles();
                    if (this.mSubFiles == null) {
                        this.mSubFiles = new File[0];
                    } else {
                        Arrays.sort(this.mSubFiles);
                    }
                    this.mDigester = MessageDigest.getInstance("SHA-256");
                    return;
                }
                throw new IllegalArgumentException("Directory node constructor expects a directory, but got " + directory);
            }

            File getFile() {
                return this.mFile;
            }

            File getCurrentSubFile() {
                if (this.mIndex < this.mSubFiles.length) {
                    return this.mSubFiles[this.mIndex];
                }
                return null;
            }

            File getNextSubFile() {
                if (this.mIndex < this.mSubFiles.length) {
                    this.mIndex++;
                }
                if (this.mIndex < this.mSubFiles.length) {
                    return this.mSubFiles[this.mIndex];
                }
                return null;
            }

            void updateHash(byte[] hash, LstatStruct lstatStruct) {
                if (hash != null) {
                    this.mDigester.update(hash);
                    byte[] lstatBlob = SystemPartitionFileFinder.getLstatBlob(lstatStruct);
                    if (lstatBlob != null) {
                        this.mDigester.update(lstatBlob);
                    }
                }
            }

            void updateHash(FilesInfo filesInfo) {
                if (filesInfo.sha256 != null) {
                    this.mDigester.update(filesInfo.sha256);
                    byte[] lstatBlob = SystemPartitionFileFinder.getLstatBlob(filesInfo.lstatStruct);
                    if (lstatBlob != null) {
                        this.mDigester.update(lstatBlob);
                    }
                }
            }

            byte[] getHash() {
                return this.mDigester.digest();
            }
        }

        SystemPartitionFileFinder(Context context, Os os, long currentTimeMs) {
            this.mAllFiles = new HashMap();
            this.mDifferingFilesList = new ArrayList();
            this.mOs = os;
            this.mCurrentTimeMs = currentTimeMs;
            defaultSha256 = new byte[32];
            Arrays.fill(defaultSha256, (byte) -86);
            this.mFilesDataStore = new SnetFilesDataStore(context);
        }

        Set<FilesInfo> getHashes(String pathsOfInterest, int numRandomFiles) {
            if (!this.mOs.apiPresent()) {
                return null;
            }
            buildHashTree(new File("/system"));
            if (this.mAllFiles.isEmpty()) {
                return null;
            }
            FilesInfo filesInfo;
            Set<FilesInfo> returnedFiles = new HashSet();
            FilesInfo rootFilesInfo = (FilesInfo) this.mAllFiles.get("/system");
            if (rootFilesInfo != null) {
                returnedFiles.add(rootFilesInfo);
            }
            if (pathsOfInterest != null) {
                for (String path : pathsOfInterest.split(Csv.COMMA)) {
                    filesInfo = (FilesInfo) this.mAllFiles.get(path);
                    if (filesInfo != null) {
                        returnedFiles.add(filesInfo);
                    }
                }
            }
            if (numRandomFiles == 0) {
                return returnedFiles;
            }
            Random random = new Random();
            List<String> systemPartitionFiles = new ArrayList(this.mAllFiles.keySet());
            int numFiles = 0;
            for (int i = 0; i < numRandomFiles * 2 && numFiles < numRandomFiles; i++) {
                filesInfo = (FilesInfo) this.mAllFiles.get((String) systemPartitionFiles.get(random.nextInt(this.mAllFiles.size())));
                if (!returnedFiles.contains(filesInfo)) {
                    returnedFiles.add(filesInfo);
                    numFiles++;
                }
            }
            return returnedFiles;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void buildHashTree(java.io.File r13) {
            /*
            r12 = this;
            r7 = new java.util.Stack;
            r7.<init>();
            r5 = r13;
        L_0x0006:
            r10 = r12.mOs;	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r10 = r10.isSymlink(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            if (r10 == 0) goto L_0x0032;
        L_0x000e:
            r10 = r12.mOs;	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r3 = r10.getLstatStruct(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r10 = r12.hashSymlink(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r11 = 1;
            r12.updateFilesInfo(r5, r10, r3, r11);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r10 = r12.mOs;	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r9 = r10.readLink(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r13 = new java.io.File;	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r13.<init>(r9);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r10 = r12.mAllFiles;	 Catch:{ NoSuchAlgorithmException -> 0x00fb, OsException -> 0x00f9 }
            r10 = r10.containsKey(r9);	 Catch:{ NoSuchAlgorithmException -> 0x00fb, OsException -> 0x00f9 }
            if (r10 == 0) goto L_0x0030;
        L_0x002f:
            return;
        L_0x0030:
            r5 = r13;
            goto L_0x0006;
        L_0x0032:
            r10 = r5.isDirectory();	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            if (r10 == 0) goto L_0x006c;
        L_0x0038:
            r10 = new com.google.android.snet.FileFinder$SystemPartitionFileFinder$HashTreeDirectoryNode;	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r10.<init>(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r7.push(r10);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
        L_0x0040:
            r10 = r7.isEmpty();
            if (r10 != 0) goto L_0x00ef;
        L_0x0046:
            r4 = r7.peek();
            r4 = (com.google.android.snet.FileFinder.SystemPartitionFileFinder.HashTreeDirectoryNode) r4;
            r0 = r4.getCurrentSubFile();
            if (r0 != 0) goto L_0x0086;
        L_0x0052:
            r2 = r4.getHash();
            r3 = 0;
            r10 = r12.mOs;	 Catch:{ OsException -> 0x00f6 }
            r11 = r4.getFile();	 Catch:{ OsException -> 0x00f6 }
            r3 = r10.getLstatStruct(r11);	 Catch:{ OsException -> 0x00f6 }
        L_0x0061:
            r10 = r4.getFile();
            r12.updateFilesInfo(r10, r2, r3);
            r7.pop();
            goto L_0x0040;
        L_0x006c:
            r10 = r5.isFile();	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            if (r10 == 0) goto L_0x0006;
        L_0x0072:
            r10 = r12.mOs;	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r3 = r10.getLstatStruct(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r10 = r12.hashFile(r5);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            r12.updateFilesInfo(r5, r10, r3);	 Catch:{ NoSuchAlgorithmException -> 0x0080, OsException -> 0x0083 }
            goto L_0x0040;
        L_0x0080:
            r1 = move-exception;
            r13 = r5;
        L_0x0082:
            goto L_0x002f;
        L_0x0083:
            r1 = move-exception;
            r13 = r5;
        L_0x0085:
            goto L_0x002f;
        L_0x0086:
            r10 = r12.mOs;	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r10 = r10.isSymlink(r0);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            if (r10 == 0) goto L_0x00a7;
        L_0x008e:
            r8 = r12.hashSymlink(r0);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r3 = 0;
            r10 = r12.mOs;	 Catch:{ OsException -> 0x00f4, NoSuchAlgorithmException -> 0x00c9 }
            r3 = r10.getLstatStruct(r0);	 Catch:{ OsException -> 0x00f4, NoSuchAlgorithmException -> 0x00c9 }
        L_0x0099:
            r10 = 1;
            r12.updateFilesInfo(r0, r8, r3, r10);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r4.updateHash(r8, r3);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
        L_0x00a0:
            r0 = r4.getNextSubFile();
            if (r0 != 0) goto L_0x0086;
        L_0x00a6:
            goto L_0x0040;
        L_0x00a7:
            r10 = r0.isDirectory();	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            if (r10 == 0) goto L_0x00d7;
        L_0x00ad:
            r10 = r12.mAllFiles;	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r11 = r0.getAbsolutePath();	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r10 = r10.containsKey(r11);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            if (r10 == 0) goto L_0x00cb;
        L_0x00b9:
            r10 = r12.mAllFiles;	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r11 = r0.getAbsolutePath();	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r6 = r10.get(r11);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r6 = (com.google.android.snet.FileFinder.FilesInfo) r6;	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r4.updateHash(r6);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            goto L_0x00a0;
        L_0x00c9:
            r1 = move-exception;
            goto L_0x00a0;
        L_0x00cb:
            r10 = new com.google.android.snet.FileFinder$SystemPartitionFileFinder$HashTreeDirectoryNode;	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r10.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r7.push(r10);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            goto L_0x0040;
        L_0x00d5:
            r1 = move-exception;
            goto L_0x00a0;
        L_0x00d7:
            r10 = r0.isFile();	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            if (r10 == 0) goto L_0x00a0;
        L_0x00dd:
            r2 = r12.hashFile(r0);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r3 = 0;
            r10 = r12.mOs;	 Catch:{ OsException -> 0x00f2, NoSuchAlgorithmException -> 0x00c9 }
            r3 = r10.getLstatStruct(r0);	 Catch:{ OsException -> 0x00f2, NoSuchAlgorithmException -> 0x00c9 }
        L_0x00e8:
            r12.updateFilesInfo(r0, r2, r3);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            r4.updateHash(r2, r3);	 Catch:{ NoSuchAlgorithmException -> 0x00c9, OsException -> 0x00d5 }
            goto L_0x00a0;
        L_0x00ef:
            r13 = r5;
            goto L_0x002f;
        L_0x00f2:
            r10 = move-exception;
            goto L_0x00e8;
        L_0x00f4:
            r10 = move-exception;
            goto L_0x0099;
        L_0x00f6:
            r10 = move-exception;
            goto L_0x0061;
        L_0x00f9:
            r1 = move-exception;
            goto L_0x0085;
        L_0x00fb:
            r1 = move-exception;
            goto L_0x0082;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.FileFinder.SystemPartitionFileFinder.buildHashTree(java.io.File):void");
        }

        private byte[] hashFile(File file) {
            return Utils.getSha256(file);
        }

        private byte[] hashSymlink(File file) {
            try {
                String target = this.mOs.readLink(file);
                if (target == null) {
                    return defaultSha256;
                }
                byte[] hash = MessageDigest.getInstance("SHA-256").digest(target.getBytes("UTF-8"));
                if (hash == null) {
                    return defaultSha256;
                }
                return hash;
            } catch (NoSuchAlgorithmException e) {
                return defaultSha256;
            } catch (UnsupportedEncodingException e2) {
                return defaultSha256;
            } catch (OsException e3) {
                return defaultSha256;
            }
        }

        static byte[] getLstatBlob(LstatStruct lstatStruct) {
            if (lstatStruct == null) {
                return null;
            }
            int numBytes = 12;
            byte[] seLinuxBytes = null;
            if (lstatStruct.seLinuxSecurityContext != null) {
                try {
                    seLinuxBytes = lstatStruct.seLinuxSecurityContext.getBytes("UTF-8");
                    numBytes = 12 + seLinuxBytes.length;
                } catch (UnsupportedEncodingException e) {
                }
            }
            ByteBuffer byteBuffer = ByteBuffer.allocate(numBytes);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.putInt(lstatStruct.mode);
            byteBuffer.putInt(lstatStruct.uid);
            byteBuffer.putInt(lstatStruct.gid);
            if (seLinuxBytes != null) {
                byteBuffer.put(seLinuxBytes);
            }
            return byteBuffer.array();
        }

        private void updateFilesInfo(File file, byte[] hash, LstatStruct lstatStruct) {
            updateFilesInfo(file, hash, lstatStruct, false);
        }

        private void updateFilesInfo(File file, byte[] hash, LstatStruct lstatStruct, boolean isSymlink) {
            FilesInfo filesInfo = new FilesInfo();
            filesInfo.filename = file.getAbsolutePath();
            filesInfo.foundAtTimeMs = this.mCurrentTimeMs;
            filesInfo.lstatStruct = lstatStruct;
            filesInfo.present = true;
            filesInfo.sha256 = hash;
            if (lstatStruct != null) {
                filesInfo.isSetuidRoot = this.mOs.isSetuidRoot(lstatStruct);
            }
            if (isSymlink) {
                filesInfo.symlink = true;
                try {
                    filesInfo.symlinkTarget = this.mOs.readLink(file);
                } catch (OsException e) {
                }
            }
            this.mAllFiles.put(filesInfo.filename, filesInfo);
            FilesInfo previousInfo = this.mFilesDataStore.get(filesInfo.filename);
            if (!(previousInfo == null || previousInfo.foundAtTimeMs == filesInfo.foundAtTimeMs || Arrays.equals(previousInfo.sha256, filesInfo.sha256))) {
                DifferingFiles differingFiles = new DifferingFiles();
                differingFiles.previousFilesInfo = previousInfo;
                differingFiles.currentFilesInfo = filesInfo;
                this.mDifferingFilesList.add(differingFiles);
            }
            this.mFilesDataStore.put(filesInfo);
        }
    }

    FileFinder() {
    }

    static {
        TAG = FileFinder.class.getCanonicalName();
        FILE_NAMES = new String[]{"/system/bin/su", "/system/xbin/su"};
    }

    static List<FilesInfo> findFiles() {
        List<FilesInfo> filesInfoList = new ArrayList();
        for (String name : FILE_NAMES) {
            File file = new File(name);
            FilesInfo fileInfo = new FilesInfo();
            fileInfo.filename = name;
            fileInfo.present = file.exists();
            if (file.exists()) {
                fileInfo.sha256 = Utils.getSha256(file);
            }
            filesInfoList.add(fileInfo);
        }
        return filesInfoList;
    }
}
