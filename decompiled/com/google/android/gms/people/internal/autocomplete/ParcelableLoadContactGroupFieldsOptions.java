package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "ParcelableLoadContactGroupFieldsOptionsCreator")
public class ParcelableLoadContactGroupFieldsOptions implements SafeParcelable {
    public static final ParcelableLoadContactGroupFieldsOptionsCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 2)
    final String mContactGroupId;
    @VersionField(id = 1)
    final int mVersionCode;

    @Constructor
    ParcelableLoadContactGroupFieldsOptions(@Param(id = 1) int versionCode, @Param(id = 2) String contactGroupId) {
        this.mVersionCode = versionCode;
        this.mContactGroupId = contactGroupId;
    }

    public ParcelableLoadContactGroupFieldsOptions(String contactGroupId) {
        this(VERSION_CODE, contactGroupId);
    }

    public final String getContactGroupId() {
        return this.mContactGroupId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelableLoadContactGroupFieldsOptionsCreator.writeToParcel(this, dest, flags);
    }

    static {
        CREATOR = new ParcelableLoadContactGroupFieldsOptionsCreator();
    }
}
