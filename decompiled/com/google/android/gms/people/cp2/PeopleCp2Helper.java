package com.google.android.gms.people.cp2;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.PeopleConstants.PeoplePostalAddress;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PeopleUtils;
import com.google.android.gsf.TalkContract.PresenceColumns;

public class PeopleCp2Helper {
    private static final String[] CONTACT_ID_ON_RAW_CONTACTS_PROJECTION;
    @VisibleForTesting
    static final String[] CONTACT_PHOTO_ID_PROJECTION;
    @VisibleForTesting
    static final String[] DATA_PHOTO_BITMAP_PROJECTION;
    private static final String[] ID_PROJECTION;
    private static final String[] LOOKUP_PROJECTION;
    private static final String[] PHOTO_URI_PROJECTION;
    private static final String RAW_CONTACT_BY_FOCUS_ID_SELECTION_GB = "account_name=?1 AND account_type='com.google' AND sourceid=?2";
    private static final String RAW_CONTACT_BY_FOCUS_ID_SELECTION_ICS = "account_name=?1 AND account_type='com.google' AND sourceid=?2 AND data_set IS NULL";
    private static final String RAW_CONTACT_BY_GAIA_ID_SELECTION_ICS = "account_name=?1 AND account_type='com.google' AND sourceid=?2 AND data_set IS 'plus'";
    public static final String TAG = "PeopleCp2Helper";

    private PeopleCp2Helper() {
    }

    static {
        ID_PROJECTION = new String[]{PeoplePostalAddress._ID};
        CONTACT_ID_ON_RAW_CONTACTS_PROJECTION = new String[]{PresenceColumns.CONTACT_ID};
        LOOKUP_PROJECTION = new String[]{"lookup"};
        PHOTO_URI_PROJECTION = new String[]{"photo_uri"};
        CONTACT_PHOTO_ID_PROJECTION = new String[]{"photo_id"};
        DATA_PHOTO_BITMAP_PROJECTION = new String[]{"data15"};
    }

    public static long findRawContactByFocusId(Context context, String account, String focusId) {
        return findLongByFocusId(context, account, focusId, ID_PROJECTION);
    }

    public static long findContactByFocusId(Context context, String account, String focusId) {
        return findLongByFocusId(context, account, focusId, CONTACT_ID_ON_RAW_CONTACTS_PROJECTION);
    }

