package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.PersonMetadata;

@Class(creator = "PersonMetadataImplCreator")
public class PersonMetadataImpl implements PersonMetadata, SafeParcelable {
    public static final Creator<PersonMetadataImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final String mOwnerId;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new PersonMetadataImplCreator();
    }

    @Constructor
    PersonMetadataImpl(@Param(id = 1) int versionCode, @Param(id = 2) String ownerId) {
        this.mVersionCode = versionCode;
        this.mOwnerId = ownerId;
    }

    public PersonMetadataImpl(String ownerId) {
        this(VERSION_CODE, ownerId);
    }

    public String getOwnerId() {
        return this.mOwnerId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        PersonMetadataImplCreator.writeToParcel(this, dest, flags);
    }
}
