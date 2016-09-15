package com.google.android.gms.auth.firstparty.dataservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.AccountCredentials;
import com.google.android.gms.auth.firstparty.shared.CaptchaSolution;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class UpdateCredentialsRequestCreator implements Creator<UpdateCredentialsRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    public UpdateCredentialsRequest createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        AccountCredentials _local_safe_0a1b_accountCredentials = null;
        CaptchaSolution _local_safe_0a1b_optionalCaptchaSolution = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_accountCredentials = (AccountCredentials) SafeParcelReader.createParcelable(parcel, header, AccountCredentials.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_optionalCaptchaSolution = (CaptchaSolution) SafeParcelReader.createParcelable(parcel, header, CaptchaSolution.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new UpdateCredentialsRequest(_local_safe_0a1b_version, _local_safe_0a1b_accountCredentials, _local_safe_0a1b_optionalCaptchaSolution);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public UpdateCredentialsRequest[] newArray(int size) {
        return new UpdateCredentialsRequest[size];
    }

    static void writeToParcel(UpdateCredentialsRequest obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.accountCredentials, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.optionalCaptchaSolution, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
