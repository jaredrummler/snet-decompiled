package com.google.android.gms.people.identity.internal.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.people.identity.internal.models.PersonImpl.MetadataImpl;
import com.google.android.gms.people.identity.internal.models.PersonImpl.NamesImpl;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.HashSet;
import java.util.Set;

public class NamesImplCreator implements Creator<NamesImpl> {
    public static final int CONTENT_DESCRIPTION = 0;

    public NamesImpl createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        Set<Integer> _local_safe_0a1b_mIndicatorSet = new HashSet();
        int _local_safe_0a1b_mVersionCode = 0;
        MetadataImpl _local_safe_0a1b_mMetadata = null;
        String _local_safe_0a1b_mDisplayName = null;
        String _local_safe_0a1b_mFamilyName = null;
        String _local_safe_0a1b_mFormatted = null;
        String _local_safe_0a1b_mGivenName = null;
        String _local_safe_0a1b_mHonorificPrefix = null;
        String _local_safe_0a1b_mHonorificSuffix = null;
        String _local_safe_0a1b_mMiddleName = null;
        String _local_safe_0a1b_mPhoneticFamilyName = null;
        String _local_safe_0a1b_mPhoneticGivenName = null;
        String _local_safe_0a1b_mPhoneticHonorificPrefix = null;
        String _local_safe_0a1b_mPhoneticHonorificSuffix = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(1));
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mMetadata = (MetadataImpl) SafeParcelReader.createParcelable(parcel, header, MetadataImpl.CREATOR);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(2));
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mDisplayName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(3));
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mFamilyName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(4));
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mFormatted = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(5));
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mGivenName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(6));
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mHonorificPrefix = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(7));
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mHonorificSuffix = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(8));
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mMiddleName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(9));
                    break;
                case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                    _local_safe_0a1b_mPhoneticFamilyName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(10));
                    break;
                case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                    _local_safe_0a1b_mPhoneticGivenName = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(11));
                    break;
                case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                    _local_safe_0a1b_mPhoneticHonorificPrefix = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(12));
                    break;
                case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                    _local_safe_0a1b_mPhoneticHonorificSuffix = SafeParcelReader.createString(parcel, header);
                    _local_safe_0a1b_mIndicatorSet.add(Integer.valueOf(13));
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new NamesImpl(_local_safe_0a1b_mIndicatorSet, _local_safe_0a1b_mVersionCode, _local_safe_0a1b_mMetadata, _local_safe_0a1b_mDisplayName, _local_safe_0a1b_mFamilyName, _local_safe_0a1b_mFormatted, _local_safe_0a1b_mGivenName, _local_safe_0a1b_mHonorificPrefix, _local_safe_0a1b_mHonorificSuffix, _local_safe_0a1b_mMiddleName, _local_safe_0a1b_mPhoneticFamilyName, _local_safe_0a1b_mPhoneticGivenName, _local_safe_0a1b_mPhoneticHonorificPrefix, _local_safe_0a1b_mPhoneticHonorificSuffix);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public NamesImpl[] newArray(int size) {
        return new NamesImpl[size];
    }

    static void writeToParcel(NamesImpl obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> __setFields = obj.mIndicatorSet;
        if (__setFields.contains(Integer.valueOf(1))) {
            SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        }
        if (__setFields.contains(Integer.valueOf(2))) {
            SafeParcelWriter.writeParcelable(parcel, 2, obj.mMetadata, flags, true);
        }
        if (__setFields.contains(Integer.valueOf(3))) {
            SafeParcelWriter.writeString(parcel, 3, obj.mDisplayName, true);
        }
        if (__setFields.contains(Integer.valueOf(4))) {
            SafeParcelWriter.writeString(parcel, 4, obj.mFamilyName, true);
        }
        if (__setFields.contains(Integer.valueOf(5))) {
            SafeParcelWriter.writeString(parcel, 5, obj.mFormatted, true);
        }
        if (__setFields.contains(Integer.valueOf(6))) {
            SafeParcelWriter.writeString(parcel, 6, obj.mGivenName, true);
        }
        if (__setFields.contains(Integer.valueOf(7))) {
            SafeParcelWriter.writeString(parcel, 7, obj.mHonorificPrefix, true);
        }
        if (__setFields.contains(Integer.valueOf(8))) {
            SafeParcelWriter.writeString(parcel, 8, obj.mHonorificSuffix, true);
        }
        if (__setFields.contains(Integer.valueOf(9))) {
            SafeParcelWriter.writeString(parcel, 9, obj.mMiddleName, true);
        }
        if (__setFields.contains(Integer.valueOf(10))) {
            SafeParcelWriter.writeString(parcel, 10, obj.mPhoneticFamilyName, true);
        }
        if (__setFields.contains(Integer.valueOf(11))) {
            SafeParcelWriter.writeString(parcel, 11, obj.mPhoneticGivenName, true);
        }
        if (__setFields.contains(Integer.valueOf(12))) {
            SafeParcelWriter.writeString(parcel, 12, obj.mPhoneticHonorificPrefix, true);
        }
        if (__setFields.contains(Integer.valueOf(13))) {
            SafeParcelWriter.writeString(parcel, 13, obj.mPhoneticHonorificSuffix, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
