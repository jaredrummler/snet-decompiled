package com.google.android.gms.people.internal.agg;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.lint.BuildConfig;
import com.google.android.gms.people.internal.AutocompleteEntryRef.AcHolderColumns;
import com.google.android.gms.people.internal.IntToStringsMap;
import com.google.android.gms.people.internal.MultiIntArray;
import com.google.android.gms.people.internal.PeopleServiceLog;
import com.google.android.gms.people.internal.SelectionHelper;
import com.google.android.gms.people.internal.StringToIntsMap;
import com.google.android.gms.people.internal.agg.AggregationQueries.Contactables;
import com.google.android.gms.people.internal.agg.PeopleAggregator.Listener;
import com.google.android.gsf.TalkContract.PresenceColumns;
import com.google.android.snet.Csv;
import java.util.ArrayList;
import java.util.HashMap;

class PeopleSearchAggregator extends PeopleAggregator {
    private static final int CONTACT_SEARCH_MAX = 100;
    public static final String[] DATA_CONTACT_ID_PROJECTION;
    private final String mQuery;

    private static class CappedCountCursorWrapper extends CursorWrapper {
        private int mMaxCount;

        public CappedCountCursorWrapper(Cursor cursor, int maxCount) {
            super(cursor);
            this.mMaxCount = maxCount;
        }

        public int getCount() {
            return Math.min(super.getCount(), this.mMaxCount);
        }
    }

    public PeopleSearchAggregator(Context context, Listener listener, boolean includeInvisible, int extraColumns, Bundle emailTypeMap, Bundle phoneTypeMap, String query) {
        super(context, listener, includeInvisible, extraColumns, emailTypeMap, phoneTypeMap, null);
        this.mQuery = query;
    }

    static {
        DATA_CONTACT_ID_PROJECTION = new String[]{PresenceColumns.CONTACT_ID};
    }

    private Cursor searchContacts() {
        Cursor phoneCursor = this.mContext.getContentResolver().query(Phone.CONTENT_FILTER_URI.buildUpon().appendPath(this.mQuery).appendQueryParameter("limit", Integer.toString(CONTACT_SEARCH_MAX)).build(), DATA_CONTACT_ID_PROJECTION, AggregationQueries.DATA1_NOT_EMPTY_SELECTION, null, null);
        Cursor emailCursor = this.mContext.getContentResolver().query(Email.CONTENT_FILTER_URI.buildUpon().appendPath(this.mQuery).appendQueryParameter("limit", Integer.toString(CONTACT_SEARCH_MAX)).build(), DATA_CONTACT_ID_PROJECTION, AggregationQueries.DATA1_NOT_EMPTY_SELECTION, null, null);
        Cursor wrappedPhoneCursor = new CappedCountCursorWrapper(phoneCursor, CONTACT_SEARCH_MAX);
        Cursor wrappedEmailCursor = new CappedCountCursorWrapper(emailCursor, CONTACT_SEARCH_MAX);
        return new MergeCursor(new Cursor[]{wrappedPhoneCursor, wrappedEmailCursor});
    }

    protected Cursor queryContacts() {
        Cursor c;
        SelectionHelper sh;
        if (!sUseContactablesApi || VERSION.SDK_INT < 18) {
            sh = new SelectionHelper();
            AggregationQueries.addBaseSelection(sh, this.mIncludeInvisible, this.mContext);
            AggregationQueries.addMimeTypeSelectionEmailPhoneGaia(sh);
            this.mStopwatch.lap("lookup start");
            Cursor searchedCursor = searchContacts();
            if (searchedCursor == null) {
                return null;
            }
            try {
                int count = searchedCursor.getCount();
                this.mStopwatch.lap("lookup finish");
                if (count == 0) {
                    return null;
                }
                sh.addWithAnd("contact_id IN (");
                String sep = BuildConfig.VERSION_NAME;
                while (searchedCursor.moveToNext()) {
                    sh.addRawString(sep);
                    sh.addRawString(Long.toString(searchedCursor.getLong(0)));
                    sep = Csv.COMMA;
                }
                sh.addRawString(")");
                searchedCursor.close();
                c = this.mContext.getContentResolver().query(Data.CONTENT_URI, AggregationQueries.DATA_PROJECTION, sh.toString(), null, AggregationQueries.DISPLAY_NAME_SORT_ORDER);
            } finally {
                searchedCursor.close();
            }
        } else {
            Uri uri = Contactables.CONTENT_FILTER_URI.buildUpon().appendPath(this.mQuery).appendQueryParameter(Contactables.VISIBLE_CONTACTS_ONLY, String.valueOf(!this.mIncludeInvisible)).build();
            sh = new SelectionHelper();
            sh.addWithAnd(AggregationQueries.getPlusExcludingSelection());
            sh.addWithAnd(AggregationQueries.DATA1_NOT_EMPTY_SELECTION);
            c = this.mContext.getContentResolver().query(uri, AggregationQueries.DATA_PROJECTION, sh.toString(), null, AggregationQueries.DISPLAY_NAME_SORT_ORDER);
        }
        if (c == null) {
            return c;
        }
        c.getCount();
        return c;
    }

