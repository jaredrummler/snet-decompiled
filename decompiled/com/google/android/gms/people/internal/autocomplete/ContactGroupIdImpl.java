package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.ContactGroupId;

@Class(creator = "ContactGroupIdImplCreator")
public class ContactGroupIdImpl implements ContactGroupId, SafeParcelable {
    public static final Creator<ContactGroupIdImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final String mId;
    @Field(id = 3)
    final String[] mLegacyId;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ContactGroupIdImplCreator();
    }

    @Constructor
    ContactGroupIdImpl(@Param(id = 1) int versionCode, @Param(id = 2) String id, @Param(id = 3) String[] legacyId) {
        this.mVersionCode = versionCode;
        this.mId = id;
        this.mLegacyId = legacyId;
    }

    public ContactGroupIdImpl(String id, String[] legacyId) {
        this(VERSION_CODE, id, legacyId);
    }

    public String getId() {
        return this.mId;
    }

    public String[] getLegacyId() {
        return this.mLegacyId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ContactGroupIdImplCreator.writeToParcel(this, dest, flags);
    }
}
