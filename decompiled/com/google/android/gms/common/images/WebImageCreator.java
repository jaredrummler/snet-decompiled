package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class WebImageCreator implements Creator<WebImage> {
    public static final int CONTENT_DESCRIPTION = 0;

    public WebImage createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        Uri _local_safe_0a1b_mUrl = null;
        int _local_safe_0a1b_mWidth = 0;
        int _local_safe_0a1b_mHeight = 0;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mUrl = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mWidth = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mHeight = SafeParcelReader.readInt(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new WebImage(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mUrl, _local_safe_0a1b_mWidth, _local_safe_0a1b_mHeight);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public WebImage[] newArray(int size) {
        return new WebImage[size];
    }

    static void writeToParcel(WebImage obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getVersionCode());
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getUrl(), flags, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.getWidth());
        SafeParcelWriter.writeInt(parcel, 4, obj.getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
