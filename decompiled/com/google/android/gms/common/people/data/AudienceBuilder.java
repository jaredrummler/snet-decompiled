package com.google.android.gms.common.people.data;

import com.google.android.gms.common.internal.Preconditions;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class AudienceBuilder {
    public static final Audience EMPTY_AUDIENCE;
    private List<AudienceMember> mAudienceMembers;
    private int mDomainRestricted;
    private boolean mReadOnly;

    static {
        EMPTY_AUDIENCE = new AudienceBuilder().build();
    }

    public AudienceBuilder() {
        this.mAudienceMembers = Collections.emptyList();
        this.mDomainRestricted = 0;
        this.mReadOnly = false;
    }

    public AudienceBuilder(Audience audience) {
        Preconditions.checkNotNull(audience, "Audience must not be null.");
        this.mAudienceMembers = audience.getAudienceMemberList();
        this.mDomainRestricted = audience.getDomainRestricted();
        this.mReadOnly = audience.isReadOnly();
    }

    public AudienceBuilder setAudienceMembers(Collection<AudienceMember> audienceMembers) {
        this.mAudienceMembers = Collections.unmodifiableList(new ArrayList((Collection) Preconditions.checkNotNull(audienceMembers, "Audience members must not be null.")));
        return this;
    }

    public AudienceBuilder setDomainRestricted(int domainRestricted) {
        this.mDomainRestricted = checkDomainRestricted(domainRestricted);
        return this;
    }

    public AudienceBuilder setReadOnly(boolean readOnly) {
        this.mReadOnly = readOnly;
        return this;
    }

    public Audience build() {
        return new Audience(this.mAudienceMembers, this.mDomainRestricted, this.mReadOnly);
    }

    private int checkDomainRestricted(int domainRestricted) {
        switch (domainRestricted) {
            case Action.UNKNOWN /*0*/:
            case Type.TEMPORARY /*1*/:
            case Type.INDEFINITELY /*2*/:
                return domainRestricted;
            default:
                throw new IllegalArgumentException("Unknown domain restriction setting: " + domainRestricted);
        }
    }
}
