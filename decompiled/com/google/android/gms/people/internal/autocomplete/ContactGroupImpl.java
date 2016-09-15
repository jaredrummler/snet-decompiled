package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.ContactGroup;
import com.google.android.gms.people.Autocomplete.ContactGroupId;
import com.google.android.gms.people.Autocomplete.ContactGroupName;
import com.google.android.gms.people.Autocomplete.GroupExtendedData;

@Class(creator = "ContactGroupImplCreator")
public class ContactGroupImpl implements ContactGroup, SafeParcelable {
    public static final Creator<ContactGroupImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    final GroupExtendedDataImpl mExtendedData;
    @Field(id = 2)
    final ContactGroupIdImpl mId;
    @Field(id = 5)
    final int mMemberCount;
    @Field(id = 3)
    final ContactGroupNameImpl mName;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ContactGroupImplCreator();
    }

    @Constructor
    ContactGroupImpl(@Param(id = 1) int versionCode, @Param(id = 2) ContactGroupIdImpl id, @Param(id = 3) ContactGroupNameImpl name, @Param(id = 4) GroupExtendedDataImpl extendedData, @Param(id = 5) int memberCount) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mId = id;
        this.mExtendedData = extendedData;
        this.mMemberCount = memberCount;
    }

    public ContactGroupImpl(ContactGroupIdImpl id, ContactGroupNameImpl name, GroupExtendedDataImpl extendedData, int memberCount) {
        this(VERSION_CODE, id, name, extendedData, memberCount);
    }

    public ContactGroupName getName() {
        return this.mName;
    }

    public ContactGroupId getId() {
        return this.mId;
    }

    public GroupExtendedData getExtendedData() {
        return this.mExtendedData;
    }

    public int getMemberCount() {
        return this.mMemberCount;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ContactGroupImplCreator.writeToParcel(this, dest, flags);
    }
}
