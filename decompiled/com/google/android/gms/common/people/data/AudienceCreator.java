package com.google.android.gms.common.people.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class AudienceCreator implements Creator<Audience> {
    public static final int CONTENT_DESCRIPTION = 0;

    public Audience createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        List<AudienceMember> _local_safe_0a1b_mAudienceMembers = null;
        int _local_safe_0a1b_mDomainRestricted = 0;
        boolean _local_safe_0a1b_mIsFullyUnderstood = false;
        boolean _local_safe_0a1b_mReadOnly = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mAudienceMembers = SafeParcelReader.createTypedList(parcel, header, AudienceMember.CREATOR);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mDomainRestricted = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mIsFullyUnderstood = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mReadOnly = SafeParcelReader.readBoolean(parcel, header);
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
            return new Audience(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mAudienceMembers, _local_safe_0a1b_mDomainRestricted, _local_safe_0a1b_mIsFullyUnderstood, _local_safe_0a1b_mReadOnly);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public Audience[] newArray(int size) {
        return new Audience[size];
    }

    static void writeToParcel(Audience obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, obj.getAudienceMemberList(), false);
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeInt(parcel, 2, obj.getDomainRestricted());
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isFullyUnderstood());
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isReadOnly());
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
