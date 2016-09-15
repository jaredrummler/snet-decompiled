package com.google.android.snet;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.people.PeopleConstants.PeopleColumnBitmask;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SettingsFinder {
    private static final int LOCK_SCREEN_TYPE_FACE_PATTERN = 5;
    private static final int LOCK_SCREEN_TYPE_FACE_PIN = 4;
    private static final int LOCK_SCREEN_TYPE_NONE = 0;
    private static final int LOCK_SCREEN_TYPE_PASSWORD = 6;
    private static final int LOCK_SCREEN_TYPE_PATTERN = 3;
    private static final int LOCK_SCREEN_TYPE_PIN = 2;
    private static final int LOCK_SCREEN_TYPE_SECURE_UNKNOWN = 1;
    private static final int NOTIFICATION_TYPE_NONE = 0;
    private static final int NOTIFICATION_TYPE_PRIVATE = 1;
    private static final int NOTIFICATION_TYPE_PUBLIC = 2;
    private static final int NOTIFICATION_TYPE_SECRET = 3;
    private static final String SETTINGS_GLOBAL_CLASS_NAME = "android.provider.Settings$Global";
    private static final String SETTINGS_SECURE_CLASS_NAME = "android.provider.Settings$Secure";
    private static final String TAG;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private DeviceSettings mDeviceSettings;
    private final GBundle mGBundle;
    private final String mSettingsClass;

    public static class DeviceSettings {
        public boolean adbEnabled;
        public int lockScreenTimeout;
        public int lockScreenType;
        public boolean nonMarketAppsEnabled;
        public int notificationVisibility;
        public boolean smartLockEnabled;
        public boolean smartLockStatusObtained;
    }

    static {
        TAG = SettingsFinder.class.getCanonicalName();
    }

    SettingsFinder(Context context, GBundle gBundle) {
        this.mContext = context;
        this.mGBundle = gBundle;
        this.mDeviceSettings = new DeviceSettings();
        this.mContentResolver = context.getContentResolver();
        if (VERSION.SDK_INT < 17) {
            this.mSettingsClass = SETTINGS_SECURE_CLASS_NAME;
        } else {
            this.mSettingsClass = SETTINGS_GLOBAL_CLASS_NAME;
        }
    }

    DeviceSettings deviceSettings() {
        this.mDeviceSettings.adbEnabled = adbEnabled();
        this.mDeviceSettings.nonMarketAppsEnabled = nonMarketAppsEnabled();
        if (VERSION.SDK_INT >= 16) {
            getLockScreenSettings();
        } else {
            getLockScreenSettingsPreJb();
        }
        if (VERSION.SDK_INT >= 21) {
            Bundle smartLockBundle = this.mGBundle.getSmartLockBundle();
            if (smartLockBundle != null && TextUtils.isEmpty(smartLockBundle.getString("errorMsg")) && smartLockBundle.getInt("statusCode") == 0) {
                this.mDeviceSettings.smartLockStatusObtained = true;
                this.mDeviceSettings.smartLockEnabled = smartLockBundle.getBoolean("smartLockStatus");
            }
        }
        return this.mDeviceSettings;
    }

    private boolean adbEnabled() {
        return getInt(this.mSettingsClass, "adb_enabled", NOTIFICATION_TYPE_NONE) != 0;
    }

    private boolean nonMarketAppsEnabled() {
        return getInt(this.mSettingsClass, "install_non_market_apps", NOTIFICATION_TYPE_NONE) != 0;
    }

    private int getInt(String settingsClass, String setting, int defaultValue) {
        try {
            Class[] clsArr = new Class[NOTIFICATION_TYPE_SECRET];
            clsArr[NOTIFICATION_TYPE_NONE] = ContentResolver.class;
            clsArr[NOTIFICATION_TYPE_PRIVATE] = String.class;
            clsArr[NOTIFICATION_TYPE_PUBLIC] = Integer.TYPE;
            Method method = Class.forName(settingsClass).getMethod("getInt", clsArr);
            Object[] objArr = new Object[NOTIFICATION_TYPE_SECRET];
            objArr[NOTIFICATION_TYPE_NONE] = this.mContentResolver;
            objArr[NOTIFICATION_TYPE_PRIVATE] = setting;
            objArr[NOTIFICATION_TYPE_PUBLIC] = Integer.valueOf(defaultValue);
            defaultValue = ((Integer) method.invoke(null, objArr)).intValue();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (NoSuchMethodException e2) {
            Log.e(TAG, e2.getMessage());
        } catch (IllegalAccessException e3) {
            Log.e(TAG, e3.getMessage());
        } catch (InvocationTargetException e4) {
            Log.e(TAG, e4.getMessage());
        }
        return defaultValue;
    }

    @TargetApi(16)
    private void getLockScreenSettings() {
        if (((KeyguardManager) this.mContext.getSystemService("keyguard")).isKeyguardSecure()) {
            this.mDeviceSettings.lockScreenType = NOTIFICATION_TYPE_PRIVATE;
            if (VERSION.SDK_INT >= 21) {
                this.mDeviceSettings.notificationVisibility = getNotificationVisibility();
            }
        } else {
            this.mDeviceSettings.lockScreenType = NOTIFICATION_TYPE_NONE;
        }
        this.mDeviceSettings.lockScreenTimeout = getInt(this.mSettingsClass, "lock_screen_lock_after_timeout", NOTIFICATION_TYPE_NONE);
    }

    @TargetApi(21)
    private int getNotificationVisibility() {
        Notification notification = new Builder(this.mContext).build();
        if (notification == null) {
            return NOTIFICATION_TYPE_NONE;
        }
        switch (notification.visibility) {
            case LogSource.UNKNOWN /*-1*/:
                return NOTIFICATION_TYPE_SECRET;
            case NOTIFICATION_TYPE_NONE /*0*/:
                return NOTIFICATION_TYPE_PRIVATE;
            case NOTIFICATION_TYPE_PRIVATE /*1*/:
                return NOTIFICATION_TYPE_PUBLIC;
            default:
                return NOTIFICATION_TYPE_NONE;
        }
    }

    private void getLockScreenSettingsPreJb() {
        switch (getInt(this.mSettingsClass, "lockscreen.password_type", -1)) {
            case PeopleColumnBitmask.AFFINITY_1 /*32768*/:
                if (!new File(Environment.getDataDirectory().getAbsolutePath(), "/system/password.key").exists()) {
                    this.mDeviceSettings.lockScreenType = LOCK_SCREEN_TYPE_FACE_PATTERN;
                    break;
                } else {
                    this.mDeviceSettings.lockScreenType = LOCK_SCREEN_TYPE_FACE_PIN;
                    break;
                }
            case PeopleColumnBitmask.AFFINITY_2 /*65536*/:
                if (getInt(this.mSettingsClass, "lock_pattern_autolock", NOTIFICATION_TYPE_NONE) == 0) {
                    this.mDeviceSettings.lockScreenType = NOTIFICATION_TYPE_NONE;
                    break;
                } else {
                    this.mDeviceSettings.lockScreenType = NOTIFICATION_TYPE_SECRET;
                    break;
                }
            case PeopleColumnBitmask.AFFINITY_3 /*131072*/:
                this.mDeviceSettings.lockScreenType = NOTIFICATION_TYPE_PUBLIC;
                break;
            case PeopleColumnBitmask.AFFINITY_4 /*262144*/:
            case 327680:
                this.mDeviceSettings.lockScreenType = LOCK_SCREEN_TYPE_PASSWORD;
                break;
            default:
                this.mDeviceSettings.lockScreenType = NOTIFICATION_TYPE_NONE;
                break;
        }
        this.mDeviceSettings.lockScreenTimeout = getInt(this.mSettingsClass, "lock_screen_lock_after_timeout", NOTIFICATION_TYPE_NONE);
    }
}
