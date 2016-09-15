package com.google.android.gms.people;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.wireless.android.play.playlog.proto.ClientAnalytics.LogRequest.LogSource;
import com.google.wireless.android.play.playlog.proto.UserLocation.LocationSharingEvent;
import com.google.wireless.android.play.playlog.proto.UserLocation.OvenFreshEvent.OvenFreshResult;
import com.google.wireless.android.play.playlog.proto.UserLocation.TimeSelection.Type;

public class PeopleConstants {

    public interface AcItemType {
        public static final int EMAIL = 1;
        public static final int GAIA_ID = 3;
        public static final int PERSON = 0;
        public static final int PHONE = 2;
        public static final int URL = 4;
    }

    public interface AutocompleteDataSource {
        public static final int ANDROID_CONTACTS = 2;
        public static final int DIRECTORY = 3;
        public static final int GOOGLE_CONTACT = 0;
        public static final int GOOGLE_PROFILE = 1;
    }

    public interface AutocompleteTypes {
        public static final int EMAIL = 0;
        public static final int EMAIL_EXACT = 1;
        public static final int PHONE = 2;
    }

    public interface Avatar {
        public static final String ACTION_SET_AVATAR = "com.google.android.gms.people.profile.ACTION_SET_AVATAR";
        public static final String EXTRA_ACCOUNT = "com.google.android.gms.people.profile.EXTRA_ACCOUNT";
        public static final String EXTRA_AVATAR_URL = "com.google.android.gms.people.profile.EXTRA_AVATAR_URL";
        public static final String EXTRA_PAGE_ID = "com.google.android.gms.people.profile.EXTRA_PAGE_ID";
        public static final String EXTRA_SOCIAL_CLIENT_APP_ID = "com.google.android.gms.people.profile.EXTRA_APP_ID";
        public static final String EXTRA_URI = "com.google.android.gms.people.profile.EXTRA_URI";
        public static final int RESULT_INTERNAL_ERROR = 1;
    }

    public interface AvatarOptions {
        public static final int NONE = 0;
        public static final int NO_DEFAULT_AVATAR = 1;
    }

    public interface AvatarSizes {
        public static final int CONTACTS_THUMBNAIL = 4;
        public static final int LARGE = 3;
        public static final int MEDIUM = 2;
        public static final int SMALL = 1;
        public static final int TINY = 0;
    }

