package com.google.android.gms.auth;

public class GoogleAuthException extends Exception {
    public GoogleAuthException(String err) {
        super(err);
    }

    public GoogleAuthException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public GoogleAuthException(Throwable throwable) {
        super(throwable);
    }
}
