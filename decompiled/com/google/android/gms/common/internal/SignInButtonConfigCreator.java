package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class SignInButtonConfigCreator implements Creator<SignInButtonConfig> {
    public static final int CONTENT_DESCRIPTION = 0;

    public SignInButtonConfig createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        int _local_safe_0a1b_mButtonSize = 0;
        int _local_safe_0a1b_mColorScheme = 0;
        Scope[] _local_safe_0a1b_mScopes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mButtonSize = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mColorScheme = SafeParcelReader.readInt(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mScopes = (Scope[]) SafeParcelReader.createTypedArray(parcel, header, Scope.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new SignInButtonConfig(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_mButtonSize, _local_safe_0a1b_mColorScheme, _local_safe_0a1b_mScopes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public SignInButtonConfig[] newArray(int size) {
        return new SignInButtonConfig[size];
    }

    static void writeToParcel(SignInButtonConfig obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeInt(parcel, 2, obj.getButtonSize());
        SafeParcelWriter.writeInt(parcel, 3, obj.getColorScheme());
        SafeParcelWriter.writeTypedArray(parcel, 4, obj.getScopes(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
