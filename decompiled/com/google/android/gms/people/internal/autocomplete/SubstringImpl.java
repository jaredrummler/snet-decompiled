package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Autocomplete.Substring;

@Class(creator = "SubstringImplCreator")
public class SubstringImpl implements Substring, SafeParcelable {
    public static final Creator<SubstringImpl> CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 3)
    final int mLength;
    @Field(id = 2)
    final int mStartIndex;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new SubstringImplCreator();
    }

    @Constructor
    SubstringImpl(@Param(id = 1) int versionCode, @Param(id = 2) int startIndex, @Param(id = 3) int length) {
        this.mVersionCode = versionCode;
        this.mStartIndex = startIndex;
        this.mLength = length;
    }

    public SubstringImpl(int startIndex, int length) {
        this(VERSION_CODE, startIndex, length);
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    public int getLength() {
        return this.mLength;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        SubstringImplCreator.writeToParcel(this, dest, flags);
    }
}
