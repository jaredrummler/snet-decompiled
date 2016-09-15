package com.google.android.snet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.android.snet.FileFinder.FilesInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SnetFilesDataStore {
    private static final String COLUMN_FILE_GROUP = "file_group";
    private static final String COLUMN_FILE_OWNER = "file_owner";
    private static final String COLUMN_FILE_PERMISSIONS = "file_permissions";
    private static final String COLUMN_FULL_PATH = "full_path";
    private static final String COLUMN_IS_SETUID = "is_setuid_root";
    private static final String COLUMN_IS_SYMLINK = "is_symlink";
    private static final String COLUMN_SE_LINUX_SECURITY_CONTEXT = "se_linux_security_context";
    private static final String COLUMN_SHA256_DIGEST = "sha256_digest";
    private static final String COLUMN_SYMLINK_TARGET = "symlink_target";
    private static final String COLUMN_TIME_MS = "time_ms";
    private static final String DATABASE_NAME = "snet_files_info.db";
    private static final int DATABASE_VERSION = 1;
    private static final String[] FULL_PROJECTION;
    private static final int INDEX_FILE_GROUP = 8;
    private static final int INDEX_FILE_OWNER = 7;
    private static final int INDEX_FILE_PERMISSIONS = 6;
    private static final int INDEX_FULL_PATH = 0;
    private static final int INDEX_IS_SETUID = 3;
    private static final int INDEX_IS_SYMLINK = 4;
    private static final int INDEX_SE_LINUX_SECURITY_CONTEXT = 9;
    private static final int INDEX_SHA256_DIGEST = 2;
    private static final int INDEX_SYMLINK_TARGET = 5;
    private static final int INDEX_TIME_MS = 1;
    private static final String TABLE_FILES_INFO = "files_info";
    private SQLiteDatabase mDb;
    private final Helper mHelper;

    private class Helper extends SQLiteOpenHelper {
        private static final String QUERY_CREATE = "CREATE TABLE files_info (full_path STRING PRIMARY KEY, time_ms INTEGER, sha256_digest BLOB, is_setuid_root INTEGER, is_symlink INTEGER, symlink_target STRING, file_permissions INTEGER, file_owner INTEGER, file_group INTEGER, se_linux_security_context STRING);";

        Helper(Context context) {
            super(context, SnetFilesDataStore.DATABASE_NAME, null, SnetFilesDataStore.INDEX_TIME_MS);
        }

        public void onCreate(SQLiteDatabase database) {
            database.execSQL(QUERY_CREATE);
        }

        private void recreateDatabase(SQLiteDatabase database) {
            try {
                database.execSQL("DROP TABLE files_info");
            } catch (SQLException e) {
            }
            onCreate(database);
        }

        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            recreateDatabase(database);
        }
    }

    static {
        FULL_PROJECTION = new String[]{COLUMN_FULL_PATH, COLUMN_TIME_MS, COLUMN_SHA256_DIGEST, COLUMN_IS_SETUID, COLUMN_IS_SYMLINK, COLUMN_SYMLINK_TARGET, COLUMN_FILE_PERMISSIONS, COLUMN_FILE_OWNER, COLUMN_FILE_GROUP, COLUMN_SE_LINUX_SECURITY_CONTEXT};
    }

    SnetFilesDataStore(Context context) {
        this.mHelper = new Helper(context);
    }

    private void reopen() {
        this.mDb = this.mHelper.getWritableDatabase();
    }

    private void close() {
        this.mDb.close();
    }

    synchronized FilesInfo get(String fullPath) {
        FilesInfo filesInfo;
        reopen();
        String[] strArr = new String[INDEX_TIME_MS];
        strArr[INDEX_FULL_PATH] = fullPath;
        Cursor cursor = this.mDb.query(TABLE_FILES_INFO, FULL_PROJECTION, "full_path=?", strArr, null, null, null);
        try {
            if (cursor.getCount() != INDEX_TIME_MS) {
                filesInfo = null;
            } else {
                cursor.moveToNext();
                filesInfo = filesInfoFromCursor(cursor);
                cursor.close();
                close();
            }
        } finally {
            cursor.close();
            close();
        }
        return filesInfo;
    }

    synchronized List<FilesInfo> get(List<String> fullPaths) {
        List<FilesInfo> results;
        Cursor cursor;
        results = new ArrayList();
        reopen();
        for (String fullPath : fullPaths) {
            String[] strArr = new String[INDEX_TIME_MS];
            strArr[INDEX_FULL_PATH] = fullPath;
            cursor = this.mDb.query(TABLE_FILES_INFO, FULL_PROJECTION, "full_path=?", strArr, null, null, null);
            try {
                if (cursor.getCount() == INDEX_TIME_MS) {
                    cursor.moveToNext();
                    FilesInfo currentFilesInfo = filesInfoFromCursor(cursor);
                    if (currentFilesInfo != null) {
                        results.add(currentFilesInfo);
                    }
                    if (cursor != null) {
                        cursor.close();
                    } else {
                        continue;
                    }
                } else if (cursor != null) {
                    cursor.close();
                } else {
                    continue;
                }
            } catch (Throwable th) {
                close();
            }
        }
        close();
        return results;
    }

    synchronized List<FilesInfo> getSetuidFiles() {
        List<FilesInfo> list = null;
        synchronized (this) {
            reopen();
            Cursor cursor = this.mDb.query(TABLE_FILES_INFO, FULL_PROJECTION, "is_setuid_root=1", null, null, null, null);
            try {
                int numFiles = cursor.getCount();
                if (numFiles != 0) {
                    list = new ArrayList();
                    for (int i = INDEX_FULL_PATH; i < numFiles; i += INDEX_TIME_MS) {
                        cursor.moveToNext();
                        FilesInfo currentFilesInfo = filesInfoFromCursor(cursor);
                        if (currentFilesInfo != null) {
                            list.add(currentFilesInfo);
                        }
                    }
                    cursor.close();
                    close();
                }
            } finally {
                cursor.close();
                close();
            }
        }
        return list;
    }

    synchronized Map<String, FilesInfo> getAll() {
        Map<String, FilesInfo> result;
        reopen();
        Cursor cursor = this.mDb.query(TABLE_FILES_INFO, FULL_PROJECTION, null, null, null, null, null);
        result = new HashMap();
        while (cursor.moveToNext()) {
            FilesInfo filesInfo = filesInfoFromCursor(cursor);
            if (filesInfo != null) {
                result.put(filesInfo.filename, filesInfo);
            }
        }
        cursor.close();
        close();
        return result;
    }

    synchronized void put(FilesInfo filesInfo) {
        int i = INDEX_TIME_MS;
        synchronized (this) {
            if (filesInfo != null) {
                if (filesInfo.filename != null) {
                    int i2;
                    reopen();
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_FULL_PATH, filesInfo.filename);
                    if (filesInfo.sha256 != null) {
                        values.put(COLUMN_SHA256_DIGEST, filesInfo.sha256);
                    }
                    String str = COLUMN_IS_SETUID;
                    if (filesInfo.isSetuidRoot) {
                        i2 = INDEX_TIME_MS;
                    } else {
                        i2 = INDEX_FULL_PATH;
                    }
                    values.put(str, Integer.valueOf(i2));
                    values.put(COLUMN_TIME_MS, Long.valueOf(filesInfo.foundAtTimeMs));
                    String str2 = COLUMN_IS_SYMLINK;
                    if (!filesInfo.symlink) {
                        i = INDEX_FULL_PATH;
                    }
                    values.put(str2, Integer.valueOf(i));
                    if (filesInfo.symlinkTarget != null) {
                        values.put(COLUMN_SYMLINK_TARGET, filesInfo.symlinkTarget);
                    }
                    if (filesInfo.lstatStruct != null) {
                        values.put(COLUMN_FILE_PERMISSIONS, Integer.valueOf(filesInfo.lstatStruct.mode));
                        values.put(COLUMN_FILE_OWNER, Integer.valueOf(filesInfo.lstatStruct.uid));
                        values.put(COLUMN_FILE_GROUP, Integer.valueOf(filesInfo.lstatStruct.gid));
                        if (filesInfo.lstatStruct.seLinuxSecurityContext != null) {
                            values.put(COLUMN_SE_LINUX_SECURITY_CONTEXT, filesInfo.lstatStruct.seLinuxSecurityContext);
                        }
                    }
                    this.mDb.replace(TABLE_FILES_INFO, null, values);
                    close();
                }
            }
        }
    }

    synchronized void remove(String fullPath) {
        reopen();
        String[] strArr = new String[INDEX_TIME_MS];
        strArr[INDEX_FULL_PATH] = fullPath;
        this.mDb.delete(TABLE_FILES_INFO, "full_path=?", strArr);
        close();
    }

    private FilesInfo filesInfoFromCursor(Cursor cursor) {
        boolean z = true;
        String fullPath = cursor.getString(INDEX_FULL_PATH);
        if (TextUtils.isEmpty(fullPath)) {
            return null;
        }
        FilesInfo filesInfo = new FilesInfo();
        filesInfo.filename = fullPath;
        filesInfo.present = true;
        filesInfo.foundAtTimeMs = cursor.getLong(INDEX_TIME_MS);
        filesInfo.sha256 = cursor.getBlob(INDEX_SHA256_DIGEST);
        filesInfo.isSetuidRoot = cursor.getInt(INDEX_IS_SETUID) == INDEX_TIME_MS;
        if (cursor.getInt(INDEX_IS_SYMLINK) != INDEX_TIME_MS) {
            z = false;
        }
        filesInfo.symlink = z;
        filesInfo.symlinkTarget = cursor.getString(INDEX_SYMLINK_TARGET);
        filesInfo.lstatStruct = new LstatStruct();
        filesInfo.lstatStruct.mode = cursor.getInt(INDEX_FILE_PERMISSIONS);
        filesInfo.lstatStruct.uid = cursor.getInt(INDEX_FILE_OWNER);
        filesInfo.lstatStruct.gid = cursor.getInt(INDEX_FILE_GROUP);
        filesInfo.lstatStruct.seLinuxSecurityContext = cursor.getString(INDEX_SE_LINUX_SECURITY_CONTEXT);
        return filesInfo;
    }
}
