package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class RecoveryDecisionCreator implements Creator<RecoveryDecision> {
    public static final int CONTENT_DESCRIPTION = 0;

    public RecoveryDecision createFromParcel(Parcel parcel) {
        int end = SafeParcelReader.validateObjectHeader(parcel);
        int _local_safe_0a1b_mVersionCode = 0;
        PendingIntent _local_safe_0a1b_recoveryIntent = null;
        boolean _local_safe_0a1b_showRecoveryInterstitial = false;
        boolean _local_safe_0a1b_isRecoveryInfoNeeded = false;
        boolean _local_safe_0a1b_isRecoveryInterstitialAllowed = false;
        PendingIntent _local_safe_0a1b_recoveryIntentWithoutIntro = null;
        while (parcel.dataPosition() < end) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case Type.TEMPORARY /*1*/:
                    _local_safe_0a1b_mVersionCode = SafeParcelReader.readInt(parcel, header);
                    break;
                case Type.INDEFINITELY /*2*/:
                    _local_safe_0a1b_recoveryIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    break;
                case Type.CUSTOM /*3*/:
                    _local_safe_0a1b_showRecoveryInterstitial = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case OvenFreshResult.NO_ACCOUNTS /*4*/:
                    _local_safe_0a1b_isRecoveryInfoNeeded = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                    _local_safe_0a1b_isRecoveryInterstitialAllowed = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                    _local_safe_0a1b_recoveryIntentWithoutIntro = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == end) {
            return new RecoveryDecision(_local_safe_0a1b_mVersionCode, _local_safe_0a1b_recoveryIntent, _local_safe_0a1b_showRecoveryInterstitial, _local_safe_0a1b_isRecoveryInfoNeeded, _local_safe_0a1b_isRecoveryInterstitialAllowed, _local_safe_0a1b_recoveryIntentWithoutIntro);
        }
        throw new ParseException("Overread allowed size end=" + end, parcel);
    }

    public RecoveryDecision[] newArray(int size) {
        return new RecoveryDecision[size];
    }

    static void writeToParcel(RecoveryDecision obj, Parcel parcel, int flags) {
        int myStart = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, obj.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, obj.recoveryIntent, flags, false);
        SafeParcelWriter.writeBoolean(parcel, 3, obj.showRecoveryInterstitial);
        SafeParcelWriter.writeBoolean(parcel, 4, obj.isRecoveryInfoNeeded);
        SafeParcelWriter.writeBoolean(parcel, 5, obj.isRecoveryInterstitialAllowed);
        SafeParcelWriter.writeParcelable(parcel, 6, obj.recoveryIntentWithoutIntro, flags, false);
        SafeParcelWriter.finishObjectHeader(parcel, myStart);
    }
}
