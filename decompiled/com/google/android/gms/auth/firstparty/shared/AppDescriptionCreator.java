package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class AppDescriptionCreator implements Creator<AppDescription> {
    public static final int CONTENT_DESCRIPTION = 0;

    public AppDescription createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_version = 0;
        int _local_safe_0a1b_callingUid = 0;
        String _local_safe_0a1b_sessionId = null;
        String _local_safe_0a1b_sessionSig = null;
        String _local_safe_0a1b_callingPkg = null;
        boolean _local_safe_0a1b_isSetupWizardInProgress = false;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_version = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_callingUid = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_sessionId = SafeParcelReader.createString(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_sessionSig = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_callingPkg = SafeParcelReader.createString(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_isSetupWizardInProgress = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new AppDescription(_local_safe_0a1b_version, _local_safe_0a1b_callingUid, _local_safe_0a1b_sessionId, _local_safe_0a1b_sessionSig, _local_safe_0a1b_callingPkg, _local_safe_0a1b_isSetupWizardInProgress);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public AppDescription[] newArray(int size) {
        return new AppDescription[size];
    }

    static void writeToParcel(AppDescription obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.version);
        SafeParcelWriter.writeInt(parcel, 2, obj.callingUid);
        SafeParcelWriter.writeString(parcel, 3, obj.sessionId, false);
        SafeParcelWriter.writeString(parcel, 4, obj.sessionSig, false);
        SafeParcelWriter.writeString(parcel, 5, obj.callingPkg, false);
        SafeParcelWriter.writeBoolean(parcel, 6, obj.isSetupWizardInProgress);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
