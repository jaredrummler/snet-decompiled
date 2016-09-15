package com.google.android.gsf;

import android.content.ContentQueryMap;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.provider.BaseColumns;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;

public class TalkContract {
    public static final String AUTHORITY = "com.google.android.providers.talk";
    public static final Uri AUTHORITY_URI;
    public static final String GTALK_CATEGORY = "com.android.im.category.GTALK";

    public interface AccountColumns {
        public static final String KEEP_SIGNED_IN = "keep_signed_in";
        public static final String LAST_LOGIN_STATE = "last_login_state";
        public static final String LOCKED = "locked";
        public static final String NAME = "name";
        public static final String USERNAME = "username";
    }

    public static final class Account implements BaseColumns, AccountColumns {
        public static final String ACCOUNT_CONNECTION_STATUS = "account_connStatus";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-accounts";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-accounts";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_WITH_STATUS;
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        private Account() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/accounts");
            CONTENT_URI_WITH_STATUS = Uri.parse("content://com.google.android.providers.talk/accounts/status");
        }
    }

    public interface AccountSettingsColumns {
        public static final String ACCOUNT_ID = "account_id";
        public static final String NAME = "name";
        public static final String VALUE = "value";
    }

    public static class AccountSettings implements AccountSettingsColumns {
        public static final String CONTENT_TYPE = "vnd.android-dir/gtalk-accountSettings";
        public static final Uri CONTENT_URI;
        private static final int DISABLED = 2;
        private static final int ENABLED = 1;
        private static final int ENABLED_MASK = 3;
        public static final String IMAGE_STABILIZATION_HIGH = "high";
        public static final String IMAGE_STABILIZATION_LOW = "low";
        public static final String IMAGE_STABILIZATION_MEDIUM = "medium";
        public static final String IMAGE_STABILIZATION_OFF = "off";
        public static final String IMAGE_STABILIZATION_VIRTUAL_CAMERA_OPERATOR = "virtual";
        public static final String LAST_RMQ_RECEIVED = "last_rmq_rec";
        public static final String NOTIFICATION_OFF = "off";
        public static final String NOTIFICATION_POPUP = "popup";
        public static final String NOTIFICATION_STATUS_BAR = "statusbar";
        public static final String SETTING_ALLOW_AUDIOCHAT = "audiochat";
        public static final String SETTING_ALLOW_AUDIOCHAT_V2 = "audiochatv2";
        public static final String SETTING_ALLOW_CAMERA = "show_camera";
        public static final String SETTING_ALLOW_VIDEOCHAT = "videochat";
        public static final String SETTING_ALLOW_VIDEOCHAT_V2 = "videochatv2";
        public static final String SETTING_AUTOMATICALLY_CONNECT_GTALK = "gtalk_auto_connect";
        public static final String SETTING_AUTOMATICALLY_START_SERVICE = "auto_start_service";
        public static final String SETTING_HEARTBEAT_INTERVAL = "heartbeat_interval";
        public static final String SETTING_HIDE_OFFLINE_CONTACTS = "hide_offline_contacts";
        public static final String SETTING_IM_NOTIFICATION_TYPE = "text-notif-type";
        public static final String SETTING_JID_RESOURCE = "jid_resource";
        public static final String SETTING_NOTIFY_FRIEND_INVITE = "notify_invite";
        public static final String SETTING_SHOW_AWAY_ON_IDLE = "show_away_on_idle";
        public static final String SETTING_SHOW_MOBILE_INDICATOR = "mobile_indicator";
        public static final String SETTING_TEXT_RINGTONE = "ringtone";
        public static final String SETTING_TEXT_RINGTONE_DEFAULT = "content://settings/system/notification_sound";
        public static final String SETTING_TEXT_VIBRATE = "vibrate";
        public static final String SETTING_TEXT_VIBRATE_WHEN = "vibrate-when";
        public static final String SETTING_UPLOAD_HEARTBEAT_STAT = "upload_heartbeat_stat";
        public static final String SETTING_VIDEO_IMAGE_STABILIZATION = "video-image-stabilization";
        public static final String SETTING_VIDEO_NOTIFICATION_TYPE = "video-notif-type";
        public static final String SETTING_VIDEO_RINGTONE = "ringtone-video";
        public static final String SETTING_VIDEO_RINGTONE_DEFAULT = "content://settings/system/ringtone";
        public static final String SETTING_VIDEO_VIBRATE = "vibrate-video";
        public static final String SETTING_VIDEO_VIBRATE_WHEN = "vibrate-when-video";
        public static final String SHOW_OFFLINE_CONTACTS = "show_offline_contacts";
        private static final int UNSET = 0;
        private static final int USER_SET = 16;
        private static final int USER_SET_MASK = 16;
        public static final String VIBRATE_ALWAYS = "always";
        public static final String VIBRATE_NEVER = "never";
        public static final String VIBRATE_SILENT = "silent";
        public static final String VIDEOCHAT_BLOCK = "off";
        public static final String VIDEOCHAT_VIDEO = "video";
        public static final String VIDEOCHAT_VOICE = "audio";

        public static class QueryMap extends ContentQueryMap {
            private static final int HAS_CAMERA_V1 = 4;
            private static final int HAS_PMUC_V1 = 8;
            private static final int HAS_VIDEO_V1 = 2;
            private static final int HAS_VOICE_V1 = 1;
            private long mAccountId;
            private ContentResolver mContentResolver;

            public QueryMap(ContentResolver contentResolver, boolean keepUpdated, long accountId, Handler handlerForUpdateNotifications) {
                Uri contentUriByAccountId = AccountSettings.getContentUriByAccountId(accountId);
                String[] strArr = new String[HAS_VIDEO_V1];
                strArr[AccountSettings.UNSET] = AccountSettingsColumns.NAME;
                strArr[HAS_VOICE_V1] = AccountSettingsColumns.VALUE;
                super(contentResolver.query(contentUriByAccountId, strArr, null, null, null), AccountSettingsColumns.NAME, keepUpdated, handlerForUpdateNotifications);
                this.mContentResolver = contentResolver;
                this.mAccountId = accountId;
            }

            public void setAutomaticallyConnectToGTalkServer(boolean autoConnect) {
                AccountSettings.setAutomaticallyConnectGTalk(this.mContentResolver, autoConnect, this.mAccountId);
            }

            public boolean getAutomaticallyConnectToGTalkServer() {
                return getBoolean(AccountSettings.SETTING_AUTOMATICALLY_CONNECT_GTALK, true);
            }

            public void setHideOfflineContacts(boolean hideOfflineContacts) {
                AccountSettings.setHideOfflineContacts(this.mContentResolver, hideOfflineContacts, this.mAccountId);
            }

            public boolean getHideOfflineContacts() {
                return getBoolean(AccountSettings.SETTING_HIDE_OFFLINE_CONTACTS, false);
            }

            public void setTextVibrate(boolean vibrate) {
                AccountSettings.setTextVibrate(this.mContentResolver, vibrate, this.mAccountId);
            }

            public boolean getTextVibrate() {
                return getBoolean(AccountSettings.SETTING_TEXT_VIBRATE, false);
            }

            public void setTextVibrateWhen(String when) {
                AccountSettings.setTextVibrateWhen(this.mContentResolver, when, this.mAccountId);
            }

            public String getTextVibrateWhen() {
                String when = getString(AccountSettings.SETTING_TEXT_VIBRATE_WHEN, null);
                if (when != null) {
                    return when;
                }
                return getTextVibrate() ? AccountSettings.VIBRATE_ALWAYS : AccountSettings.VIBRATE_NEVER;
            }

            public void setVideoVibrate(boolean vibrate) {
                AccountSettings.setVideoVibrate(this.mContentResolver, vibrate, this.mAccountId);
            }

            public boolean getVideoVibrate() {
                return getBoolean(AccountSettings.SETTING_VIDEO_VIBRATE, false);
            }

            public void setVideoVibrateWhen(String when) {
                AccountSettings.setVideoVibrateWhen(this.mContentResolver, when, this.mAccountId);
            }

            public String getVideoVibrateWhen() {
                String when = getString(AccountSettings.SETTING_VIDEO_VIBRATE_WHEN, null);
                if (when != null) {
                    return when;
                }
                return getVideoVibrate() ? AccountSettings.VIBRATE_ALWAYS : AccountSettings.VIBRATE_NEVER;
            }

            public void setVideoImageStabilization(String imageStabilization) {
                AccountSettings.setVideoImageStabilization(this.mContentResolver, imageStabilization, this.mAccountId);
            }

            public String getVideoImageStabilization() {
                return getString(AccountSettings.SETTING_VIDEO_IMAGE_STABILIZATION, null);
            }

            public void setTextNotification(String notificationType) {
                AccountSettings.setNotificationType(this.mContentResolver, notificationType, this.mAccountId);
            }

            public String getTextNotification() {
                return getString(AccountSettings.SETTING_IM_NOTIFICATION_TYPE, AccountSettings.NOTIFICATION_STATUS_BAR);
            }

            public void setVideoNotification(String notificationType) {
                AccountSettings.setVideoNotificationType(this.mContentResolver, notificationType, this.mAccountId);
            }

            public String getVideoNotification() {
                return getString(AccountSettings.SETTING_VIDEO_NOTIFICATION_TYPE, AccountSettings.NOTIFICATION_POPUP);
            }

            public void setTextRingtoneURI(String ringtoneUri) {
                AccountSettings.setTextRingtoneURI(this.mContentResolver, ringtoneUri, this.mAccountId);
            }

            public String getTextRingtoneURI() {
                return getString(AccountSettings.SETTING_TEXT_RINGTONE, AccountSettings.SETTING_TEXT_RINGTONE_DEFAULT);
            }

            public void setVideoRingtoneURI(String ringtoneUri) {
                AccountSettings.setVideoRingtoneURI(this.mContentResolver, ringtoneUri, this.mAccountId);
            }

            public String getVideoRingtoneURI() {
                return getString(AccountSettings.SETTING_VIDEO_RINGTONE, AccountSettings.SETTING_VIDEO_RINGTONE_DEFAULT);
            }

            public void setShowMobileIndicator(boolean showMobile) {
                AccountSettings.setShowMobileIndicator(this.mContentResolver, showMobile, this.mAccountId);
            }

            public boolean getShowMobileIndicator(Context ctx) {
                return getBoolean(AccountSettings.SETTING_SHOW_MOBILE_INDICATOR, !isTablet(ctx));
            }

            private static boolean isTablet(Context ctx) {
                if ((ctx.getResources().getConfiguration().screenLayout & 15) > AccountSettings.ENABLED_MASK) {
                    return true;
                }
                return false;
            }

            public void setAudioChatEnabled(boolean enabled) {
                AccountSettings.setAudioChatEnabled(this.mContentResolver, enabled, this.mAccountId);
            }

            @Deprecated
            public boolean setAudioChatEnabled(boolean enabled, boolean userSet) {
                return AccountSettings.setAudioChatEnabled(this.mContentResolver, enabled, userSet, getLong(AccountSettings.SETTING_ALLOW_AUDIOCHAT_V2, 0), this.mAccountId);
            }

            public boolean getAudioChatEnabled() {
                return AccountSettings.isEnabled(getLong(AccountSettings.SETTING_ALLOW_AUDIOCHAT_V2, 2));
            }

            public boolean getAudioChatUnset() {
                return getLong(AccountSettings.SETTING_ALLOW_AUDIOCHAT_V2, 0) == 0;
            }

            public void setVideoChatEnabled(boolean enabled) {
                AccountSettings.setVideoChatEnabled(this.mContentResolver, enabled, this.mAccountId);
            }

            @Deprecated
            public boolean setVideoChatEnabled(boolean enabled, boolean userSet) {
                return AccountSettings.setVideoChatEnabled(this.mContentResolver, enabled, userSet, getLong(AccountSettings.SETTING_ALLOW_VIDEOCHAT_V2, 0), this.mAccountId);
            }

            public boolean getVideoChatEnabled() {
                return AccountSettings.isEnabled(getLong(AccountSettings.SETTING_ALLOW_VIDEOCHAT_V2, 2));
            }

            public boolean getVideoChatUnset() {
                return getLong(AccountSettings.SETTING_ALLOW_VIDEOCHAT_V2, 0) == 0;
            }

            @Deprecated
            public boolean setCameraEnabled(boolean enabled, boolean userSet) {
                return AccountSettings.setCameraEnabled(this.mContentResolver, enabled, userSet, getLong(AccountSettings.SETTING_ALLOW_CAMERA, 0), this.mAccountId);
            }

            public void setCameraEnabled(boolean enabled) {
                AccountSettings.setCameraEnabled(this.mContentResolver, enabled, this.mAccountId);
            }

            public boolean getCameraEnabled() {
                return AccountSettings.isEnabled(getLong(AccountSettings.SETTING_ALLOW_CAMERA, 2));
            }

            public boolean getCameraUnset() {
                return getLong(AccountSettings.SETTING_ALLOW_CAMERA, 0) == 0;
            }

            public int getCapabilities() {
                int caps = HAS_PMUC_V1;
                if (getAudioChatEnabled()) {
                    caps = HAS_PMUC_V1 | HAS_VOICE_V1;
                }
                if (!getVideoChatEnabled()) {
                    return caps;
                }
                caps |= HAS_VIDEO_V1;
                if (getCameraEnabled()) {
                    return caps | HAS_CAMERA_V1;
                }
                return caps;
            }

            public static int getCapabilities(boolean video, boolean audio) {
                int i = AccountSettings.UNSET;
                int i2 = video ? 6 : AccountSettings.UNSET;
                if (audio) {
                    i = HAS_VOICE_V1;
                }
                return i | i2;
            }

            public void setShowAwayOnIdle(boolean showAway) {
                AccountSettings.setShowAwayOnIdle(this.mContentResolver, showAway, this.mAccountId);
            }

            public boolean getShowAwayOnIdle() {
                return getBoolean(AccountSettings.SETTING_SHOW_AWAY_ON_IDLE, true);
            }

            public void setNotifyFriendInvitation(boolean notify) {
                AccountSettings.setNotifyFriendInvitation(this.mContentResolver, notify, this.mAccountId);
            }

            public boolean getNotifyFriendInvitation() {
                return getBoolean(AccountSettings.SETTING_NOTIFY_FRIEND_INVITE, true);
            }

            public void setUploadHeartbeatStat(boolean uploadStat) {
                AccountSettings.setUploadHeartbeatStat(this.mContentResolver, uploadStat, this.mAccountId);
            }

            public boolean getUploadHeartbeatStat() {
                return getBoolean(AccountSettings.SETTING_UPLOAD_HEARTBEAT_STAT, false);
            }

            public void setHeartbeatInterval(long interval) {
                AccountSettings.setHeartbeatInterval(this.mContentResolver, interval, this.mAccountId);
            }

            public long getHeartbeatInterval() {
                return getLong(AccountSettings.SETTING_HEARTBEAT_INTERVAL, 0);
            }

            public void setJidResource(String jidResource) {
                AccountSettings.setJidResource(this.mContentResolver, jidResource, this.mAccountId);
            }

            public String getJidResource() {
                return getString(AccountSettings.SETTING_JID_RESOURCE, null);
            }

            private boolean getBoolean(String name, boolean def) {
                ContentValues values = getValues(name);
                return values != null ? values.getAsBoolean(AccountSettingsColumns.VALUE).booleanValue() : def;
            }

            private String getString(String name, String def) {
                ContentValues values = getValues(name);
                return values != null ? values.getAsString(AccountSettingsColumns.VALUE) : def;
            }

            private int getInteger(String name, int def) {
                ContentValues values = getValues(name);
                return values != null ? values.getAsInteger(AccountSettingsColumns.VALUE).intValue() : def;
            }

            private long getLong(String name, long def) {
                ContentValues values = getValues(name);
                return values != null ? values.getAsLong(AccountSettingsColumns.VALUE).longValue() : def;
            }
        }

        private AccountSettings() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/accountSettings");
        }

        public static final Uri getContentUriByAccountId(long accountId) {
            Builder builder = CONTENT_URI.buildUpon();
            ContentUris.appendId(builder, accountId);
            return builder.build();
        }

        public static void putLongValue(ContentResolver cr, String name, long value, long accountId) {
            ContentValues v = new ContentValues(ENABLED_MASK);
            v.put(AccountSettingsColumns.NAME, name);
            v.put(AccountSettingsColumns.VALUE, Long.valueOf(value));
            v.put(ChatsColumns.ACCOUNT_ID, Long.valueOf(accountId));
            cr.insert(CONTENT_URI, v);
        }

        public static void putBooleanValue(ContentResolver cr, String name, boolean value, long accountId) {
            ContentValues v = new ContentValues(ENABLED_MASK);
            v.put(AccountSettingsColumns.NAME, name);
            v.put(AccountSettingsColumns.VALUE, Boolean.toString(value));
            v.put(ChatsColumns.ACCOUNT_ID, Long.valueOf(accountId));
            cr.insert(CONTENT_URI, v);
        }

        public static void putStringValue(ContentResolver cr, String name, String value, long accountId) {
            ContentValues v = new ContentValues(ENABLED_MASK);
            v.put(AccountSettingsColumns.NAME, name);
            v.put(AccountSettingsColumns.VALUE, value);
            v.put(ChatsColumns.ACCOUNT_ID, Long.valueOf(accountId));
            cr.insert(CONTENT_URI, v);
        }

        public static void setAutomaticallyConnectGTalk(ContentResolver contentResolver, boolean autoConnect, long accountId) {
            putBooleanValue(contentResolver, SETTING_AUTOMATICALLY_CONNECT_GTALK, autoConnect, accountId);
        }

        public static void setHideOfflineContacts(ContentResolver contentResolver, boolean hideOfflineContacts, long accountId) {
            putBooleanValue(contentResolver, SETTING_HIDE_OFFLINE_CONTACTS, hideOfflineContacts, accountId);
        }

        public static void setTextVibrate(ContentResolver contentResolver, boolean vibrate, long accountId) {
            putBooleanValue(contentResolver, SETTING_TEXT_VIBRATE, vibrate, accountId);
        }

        public static void setTextVibrateWhen(ContentResolver contentResolver, String when, long accountId) {
            putStringValue(contentResolver, SETTING_TEXT_VIBRATE_WHEN, when, accountId);
        }

        public static void setVideoVibrate(ContentResolver contentResolver, boolean vibrate, long accountId) {
            putBooleanValue(contentResolver, SETTING_VIDEO_VIBRATE, vibrate, accountId);
        }

        public static void setVideoVibrateWhen(ContentResolver contentResolver, String when, long accountId) {
            putStringValue(contentResolver, SETTING_VIDEO_VIBRATE_WHEN, when, accountId);
        }

        public static void setVideoImageStabilization(ContentResolver contentResolver, String imageStabilization, long accountId) {
            putStringValue(contentResolver, SETTING_VIDEO_IMAGE_STABILIZATION, imageStabilization, accountId);
        }

        public static void setNotificationType(ContentResolver contentResolver, String notificationType, long accountId) {
            putStringValue(contentResolver, SETTING_IM_NOTIFICATION_TYPE, notificationType, accountId);
        }

        public static void setVideoNotificationType(ContentResolver contentResolver, String notificationType, long accountId) {
            putStringValue(contentResolver, SETTING_VIDEO_NOTIFICATION_TYPE, notificationType, accountId);
        }

        public static void setTextRingtoneURI(ContentResolver contentResolver, String ringtoneUri, long accountId) {
            putStringValue(contentResolver, SETTING_TEXT_RINGTONE, ringtoneUri, accountId);
        }

        public static void setVideoRingtoneURI(ContentResolver contentResolver, String ringtoneUri, long accountId) {
            putStringValue(contentResolver, SETTING_VIDEO_RINGTONE, ringtoneUri, accountId);
        }

        public static void setShowMobileIndicator(ContentResolver contentResolver, boolean showMobileIndicator, long accountId) {
            putBooleanValue(contentResolver, SETTING_SHOW_MOBILE_INDICATOR, showMobileIndicator, accountId);
        }

        @Deprecated
        public static boolean setAudioChatEnabled(ContentResolver contentResolver, boolean enabled, boolean userSet, long currentValue, long accountId) {
            if (!userSet && isUserSet(currentValue)) {
                return false;
            }
            putLongValue(contentResolver, SETTING_ALLOW_AUDIOCHAT_V2, (long) getUserSettingValue(enabled, userSet), accountId);
            return true;
        }

        public static void setAudioChatEnabled(ContentResolver contentResolver, boolean enabled, long accountId) {
            putLongValue(contentResolver, SETTING_ALLOW_AUDIOCHAT_V2, (long) getSettingValue(enabled), accountId);
        }

        @Deprecated
        public static boolean setVideoChatEnabled(ContentResolver contentResolver, boolean enabled, boolean userSet, long currentValue, long accountId) {
            if (!userSet && isUserSet(currentValue)) {
                return false;
            }
            putLongValue(contentResolver, SETTING_ALLOW_VIDEOCHAT_V2, (long) getUserSettingValue(enabled, userSet), accountId);
            return true;
        }

        public static void setVideoChatEnabled(ContentResolver contentResolver, boolean enabled, long accountId) {
            putLongValue(contentResolver, SETTING_ALLOW_VIDEOCHAT_V2, (long) getSettingValue(enabled), accountId);
        }

        @Deprecated
        public static boolean setCameraEnabled(ContentResolver contentResolver, boolean enabled, boolean userSet, long currentValue, long accountId) {
            if (!userSet && isUserSet(currentValue)) {
                return false;
            }
            putLongValue(contentResolver, SETTING_ALLOW_CAMERA, (long) getUserSettingValue(enabled, userSet), accountId);
            return true;
        }

        public static void setCameraEnabled(ContentResolver contentResolver, boolean enabled, long accountId) {
            putLongValue(contentResolver, SETTING_ALLOW_CAMERA, (long) getSettingValue(enabled), accountId);
        }

        private static boolean isEnabled(long value) {
            return (3 & value) == 1;
        }

        private static boolean isUserSet(long value) {
            return (value & 16) == 16;
        }

        private static int getUserSettingValue(boolean enabled, boolean userSet) {
            return (userSet ? USER_SET_MASK : UNSET) | (enabled ? ENABLED : DISABLED);
        }

        private static int getSettingValue(boolean enabled) {
            return enabled ? ENABLED : DISABLED;
        }

        public static void setShowAwayOnIdle(ContentResolver contentResolver, boolean showAway, long accountId) {
            putBooleanValue(contentResolver, SETTING_SHOW_AWAY_ON_IDLE, showAway, accountId);
        }

        public static void setNotifyFriendInvitation(ContentResolver contentResolver, boolean notify, long accountId) {
            putBooleanValue(contentResolver, SETTING_NOTIFY_FRIEND_INVITE, notify, accountId);
        }

        public static void setUploadHeartbeatStat(ContentResolver contentResolver, boolean uploadStat, long accountId) {
            putBooleanValue(contentResolver, SETTING_UPLOAD_HEARTBEAT_STAT, uploadStat, accountId);
        }

        public static void setHeartbeatInterval(ContentResolver contentResolver, long interval, long accountId) {
            putLongValue(contentResolver, SETTING_HEARTBEAT_INTERVAL, interval, accountId);
        }

        public static void setJidResource(ContentResolver contentResolver, String jidResource, long accountId) {
            putStringValue(contentResolver, SETTING_JID_RESOURCE, jidResource, accountId);
        }
    }

    public interface AccountStatusColumns {
        public static final String ACCOUNT = "account";
        public static final String CONNECTION_STATUS = "connStatus";
        public static final String PRESENCE_STATUS = "presenceStatus";
    }

    public static final class AccountStatus implements BaseColumns, AccountStatusColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-account-status";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-account-status";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_UNREAD_CHATS;
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/accountStatus");
            CONTENT_URI_UNREAD_CHATS = Uri.parse("content://com.google.android.providers.talk/accountStatus/new_messages");
        }
    }

    public interface AvatarsColumns {
        public static final String ACCOUNT = "account_id";
        public static final String CONTACT = "contact";
        public static final String DATA = "data";
        public static final String HASH = "hash";
    }

    public static final class Avatars implements BaseColumns, AvatarsColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-avatars";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-avatars";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_AVATARS_BY;
        public static final String DEFAULT_SORT_ORDER = "contact ASC";

        private Avatars() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/avatars");
            CONTENT_URI_AVATARS_BY = Uri.parse("content://com.google.android.providers.talk/avatarsBy");
        }
    }

    public interface ChatsColumns {
        public static final String ACCOUNT_ID = "account_id";
        public static final String CONTACT_ID = "contact_id";
        public static final String GROUP_CHAT = "groupchat";
        public static final String INITIATED_BY_LOCAL = "local";
        public static final String IS_ACTIVE = "is_active";
        public static final String JID_RESOURCE = "jid_resource";
        public static final String LAST_MESSAGE_DATE = "last_message_date";
        public static final String LAST_UNREAD_MESSAGE = "last_unread_message";
        public static final String OTHER_CLIENT = "otherClient";
        public static final String SHORTCUT = "shortcut";
        public static final String UNSENT_COMPOSED_MESSAGE = "unsent_composed_message";
    }

    public static final class Chats implements BaseColumns, ChatsColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-chats";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-chats";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_BY_ACCOUNT;
        public static final String DEFAULT_SORT_ORDER = "last_message_date ASC";

        private Chats() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/chats");
            CONTENT_URI_BY_ACCOUNT = Uri.parse("content://com.google.android.providers.talk/chats/account");
        }
    }

    public interface CommonPresenceColumns {
        public static final int AVAILABLE = 5;
        public static final int AWAY = 2;
        public static final int DO_NOT_DISTURB = 4;
        public static final int IDLE = 3;
        public static final int INVISIBLE = 1;
        public static final int OFFLINE = 0;
        public static final String PRESENCE_CUSTOM_STATUS = "status";
        public static final String PRESENCE_STATUS = "mode";
        public static final String PRIORITY = "priority";
    }

    public interface ConnectionStatus {
        public static final int CONNECTING = 1;
        public static final int OFFLINE = 0;
        public static final int ONLINE = 3;
        public static final int SUSPENDED = 2;
    }

    public interface ContactsColumns {
        public static final String ACCOUNT = "account";
        public static final String CONTACTLIST = "contactList";
        public static final String NICKNAME = "nickname";
        public static final String OTR = "otr";
        public static final String QUICK_CONTACT = "qc";
        public static final String REJECTED = "rejected";
        public static final String SUBSCRIPTION_STATUS = "subscriptionStatus";
        public static final int SUBSCRIPTION_STATUS_NONE = 0;
        public static final int SUBSCRIPTION_STATUS_SUBSCRIBE_PENDING = 1;
        public static final int SUBSCRIPTION_STATUS_UNSUBSCRIBE_PENDING = 2;
        public static final String SUBSCRIPTION_TYPE = "subscriptionType";
        public static final int SUBSCRIPTION_TYPE_BOTH = 4;
        public static final int SUBSCRIPTION_TYPE_FROM = 3;
        public static final int SUBSCRIPTION_TYPE_INVITATIONS = 5;
        public static final int SUBSCRIPTION_TYPE_NONE = 0;
        public static final int SUBSCRIPTION_TYPE_REMOVE = 1;
        public static final int SUBSCRIPTION_TYPE_TO = 2;
        public static final String TYPE = "type";
        public static final int TYPE_BLOCKED = 3;
        public static final int TYPE_GROUP = 2;
        public static final int TYPE_HIDDEN = 4;
        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_PINNED = 5;
        public static final int TYPE_TEMPORARY = 1;
        public static final String USERNAME = "username";
    }

    public interface PresenceColumns extends CommonPresenceColumns {
        public static final String CAPABILITIES = "cap";
        public static final int CAPABILITY_HAS_CAMERA_V1 = 4;
        public static final int CAPABILITY_HAS_PMUC_V1 = 8;
        public static final int CAPABILITY_HAS_VIDEO_V1 = 2;
        public static final int CAPABILITY_HAS_VOICE_V1 = 1;
        public static final String CLIENT_TYPE = "client_type";
        public static final int CLIENT_TYPE_ANDROID = 2;
        public static final int CLIENT_TYPE_DEFAULT = 0;
        public static final int CLIENT_TYPE_MOBILE = 1;
        public static final String CONTACT_ID = "contact_id";
        public static final String JID_RESOURCE = "jid_resource";
    }

    public static final class Contacts implements BaseColumns, ContactsColumns, PresenceColumns, ChatsColumns {
        public static final String AVATAR_DATA = "avatars_data";
        public static final String AVATAR_HASH = "avatars_hash";
        public static final String CHATS_CONTACT = "chats_contact";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-contacts";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-contacts";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_BLOCKED_CONTACTS;
        public static final Uri CONTENT_URI_CHAT_CONTACTS;
        public static final Uri CONTENT_URI_CONTACTS_BAREBONE;
        public static final Uri CONTENT_URI_CONTACT_ID;
        public static final String DEFAULT_SORT_ORDER = "subscriptionType DESC, (chats._id != 0) DESC, chats._id DESC, mode DESC, nickname COLLATE UNICODE ASC";

        private Contacts() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/contacts");
            CONTENT_URI_CONTACTS_BAREBONE = Uri.parse("content://com.google.android.providers.talk/contacts_barebone");
            CONTENT_URI_CHAT_CONTACTS = Uri.parse("content://com.google.android.providers.talk/contacts_chatting");
            CONTENT_URI_BLOCKED_CONTACTS = Uri.parse("content://com.google.android.providers.talk/contacts/blocked");
            CONTENT_URI_CONTACT_ID = Uri.parse("content://com.google.android.providers.talk/contacts");
        }
    }

    public interface ContactsEtagColumns {
        public static final String ACCOUNT = "account";
        public static final String ETAG = "etag";
        public static final String OTR_ETAG = "otr_etag";
    }

    public static final class ContactsEtag implements BaseColumns, ContactsEtagColumns {
        private static int COLUMN_ETAG = 0;
        private static int COLUMN_OTR_ETAG = 0;
        private static final String[] CONTACT_ETAG_PROJECTION;
        private static final String[] CONTACT_OTR_ETAG_PROJECTION;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-contactsEtag";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-contactsEtag";
        public static final Uri CONTENT_URI;

        private ContactsEtag() {
        }

        public static final Cursor query(ContentResolver cr, String[] projection) {
            return cr.query(CONTENT_URI, projection, null, null, null);
        }

        public static final Cursor query(ContentResolver cr, String[] projection, String where, String orderBy) {
            return cr.query(CONTENT_URI, projection, where, null, orderBy == null ? null : orderBy);
        }

        public static final String getRosterEtag(ContentResolver resolver, long accountId) {
            String retVal = null;
            Cursor c = resolver.query(CONTENT_URI, CONTACT_ETAG_PROJECTION, "account=" + accountId, null, null);
            try {
                if (c.moveToFirst()) {
                    retVal = c.getString(COLUMN_ETAG);
                }
                c.close();
                return retVal;
            } catch (Throwable th) {
                c.close();
            }
        }

        public static final String getOtrEtag(ContentResolver resolver, long accountId) {
            String retVal = null;
            Cursor c = resolver.query(CONTENT_URI, CONTACT_OTR_ETAG_PROJECTION, "account=" + accountId, null, null);
            try {
                if (c.moveToFirst()) {
                    retVal = c.getString(COLUMN_OTR_ETAG);
                }
                c.close();
                return retVal;
            } catch (Throwable th) {
                c.close();
            }
        }

        static {
            CONTACT_ETAG_PROJECTION = new String[]{ContactsEtagColumns.ETAG};
            COLUMN_ETAG = 0;
            CONTACT_OTR_ETAG_PROJECTION = new String[]{ContactsEtagColumns.OTR_ETAG};
            COLUMN_OTR_ETAG = 0;
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/contactsEtag");
        }
    }

    public interface GroupMemberColumns {
        public static final String GROUP = "groupId";
        public static final String NICKNAME = "nickname";
        public static final String USERNAME = "username";
    }

    public static final class GroupMembers implements GroupMemberColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-groupMembers";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-groupMembers";
        public static final Uri CONTENT_URI;

        private GroupMembers() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/groupMembers");
        }
    }

    public interface InvitationColumns {
        public static final String ACCOUNT = "accountId";
        public static final String GROUP_NAME = "groupName";
        public static final String INVITE_ID = "inviteId";
        public static final String NOTE = "note";
        public static final String SENDER = "sender";
        public static final String STATUS = "status";
        public static final int STATUS_ACCEPTED = 1;
        public static final int STATUS_PENDING = 0;
        public static final int STATUS_REJECTED = 2;
    }

    public static final class Invitation implements InvitationColumns, BaseColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-invitations";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-invitations";
        public static final Uri CONTENT_URI;

        private Invitation() {
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/invitations");
        }
    }

    public interface LastRmqIdColumns {
        public static final String RMQ_ID = "rmq_id";
    }

    public static final class LastRmqId implements BaseColumns, LastRmqIdColumns {
        public static final Uri CONTENT_URI;
        private static String[] PROJECTION;

        static {
            PROJECTION = new String[]{ServerToDeviceRmqIdsColumn.RMQ_ID};
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/lastRmqId");
        }

        public static final long queryLastRmqId(ContentResolver resolver) {
            Cursor cursor = resolver.query(CONTENT_URI, PROJECTION, null, null, null);
            long retVal = 0;
            try {
                if (cursor.moveToFirst()) {
                    retVal = cursor.getLong(cursor.getColumnIndexOrThrow(ServerToDeviceRmqIdsColumn.RMQ_ID));
                }
                cursor.close();
                return retVal;
            } catch (Throwable th) {
                cursor.close();
            }
        }

        public static final void saveLastRmqId(ContentResolver resolver, long rmqId) {
            ContentValues values = new ContentValues();
            values.put(PeoplePostalAddress._ID, Integer.valueOf(1));
            values.put(ServerToDeviceRmqIdsColumn.RMQ_ID, Long.valueOf(rmqId));
            resolver.insert(CONTENT_URI, values);
        }
    }

    public interface MessageColumns {
        public static final String BODY = "body";
        public static final String CONSOLIDATION_KEY = "consolidation_key";
        public static final String DATE = "date";
        public static final String DISPLAY_SENT_TIME = "show_ts";
        public static final String ERROR_CODE = "err_code";
        public static final String ERROR_MESSAGE = "err_msg";
        public static final String IS_GROUP_CHAT = "is_muc";
        public static final String MESSAGE_READ_KEY = "message_read";
        public static final String NICKNAME = "nickname";
        public static final String PACKET_ID = "packet_id";
        public static final String REAL_DATE = "real_date";
        public static final String SEND_STATUS_KEY = "send_status";
        public static final String THREAD_ID = "thread_id";
        public static final String TYPE = "type";
    }

    public interface MessageType {
        public static final int CONVERT_TO_GROUPCHAT = 6;
        public static final int END_CAUSE_MESSAGE = 16;
        public static final int INCOMING = 1;
        public static final int MISSED_CALL_MESSAGE = 15;
        public static final int NEW_STATUS_MESSAGE = 13;
        public static final int OTR_IS_TURNED_OFF = 9;
        public static final int OTR_IS_TURNED_ON = 10;
        public static final int OTR_TURNED_ON_BY_BUDDY = 12;
        public static final int OTR_TURNED_ON_BY_USER = 11;
        public static final int OUTGOING = 0;
        public static final int PRESENCE_AVAILABLE = 2;
        public static final int PRESENCE_AWAY = 3;
        public static final int PRESENCE_DND = 4;
        public static final int PRESENCE_UNAVAILABLE = 5;
        public static final int STATUS = 7;
        public static final int STATUS_MESSAGE = 14;
    }

    public static final class Messages implements BaseColumns, MessageColumns {
        public static final String CONTACT = "contact";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/gtalk-messages";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-messages";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_BY_ACCOUNT;
        public static final Uri CONTENT_URI_MESSAGES_BY_ACCOUNT_AND_CONTACT;
        public static final Uri CONTENT_URI_MESSAGES_BY_THREAD_ID;
        public static final String DEFAULT_SORT_ORDER = "date ASC";
        public static final Uri OTR_MESSAGES_CONTENT_URI;
        public static final Uri OTR_MESSAGES_CONTENT_URI_BY_ACCOUNT;
        public static final Uri OTR_MESSAGES_CONTENT_URI_BY_ACCOUNT_AND_CONTACT;
        public static final Uri OTR_MESSAGES_CONTENT_URI_BY_THREAD_ID;

        private Messages() {
        }

        public static final Uri getContentUriByThreadId(long threadId) {
            Builder builder = CONTENT_URI_MESSAGES_BY_THREAD_ID.buildUpon();
            ContentUris.appendId(builder, threadId);
            return builder.build();
        }

        @Deprecated
        public static final Uri getContentUriByContact(long accountId, String username) {
            Builder builder = CONTENT_URI_MESSAGES_BY_ACCOUNT_AND_CONTACT.buildUpon();
            ContentUris.appendId(builder, accountId);
            builder.appendPath(username);
            return builder.build();
        }

        public static final Uri getContentUriByAccount(long accountId) {
            Builder builder = CONTENT_URI_BY_ACCOUNT.buildUpon();
            ContentUris.appendId(builder, accountId);
            return builder.build();
        }

        public static final Uri getOtrMessagesContentUriByThreadId(long threadId) {
            Builder builder = OTR_MESSAGES_CONTENT_URI_BY_THREAD_ID.buildUpon();
            ContentUris.appendId(builder, threadId);
            return builder.build();
        }

        @Deprecated
        public static final Uri getOtrMessagesContentUriByContact(long accountId, String username) {
            Builder builder = OTR_MESSAGES_CONTENT_URI_BY_ACCOUNT_AND_CONTACT.buildUpon();
            ContentUris.appendId(builder, accountId);
            builder.appendPath(username);
            return builder.build();
        }

        public static final Uri getOtrMessagesContentUriByAccount(long accountId) {
            Builder builder = OTR_MESSAGES_CONTENT_URI_BY_ACCOUNT.buildUpon();
            ContentUris.appendId(builder, accountId);
            return builder.build();
        }

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/messages");
            CONTENT_URI_MESSAGES_BY_THREAD_ID = Uri.parse("content://com.google.android.providers.talk/messagesByThreadId");
            CONTENT_URI_MESSAGES_BY_ACCOUNT_AND_CONTACT = Uri.parse("content://com.google.android.providers.talk/messagesByAcctAndContact");
            CONTENT_URI_BY_ACCOUNT = Uri.parse("content://com.google.android.providers.talk/messagesByAccount");
            OTR_MESSAGES_CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/otrMessages");
            OTR_MESSAGES_CONTENT_URI_BY_THREAD_ID = Uri.parse("content://com.google.android.providers.talk/otrMessagesByThreadId");
            OTR_MESSAGES_CONTENT_URI_BY_ACCOUNT_AND_CONTACT = Uri.parse("content://com.google.android.providers.talk/otrMessagesByAcctAndContact");
            OTR_MESSAGES_CONTENT_URI_BY_ACCOUNT = Uri.parse("content://com.google.android.providers.talk/otrMessagesByAccount");
        }
    }

    public interface OffTheRecordType {
        public static final int DISABLED = 0;
        public static final int ENABLED = 1;
        public static final int ENABLED_BY_BUDDY = 3;
        public static final int ENABLED_BY_USER = 2;
    }

    public interface OutgoingRmqColumns {
        public static final String ACCOUNT_ID = "account";
        public static final String DATA = "data";
        public static final String PACKET_ID = "packet_id";
        public static final String PROTOBUF_TAG = "type";
        public static final String RMQ_ID = "rmq_id";
        public static final String TIMESTAMP = "ts";
    }

    public static final class OutgoingRmq implements BaseColumns, OutgoingRmqColumns {
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_FOR_HIGHEST_RMQ_ID;
        public static final String DEFAULT_SORT_ORDER = "rmq_id ASC";
        private static String[] RMQ_ID_PROJECTION;

        static {
            RMQ_ID_PROJECTION = new String[]{ServerToDeviceRmqIdsColumn.RMQ_ID};
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/outgoingRmqMessages");
            CONTENT_URI_FOR_HIGHEST_RMQ_ID = Uri.parse("content://com.google.android.providers.talk/outgoingHighestRmqId");
        }

        public static final long queryHighestRmqId(ContentResolver resolver) {
            Cursor cursor = resolver.query(CONTENT_URI_FOR_HIGHEST_RMQ_ID, RMQ_ID_PROJECTION, null, null, null);
            long retVal = 0;
            try {
                if (cursor.moveToFirst()) {
                    retVal = cursor.getLong(cursor.getColumnIndexOrThrow(ServerToDeviceRmqIdsColumn.RMQ_ID));
                }
                cursor.close();
                return retVal;
            } catch (Throwable th) {
                cursor.close();
            }
        }
    }

    public static final class Presence implements BaseColumns, PresenceColumns {
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/gtalk-presence";
        public static final Uri CONTENT_URI;
        public static final Uri CONTENT_URI_BY_ACCOUNT;
        public static final String DEFAULT_SORT_ORDER = "mode DESC";

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/presence");
            CONTENT_URI_BY_ACCOUNT = Uri.parse("content://com.google.android.providers.talk/presence/account");
        }
    }

    public interface ProviderNames {
        public static final String AIM = "AIM";
        public static final String GTALK = "GTalk";
        public static final String ICQ = "ICQ";
        public static final String JABBER = "JABBER";
        public static final String MSN = "MSN";
        public static final String QQ = "QQ";
        public static final String SKYPE = "SKYPE";
        public static final String XMPP = "XMPP";
        public static final String YAHOO = "Yahoo";
    }

    public interface SendingStatus {
        public static final int DELIVERED = 3;
        public static final int SENDING = 1;
        public static final int SENT = 2;
    }

    public interface ServerToDeviceRmqIdsColumn {
        public static final String RMQ_ID = "rmq_id";
    }

    public static final class ServerToDeviceRmqIds implements BaseColumns, ServerToDeviceRmqIdsColumn {
        public static final Uri CONTENT_URI;

        static {
            CONTENT_URI = Uri.parse("content://com.google.android.providers.talk/s2dids");
        }
    }

    static {
        AUTHORITY_URI = Uri.parse("content://com.google.android.providers.talk");
    }

    private TalkContract() {
    }
}
