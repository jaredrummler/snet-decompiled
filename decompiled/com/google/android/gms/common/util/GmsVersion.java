package com.google.android.gms.common.util;

public final class GmsVersion {
    public static final int VERSION_FENACHO = 3200000;
    public static final int VERSION_HALLOUMI = 4100000;
    public static final int VERSION_IBERICO = 4200000;
    public static final int VERSION_JARLSBERG = 4300000;
    public static final int VERSION_KENAFA = 4400000;
    public static final int VERSION_LONGHORN = 5000000;
    public static final int VERSION_MANCHEGO = 6000000;
    public static final int VERSION_NACHO = 6500000;
    public static final int VERSION_OLIVET = 6700000;
    public static final int VERSION_ORLA = 7000000;
    public static final int VERSION_PARMESAN = 7200000;
    public static final int VERSION_QUESO = 7500000;
    public static final int VERSION_REBLOCHON = 7800000;
    public static final int VERSION_SAGA = 8000000;
    public static final int VERSION_TALA = 8200000;
    public static final int VERSION_URDA = 8400000;

    private GmsVersion() {
    }

    public static boolean isAtLeastFenacho(int versionCode) {
        return versionCode >= VERSION_FENACHO;
    }

    public static boolean isAtLeastHalloumi(int versionCode) {
        return versionCode >= VERSION_HALLOUMI;
    }

    public static boolean isAtLeastJarlsberg(int versionCode) {
        return versionCode >= VERSION_JARLSBERG;
    }

    public static boolean isAtLeastLonghorn(int versionCode) {
        return versionCode >= VERSION_LONGHORN;
    }

    public static boolean isAtLeastManchego(int versionCode) {
        return versionCode >= VERSION_MANCHEGO;
    }

    public static boolean isAtLeastNacho(int versionCode) {
        return versionCode >= VERSION_NACHO;
    }

    public static boolean isAtLeastOlivet(int versionCode) {
        return versionCode >= VERSION_OLIVET;
    }

    public static boolean isAtLeastOrla(int versionCode) {
        return versionCode >= VERSION_ORLA;
    }

    public static boolean isAtLeastParmesan(int versionCode) {
        return versionCode >= VERSION_PARMESAN;
    }

    public static boolean isAtLeastQueso(int versionCode) {
        return versionCode >= VERSION_QUESO;
    }

    public static boolean isAtLeastReblochon(int versionCode) {
        return versionCode >= VERSION_REBLOCHON;
    }

    public static boolean isAtLeastSaga(int versionCode) {
        return versionCode >= VERSION_SAGA;
    }

    public static boolean isAtLeastTala(int versionCode) {
        return versionCode >= VERSION_TALA;
    }

    public static boolean isAtLeastUrda(int versionCode) {
        return versionCode >= VERSION_URDA;
    }
}
