package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class BitmapTeleporterCreator implements Creator<BitmapTeleporter> {
    public static final int CONTENT_DESCRIPTION = 0;

    public BitmapTeleporter createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        ParcelFileDescriptor _local_safe_0a1b_mFileDescriptor = null;
        int _local_safe_0a1b_mType = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, header, ParcelFileDescriptor.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mType = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new BitmapTeleporter(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mFileDescriptor, _local_safe_0a1b_mType);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public BitmapTeleporter[] newArray(int size) {
        return new BitmapTeleporter[size];
    }

    static void writeToParcel(BitmapTeleporter obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.mFileDescriptor, flags, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.mType);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
