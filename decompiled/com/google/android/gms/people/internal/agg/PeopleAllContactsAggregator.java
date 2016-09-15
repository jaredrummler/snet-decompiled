package com.google.android.gms.people.internal.agg;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import com.google.android.gms.common.data.DataHolder;
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
import com.google.android.gsf.TalkContract.AccountSettingsColumns;
import com.google.android.gsf.TalkContract.PresenceColumns;
import com.google.android.snet.Csv;
import java.util.ArrayList;
import java.util.HashMap;

class PeopleAllContactsAggregator extends PeopleAggregator {
    private static final String[] CONTACT_ID_PROJECTION;

    public PeopleAllContactsAggregator(Context context, Listener listener, boolean includeInvisible, int extraColumns, Bundle emailTypeMap, Bundle phoneTypeMap, String filterGaiaId) {
        super(context, listener, includeInvisible, extraColumns, emailTypeMap, phoneTypeMap, filterGaiaId);
    }

    protected Cursor queryContacts() {
        String filterBasedOnGaiaId = buildFilterFromGaiaMap();
        if (filterBasedOnGaiaId == null) {
            return null;
        }
        Cursor c;
        SelectionHelper sh;
        if (!sUseContactablesApi || VERSION.SDK_INT < 18) {
            sh = new SelectionHelper();
            AggregationQueries.addBaseSelection(sh, this.mIncludeInvisible, this.mContext);
            AggregationQueries.addMimeTypeSelectionEmailPhoneGaia(sh);
            sh.addWithAnd(filterBasedOnGaiaId);
            sh.addWithAnd(AggregationQueries.DATA1_NOT_EMPTY_SELECTION);
            c = this.mContext.getContentResolver().query(Data.CONTENT_URI, AggregationQueries.DATA_PROJECTION, sh.toString(), null, AggregationQueries.DISPLAY_NAME_SORT_ORDER);
        } else {
            Uri uri = Contactables.CONTENT_URI.buildUpon().appendQueryParameter(Contactables.VISIBLE_CONTACTS_ONLY, String.valueOf(!this.mIncludeInvisible)).build();
            sh = new SelectionHelper();
            sh.addWithAnd(AggregationQueries.getPlusExcludingSelection());
            sh.addWithAnd(filterBasedOnGaiaId);
            sh.addWithAnd(AggregationQueries.DATA1_NOT_EMPTY_SELECTION);
            c = this.mContext.getContentResolver().query(uri, AggregationQueries.DATA_PROJECTION, sh.toString(), null, AggregationQueries.DISPLAY_NAME_SORT_ORDER);
        }
        if (c != null) {
            c.getCount();
        }
        return c;
    }

    static {
        CONTACT_ID_PROJECTION = new String[]{PresenceColumns.CONTACT_ID};
    }

    private String buildFilterFromGaiaMap() {
        if (!this.mFilteringByGaiaId) {
            return BuildConfig.VERSION_NAME;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("contact_id IN(");
        Cursor c = this.mContext.getContentResolver().query(Data.CONTENT_URI, CONTACT_ID_PROJECTION, buildFilterFromGaiaMapDataSelection(), null, null);
        if (c == null) {
            return null;
        }
        boolean first = true;
        while (c.moveToNext()) {
            try {
                if (!first) {
                    sb.append(Csv.COMMA);
                }
                first = false;
                sb.append(c.getLong(0));
            } finally {
                c.close();
            }
        }
        sb.append(")");
        return sb.toString();
    }

    private String buildFilterFromGaiaMapDataSelection() {
        Preconditions.checkState(this.mFilteringByGaiaId);
        DataHolder dh = getGaiaMapHolder();
        Preconditions.checkNotNull(dh);
        StringBuilder sb = new StringBuilder();
        sb.append("data1 IN(");
        DataHolderWrapper dhw = new DataHolderWrapper(dh);
        boolean first = true;
        while (dhw.moveToNext()) {
            if (!first) {
                sb.append(Csv.COMMA);
            }
            first = false;
            DatabaseUtils.appendEscapedSQLString(sb, dhw.getString(AccountSettingsColumns.VALUE));
        }
        sb.append(")");
        return sb.toString();
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
        people.moveToPosition(0);
        contacts.moveToPosition(0);
        ArrayList<String> resultGaiaIds = ArrayUtils.newArrayList();
        while (true) {
            boolean hasPerson = !people.isAfterLast();
            boolean hasContact = !contacts.isAfterLast();
            if (hasPerson || hasContact) {
                int cmp;
                String peopleName = hasPerson ? people.getString(AccountSettingsColumns.NAME) : null;
                String contactName = hasContact ? contacts.getString(1) : null;
                if (hasPerson && hasContact) {
                    cmp = compareLocalized(peopleName, contactName);
                } else {
                    cmp = hasPerson ? -1 : 1;
                }
                if (cmp <= 0) {
                    int peoplePos = people.getPosition();
                    String peopleGaia = people.getString(AcHolderColumns.GAIA_ID);
                    resultPeoplePos.add(peoplePos);
                    resultGaiaIds.add(peopleGaia);
                    if (peopleGaia == null || contactGaiaToPos.getValueCount(peopleGaia) == 0) {
                        resultContactPos.addEmptyRow();
                    } else {
                        resultContactPos.addAllValues(contactGaiaToPos, peopleGaia);
                    }
                    people.moveToNext();
                }
                if (cmp >= 0) {
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
            } else {
                this.mStopwatch.lap("merge finish");
                return new AggregatedPersonBufferImpl(people.holder, contacts, this.mContext, resultPeoplePos.size(), resultPeoplePos, resultContactPos, resultGaiaIds, contactInfoToGaiaMap, this.mExtraColumns, this.mEmailTypeMap, this.mPhoneTypeMap);
            }
        }
    }
}
