package com.google.android.gsf;

import android.util.AndroidException;

public class GoogleLoginServiceNotFoundException extends AndroidException {
    private int mErrorCode;

    public GoogleLoginServiceNotFoundException(int errorCode) {
        super(GoogleLoginServiceConstants.getErrorCodeMessage(errorCode));
        this.mErrorCode = errorCode;
    }

    int getErrorCode() {
        return this.mErrorCode;
    }
}
