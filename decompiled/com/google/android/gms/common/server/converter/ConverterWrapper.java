package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.server.response.FastJsonResponse.FieldConverter;

@Class(creator = "ConverterWrapperCreator")
public class ConverterWrapper implements SafeParcelable {
    public static final ConverterWrapperCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getStringToIntConverter", id = 2)
    private final StringToIntConverter mStringToIntConverter;
    @VersionField(getter = "getVersionCode", id = 1)
    private final int mVersionCode;

    static {
        CREATOR = new ConverterWrapperCreator();
    }

    @Constructor
    ConverterWrapper(@Param(id = 1) int versionCode, @Param(id = 2) StringToIntConverter stringToIntConverter) {
        this.mVersionCode = versionCode;
        this.mStringToIntConverter = stringToIntConverter;
    }

    private ConverterWrapper(StringToIntConverter stringToIntConverter) {
        this.mVersionCode = VERSION_CODE;
        this.mStringToIntConverter = stringToIntConverter;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    StringToIntConverter getStringToIntConverter() {
        return this.mStringToIntConverter;
    }

    public static ConverterWrapper wrap(FieldConverter<?, ?> converter) {
        if (converter instanceof StringToIntConverter) {
            return new ConverterWrapper((StringToIntConverter) converter);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public FieldConverter<?, ?> unwrap() {
        if (this.mStringToIntConverter != null) {
            return this.mStringToIntConverter;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    public int describeContents() {
        ConverterWrapperCreator converterWrapperCreator = CREATOR;
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ConverterWrapperCreator converterWrapperCreator = CREATOR;
        ConverterWrapperCreator.writeToParcel(this, out, flags);
    }
}
