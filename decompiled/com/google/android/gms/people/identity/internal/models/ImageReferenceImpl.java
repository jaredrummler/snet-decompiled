package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Indicator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.identity.models.ImageReference;
import java.util.HashSet;
import java.util.Set;

@Class(creator = "ImageReferenceImplCreator")
@VisibleForTesting
public class ImageReferenceImpl implements SafeParcelable, ImageReference {
    public static final ImageReferenceImplCreator CREATOR;
    private static final int VERSION_CODE = 1;
    @Field(id = 4)
    byte[] mData;
    @Indicator
    final Set<Integer> mIndicatorSet;
    @Field(id = 3)
    String mLocation;
    @Field(id = 2)
    int mType;
    @VersionField(id = 1)
    final int mVersionCode;

    static {
        CREATOR = new ImageReferenceImplCreator();
    }

    public ImageReferenceImpl() {
        this.mIndicatorSet = new HashSet();
        this.mVersionCode = VERSION_CODE;
    }

    @Constructor
    ImageReferenceImpl(@Indicator Set<Integer> indicatorSet, @Param(id = 1) int versionCode, @Param(id = 2) int type, @Param(id = 3) String location, @Param(id = 4) byte[] data) {
        this.mIndicatorSet = indicatorSet;
        this.mVersionCode = versionCode;
        this.mType = type;
        this.mLocation = location;
        this.mData = data;
    }

    public ImageReferenceImpl(ImageReferenceBase other) {
        this();
        importData(other);
    }

    public ImageReferenceImpl importData(ImageReferenceBase other) {
        clearType();
        if (other.hasType()) {
            setType(other.getType());
        }
        clearLocation();
        if (other.hasLocation()) {
            setLocation(other.getLocation());
        }
        clearData();
        if (other.hasData()) {
            setData(other.getData());
        }
        return this;
    }

    public boolean hasType() {
        return this.mIndicatorSet.contains(Integer.valueOf(2));
    }

    public int getType() {
        return this.mType;
    }

    public ImageReferenceImpl setType(int value) {
        this.mIndicatorSet.add(Integer.valueOf(2));
        this.mType = value;
        return this;
    }

    public ImageReferenceImpl clearType() {
        this.mIndicatorSet.remove(Integer.valueOf(2));
        return this;
    }

    public boolean hasLocation() {
        return this.mLocation != null;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public ImageReferenceImpl setLocation(String value) {
        this.mLocation = value;
        return this;
    }

    public ImageReferenceImpl clearLocation() {
        return setLocation(null);
    }

    public boolean hasData() {
        return this.mData != null;
    }

    public byte[] getData() {
        return this.mData;
    }

    public ImageReferenceImpl setData(byte[] value) {
        this.mData = value;
        return this;
    }

    public ImageReferenceImpl clearData() {
        return setData(null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ImageReferenceImplCreator.writeToParcel(this, out, flags);
    }
}
