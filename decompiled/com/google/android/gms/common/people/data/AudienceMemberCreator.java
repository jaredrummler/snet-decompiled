package com.google.android.gms.common.people.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AudienceMemberCreator implements Creator<AudienceMember> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AudienceMember createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mType = 0;
        int _local_safe_0a1b_mCircleType = 0;
        String _local_safe_0a1b_mCircleId = null;
        String _local_safe_0a1b_mPeopleQualifiedId = null;
        String _local_safe_0a1b_mDisplayName = null;
        String _local_safe_0a1b_mAvatarUrl = null;
        Bundle _local_safe_0a1b_mMetadata = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mType = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mCircleType = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mCircleId = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mPeopleQualifiedId = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mDisplayName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mAvatarUrl = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mMetadata = SafeParcelReader.createBundle(parcel, header);
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
            return new AudienceMember(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mType, _local_safe_0a1b_mCircleType, _local_safe_0a1b_mCircleId, _local_safe_0a1b_mPeopleQualifiedId, _local_safe_0a1b_mDisplayName, _local_safe_0a1b_mAvatarUrl, _local_safe_0a1b_mMetadata);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AudienceMember[] newArray(int size) {
        return new AudienceMember[size];
    }

    static void writeToParcel(AudienceMember obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getType());
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeInt(parcel, 2, obj.getCircleType());
        SafeParcelWriter.writeString(parcel, 3, obj.getCircleId(), false);
        SafeParcelWriter.writeString(parcel, 4, obj.getPeopleQualifiedId(), false);
        SafeParcelWriter.writeString(parcel, 5, obj.getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 6, obj.getAvatarUrl(), false);
        SafeParcelWriter.writeBundle(parcel, 7, obj.getMetadata(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
