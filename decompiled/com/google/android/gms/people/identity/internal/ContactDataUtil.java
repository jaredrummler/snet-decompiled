package com.google.android.gms.people.identity.internal;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal;
import android.provider.ContactsContract.DisplayPhoto;
import android.text.TextUtils;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.people.identity.PersonFactory.RawContactData;
import com.google.android.gms.people.internal.PeopleServiceLog;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContactDataUtil {
    private static final String CONTACT_PHOTO_URL_PREFIX = "content://com.android.contacts/display_photo/";
    private static final String CP2_CONTACT_ID_QUALIFIER = "c:";
    private static final String FOCUS_ID_QUALIFIER = "f:";
    public static final String MIMETYPE_EMAIL = "vnd.android.cursor.item/email_v2";
    public static final String MIMETYPE_EVENT = "vnd.android.cursor.item/contact_event";
    public static final String MIMETYPE_IM = "vnd.android.cursor.item/im";
    public static final String MIMETYPE_NICKNAME = "vnd.android.cursor.item/nickname";
    public static final String MIMETYPE_NOTE = "vnd.android.cursor.item/note";
    public static final String MIMETYPE_ORGANIZATION = "vnd.android.cursor.item/organization";
    public static final String MIMETYPE_PHONE = "vnd.android.cursor.item/phone_v2";
    public static final String MIMETYPE_PHOTO = "vnd.android.cursor.item/photo";
    public static final String MIMETYPE_RELATION = "vnd.android.cursor.item/relation";
    public static final String MIMETYPE_STRUCTURED_NAME = "vnd.android.cursor.item/name";
    public static final String MIMETYPE_STRUCTURED_POSTAL = "vnd.android.cursor.item/postal-address_v2";
    public static final String MIMETYPE_WEBSITE = "vnd.android.cursor.item/website";
    private static final String PHONE_NUMBER_QUALIFIER = "p:";
    private static final String TAG = "ContactData";

    public static final class ContactAddressUtil {
        private static final int COLUMN_CITY = 6;
        private static final int COLUMN_COUNTRY = 9;
        private static final int COLUMN_CUSTOM_TYPE = 2;
        private static final int COLUMN_NEIGHBORHOOD = 5;
        private static final int COLUMN_POSTAL_CODE = 8;
        private static final int COLUMN_PO_BOX = 4;
        private static final int COLUMN_REGION = 7;
        private static final int COLUMN_STREET_ADDRESS = 3;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> STRUCTURED_POSTAL_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_TYPE), "home");
            temp.put(Integer.valueOf(COLUMN_STREET_ADDRESS), "other");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_TYPE), IdentityUtils.WORK_ORGANIZATION);
            temp.put(Integer.valueOf(0), "custom");
            STRUCTURED_POSTAL_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static String getFormattedTypeString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id == null) {
                return null;
            }
            return StructuredPostal.getTypeLabel(context.getResources(), id.intValue(), rawData.getData(COLUMN_CUSTOM_TYPE)).toString();
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && STRUCTURED_POSTAL_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) STRUCTURED_POSTAL_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid StructuredPostal Type: " + id);
            return null;
        }

        public static final String getStreetAddress(RawContactData rawData) {
            return rawData.getData(COLUMN_STREET_ADDRESS);
        }

        public static final String getPoBox(RawContactData rawData) {
            return rawData.getData(COLUMN_PO_BOX);
        }

        public static final String getNeighborhood(RawContactData rawData) {
            return rawData.getData(COLUMN_NEIGHBORHOOD);
        }

        public static final String getCity(RawContactData rawData) {
            return rawData.getData(COLUMN_CITY);
        }

        public static final String getRegion(RawContactData rawData) {
            return rawData.getData(COLUMN_REGION);
        }

        public static final String getPostalCode(RawContactData rawData) {
            return rawData.getData(COLUMN_POSTAL_CODE);
        }

        public static final String getCountry(RawContactData rawData) {
            String data = rawData.getData(COLUMN_COUNTRY);
            if (data == null || !TextUtils.isDigitsOnly(data)) {
                return data;
            }
            return null;
        }

        public static final String getCountryCode(RawContactData rawData) {
            String data = rawData.getData(COLUMN_COUNTRY);
            return (data == null || !TextUtils.isDigitsOnly(data)) ? null : data;
        }
    }

    public static final class ContactEmailUtil {
        private static final int COLUMN_ADDRESS = 0;
        private static final int COLUMN_CUSTOM_TYPE = 2;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> EMAIL_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_TYPE), "home");
            temp.put(Integer.valueOf(4), "mobile");
            temp.put(Integer.valueOf(3), "other");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_TYPE), IdentityUtils.WORK_ORGANIZATION);
            temp.put(Integer.valueOf(COLUMN_ADDRESS), "custom");
            EMAIL_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getAddress(RawContactData rawData) {
            return rawData.getData(COLUMN_ADDRESS);
        }

        public static String getFormattedTypeString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id == null) {
                return null;
            }
            return Email.getTypeLabel(context.getResources(), id.intValue(), rawData.getData(COLUMN_CUSTOM_TYPE)).toString();
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && EMAIL_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) EMAIL_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Email Type: " + id);
            return null;
        }
    }

    public static final class ContactEventUtil {
        private static final int COLUMN_DATE = 0;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> EVENT_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_TYPE), "anniversary");
            temp.put(Integer.valueOf(3), "birthday");
            temp.put(Integer.valueOf(2), "other");
            temp.put(Integer.valueOf(COLUMN_DATE), "custom");
            EVENT_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getDateString(RawContactData rawData) {
            return rawData.getData(COLUMN_DATE);
        }

        public static final String getTypeString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id == null) {
                return null;
            }
            return context.getString(Event.getTypeResource(id));
        }

        public static final String getFormattedTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && EVENT_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) EVENT_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Event Type: " + id);
            return null;
        }
    }

    public static final class ContactImUtil {
        private static final int COLUMN_ADDRESS = 0;
        private static final int COLUMN_CUSTOM_PROTOCOL = 5;
        private static final int COLUMN_CUSTOM_TYPE = 2;
        private static final int COLUMN_PROTOCOL = 4;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> IM_PROTOCOL_NUM_TO_STRING;
        private static final Map<Integer, String> IM_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_TYPE), "home");
            temp.put(Integer.valueOf(3), "other");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_TYPE), IdentityUtils.WORK_ORGANIZATION);
            temp.put(Integer.valueOf(COLUMN_ADDRESS), "custom");
            IM_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
            temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_ADDRESS), "aim");
            temp.put(Integer.valueOf(-1), "custom");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_PROTOCOL), "googleTalk");
            temp.put(Integer.valueOf(6), "icq");
            temp.put(Integer.valueOf(7), "jabber");
            temp.put(Integer.valueOf(COLUMN_TYPE), "msn");
            temp.put(Integer.valueOf(8), "netMeeting");
            temp.put(Integer.valueOf(COLUMN_PROTOCOL), "qq");
            temp.put(Integer.valueOf(3), "skype");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_TYPE), "yahoo");
            IM_PROTOCOL_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static String getAddress(RawContactData rawData) {
            return rawData.getData(COLUMN_ADDRESS);
        }

        public static String getFormattedTypeString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id == null) {
                return null;
            }
            return Im.getTypeLabel(context.getResources(), id.intValue(), rawData.getData(COLUMN_CUSTOM_TYPE)).toString();
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && IM_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) IM_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid IM Type: " + id);
            return null;
        }

        public static String getFormattedProtocolString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_PROTOCOL);
            if (id == null) {
                return null;
            }
            return Im.getProtocolLabel(context.getResources(), id.intValue(), rawData.getData(COLUMN_CUSTOM_PROTOCOL)).toString();
        }

        public static String getProtocolString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_PROTOCOL);
            if (id != null && IM_PROTOCOL_NUM_TO_STRING.containsKey(id)) {
                return (String) IM_PROTOCOL_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid IM Protocol: " + id);
            return null;
        }
    }

    public static final class ContactImageUtil {
        private static final int COLUMN_ID = 13;

        @TargetApi(14)
        public static final String getUrl(RawContactData rawData) {
            String str = null;
            String id = rawData.getData(COLUMN_ID);
            if (id != null && PlatformVersion.isAtLeastIceCreamSandwich()) {
                try {
                    str = ContentUris.withAppendedId(DisplayPhoto.CONTENT_URI, Long.valueOf(id).longValue()).toString();
                } catch (NumberFormatException e) {
                }
            }
            return str;
        }
    }

    public static final class ContactListItemUtil {
        public static final String getId(RawContactData rawData) {
            return rawData.getData(0);
        }

        public static final String getName(RawContactData rawData) {
            return rawData.getData(1);
        }

        @TargetApi(14)
        public static final String getImageUrl(RawContactData rawData) {
            String str = null;
            String id = rawData.getData(2);
            if (id != null && PlatformVersion.isAtLeastIceCreamSandwich()) {
                try {
                    str = ContentUris.withAppendedId(DisplayPhoto.CONTENT_URI, Long.valueOf(id).longValue()).toString();
                } catch (NumberFormatException e) {
                }
            }
            return str;
        }
    }

    public static final class ContactNameUtil {
        private static final int COLUMN_DISPLAY_NAME = 0;
        private static final int COLUMN_FAMILY_NAME = 2;
        private static final int COLUMN_GIVEN_NAME = 1;
        private static final int COLUMN_HONORIFIC_PREFIX = 3;
        private static final int COLUMN_HONORIFIC_SUFFIX = 5;
        private static final int COLUMN_MIDDLE_NAME = 4;
        private static final int COLUMN_PHONETIC_FAMILY_NAME = 8;
        private static final int COLUMN_PHONETIC_GIVEN_NAME = 6;
        private static final int COLUMN_PHONETIC_MIDDLE_NAME = 7;

        public static final String getDisplayName(RawContactData rawData) {
            return rawData.getData(COLUMN_DISPLAY_NAME);
        }

        public static final String getGivenName(RawContactData rawData) {
            return rawData.getData(COLUMN_GIVEN_NAME);
        }

        public static final String getFamilyName(RawContactData rawData) {
            return rawData.getData(COLUMN_FAMILY_NAME);
        }

        public static final String getHonorificPrefix(RawContactData rawData) {
            return rawData.getData(COLUMN_HONORIFIC_PREFIX);
        }

        public static final String getMiddleName(RawContactData rawData) {
            return rawData.getData(COLUMN_MIDDLE_NAME);
        }

        public static final String getHonorificSuffix(RawContactData rawData) {
            return rawData.getData(COLUMN_HONORIFIC_SUFFIX);
        }

        public static final String getPhoneticGivenName(RawContactData rawData) {
            return rawData.getData(COLUMN_PHONETIC_GIVEN_NAME);
        }

        public static final String getPhoneticMiddleName(RawContactData rawData) {
            return rawData.getData(COLUMN_PHONETIC_MIDDLE_NAME);
        }

        public static final String getPhoneticFamilyName(RawContactData rawData) {
            return rawData.getData(COLUMN_PHONETIC_FAMILY_NAME);
        }
    }

    public static final class ContactNicknameUtil {
        private static final int COLUMN_NAME = 0;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> NICKNAME_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_TYPE), "default");
            temp.put(Integer.valueOf(5), "initials");
            temp.put(Integer.valueOf(3), "maidenName");
            temp.put(Integer.valueOf(2), "otherName");
            temp.put(Integer.valueOf(4), "shortName");
            temp.put(Integer.valueOf(COLUMN_NAME), "custom");
            NICKNAME_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getName(RawContactData rawData) {
            return rawData.getData(COLUMN_NAME);
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && NICKNAME_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) NICKNAME_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Nickname Type: " + id);
            return null;
        }
    }

    public static final class ContactNoteUtil {
        private static final int COLUMN_VALUE = 0;

        public static final String getValue(RawContactData rawData) {
            return rawData.getData(0);
        }
    }

    public static final class ContactOrganizationUtil {
        private static final int COLUMN_DEPARTMENT = 4;
        private static final int COLUMN_DESCRIPTION = 5;
        private static final int COLUMN_LOCATION = 8;
        private static final int COLUMN_NAME = 0;
        private static final int COLUMN_PHONETIC = 7;
        private static final int COLUMN_SYMBOL = 6;
        private static final int COLUMN_TITLE = 3;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> ORGANIZATION_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(2), "other");
            temp.put(Integer.valueOf(COLUMN_TYPE), IdentityUtils.WORK_ORGANIZATION);
            temp.put(Integer.valueOf(COLUMN_NAME), "custom");
            ORGANIZATION_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getName(RawContactData rawData) {
            return rawData.getData(COLUMN_NAME);
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && ORGANIZATION_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) ORGANIZATION_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Organization Type: " + id);
            return null;
        }

        public static final String getTitle(RawContactData rawData) {
            return rawData.getData(COLUMN_TITLE);
        }

        public static final String getDepartment(RawContactData rawData) {
            return rawData.getData(COLUMN_DEPARTMENT);
        }

        public static final String getDescription(RawContactData rawData) {
            return rawData.getData(COLUMN_DESCRIPTION);
        }

        public static final String getSymbol(RawContactData rawData) {
            return rawData.getData(COLUMN_SYMBOL);
        }

        public static final String getPhoneticName(RawContactData rawData) {
            return rawData.getData(COLUMN_PHONETIC);
        }

        public static final String getLocation(RawContactData rawData) {
            return rawData.getData(COLUMN_LOCATION);
        }
    }

    public static final class ContactPhoneUtil {
        private static final int COLUMN_CUSTOM_TYPE = 2;
        private static final int COLUMN_PHONE_NUMBER = 0;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> PHONE_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(19), "assistant");
            temp.put(Integer.valueOf(8), "callback");
            temp.put(Integer.valueOf(9), "car");
            temp.put(Integer.valueOf(10), "mainCompany");
            temp.put(Integer.valueOf(5), "homeFax");
            temp.put(Integer.valueOf(4), "workFax");
            temp.put(Integer.valueOf(COLUMN_TYPE), "home");
            temp.put(Integer.valueOf(11), "isdn");
            temp.put(Integer.valueOf(12), "main");
            temp.put(Integer.valueOf(20), "mms");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_TYPE), "mobile");
            temp.put(Integer.valueOf(7), "other");
            temp.put(Integer.valueOf(13), "otherFax");
            temp.put(Integer.valueOf(6), "pager");
            temp.put(Integer.valueOf(14), "radio");
            temp.put(Integer.valueOf(15), "telex");
            temp.put(Integer.valueOf(16), "ttytdd");
            temp.put(Integer.valueOf(3), IdentityUtils.WORK_ORGANIZATION);
            temp.put(Integer.valueOf(17), "workMobile");
            temp.put(Integer.valueOf(18), "workPager");
            temp.put(Integer.valueOf(COLUMN_PHONE_NUMBER), "custom");
            PHONE_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getPhoneNumber(RawContactData rawData) {
            return rawData.getData(COLUMN_PHONE_NUMBER);
        }

        public static String getFormattedTypeString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id == null) {
                return null;
            }
            return Phone.getTypeLabel(context.getResources(), id.intValue(), rawData.getData(COLUMN_CUSTOM_TYPE)).toString();
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && PHONE_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) PHONE_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Phone Type: " + id);
            return null;
        }
    }

    public static final class ContactRelationUtil {
        private static final int COLUMN_CUSTOM_TYPE = 2;
        private static final int COLUMN_NAME = 0;
        private static final int COLUMN_TYPE = 1;
        private static final Map<Integer, String> RELATION_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(COLUMN_TYPE), "assistant");
            temp.put(Integer.valueOf(COLUMN_CUSTOM_TYPE), "brother");
            temp.put(Integer.valueOf(3), "child");
            temp.put(Integer.valueOf(4), "domesticPartner");
            temp.put(Integer.valueOf(5), "father");
            temp.put(Integer.valueOf(6), "friend");
            temp.put(Integer.valueOf(7), "manager");
            temp.put(Integer.valueOf(8), "mother");
            temp.put(Integer.valueOf(9), "parent");
            temp.put(Integer.valueOf(10), "partner");
            temp.put(Integer.valueOf(11), "referredBy");
            temp.put(Integer.valueOf(12), "relative");
            temp.put(Integer.valueOf(13), "sister");
            temp.put(Integer.valueOf(14), "spouse");
            temp.put(Integer.valueOf(COLUMN_NAME), "custom");
            RELATION_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getRelationshipName(RawContactData rawData) {
            return rawData.getData(COLUMN_NAME);
        }

        @TargetApi(11)
        public static String getFormattedTypeString(Context context, RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id == null || !PlatformVersion.isAtLeastHoneycomb()) {
                return null;
            }
            return Relation.getTypeLabel(context.getResources(), id.intValue(), rawData.getData(COLUMN_CUSTOM_TYPE)).toString();
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && RELATION_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) RELATION_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Relation Type: " + id);
            return null;
        }
    }

    public static final class ContactWebsiteUtil {
        private static final int COLUMN_TYPE = 1;
        private static final int COLUMN_URL = 0;
        private static final Map<Integer, String> WEBSITE_TYPE_NUM_TO_STRING;

        static {
            Map<Integer, String> temp = new HashMap();
            temp.put(Integer.valueOf(2), "blog");
            temp.put(Integer.valueOf(6), "ftp");
            temp.put(Integer.valueOf(4), "home");
            temp.put(Integer.valueOf(COLUMN_TYPE), "homePage");
            temp.put(Integer.valueOf(7), "other");
            temp.put(Integer.valueOf(3), Scopes.PROFILE);
            temp.put(Integer.valueOf(5), IdentityUtils.WORK_ORGANIZATION);
            temp.put(Integer.valueOf(0), "custom");
            WEBSITE_TYPE_NUM_TO_STRING = Collections.unmodifiableMap(temp);
        }

        public static final String getUrl(RawContactData rawData) {
            return rawData.getData(0);
        }

        public static String getTypeString(RawContactData rawData) {
            Integer id = ContactDataUtil.getId(rawData, COLUMN_TYPE);
            if (id != null && WEBSITE_TYPE_NUM_TO_STRING.containsKey(id)) {
                return (String) WEBSITE_TYPE_NUM_TO_STRING.get(id);
            }
            PeopleServiceLog.w(ContactDataUtil.TAG, "Invalid Organization Type: " + id);
            return null;
        }
    }

    public interface DataColumns {
        public static final int DATA1 = 0;
        public static final int DATA10 = 9;
        public static final int DATA11 = 10;
        public static final int DATA12 = 11;
        public static final int DATA13 = 12;
        public static final int DATA14 = 13;
        public static final int DATA15 = 14;
        public static final int DATA2 = 1;
        public static final int DATA3 = 2;
        public static final int DATA4 = 3;
        public static final int DATA5 = 4;
        public static final int DATA6 = 5;
        public static final int DATA7 = 6;
        public static final int DATA8 = 7;
        public static final int DATA9 = 8;
    }

    public interface ListColumns {
        public static final int ID = 0;
        public static final int NAME = 1;
        public static final int PHOTO = 2;
    }

    private static Integer getId(RawContactData rawData, int column) {
        String id = rawData.getData(column);
        if (!TextUtils.isEmpty(id)) {
            try {
                return Integer.valueOf(Integer.parseInt(id));
            } catch (NumberFormatException e) {
                PeopleServiceLog.w(TAG, "Invalid ID: " + rawData.getMimeType() + "[" + column + "] = " + id, e);
            }
        }
        return null;
    }

    public static boolean isQualifiedIdFromCP2(String qualifiedId) {
        return qualifiedId != null && qualifiedId.startsWith(CP2_CONTACT_ID_QUALIFIER);
    }

    public static String peopleQualifiedIdToCP2ContactId(String qualifiedId) {
        if (isQualifiedIdFromCP2(qualifiedId)) {
            return qualifiedId.substring(CP2_CONTACT_ID_QUALIFIER.length());
        }
        return null;
    }

    public static String cp2ContactIdToPeopleQualifiedId(String cp2Id) {
        return CP2_CONTACT_ID_QUALIFIER + cp2Id;
    }

    public static Set<String> retrieveContactsFromContactId(String contactId) {
        if (TextUtils.isEmpty(contactId)) {
            return Collections.emptySet();
        }
        Set<String> contactIds = new HashSet();
        contactIds.add(contactId);
        return contactIds;
    }

    public static boolean isContactPhotoUrl(String url) {
        return url.startsWith(CONTACT_PHOTO_URL_PREFIX);
    }

    public static boolean isQualifiedIdFromFocus(String qualifiedId) {
        return qualifiedId != null && qualifiedId.startsWith(FOCUS_ID_QUALIFIER);
    }

    public static boolean isQualifiedIdFromPhoneNumber(String qualifiedId) {
        return qualifiedId != null && qualifiedId.startsWith(PHONE_NUMBER_QUALIFIER);
    }

    public static String peopleQualifiedIdToFocusId(String qualifiedId) {
        if (isQualifiedIdFromFocus(qualifiedId)) {
            return qualifiedId.substring(FOCUS_ID_QUALIFIER.length());
        }
        return null;
    }

    public static String focusIdToPeopleQualifiedId(String focusId) {
        return FOCUS_ID_QUALIFIER + focusId;
    }

    public static String peopleQualifiedIdToPhoneNumber(String qualifiedId) {
        if (isQualifiedIdFromPhoneNumber(qualifiedId)) {
            return qualifiedId.substring(PHONE_NUMBER_QUALIFIER.length());
        }
        return null;
    }

    public static String phoneNumberToPeopleQualifiedId(String phoneNumber) {
        return PHONE_NUMBER_QUALIFIER + phoneNumber;
    }
}
