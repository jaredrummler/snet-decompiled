package com.google.android.gms.people.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.people.Images.LoadImageOptions;

@Class(creator = "ParcelableLoadImageOptionsCreator")
public class ParcelableLoadImageOptions implements SafeParcelable {
    public static final ParcelableLoadImageOptionsCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(getter = "getAvatarOptions", id = 2)
    private final int mAvatarOptions;
    @Field(getter = "getImageSize", id = 1)
    private final int mImageSize;
    @Field(getter = "isUseLargePictureForCp2Images", id = 3)
    private final boolean mUseLargePictureForCp2Images;
    @VersionField(getter = "getVersionCode", id = 1000)
    private final int mVersionCode;

    static {
        CREATOR = new ParcelableLoadImageOptionsCreator();
    }

    @Constructor
    ParcelableLoadImageOptions(@Param(id = 1000) int versionCode, @Param(id = 1) int imageSize, @Param(id = 2) int avatarOptions, @Param(id = 3) boolean useLargePictureForCp2Images) {
        this.mVersionCode = versionCode;
        this.mImageSize = imageSize;
        this.mAvatarOptions = avatarOptions;
        this.mUseLargePictureForCp2Images = useLargePictureForCp2Images;
    }

    public static ParcelableLoadImageOptions build(LoadImageOptions options) {
        if (options == null) {
            options = LoadImageOptions.DEFAULT;
        }
        return new ParcelableLoadImageOptions(VERSION_CODE, options.imageSize, options.avatarOptions, options.useLargePictureForCp2Images);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ParcelableLoadImageOptionsCreator.writeToParcel(this, out, flags);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int getImageSize() {
        return this.mImageSize;
    }

    public int getAvatarOptions() {
        return this.mAvatarOptions;
    }

    public boolean isUseLargePictureForCp2Images() {
        return this.mUseLargePictureForCp2Images;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("imageSize", Integer.valueOf(this.mImageSize)).add("avatarOptions", Integer.valueOf(this.mAvatarOptions)).add("useLargePictureForCp2Images", Boolean.valueOf(this.mUseLargePictureForCp2Images)).toString();
    }
}
