package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.gms.lint.BuildConfig;
import javax.annotation.Nonnull;

public final class CircleCreation {
    public static final String ACTION = "com.google.android.gms.plus.audience.ACTION_CIRCLE_CREATION";
    private static final String EXTRA_ACCOUNT_NAME = "com.google.android.gms.common.audience.EXTRA_ACCOUNT_NAME";
    private static final String EXTRA_APP_ID = "com.google.android.gms.common.audience.EXTRA_APP_ID";
    private static final String EXTRA_HEADER_BACKGROUND_COLOR = "com.google.android.gms.common.audience.EXTRA_HEADER_BACKGROUND_COLOR";
    private static final String EXTRA_HEADER_TEXT_COLOR = "com.google.android.gms.common.audience.EXTRA_HEADER_TEXT_COLOR";
    private static final String EXTRA_PAGE_ID = "com.google.android.gms.common.audience.EXTRA_PAGE_ID";
    private static final String EXTRA_TARGET_PERSON = "com.google.android.gms.common.audience.EXTRA_TARGET_PERSON";
    private static final int GOOGLE_PLAY_SERVICES_APPLICATION_ID = 80;

    public static class IntentBuilder {
        private final Intent mIntent;

        public IntentBuilder(@Nonnull String accountName, String clientApplicationId) {
            this.mIntent = new Intent(CircleCreation.ACTION);
            setAccountName(accountName);
            setClientApplicationId(clientApplicationId);
        }

        public IntentBuilder setAccountName(@Nonnull String accountName) {
            if (TextUtils.isEmpty(accountName)) {
                throw new IllegalArgumentException("The account name is required.");
            }
            this.mIntent.putExtra(CircleCreation.EXTRA_ACCOUNT_NAME, accountName);
            return this;
        }

        public IntentBuilder setClientApplicationId(String clientApplicationId) {
            this.mIntent.putExtra(CircleCreation.EXTRA_APP_ID, clientApplicationId);
            return this;
        }

        public IntentBuilder setPageId(String pageId) {
            this.mIntent.putExtra(CircleCreation.EXTRA_PAGE_ID, pageId);
            return this;
        }

        public IntentBuilder setTargetPerson(AudienceMember targetPerson) {
            if (!TextUtils.isEmpty(targetPerson.getPeopleQualifiedId())) {
                this.mIntent.putExtra(CircleCreation.EXTRA_TARGET_PERSON, targetPerson);
            }
            return this;
        }

        public IntentBuilder setHeaderTextColor(int color) {
            this.mIntent.putExtra(CircleCreation.EXTRA_HEADER_TEXT_COLOR, color);
            return this;
        }

        public IntentBuilder setHeaderBackgroundColor(int color) {
            this.mIntent.putExtra(CircleCreation.EXTRA_HEADER_BACKGROUND_COLOR, color);
            return this;
        }

        public Intent build() {
            return this.mIntent;
        }
    }

    public static class IntentInterpreter {
        public static String getAccountName(Intent intent) {
            return intent.getStringExtra(CircleCreation.EXTRA_ACCOUNT_NAME);
        }

        public static String getClientApplicationId(Intent intent) {
            return BuildConfig.VERSION_NAME + getClientApplicationIdInt(intent);
        }

        public static int getClientApplicationIdInt(Intent intent) {
            String clientApplicationId = intent.getStringExtra(CircleCreation.EXTRA_APP_ID);
            if (TextUtils.isEmpty(clientApplicationId)) {
                return CircleCreation.GOOGLE_PLAY_SERVICES_APPLICATION_ID;
            }
            return Integer.parseInt(clientApplicationId);
        }

        public static String getPageId(Intent intent) {
            return intent.getStringExtra(CircleCreation.EXTRA_PAGE_ID);
        }

        public static int getHeaderTextColor(Intent intent, int defaultColor) {
            return intent.getIntExtra(CircleCreation.EXTRA_HEADER_TEXT_COLOR, defaultColor);
        }

        public static int getHeaderBackgroundColor(Intent intent, int defaultColor) {
            return intent.getIntExtra(CircleCreation.EXTRA_HEADER_BACKGROUND_COLOR, defaultColor);
        }

        public static AudienceMember getTargetPerson(Intent intent) {
            return (AudienceMember) intent.getParcelableExtra(CircleCreation.EXTRA_TARGET_PERSON);
        }
    }

    private CircleCreation() {
    }
}
