package com.google.android.gms.common.people.data;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.internal.PeopleUtils;

@Class(creator = "AudienceMemberCreator")
public final class AudienceMember implements SafeParcelable {
    public static final String AUDIENCE_GROUP_DOMAIN = "domain";
    public static final String AUDIENCE_GROUP_EXTENDED = "extendedCircles";
    public static final String AUDIENCE_GROUP_PUBLIC = "public";
    public static final String AUDIENCE_GROUP_YOUR_CIRCLES = "myCircles";
    public static final AudienceMemberCreator CREATOR;
    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_PERSON = 2;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getAvatarUrl", id = 6)
    private final String mAvatarUrl;
    @Field(getter = "getCircleId", id = 3)
    private final String mCircleId;
    @Field(getter = "getCircleType", id = 2)
    private final int mCircleType;
    @Field(getter = "getDisplayName", id = 5)
    private final String mDisplayName;
    @Field(getter = "getMetadata", id = 7)
    @Deprecated
    private final Bundle mMetadata;
    @Field(getter = "getPeopleQualifiedId", id = 4)
    private final String mPeopleQualifiedId;
    @Field(getter = "getType", id = 1)
    private final int mType;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        CREATOR = new AudienceMemberCreator();
    }

    @Constructor
    AudienceMember(@Param(id = 1000) int versionCode, @Param(id = 1) int type, @Param(id = 2) int circleType, @Param(id = 3) String circleId, @Param(id = 4) String peopleQualifiedId, @Param(id = 5) String displayName, @Param(id = 6) String avatarUrl, @Param(id = 7) Bundle metadata) {
        this.mVersionCode = versionCode;
        this.mType = type;
        this.mCircleType = circleType;
        this.mCircleId = circleId;
        this.mPeopleQualifiedId = peopleQualifiedId;
        this.mDisplayName = displayName;
        this.mAvatarUrl = avatarUrl;
        if (metadata == null) {
            metadata = new Bundle();
        }
        this.mMetadata = metadata;
    }

    private AudienceMember(int type, int circleType, String circleId, String qualifiedPersonId, String displayName, String avatarUrl) {
        this(VERSION_CODE, type, circleType, circleId, qualifiedPersonId, displayName, avatarUrl, null);
    }

    public static AudienceMember forPersonWithGaiaId(String gaiaId, String displayName, String avatarUrl) {
        Preconditions.checkNotEmpty(gaiaId, "Person ID must not be empty.");
        return forPersonWithPeopleQualifiedId(PeopleUtils.gaiaIdToPeopleQualifiedId(gaiaId), displayName, avatarUrl);
    }

    public static AudienceMember forPersonWithPeopleQualifiedId(String peopleQualifiedId, String displayName, String avatarUrl) {
        return new AudienceMember(TYPE_PERSON, 0, null, peopleQualifiedId, displayName, avatarUrl);
    }

    public static AudienceMember forPersonWithEmail(String email, String displayName) {
        return forPersonWithPeopleQualifiedId(PeopleUtils.emailToPeopleQualifiedId(email), displayName, null);
    }

    public static AudienceMember forCircle(String circleId, String displayName) {
        return new AudienceMember(VERSION_CODE, -1, circleId, null, displayName, null);
    }

    public static AudienceMember forGroup(String groupName, String displayName) {
        Integer groupId = (Integer) PeopleUtils.MAP_CIRCLE_TYPE.get(groupName);
        if (groupId == null) {
            groupId = (Integer) PeopleUtils.MAP_CIRCLE_TYPE.get(null);
        }
        return new AudienceMember(VERSION_CODE, groupId.intValue(), groupName, null, displayName, null);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int getType() {
        return this.mType;
    }

    public int getCircleType() {
        return this.mCircleType;
    }

    public String getCircleId() {
        return this.mCircleId;
    }

    public String getPeopleQualifiedId() {
        return this.mPeopleQualifiedId;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public String getAvatarUrl() {
        return this.mAvatarUrl;
    }

    public boolean isPersonalCircle() {
        return this.mType == VERSION_CODE && this.mCircleType == -1;
    }

    public boolean isSystemGroup() {
        return this.mType == VERSION_CODE && this.mCircleType != -1;
    }

    public boolean isPublicSystemGroup() {
        return this.mType == VERSION_CODE && this.mCircleType == VERSION_CODE;
    }

    public boolean isPerson() {
        return this.mType == TYPE_PERSON;
    }

    @Deprecated
    public Bundle getMetadata() {
        return this.mMetadata;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        AudienceMemberCreator.writeToParcel(this, out, flags);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.mType), Integer.valueOf(this.mCircleType), this.mCircleId, this.mPeopleQualifiedId);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudienceMember)) {
            return false;
        }
        AudienceMember audienceMember = (AudienceMember) obj;
        if (this.mVersionCode == audienceMember.mVersionCode && this.mType == audienceMember.mType && this.mCircleType == audienceMember.mCircleType && Objects.equal(this.mCircleId, audienceMember.mCircleId) && Objects.equal(this.mPeopleQualifiedId, audienceMember.mPeopleQualifiedId)) {
            return true;
        }
        return false;
    }

    public String toString() {
        Object[] objArr;
        if (isPerson()) {
            objArr = new Object[TYPE_PERSON];
            objArr[0] = getPeopleQualifiedId();
            objArr[VERSION_CODE] = getDisplayName();
            return String.format("Person [%s] %s", objArr);
        } else if (isPersonalCircle()) {
            objArr = new Object[TYPE_PERSON];
            objArr[0] = getCircleId();
            objArr[VERSION_CODE] = getDisplayName();
            return String.format("Circle [%s] %s", objArr);
        } else {
            objArr = new Object[TYPE_PERSON];
            objArr[0] = getCircleId();
            objArr[VERSION_CODE] = getDisplayName();
            return String.format("Group [%s] %s", objArr);
        }
    }
}
