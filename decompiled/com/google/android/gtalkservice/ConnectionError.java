package com.google.android.gtalkservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class ConnectionError implements Parcelable {
    public static final int AUTHENTICATION_EXPIRED = 5;
    public static final int AUTHENTICATION_FAILED = 4;
    public static final int CONNECTION_FAILED = 2;
    public static final Creator<ConnectionError> CREATOR;
    public static final int HEART_BEAT_TIMED_OUT = 6;
    public static final int NONE = 0;
    public static final int NO_NETWORK = 1;
    public static final int SERVER_ERROR = 7;
    public static final int SERVER_REJECT_RATE_LIMITING = 8;
    public static final int SERVICE_DISABLED = 11;
    public static final int SESSION_TERMINATED = 9;
    public static final int UNKNOWN = 10;
    public static final int UNKNOWN_HOST = 3;
    public static final String UNKNOWN_HOST_ERROR_STR = "host-unknown";
    private int mError;

    public ConnectionError(int error) {
        setError(error);
    }

    public ConnectionError(Parcel source) {
        this.mError = source.readInt();
    }

    public int getError() {
        return this.mError;
    }

    public void setError(int error) {
        this.mError = error;
    }

    public boolean isNoError() {
        return this.mError == 0;
    }

    public boolean isNetworkError() {
        return isNetworkError(this.mError);
    }

    public boolean isAuthenticationError() {
        return this.mError == AUTHENTICATION_FAILED;
    }

    public boolean isAuthenticationExpired() {
        return this.mError == AUTHENTICATION_EXPIRED;
    }

    public final String toString() {
        return toString(this.mError);
    }

    public static boolean isNetworkError(int error) {
        return error == NO_NETWORK || error == CONNECTION_FAILED || error == UNKNOWN_HOST || error == UNKNOWN;
    }

    public static boolean isAuthenticationError(int error) {
        return error == AUTHENTICATION_FAILED;
    }

    public static final String toString(int state) {
        switch (state) {
            case NO_NETWORK /*1*/:
                return "NO NETWORK";
            case CONNECTION_FAILED /*2*/:
                return "CONNECTION FAILED";
            case UNKNOWN_HOST /*3*/:
                return "UNKNOWN HOST";
            case AUTHENTICATION_FAILED /*4*/:
                return "AUTH FAILED";
            case AUTHENTICATION_EXPIRED /*5*/:
                return "AUTH EXPIRED";
            case HEART_BEAT_TIMED_OUT /*6*/:
                return "HEARTBEAT TIMEOUT";
            case SERVER_ERROR /*7*/:
                return "SERVER FAILED";
            case SERVER_REJECT_RATE_LIMITING /*8*/:
                return "SERVER REJECT - RATE LIMIT";
            case UNKNOWN /*10*/:
                return "UNKNOWN";
            default:
                return "NO ERROR";
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mError);
    }

    public int describeContents() {
        return NONE;
    }

    static {
        CREATOR = new Creator<ConnectionError>() {
            public ConnectionError createFromParcel(Parcel source) {
                return new ConnectionError(source);
            }

            public ConnectionError[] newArray(int size) {
                return new ConnectionError[size];
            }
        };
    }
}
