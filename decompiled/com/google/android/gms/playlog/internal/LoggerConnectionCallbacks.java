package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.playlog.PlayLogger.LoggerCallbacks;

public class LoggerConnectionCallbacks implements ConnectionCallbacks, OnConnectionFailedListener {
    private final LoggerCallbacks mListener;
    private PlayLoggerImpl mLoggerImpl;
    private boolean mSendCallback;

    public LoggerConnectionCallbacks(LoggerCallbacks listener) {
        this.mListener = listener;
        this.mLoggerImpl = null;
        this.mSendCallback = true;
    }

    public void onConnected(Bundle connectionHint) {
        this.mLoggerImpl.setIsCaching(false);
        if (this.mSendCallback && this.mListener != null) {
            this.mListener.onLoggerConnected();
        }
        this.mSendCallback = false;
    }

    public void onConnectionSuspended(int cause) {
        this.mLoggerImpl.setIsCaching(true);
    }

    public void onConnectionFailed(ConnectionResult result) {
        this.mLoggerImpl.setIsCaching(true);
        if (this.mSendCallback && this.mListener != null) {
            if (result.hasResolution()) {
                this.mListener.onLoggerFailedConnectionWithResolution(result.getResolution());
            } else {
                this.mListener.onLoggerFailedConnection();
            }
        }
        this.mSendCallback = false;
    }

    public void setLoggerImpl(PlayLoggerImpl loggerImpl) {
        this.mLoggerImpl = loggerImpl;
    }

    public void setSendCallbackToListener(boolean flag) {
        this.mSendCallback = flag;
    }
}