    private static long findLongByFocusId(Context context, String account, String originalFocusId, String[] projection) {
        if (TextUtils.isEmpty(account)) {
            return -1;
        }
        Cursor cursor = context.getContentResolver().query(RawContacts.CONTENT_URI, projection, VERSION.SDK_INT >= 15 ? RAW_CONTACT_BY_FOCUS_ID_SELECTION_ICS : RAW_CONTACT_BY_FOCUS_ID_SELECTION_GB, PeopleUtils.getTemporaryStrings2(account, PeopleUtils.stripLeadingZeros(originalFocusId)), null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "Contacts query failed.");
            return -1;
        }
        try {
            if (cursor.moveToFirst()) {
                long j = (long) cursor.getInt(0);
                return j;
            }
            cursor.close();
            PeopleServiceLog.d(TAG, "Raw contact not found.");
            return -1;
        } finally {
            cursor.close();
        }
    }

    public static long findContactByGaiaId(Context context, String account, String gaiaId) {
        return findLongByGaiaId(context, account, gaiaId, CONTACT_ID_ON_RAW_CONTACTS_PROJECTION);
    }

    private static long findLongByGaiaId(Context context, String account, String gaiaId, String[] projection) {
        if (TextUtils.isEmpty(account)) {
            return -1;
        }
        if (VERSION.SDK_INT < 15) {
            return -1;
        }
        String[] strArr = projection;
        Cursor cursor = context.getContentResolver().query(RawContacts.CONTENT_URI, strArr, RAW_CONTACT_BY_GAIA_ID_SELECTION_ICS, PeopleUtils.getTemporaryStrings2(account, PeopleUtils.gaiaIdToPeopleQualifiedId(gaiaId)), null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "Contacts query failed.");
            return -1;
        }
        try {
            if (cursor.moveToFirst()) {
                long j = (long) cursor.getInt(0);
                return j;
            }
            cursor.close();
            PeopleServiceLog.d(TAG, "Raw contact not found.");
            return -1;
        } finally {
            cursor.close();
        }
    }

    public static String findPhotoUriByFocusId(Context context, String account, String focusId) {
        return findStringByAndroidContactId(context, findContactByFocusId(context, account, focusId), PHOTO_URI_PROJECTION);
    }

    public static Uri getContactLookupUriFromAndroidContactId(Context context, long contactId) {
        String lookupKey = findStringByAndroidContactId(context, contactId, LOOKUP_PROJECTION);
        if (lookupKey == null) {
            return null;
        }
        return Contacts.CONTENT_LOOKUP_URI.buildUpon().appendPath(lookupKey).appendPath(String.valueOf(contactId)).build();
    }

    private static String findStringByAndroidContactId(Context context, long androidContactId, String[] projection) {
        if (androidContactId < 0) {
            return null;
        }
        Cursor cursor = context.getContentResolver().query(ContentUris.withAppendedId(Contacts.CONTENT_URI, androidContactId), projection, null, null, null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "Contacts query failed.");
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                String result = cursor.getString(0);
                if (TextUtils.isEmpty(result)) {
                    PeopleServiceLog.d(TAG, "Contacts query with projection = " + projection.toString() + " not found.");
                    return null;
                }
                cursor.close();
                return result;
            }
            cursor.close();
            PeopleServiceLog.d(TAG, "Contact not found.");
            return null;
        } finally {
            cursor.close();
        }
    }

    public static byte[] loadContactPictureForRawContact(Context context, long rawContactId, boolean thumbnail) {
        byte[] bArr = null;
        if (!thumbnail) {
            PeopleServiceLog.w(TAG, "Large contact picture not supported yet.");
        }
        Cursor cursor = context.getContentResolver().query(Data.CONTENT_URI, DATA_PHOTO_BITMAP_PROJECTION, "mimetype='vnd.android.cursor.item/photo' AND raw_contact_id=?", PeopleUtils.getTemporaryStrings1(String.valueOf(rawContactId)), null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "Contacts query failed.");
        } else {
            try {
                if (cursor.moveToFirst()) {
                    bArr = cursor.getBlob(0);
                } else {
                    PeopleServiceLog.d(TAG, "Unable to load thumbnail.");
                    cursor.close();
                }
            } finally {
                cursor.close();
            }
        }
        return bArr;
    }

    public static byte[] loadContactImage(Context context, long contactId, boolean thumbnail) {
        if (!thumbnail) {
            PeopleServiceLog.w(TAG, "Large contact picture not supported yet.");
        }
        long thumbnailId = getContactThumbnailId(context, contactId);
        if (thumbnailId >= 0) {
            return loadContactImageByThumbnailId(context, thumbnailId);
        }
        PeopleServiceLog.d(TAG, "Contact has no thumbnail.");
        return null;
    }

    @VisibleForTesting
    static byte[] loadContactImageByThumbnailId(Context context, long thumbnailId) {
        byte[] bArr = null;
        Cursor cursor = context.getContentResolver().query(ContentUris.withAppendedId(Data.CONTENT_URI, thumbnailId), DATA_PHOTO_BITMAP_PROJECTION, null, null, null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "Contacts query failed.");
        } else {
            try {
                if (cursor.moveToFirst()) {
                    bArr = cursor.getBlob(0);
                } else {
                    PeopleServiceLog.d(TAG, "Unable to load thumbnail.");
                    cursor.close();
                }
            } finally {
                cursor.close();
            }
        }
        return bArr;
    }

    @VisibleForTesting
    static long getContactThumbnailId(Context context, long contactId) {
        Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, contactId);
        ContentResolver contentResolver = context.getContentResolver();
        long j = CONTACT_PHOTO_ID_PROJECTION;
        Cursor cursor = contentResolver.query(uri, j, null, null, null);
        if (cursor == null) {
            PeopleServiceLog.w(TAG, "Contacts query failed.");
            return -1;
        }
        try {
            if (cursor.moveToFirst()) {
                j = (long) cursor.getInt(0);
                return j;
            }
            cursor.close();
            return -1;
        } finally {
            cursor.close();
        }
    }
}
