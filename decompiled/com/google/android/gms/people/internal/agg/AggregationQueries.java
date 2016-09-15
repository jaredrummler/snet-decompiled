package com.google.android.gms.people.internal.agg;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Groups;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.PhoneNumberEntryRef.PhoneNumberHolderColumns;
import com.google.android.gms.people.internal.SelectionHelper;
import com.google.android.gsf.TalkContract.PresenceColumns;

@VisibleForTesting
public final class AggregationQueries {
    public static final int CONTACT_COLUMN_CONTACT_ID = 0;
    public static final int CONTACT_COLUMN_DISPLAY_NAME = 1;
    public static final int CONTACT_COLUMN_MIMETYPE = 2;
    public static final int CONTACT_COLUMN_RAW_DATA_1 = 3;
    public static final int CONTACT_COLUMN_RAW_DATA_2 = 4;
    public static final int CONTACT_COLUMN_RAW_DATA_3 = 5;
    public static final String DATA1_NOT_EMPTY_SELECTION = "(data1 IS NOT NULL AND data1!='')";
    public static final String[] DATA_PROJECTION;
    private static final String DEFAULT_DIRECTORY_TABLE = "default_directory";
    public static final String DISPLAY_NAME_SORT_ORDER = "display_name COLLATE LOCALIZED,contact_id";
    private static boolean sDefaultDirectoryChecked;
    private static boolean sDefaultDirectoryExists;

    public interface Contactables {
        public static final Uri CONTENT_FILTER_URI;
        public static final Uri CONTENT_URI;
        public static final String VISIBLE_CONTACTS_ONLY = "visible_contacts_only";

        static {
            CONTENT_URI = Uri.withAppendedPath(Data.CONTENT_URI, "contactables");
            CONTENT_FILTER_URI = Uri.withAppendedPath(CONTENT_URI, "filter");
        }
    }

    private AggregationQueries() {
    }

    static {
        DATA_PROJECTION = new String[]{PresenceColumns.CONTACT_ID, PhoneNumberHolderColumns.CONTACT_NAME, "mimetype", "data1", "data2", "data3"};
        sDefaultDirectoryExists = false;
        sDefaultDirectoryChecked = false;
    }

    private static final synchronized boolean isDefaultDirectoryTableAvailable(Context context) {
        boolean z;
        synchronized (AggregationQueries.class) {
            if (sDefaultDirectoryChecked) {
                z = sDefaultDirectoryExists;
            } else {
                sDefaultDirectoryChecked = true;
                Cursor c = null;
                try {
                    c = context.getContentResolver().query(Groups.CONTENT_URI, null, "EXISTS (SELECT _id FROM default_directory LIMIT 1)", null, null);
                    if (c != null) {
                        sDefaultDirectoryExists = true;
                    }
                    if (c != null) {
                        c.close();
                    }
                } catch (Exception e) {
                    PeopleServiceLog.w("PeopleAggregator", "Error occurred when checking for default_directory table.");
                    PeopleServiceLog.d("PeopleAggregator", e.getMessage());
                    if (c != null) {
                        c.close();
                    }
                } catch (Throwable th) {
                    if (c != null) {
                        c.close();
                    }
                }
                z = sDefaultDirectoryExists;
            }
        }
        return z;
    }

    public static final void addBaseSelection(SelectionHelper sh, boolean includeInvisible, Context context) {
        if (!includeInvisible) {
            if (VERSION.SDK_INT < 11) {
                sh.addWithAnd("(in_visible_group=1)");
            } else if (isDefaultDirectoryTableAvailable(context)) {
                sh.addWithAnd("(contact_id IN (SELECT _id FROM default_directory))");
            }
        }
        String plusExcludingSelection = getPlusExcludingSelection();
        if (!TextUtils.isEmpty(plusExcludingSelection)) {
            sh.addWithAnd(plusExcludingSelection);
        }
    }

    public static final String getPlusExcludingSelection() {
        if (VERSION.SDK_INT < 14) {
            return null;
        }
        return "((data_set IS NULL) OR (account_type='com.google' AND data_set!='plus'))";
    }

    public static final void addMimeTypeSelectionEmailPhoneGaia(SelectionHelper sh) {
        sh.addWithAnd("(mimetype IN ('vnd.android.cursor.item/email_v2','vnd.android.cursor.item/phone_v2'))");
    }

    public static boolean advanceToNextContact(Cursor contacts) {
        if (contacts.isAfterLast()) {
            return false;
        }
        long startId = contacts.getLong(CONTACT_COLUMN_CONTACT_ID);
        while (contacts.moveToNext()) {
            if (startId != contacts.getLong(CONTACT_COLUMN_CONTACT_ID)) {
                return true;
            }
        }
        return false;
    }

    public static boolean advanceToNextData(Cursor contacts) {
        boolean z = true;
        Preconditions.checkArgument(!contacts.isBeforeFirst());
        if (contacts.isAfterLast()) {
            return false;
        }
        long startId = contacts.getLong(CONTACT_COLUMN_CONTACT_ID);
        if (!contacts.moveToNext()) {
            return false;
        }
        if (startId != contacts.getLong(CONTACT_COLUMN_CONTACT_ID)) {
            z = false;
        }
        return z;
    }
}
