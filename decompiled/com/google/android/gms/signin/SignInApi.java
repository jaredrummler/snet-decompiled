package com.google.android.gms.signin;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface SignInApi {
    PendingResult<GoogleSignInResult> getCurrentAccount(GoogleApiClient googleApiClient);
}
