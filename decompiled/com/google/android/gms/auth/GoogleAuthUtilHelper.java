package com.google.android.gms.auth;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import java.io.IOException;
import java.util.List;

public class GoogleAuthUtilHelper {
    public static final GoogleAuthUtilHelper INSTANCE;

    static {
        INSTANCE = new GoogleAuthUtilHelper();
    }

    public String getToken(Context context, Account account, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return GoogleAuthUtilLight.getToken(context, account, scope);
    }

    public String getToken(Context context, Account account, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return GoogleAuthUtilLight.getToken(context, account, scope, extras);
    }

    public TokenData getTokenWithDetails(Context context, Account account, String scope) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return GoogleAuthUtilLight.getTokenWithDetails(context, account, scope);
    }

    public TokenData getTokenWithDetails(Context context, Account account, String scope, Bundle extras) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return GoogleAuthUtilLight.getTokenWithDetails(context, account, scope, extras);
    }

    public void clearToken(Context context, String token) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        GoogleAuthUtilLight.clearToken(context, token);
    }

    public String getAccountId(Context context, String accountName) throws GoogleAuthException, IOException {
        return GoogleAuthUtilLight.getAccountId(context, accountName);
    }

    public List<AccountChangeEvent> getAccountChangeEvents(Context context, int eventIndex, String accountName) throws GoogleAuthException, IOException {
        return GoogleAuthUtilLight.getAccountChangeEvents(context, eventIndex, accountName);
    }

    private GoogleAuthUtilHelper() {
    }
}
