package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.people.data.AudienceMember;
import java.util.ArrayList;
import java.util.List;

public final class FaclSelection {
    static final String ACTION_CHOOSE_FACL = "com.google.android.gms.common.acl.CHOOSE_FACL";

    public interface Builder {
        Intent build();

        Builder setAccountName(String str);

        Builder setAllCirclesChecked(boolean z);

        Builder setAllContactsChecked(boolean z);

        AudienceSelectionIntentBuilder setClientApplicationId(String str);

        Builder setDescription(String str);

        Builder setHasShowCircles(boolean z);

        Builder setInitialCircles(List<AudienceMember> list);

        AudienceSelectionIntentBuilder setKnownAudienceMembers(List<AudienceMember> list);

        Builder setPlusPageId(String str);

        Builder setShowCircles(boolean z);

        Builder setShowContacts(boolean z);

        Builder setTitleLogo(String str);

        Builder setTitleText(String str);
    }

    public interface Results {
        ArrayList<AudienceMember> getAddedAudienceDelta();

        boolean getAllCirclesChecked();

        boolean getAllContactsChecked();

        List<AudienceMember> getInitialAudienceMembers();

        ArrayList<AudienceMember> getRemovedAudienceDelta();

        ArrayList<AudienceMember> getSelectedAudienceMembers();
    }

    private FaclSelection() {
    }

    public static Builder getBuilder() {
        return new AudienceSelectionIntentBuilder(ACTION_CHOOSE_FACL);
    }

    public static Results getResults(Intent intent) {
        return new AudienceSelectionIntentBuilder(intent);
    }

    public static ArrayList<AudienceMember> getAddedAclDeltaFromResult(Intent intent) {
        return AudienceSelectionIntentBuilder.getAddedAudienceDelta(intent);
    }

    public static ArrayList<AudienceMember> getSelectedAclsFromResult(Intent intent) {
        return AudienceSelectionIntentBuilder.getSelectedAudienceMembers(intent);
    }
}
