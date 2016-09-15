package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.wireless.android.play.playlog.proto.UserLocation.LaunchSettingsEvent.Action;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

class ConnectivityManagerCompatHoneycombMR2 {
    ConnectivityManagerCompatHoneycombMR2() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return true;
        }
        switch (info.getType()) {
            case Action.UNKNOWN /*0*/:
            case Type.INDEFINITELY /*2*/:
            case Type.CUSTOM /*3*/:
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                return true;
            case Type.TEMPORARY /*1*/:
            case LocationSharingEvent.Type.RESET_TIME /*7*/:
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                return false;
            default:
                return true;
        }
    }
}
