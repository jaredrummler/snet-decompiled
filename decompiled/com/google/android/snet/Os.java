package com.google.android.snet;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Os {
    private final boolean mApiPresent;
    private final int mIsuid;
    private final Object mLibcoreOsInstance;
    private final Method mLstatMethod;
    private final Method mReadLinkMethod;
    private final Method mSIslnkMethod;
    private final Field mStGidField;
    private final Field mStModeField;
    private final Field mStMtimeField;
    private final Field mStUidField;

    static class LstatStruct {
        int gid;
        int mode;
        long mtime;
        String seLinuxSecurityContext;
        int uid;

        LstatStruct() {
        }
    }

    static final class OsException extends Exception {
        private static final long serialVersionUID = 1;

        OsException() {
        }

        OsException(Throwable cause) {
            super(cause);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Os() {
        /*
        r25 = this;
        r25.<init>();
        r7 = 0;
        r8 = 0;
        r12 = 0;
        r11 = 0;
        r16 = 0;
        r15 = 0;
        r18 = 0;
        r17 = 0;
        r13 = 0;
        r2 = 1;
        r10 = "libcore.io";
        r20 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0143 }
        r20.<init>();	 Catch:{ ClassNotFoundException -> 0x0143 }
        r0 = r20;
        r20 = r0.append(r10);	 Catch:{ ClassNotFoundException -> 0x0143 }
        r21 = ".StructStat";
        r20 = r20.append(r21);	 Catch:{ ClassNotFoundException -> 0x0143 }
        r20 = r20.toString();	 Catch:{ ClassNotFoundException -> 0x0143 }
        java.lang.Class.forName(r20);	 Catch:{ ClassNotFoundException -> 0x0143 }
    L_0x002a:
        r20 = new java.lang.StringBuilder;	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20.<init>();	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r0 = r20;
        r20 = r0.append(r10);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r21 = ".StructStat";
        r20 = r20.append(r21);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = r20.toString();	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r19 = java.lang.Class.forName(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = new java.lang.StringBuilder;	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20.<init>();	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r0 = r20;
        r20 = r0.append(r10);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r21 = ".OsConstants";
        r20 = r20.append(r21);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = r20.toString();	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r9 = java.lang.Class.forName(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = new java.lang.StringBuilder;	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20.<init>();	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r0 = r20;
        r20 = r0.append(r10);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r21 = ".Os";
        r20 = r20.append(r21);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = r20.toString();	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r5 = java.lang.Class.forName(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "libcore.io.Libcore";
        r4 = java.lang.Class.forName(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "os";
        r0 = r20;
        r6 = r4.getField(r0);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = 0;
        r0 = r20;
        r7 = r6.get(r0);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "lstat";
        r21 = 1;
        r0 = r21;
        r0 = new java.lang.Class[r0];	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r21 = r0;
        r22 = 0;
        r23 = java.lang.String.class;
        r21[r22] = r23;	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r0 = r20;
        r1 = r21;
        r8 = r5.getMethod(r0, r1);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "S_ISLNK";
        r21 = 1;
        r0 = r21;
        r0 = new java.lang.Class[r0];	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r21 = r0;
        r22 = 0;
        r23 = java.lang.Integer.TYPE;	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r21[r22] = r23;	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r0 = r20;
        r1 = r21;
        r12 = r9.getMethod(r0, r1);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "st_mode";
        r16 = r19.getField(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "st_gid";
        r15 = r19.getField(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "st_uid";
        r18 = r19.getField(r20);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = "st_mtime";
        r17 = r19.getField(r20);	 Catch:{ NoSuchFieldException -> 0x017c, NoSuchMethodException -> 0x0148, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
    L_0x00d3:
        r20 = "S_ISUID";
        r0 = r20;
        r14 = r9.getField(r0);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
        r20 = 0;
        r0 = r20;
        r13 = r14.getInt(r0);	 Catch:{ NoSuchMethodException -> 0x0148, NoSuchFieldException -> 0x014b, ClassNotFoundException -> 0x014e, IllegalAccessException -> 0x0151, IllegalArgumentException -> 0x0154 }
    L_0x00e3:
        r20 = new java.lang.StringBuilder;	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r20.<init>();	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r0 = r20;
        r20 = r0.append(r10);	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r21 = ".Os";
        r20 = r20.append(r21);	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r20 = r20.toString();	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r5 = java.lang.Class.forName(r20);	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r20 = "readlink";
        r21 = 1;
        r0 = r21;
        r0 = new java.lang.Class[r0];	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r21 = r0;
        r22 = 0;
        r23 = java.lang.String.class;
        r21[r22] = r23;	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
        r0 = r20;
        r1 = r21;
        r11 = r5.getMethod(r0, r1);	 Catch:{ NoSuchMethodException -> 0x0157, ClassNotFoundException -> 0x017a }
    L_0x0114:
        r0 = r25;
        r0.mLibcoreOsInstance = r7;
        r0 = r25;
        r0.mLstatMethod = r8;
        r0 = r25;
        r0.mSIslnkMethod = r12;
        r0 = r25;
        r0.mReadLinkMethod = r11;
        r0 = r16;
        r1 = r25;
        r1.mStModeField = r0;
        r0 = r25;
        r0.mStGidField = r15;
        r0 = r18;
        r1 = r25;
        r1.mStUidField = r0;
        r0 = r17;
        r1 = r25;
        r1.mStMtimeField = r0;
        r0 = r25;
        r0.mIsuid = r13;
        r0 = r25;
        r0.mApiPresent = r2;
        return;
    L_0x0143:
        r3 = move-exception;
        r10 = "android.system";
        goto L_0x002a;
    L_0x0148:
        r3 = move-exception;
        r2 = 0;
        goto L_0x00e3;
    L_0x014b:
        r3 = move-exception;
        r2 = 0;
        goto L_0x00e3;
    L_0x014e:
        r3 = move-exception;
        r2 = 0;
        goto L_0x00e3;
    L_0x0151:
        r3 = move-exception;
        r2 = 0;
        goto L_0x00e3;
    L_0x0154:
        r3 = move-exception;
        r2 = 0;
        goto L_0x00e3;
    L_0x0157:
        r3 = move-exception;
        r20 = java.io.File.class;
        r21 = "readlink";
        r22 = 1;
        r0 = r22;
        r0 = new java.lang.Class[r0];	 Catch:{ NoSuchMethodException -> 0x0178 }
        r22 = r0;
        r23 = 0;
        r24 = java.lang.String.class;
        r22[r23] = r24;	 Catch:{ NoSuchMethodException -> 0x0178 }
        r11 = r20.getDeclaredMethod(r21, r22);	 Catch:{ NoSuchMethodException -> 0x0178 }
        if (r11 == 0) goto L_0x0114;
    L_0x0170:
        r20 = 1;
        r0 = r20;
        r11.setAccessible(r0);	 Catch:{ NoSuchMethodException -> 0x0178 }
        goto L_0x0114;
    L_0x0178:
        r20 = move-exception;
        goto L_0x0114;
    L_0x017a:
        r20 = move-exception;
        goto L_0x0114;
    L_0x017c:
        r20 = move-exception;
        goto L_0x00d3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.Os.<init>():void");
    }

    boolean apiPresent() {
        return this.mApiPresent;
    }

    boolean isSetuidRoot(File file) throws OsException {
        return isSetuidRoot(getLstatStruct(file));
    }

    boolean isSetuidRoot(LstatStruct lstatStruct) {
        if (lstatStruct == null || (lstatStruct.mode & this.mIsuid) == 0 || lstatStruct.uid != 0) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    com.google.android.snet.Os.LstatStruct getLstatStruct(java.io.File r14) throws com.google.android.snet.Os.OsException {
        /*
        r13 = this;
        r9 = r13.mApiPresent;
        if (r9 != 0) goto L_0x000a;
    L_0x0004:
        r9 = new com.google.android.snet.Os$OsException;
        r9.<init>();
        throw r9;
    L_0x000a:
        r3 = r14.getAbsolutePath();
        r9 = r13.mLstatMethod;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r10 = r13.mLibcoreOsInstance;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r11 = 1;
        r11 = new java.lang.Object[r11];	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r12 = 0;
        r11[r12] = r3;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r8 = r9.invoke(r10, r11);	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r5 = new com.google.android.snet.Os$LstatStruct;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r5.<init>();	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r13.mStUidField;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r9.getInt(r8);	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r5.uid = r9;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r13.mStGidField;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r9.getInt(r8);	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r5.gid = r9;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r13.mStModeField;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r9.getInt(r8);	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r5.mode = r9;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r9 = r13.mStMtimeField;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        if (r9 == 0) goto L_0x0045;
    L_0x003d:
        r9 = r13.mStMtimeField;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r10 = r9.getLong(r8);	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        r5.mtime = r10;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
    L_0x0045:
        r7 = 0;
        r9 = "android.os.SELinux";
        r6 = java.lang.Class.forName(r9);	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r9 = "getFileContext";
        r10 = 1;
        r10 = new java.lang.Class[r10];	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r11 = 0;
        r12 = java.lang.String.class;
        r10[r11] = r12;	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r4 = r6.getMethod(r9, r10);	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r9 = 0;
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r11 = 0;
        r10[r11] = r3;	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r9 = r4.invoke(r9, r10);	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r0 = r9;
        r0 = (java.lang.String) r0;	 Catch:{ NoSuchMethodException -> 0x0087, ClassNotFoundException -> 0x0085, IllegalAccessException -> 0x0083, IllegalArgumentException -> 0x0081, InvocationTargetException -> 0x007a }
        r7 = r0;
    L_0x0069:
        r5.seLinuxSecurityContext = r7;	 Catch:{ IllegalAccessException -> 0x006c, IllegalArgumentException -> 0x0073, InvocationTargetException -> 0x007a }
        return r5;
    L_0x006c:
        r2 = move-exception;
        r9 = new com.google.android.snet.Os$OsException;
        r9.<init>(r2);
        throw r9;
    L_0x0073:
        r2 = move-exception;
        r9 = new com.google.android.snet.Os$OsException;
        r9.<init>(r2);
        throw r9;
    L_0x007a:
        r2 = move-exception;
        r9 = new com.google.android.snet.Os$OsException;
        r9.<init>(r2);
        throw r9;
    L_0x0081:
        r9 = move-exception;
        goto L_0x0069;
    L_0x0083:
        r9 = move-exception;
        goto L_0x0069;
    L_0x0085:
        r9 = move-exception;
        goto L_0x0069;
    L_0x0087:
        r9 = move-exception;
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.snet.Os.getLstatStruct(java.io.File):com.google.android.snet.Os$LstatStruct");
    }

    boolean isSymlink(File file) throws OsException {
        int filePermissionsCode = getFilePermissionsCode(file);
        try {
            return ((Boolean) this.mSIslnkMethod.invoke(null, new Object[]{Integer.valueOf(filePermissionsCode)})).booleanValue();
        } catch (IllegalAccessException e) {
            throw new OsException(e);
        } catch (IllegalArgumentException e2) {
            throw new OsException(e2);
        } catch (InvocationTargetException e3) {
            throw new OsException(e3);
        }
    }

    String readLink(File file) throws OsException {
        if (!this.mApiPresent || this.mReadLinkMethod == null) {
            throw new OsException();
        }
        String filename = file.getAbsolutePath();
        try {
            return (String) this.mReadLinkMethod.invoke(this.mLibcoreOsInstance, new Object[]{filename});
        } catch (IllegalAccessException e) {
            throw new OsException(e);
        } catch (IllegalArgumentException e2) {
            throw new OsException(e2);
        } catch (InvocationTargetException e3) {
            throw new OsException(e3);
        }
    }

    private int getFilePermissionsCode(File file) throws OsException {
        if (this.mApiPresent) {
            String filename = file.getAbsolutePath();
            try {
                return this.mStModeField.getInt(this.mLstatMethod.invoke(this.mLibcoreOsInstance, new Object[]{filename}));
            } catch (IllegalAccessException e) {
                throw new OsException(e);
            } catch (IllegalArgumentException e2) {
                throw new OsException(e2);
            } catch (InvocationTargetException e3) {
                throw new OsException(e3);
            }
        }
        throw new OsException();
    }
}
