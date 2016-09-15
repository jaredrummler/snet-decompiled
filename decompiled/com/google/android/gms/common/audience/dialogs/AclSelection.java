package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.people.data.Audience;
import com.google.android.gms.common.people.data.AudienceMember;
import java.util.ArrayList;
import java.util.List;

public final class AclSelection {
    static final String ACTION_CHOOSE_ACL = "com.google.android.gms.common.acl.CHOOSE_ACL";
    public static final int PEOPLE_TYPE_AGGREGATED = 1;
    public static final int PEOPLE_TYPE_CIRCLED = 0;

    public interface Builder {
        Intent build();

        Builder setAccountName(String str);

        Builder setAllowEmptySelection(boolean z);

        Builder setClientApplicationId(String str);

        Builder setDescription(String str);

        Builder setDomainRestricted(int i);

        Builder setInitialAcl(Audience audience);

        @Deprecated
        Builder setInitialAcl(List<AudienceMember> list);

        Builder setKnownAudienceMembers(List<AudienceMember> list);

        Builder setLoadPeopleType(int i);

        Builder setOkText(String str);

        Builder setPlusPageId(String str);

        Builder setShowCancel(boolean z);

        Builder setTitleText(String str);
    }

    public interface DomainRestricted {
        public static final int NOT_VISIBLE = 0;
        public static final int OFF = 2;
        public static final int ON = 1;
    }

    public interface Results {
        ArrayList<AudienceMember> getAddedAudienceDelta();

        int getDomainRestricted();

        List<AudienceMember> getInitialAudienceMembers();

        ArrayList<AudienceMember> getRemovedAudienceDelta();

        ArrayList<AudienceMember> getSelectedAudienceMembers();
    }

    private AclSelection() {
    }

    public static Builder getBuilder() {
        return new AudienceSelectionIntentBuilder(ACTION_CHOOSE_ACL);
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
