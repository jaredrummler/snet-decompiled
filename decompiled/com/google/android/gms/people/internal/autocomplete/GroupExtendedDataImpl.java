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
import com.google.android.gms.people.Autocomplete.GroupExtendedData;

@Class(creator = "GroupExtendedDataImplCreator")
public class GroupExtendedDataImpl implements GroupExtendedData, SafeParcelable {
    public static final Creator<GroupExtendedDataImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final ContactPreferredFieldsEntity[] mContactPreferences;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new GroupExtendedDataImplCreator();
    }

    @Constructor
    GroupExtendedDataImpl(@Param(id = 1) int versionCode, @Param(id = 2) ContactPreferredFieldsEntity[] contactPreferences) {
        this.mVersionCode = versionCode;
        this.mContactPreferences = contactPreferences;
    }

    public GroupExtendedDataImpl(ContactPreferredFieldsEntity[] contactPreferences) {
        this(VERSION_CODE, contactPreferences);
    }

    public ContactPreferredFields[] getContactPreferences() {
        return this.mContactPreferences;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        GroupExtendedDataImplCreator.writeToParcel(this, dest, flags);
    }
}
