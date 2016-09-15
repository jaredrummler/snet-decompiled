package android.support.v4.media.session;

import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import com.google.android.gsf.TalkContract.AccountSettings;

public class MediaSessionCompatApi8 {
    public static void registerMediaButtonEventReceiver(Context context, ComponentName mbr) {
        ((AudioManager) context.getSystemService(AccountSettings.VIDEOCHAT_VOICE)).registerMediaButtonEventReceiver(mbr);
    }

    public static void unregisterMediaButtonEventReceiver(Context context, ComponentName mbr) {
        ((AudioManager) context.getSystemService(AccountSettings.VIDEOCHAT_VOICE)).unregisterMediaButtonEventReceiver(mbr);
    }
}