    public interface BundleKeys {
        public static final String ACCOUNT = "account";
        public static final String ACCOUNT_METADATA = "account_metadata";
        public static final String ADDED_CIRCLES = "added_circles";
        public static final String ADDED_PEOPLE = "added_people";
        public static final String AUTOCOMPLETE_AUTOCOMPLETIONS = "autocomplete_autocompletions";
        public static final String AUTOCOMPLETE_AUTOCOMPLETION_REF = "autocomplete_autocompletion_ref";
        public static final String AUTOCOMPLETE_CALLBACK_NUMBER = "autocomplete_callback_number";
        public static final String AUTOCOMPLETE_CALLBACK_TOTAL = "autocomplete_callback_total";
        public static final String AUTOCOMPLETE_EVENT_TYPE = "autocomplete_event_type";
        public static final String AVATAR_URL = "avatarurl";
        public static final String CIRCLES_FIRST_TIME_ADD_NEED_CONSENT = "circles.first_time_add_need_consent";
        public static final String CIRCLES_FIRST_TIME_ADD_OK_TEXT = "circles.first_time_add_ok_text";
        public static final String CIRCLES_FIRST_TIME_ADD_TEXT = "circles.first_time_add_text";
        public static final String CIRCLES_FIRST_TIME_ADD_TITLE_TEXT = "circles.first_time_add_title_text";
        public static final String CIRCLE_ID = "circle_id";
        public static final String CIRCLE_NAME = "circle_name";
        public static final String CIRCLE_VISIBILITY = "circlevisibility";
        public static final String CONFIG_EMAIL_TYPE_MAP = "config.email_type_map";
        public static final String CONFIG_PHONE_TYPE_MAP = "config.phone_type_map";
        public static final String CONFIG_URL_UNCOMPRESS_PATTERNS = "config.url_uncompress.patterns";
        public static final String CONFIG_URL_UNCOMPRESS_REPLACEMENTS = "config.url_uncompress.replacements";
        public static final String EMAILS_WITH_AFFINITIES = "emails_with_affinities";
        public static final String HEIGHT = "height";
        public static final String INTERNAL_CALL_ARG_1 = "internal_call_arg_1";
        public static final String INTERNAL_CALL_METHOD = "internal_call_method";
        public static final String INTERNAL_CALL_RESULT = "internal_call_result";
        public static final String IS_PERIODIC_SYNC_ENABLED = "is_periodic_sync_enabled";
        public static final String IS_RESPONSE_COMPLETE = "response_complete";
        public static final String IS_TICKLE_SYNC_ENABLED = "is_tickle_sync_enabled";
        public static final String LAST_SUCCESSFUL_SYNC_FINISH_TIMESTAMP = "last_successful_sync_finish_timestamp";
        public static final String LAST_SYNC_FINISH_TIMESTAMP = "last_sync_finish_timestamp";
        public static final String LAST_SYNC_START_TIMESTAMP = "last_sync_start_timestamp";
        public static final String LAST_SYNC_STATUS = "last_sync_status";
        public static final String LOCALIZED_GROUP_NAMES = "localized_group_names";
        public static final String LOG_TEXT = "log_text";
        public static final String ME_PERSON = "me.person";
        public static final String ME_UPDATE_MASK = "me.update_mask";
        public static final String ME_UPDATE_RESULT = "me.update_result";
        public static final String PAGE_GAIA_ID = "pagegaiaid";
        public static final String PAGE_TOKEN = "pageToken";
        public static final String PEOPLE_DATABASE_TABLES = "db";
        public static final String PEOPLE_DATABASE_TABLE_CIRCLES = "circles";
        public static final String PEOPLE_DATABASE_TABLE_OWNER = "owner";
        public static final String PEOPLE_DATABASE_TABLE_OWNER_ADDRESS = "owner_address";
        public static final String PEOPLE_DATABASE_TABLE_OWNER_EMAIL = "owner_email";
        public static final String PEOPLE_DATABASE_TABLE_OWNER_PHONE = "owner_phone";
        public static final String PEOPLE_DATABASE_TABLE_PEOPLE = "people";
        public static final String PEOPLE_DATABASE_TABLE_PEOPLE_ADDRESS = "people_address";
        public static final String PEOPLE_DATABASE_TABLE_PEOPLE_EMAIL = "people_email";
        public static final String PEOPLE_DATABASE_TABLE_PEOPLE_PHONE = "people_phone";
        public static final String PEOPLE_MAP_GAIA = "gaia_map";
        public static final String PEOPLE_ONLY = "people_only";
        public static final String PEOPLE_SERVER_BLOBS = "get.server_blob";
        public static final String PEOPLE_SERVER_BLOB_BODY = "get.server_blob.body";
        public static final String PEOPLE_SERVER_BLOB_FORMAT = "get.server_blob.format";
        public static final String PEOPLE_SERVER_BLOB_HEADERS = "get.server_blob.headers";
        public static final String PEOPLE_SERVER_BLOB_RESPONSE_CODE = "get.server_blob.code";
        public static final String POST_INIT_CONFIGURATION = "post_init_configuration";
        public static final String POST_INIT_RESOLUTION = "post_init_resolution";
        public static final String QUERY = "query";
        public static final String REAL_CLIENT_PACKAGE = "real_client_package_name";
        public static final String REMOVED_CIRCLES = "removed_circles";
        public static final String REWINDABLE = "rewindable";
        public static final String SCOPE = "scope";
        public static final String SOCIAL_CLIENT_APPLICATION_ID = "social_client_application_id";
        public static final String SUPPORT_NEW_IMAGE_CALLBACK = "support_new_image_callback";
        public static final String USE_CONTACTABLES_API = "use_contactables_api";
        public static final String WIDTH = "width";
    }

    public interface CircleTypes {
        public static final int ALL = -999;
        public static final int DASHER_DOMAIN = 2;
        public static final int EXTENDED_CIRCLES = 4;
        public static final int OTHER_SYSTEM_GROUP = -2;
        public static final int PERSONAL = -1;
        public static final int PUBLIC = 1;
        public static final int SYSTEM_GROUPS = -998;
        public static final int YOUR_CIRCLES = 3;
    }

