package com.google.android.gtalkservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class ConnectionState implements Parcelable {
    public static final int CONNECTING = 2;
    public static final Creator<ConnectionState> CREATOR;
    public static final int IDLE = 0;
    public static final int LOGGED_IN = 3;
    public static final int ONLINE = 4;
    public static final int PENDING = 1;
    private volatile int mState;

    public ConnectionState(int state) {
        setState(state);
    }

    public ConnectionState(Parcel source) {
        this.mState = source.readInt();
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int state) {
        this.mState = state;
    }

    public boolean isLoggingIn() {
        return this.mState == CONNECTING;
    }

    public boolean isLoggedIn() {
        return this.mState >= LOGGED_IN;
    }

    public boolean isOnline() {
        return this.mState == ONLINE;
    }

    public boolean isDisconnected() {
        return this.mState == 0 || this.mState == PENDING;
    }

    public boolean isPendingReconnect() {
        return this.mState == PENDING;
    }

    public final String toString() {
        return toString(this.mState);
    }

    public static final String toString(int state) {
        switch (state) {
            case PENDING /*1*/:
                return "RECONNECTION_SCHEDULED";
            case CONNECTING /*2*/:
                return "CONNECTING";
            case LOGGED_IN /*3*/:
                return "AUTHENTICATED";
            case ONLINE /*4*/:
                return "ONLINE";
            default:
                return "IDLE";
        }
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mState);
    }

    public int describeContents() {
        return IDLE;
    }

    static {
        CREATOR = new Creator<ConnectionState>() {
            public ConnectionState createFromParcel(Parcel source) {
                return new ConnectionState(source);
            }

            public ConnectionState[] newArray(int size) {
                return new ConnectionState[size];
            }
        };
    }
}
