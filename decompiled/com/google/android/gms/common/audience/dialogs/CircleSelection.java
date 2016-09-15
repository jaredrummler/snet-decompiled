package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.people.data.AudienceMember;
import java.util.ArrayList;
import java.util.List;

public final class CircleSelection {
    public static final String ACTION_CHOOSE_CIRCLES = "com.google.android.gms.common.acl.CHOOSE_CIRCLES";
    static final String ACTION_UPDATE_CIRCLES = "com.google.android.gms.common.acl.UPDATE_CIRCLES";

    public interface UpdateBuilder {
        Intent build();

        UpdateBuilder setAccountName(String str);

        UpdateBuilder setClientApplicationId(String str);

        UpdateBuilder setHeaderBackgroundColor(int i);

        UpdateBuilder setHeaderTextColor(int i);

        UpdateBuilder setInitialCircles(List<AudienceMember> list);

        UpdateBuilder setPlusPageId(String str);

        UpdateBuilder setTitleText(String str);

        UpdateBuilder setUpdatePerson(AudienceMember audienceMember);

        UpdateBuilder setUpdatePersonId(String str);
    }

    public interface SelectBuilder {
        Intent build();

        SelectBuilder setAccountName(String str);

        SelectBuilder setClientApplicationId(String str);

        SelectBuilder setInitialCircles(List<AudienceMember> list);

        SelectBuilder setPlusPageId(String str);

        SelectBuilder setTitleText(String str);
    }

    public interface Results {
        ArrayList<AudienceMember> getAddedCirclesDelta();

        List<AudienceMember> getInitialCircles();

        ArrayList<AudienceMember> getRemovedCirclesDelta();

        ArrayList<AudienceMember> getSelectedCircles();

        AudienceMember getUpdatePerson();
    }

    private CircleSelection() {
    }

    public static SelectBuilder getChooseCirclesBuilder() {
        return new AudienceSelectionIntentBuilder(ACTION_CHOOSE_CIRCLES);
    }

    public static UpdateBuilder getUpdateCirclesBuilder() {
        return new AudienceSelectionIntentBuilder(ACTION_UPDATE_CIRCLES);
    }

    public static Results getResults(Intent intent) {
        return new AudienceSelectionIntentBuilder(intent);
    }

    public static ArrayList<AudienceMember> getAddedCirclesDeltaFromResult(Intent intent) {
        return AudienceSelectionIntentBuilder.getAddedAudienceDelta(intent);
    }

    public static ArrayList<AudienceMember> getRemovedCirclesDeltaFromResult(Intent intent) {
        return AudienceSelectionIntentBuilder.getRemovedAudienceDelta(intent);
    }

    public static ArrayList<AudienceMember> getSelectedCirclesFromResult(Intent intent) {
        return AudienceSelectionIntentBuilder.getSelectedAudienceMembers(intent);
    }
}
