package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FieldMappingDictionary.Entry;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;

public class FieldMappingDictionaryCreator implements Creator<FieldMappingDictionary> {
    public static final int CONTENT_DESCRIPTION = 0;

    public FieldMappingDictionary createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        ArrayList<Entry> _local_safe_0a1b_mSerializedDictionary = null;
        String _local_safe_0a1b_mRootClassName = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mSerializedDictionary = SafeParcelReader.createTypedList(parcel, header, Entry.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mRootClassName = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new FieldMappingDictionary(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mSerializedDictionary, _local_safe_0a1b_mRootClassName);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public FieldMappingDictionary[] newArray(int size) {
        return new FieldMappingDictionary[size];
    }

    static void writeToParcel(FieldMappingDictionary obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.getVersionCode());
        SafeParcelWriter.writeTypedList(parcel, 2, obj.getSerializedDictionary(), false);
        SafeParcelWriter.writeString(parcel, 3, obj.getRootClassName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
