package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.CaptchaChallenge;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;
import java.util.List;

public class AccountNameCheckResponseCreator implements Creator<AccountNameCheckResponse> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AccountNameCheckResponse createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        String _local_safe_0a1b_statusWireCode = null;
        List<String> _local_safe_0a1b_suggestions = null;
        String _local_safe_0a1b_detail = null;
        CaptchaChallenge _local_safe_0a1b_captcha = null;
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
                    _local_safe_0a1b_suggestions = SafeParcelReader.createStringList(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_detail = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_captcha = (CaptchaChallenge) SafeParcelReader.createParcelable(parcel, header, CaptchaChallenge.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AccountNameCheckResponse(_local_safe_0a1b_version, _local_safe_0a1b_statusWireCode, _local_safe_0a1b_suggestions, _local_safe_0a1b_detail, _local_safe_0a1b_captcha);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AccountNameCheckResponse[] newArray(int size) {
        return new AccountNameCheckResponse[size];
    }

    static void writeToParcel(AccountNameCheckResponse obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeString(parcel, 2, obj.statusWireCode, false);
        SafeParcelWriter.writeStringList(parcel, 3, obj.suggestions, false);
        SafeParcelWriter.writeString(parcel, 4, obj.detail, false);
        SafeParcelWriter.writeParcelable(parcel, 5, obj.captcha, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
