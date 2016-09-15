package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class EmailSignInOptionsCreator implements Creator<EmailSignInOptions> {
    public static final int CONTENT_DESCRIPTION = 0;

    public EmailSignInOptions createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_versionCode = 0;
        Uri _local_safe_0a1b_mServerWidgetUrl = null;
        String _local_safe_0a1b_mModeQueryName = null;
        Uri _local_safe_0a1b_mTosUrl = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_versionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_mServerWidgetUrl = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_mModeQueryName = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_mTosUrl = (Uri) SafeParcelReader.createParcelable(parcel, header, Uri.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new EmailSignInOptions(_local_safe_0a1b_versionCode, _local_safe_0a1b_mServerWidgetUrl, _local_safe_0a1b_mModeQueryName, _local_safe_0a1b_mTosUrl);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public EmailSignInOptions[] newArray(int size) {
        return new EmailSignInOptions[size];
    }

    static void writeToParcel(EmailSignInOptions obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.versionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.getServerWidgetUrl(), flags, false);
        SafeParcelWriter.writeString(parcel, 3, obj.getModeQueryName(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, obj.getTermsOfServiceUrl(), flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
