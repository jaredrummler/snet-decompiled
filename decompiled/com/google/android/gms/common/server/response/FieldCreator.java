package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class FieldCreator implements Creator<Field> {
    public static final int CONTENT_DESCRIPTION = 0;

    public Field createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mTypeIn = 0;
        boolean _local_safe_0a1b_mTypeInArray = false;
        int _local_safe_0a1b_mTypeOut = 0;
        boolean _local_safe_0a1b_mTypeOutArray = false;
        String _local_safe_0a1b_mOutputFieldName = null;
        int _local_safe_0a1b_mSafeParcelableFieldId = 0;
        String _local_safe_0a1b_mConcreteTypeName = null;
        ConverterWrapper _local_safe_0a1b_mConverter = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mTypeIn = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mTypeInArray = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mTypeOut = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mTypeOutArray = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mOutputFieldName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.RESET_TIME /*7*/:
                    _local_safe_0a1b_mSafeParcelableFieldId = SafeParcelReader.readInt(parcel, header);
                    break;
                case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                    _local_safe_0a1b_mConcreteTypeName = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                    _local_safe_0a1b_mConverter = (ConverterWrapper) SafeParcelReader.createParcelable(parcel, header, ConverterWrapper.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new Field(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mTypeIn, _local_safe_0a1b_mTypeInArray, _local_safe_0a1b_mTypeOut, _local_safe_0a1b_mTypeOutArray, _local_safe_0a1b_mOutputFieldName, _local_safe_0a1b_mSafeParcelableFieldId, _local_safe_0a1b_mConcreteTypeName, _local_safe_0a1b_mConverter);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public Field[] newArray(int size) {
        return new Field[size];
    }

    static void writeToParcel(Field obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getVersionCode());
        SafeParcelWriter.writeInt(parcel, 2, obj.getTypeIn());
        SafeParcelWriter.writeBoolean(parcel, 3, obj.isTypeInArray());
        SafeParcelWriter.writeInt(parcel, 4, obj.getTypeOut());
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isTypeOutArray());
        SafeParcelWriter.writeString(parcel, 6, obj.getOutputFieldName(), false);
        SafeParcelWriter.writeInt(parcel, 7, obj.getSafeParcelableFieldId());
        SafeParcelWriter.writeString(parcel, 8, obj.getConcreteTypeName(), false);
        SafeParcelWriter.writeParcelable(parcel, 9, obj.getWrappedConverter(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
