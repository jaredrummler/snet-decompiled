package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.Set;

public class PersonReferenceImplCreator implements Creator<PersonReferenceImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PersonReferenceImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mName = null;
        String _local_safe_0a1b_mQualifiedId = null;
        ImageReferenceImpl _local_safe_0a1b_mAvatarReference = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mQualifiedId = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mAvatarReference = (ImageReferenceImpl) SafeParcelReader.createParcelable(parcel, header, ImageReferenceImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PersonReferenceImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mName, _local_safe_0a1b_mQualifiedId, _local_safe_0a1b_mAvatarReference);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PersonReferenceImpl[] newArray(int size) {
        return new PersonReferenceImpl[size];
    }

    static void writeToParcel(PersonReferenceImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeString(parcel, 2, obj.mName, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeString(parcel, 3, obj.mQualifiedId, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeParcelable(parcel, 4, obj.mAvatarReference, flags, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
