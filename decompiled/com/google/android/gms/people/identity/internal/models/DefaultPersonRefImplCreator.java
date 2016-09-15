package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class DefaultPersonRefImplCreator implements Creator<DefaultPersonRefImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public DefaultPersonRefImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mName = null;
        String _local_safe_0a1b_mQualifiedId = null;
        DefaultAvatarRefImpl _local_safe_0a1b_mAvatar = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mName = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mQualifiedId = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mAvatar = (DefaultAvatarRefImpl) SafeParcelReader.createParcelable(parcel, header, DefaultAvatarRefImpl.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new DefaultPersonRefImpl(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mName, _local_safe_0a1b_mQualifiedId, _local_safe_0a1b_mAvatar);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public DefaultPersonRefImpl[] newArray(int size) {
        return new DefaultPersonRefImpl[size];
    }

    static void writeToParcel(DefaultPersonRefImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.mName, false);
        SafeParcelWriter.writeString(parcel, 3, obj.mQualifiedId, false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.mAvatar, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