    public interface CircleVisibility {
        public static final int LIMITED = 2;
        public static final int PRIVATE = 3;
        public static final int PUBLIC = 1;
        public static final int UNKNOWN = 0;
    }

    public interface Circles {
        public static final String CIRCLE_ID = "circle_id";
        public static final String CLIENT_POLICIES = "client_policies";
        public static final String ETAG = "etag";
        public static final String FOR_SHARING = "for_sharing";
        public static final String LAST_MODIFIED_TIME = "last_modified";
        public static final String NAME = "name";
        public static final String PEOPLE_COUNT = "people_count";
        public static final String SORT_KEY = "sort_key";
        public static final String SYNC_TO_CONTACTS = "sync_to_contacts";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface ClientPolicies {
        public static final int CANNOT_ACL_TO = 8;
        public static final int CANNOT_MODIFY_MEMBERSHIP = 2;
        public static final int CANNOT_VIEW_MEMBERSHIP = 1;
        public static final int NONE = 0;
        public static final int UNDELETABLE = 4;
        public static final int VISIBLE_ONLY_WHEN_POPULATED = 16;
    }

    public interface ContactGroupPreferredFields {
        public static final String EMAIL = "email";
        public static final String NAME = "name";
    }

    public interface ContactsSyncTargets {
        public static final String EVERGREEN = "$$everrgreen$$";
        public static final String ME = "$$me$$";
        public static final String MY_CIRCLES = "$$mycircles$$";
    }

    public interface ContainerType {
        public static final int CIRCLE = 2;
        public static final int CONTACT = 1;
        public static final int PROFILE = 0;
        public static final int UNKNOWN = -1;
    }

    public interface DataChangedScopes {
        public static final int AGGREGATED_PEOPLE = 12;
        public static final int AUTOCOMPLETE = 256;
        public static final int CIRCLES = 2;
        public static final int CONTACTS_PROVIDER = 8;
        public static final int OWNERS = 1;
        public static final int PEOPLE = 4;
        public static final int SYNC_CANCELLED = 128;
        public static final int SYNC_FINISHED = 32;
        public static final int SYNC_FINISHED_UNSUCCESSFUL = 64;
        public static final int SYNC_STARTED = 16;
    }

    public interface DirectoryAccountTypes {
        public static final String GOOGLE = "com.google";
    }

    public interface EmailTypes {
        public static final int HOME = 1;
        public static final int OTHER = -1;
        public static final int WORK = 2;
    }

    public interface Endpoints {
        public static final String ENDPOINT_GET = "get";
        public static final String ENDPOINT_LIST = "list";
        public static final String ENDPOINT_LIST_BY_EMAIL = "list_by_email";
        public static final String ENDPOINT_LIST_BY_FOCUS_ID = "list_by_focus_id";
        public static final String ENDPOINT_LIST_BY_PHONE = "list_by_phone";
        public static final String ENDPOINT_LIST_WITH_EMAIL_AND_PHONE = "list_by_email_and_phone";
        public static final String KEY_EMAIL = "email";
        public static final String KEY_FOCUS_ID = "contact";
        public static final String KEY_ON_BEHALF_OF = "on_behalf_of";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_TARGET_GAIA_ID = "gaia_id";
        public static final String KEY_TARGET_QUALIFIED_ID = "qualified_id";
    }

    public interface FocusContactRelationship {
        public static final int IN_VISIBLE_CONTACTS = 1;
        public static final int NOT_IN_CONTACTS = 0;
    }

    public interface GaiaEdgeType {
        public static final int ALL = 7;
        public static final int EMAIL = 1;
        public static final int EMAIL_PHONE = 3;
        public static final int PHONE = 2;
        public static final int PROFILE_URL = 4;
    }

    public interface GaiaIdMap {
        public static final String CONTACT_ID = "contact_id";
        public static final String GAIA_ID = "gaia_id";
        public static final String TYPE = "type";
        public static final String VALUE = "value";
    }

    public interface InteractionTypes {
        public static final int CALL = 2;
        public static final int LONG_TEXT = 1;
        public static final int SHORT_TEXT = 0;
    }

