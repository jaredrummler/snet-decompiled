package com.google.android.gms.auth.firstparty.dataservice;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PostSignInDataCreator implements Creator<PostSignInData> {
    public static final int CONTENT_DESCRIPTION = 0;

    public PostSignInData createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        Intent _local_safe_0a1b_postSignInForeignIntent = null;
        PendingIntent _local_safe_0a1b_accountInstallationCompletionAction = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_postSignInForeignIntent = (Intent) SafeParcelReader.createParcelable(parcel, header, Intent.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_accountInstallationCompletionAction = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new PostSignInData(_local_safe_0a1b_version, _local_safe_0a1b_postSignInForeignIntent, _local_safe_0a1b_accountInstallationCompletionAction);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public PostSignInData[] newArray(int size) {
        return new PostSignInData[size];
    }

    static void writeToParcel(PostSignInData obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.postSignInForeignIntent, flags, false);
        SafeParcelWriter.writeParcelable(parcel, 3, obj.accountInstallationCompletionAction, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
