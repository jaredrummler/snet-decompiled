package com.google.android.gms.common.people.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Class(creator = "AudienceCreator")
public final class Audience implements SafeParcelable {
    public static final AudienceCreator CREATOR;
    public static final int DOMAIN_RESTRICTED_NOT_SET = 0;
    public static final int DOMAIN_RESTRICTED_RESTRICTED = 1;
    public static final int DOMAIN_RESTRICTED_UNRESTRICTED = 2;
    private static final int VERSION_CODE = 2;
    @Field(getter = "getAudienceMemberList", id = 1)
    private final List<AudienceMember> mAudienceMembers;
    @Field(getter = "getDomainRestricted", id = 2)
    private final int mDomainRestricted;
    @Field(getter = "isFullyUnderstood", id = 3)
    @Deprecated
    private final boolean mIsFullyUnderstood;
    @Field(getter = "isReadOnly", id = 4)
    private final boolean mReadOnly;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        CREATOR = new AudienceCreator();
    }

    @Constructor
    @VisibleForTesting
    Audience(@Param(id = 1000) int versionCode, @Param(id = 1) List<AudienceMember> audienceMembers, @Param(id = 2) int domainRestricted, @Param(id = 3) boolean fullyUnderstood, @Param(id = 4) boolean readOnly) {
        boolean z = true;
        if (versionCode == DOMAIN_RESTRICTED_RESTRICTED && audienceMembers == null) {
            audienceMembers = Collections.emptyList();
        }
        this.mVersionCode = versionCode;
        this.mAudienceMembers = domainRestricted == DOMAIN_RESTRICTED_RESTRICTED ? removePublicMembers(audienceMembers) : Collections.unmodifiableList(audienceMembers);
        this.mDomainRestricted = domainRestricted;
        if (versionCode == DOMAIN_RESTRICTED_RESTRICTED) {
            boolean z2;
            this.mIsFullyUnderstood = fullyUnderstood;
            if (fullyUnderstood) {
                z2 = false;
            } else {
                z2 = true;
            }
            this.mReadOnly = z2;
            return;
        }
        this.mReadOnly = readOnly;
        if (readOnly) {
            z = false;
        }
        this.mIsFullyUnderstood = z;
    }

    @VisibleForTesting
    Audience(List<AudienceMember> audienceMembers, int domainRestricted, boolean readOnly) {
        boolean z = true;
        this.mVersionCode = VERSION_CODE;
        if (domainRestricted == DOMAIN_RESTRICTED_RESTRICTED) {
            audienceMembers = removePublicMembers(audienceMembers);
        }
        this.mAudienceMembers = audienceMembers;
        this.mDomainRestricted = domainRestricted;
        this.mReadOnly = readOnly;
        if (readOnly) {
            z = false;
        }
        this.mIsFullyUnderstood = z;
    }

    public List<AudienceMember> getAudienceMemberList() {
        return this.mAudienceMembers;
    }

    public int getDomainRestricted() {
        return this.mDomainRestricted;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isReadOnly() {
        return this.mReadOnly;
    }

    @Deprecated
    boolean isFullyUnderstood() {
        return this.mIsFullyUnderstood;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mVersionCode), this.mAudienceMembers, Integer.valueOf(this.mDomainRestricted), Boolean.valueOf(this.mReadOnly));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Audience)) {
            return false;
        }
        Audience audience = (Audience) obj;
        if (this.mVersionCode == audience.mVersionCode && Objects.equal(this.mAudienceMembers, audience.mAudienceMembers) && this.mDomainRestricted == audience.mDomainRestricted && this.mReadOnly == audience.mReadOnly) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return DOMAIN_RESTRICTED_NOT_SET;
    }

    public void writeToParcel(Parcel out, int flags) {
        AudienceCreator.writeToParcel(this, out, flags);
    }

    private static List<AudienceMember> removePublicMembers(List<AudienceMember> members) {
        List<AudienceMember> restrictedMembers = new ArrayList();
        for (AudienceMember member : members) {
            if (!member.isPublicSystemGroup()) {
                restrictedMembers.add(member);
            }
        }
        return Collections.unmodifiableList(restrictedMembers);
    }
}
