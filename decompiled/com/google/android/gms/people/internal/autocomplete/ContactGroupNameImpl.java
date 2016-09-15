package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.ContactGroupName;

@Class(creator = "ContactGroupNameImplCreator")
public class ContactGroupNameImpl implements ContactGroupName, SafeParcelable {
    public static final Creator<ContactGroupNameImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    final String mFormattedValue;
    @Field(id = 2)
    final String mValue;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ContactGroupNameImplCreator();
    }

    @Constructor
    ContactGroupNameImpl(@Param(id = 1) int versionCode, @Param(id = 2) String value, @Param(id = 3) String formattedValue) {
        this.mVersionCode = versionCode;
        this.mValue = value;
        this.mFormattedValue = formattedValue;
    }

    public ContactGroupNameImpl(String value, String formattedValue) {
        this(VERSION_CODE, value, formattedValue);
    }

    public CharSequence getValue() {
        return this.mValue;
    }

    public CharSequence getFormattedValue() {
        return this.mFormattedValue;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ContactGroupNameImplCreator.writeToParcel(this, dest, flags);
    }
}
