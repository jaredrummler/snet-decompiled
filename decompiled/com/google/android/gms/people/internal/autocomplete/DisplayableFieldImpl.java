package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.DisplayableField;
import com.google.android.gms.people.Autocomplete.Substring;

@Class(creator = "DisplayableFieldImplCreator")
public class DisplayableFieldImpl implements DisplayableField, SafeParcelable {
    public static final Creator<DisplayableFieldImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    final int mIndex;
    @Field(id = 5)
    final SubstringImpl[] mMatchingSubstrings;
    @Field(id = 2)
    final int mType;
    @Field(id = 4)
    final String mValue;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new DisplayableFieldImplCreator();
    }

    @Constructor
    DisplayableFieldImpl(@Param(id = 1) int versionCode, @Param(id = 2) int type, @Param(id = 3) int index, @Param(id = 4) String value, @Param(id = 5) SubstringImpl[] matchingSubstrings) {
        this.mVersionCode = versionCode;
        this.mType = type;
        this.mIndex = index;
        this.mValue = value;
        this.mMatchingSubstrings = matchingSubstrings;
    }

    public DisplayableFieldImpl(int type, int index, String value, SubstringImpl[] matchingSubstrings) {
        this(VERSION_CODE, type, index, value, matchingSubstrings);
    }

    public int getType() {
        return this.mType;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public CharSequence getValue() {
        return this.mValue;
    }

    public Substring[] getMatchingSubstrings() {
        return this.mMatchingSubstrings;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        DisplayableFieldImplCreator.writeToParcel(this, dest, flags);
    }
}
