package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.common.people.data.AudienceMember;
import javax.annotation.Nonnull;

public final class CircleUpdate {
    private static final String ACTION_ONLY_UPDATE = "com.google.android.gms.common.acl.ACTION_ONLY_UPDATE";
    private static final String DEFAULT_START_VIEW_NAMESPACE = "sg";
    private static final int DEFAULT_START_VIEW_TYPE_NUM = 0;
    private static final String EXTRA_ACCOUNT_NAME = "EXTRA_ACCOUNT_NAME";
    private static final String EXTRA_CLIENT_APPLICATION_ID = "EXTRA_CLIENT_APPLICATION_ID";
    private static final String EXTRA_PLUS_PAGE_ID = "EXTRA_PLUS_PAGE_ID";
    private static final String EXTRA_START_VIEW_NAMESPACE = "EXTRA_START_VIEW_NAMESPACE";
    private static final String EXTRA_START_VIEW_TYPE_NUM = "EXTRA_START_VIEW_TYPE_NUM";
    private static final String EXTRA_TARGET_CIRCLE_ID = "EXTRA_TARGET_CIRCLE_ID";
    private static final String EXTRA_UPDATE_PERSON = "EXTRA_UPDATE_PERSON";

    public static class IntentBuilder {
        private final Intent mIntent;

        public IntentBuilder(@Nonnull String accountName, @Nonnull String clientApplicationId) {
            this.mIntent = new Intent(CircleUpdate.ACTION_ONLY_UPDATE);
            setAccountName(accountName);
            setClientApplicationId(clientApplicationId);
        }

        public IntentBuilder setAccountName(@Nonnull String accountName) {
            if (TextUtils.isEmpty(accountName)) {
                throw new IllegalArgumentException("The account name is required.");
            }
            this.mIntent.putExtra(CircleUpdate.EXTRA_ACCOUNT_NAME, accountName);
            return this;
        }

        public IntentBuilder setPageId(String pageId) {
            this.mIntent.putExtra(CircleUpdate.EXTRA_PLUS_PAGE_ID, pageId);
            return this;
        }

        public IntentBuilder setClientApplicationId(String clientApplicationId) {
            this.mIntent.putExtra(CircleUpdate.EXTRA_CLIENT_APPLICATION_ID, clientApplicationId);
            return this;
        }

        public IntentBuilder setTargetCircleId(String targetCircleId) {
            this.mIntent.putExtra(CircleUpdate.EXTRA_TARGET_CIRCLE_ID, targetCircleId);
            return this;
        }

        public IntentBuilder setUpdatePerson(AudienceMember updatePerson) {
            this.mIntent.putExtra(CircleUpdate.EXTRA_UPDATE_PERSON, updatePerson);
            return this;
        }

        public IntentBuilder setStartViewNamespace(String namespace) {
            this.mIntent.putExtra(CircleUpdate.EXTRA_START_VIEW_NAMESPACE, namespace);
            return this;
        }

        public IntentBuilder setStartViewTypeNum(int typeNum) {
            this.mIntent.putExtra(CircleUpdate.EXTRA_START_VIEW_TYPE_NUM, typeNum);
            return this;
        }

        public Intent build() {
            return this.mIntent;
        }

        public static String getAccountName(Intent intent) {
            return intent.getStringExtra(CircleUpdate.EXTRA_ACCOUNT_NAME);
        }

        public static String getPageId(Intent intent) {
            return intent.getStringExtra(CircleUpdate.EXTRA_PLUS_PAGE_ID);
        }

        public static String getClientApplicationId(Intent intent) {
            return intent.getStringExtra(CircleUpdate.EXTRA_CLIENT_APPLICATION_ID);
        }

        public static String getTargetCircleId(Intent intent) {
            return intent.getStringExtra(CircleUpdate.EXTRA_TARGET_CIRCLE_ID);
        }

        public static AudienceMember getUpdatePerson(Intent intent) {
            return (AudienceMember) intent.getParcelableExtra(CircleUpdate.EXTRA_UPDATE_PERSON);
        }

        public static String getStartViewNamespace(Intent intent) {
            String result = intent.getStringExtra(CircleUpdate.EXTRA_START_VIEW_NAMESPACE);
            if (TextUtils.isEmpty(result)) {
                return CircleUpdate.DEFAULT_START_VIEW_NAMESPACE;
            }
            return result;
        }

        public static int getStartViewTypeNum(Intent intent) {
            return intent.getIntExtra(CircleUpdate.EXTRA_START_VIEW_TYPE_NUM, CircleUpdate.DEFAULT_START_VIEW_TYPE_NUM);
        }
    }

    public static class ResultBuilder {
        private final Intent mResult;

        public ResultBuilder() {
            this.mResult = new Intent();
        }

        public ResultBuilder setTargetCircleId(String targetCircleId) {
            this.mResult.putExtra(CircleUpdate.EXTRA_TARGET_CIRCLE_ID, targetCircleId);
            return this;
        }

        public ResultBuilder setUpdatePerson(AudienceMember updatePerson) {
            this.mResult.putExtra(CircleUpdate.EXTRA_UPDATE_PERSON, updatePerson);
            return this;
        }

        public Intent build() {
            return this.mResult;
        }

        public static String getTargetCircle(Intent result) {
            return result.getStringExtra(CircleUpdate.EXTRA_TARGET_CIRCLE_ID);
        }

        public static AudienceMember getUpdatePerson(Intent result) {
            return (AudienceMember) result.getParcelableExtra(CircleUpdate.EXTRA_UPDATE_PERSON);
        }
    }

    private CircleUpdate() {
    }
}
