package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class TokenDataCreator implements Creator<TokenData> {
    public static final int CONTENT_DESCRIPTION = 0;

    public TokenData createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        String _local_safe_0a1b_mToken = null;
        Long _local_safe_0a1b_mExpirationTimeSecs = null;
        boolean _local_safe_0a1b_mIsCached = false;
        boolean _local_safe_0a1b_mIsSnowballed = false;
        List<String> _local_safe_0a1b_mGrantedScopes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mToken = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mExpirationTimeSecs = SafeParcelReader.readLongObject(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mIsCached = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_mIsSnowballed = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_mGrantedScopes = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new TokenData(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mToken, _local_safe_0a1b_mExpirationTimeSecs, _local_safe_0a1b_mIsCached, _local_safe_0a1b_mIsSnowballed, _local_safe_0a1b_mGrantedScopes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public TokenData[] newArray(int size) {
        return new TokenData[size];
    }

    static void writeToParcel(TokenData obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeString(parcel, 2, obj.getToken(), false);
        SafeParcelWriter.writeLongObject(parcel, 3, obj.getExpirationTimeSecs(), false);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isCached());
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isSnowballed());
        SafeParcelWriter.writeStringList(parcel, 6, obj.getGrantedScopes(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
