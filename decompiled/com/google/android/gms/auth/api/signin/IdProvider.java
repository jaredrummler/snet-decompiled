package com.google.android.gms.auth.api.signin;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.R;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import javax.annotation.Nullable;

public enum IdProvider {
    GOOGLE("google.com", R.string.auth_google_play_services_client_google_display_name, IdentityProviders.GOOGLE),
    FACEBOOK("facebook.com", R.string.auth_google_play_services_client_facebook_display_name, IdentityProviders.FACEBOOK);
    
    private static final String TAG = "IdProvider";
    private final String mAccountType;
    private final int mDisplayNameResId;
    private final String mProviderId;

    @Nullable
    public static IdProvider fromProviderId(@Nullable String providerId) {
        if (providerId != null) {
            for (IdProvider idProvider : values()) {
                if (idProvider.getProviderId().equals(providerId)) {
                    return idProvider;
                }
            }
            Log.w(TAG, "Unrecognized providerId: " + providerId);
        }
        return null;
    }

    @Nullable
    public static IdProvider fromAccountType(@Nullable String accountType) {
        if (accountType != null) {
            for (IdProvider idProvider : values()) {
                if (idProvider.getAccountType().equals(accountType)) {
                    return idProvider;
                }
            }
            Log.w(TAG, "Unrecognized accountType: " + accountType);
        }
        return null;
    }

    private IdProvider(String providerId, int displayNameId, String accountType) {
        this.mProviderId = providerId;
        this.mDisplayNameResId = displayNameId;
        this.mAccountType = accountType;
    }

    public String getProviderId() {
        return this.mProviderId;
    }

    public CharSequence getDisplayName(Context context) {
        return context.getResources().getString(this.mDisplayNameResId);
    }

    public String getAccountType() {
        return this.mAccountType;
    }

    public String toString() {
        return this.mProviderId;
    }
}
