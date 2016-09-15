package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class FACLConfigCreator implements Creator<FACLConfig> {
    public static final int CONTENT_DESCRIPTION = 0;

    public FACLConfig createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        boolean _local_safe_0a1b_isAllCirclesVisible = false;
        String _local_safe_0a1b_visibleEdges = null;
        boolean _local_safe_0a1b_isAllContactsVisible = false;
        boolean _local_safe_0a1b_showCircles = false;
        boolean _local_safe_0a1b_showContacts = false;
        boolean _local_safe_0a1b_hasShowCircles = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_isAllCirclesVisible = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_visibleEdges = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_isAllContactsVisible = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_showCircles = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_showContacts = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_hasShowCircles = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new FACLConfig(_local_safe_0a1b_version, _local_safe_0a1b_isAllCirclesVisible, _local_safe_0a1b_visibleEdges, _local_safe_0a1b_isAllContactsVisible, _local_safe_0a1b_showCircles, _local_safe_0a1b_showContacts, _local_safe_0a1b_hasShowCircles);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public FACLConfig[] newArray(int size) {
        return new FACLConfig[size];
    }

    static void writeToParcel(FACLConfig obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeBoolean(parcel, 2, obj.isAllCirclesVisible);
        SafeParcelWriter.writeString(parcel, 3, obj.visibleEdges, false);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isAllContactsVisible);
        SafeParcelWriter.writeBoolean(parcel, 5, obj.showCircles);
        SafeParcelWriter.writeBoolean(parcel, 6, obj.showContacts);
        SafeParcelWriter.writeBoolean(parcel, 7, obj.hasShowCircles);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