    protected AggregatedPersonBufferImpl aggregateInner(DataHolderWrapper people, DataHolderWrapper infoGaiaMap, Cursor contacts) {
        Preconditions.checkNotNull(people);
        Preconditions.checkNotNull(contacts);
        MultiIntArray resultPeoplePos = new MultiIntArray();
        MultiIntArray resultContactPos = new MultiIntArray();
        int peopleCount = people.getCount();
        int contacCursorCount = contacts.getCount();
        HashMap<String, Integer> peopleGaiaToPos = new HashMap();
        this.mStopwatch.lap("people-map start");
        PeopleAggregator.buildPeopleGaiaIdMap(people, peopleGaiaToPos);
        this.mStopwatch.lap("people-map finish");
        StringToIntsMap contactGaiaToPos = new StringToIntsMap();
        IntToStringsMap contactPosToGaiaIds = new IntToStringsMap();
        HashMap<String, String> contactInfoToGaiaMap = new HashMap();
        buildInfoToGaiaIdMap(infoGaiaMap, contactInfoToGaiaMap);
        this.mStopwatch.lap("contact-map start");
        int contactsCounts = contactBuildGaiaIdMap(contacts, contactGaiaToPos, contactPosToGaiaIds, contactInfoToGaiaMap);
        this.mStopwatch.lap("contact-map finish");
        if (PeopleServiceLog.canDebugLog()) {
            PeopleServiceLog.d("PeopleAggregator", "#people=" + peopleCount + ", #contacts=" + contactsCounts);
        }
        this.mStopwatch.lap("merge start");
        ArrayList<String> resultGaiaIds = ArrayUtils.newArrayList();
        people.moveToPosition(-1);
        while (people.moveToNext()) {
            int peoplePos = people.getPosition();
            String peopleGaia = people.getString(AcHolderColumns.GAIA_ID);
            resultPeoplePos.add(peoplePos);
            resultGaiaIds.add(peopleGaia);
            if (peopleGaia == null || contactGaiaToPos.getValueCount(peopleGaia) == 0) {
                resultContactPos.addEmptyRow();
            } else {
                resultContactPos.addAllValues(contactGaiaToPos, peopleGaia);
            }
        }
        contacts.moveToPosition(0);
        while (!contacts.isAfterLast()) {
            int contactPos = contacts.getPosition();
            int contactGaiaIdCount = contactPosToGaiaIds.getValueCount(contactPos);
            if (contactGaiaIdCount == 0) {
                resultPeoplePos.addEmptyRow();
                resultContactPos.add(contactPos);
                resultGaiaIds.add(null);
            } else {
                for (int i = 0; i < contactGaiaIdCount; i++) {
                    String contactGaia = contactPosToGaiaIds.getValue(contactPos, i);
                    if (!peopleGaiaToPos.containsKey(contactGaia)) {
                        resultPeoplePos.addEmptyRow();
                        resultContactPos.add(contactPos);
                        resultGaiaIds.add(contactGaia);
                    }
                }
            }
            AggregationQueries.advanceToNextContact(contacts);
        }
        this.mStopwatch.lap("merge finish");
        return new AggregatedPersonBufferImpl(people.holder, contacts, this.mContext, resultPeoplePos.size(), resultPeoplePos, resultContactPos, resultGaiaIds, contactInfoToGaiaMap, this.mExtraColumns, this.mEmailTypeMap, this.mPhoneTypeMap);
    }
}
