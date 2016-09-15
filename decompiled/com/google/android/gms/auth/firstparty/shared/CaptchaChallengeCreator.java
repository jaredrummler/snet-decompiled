package com.google.android.gms.auth.firstparty.shared;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class CaptchaChallengeCreator implements Creator<CaptchaChallenge> {
    public static final int CONTENT_DESCRIPTION = 0;

    public CaptchaChallenge createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_statusWireCode = null;
        String _local_safe_0a1b_token = null;
        Bitmap _local_safe_0a1b_captcha = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_statusWireCode = SafeParcelReader.createString(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_token = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_captcha = (Bitmap) SafeParcelReader.createParcelable(parcel, header, Bitmap.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new CaptchaChallenge(_local_safe_0a1b_version, _local_safe_0a1b_statusWireCode, _local_safe_0a1b_token, _local_safe_0a1b_captcha);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public CaptchaChallenge[] newArray(int size) {
        return new CaptchaChallenge[size];
    }

    static void writeToParcel(CaptchaChallenge obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.statusWireCode, false);
        SafeParcelWriter.writeString(parcel, 3, obj.token, false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.captcha, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
