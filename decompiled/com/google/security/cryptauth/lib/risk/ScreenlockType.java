package com.google.security.cryptauth.lib.risk;

public interface ScreenlockType {
    public static final int BIOMETRIC_SCREENLOCK = 50;
    public static final int FACE_SCREENLOCK = 20;
    public static final int INSECURE_SCREENLOCK = 5;
    public static final int NO_SCREENLOCK = 1;
    public static final int PATTERN_SCREENLOCK = 30;
    public static final int PIN_SCREENLOCK = 40;
    public static final int SECURE_SCREENLOCK = 10;
    public static final int UNKNOWN_SCREENLOCK = 0;
}
