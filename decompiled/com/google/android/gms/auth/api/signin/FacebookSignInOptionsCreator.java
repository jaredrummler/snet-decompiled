package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.ArrayList;

public class FacebookSignInOptionsCreator implements Creator<FacebookSignInOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public FacebookSignInOptions createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        Intent _local_safe_0a1b_mIntent = null;
        ArrayList<String> _local_safe_0a1b_mScopes = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mIntent = (Intent) SafeParcelReader.createParcelable(parcel, header, Intent.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mScopes = SafeParcelReader.createStringList(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new FacebookSignInOptions(_local_safe_0a1b_versionCode, _local_safe_0a1b_mIntent, _local_safe_0a1b_mScopes);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public FacebookSignInOptions[] newArray(int size) {
        return new FacebookSignInOptions[size];
    }

    static void writeToParcel(FacebookSignInOptions obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getCustomFacebookSignInActivityIntent(), flags, false);
        SafeParcelWriter.writeStringList(parcel, 3, obj.getScopes(), false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
