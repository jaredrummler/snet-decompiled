package com.google.android.gms.people.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ParcelableLoadImageOptionsCreator implements Creator<ParcelableLoadImageOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ParcelableLoadImageOptions createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mImageSize = 0;
        int _local_safe_0a1b_mAvatarOptions = 0;
        boolean _local_safe_0a1b_mUseLargePictureForCp2Images = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mImageSize = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mAvatarOptions = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mUseLargePictureForCp2Images = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 1000:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ParcelableLoadImageOptions(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mImageSize, _local_safe_0a1b_mAvatarOptions, _local_safe_0a1b_mUseLargePictureForCp2Images);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ParcelableLoadImageOptions[] newArray(int size) {
        return new ParcelableLoadImageOptions[size];
    }

    static void writeToParcel(ParcelableLoadImageOptions obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getImageSize());
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeInt(parcel, 2, obj.getAvatarOptions());
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isUseLargePictureForCp2Images());
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
