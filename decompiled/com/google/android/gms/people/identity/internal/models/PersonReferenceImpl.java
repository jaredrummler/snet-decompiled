package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.models.PersonReference;
import java.util.HashSet;
import java.util.Set;

@Class(creator = "PersonReferenceImplCreator")
@VisibleForTesting
public class PersonReferenceImpl implements SafeParcelable, PersonReference {
    public static final PersonReferenceImplCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    ImageReferenceImpl mAvatarReference;
    @Indicator
    final Set<Integer> mIndicatorSet;
    @Field(id = 2)
    String mName;
    @Field(id = 3)
    String mQualifiedId;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new PersonReferenceImplCreator();
    }

    public PersonReferenceImpl() {
        this.mIndicatorSet = new HashSet();
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    PersonReferenceImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) String name, @Param(id = 3) String qualifiedId, @Param(id = 4) ImageReferenceImpl avatarReference) {
        this.mIndicatorSet = indicatorSet;
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mQualifiedId = qualifiedId;
        this.mAvatarReference = avatarReference;
    }

    public PersonReferenceImpl(PersonReferenceBase other) {
        this();
        importData(other);
    }

    public PersonReferenceImpl importData(PersonReferenceBase other) {
        clearName();
        if (other.hasName()) {
            setName(other.getName());
        }
        clearQualifiedId();
        if (other.hasQualifiedId()) {
            setQualifiedId(other.getQualifiedId());
        }
        clearAvatarReference();
        if (other.hasAvatarReference()) {
            setAvatarReference(new ImageReferenceImpl(other.getAvatarReference()));
        }
        return this;
    }

    public boolean hasName() {
        return this.mName != null;
    }

    public String getName() {
        return this.mName;
    }

    public PersonReferenceImpl setName(String value) {
        this.mName = value;
        return this;
    }

    public PersonReferenceImpl clearName() {
        return setName(null);
    }

    public boolean hasQualifiedId() {
        return this.mQualifiedId != null;
    }

    public String getQualifiedId() {
        return this.mQualifiedId;
    }

    public PersonReferenceImpl setQualifiedId(String value) {
        this.mQualifiedId = value;
        return this;
    }

    public PersonReferenceImpl clearQualifiedId() {
        return setQualifiedId(null);
    }

    public boolean hasAvatarReference() {
        return this.mAvatarReference != null;
    }

    public ImageReferenceImpl getAvatarReference() {
        return this.mAvatarReference;
    }

    public PersonReferenceImpl setAvatarReference(ImageReferenceImpl value) {
        this.mAvatarReference = value;
        return this;
    }

    public PersonReferenceImpl clearAvatarReference() {
        return setAvatarReference(null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        PersonReferenceImplCreator.writeToParcel(this, out, flags);
    }
}
