package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.people.data.AudienceMember;

public class CircleCreationResponse {
    private static final String EXTRA_ADD_PERSON_STATUS_CODE = "com.google.android.gms.plus.audience.EXTRA_ADD_PERSON_STATUS_CODE";
    private static final String EXTRA_CIRCLE = "com.google.android.gms.plus.audience.EXTRA_CIRCLE";
    private static final String EXTRA_CREATE_CIRCLE_STATUS_CODE = "com.google.android.gms.plus.audience.EXTRA_CREATE_CIRCLE_STATUS_CODE";
    private static final String EXTRA_QUALIFIED_ID = "com.google.android.gms.plus.audience.EXTRA_QUALIFIED_ID";
    public static final int STATUS_CODE_ERROR = 2;
    public static final int STATUS_CODE_NOT_INTENDED = 3;
    public static final int STATUS_CODE_SUCCESS = 1;
    private static final int UNKNOWN_INT = 0;
    private final int mAddPersonStatusCode;
    private final AudienceMember mCircle;
    private final int mCreateCircleStatusCode;
    private final String mQualifiedId;

    public CircleCreationResponse(Intent intent) {
        this.mCreateCircleStatusCode = intent.getIntExtra(EXTRA_CREATE_CIRCLE_STATUS_CODE, 0);
        this.mCircle = (AudienceMember) intent.getParcelableExtra(EXTRA_CIRCLE);
        this.mAddPersonStatusCode = intent.getIntExtra(EXTRA_ADD_PERSON_STATUS_CODE, 0);
        this.mQualifiedId = intent.getStringExtra(EXTRA_QUALIFIED_ID);
        sanityCheck();
    }

    public CircleCreationResponse(int createCircleStatusCode, AudienceMember circle, int addPersonStatusCode, String qualifiedId) {
        this.mCreateCircleStatusCode = createCircleStatusCode;
        this.mCircle = circle;
        this.mAddPersonStatusCode = addPersonStatusCode;
        this.mQualifiedId = qualifiedId;
        sanityCheck();
    }

    private void sanityCheck() {
        Preconditions.checkNotZero(this.mCreateCircleStatusCode, (Object) "Invalid create circle status code.");
        Preconditions.checkNotZero(this.mAddPersonStatusCode, (Object) "Invalid add person status code.");
        if (this.mCreateCircleStatusCode == STATUS_CODE_SUCCESS) {
            Preconditions.checkNotNull(this.mCircle.getCircleId(), "Must provide a circle with circle id.");
            Preconditions.checkNotNull(this.mCircle.getDisplayName(), "Must provide a circle with display name.");
        }
        if (this.mAddPersonStatusCode == STATUS_CODE_SUCCESS) {
            Preconditions.checkNotNull(this.mQualifiedId, "Must provide qualified id.");
        }
    }

    public Intent convertToIntent() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_CREATE_CIRCLE_STATUS_CODE, this.mCreateCircleStatusCode);
        intent.putExtra(EXTRA_CIRCLE, this.mCircle);
        intent.putExtra(EXTRA_ADD_PERSON_STATUS_CODE, this.mAddPersonStatusCode);
        intent.putExtra(EXTRA_QUALIFIED_ID, this.mQualifiedId);
        return intent;
    }

    public AudienceMember getCircle() {
        return this.mCircle;
    }

    public String getQualifiedId() {
        return this.mQualifiedId;
    }

    public int getCreateCircleStatusCode() {
        return this.mCreateCircleStatusCode;
    }

    public int getAddPersonStatusCode() {
        return this.mAddPersonStatusCode;
    }
}
