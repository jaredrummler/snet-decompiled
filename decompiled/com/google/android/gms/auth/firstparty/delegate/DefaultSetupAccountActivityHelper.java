package com.google.android.gms.auth.firstparty.delegate;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultSetupAccountActivityHelper {
    private static final String EXTRAS_KEY_ALLOWED_DOMAINS = "allowed_domains";
    private static final String EXTRAS_KEY_LOGIN_OPTIONS = "login_options";
    private static final String EXTRAS_KEY_MULTI_USER = "multi_user";
    private static final String EXTRAS_KEY_SETUP_WIZARD = "setup_wizard";

    public static class IntentBuilder {
        private Intent mIntent;

        public IntentBuilder() {
            this.mIntent = new Intent();
        }

        public IntentBuilder setSetupWizardInProgress(boolean isInProgress) {
            this.mIntent.putExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_SETUP_WIZARD, false);
            return this;
        }

        public IntentBuilder setMultiUserContext(boolean isMultiUserContext) {
            this.mIntent.putExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_MULTI_USER, false);
            return this;
        }

        public IntentBuilder setAllowedDomains(List<String> allowedDomains) {
            this.mIntent.putExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_ALLOWED_DOMAINS, new ArrayList((Collection) Preconditions.checkNotNull(allowedDomains)));
            return this;
        }

        public IntentBuilder setOptions(Bundle options) {
            this.mIntent.putExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_LOGIN_OPTIONS, new Bundle((Bundle) Preconditions.checkNotNull(options)));
            return this;
        }

        public Intent build() {
            return new Intent(this.mIntent);
        }
    }

    public static class IntentInterpreter {
        private final Intent mIntent;

        public IntentInterpreter(Intent intent) {
            this.mIntent = new Intent(intent);
        }

        public Bundle getOptions() {
            return new Bundle(this.mIntent.getBundleExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_LOGIN_OPTIONS));
        }

        public List<String> getAllowedDomains() {
            return new ArrayList(this.mIntent.getStringArrayListExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_ALLOWED_DOMAINS));
        }

        public boolean isMultiUser() {
            return this.mIntent.getBooleanExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_MULTI_USER, false);
        }

        public boolean isSetupWizardInProgress() {
            return this.mIntent.getBooleanExtra(DefaultSetupAccountActivityHelper.EXTRAS_KEY_SETUP_WIZARD, false);
        }
    }
}
