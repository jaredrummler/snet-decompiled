package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.ContactPreferredFields;

@Class(creator = "ContactPreferredFieldsEntityCreator")
public class ContactPreferredFieldsEntity implements ContactPreferredFields, SafeParcelable {
    public static final Creator<ContactPreferredFieldsEntity> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final String mEmail;
    @Field(id = 3)
    final String mName;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ContactPreferredFieldsEntityCreator();
    }

    @Constructor
    ContactPreferredFieldsEntity(@Param(id = 1) int versionCode, @Param(id = 2) String email, @Param(id = 3) String name) {
        this.mVersionCode = versionCode;
        this.mName = name;
        this.mEmail = email;
    }

    public ContactPreferredFieldsEntity(String email, String name) {
        this(VERSION_CODE, email, name);
    }

    public ContactPreferredFieldsEntity(ContactPreferredFieldsRef contactPreferredFieldsRef) {
        this(getStringOrNull(contactPreferredFieldsRef.getEmail()), getStringOrNull(contactPreferredFieldsRef.getName()));
    }

    private static String getStringOrNull(CharSequence value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    public CharSequence getEmail() {
        return this.mEmail;
    }

    public CharSequence getName() {
        return this.mName;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ContactPreferredFieldsEntityCreator.writeToParcel(this, dest, flags);
    }

    public ContactPreferredFields freeze() {
        return this;
    }

    public boolean isDataValid() {
        return true;
    }
}
