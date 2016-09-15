package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class DataHolderCreator implements Creator<DataHolder> {
    public static final int CONTENT_DESCRIPTION = 0;

    public DataHolder createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String[] _local_safe_0a1b_mColumns = null;
        CursorWindow[] _local_safe_0a1b_mWindows = null;
        int _local_safe_0a1b_mStatusCode = 0;
        Bundle _local_safe_0a1b_mMetadata = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mColumns = SafeParcelReader.createStringArray(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mWindows = (CursorWindow[]) SafeParcelReader.createTypedArray(parcel, header, CursorWindow.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mStatusCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
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
        if (parcel.dataPosition() != end) {
            throw new ParseException("Overread allowed size end=" + end, parcel);
        }
        DataHolder obj = new DataHolder(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mColumns, _local_safe_0a1b_mWindows, _local_safe_0a1b_mStatusCode, _local_safe_0a1b_mMetadata);
        obj.validateContents();
        return obj;
    }

    public DataHolder[] newArray(int size) {
        return new DataHolder[size];
    }

    static void writeToParcel(DataHolder obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, obj.getColumns(), false);
        SafeParcelWriter.writeInt(parcel, 1000, obj.getVersionCode());
        SafeParcelWriter.writeTypedArray(parcel, 2, obj.getWindows(), flags, false);
        SafeParcelWriter.writeInt(parcel, 3, obj.getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, obj.getMetadata(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