    public interface InternalCallMethods {
        public static final String GET_FORCE_VERBOSE_LOG = "GET_FORCE_VERBOSE_LOG";
        public static final String GET_SHOW_SYNC_NOTIFICATION_ERROR = "GET_SHOW_SYNC_NOTIFICATION_ERROR";
        public static final String LOAD_GSERVICES_VALUES_FOR_SMART_PROFILE = "LOAD_GSERVICES_VALUES_FOR_SMART_PROFILE";
        public static final String LOAD_LOG = "LOAD_LOG";
        public static final String SET_FORCE_VERBOSE_LOG = "SET_FORCE_VERBOSE_LOG";
        public static final String SET_SHOW_SYNC_NOTIFICATION_ERROR = "SET_SHOW_SYNC_NOTIFICATION_ERROR";
    }

    public interface LastSyncStatus {
        public static final int FAILURE = 3;
        public static final int IN_PROGRESS = 1;
        public static final int NOT_SYNCED = 0;
        public static final int SUCCESS = 2;
        public static final int UPGRADED_SYNC_NEEDED = 4;
    }

    public interface LoadOwnersSortOrder {
        public static final int ACCOUNT_CREATION_ORDER = 1;
        public static final int ACCOUNT_NAME = 0;
    }

    public interface OwnerEmail {
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String EMAIL_ADDRESS = "email";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface OwnerPhone {
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String PHONE_NUMBER = "phone";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface OwnerPostalAddress {
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String POSTAL_ADDRESS = "postal_address";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface Owners {
        public static final String ACCOUNT_GAIA_ID = "gaia_id";
        public static final String ACCOUNT_NAME = "account_name";
        public static final String COMPRESSED_AVATAR_URL = "avatar";
        public static final String COMPRESSED_COVER_PHOTO_URL = "cover_photo_url";
        public static final String COVER_PHOTO_HEIGHT = "cover_photo_height";
        public static final String COVER_PHOTO_ID = "cover_photo_id";
        public static final String COVER_PHOTO_WIDTH = "cover_photo_width";
        public static final String DASHER_DOMAIN = "dasher_domain";
        public static final String DISPLAY_NAME = "display_name";
        public static final String IS_DASHER_ACCOUNT = "is_dasher";
        public static final String LAST_SUCCESSFUL_SYNC_FINISH_TIME = "last_successful_sync_time";
        public static final String LAST_SYNC_FINISH_TIME = "last_sync_finish_time";
        public static final String LAST_SYNC_START_TIME = "last_sync_start_time";
        public static final String LAST_SYNC_STATUS = "last_sync_status";
        public static final String PAGE_GAIA_ID = "page_gaia_id";
        public static final String SYNC_CIRCLES_TO_CONTACTS = "sync_circles_to_contacts";
        public static final String SYNC_EVERGREEN_TO_CONTACTS = "sync_evergreen_to_contacts";
        public static final String SYNC_ME_TO_CONTACTS = "sync_me_to_contacts";
        @Deprecated
        public static final String SYNC_TO_CONTACTS_DEPRECATED = "sync_to_contacts";
        public static final String _ID = "_id";
    }

    public interface People {
        public static final String AFFINITY_1 = "affinity1";
        public static final String AFFINITY_2 = "affinity2";
        public static final String AFFINITY_3 = "affinity3";
        public static final String AFFINITY_4 = "affinity4";
        public static final String AFFINITY_5 = "affinity5";
        public static final String BLOCKED = "blocked";
        public static final String COMPRESSED_AVATAR_URL = "avatar";
        public static final String DISPLAY_NAME = "name";
        public static final String ETAG = "etag";
        public static final String FAMILY_NAME = "family_name";
        public static final String GAIA_ID = "gaia_id";
        public static final String GIVEN_NAME = "given_name";
        public static final String INTERACTION_RANK_SORT_KEY = "sort_key_irank";
        public static final String IN_CIRCLE = "in_circle";
        public static final String IN_CONTACTS = "in_contacts";
        public static final String IN_VIEWER_DOMAIN = "in_viewer_domain";
        public static final String LAST_MODIFIED_TIME = "last_modified";
        public static final String LAST_NAME_SORT_KEY = "sort_key_last_name";
        public static final String LOGGING_ID_1 = "logging_id";
        public static final String LOGGING_ID_2 = "logging_id2";
        public static final String LOGGING_ID_3 = "logging_id3";
        public static final String LOGGING_ID_4 = "logging_id4";
        public static final String LOGGING_ID_5 = "logging_id5";
        public static final String MIDDLE_NAME = "middle_name";
        public static final String NAME_VERIFIED = "name_verified";
        public static final String PEOPLE_IN_COMMON = "people_in_common";
        public static final String PROFILE_TYPE = "profile_type";
        public static final String QUALIFIED_ID = "qualified_id";
        public static final String SORT_KEY = "sort_key";
        public static final String TAGLINE = "tagline";
        public static final String VIRTUAL_BELONGING_CIRCLE_IDS = "v_circle_ids";
        public static final String VIRTUAL_EMAILS = "v_emails";
        public static final String VIRTUAL_PHONES = "v_phones";
        public static final String _ID = "_id";
    }

    public interface PeopleColumnBitmask {
        public static final int AFFINITY_1 = 32768;
        public static final int AFFINITY_2 = 65536;
        public static final int AFFINITY_3 = 131072;
        public static final int AFFINITY_4 = 262144;
        public static final int AFFINITY_5 = 524288;
        public static final int ALL = 2097151;
        public static final int AVATAR_URL = 32;
        public static final int BELONGING_CIRCLES = 128;
        public static final int DISPLAY_NAME = 4;
        public static final int FAMILY_NAME = 8192;
        public static final int GAIA_ID = 2;
        public static final int GIVEN_NAME = 4096;
        public static final int INTERACTION_RANK_SORT_KEY = 16;
        public static final int IN_VIEWER_DOMAIN = 1024;
        public static final int IS_BLOCKED = 256;
        public static final int LAST_MODIFIED_TIME = 512;
        public static final int NAME_VERIFIED = 2048;
        public static final int PEOPLE_IN_COMMON = 1048576;
        public static final int PROFILE_TYPE = 64;
        public static final int QUALIFIED_ID = 1;
        public static final int SORT_KEY = 8;
        public static final int _ID = 16384;
    }

    public interface PeopleEmail {
        public static final String AFFINITY_1 = "affinity1";
        public static final String AFFINITY_2 = "affinity2";
        public static final String AFFINITY_3 = "affinity3";
        public static final String AFFINITY_4 = "affinity4";
        public static final String AFFINITY_5 = "affinity5";
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String EMAIL_ADDRESS = "email";
        public static final String LOGGING_ID_1 = "logging_id";
        public static final String LOGGING_ID_2 = "logging_id2";
        public static final String LOGGING_ID_3 = "logging_id3";
        public static final String LOGGING_ID_4 = "logging_id4";
        public static final String LOGGING_ID_5 = "logging_id5";
        public static final String QUALIFIED_ID = "qualified_id";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface PeopleEndpointDataFormat {
        public static final int MERGED_PERSON_JSON = 2;
        public static final int MERGED_PERSON_LIST_JSON = 4;
        public static final int MERGED_PERSON_LIST_PROTO = 3;
        public static final int MERGED_PERSON_PROTO = 1;
        public static final int UNKNOWN = 0;
    }

    public interface PeopleExtraColumnBitmask {
        public static final int ALL = 7;
        public static final int EMAILS = 1;
        public static final int EMAIL_AFFINITIES = 4;
        public static final int PHONES = 2;
    }

    public interface PeoplePhone {
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String PHONE_NUMBER = "phone";
        public static final String QUALIFIED_ID = "qualified_id";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface PeoplePostalAddress {
        public static final String CUSTOM_LABEL = "custom_label";
        public static final String POSTAL_ADDRESS = "postal_address";
        public static final String QUALIFIED_ID = "qualified_id";
        public static final String TYPE = "type";
        public static final String _ID = "_id";
    }

    public interface PeopleSearchFields {
        public static final int ALL = 7;
        public static final int EMAIL = 2;
        public static final int NAME = 1;
        public static final int PHONE = 4;
    }

    public interface PeopleSortBy {
        public static final int ALPHABETICAL = 0;
        public static final int BEST = 1;
    }

    @VisibleForTesting
    public interface PeopleSortOrder {
        public static final int AFFINITY_1 = 4;
        public static final int AFFINITY_2 = 5;
        public static final int AFFINITY_3 = 6;
        public static final int AFFINITY_4 = 7;
        public static final int AFFINITY_5 = 8;
        public static final int ALPHABETICAL = 1;
        public static final int DEFAULT = 0;
        public static final int DISPLAY_NAME_SQLITE_COLLATION = 3;
        public static final int INTERACTION_RANK = 2;
        public static final int MAX = 8;
        public static final int MIN = 0;
    }

    public interface PhoneTypes {
        public static final int ASSISTANT = 9;
        public static final int ASSISTENT = 9;
        public static final int CALLBACK = 13;
        public static final int CAR = 10;
        public static final int COMPANY_MAIN = 8;
        public static final int GOOGLE_VOICE = 19;
        public static final int HOME = 1;
        public static final int HOME_FAX = 4;
        public static final int ISDN = 12;
        public static final int MAIN = 18;
        public static final int MOBILE = 3;
        public static final int OTHER = -1;
        public static final int OTHER_FAX = 6;
        public static final int PAGER = 7;
        public static final int RADIO = 11;
        public static final int TELEX = 14;
        public static final int TTY = 15;
        public static final int WORK = 2;
        public static final int WORK_FAX = 5;
        public static final int WORK_MOBILE = 16;
        public static final int WORK_PAGER = 17;
    }

    public interface PostalAddressTypes {
        public static final int HOME = 1;
        public static final int OTHER = -1;
        public static final int WORK = 2;
    }

    public interface ProfileTypes {
        public static final int PAGE = 2;
        public static final int PERSON = 1;
        public static final int UNKNOWN = -1;
    }

    public interface TempGaiaToOrdinal {
        public static final String GAIA_ID = "gaia_id";
        public static final String ORDINAL = "ordinal";
        public static final String QUALIFIED_ID = "qualified_id";
    }

    public interface TriState {
        public static final int FALSE = 1;
        public static final int TRUE = 2;
        public static final int UNKNOWN = 0;
    }

    private PeopleConstants() {
    }

    public static int getPhoneTypeFromCp2PhoneType(int cp2PhoneType) {
        switch (cp2PhoneType) {
            case Type.TEMPORARY /*1*/:
                return 1;
            case Type.INDEFINITELY /*2*/:
                return 3;
            case Type.CUSTOM /*3*/:
                return 2;
            case OvenFreshResult.NO_ACCOUNTS /*4*/:
                return 5;
            case LocationSharingEvent.Type.ADD_NEW_SHARES /*5*/:
                return 4;
            case LocationSharingEvent.Type.REMOVE_SHARE /*6*/:
                return 7;
            case LocationSharingEvent.Type.TAP_GET_LINK /*8*/:
                return 13;
            case LocationSharingEvent.Type.CREATE_LINK /*9*/:
                return 10;
            case LocationSharingEvent.Type.TAP_ABOUT /*10*/:
                return 8;
            case LocationSharingEvent.Type.TAP_LEARN_MORE /*11*/:
                return 12;
            case LocationSharingEvent.Type.SWITCH_ACCOUNT /*12*/:
                return 18;
            case LocationSharingEvent.Type.DISPLAY_ERROR /*13*/:
                return 6;
            case LocationSharingEvent.Type.LAUNCH_SETTINGS /*14*/:
                return 11;
            case LocationSharingEvent.Type.OVEN_FRESH /*15*/:
                return 14;
            case LogSource.GMS_CORE_PEOPLE /*16*/:
                return 15;
            case LogSource.LE /*17*/:
                return 16;
            case LogSource.GOOGLE_ANALYTICS /*18*/:
                return 17;
            case LogSource.LB_D /*19*/:
                return 9;
            default:
                return -1;
        }
    }

    public static int getEmailTypeFromCp2EmailType(int cp2EmailType) {
        switch (cp2EmailType) {
            case Type.TEMPORARY /*1*/:
                return 1;
            case Type.INDEFINITELY /*2*/:
                return 2;
            default:
                return -1;
        }
    }

    public static void checkAvatarSizeArgument(int avatarSize, String argName) {
        boolean z = avatarSize >= 0 && avatarSize <= 4;
        Preconditions.checkArgument(z, argName);
    }

    public static int booleanToTriState(Boolean value) {
        if (value == null) {
            return 0;
        }
        return value.booleanValue() ? 2 : 1;
    }

    @VisibleForTesting
    public static Boolean triStateToBoolean(int triState) {
        boolean z = true;
        if (triState == 0) {
            return null;
        }
        if (triState == 1) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
