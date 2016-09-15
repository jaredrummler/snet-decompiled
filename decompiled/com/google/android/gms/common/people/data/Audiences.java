package com.google.android.gms.common.people.data;

import android.content.Intent;
import com.google.android.gms.common.audience.dialogs.AudienceSelectionIntentBuilder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collection;
import java.util.LinkedHashSet;
import javax.annotation.Nullable;

public final class Audiences {
    private Audiences() {
    }

    public static Audience addMember(Audience audience, AudienceMember audienceMember) {
        Preconditions.checkNotNull(audience, "Audience must not be null.");
        Preconditions.checkNotNull(audienceMember, "Audience member must not be null.");
        LinkedHashSet<AudienceMember> members = new LinkedHashSet(audience.getAudienceMemberList());
        members.add(audienceMember);
        return new AudienceBuilder(audience).setAudienceMembers(members).build();
    }

    public static Audience removeMember(Audience audience, AudienceMember audienceMember) {
        Preconditions.checkNotNull(audience, "Audience must not be null.");
        Preconditions.checkNotNull(audienceMember, "Audience member must not be null.");
        LinkedHashSet<AudienceMember> members = new LinkedHashSet(audience.getAudienceMemberList());
        members.remove(audienceMember);
        return new AudienceBuilder(audience).setAudienceMembers(members).build();
    }

    public static boolean hasMember(Audience audience, AudienceMember audienceMember) {
        Preconditions.checkNotNull(audience, "Audience must not be null.");
        Preconditions.checkNotNull(audienceMember, "Audience member must not be null.");
        return audience.getAudienceMemberList().contains(audienceMember);
    }

    public static boolean isEmpty(Audience audience) {
        Preconditions.checkNotNull(audience, "Audience must not be null.");
        return !audience.isReadOnly() && audience.getAudienceMemberList().isEmpty();
    }

    public static Audience addMemberList(Audience audience, Collection<AudienceMember> newMembers) {
        LinkedHashSet<AudienceMember> members = new LinkedHashSet(audience.getAudienceMemberList());
        members.addAll(newMembers);
        return new AudienceBuilder(audience).setAudienceMembers(members).build();
    }

    public static Audience fromAudienceSelectionIntent(Intent intent, @Nullable Audience baseAudience) {
        if (baseAudience == null) {
            baseAudience = new AudienceBuilder().build();
        }
        return new AudienceBuilder(baseAudience).setAudienceMembers(AudienceSelectionIntentBuilder.getSelectedAudienceMembers(intent)).build();
    }
}
