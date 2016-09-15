package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.Autocompletion;
import com.google.android.gms.people.Autocomplete.ContactGroup;
import com.google.android.gms.people.Autocomplete.DisplayableField;
import com.google.android.gms.people.Autocomplete.Person;

@Class(creator = "AutocompletionImplCreator")
public class AutocompletionImpl implements Autocompletion, SafeParcelable {
    public static final Creator<AutocompletionImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    final ContactGroupImpl mContactGroup;
    @Field(id = 5)
    final DisplayableFieldImpl[] mMatches;
    @Field(id = 2)
    final int mObjectType;
    @Field(id = 3)
    final PersonImpl mPerson;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new AutocompletionImplCreator();
    }

    @Constructor
    AutocompletionImpl(@Param(id = 1) int versionCode, @Param(id = 2) int objectType, @Param(id = 3) PersonImpl person, @Param(id = 4) ContactGroupImpl contactGroup, @Param(id = 5) DisplayableFieldImpl[] matches) {
        this.mVersionCode = versionCode;
        this.mObjectType = objectType;
        this.mMatches = matches;
        this.mPerson = person;
        this.mContactGroup = contactGroup;
    }

    public AutocompletionImpl(PersonImpl person, DisplayableFieldImpl[] matches) {
        this(VERSION_CODE, VERSION_CODE, person, null, matches);
    }

    public AutocompletionImpl(ContactGroupImpl contactGroup, DisplayableFieldImpl[] matches) {
        this(VERSION_CODE, 5, null, contactGroup, matches);
    }

    public int getObjectType() {
        return this.mObjectType;
    }

    public DisplayableField[] getMatches() {
        return this.mMatches;
    }

    public Person getPerson() {
        return this.mPerson;
    }

    public ContactGroup getContactGroup() {
        return this.mContactGroup;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        AutocompletionImplCreator.writeToParcel(this, dest, flags);
    }
}
