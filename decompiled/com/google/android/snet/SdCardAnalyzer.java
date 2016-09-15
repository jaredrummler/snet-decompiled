package com.google.android.snet;

import android.content.Context;
import android.os.Environment;
import java.io.File;

class SdCardAnalyzer {
    static final byte[] JPEG_CONTENT;
    private static final String TAG;
    private final Context mContext;
    private final GBundle mGBundle;
    private SnetSharedPreferences mPreferences;

    public static class SdCardAnalysisInfo {
        public String jpegFileName;
        public boolean jpegMissing;
        public boolean jpegNewlyTampered;
        public boolean jpegTampered;
        public int jpegTamperedBytes;
        public long jpegWrongLength;
    }

    static {
        TAG = SdCardAnalyzer.class.getCanonicalName();
        JPEG_CONTENT = new byte[]{(byte) -1, (byte) -40, (byte) -1, (byte) -32, (byte) 0, (byte) 16, (byte) 74, (byte) 70, (byte) 73, (byte) 70, (byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) -1, (byte) -37, (byte) 0, (byte) 67, (byte) 0, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -64, (byte) 0, (byte) 11, (byte) 8, (byte) 0, (byte) 1, (byte) 0, (byte) 1, (byte) 1, (byte) 1, (byte) 17, (byte) 0, (byte) -1, (byte) -60, (byte) 0, (byte) 20, (byte) 0, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 3, (byte) -1, (byte) -60, (byte) 0, (byte) 20, (byte) 16, (byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) -1, (byte) -38, (byte) 0, (byte) 8, (byte) 1, (byte) 1, (byte) 0, (byte) 0, (byte) 63, (byte) 0, (byte) 71, (byte) -1, (byte) -39};
    }

    SdCardAnalyzer(Context context, GBundle gBundle) {
        this.mContext = context;
        this.mGBundle = gBundle;
        this.mPreferences = new SnetSharedPreferences(this.mContext);
    }

    SdCardAnalysisInfo sdCardTampered() {
        boolean z = false;
        SdCardAnalysisInfo info = new SdCardAnalysisInfo();
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File snetExternalDir = this.mContext.getExternalFilesDir(null);
            if (snetExternalDir != null) {
                if (!snetExternalDir.exists()) {
                    snetExternalDir.mkdirs();
                }
                boolean jpegWritten = this.mPreferences.sdCardJpegWritten();
                boolean previouslyTampered = this.mPreferences.sdCardJpegPreviouslyTampered();
                info.jpegFileName = this.mGBundle.getSdCardJpegName();
                if (!info.jpegFileName.equals(this.mPreferences.sdCardJpegFileNameUsed())) {
                    this.mPreferences.saveSdCardJpegWritten(false);
                    this.mPreferences.saveSdCardJpegPreviouslyTampered(false);
                    jpegWritten = false;
                    previouslyTampered = false;
                }
                File jpegFile = new File(snetExternalDir, info.jpegFileName);
                if (!jpegFile.exists()) {
                    if (jpegWritten) {
                        info.jpegMissing = true;
                        if (writeBytes(JPEG_CONTENT, jpegFile)) {
                            this.mPreferences.saveSdCardJpegFileNameUsed(info.jpegFileName);
                        } else {
                            this.mPreferences.saveSdCardJpegWritten(false);
                        }
                    } else if (writeBytes(JPEG_CONTENT, jpegFile)) {
                        this.mPreferences.saveSdCardJpegFileNameUsed(info.jpegFileName);
                        this.mPreferences.saveSdCardJpegWritten(true);
                    }
                    this.mPreferences.saveSdCardJpegPreviouslyTampered(false);
                } else if (jpegWritten) {
                    if (jpegFile.length() != ((long) JPEG_CONTENT.length)) {
                        info.jpegTampered = true;
                        info.jpegWrongLength = jpegFile.length();
                    } else {
                        info.jpegTamperedBytes = numTamperedBytes(Utils.readBytes(jpegFile));
                        if (info.jpegTamperedBytes != 0) {
                            z = true;
                        }
                        info.jpegTampered = z;
                    }
                    if (info.jpegTampered && !previouslyTampered) {
                        info.jpegNewlyTampered = true;
                        this.mPreferences.saveSdCardJpegPreviouslyTampered(true);
                    }
                }
            }
        }
        return info;
    }

    private static int numTamperedBytes(byte[] storedFile) {
        if (storedFile == null) {
            return JPEG_CONTENT.length;
        }
        int numTampered = 0;
        for (int i = 0; i < Math.min(storedFile.length, JPEG_CONTENT.length); i++) {
            if (storedFile[i] != JPEG_CONTENT[i]) {
                numTampered++;
            }
        }
        return numTampered + Math.abs(storedFile.length - JPEG_CONTENT.length);
    }

    static boolean writeBytes(byte[] data, File file) {
        if (!Utils.writeBytes(data, file)) {
            return false;
        }
        if (numTamperedBytes(Utils.readBytes(file)) == 0) {
            return true;
        }
        file.delete();
        return false;
    }
}
