package com.google.android.gms.people.internal.autocomplete;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class ParcelableLoadAutocompleteResultsOptionsCreator implements Creator<ParcelableLoadAutocompleteResultsOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public ParcelableLoadAutocompleteResultsOptions createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mClientId = 0;
        long _local_safe_0a1b_mSessionId = 0;
        String _local_safe_0a1b_mQuery = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mClientId = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mSessionId = SafeParcelReader.readLong(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mQuery = SafeParcelReader.createString(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new ParcelableLoadAutocompleteResultsOptions(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mClientId, _local_safe_0a1b_mSessionId, _local_safe_0a1b_mQuery);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public ParcelableLoadAutocompleteResultsOptions[] newArray(int size) {
        return new ParcelableLoadAutocompleteResultsOptions[size];
    }

    static void writeToParcel(ParcelableLoadAutocompleteResultsOptions obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.mClientId);
        SafeParcelWriter.writeLong(parcel, 3, obj.mSessionId);
        SafeParcelWriter.writeString(parcel, 4, obj.mQuery, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
